/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemService
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

import dz.mdn.iaas.business.plan.dto.ItemDTO;
import dz.mdn.iaas.business.plan.model.Item;
import dz.mdn.iaas.business.plan.repository.ItemRepository;
import dz.mdn.iaas.business.plan.repository.RubricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Item Service with CRUD operations
 * Handles item management operations with multilingual support, rubric relationship, and item classification
 * Based on exact field names and business rules for item management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    
    // Repository bean for related entity (injected as needed)
    private final RubricRepository rubricRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new item
     */
    public ItemDTO createItem(ItemDTO itemDTO) {
        log.info("Creating item with French designation: {} for rubric ID: {}", 
                itemDTO.getDesignationFr(), itemDTO.getRubricId());

        // Validate required fields and business rules
        validateRequiredFields(itemDTO, "create");
        validateBusinessRules(itemDTO, "create");

        // Create entity with exact field mapping
        Item item = new Item();
        mapDtoToEntity(itemDTO, item);

        // Handle foreign key relationships
        setEntityRelationships(itemDTO, item);

        Item savedItem = itemRepository.save(item);
        log.info("Successfully created item with ID: {}", savedItem.getId());

        return ItemDTO.fromEntityWithRelations(savedItem);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get item by ID
     */
    @Transactional(readOnly = true)
    public ItemDTO getItemById(Long id) {
        log.debug("Getting item with ID: {}", id);

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));

        return ItemDTO.fromEntityWithRelations(item);
    }

    /**
     * Get item entity by ID
     */
    @Transactional(readOnly = true)
    public Item getItemEntityById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));
    }

    /**
     * Get all items with pagination
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getAllItems(Pageable pageable) {
        log.debug("Getting all items with pagination");

        Page<Item> items = itemRepository.findAllOrderByRubricAndDesignation(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Find one item by ID
     */
    @Transactional(readOnly = true)
    public Optional<ItemDTO> findOne(Long id) {
        log.debug("Finding item by ID: {}", id);

        return itemRepository.findById(id)
                .map(ItemDTO::fromEntityWithRelations);
    }

    /**
     * Search items by designation
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> searchItemsByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching items by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllItems(pageable);
        }

        Page<Item> items = itemRepository.searchByDesignation(searchTerm.trim(), pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items by rubric
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsByRubric(Long rubricId, Pageable pageable) {
        log.debug("Getting items for rubric ID: {}", rubricId);

        Page<Item> items = itemRepository.findByRubric(rubricId, pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items by domain (through rubric)
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsByDomain(Long domainId, Pageable pageable) {
        log.debug("Getting items for domain ID: {}", domainId);

        Page<Item> items = itemRepository.findByDomain(domainId, pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items with planned items
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsWithPlannedItems(Pageable pageable) {
        log.debug("Getting items with planned items");

        Page<Item> items = itemRepository.findItemsWithPlannedItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items without planned items
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsWithoutPlannedItems(Pageable pageable) {
        log.debug("Getting items without planned items");

        Page<Item> items = itemRepository.findItemsWithoutPlannedItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items by planned items count range
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsByPlannedItemsCountRange(int minCount, int maxCount, Pageable pageable) {
        log.debug("Getting items with planned items count between {} and {}", minCount, maxCount);

        Page<Item> items = itemRepository.findByPlannedItemsCountRange(minCount, maxCount, pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items by category
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getEquipmentItems(Pageable pageable) {
        log.debug("Getting equipment items");

        Page<Item> items = itemRepository.findEquipmentItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getResourceItems(Pageable pageable) {
        log.debug("Getting resource items");

        Page<Item> items = itemRepository.findResourceItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getServiceItems(Pageable pageable) {
        log.debug("Getting service items");

        Page<Item> items = itemRepository.findServiceItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getInfrastructureItems(Pageable pageable) {
        log.debug("Getting infrastructure items");

        Page<Item> items = itemRepository.findInfrastructureItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getTechnologyItems(Pageable pageable) {
        log.debug("Getting technology items");

        Page<Item> items = itemRepository.findTechnologyItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getConsumableItems(Pageable pageable) {
        log.debug("Getting consumable items");

        Page<Item> items = itemRepository.findConsumableItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getTrainingItems(Pageable pageable) {
        log.debug("Getting training items");

        Page<Item> items = itemRepository.findTrainingItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getDocumentItems(Pageable pageable) {
        log.debug("Getting document items");

        Page<Item> items = itemRepository.findDocumentItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getVehicleItems(Pageable pageable) {
        log.debug("Getting vehicle items");

        Page<Item> items = itemRepository.findVehicleItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get planning complexity-based items
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getComplexPlanningItems(Pageable pageable) {
        log.debug("Getting complex planning items");

        Page<Item> items = itemRepository.findComplexPlanningItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getModeratePlanningItems(Pageable pageable) {
        log.debug("Getting moderate planning items");

        Page<Item> items = itemRepository.findModeratePlanningItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getSimplePlanningItems(Pageable pageable) {
        log.debug("Getting simple planning items");

        Page<Item> items = itemRepository.findSimplePlanningItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get priority-based items
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsByPriorityLevel(String priority, Pageable pageable) {
        log.debug("Getting items by priority level: {}", priority);

        Page<Item> items = itemRepository.findByPriorityLevel(priority, pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get high priority items
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getHighPriorityItems(Pageable pageable) {
        log.debug("Getting high priority items");

        Page<Item> items = itemRepository.findHighPriorityItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items requiring immediate planning
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsRequiringImmediatePlanning(Pageable pageable) {
        log.debug("Getting items requiring immediate planning");

        Page<Item> items = itemRepository.findRequiringImmediatePlanning(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get multilingual items
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getMultilingualItems(Pageable pageable) {
        log.debug("Getting multilingual items");

        Page<Item> items = itemRepository.findMultilingualItems(pageable);
        return items.map(ItemDTO::fromEntity);
    }

    /**
     * Get items by rubric category
     */
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItemsByRubricCategory(String category, Pageable pageable) {
        log.debug("Getting items by rubric category: {}", category);

        Page<Item> items = itemRepository.findByRubricCategory(category, pageable);
        return items.map(ItemDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update item
     */
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        log.info("Updating item with ID: {}", id);

        Item existingItem = getItemEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(itemDTO, "update");
        validateBusinessRules(itemDTO, "update");

        // Update fields with exact field mapping
        mapDtoToEntity(itemDTO, existingItem);

        // Handle foreign key relationships
        setEntityRelationships(itemDTO, existingItem);

        Item updatedItem = itemRepository.save(existingItem);
        log.info("Successfully updated item with ID: {}", id);

        return ItemDTO.fromEntityWithRelations(updatedItem);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete item
     */
    public void deleteItem(Long id) {
        log.info("Deleting item with ID: {}", id);

        Item item = getItemEntityById(id);
        
        // Check if item has planned items before deletion
        if (item.getPlannedItems() != null && !item.getPlannedItems().isEmpty()) {
            throw new RuntimeException("Cannot delete item with ID " + id + 
                " because it has " + item.getPlannedItems().size() + " associated planned items");
        }
        
        itemRepository.delete(item);

        log.info("Successfully deleted item with ID: {}", id);
    }

    /**
     * Delete item by ID (direct)
     */
    public void deleteItemById(Long id) {
        log.info("Deleting item by ID: {}", id);

        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item not found with ID: " + id);
        }

        // Check for associated planned items
        Item item = getItemEntityById(id);
        if (item.getPlannedItems() != null && !item.getPlannedItems().isEmpty()) {
            throw new RuntimeException("Cannot delete item with ID " + id + 
                " because it has " + item.getPlannedItems().size() + " associated planned items");
        }

        itemRepository.deleteById(id);
        log.info("Successfully deleted item with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if item exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countItemsByRubric(Long rubricId) {
        return itemRepository.countByRubric(rubricId);
    }

    @Transactional(readOnly = true)
    public Long countItemsByDomain(Long domainId) {
        return itemRepository.countByDomain(domainId);
    }

    @Transactional(readOnly = true)
    public Long countAllItems() {
        return itemRepository.countAllItems();
    }

    @Transactional(readOnly = true)
    public Long countItemsWithPlannedItems() {
        return itemRepository.countItemsWithPlannedItems();
    }

    @Transactional(readOnly = true)
    public Long countItemsWithoutPlannedItems() {
        return itemRepository.countItemsWithoutPlannedItems();
    }

    @Transactional(readOnly = true)
    public Long countEquipmentItems() {
        return itemRepository.countEquipmentItems();
    }

    @Transactional(readOnly = true)
    public Long countResourceItems() {
        return itemRepository.countResourceItems();
    }

    /**
     * Get planned items statistics
     */
    @Transactional(readOnly = true)
    public Double getAveragePlannedItemsPerItem() {
        return itemRepository.getAveragePlannedItemsPerItem();
    }

    @Transactional(readOnly = true)
    public Integer getMaxPlannedItemsCount() {
        return itemRepository.getMaxPlannedItemsCount();
    }

    @Transactional(readOnly = true)
    public Integer getMinPlannedItemsCountExcludingZero() {
        return itemRepository.getMinPlannedItemsCountExcludingZero();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(ItemDTO dto, Item entity) {
        entity.setDesignationAr(dto.getDesignationAr()); // F_01
        entity.setDesignationEn(dto.getDesignationEn()); // F_02
        entity.setDesignationFr(dto.getDesignationFr()); // F_03
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(ItemDTO dto, Item entity) {
        // F_04 - Rubric (required)
        if (dto.getRubricId() != null) {
            entity.setRubric(rubricRepository.findById(dto.getRubricId())
                    .orElseThrow(() -> new RuntimeException("Rubric not found with ID: " + dto.getRubricId())));
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ItemDTO dto, String operation) {
        if (dto.getDesignationFr() == null || dto.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (dto.getRubricId() == null) {
            throw new RuntimeException("Rubric is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(ItemDTO dto, String operation) {
        // Validate designation lengths
        if (dto.getDesignationFr() != null && dto.getDesignationFr().length() > 200) {
            throw new RuntimeException("French designation cannot exceed 200 characters for " + operation);
        }
        if (dto.getDesignationEn() != null && dto.getDesignationEn().length() > 200) {
            throw new RuntimeException("English designation cannot exceed 200 characters for " + operation);
        }
        if (dto.getDesignationAr() != null && dto.getDesignationAr().length() > 200) {
            throw new RuntimeException("Arabic designation cannot exceed 200 characters for " + operation);
        }

        // Validate at least one designation is provided
        boolean hasDesignation = (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty());
        if (!hasDesignation) {
            throw new RuntimeException("At least French designation must be provided for " + operation);
        }
    }
}
