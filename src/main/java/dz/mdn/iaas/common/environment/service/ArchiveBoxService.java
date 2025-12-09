/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBoxService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.model.ArchiveBox;
import dz.mdn.iaas.common.environment.model.Shelf;
import dz.mdn.iaas.common.environment.model.ShelfFloor;
import dz.mdn.iaas.common.environment.repository.ArchiveBoxRepository;
import dz.mdn.iaas.common.environment.repository.ShelfRepository;
import dz.mdn.iaas.common.environment.repository.ShelfFloorRepository;
import dz.mdn.iaas.common.environment.dto.ArchiveBoxDTO;

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
public class ArchiveBoxService {

    private final ArchiveBoxRepository archiveBoxRepository;
    private final ShelfRepository shelfRepository;
    private final ShelfFloorRepository shelfFloorRepository;

    // ========== CREATE OPERATIONS ==========

    public ArchiveBoxDTO createArchiveBox(ArchiveBoxDTO archiveBoxDTO) {
        log.info("Creating archive box with code: {} for shelf ID: {} and shelf floor ID: {}", 
                archiveBoxDTO.getCode(), archiveBoxDTO.getShelfId(), archiveBoxDTO.getShelfFloorId());

        // Validate required fields
        validateRequiredFields(archiveBoxDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(archiveBoxDTO, null);

        // Validate required relationships
        Shelf shelf = validateAndGetShelf(archiveBoxDTO.getShelfId());
        ShelfFloor shelfFloor = validateAndGetShelfFloor(archiveBoxDTO.getShelfFloorId());

        // Create entity with exact field mapping
        ArchiveBox archiveBox = new ArchiveBox();
        archiveBox.setCode(archiveBoxDTO.getCode()); // F_01
        archiveBox.setShelf(shelf); // F_02
        archiveBox.setShelfFloor(shelfFloor); // F_03

        ArchiveBox savedArchiveBox = archiveBoxRepository.save(archiveBox);
        log.info("Successfully created archive box with ID: {}", savedArchiveBox.getId());

        return ArchiveBoxDTO.fromEntity(savedArchiveBox);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public ArchiveBoxDTO getArchiveBoxById(Long id) {
        log.debug("Getting archive box with ID: {}", id);

        ArchiveBox archiveBox = archiveBoxRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archive box not found with ID: " + id));

        return ArchiveBoxDTO.fromEntity(archiveBox);
    }

    @Transactional(readOnly = true)
    public ArchiveBox getArchiveBoxEntityById(Long id) {
        return archiveBoxRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archive box not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<ArchiveBoxDTO> findByCode(String code) {
        log.debug("Finding archive box with code: {}", code);

        return archiveBoxRepository.findByCode(code)
                .map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getAllArchiveBoxes(Pageable pageable) {
        log.debug("Getting all archive boxes with pagination");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findAllWithRelationships(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<ArchiveBoxDTO> findOne(Long id) {
        log.debug("Finding archive box by ID: {}", id);

        return archiveBoxRepository.findById(id)
                .map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveBoxesByShelfId(Long shelfId, Pageable pageable) {
        log.debug("Getting archive boxes for shelf ID: {}", shelfId);

        validateShelfExists(shelfId);
        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findByShelfIdWithRelationships(shelfId, pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveBoxesByShelfFloorId(Long shelfFloorId, Pageable pageable) {
        log.debug("Getting archive boxes for shelf floor ID: {}", shelfFloorId);

        validateShelfFloorExists(shelfFloorId);
        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findByShelfFloorId(shelfFloorId, pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveBoxesByShelfAndShelfFloor(Long shelfId, Long shelfFloorId, Pageable pageable) {
        log.debug("Getting archive boxes for shelf ID: {} and shelf floor ID: {}", shelfId, shelfFloorId);

        validateShelfExists(shelfId);
        validateShelfFloorExists(shelfFloorId);
        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findByShelfIdAndShelfFloorId(shelfId, shelfFloorId, pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveBoxesByRoomId(Long roomId, Pageable pageable) {
        log.debug("Getting archive boxes for room ID: {}", roomId);

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findByRoomId(roomId, pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveBoxesByBlocId(Long blocId, Pageable pageable) {
        log.debug("Getting archive boxes for bloc ID: {}", blocId);

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findByBlocId(blocId, pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveBoxesByFloorId(Long floorId, Pageable pageable) {
        log.debug("Getting archive boxes for floor ID: {}", floorId);

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findByFloorId(floorId, pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveBoxesByStructureId(Long structureId, Pageable pageable) {
        log.debug("Getting archive boxes for structure ID: {}", structureId);

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findByStructureId(structureId, pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getDocumentArchiveBoxes(Pageable pageable) {
        log.debug("Getting document archive boxes");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findDocumentArchiveBoxes(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getArchiveStorageBoxes(Pageable pageable) {
        log.debug("Getting archive storage boxes");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findArchiveStorageBoxes(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getConfidentialArchiveBoxes(Pageable pageable) {
        log.debug("Getting confidential archive boxes");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findConfidentialArchiveBoxes(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getTemporaryArchiveBoxes(Pageable pageable) {
        log.debug("Getting temporary archive boxes");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findTemporaryArchiveBoxes(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getUrgentArchiveBoxes(Pageable pageable) {
        log.debug("Getting urgent archive boxes");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findUrgentArchiveBoxes(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getHighAccessibilityArchiveBoxes(Pageable pageable) {
        log.debug("Getting high accessibility archive boxes");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findHighAccessibilityArchiveBoxes(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> getLowAccessibilityArchiveBoxes(Pageable pageable) {
        log.debug("Getting low accessibility archive boxes");

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.findLowAccessibilityArchiveBoxes(pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ArchiveBoxDTO> searchArchiveBoxes(String searchTerm, Pageable pageable) {
        log.debug("Searching archive boxes with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllArchiveBoxes(pageable);
        }

        Page<ArchiveBox> archiveBoxes = archiveBoxRepository.searchByCodeOrShelfInfo(searchTerm.trim(), pageable);
        return archiveBoxes.map(ArchiveBoxDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public ArchiveBoxDTO updateArchiveBox(Long id, ArchiveBoxDTO archiveBoxDTO) {
        log.info("Updating archive box with ID: {}", id);

        ArchiveBox existingArchiveBox = getArchiveBoxEntityById(id);

        // Validate required fields
        validateRequiredFields(archiveBoxDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(archiveBoxDTO, id);

        // Validate required relationships
        Shelf shelf = validateAndGetShelf(archiveBoxDTO.getShelfId());
        ShelfFloor shelfFloor = validateAndGetShelfFloor(archiveBoxDTO.getShelfFloorId());

        // Update fields with exact field mapping
        existingArchiveBox.setCode(archiveBoxDTO.getCode()); // F_01
        existingArchiveBox.setShelf(shelf); // F_02
        existingArchiveBox.setShelfFloor(shelfFloor); // F_03

        ArchiveBox updatedArchiveBox = archiveBoxRepository.save(existingArchiveBox);
        log.info("Successfully updated archive box with ID: {}", id);

        return ArchiveBoxDTO.fromEntity(updatedArchiveBox);
    }

    public ArchiveBoxDTO moveArchiveBoxToShelf(Long archiveBoxId, Long newShelfId) {
        log.info("Moving archive box ID: {} to shelf ID: {}", archiveBoxId, newShelfId);

        ArchiveBox archiveBox = getArchiveBoxEntityById(archiveBoxId);
        Shelf newShelf = validateAndGetShelf(newShelfId);

        archiveBox.setShelf(newShelf); // F_02
        ArchiveBox updatedArchiveBox = archiveBoxRepository.save(archiveBox);

        log.info("Successfully moved archive box to new shelf");
        return ArchiveBoxDTO.fromEntity(updatedArchiveBox);
    }

    public ArchiveBoxDTO moveArchiveBoxToShelfFloor(Long archiveBoxId, Long newShelfFloorId) {
        log.info("Moving archive box ID: {} to shelf floor ID: {}", archiveBoxId, newShelfFloorId);

        ArchiveBox archiveBox = getArchiveBoxEntityById(archiveBoxId);
        ShelfFloor newShelfFloor = validateAndGetShelfFloor(newShelfFloorId);

        archiveBox.setShelfFloor(newShelfFloor); // F_03
        ArchiveBox updatedArchiveBox = archiveBoxRepository.save(archiveBox);

        log.info("Successfully moved archive box to new shelf floor");
        return ArchiveBoxDTO.fromEntity(updatedArchiveBox);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteArchiveBox(Long id) {
        log.info("Deleting archive box with ID: {}", id);

        ArchiveBox archiveBox = getArchiveBoxEntityById(id);
        archiveBoxRepository.delete(archiveBox);

        log.info("Successfully deleted archive box with ID: {}", id);
    }

    public void deleteArchiveBoxById(Long id) {
        log.info("Deleting archive box by ID: {}", id);

        if (!archiveBoxRepository.existsById(id)) {
            throw new RuntimeException("Archive box not found with ID: " + id);
        }

        archiveBoxRepository.deleteById(id);
        log.info("Successfully deleted archive box with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return archiveBoxRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return archiveBoxRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return archiveBoxRepository.countAllArchiveBoxes();
    }

    @Transactional(readOnly = true)
    public Long getCountByShelfId(Long shelfId) {
        return archiveBoxRepository.countByShelfId(shelfId);
    }

    @Transactional(readOnly = true)
    public Long getCountByShelfFloorId(Long shelfFloorId) {
        return archiveBoxRepository.countByShelfFloorId(shelfFloorId);
    }

    @Transactional(readOnly = true)
    public Long getCountByRoomId(Long roomId) {
        return archiveBoxRepository.countByRoomId(roomId);
    }

    @Transactional(readOnly = true)
    public Long getCountByBlocId(Long blocId) {
        return archiveBoxRepository.countByBlocId(blocId);
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(ArchiveBoxDTO archiveBoxDTO, String operation) {
        if (archiveBoxDTO.getCode() == null || archiveBoxDTO.getCode().trim().isEmpty()) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (archiveBoxDTO.getShelfId() == null) {
            throw new RuntimeException("Shelf ID is required for " + operation);
        }

        if (archiveBoxDTO.getShelfFloorId() == null) {
            throw new RuntimeException("Shelf floor ID is required for " + operation);
        }
    }

    private void validateUniqueConstraints(ArchiveBoxDTO archiveBoxDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (archiveBoxRepository.existsByCode(archiveBoxDTO.getCode())) {
                throw new RuntimeException("Archive box with code '" + archiveBoxDTO.getCode() + "' already exists");
            }
        } else {
            if (archiveBoxRepository.existsByCodeAndIdNot(archiveBoxDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another archive box with code '" + archiveBoxDTO.getCode() + "' already exists");
            }
        }
    }

    private Shelf validateAndGetShelf(Long shelfId) {
        if (shelfId == null) {
            throw new RuntimeException("Shelf ID is required");
        }
        return shelfRepository.findById(shelfId)
                .orElseThrow(() -> new RuntimeException("Shelf not found with ID: " + shelfId));
    }

    private ShelfFloor validateAndGetShelfFloor(Long shelfFloorId) {
        if (shelfFloorId == null) {
            throw new RuntimeException("Shelf floor ID is required");
        }
        return shelfFloorRepository.findById(shelfFloorId)
                .orElseThrow(() -> new RuntimeException("Shelf floor not found with ID: " + shelfFloorId));
    }

    private void validateShelfExists(Long shelfId) {
        if (!shelfRepository.existsById(shelfId)) {
            throw new RuntimeException("Shelf not found with ID: " + shelfId);
        }
    }

    private void validateShelfFloorExists(Long shelfFloorId) {
        if (!shelfFloorRepository.existsById(shelfFloorId)) {
            throw new RuntimeException("Shelf floor not found with ID: " + shelfFloorId);
        }
    }
}
