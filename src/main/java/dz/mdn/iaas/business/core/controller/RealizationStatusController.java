/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationStatusController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.RealizationStatusService;
import dz.mdn.iaas.business.core.dto.RealizationStatusDTO;

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
 * RealizationStatus REST Controller
 * Handles realization status operations: create, get metadata, delete, get all
 * Based on exact RealizationStatus model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/realizationStatus")
@RequiredArgsConstructor
@Slf4j
public class RealizationStatusController {

    private final RealizationStatusService realizationStatusService;

    // ========== POST ONE REALIZATION STATUS ==========

    /**
     * Create new realization status
     * Creates realization status with multilingual designations and project lifecycle management
     */
    @PostMapping
    public ResponseEntity<RealizationStatusDTO> createRealizationStatus(@Valid @RequestBody RealizationStatusDTO realizationStatusDTO) {
        log.info("Creating realization status with French designation: {} and designations: AR={}, EN={}", 
                realizationStatusDTO.getDesignationFr(), realizationStatusDTO.getDesignationAr(), 
                realizationStatusDTO.getDesignationEn());
        
        RealizationStatusDTO createdRealizationStatus = realizationStatusService.createRealizationStatus(realizationStatusDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRealizationStatus);
    }

    // ========== GET METADATA ==========

    /**
     * Get realization status metadata by ID
     * Returns realization status information with project lifecycle classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<RealizationStatusDTO> getRealizationStatusMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for realization status ID: {}", id);
        
        RealizationStatusDTO realizationStatusMetadata = realizationStatusService.getRealizationStatusById(id);
        
        return ResponseEntity.ok(realizationStatusMetadata);
    }

    /**
     * Get realization status by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<RealizationStatusDTO> getRealizationStatusByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting realization status by French designation: {}", designationFr);
        
        return realizationStatusService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get realization status by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<RealizationStatusDTO> getRealizationStatusByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting realization status by Arabic designation: {}", designationAr);
        
        return realizationStatusService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get realization status by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<RealizationStatusDTO> getRealizationStatusByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting realization status by English designation: {}", designationEn);
        
        return realizationStatusService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete realization status by ID
     * Removes realization status from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRealizationStatus(@PathVariable Long id) {
        log.info("Deleting realization status with ID: {}", id);
        
        realizationStatusService.deleteRealizationStatus(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all realization statuses with pagination
     * Returns list of all realization statuses ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<RealizationStatusDTO>> getAllRealizationStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all realization statuses - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getAllRealizationStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search realization statuses by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<RealizationStatusDTO>> searchRealizationStatuses(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching realization statuses with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.searchRealizationStatuses(query, pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    // ========== STATUS CATEGORY ENDPOINTS ==========

    /**
     * Get multilingual realization statuses
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<RealizationStatusDTO>> getMultilingualRealizationStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual realization statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getMultilingualRealizationStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get planning statuses
     */
    @GetMapping("/planning")
    public ResponseEntity<Page<RealizationStatusDTO>> getPlanningStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planning statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getPlanningStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get in-progress statuses
     */
    @GetMapping("/in-progress")
    public ResponseEntity<Page<RealizationStatusDTO>> getInProgressStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting in-progress statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getInProgressStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get completed statuses
     */
    @GetMapping("/completed")
    public ResponseEntity<Page<RealizationStatusDTO>> getCompletedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting completed statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getCompletedStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get suspended statuses
     */
    @GetMapping("/suspended")
    public ResponseEntity<Page<RealizationStatusDTO>> getSuspendedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting suspended statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getSuspendedStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get cancelled statuses
     */
    @GetMapping("/cancelled")
    public ResponseEntity<Page<RealizationStatusDTO>> getCancelledStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting cancelled statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getCancelledStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get review statuses
     */
    @GetMapping("/review")
    public ResponseEntity<Page<RealizationStatusDTO>> getReviewStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting review statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getReviewStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get approved statuses
     */
    @GetMapping("/approved")
    public ResponseEntity<Page<RealizationStatusDTO>> getApprovedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting approved statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getApprovedStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get rejected statuses
     */
    @GetMapping("/rejected")
    public ResponseEntity<Page<RealizationStatusDTO>> getRejectedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rejected statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getRejectedStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get on-hold statuses
     */
    @GetMapping("/on-hold")
    public ResponseEntity<Page<RealizationStatusDTO>> getOnHoldStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting on-hold statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getOnHoldStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    // ========== STATUS LIFECYCLE ENDPOINTS ==========

    /**
     * Get active statuses
     */
    @GetMapping("/active")
    public ResponseEntity<Page<RealizationStatusDTO>> getActiveStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting active statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getActiveStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get final statuses
     */
    @GetMapping("/final")
    public ResponseEntity<Page<RealizationStatusDTO>> getFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getFinalStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get transitional statuses
     */
    @GetMapping("/transitional")
    public ResponseEntity<Page<RealizationStatusDTO>> getTransitionalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting transitional statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getTransitionalStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    // ========== PROJECT PHASE ENDPOINTS ==========

