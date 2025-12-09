/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.plan.dto.PlannedItemDTO;
import dz.mdn.iaas.business.plan.model.PlannedItem;
import dz.mdn.iaas.business.plan.repository.BudgetModificationRepository;
import dz.mdn.iaas.business.plan.repository.FinancialOperationRepository;
import dz.mdn.iaas.business.plan.repository.ItemRepository;
import dz.mdn.iaas.business.plan.repository.ItemStatusRepository;
import dz.mdn.iaas.business.plan.repository.PlannedItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PlannedItem Service with CRUD operations
 * Handles planned item management operations with financial calculations and relationship management
 * Based on exact field names and business rules for planned item management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PlannedItemService {

    private final PlannedItemRepository plannedItemRepository;
    
    // Repository beans for related entities (injected as needed)
    private final ItemStatusRepository itemStatusRepository;
    private final ItemRepository itemRepository;
    private final FinancialOperationRepository financialOperationRepository;
    private final BudgetModificationRepository budgetModificationRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new planned item
     */
    public PlannedItemDTO createPlannedItem(PlannedItemDTO plannedItemDTO) {
        log.info("Creating planned item with designation: {} for item ID: {}", 
                plannedItemDTO.getDesignation(), plannedItemDTO.getItemId());

        // Validate required fields and business rules
        validateRequiredFields(plannedItemDTO, "create");
        validateBusinessRules(plannedItemDTO, "create");

        // Create entity with exact field mapping
        PlannedItem plannedItem = new PlannedItem();
        mapDtoToEntity(plannedItemDTO, plannedItem);

        // Handle foreign key relationships
        setEntityRelationships(plannedItemDTO, plannedItem);

        PlannedItem savedPlannedItem = plannedItemRepository.save(plannedItem);
        log.info("Successfully created planned item with ID: {}", savedPlannedItem.getId());

        return PlannedItemDTO.fromEntityWithRelations(savedPlannedItem);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get planned item by ID
     */
    @Transactional(readOnly = true)
    public PlannedItemDTO getPlannedItemById(Long id) {
        log.debug("Getting planned item with ID: {}", id);

        PlannedItem plannedItem = plannedItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planned item not found with ID: " + id));

        return PlannedItemDTO.fromEntityWithRelations(plannedItem);
    }

    /**
     * Get planned item entity by ID
     */
    @Transactional(readOnly = true)
    public PlannedItem getPlannedItemEntityById(Long id) {
        return plannedItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planned item not found with ID: " + id));
    }

    /**
     * Get all planned items with pagination
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getAllPlannedItems(Pageable pageable) {
        log.debug("Getting all planned items with pagination");

        Page<PlannedItem> plannedItems = plannedItemRepository.findAllOrderByDesignation(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    /**
     * Find one planned item by ID
     */
    @Transactional(readOnly = true)
    public Optional<PlannedItemDTO> findOne(Long id) {
        log.debug("Finding planned item by ID: {}", id);

        return plannedItemRepository.findById(id)
                .map(PlannedItemDTO::fromEntityWithRelations);
    }

    /**
     * Search planned items by designation
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> searchPlannedItemsByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching planned items by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllPlannedItems(pageable);
        }

        Page<PlannedItem> plannedItems = plannedItemRepository.searchByDesignation(searchTerm.trim(), pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    /**
     * Get planned items by relationships
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByItem(Long itemId, Pageable pageable) {
        log.debug("Getting planned items for item ID: {}", itemId);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByItem(itemId, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByItemStatus(Long itemStatusId, Pageable pageable) {
        log.debug("Getting planned items for item status ID: {}", itemStatusId);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByItemStatus(itemStatusId, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByFinancialOperation(Long financialOperationId, Pageable pageable) {
        log.debug("Getting planned items for financial operation ID: {}", financialOperationId);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByFinancialOperation(financialOperationId, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByBudgetModification(Long budgetModificationId, Pageable pageable) {
        log.debug("Getting planned items for budget modification ID: {}", budgetModificationId);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByBudgetModification(budgetModificationId, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    /**
     * Get planned items by hierarchy (rubric, domain, budget type)
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByRubric(Long rubricId, Pageable pageable) {
        log.debug("Getting planned items for rubric ID: {}", rubricId);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByRubric(rubricId, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByDomain(Long domainId, Pageable pageable) {
        log.debug("Getting planned items for domain ID: {}", domainId);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByDomain(domainId, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByBudgetType(Long budgetTypeId, Pageable pageable) {
        log.debug("Getting planned items for budget type ID: {}", budgetTypeId);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByBudgetType(budgetTypeId, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    /**
     * Get planned items with/without distributions
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsWithDistributions(Pageable pageable) {
        log.debug("Getting planned items with distributions");

        Page<PlannedItem> plannedItems = plannedItemRepository.findPlannedItemsWithDistributions(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsWithoutDistributions(Pageable pageable) {
        log.debug("Getting planned items without distributions");

        Page<PlannedItem> plannedItems = plannedItemRepository.findPlannedItemsWithoutDistributions(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    /**
     * Get planned items by financial criteria
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByCostRange(double minCost, double maxCost, Pageable pageable) {
        log.debug("Getting planned items with cost between {} and {}", minCost, maxCost);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByCostRange(minCost, maxCost, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByQuantityRange(double minQuantity, double maxQuantity, Pageable pageable) {
        log.debug("Getting planned items with quantity between {} and {}", minQuantity, maxQuantity);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByQuantityRange(minQuantity, maxQuantity, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsByAllocatedAmountRange(double minAmount, double maxAmount, Pageable pageable) {
        log.debug("Getting planned items with allocated amount between {} and {}", minAmount, maxAmount);

        Page<PlannedItem> plannedItems = plannedItemRepository.findByAllocatedAmountRange(minAmount, maxAmount, pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getHighCostItems(Pageable pageable) {
        log.debug("Getting high cost planned items");

        Page<PlannedItem> plannedItems = plannedItemRepository.findHighCostItems(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getLargeQuantityItems(Pageable pageable) {
        log.debug("Getting large quantity planned items");

        Page<PlannedItem> plannedItems = plannedItemRepository.findLargeQuantityItems(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    /**
     * Get planned items by budget analysis
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getOverBudgetItems(Pageable pageable) {
        log.debug("Getting over-budget planned items");

        Page<PlannedItem> plannedItems = plannedItemRepository.findOverBudgetItems(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getUnderBudgetItems(Pageable pageable) {
        log.debug("Getting under-budget planned items");

        Page<PlannedItem> plannedItems = plannedItemRepository.findUnderBudgetItems(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getWellBudgetedItems(Pageable pageable) {
        log.debug("Getting well-budgeted planned items");

        Page<PlannedItem> plannedItems = plannedItemRepository.findWellBudgetedItems(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getMostExpensiveItems(Pageable pageable) {
        log.debug("Getting most expensive planned items");

        Page<PlannedItem> plannedItems = plannedItemRepository.findMostExpensiveItems(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getItemsRequiringImmediateAttention(Pageable pageable) {
        log.debug("Getting planned items requiring immediate attention");

        Page<PlannedItem> plannedItems = plannedItemRepository.findRequiringImmediateAttention(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getCriticalPlannedItems(Pageable pageable) {
        log.debug("Getting critical planned items");

        Page<PlannedItem> plannedItems = plannedItemRepository.findCriticalPlannedItems(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    /**
     * Get planned items by budget modification status
     */
    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsWithoutBudgetModification(Pageable pageable) {
        log.debug("Getting planned items without budget modification");

        Page<PlannedItem> plannedItems = plannedItemRepository.findWithoutBudgetModification(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<PlannedItemDTO> getPlannedItemsWithBudgetModification(Pageable pageable) {
        log.debug("Getting planned items with budget modification");

        Page<PlannedItem> plannedItems = plannedItemRepository.findWithBudgetModification(pageable);
        return plannedItems.map(PlannedItemDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update planned item
     */
    public PlannedItemDTO updatePlannedItem(Long id, PlannedItemDTO plannedItemDTO) {
        log.info("Updating planned item with ID: {}", id);

        PlannedItem existingPlannedItem = getPlannedItemEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(plannedItemDTO, "update");
        validateBusinessRules(plannedItemDTO, "update");

        // Update fields with exact field mapping
        mapDtoToEntity(plannedItemDTO, existingPlannedItem);

        // Handle foreign key relationships
        setEntityRelationships(plannedItemDTO, existingPlannedItem);

        PlannedItem updatedPlannedItem = plannedItemRepository.save(existingPlannedItem);
        log.info("Successfully updated planned item with ID: {}", id);

        return PlannedItemDTO.fromEntityWithRelations(updatedPlannedItem);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete planned item
     */
    public void deletePlannedItem(Long id) {
        log.info("Deleting planned item with ID: {}", id);

        PlannedItem plannedItem = getPlannedItemEntityById(id);
        
        // Check if planned item has distributions before deletion
        if (plannedItem.getItemDistribution() != null && !plannedItem.getItemDistribution().isEmpty()) {
            throw new RuntimeException("Cannot delete planned item with ID " + id + 
                " because it has " + plannedItem.getItemDistribution().size() + " associated item distributions");
        }
        
        plannedItemRepository.delete(plannedItem);

        log.info("Successfully deleted planned item with ID: {}", id);
    }

    /**
     * Delete planned item by ID (direct)
     */
    public void deletePlannedItemById(Long id) {
        log.info("Deleting planned item by ID: {}", id);

        if (!plannedItemRepository.existsById(id)) {
            throw new RuntimeException("Planned item not found with ID: " + id);
        }

        // Check for associated distributions
        PlannedItem plannedItem = getPlannedItemEntityById(id);
        if (plannedItem.getItemDistribution() != null && !plannedItem.getItemDistribution().isEmpty()) {
            throw new RuntimeException("Cannot delete planned item with ID " + id + 
                " because it has " + plannedItem.getItemDistribution().size() + " associated item distributions");
        }

        plannedItemRepository.deleteById(id);
        log.info("Successfully deleted planned item with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if planned item exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return plannedItemRepository.existsById(id);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countPlannedItemsByItem(Long itemId) {
        return plannedItemRepository.countByItem(itemId);
    }

    @Transactional(readOnly = true)
    public Long countPlannedItemsByItemStatus(Long itemStatusId) {
        return plannedItemRepository.countByItemStatus(itemStatusId);
    }

    @Transactional(readOnly = true)
    public Long countPlannedItemsByFinancialOperation(Long financialOperationId) {
        return plannedItemRepository.countByFinancialOperation(financialOperationId);
    }

    @Transactional(readOnly = true)
    public Long countAllPlannedItems() {
        return plannedItemRepository.countAllPlannedItems();
    }

    @Transactional(readOnly = true)
    public Long countPlannedItemsWithDistributions() {
        return plannedItemRepository.countPlannedItemsWithDistributions();
    }

    @Transactional(readOnly = true)
    public Long countPlannedItemsWithoutDistributions() {
        return plannedItemRepository.countPlannedItemsWithoutDistributions();
    }

    /**
     * Get financial statistics
     */
    @Transactional(readOnly = true)
    public Double getSumAllocatedAmount() {
        return plannedItemRepository.getSumAllocatedAmount();
    }

    @Transactional(readOnly = true)
    public Double getSumTotalCost() {
        return plannedItemRepository.getSumTotalCost();
    }

    @Transactional(readOnly = true)
    public Double getAverageUnitCost() {
        return plannedItemRepository.getAverageUnitCost();
    }

    @Transactional(readOnly = true)
    public Double getAveragePlannedQuantity() {
        return plannedItemRepository.getAveragePlannedQuantity();
    }

    @Transactional(readOnly = true)
    public Double getAverageAllocatedAmount() {
        return plannedItemRepository.getAverageAllocatedAmount();
    }

    @Transactional(readOnly = true)
    public Double getMaxUnitCost() {
        return plannedItemRepository.getMaxUnitCost();
    }

    @Transactional(readOnly = true)
    public Double getMaxPlannedQuantity() {
        return plannedItemRepository.getMaxPlannedQuantity();
    }

    @Transactional(readOnly = true)
    public Double getMaxAllocatedAmount() {
        return plannedItemRepository.getMaxAllocatedAmount();
    }

    /**
     * Get distribution statistics
     */
    @Transactional(readOnly = true)
    public Double getAverageDistributionsPerPlannedItem() {
        return plannedItemRepository.getAverageDistributionsPerPlannedItem();
    }

    @Transactional(readOnly = true)
    public Integer getMaxDistributionsCount() {
        return plannedItemRepository.getMaxDistributionsCount();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(PlannedItemDTO dto, PlannedItem entity) {
        entity.setDesignation(dto.getDesignation()); // F_01
        entity.setUnitairCost(dto.getUnitairCost() != null ? dto.getUnitairCost() : 0.0); // F_02
        entity.setPlanedQuantity(dto.getPlanedQuantity() != null ? dto.getPlanedQuantity() : 0.0); // F_03
        entity.setAllocatedAmount(dto.getAllocatedAmount() != null ? dto.getAllocatedAmount() : 0.0); // F_04
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(PlannedItemDTO dto, PlannedItem entity) {
        // F_05 - ItemStatus (required)
        if (dto.getItemStatusId() != null) {
            entity.setItemStatus(itemStatusRepository.findById(dto.getItemStatusId())
                    .orElseThrow(() -> new RuntimeException("Item status not found with ID: " + dto.getItemStatusId())));
        }

        // F_06 - Item (required)
        if (dto.getItemId() != null) {
            entity.setItem(itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found with ID: " + dto.getItemId())));
        }

        // F_07 - FinancialOperation (required)
        if (dto.getFinancialOperationId() != null) {
            entity.setFinancialOperation(financialOperationRepository.findById(dto.getFinancialOperationId())
                    .orElseThrow(() -> new RuntimeException("Financial operation not found with ID: " + dto.getFinancialOperationId())));
        }

        // F_08 - BudgetModification (optional)
        if (dto.getBudgetModificationId() != null) {
            entity.setBudgetModification(budgetModificationRepository.findById(dto.getBudgetModificationId())
                    .orElseThrow(() -> new RuntimeException("Budget modification not found with ID: " + dto.getBudgetModificationId())));
        } else {
            entity.setBudgetModification(null);
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(PlannedItemDTO dto, String operation) {
        if (dto.getDesignation() == null || dto.getDesignation().trim().isEmpty()) {
            throw new RuntimeException("Designation is required for " + operation);
        }
        if (dto.getItemStatusId() == null) {
            throw new RuntimeException("Item status is required for " + operation);
        }
        if (dto.getItemId() == null) {
            throw new RuntimeException("Item is required for " + operation);
        }
        if (dto.getFinancialOperationId() == null) {
            throw new RuntimeException("Financial operation is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(PlannedItemDTO dto, String operation) {
        // Validate designation length
        if (dto.getDesignation() != null && dto.getDesignation().length() > 200) {
            throw new RuntimeException("Designation cannot exceed 200 characters for " + operation);
        }

        // Validate numeric fields
        if (dto.getUnitairCost() != null && dto.getUnitairCost() <= 0) {
            throw new RuntimeException("Unit cost must be positive for " + operation);
        }
        
        if (dto.getPlanedQuantity() != null && dto.getPlanedQuantity() <= 0) {
            throw new RuntimeException("Planned quantity must be positive for " + operation);
        }
        
        if (dto.getAllocatedAmount() != null && dto.getAllocatedAmount() < 0) {
            throw new RuntimeException("Allocated amount must be non-negative for " + operation);
        }

        // Business logic validation
        if (dto.getUnitairCost() != null && dto.getPlanedQuantity() != null && dto.getAllocatedAmount() != null) {
            double totalCost = dto.getUnitairCost() * dto.getPlanedQuantity();
            double variance = Math.abs(totalCost - dto.getAllocatedAmount());
            double allowedVariance = dto.getAllocatedAmount() * 0.5; // 50% variance threshold
            
            if (variance > allowedVariance) {
                log.warn("Large variance detected for planned item: total cost {} vs allocated amount {} for {}", 
                        totalCost, dto.getAllocatedAmount(), operation);
            }
        }
    }
}
