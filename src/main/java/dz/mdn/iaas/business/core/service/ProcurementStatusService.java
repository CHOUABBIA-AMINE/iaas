/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementStatusService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.ProcurementStatus;
import dz.mdn.iaas.business.core.repository.ProcurementStatusRepository;
import dz.mdn.iaas.business.core.dto.ProcurementStatusDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ProcurementStatus Service with CRUD operations
 * Handles procurement status management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProcurementStatusService {

    private final ProcurementStatusRepository procurementStatusRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new procurement status
     */
    public ProcurementStatusDTO createProcurementStatus(ProcurementStatusDTO procurementStatusDTO) {
        log.info("Creating procurement status with French designation: {} and designations: AR={}, EN={}", 
                procurementStatusDTO.getDesignationFr(), procurementStatusDTO.getDesignationAr(), 
                procurementStatusDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(procurementStatusDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(procurementStatusDTO, null);

        // Create entity with exact field mapping
        ProcurementStatus procurementStatus = new ProcurementStatus();
        procurementStatus.setDesignationAr(procurementStatusDTO.getDesignationAr()); // F_01
        procurementStatus.setDesignationEn(procurementStatusDTO.getDesignationEn()); // F_02
        procurementStatus.setDesignationFr(procurementStatusDTO.getDesignationFr()); // F_03

        ProcurementStatus savedProcurementStatus = procurementStatusRepository.save(procurementStatus);
        log.info("Successfully created procurement status with ID: {}", savedProcurementStatus.getId());

        return ProcurementStatusDTO.fromEntity(savedProcurementStatus);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get procurement status by ID
     */
    @Transactional(readOnly = true)
    public ProcurementStatusDTO getProcurementStatusById(Long id) {
        log.debug("Getting procurement status with ID: {}", id);

        ProcurementStatus procurementStatus = procurementStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procurement status not found with ID: " + id));

        return ProcurementStatusDTO.fromEntity(procurementStatus);
    }

    /**
     * Get procurement status entity by ID
     */
    @Transactional(readOnly = true)
    public ProcurementStatus getProcurementStatusEntityById(Long id) {
        return procurementStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procurement status not found with ID: " + id));
    }

    /**
     * Find procurement status by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementStatusDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding procurement status with French designation: {}", designationFr);

        return procurementStatusRepository.findByDesignationFr(designationFr)
                .map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Find procurement status by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementStatusDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding procurement status with Arabic designation: {}", designationAr);

        return procurementStatusRepository.findByDesignationAr(designationAr)
                .map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Find procurement status by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementStatusDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding procurement status with English designation: {}", designationEn);

        return procurementStatusRepository.findByDesignationEn(designationEn)
                .map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get all procurement statuses with pagination
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getAllProcurementStatuses(Pageable pageable) {
        log.debug("Getting all procurement statuses with pagination");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findAllOrderByDesignationFr(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Find one procurement status by ID
     */
    @Transactional(readOnly = true)
    public Optional<ProcurementStatusDTO> findOne(Long id) {
        log.debug("Finding procurement status by ID: {}", id);

        return procurementStatusRepository.findById(id)
                .map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Search procurement statuses by designation
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> searchProcurementStatuses(String searchTerm, Pageable pageable) {
        log.debug("Searching procurement statuses with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProcurementStatuses(pageable);
        }

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.searchByDesignation(searchTerm.trim(), pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get multilingual procurement statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getMultilingualProcurementStatuses(Pageable pageable) {
        log.debug("Getting multilingual procurement statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findMultilingualProcurementStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get planning statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getPlanningStatuses(Pageable pageable) {
        log.debug("Getting planning statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findPlanningStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get in-progress statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getInProgressStatuses(Pageable pageable) {
        log.debug("Getting in-progress statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findInProgressStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get completed statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getCompletedStatuses(Pageable pageable) {
        log.debug("Getting completed statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findCompletedStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get suspended statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getSuspendedStatuses(Pageable pageable) {
        log.debug("Getting suspended statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findSuspendedStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get cancelled statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getCancelledStatuses(Pageable pageable) {
        log.debug("Getting cancelled statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findCancelledStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get review statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getReviewStatuses(Pageable pageable) {
        log.debug("Getting review statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findReviewStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get approved statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getApprovedStatuses(Pageable pageable) {
        log.debug("Getting approved statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findApprovedStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get rejected statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getRejectedStatuses(Pageable pageable) {
        log.debug("Getting rejected statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findRejectedStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get on-hold statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getOnHoldStatuses(Pageable pageable) {
        log.debug("Getting on-hold statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findOnHoldStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get active statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getActiveStatuses(Pageable pageable) {
        log.debug("Getting active statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findActiveStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get final statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getFinalStatuses(Pageable pageable) {
        log.debug("Getting final statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findFinalStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get transitional statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getTransitionalStatuses(Pageable pageable) {
        log.debug("Getting transitional statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findTransitionalStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get initiation phase statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getInitiationPhaseStatuses(Pageable pageable) {
        log.debug("Getting initiation phase statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findInitiationPhaseStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get execution phase statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getExecutionPhaseStatuses(Pageable pageable) {
        log.debug("Getting execution phase statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findExecutionPhaseStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get monitoring phase statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getMonitoringPhaseStatuses(Pageable pageable) {
        log.debug("Getting monitoring phase statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findMonitoringPhaseStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    /**
     * Get closure phase statuses
     */
    @Transactional(readOnly = true)
    public Page<ProcurementStatusDTO> getClosurePhaseStatuses(Pageable pageable) {
        log.debug("Getting closure phase statuses");

        Page<ProcurementStatus> procurementStatuses = procurementStatusRepository.findClosurePhaseStatuses(pageable);
        return procurementStatuses.map(ProcurementStatusDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update procurement status
     */
    public ProcurementStatusDTO updateProcurementStatus(Long id, ProcurementStatusDTO procurementStatusDTO) {
        log.info("Updating procurement status with ID: {}", id);

        ProcurementStatus existingProcurementStatus = getProcurementStatusEntityById(id);

        // Validate required fields
        validateRequiredFields(procurementStatusDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(procurementStatusDTO, id);

        // Update fields with exact field mapping
        existingProcurementStatus.setDesignationAr(procurementStatusDTO.getDesignationAr()); // F_01
        existingProcurementStatus.setDesignationEn(procurementStatusDTO.getDesignationEn()); // F_02
        existingProcurementStatus.setDesignationFr(procurementStatusDTO.getDesignationFr()); // F_03

        ProcurementStatus updatedProcurementStatus = procurementStatusRepository.save(existingProcurementStatus);
        log.info("Successfully updated procurement status with ID: {}", id);

        return ProcurementStatusDTO.fromEntity(updatedProcurementStatus);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete procurement status
     */
    public void deleteProcurementStatus(Long id) {
        log.info("Deleting procurement status with ID: {}", id);

        ProcurementStatus procurementStatus = getProcurementStatusEntityById(id);
        procurementStatusRepository.delete(procurementStatus);

        log.info("Successfully deleted procurement status with ID: {}", id);
    }

    /**
     * Delete procurement status by ID (direct)
     */
    public void deleteProcurementStatusById(Long id) {
        log.info("Deleting procurement status by ID: {}", id);

        if (!procurementStatusRepository.existsById(id)) {
            throw new RuntimeException("Procurement status not found with ID: " + id);
        }

        procurementStatusRepository.deleteById(id);
        log.info("Successfully deleted procurement status with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if procurement status exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return procurementStatusRepository.existsById(id);
    }

    /**
     * Check if procurement status exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return procurementStatusRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of procurement statuses
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return procurementStatusRepository.countAllProcurementStatuses();
    }

    /**
     * Get count of planning statuses
     */
    @Transactional(readOnly = true)
    public Long getPlanningCount() {
        return procurementStatusRepository.countPlanningStatuses();
    }

    /**
     * Get count of in-progress statuses
     */
    @Transactional(readOnly = true)
    public Long getInProgressCount() {
        return procurementStatusRepository.countInProgressStatuses();
    }

    /**
     * Get count of completed statuses
     */
    @Transactional(readOnly = true)
    public Long getCompletedCount() {
        return procurementStatusRepository.countCompletedStatuses();
    }

    /**
     * Get count of suspended statuses
     */
    @Transactional(readOnly = true)
    public Long getSuspendedCount() {
        return procurementStatusRepository.countSuspendedStatuses();
    }

    /**
     * Get count of cancelled statuses
     */
    @Transactional(readOnly = true)
    public Long getCancelledCount() {
        return procurementStatusRepository.countCancelledStatuses();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ProcurementStatusDTO procurementStatusDTO, String operation) {
        if (procurementStatusDTO.getDesignationFr() == null || procurementStatusDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ProcurementStatusDTO procurementStatusDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (procurementStatusRepository.existsByDesignationFr(procurementStatusDTO.getDesignationFr())) {
                throw new RuntimeException("Procurement status with French designation '" + procurementStatusDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (procurementStatusRepository.existsByDesignationFrAndIdNot(procurementStatusDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another procurement status with French designation '" + procurementStatusDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
