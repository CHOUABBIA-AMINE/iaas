/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.Item;
import dz.mdn.iaas.business.plan.model.ItemStatus;
import dz.mdn.iaas.business.plan.model.PlannedItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PlannedItem Repository
 * Methods aligned with PlannedItemService requirements
 */
@Repository
public interface PlannedItemRepository extends JpaRepository<PlannedItem, Long> {
    
    /**
     * Find planned items by item ID (already exists from previous update)
     */
    List<PlannedItem> findByItemId(Long itemId);
    
    /**
     * Find planned items by item status ID (already exists from previous update)
     */
    List<PlannedItem> findByItemStatusId(Long itemStatusId);
    
    /**
     * Search by designation (case-insensitive)
     * Used by PlannedItemService.globalSearch()
     */
    Page<PlannedItem> findByDesignationContainingIgnoreCase(String designation, Pageable pageable);
    
    /**
     * Find planned items by ItemStatus entity
     * Used by PlannedItemService.findByItemStatus()
     */
    List<PlannedItem> findByItemStatus(ItemStatus itemStatus);
    
    /**
     * Find planned items by Item entity
     * Used by PlannedItemService.findByItem()
     */
    List<PlannedItem> findByItem(Item item);
}
