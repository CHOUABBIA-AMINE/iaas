/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemStatusController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.ItemStatusService;
import dz.mdn.iaas.business.plan.dto.ItemStatusDTO;

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
 * Item Status REST Controller
 * Handles item status operations: create, get metadata, delete, get all
 * Based on exact ItemStatus model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique)
 */
@RestController
@RequestMapping("/itemStatus")
@RequiredArgsConstructor
@Slf4j
public class ItemStatusController {

    private final ItemStatusService itemStatusService;

    // ========== POST ONE ITEM STATUS ==========

    /**
     * Create new item status
     * Creates item status with multilingual support and status classification
     */
    @PostMapping
    public ResponseEntity<ItemStatusDTO> createItemStatus(@Valid @RequestBody ItemStatusDTO itemStatusDTO) {
        log.info("Creating item status with French designation: {}", 
                itemStatusDTO.getDesignationFr());
        
        ItemStatusDTO createdItemStatus = itemStatusService.createItemStatus(itemStatusDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItemStatus);
    }

    // ========== GET METADATA ==========

    /**
     * Get item status metadata by ID
     * Returns item status information with multilingual details and status classification
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemStatusDTO> getItemStatusMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for item status ID: {}", id);
        
        ItemStatusDTO itemStatusMetadata = itemStatusService.getItemStatusById(id);
        
        return ResponseEntity.ok(itemStatusMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete item status by ID
     * Removes item status from the inventory management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemStatus(@PathVariable Long id) {
        log.info("Deleting item status with ID: {}", id);
        
        itemStatusService.deleteItemStatus(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all item statuses with pagination
     * Returns list of all item statuses ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ItemStatusDTO>> getAllItemStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all item statuses - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getAllItemStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search item statuses by designation (any language)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<ItemStatusDTO>> searchItemStatusesByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching item statuses by designation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.searchItemStatusesByDesignation(query, pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    // ========== STATUS CATEGORY ENDPOINTS ==========

    /**
     * Get active item statuses
     */
    @GetMapping("/category/active")
    public ResponseEntity<Page<ItemStatusDTO>> getActiveStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting active item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getActiveStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get pending item statuses
     */
    @GetMapping("/category/pending")
    public ResponseEntity<Page<ItemStatusDTO>> getPendingStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting pending item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getPendingStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get reserved item statuses
     */
    @GetMapping("/category/reserved")
    public ResponseEntity<Page<ItemStatusDTO>> getReservedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting reserved item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getReservedStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get maintenance item statuses
     */
    @GetMapping("/category/maintenance")
    public ResponseEntity<Page<ItemStatusDTO>> getMaintenanceStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting maintenance item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getMaintenanceStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get damaged item statuses
     */
    @GetMapping("/category/damaged")
    public ResponseEntity<Page<ItemStatusDTO>> getDamagedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting damaged item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getDamagedStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get obsolete item statuses
     */
    @GetMapping("/category/obsolete")
    public ResponseEntity<Page<ItemStatusDTO>> getObsoleteStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting obsolete item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getObsoleteStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get disposed item statuses
     */
    @GetMapping("/category/disposed")
    public ResponseEntity<Page<ItemStatusDTO>> getDisposedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting disposed item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getDisposedStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get lost item statuses
     */
    @GetMapping("/category/lost")
    public ResponseEntity<Page<ItemStatusDTO>> getLostStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting lost item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getLostStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get procurement item statuses
     */
    @GetMapping("/category/procurement")
    public ResponseEntity<Page<ItemStatusDTO>> getProcurementStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting procurement item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getProcurementStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    // ========== OPERATIONAL STATUS ENDPOINTS ==========

