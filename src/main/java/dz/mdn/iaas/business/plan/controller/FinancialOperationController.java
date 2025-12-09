/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperationController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import java.util.List;

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

import dz.mdn.iaas.business.plan.dto.FinancialOperationDTO;
import dz.mdn.iaas.business.plan.service.FinancialOperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Financial Operation REST Controller
 * Handles financial operation operations: create, get metadata, delete, get all
 * Based on exact FinancialOperation model: F_00=id, F_01=operation (unique), F_02=budgetYear, F_03=budgetTypeId
 */
@RestController
@RequestMapping("/financialOperation")
@RequiredArgsConstructor
@Slf4j
public class FinancialOperationController {

    private final FinancialOperationService financialOperationService;

    // ========== POST ONE FINANCIAL OPERATION ==========

    /**
     * Create new financial operation
     * Creates financial operation with budget intelligence and fiscal year tracking
     */
    @PostMapping
    public ResponseEntity<FinancialOperationDTO> createFinancialOperation(@Valid @RequestBody FinancialOperationDTO financialOperationDTO) {
        log.info("Creating financial operation: {} for budget year: {}", 
                financialOperationDTO.getOperation(), financialOperationDTO.getBudgetYear());
        
        FinancialOperationDTO createdFinancialOperation = financialOperationService.createFinancialOperation(financialOperationDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFinancialOperation);
    }

    // ========== GET METADATA ==========

    /**
     * Get financial operation metadata by ID
     * Returns financial operation information with budget details and fiscal analysis
     */
    @GetMapping("/{id}")
    public ResponseEntity<FinancialOperationDTO> getFinancialOperationMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for financial operation ID: {}", id);
        
        FinancialOperationDTO financialOperationMetadata = financialOperationService.getFinancialOperationById(id);
        
