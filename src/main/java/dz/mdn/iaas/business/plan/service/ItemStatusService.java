/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemStatusService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.service;

import dz.mdn.iaas.business.plan.model.ItemStatus;
import dz.mdn.iaas.business.plan.repository.ItemStatusRepository;
import dz.mdn.iaas.business.plan.dto.ItemStatusDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Item Status Service with CRUD operations
 * Handles item status management operations with multilingual support and status classification
 * Based on exact field names and business rules for inventory status management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ItemStatusService {

    private final ItemStatusRepository itemStatusRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new item status
     */
    public ItemStatusDTO createItemStatus(ItemStatusDTO itemStatusDTO) {
        log.info("Creating item status with French designation: {}", 
                itemStatusDTO.getDesignationFr());

        // Validate required fields and business rules
        validateRequiredFields(itemStatusDTO, "create");
        validateBusinessRules(itemStatusDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(itemStatusDTO, null);

        // Create entity with exact field mapping
        ItemStatus itemStatus = new ItemStatus();
        mapDtoToEntity(itemStatusDTO, itemStatus);

        ItemStatus savedItemStatus = itemStatusRepository.save(itemStatus);
        log.info("Successfully created item status with ID: {}", savedItemStatus.getId());

        return ItemStatusDTO.fromEntity(savedItemStatus);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get item status by ID
     */
    @Transactional(readOnly = true)
    public ItemStatusDTO getItemStatusById(Long id) {
        log.debug("Getting item status with ID: {}", id);

        ItemStatus itemStatus = itemStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item status not found with ID: " + id));

        return ItemStatusDTO.fromEntity(itemStatus);
    }

    /**
     * Get item status entity by ID
     */
    @Transactional(readOnly = true)
    public ItemStatus getItemStatusEntityById(Long id) {
        return itemStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item status not found with ID: " + id));
    }

    /**
     * Get all item statuses with pagination
     */
    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getAllItemStatuses(Pageable pageable) {
        log.debug("Getting all item statuses with pagination");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findAllOrderByDesignationFr(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    /**
     * Find one item status by ID
     */
    @Transactional(readOnly = true)
    public Optional<ItemStatusDTO> findOne(Long id) {
        log.debug("Finding item status by ID: {}", id);

        return itemStatusRepository.findById(id)
                .map(ItemStatusDTO::fromEntity);
    }

    /**
     * Find item status by French designation (unique)
     */
    @Transactional(readOnly = true)
    public Optional<ItemStatusDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding item status by French designation: {}", designationFr);

        return itemStatusRepository.findByDesignationFr(designationFr)
                .map(ItemStatusDTO::fromEntity);
    }

    /**
     * Search item statuses by designation
     */
    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> searchItemStatusesByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching item statuses by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllItemStatuses(pageable);
        }

        Page<ItemStatus> itemStatuses = itemStatusRepository.searchByDesignation(searchTerm.trim(), pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    /**
     * Get item statuses by category
     */
    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getActiveStatuses(Pageable pageable) {
        log.debug("Getting active item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findActiveStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getPendingStatuses(Pageable pageable) {
        log.debug("Getting pending item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findPendingStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getReservedStatuses(Pageable pageable) {
        log.debug("Getting reserved item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findReservedStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getMaintenanceStatuses(Pageable pageable) {
        log.debug("Getting maintenance item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findMaintenanceStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getDamagedStatuses(Pageable pageable) {
        log.debug("Getting damaged item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findDamagedStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getObsoleteStatuses(Pageable pageable) {
        log.debug("Getting obsolete item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findObsoleteStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getDisposedStatuses(Pageable pageable) {
        log.debug("Getting disposed item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findDisposedStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getLostStatuses(Pageable pageable) {
        log.debug("Getting lost item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findLostStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getProcurementStatuses(Pageable pageable) {
        log.debug("Getting procurement item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findProcurementStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    /**
     * Get operational statuses
     */
    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getOperationalStatuses(Pageable pageable) {
        log.debug("Getting operational item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findOperationalStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getNonOperationalStatuses(Pageable pageable) {
        log.debug("Getting non-operational item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findNonOperationalStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    /**
     * Get statuses requiring action
     */
    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getStatusesRequiringAction(Pageable pageable) {
        log.debug("Getting item statuses requiring action");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findStatusesRequiringAction(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    /**
     * Get multilingual item statuses
     */
    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getMultilingualStatuses(Pageable pageable) {
        log.debug("Getting multilingual item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findMultilingualStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    /**
     * Get priority-based statuses
     */
    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getCriticalPriorityStatuses(Pageable pageable) {
        log.debug("Getting critical priority item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findCriticalPriorityStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getHighPriorityStatuses(Pageable pageable) {
        log.debug("Getting high priority item statuses");

        Page<ItemStatus> itemStatuses = itemStatusRepository.findHighPriorityStatuses(pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ItemStatusDTO> getStatusesByPriorityLevel(String priority, Pageable pageable) {
        log.debug("Getting item statuses by priority level: {}", priority);

        Page<ItemStatus> itemStatuses = itemStatusRepository.findByPriorityLevel(priority, pageable);
        return itemStatuses.map(ItemStatusDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update item status
     */
    public ItemStatusDTO updateItemStatus(Long id, ItemStatusDTO itemStatusDTO) {
        log.info("Updating item status with ID: {}", id);

        ItemStatus existingItemStatus = getItemStatusEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(itemStatusDTO, "update");
        validateBusinessRules(itemStatusDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(itemStatusDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(itemStatusDTO, existingItemStatus);

        ItemStatus updatedItemStatus = itemStatusRepository.save(existingItemStatus);
        log.info("Successfully updated item status with ID: {}", id);

        return ItemStatusDTO.fromEntity(updatedItemStatus);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete item status
     */
    public void deleteItemStatus(Long id) {
        log.info("Deleting item status with ID: {}", id);

        ItemStatus itemStatus = getItemStatusEntityById(id);
        itemStatusRepository.delete(itemStatus);

        log.info("Successfully deleted item status with ID: {}", id);
    }

    /**
     * Delete item status by ID (direct)
     */
    public void deleteItemStatusById(Long id) {
        log.info("Deleting item status by ID: {}", id);

        if (!itemStatusRepository.existsById(id)) {
            throw new RuntimeException("Item status not found with ID: " + id);
        }

        itemStatusRepository.deleteById(id);
        log.info("Successfully deleted item status with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if item status exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return itemStatusRepository.existsById(id);
    }

    /**
     * Check if French designation exists
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return itemStatusRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countAllItemStatuses() {
        return itemStatusRepository.countAllItemStatuses();
    }

    @Transactional(readOnly = true)
    public Long countActiveStatuses() {
        return itemStatusRepository.countActiveStatuses();
    }

    @Transactional(readOnly = true)
    public Long countOperationalStatuses() {
        return itemStatusRepository.countOperationalStatuses();
    }

    @Transactional(readOnly = true)
    public Long countNonOperationalStatuses() {
        return itemStatusRepository.countNonOperationalStatuses();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(ItemStatusDTO dto, ItemStatus entity) {
        entity.setDesignationAr(dto.getDesignationAr()); // F_01
        entity.setDesignationEn(dto.getDesignationEn()); // F_02
        entity.setDesignationFr(dto.getDesignationFr()); // F_03
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ItemStatusDTO dto, String operation) {
        if (dto.getDesignationFr() == null || dto.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(ItemStatusDTO dto, String operation) {
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

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ItemStatusDTO dto, Long excludeId) {
        // Check French designation uniqueness (T_02_02_02_UK_01)
        if (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty()) {
            if (excludeId == null) {
                if (itemStatusRepository.existsByDesignationFr(dto.getDesignationFr())) {
                    throw new RuntimeException("Item status with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            } else {
                if (itemStatusRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), excludeId)) {
                    throw new RuntimeException("Another item status with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            }
        }
    }
}