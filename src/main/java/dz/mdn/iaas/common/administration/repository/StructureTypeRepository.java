/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureTypeRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.StructureType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * StructureType Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr), F_02 (designationEn) are optional
 */
@Repository
public interface StructureTypeRepository extends JpaRepository<StructureType, Long> {

    /**
     * Find structure type by French designation (F_03) - unique field
     */
    @Query("SELECT s FROM StructureType s WHERE s.designationFr = :designationFr")
    Optional<StructureType> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find structure type by Arabic designation (F_01)
     */
    @Query("SELECT s FROM StructureType s WHERE s.designationAr = :designationAr")
    Optional<StructureType> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find structure type by English designation (F_02)
     */
    @Query("SELECT s FROM StructureType s WHERE s.designationEn = :designationEn")
    Optional<StructureType> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if structure type exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM StructureType s WHERE s.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID) - designation
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM StructureType s WHERE s.designationFr = :designationFr AND s.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all structure types with pagination ordered by French designation
     */
    @Query("SELECT s FROM StructureType s ORDER BY s.designationFr ASC")
    Page<StructureType> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search structure types by any designation field
     */
    @Query("SELECT s FROM StructureType s WHERE " +
           "s.designationAr LIKE %:search% OR " +
           "s.designationEn LIKE %:search% OR " +
           "s.designationFr LIKE %:search%")
    Page<StructureType> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find structure types by French designation pattern (F_03)
     */
    @Query("SELECT s FROM StructureType s WHERE s.designationFr LIKE %:pattern%")
    Page<StructureType> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total structure types
     */
    @Query("SELECT COUNT(s) FROM StructureType s")
    Long countAllStructureTypes();

    /**
     * Find structure types that have Arabic designation
     */
    @Query("SELECT s FROM StructureType s WHERE s.designationAr IS NOT NULL AND s.designationAr != ''")
    Page<StructureType> findWithArabicDesignation(Pageable pageable);

    /**
     * Find structure types that have English designation
     */
    @Query("SELECT s FROM StructureType s WHERE s.designationEn IS NOT NULL AND s.designationEn != ''")
    Page<StructureType> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual structure types (have at least 2 designations)
     */
    @Query("SELECT s FROM StructureType s WHERE " +
           "(s.designationAr IS NOT NULL AND s.designationAr != '' AND s.designationEn IS NOT NULL AND s.designationEn != '') OR " +
           "(s.designationAr IS NOT NULL AND s.designationAr != '' AND s.designationFr IS NOT NULL AND s.designationFr != '') OR " +
           "(s.designationEn IS NOT NULL AND s.designationEn != '' AND s.designationFr IS NOT NULL AND s.designationFr != '')")
    Page<StructureType> findMultilingualStructureTypes(Pageable pageable);

    /**
     * Find structure types ordered by designation in specific language
     */
    @Query("SELECT s FROM StructureType s ORDER BY s.designationAr ASC")
    Page<StructureType> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT s FROM StructureType s ORDER BY s.designationEn ASC")
    Page<StructureType> findAllOrderByDesignationEn(Pageable pageable);
	
}
