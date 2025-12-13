/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoomRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Repository
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Room Repository
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
    /**
     * Find rooms by bloc ID
     * Used by RoomService.getByBlocId()
     */
    @Query("SELECT r FROM Room r WHERE r.bloc.id = :blocId")
    List<Room> findByBlocId(@Param("blocId") Long blocId);
    
    /**
     * Find rooms by floor ID
     * Used by RoomService.getByFloorId()
     */
    @Query("SELECT r FROM Room r WHERE r.floor.id = :floorId")
    List<Room> findByFloorId(@Param("floorId") Long floorId);
}
