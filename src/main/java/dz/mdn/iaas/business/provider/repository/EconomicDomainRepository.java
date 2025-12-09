/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicDomainRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.EconomicDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Economic Domain Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@Repository
public interface EconomicDomainRepository extends JpaRepository<EconomicDomain, Long> {

    /**
     * Find economic domain by Code (F_01) - unique field
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE ed.code = :code")
    Optional<EconomicDomain> findByCode(@Param("code") String code);

    /**
     * Find economic domain by French designation (F_04) - unique field
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE ed.designationFr = :designationFr")
    Optional<EconomicDomain> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if economic domain exists by code
     */
    @Query("SELECT CASE WHEN COUNT(ed) > 0 THEN true ELSE false END FROM EconomicDomain ed WHERE ed.code = :code")
    boolean existsByCode(@Param("code") String code);

    /**
     * Check if economic domain exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(ed) > 0 THEN true ELSE false END FROM EconomicDomain ed WHERE ed.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(ed) > 0 THEN true ELSE false END FROM EconomicDomain ed WHERE ed.code = :code AND ed.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(ed) > 0 THEN true ELSE false END FROM EconomicDomain ed WHERE ed.designationFr = :designationFr AND ed.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all economic domains with pagination ordered by French designation
     */
    @Query("SELECT ed FROM EconomicDomain ed ORDER BY ed.code ASC")
    Page<EconomicDomain> findAllOrderByCode(Pageable pageable);

