/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ExclusionTypeService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.service;

import dz.mdn.iaas.business.provider.model.ExclusionType;
import dz.mdn.iaas.business.provider.repository.ExclusionTypeRepository;
import dz.mdn.iaas.business.provider.dto.ExclusionTypeDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Exclusion Type Service with CRUD operations
 * Handles exclusion type management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExclusionTypeService {

    private final ExclusionTypeRepository exclusionTypeRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new exclusion type
     */
    public ExclusionTypeDTO createExclusionType(ExclusionTypeDTO exclusionTypeDTO) {
        log.info("Creating exclusion type with French designation: {} and designations: AR={}, EN={}", 
                exclusionTypeDTO.getDesignationFr(), exclusionTypeDTO.getDesignationAr(), 
                exclusionTypeDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(exclusionTypeDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(exclusionTypeDTO, null);

        // Create entity with exact field mapping
        ExclusionType exclusionType = new ExclusionType();
        exclusionType.setDesignationAr(exclusionTypeDTO.getDesignationAr()); // F_01
        exclusionType.setDesignationEn(exclusionTypeDTO.getDesignationEn()); // F_02
        exclusionType.setDesignationFr(exclusionTypeDTO.getDesignationFr()); // F_03

        ExclusionType savedExclusionType = exclusionTypeRepository.save(exclusionType);
        log.info("Successfully created exclusion type with ID: {}", savedExclusionType.getId());

        return ExclusionTypeDTO.fromEntity(savedExclusionType);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get exclusion type by ID
     */
    @Transactional(readOnly = true)
    public ExclusionTypeDTO getExclusionTypeById(Long id) {
        log.debug("Getting exclusion type with ID: {}", id);

        ExclusionType exclusionType = exclusionTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exclusion type not found with ID: " + id));

        return ExclusionTypeDTO.fromEntity(exclusionType);
    }

    /**
     * Get exclusion type entity by ID
     */
    @Transactional(readOnly = true)
    public ExclusionType getExclusionTypeEntityById(Long id) {
        return exclusionTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exclusion type not found with ID: " + id));
    }

    /**
     * Find exclusion type by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ExclusionTypeDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding exclusion type with French designation: {}", designationFr);

        return exclusionTypeRepository.findByDesignationFr(designationFr)
                .map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Find exclusion type by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<ExclusionTypeDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding exclusion type with Arabic designation: {}", designationAr);

        return exclusionTypeRepository.findByDesignationAr(designationAr)
                .map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Find exclusion type by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<ExclusionTypeDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding exclusion type with English designation: {}", designationEn);

        return exclusionTypeRepository.findByDesignationEn(designationEn)
                .map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get all exclusion types with pagination
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getAllExclusionTypes(Pageable pageable) {
        log.debug("Getting all exclusion types with pagination");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findAllOrderByDesignationFr(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Find one exclusion type by ID
     */
    @Transactional(readOnly = true)
    public Optional<ExclusionTypeDTO> findOne(Long id) {
        log.debug("Finding exclusion type by ID: {}", id);

        return exclusionTypeRepository.findById(id)
                .map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Search exclusion types by designation
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> searchExclusionTypes(String searchTerm, Pageable pageable) {
        log.debug("Searching exclusion types with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllExclusionTypes(pageable);
        }

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.searchByDesignation(searchTerm.trim(), pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get multilingual exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getMultilingualExclusionTypes(Pageable pageable) {
        log.debug("Getting multilingual exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findMultilingualExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get legal exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getLegalExclusionTypes(Pageable pageable) {
        log.debug("Getting legal exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findLegalExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get criminal exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getCriminalExclusionTypes(Pageable pageable) {
        log.debug("Getting criminal exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findCriminalExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get financial exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getFinancialExclusionTypes(Pageable pageable) {
        log.debug("Getting financial exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findFinancialExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get tax exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getTaxExclusionTypes(Pageable pageable) {
        log.debug("Getting tax exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findTaxExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get administrative exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getAdministrativeExclusionTypes(Pageable pageable) {
        log.debug("Getting administrative exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findAdministrativeExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get security exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getSecurityExclusionTypes(Pageable pageable) {
        log.debug("Getting security exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findSecurityExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get sectoral exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getSectoralExclusionTypes(Pageable pageable) {
        log.debug("Getting sectoral exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findSectoralExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get geographical exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getGeographicalExclusionTypes(Pageable pageable) {
        log.debug("Getting geographical exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findGeographicalExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get temporal exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getTemporalExclusionTypes(Pageable pageable) {
        log.debug("Getting temporal exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findTemporalExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get qualification exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getQualificationExclusionTypes(Pageable pageable) {
        log.debug("Getting qualification exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findQualificationExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get conflict exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getConflictExclusionTypes(Pageable pageable) {
        log.debug("Getting conflict exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findConflictExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get permanent exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getPermanentExclusionTypes(Pageable pageable) {
        log.debug("Getting permanent exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findPermanentExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get temporary exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getTemporaryExclusionTypes(Pageable pageable) {
        log.debug("Getting temporary exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findTemporaryExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get conditional exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getConditionalExclusionTypes(Pageable pageable) {
        log.debug("Getting conditional exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findConditionalExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get high severity exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getHighSeverityExclusionTypes(Pageable pageable) {
        log.debug("Getting high severity exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findHighSeverityExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get public contract exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getPublicContractExclusionTypes(Pageable pageable) {
        log.debug("Getting public contract exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findPublicContractExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get legal review exclusion types
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getLegalReviewExclusionTypes(Pageable pageable) {
        log.debug("Getting legal review exclusion types");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findLegalReviewExclusionTypes(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    /**
     * Get exclusion types missing translations
     */
    @Transactional(readOnly = true)
    public Page<ExclusionTypeDTO> getExclusionTypesMissingTranslations(Pageable pageable) {
        log.debug("Getting exclusion types missing translations");

        Page<ExclusionType> exclusionTypes = exclusionTypeRepository.findMissingTranslations(pageable);
        return exclusionTypes.map(ExclusionTypeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update exclusion type
     */
    public ExclusionTypeDTO updateExclusionType(Long id, ExclusionTypeDTO exclusionTypeDTO) {
        log.info("Updating exclusion type with ID: {}", id);

        ExclusionType existingExclusionType = getExclusionTypeEntityById(id);

        // Validate required fields
        validateRequiredFields(exclusionTypeDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(exclusionTypeDTO, id);

        // Update fields with exact field mapping
        existingExclusionType.setDesignationAr(exclusionTypeDTO.getDesignationAr()); // F_01
        existingExclusionType.setDesignationEn(exclusionTypeDTO.getDesignationEn()); // F_02
        existingExclusionType.setDesignationFr(exclusionTypeDTO.getDesignationFr()); // F_03

        ExclusionType updatedExclusionType = exclusionTypeRepository.save(existingExclusionType);
        log.info("Successfully updated exclusion type with ID: {}", id);

        return ExclusionTypeDTO.fromEntity(updatedExclusionType);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete exclusion type
     */
    public void deleteExclusionType(Long id) {
        log.info("Deleting exclusion type with ID: {}", id);

        ExclusionType exclusionType = getExclusionTypeEntityById(id);
        exclusionTypeRepository.delete(exclusionType);

        log.info("Successfully deleted exclusion type with ID: {}", id);
    }

    /**
     * Delete exclusion type by ID (direct)
     */
    public void deleteExclusionTypeById(Long id) {
        log.info("Deleting exclusion type by ID: {}", id);

        if (!exclusionTypeRepository.existsById(id)) {
            throw new RuntimeException("Exclusion type not found with ID: " + id);
        }

        exclusionTypeRepository.deleteById(id);
        log.info("Successfully deleted exclusion type with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if exclusion type exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return exclusionTypeRepository.existsById(id);
    }

    /**
     * Check if exclusion type exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return exclusionTypeRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of exclusion types
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return exclusionTypeRepository.countAllExclusionTypes();
    }

    /**
     * Get count of legal exclusion types
     */
    @Transactional(readOnly = true)
    public Long getLegalExclusionTypesCount() {
        return exclusionTypeRepository.countLegalExclusionTypes();
    }

    /**
     * Get count of financial exclusion types
     */
    @Transactional(readOnly = true)
    public Long getFinancialExclusionTypesCount() {
        return exclusionTypeRepository.countFinancialExclusionTypes();
    }

    /**
     * Get count of administrative exclusion types
     */
    @Transactional(readOnly = true)
    public Long getAdministrativeExclusionTypesCount() {
        return exclusionTypeRepository.countAdministrativeExclusionTypes();
    }

    /**
     * Get count of security exclusion types
     */
    @Transactional(readOnly = true)
    public Long getSecurityExclusionTypesCount() {
        return exclusionTypeRepository.countSecurityExclusionTypes();
    }

    /**
     * Get count of multilingual exclusion types
     */
    @Transactional(readOnly = true)
    public Long getMultilingualCount() {
        return exclusionTypeRepository.countMultilingualExclusionTypes();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ExclusionTypeDTO exclusionTypeDTO, String operation) {
        if (exclusionTypeDTO.getDesignationFr() == null || exclusionTypeDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ExclusionTypeDTO exclusionTypeDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (exclusionTypeRepository.existsByDesignationFr(exclusionTypeDTO.getDesignationFr())) {
                throw new RuntimeException("Exclusion type with French designation '" + exclusionTypeDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (exclusionTypeRepository.existsByDesignationFrAndIdNot(exclusionTypeDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another exclusion type with French designation '" + exclusionTypeDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
