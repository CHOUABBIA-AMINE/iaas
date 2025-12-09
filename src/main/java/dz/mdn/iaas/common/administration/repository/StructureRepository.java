/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Structure Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr, F_07=structureType, F_08=structureUp
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_07 (structureType) is required foreign key
 * F_08 (structureUp) is optional foreign key (self-reference for hierarchy)
 * F_01 (designationAr), F_02 (designationEn), F_04 (acronymAr), F_05 (acronymEn) are optional
 */
@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {

    /**
     * Find structure by French designation (F_03) - unique field
     */
    @Query("SELECT s FROM Structure s WHERE s.designationFr = :designationFr")
    Optional<Structure> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find structure by French acronym (F_06) - unique field
     */
    @Query("SELECT s FROM Structure s WHERE s.acronymFr = :acronymFr")
    Optional<Structure> findByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Find structure by Arabic designation (F_01)
     */
    @Query("SELECT s FROM Structure s WHERE s.designationAr = :designationAr")
    Optional<Structure> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find structure by English designation (F_02)
     */
    @Query("SELECT s FROM Structure s WHERE s.designationEn = :designationEn")
    Optional<Structure> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Find structure by Arabic acronym (F_04)
     */
    @Query("SELECT s FROM Structure s WHERE s.acronymAr = :acronymAr")
    Optional<Structure> findByAcronymAr(@Param("acronymAr") String acronymAr);

    /**
     * Find structure by English acronym (F_05)
     */
    @Query("SELECT s FROM Structure s WHERE s.acronymEn = :acronymEn")
    Optional<Structure> findByAcronymEn(@Param("acronymEn") String acronymEn);

    /**
     * Check if structure exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Structure s WHERE s.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if structure exists by French acronym
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Structure s WHERE s.acronymFr = :acronymFr")
    boolean existsByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Check unique constraint for updates (excluding current ID) - designation
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Structure s WHERE s.designationFr = :designationFr AND s.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Check unique constraint for updates (excluding current ID) - acronym
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Structure s WHERE s.acronymFr = :acronymFr AND s.id != :id")
    boolean existsByAcronymFrAndIdNot(@Param("acronymFr") String acronymFr, @Param("id") Long id);

    /**
     * Find structures by structure type ID (F_07)
     */
    @Query("SELECT s FROM Structure s WHERE s.structureType.id = :typeId ORDER BY s.designationFr ASC")
    Page<Structure> findByStructureTypeId(@Param("typeId") Long typeId, Pageable pageable);

    /**
     * Find structures by parent structure ID (F_08) - direct children
     */
    @Query("SELECT s FROM Structure s WHERE s.structureUp.id = :parentId ORDER BY s.designationFr ASC")
    Page<Structure> findByStructureUpId(@Param("parentId") Long parentId, Pageable pageable);

    /**
     * Find root structures (no parent - F_08 is null)
     */
    @Query("SELECT s FROM Structure s WHERE s.structureUp IS NULL ORDER BY s.designationFr ASC")
    Page<Structure> findRootStructures(Pageable pageable);

    /**
     * Find structures with children (structures that are parents)
     */
    @Query("SELECT DISTINCT s FROM Structure s WHERE s.id IN (SELECT DISTINCT p.structureUp.id FROM Structure p WHERE p.structureUp IS NOT NULL) ORDER BY s.designationFr ASC")
    Page<Structure> findStructuresWithChildren(Pageable pageable);

    /**
     * Find leaf structures (structures with no children)
     */
    @Query("SELECT s FROM Structure s WHERE s.id NOT IN (SELECT DISTINCT p.structureUp.id FROM Structure p WHERE p.structureUp IS NOT NULL) ORDER BY s.designationFr ASC")
    Page<Structure> findLeafStructures(Pageable pageable);

    /**
     * Find all structures with pagination ordered by French designation
     */
    @Query("SELECT s FROM Structure s ORDER BY s.designationFr ASC")
    Page<Structure> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Find all structures ordered by hierarchy (root structures first, then their children)
     */
    @Query("SELECT s FROM Structure s LEFT JOIN s.structureUp p ORDER BY COALESCE(p.designationFr, ''), s.designationFr ASC")
    Page<Structure> findAllOrderByHierarchy(Pageable pageable);

    /**
     * Find all structures ordered by French acronym
     */
    @Query("SELECT s FROM Structure s ORDER BY s.acronymFr ASC")
    Page<Structure> findAllOrderByAcronymFr(Pageable pageable);

    /**
     * Search structures by any designation field
     */
    @Query("SELECT s FROM Structure s WHERE " +
           "s.designationAr LIKE %:search% OR " +
           "s.designationEn LIKE %:search% OR " +
           "s.designationFr LIKE %:search%")
    Page<Structure> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Search structures by any acronym field
     */
    @Query("SELECT s FROM Structure s WHERE " +
           "s.acronymAr LIKE %:search% OR " +
           "s.acronymEn LIKE %:search% OR " +
           "s.acronymFr LIKE %:search%")
    Page<Structure> searchByAcronym(@Param("search") String search, Pageable pageable);

    /**
     * Search structures by designation or acronym
     */
    @Query("SELECT s FROM Structure s WHERE " +
           "s.designationAr LIKE %:search% OR s.designationEn LIKE %:search% OR s.designationFr LIKE %:search% OR " +
           "s.acronymAr LIKE %:search% OR s.acronymEn LIKE %:search% OR s.acronymFr LIKE %:search%")
    Page<Structure> searchByDesignationOrAcronym(@Param("search") String search, Pageable pageable);

    /**
     * Count total structures
     */
    @Query("SELECT COUNT(s) FROM Structure s")
    Long countAllStructures();

    /**
     * Count structures by structure type
     */
    @Query("SELECT COUNT(s) FROM Structure s WHERE s.structureType.id = :typeId")
    Long countByStructureTypeId(@Param("typeId") Long typeId);

    /**
     * Count direct children of a structure
     */
    @Query("SELECT COUNT(s) FROM Structure s WHERE s.structureUp.id = :parentId")
    Long countDirectChildren(@Param("parentId") Long parentId);

    /**
     * Count root structures
     */
    @Query("SELECT COUNT(s) FROM Structure s WHERE s.structureUp IS NULL")
    Long countRootStructures();

    /**
     * Find structures with join fetch for structure type and parent
     */
    @Query("SELECT s FROM Structure s LEFT JOIN FETCH s.structureType LEFT JOIN FETCH s.structureUp ORDER BY s.designationFr ASC")
    Page<Structure> findAllWithTypeAndParent(Pageable pageable);

    /**
     * Find structures by structure type designation
     */
    @Query("SELECT s FROM Structure s WHERE s.structureType.designationFr = :typeDesignation ORDER BY s.designationFr ASC")
    Page<Structure> findByStructureTypeDesignation(@Param("typeDesignation") String typeDesignation, Pageable pageable);

    /**
     * Find multilingual structures (have at least 2 designations)
     */
    @Query("SELECT s FROM Structure s WHERE " +
           "(s.designationAr IS NOT NULL AND s.designationAr != '' AND s.designationEn IS NOT NULL AND s.designationEn != '') OR " +
           "(s.designationAr IS NOT NULL AND s.designationAr != '' AND s.designationFr IS NOT NULL AND s.designationFr != '') OR " +
           "(s.designationEn IS NOT NULL AND s.designationEn != '' AND s.designationFr IS NOT NULL AND s.designationFr != '')")
    Page<Structure> findMultilingualStructures(Pageable pageable);

    /**
     * Find structures by hierarchy level (depth from root)
     */
    @Query("SELECT s FROM Structure s WHERE " +
           "(s.structureUp IS NULL) " + // Level 0 (root)
           "ORDER BY s.designationFr ASC")
    Page<Structure> findStructuresByLevel0(Pageable pageable);

    @Query("SELECT s FROM Structure s WHERE " +
           "(s.structureUp IS NOT NULL AND s.structureUp.structureUp IS NULL) " + // Level 1
           "ORDER BY s.designationFr ASC")
    Page<Structure> findStructuresByLevel1(Pageable pageable);

    @Query("SELECT s FROM Structure s WHERE " +
           "(s.structureUp IS NOT NULL AND s.structureUp.structureUp IS NOT NULL AND s.structureUp.structureUp.structureUp IS NULL) " + // Level 2
           "ORDER BY s.designationFr ASC")
    Page<Structure> findStructuresByLevel2(Pageable pageable);

    /**
     * Find all descendants of a structure (recursive - children, grandchildren, etc.)
     */
    @Query(value = "WITH RECURSIVE structure_hierarchy AS (" +
                   "SELECT id, designation_fr, acronym_fr, f_07 as structure_type_id, f_08 as parent_id, 0 as level " +
                   "FROM t_01_04_07 WHERE id = :structureId " +
                   "UNION ALL " +
                   "SELECT s.id, s.designation_fr, s.acronym_fr, s.f_07, s.f_08, sh.level + 1 " +
                   "FROM t_01_04_07 s INNER JOIN structure_hierarchy sh ON s.f_08 = sh.id " +
                   "WHERE sh.level < 10) " + // Prevent infinite recursion
                   "SELECT * FROM structure_hierarchy WHERE id != :structureId ORDER BY level, designation_fr",
           nativeQuery = true)
    List<Object[]> findAllDescendants(@Param("structureId") Long structureId);

    /**
     * Find all ancestors of a structure (recursive - parent, grandparent, etc.)
     */
    @Query(value = "WITH RECURSIVE structure_hierarchy AS (" +
                   "SELECT id, designation_fr, acronym_fr, f_07 as structure_type_id, f_08 as parent_id, 0 as level " +
                   "FROM t_01_04_07 WHERE id = :structureId " +
                   "UNION ALL " +
                   "SELECT s.id, s.designation_fr, s.acronym_fr, s.f_07, s.f_08, sh.level + 1 " +
                   "FROM t_01_04_07 s INNER JOIN structure_hierarchy sh ON s.id = sh.parent_id " +
                   "WHERE sh.level < 10) " + // Prevent infinite recursion
                   "SELECT * FROM structure_hierarchy WHERE id != :structureId ORDER BY level DESC",
           nativeQuery = true)
    List<Object[]> findAllAncestors(@Param("structureId") Long structureId);

    /**
     * Check if structure is ancestor of another structure
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Structure s " +
           "WHERE s.id = :descendantId AND " +
           "(s.structureUp.id = :ancestorId OR " +
           "s.structureUp.structureUp.id = :ancestorId OR " +
           "s.structureUp.structureUp.structureUp.id = :ancestorId)")
    boolean isAncestorOf(@Param("ancestorId") Long ancestorId, @Param("descendantId") Long descendantId);

    /**
     * Find structures that would create circular reference if set as parent
     */
    @Query("SELECT s FROM Structure s WHERE s.id = :structureId OR s.structureUp.id = :structureId OR s.structureUp.structureUp.id = :structureId")
    List<Structure> findPotentialCircularReferences(@Param("structureId") Long structureId);

    /**
     * Find structures ordered by designation in specific language
     */
    @Query("SELECT s FROM Structure s ORDER BY s.designationAr ASC")
    Page<Structure> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT s FROM Structure s ORDER BY s.designationEn ASC")
    Page<Structure> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Find structures by pattern matching
     */
    @Query("SELECT s FROM Structure s WHERE s.designationFr LIKE %:pattern%")
    Page<Structure> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT s FROM Structure s WHERE s.acronymFr LIKE %:pattern%")
    Page<Structure> findByAcronymFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count structures by hierarchy level
     */
    @Query("SELECT COUNT(s) FROM Structure s WHERE s.structureUp IS NULL") // Root level
    Long countRootLevelStructures();

    @Query("SELECT COUNT(s) FROM Structure s WHERE s.structureUp IS NOT NULL AND s.structureUp.structureUp IS NULL") // Level 1
    Long countLevel1Structures();

    @Query("SELECT COUNT(s) FROM Structure s WHERE s.structureUp IS NOT NULL AND s.structureUp.structureUp IS NOT NULL") // Level 2+
    Long countLevel2PlusStructures();

    /**
     * Find structures by command chain (structures in same hierarchy branch)
     */
    @Query("SELECT s FROM Structure s WHERE s.structureUp.id = (SELECT parent.structureUp.id FROM Structure parent WHERE parent.id = :structureId)")
    List<Structure> findSiblingStructures(@Param("structureId") Long structureId);

    /**
     * Find top-level structures by type
     */
    @Query("SELECT s FROM Structure s WHERE s.structureUp IS NULL AND s.structureType.id = :typeId ORDER BY s.designationFr ASC")
    Page<Structure> findRootStructuresByType(@Param("typeId") Long typeId, Pageable pageable);

    /**
     * Search structures with type and hierarchy context
     */
    @Query("SELECT s FROM Structure s LEFT JOIN s.structureType t LEFT JOIN s.structureUp p WHERE " +
           "(s.designationFr LIKE %:search% OR s.acronymFr LIKE %:search% OR " +
           "t.designationFr LIKE %:search% OR " +
           "p.designationFr LIKE %:search% OR p.acronymFr LIKE %:search%) " +
           "ORDER BY s.designationFr ASC")
    Page<Structure> searchWithTypeAndParentContext(@Param("search") String search, Pageable pageable);

    /**
     * Find structures that can be parents for a given structure (prevent circular references)
     */
    @Query("SELECT s FROM Structure s WHERE s.id != :structureId AND " +
           "s.id NOT IN (SELECT DISTINCT child.id FROM Structure child WHERE " +
           "child.structureUp.id = :structureId OR " +
           "child.structureUp.structureUp.id = :structureId OR " +
           "child.structureUp.structureUp.structureUp.id = :structureId) " +
           "ORDER BY s.designationFr ASC")
    List<Structure> findPotentialParents(@Param("structureId") Long structureId);

    /**
     * Find structures by organizational scope
     */
    @Query("SELECT s FROM Structure s WHERE s.structureUp IS NULL") // Organizational level
    Page<Structure> findOrganizationalLevelStructures(Pageable pageable);

    @Query("SELECT s FROM Structure s WHERE s.structureUp IS NOT NULL AND s.structureUp.structureUp IS NULL") // Departmental level
    Page<Structure> findDepartmentalLevelStructures(Pageable pageable);

    /**
     * Validate hierarchy integrity - find orphaned structures
     */
    @Query("SELECT s FROM Structure s WHERE s.structureUp IS NOT NULL AND s.structureUp.id NOT IN (SELECT id FROM Structure)")
    List<Structure> findOrphanedStructures();
}
