/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementNatureService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.ProcurementNature;
import dz.mdn.iaas.business.core.repository.ProcurementNatureRepository;
import dz.mdn.iaas.business.core.dto.ProcurementNatureDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ProcurementNature Service with CRUD operations
 * Handles procurement nature management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProcurementNatureService {

    private final ProcurementNatureRepository procurementNatureRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new procurement nature
     */
    public ProcurementNatureDTO createProcurementNature(ProcurementNatureDTO procurementNatureDTO) {
        log.info("Creating procurement nature with French designation: {} and designations: AR={}, EN={}", 
                procurementNatureDTO.getDesignationFr(), procurementNatureDTO.getDesignationAr(), 
                procurementNatureDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(procurementNatureDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(procurementNatureDTO, null);

        // Create entity with exact field mapping
        ProcurementNature procurementNature = new ProcurementNature();
        procurementNature.setDesignationAr(procurementNatureDTO.getDesignationAr()); // F_01
        procurementNature.setDesignationEn(procurementNatureDTO.getDesignationEn()); // F_02
        procurementNature.setDesignationFr(procurementNatureDTO.getDesignationFr()); // F_03

        ProcurementNature savedProcurementNature = procurementNatureRepository.save(procurementNature);
        log.info("Successfully created procurement nature with ID: {}", savedProcurementNature.getId());

        return ProcurementNatureDTO.fromEntity(savedProcurementNature);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get procurement nature by ID
     */
    @Transactional(readOnly = true)
    public ProcurementNatureDTO getProcurementNatureById(Long id) {
        log.debug("Getting procurement nature with ID: {}", id);

        ProcurementNature procurementNature = procurementNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procurement nature not found with ID: " + id));

        return ProcurementNatureDTO.fromEntity(procurementNature);
    }

    /**
     * Get procurement nature entity by ID
     */
    @Transactional(readOnly = true)
    public ProcurementNature getProcurementNatureEntityById(Long id) {
        return procurementNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procurement nature not found with ID: " + id));
    }

    /**
     * Find procurement nature by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementNatureDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding procurement nature with French designation: {}", designationFr);

        return procurementNatureRepository.findByDesignationFr(designationFr)
                .map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Find procurement nature by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementNatureDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding procurement nature with Arabic designation: {}", designationAr);

        return procurementNatureRepository.findByDesignationAr(designationAr)
                .map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Find procurement nature by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementNatureDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding procurement nature with English designation: {}", designationEn);

        return procurementNatureRepository.findByDesignationEn(designationEn)
                .map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get all procurement natures with pagination
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getAllProcurementNatures(Pageable pageable) {
        log.debug("Getting all procurement natures with pagination");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findAllOrderByDesignationFr(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Find one procurement nature by ID
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementNatureDTO> findOne(Long id) {
        log.debug("Finding procurement nature by ID: {}", id);

        return procurementNatureRepository.findById(id)
                .map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Search procurement natures by designation
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> searchProcurementNatures(String searchTerm, Pageable pageable) {
        log.debug("Searching procurement natures with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProcurementNatures(pageable);
        }

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.searchByDesignation(searchTerm.trim(), pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get multilingual procurement natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getMultilingualProcurementNatures(Pageable pageable) {
        log.debug("Getting multilingual procurement natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findMultilingualProcurementNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get infrastructure natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getInfrastructureNatures(Pageable pageable) {
        log.debug("Getting infrastructure natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findInfrastructureNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get technology natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getTechnologyNatures(Pageable pageable) {
        log.debug("Getting technology natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findTechnologyNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get service natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getServiceNatures(Pageable pageable) {
        log.debug("Getting service natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findServiceNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get manufacturing natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getManufacturingNatures(Pageable pageable) {
        log.debug("Getting manufacturing natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findManufacturingNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get research and development natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getResearchDevelopmentNatures(Pageable pageable) {
        log.debug("Getting research and development natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findResearchDevelopmentNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get energy and utilities natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getEnergyUtilitiesNatures(Pageable pageable) {
        log.debug("Getting energy and utilities natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findEnergyUtilitiesNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get environmental natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getEnvironmentalNatures(Pageable pageable) {
        log.debug("Getting environmental natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findEnvironmentalNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get commercial natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getCommercialNatures(Pageable pageable) {
        log.debug("Getting commercial natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findCommercialNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get education natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getEducationNatures(Pageable pageable) {
        log.debug("Getting education natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findEducationNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get health and medical natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getHealthMedicalNatures(Pageable pageable) {
        log.debug("Getting health and medical natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findHealthMedicalNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get transportation natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getTransportationNatures(Pageable pageable) {
        log.debug("Getting transportation natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findTransportationNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get agricultural natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getAgriculturalNatures(Pageable pageable) {
        log.debug("Getting agricultural natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findAgriculturalNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get high complexity natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getHighComplexityNatures(Pageable pageable) {
        log.debug("Getting high complexity natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findHighComplexityNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get public interest natures
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getPublicInterestNatures(Pageable pageable) {
        log.debug("Getting public interest natures");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findPublicInterestNatures(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get natures requiring environmental assessment
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getNaturesRequiringEnvironmentalAssessment(Pageable pageable) {
        log.debug("Getting natures requiring environmental assessment");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findNaturesRequiringEnvironmentalAssessment(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    /**
     * Get natures requiring technical expertise
     */
    @Transactional(readOnly = true)
    public Page<ProcurementNatureDTO> getNaturesRequiringTechnicalExpertise(Pageable pageable) {
        log.debug("Getting natures requiring technical expertise");

        Page<ProcurementNature> procurementNatures = procurementNatureRepository.findNaturesRequiringTechnicalExpertise(pageable);
        return procurementNatures.map(ProcurementNatureDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update procurement nature
     */
    public ProcurementNatureDTO updateProcurementNature(Long id, ProcurementNatureDTO procurementNatureDTO) {
        log.info("Updating procurement nature with ID: {}", id);

        ProcurementNature existingProcurementNature = getProcurementNatureEntityById(id);

        // Validate required fields
        validateRequiredFields(procurementNatureDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(procurementNatureDTO, id);

        // Update fields with exact field mapping
        existingProcurementNature.setDesignationAr(procurementNatureDTO.getDesignationAr()); // F_01
        existingProcurementNature.setDesignationEn(procurementNatureDTO.getDesignationEn()); // F_02
        existingProcurementNature.setDesignationFr(procurementNatureDTO.getDesignationFr()); // F_03

        ProcurementNature updatedProcurementNature = procurementNatureRepository.save(existingProcurementNature);
        log.info("Successfully updated procurement nature with ID: {}", id);

        return ProcurementNatureDTO.fromEntity(updatedProcurementNature);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete procurement nature
     */
    public void deleteProcurementNature(Long id) {
        log.info("Deleting procurement nature with ID: {}", id);

        ProcurementNature procurementNature = getProcurementNatureEntityById(id);
        procurementNatureRepository.delete(procurementNature);

        log.info("Successfully deleted procurement nature with ID: {}", id);
    }

    /**
     * Delete procurement nature by ID (direct)
     */
    public void deleteProcurementNatureById(Long id) {
        log.info("Deleting procurement nature by ID: {}", id);

        if (!procurementNatureRepository.existsById(id)) {
            throw new RuntimeException("Procurement nature not found with ID: " + id);
        }

        procurementNatureRepository.deleteById(id);
        log.info("Successfully deleted procurement nature with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if procurement nature exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return procurementNatureRepository.existsById(id);
    }

    /**
     * Check if procurement nature exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return procurementNatureRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of procurement natures
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return procurementNatureRepository.countAllProcurementNatures();
    }

    /**
     * Get count of infrastructure natures
     */
    @Transactional(readOnly = true)
    public Long getInfrastructureCount() {
        return procurementNatureRepository.countInfrastructureNatures();
    }

    /**
     * Get count of technology natures
     */
    @Transactional(readOnly = true)
    public Long getTechnologyCount() {
        return procurementNatureRepository.countTechnologyNatures();
    }

    /**
     * Get count of service natures
     */
    @Transactional(readOnly = true)
    public Long getServiceCount() {
        return procurementNatureRepository.countServiceNatures();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ProcurementNatureDTO procurementNatureDTO, String operation) {
        if (procurementNatureDTO.getDesignationFr() == null || procurementNatureDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ProcurementNatureDTO procurementNatureDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (procurementNatureRepository.existsByDesignationFr(procurementNatureDTO.getDesignationFr())) {
                throw new RuntimeException("Procurement nature with French designation '" + procurementNatureDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (procurementNatureRepository.existsByDesignationFrAndIdNot(procurementNatureDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another procurement nature with French designation '" + procurementNatureDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
