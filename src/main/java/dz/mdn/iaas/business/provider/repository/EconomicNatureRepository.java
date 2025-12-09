/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicNatureRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.EconomicNature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Economic Nature Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_01, F_02, F_04, F_05 are optional
 */
@Repository
public interface EconomicNatureRepository extends JpaRepository<EconomicNature, Long> {

    /**
     * Find economic nature by French designation (F_03) - unique field
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.designationFr = :designationFr")
    Optional<EconomicNature> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find economic nature by French acronym (F_06) - unique field
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.acronymFr = :acronymFr")
    Optional<EconomicNature> findByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Check if economic nature exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(en) > 0 THEN true ELSE false END FROM EconomicNature en WHERE en.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if economic nature exists by French acronym
     */
    @Query("SELECT CASE WHEN COUNT(en) > 0 THEN true ELSE false END FROM EconomicNature en WHERE en.acronymFr = :acronymFr")
    boolean existsByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Check unique constraints for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(en) > 0 THEN true ELSE false END FROM EconomicNature en WHERE en.designationFr = :designationFr AND en.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(en) > 0 THEN true ELSE false END FROM EconomicNature en WHERE en.acronymFr = :acronymFr AND en.id != :id")
    boolean existsByAcronymFrAndIdNot(@Param("acronymFr") String acronymFr, @Param("id") Long id);

    /**
     * Find all economic natures with pagination ordered by French designation
     */
    @Query("SELECT en FROM EconomicNature en ORDER BY en.designationFr ASC")
    Page<EconomicNature> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Find all economic natures ordered by French acronym
     */
    @Query("SELECT en FROM EconomicNature en ORDER BY en.acronymFr ASC")
    Page<EconomicNature> findAllOrderByAcronymFr(Pageable pageable);

    /**
     * Search economic natures by any designation or acronym field
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "en.designationAr LIKE %:search% OR " +
           "en.designationEn LIKE %:search% OR " +
           "en.designationFr LIKE %:search% OR " +
           "en.acronymAr LIKE %:search% OR " +
           "en.acronymEn LIKE %:search% OR " +
           "en.acronymFr LIKE %:search%")
    Page<EconomicNature> searchByDesignationOrAcronym(@Param("search") String search, Pageable pageable);

    /**
     * Find economic natures by French designation pattern (F_03)
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.designationFr LIKE %:pattern%")
    Page<EconomicNature> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find economic natures by French acronym pattern (F_06)
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.acronymFr LIKE %:pattern%")
    Page<EconomicNature> findByAcronymFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total economic natures
     */
    @Query("SELECT COUNT(en) FROM EconomicNature en")
    Long countAllEconomicNatures();

    /**
     * Find economic natures that have Arabic designation
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.designationAr IS NOT NULL AND en.designationAr != ''")
    Page<EconomicNature> findWithArabicDesignation(Pageable pageable);

    /**
     * Find economic natures that have English designation
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.designationEn IS NOT NULL AND en.designationEn != ''")
    Page<EconomicNature> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual economic natures (have at least 2 designations)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "(en.designationAr IS NOT NULL AND en.designationAr != '' AND en.designationEn IS NOT NULL AND en.designationEn != '') OR " +
           "(en.designationAr IS NOT NULL AND en.designationAr != '' AND en.designationFr IS NOT NULL AND en.designationFr != '') OR " +
           "(en.designationEn IS NOT NULL AND en.designationEn != '' AND en.designationFr IS NOT NULL AND en.designationFr != '')")
    Page<EconomicNature> findMultilingualEconomicNatures(Pageable pageable);

    /**
     * Find public sector economic natures (based on French designation patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%public%' OR LOWER(en.designationFr) LIKE '%état%' OR " +
           "LOWER(en.designationFr) LIKE '%administration%' OR LOWER(en.acronymFr) LIKE '%epa%' OR " +
           "LOWER(en.acronymFr) LIKE '%epic%'")
    Page<EconomicNature> findPublicSectorNatures(Pageable pageable);

    /**
     * Find private sector economic natures (based on French designation patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%privé%' OR LOWER(en.designationFr) LIKE '%particulier%' OR " +
           "LOWER(en.acronymFr) IN ('sarl', 'spa', 'eurl', 'snc', 'scs')")
    Page<EconomicNature> findPrivateSectorNatures(Pageable pageable);

    /**
     * Find company economic natures (based on French designation patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%société%' OR LOWER(en.designationFr) LIKE '%entreprise%' OR " +
           "LOWER(en.acronymFr) IN ('sarl', 'spa', 'eurl', 'snc', 'scs')")
    Page<EconomicNature> findCompanyNatures(Pageable pageable);

    /**
     * Find cooperative economic natures (based on French designation patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%coopératif%' OR LOWER(en.designationFr) LIKE '%coopérative%' OR " +
           "LOWER(en.acronymFr) LIKE '%coop%'")
    Page<EconomicNature> findCooperativeNatures(Pageable pageable);

    /**
     * Find association economic natures (based on French designation patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%association%' OR LOWER(en.designationFr) LIKE '%ong%' OR " +
           "LOWER(en.designationFr) LIKE '%but non lucratif%'")
    Page<EconomicNature> findAssociationNatures(Pageable pageable);

    /**
     * Find individual enterprise economic natures (based on French designation patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%individuel%' OR LOWER(en.designationFr) LIKE '%personnel%'")
    Page<EconomicNature> findIndividualEnterpriseNatures(Pageable pageable);

    /**
     * Find foreign entity economic natures (based on French designation patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%étranger%' OR LOWER(en.designationFr) LIKE '%international%' OR " +
           "LOWER(en.designationFr) LIKE '%multinational%'")
    Page<EconomicNature> findForeignEntityNatures(Pageable pageable);

    /**
     * Find limited liability economic natures (based on acronym patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.acronymFr) IN ('sarl', 'spa', 'eurl') OR " +
           "LOWER(en.designationFr) LIKE '%responsabilité limitée%' OR " +
           "LOWER(en.designationFr) LIKE '%société par actions%'")
    Page<EconomicNature> findLimitedLiabilityNatures(Pageable pageable);

    /**
     * Find partnership economic natures (based on acronym patterns)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.acronymFr) IN ('snc', 'scs') OR " +
           "LOWER(en.designationFr) LIKE '%société en nom%' OR " +
           "LOWER(en.designationFr) LIKE '%société en commandite%'")
    Page<EconomicNature> findPartnershipNatures(Pageable pageable);

    /**
     * Find economic natures ordered by designation in specific language
     */
    @Query("SELECT en FROM EconomicNature en ORDER BY en.designationAr ASC")
    Page<EconomicNature> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT en FROM EconomicNature en ORDER BY en.designationEn ASC")
    Page<EconomicNature> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count economic natures by legal structure type
     */
    @Query("SELECT COUNT(en) FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%public%' OR LOWER(en.designationFr) LIKE '%état%' OR " +
           "LOWER(en.acronymFr) LIKE '%epa%' OR LOWER(en.acronymFr) LIKE '%epic%'")
    Long countPublicSectorNatures();

