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

import dz.mdn.iaas.business.plan.model.PlannedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PlannedItem Repository
 * PlannedItem belongs to Item and ItemStatus
 */
@Repository
public interface PlannedItemRepository extends JpaRepository<PlannedItem, Long> {
    
    /**
     * Find planned items by item ID
     * Used by PlannedItemService.getByItemId()
     */
    @Query("SELECT pi FROM PlannedItem pi WHERE pi.item.id = :itemId")
    List<PlannedItem> findByItemId(@Param("itemId") Long itemId);
    
    /**
     * Find planned items by item status ID
     * Used by PlannedItemService.getByItemStatusId()
     */
    @Query("SELECT pi FROM PlannedItem pi WHERE pi.itemStatus.id = :itemStatusId")
    List<PlannedItem> findByItemStatusId(@Param("itemStatusId") Long itemStatusId);
}
