/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.BudgetModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BudgetModification Repository
 * BudgetModification belongs to PlannedItem and BudgetType
 */
@Repository
public interface BudgetModificationRepository extends JpaRepository<BudgetModification, Long> {
    
    /**
     * Find budget modifications by planned item ID
     * Used by BudgetModificationService.getByPlannedItemId()
     */
    @Query("SELECT bm FROM BudgetModification bm WHERE bm.plannedItem.id = :plannedItemId")
    List<BudgetModification> findByPlannedItemId(@Param("plannedItemId") Long plannedItemId);
    
    /**
     * Find budget modifications by budget type ID
     * Used by BudgetModificationService.getByBudgetTypeId()
     */
    @Query("SELECT bm FROM BudgetModification bm WHERE bm.budgetType.id = :budgetTypeId")
    List<BudgetModification> findByBudgetTypeId(@Param("budgetTypeId") Long budgetTypeId);
}
