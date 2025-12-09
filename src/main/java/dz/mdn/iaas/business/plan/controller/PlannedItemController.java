/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.PlannedItemService;
import dz.mdn.iaas.business.plan.dto.PlannedItemDTO;

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
 * PlannedItem REST Controller
 * Handles planned item operations: create, get metadata, delete, get all
 * Based on exact PlannedItem model: F_00=id, F_01=designation, F_02=unitairCost, F_03=planedQuantity, 
 * F_04=allocatedAmount, F_05=itemStatusId, F_06=itemId, F_07=financialOperationId, F_08=budgetModificationId
 * Includes multiple many-to-one relationships and one-to-many relationship with ItemDistributions
 */
@RestController
@RequestMapping("/plannedItem")
@RequiredArgsConstructor
@Slf4j
public class PlannedItemController {

    private final PlannedItemService plannedItemService;

    // ========== POST ONE PLANNED ITEM ==========

    /**
     * Create new planned item
     * Creates planned item with financial calculations and relationship management
     */
    @PostMapping
    public ResponseEntity<PlannedItemDTO> createPlannedItem(@Valid @RequestBody PlannedItemDTO plannedItemDTO) {
        log.info("Creating planned item with designation: {} for item ID: {}", 
                plannedItemDTO.getDesignation(), plannedItemDTO.getItemId());
        
        PlannedItemDTO createdPlannedItem = plannedItemService.createPlannedItem(plannedItemDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlannedItem);
    }

    // ========== GET METADATA ==========

