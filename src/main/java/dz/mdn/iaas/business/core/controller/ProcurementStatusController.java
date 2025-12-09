/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementStatusController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.ProcurementStatusService;
import dz.mdn.iaas.business.core.dto.ProcurementStatusDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ProcurementStatus REST Controller
 * Handles procurement status operations: create, get metadata, delete, get all
 * Based on exact ProcurementStatus model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/procurementStatus")
@RequiredArgsConstructor
@Slf4j
public class ProcurementStatusController {

    private final ProcurementStatusService procurementStatusService;

    // ========== POST ONE REALIZATION STATUS ==========

    /**
     * Create new procurement status
     * Creates procurement status with multilingual designations and project lifecycle management
     */
    @PostMapping
    public ResponseEntity<ProcurementStatusDTO> createProcurementStatus(@Valid @RequestBody ProcurementStatusDTO procurementStatusDTO) {
        log.info("Creating procurement status with French designation: {} and designations: AR={}, EN={}", 
                procurementStatusDTO.getDesignationFr(), procurementStatusDTO.getDesignationAr(), 
                procurementStatusDTO.getDesignationEn());
        
        ProcurementStatusDTO createdProcurementStatus = procurementStatusService.createProcurementStatus(procurementStatusDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcurementStatus);
    }

    // ========== GET METADATA ==========

    /**
     * Get procurement status metadata by ID
     * Returns procurement status information with project lifecycle classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProcurementStatusDTO> getProcurementStatusMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for procurement status ID: {}", id);
        
        ProcurementStatusDTO procurementStatusMetadata = procurementStatusService.getProcurementStatusById(id);
        
        return ResponseEntity.ok(procurementStatusMetadata);
    }

    /**
     * Get procurement status by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ProcurementStatusDTO> getProcurementStatusByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting procurement status by French designation: {}", designationFr);
        
        return procurementStatusService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get procurement status by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<ProcurementStatusDTO> getProcurementStatusByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting procurement status by Arabic designation: {}", designationAr);
        
        return procurementStatusService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get procurement status by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<ProcurementStatusDTO> getProcurementStatusByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting procurement status by English designation: {}", designationEn);
        
        return procurementStatusService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete procurement status by ID
     * Removes procurement status from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcurementStatus(@PathVariable Long id) {
        log.info("Deleting procurement status with ID: {}", id);
        
        procurementStatusService.deleteProcurementStatus(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all procurement statuses with pagination
     * Returns list of all procurement statuses ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ProcurementStatusDTO>> getAllProcurementStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all procurement statuses - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getAllProcurementStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search procurement statuses by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProcurementStatusDTO>> searchProcurementStatuses(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching procurement statuses with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.searchProcurementStatuses(query, pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    // ========== STATUS CATEGORY ENDPOINTS ==========

    /**
     * Get multilingual procurement statuses
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ProcurementStatusDTO>> getMultilingualProcurementStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual procurement statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getMultilingualProcurementStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get planning statuses
     */
    @GetMapping("/planning")
    public ResponseEntity<Page<ProcurementStatusDTO>> getPlanningStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planning statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getPlanningStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get in-progress statuses
     */
    @GetMapping("/in-progress")
    public ResponseEntity<Page<ProcurementStatusDTO>> getInProgressStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting in-progress statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getInProgressStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get completed statuses
     */
    @GetMapping("/completed")
    public ResponseEntity<Page<ProcurementStatusDTO>> getCompletedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting completed statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getCompletedStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get suspended statuses
     */
    @GetMapping("/suspended")
    public ResponseEntity<Page<ProcurementStatusDTO>> getSuspendedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting suspended statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getSuspendedStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get cancelled statuses
     */
    @GetMapping("/cancelled")
    public ResponseEntity<Page<ProcurementStatusDTO>> getCancelledStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting cancelled statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getCancelledStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get review statuses
     */
    @GetMapping("/review")
    public ResponseEntity<Page<ProcurementStatusDTO>> getReviewStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting review statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getReviewStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get approved statuses
     */
    @GetMapping("/approved")
    public ResponseEntity<Page<ProcurementStatusDTO>> getApprovedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting approved statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getApprovedStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get rejected statuses
     */
    @GetMapping("/rejected")
    public ResponseEntity<Page<ProcurementStatusDTO>> getRejectedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rejected statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getRejectedStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get on-hold statuses
     */
    @GetMapping("/on-hold")
    public ResponseEntity<Page<ProcurementStatusDTO>> getOnHoldStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting on-hold statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getOnHoldStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    // ========== STATUS LIFECYCLE ENDPOINTS ==========

    /**
     * Get active statuses
     */
    @GetMapping("/active")
    public ResponseEntity<Page<ProcurementStatusDTO>> getActiveStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting active statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getActiveStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get final statuses
     */
    @GetMapping("/final")
    public ResponseEntity<Page<ProcurementStatusDTO>> getFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getFinalStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get transitional statuses
     */
    @GetMapping("/transitional")
    public ResponseEntity<Page<ProcurementStatusDTO>> getTransitionalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting transitional statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getTransitionalStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    // ========== PROJECT PHASE ENDPOINTS ==========

