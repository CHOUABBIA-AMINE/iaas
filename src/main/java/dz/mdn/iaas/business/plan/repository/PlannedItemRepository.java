/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-13-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.PlannedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PlannedItem Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface PlannedItemRepository extends JpaRepository<PlannedItem, Long> {
    
    /**
     * Find all planned items by year
     * @param year the year
     * @return list of planned items
     */
    List<PlannedItem> findByYear(Integer year);
    
    /**
     * Find all planned items by structure ID
     * @param structureId the structure ID
     * @return list of planned items
     */
    List<PlannedItem> findByStructureId(Long structureId);
}
