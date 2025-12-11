/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemRepository
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Item Repository
 * Item belongs to Rubric (many-to-one)
 * Item can have parent Item (hierarchical structure)
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    /**
     * Find items by rubric ID
     * Used by ItemService.getByRubricId()
     */
    @Query("SELECT i FROM Item i WHERE i.rubric.id = :rubricId")
    List<Item> findByRubricId(@Param("rubricId") Long rubricId);
    
    /**
     * Find items by parent item ID
     * Used by ItemService.getByParentItemId()
     */
    @Query("SELECT i FROM Item i WHERE i.parentItem.id = :parentItemId")
    List<Item> findByParentItemId(@Param("parentItemId") Long parentItemId);
    
    /**
     * Find root items (no parent)
     * Used by ItemService.getRootItems()
     */
    @Query("SELECT i FROM Item i WHERE i.parentItem IS NULL")
    List<Item> findRootItems();
}
