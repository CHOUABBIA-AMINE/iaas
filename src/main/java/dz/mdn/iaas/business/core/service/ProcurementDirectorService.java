/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementDirectorService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.ProcurementDirector;
import dz.mdn.iaas.business.core.repository.ProcurementDirectorRepository;
import dz.mdn.iaas.business.core.dto.ProcurementDirectorDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ProcurementDirector Service with CRUD operations
 * Handles procurement director management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProcurementDirectorService {

    private final ProcurementDirectorRepository procurementDirectorRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new procurement director
     */
    public ProcurementDirectorDTO createProcurementDirector(ProcurementDirectorDTO procurementDirectorDTO) {
        log.info("Creating procurement director with French designation: {} and designations: AR={}, EN={}", 
                procurementDirectorDTO.getDesignationFr(), procurementDirectorDTO.getDesignationAr(), 
                procurementDirectorDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(procurementDirectorDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(procurementDirectorDTO, null);

        // Create entity with exact field mapping
        ProcurementDirector procurementDirector = new ProcurementDirector();
        procurementDirector.setDesignationAr(procurementDirectorDTO.getDesignationAr()); // F_01
        procurementDirector.setDesignationEn(procurementDirectorDTO.getDesignationEn()); // F_02
        procurementDirector.setDesignationFr(procurementDirectorDTO.getDesignationFr()); // F_03

        ProcurementDirector savedProcurementDirector = procurementDirectorRepository.save(procurementDirector);
        log.info("Successfully created procurement director with ID: {}", savedProcurementDirector.getId());

        return ProcurementDirectorDTO.fromEntity(savedProcurementDirector);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get procurement director by ID
     */
    @Transactional(readOnly = true)
    public ProcurementDirectorDTO getProcurementDirectorById(Long id) {
        log.debug("Getting procurement director with ID: {}", id);

        ProcurementDirector procurementDirector = procurementDirectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procurement director not found with ID: " + id));

        return ProcurementDirectorDTO.fromEntity(procurementDirector);
    }

    /**
     * Get procurement director entity by ID
     */
    @Transactional(readOnly = true)
    public ProcurementDirector getProcurementDirectorEntityById(Long id) {
        return procurementDirectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procurement director not found with ID: " + id));
    }

    /**
     * Find procurement director by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementDirectorDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding procurement director with French designation: {}", designationFr);

        return procurementDirectorRepository.findByDesignationFr(designationFr)
                .map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Find procurement director by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementDirectorDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding procurement director with Arabic designation: {}", designationAr);

        return procurementDirectorRepository.findByDesignationAr(designationAr)
                .map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Find procurement director by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementDirectorDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding procurement director with English designation: {}", designationEn);

        return procurementDirectorRepository.findByDesignationEn(designationEn)
                .map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get all procurement directors with pagination
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getAllProcurementDirectors(Pageable pageable) {
        log.debug("Getting all procurement directors with pagination");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findAllOrderByDesignationFr(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Find one procurement director by ID
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementDirectorDTO> findOne(Long id) {
        log.debug("Finding procurement director by ID: {}", id);

        return procurementDirectorRepository.findById(id)
                .map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Search procurement directors by designation
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> searchProcurementDirectors(String searchTerm, Pageable pageable) {
        log.debug("Searching procurement directors with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProcurementDirectors(pageable);
        }

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.searchByDesignation(searchTerm.trim(), pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get multilingual procurement directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getMultilingualProcurementDirectors(Pageable pageable) {
        log.debug("Getting multilingual procurement directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findMultilingualProcurementDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get executive directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getExecutiveDirectors(Pageable pageable) {
        log.debug("Getting executive directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findExecutiveDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get technical directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getTechnicalDirectors(Pageable pageable) {
        log.debug("Getting technical directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findTechnicalDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get project directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getProjectDirectors(Pageable pageable) {
        log.debug("Getting project directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findProjectDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get operations directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getOperationsDirectors(Pageable pageable) {
        log.debug("Getting operations directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findOperationsDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get financial directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getFinancialDirectors(Pageable pageable) {
        log.debug("Getting financial directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findFinancialDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get commercial directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getCommercialDirectors(Pageable pageable) {
        log.debug("Getting commercial directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findCommercialDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get HR directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getHRDirectors(Pageable pageable) {
        log.debug("Getting HR directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findHRDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get quality directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getQualityDirectors(Pageable pageable) {
        log.debug("Getting quality directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findQualityDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get regional directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getRegionalDirectors(Pageable pageable) {
        log.debug("Getting regional directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findRegionalDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get administrative directors
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getAdministrativeDirectors(Pageable pageable) {
        log.debug("Getting administrative directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findAdministrativeDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    /**
     * Get high authority directors (executive/senior level)
     */
    @Transactional(readOnly = true)
    public Page<ProcurementDirectorDTO> getHighAuthorityDirectors(Pageable pageable) {
        log.debug("Getting high authority directors");

        Page<ProcurementDirector> procurementDirectors = procurementDirectorRepository.findHighAuthorityDirectors(pageable);
        return procurementDirectors.map(ProcurementDirectorDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update procurement director
     */
    public ProcurementDirectorDTO updateProcurementDirector(Long id, ProcurementDirectorDTO procurementDirectorDTO) {
        log.info("Updating procurement director with ID: {}", id);

        ProcurementDirector existingProcurementDirector = getProcurementDirectorEntityById(id);

        // Validate required fields
        validateRequiredFields(procurementDirectorDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(procurementDirectorDTO, id);

        // Update fields with exact field mapping
        existingProcurementDirector.setDesignationAr(procurementDirectorDTO.getDesignationAr()); // F_01
        existingProcurementDirector.setDesignationEn(procurementDirectorDTO.getDesignationEn()); // F_02
        existingProcurementDirector.setDesignationFr(procurementDirectorDTO.getDesignationFr()); // F_03

        ProcurementDirector updatedProcurementDirector = procurementDirectorRepository.save(existingProcurementDirector);
        log.info("Successfully updated procurement director with ID: {}", id);

        return ProcurementDirectorDTO.fromEntity(updatedProcurementDirector);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete procurement director
     */
    public void deleteProcurementDirector(Long id) {
        log.info("Deleting procurement director with ID: {}", id);

        ProcurementDirector procurementDirector = getProcurementDirectorEntityById(id);
        procurementDirectorRepository.delete(procurementDirector);

        log.info("Successfully deleted procurement director with ID: {}", id);
    }

    /**
     * Delete procurement director by ID (direct)
     */
    public void deleteProcurementDirectorById(Long id) {
        log.info("Deleting procurement director by ID: {}", id);

        if (!procurementDirectorRepository.existsById(id)) {
            throw new RuntimeException("Procurement director not found with ID: " + id);
        }

        procurementDirectorRepository.deleteById(id);
        log.info("Successfully deleted procurement director with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if procurement director exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return procurementDirectorRepository.existsById(id);
    }

    /**
     * Check if procurement director exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return procurementDirectorRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of procurement directors
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return procurementDirectorRepository.countAllProcurementDirectors();
    }

    /**
     * Get count of executive directors
     */
    @Transactional(readOnly = true)
    public Long getExecutiveCount() {
        return procurementDirectorRepository.countExecutiveDirectors();
    }

    /**
     * Get count of technical directors
     */
    @Transactional(readOnly = true)
    public Long getTechnicalCount() {
        return procurementDirectorRepository.countTechnicalDirectors();
    }

    /**
     * Get count of project directors
     */
    @Transactional(readOnly = true)
    public Long getProjectCount() {
        return procurementDirectorRepository.countProjectDirectors();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ProcurementDirectorDTO procurementDirectorDTO, String operation) {
        if (procurementDirectorDTO.getDesignationFr() == null || procurementDirectorDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ProcurementDirectorDTO procurementDirectorDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (procurementDirectorRepository.existsByDesignationFr(procurementDirectorDTO.getDesignationFr())) {
                throw new RuntimeException("Procurement director with French designation '" + procurementDirectorDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (procurementDirectorRepository.existsByDesignationFrAndIdNot(procurementDirectorDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another procurement director with French designation '" + procurementDirectorDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
