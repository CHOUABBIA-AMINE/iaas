/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureTypeRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Repository
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.StructureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StructureType Repository
 */
@Repository
public interface StructureTypeRepository extends JpaRepository<StructureType, Long> {
    
    /**
     * Find operational structure types
     * Used by StructureTypeService.getOperationalTypes()
     */
    @Query("SELECT st FROM StructureType st WHERE st.isOperational = true")
    List<StructureType> findByIsOperationalTrue();
    
    /**
     * Find structure types by hierarchy level
     * Used by StructureTypeService.getByHierarchyLevel()
     */
    @Query("SELECT st FROM StructureType st WHERE st.hierarchyLevel = :hierarchyLevel")
    List<StructureType> findByHierarchyLevel(@Param("hierarchyLevel") Integer hierarchyLevel);
}