    /**
     * Get operational item statuses
     */
    @GetMapping("/operational/ready")
    public ResponseEntity<Page<ItemStatusDTO>> getOperationalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting operational item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getOperationalStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get non-operational item statuses
     */
    @GetMapping("/operational/not-ready")
    public ResponseEntity<Page<ItemStatusDTO>> getNonOperationalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting non-operational item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getNonOperationalStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get statuses requiring action
     */
    @GetMapping("/requiring-action")
    public ResponseEntity<Page<ItemStatusDTO>> getStatusesRequiringAction(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item statuses requiring action");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getStatusesRequiringAction(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    // ========== PRIORITY-BASED ENDPOINTS ==========

    /**
     * Get critical priority statuses
     */
    @GetMapping("/priority/critical")
    public ResponseEntity<Page<ItemStatusDTO>> getCriticalPriorityStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting critical priority item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getCriticalPriorityStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get high priority statuses
     */
    @GetMapping("/priority/high")
    public ResponseEntity<Page<ItemStatusDTO>> getHighPriorityStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high priority item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getHighPriorityStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    /**
     * Get statuses by priority level
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<Page<ItemStatusDTO>> getStatusesByPriorityLevel(
            @PathVariable String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting item statuses by priority level: {}", priority);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getStatusesByPriorityLevel(priority.toUpperCase(), pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    // ========== LANGUAGE SPECIFIC ENDPOINTS ==========

    /**
     * Get multilingual item statuses
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ItemStatusDTO>> getMultilingualStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual item statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ItemStatusDTO> itemStatuses = itemStatusService.getMultilingualStatuses(pageable);
        
        return ResponseEntity.ok(itemStatuses);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find item status by French designation (unique)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ItemStatusDTO> getItemStatusByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting item status by French designation: {}", designationFr);
        
        return itemStatusService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update item status metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemStatusDTO> updateItemStatus(
            @PathVariable Long id,
            @Valid @RequestBody ItemStatusDTO itemStatusDTO) {
        
        log.info("Updating item status with ID: {}", id);
        
        ItemStatusDTO updatedItemStatus = itemStatusService.updateItemStatus(id, itemStatusDTO);
        
        return ResponseEntity.ok(updatedItemStatus);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if item status exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkItemStatusExists(@PathVariable Long id) {
        log.debug("Checking existence of item status ID: {}", id);
        
        boolean exists = itemStatusService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if French designation exists
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkDesignationFrExists(@PathVariable String designationFr) {
        log.debug("Checking if French designation exists: {}", designationFr);
        
        boolean exists = itemStatusService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of all item statuses
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllItemStatuses() {
        log.debug("Getting count of all item statuses");
        
        Long count = itemStatusService.countAllItemStatuses();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of active statuses
     */
    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveStatuses() {
        log.debug("Getting count of active item statuses");
        
        Long count = itemStatusService.countActiveStatuses();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of operational statuses
     */
    @GetMapping("/count/operational")
    public ResponseEntity<Long> countOperationalStatuses() {
        log.debug("Getting count of operational item statuses");
        
        Long count = itemStatusService.countOperationalStatuses();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of non-operational statuses
     */
    @GetMapping("/count/non-operational")
    public ResponseEntity<Long> countNonOperationalStatuses() {
        log.debug("Getting count of non-operational item statuses");
        
        Long count = itemStatusService.countNonOperationalStatuses();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get item status info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ItemStatusInfoResponse> getItemStatusInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for item status ID: {}", id);
        
        try {
            return itemStatusService.findOne(id)
                    .map(itemStatusDTO -> {
                        ItemStatusInfoResponse response = ItemStatusInfoResponse.builder()
                                .itemStatusMetadata(itemStatusDTO)
                                .defaultDesignation(itemStatusDTO.getDefaultDesignation())
                                .displayText(itemStatusDTO.getDisplayText())
                                .isMultilingual(itemStatusDTO.isMultilingual())
                                .availableLanguages(itemStatusDTO.getAvailableLanguages())
                                .statusCategory(itemStatusDTO.getStatusCategory())
                                .statusPriority(itemStatusDTO.getStatusPriority())
                                .operationalImpact(itemStatusDTO.getOperationalImpact())
                                .allowsUsage(itemStatusDTO.allowsUsage())
                                .requiresAction(itemStatusDTO.requiresAction())
                                .isAvailable(itemStatusDTO.isAvailable())
                                .workflowStage(itemStatusDTO.getWorkflowStage())
                                .shortDisplay(itemStatusDTO.getShortDisplay())
                                .fullDisplay(itemStatusDTO.getFullDisplay())
                                .statusDisplay(itemStatusDTO.getStatusDisplay())
                                .formalDisplay(itemStatusDTO.getFormalDisplay())
                                .statusClassification(itemStatusDTO.getStatusClassification())
                                .statusUsageContext(itemStatusDTO.getStatusUsageContext())
                                .monitoringRequirements(itemStatusDTO.getMonitoringRequirements())
                                .recommendedActions(itemStatusDTO.getRecommendedActions())
                                .reportingFrequency(itemStatusDTO.getReportingFrequency())
                                .alertLevel(itemStatusDTO.getAlertLevel())
                                .possibleTransitions(itemStatusDTO.getPossibleTransitions())
                                .lifecycleStage(itemStatusDTO.getLifecycleStage())
                                .inventoryImpact(itemStatusDTO.getInventoryImpact())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting item status info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ItemStatusInfoResponse {
        private ItemStatusDTO itemStatusMetadata;
        private String defaultDesignation;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String statusCategory;
        private String statusPriority;
        private String operationalImpact;
        private Boolean allowsUsage;
        private Boolean requiresAction;
        private Boolean isAvailable;
        private String workflowStage;
        private String shortDisplay;
        private String fullDisplay;
        private String statusDisplay;
        private String formalDisplay;
        private String statusClassification;
        private String statusUsageContext;
        private String monitoringRequirements;
        private String recommendedActions;
        private String reportingFrequency;
        private String alertLevel;
        private String[] possibleTransitions;
        private String lifecycleStage;
        private String inventoryImpact;
    }
}