/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FolderService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.model.Folder;
import dz.mdn.iaas.common.environment.model.ArchiveBox;
import dz.mdn.iaas.common.environment.repository.FolderRepository;
import dz.mdn.iaas.common.environment.repository.ArchiveBoxRepository;
import dz.mdn.iaas.common.environment.dto.FolderDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FolderService {

    private final FolderRepository folderRepository;
    private final ArchiveBoxRepository archiveBoxRepository;

    // ========== CREATE OPERATIONS ==========

    public FolderDTO createFolder(FolderDTO folderDTO) {
        log.info("Creating folder with code: {} and French designation: {} for archive box ID: {}", 
                folderDTO.getCode(), folderDTO.getDesignationFr(), folderDTO.getArchiveBoxId());

        // Validate required fields
        validateRequiredFields(folderDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(folderDTO, null);

        // Validate required relationship
        ArchiveBox archiveBox = validateAndGetArchiveBox(folderDTO.getArchiveBoxId());

        // Create entity with exact field mapping
        Folder folder = new Folder();
        folder.setCode(folderDTO.getCode()); // F_01
        folder.setDesignationAr(folderDTO.getDesignationAr()); // F_02
        folder.setDesignationEn(folderDTO.getDesignationEn()); // F_03
        folder.setDesignationFr(folderDTO.getDesignationFr()); // F_04
        folder.setArchiveBox(archiveBox); // F_05

        Folder savedFolder = folderRepository.save(folder);
        log.info("Successfully created folder with ID: {}", savedFolder.getId());

        return FolderDTO.fromEntity(savedFolder);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public FolderDTO getFolderById(Long id) {
        log.debug("Getting folder with ID: {}", id);

        Folder folder = folderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder not found with ID: " + id));

        return FolderDTO.fromEntity(folder);
    }

    @Transactional(readOnly = true)
    public Folder getFolderEntityById(Long id) {
        return folderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Folder not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<FolderDTO> findByCode(String code) {
        log.debug("Finding folder with code: {}", code);

        return folderRepository.findByCode(code)
                .map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<FolderDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding folder with French designation: {}", designationFr);

        return folderRepository.findByDesignationFr(designationFr)
                .map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getAllFolders(Pageable pageable) {
        log.debug("Getting all folders with pagination");

        Page<Folder> folders = folderRepository.findAllWithRelationships(pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<FolderDTO> findOne(Long id) {
        log.debug("Finding folder by ID: {}", id);

        return folderRepository.findById(id)
                .map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getFoldersByArchiveBoxId(Long archiveBoxId, Pageable pageable) {
        log.debug("Getting folders for archive box ID: {}", archiveBoxId);

        validateArchiveBoxExists(archiveBoxId);
        Page<Folder> folders = folderRepository.findByArchiveBoxIdWithRelationships(archiveBoxId, pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getFoldersByShelfId(Long shelfId, Pageable pageable) {
        log.debug("Getting folders for shelf ID: {}", shelfId);

        Page<Folder> folders = folderRepository.findByShelfId(shelfId, pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getFoldersByRoomId(Long roomId, Pageable pageable) {
        log.debug("Getting folders for room ID: {}", roomId);

        Page<Folder> folders = folderRepository.findByRoomId(roomId, pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getFoldersByBlocId(Long blocId, Pageable pageable) {
        log.debug("Getting folders for bloc ID: {}", blocId);

        Page<Folder> folders = folderRepository.findByBlocId(blocId, pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getFoldersByFloorId(Long floorId, Pageable pageable) {
        log.debug("Getting folders for floor ID: {}", floorId);

        Page<Folder> folders = folderRepository.findByFloorId(floorId, pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getFoldersByStructureId(Long structureId, Pageable pageable) {
        log.debug("Getting folders for structure ID: {}", structureId);

        Page<Folder> folders = folderRepository.findByStructureId(structureId, pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getMultilingualFolders(Pageable pageable) {
        log.debug("Getting multilingual folders");

        Page<Folder> folders = folderRepository.findMultilingualFolders(pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getUrgentFolders(Pageable pageable) {
        log.debug("Getting urgent folders");

        Page<Folder> folders = folderRepository.findUrgentFolders(pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getConfidentialFolders(Pageable pageable) {
        log.debug("Getting confidential folders");

        Page<Folder> folders = folderRepository.findConfidentialFolders(pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getTemporaryFolders(Pageable pageable) {
        log.debug("Getting temporary folders");

        Page<Folder> folders = folderRepository.findTemporaryFolders(pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> getDocumentFolders(Pageable pageable) {
        log.debug("Getting document folders");

        Page<Folder> folders = folderRepository.findDocumentFolders(pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FolderDTO> searchFolders(String searchTerm, Pageable pageable) {
        log.debug("Searching folders with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllFolders(pageable);
        }

        Page<Folder> folders = folderRepository.searchByCodeOrArchiveBoxInfo(searchTerm.trim(), pageable);
        return folders.map(FolderDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public FolderDTO updateFolder(Long id, FolderDTO folderDTO) {
        log.info("Updating folder with ID: {}", id);

        Folder existingFolder = getFolderEntityById(id);

        // Validate required fields
        validateRequiredFields(folderDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(folderDTO, id);

        // Validate required relationship
        ArchiveBox archiveBox = validateAndGetArchiveBox(folderDTO.getArchiveBoxId());

        // Update fields with exact field mapping
        existingFolder.setCode(folderDTO.getCode()); // F_01
        existingFolder.setDesignationAr(folderDTO.getDesignationAr()); // F_02
        existingFolder.setDesignationEn(folderDTO.getDesignationEn()); // F_03
        existingFolder.setDesignationFr(folderDTO.getDesignationFr()); // F_04
        existingFolder.setArchiveBox(archiveBox); // F_05

        Folder updatedFolder = folderRepository.save(existingFolder);
        log.info("Successfully updated folder with ID: {}", id);

        return FolderDTO.fromEntity(updatedFolder);
    }

    public FolderDTO moveFolderToArchiveBox(Long folderId, Long newArchiveBoxId) {
        log.info("Moving folder ID: {} to archive box ID: {}", folderId, newArchiveBoxId);

        Folder folder = getFolderEntityById(folderId);
        ArchiveBox newArchiveBox = validateAndGetArchiveBox(newArchiveBoxId);

        folder.setArchiveBox(newArchiveBox); // F_05
        Folder updatedFolder = folderRepository.save(folder);

        log.info("Successfully moved folder to new archive box");
        return FolderDTO.fromEntity(updatedFolder);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteFolder(Long id) {
        log.info("Deleting folder with ID: {}", id);

        Folder folder = getFolderEntityById(id);
        folderRepository.delete(folder);

        log.info("Successfully deleted folder with ID: {}", id);
    }

    public void deleteFolderById(Long id) {
        log.info("Deleting folder by ID: {}", id);

        if (!folderRepository.existsById(id)) {
            throw new RuntimeException("Folder not found with ID: " + id);
        }

        folderRepository.deleteById(id);
        log.info("Successfully deleted folder with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return folderRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return folderRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return folderRepository.existsByDesignationFr(designationFr);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return folderRepository.countAllFolders();
    }

    @Transactional(readOnly = true)
    public Long getCountByArchiveBoxId(Long archiveBoxId) {
        return folderRepository.countByArchiveBoxId(archiveBoxId);
    }

    @Transactional(readOnly = true)
    public Long getCountByShelfId(Long shelfId) {
        return folderRepository.countByShelfId(shelfId);
    }

    @Transactional(readOnly = true)
    public Long getCountByRoomId(Long roomId) {
        return folderRepository.countByRoomId(roomId);
    }

    @Transactional(readOnly = true)
    public Long getCountByBlocId(Long blocId) {
        return folderRepository.countByBlocId(blocId);
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(FolderDTO folderDTO, String operation) {
        if (folderDTO.getCode() == null || folderDTO.getCode().trim().isEmpty()) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (folderDTO.getDesignationFr() == null || folderDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }

        if (folderDTO.getArchiveBoxId() == null) {
            throw new RuntimeException("Archive box ID is required for " + operation);
        }
    }

    private void validateUniqueConstraints(FolderDTO folderDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (folderRepository.existsByCode(folderDTO.getCode())) {
                throw new RuntimeException("Folder with code '" + folderDTO.getCode() + "' already exists");
            }
        } else {
            if (folderRepository.existsByCodeAndIdNot(folderDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another folder with code '" + folderDTO.getCode() + "' already exists");
            }
        }

        // Check French designation uniqueness (F_04)
        if (excludeId == null) {
            if (folderRepository.existsByDesignationFr(folderDTO.getDesignationFr())) {
                throw new RuntimeException("Folder with French designation '" + folderDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (folderRepository.existsByDesignationFrAndIdNot(folderDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another folder with French designation '" + folderDTO.getDesignationFr() + "' already exists");
            }
        }
    }

    private ArchiveBox validateAndGetArchiveBox(Long archiveBoxId) {
        if (archiveBoxId == null) {
            throw new RuntimeException("Archive box ID is required");
        }
        return archiveBoxRepository.findById(archiveBoxId)
                .orElseThrow(() -> new RuntimeException("Archive box not found with ID: " + archiveBoxId));
    }

    private void validateArchiveBoxExists(Long archiveBoxId) {
        if (!archiveBoxRepository.existsById(archiveBoxId)) {
            throw new RuntimeException("Archive box not found with ID: " + archiveBoxId);
        }
    }
}
