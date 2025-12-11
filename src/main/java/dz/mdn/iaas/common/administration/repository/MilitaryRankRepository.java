/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRankRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Repository
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.MilitaryRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MilitaryRank Repository
 */
@Repository
public interface MilitaryRankRepository extends JpaRepository<MilitaryRank, Long> {
    
    /**
     * Find military ranks by category ID
     * Used by MilitaryRankService.getByCategoryId()
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.category.id = :categoryId")
    List<MilitaryRank> findByCategoryId(@Param("categoryId") Long categoryId);
    
    /**
     * Find military ranks by hierarchy level
     * Used by MilitaryRankService.getByHierarchyLevel()
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.hierarchyLevel = :hierarchyLevel")
    List<MilitaryRank> findByHierarchyLevel(@Param("hierarchyLevel") Integer hierarchyLevel);
}