    /**
     * Get initiation phase statuses
     */
    @GetMapping("/phase/initiation")
    public ResponseEntity<Page<ProcurementStatusDTO>> getInitiationPhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting initiation phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getInitiationPhaseStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get execution phase statuses
     */
    @GetMapping("/phase/execution")
    public ResponseEntity<Page<ProcurementStatusDTO>> getExecutionPhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting execution phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getExecutionPhaseStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get monitoring phase statuses
     */
    @GetMapping("/phase/monitoring")
    public ResponseEntity<Page<ProcurementStatusDTO>> getMonitoringPhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting monitoring phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getMonitoringPhaseStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    /**
     * Get closure phase statuses
     */
    @GetMapping("/phase/closure")
    public ResponseEntity<Page<ProcurementStatusDTO>> getClosurePhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting closure phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementStatusDTO> procurementStatuses = procurementStatusService.getClosurePhaseStatuses(pageable);
        
        return ResponseEntity.ok(procurementStatuses);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update procurement status metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProcurementStatusDTO> updateProcurementStatus(
            @PathVariable Long id,
            @Valid @RequestBody ProcurementStatusDTO procurementStatusDTO) {
        
        log.info("Updating procurement status with ID: {}", id);
        
        ProcurementStatusDTO updatedProcurementStatus = procurementStatusService.updateProcurementStatus(id, procurementStatusDTO);
        
        return ResponseEntity.ok(updatedProcurementStatus);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if procurement status exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkProcurementStatusExists(@PathVariable Long id) {
        log.debug("Checking existence of procurement status ID: {}", id);
        
        boolean exists = procurementStatusService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if procurement status exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkProcurementStatusExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = procurementStatusService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of procurement statuses
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getProcurementStatusesCount() {
        log.debug("Getting total count of procurement statuses");
        
        Long count = procurementStatusService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of planning statuses
     */
    @GetMapping("/count/planning")
    public ResponseEntity<Long> getPlanningStatusesCount() {
        log.debug("Getting count of planning statuses");
        
        Long count = procurementStatusService.getPlanningCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of in-progress statuses
     */
    @GetMapping("/count/in-progress")
    public ResponseEntity<Long> getInProgressStatusesCount() {
        log.debug("Getting count of in-progress statuses");
        
        Long count = procurementStatusService.getInProgressCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of completed statuses
     */
    @GetMapping("/count/completed")
    public ResponseEntity<Long> getCompletedStatusesCount() {
        log.debug("Getting count of completed statuses");
        
        Long count = procurementStatusService.getCompletedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of suspended statuses
     */
    @GetMapping("/count/suspended")
    public ResponseEntity<Long> getSuspendedStatusesCount() {
        log.debug("Getting count of suspended statuses");
        
        Long count = procurementStatusService.getSuspendedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of cancelled statuses
     */
    @GetMapping("/count/cancelled")
    public ResponseEntity<Long> getCancelledStatusesCount() {
        log.debug("Getting count of cancelled statuses");
        
        Long count = procurementStatusService.getCancelledCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get procurement status info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ProcurementStatusInfoResponse> getProcurementStatusInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for procurement status ID: {}", id);
        
        try {
            return procurementStatusService.findOne(id)
                    .map(procurementStatusDTO -> {
                        ProcurementStatusInfoResponse response = ProcurementStatusInfoResponse.builder()
                                .procurementStatusMetadata(procurementStatusDTO)
                                .hasArabicDesignation(procurementStatusDTO.getDesignationAr() != null && !procurementStatusDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(procurementStatusDTO.getDesignationEn() != null && !procurementStatusDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(procurementStatusDTO.getDesignationFr() != null && !procurementStatusDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(procurementStatusDTO.isMultilingual())
                                .isActive(procurementStatusDTO.isActive())
                                .isFinal(procurementStatusDTO.isFinal())
                                .isCompleted(procurementStatusDTO.isCompleted())
                                .isSuspended(procurementStatusDTO.isSuspended())
                                .isInProgress(procurementStatusDTO.isInProgress())
                                .allowsTransition(procurementStatusDTO.allowsTransition())
                                .requiresDocumentation(procurementStatusDTO.requiresDocumentation())
                                .isValid(procurementStatusDTO.isValid())
                                .defaultDesignation(procurementStatusDTO.getDefaultDesignation())
                                .displayText(procurementStatusDTO.getDisplayText())
                                .statusCategory(procurementStatusDTO.getStatusCategory())
                                .projectPhase(procurementStatusDTO.getProjectPhase())
                                .statusPriority(procurementStatusDTO.getStatusPriority())
                                .statusColor(procurementStatusDTO.getStatusColor())
                                .typicalDuration(procurementStatusDTO.getTypicalDuration())
                                .milestoneType(procurementStatusDTO.getMilestoneType())
                                .progressPercentage(procurementStatusDTO.getProgressPercentage())
                                .notificationLevel(procurementStatusDTO.getNotificationLevel())
                                .nextPossibleStatuses(procurementStatusDTO.getNextPossibleStatuses())
                                .shortDisplay(procurementStatusDTO.getShortDisplay())
                                .fullDisplay(procurementStatusDTO.getFullDisplay())
                                .displayWithCategory(procurementStatusDTO.getDisplayWithCategory())
                                .availableLanguages(procurementStatusDTO.getAvailableLanguages())
                                .comparisonKey(procurementStatusDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting procurement status info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ProcurementStatusInfoResponse {
        private ProcurementStatusDTO procurementStatusMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isActive;
        private Boolean isFinal;
        private Boolean isCompleted;
        private Boolean isSuspended;
        private Boolean isInProgress;
        private Boolean allowsTransition;
        private Boolean requiresDocumentation;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String statusCategory;
        private String projectPhase;
        private Integer statusPriority;
        private String statusColor;
        private String typicalDuration;
        private String milestoneType;
        private Integer progressPercentage;
        private String notificationLevel;
        private String[] nextPossibleStatuses;
        private String shortDisplay;
        private String fullDisplay;
        private String displayWithCategory;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
