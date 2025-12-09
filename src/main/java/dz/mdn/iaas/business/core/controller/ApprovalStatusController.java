/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ApprovalStatusController
 *	@CreatedOn	: 10-16-2025
 *	@Type		: REST Controller
 *	@Layer		: Presentation
 *	@Package	: Business / Core / Controller
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.ApprovalStatusService;
import dz.mdn.iaas.business.core.dto.ApprovalStatusDTO;

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
 * ApprovalStatus REST Controller
 * Handles approval status operations: create, get metadata, delete, get all
 * Based on exact ApprovalStatus model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/approvalStatus")
@RequiredArgsConstructor
@Slf4j
public class ApprovalStatusController {

    private final ApprovalStatusService approvalStatusService;

    // ========== POST ONE APPROVAL STATUS ==========

    /**
     * Create new approval status
     * Creates approval status with multilingual designations and workflow classification
     */
    @PostMapping
    public ResponseEntity<ApprovalStatusDTO> createApprovalStatus(@Valid @RequestBody ApprovalStatusDTO approvalStatusDTO) {
        log.info("Creating approval status with French designation: {} and designations: AR={}, EN={}", 
                approvalStatusDTO.getDesignationFr(), approvalStatusDTO.getDesignationAr(), 
                approvalStatusDTO.getDesignationEn());
        
        ApprovalStatusDTO createdApprovalStatus = approvalStatusService.createApprovalStatus(approvalStatusDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApprovalStatus);
    }

    // ========== GET METADATA ==========

    /**
     * Get approval status metadata by ID
     * Returns approval status information with workflow classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApprovalStatusDTO> getApprovalStatusMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for approval status ID: {}", id);
        
        ApprovalStatusDTO approvalStatusMetadata = approvalStatusService.getApprovalStatusById(id);
        
        return ResponseEntity.ok(approvalStatusMetadata);
    }

    /**
     * Get approval status by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ApprovalStatusDTO> getApprovalStatusByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting approval status by French designation: {}", designationFr);
        
        return approvalStatusService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get approval status by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<ApprovalStatusDTO> getApprovalStatusByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting approval status by Arabic designation: {}", designationAr);
        
        return approvalStatusService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get approval status by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<ApprovalStatusDTO> getApprovalStatusByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting approval status by English designation: {}", designationEn);
        
        return approvalStatusService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete approval status by ID
     * Removes approval status from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprovalStatus(@PathVariable Long id) {
        log.info("Deleting approval status with ID: {}", id);
        
        approvalStatusService.deleteApprovalStatus(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all approval statuses with pagination
     * Returns list of all approval statuses ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ApprovalStatusDTO>> getAllApprovalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all approval statuses - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getAllApprovalStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search approval statuses by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ApprovalStatusDTO>> searchApprovalStatuses(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching approval statuses with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.searchApprovalStatuses(query, pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    // ========== APPROVAL STATUS TYPE ENDPOINTS ==========

    /**
     * Get multilingual approval statuses
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ApprovalStatusDTO>> getMultilingualApprovalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual approval statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getMultilingualApprovalStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    /**
     * Get approved statuses
     */
    @GetMapping("/approved")
    public ResponseEntity<Page<ApprovalStatusDTO>> getApprovedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting approved statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getApprovedStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    /**
     * Get rejected statuses
     */
    @GetMapping("/rejected")
    public ResponseEntity<Page<ApprovalStatusDTO>> getRejectedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rejected statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getRejectedStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    /**
     * Get pending statuses
     */
    @GetMapping("/pending")
    public ResponseEntity<Page<ApprovalStatusDTO>> getPendingStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting pending statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getPendingStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    /**
     * Get draft statuses
     */
    @GetMapping("/draft")
    public ResponseEntity<Page<ApprovalStatusDTO>> getDraftStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting draft statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getDraftStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    /**
     * Get review statuses
     */
    @GetMapping("/review")
    public ResponseEntity<Page<ApprovalStatusDTO>> getReviewStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting review statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getReviewStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    /**
     * Get final statuses (approved, rejected, cancelled)
     */
    @GetMapping("/final")
    public ResponseEntity<Page<ApprovalStatusDTO>> getFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getFinalStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    /**
     * Get non-final statuses (pending, draft, under review)
     */
    @GetMapping("/non-final")
    public ResponseEntity<Page<ApprovalStatusDTO>> getNonFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting non-final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ApprovalStatusDTO> approvalStatuses = approvalStatusService.getNonFinalStatuses(pageable);
        
