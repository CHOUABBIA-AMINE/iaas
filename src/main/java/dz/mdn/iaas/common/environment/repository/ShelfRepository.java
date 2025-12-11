/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Repository
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Shelf Repository
 */
@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    
    /**
     * Find shelves by room ID
     * Used by ShelfService.getByRoomId()
     */
    @Query("SELECT s FROM Shelf s WHERE s.room.id = :roomId")
    List<Shelf> findByRoomId(@Param("roomId") Long roomId);
}
