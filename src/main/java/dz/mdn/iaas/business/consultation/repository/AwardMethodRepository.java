/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AwardMethodRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Repository
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.AwardMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AwardMethod Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * Unique constraints: F_03 (designationFr), F_06 (acronymFr)
 */
@Repository
public interface AwardMethodRepository extends JpaRepository<AwardMethod, Long> {

    /**
     * Find award method by French designation (F_03) - unique field
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.designationFr = :designationFr")
    Optional<AwardMethod> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find award method by French acronym (F_06) - unique field
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.acronymFr = :acronymFr")
    Optional<AwardMethod> findByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Check unique constraints for creation
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AwardMethod a WHERE a.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AwardMethod a WHERE a.acronymFr = :acronymFr")
    boolean existsByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Check unique constraints for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AwardMethod a WHERE a.designationFr = :designationFr AND a.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AwardMethod a WHERE a.acronymFr = :acronymFr AND a.id != :id")
    boolean existsByAcronymFrAndIdNot(@Param("acronymFr") String acronymFr, @Param("id") Long id);

    /**
     * Find all award methods with pagination ordered by French acronym
     */
    @Query("SELECT a FROM AwardMethod a ORDER BY a.acronymFr ASC")
    Page<AwardMethod> findAllOrderByAcronymFr(Pageable pageable);

    /**
     * Search award methods by any designation field
     */
    @Query("SELECT a FROM AwardMethod a WHERE " +
           "a.designationAr LIKE %:search% OR " +
           "a.designationEn LIKE %:search% OR " +
           "a.designationFr LIKE %:search%")
    Page<AwardMethod> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Search award methods by any acronym field
     */
    @Query("SELECT a FROM AwardMethod a WHERE " +
           "a.acronymAr LIKE %:search% OR " +
           "a.acronymEn LIKE %:search% OR " +
           "a.acronymFr LIKE %:search%")
    Page<AwardMethod> searchByAcronym(@Param("search") String search, Pageable pageable);

    /**
     * Search award methods by any field (designations and acronyms)
     */
    @Query("SELECT a FROM AwardMethod a WHERE " +
           "a.designationAr LIKE %:search% OR " +
           "a.designationEn LIKE %:search% OR " +
           "a.designationFr LIKE %:search% OR " +
           "a.acronymAr LIKE %:search% OR " +
           "a.acronymEn LIKE %:search% OR " +
           "a.acronymFr LIKE %:search%")
    Page<AwardMethod> searchByAnyField(@Param("search") String search, Pageable pageable);

    /**
     * Find award methods by French acronym pattern
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.acronymFr LIKE %:pattern%")
    Page<AwardMethod> findByAcronymFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find award methods by Arabic designation pattern
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.designationAr LIKE %:pattern%")
    Page<AwardMethod> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find award methods by English designation pattern
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.designationEn LIKE %:pattern%")
    Page<AwardMethod> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find award methods by French designation pattern
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.designationFr LIKE %:pattern%")
    Page<AwardMethod> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find award methods by category (based on acronym patterns)
     */
    @Query("SELECT a FROM AwardMethod a WHERE " +
           "(:category = 'APPEL_OFFRES' AND UPPER(a.acronymFr) LIKE 'AO%') OR " +
           "(:category = 'CONCOURS' AND UPPER(a.acronymFr) LIKE 'CC%') OR " +
           "(:category = 'MARCHE_NEGOCIE' AND UPPER(a.acronymFr) LIKE 'GRE%') OR " +
           "(:category = 'CONSULTATION_PRIX' AND UPPER(a.acronymFr) LIKE 'CP%') OR " +
           "(:category = 'DEMANDE_UNIQUE' AND UPPER(a.acronymFr) LIKE 'DU%') OR " +
           "(:category = 'ACCORD_CADRE' AND UPPER(a.acronymFr) LIKE 'AC%')")
    Page<AwardMethod> findByCategory(@Param("category") String category, Pageable pageable);

    /**
     * Find open tender methods (Appel d'Offres)
     */
    @Query("SELECT a FROM AwardMethod a WHERE UPPER(a.acronymFr) LIKE 'AO%'")
    Page<AwardMethod> findOpenTenderMethods(Pageable pageable);

    /**
     * Find negotiated procedures (Gré à Gré)
     */
    @Query("SELECT a FROM AwardMethod a WHERE UPPER(a.acronymFr) LIKE 'GRE%'")
    Page<AwardMethod> findNegotiatedProcedures(Pageable pageable);

    /**
     * Find competitive procedures (Concours)
     */
    @Query("SELECT a FROM AwardMethod a WHERE UPPER(a.acronymFr) LIKE 'CC%'")
    Page<AwardMethod> findCompetitiveProcedures(Pageable pageable);

    /**
     * Count total award methods
     */
    @Query("SELECT COUNT(a) FROM AwardMethod a")
    Long countAllAwardMethods();

