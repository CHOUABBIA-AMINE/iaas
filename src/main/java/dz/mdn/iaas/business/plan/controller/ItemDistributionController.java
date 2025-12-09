/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.ItemDistributionService;
import dz.mdn.iaas.business.plan.dto.ItemDistributionDTO;

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
 * ItemDistribution REST Controller
 * Handles item distribution operations: create, get metadata, delete, get all
 * Based on exact ItemDistribution model: F_00=id, F_01=quantity, F_02=plannedItemId, F_03=structureId
 * Includes many-to-one relationships with PlannedItem and Structure
 */
@RestController
@RequestMapping("/itemDistribution")
@RequiredArgsConstructor
@Slf4j
public class ItemDistributionController {

    private final ItemDistributionService itemDistributionService;

    // ========== POST ONE ITEM DISTRIBUTION ==========

    /**
     * Create new item distribution
     * Creates item distribution with logistics management and structure relationships
     */
    @PostMapping
    public ResponseEntity<ItemDistributionDTO> createItemDistribution(@Valid @RequestBody ItemDistributionDTO itemDistributionDTO) {
        log.info("Creating item distribution with quantity: {} for planned item ID: {} to structure ID: {}", 
                itemDistributionDTO.getQuantity(), itemDistributionDTO.getPlannedItemId(), itemDistributionDTO.getStructureId());
        
        ItemDistributionDTO createdItemDistribution = itemDistributionService.createItemDistribution(itemDistributionDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItemDistribution);
    }

    // ========== GET METADATA ==========

    /**
     * Get item distribution metadata by ID
     * Returns item distribution information with logistics analysis and relationship details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemDistributionDTO> getItemDistributionMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for item distribution ID: {}", id);
        
        ItemDistributionDTO itemDistributionMetadata = itemDistributionService.getItemDistributionById(id);
        
        return ResponseEntity.ok(itemDistributionMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete item distribution by ID
     * Removes item distribution from the distribution system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemDistribution(@PathVariable Long id) {
        log.info("Deleting item distribution with ID: {}", id);
        
        itemDistributionService.deleteItemDistribution(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all item distributions with pagination
     * Returns list of all item distributions ordered by structure, then by planned item
     */
    @GetMapping
    public ResponseEntity<Page<ItemDistributionDTO>> getAllItemDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "structure.designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all item distributions - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getAllItemDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== RELATIONSHIP ENDPOINTS ==========

