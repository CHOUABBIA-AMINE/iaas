/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryCategoryRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.MilitaryCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Military Category Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@Repository
public interface MilitaryCategoryRepository extends JpaRepository<MilitaryCategory, Long> {

    /**
     * Find military category by French designation (F_03) - unique field
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE mc.designationFr = :designationFr")
    Optional<MilitaryCategory> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if military category exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(mc) > 0 THEN true ELSE false END FROM MilitaryCategory mc WHERE mc.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(mc) > 0 THEN true ELSE false END FROM MilitaryCategory mc WHERE mc.designationFr = :designationFr AND mc.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all military categories with pagination ordered by French designation
     */
    @Query("SELECT mc FROM MilitaryCategory mc ORDER BY mc.designationFr ASC")
    Page<MilitaryCategory> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search military categories by any designation field
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "mc.designationAr LIKE %:search% OR " +
           "mc.designationEn LIKE %:search% OR " +
           "mc.designationFr LIKE %:search% ")
    Page<MilitaryCategory> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find military categories by French designation pattern (F_03)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE mc.designationFr LIKE %:pattern%")
    Page<MilitaryCategory> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total military categories
     */
    @Query("SELECT COUNT(mc) FROM MilitaryCategory mc")
    Long countAllMilitaryCategories();

    /**
     * Find military categories that have Arabic designation
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE mc.designationAr IS NOT NULL AND mc.designationAr != ''")
    Page<MilitaryCategory> findWithArabicDesignation(Pageable pageable);

    /**
     * Find military categories that have English designation
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE mc.designationEn IS NOT NULL AND mc.designationEn != ''")
    Page<MilitaryCategory> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual military categories (have at least 2 designations)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "(mc.designationAr IS NOT NULL AND mc.designationAr != '' AND mc.designationEn IS NOT NULL AND mc.designationEn != '') OR " +
           "(mc.designationAr IS NOT NULL AND mc.designationAr != '' AND mc.designationFr IS NOT NULL AND mc.designationFr != '') OR " +
           "(mc.designationEn IS NOT NULL AND mc.designationEn != '' AND mc.designationFr IS NOT NULL AND mc.designationFr != '')")
    Page<MilitaryCategory> findMultilingualMilitaryCategories(Pageable pageable);

    /**
     * Find army categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%armée%' AND LOWER(mc.designationFr) LIKE '%terre%'")
    Page<MilitaryCategory> findArmyCategories(Pageable pageable);

    /**
     * Find navy categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%marine%' OR LOWER(mc.designationFr) LIKE '%naval%'")
    Page<MilitaryCategory> findNavyCategories(Pageable pageable);

    /**
     * Find air force categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%air%' OR LOWER(mc.designationFr) LIKE '%aérienne%'")
    Page<MilitaryCategory> findAirForceCategories(Pageable pageable);

    /**
     * Find gendarmerie categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%gendarmerie%'")
    Page<MilitaryCategory> findGendarmerieCategories(Pageable pageable);

    /**
     * Find security categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%sécurité%' OR LOWER(mc.designationFr) LIKE '%garde%'")
    Page<MilitaryCategory> findSecurityCategories(Pageable pageable);

    /**
     * Find support categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%logistique%' OR LOWER(mc.designationFr) LIKE '%médical%' OR " +
           "LOWER(mc.designationFr) LIKE '%communication%' OR LOWER(mc.designationFr) LIKE '%transmission%'")
    Page<MilitaryCategory> findSupportCategories(Pageable pageable);

    /**
     * Find main service branches (Army, Navy, Air Force)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "(LOWER(mc.designationFr) LIKE '%armée%' AND LOWER(mc.designationFr) LIKE '%terre%') OR " +
           "LOWER(mc.designationFr) LIKE '%marine%' OR LOWER(mc.designationFr) LIKE '%naval%' OR " +
           "LOWER(mc.designationFr) LIKE '%air%' OR LOWER(mc.designationFr) LIKE '%aérienne%'")
    Page<MilitaryCategory> findMainServiceBranches(Pageable pageable);

    /**
     * Find military categories ordered by designation in specific language
     */
    @Query("SELECT mc FROM MilitaryCategory mc ORDER BY mc.designationAr ASC")
    Page<MilitaryCategory> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT mc FROM MilitaryCategory mc ORDER BY mc.designationEn ASC")
    Page<MilitaryCategory> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count military categories by type
     */
    @Query("SELECT COUNT(mc) FROM MilitaryCategory mc WHERE " +
           "(LOWER(mc.designationFr) LIKE '%armée%' AND LOWER(mc.designationFr) LIKE '%terre%') OR " +
           "LOWER(mc.designationFr) LIKE '%marine%' OR LOWER(mc.designationFr) LIKE '%naval%' OR " +
           "LOWER(mc.designationFr) LIKE '%air%' OR LOWER(mc.designationFr) LIKE '%aérienne%'")
    Long countMainServiceBranches();

    @Query("SELECT COUNT(mc) FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%gendarmerie%' OR " +
           "LOWER(mc.designationFr) LIKE '%sécurité%' OR LOWER(mc.designationFr) LIKE '%garde%'")
    Long countSecurityServices();

    @Query("SELECT COUNT(mc) FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%logistique%' OR LOWER(mc.designationFr) LIKE '%médical%' OR " +
           "LOWER(mc.designationFr) LIKE '%communication%' OR LOWER(mc.designationFr) LIKE '%transmission%'")
    Long countSupportServices();

    /**
     * Count multilingual military categories
     */
    @Query("SELECT COUNT(mc) FROM MilitaryCategory mc WHERE " +
           "(mc.designationAr IS NOT NULL AND mc.designationAr != '' AND mc.designationEn IS NOT NULL AND mc.designationEn != '') OR " +
           "(mc.designationAr IS NOT NULL AND mc.designationAr != '' AND mc.designationFr IS NOT NULL AND mc.designationFr != '') OR " +
           "(mc.designationEn IS NOT NULL AND mc.designationEn != '' AND mc.designationFr IS NOT NULL AND mc.designationFr != '')")
    Long countMultilingualMilitaryCategories();

    /**
     * Find military categories missing translations
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "(mc.designationAr IS NULL OR mc.designationAr = '') OR " +
           "(mc.designationEn IS NULL OR mc.designationEn = '') ")
    Page<MilitaryCategory> findMissingTranslations(Pageable pageable);

    /**
     * Find military categories by Arabic designation
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE mc.designationAr = :designationAr")
    Optional<MilitaryCategory> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find military categories by English designation
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE mc.designationEn = :designationEn")
    Optional<MilitaryCategory> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Find intelligence categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%renseignement%' OR LOWER(mc.designationFr) LIKE '%intelligence%'")
    Page<MilitaryCategory> findIntelligenceCategories(Pageable pageable);

    /**
     * Find medical categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%médical%' OR LOWER(mc.designationFr) LIKE '%santé%'")
    Page<MilitaryCategory> findMedicalCategories(Pageable pageable);

    /**
     * Find logistics categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%logistique%' OR LOWER(mc.designationFr) LIKE '%approvisionnement%'")
    Page<MilitaryCategory> findLogisticsCategories(Pageable pageable);

    /**
     * Find communications categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%communication%' OR LOWER(mc.designationFr) LIKE '%transmission%'")
    Page<MilitaryCategory> findCommunicationsCategories(Pageable pageable);

    /**
     * Find republican guard categories (based on French designation patterns)
     */
    @Query("SELECT mc FROM MilitaryCategory mc WHERE " +
           "LOWER(mc.designationFr) LIKE '%garde%' AND LOWER(mc.designationFr) LIKE '%républicaine%'")
    Page<MilitaryCategory> findRepublicanGuardCategories(Pageable pageable);
}