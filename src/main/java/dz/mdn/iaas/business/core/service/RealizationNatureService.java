/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationNatureService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.RealizationNature;
import dz.mdn.iaas.business.core.repository.RealizationNatureRepository;
import dz.mdn.iaas.business.core.dto.RealizationNatureDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * RealizationNature Service with CRUD operations
 * Handles realization nature management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RealizationNatureService {

    private final RealizationNatureRepository realizationNatureRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new realization nature
     */
    public RealizationNatureDTO createRealizationNature(RealizationNatureDTO realizationNatureDTO) {
        log.info("Creating realization nature with French designation: {} and designations: AR={}, EN={}", 
                realizationNatureDTO.getDesignationFr(), realizationNatureDTO.getDesignationAr(), 
                realizationNatureDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(realizationNatureDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(realizationNatureDTO, null);

        // Create entity with exact field mapping
        RealizationNature realizationNature = new RealizationNature();
        realizationNature.setDesignationAr(realizationNatureDTO.getDesignationAr()); // F_01
        realizationNature.setDesignationEn(realizationNatureDTO.getDesignationEn()); // F_02
        realizationNature.setDesignationFr(realizationNatureDTO.getDesignationFr()); // F_03

        RealizationNature savedRealizationNature = realizationNatureRepository.save(realizationNature);
        log.info("Successfully created realization nature with ID: {}", savedRealizationNature.getId());

        return RealizationNatureDTO.fromEntity(savedRealizationNature);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get realization nature by ID
     */
    @Transactional(readOnly = true)
    public RealizationNatureDTO getRealizationNatureById(Long id) {
        log.debug("Getting realization nature with ID: {}", id);

        RealizationNature realizationNature = realizationNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realization nature not found with ID: " + id));

        return RealizationNatureDTO.fromEntity(realizationNature);
    }

    /**
     * Get realization nature entity by ID
     */
    @Transactional(readOnly = true)
    public RealizationNature getRealizationNatureEntityById(Long id) {
        return realizationNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realization nature not found with ID: " + id));
    }

    /**
     * Find realization nature by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationNatureDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding realization nature with French designation: {}", designationFr);

        return realizationNatureRepository.findByDesignationFr(designationFr)
                .map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Find realization nature by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationNatureDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding realization nature with Arabic designation: {}", designationAr);

        return realizationNatureRepository.findByDesignationAr(designationAr)
                .map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Find realization nature by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationNatureDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding realization nature with English designation: {}", designationEn);

        return realizationNatureRepository.findByDesignationEn(designationEn)
                .map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get all realization natures with pagination
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getAllRealizationNatures(Pageable pageable) {
        log.debug("Getting all realization natures with pagination");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findAllOrderByDesignationFr(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Find one realization nature by ID
     */
    @Transactional(readOnly = true)
    public Optional<RealizationNatureDTO> findOne(Long id) {
        log.debug("Finding realization nature by ID: {}", id);

        return realizationNatureRepository.findById(id)
                .map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Search realization natures by designation
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> searchRealizationNatures(String searchTerm, Pageable pageable) {
        log.debug("Searching realization natures with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllRealizationNatures(pageable);
        }

        Page<RealizationNature> realizationNatures = realizationNatureRepository.searchByDesignation(searchTerm.trim(), pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get multilingual realization natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getMultilingualRealizationNatures(Pageable pageable) {
        log.debug("Getting multilingual realization natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findMultilingualRealizationNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get infrastructure natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getInfrastructureNatures(Pageable pageable) {
        log.debug("Getting infrastructure natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findInfrastructureNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get technology natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getTechnologyNatures(Pageable pageable) {
        log.debug("Getting technology natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findTechnologyNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get service natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getServiceNatures(Pageable pageable) {
        log.debug("Getting service natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findServiceNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get manufacturing natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getManufacturingNatures(Pageable pageable) {
        log.debug("Getting manufacturing natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findManufacturingNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get research and development natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getResearchDevelopmentNatures(Pageable pageable) {
        log.debug("Getting research and development natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findResearchDevelopmentNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get energy and utilities natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getEnergyUtilitiesNatures(Pageable pageable) {
        log.debug("Getting energy and utilities natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findEnergyUtilitiesNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get environmental natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getEnvironmentalNatures(Pageable pageable) {
        log.debug("Getting environmental natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findEnvironmentalNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get commercial natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getCommercialNatures(Pageable pageable) {
        log.debug("Getting commercial natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findCommercialNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get education natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getEducationNatures(Pageable pageable) {
        log.debug("Getting education natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findEducationNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get health and medical natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getHealthMedicalNatures(Pageable pageable) {
        log.debug("Getting health and medical natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findHealthMedicalNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get transportation natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getTransportationNatures(Pageable pageable) {
        log.debug("Getting transportation natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findTransportationNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get agricultural natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getAgriculturalNatures(Pageable pageable) {
        log.debug("Getting agricultural natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findAgriculturalNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get high complexity natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getHighComplexityNatures(Pageable pageable) {
        log.debug("Getting high complexity natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findHighComplexityNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get public interest natures
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getPublicInterestNatures(Pageable pageable) {
        log.debug("Getting public interest natures");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findPublicInterestNatures(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get natures requiring environmental assessment
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getNaturesRequiringEnvironmentalAssessment(Pageable pageable) {
        log.debug("Getting natures requiring environmental assessment");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findNaturesRequiringEnvironmentalAssessment(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    /**
     * Get natures requiring technical expertise
     */
    @Transactional(readOnly = true)
    public Page<RealizationNatureDTO> getNaturesRequiringTechnicalExpertise(Pageable pageable) {
        log.debug("Getting natures requiring technical expertise");

        Page<RealizationNature> realizationNatures = realizationNatureRepository.findNaturesRequiringTechnicalExpertise(pageable);
        return realizationNatures.map(RealizationNatureDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update realization nature
     */
    public RealizationNatureDTO updateRealizationNature(Long id, RealizationNatureDTO realizationNatureDTO) {
        log.info("Updating realization nature with ID: {}", id);

        RealizationNature existingRealizationNature = getRealizationNatureEntityById(id);

        // Validate required fields
        validateRequiredFields(realizationNatureDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(realizationNatureDTO, id);

        // Update fields with exact field mapping
        existingRealizationNature.setDesignationAr(realizationNatureDTO.getDesignationAr()); // F_01
        existingRealizationNature.setDesignationEn(realizationNatureDTO.getDesignationEn()); // F_02
        existingRealizationNature.setDesignationFr(realizationNatureDTO.getDesignationFr()); // F_03

        RealizationNature updatedRealizationNature = realizationNatureRepository.save(existingRealizationNature);
        log.info("Successfully updated realization nature with ID: {}", id);

        return RealizationNatureDTO.fromEntity(updatedRealizationNature);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete realization nature
     */
    public void deleteRealizationNature(Long id) {
        log.info("Deleting realization nature with ID: {}", id);

        RealizationNature realizationNature = getRealizationNatureEntityById(id);
        realizationNatureRepository.delete(realizationNature);

        log.info("Successfully deleted realization nature with ID: {}", id);
    }

    /**
     * Delete realization nature by ID (direct)
     */
    public void deleteRealizationNatureById(Long id) {
        log.info("Deleting realization nature by ID: {}", id);

        if (!realizationNatureRepository.existsById(id)) {
            throw new RuntimeException("Realization nature not found with ID: " + id);
        }

        realizationNatureRepository.deleteById(id);
        log.info("Successfully deleted realization nature with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if realization nature exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return realizationNatureRepository.existsById(id);
    }

    /**
     * Check if realization nature exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return realizationNatureRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of realization natures
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return realizationNatureRepository.countAllRealizationNatures();
    }

    /**
     * Get count of infrastructure natures
     */
    @Transactional(readOnly = true)
    public Long getInfrastructureCount() {
        return realizationNatureRepository.countInfrastructureNatures();
    }

    /**
     * Get count of technology natures
     */
    @Transactional(readOnly = true)
    public Long getTechnologyCount() {
        return realizationNatureRepository.countTechnologyNatures();
    }

    /**
     * Get count of service natures
     */
    @Transactional(readOnly = true)
    public Long getServiceCount() {
        return realizationNatureRepository.countServiceNatures();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(RealizationNatureDTO realizationNatureDTO, String operation) {
        if (realizationNatureDTO.getDesignationFr() == null || realizationNatureDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(RealizationNatureDTO realizationNatureDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (realizationNatureRepository.existsByDesignationFr(realizationNatureDTO.getDesignationFr())) {
                throw new RuntimeException("Realization nature with French designation '" + realizationNatureDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (realizationNatureRepository.existsByDesignationFrAndIdNot(realizationNatureDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another realization nature with French designation '" + realizationNatureDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