    /**
     * Get item distributions by planned item
     */
    @GetMapping("/planned-item/{plannedItemId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByPlannedItem(
            @PathVariable Long plannedItemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for planned item ID: {}", plannedItemId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByPlannedItem(plannedItemId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by structure
     */
    @GetMapping("/structure/{structureId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByStructure(
            @PathVariable Long structureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for structure ID: {}", structureId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "plannedItem.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByStructure(structureId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== HIERARCHY ENDPOINTS ==========

    /**
     * Get item distributions by item (through planned item)
     */
    @GetMapping("/item/{itemId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByItem(
            @PathVariable Long itemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for item ID: {}", itemId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByItem(itemId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by rubric (through planned item → item)
     */
    @GetMapping("/rubric/{rubricId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByRubric(
            @PathVariable Long rubricId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for rubric ID: {}", rubricId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByRubric(rubricId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by domain (through planned item → item → rubric)
     */
    @GetMapping("/domain/{domainId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByDomain(
            @PathVariable Long domainId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for domain ID: {}", domainId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByDomain(domainId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by financial operation (through planned item)
     */
    @GetMapping("/financial-operation/{financialOperationId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByFinancialOperation(
            @PathVariable Long financialOperationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for financial operation ID: {}", financialOperationId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByFinancialOperation(financialOperationId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by budget type (through planned item → financial operation)
     */
    @GetMapping("/budget-type/{budgetTypeId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByBudgetType(
            @PathVariable Long budgetTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for budget type ID: {}", budgetTypeId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByBudgetType(budgetTypeId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by item status (through planned item)
     */
    @GetMapping("/item-status/{itemStatusId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByItemStatus(
            @PathVariable Long itemStatusId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for item status ID: {}", itemStatusId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByItemStatus(itemStatusId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== QUANTITY CATEGORY ENDPOINTS ==========

    /**
     * Get item distributions by quantity range
     */
    @GetMapping("/quantity-range")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByQuantityRange(
            @RequestParam float minQuantity,
            @RequestParam float maxQuantity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions with quantity between {} and {}", minQuantity, maxQuantity);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByQuantityRange(minQuantity, maxQuantity, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get small distributions (quantity <= 10)
     */
    @GetMapping("/quantity/small")
    public ResponseEntity<Page<ItemDistributionDTO>> getSmallDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting small item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getSmallDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get medium distributions (quantity 10-50)
     */
    @GetMapping("/quantity/medium")
    public ResponseEntity<Page<ItemDistributionDTO>> getMediumDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting medium item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getMediumDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get large distributions (quantity 50-100)
     */
    @GetMapping("/quantity/large")
    public ResponseEntity<Page<ItemDistributionDTO>> getLargeDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting large item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getLargeDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get bulk distributions (quantity > 100)
     */
    @GetMapping("/quantity/bulk")
    public ResponseEntity<Page<ItemDistributionDTO>> getBulkDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting bulk item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getBulkDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== STRUCTURE CHARACTERISTIC ENDPOINTS ==========

    /**
     * Get item distributions by structure type
     */
    @GetMapping("/structure-type/{structureType}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByStructureType(
            @PathVariable String structureType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for structure type: {}", structureType);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByStructureType(structureType, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by structure level
     */
    /*@GetMapping("/structure-level/{level}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByStructureLevel(
            @PathVariable int level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for structure level: {}", level);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByStructureLevel(level, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }*/

    /**
     * Get item distributions by parent structure
     */
    @GetMapping("/parent-structure/{parentStructureId}")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByParentStructure(
            @PathVariable Long parentStructureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions for parent structure ID: {}", parentStructureId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByParentStructure(parentStructureId, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== PRIORITY AND ANALYSIS ENDPOINTS ==========

    /**
     * Get high priority distributions
     */
    @GetMapping("/priority/high")
    public ResponseEntity<Page<ItemDistributionDTO>> getHighPriorityDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high priority item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getHighPriorityDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get urgent distributions
     */
    @GetMapping("/urgent")
    public ResponseEntity<Page<ItemDistributionDTO>> getUrgentDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting urgent item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getUrgentDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get over-distributions
     */
    @GetMapping("/over-distributions")
    public ResponseEntity<Page<ItemDistributionDTO>> getOverDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting over-distributed items");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getOverDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get valid distributions
     */
    @GetMapping("/valid")
    public ResponseEntity<Page<ItemDistributionDTO>> getValidDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting valid item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getValidDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== FINANCIAL ANALYSIS ENDPOINTS ==========

    /**
     * Get item distributions by total cost range
     */
    @GetMapping("/total-cost-range")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByTotalCostRange(
            @RequestParam double minCost,
            @RequestParam double maxCost,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions with total cost between {} and {}", minCost, maxCost);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByTotalCostRange(minCost, maxCost, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get most expensive distributions
     */
    @GetMapping("/most-expensive")
    public ResponseEntity<Page<ItemDistributionDTO>> getMostExpensiveDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting most expensive item distributions");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getMostExpensiveDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get item distributions by distribution percentage
     */
    @GetMapping("/distribution-percentage")
    public ResponseEntity<Page<ItemDistributionDTO>> getItemDistributionsByDistributionPercentage(
            @RequestParam double minPercentage,
            @RequestParam double maxPercentage,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions with percentage between {} and {}", minPercentage, maxPercentage);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getItemDistributionsByDistributionPercentage(minPercentage, maxPercentage, pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get complete distributions (100% of planned quantity)
     */
    @GetMapping("/complete")
    public ResponseEntity<Page<ItemDistributionDTO>> getCompleteDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting complete item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "quantity"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getCompleteDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get partial distributions (less than 100% of planned quantity)
     */
    @GetMapping("/partial")
    public ResponseEntity<Page<ItemDistributionDTO>> getPartialDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting partial item distributions");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getPartialDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== SPECIAL CATEGORY ENDPOINTS ==========

    /**
     * Get distributions requiring coordination
     */
    @GetMapping("/requiring-coordination")
    public ResponseEntity<Page<ItemDistributionDTO>> getDistributionsRequiringCoordination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions requiring coordination");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getDistributionsRequiringCoordination(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get single distributions
     */
    @GetMapping("/single")
    public ResponseEntity<Page<ItemDistributionDTO>> getSingleDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting single item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getSingleDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get cross-domain distributions
     */
    /*@GetMapping("/cross-domain")
    public ResponseEntity<Page<ItemDistributionDTO>> getCrossDomainDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting cross-domain item distributions");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getCrossDomainDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }*/

    /**
     * Get same-domain distributions
     */
    /*@GetMapping("/same-domain")
    public ResponseEntity<Page<ItemDistributionDTO>> getSameDomainDistributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting same-domain item distributions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getSameDomainDistributions(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }*/

    /**
     * Get distributions with budget modifications
     */
    @GetMapping("/with-budget-modifications")
    public ResponseEntity<Page<ItemDistributionDTO>> getDistributionsWithBudgetModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions with budget modifications");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getDistributionsWithBudgetModifications(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    /**
     * Get distributions without budget modifications
     */
    @GetMapping("/without-budget-modifications")
    public ResponseEntity<Page<ItemDistributionDTO>> getDistributionsWithoutBudgetModifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item distributions without budget modifications");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "structure.designation"));
        Page<ItemDistributionDTO> itemDistributions = itemDistributionService.getDistributionsWithoutBudgetModifications(pageable);
        
        return ResponseEntity.ok(itemDistributions);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update item distribution metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemDistributionDTO> updateItemDistribution(
            @PathVariable Long id,
            @Valid @RequestBody ItemDistributionDTO itemDistributionDTO) {
        
        log.info("Updating item distribution with ID: {}", id);
        
        ItemDistributionDTO updatedItemDistribution = itemDistributionService.updateItemDistribution(id, itemDistributionDTO);
        
        return ResponseEntity.ok(updatedItemDistribution);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if item distribution exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkItemDistributionExists(@PathVariable Long id) {
        log.debug("Checking existence of item distribution ID: {}", id);
        
        boolean exists = itemDistributionService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of item distributions by planned item
     */
    @GetMapping("/planned-item/{plannedItemId}/count")
    public ResponseEntity<Long> countItemDistributionsByPlannedItem(@PathVariable Long plannedItemId) {
        log.debug("Getting count of item distributions for planned item ID: {}", plannedItemId);
        
        Long count = itemDistributionService.countItemDistributionsByPlannedItem(plannedItemId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of item distributions by structure
     */
    @GetMapping("/structure/{structureId}/count")
    public ResponseEntity<Long> countItemDistributionsByStructure(@PathVariable Long structureId) {
        log.debug("Getting count of item distributions for structure ID: {}", structureId);
        
        Long count = itemDistributionService.countItemDistributionsByStructure(structureId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of item distributions by item
     */
    @GetMapping("/item/{itemId}/count")
    public ResponseEntity<Long> countItemDistributionsByItem(@PathVariable Long itemId) {
        log.debug("Getting count of item distributions for item ID: {}", itemId);
        
        Long count = itemDistributionService.countItemDistributionsByItem(itemId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of item distributions by rubric
     */
    @GetMapping("/rubric/{rubricId}/count")
    public ResponseEntity<Long> countItemDistributionsByRubric(@PathVariable Long rubricId) {
        log.debug("Getting count of item distributions for rubric ID: {}", rubricId);
        
        Long count = itemDistributionService.countItemDistributionsByRubric(rubricId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of all item distributions
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllItemDistributions() {
        log.debug("Getting count of all item distributions");
        
        Long count = itemDistributionService.countAllItemDistributions();
        
        return ResponseEntity.ok(count);
    }

    // ========== QUANTITY STATISTICS ENDPOINTS ==========

    /**
     * Get sum of quantities by planned item
     */
    @GetMapping("/planned-item/{plannedItemId}/sum-quantity")
    public ResponseEntity<Float> getSumQuantityByPlannedItem(@PathVariable Long plannedItemId) {
        log.debug("Getting sum of quantities for planned item ID: {}", plannedItemId);
        
        Float sum = itemDistributionService.getSumQuantityByPlannedItem(plannedItemId);
        
        return ResponseEntity.ok(sum != null ? sum : 0.0f);
    }

    /**
     * Get sum of quantities by structure
     */
    @GetMapping("/structure/{structureId}/sum-quantity")
    public ResponseEntity<Float> getSumQuantityByStructure(@PathVariable Long structureId) {
        log.debug("Getting sum of quantities for structure ID: {}", structureId);
        
        Float sum = itemDistributionService.getSumQuantityByStructure(structureId);
        
        return ResponseEntity.ok(sum != null ? sum : 0.0f);
    }

    /**
     * Get sum of total costs by structure
     */
    @GetMapping("/structure/{structureId}/sum-total-cost")
    public ResponseEntity<Double> getSumTotalCostByStructure(@PathVariable Long structureId) {
        log.debug("Getting sum of total costs for structure ID: {}", structureId);
        
        Double sum = itemDistributionService.getSumTotalCostByStructure(structureId);
        
        return ResponseEntity.ok(sum != null ? sum : 0.0);
    }

    /**
     * Get average quantity
     */
    @GetMapping("/statistics/average-quantity")
    public ResponseEntity<Float> getAverageQuantity() {
        log.debug("Getting average quantity");
        
        Float average = itemDistributionService.getAverageQuantity();
        
        return ResponseEntity.ok(average != null ? average : 0.0f);
    }

    /**
     * Get maximum quantity
     */
    @GetMapping("/statistics/max-quantity")
    public ResponseEntity<Float> getMaxQuantity() {
        log.debug("Getting maximum quantity");
        
        Float max = itemDistributionService.getMaxQuantity();
        
        return ResponseEntity.ok(max != null ? max : 0.0f);
    }

    /**
     * Get minimum quantity
     */
    @GetMapping("/statistics/min-quantity")
    public ResponseEntity<Float> getMinQuantity() {
        log.debug("Getting minimum quantity");
        
        Float min = itemDistributionService.getMinQuantity();
        
        return ResponseEntity.ok(min != null ? min : 0.0f);
    }

    /**
     * Get item distribution info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ItemDistributionInfoResponse> getItemDistributionInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for item distribution ID: {}", id);
        
        try {
            return itemDistributionService.findOne(id)
                    .map(itemDistributionDTO -> {
                        ItemDistributionInfoResponse response = ItemDistributionInfoResponse.builder()
                                .itemDistributionMetadata(itemDistributionDTO)
                                .displayText(itemDistributionDTO.getDisplayText())
                                .distributionCategory(itemDistributionDTO.getDistributionCategory())
                                .distributionStatus(itemDistributionDTO.getDistributionStatus())
                                .distributionPercentage(itemDistributionDTO.getDistributionPercentage())
                                .distributionCost(itemDistributionDTO.getDistributionCost())
                                .distributionComplexity(itemDistributionDTO.getDistributionComplexity())
                                .logisticsRequirements(itemDistributionDTO.getLogisticsRequirements())
                                .shortDisplay(itemDistributionDTO.getShortDisplay())
                                .fullDisplay(itemDistributionDTO.getFullDisplay())
                                .distributionDisplay(itemDistributionDTO.getDistributionDisplay())
                                .formalDisplay(itemDistributionDTO.getFormalDisplay())
                                .itemDistributionClassification(itemDistributionDTO.getItemDistributionClassification())
                                .distributionPlanningRequirements(itemDistributionDTO.getDistributionPlanningRequirements())
                                .distributionTimeline(itemDistributionDTO.getDistributionTimeline())
                                .distributionRiskAssessment(itemDistributionDTO.getDistributionRiskAssessment())
                                .distributionSuccessMetrics(itemDistributionDTO.getDistributionSuccessMetrics())
                                .distributionControlMeasures(itemDistributionDTO.getDistributionControlMeasures())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting item distribution info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ItemDistributionInfoResponse {
        private ItemDistributionDTO itemDistributionMetadata;
        private String displayText;
        private String distributionCategory;
        private String distributionPriority;
        private String distributionUrgency;
        private String distributionStatus;
        private Double distributionPercentage;
        private Double distributionCost;
        private String distributionComplexity;
        private String logisticsRequirements;
        private String shortDisplay;
        private String fullDisplay;
        private String distributionDisplay;
        private String formalDisplay;
        private String itemDistributionClassification;
        private String distributionPlanningRequirements;
        private String distributionTimeline;
        private String distributionRiskAssessment;
        private String distributionSuccessMetrics;
        private String distributionControlMeasures;
    }
}