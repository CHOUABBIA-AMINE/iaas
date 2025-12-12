/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetTypeRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-13-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.BudgetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BudgetType Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface BudgetTypeRepository extends JpaRepository<BudgetType, Long> {
    
    /**
     * Find all budget types by category
     * @param category the category
     * @return list of budget types
     */
    List<BudgetType> findByCategory(String category);
}