        return ResponseEntity.ok(approvalStatuses);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update approval status metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApprovalStatusDTO> updateApprovalStatus(
            @PathVariable Long id,
            @Valid @RequestBody ApprovalStatusDTO approvalStatusDTO) {
        
        log.info("Updating approval status with ID: {}", id);
        
        ApprovalStatusDTO updatedApprovalStatus = approvalStatusService.updateApprovalStatus(id, approvalStatusDTO);
        
        return ResponseEntity.ok(updatedApprovalStatus);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if approval status exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkApprovalStatusExists(@PathVariable Long id) {
        log.debug("Checking existence of approval status ID: {}", id);
        
        boolean exists = approvalStatusService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if approval status exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkApprovalStatusExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = approvalStatusService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of approval statuses
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getApprovalStatusesCount() {
        log.debug("Getting total count of approval statuses");
        
        Long count = approvalStatusService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of approved statuses
     */
    @GetMapping("/count/approved")
    public ResponseEntity<Long> getApprovedStatusesCount() {
        log.debug("Getting count of approved statuses");
        
        Long count = approvalStatusService.getApprovedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of rejected statuses
     */
    @GetMapping("/count/rejected")
    public ResponseEntity<Long> getRejectedStatusesCount() {
        log.debug("Getting count of rejected statuses");
        
        Long count = approvalStatusService.getRejectedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of pending statuses
     */
    @GetMapping("/count/pending")
    public ResponseEntity<Long> getPendingStatusesCount() {
        log.debug("Getting count of pending statuses");
        
        Long count = approvalStatusService.getPendingCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get approval status info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ApprovalStatusInfoResponse> getApprovalStatusInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for approval status ID: {}", id);
        
        try {
            return approvalStatusService.findOne(id)
                    .map(approvalStatusDTO -> {
                        ApprovalStatusInfoResponse response = ApprovalStatusInfoResponse.builder()
                                .approvalStatusMetadata(approvalStatusDTO)
                                .hasArabicDesignation(approvalStatusDTO.getDesignationAr() != null && !approvalStatusDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(approvalStatusDTO.getDesignationEn() != null && !approvalStatusDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(approvalStatusDTO.getDesignationFr() != null && !approvalStatusDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(approvalStatusDTO.isMultilingual())
                                .isApproved(approvalStatusDTO.isApproved())
                                .isRejected(approvalStatusDTO.isRejected())
                                .isPending(approvalStatusDTO.isPending())
                                .isFinal(approvalStatusDTO.isFinal())
                                .isValid(approvalStatusDTO.isValid())
                                .defaultDesignation(approvalStatusDTO.getDefaultDesignation())
                                .displayText(approvalStatusDTO.getDisplayText())
                                .approvalStatusType(approvalStatusDTO.getApprovalStatusType())
                                .statusPriority(approvalStatusDTO.getStatusPriority())
                                .statusColor(approvalStatusDTO.getStatusColor())
                                .workflowStage(approvalStatusDTO.getWorkflowStage())
                                .allowsTransition(approvalStatusDTO.allowsTransition())
                                .shortDisplay(approvalStatusDTO.getShortDisplay())
                                .fullDisplay(approvalStatusDTO.getFullDisplay())
                                .availableLanguages(approvalStatusDTO.getAvailableLanguages())
                                .comparisonKey(approvalStatusDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting approval status info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ApprovalStatusInfoResponse {
        private ApprovalStatusDTO approvalStatusMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isApproved;
        private Boolean isRejected;
        private Boolean isPending;
        private Boolean isFinal;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String approvalStatusType;
        private Integer statusPriority;
        private String statusColor;
        private Integer workflowStage;
        private Boolean allowsTransition;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
