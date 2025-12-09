/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationDirectorRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Repository
 *	@Pakage		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.RealizationDirector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RealizationDirector Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Repository
public interface RealizationDirectorRepository extends JpaRepository<RealizationDirector, Long> {

    /**
     * Find realization director by French designation (F_03) - unique field
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationFr = :designationFr")
    Optional<RealizationDirector> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find realization director by Arabic designation (F_01)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationAr = :designationAr")
    Optional<RealizationDirector> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find realization director by English designation (F_02)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationEn = :designationEn")
    Optional<RealizationDirector> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if realization director exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RealizationDirector r WHERE r.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RealizationDirector r WHERE r.designationFr = :designationFr AND r.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all realization directors with pagination ordered by French designation
     */
    @Query("SELECT r FROM RealizationDirector r ORDER BY r.designationFr ASC")
    Page<RealizationDirector> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search realization directors by any designation field
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "r.designationAr LIKE %:search% OR " +
           "r.designationEn LIKE %:search% OR " +
           "r.designationFr LIKE %:search%")
    Page<RealizationDirector> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find realization directors by French designation pattern (F_03)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationFr LIKE %:pattern%")
    Page<RealizationDirector> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find realization directors by Arabic designation pattern (F_01)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationAr LIKE %:pattern%")
    Page<RealizationDirector> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find realization directors by English designation pattern (F_02)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationEn LIKE %:pattern%")
    Page<RealizationDirector> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total realization directors
     */
    @Query("SELECT COUNT(r) FROM RealizationDirector r")
    Long countAllRealizationDirectors();

    /**
     * Find realization directors that have Arabic designation
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationAr IS NOT NULL AND r.designationAr != ''")
    Page<RealizationDirector> findWithArabicDesignation(Pageable pageable);

    /**
     * Find realization directors that have English designation
     */
    @Query("SELECT r FROM RealizationDirector r WHERE r.designationEn IS NOT NULL AND r.designationEn != ''")
    Page<RealizationDirector> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual realization directors (have at least 2 designations)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationEn IS NOT NULL AND r.designationEn != '') OR " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationFr IS NOT NULL AND r.designationFr != '') OR " +
           "(r.designationEn IS NOT NULL AND r.designationEn != '' AND r.designationFr IS NOT NULL AND r.designationFr != '')")
    Page<RealizationDirector> findMultilingualRealizationDirectors(Pageable pageable);

    /**
     * Find executive directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%directeur général%' OR LOWER(r.designationFr) LIKE '%dg%' OR " +
           "LOWER(r.designationFr) LIKE '%ceo%' OR LOWER(r.designationFr) LIKE '%président%'")
    Page<RealizationDirector> findExecutiveDirectors(Pageable pageable);

    /**
     * Find technical directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%technique%' OR LOWER(r.designationFr) LIKE '%technical%' OR " +
           "LOWER(r.designationFr) LIKE '%ingénieur%' OR LOWER(r.designationFr) LIKE '%engineer%'")
    Page<RealizationDirector> findTechnicalDirectors(Pageable pageable);

    /**
     * Find project directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%projet%' OR LOWER(r.designationFr) LIKE '%project%' OR " +
           "LOWER(r.designationFr) LIKE '%programme%' OR LOWER(r.designationFr) LIKE '%program%'")
    Page<RealizationDirector> findProjectDirectors(Pageable pageable);

    /**
     * Find operations directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%opération%' OR LOWER(r.designationFr) LIKE '%operations%' OR " +
           "LOWER(r.designationFr) LIKE '%exploitation%' OR LOWER(r.designationFr) LIKE '%production%'")
    Page<RealizationDirector> findOperationsDirectors(Pageable pageable);

    /**
     * Find financial directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%financier%' OR LOWER(r.designationFr) LIKE '%financial%' OR " +
           "LOWER(r.designationFr) LIKE '%comptable%' OR LOWER(r.designationFr) LIKE '%finance%'")
    Page<RealizationDirector> findFinancialDirectors(Pageable pageable);

    /**
     * Find commercial directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%commercial%' OR LOWER(r.designationFr) LIKE '%vente%' OR " +
           "LOWER(r.designationFr) LIKE '%sales%' OR LOWER(r.designationFr) LIKE '%marketing%'")
    Page<RealizationDirector> findCommercialDirectors(Pageable pageable);

    /**
     * Find HR directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%ressources humaines%' OR LOWER(r.designationFr) LIKE '%rh%' OR " +
           "LOWER(r.designationFr) LIKE '%human resources%' OR LOWER(r.designationFr) LIKE '%hr%'")
    Page<RealizationDirector> findHRDirectors(Pageable pageable);

    /**
     * Find quality directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%qualité%' OR LOWER(r.designationFr) LIKE '%quality%' OR " +
           "LOWER(r.designationFr) LIKE '%qhse%' OR LOWER(r.designationFr) LIKE '%assurance%'")
    Page<RealizationDirector> findQualityDirectors(Pageable pageable);

    /**
     * Find regional directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%régional%' OR LOWER(r.designationFr) LIKE '%regional%' OR " +
           "LOWER(r.designationFr) LIKE '%zone%' OR LOWER(r.designationFr) LIKE '%territorial%'")
    Page<RealizationDirector> findRegionalDirectors(Pageable pageable);

    /**
     * Find administrative directors (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%administratif%' OR LOWER(r.designationFr) LIKE '%administrative%' OR " +
           "LOWER(r.designationFr) LIKE '%administration%' OR LOWER(r.designationFr) LIKE '%admin%'")
    Page<RealizationDirector> findAdministrativeDirectors(Pageable pageable);

    /**
     * Find directors by specific type pattern
     */
    @Query("SELECT r FROM RealizationDirector r WHERE LOWER(r.designationFr) LIKE %:typePattern%")
    Page<RealizationDirector> findByDirectorType(@Param("typePattern") String typePattern, Pageable pageable);

    /**
     * Find realization directors ordered by designation in specific language
     */
    @Query("SELECT r FROM RealizationDirector r ORDER BY r.designationAr ASC")
    Page<RealizationDirector> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT r FROM RealizationDirector r ORDER BY r.designationEn ASC")
    Page<RealizationDirector> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count directors by type
     */
    @Query("SELECT COUNT(r) FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%directeur général%' OR LOWER(r.designationFr) LIKE '%ceo%'")
    Long countExecutiveDirectors();

    @Query("SELECT COUNT(r) FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%technique%' OR LOWER(r.designationFr) LIKE '%technical%'")
    Long countTechnicalDirectors();

    @Query("SELECT COUNT(r) FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%projet%' OR LOWER(r.designationFr) LIKE '%project%'")
    Long countProjectDirectors();

    /**
     * Find directors with specific authority level (executive/senior)
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%directeur général%' OR LOWER(r.designationFr) LIKE '%ceo%' OR " +
           "LOWER(r.designationFr) LIKE '%président%' OR LOWER(r.designationFr) LIKE '%technique%' OR " +
           "LOWER(r.designationFr) LIKE '%financier%' OR LOWER(r.designationFr) LIKE '%opération%'")
    Page<RealizationDirector> findHighAuthorityDirectors(Pageable pageable);

    /**
     * Search directors by title or name pattern
     */
    @Query("SELECT r FROM RealizationDirector r WHERE " +
           "LOWER(r.designationFr) LIKE %:search% OR " +
           "LOWER(r.designationEn) LIKE %:search% OR " +
           "LOWER(r.designationAr) LIKE %:search%")
    Page<RealizationDirector> searchByTitleOrName(@Param("search") String search, Pageable pageable);
}
