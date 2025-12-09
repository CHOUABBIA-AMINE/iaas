/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureTypeService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.model.StructureType;
import dz.mdn.iaas.common.administration.repository.StructureTypeRepository;
import dz.mdn.iaas.common.administration.dto.StructureTypeDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * StructureType Service with CRUD operations
 * Handles structure type management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr), F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StructureTypeService {

    private final StructureTypeRepository structureTypeRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new structure type
     */
    public StructureTypeDTO createStructureType(StructureTypeDTO structureTypeDTO) {
        log.info("Creating structure type with French designation: {} , designations: AR={}, EN={}", 
                structureTypeDTO.getDesignationFr(),
                structureTypeDTO.getDesignationAr(), structureTypeDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(structureTypeDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(structureTypeDTO, null);

        // Create entity with exact field mapping
        StructureType structureType = new StructureType();
        structureType.setDesignationAr(structureTypeDTO.getDesignationAr()); // F_01
        structureType.setDesignationEn(structureTypeDTO.getDesignationEn()); // F_02
        structureType.setDesignationFr(structureTypeDTO.getDesignationFr()); // F_03

        StructureType savedStructureType = structureTypeRepository.save(structureType);
        log.info("Successfully created structure type with ID: {}", savedStructureType.getId());

        return StructureTypeDTO.fromEntity(savedStructureType);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get structure type by ID
     */
    @Transactional(readOnly = true)
    public StructureTypeDTO getStructureTypeById(Long id) {
        log.debug("Getting structure type with ID: {}", id);

        StructureType structureType = structureTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure type not found with ID: " + id));

        return StructureTypeDTO.fromEntity(structureType);
    }

    /**
     * Get structure type entity by ID
     */
    @Transactional(readOnly = true)
    public StructureType getStructureTypeEntityById(Long id) {
        return structureTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure type not found with ID: " + id));
    }

    /**
     * Find structure type by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<StructureTypeDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding structure type with French designation: {}", designationFr);

        return structureTypeRepository.findByDesignationFr(designationFr)
                .map(StructureTypeDTO::fromEntity);
    }

    /**
     * Find structure type by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<StructureTypeDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding structure type with Arabic designation: {}", designationAr);

        return structureTypeRepository.findByDesignationAr(designationAr)
                .map(StructureTypeDTO::fromEntity);
    }

    /**
     * Find structure type by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<StructureTypeDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding structure type with English designation: {}", designationEn);

        return structureTypeRepository.findByDesignationEn(designationEn)
                .map(StructureTypeDTO::fromEntity);
    }

    /**
     * Get all structure types with pagination
     */
    @Transactional(readOnly = true)
    public Page<StructureTypeDTO> getAllStructureTypes(Pageable pageable) {
        log.debug("Getting all structure types with pagination");

        Page<StructureType> structureTypes = structureTypeRepository.findAllOrderByDesignationFr(pageable);
        return structureTypes.map(StructureTypeDTO::fromEntity);
    }

    /**
     * Find one structure type by ID
     */
    @Transactional(readOnly = true)
    public Optional<StructureTypeDTO> findOne(Long id) {
        log.debug("Finding structure type by ID: {}", id);

        return structureTypeRepository.findById(id)
                .map(StructureTypeDTO::fromEntity);
    }

    /**
     * Search structure types by designation
     */
    @Transactional(readOnly = true)
    public Page<StructureTypeDTO> searchStructureTypesByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching structure types by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllStructureTypes(pageable);
        }

        Page<StructureType> structureTypes = structureTypeRepository.searchByDesignation(searchTerm.trim(), pageable);
        return structureTypes.map(StructureTypeDTO::fromEntity);
    }

    /**
     * Search structure types by designation or acronym
     */
    @Transactional(readOnly = true)
    public Page<StructureTypeDTO> searchStructureTypes(String searchTerm, Pageable pageable) {
        log.debug("Searching structure types with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllStructureTypes(pageable);
        }

        Page<StructureType> structureTypes = structureTypeRepository.searchByDesignation(searchTerm.trim(), pageable);
        return structureTypes.map(StructureTypeDTO::fromEntity);
    }

    /**
     * Get multilingual structure types
     */
    @Transactional(readOnly = true)
    public Page<StructureTypeDTO> getMultilingualStructureTypes(Pageable pageable) {
        log.debug("Getting multilingual structure types");

        Page<StructureType> structureTypes = structureTypeRepository.findMultilingualStructureTypes(pageable);
        return structureTypes.map(StructureTypeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update structure type
     */
    public StructureTypeDTO updateStructureType(Long id, StructureTypeDTO structureTypeDTO) {
        log.info("Updating structure type with ID: {}", id);

        StructureType existingStructureType = getStructureTypeEntityById(id);

        // Validate required fields
        validateRequiredFields(structureTypeDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(structureTypeDTO, id);

        // Update fields with exact field mapping
        existingStructureType.setDesignationAr(structureTypeDTO.getDesignationAr()); // F_01
        existingStructureType.setDesignationEn(structureTypeDTO.getDesignationEn()); // F_02
        existingStructureType.setDesignationFr(structureTypeDTO.getDesignationFr()); // F_03

        StructureType updatedStructureType = structureTypeRepository.save(existingStructureType);
        log.info("Successfully updated structure type with ID: {}", id);

        return StructureTypeDTO.fromEntity(updatedStructureType);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete structure type
     */
    public void deleteStructureType(Long id) {
        log.info("Deleting structure type with ID: {}", id);

        StructureType structureType = getStructureTypeEntityById(id);
        structureTypeRepository.delete(structureType);

        log.info("Successfully deleted structure type with ID: {}", id);
    }

    /**
     * Delete structure type by ID (direct)
     */
    public void deleteStructureTypeById(Long id) {
        log.info("Deleting structure type by ID: {}", id);

        if (!structureTypeRepository.existsById(id)) {
            throw new RuntimeException("Structure type not found with ID: " + id);
        }

        structureTypeRepository.deleteById(id);
        log.info("Successfully deleted structure type with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if structure type exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return structureTypeRepository.existsById(id);
    }

    /**
     * Check if structure type exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return structureTypeRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of structure types
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return structureTypeRepository.countAllStructureTypes();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(StructureTypeDTO structureTypeDTO, String operation) {
        if (structureTypeDTO.getDesignationFr() == null || structureTypeDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(StructureTypeDTO structureTypeDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (structureTypeRepository.existsByDesignationFr(structureTypeDTO.getDesignationFr())) {
                throw new RuntimeException("Structure type with French designation '" + structureTypeDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (structureTypeRepository.existsByDesignationFrAndIdNot(structureTypeDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another structure type with French designation '" + structureTypeDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
