/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryCategoryService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: MilitaryCategoryService
 *	@CreatedOn	: 10-16-2025
 *	@Type		: Service
 *	@Layer		: Business / Core
 *	@Package	: Common / Administration / Service
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.model.MilitaryCategory;
import dz.mdn.iaas.common.administration.repository.MilitaryCategoryRepository;
import dz.mdn.iaas.common.administration.dto.MilitaryCategoryDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Military Category Service with CRUD operations
 * Handles military category management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=abbreviationAr, F_05=abbreviationEn, F_06=abbreviationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (abbreviationFr) is required
 * F_01, F_02, F_04, F_05 are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MilitaryCategoryService {

    private final MilitaryCategoryRepository militaryCategoryRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new military category
     */
    public MilitaryCategoryDTO createMilitaryCategory(MilitaryCategoryDTO militaryCategoryDTO) {
        log.info("Creating military category with French designation: {} and designations: AR={}, EN={}, Abbreviations: FR={}, AR={}, EN={}", 
                militaryCategoryDTO.getDesignationFr(), militaryCategoryDTO.getDesignationAr(), 
                militaryCategoryDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(militaryCategoryDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(militaryCategoryDTO, null);

        // Create entity with exact field mapping
        MilitaryCategory militaryCategory = new MilitaryCategory();
        militaryCategory.setDesignationAr(militaryCategoryDTO.getDesignationAr()); // F_01
        militaryCategory.setDesignationEn(militaryCategoryDTO.getDesignationEn()); // F_02
        militaryCategory.setDesignationFr(militaryCategoryDTO.getDesignationFr()); // F_03

        MilitaryCategory savedMilitaryCategory = militaryCategoryRepository.save(militaryCategory);
        log.info("Successfully created military category with ID: {}", savedMilitaryCategory.getId());

        return MilitaryCategoryDTO.fromEntity(savedMilitaryCategory);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get military category by ID
     */
    @Transactional(readOnly = true)
    public MilitaryCategoryDTO getMilitaryCategoryById(Long id) {
        log.debug("Getting military category with ID: {}", id);

        MilitaryCategory militaryCategory = militaryCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Military category not found with ID: " + id));

        return MilitaryCategoryDTO.fromEntity(militaryCategory);
    }

    /**
     * Get military category entity by ID
     */
    @Transactional(readOnly = true)
    public MilitaryCategory getMilitaryCategoryEntityById(Long id) {
        return militaryCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Military category not found with ID: " + id));
    }

    /**
     * Find military category by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<MilitaryCategoryDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding military category with French designation: {}", designationFr);

        return militaryCategoryRepository.findByDesignationFr(designationFr)
                .map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Find military category by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<MilitaryCategoryDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding military category with Arabic designation: {}", designationAr);

        return militaryCategoryRepository.findByDesignationAr(designationAr)
                .map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Find military category by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<MilitaryCategoryDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding military category with English designation: {}", designationEn);

        return militaryCategoryRepository.findByDesignationEn(designationEn)
                .map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get all military categories with pagination
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getAllMilitaryCategories(Pageable pageable) {
        log.debug("Getting all military categories with pagination");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findAllOrderByDesignationFr(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Find one military category by ID
     */
    @Transactional(readOnly = true)
    public Optional<MilitaryCategoryDTO> findOne(Long id) {
        log.debug("Finding military category by ID: {}", id);

        return militaryCategoryRepository.findById(id)
                .map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Search military categories by designation or abbreviation
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> searchMilitaryCategories(String searchTerm, Pageable pageable) {
        log.debug("Searching military categories with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllMilitaryCategories(pageable);
        }

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.searchByDesignation(searchTerm.trim(), pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get multilingual military categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getMultilingualMilitaryCategories(Pageable pageable) {
        log.debug("Getting multilingual military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findMultilingualMilitaryCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get army categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getArmyCategories(Pageable pageable) {
        log.debug("Getting army military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findArmyCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get navy categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getNavyCategories(Pageable pageable) {
        log.debug("Getting navy military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findNavyCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get air force categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getAirForceCategories(Pageable pageable) {
        log.debug("Getting air force military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findAirForceCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get gendarmerie categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getGendarmerieCategories(Pageable pageable) {
        log.debug("Getting gendarmerie military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findGendarmerieCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get security categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getSecurityCategories(Pageable pageable) {
        log.debug("Getting security military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findSecurityCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get support categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getSupportCategories(Pageable pageable) {
        log.debug("Getting support military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findSupportCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get main service branches
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getMainServiceBranches(Pageable pageable) {
        log.debug("Getting main service branch military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findMainServiceBranches(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get intelligence categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getIntelligenceCategories(Pageable pageable) {
        log.debug("Getting intelligence military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findIntelligenceCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get medical categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getMedicalCategories(Pageable pageable) {
        log.debug("Getting medical military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findMedicalCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get logistics categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getLogisticsCategories(Pageable pageable) {
        log.debug("Getting logistics military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findLogisticsCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get communications categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getCommunicationsCategories(Pageable pageable) {
        log.debug("Getting communications military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findCommunicationsCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get republican guard categories
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getRepublicanGuardCategories(Pageable pageable) {
        log.debug("Getting republican guard military categories");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findRepublicanGuardCategories(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    /**
     * Get military categories missing translations
     */
    @Transactional(readOnly = true)
    public Page<MilitaryCategoryDTO> getMilitaryCategoriesMissingTranslations(Pageable pageable) {
        log.debug("Getting military categories missing translations");

        Page<MilitaryCategory> militaryCategories = militaryCategoryRepository.findMissingTranslations(pageable);
        return militaryCategories.map(MilitaryCategoryDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update military category
     */
    public MilitaryCategoryDTO updateMilitaryCategory(Long id, MilitaryCategoryDTO militaryCategoryDTO) {
        log.info("Updating military category with ID: {}", id);

        MilitaryCategory existingMilitaryCategory = getMilitaryCategoryEntityById(id);

        // Validate required fields
        validateRequiredFields(militaryCategoryDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(militaryCategoryDTO, id);

        // Update fields with exact field mapping
        existingMilitaryCategory.setDesignationAr(militaryCategoryDTO.getDesignationAr()); // F_01
        existingMilitaryCategory.setDesignationEn(militaryCategoryDTO.getDesignationEn()); // F_02
        existingMilitaryCategory.setDesignationFr(militaryCategoryDTO.getDesignationFr()); // F_03

        MilitaryCategory updatedMilitaryCategory = militaryCategoryRepository.save(existingMilitaryCategory);
        log.info("Successfully updated military category with ID: {}", id);

        return MilitaryCategoryDTO.fromEntity(updatedMilitaryCategory);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete military category
     */
    public void deleteMilitaryCategory(Long id) {
        log.info("Deleting military category with ID: {}", id);

        MilitaryCategory militaryCategory = getMilitaryCategoryEntityById(id);
        militaryCategoryRepository.delete(militaryCategory);

        log.info("Successfully deleted military category with ID: {}", id);
    }

    /**
     * Delete military category by ID (direct)
     */
    public void deleteMilitaryCategoryById(Long id) {
        log.info("Deleting military category by ID: {}", id);

        if (!militaryCategoryRepository.existsById(id)) {
            throw new RuntimeException("Military category not found with ID: " + id);
        }

        militaryCategoryRepository.deleteById(id);
        log.info("Successfully deleted military category with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if military category exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return militaryCategoryRepository.existsById(id);
    }

    /**
     * Check if military category exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return militaryCategoryRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of military categories
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return militaryCategoryRepository.countAllMilitaryCategories();
    }

    /**
     * Get count of main service branches
     */
    @Transactional(readOnly = true)
    public Long getMainServiceBranchesCount() {
        return militaryCategoryRepository.countMainServiceBranches();
    }

    /**
     * Get count of security services
     */
    @Transactional(readOnly = true)
    public Long getSecurityServicesCount() {
        return militaryCategoryRepository.countSecurityServices();
    }

    /**
     * Get count of support services
     */
    @Transactional(readOnly = true)
    public Long getSupportServicesCount() {
        return militaryCategoryRepository.countSupportServices();
    }

    /**
     * Get count of multilingual military categories
     */
    @Transactional(readOnly = true)
    public Long getMultilingualCount() {
        return militaryCategoryRepository.countMultilingualMilitaryCategories();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(MilitaryCategoryDTO militaryCategoryDTO, String operation) {
        if (militaryCategoryDTO.getDesignationFr() == null || militaryCategoryDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(MilitaryCategoryDTO militaryCategoryDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (militaryCategoryRepository.existsByDesignationFr(militaryCategoryDTO.getDesignationFr())) {
                throw new RuntimeException("Military category with French designation '" + militaryCategoryDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (militaryCategoryRepository.existsByDesignationFrAndIdNot(militaryCategoryDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another military category with French designation '" + militaryCategoryDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}