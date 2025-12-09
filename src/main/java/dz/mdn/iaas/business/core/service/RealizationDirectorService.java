/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationDirectorService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.ProcurementDirector;
import dz.mdn.iaas.business.core.repository.RealizationDirectorRepository;
import dz.mdn.iaas.business.core.dto.RealizationDirectorDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * RealizationDirector Service with CRUD operations
 * Handles realization director management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RealizationDirectorService {

    private final RealizationDirectorRepository realizationDirectorRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new realization director
     */
    public RealizationDirectorDTO createRealizationDirector(RealizationDirectorDTO realizationDirectorDTO) {
        log.info("Creating realization director with French designation: {} and designations: AR={}, EN={}", 
                realizationDirectorDTO.getDesignationFr(), realizationDirectorDTO.getDesignationAr(), 
                realizationDirectorDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(realizationDirectorDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(realizationDirectorDTO, null);

        // Create entity with exact field mapping
        ProcurementDirector realizationDirector = new ProcurementDirector();
        realizationDirector.setDesignationAr(realizationDirectorDTO.getDesignationAr()); // F_01
        realizationDirector.setDesignationEn(realizationDirectorDTO.getDesignationEn()); // F_02
        realizationDirector.setDesignationFr(realizationDirectorDTO.getDesignationFr()); // F_03

        ProcurementDirector savedRealizationDirector = realizationDirectorRepository.save(realizationDirector);
        log.info("Successfully created realization director with ID: {}", savedRealizationDirector.getId());

        return RealizationDirectorDTO.fromEntity(savedRealizationDirector);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get realization director by ID
     */
    @Transactional(readOnly = true)
    public RealizationDirectorDTO getRealizationDirectorById(Long id) {
        log.debug("Getting realization director with ID: {}", id);

        ProcurementDirector realizationDirector = realizationDirectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realization director not found with ID: " + id));

        return RealizationDirectorDTO.fromEntity(realizationDirector);
    }

    /**
     * Get realization director entity by ID
     */
    @Transactional(readOnly = true)
    public ProcurementDirector getRealizationDirectorEntityById(Long id) {
        return realizationDirectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realization director not found with ID: " + id));
    }

    /**
     * Find realization director by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationDirectorDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding realization director with French designation: {}", designationFr);

        return realizationDirectorRepository.findByDesignationFr(designationFr)
                .map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Find realization director by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationDirectorDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding realization director with Arabic designation: {}", designationAr);

        return realizationDirectorRepository.findByDesignationAr(designationAr)
                .map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Find realization director by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationDirectorDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding realization director with English designation: {}", designationEn);

        return realizationDirectorRepository.findByDesignationEn(designationEn)
                .map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get all realization directors with pagination
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getAllRealizationDirectors(Pageable pageable) {
        log.debug("Getting all realization directors with pagination");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findAllOrderByDesignationFr(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Find one realization director by ID
     */
    @Transactional(readOnly = true)
    public Optional<RealizationDirectorDTO> findOne(Long id) {
        log.debug("Finding realization director by ID: {}", id);

        return realizationDirectorRepository.findById(id)
                .map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Search realization directors by designation
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> searchRealizationDirectors(String searchTerm, Pageable pageable) {
        log.debug("Searching realization directors with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllRealizationDirectors(pageable);
        }

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.searchByDesignation(searchTerm.trim(), pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get multilingual realization directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getMultilingualRealizationDirectors(Pageable pageable) {
        log.debug("Getting multilingual realization directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findMultilingualRealizationDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get executive directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getExecutiveDirectors(Pageable pageable) {
        log.debug("Getting executive directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findExecutiveDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get technical directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getTechnicalDirectors(Pageable pageable) {
        log.debug("Getting technical directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findTechnicalDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get project directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getProjectDirectors(Pageable pageable) {
        log.debug("Getting project directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findProjectDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get operations directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getOperationsDirectors(Pageable pageable) {
        log.debug("Getting operations directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findOperationsDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get financial directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getFinancialDirectors(Pageable pageable) {
        log.debug("Getting financial directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findFinancialDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get commercial directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getCommercialDirectors(Pageable pageable) {
        log.debug("Getting commercial directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findCommercialDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get HR directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getHRDirectors(Pageable pageable) {
        log.debug("Getting HR directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findHRDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get quality directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getQualityDirectors(Pageable pageable) {
        log.debug("Getting quality directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findQualityDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get regional directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getRegionalDirectors(Pageable pageable) {
        log.debug("Getting regional directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findRegionalDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get administrative directors
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getAdministrativeDirectors(Pageable pageable) {
        log.debug("Getting administrative directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findAdministrativeDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    /**
     * Get high authority directors (executive/senior level)
     */
    @Transactional(readOnly = true)
    public Page<RealizationDirectorDTO> getHighAuthorityDirectors(Pageable pageable) {
        log.debug("Getting high authority directors");

        Page<ProcurementDirector> realizationDirectors = realizationDirectorRepository.findHighAuthorityDirectors(pageable);
        return realizationDirectors.map(RealizationDirectorDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update realization director
     */
    public RealizationDirectorDTO updateRealizationDirector(Long id, RealizationDirectorDTO realizationDirectorDTO) {
        log.info("Updating realization director with ID: {}", id);

        ProcurementDirector existingRealizationDirector = getRealizationDirectorEntityById(id);

        // Validate required fields
        validateRequiredFields(realizationDirectorDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(realizationDirectorDTO, id);

        // Update fields with exact field mapping
        existingRealizationDirector.setDesignationAr(realizationDirectorDTO.getDesignationAr()); // F_01
        existingRealizationDirector.setDesignationEn(realizationDirectorDTO.getDesignationEn()); // F_02
        existingRealizationDirector.setDesignationFr(realizationDirectorDTO.getDesignationFr()); // F_03

        ProcurementDirector updatedRealizationDirector = realizationDirectorRepository.save(existingRealizationDirector);
        log.info("Successfully updated realization director with ID: {}", id);

        return RealizationDirectorDTO.fromEntity(updatedRealizationDirector);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete realization director
     */
    public void deleteRealizationDirector(Long id) {
        log.info("Deleting realization director with ID: {}", id);

        ProcurementDirector realizationDirector = getRealizationDirectorEntityById(id);
        realizationDirectorRepository.delete(realizationDirector);

        log.info("Successfully deleted realization director with ID: {}", id);
    }

    /**
     * Delete realization director by ID (direct)
     */
    public void deleteRealizationDirectorById(Long id) {
        log.info("Deleting realization director by ID: {}", id);

        if (!realizationDirectorRepository.existsById(id)) {
            throw new RuntimeException("Realization director not found with ID: " + id);
        }

        realizationDirectorRepository.deleteById(id);
        log.info("Successfully deleted realization director with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if realization director exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return realizationDirectorRepository.existsById(id);
    }

    /**
     * Check if realization director exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return realizationDirectorRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of realization directors
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return realizationDirectorRepository.countAllRealizationDirectors();
    }

    /**
     * Get count of executive directors
     */
    @Transactional(readOnly = true)
    public Long getExecutiveCount() {
        return realizationDirectorRepository.countExecutiveDirectors();
    }

    /**
     * Get count of technical directors
     */
    @Transactional(readOnly = true)
    public Long getTechnicalCount() {
        return realizationDirectorRepository.countTechnicalDirectors();
    }

    /**
     * Get count of project directors
     */
    @Transactional(readOnly = true)
    public Long getProjectCount() {
        return realizationDirectorRepository.countProjectDirectors();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(RealizationDirectorDTO realizationDirectorDTO, String operation) {
        if (realizationDirectorDTO.getDesignationFr() == null || realizationDirectorDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(RealizationDirectorDTO realizationDirectorDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (realizationDirectorRepository.existsByDesignationFr(realizationDirectorDTO.getDesignationFr())) {
                throw new RuntimeException("Realization director with French designation '" + realizationDirectorDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (realizationDirectorRepository.existsByDesignationFrAndIdNot(realizationDirectorDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another realization director with French designation '" + realizationDirectorDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