    /**
     * Find all economic domains with pagination ordered by French designation
     */
    @Query("SELECT ed FROM EconomicDomain ed ORDER BY ed.designationFr ASC")
    Page<EconomicDomain> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search economic domains by any designation field
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "ed.designationAr LIKE %:search% OR " +
           "ed.designationEn LIKE %:search% OR " +
           "ed.designationFr LIKE %:search%")
    Page<EconomicDomain> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find economic domains by French designation pattern (F_03)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE ed.designationFr LIKE %:pattern%")
    Page<EconomicDomain> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total economic domains
     */
    @Query("SELECT COUNT(ed) FROM EconomicDomain ed")
    Long countAllEconomicDomains();

    /**
     * Find economic domains that have Arabic designation
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE ed.designationAr IS NOT NULL AND ed.designationAr != ''")
    Page<EconomicDomain> findWithArabicDesignation(Pageable pageable);

    /**
     * Find economic domains that have English designation
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE ed.designationEn IS NOT NULL AND ed.designationEn != ''")
    Page<EconomicDomain> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual economic domains (have at least 2 designations)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "(ed.designationAr IS NOT NULL AND ed.designationAr != '' AND ed.designationEn IS NOT NULL AND ed.designationEn != '') OR " +
           "(ed.designationAr IS NOT NULL AND ed.designationAr != '' AND ed.designationFr IS NOT NULL AND ed.designationFr != '') OR " +
           "(ed.designationEn IS NOT NULL AND ed.designationEn != '' AND ed.designationFr IS NOT NULL AND ed.designationFr != '')")
    Page<EconomicDomain> findMultilingualEconomicDomains(Pageable pageable);

    /**
     * Find agriculture domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%agriculture%' OR LOWER(ed.designationFr) LIKE '%agricole%'")
    Page<EconomicDomain> findAgricultureDomains(Pageable pageable);

    /**
     * Find industry domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%industrie%' OR LOWER(ed.designationFr) LIKE '%industriel%'")
    Page<EconomicDomain> findIndustryDomains(Pageable pageable);

    /**
     * Find service domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%service%' OR LOWER(ed.designationFr) LIKE '%commerce%'")
    Page<EconomicDomain> findServiceDomains(Pageable pageable);

    /**
     * Find energy domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%énergie%' OR LOWER(ed.designationFr) LIKE '%pétrole%' OR " +
           "LOWER(ed.designationFr) LIKE '%gazier%' OR LOWER(ed.designationFr) LIKE '%électricité%'")
    Page<EconomicDomain> findEnergyDomains(Pageable pageable);

    /**
     * Find technology domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%technologie%' OR LOWER(ed.designationFr) LIKE '%informatique%' OR " +
           "LOWER(ed.designationFr) LIKE '%numérique%' OR LOWER(ed.designationFr) LIKE '%digital%'")
    Page<EconomicDomain> findTechnologyDomains(Pageable pageable);

    /**
     * Find finance domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%banque%' OR LOWER(ed.designationFr) LIKE '%bancaire%' OR " +
           "LOWER(ed.designationFr) LIKE '%finance%' OR LOWER(ed.designationFr) LIKE '%assurance%'")
    Page<EconomicDomain> findFinanceDomains(Pageable pageable);

    /**
     * Find healthcare domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%santé%' OR LOWER(ed.designationFr) LIKE '%médical%' OR " +
           "LOWER(ed.designationFr) LIKE '%pharmaceutique%'")
    Page<EconomicDomain> findHealthcareDomains(Pageable pageable);

    /**
     * Find education domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%éducation%' OR LOWER(ed.designationFr) LIKE '%enseignement%'")
    Page<EconomicDomain> findEducationDomains(Pageable pageable);

    /**
     * Find tourism domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%tourisme%' OR LOWER(ed.designationFr) LIKE '%hôtellerie%' OR " +
           "LOWER(ed.designationFr) LIKE '%restauration%'")
    Page<EconomicDomain> findTourismDomains(Pageable pageable);

    /**
     * Find construction domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%construction%' OR LOWER(ed.designationFr) LIKE '%bâtiment%' OR " +
           "LOWER(ed.designationFr) LIKE '%travaux%'")
    Page<EconomicDomain> findConstructionDomains(Pageable pageable);

    /**
     * Find transport domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%transport%' OR LOWER(ed.designationFr) LIKE '%logistique%'")
    Page<EconomicDomain> findTransportDomains(Pageable pageable);

    /**
     * Find mining domains (based on French designation patterns)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%minier%' OR LOWER(ed.designationFr) LIKE '%mine%' OR " +
           "LOWER(ed.designationFr) LIKE '%extraction%'")
    Page<EconomicDomain> findMiningDomains(Pageable pageable);

    /**
     * Find economic domains ordered by designation in specific language
     */
    @Query("SELECT ed FROM EconomicDomain ed ORDER BY ed.designationAr ASC")
    Page<EconomicDomain> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT ed FROM EconomicDomain ed ORDER BY ed.designationEn ASC")
    Page<EconomicDomain> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count economic domains by sector type
     */
    @Query("SELECT COUNT(ed) FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%agriculture%' OR LOWER(ed.designationFr) LIKE '%agricole%' OR " +
           "LOWER(ed.designationFr) LIKE '%pêche%' OR LOWER(ed.designationFr) LIKE '%forestier%' OR " +
           "LOWER(ed.designationFr) LIKE '%minier%' OR LOWER(ed.designationFr) LIKE '%pétrole%'")
    Long countPrimarySectorDomains();

    @Query("SELECT COUNT(ed) FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%industrie%' OR LOWER(ed.designationFr) LIKE '%industriel%' OR " +
           "LOWER(ed.designationFr) LIKE '%construction%' OR LOWER(ed.designationFr) LIKE '%énergie%'")
    Long countSecondarySectorDomains();

    @Query("SELECT COUNT(ed) FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%service%' OR LOWER(ed.designationFr) LIKE '%commerce%' OR " +
           "LOWER(ed.designationFr) LIKE '%banque%' OR LOWER(ed.designationFr) LIKE '%transport%' OR " +
           "LOWER(ed.designationFr) LIKE '%tourisme%' OR LOWER(ed.designationFr) LIKE '%santé%'")
    Long countTertiarySectorDomains();

    @Query("SELECT COUNT(ed) FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%technologie%' OR LOWER(ed.designationFr) LIKE '%recherche%' OR " +
           "LOWER(ed.designationFr) LIKE '%développement%' OR LOWER(ed.designationFr) LIKE '%innovation%'")
    Long countQuaternarySectorDomains();

    /**
     * Count multilingual economic domains
     */
    @Query("SELECT COUNT(ed) FROM EconomicDomain ed WHERE " +
           "(ed.designationAr IS NOT NULL AND ed.designationAr != '' AND ed.designationEn IS NOT NULL AND ed.designationEn != '') OR " +
           "(ed.designationAr IS NOT NULL AND ed.designationAr != '' AND ed.designationFr IS NOT NULL AND ed.designationFr != '') OR " +
           "(ed.designationEn IS NOT NULL AND ed.designationEn != '' AND ed.designationFr IS NOT NULL AND ed.designationFr != '')")
    Long countMultilingualEconomicDomains();

    /**
     * Find economic domains missing translations
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "(ed.designationAr IS NULL OR ed.designationAr = '') OR " +
           "(ed.designationEn IS NULL OR ed.designationEn = '')")
    Page<EconomicDomain> findMissingTranslations(Pageable pageable);

    /**
     * Find economic domains by Arabic designation
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE ed.designationAr = :designationAr")
    Optional<EconomicDomain> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find economic domains by English designation
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE ed.designationEn = :designationEn")
    Optional<EconomicDomain> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Find strategic economic domains (key sectors for national economy)
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%pétrole%' OR LOWER(ed.designationFr) LIKE '%gazier%' OR " +
           "LOWER(ed.designationFr) LIKE '%agriculture%' OR LOWER(ed.designationFr) LIKE '%minier%' OR " +
           "LOWER(ed.designationFr) LIKE '%énergie%' OR LOWER(ed.designationFr) LIKE '%banque%' OR " +
           "LOWER(ed.designationFr) LIKE '%télécommunication%' OR LOWER(ed.designationFr) LIKE '%santé%'")
    Page<EconomicDomain> findStrategicDomains(Pageable pageable);

    /**
     * Find export-oriented domains
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%pétrole%' OR LOWER(ed.designationFr) LIKE '%minier%' OR " +
           "LOWER(ed.designationFr) LIKE '%agriculture%' OR LOWER(ed.designationFr) LIKE '%industrie%' OR " +
           "LOWER(ed.designationFr) LIKE '%textile%' OR LOWER(ed.designationFr) LIKE '%automobile%' OR " +
           "LOWER(ed.designationFr) LIKE '%tourisme%'")
    Page<EconomicDomain> findExportOrientedDomains(Pageable pageable);

    /**
     * Find high investment domains
     */
    @Query("SELECT ed FROM EconomicDomain ed WHERE " +
           "LOWER(ed.designationFr) LIKE '%pétrole%' OR LOWER(ed.designationFr) LIKE '%minier%' OR " +
           "LOWER(ed.designationFr) LIKE '%énergie%' OR LOWER(ed.designationFr) LIKE '%industrie%' OR " +
           "LOWER(ed.designationFr) LIKE '%automobile%' OR LOWER(ed.designationFr) LIKE '%construction%' OR " +
           "LOWER(ed.designationFr) LIKE '%télécommunication%' OR LOWER(ed.designationFr) LIKE '%technologie%'")
    Page<EconomicDomain> findHighInvestmentDomains(Pageable pageable);
}