    /**
     * Get planned item metadata by ID
     * Returns planned item information with financial calculations and relationship details
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlannedItemDTO> getPlannedItemMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for planned item ID: {}", id);
        
        PlannedItemDTO plannedItemMetadata = plannedItemService.getPlannedItemById(id);
        
        return ResponseEntity.ok(plannedItemMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete planned item by ID
     * Removes planned item from the planning system
     * Note: Cannot delete planned items with associated item distributions
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlannedItem(@PathVariable Long id) {
        log.info("Deleting planned item with ID: {}", id);
        
        plannedItemService.deletePlannedItem(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all planned items with pagination
     * Returns list of all planned items ordered by designation
     */
    @GetMapping
    public ResponseEntity<Page<PlannedItemDTO>> getAllPlannedItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designation") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all planned items - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<PlannedItemDTO> plannedItems = plannedItemService.getAllPlannedItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search planned items by designation
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<PlannedItemDTO>> searchPlannedItemsByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching planned items by designation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.searchPlannedItemsByDesignation(query, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== RELATIONSHIP ENDPOINTS ==========

    /**
     * Get planned items by item
     */
    @GetMapping("/item/{itemId}")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByItem(
            @PathVariable Long itemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items for item ID: {}", itemId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByItem(itemId, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items by item status
     */
    @GetMapping("/item-status/{itemStatusId}")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByItemStatus(
            @PathVariable Long itemStatusId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items for item status ID: {}", itemStatusId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByItemStatus(itemStatusId, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items by financial operation
     */
    @GetMapping("/financial-operation/{financialOperationId}")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByFinancialOperation(
            @PathVariable Long financialOperationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items for financial operation ID: {}", financialOperationId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByFinancialOperation(financialOperationId, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items by budget modification
     */
    @GetMapping("/budget-modification/{budgetModificationId}")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByBudgetModification(
            @PathVariable Long budgetModificationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items for budget modification ID: {}", budgetModificationId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByBudgetModification(budgetModificationId, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== HIERARCHY ENDPOINTS ==========

    /**
     * Get planned items by rubric (through item)
     */
    @GetMapping("/rubric/{rubricId}")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByRubric(
            @PathVariable Long rubricId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items for rubric ID: {}", rubricId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByRubric(rubricId, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items by domain (through item → rubric)
     */
    @GetMapping("/domain/{domainId}")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByDomain(
            @PathVariable Long domainId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items for domain ID: {}", domainId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByDomain(domainId, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items by budget type (through financial operation)
     */
    @GetMapping("/budget-type/{budgetTypeId}")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByBudgetType(
            @PathVariable Long budgetTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items for budget type ID: {}", budgetTypeId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByBudgetType(budgetTypeId, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== DISTRIBUTION ENDPOINTS ==========

    /**
     * Get planned items with distributions
     */
    @GetMapping("/with-distributions")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsWithDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items with distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsWithDistributions(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items without distributions
     */
    @GetMapping("/without-distributions")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsWithoutDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items without distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsWithoutDistributions(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== FINANCIAL ANALYSIS ENDPOINTS ==========

    /**
     * Get planned items by cost range
     */
    @GetMapping("/cost-range")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByCostRange(
            @RequestParam double minCost,
            @RequestParam double maxCost,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items with cost between {} and {}", minCost, maxCost);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "unitairCost"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByCostRange(minCost, maxCost, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items by quantity range
     */
    @GetMapping("/quantity-range")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByQuantityRange(
            @RequestParam double minQuantity,
            @RequestParam double maxQuantity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items with quantity between {} and {}", minQuantity, maxQuantity);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "planedQuantity"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByQuantityRange(minQuantity, maxQuantity, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items by allocated amount range
     */
    @GetMapping("/allocated-amount-range")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsByAllocatedAmountRange(
            @RequestParam double minAmount,
            @RequestParam double maxAmount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items with allocated amount between {} and {}", minAmount, maxAmount);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "allocatedAmount"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsByAllocatedAmountRange(minAmount, maxAmount, pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get high cost items
     */
    @GetMapping("/high-cost")
    public ResponseEntity<Page<PlannedItemDTO>> getHighCostItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high cost planned items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "unitairCost"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getHighCostItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get large quantity items
     */
    @GetMapping("/large-quantity")
    public ResponseEntity<Page<PlannedItemDTO>> getLargeQuantityItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting large quantity planned items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "planedQuantity"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getLargeQuantityItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== BUDGET ANALYSIS ENDPOINTS ==========

    /**
     * Get over-budget items
     */
    @GetMapping("/over-budget")
    public ResponseEntity<Page<PlannedItemDTO>> getOverBudgetItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting over-budget planned items");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PlannedItemDTO> plannedItems = plannedItemService.getOverBudgetItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get under-budget items
     */
    @GetMapping("/under-budget")
    public ResponseEntity<Page<PlannedItemDTO>> getUnderBudgetItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting under-budget planned items");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PlannedItemDTO> plannedItems = plannedItemService.getUnderBudgetItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get well-budgeted items
     */
    @GetMapping("/well-budgeted")
    public ResponseEntity<Page<PlannedItemDTO>> getWellBudgetedItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting well-budgeted planned items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getWellBudgetedItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get most expensive items
     */
    @GetMapping("/most-expensive")
    public ResponseEntity<Page<PlannedItemDTO>> getMostExpensiveItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting most expensive planned items");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PlannedItemDTO> plannedItems = plannedItemService.getMostExpensiveItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get items requiring immediate attention
     */
    @GetMapping("/requiring-immediate-attention")
    public ResponseEntity<Page<PlannedItemDTO>> getItemsRequiringImmediateAttention(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items requiring immediate attention");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PlannedItemDTO> plannedItems = plannedItemService.getItemsRequiringImmediateAttention(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get critical planned items
     */
    @GetMapping("/critical")
    public ResponseEntity<Page<PlannedItemDTO>> getCriticalPlannedItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting critical planned items");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PlannedItemDTO> plannedItems = plannedItemService.getCriticalPlannedItems(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== BUDGET MODIFICATION STATUS ENDPOINTS ==========

    /**
     * Get planned items without budget modification
     */
    @GetMapping("/without-budget-modification")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsWithoutBudgetModification(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items without budget modification");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsWithoutBudgetModification(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    /**
     * Get planned items with budget modification
     */
    @GetMapping("/with-budget-modification")
    public ResponseEntity<Page<PlannedItemDTO>> getPlannedItemsWithBudgetModification(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting planned items with budget modification");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designation"));
        Page<PlannedItemDTO> plannedItems = plannedItemService.getPlannedItemsWithBudgetModification(pageable);
        
        return ResponseEntity.ok(plannedItems);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update planned item metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlannedItemDTO> updatePlannedItem(
            @PathVariable Long id,
            @Valid @RequestBody PlannedItemDTO plannedItemDTO) {
        
        log.info("Updating planned item with ID: {}", id);
        
        PlannedItemDTO updatedPlannedItem = plannedItemService.updatePlannedItem(id, plannedItemDTO);
        
        return ResponseEntity.ok(updatedPlannedItem);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if planned item exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkPlannedItemExists(@PathVariable Long id) {
        log.debug("Checking existence of planned item ID: {}", id);
        
        boolean exists = plannedItemService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of planned items by item
     */
    @GetMapping("/item/{itemId}/count")
    public ResponseEntity<Long> countPlannedItemsByItem(@PathVariable Long itemId) {
        log.debug("Getting count of planned items for item ID: {}", itemId);
        
        Long count = plannedItemService.countPlannedItemsByItem(itemId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of planned items by item status
     */
    @GetMapping("/item-status/{itemStatusId}/count")
    public ResponseEntity<Long> countPlannedItemsByItemStatus(@PathVariable Long itemStatusId) {
        log.debug("Getting count of planned items for item status ID: {}", itemStatusId);
        
        Long count = plannedItemService.countPlannedItemsByItemStatus(itemStatusId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of planned items by financial operation
     */
    @GetMapping("/financial-operation/{financialOperationId}/count")
    public ResponseEntity<Long> countPlannedItemsByFinancialOperation(@PathVariable Long financialOperationId) {
        log.debug("Getting count of planned items for financial operation ID: {}", financialOperationId);
        
        Long count = plannedItemService.countPlannedItemsByFinancialOperation(financialOperationId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of all planned items
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllPlannedItems() {
        log.debug("Getting count of all planned items");
        
        Long count = plannedItemService.countAllPlannedItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of planned items with distributions
     */
    @GetMapping("/count/with-distributions")
    public ResponseEntity<Long> countPlannedItemsWithDistributions() {
        log.debug("Getting count of planned items with distributions");
        
        Long count = plannedItemService.countPlannedItemsWithDistributions();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of planned items without distributions
     */
    @GetMapping("/count/without-distributions")
    public ResponseEntity<Long> countPlannedItemsWithoutDistributions() {
        log.debug("Getting count of planned items without distributions");
        
        Long count = plannedItemService.countPlannedItemsWithoutDistributions();
        
        return ResponseEntity.ok(count);
    }

    // ========== FINANCIAL STATISTICS ENDPOINTS ==========

    /**
     * Get sum of allocated amounts
     */
    @GetMapping("/financial/sum-allocated")
    public ResponseEntity<Double> getSumAllocatedAmount() {
        log.debug("Getting sum of allocated amounts");
        
        Double sum = plannedItemService.getSumAllocatedAmount();
        
        return ResponseEntity.ok(sum != null ? sum : 0.0);
    }

    /**
     * Get sum of total costs
     */
    @GetMapping("/financial/sum-total-cost")
    public ResponseEntity<Double> getSumTotalCost() {
        log.debug("Getting sum of total costs");
        
        Double sum = plannedItemService.getSumTotalCost();
        
        return ResponseEntity.ok(sum != null ? sum : 0.0);
    }

    /**
     * Get average unit cost
     */
    @GetMapping("/financial/average-unit-cost")
    public ResponseEntity<Double> getAverageUnitCost() {
        log.debug("Getting average unit cost");
        
        Double average = plannedItemService.getAverageUnitCost();
        
        return ResponseEntity.ok(average != null ? average : 0.0);
    }

    /**
     * Get average planned quantity
     */
    @GetMapping("/financial/average-quantity")
    public ResponseEntity<Double> getAveragePlannedQuantity() {
        log.debug("Getting average planned quantity");
        
        Double average = plannedItemService.getAveragePlannedQuantity();
        
        return ResponseEntity.ok(average != null ? average : 0.0);
    }

    /**
     * Get average allocated amount
     */
    @GetMapping("/financial/average-allocated")
    public ResponseEntity<Double> getAverageAllocatedAmount() {
        log.debug("Getting average allocated amount");
        
        Double average = plannedItemService.getAverageAllocatedAmount();
        
        return ResponseEntity.ok(average != null ? average : 0.0);
    }

    /**
     * Get maximum unit cost
     */
    @GetMapping("/financial/max-unit-cost")
    public ResponseEntity<Double> getMaxUnitCost() {
        log.debug("Getting maximum unit cost");
        
        Double max = plannedItemService.getMaxUnitCost();
        
        return ResponseEntity.ok(max != null ? max : 0.0);
    }

    /**
     * Get maximum planned quantity
     */
    @GetMapping("/financial/max-quantity")
    public ResponseEntity<Double> getMaxPlannedQuantity() {
        log.debug("Getting maximum planned quantity");
        
        Double max = plannedItemService.getMaxPlannedQuantity();
        
        return ResponseEntity.ok(max != null ? max : 0.0);
    }

    /**
     * Get maximum allocated amount
     */
    @GetMapping("/financial/max-allocated")
    public ResponseEntity<Double> getMaxAllocatedAmount() {
        log.debug("Getting maximum allocated amount");
        
        Double max = plannedItemService.getMaxAllocatedAmount();
        
        return ResponseEntity.ok(max != null ? max : 0.0);
    }

    // ========== DISTRIBUTION STATISTICS ENDPOINTS ==========

    /**
     * Get average distributions per planned item
     */
    @GetMapping("/distributions/average")
    public ResponseEntity<Double> getAverageDistributionsPerPlannedItem() {
        log.debug("Getting average distributions per planned item");
        
        Double average = plannedItemService.getAverageDistributionsPerPlannedItem();
        
        return ResponseEntity.ok(average != null ? average : 0.0);
    }

    /**
     * Get maximum distributions count
     */
    @GetMapping("/distributions/max")
    public ResponseEntity<Integer> getMaxDistributionsCount() {
        log.debug("Getting maximum distributions count");
        
        Integer max = plannedItemService.getMaxDistributionsCount();
        
        return ResponseEntity.ok(max != null ? max : 0);
    }

    /**
     * Get planned item info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<PlannedItemInfoResponse> getPlannedItemInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for planned item ID: {}", id);
        
        try {
            return plannedItemService.findOne(id)
                    .map(plannedItemDTO -> {
                        PlannedItemInfoResponse response = PlannedItemInfoResponse.builder()
                                .plannedItemMetadata(plannedItemDTO)
                                .displayText(plannedItemDTO.getDisplayText())
                                .totalCost(plannedItemDTO.getTotalCost())
                                .variance(plannedItemDTO.getVariance())
                                .budgetUtilization(plannedItemDTO.getBudgetUtilization())
                                .planningStatus(plannedItemDTO.getPlanningStatus())
                                .costCategory(plannedItemDTO.getCostCategory())
                                .quantityScale(plannedItemDTO.getQuantityScale())
                                .hasItemDistributions(plannedItemDTO.hasItemDistributions())
                                .itemDistributionsCountSafe(plannedItemDTO.getItemDistributionsCountSafe())
                                .distributionComplexity(plannedItemDTO.getDistributionComplexity())
                                .shortDisplay(plannedItemDTO.getShortDisplay())
                                .fullDisplay(plannedItemDTO.getFullDisplay())
                                .financialDisplay(plannedItemDTO.getFinancialDisplay())
                                .formalDisplay(plannedItemDTO.getFormalDisplay())
                                .plannedItemClassification(plannedItemDTO.getPlannedItemClassification())
                                .procurementRequirements(plannedItemDTO.getProcurementRequirements())
                                .financialControlMeasures(plannedItemDTO.getFinancialControlMeasures())
                                .riskAssessment(plannedItemDTO.getRiskAssessment())
                                .deliveryTimeline(plannedItemDTO.getDeliveryTimeline())
                                .qualityAssuranceRequirements(plannedItemDTO.getQualityAssuranceRequirements())
                                .successMetrics(plannedItemDTO.getSuccessMetrics())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting planned item info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class PlannedItemInfoResponse {
        private PlannedItemDTO plannedItemMetadata;
        private String displayText;
        private Double totalCost;
        private Double variance;
        private Double budgetUtilization;
        private String planningStatus;
        private String costCategory;
        private String quantityScale;
        private Boolean hasItemDistributions;
        private Integer itemDistributionsCountSafe;
        private String distributionComplexity;
        private String shortDisplay;
        private String fullDisplay;
        private String financialDisplay;
        private String formalDisplay;
        private String plannedItemClassification;
        private String procurementRequirements;
        private String financialControlMeasures;
        private String riskAssessment;
        private String deliveryTimeline;
        private String qualityAssuranceRequirements;
        private String successMetrics;
    }
}