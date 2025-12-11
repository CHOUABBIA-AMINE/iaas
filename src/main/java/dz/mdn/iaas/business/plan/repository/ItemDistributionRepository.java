/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.ItemDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ItemDistribution Repository
 * ItemDistribution relates PlannedItem with Structure
 */
@Repository
public interface ItemDistributionRepository extends JpaRepository<ItemDistribution, Long> {
    
    /**
     * Find distributions by planned item ID
     * Used by ItemDistributionService.getByPlannedItemId()
     */
    @Query("SELECT id FROM ItemDistribution id WHERE id.plannedItem.id = :plannedItemId")
    List<ItemDistribution> findByPlannedItemId(@Param("plannedItemId") Long plannedItemId);
    
    /**
     * Find distributions by structure ID
     * Used by ItemDistributionService.getByStructureId()
     */
    @Query("SELECT id FROM ItemDistribution id WHERE id.structure.id = :structureId")
    List<ItemDistribution> findByStructureId(@Param("structureId") Long structureId);
}