    @Query("SELECT COUNT(en) FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%privé%' OR LOWER(en.acronymFr) IN ('sarl', 'spa', 'eurl', 'snc', 'scs')")
    Long countPrivateSectorNatures();

    @Query("SELECT COUNT(en) FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%coopératif%' OR LOWER(en.designationFr) LIKE '%coopérative%'")
    Long countCooperativeNatures();

    @Query("SELECT COUNT(en) FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%association%' OR LOWER(en.designationFr) LIKE '%ong%'")
    Long countAssociationNatures();

    /**
     * Count multilingual economic natures
     */
    @Query("SELECT COUNT(en) FROM EconomicNature en WHERE " +
           "(en.designationAr IS NOT NULL AND en.designationAr != '' AND en.designationEn IS NOT NULL AND en.designationEn != '') OR " +
           "(en.designationAr IS NOT NULL AND en.designationAr != '' AND en.designationFr IS NOT NULL AND en.designationFr != '') OR " +
           "(en.designationEn IS NOT NULL AND en.designationEn != '' AND en.designationFr IS NOT NULL AND en.designationFr != '')")
    Long countMultilingualEconomicNatures();

    /**
     * Find economic natures missing translations
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "(en.designationAr IS NULL OR en.designationAr = '') OR " +
           "(en.designationEn IS NULL OR en.designationEn = '') OR " +
           "(en.acronymAr IS NULL OR en.acronymAr = '') OR " +
           "(en.acronymEn IS NULL OR en.acronymEn = '')")
    Page<EconomicNature> findMissingTranslations(Pageable pageable);

    /**
     * Find economic natures by Arabic designation
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.designationAr = :designationAr")
    Optional<EconomicNature> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find economic natures by English designation
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.designationEn = :designationEn")
    Optional<EconomicNature> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Find economic natures by Arabic acronym
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.acronymAr = :acronymAr")
    Optional<EconomicNature> findByAcronymAr(@Param("acronymAr") String acronymAr);

    /**
     * Find economic natures by English acronym
     */
    @Query("SELECT en FROM EconomicNature en WHERE en.acronymEn = :acronymEn")
    Optional<EconomicNature> findByAcronymEn(@Param("acronymEn") String acronymEn);

    /**
     * Find government-related economic natures
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.designationFr) LIKE '%public%' OR LOWER(en.designationFr) LIKE '%état%' OR " +
           "LOWER(en.designationFr) LIKE '%mixte%' OR LOWER(en.acronymFr) LIKE '%epa%' OR " +
           "LOWER(en.acronymFr) LIKE '%epic%'")
    Page<EconomicNature> findGovernmentRelatedNatures(Pageable pageable);

    /**
     * Find commercial economic natures (for-profit entities)
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.acronymFr) IN ('sarl', 'spa', 'eurl', 'snc', 'scs') OR " +
           "LOWER(en.designationFr) LIKE '%société%' OR LOWER(en.designationFr) LIKE '%entreprise%'")
    Page<EconomicNature> findCommercialNatures(Pageable pageable);

    /**
     * Find economic natures by acronym length
     */
    @Query("SELECT en FROM EconomicNature en WHERE LENGTH(en.acronymFr) <= :maxLength")
    Page<EconomicNature> findByAcronymLength(@Param("maxLength") Integer maxLength, Pageable pageable);

    /**
     * Find economic natures with special registration requirements
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.acronymFr) IN ('spa', 'epic', 'epa') OR " +
           "LOWER(en.designationFr) LIKE '%établissement public%' OR " +
           "LOWER(en.designationFr) LIKE '%société par actions%' OR " +
           "LOWER(en.designationFr) LIKE '%coopérative%' OR " +
           "LOWER(en.designationFr) LIKE '%étranger%'")
    Page<EconomicNature> findSpecialRegistrationNatures(Pageable pageable);

    /**
     * Find economic natures with limited liability
     */
    @Query("SELECT en FROM EconomicNature en WHERE " +
           "LOWER(en.acronymFr) IN ('sarl', 'spa', 'eurl', 'scs') OR " +
           "LOWER(en.designationFr) LIKE '%responsabilité limitée%' OR " +
           "LOWER(en.designationFr) LIKE '%société par actions%' OR " +
           "LOWER(en.designationFr) LIKE '%établissement public%'")
    Page<EconomicNature> findLimitedLiabilityEconomicNatures(Pageable pageable);
}