/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.ItemService;
import dz.mdn.iaas.business.plan.dto.ItemDTO;

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
 * Item REST Controller
 * Handles item operations: create, get metadata, delete, get all
 * Based on exact Item model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=rubricId
 * Includes many-to-one relationship with Rubric and one-to-many relationship with PlannedItems
 */
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    // ========== POST ONE ITEM ==========

    /**
     * Create new item
     * Creates item with multilingual support, rubric relationship, and classification
     */
    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        log.info("Creating item with French designation: {} for rubric ID: {}", 
                itemDTO.getDesignationFr(), itemDTO.getRubricId());
        
        ItemDTO createdItem = itemService.createItem(itemDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    // ========== GET METADATA ==========

    /**
     * Get item metadata by ID
     * Returns item information with rubric details, planned items details, and classification
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for item ID: {}", id);
        
        ItemDTO itemMetadata = itemService.getItemById(id);
        
        return ResponseEntity.ok(itemMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete item by ID
     * Removes item from the item management system
     * Note: Cannot delete items with associated planned items
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        log.info("Deleting item with ID: {}", id);
        
        itemService.deleteItem(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all items with pagination
     * Returns list of all items ordered by rubric designation, then by item designation
     */
    @GetMapping
    public ResponseEntity<Page<ItemDTO>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "rubric.designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all items - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ItemDTO> items = itemService.getAllItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search items by designation (any language)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<ItemDTO>> searchItemsByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching items by designation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "rubric.designationFr"));
        Page<ItemDTO> items = itemService.searchItemsByDesignation(query, pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== RUBRIC RELATIONSHIP ENDPOINTS ==========

    /**
     * Get items by rubric
     */
    @GetMapping("/rubric/{rubricId}")
    public ResponseEntity<Page<ItemDTO>> getItemsByRubric(
            @PathVariable Long rubricId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items for rubric ID: {}", rubricId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getItemsByRubric(rubricId, pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get items by domain (through rubric)
     */
    @GetMapping("/domain/{domainId}")
    public ResponseEntity<Page<ItemDTO>> getItemsByDomain(
            @PathVariable Long domainId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items for domain ID: {}", domainId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "rubric.designationFr"));
        Page<ItemDTO> items = itemService.getItemsByDomain(domainId, pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get items by rubric category
     */
    @GetMapping("/rubric-category/{category}")
    public ResponseEntity<Page<ItemDTO>> getItemsByRubricCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items by rubric category: {}", category);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "rubric.designationFr"));
        Page<ItemDTO> items = itemService.getItemsByRubricCategory(category, pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== PLANNED ITEMS RELATIONSHIP ENDPOINTS ==========

    /**
     * Get items with planned items
     */
    @GetMapping("/with-planned-items")
    public ResponseEntity<Page<ItemDTO>> getItemsWithPlannedItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items with planned items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "rubric.designationFr"));
        Page<ItemDTO> items = itemService.getItemsWithPlannedItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get items without planned items
     */
    @GetMapping("/without-planned-items")
    public ResponseEntity<Page<ItemDTO>> getItemsWithoutPlannedItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items without planned items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "rubric.designationFr"));
        Page<ItemDTO> items = itemService.getItemsWithoutPlannedItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get items by planned items count range
     */
    @GetMapping("/planned-items-count-range")
    public ResponseEntity<Page<ItemDTO>> getItemsByPlannedItemsCountRange(
            @RequestParam int minCount,
            @RequestParam int maxCount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items with planned items count between {} and {}", minCount, maxCount);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "plannedItemsCount"));
        Page<ItemDTO> items = itemService.getItemsByPlannedItemsCountRange(minCount, maxCount, pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== ITEM CATEGORY ENDPOINTS ==========

    /**
     * Get equipment items
     */
    @GetMapping("/category/equipment")
    public ResponseEntity<Page<ItemDTO>> getEquipmentItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting equipment items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getEquipmentItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get resource items
     */
    @GetMapping("/category/resource")
    public ResponseEntity<Page<ItemDTO>> getResourceItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting resource items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getResourceItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get service items
     */
    @GetMapping("/category/service")
    public ResponseEntity<Page<ItemDTO>> getServiceItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting service items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getServiceItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get infrastructure items
     */
    @GetMapping("/category/infrastructure")
    public ResponseEntity<Page<ItemDTO>> getInfrastructureItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting infrastructure items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getInfrastructureItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get technology items
     */
    @GetMapping("/category/technology")
    public ResponseEntity<Page<ItemDTO>> getTechnologyItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technology items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getTechnologyItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get consumable items
     */
    @GetMapping("/category/consumable")
    public ResponseEntity<Page<ItemDTO>> getConsumableItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting consumable items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getConsumableItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get training items
     */
    @GetMapping("/category/training")
    public ResponseEntity<Page<ItemDTO>> getTrainingItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting training items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getTrainingItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get document items
     */
    @GetMapping("/category/document")
    public ResponseEntity<Page<ItemDTO>> getDocumentItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting document items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getDocumentItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get vehicle items
     */
    @GetMapping("/category/vehicle")
    public ResponseEntity<Page<ItemDTO>> getVehicleItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting vehicle items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getVehicleItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== PLANNING COMPLEXITY ENDPOINTS ==========

    /**
     * Get complex planning items (many planned items)
     */
    @GetMapping("/planning-complexity/complex")
    public ResponseEntity<Page<ItemDTO>> getComplexPlanningItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting complex planning items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "plannedItemsCount"));
        Page<ItemDTO> items = itemService.getComplexPlanningItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get moderate planning items
     */
    @GetMapping("/planning-complexity/moderate")
    public ResponseEntity<Page<ItemDTO>> getModeratePlanningItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting moderate planning items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "plannedItemsCount"));
        Page<ItemDTO> items = itemService.getModeratePlanningItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get simple planning items
     */
    @GetMapping("/planning-complexity/simple")
    public ResponseEntity<Page<ItemDTO>> getSimplePlanningItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting simple planning items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getSimplePlanningItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== PRIORITY-BASED ENDPOINTS ==========

    /**
     * Get items by priority level
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<Page<ItemDTO>> getItemsByPriorityLevel(
            @PathVariable String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items by priority level: {}", priority);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getItemsByPriorityLevel(priority.toUpperCase(), pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get high priority items
     */
    @GetMapping("/priority/high")
    public ResponseEntity<Page<ItemDTO>> getHighPriorityItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high priority items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getHighPriorityItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    /**
     * Get items requiring immediate planning
     */
    @GetMapping("/requiring-immediate-planning")
    public ResponseEntity<Page<ItemDTO>> getItemsRequiringImmediatePlanning(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting items requiring immediate planning");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getItemsRequiringImmediatePlanning(pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== LANGUAGE SPECIFIC ENDPOINTS ==========

    /**
     * Get multilingual items
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ItemDTO>> getMultilingualItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemDTO> items = itemService.getMultilingualItems(pageable);
        
        return ResponseEntity.ok(items);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update item metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody ItemDTO itemDTO) {
        
        log.info("Updating item with ID: {}", id);
        
        ItemDTO updatedItem = itemService.updateItem(id, itemDTO);
        
        return ResponseEntity.ok(updatedItem);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if item exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkItemExists(@PathVariable Long id) {
        log.debug("Checking existence of item ID: {}", id);
        
        boolean exists = itemService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of items by rubric
     */
    @GetMapping("/rubric/{rubricId}/count")
    public ResponseEntity<Long> countItemsByRubric(@PathVariable Long rubricId) {
        log.debug("Getting count of items for rubric ID: {}", rubricId);
        
        Long count = itemService.countItemsByRubric(rubricId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of items by domain
     */
    @GetMapping("/domain/{domainId}/count")
    public ResponseEntity<Long> countItemsByDomain(@PathVariable Long domainId) {
        log.debug("Getting count of items for domain ID: {}", domainId);
        
        Long count = itemService.countItemsByDomain(domainId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of all items
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllItems() {
        log.debug("Getting count of all items");
        
        Long count = itemService.countAllItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of items with planned items
     */
    @GetMapping("/count/with-planned-items")
    public ResponseEntity<Long> countItemsWithPlannedItems() {
        log.debug("Getting count of items with planned items");
        
        Long count = itemService.countItemsWithPlannedItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of items without planned items
     */
    @GetMapping("/count/without-planned-items")
    public ResponseEntity<Long> countItemsWithoutPlannedItems() {
        log.debug("Getting count of items without planned items");
        
        Long count = itemService.countItemsWithoutPlannedItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of equipment items
     */
    @GetMapping("/count/equipment")
    public ResponseEntity<Long> countEquipmentItems() {
        log.debug("Getting count of equipment items");
        
        Long count = itemService.countEquipmentItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of resource items
     */
    @GetMapping("/count/resource")
    public ResponseEntity<Long> countResourceItems() {
        log.debug("Getting count of resource items");
        
        Long count = itemService.countResourceItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get average planned items per item
     */
    @GetMapping("/planned-items/average")
    public ResponseEntity<Double> getAveragePlannedItemsPerItem() {
        log.debug("Getting average planned items per item");
        
        Double average = itemService.getAveragePlannedItemsPerItem();
        
        return ResponseEntity.ok(average != null ? average : 0.0);
    }

    /**
     * Get maximum planned items count
     */
    @GetMapping("/planned-items/max")
    public ResponseEntity<Integer> getMaxPlannedItemsCount() {
        log.debug("Getting maximum planned items count");
        
        Integer max = itemService.getMaxPlannedItemsCount();
        
        return ResponseEntity.ok(max != null ? max : 0);
    }

    /**
     * Get minimum planned items count (excluding zero)
     */
    @GetMapping("/planned-items/min-excluding-zero")
    public ResponseEntity<Integer> getMinPlannedItemsCountExcludingZero() {
        log.debug("Getting minimum planned items count excluding zero");
        
        Integer min = itemService.getMinPlannedItemsCountExcludingZero();
        
        return ResponseEntity.ok(min != null ? min : 0);
    }

    /**
     * Get item info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ItemInfoResponse> getItemInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for item ID: {}", id);
        
        try {
            return itemService.findOne(id)
                    .map(itemDTO -> {
                        ItemInfoResponse response = ItemInfoResponse.builder()
                                .itemMetadata(itemDTO)
                                .defaultDesignation(itemDTO.getDefaultDesignation())
                                .displayText(itemDTO.getDisplayText())
                                .isMultilingual(itemDTO.isMultilingual())
                                .availableLanguages(itemDTO.getAvailableLanguages())
                                .itemCategory(itemDTO.getItemCategory())
                                .itemPriority(itemDTO.getItemPriority())
                                .lifecycleStage(itemDTO.getLifecycleStage())
                                .hasPlannedItems(itemDTO.hasPlannedItems())
                                .plannedItemsCountSafe(itemDTO.getPlannedItemsCountSafe())
                                .planningComplexity(itemDTO.getPlanningComplexity())
                                .shortDisplay(itemDTO.getShortDisplay())
                                .fullDisplay(itemDTO.getFullDisplay())
                                .itemDisplay(itemDTO.getItemDisplay())
                                .formalDisplay(itemDTO.getFormalDisplay())
                                .itemClassification(itemDTO.getItemClassification())
                                .itemUsageContext(itemDTO.getItemUsageContext())
                                .managementRequirements(itemDTO.getManagementRequirements())
                                .procurementStrategy(itemDTO.getProcurementStrategy())
                                .maintenanceRequirements(itemDTO.getMaintenanceRequirements())
                                .successMetrics(itemDTO.getSuccessMetrics())
                                .riskFactors(itemDTO.getRiskFactors())
                                .lifecycleManagement(itemDTO.getLifecycleManagement())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting item info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ItemInfoResponse {
        private ItemDTO itemMetadata;
        private String defaultDesignation;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String itemCategory;
        private String itemPriority;
        private String lifecycleStage;
        private Boolean hasPlannedItems;
        private Integer plannedItemsCountSafe;
        private String planningComplexity;
        private String shortDisplay;
        private String fullDisplay;
        private String itemDisplay;
        private String formalDisplay;
        private String itemClassification;
        private String itemUsageContext;
        private String managementRequirements;
        private String procurementStrategy;
        private String maintenanceRequirements;
        private String successMetrics;
        private String riskFactors;
        private String lifecycleManagement;
    }
}