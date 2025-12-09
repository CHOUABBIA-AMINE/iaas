/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentTypeController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.business.amendment.dto.AmendmentTypeDTO;
import dz.mdn.iaas.business.amendment.service.AmendmentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AmendmentType REST Controller
 * Handles approval status operations: create, get metadata, delete, get all
 * Based on exact AmendmentType model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/amendmentType")
@RequiredArgsConstructor
@Slf4j
public class AmendmentTypeController {

    private final AmendmentTypeService amendmentTypeService;

    // ========== POST ONE APPROVAL STATUS ==========

    /**
     * Create new approval status
     * Creates approval status with multilingual designations and workflow classification
     */
    @PostMapping
    public ResponseEntity<AmendmentTypeDTO> createAmendmentType(@Valid @RequestBody AmendmentTypeDTO amendmentTypeDTO) {
        log.info("Creating approval status with French designation: {} and designations: AR={}, EN={}", 
                amendmentTypeDTO.getDesignationFr(), amendmentTypeDTO.getDesignationAr(), 
                amendmentTypeDTO.getDesignationEn());
        
        AmendmentTypeDTO createdAmendmentType = amendmentTypeService.createAmendmentType(amendmentTypeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAmendmentType);
    }

    // ========== GET METADATA ==========

    /**
     * Get approval status metadata by ID
     * Returns approval status information with workflow classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<AmendmentTypeDTO> getAmendmentTypeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for approval status ID: {}", id);
        
        AmendmentTypeDTO amendmentTypeMetadata = amendmentTypeService.getAmendmentTypeById(id);
        
        return ResponseEntity.ok(amendmentTypeMetadata);
    }

    /**
     * Get approval status by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<AmendmentTypeDTO> getAmendmentTypeByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting approval status by French designation: {}", designationFr);
        
        return amendmentTypeService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get approval status by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<AmendmentTypeDTO> getAmendmentTypeByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting approval status by Arabic designation: {}", designationAr);
        
        return amendmentTypeService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get approval status by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<AmendmentTypeDTO> getAmendmentTypeByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting approval status by English designation: {}", designationEn);
        
        return amendmentTypeService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete approval status by ID
     * Removes approval status from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmendmentType(@PathVariable Long id) {
        log.info("Deleting approval status with ID: {}", id);
        
        amendmentTypeService.deleteAmendmentType(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all approval statuses with pagination
     * Returns list of all approval statuses ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<AmendmentTypeDTO>> getAllAmendmentTypees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all approval statuses - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getAllAmendmentTypees(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search approval statuses by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<AmendmentTypeDTO>> searchAmendmentTypees(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching approval statuses with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.searchAmendmentTypees(query, pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    // ========== APPROVAL STATUS TYPE ENDPOINTS ==========

    /**
     * Get multilingual approval statuses
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<AmendmentTypeDTO>> getMultilingualAmendmentTypees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual approval statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getMultilingualAmendmentTypees(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    /**
     * Get approved statuses
     */
    @GetMapping("/approved")
    public ResponseEntity<Page<AmendmentTypeDTO>> getApprovedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting approved statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getApprovedStatuses(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    /**
     * Get rejected statuses
     */
    @GetMapping("/rejected")
    public ResponseEntity<Page<AmendmentTypeDTO>> getRejectedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rejected statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getRejectedStatuses(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    /**
     * Get pending statuses
     */
    @GetMapping("/pending")
    public ResponseEntity<Page<AmendmentTypeDTO>> getPendingStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting pending statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getPendingStatuses(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    /**
     * Get draft statuses
     */
    @GetMapping("/draft")
    public ResponseEntity<Page<AmendmentTypeDTO>> getDraftStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting draft statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getDraftStatuses(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    /**
     * Get review statuses
     */
    @GetMapping("/review")
    public ResponseEntity<Page<AmendmentTypeDTO>> getReviewStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting review statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getReviewStatuses(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    /**
     * Get final statuses (approved, rejected, cancelled)
     */
    @GetMapping("/final")
    public ResponseEntity<Page<AmendmentTypeDTO>> getFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getFinalStatuses(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    /**
     * Get non-final statuses (pending, draft, under review)
     */
    @GetMapping("/non-final")
    public ResponseEntity<Page<AmendmentTypeDTO>> getNonFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting non-final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentTypeDTO> amendmentTypees = amendmentTypeService.getNonFinalStatuses(pageable);
        
        return ResponseEntity.ok(amendmentTypees);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update approval status metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<AmendmentTypeDTO> updateAmendmentType(
            @PathVariable Long id,
            @Valid @RequestBody AmendmentTypeDTO amendmentTypeDTO) {
        
        log.info("Updating approval status with ID: {}", id);
        
        AmendmentTypeDTO updatedAmendmentType = amendmentTypeService.updateAmendmentType(id, amendmentTypeDTO);
        
        return ResponseEntity.ok(updatedAmendmentType);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if approval status exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkAmendmentTypeExists(@PathVariable Long id) {
        log.debug("Checking existence of approval status ID: {}", id);
        
        boolean exists = amendmentTypeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if approval status exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkAmendmentTypeExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = amendmentTypeService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of approval statuses
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getAmendmentTypeesCount() {
        log.debug("Getting total count of approval statuses");
        
        Long count = amendmentTypeService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of approved statuses
     */
    @GetMapping("/count/approved")
    public ResponseEntity<Long> getApprovedStatusesCount() {
        log.debug("Getting count of approved statuses");
        
        Long count = amendmentTypeService.getApprovedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of rejected statuses
     */
    @GetMapping("/count/rejected")
    public ResponseEntity<Long> getRejectedStatusesCount() {
        log.debug("Getting count of rejected statuses");
        
        Long count = amendmentTypeService.getRejectedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of pending statuses
     */
    @GetMapping("/count/pending")
    public ResponseEntity<Long> getPendingStatusesCount() {
        log.debug("Getting count of pending statuses");
        
        Long count = amendmentTypeService.getPendingCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get approval status info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<AmendmentTypeInfoResponse> getAmendmentTypeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for approval status ID: {}", id);
        
        try {
            return amendmentTypeService.findOne(id)
                    .map(amendmentTypeDTO -> {
                        AmendmentTypeInfoResponse response = AmendmentTypeInfoResponse.builder()
                                .amendmentTypeMetadata(amendmentTypeDTO)
                                .hasArabicDesignation(amendmentTypeDTO.getDesignationAr() != null && !amendmentTypeDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(amendmentTypeDTO.getDesignationEn() != null && !amendmentTypeDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(amendmentTypeDTO.getDesignationFr() != null && !amendmentTypeDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(amendmentTypeDTO.isMultilingual())
                                .isApproved(amendmentTypeDTO.isApproved())
                                .isRejected(amendmentTypeDTO.isRejected())
                                .isPending(amendmentTypeDTO.isPending())
                                .isFinal(amendmentTypeDTO.isFinal())
                                .isValid(amendmentTypeDTO.isValid())
                                .defaultDesignation(amendmentTypeDTO.getDefaultDesignation())
                                .displayText(amendmentTypeDTO.getDisplayText())
                                .amendmentTypeType(amendmentTypeDTO.getAmendmentTypeType())
                                .statusPriority(amendmentTypeDTO.getStatusPriority())
                                .statusColor(amendmentTypeDTO.getStatusColor())
                                .workflowStage(amendmentTypeDTO.getWorkflowStage())
                                .allowsTransition(amendmentTypeDTO.allowsTransition())
                                .shortDisplay(amendmentTypeDTO.getShortDisplay())
                                .fullDisplay(amendmentTypeDTO.getFullDisplay())
                                .availableLanguages(amendmentTypeDTO.getAvailableLanguages())
                                .comparisonKey(amendmentTypeDTO.getComparisonKey())
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
    public static class AmendmentTypeInfoResponse {
        private AmendmentTypeDTO amendmentTypeMetadata;
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
        private String amendmentTypeType;
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
