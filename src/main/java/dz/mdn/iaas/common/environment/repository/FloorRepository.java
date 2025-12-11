/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FloorRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Repository
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Floor Repository
 */
@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    
    /**
     * Find floors by bloc ID
     * Used by FloorService.getByBlocId()
     */
    @Query("SELECT f FROM Floor f WHERE f.bloc.id = :blocId")
    List<Floor> findByBlocId(@Param("blocId") Long blocId);
}
