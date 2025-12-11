/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FileService
 *	@CreatedOn	: 06-26-2023
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Service
 *	@Layer		: Service
 *	@Package	: System / Utility
 *
 **/

package dz.mdn.iaas.system.utility.service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.exception.ResourceNotFoundException;
import dz.mdn.iaas.system.utility.dto.FileDTO;
import dz.mdn.iaas.system.utility.model.File;
import dz.mdn.iaas.system.utility.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class FileService extends GenericService<File, FileDTO, Long> {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        super(fileRepository);
        this.fileRepository = fileRepository;
    }

    // ========== ABSTRACT METHOD IMPLEMENTATIONS ==========

    @Override
    protected FileDTO convertToDTO(File entity) {
        return FileDTO.fromEntity(entity);
    }

    @Override
    protected File convertToEntity(FileDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(File entity, FileDTO dto) {
        dto.updateEntity(entity);
    }

    @Override
    protected void validateCreate(FileDTO dto) {
        log.debug("Validating File creation: {}", dto);
        
        // Check if path already exists
        if (fileRepository.existsByPath(dto.getPath())) {
            throw new IllegalArgumentException("File with path '" + dto.getPath() + "' already exists");
        }

        // Validate extension
        if (dto.getExtension() == null || dto.getExtension().trim().isEmpty()) {
            throw new IllegalArgumentException("File extension is required");
        }

        // Validate path
        if (dto.getPath() == null || dto.getPath().trim().isEmpty()) {
            throw new IllegalArgumentException("File path is required");
        }

        // Validate size
        if (dto.getSize() != null && dto.getSize() < 0) {
            throw new IllegalArgumentException("File size cannot be negative");
        }
    }

    @Override
    protected void validateUpdate(Long id, FileDTO dto) {
        log.debug("Validating File update for id: {}", id);
        
        // Check if file exists
        if (!fileRepository.existsById(id)) {
            throw new ResourceNotFoundException("File not found with id: " + id);
        }

        // Check if new path conflicts with existing file
        if (dto.getPath() != null) {
            fileRepository.findByPath(dto.getPath()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new IllegalArgumentException("File with path '" + dto.getPath() + "' already exists");
                }
            });
        }

        // Validate size if provided
        if (dto.getSize() != null && dto.getSize() < 0) {
            throw new IllegalArgumentException("File size cannot be negative");
        }
    }

    @Override
    public Page<FileDTO> globalSearch(String query, Pageable pageable) {
        log.debug("Searching files with query: {}", query);
        
        if (query == null || query.trim().isEmpty()) {
            return fileRepository.findAll(pageable).map(this::convertToDTO);
        }
        
        return fileRepository.searchFiles(query.trim(), pageable).map(this::convertToDTO);
    }

    // ========== CUSTOM BUSINESS METHODS ==========

    /**
     * Find file by path
     */
    @Transactional(readOnly = true)
    public FileDTO findByPath(String path) {
        log.debug("Finding file by path: {}", path);
        return fileRepository.findByPath(path)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with path: " + path));
    }

    /**
     * Find files by extension
     */
    @Transactional(readOnly = true)
    public List<FileDTO> findByExtension(String extension) {
        log.debug("Finding files by extension: {}", extension);
        return fileRepository.findByExtension(extension)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find files by file type
     */
    @Transactional(readOnly = true)
    public List<FileDTO> findByFileType(String fileType) {
        log.debug("Finding files by file type: {}", fileType);
        return fileRepository.findByFileType(fileType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find files by extension and file type
     */
    @Transactional(readOnly = true)
    public List<FileDTO> findByExtensionAndFileType(String extension, String fileType) {
        log.debug("Finding files by extension: {} and file type: {}", extension, fileType);
        return fileRepository.findByExtensionAndFileType(extension, fileType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find large files
     */
    @Transactional(readOnly = true)
    public List<FileDTO> findLargeFiles(Long minimumSize) {
        log.debug("Finding files larger than: {} bytes", minimumSize);
        return fileRepository.findBySizeGreaterThan(minimumSize)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get total storage size
     */
    @Transactional(readOnly = true)
    public Long getTotalStorageSize() {
        log.debug("Calculating total storage size");
        Long total = fileRepository.getTotalStorageSize();
        return total != null ? total : 0L;
    }

    /**
     * Get storage statistics
     */
    @Transactional(readOnly = true)
    public FileStorageStats getStorageStats() {
        log.debug("Getting storage statistics");
        
        long totalFiles = fileRepository.count();
        Long totalSize = getTotalStorageSize();
        
        return new FileStorageStats(totalFiles, totalSize);
    }

    /**
     * Count files by extension
     */
    @Transactional(readOnly = true)
    public long countByExtension(String extension) {
        log.debug("Counting files by extension: {}", extension);
        return fileRepository.countByExtension(extension);
    }

    // Inner class for storage statistics
    public record FileStorageStats(long totalFiles, Long totalSize) {
        public String getFormattedTotalSize() {
            if (totalSize == null || totalSize == 0) return "0 B";
            if (totalSize < 1024) return totalSize + " B";
            if (totalSize < 1024 * 1024) return String.format("%.1f KB", totalSize / 1024.0);
            if (totalSize < 1024 * 1024 * 1024) return String.format("%.1f MB", totalSize / (1024.0 * 1024));
            return String.format("%.1f GB", totalSize / (1024.0 * 1024 * 1024));
        }
    }
}
