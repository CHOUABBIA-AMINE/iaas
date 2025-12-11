/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBoxRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Repository
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.ArchiveBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArchiveBox Repository
 */
@Repository
public interface ArchiveBoxRepository extends JpaRepository<ArchiveBox, Long> {
    
    /**
     * Find archive boxes by shelf floor ID
     * Used by ArchiveBoxService.getByShelfFloorId()
     */
    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelfFloor.id = :shelfFloorId")
    List<ArchiveBox> findByShelfFloorId(@Param("shelfFloorId") Long shelfFloorId);
}
