/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionService
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

import dz.mdn.iaas.business.plan.dto.ItemDistributionDTO;
import dz.mdn.iaas.business.plan.model.ItemDistribution;
import dz.mdn.iaas.business.plan.repository.ItemDistributionRepository;
import dz.mdn.iaas.business.plan.repository.PlannedItemRepository;
import dz.mdn.iaas.common.administration.repository.StructureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ItemDistribution Service with CRUD operations
 * Handles item distribution operations with logistics management and structure relationships
 * Based on exact field names and business rules for item distribution management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ItemDistributionService {

    private final ItemDistributionRepository itemDistributionRepository;
    
    // Repository beans for related entities (injected as needed)
    private final PlannedItemRepository plannedItemRepository;
    private final StructureRepository structureRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new item distribution
     */
    public ItemDistributionDTO createItemDistribution(ItemDistributionDTO itemDistributionDTO) {
        log.info("Creating item distribution with quantity: {} for planned item ID: {} to structure ID: {}", 
                itemDistributionDTO.getQuantity(), itemDistributionDTO.getPlannedItemId(), itemDistributionDTO.getStructureId());

        // Validate required fields and business rules
        validateRequiredFields(itemDistributionDTO, "create");
        validateBusinessRules(itemDistributionDTO, "create");

        // Create entity with exact field mapping
        ItemDistribution itemDistribution = new ItemDistribution();
        mapDtoToEntity(itemDistributionDTO, itemDistribution);

        // Handle foreign key relationships
        setEntityRelationships(itemDistributionDTO, itemDistribution);

        ItemDistribution savedItemDistribution = itemDistributionRepository.save(itemDistribution);
        log.info("Successfully created item distribution with ID: {}", savedItemDistribution.getId());

        return ItemDistributionDTO.fromEntityWithRelations(savedItemDistribution);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get item distribution by ID
     */
    @Transactional(readOnly = true)
    public ItemDistributionDTO getItemDistributionById(Long id) {
        log.debug("Getting item distribution with ID: {}", id);

        ItemDistribution itemDistribution = itemDistributionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item distribution not found with ID: " + id));

        return ItemDistributionDTO.fromEntityWithRelations(itemDistribution);
    }

    /**
     * Get item distribution entity by ID
     */
    @Transactional(readOnly = true)
    public ItemDistribution getItemDistributionEntityById(Long id) {
        return itemDistributionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item distribution not found with ID: " + id));
    }

    /**
     * Get all item distributions with pagination
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getAllItemDistributions(Pageable pageable) {
        log.debug("Getting all item distributions with pagination");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findAllOrderByStructureAndPlannedItem(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /**
     * Find one item distribution by ID
     */
    @Transactional(readOnly = true)
    public Optional<ItemDistributionDTO> findOne(Long id) {
        log.debug("Finding item distribution by ID: {}", id);

        return itemDistributionRepository.findById(id)
                .map(ItemDistributionDTO::fromEntityWithRelations);
    }

    /**
     * Get item distributions by relationships
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByPlannedItem(Long plannedItemId, Pageable pageable) {
        log.debug("Getting item distributions for planned item ID: {}", plannedItemId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByPlannedItem(plannedItemId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByStructure(Long structureId, Pageable pageable) {
        log.debug("Getting item distributions for structure ID: {}", structureId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByStructure(structureId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /**
     * Get item distributions by hierarchy
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByItem(Long itemId, Pageable pageable) {
        log.debug("Getting item distributions for item ID: {}", itemId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByItem(itemId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByRubric(Long rubricId, Pageable pageable) {
        log.debug("Getting item distributions for rubric ID: {}", rubricId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByRubric(rubricId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByDomain(Long domainId, Pageable pageable) {
        log.debug("Getting item distributions for domain ID: {}", domainId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByDomain(domainId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByFinancialOperation(Long financialOperationId, Pageable pageable) {
        log.debug("Getting item distributions for financial operation ID: {}", financialOperationId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByFinancialOperation(financialOperationId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByBudgetType(Long budgetTypeId, Pageable pageable) {
        log.debug("Getting item distributions for budget type ID: {}", budgetTypeId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByBudgetType(budgetTypeId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByItemStatus(Long itemStatusId, Pageable pageable) {
        log.debug("Getting item distributions for item status ID: {}", itemStatusId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByItemStatus(itemStatusId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /**
     * Get item distributions by quantity categories
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByQuantityRange(float minQuantity, float maxQuantity, Pageable pageable) {
        log.debug("Getting item distributions with quantity between {} and {}", minQuantity, maxQuantity);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByQuantityRange(minQuantity, maxQuantity, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getSmallDistributions(Pageable pageable) {
        log.debug("Getting small item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findSmallDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getMediumDistributions(Pageable pageable) {
        log.debug("Getting medium item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findMediumDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getLargeDistributions(Pageable pageable) {
        log.debug("Getting large item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findLargeDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getBulkDistributions(Pageable pageable) {
        log.debug("Getting bulk item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findBulkDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /**
     * Get item distributions by structure characteristics
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByStructureType(String structureType, Pageable pageable) {
        log.debug("Getting item distributions for structure type: {}", structureType);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByStructureType(structureType, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /*@Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByStructureLevel(int level, Pageable pageable) {
        log.debug("Getting item distributions for structure level: {}", level);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByStructureLevel(level, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }*/

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByParentStructure(Long parentStructureId, Pageable pageable) {
        log.debug("Getting item distributions for parent structure ID: {}", parentStructureId);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByParentStructure(parentStructureId, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /**
     * Get item distributions by priority and analysis
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getHighPriorityDistributions(Pageable pageable) {
        log.debug("Getting high priority item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findHighPriorityDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getUrgentDistributions(Pageable pageable) {
        log.debug("Getting urgent item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findUrgentDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getOverDistributions(Pageable pageable) {
        log.debug("Getting over-distributed items");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findOverDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getValidDistributions(Pageable pageable) {
        log.debug("Getting valid item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findValidDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /**
     * Get item distributions by financial criteria
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByTotalCostRange(double minCost, double maxCost, Pageable pageable) {
        log.debug("Getting item distributions with total cost between {} and {}", minCost, maxCost);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByTotalCostRange(minCost, maxCost, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getMostExpensiveDistributions(Pageable pageable) {
        log.debug("Getting most expensive item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findMostExpensiveDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getItemDistributionsByDistributionPercentage(double minPercentage, double maxPercentage, Pageable pageable) {
        log.debug("Getting item distributions with percentage between {} and {}", minPercentage, maxPercentage);

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findByDistributionPercentage(minPercentage, maxPercentage, pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getCompleteDistributions(Pageable pageable) {
        log.debug("Getting complete item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findCompleteDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getPartialDistributions(Pageable pageable) {
        log.debug("Getting partial item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findPartialDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /**
     * Get special categories
     */
    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getDistributionsRequiringCoordination(Pageable pageable) {
        log.debug("Getting item distributions requiring coordination");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findDistributionsRequiringCoordination(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getSingleDistributions(Pageable pageable) {
        log.debug("Getting single item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findSingleDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    /*@Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getCrossDomainDistributions(Pageable pageable) {
        log.debug("Getting cross-domain item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findCrossDomainDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }*/

    /*@Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getSameDomainDistributions(Pageable pageable) {
        log.debug("Getting same-domain item distributions");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findSameDomainDistributions(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }*/

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getDistributionsWithBudgetModifications(Pageable pageable) {
        log.debug("Getting item distributions with budget modifications");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findWithBudgetModifications(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDistributionDTO> getDistributionsWithoutBudgetModifications(Pageable pageable) {
        log.debug("Getting item distributions without budget modifications");

        Page<ItemDistribution> itemDistributions = itemDistributionRepository.findWithoutBudgetModifications(pageable);
        return itemDistributions.map(ItemDistributionDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update item distribution
     */
    public ItemDistributionDTO updateItemDistribution(Long id, ItemDistributionDTO itemDistributionDTO) {
        log.info("Updating item distribution with ID: {}", id);

        ItemDistribution existingItemDistribution = getItemDistributionEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(itemDistributionDTO, "update");
        validateBusinessRules(itemDistributionDTO, "update");

        // Update fields with exact field mapping
        mapDtoToEntity(itemDistributionDTO, existingItemDistribution);

        // Handle foreign key relationships
        setEntityRelationships(itemDistributionDTO, existingItemDistribution);

        ItemDistribution updatedItemDistribution = itemDistributionRepository.save(existingItemDistribution);
        log.info("Successfully updated item distribution with ID: {}", id);

        return ItemDistributionDTO.fromEntityWithRelations(updatedItemDistribution);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete item distribution
     */
    public void deleteItemDistribution(Long id) {
        log.info("Deleting item distribution with ID: {}", id);

        ItemDistribution itemDistribution = getItemDistributionEntityById(id);
        
        itemDistributionRepository.delete(itemDistribution);

        log.info("Successfully deleted item distribution with ID: {}", id);
    }

    /**
     * Delete item distribution by ID (direct)
     */
    public void deleteItemDistributionById(Long id) {
        log.info("Deleting item distribution by ID: {}", id);

        if (!itemDistributionRepository.existsById(id)) {
            throw new RuntimeException("Item distribution not found with ID: " + id);
        }

        itemDistributionRepository.deleteById(id);
        log.info("Successfully deleted item distribution with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if item distribution exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return itemDistributionRepository.existsById(id);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countItemDistributionsByPlannedItem(Long plannedItemId) {
        return itemDistributionRepository.countByPlannedItem(plannedItemId);
    }

    @Transactional(readOnly = true)
    public Long countItemDistributionsByStructure(Long structureId) {
        return itemDistributionRepository.countByStructure(structureId);
    }

    @Transactional(readOnly = true)
    public Long countItemDistributionsByItem(Long itemId) {
        return itemDistributionRepository.countByItem(itemId);
    }

    @Transactional(readOnly = true)
    public Long countItemDistributionsByRubric(Long rubricId) {
        return itemDistributionRepository.countByRubric(rubricId);
    }

    @Transactional(readOnly = true)
    public Long countAllItemDistributions() {
        return itemDistributionRepository.countAllDistributions();
    }

    /**
     * Get quantity statistics
     */
    @Transactional(readOnly = true)
    public Float getSumQuantityByPlannedItem(Long plannedItemId) {
        return itemDistributionRepository.getSumQuantityByPlannedItem(plannedItemId);
    }

    @Transactional(readOnly = true)
    public Float getSumQuantityByStructure(Long structureId) {
        return itemDistributionRepository.getSumQuantityByStructure(structureId);
    }

    @Transactional(readOnly = true)
    public Double getSumTotalCostByStructure(Long structureId) {
        return itemDistributionRepository.getSumTotalCostByStructure(structureId);
    }

    @Transactional(readOnly = true)
    public Float getAverageQuantity() {
        return itemDistributionRepository.getAverageQuantity();
    }

    @Transactional(readOnly = true)
    public Float getMaxQuantity() {
        return itemDistributionRepository.getMaxQuantity();
    }

    @Transactional(readOnly = true)
    public Float getMinQuantity() {
        return itemDistributionRepository.getMinQuantity();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(ItemDistributionDTO dto, ItemDistribution entity) {
        entity.setQuantity(dto.getQuantity() != null ? dto.getQuantity() : 0.0f); // F_01
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(ItemDistributionDTO dto, ItemDistribution entity) {
        // F_02 - PlannedItem (required)
        if (dto.getPlannedItemId() != null) {
            entity.setPlannedItem(plannedItemRepository.findById(dto.getPlannedItemId())
                    .orElseThrow(() -> new RuntimeException("Planned item not found with ID: " + dto.getPlannedItemId())));
        }

        // F_03 - Structure (required)
        if (dto.getStructureId() != null) {
            entity.setStructure(structureRepository.findById(dto.getStructureId())
                    .orElseThrow(() -> new RuntimeException("Structure not found with ID: " + dto.getStructureId())));
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ItemDistributionDTO dto, String operation) {
        if (dto.getPlannedItemId() == null) {
            throw new RuntimeException("Planned item is required for " + operation);
        }
        if (dto.getStructureId() == null) {
            throw new RuntimeException("Structure is required for " + operation);
        }
        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be positive for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(ItemDistributionDTO dto, String operation) {
        // Validate quantity is positive
        if (dto.getQuantity() != null && dto.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be positive for " + operation);
        }

        // Business validation: Check if total distributed quantity would exceed planned quantity
        if (dto.getPlannedItemId() != null && dto.getQuantity() != null) {
            Float currentTotal = getSumQuantityByPlannedItem(dto.getPlannedItemId());
            if (currentTotal == null) currentTotal = 0.0f;
            
            // For updates, subtract the existing quantity
            if ("update".equals(operation) && dto.getId() != null) {
                ItemDistribution existing = itemDistributionRepository.findById(dto.getId()).orElse(null);
                if (existing != null && existing.getQuantity() != 0) {
                    currentTotal -= existing.getQuantity();
                }
            }
            
            float newTotal = currentTotal + dto.getQuantity();
            
            // Get planned quantity
            var plannedItem = plannedItemRepository.findById(dto.getPlannedItemId()).orElse(null);
            if (plannedItem != null && plannedItem.getPlanedQuantity() != 0) {
                double plannedQuantity = plannedItem.getPlanedQuantity();
                
                if (newTotal > plannedQuantity) {
                    throw new RuntimeException("Total distribution quantity (" + newTotal + 
                        ") cannot exceed planned quantity (" + plannedQuantity + ") for " + operation);
                }
            }
        }

        // Validate quantity precision (float precision issues)
        if (dto.getQuantity() != null && dto.getQuantity() > 1000000) {
            log.warn("Very large quantity detected for {}: {}", operation, dto.getQuantity());
        }
    }
}