    /**
     * Get initiation phase statuses
     */
    @GetMapping("/phase/initiation")
    public ResponseEntity<Page<RealizationStatusDTO>> getInitiationPhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting initiation phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getInitiationPhaseStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get execution phase statuses
     */
    @GetMapping("/phase/execution")
    public ResponseEntity<Page<RealizationStatusDTO>> getExecutionPhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting execution phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getExecutionPhaseStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get monitoring phase statuses
     */
    @GetMapping("/phase/monitoring")
    public ResponseEntity<Page<RealizationStatusDTO>> getMonitoringPhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting monitoring phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getMonitoringPhaseStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    /**
     * Get closure phase statuses
     */
    @GetMapping("/phase/closure")
    public ResponseEntity<Page<RealizationStatusDTO>> getClosurePhaseStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting closure phase statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationStatusDTO> realizationStatuses = realizationStatusService.getClosurePhaseStatuses(pageable);
        
        return ResponseEntity.ok(realizationStatuses);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update realization status metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<RealizationStatusDTO> updateRealizationStatus(
            @PathVariable Long id,
            @Valid @RequestBody RealizationStatusDTO realizationStatusDTO) {
        
        log.info("Updating realization status with ID: {}", id);
        
        RealizationStatusDTO updatedRealizationStatus = realizationStatusService.updateRealizationStatus(id, realizationStatusDTO);
        
        return ResponseEntity.ok(updatedRealizationStatus);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if realization status exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkRealizationStatusExists(@PathVariable Long id) {
        log.debug("Checking existence of realization status ID: {}", id);
        
        boolean exists = realizationStatusService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if realization status exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkRealizationStatusExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = realizationStatusService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of realization statuses
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getRealizationStatusesCount() {
        log.debug("Getting total count of realization statuses");
        
        Long count = realizationStatusService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of planning statuses
     */
    @GetMapping("/count/planning")
    public ResponseEntity<Long> getPlanningStatusesCount() {
        log.debug("Getting count of planning statuses");
        
        Long count = realizationStatusService.getPlanningCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of in-progress statuses
     */
    @GetMapping("/count/in-progress")
    public ResponseEntity<Long> getInProgressStatusesCount() {
        log.debug("Getting count of in-progress statuses");
        
        Long count = realizationStatusService.getInProgressCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of completed statuses
     */
    @GetMapping("/count/completed")
    public ResponseEntity<Long> getCompletedStatusesCount() {
        log.debug("Getting count of completed statuses");
        
        Long count = realizationStatusService.getCompletedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of suspended statuses
     */
    @GetMapping("/count/suspended")
    public ResponseEntity<Long> getSuspendedStatusesCount() {
        log.debug("Getting count of suspended statuses");
        
        Long count = realizationStatusService.getSuspendedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of cancelled statuses
     */
    @GetMapping("/count/cancelled")
    public ResponseEntity<Long> getCancelledStatusesCount() {
        log.debug("Getting count of cancelled statuses");
        
        Long count = realizationStatusService.getCancelledCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get realization status info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<RealizationStatusInfoResponse> getRealizationStatusInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for realization status ID: {}", id);
        
        try {
            return realizationStatusService.findOne(id)
                    .map(realizationStatusDTO -> {
                        RealizationStatusInfoResponse response = RealizationStatusInfoResponse.builder()
                                .realizationStatusMetadata(realizationStatusDTO)
                                .hasArabicDesignation(realizationStatusDTO.getDesignationAr() != null && !realizationStatusDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(realizationStatusDTO.getDesignationEn() != null && !realizationStatusDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(realizationStatusDTO.getDesignationFr() != null && !realizationStatusDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(realizationStatusDTO.isMultilingual())
                                .isActive(realizationStatusDTO.isActive())
                                .isFinal(realizationStatusDTO.isFinal())
                                .isCompleted(realizationStatusDTO.isCompleted())
                                .isSuspended(realizationStatusDTO.isSuspended())
                                .isInProgress(realizationStatusDTO.isInProgress())
                                .allowsTransition(realizationStatusDTO.allowsTransition())
                                .requiresDocumentation(realizationStatusDTO.requiresDocumentation())
                                .isValid(realizationStatusDTO.isValid())
                                .defaultDesignation(realizationStatusDTO.getDefaultDesignation())
                                .displayText(realizationStatusDTO.getDisplayText())
                                .statusCategory(realizationStatusDTO.getStatusCategory())
                                .projectPhase(realizationStatusDTO.getProjectPhase())
                                .statusPriority(realizationStatusDTO.getStatusPriority())
                                .statusColor(realizationStatusDTO.getStatusColor())
                                .typicalDuration(realizationStatusDTO.getTypicalDuration())
                                .milestoneType(realizationStatusDTO.getMilestoneType())
                                .progressPercentage(realizationStatusDTO.getProgressPercentage())
                                .notificationLevel(realizationStatusDTO.getNotificationLevel())
                                .nextPossibleStatuses(realizationStatusDTO.getNextPossibleStatuses())
                                .shortDisplay(realizationStatusDTO.getShortDisplay())
                                .fullDisplay(realizationStatusDTO.getFullDisplay())
                                .displayWithCategory(realizationStatusDTO.getDisplayWithCategory())
                                .availableLanguages(realizationStatusDTO.getAvailableLanguages())
                                .comparisonKey(realizationStatusDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting realization status info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class RealizationStatusInfoResponse {
        private RealizationStatusDTO realizationStatusMetadata;
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
