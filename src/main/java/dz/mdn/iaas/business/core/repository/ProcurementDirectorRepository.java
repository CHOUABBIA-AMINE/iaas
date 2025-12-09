/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementDirectorRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Repository
 *	@Pakage		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.ProcurementDirector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ProcurementDirector Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Repository
public interface ProcurementDirectorRepository extends JpaRepository<ProcurementDirector, Long> {

    /**
     * Find procurement director by French designation (F_03) - unique field
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationFr = :designationFr")
    Optional<ProcurementDirector> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find procurement director by Arabic designation (F_01)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationAr = :designationAr")
    Optional<ProcurementDirector> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find procurement director by English designation (F_02)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationEn = :designationEn")
    Optional<ProcurementDirector> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if procurement director exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ProcurementDirector r WHERE r.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ProcurementDirector r WHERE r.designationFr = :designationFr AND r.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all procurement directors with pagination ordered by French designation
     */
    @Query("SELECT r FROM ProcurementDirector r ORDER BY r.designationFr ASC")
    Page<ProcurementDirector> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search procurement directors by any designation field
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "r.designationAr LIKE %:search% OR " +
           "r.designationEn LIKE %:search% OR " +
           "r.designationFr LIKE %:search%")
    Page<ProcurementDirector> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find procurement directors by French designation pattern (F_03)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationFr LIKE %:pattern%")
    Page<ProcurementDirector> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find procurement directors by Arabic designation pattern (F_01)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationAr LIKE %:pattern%")
    Page<ProcurementDirector> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find procurement directors by English designation pattern (F_02)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationEn LIKE %:pattern%")
    Page<ProcurementDirector> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total procurement directors
     */
    @Query("SELECT COUNT(r) FROM ProcurementDirector r")
    Long countAllProcurementDirectors();

    /**
     * Find procurement directors that have Arabic designation
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationAr IS NOT NULL AND r.designationAr != ''")
    Page<ProcurementDirector> findWithArabicDesignation(Pageable pageable);

    /**
     * Find procurement directors that have English designation
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE r.designationEn IS NOT NULL AND r.designationEn != ''")
    Page<ProcurementDirector> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual procurement directors (have at least 2 designations)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationEn IS NOT NULL AND r.designationEn != '') OR " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationFr IS NOT NULL AND r.designationFr != '') OR " +
           "(r.designationEn IS NOT NULL AND r.designationEn != '' AND r.designationFr IS NOT NULL AND r.designationFr != '')")
    Page<ProcurementDirector> findMultilingualProcurementDirectors(Pageable pageable);

    /**
     * Find executive directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%directeur général%' OR LOWER(r.designationFr) LIKE '%dg%' OR " +
           "LOWER(r.designationFr) LIKE '%ceo%' OR LOWER(r.designationFr) LIKE '%président%'")
    Page<ProcurementDirector> findExecutiveDirectors(Pageable pageable);

    /**
     * Find technical directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%technique%' OR LOWER(r.designationFr) LIKE '%technical%' OR " +
           "LOWER(r.designationFr) LIKE '%ingénieur%' OR LOWER(r.designationFr) LIKE '%engineer%'")
    Page<ProcurementDirector> findTechnicalDirectors(Pageable pageable);

    /**
     * Find project directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%projet%' OR LOWER(r.designationFr) LIKE '%project%' OR " +
           "LOWER(r.designationFr) LIKE '%programme%' OR LOWER(r.designationFr) LIKE '%program%'")
    Page<ProcurementDirector> findProjectDirectors(Pageable pageable);

    /**
     * Find operations directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%opération%' OR LOWER(r.designationFr) LIKE '%operations%' OR " +
           "LOWER(r.designationFr) LIKE '%exploitation%' OR LOWER(r.designationFr) LIKE '%production%'")
    Page<ProcurementDirector> findOperationsDirectors(Pageable pageable);

    /**
     * Find financial directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%financier%' OR LOWER(r.designationFr) LIKE '%financial%' OR " +
           "LOWER(r.designationFr) LIKE '%comptable%' OR LOWER(r.designationFr) LIKE '%finance%'")
    Page<ProcurementDirector> findFinancialDirectors(Pageable pageable);

    /**
     * Find commercial directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%commercial%' OR LOWER(r.designationFr) LIKE '%vente%' OR " +
           "LOWER(r.designationFr) LIKE '%sales%' OR LOWER(r.designationFr) LIKE '%marketing%'")
    Page<ProcurementDirector> findCommercialDirectors(Pageable pageable);

    /**
     * Find HR directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%ressources humaines%' OR LOWER(r.designationFr) LIKE '%rh%' OR " +
           "LOWER(r.designationFr) LIKE '%human resources%' OR LOWER(r.designationFr) LIKE '%hr%'")
    Page<ProcurementDirector> findHRDirectors(Pageable pageable);

    /**
     * Find quality directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%qualité%' OR LOWER(r.designationFr) LIKE '%quality%' OR " +
           "LOWER(r.designationFr) LIKE '%qhse%' OR LOWER(r.designationFr) LIKE '%assurance%'")
    Page<ProcurementDirector> findQualityDirectors(Pageable pageable);

    /**
     * Find regional directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%régional%' OR LOWER(r.designationFr) LIKE '%regional%' OR " +
           "LOWER(r.designationFr) LIKE '%zone%' OR LOWER(r.designationFr) LIKE '%territorial%'")
    Page<ProcurementDirector> findRegionalDirectors(Pageable pageable);

    /**
     * Find administrative directors (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%administratif%' OR LOWER(r.designationFr) LIKE '%administrative%' OR " +
           "LOWER(r.designationFr) LIKE '%administration%' OR LOWER(r.designationFr) LIKE '%admin%'")
    Page<ProcurementDirector> findAdministrativeDirectors(Pageable pageable);

    /**
     * Find directors by specific type pattern
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE LOWER(r.designationFr) LIKE %:typePattern%")
    Page<ProcurementDirector> findByDirectorType(@Param("typePattern") String typePattern, Pageable pageable);

    /**
     * Find procurement directors ordered by designation in specific language
     */
    @Query("SELECT r FROM ProcurementDirector r ORDER BY r.designationAr ASC")
    Page<ProcurementDirector> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT r FROM ProcurementDirector r ORDER BY r.designationEn ASC")
    Page<ProcurementDirector> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count directors by type
     */
    @Query("SELECT COUNT(r) FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%directeur général%' OR LOWER(r.designationFr) LIKE '%ceo%'")
    Long countExecutiveDirectors();

    @Query("SELECT COUNT(r) FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%technique%' OR LOWER(r.designationFr) LIKE '%technical%'")
    Long countTechnicalDirectors();

    @Query("SELECT COUNT(r) FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%projet%' OR LOWER(r.designationFr) LIKE '%project%'")
    Long countProjectDirectors();

    /**
     * Find directors with specific authority level (executive/senior)
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE '%directeur général%' OR LOWER(r.designationFr) LIKE '%ceo%' OR " +
           "LOWER(r.designationFr) LIKE '%président%' OR LOWER(r.designationFr) LIKE '%technique%' OR " +
           "LOWER(r.designationFr) LIKE '%financier%' OR LOWER(r.designationFr) LIKE '%opération%'")
    Page<ProcurementDirector> findHighAuthorityDirectors(Pageable pageable);

    /**
     * Search directors by title or name pattern
     */
    @Query("SELECT r FROM ProcurementDirector r WHERE " +
           "LOWER(r.designationFr) LIKE %:search% OR " +
           "LOWER(r.designationEn) LIKE %:search% OR " +
           "LOWER(r.designationAr) LIKE %:search%")
    Page<ProcurementDirector> searchByTitleOrName(@Param("search") String search, Pageable pageable);
}
