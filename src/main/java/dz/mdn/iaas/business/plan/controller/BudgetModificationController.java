/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.BudgetModificationService;
import dz.mdn.iaas.business.plan.dto.BudgetModificationDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * BudgetModification REST Controller
 * Handles budget modification operations: create, get metadata, delete, get all
 * Based on exact BudgetModification model: F_00=id, F_01=object, F_02=description, 
 * F_03=approvalDate, F_04=demandeId, F_05=responseId
 * Includes unique constraint on F_03+F_04 and many-to-one relationships with Documents
 */
@RestController
@RequestMapping("/budgetModification")
@RequiredArgsConstructor
@Slf4j
public class BudgetModificationController {

    private final BudgetModificationService budgetModificationService;

    // ========== POST ONE BUDGET MODIFICATION ==========

    /**
     * Create new budget modification
     * Creates budget modification with approval workflow and document management
     */
    @PostMapping
    public ResponseEntity<BudgetModificationDTO> createBudgetModification(@Valid @RequestBody BudgetModificationDTO budgetModificationDTO) {
        log.info("Creating budget modification with object: {} for demande ID: {}", 
                budgetModificationDTO.getObject(), budgetModificationDTO.getDemandeId());
        
        BudgetModificationDTO createdBudgetModification = budgetModificationService.createBudgetModification(budgetModificationDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBudgetModification);
    }

    // ========== GET METADATA ==========

    /**
     * Get budget modification metadata by ID
     * Returns budget modification information with approval status and document details
     */
    @GetMapping("/{id}")
    public ResponseEntity<BudgetModificationDTO> getBudgetModificationMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for budget modification ID: {}", id);
        
        BudgetModificationDTO budgetModificationMetadata = budgetModificationService.getBudgetModificationById(id);
        