        return ResponseEntity.ok(financialOperationMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete financial operation by ID
     * Removes financial operation from the financial management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialOperation(@PathVariable Long id) {
        log.info("Deleting financial operation with ID: {}", id);
        
        financialOperationService.deleteFinancialOperation(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all financial operations with pagination
     * Returns list of all financial operations ordered by budget year (desc) and operation name
     */
    @GetMapping
    public ResponseEntity<Page<FinancialOperationDTO>> getAllFinancialOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "budgetYear") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting all financial operations - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getAllFinancialOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search financial operations by operation name
     */
    @GetMapping("/search/operation")
    public ResponseEntity<Page<FinancialOperationDTO>> searchFinancialOperationsByOperation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching financial operations by operation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.searchFinancialOperationsByOperation(query, pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    // ========== BUDGET YEAR SPECIFIC ENDPOINTS ==========

    /**
     * Get financial operations by budget year
     */
    @GetMapping("/budget-year/{budgetYear}")
    public ResponseEntity<Page<FinancialOperationDTO>> getFinancialOperationsByBudgetYear(
            @PathVariable String budgetYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial operations for budget year: {}", budgetYear);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "operation"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getFinancialOperationsByBudgetYear(budgetYear, pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get current year financial operations
     */
    @GetMapping("/current-year")
    public ResponseEntity<Page<FinancialOperationDTO>> getCurrentYearOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting current year financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "operation"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getCurrentYearOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get future year financial operations
     */
    @GetMapping("/future-years")
    public ResponseEntity<Page<FinancialOperationDTO>> getFutureYearOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting future year financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getFutureYearOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get past year financial operations
     */
    @GetMapping("/past-years")
    public ResponseEntity<Page<FinancialOperationDTO>> getPastYearOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting past year financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getPastYearOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    // ========== BUDGET TYPE SPECIFIC ENDPOINTS ==========

    /**
     * Get financial operations by budget type
     */
    @GetMapping("/budget-type/{budgetTypeId}")
    public ResponseEntity<Page<FinancialOperationDTO>> getFinancialOperationsByBudgetType(
            @PathVariable Long budgetTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial operations for budget type ID: {}", budgetTypeId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getFinancialOperationsByBudgetType(budgetTypeId, pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get financial operations by budget year and budget type
     */
    @GetMapping("/budget-year/{budgetYear}/budget-type/{budgetTypeId}")
    public ResponseEntity<Page<FinancialOperationDTO>> getFinancialOperationsByBudgetYearAndType(
            @PathVariable String budgetYear,
            @PathVariable Long budgetTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial operations for budget year: {} and budget type ID: {}", budgetYear, budgetTypeId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "operation"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getFinancialOperationsByBudgetYearAndType(budgetYear, budgetTypeId, pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    // ========== OPERATION CATEGORY ENDPOINTS ==========

    /**
     * Get budget allocation operations
     */
    @GetMapping("/category/budget-allocation")
    public ResponseEntity<Page<FinancialOperationDTO>> getBudgetAllocationOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget allocation financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getBudgetAllocationOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get expenditure operations
     */
    @GetMapping("/category/expenditure")
    public ResponseEntity<Page<FinancialOperationDTO>> getExpenditureOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting expenditure financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getExpenditureOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get revenue operations
     */
    @GetMapping("/category/revenue")
    public ResponseEntity<Page<FinancialOperationDTO>> getRevenueOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting revenue financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getRevenueOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get transfer operations
     */
    @GetMapping("/category/transfer")
    public ResponseEntity<Page<FinancialOperationDTO>> getTransferOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting transfer financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getTransferOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get investment operations
     */
    @GetMapping("/category/investment")
    public ResponseEntity<Page<FinancialOperationDTO>> getInvestmentOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting investment financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getInvestmentOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get procurement operations
     */
    @GetMapping("/category/procurement")
    public ResponseEntity<Page<FinancialOperationDTO>> getProcurementOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting procurement financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getProcurementOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get payment operations
     */
    @GetMapping("/category/payment")
    public ResponseEntity<Page<FinancialOperationDTO>> getPaymentOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting payment financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getPaymentOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get adjustment operations
     */
    @GetMapping("/category/adjustment")
    public ResponseEntity<Page<FinancialOperationDTO>> getAdjustmentOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting adjustment financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getAdjustmentOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    // ========== PRIORITY AND APPROVAL ENDPOINTS ==========

    /**
     * Get high priority operations
     */
    @GetMapping("/priority/high")
    public ResponseEntity<Page<FinancialOperationDTO>> getHighPriorityOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high priority financial operations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getHighPriorityOperations(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get operations requiring executive approval
     */
    @GetMapping("/requiring-executive-approval")
    public ResponseEntity<Page<FinancialOperationDTO>> getOperationsRequiringExecutiveApproval(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial operations requiring executive approval");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getOperationsRequiringExecutiveApproval(pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    // ========== FISCAL PERIOD ENDPOINTS ==========

    /**
     * Get operations by fiscal period
     */
    @GetMapping("/fiscal-period/{period}")
    public ResponseEntity<Page<FinancialOperationDTO>> getOperationsByFiscalPeriod(
            @PathVariable String period,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial operations for fiscal period: {}", period);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getOperationsByFiscalPeriod(period.toUpperCase(), pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    /**
     * Get operations by budget year range
     */
    @GetMapping("/budget-year-range")
    public ResponseEntity<Page<FinancialOperationDTO>> getOperationsByBudgetYearRange(
            @RequestParam String startYear,
            @RequestParam String endYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial operations between years: {} and {}", startYear, endYear);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "budgetYear"));
        Page<FinancialOperationDTO> financialOperations = financialOperationService.getOperationsByBudgetYearRange(startYear, endYear, pageable);
        
        return ResponseEntity.ok(financialOperations);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find financial operation by operation name (unique)
     */
    @GetMapping("/operation/{operation}")
    public ResponseEntity<FinancialOperationDTO> getFinancialOperationByOperation(@PathVariable String operation) {
        log.debug("Getting financial operation by operation: {}", operation);
        
        return financialOperationService.findByOperation(operation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get distinct budget years
     */
    @GetMapping("/budget-years")
    public ResponseEntity<List<String>> getDistinctBudgetYears() {
        log.debug("Getting distinct budget years");
        
        List<String> budgetYears = financialOperationService.getDistinctBudgetYears();
        
        return ResponseEntity.ok(budgetYears);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update financial operation metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<FinancialOperationDTO> updateFinancialOperation(
            @PathVariable Long id,
            @Valid @RequestBody FinancialOperationDTO financialOperationDTO) {
        
        log.info("Updating financial operation with ID: {}", id);
        
        FinancialOperationDTO updatedFinancialOperation = financialOperationService.updateFinancialOperation(id, financialOperationDTO);
        
        return ResponseEntity.ok(updatedFinancialOperation);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if financial operation exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkFinancialOperationExists(@PathVariable Long id) {
        log.debug("Checking existence of financial operation ID: {}", id);
        
        boolean exists = financialOperationService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if operation name exists
     */
    @GetMapping("/exists/operation/{operation}")
    public ResponseEntity<Boolean> checkOperationExists(@PathVariable String operation) {
        log.debug("Checking if operation exists: {}", operation);
        
        boolean exists = financialOperationService.existsByOperation(operation);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of operations by budget year
     */
    @GetMapping("/budget-year/{budgetYear}/count")
    public ResponseEntity<Long> countOperationsByBudgetYear(@PathVariable String budgetYear) {
        log.debug("Getting count of operations for budget year: {}", budgetYear);
        
        Long count = financialOperationService.countOperationsByBudgetYear(budgetYear);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of operations by budget type
     */
    @GetMapping("/budget-type/{budgetTypeId}/count")
    public ResponseEntity<Long> countOperationsByBudgetType(@PathVariable Long budgetTypeId) {
        log.debug("Getting count of operations for budget type ID: {}", budgetTypeId);
        
        Long count = financialOperationService.countOperationsByBudgetType(budgetTypeId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get total count of operations
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> countAllOperations() {
        log.debug("Getting total count of financial operations");
        
        Long count = financialOperationService.countAllOperations();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of current year operations
     */
    @GetMapping("/count/current-year")
    public ResponseEntity<Long> countCurrentYearOperations() {
        log.debug("Getting count of current year financial operations");
        
        Long count = financialOperationService.countCurrentYearOperations();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of budget allocation operations
     */
    @GetMapping("/count/budget-allocation")
    public ResponseEntity<Long> countBudgetAllocationOperations() {
        log.debug("Getting count of budget allocation operations");
        
        Long count = financialOperationService.countBudgetAllocationOperations();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of expenditure operations
     */
    @GetMapping("/count/expenditure")
    public ResponseEntity<Long> countExpenditureOperations() {
        log.debug("Getting count of expenditure operations");
        
        Long count = financialOperationService.countExpenditureOperations();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get financial operation info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<FinancialOperationInfoResponse> getFinancialOperationInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for financial operation ID: {}", id);
        
        try {
            return financialOperationService.findOne(id)
                    .map(financialOperationDTO -> {
                        FinancialOperationInfoResponse response = FinancialOperationInfoResponse.builder()
                                .financialOperationMetadata(financialOperationDTO)
                                .displayText(financialOperationDTO.getDisplayText())
                                .budgetYearAsInt(financialOperationDTO.getBudgetYearAsInt())
                                .operationCategory(financialOperationDTO.getOperationCategory())
                                .operationPriority(financialOperationDTO.getOperationPriority())
                                .financialImpact(financialOperationDTO.getFinancialImpact())
                                .isCurrentYear(financialOperationDTO.isCurrentYear())
                                .isFutureYear(financialOperationDTO.isFutureYear())
                                .isPastYear(financialOperationDTO.isPastYear())
                                .fiscalPeriod(financialOperationDTO.getFiscalPeriod())
                                .approvalRequirements(financialOperationDTO.getApprovalRequirements())
                                .workflowStage(financialOperationDTO.getWorkflowStage())
                                .shortDisplay(financialOperationDTO.getShortDisplay())
                                .fullDisplay(financialOperationDTO.getFullDisplay())
                                .financialDisplay(financialOperationDTO.getFinancialDisplay())
                                .formalDisplay(financialOperationDTO.getFormalDisplay())
                                .operationClassification(financialOperationDTO.getOperationClassification())
                                .executionContext(financialOperationDTO.getExecutionContext())
                                .monitoringRequirements(financialOperationDTO.getMonitoringRequirements())
                                .auditRequirements(financialOperationDTO.getAuditRequirements())
                                .complianceRequirements(financialOperationDTO.getComplianceRequirements())
                                .documentationRequirements(financialOperationDTO.getDocumentationRequirements())
                                .yearEndProcessing(financialOperationDTO.getYearEndProcessing())
                                .budgetCycleStage(financialOperationDTO.getBudgetCycleStage())
                                .financialControlMeasures(financialOperationDTO.getFinancialControlMeasures())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting financial operation info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class FinancialOperationInfoResponse {
        private FinancialOperationDTO financialOperationMetadata;
        private String displayText;
        private Integer budgetYearAsInt;
        private String operationCategory;
        private String operationPriority;
        private String financialImpact;
        private Boolean isCurrentYear;
        private Boolean isFutureYear;
        private Boolean isPastYear;
        private String fiscalPeriod;
        private String approvalRequirements;
        private String workflowStage;
        private String shortDisplay;
        private String fullDisplay;
        private String financialDisplay;
        private String formalDisplay;
        private String operationClassification;
        private String executionContext;
        private String monitoringRequirements;
        private String auditRequirements;
        private String complianceRequirements;
        private String documentationRequirements;
        private String yearEndProcessing;
        private String budgetCycleStage;
        private String financialControlMeasures;
    }
}