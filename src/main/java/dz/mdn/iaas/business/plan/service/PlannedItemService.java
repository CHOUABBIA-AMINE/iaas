/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: PlannedItemService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Service
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Service
 *
 **/

package dz.mdn.iaas.business.plan.service;

import dz.mdn.iaas.business.plan.dto.PlannedItemDTO;
import dz.mdn.iaas.business.plan.model.Item;
import dz.mdn.iaas.business.plan.model.ItemStatus;
import dz.mdn.iaas.business.plan.model.PlannedItem;
import dz.mdn.iaas.business.plan.repository.ItemRepository;
import dz.mdn.iaas.business.plan.repository.ItemStatusRepository;
import dz.mdn.iaas.business.plan.repository.PlannedItemRepository;
import dz.mdn.iaas.configuration.template.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PlannedItem Service - Extends GenericService
 * Provides business logic for planned items with item status and item relationships
 */
@Service
@Slf4j
@Transactional
public class PlannedItemService extends GenericService<PlannedItem, PlannedItemDTO, Long> {

    private final PlannedItemRepository plannedItemRepository;
    private final ItemStatusRepository itemStatusRepository;
    private final ItemRepository itemRepository;

    public PlannedItemService(PlannedItemRepository plannedItemRepository,
                             ItemStatusRepository itemStatusRepository,
                             ItemRepository itemRepository) {
        super(plannedItemRepository, PlannedItemDTO.class);
        this.plannedItemRepository = plannedItemRepository;
        this.itemStatusRepository = itemStatusRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    protected PlannedItem convertToEntity(PlannedItemDTO dto) {
        PlannedItem entity = dto.toEntity();
        
        // Set ItemStatus relationship
        if (dto.getItemStatusId() != null) {
            ItemStatus itemStatus = itemStatusRepository.findById(dto.getItemStatusId())
                    .orElseThrow(() -> new RuntimeException("ItemStatus not found with id: " + dto.getItemStatusId()));
            entity.setItemStatus(itemStatus);
        }
        
        // Set Item relationship
        if (dto.getItemId() != null) {
            Item item = itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + dto.getItemId()));
            entity.setItem(item);
        }
        
        return entity;
    }

    @Override
    protected PlannedItemDTO convertToDTO(PlannedItem entity) {
        return PlannedItemDTO.fromEntity(entity);
    }

    @Override
    protected void updateEntityFromDTO(PlannedItemDTO dto, PlannedItem entity) {
        dto.updateEntity(entity);
        
        // Update ItemStatus relationship if provided
        if (dto.getItemStatusId() != null) {
            ItemStatus itemStatus = itemStatusRepository.findById(dto.getItemStatusId())
                    .orElseThrow(() -> new RuntimeException("ItemStatus not found with id: " + dto.getItemStatusId()));
            entity.setItemStatus(itemStatus);
        }
        
        // Update Item relationship if provided
        if (dto.getItemId() != null) {
            Item item = itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + dto.getItemId()));
            entity.setItem(item);
        }
    }

    // ========== SEARCH ==========

    @Override
    public Page<PlannedItemDTO> globalSearch(String query, Pageable pageable) {
        log.debug("Global search for PlannedItems with query: {}", query);
        Page<PlannedItem> entities = plannedItemRepository.findByDesignationContainingIgnoreCase(query, pageable);
        return entities.map(this::convertToDTO);
    }

    // ========== CUSTOM METHODS ==========

    /**
     * Get all planned items without pagination
     */
    public List<PlannedItemDTO> getAll() {
        log.debug("Getting all planned items without pagination");
        return plannedItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find planned items by item status
     */
    public List<PlannedItemDTO> findByItemStatus(Long itemStatusId) {
        log.debug("Finding planned items by item status id: {}", itemStatusId);
        ItemStatus itemStatus = itemStatusRepository.findById(itemStatusId)
                .orElseThrow(() -> new RuntimeException("ItemStatus not found with id: " + itemStatusId));
        return plannedItemRepository.findByItemStatus(itemStatus).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Find planned items by item
     */
    public List<PlannedItemDTO> findByItem(Long itemId) {
        log.debug("Finding planned items by item id: {}", itemId);
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));
        return plannedItemRepository.findByItem(item).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
