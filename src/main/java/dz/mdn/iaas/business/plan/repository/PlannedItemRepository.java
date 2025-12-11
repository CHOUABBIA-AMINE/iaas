/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: PlannedItemRepository
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
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
 * Provides database access for PlannedItem entities
 */
@Repository
public interface PlannedItemRepository extends JpaRepository<PlannedItem, Long> {
    
    /**
     * Search planned items by designation (case-insensitive)
     */
    Page<PlannedItem> findByDesignationContainingIgnoreCase(String designation, Pageable pageable);
    
    /**
     * Find planned items by item status
     */
    List<PlannedItem> findByItemStatus(ItemStatus itemStatus);
    
    /**
     * Find planned items by item
     */
    List<PlannedItem> findByItem(Item item);
}
