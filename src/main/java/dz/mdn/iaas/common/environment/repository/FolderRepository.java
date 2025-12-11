/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FolderRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Repository
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Folder Repository
 */
@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    
    /**
     * Find folders by archive box ID
     * Used by FolderService.getByArchiveBoxId()
     */
    @Query("SELECT f FROM Folder f WHERE f.archiveBox.id = :archiveBoxId")
    List<Folder> findByArchiveBoxId(@Param("archiveBoxId") Long archiveBoxId);
}
