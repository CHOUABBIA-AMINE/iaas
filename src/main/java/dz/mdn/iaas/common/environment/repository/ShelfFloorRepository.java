/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Repository
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.ShelfFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShelfFloor Repository
 */
@Repository
public interface ShelfFloorRepository extends JpaRepository<ShelfFloor, Long> {
    
    /**
     * Find shelf floors by shelf ID
     * Used by ShelfFloorService.getByShelfId()
     */
    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.shelf.id = :shelfId")
    List<ShelfFloor> findByShelfId(@Param("shelfId") Long shelfId);
}
