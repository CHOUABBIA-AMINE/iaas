/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationStatusService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.ProcurementStatus;
import dz.mdn.iaas.business.core.repository.RealizationStatusRepository;
import dz.mdn.iaas.business.core.dto.RealizationStatusDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * RealizationStatus Service with CRUD operations
 * Handles realization status management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RealizationStatusService {

    private final RealizationStatusRepository realizationStatusRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new realization status
     */
    public RealizationStatusDTO createRealizationStatus(RealizationStatusDTO realizationStatusDTO) {
        log.info("Creating realization status with French designation: {} and designations: AR={}, EN={}", 
                realizationStatusDTO.getDesignationFr(), realizationStatusDTO.getDesignationAr(), 
                realizationStatusDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(realizationStatusDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(realizationStatusDTO, null);

        // Create entity with exact field mapping
        ProcurementStatus realizationStatus = new ProcurementStatus();
        realizationStatus.setDesignationAr(realizationStatusDTO.getDesignationAr()); // F_01
        realizationStatus.setDesignationEn(realizationStatusDTO.getDesignationEn()); // F_02
        realizationStatus.setDesignationFr(realizationStatusDTO.getDesignationFr()); // F_03

        ProcurementStatus savedRealizationStatus = realizationStatusRepository.save(realizationStatus);
        log.info("Successfully created realization status with ID: {}", savedRealizationStatus.getId());

        return RealizationStatusDTO.fromEntity(savedRealizationStatus);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get realization status by ID
     */
    @Transactional(readOnly = true)
    public RealizationStatusDTO getRealizationStatusById(Long id) {
        log.debug("Getting realization status with ID: {}", id);

        ProcurementStatus realizationStatus = realizationStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realization status not found with ID: " + id));

        return RealizationStatusDTO.fromEntity(realizationStatus);
    }

    /**
     * Get realization status entity by ID
     */
    @Transactional(readOnly = true)
    public ProcurementStatus getRealizationStatusEntityById(Long id) {
        return realizationStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realization status not found with ID: " + id));
    }

    /**
     * Find realization status by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationStatusDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding realization status with French designation: {}", designationFr);

        return realizationStatusRepository.findByDesignationFr(designationFr)
                .map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Find realization status by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationStatusDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding realization status with Arabic designation: {}", designationAr);

        return realizationStatusRepository.findByDesignationAr(designationAr)
                .map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Find realization status by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<RealizationStatusDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding realization status with English designation: {}", designationEn);

        return realizationStatusRepository.findByDesignationEn(designationEn)
                .map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get all realization statuses with pagination
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getAllRealizationStatuses(Pageable pageable) {
        log.debug("Getting all realization statuses with pagination");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findAllOrderByDesignationFr(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Find one realization status by ID
     */
    @Transactional(readOnly = true)
    public Optional<RealizationStatusDTO> findOne(Long id) {
        log.debug("Finding realization status by ID: {}", id);

        return realizationStatusRepository.findById(id)
                .map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Search realization statuses by designation
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> searchRealizationStatuses(String searchTerm, Pageable pageable) {
        log.debug("Searching realization statuses with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllRealizationStatuses(pageable);
        }

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.searchByDesignation(searchTerm.trim(), pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get multilingual realization statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getMultilingualRealizationStatuses(Pageable pageable) {
        log.debug("Getting multilingual realization statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findMultilingualRealizationStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get planning statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getPlanningStatuses(Pageable pageable) {
        log.debug("Getting planning statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findPlanningStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get in-progress statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getInProgressStatuses(Pageable pageable) {
        log.debug("Getting in-progress statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findInProgressStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get completed statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getCompletedStatuses(Pageable pageable) {
        log.debug("Getting completed statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findCompletedStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get suspended statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getSuspendedStatuses(Pageable pageable) {
        log.debug("Getting suspended statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findSuspendedStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get cancelled statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getCancelledStatuses(Pageable pageable) {
        log.debug("Getting cancelled statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findCancelledStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get review statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getReviewStatuses(Pageable pageable) {
        log.debug("Getting review statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findReviewStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get approved statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getApprovedStatuses(Pageable pageable) {
        log.debug("Getting approved statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findApprovedStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get rejected statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getRejectedStatuses(Pageable pageable) {
        log.debug("Getting rejected statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findRejectedStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get on-hold statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getOnHoldStatuses(Pageable pageable) {
        log.debug("Getting on-hold statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findOnHoldStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get active statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getActiveStatuses(Pageable pageable) {
        log.debug("Getting active statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findActiveStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get final statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getFinalStatuses(Pageable pageable) {
        log.debug("Getting final statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findFinalStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get transitional statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getTransitionalStatuses(Pageable pageable) {
        log.debug("Getting transitional statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findTransitionalStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get initiation phase statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getInitiationPhaseStatuses(Pageable pageable) {
        log.debug("Getting initiation phase statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findInitiationPhaseStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get execution phase statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getExecutionPhaseStatuses(Pageable pageable) {
        log.debug("Getting execution phase statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findExecutionPhaseStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get monitoring phase statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getMonitoringPhaseStatuses(Pageable pageable) {
        log.debug("Getting monitoring phase statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findMonitoringPhaseStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    /**
     * Get closure phase statuses
     */
    @Transactional(readOnly = true)
    public Page<RealizationStatusDTO> getClosurePhaseStatuses(Pageable pageable) {
        log.debug("Getting closure phase statuses");

        Page<ProcurementStatus> realizationStatuses = realizationStatusRepository.findClosurePhaseStatuses(pageable);
        return realizationStatuses.map(RealizationStatusDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update realization status
     */
    public RealizationStatusDTO updateRealizationStatus(Long id, RealizationStatusDTO realizationStatusDTO) {
        log.info("Updating realization status with ID: {}", id);

        ProcurementStatus existingRealizationStatus = getRealizationStatusEntityById(id);

        // Validate required fields
        validateRequiredFields(realizationStatusDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(realizationStatusDTO, id);

        // Update fields with exact field mapping
        existingRealizationStatus.setDesignationAr(realizationStatusDTO.getDesignationAr()); // F_01
        existingRealizationStatus.setDesignationEn(realizationStatusDTO.getDesignationEn()); // F_02
        existingRealizationStatus.setDesignationFr(realizationStatusDTO.getDesignationFr()); // F_03

        ProcurementStatus updatedRealizationStatus = realizationStatusRepository.save(existingRealizationStatus);
        log.info("Successfully updated realization status with ID: {}", id);

        return RealizationStatusDTO.fromEntity(updatedRealizationStatus);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete realization status
     */
    public void deleteRealizationStatus(Long id) {
        log.info("Deleting realization status with ID: {}", id);

        ProcurementStatus realizationStatus = getRealizationStatusEntityById(id);
        realizationStatusRepository.delete(realizationStatus);

        log.info("Successfully deleted realization status with ID: {}", id);
    }

    /**
     * Delete realization status by ID (direct)
     */
    public void deleteRealizationStatusById(Long id) {
        log.info("Deleting realization status by ID: {}", id);

        if (!realizationStatusRepository.existsById(id)) {
            throw new RuntimeException("Realization status not found with ID: " + id);
        }

        realizationStatusRepository.deleteById(id);
        log.info("Successfully deleted realization status with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if realization status exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return realizationStatusRepository.existsById(id);
    }

    /**
     * Check if realization status exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return realizationStatusRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of realization statuses
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return realizationStatusRepository.countAllRealizationStatuses();
    }

    /**
     * Get count of planning statuses
     */
    @Transactional(readOnly = true)
    public Long getPlanningCount() {
        return realizationStatusRepository.countPlanningStatuses();
    }

    /**
     * Get count of in-progress statuses
     */
    @Transactional(readOnly = true)
    public Long getInProgressCount() {
        return realizationStatusRepository.countInProgressStatuses();
    }

    /**
     * Get count of completed statuses
     */
    @Transactional(readOnly = true)
    public Long getCompletedCount() {
        return realizationStatusRepository.countCompletedStatuses();
    }

    /**
     * Get count of suspended statuses
     */
    @Transactional(readOnly = true)
    public Long getSuspendedCount() {
        return realizationStatusRepository.countSuspendedStatuses();
    }

    /**
     * Get count of cancelled statuses
     */
    @Transactional(readOnly = true)
    public Long getCancelledCount() {
        return realizationStatusRepository.countCancelledStatuses();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(RealizationStatusDTO realizationStatusDTO, String operation) {
        if (realizationStatusDTO.getDesignationFr() == null || realizationStatusDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(RealizationStatusDTO realizationStatusDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (realizationStatusRepository.existsByDesignationFr(realizationStatusDTO.getDesignationFr())) {
                throw new RuntimeException("Realization status with French designation '" + realizationStatusDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (realizationStatusRepository.existsByDesignationFrAndIdNot(realizationStatusDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another realization status with French designation '" + realizationStatusDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
