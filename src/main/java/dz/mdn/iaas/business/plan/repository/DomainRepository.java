/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DomainRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Domain Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique)
 * Includes one-to-many relationship with Rubrics
 */
@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {

    /**
     * Find domain by French designation (F_03) - unique constraint
     */
    @Query("SELECT d FROM Domain d WHERE d.designationFr = :designationFr")
    Optional<Domain> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find all domains ordered by French designation
     */
    @Query("SELECT d FROM Domain d ORDER BY d.designationFr ASC")
    Page<Domain> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search domains by designation (any language)
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(d.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(d.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Domain> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find domains with rubrics (has child rubrics)
     */
    @Query("SELECT DISTINCT d FROM Domain d JOIN d.rubrics r ORDER BY d.designationFr ASC")
    Page<Domain> findDomainsWithRubrics(Pageable pageable);

    /**
     * Find domains without rubrics (no child rubrics)
     */
    @Query("SELECT d FROM Domain d WHERE d.rubrics IS EMPTY ORDER BY d.designationFr ASC")
    Page<Domain> findDomainsWithoutRubrics(Pageable pageable);

    /**
     * Find domains by rubrics count range
     */
    @Query("SELECT d FROM Domain d WHERE SIZE(d.rubrics) BETWEEN :minCount AND :maxCount ORDER BY SIZE(d.rubrics) DESC, d.designationFr ASC")
    Page<Domain> findByRubricsCountRange(@Param("minCount") int minCount, @Param("maxCount") int maxCount, Pageable pageable);

    /**
     * Find domains by minimum rubrics count
     */
    @Query("SELECT d FROM Domain d WHERE SIZE(d.rubrics) >= :minCount ORDER BY SIZE(d.rubrics) DESC, d.designationFr ASC")
    Page<Domain> findByMinRubricsCount(@Param("minCount") int minCount, Pageable pageable);

    /**
     * Find technical domains (based on designation patterns)
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%technique%' OR " +
           "LOWER(d.designationEn) LIKE '%technical%' OR " +
           "LOWER(d.designationFr) LIKE '%technologie%' OR " +
           "LOWER(d.designationEn) LIKE '%technology%' OR " +
           "LOWER(d.designationFr) LIKE '%ingénierie%' OR " +
           "LOWER(d.designationEn) LIKE '%engineering%'")
    Page<Domain> findTechnicalDomains(Pageable pageable);

    /**
     * Find administrative domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%administratif%' OR " +
           "LOWER(d.designationEn) LIKE '%administrative%' OR " +
           "LOWER(d.designationFr) LIKE '%administration%' OR " +
           "LOWER(d.designationEn) LIKE '%administration%' OR " +
           "LOWER(d.designationFr) LIKE '%gestion%' OR " +
           "LOWER(d.designationEn) LIKE '%management%'")
    Page<Domain> findAdministrativeDomains(Pageable pageable);

    /**
     * Find operational domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%opérationnel%' OR " +
           "LOWER(d.designationEn) LIKE '%operational%' OR " +
           "LOWER(d.designationFr) LIKE '%opération%' OR " +
           "LOWER(d.designationEn) LIKE '%operation%' OR " +
           "LOWER(d.designationFr) LIKE '%mission%' OR " +
           "LOWER(d.designationEn) LIKE '%mission%'")
    Page<Domain> findOperationalDomains(Pageable pageable);

    /**
     * Find strategic domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%stratégique%' OR " +
           "LOWER(d.designationEn) LIKE '%strategic%' OR " +
           "LOWER(d.designationFr) LIKE '%stratégie%' OR " +
           "LOWER(d.designationEn) LIKE '%strategy%' OR " +
           "LOWER(d.designationFr) LIKE '%planification%' OR " +
           "LOWER(d.designationEn) LIKE '%planning%'")
    Page<Domain> findStrategicDomains(Pageable pageable);

    /**
     * Find financial domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%financier%' OR " +
           "LOWER(d.designationEn) LIKE '%financial%' OR " +
           "LOWER(d.designationFr) LIKE '%finance%' OR " +
           "LOWER(d.designationEn) LIKE '%finance%' OR " +
           "LOWER(d.designationFr) LIKE '%budget%' OR " +
           "LOWER(d.designationEn) LIKE '%budget%'")
    Page<Domain> findFinancialDomains(Pageable pageable);

    /**
     * Find security domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%sécurité%' OR " +
           "LOWER(d.designationEn) LIKE '%security%' OR " +
           "LOWER(d.designationFr) LIKE '%défense%' OR " +
           "LOWER(d.designationEn) LIKE '%defense%' OR " +
           "LOWER(d.designationFr) LIKE '%protection%' OR " +
           "LOWER(d.designationEn) LIKE '%protection%'")
    Page<Domain> findSecurityDomains(Pageable pageable);

    /**
     * Find HR domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%ressources humaines%' OR " +
           "LOWER(d.designationEn) LIKE '%human resources%' OR " +
           "LOWER(d.designationFr) LIKE '%personnel%' OR " +
           "LOWER(d.designationEn) LIKE '%personnel%' OR " +
           "LOWER(d.designationFr) LIKE '%rh%' OR " +
           "LOWER(d.designationEn) LIKE '%hr%'")
    Page<Domain> findHRDomains(Pageable pageable);

    /**
     * Find logistics domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%logistique%' OR " +
           "LOWER(d.designationEn) LIKE '%logistics%' OR " +
           "LOWER(d.designationFr) LIKE '%approvisionnement%' OR " +
           "LOWER(d.designationEn) LIKE '%supply%' OR " +
           "LOWER(d.designationFr) LIKE '%transport%' OR " +
           "LOWER(d.designationEn) LIKE '%transport%'")
    Page<Domain> findLogisticsDomains(Pageable pageable);

    /**
     * Find training domains
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%formation%' OR " +
           "LOWER(d.designationEn) LIKE '%training%' OR " +
           "LOWER(d.designationFr) LIKE '%éducation%' OR " +
           "LOWER(d.designationEn) LIKE '%education%' OR " +
           "LOWER(d.designationFr) LIKE '%apprentissage%' OR " +
           "LOWER(d.designationEn) LIKE '%learning%'")
    Page<Domain> findTrainingDomains(Pageable pageable);

    /**
     * Find multilingual domains (have designations in multiple languages)
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "d.designationFr IS NOT NULL AND d.designationFr != '' AND " +
           "(d.designationEn IS NOT NULL AND d.designationEn != '' OR " +
           "d.designationAr IS NOT NULL AND d.designationAr != '')")
    Page<Domain> findMultilingualDomains(Pageable pageable);

    /**
     * Find domains with Arabic designations
     */
    @Query("SELECT d FROM Domain d WHERE d.designationAr IS NOT NULL AND d.designationAr != ''")
    Page<Domain> findWithArabicDesignation(Pageable pageable);

    /**
     * Find domains with English designations
     */
    @Query("SELECT d FROM Domain d WHERE d.designationEn IS NOT NULL AND d.designationEn != ''")
    Page<Domain> findWithEnglishDesignation(Pageable pageable);

    /**
     * Check if French designation exists (for uniqueness validation)
     */
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Domain d WHERE d.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if French designation exists excluding current ID (for update validation)
     */
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Domain d WHERE d.designationFr = :designationFr AND d.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find domains by designation pattern (French)
     */
    @Query("SELECT d FROM Domain d WHERE LOWER(d.designationFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<Domain> findByDesignationFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find domains with complete information (all required fields filled)
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "d.designationFr IS NOT NULL AND d.designationFr != ''")
    Page<Domain> findWithCompleteInformation(Pageable pageable);

    /**
     * Find high complexity domains (many rubrics)
     */
    @Query("SELECT d FROM Domain d WHERE SIZE(d.rubrics) > 15 ORDER BY SIZE(d.rubrics) DESC, d.designationFr ASC")
    Page<Domain> findHighComplexityDomains(Pageable pageable);

    /**
     * Find medium complexity domains (moderate rubrics)
     */
    @Query("SELECT d FROM Domain d WHERE SIZE(d.rubrics) BETWEEN 5 AND 15 ORDER BY SIZE(d.rubrics) DESC, d.designationFr ASC")
    Page<Domain> findMediumComplexityDomains(Pageable pageable);

    /**
     * Find low complexity domains (few rubrics)
     */
    @Query("SELECT d FROM Domain d WHERE SIZE(d.rubrics) BETWEEN 1 AND 5 ORDER BY SIZE(d.rubrics) DESC, d.designationFr ASC")
    Page<Domain> findLowComplexityDomains(Pageable pageable);

    /**
     * Count total domains
     */
    @Query("SELECT COUNT(d) FROM Domain d")
    Long countAllDomains();

    /**
     * Count domains with rubrics
     */
    @Query("SELECT COUNT(DISTINCT d) FROM Domain d JOIN d.rubrics r")
    Long countDomainsWithRubrics();

    /**
     * Count domains without rubrics
     */
    @Query("SELECT COUNT(d) FROM Domain d WHERE d.rubrics IS EMPTY")
    Long countDomainsWithoutRubrics();

    /**
     * Count technical domains
     */
    @Query("SELECT COUNT(d) FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%technique%' OR " +
           "LOWER(d.designationEn) LIKE '%technical%' OR " +
           "LOWER(d.designationFr) LIKE '%technologie%' OR " +
           "LOWER(d.designationEn) LIKE '%technology%'")
    Long countTechnicalDomains();

    /**
     * Count operational domains
     */
    @Query("SELECT COUNT(d) FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%opérationnel%' OR " +
           "LOWER(d.designationEn) LIKE '%operational%'")
    Long countOperationalDomains();

    /**
     * Find similar domains (for duplicate detection)
     */
    @Query("SELECT d FROM Domain d WHERE LOWER(d.designationFr) = LOWER(:designation)")
    List<Domain> findSimilarDomains(@Param("designation") String designation);

    /**
     * Get domain statistics by category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(d.designationFr) LIKE '%technique%' OR LOWER(d.designationEn) LIKE '%technical%' THEN 'TECHNICAL' " +
           "WHEN LOWER(d.designationFr) LIKE '%administratif%' OR LOWER(d.designationEn) LIKE '%administrative%' THEN 'ADMINISTRATIVE' " +
           "WHEN LOWER(d.designationFr) LIKE '%opérationnel%' OR LOWER(d.designationEn) LIKE '%operational%' THEN 'OPERATIONAL' " +
           "WHEN LOWER(d.designationFr) LIKE '%stratégique%' OR LOWER(d.designationEn) LIKE '%strategic%' THEN 'STRATEGIC' " +
           "WHEN LOWER(d.designationFr) LIKE '%financier%' OR LOWER(d.designationEn) LIKE '%financial%' THEN 'FINANCIAL' " +
           "WHEN LOWER(d.designationFr) LIKE '%sécurité%' OR LOWER(d.designationEn) LIKE '%security%' THEN 'SECURITY' " +
           "ELSE 'OTHER' " +
           "END, COUNT(d) " +
           "FROM Domain d " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(d.designationFr) LIKE '%technique%' OR LOWER(d.designationEn) LIKE '%technical%' THEN 'TECHNICAL' " +
           "WHEN LOWER(d.designationFr) LIKE '%administratif%' OR LOWER(d.designationEn) LIKE '%administrative%' THEN 'ADMINISTRATIVE' " +
           "WHEN LOWER(d.designationFr) LIKE '%opérationnel%' OR LOWER(d.designationEn) LIKE '%operational%' THEN 'OPERATIONAL' " +
           "WHEN LOWER(d.designationFr) LIKE '%stratégique%' OR LOWER(d.designationEn) LIKE '%strategic%' THEN 'STRATEGIC' " +
           "WHEN LOWER(d.designationFr) LIKE '%financier%' OR LOWER(d.designationEn) LIKE '%financial%' THEN 'FINANCIAL' " +
           "WHEN LOWER(d.designationFr) LIKE '%sécurité%' OR LOWER(d.designationEn) LIKE '%security%' THEN 'SECURITY' " +
           "ELSE 'OTHER' " +
           "END")
    List<Object[]> getDomainStatisticsByCategory();

    /**
     * Get domain statistics by complexity
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN SIZE(d.rubrics) = 0 THEN 'NO_RUBRICS' " +
           "WHEN SIZE(d.rubrics) <= 5 THEN 'LOW_COMPLEXITY' " +
           "WHEN SIZE(d.rubrics) <= 15 THEN 'MEDIUM_COMPLEXITY' " +
           "WHEN SIZE(d.rubrics) <= 30 THEN 'HIGH_COMPLEXITY' " +
           "ELSE 'VERY_HIGH_COMPLEXITY' " +
           "END, COUNT(d) " +
           "FROM Domain d " +
           "GROUP BY " +
           "CASE " +
           "WHEN SIZE(d.rubrics) = 0 THEN 'NO_RUBRICS' " +
           "WHEN SIZE(d.rubrics) <= 5 THEN 'LOW_COMPLEXITY' " +
           "WHEN SIZE(d.rubrics) <= 15 THEN 'MEDIUM_COMPLEXITY' " +
           "WHEN SIZE(d.rubrics) <= 30 THEN 'HIGH_COMPLEXITY' " +
           "ELSE 'VERY_HIGH_COMPLEXITY' " +
           "END")
    List<Object[]> getDomainStatisticsByComplexity();

    /**
     * Find most recently added domains
     */
    @Query("SELECT d FROM Domain d ORDER BY d.id DESC")
    Page<Domain> findMostRecentDomains(Pageable pageable);

    /**
     * Find domains with most rubrics
     */
    @Query("SELECT d FROM Domain d WHERE SIZE(d.rubrics) > 0 ORDER BY SIZE(d.rubrics) DESC, d.designationFr ASC")
    Page<Domain> findDomainsWithMostRubrics(Pageable pageable);

    /**
     * Find domains by priority level (based on category keywords)
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "(:priority = 'CRITICAL' AND " +
           "(LOWER(d.designationFr) LIKE '%sécurité%' OR LOWER(d.designationFr) LIKE '%stratégique%' OR " +
           "LOWER(d.designationEn) LIKE '%security%' OR LOWER(d.designationEn) LIKE '%strategic%')) OR " +
           "(:priority = 'HIGH' AND " +
           "(LOWER(d.designationFr) LIKE '%opérationnel%' OR LOWER(d.designationFr) LIKE '%technique%' OR " +
           "LOWER(d.designationEn) LIKE '%operational%' OR LOWER(d.designationEn) LIKE '%technical%')) OR " +
           "(:priority = 'MEDIUM' AND " +
           "(LOWER(d.designationFr) LIKE '%financier%' OR LOWER(d.designationFr) LIKE '%administratif%' OR " +
           "LOWER(d.designationEn) LIKE '%financial%' OR LOWER(d.designationEn) LIKE '%administrative%'))")
    Page<Domain> findByPriorityLevel(@Param("priority") String priority, Pageable pageable);

    /**
     * Find domains requiring executive oversight (critical priority)
     */
    @Query("SELECT d FROM Domain d WHERE " +
           "LOWER(d.designationFr) LIKE '%sécurité%' OR LOWER(d.designationFr) LIKE '%stratégique%' OR " +
           "LOWER(d.designationFr) LIKE '%défense%' OR " +
           "LOWER(d.designationEn) LIKE '%security%' OR LOWER(d.designationEn) LIKE '%strategic%' OR " +
           "LOWER(d.designationEn) LIKE '%defense%'")
    Page<Domain> findRequiringExecutiveOversight(Pageable pageable);

    /**
     * Get average rubrics count per domain
     */
    @Query("SELECT AVG(SIZE(d.rubrics)) FROM Domain d")
    Double getAverageRubricsPerDomain();

    /**
     * Get maximum rubrics count
     */
    @Query("SELECT MAX(SIZE(d.rubrics)) FROM Domain d")
    Integer getMaxRubricsCount();

    /**
     * Get minimum rubrics count (excluding zero)
     */
    @Query("SELECT MIN(SIZE(d.rubrics)) FROM Domain d WHERE SIZE(d.rubrics) > 0")
    Integer getMinRubricsCountExcludingZero();
}