        return ResponseEntity.ok(budgetModificationMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete budget modification by ID
     * Removes budget modification from the budget modification system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudgetModification(@PathVariable Long id) {
        log.info("Deleting budget modification with ID: {}", id);
        
        budgetModificationService.deleteBudgetModification(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all budget modifications with pagination
     * Returns list of all budget modifications ordered by approval date (most recent first)
     */
    @GetMapping
    public ResponseEntity<Page<BudgetModificationDTO>> getAllBudgetModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "approvalDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting all budget modifications - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getAllBudgetModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search budget modifications by object or description
     */
    @GetMapping("/search")
    public ResponseEntity<Page<BudgetModificationDTO>> searchBudgetModifications(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching budget modifications with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.searchBudgetModifications(query, pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    // ========== DOCUMENT RELATIONSHIP ENDPOINTS ==========

    /**
     * Get budget modifications by demande document
     */
    @GetMapping("/demande/{demandeId}")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetModificationsByDemande(
            @PathVariable Long demandeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget modifications for demande ID: {}", demandeId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetModificationsByDemande(demandeId, pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get budget modifications by response document
     */
    @GetMapping("/response/{responseId}")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetModificationsByResponse(
            @PathVariable Long responseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget modifications for response ID: {}", responseId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetModificationsByResponse(responseId, pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get budget modifications by document type
     */
    @GetMapping("/document-type/{documentType}")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetModificationsByDocumentType(
            @PathVariable String documentType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget modifications for document type: {}", documentType);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetModificationsByDocumentType(documentType, pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    // ========== APPROVAL STATUS ENDPOINTS ==========

    /**
     * Get pending budget modifications
     */
    @GetMapping("/status/pending")
    public ResponseEntity<Page<BudgetModificationDTO>> getPendingModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting pending budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getPendingModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get approved budget modifications
     */
    @GetMapping("/status/approved")
    public ResponseEntity<Page<BudgetModificationDTO>> getApprovedModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting approved budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getApprovedModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get scheduled budget modifications
     */
    @GetMapping("/status/scheduled")
    public ResponseEntity<Page<BudgetModificationDTO>> getScheduledModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting scheduled budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getScheduledModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    // ========== DATE-BASED ENDPOINTS ==========

    /**
     * Get budget modifications by approval date range
     */
    @GetMapping("/approval-date-range")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetModificationsByApprovalDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget modifications between {} and {}", startDate, endDate);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetModificationsByApprovalDateRange(startDate, endDate, pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get current year budget modifications
     */
    @GetMapping("/current-year")
    public ResponseEntity<Page<BudgetModificationDTO>> getCurrentYearModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting current year budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getCurrentYearModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get budget modifications by specific year
     */
    @GetMapping("/year/{year}")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetModificationsByYear(
            @PathVariable int year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget modifications for year: {}", year);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetModificationsByYear(year, pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get current month budget modifications
     */
    @GetMapping("/current-month")
    public ResponseEntity<Page<BudgetModificationDTO>> getCurrentMonthModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting current month budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getCurrentMonthModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get recent budget modifications (last 30 days)
     */
    @GetMapping("/recent")
    public ResponseEntity<Page<BudgetModificationDTO>> getRecentModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting recent budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getRecentModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    // ========== MODIFICATION TYPE ENDPOINTS ==========

    /**
     * Get budget increase modifications
     */
    @GetMapping("/type/increase")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetIncreaseModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget increase modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetIncreaseModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get budget decrease modifications
     */
    @GetMapping("/type/decrease")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetDecreaseModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget decrease modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetDecreaseModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get budget reallocation modifications
     */
    @GetMapping("/type/reallocation")
    public ResponseEntity<Page<BudgetModificationDTO>> getBudgetReallocationModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget reallocation modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getBudgetReallocationModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get emergency modifications
     */
    @GetMapping("/type/emergency")
    public ResponseEntity<Page<BudgetModificationDTO>> getEmergencyModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting emergency budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getEmergencyModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get correction modifications
     */
    @GetMapping("/type/correction")
    public ResponseEntity<Page<BudgetModificationDTO>> getCorrectionModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting correction budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "approvalDate"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getCorrectionModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    // ========== SPECIAL CATEGORY ENDPOINTS ==========

    /**
     * Get overdue modifications
     */
    @GetMapping("/overdue")
    public ResponseEntity<Page<BudgetModificationDTO>> getOverdueModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting overdue budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getOverdueModifications(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get modifications requiring immediate attention
     */
    @GetMapping("/requiring-immediate-attention")
    public ResponseEntity<Page<BudgetModificationDTO>> getModificationsRequiringImmediateAttention(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget modifications requiring immediate attention");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getModificationsRequiringImmediateAttention(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    /**
     * Get modifications with missing information
     */
    @GetMapping("/missing-information")
    public ResponseEntity<Page<BudgetModificationDTO>> getModificationsWithMissingInformation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget modifications with missing information");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<BudgetModificationDTO> budgetModifications = budgetModificationService.getModificationsWithMissingInformation(pageable);
        
        return ResponseEntity.ok(budgetModifications);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update budget modification metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<BudgetModificationDTO> updateBudgetModification(
            @PathVariable Long id,
            @Valid @RequestBody BudgetModificationDTO budgetModificationDTO) {
        
        log.info("Updating budget modification with ID: {}", id);
        
        BudgetModificationDTO updatedBudgetModification = budgetModificationService.updateBudgetModification(id, budgetModificationDTO);
        
        return ResponseEntity.ok(updatedBudgetModification);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if budget modification exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkBudgetModificationExists(@PathVariable Long id) {
        log.debug("Checking existence of budget modification ID: {}", id);
        
        boolean exists = budgetModificationService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if unique constraint exists (approval date + demande)
     */
    @GetMapping("/exists/approval-date-demande")
    public ResponseEntity<Boolean> checkApprovalDateDemandeExists(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date approvalDate,
            @RequestParam Long demandeId) {
        
        log.debug("Checking if approval date {} and demande {} combination exists", approvalDate, demandeId);
        
        boolean exists = budgetModificationService.existsByApprovalDateAndDemande(approvalDate, demandeId);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of budget modifications by demande
     */
    @GetMapping("/demande/{demandeId}/count")
    public ResponseEntity<Long> countBudgetModificationsByDemande(@PathVariable Long demandeId) {
        log.debug("Getting count of budget modifications for demande ID: {}", demandeId);
        
        Long count = budgetModificationService.countBudgetModificationsByDemande(demandeId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of budget modifications by response
     */
    @GetMapping("/response/{responseId}/count")
    public ResponseEntity<Long> countBudgetModificationsByResponse(@PathVariable Long responseId) {
        log.debug("Getting count of budget modifications for response ID: {}", responseId);
        
        Long count = budgetModificationService.countBudgetModificationsByResponse(responseId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of all budget modifications
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllBudgetModifications() {
        log.debug("Getting count of all budget modifications");
        
        Long count = budgetModificationService.countAllBudgetModifications();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of pending modifications
     */
    @GetMapping("/count/pending")
    public ResponseEntity<Long> countPendingModifications() {
        log.debug("Getting count of pending budget modifications");
        
        Long count = budgetModificationService.countPendingModifications();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of approved modifications
     */
    @GetMapping("/count/approved")
    public ResponseEntity<Long> countApprovedModifications() {
        log.debug("Getting count of approved budget modifications");
        
        Long count = budgetModificationService.countApprovedModifications();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of scheduled modifications
     */
    @GetMapping("/count/scheduled")
    public ResponseEntity<Long> countScheduledModifications() {
        log.debug("Getting count of scheduled budget modifications");
        
        Long count = budgetModificationService.countScheduledModifications();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of current year modifications
     */
    @GetMapping("/count/current-year")
    public ResponseEntity<Long> countCurrentYearModifications() {
        log.debug("Getting count of current year budget modifications");
        
        Long count = budgetModificationService.countCurrentYearModifications();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of modifications by specific year
     */
    @GetMapping("/count/year/{year}")
    public ResponseEntity<Long> countBudgetModificationsByYear(@PathVariable int year) {
        log.debug("Getting count of budget modifications for year: {}", year);
        
        Long count = budgetModificationService.countBudgetModificationsByYear(year);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get budget modification info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<BudgetModificationInfoResponse> getBudgetModificationInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for budget modification ID: {}", id);
        
        try {
            return budgetModificationService.findOne(id)
                    .map(budgetModificationDTO -> {
                        BudgetModificationInfoResponse response = BudgetModificationInfoResponse.builder()
                                .budgetModificationMetadata(budgetModificationDTO)
                                .displayText(budgetModificationDTO.getDisplayText())
                                .modificationStatus(budgetModificationDTO.getModificationStatus())
                                .modificationType(budgetModificationDTO.getModificationType())
                                .modificationPriority(budgetModificationDTO.getModificationPriority())
                                .urgencyLevel(budgetModificationDTO.getUrgencyLevel())
                                .isPending(budgetModificationDTO.isPending())
                                .isApproved(budgetModificationDTO.isApproved())
                                .isScheduled(budgetModificationDTO.isScheduled())
                                .daysToApproval(budgetModificationDTO.getDaysToApproval())
                                .shortDisplay(budgetModificationDTO.getShortDisplay())
                                .fullDisplay(budgetModificationDTO.getFullDisplay())
                                .modificationDisplay(budgetModificationDTO.getModificationDisplay())
                                .formalDisplay(budgetModificationDTO.getFormalDisplay())
                                .budgetModificationClassification(budgetModificationDTO.getBudgetModificationClassification())
                                .impactAssessment(budgetModificationDTO.getImpactAssessment())
                                .approvalRequirements(budgetModificationDTO.getApprovalRequirements())
                                .processingTimeline(budgetModificationDTO.getProcessingTimeline())
                                .riskFactors(budgetModificationDTO.getRiskFactors())
                                .successMetrics(budgetModificationDTO.getSuccessMetrics())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting budget modification info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class BudgetModificationInfoResponse {
        private BudgetModificationDTO budgetModificationMetadata;
        private String displayText;
        private String modificationStatus;
        private String modificationType;
        private String modificationPriority;
        private String urgencyLevel;
        private Boolean isPending;
        private Boolean isApproved;
        private Boolean isScheduled;
        private Long daysToApproval;
        private String shortDisplay;
        private String fullDisplay;
        private String modificationDisplay;
        private String formalDisplay;
        private String budgetModificationClassification;
        private String impactAssessment;
        private String approvalRequirements;
        private String processingTimeline;
        private String riskFactors;
        private String successMetrics;
    }
}