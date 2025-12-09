/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.model.ShelfFloor;
import dz.mdn.iaas.common.environment.repository.ShelfFloorRepository;
import dz.mdn.iaas.common.environment.dto.ShelfFloorDTO;

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
public class ShelfFloorService {

    private final ShelfFloorRepository shelfFloorRepository;

    // ========== CREATE OPERATIONS ==========

    public ShelfFloorDTO createShelfFloor(ShelfFloorDTO shelfFloorDTO) {
        log.info("Creating shelf floor with code: {} and French designation: {}", 
                shelfFloorDTO.getCode(), shelfFloorDTO.getDesignationFr());

        // Validate required fields
        validateRequiredFields(shelfFloorDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(shelfFloorDTO, null);

        // Create entity with exact field mapping
        ShelfFloor shelfFloor = new ShelfFloor();
        shelfFloor.setCode(shelfFloorDTO.getCode()); // F_01
        shelfFloor.setDesignationAr(shelfFloorDTO.getDesignationAr()); // F_02
        shelfFloor.setDesignationEn(shelfFloorDTO.getDesignationEn()); // F_03
        shelfFloor.setDesignationFr(shelfFloorDTO.getDesignationFr()); // F_04

        ShelfFloor savedShelfFloor = shelfFloorRepository.save(shelfFloor);
        log.info("Successfully created shelf floor with ID: {}", savedShelfFloor.getId());

        return ShelfFloorDTO.fromEntity(savedShelfFloor);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public ShelfFloorDTO getShelfFloorById(Long id) {
        log.debug("Getting shelf floor with ID: {}", id);

        ShelfFloor shelfFloor = shelfFloorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShelfFloor not found with ID: " + id));

        return ShelfFloorDTO.fromEntity(shelfFloor);
    }

    @Transactional(readOnly = true)
    public ShelfFloor getShelfFloorEntityById(Long id) {
        return shelfFloorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShelfFloor not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<ShelfFloorDTO> findByCode(String code) {
        log.debug("Finding shelf floor with code: {}", code);

        return shelfFloorRepository.findByCode(code)
                .map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<ShelfFloorDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding shelf floor with French designation: {}", designationFr);

        return shelfFloorRepository.findByDesignationFr(designationFr)
                .map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> getAllShelfFloors(Pageable pageable) {
        log.debug("Getting all shelf floors with pagination");

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findAllOrderByCode(pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<ShelfFloorDTO> findOne(Long id) {
        log.debug("Finding shelf floor by ID: {}", id);

        return shelfFloorRepository.findById(id)
                .map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> searchShelfFloors(String searchTerm, Pageable pageable) {
        log.debug("Searching shelf floors with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllShelfFloors(pageable);
        }

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.searchByAnyField(searchTerm.trim(), pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> searchByCode(String code, Pageable pageable) {
        log.debug("Searching shelf floors by code: {}", code);

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findByCodeContaining(code, pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching shelf floors by designation: {}", designation);

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findByDesignationPattern(designation, pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> getMultilingualShelfFloors(Pageable pageable) {
        log.debug("Getting multilingual shelf floors");

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findMultilingualShelfFloors(pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> getTopShelfFloors(Pageable pageable) {
        log.debug("Getting top shelf floors");

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findTopShelfFloors(pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> getBottomShelfFloors(Pageable pageable) {
        log.debug("Getting bottom shelf floors");

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findBottomShelfFloors(pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> getMiddleShelfFloors(Pageable pageable) {
        log.debug("Getting middle shelf floors");

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findMiddleShelfFloors(pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfFloorDTO> getEyeLevelShelfFloors(Pageable pageable) {
        log.debug("Getting eye-level shelf floors");

        Page<ShelfFloor> shelfFloors = shelfFloorRepository.findEyeLevelShelfFloors(pageable);
        return shelfFloors.map(ShelfFloorDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public ShelfFloorDTO updateShelfFloor(Long id, ShelfFloorDTO shelfFloorDTO) {
        log.info("Updating shelf floor with ID: {}", id);

        ShelfFloor existingShelfFloor = getShelfFloorEntityById(id);

        // Validate required fields
        validateRequiredFields(shelfFloorDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(shelfFloorDTO, id);

        // Update fields with exact field mapping
        existingShelfFloor.setCode(shelfFloorDTO.getCode()); // F_01
        existingShelfFloor.setDesignationAr(shelfFloorDTO.getDesignationAr()); // F_02
        existingShelfFloor.setDesignationEn(shelfFloorDTO.getDesignationEn()); // F_03
        existingShelfFloor.setDesignationFr(shelfFloorDTO.getDesignationFr()); // F_04

        ShelfFloor updatedShelfFloor = shelfFloorRepository.save(existingShelfFloor);
        log.info("Successfully updated shelf floor with ID: {}", id);

        return ShelfFloorDTO.fromEntity(updatedShelfFloor);
    }

    public ShelfFloorDTO partialUpdateShelfFloor(Long id, ShelfFloorDTO shelfFloorDTO) {
        log.info("Partially updating shelf floor with ID: {}", id);

        ShelfFloor existingShelfFloor = getShelfFloorEntityById(id);
        boolean updated = false;

        // Update only non-null fields
        if (shelfFloorDTO.getCode() != null) {
            if (shelfFloorDTO.getCode().trim().isEmpty()) {
                throw new RuntimeException("Code cannot be empty");
            }
            if (shelfFloorRepository.existsByCodeAndIdNot(shelfFloorDTO.getCode(), id)) {
                throw new RuntimeException("Another shelf floor with code '" + shelfFloorDTO.getCode() + "' already exists");
            }
            existingShelfFloor.setCode(shelfFloorDTO.getCode()); // F_01
            updated = true;
        }

        if (shelfFloorDTO.getDesignationAr() != null) {
            existingShelfFloor.setDesignationAr(shelfFloorDTO.getDesignationAr()); // F_02
            updated = true;
        }

        if (shelfFloorDTO.getDesignationEn() != null) {
            existingShelfFloor.setDesignationEn(shelfFloorDTO.getDesignationEn()); // F_03
            updated = true;
        }

        if (shelfFloorDTO.getDesignationFr() != null) {
            if (shelfFloorDTO.getDesignationFr().trim().isEmpty()) {
                throw new RuntimeException("French designation cannot be empty");
            }
            if (shelfFloorRepository.existsByDesignationFrAndIdNot(shelfFloorDTO.getDesignationFr(), id)) {
                throw new RuntimeException("Another shelf floor with French designation '" + shelfFloorDTO.getDesignationFr() + "' already exists");
            }
            existingShelfFloor.setDesignationFr(shelfFloorDTO.getDesignationFr()); // F_04
            updated = true;
        }

        if (updated) {
            ShelfFloor updatedShelfFloor = shelfFloorRepository.save(existingShelfFloor);
            log.info("Successfully partially updated shelf floor with ID: {}", id);
            return ShelfFloorDTO.fromEntity(updatedShelfFloor);
        } else {
            log.debug("No fields to update for shelf floor with ID: {}", id);
            return ShelfFloorDTO.fromEntity(existingShelfFloor);
        }
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteShelfFloor(Long id) {
        log.info("Deleting shelf floor with ID: {}", id);

        ShelfFloor shelfFloor = getShelfFloorEntityById(id);
        shelfFloorRepository.delete(shelfFloor);

        log.info("Successfully deleted shelf floor with ID: {}", id);
    }

    public void deleteShelfFloorById(Long id) {
        log.info("Deleting shelf floor by ID: {}", id);

        if (!shelfFloorRepository.existsById(id)) {
            throw new RuntimeException("ShelfFloor not found with ID: " + id);
        }

        shelfFloorRepository.deleteById(id);
        log.info("Successfully deleted shelf floor with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return shelfFloorRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return shelfFloorRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return shelfFloorRepository.existsByDesignationFr(designationFr);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return shelfFloorRepository.countAllShelfFloors();
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(ShelfFloorDTO shelfFloorDTO, String operation) {
        if (shelfFloorDTO.getCode() == null || shelfFloorDTO.getCode().trim().isEmpty()) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (shelfFloorDTO.getDesignationFr() == null || shelfFloorDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    private void validateUniqueConstraints(ShelfFloorDTO shelfFloorDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (shelfFloorRepository.existsByCode(shelfFloorDTO.getCode())) {
                throw new RuntimeException("Shelf floor with code '" + shelfFloorDTO.getCode() + "' already exists");
            }
        } else {
            if (shelfFloorRepository.existsByCodeAndIdNot(shelfFloorDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another shelf floor with code '" + shelfFloorDTO.getCode() + "' already exists");
            }
        }

        // Check French designation uniqueness (F_04)
        if (excludeId == null) {
            if (shelfFloorRepository.existsByDesignationFr(shelfFloorDTO.getDesignationFr())) {
                throw new RuntimeException("Shelf floor with French designation '" + shelfFloorDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (shelfFloorRepository.existsByDesignationFrAndIdNot(shelfFloorDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another shelf floor with French designation '" + shelfFloorDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
