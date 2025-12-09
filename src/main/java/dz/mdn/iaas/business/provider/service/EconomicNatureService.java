/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicNatureService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.service;

import dz.mdn.iaas.business.provider.model.EconomicNature;
import dz.mdn.iaas.business.provider.repository.EconomicNatureRepository;
import dz.mdn.iaas.business.provider.dto.EconomicNatureDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Economic Nature Service with CRUD operations
 * Handles economic nature management operations with multilingual support and dual unique constraints
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_01, F_02, F_04, F_05 are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EconomicNatureService {

    private final EconomicNatureRepository economicNatureRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new economic nature
     */
    public EconomicNatureDTO createEconomicNature(EconomicNatureDTO economicNatureDTO) {
        log.info("Creating economic nature with French designation: {} and designations: AR={}, EN={}, Acronyms: FR={}, AR={}, EN={}", 
                economicNatureDTO.getDesignationFr(), economicNatureDTO.getDesignationAr(), 
                economicNatureDTO.getDesignationEn(), economicNatureDTO.getAcronymFr(),
                economicNatureDTO.getAcronymAr(), economicNatureDTO.getAcronymEn());

        // Validate required fields
        validateRequiredFields(economicNatureDTO, "create");

        // Check for unique constraints violations
        validateUniqueConstraints(economicNatureDTO, null);

        // Create entity with exact field mapping
        EconomicNature economicNature = new EconomicNature();
        economicNature.setDesignationAr(economicNatureDTO.getDesignationAr()); // F_01
        economicNature.setDesignationEn(economicNatureDTO.getDesignationEn()); // F_02
        economicNature.setDesignationFr(economicNatureDTO.getDesignationFr()); // F_03
        economicNature.setAcronymAr(economicNatureDTO.getAcronymAr()); // F_04
        economicNature.setAcronymEn(economicNatureDTO.getAcronymEn()); // F_05
        economicNature.setAcronymFr(economicNatureDTO.getAcronymFr()); // F_06

        EconomicNature savedEconomicNature = economicNatureRepository.save(economicNature);
        log.info("Successfully created economic nature with ID: {}", savedEconomicNature.getId());

        return EconomicNatureDTO.fromEntity(savedEconomicNature);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get economic nature by ID
     */
    @Transactional(readOnly = true)
    public EconomicNatureDTO getEconomicNatureById(Long id) {
        log.debug("Getting economic nature with ID: {}", id);

        EconomicNature economicNature = economicNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Economic nature not found with ID: " + id));

        return EconomicNatureDTO.fromEntity(economicNature);
    }

    /**
     * Get economic nature entity by ID
     */
    @Transactional(readOnly = true)
    public EconomicNature getEconomicNatureEntityById(Long id) {
        return economicNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Economic nature not found with ID: " + id));
    }

    /**
     * Find economic nature by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicNatureDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding economic nature with French designation: {}", designationFr);

        return economicNatureRepository.findByDesignationFr(designationFr)
                .map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Find economic nature by French acronym (unique field F_06)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicNatureDTO> findByAcronymFr(String acronymFr) {
        log.debug("Finding economic nature with French acronym: {}", acronymFr);

        return economicNatureRepository.findByAcronymFr(acronymFr)
                .map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Find economic nature by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicNatureDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding economic nature with Arabic designation: {}", designationAr);

        return economicNatureRepository.findByDesignationAr(designationAr)
                .map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Find economic nature by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicNatureDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding economic nature with English designation: {}", designationEn);

        return economicNatureRepository.findByDesignationEn(designationEn)
                .map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Find economic nature by Arabic acronym (F_04)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicNatureDTO> findByAcronymAr(String acronymAr) {
        log.debug("Finding economic nature with Arabic acronym: {}", acronymAr);

        return economicNatureRepository.findByAcronymAr(acronymAr)
                .map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Find economic nature by English acronym (F_05)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicNatureDTO> findByAcronymEn(String acronymEn) {
        log.debug("Finding economic nature with English acronym: {}", acronymEn);

        return economicNatureRepository.findByAcronymEn(acronymEn)
                .map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get all economic natures with pagination
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getAllEconomicNatures(Pageable pageable) {
        log.debug("Getting all economic natures with pagination");

        Page<EconomicNature> economicNatures = economicNatureRepository.findAllOrderByDesignationFr(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get all economic natures ordered by French acronym
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getAllEconomicNaturesOrderedByAcronym(Pageable pageable) {
        log.debug("Getting all economic natures ordered by French acronym");

        Page<EconomicNature> economicNatures = economicNatureRepository.findAllOrderByAcronymFr(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Find one economic nature by ID
     */
    @Transactional(readOnly = true)
    public Optional<EconomicNatureDTO> findOne(Long id) {
        log.debug("Finding economic nature by ID: {}", id);

        return economicNatureRepository.findById(id)
                .map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Search economic natures by designation or acronym
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> searchEconomicNatures(String searchTerm, Pageable pageable) {
        log.debug("Searching economic natures with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllEconomicNatures(pageable);
        }

        Page<EconomicNature> economicNatures = economicNatureRepository.searchByDesignationOrAcronym(searchTerm.trim(), pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get multilingual economic natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getMultilingualEconomicNatures(Pageable pageable) {
        log.debug("Getting multilingual economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findMultilingualEconomicNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get public sector natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getPublicSectorNatures(Pageable pageable) {
        log.debug("Getting public sector economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findPublicSectorNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get private sector natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getPrivateSectorNatures(Pageable pageable) {
        log.debug("Getting private sector economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findPrivateSectorNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get company natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getCompanyNatures(Pageable pageable) {
        log.debug("Getting company economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findCompanyNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get cooperative natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getCooperativeNatures(Pageable pageable) {
        log.debug("Getting cooperative economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findCooperativeNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get association natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getAssociationNatures(Pageable pageable) {
        log.debug("Getting association economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findAssociationNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get individual enterprise natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getIndividualEnterpriseNatures(Pageable pageable) {
        log.debug("Getting individual enterprise economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findIndividualEnterpriseNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get foreign entity natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getForeignEntityNatures(Pageable pageable) {
        log.debug("Getting foreign entity economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findForeignEntityNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get limited liability natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getLimitedLiabilityNatures(Pageable pageable) {
        log.debug("Getting limited liability economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findLimitedLiabilityNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get partnership natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getPartnershipNatures(Pageable pageable) {
        log.debug("Getting partnership economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findPartnershipNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get government-related natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getGovernmentRelatedNatures(Pageable pageable) {
        log.debug("Getting government-related economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findGovernmentRelatedNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get commercial natures
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getCommercialNatures(Pageable pageable) {
        log.debug("Getting commercial economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findCommercialNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get natures with special registration requirements
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getSpecialRegistrationNatures(Pageable pageable) {
        log.debug("Getting special registration economic natures");

        Page<EconomicNature> economicNatures = economicNatureRepository.findSpecialRegistrationNatures(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    /**
     * Get economic natures missing translations
     */
    @Transactional(readOnly = true)
    public Page<EconomicNatureDTO> getEconomicNaturesMissingTranslations(Pageable pageable) {
        log.debug("Getting economic natures missing translations");

        Page<EconomicNature> economicNatures = economicNatureRepository.findMissingTranslations(pageable);
        return economicNatures.map(EconomicNatureDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update economic nature
     */
    public EconomicNatureDTO updateEconomicNature(Long id, EconomicNatureDTO economicNatureDTO) {
        log.info("Updating economic nature with ID: {}", id);

        EconomicNature existingEconomicNature = getEconomicNatureEntityById(id);

        // Validate required fields
        validateRequiredFields(economicNatureDTO, "update");

        // Check for unique constraints violations (excluding current record)
        validateUniqueConstraints(economicNatureDTO, id);

        // Update fields with exact field mapping
        existingEconomicNature.setDesignationAr(economicNatureDTO.getDesignationAr()); // F_01
        existingEconomicNature.setDesignationEn(economicNatureDTO.getDesignationEn()); // F_02
        existingEconomicNature.setDesignationFr(economicNatureDTO.getDesignationFr()); // F_03
        existingEconomicNature.setAcronymAr(economicNatureDTO.getAcronymAr()); // F_04
        existingEconomicNature.setAcronymEn(economicNatureDTO.getAcronymEn()); // F_05
        existingEconomicNature.setAcronymFr(economicNatureDTO.getAcronymFr()); // F_06

        EconomicNature updatedEconomicNature = economicNatureRepository.save(existingEconomicNature);
        log.info("Successfully updated economic nature with ID: {}", id);

        return EconomicNatureDTO.fromEntity(updatedEconomicNature);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete economic nature
     */
    public void deleteEconomicNature(Long id) {
        log.info("Deleting economic nature with ID: {}", id);

        EconomicNature economicNature = getEconomicNatureEntityById(id);
        economicNatureRepository.delete(economicNature);

        log.info("Successfully deleted economic nature with ID: {}", id);
    }

    /**
     * Delete economic nature by ID (direct)
     */
    public void deleteEconomicNatureById(Long id) {
        log.info("Deleting economic nature by ID: {}", id);

        if (!economicNatureRepository.existsById(id)) {
            throw new RuntimeException("Economic nature not found with ID: " + id);
        }

        economicNatureRepository.deleteById(id);
        log.info("Successfully deleted economic nature with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if economic nature exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return economicNatureRepository.existsById(id);
    }

    /**
     * Check if economic nature exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return economicNatureRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Check if economic nature exists by French acronym
     */
    @Transactional(readOnly = true)
    public boolean existsByAcronymFr(String acronymFr) {
        return economicNatureRepository.existsByAcronymFr(acronymFr);
    }

    /**
     * Get total count of economic natures
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return economicNatureRepository.countAllEconomicNatures();
    }

    /**
     * Get count of public sector natures
     */
    @Transactional(readOnly = true)
    public Long getPublicSectorNaturesCount() {
        return economicNatureRepository.countPublicSectorNatures();
    }

    /**
     * Get count of private sector natures
     */
    @Transactional(readOnly = true)
    public Long getPrivateSectorNaturesCount() {
        return economicNatureRepository.countPrivateSectorNatures();
    }

    /**
     * Get count of cooperative natures
     */
    @Transactional(readOnly = true)
    public Long getCooperativeNaturesCount() {
        return economicNatureRepository.countCooperativeNatures();
    }

    /**
     * Get count of association natures
     */
    @Transactional(readOnly = true)
    public Long getAssociationNaturesCount() {
        return economicNatureRepository.countAssociationNatures();
    }

    /**
     * Get count of multilingual economic natures
     */
    @Transactional(readOnly = true)
    public Long getMultilingualCount() {
        return economicNatureRepository.countMultilingualEconomicNatures();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(EconomicNatureDTO economicNatureDTO, String operation) {
        if (economicNatureDTO.getDesignationFr() == null || economicNatureDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (economicNatureDTO.getAcronymFr() == null || economicNatureDTO.getAcronymFr().trim().isEmpty()) {
            throw new RuntimeException("French acronym is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(EconomicNatureDTO economicNatureDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (economicNatureRepository.existsByDesignationFr(economicNatureDTO.getDesignationFr())) {
                throw new RuntimeException("Economic nature with French designation '" + economicNatureDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (economicNatureRepository.existsByDesignationFrAndIdNot(economicNatureDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another economic nature with French designation '" + economicNatureDTO.getDesignationFr() + "' already exists");
            }
        }

        // Check French acronym uniqueness (F_06)
        if (excludeId == null) {
            if (economicNatureRepository.existsByAcronymFr(economicNatureDTO.getAcronymFr())) {
                throw new RuntimeException("Economic nature with French acronym '" + economicNatureDTO.getAcronymFr() + "' already exists");
            }
        } else {
            if (economicNatureRepository.existsByAcronymFrAndIdNot(economicNatureDTO.getAcronymFr(), excludeId)) {
                throw new RuntimeException("Another economic nature with French acronym '" + economicNatureDTO.getAcronymFr() + "' already exists");
            }
        }
    }
}