    /**
     * Find award methods ordered by specific language designation
     */
    @Query("SELECT a FROM AwardMethod a ORDER BY a.designationAr ASC")
    Page<AwardMethod> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT a FROM AwardMethod a ORDER BY a.designationEn ASC")
    Page<AwardMethod> findAllOrderByDesignationEn(Pageable pageable);

    @Query("SELECT a FROM AwardMethod a ORDER BY a.designationFr ASC")
    Page<AwardMethod> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Find award methods with specific acronym length
     */
    @Query("SELECT a FROM AwardMethod a WHERE LENGTH(a.acronymFr) = :length")
    Page<AwardMethod> findByAcronymLength(@Param("length") int length, Pageable pageable);

    /**
     * Find award methods starting with specific letters (for grouping)
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.acronymFr LIKE :prefix%")
    Page<AwardMethod> findByAcronymPrefix(@Param("prefix") String prefix, Pageable pageable);

    /**
     * Check if award method is used in consultations
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Consultation c WHERE c.awardMethod.id = :awardMethodId")
    boolean isUsedInConsultations(@Param("awardMethodId") Long awardMethodId);

    /**
     * Count consultations using this award method
     */
    @Query("SELECT COUNT(c) FROM Consultation c WHERE c.awardMethod.id = :awardMethodId")
    Long countConsultationsUsingAwardMethod(@Param("awardMethodId") Long awardMethodId);

    /**
     * Find most used award methods
     */
    @Query("SELECT a FROM AwardMethod a LEFT JOIN Consultation c ON c.awardMethod = a " +
           "GROUP BY a ORDER BY COUNT(c) DESC")
    Page<AwardMethod> findMostUsedAwardMethods(Pageable pageable);

    /**
     * Find unused award methods
     */
    @Query("SELECT a FROM AwardMethod a WHERE a.id NOT IN " +
           "(SELECT DISTINCT c.awardMethod.id FROM Consultation c WHERE c.awardMethod IS NOT NULL)")
    Page<AwardMethod> findUnusedAwardMethods(Pageable pageable);

    /**
     * Check for potential duplicate entries (same French acronym but different case)
     */
    @Query("SELECT a FROM AwardMethod a WHERE UPPER(a.acronymFr) = UPPER(:acronymFr)")
    Page<AwardMethod> findPotentialDuplicatesByAcronymFr(@Param("acronymFr") String acronymFr, Pageable pageable);

    /**
     * Find award methods by multilingual support
     */
    @Query("SELECT a FROM AwardMethod a WHERE " +
           "a.designationAr IS NOT NULL AND a.designationAr != '' AND " +
           "a.designationEn IS NOT NULL AND a.designationEn != '' AND " +
           "a.designationFr IS NOT NULL AND a.designationFr != ''")
    Page<AwardMethod> findMultilingualAwardMethods(Pageable pageable);

    /**
     * Find award methods with missing translations
     */
    @Query("SELECT a FROM AwardMethod a WHERE " +
           "a.designationAr IS NULL OR a.designationAr = '' OR " +
           "a.designationEn IS NULL OR a.designationEn = ''")
    Page<AwardMethod> findAwardMethodsWithMissingTranslations(Pageable pageable);

    /**
     * Statistics: Count award methods by category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN UPPER(a.acronymFr) LIKE 'AO%' THEN 'APPEL_OFFRES' " +
           "WHEN UPPER(a.acronymFr) LIKE 'CC%' THEN 'CONCOURS' " +
           "WHEN UPPER(a.acronymFr) LIKE 'GRE%' THEN 'MARCHE_NEGOCIE' " +
           "WHEN UPPER(a.acronymFr) LIKE 'CP%' THEN 'CONSULTATION_PRIX' " +
           "WHEN UPPER(a.acronymFr) LIKE 'DU%' THEN 'DEMANDE_UNIQUE' " +
           "WHEN UPPER(a.acronymFr) LIKE 'AC%' THEN 'ACCORD_CADRE' " +
           "ELSE 'OTHER' END as category, COUNT(a) " +
           "FROM AwardMethod a GROUP BY " +
           "CASE " +
           "WHEN UPPER(a.acronymFr) LIKE 'AO%' THEN 'APPEL_OFFRES' " +
           "WHEN UPPER(a.acronymFr) LIKE 'CC%' THEN 'CONCOURS' " +
           "WHEN UPPER(a.acronymFr) LIKE 'GRE%' THEN 'MARCHE_NEGOCIE' " +
           "WHEN UPPER(a.acronymFr) LIKE 'CP%' THEN 'CONSULTATION_PRIX' " +
           "WHEN UPPER(a.acronymFr) LIKE 'DU%' THEN 'DEMANDE_UNIQUE' " +
           "WHEN UPPER(a.acronymFr) LIKE 'AC%' THEN 'ACCORD_CADRE' " +
           "ELSE 'OTHER' END")
    java.util.List<Object[]> countAwardMethodsByCategory();
}