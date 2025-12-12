/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-13-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.BudgetModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BudgetModification Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface BudgetModificationRepository extends JpaRepository<BudgetModification, Long> {
    
    /**
     * Find all budget modifications by year
     * @param year the year
     * @return list of budget modifications
     */
    List<BudgetModification> findByYear(Integer year);
}
