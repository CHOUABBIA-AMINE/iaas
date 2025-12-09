/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FloorService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.model.Floor;
import dz.mdn.iaas.common.environment.repository.FloorRepository;
import dz.mdn.iaas.common.environment.dto.FloorDTO;

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
public class FloorService {

    private final FloorRepository floorRepository;

    // ========== CREATE OPERATIONS ==========

    public FloorDTO createFloor(FloorDTO floorDTO) {
        log.info("Creating floor with code: {} and French designation: {}", 
                floorDTO.getCode(), floorDTO.getDesignationFr());

        // Validate required fields
        validateRequiredFields(floorDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(floorDTO, null);

        // Create entity with exact field mapping
        Floor floor = new Floor();
        floor.setCode(floorDTO.getCode()); // F_01
        floor.setDesignationAr(floorDTO.getDesignationAr()); // F_02
        floor.setDesignationEn(floorDTO.getDesignationEn()); // F_03
        floor.setDesignationFr(floorDTO.getDesignationFr()); // F_04

        Floor savedFloor = floorRepository.save(floor);
        log.info("Successfully created floor with ID: {}", savedFloor.getId());

        return FloorDTO.fromEntity(savedFloor);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public FloorDTO getFloorById(Long id) {
        log.debug("Getting floor with ID: {}", id);

        Floor floor = floorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Floor not found with ID: " + id));

        return FloorDTO.fromEntity(floor);
    }

    @Transactional(readOnly = true)
    public Floor getFloorEntityById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Floor not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<FloorDTO> findByCode(String code) {
        log.debug("Finding floor with code: {}", code);

        return floorRepository.findByCode(code)
                .map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<FloorDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding floor with French designation: {}", designationFr);

        return floorRepository.findByDesignationFr(designationFr)
                .map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> getAllFloors(Pageable pageable) {
        log.debug("Getting all floors with pagination");

        Page<Floor> floors = floorRepository.findAllOrderByCode(pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<FloorDTO> findOne(Long id) {
        log.debug("Finding floor by ID: {}", id);

        return floorRepository.findById(id)
                .map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> searchFloors(String searchTerm, Pageable pageable) {
        log.debug("Searching floors with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllFloors(pageable);
        }

        Page<Floor> floors = floorRepository.searchByAnyField(searchTerm.trim(), pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> searchByCode(String code, Pageable pageable) {
        log.debug("Searching floors by code: {}", code);

        Page<Floor> floors = floorRepository.findByCodeContaining(code, pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching floors by designation: {}", designation);

        Page<Floor> floors = floorRepository.findByDesignationPattern(designation, pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> getMultilingualFloors(Pageable pageable) {
        log.debug("Getting multilingual floors");

        Page<Floor> floors = floorRepository.findMultilingualFloors(pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> getGroundFloors(Pageable pageable) {
        log.debug("Getting ground floors");

        Page<Floor> floors = floorRepository.findGroundFloors(pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> getBasementFloors(Pageable pageable) {
        log.debug("Getting basement floors");

        Page<Floor> floors = floorRepository.findBasementFloors(pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FloorDTO> getUpperFloors(Pageable pageable) {
        log.debug("Getting upper floors");

        Page<Floor> floors = floorRepository.findUpperFloors(pageable);
        return floors.map(FloorDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public FloorDTO updateFloor(Long id, FloorDTO floorDTO) {
        log.info("Updating floor with ID: {}", id);

        Floor existingFloor = getFloorEntityById(id);

        // Validate required fields
        validateRequiredFields(floorDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(floorDTO, id);

        // Update fields with exact field mapping
        existingFloor.setCode(floorDTO.getCode()); // F_01
        existingFloor.setDesignationAr(floorDTO.getDesignationAr()); // F_02
        existingFloor.setDesignationEn(floorDTO.getDesignationEn()); // F_03
        existingFloor.setDesignationFr(floorDTO.getDesignationFr()); // F_04

        Floor updatedFloor = floorRepository.save(existingFloor);
        log.info("Successfully updated floor with ID: {}", id);

        return FloorDTO.fromEntity(updatedFloor);
    }

    public FloorDTO partialUpdateFloor(Long id, FloorDTO floorDTO) {
        log.info("Partially updating floor with ID: {}", id);

        Floor existingFloor = getFloorEntityById(id);
        boolean updated = false;

        // Update only non-null fields
        if (floorDTO.getCode() != null) {
            if (floorDTO.getCode().trim().isEmpty()) {
                throw new RuntimeException("Code cannot be empty");
            }
            if (floorRepository.existsByCodeAndIdNot(floorDTO.getCode(), id)) {
                throw new RuntimeException("Another floor with code '" + floorDTO.getCode() + "' already exists");
            }
            existingFloor.setCode(floorDTO.getCode()); // F_01
            updated = true;
        }

        if (floorDTO.getDesignationAr() != null) {
            existingFloor.setDesignationAr(floorDTO.getDesignationAr()); // F_02
            updated = true;
        }

        if (floorDTO.getDesignationEn() != null) {
            existingFloor.setDesignationEn(floorDTO.getDesignationEn()); // F_03
            updated = true;
        }

        if (floorDTO.getDesignationFr() != null) {
            if (floorDTO.getDesignationFr().trim().isEmpty()) {
                throw new RuntimeException("French designation cannot be empty");
            }
            if (floorRepository.existsByDesignationFrAndIdNot(floorDTO.getDesignationFr(), id)) {
                throw new RuntimeException("Another floor with French designation '" + floorDTO.getDesignationFr() + "' already exists");
            }
            existingFloor.setDesignationFr(floorDTO.getDesignationFr()); // F_04
            updated = true;
        }

        if (updated) {
            Floor updatedFloor = floorRepository.save(existingFloor);
            log.info("Successfully partially updated floor with ID: {}", id);
            return FloorDTO.fromEntity(updatedFloor);
        } else {
            log.debug("No fields to update for floor with ID: {}", id);
            return FloorDTO.fromEntity(existingFloor);
        }
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteFloor(Long id) {
        log.info("Deleting floor with ID: {}", id);

        Floor floor = getFloorEntityById(id);
        floorRepository.delete(floor);

        log.info("Successfully deleted floor with ID: {}", id);
    }

    public void deleteFloorById(Long id) {
        log.info("Deleting floor by ID: {}", id);

        if (!floorRepository.existsById(id)) {
            throw new RuntimeException("Floor not found with ID: " + id);
        }

        floorRepository.deleteById(id);
        log.info("Successfully deleted floor with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return floorRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return floorRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return floorRepository.existsByDesignationFr(designationFr);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return floorRepository.countAllFloors();
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(FloorDTO floorDTO, String operation) {
        if (floorDTO.getCode() == null || floorDTO.getCode().trim().isEmpty()) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (floorDTO.getDesignationFr() == null || floorDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    private void validateUniqueConstraints(FloorDTO floorDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (floorRepository.existsByCode(floorDTO.getCode())) {
                throw new RuntimeException("Floor with code '" + floorDTO.getCode() + "' already exists");
            }
        } else {
            if (floorRepository.existsByCodeAndIdNot(floorDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another floor with code '" + floorDTO.getCode() + "' already exists");
            }
        }

        // Check French designation uniqueness (F_04)
        if (excludeId == null) {
            if (floorRepository.existsByDesignationFr(floorDTO.getDesignationFr())) {
                throw new RuntimeException("Floor with French designation '" + floorDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (floorRepository.existsByDesignationFrAndIdNot(floorDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another floor with French designation '" + floorDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
