/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ExclusionTypeRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.ExclusionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Exclusion Type Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@Repository
public interface ExclusionTypeRepository extends JpaRepository<ExclusionType, Long> {

    /**
     * Find exclusion type by French designation (F_03) - unique field
     */
    @Query("SELECT et FROM ExclusionType et WHERE et.designationFr = :designationFr")
    Optional<ExclusionType> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if exclusion type exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(et) > 0 THEN true ELSE false END FROM ExclusionType et WHERE et.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(et) > 0 THEN true ELSE false END FROM ExclusionType et WHERE et.designationFr = :designationFr AND et.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all exclusion types with pagination ordered by French designation
     */
    @Query("SELECT et FROM ExclusionType et ORDER BY et.designationFr ASC")
    Page<ExclusionType> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search exclusion types by any designation field
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "et.designationAr LIKE %:search% OR " +
           "et.designationEn LIKE %:search% OR " +
           "et.designationFr LIKE %:search%")
    Page<ExclusionType> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find exclusion types by French designation pattern (F_03)
     */
    @Query("SELECT et FROM ExclusionType et WHERE et.designationFr LIKE %:pattern%")
    Page<ExclusionType> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total exclusion types
     */
    @Query("SELECT COUNT(et) FROM ExclusionType et")
    Long countAllExclusionTypes();

    /**
     * Find exclusion types that have Arabic designation
     */
    @Query("SELECT et FROM ExclusionType et WHERE et.designationAr IS NOT NULL AND et.designationAr != ''")
    Page<ExclusionType> findWithArabicDesignation(Pageable pageable);

    /**
     * Find exclusion types that have English designation
     */
    @Query("SELECT et FROM ExclusionType et WHERE et.designationEn IS NOT NULL AND et.designationEn != ''")
    Page<ExclusionType> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual exclusion types (have at least 2 designations)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "(et.designationAr IS NOT NULL AND et.designationAr != '' AND et.designationEn IS NOT NULL AND et.designationEn != '') OR " +
           "(et.designationAr IS NOT NULL AND et.designationAr != '' AND et.designationFr IS NOT NULL AND et.designationFr != '') OR " +
           "(et.designationEn IS NOT NULL AND et.designationEn != '' AND et.designationFr IS NOT NULL AND et.designationFr != '')")
    Page<ExclusionType> findMultilingualExclusionTypes(Pageable pageable);

    /**
     * Find legal exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%judiciaire%' OR LOWER(et.designationFr) LIKE '%juridique%' OR " +
           "LOWER(et.designationFr) LIKE '%tribunal%' OR LOWER(et.designationFr) LIKE '%cour%'")
    Page<ExclusionType> findLegalExclusionTypes(Pageable pageable);

    /**
     * Find criminal exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%criminel%' OR LOWER(et.designationFr) LIKE '%pénal%' OR " +
           "LOWER(et.designationFr) LIKE '%condamnation%' OR LOWER(et.designationFr) LIKE '%délit%'")
    Page<ExclusionType> findCriminalExclusionTypes(Pageable pageable);

    /**
     * Find financial exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%financier%' OR LOWER(et.designationFr) LIKE '%crédit%' OR " +
           "LOWER(et.designationFr) LIKE '%endettement%' OR LOWER(et.designationFr) LIKE '%faillite%'")
    Page<ExclusionType> findFinancialExclusionTypes(Pageable pageable);

    /**
     * Find tax exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%fiscal%' OR LOWER(et.designationFr) LIKE '%impôt%' OR " +
           "LOWER(et.designationFr) LIKE '%taxe%' OR LOWER(et.designationFr) LIKE '%douane%'")
    Page<ExclusionType> findTaxExclusionTypes(Pageable pageable);

    /**
     * Find administrative exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%administratif%' OR LOWER(et.designationFr) LIKE '%réglementaire%' OR " +
           "LOWER(et.designationFr) LIKE '%licence%' OR LOWER(et.designationFr) LIKE '%autorisation%'")
    Page<ExclusionType> findAdministrativeExclusionTypes(Pageable pageable);

    /**
     * Find security exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%sécurité%' OR LOWER(et.designationFr) LIKE '%secret%' OR " +
           "LOWER(et.designationFr) LIKE '%confidentiel%' OR LOWER(et.designationFr) LIKE '%national%'")
    Page<ExclusionType> findSecurityExclusionTypes(Pageable pageable);

    /**
     * Find sectoral exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%sectoriel%' OR LOWER(et.designationFr) LIKE '%activité%' OR " +
           "LOWER(et.designationFr) LIKE '%domaine%' OR LOWER(et.designationFr) LIKE '%spécialisé%'")
    Page<ExclusionType> findSectoralExclusionTypes(Pageable pageable);

    /**
     * Find geographical exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%géographique%' OR LOWER(et.designationFr) LIKE '%territorial%' OR " +
           "LOWER(et.designationFr) LIKE '%région%' OR LOWER(et.designationFr) LIKE '%zone%'")
    Page<ExclusionType> findGeographicalExclusionTypes(Pageable pageable);

    /**
     * Find temporal exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%temporaire%' OR LOWER(et.designationFr) LIKE '%période%' OR " +
           "LOWER(et.designationFr) LIKE '%durée%' OR LOWER(et.designationFr) LIKE '%délai%'")
    Page<ExclusionType> findTemporalExclusionTypes(Pageable pageable);

    /**
     * Find qualification exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%qualification%' OR LOWER(et.designationFr) LIKE '%compétence%' OR " +
           "LOWER(et.designationFr) LIKE '%expérience%' OR LOWER(et.designationFr) LIKE '%formation%'")
    Page<ExclusionType> findQualificationExclusionTypes(Pageable pageable);

    /**
     * Find conflict of interest exclusion types (based on French designation patterns)
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%conflit%' OR LOWER(et.designationFr) LIKE '%intérêt%' OR " +
           "LOWER(et.designationFr) LIKE '%incompatibilité%' OR LOWER(et.designationFr) LIKE '%éthique%'")
    Page<ExclusionType> findConflictExclusionTypes(Pageable pageable);

    /**
     * Find exclusion types ordered by designation in specific language
     */
    @Query("SELECT et FROM ExclusionType et ORDER BY et.designationAr ASC")
    Page<ExclusionType> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT et FROM ExclusionType et ORDER BY et.designationEn ASC")
    Page<ExclusionType> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count exclusion types by category
     */
    @Query("SELECT COUNT(et) FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%judiciaire%' OR LOWER(et.designationFr) LIKE '%juridique%' OR " +
           "LOWER(et.designationFr) LIKE '%criminel%' OR LOWER(et.designationFr) LIKE '%pénal%'")
    Long countLegalExclusionTypes();

    @Query("SELECT COUNT(et) FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%financier%' OR LOWER(et.designationFr) LIKE '%fiscal%' OR " +
           "LOWER(et.designationFr) LIKE '%crédit%' OR LOWER(et.designationFr) LIKE '%faillite%'")
    Long countFinancialExclusionTypes();

    @Query("SELECT COUNT(et) FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%administratif%' OR LOWER(et.designationFr) LIKE '%licence%' OR " +
           "LOWER(et.designationFr) LIKE '%autorisation%' OR LOWER(et.designationFr) LIKE '%réglementaire%'")
    Long countAdministrativeExclusionTypes();

    @Query("SELECT COUNT(et) FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%sécurité%' OR LOWER(et.designationFr) LIKE '%secret%' OR " +
           "LOWER(et.designationFr) LIKE '%confidentiel%'")
    Long countSecurityExclusionTypes();

    /**
     * Count multilingual exclusion types
     */
    @Query("SELECT COUNT(et) FROM ExclusionType et WHERE " +
           "(et.designationAr IS NOT NULL AND et.designationAr != '' AND et.designationEn IS NOT NULL AND et.designationEn != '') OR " +
           "(et.designationAr IS NOT NULL AND et.designationAr != '' AND et.designationFr IS NOT NULL AND et.designationFr != '') OR " +
           "(et.designationEn IS NOT NULL AND et.designationEn != '' AND et.designationFr IS NOT NULL AND et.designationFr != '')")
    Long countMultilingualExclusionTypes();

    /**
     * Find exclusion types missing translations
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "(et.designationAr IS NULL OR et.designationAr = '') OR " +
           "(et.designationEn IS NULL OR et.designationEn = '')")
    Page<ExclusionType> findMissingTranslations(Pageable pageable);

    /**
     * Find exclusion types by Arabic designation
     */
    @Query("SELECT et FROM ExclusionType et WHERE et.designationAr = :designationAr")
    Optional<ExclusionType> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find exclusion types by English designation
     */
    @Query("SELECT et FROM ExclusionType et WHERE et.designationEn = :designationEn")
    Optional<ExclusionType> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Find permanent exclusion types
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%permanent%' OR LOWER(et.designationFr) LIKE '%définitif%' OR " +
           "LOWER(et.designationFr) LIKE '%criminel%' OR LOWER(et.designationFr) LIKE '%condamnation%'")
    Page<ExclusionType> findPermanentExclusionTypes(Pageable pageable);

    /**
     * Find temporary exclusion types
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%temporaire%' OR LOWER(et.designationFr) LIKE '%provisoire%' OR " +
           "LOWER(et.designationFr) LIKE '%délai%' OR LOWER(et.designationFr) LIKE '%période%'")
    Page<ExclusionType> findTemporaryExclusionTypes(Pageable pageable);

    /**
     * Find conditional exclusion types
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%conditionnel%' OR LOWER(et.designationFr) LIKE '%qualification%' OR " +
           "LOWER(et.designationFr) LIKE '%licence%' OR LOWER(et.designationFr) LIKE '%autorisation%'")
    Page<ExclusionType> findConditionalExclusionTypes(Pageable pageable);

    /**
     * Find high severity exclusion types
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%criminel%' OR LOWER(et.designationFr) LIKE '%sécurité%' OR " +
           "LOWER(et.designationFr) LIKE '%faillite%' OR LOWER(et.designationFr) LIKE '%conflit%'")
    Page<ExclusionType> findHighSeverityExclusionTypes(Pageable pageable);

    /**
     * Find exclusion types that affect public contracts
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%public%' OR LOWER(et.designationFr) LIKE '%criminel%' OR " +
           "LOWER(et.designationFr) LIKE '%fiscal%' OR LOWER(et.designationFr) LIKE '%faillite%'")
    Page<ExclusionType> findPublicContractExclusionTypes(Pageable pageable);

    /**
     * Find exclusion types requiring legal review
     */
    @Query("SELECT et FROM ExclusionType et WHERE " +
           "LOWER(et.designationFr) LIKE '%judiciaire%' OR LOWER(et.designationFr) LIKE '%criminel%' OR " +
           "LOWER(et.designationFr) LIKE '%sécurité%' OR LOWER(et.designationFr) LIKE '%conflit%'")
    Page<ExclusionType> findLegalReviewExclusionTypes(Pageable pageable);
}
