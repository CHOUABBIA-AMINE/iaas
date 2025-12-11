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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class FileService extends GenericService<File, FileDTO, Long> {

    private final FileRepository fileRepository;
    private final Path fileStorageLocation;

    public FileService(
            FileRepository fileRepository,
            @Value("${file.upload-dir:uploads}") String uploadDir) {
        super(fileRepository);
        this.fileRepository = fileRepository;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        
        try {
            Files.createDirectories(this.fileStorageLocation);
            log.info("File storage location initialized: {}", this.fileStorageLocation);
        } catch (Exception ex) {
            log.error("Could not create upload directory!", ex);
            throw new RuntimeException("Could not create upload directory!", ex);
        }
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
        
        if (fileRepository.existsByPath(dto.getPath())) {
            throw new IllegalArgumentException("File with path '" + dto.getPath() + "' already exists");
        }

        if (dto.getExtension() == null || dto.getExtension().trim().isEmpty()) {
            throw new IllegalArgumentException("File extension is required");
        }

        if (dto.getPath() == null || dto.getPath().trim().isEmpty()) {
            throw new IllegalArgumentException("File path is required");
        }

        if (dto.getSize() != null && dto.getSize() < 0) {
            throw new IllegalArgumentException("File size cannot be negative");
        }
    }

    @Override
    protected void validateUpdate(Long id, FileDTO dto) {
        log.debug("Validating File update for id: {}", id);
        
        if (!fileRepository.existsById(id)) {
            throw new ResourceNotFoundException("File not found with id: " + id);
        }

        if (dto.getPath() != null) {
            fileRepository.findByPath(dto.getPath()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new IllegalArgumentException("File with path '" + dto.getPath() + "' already exists");
                }
            });
        }

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

    // ========== PHYSICAL FILE STORAGE OPERATIONS ==========

    /**
     * Upload physical file and create metadata
     */
    @Transactional
    public FileDTO uploadFile(MultipartFile file, String fileType) {
        log.info("Uploading file: {}", file.getOriginalFilename());
        
        try {
            // Validate file
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Cannot upload empty file");
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);
            String uniqueFilename = generateUniqueFilename(originalFilename);
            
            // Create subdirectory based on date
            String dateSubdir = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            Path targetLocation = fileStorageLocation.resolve(dateSubdir).normalize();
            Files.createDirectories(targetLocation);
            
            // Store physical file
            Path filePath = targetLocation.resolve(uniqueFilename);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            
            log.info("File stored at: {}", filePath);
            
            // Create metadata
            FileDTO fileDTO = FileDTO.builder()
                    .extension(extension)
                    .size(file.getSize())
                    .path(dateSubdir + "/" + uniqueFilename)
                    .fileType(fileType)
                    .build();
            
            return create(fileDTO);
            
        } catch (IOException ex) {
            log.error("Failed to store file: {}", file.getOriginalFilename(), ex);
            throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), ex);
        }
    }

    /**
     * Download physical file
     */
    @Transactional(readOnly = true)
    public Resource downloadFile(Long fileId) {
        log.info("Downloading file with id: {}", fileId);
        
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id: " + fileId));
        
        try {
            Path filePath = fileStorageLocation.resolve(file.getPath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("File not found or not readable: " + file.getPath());
            }
        } catch (MalformedURLException ex) {
            log.error("Invalid file path: {}", file.getPath(), ex);
            throw new RuntimeException("Invalid file path: " + file.getPath(), ex);
        }
    }

    /**
     * Delete physical file and metadata
     */
    @Transactional
    public void deleteFilePhysically(Long fileId) {
        log.info("Deleting file physically with id: {}", fileId);
        
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id: " + fileId));
        
        try {
            // Delete physical file
            Path filePath = fileStorageLocation.resolve(file.getPath()).normalize();
            Files.deleteIfExists(filePath);
            log.info("Physical file deleted: {}", filePath);
            
            // Delete metadata
            fileRepository.delete(file);
            log.info("File metadata deleted for id: {}", fileId);
            
        } catch (IOException ex) {
            log.error("Failed to delete physical file: {}", file.getPath(), ex);
            throw new RuntimeException("Failed to delete physical file: " + file.getPath(), ex);
        }
    }

    /**
     * Check if physical file exists
     */
    @Transactional(readOnly = true)
    public boolean physicalFileExists(Long fileId) {
        log.debug("Checking physical file existence for id: {}", fileId);
        
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id: " + fileId));
        
        Path filePath = fileStorageLocation.resolve(file.getPath()).normalize();
        return Files.exists(filePath);
    }

    /**
     * Get physical file size
     */
    @Transactional(readOnly = true)
    public Long getPhysicalFileSize(Long fileId) {
        log.debug("Getting physical file size for id: {}", fileId);
        
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id: " + fileId));
        
        try {
            Path filePath = fileStorageLocation.resolve(file.getPath()).normalize();
            return Files.size(filePath);
        } catch (IOException ex) {
            log.error("Failed to get file size: {}", file.getPath(), ex);
            return null;
        }
    }

    // ========== HELPER METHODS ==========

    private String generateUniqueFilename(String originalFilename) {
        String name = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
        String extension = getFileExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return sanitizeFilename(name) + "_" + uuid + "." + extension;
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }

    private String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    // ========== CUSTOM BUSINESS METHODS ==========

    @Transactional(readOnly = true)
    public FileDTO findByPath(String path) {
        log.debug("Finding file by path: {}", path);
        return fileRepository.findByPath(path)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with path: " + path));
    }

    @Transactional(readOnly = true)
    public List<FileDTO> findByExtension(String extension) {
        log.debug("Finding files by extension: {}", extension);
        return fileRepository.findByExtension(extension)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FileDTO> findByFileType(String fileType) {
        log.debug("Finding files by file type: {}", fileType);
        return fileRepository.findByFileType(fileType)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long getTotalStorageSize() {
        log.debug("Calculating total storage size");
        Long total = fileRepository.getTotalStorageSize();
        return total != null ? total : 0L;
    }

    @Transactional(readOnly = true)
    public FileStorageStats getStorageStats() {
        log.debug("Getting storage statistics");
        
        long totalFiles = fileRepository.count();
        Long totalSize = getTotalStorageSize();
        
        return new FileStorageStats(totalFiles, totalSize);
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
