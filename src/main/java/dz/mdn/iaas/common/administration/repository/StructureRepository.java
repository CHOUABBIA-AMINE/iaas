/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Repository
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Structure Repository
 */
@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
    
    /**
     * Find structures by structure type ID
     * Used by StructureService.getByStructureTypeId()
     */
    @Query("SELECT s FROM Structure s WHERE s.structureType.id = :structureTypeId")
    List<Structure> findByStructureTypeId(@Param("structureTypeId") Long structureTypeId);
    
    /**
     * Find structures by parent structure ID
     * Used by StructureService.getByParentStructureId()
     */
    @Query("SELECT s FROM Structure s WHERE s.parentStructure.id = :parentStructureId")
    List<Structure> findByParentStructureId(@Param("parentStructureId") Long parentStructureId);
    
    /**
     * Find root structures (no parent)
     * Used by StructureService.getRootStructures()
     */
    @Query("SELECT s FROM Structure s WHERE s.parentStructure IS NULL")
    List<Structure> findByParentStructureIsNull();
}
