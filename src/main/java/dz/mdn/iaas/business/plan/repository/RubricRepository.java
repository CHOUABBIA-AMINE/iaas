/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RubricRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.Rubric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Rubric Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique), F_04=domain
 * Includes many-to-one relationship with Domain and one-to-many relationship with Items
 */
@Repository
public interface RubricRepository extends JpaRepository<Rubric, Long> {

    /**
     * Find rubric by French designation (F_03) - unique constraint
     */
    @Query("SELECT r FROM Rubric r WHERE r.designationFr = :designationFr")
    Optional<Rubric> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find all rubrics ordered by domain designation, then by rubric designation
     */
    @Query("SELECT r FROM Rubric r ORDER BY r.domain.designationFr ASC, r.designationFr ASC")
    Page<Rubric> findAllOrderByDomainAndDesignation(Pageable pageable);

    /**
     * Find rubrics by domain
     */
    @Query("SELECT r FROM Rubric r WHERE r.domain.id = :domainId ORDER BY r.designationFr ASC")
    Page<Rubric> findByDomain(@Param("domainId") Long domainId, Pageable pageable);

    /**
     * Search rubrics by designation (any language)
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(r.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(r.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Rubric> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find rubrics with items (has child items)
     */
    @Query("SELECT DISTINCT r FROM Rubric r JOIN r.items i ORDER BY r.domain.designationFr ASC, r.designationFr ASC")
    Page<Rubric> findRubricsWithItems(Pageable pageable);

    /**
     * Find rubrics without items (no child items)
     */
    @Query("SELECT r FROM Rubric r WHERE r.items IS EMPTY ORDER BY r.domain.designationFr ASC, r.designationFr ASC")
    Page<Rubric> findRubricsWithoutItems(Pageable pageable);

    /**
     * Find rubrics by items count range
     */
    @Query("SELECT r FROM Rubric r WHERE SIZE(r.items) BETWEEN :minCount AND :maxCount ORDER BY SIZE(r.items) DESC, r.designationFr ASC")
    Page<Rubric> findByItemsCountRange(@Param("minCount") int minCount, @Param("maxCount") int maxCount, Pageable pageable);

    /**
     * Find rubrics by minimum items count
     */
    @Query("SELECT r FROM Rubric r WHERE SIZE(r.items) >= :minCount ORDER BY SIZE(r.items) DESC, r.designationFr ASC")
    Page<Rubric> findByMinItemsCount(@Param("minCount") int minCount, Pageable pageable);

    /**
     * Find requirements rubrics (based on designation patterns)
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%exigence%' OR " +
           "LOWER(r.designationEn) LIKE '%requirement%' OR " +
           "LOWER(r.designationFr) LIKE '%spécification%' OR " +
           "LOWER(r.designationEn) LIKE '%specification%' OR " +
           "LOWER(r.designationFr) LIKE '%besoin%' OR " +
           "LOWER(r.designationEn) LIKE '%need%'")
    Page<Rubric> findRequirementsRubrics(Pageable pageable);

    /**
     * Find quality rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%qualité%' OR " +
           "LOWER(r.designationEn) LIKE '%quality%' OR " +
           "LOWER(r.designationFr) LIKE '%norme%' OR " +
           "LOWER(r.designationEn) LIKE '%standard%' OR " +
           "LOWER(r.designationFr) LIKE '%conformité%' OR " +
           "LOWER(r.designationEn) LIKE '%compliance%'")
    Page<Rubric> findQualityRubrics(Pageable pageable);

    /**
     * Find performance rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%performance%' OR " +
           "LOWER(r.designationEn) LIKE '%performance%' OR " +
           "LOWER(r.designationFr) LIKE '%efficacité%' OR " +
           "LOWER(r.designationEn) LIKE '%efficiency%' OR " +
           "LOWER(r.designationFr) LIKE '%rendement%' OR " +
           "LOWER(r.designationEn) LIKE '%productivity%'")
    Page<Rubric> findPerformanceRubrics(Pageable pageable);

    /**
     * Find security rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%sécurité%' OR " +
           "LOWER(r.designationEn) LIKE '%security%' OR " +
           "LOWER(r.designationFr) LIKE '%protection%' OR " +
           "LOWER(r.designationEn) LIKE '%protection%' OR " +
           "LOWER(r.designationFr) LIKE '%confidentialité%' OR " +
           "LOWER(r.designationEn) LIKE '%confidentiality%'")
    Page<Rubric> findSecurityRubrics(Pageable pageable);

    /**
     * Find compliance rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%conformité%' OR " +
           "LOWER(r.designationEn) LIKE '%compliance%' OR " +
           "LOWER(r.designationFr) LIKE '%réglementation%' OR " +
           "LOWER(r.designationEn) LIKE '%regulation%' OR " +
           "LOWER(r.designationFr) LIKE '%audit%' OR " +
           "LOWER(r.designationEn) LIKE '%audit%'")
    Page<Rubric> findComplianceRubrics(Pageable pageable);

    /**
     * Find technical rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%technique%' OR " +
           "LOWER(r.designationEn) LIKE '%technical%' OR " +
           "LOWER(r.designationFr) LIKE '%technologie%' OR " +
           "LOWER(r.designationEn) LIKE '%technology%' OR " +
           "LOWER(r.designationFr) LIKE '%système%' OR " +
           "LOWER(r.designationEn) LIKE '%system%'")
    Page<Rubric> findTechnicalRubrics(Pageable pageable);

    /**
     * Find operational rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%opérationnel%' OR " +
           "LOWER(r.designationEn) LIKE '%operational%' OR " +
           "LOWER(r.designationFr) LIKE '%processus%' OR " +
           "LOWER(r.designationEn) LIKE '%process%' OR " +
           "LOWER(r.designationFr) LIKE '%procédure%' OR " +
           "LOWER(r.designationEn) LIKE '%procedure%'")
    Page<Rubric> findOperationalRubrics(Pageable pageable);

    /**
     * Find training rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%formation%' OR " +
           "LOWER(r.designationEn) LIKE '%training%' OR " +
           "LOWER(r.designationFr) LIKE '%compétence%' OR " +
           "LOWER(r.designationEn) LIKE '%competency%' OR " +
           "LOWER(r.designationFr) LIKE '%apprentissage%' OR " +
           "LOWER(r.designationEn) LIKE '%learning%'")
    Page<Rubric> findTrainingRubrics(Pageable pageable);

    /**
     * Find documentation rubrics
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%documentation%' OR " +
           "LOWER(r.designationEn) LIKE '%documentation%' OR " +
           "LOWER(r.designationFr) LIKE '%document%' OR " +
           "LOWER(r.designationEn) LIKE '%document%' OR " +
           "LOWER(r.designationFr) LIKE '%manuel%' OR " +
           "LOWER(r.designationEn) LIKE '%manual%'")
    Page<Rubric> findDocumentationRubrics(Pageable pageable);

    /**
     * Find multilingual rubrics (have designations in multiple languages)
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "r.designationFr IS NOT NULL AND r.designationFr != '' AND " +
           "(r.designationEn IS NOT NULL AND r.designationEn != '' OR " +
           "r.designationAr IS NOT NULL AND r.designationAr != '')")
    Page<Rubric> findMultilingualRubrics(Pageable pageable);

    /**
     * Find rubrics with Arabic designations
     */
    @Query("SELECT r FROM Rubric r WHERE r.designationAr IS NOT NULL AND r.designationAr != ''")
    Page<Rubric> findWithArabicDesignation(Pageable pageable);

    /**
     * Find rubrics with English designations
     */
    @Query("SELECT r FROM Rubric r WHERE r.designationEn IS NOT NULL AND r.designationEn != ''")
    Page<Rubric> findWithEnglishDesignation(Pageable pageable);

    /**
     * Check if French designation exists (for uniqueness validation)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Rubric r WHERE r.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if French designation exists excluding current ID (for update validation)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Rubric r WHERE r.designationFr = :designationFr AND r.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find rubrics by designation pattern (French)
     */
    @Query("SELECT r FROM Rubric r WHERE LOWER(r.designationFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<Rubric> findByDesignationFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find rubrics with complete information (all required fields filled)
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "r.designationFr IS NOT NULL AND r.designationFr != '' AND " +
           "r.domain IS NOT NULL")
    Page<Rubric> findWithCompleteInformation(Pageable pageable);

    /**
     * Find high complexity rubrics (many items)
     */
    @Query("SELECT r FROM Rubric r WHERE SIZE(r.items) > 8 ORDER BY SIZE(r.items) DESC, r.designationFr ASC")
    Page<Rubric> findHighComplexityRubrics(Pageable pageable);

    /**
     * Find medium complexity rubrics (moderate items)
     */
    @Query("SELECT r FROM Rubric r WHERE SIZE(r.items) BETWEEN 3 AND 8 ORDER BY SIZE(r.items) DESC, r.designationFr ASC")
    Page<Rubric> findMediumComplexityRubrics(Pageable pageable);

    /**
     * Find low complexity rubrics (few items)
     */
    @Query("SELECT r FROM Rubric r WHERE SIZE(r.items) BETWEEN 1 AND 3 ORDER BY SIZE(r.items) DESC, r.designationFr ASC")
    Page<Rubric> findLowComplexityRubrics(Pageable pageable);

    /**
     * Count rubrics by domain
     */
    @Query("SELECT COUNT(r) FROM Rubric r WHERE r.domain.id = :domainId")
    Long countByDomain(@Param("domainId") Long domainId);

    /**
     * Count total rubrics
     */
    @Query("SELECT COUNT(r) FROM Rubric r")
    Long countAllRubrics();

    /**
     * Count rubrics with items
     */
    @Query("SELECT COUNT(DISTINCT r) FROM Rubric r JOIN r.items i")
    Long countRubricsWithItems();

    /**
     * Count rubrics without items
     */
    @Query("SELECT COUNT(r) FROM Rubric r WHERE r.items IS EMPTY")
    Long countRubricsWithoutItems();

    /**
     * Count requirements rubrics
     */
    @Query("SELECT COUNT(r) FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%exigence%' OR " +
           "LOWER(r.designationEn) LIKE '%requirement%' OR " +
           "LOWER(r.designationFr) LIKE '%spécification%' OR " +
           "LOWER(r.designationEn) LIKE '%specification%'")
    Long countRequirementsRubrics();

    /**
     * Count quality rubrics
     */
    @Query("SELECT COUNT(r) FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%qualité%' OR " +
           "LOWER(r.designationEn) LIKE '%quality%'")
    Long countQualityRubrics();

    /**
     * Find similar rubrics (for duplicate detection)
     */
    @Query("SELECT r FROM Rubric r WHERE LOWER(r.designationFr) = LOWER(:designation)")
    List<Rubric> findSimilarRubrics(@Param("designation") String designation);

    /**
     * Get rubric statistics by category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(r.designationFr) LIKE '%exigence%' OR LOWER(r.designationEn) LIKE '%requirement%' THEN 'REQUIREMENTS' " +
           "WHEN LOWER(r.designationFr) LIKE '%qualité%' OR LOWER(r.designationEn) LIKE '%quality%' THEN 'QUALITY' " +
           "WHEN LOWER(r.designationFr) LIKE '%performance%' OR LOWER(r.designationEn) LIKE '%performance%' THEN 'PERFORMANCE' " +
           "WHEN LOWER(r.designationFr) LIKE '%sécurité%' OR LOWER(r.designationEn) LIKE '%security%' THEN 'SECURITY' " +
           "WHEN LOWER(r.designationFr) LIKE '%conformité%' OR LOWER(r.designationEn) LIKE '%compliance%' THEN 'COMPLIANCE' " +
           "WHEN LOWER(r.designationFr) LIKE '%technique%' OR LOWER(r.designationEn) LIKE '%technical%' THEN 'TECHNICAL' " +
           "ELSE 'OTHER' " +
           "END, COUNT(r) " +
           "FROM Rubric r " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(r.designationFr) LIKE '%exigence%' OR LOWER(r.designationEn) LIKE '%requirement%' THEN 'REQUIREMENTS' " +
           "WHEN LOWER(r.designationFr) LIKE '%qualité%' OR LOWER(r.designationEn) LIKE '%quality%' THEN 'QUALITY' " +
           "WHEN LOWER(r.designationFr) LIKE '%performance%' OR LOWER(r.designationEn) LIKE '%performance%' THEN 'PERFORMANCE' " +
           "WHEN LOWER(r.designationFr) LIKE '%sécurité%' OR LOWER(r.designationEn) LIKE '%security%' THEN 'SECURITY' " +
           "WHEN LOWER(r.designationFr) LIKE '%conformité%' OR LOWER(r.designationEn) LIKE '%compliance%' THEN 'COMPLIANCE' " +
           "WHEN LOWER(r.designationFr) LIKE '%technique%' OR LOWER(r.designationEn) LIKE '%technical%' THEN 'TECHNICAL' " +
           "ELSE 'OTHER' " +
           "END")
    List<Object[]> getRubricStatisticsByCategory();

    /**
     * Get rubric statistics by complexity
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN SIZE(r.items) = 0 THEN 'NO_ITEMS' " +
           "WHEN SIZE(r.items) <= 3 THEN 'LOW_COMPLEXITY' " +
           "WHEN SIZE(r.items) <= 8 THEN 'MEDIUM_COMPLEXITY' " +
           "WHEN SIZE(r.items) <= 15 THEN 'HIGH_COMPLEXITY' " +
           "ELSE 'VERY_HIGH_COMPLEXITY' " +
           "END, COUNT(r) " +
           "FROM Rubric r " +
           "GROUP BY " +
           "CASE " +
           "WHEN SIZE(r.items) = 0 THEN 'NO_ITEMS' " +
           "WHEN SIZE(r.items) <= 3 THEN 'LOW_COMPLEXITY' " +
           "WHEN SIZE(r.items) <= 8 THEN 'MEDIUM_COMPLEXITY' " +
           "WHEN SIZE(r.items) <= 15 THEN 'HIGH_COMPLEXITY' " +
           "ELSE 'VERY_HIGH_COMPLEXITY' " +
           "END")
    List<Object[]> getRubricStatisticsByComplexity();

    /**
     * Get rubric statistics by domain
     */
    @Query("SELECT r.domain.designationFr, COUNT(r) FROM Rubric r GROUP BY r.domain.designationFr ORDER BY COUNT(r) DESC")
    List<Object[]> getRubricStatisticsByDomain();

    /**
     * Find most recently added rubrics
     */
    @Query("SELECT r FROM Rubric r ORDER BY r.id DESC")
    Page<Rubric> findMostRecentRubrics(Pageable pageable);

    /**
     * Find rubrics with most items
     */
    @Query("SELECT r FROM Rubric r WHERE SIZE(r.items) > 0 ORDER BY SIZE(r.items) DESC, r.designationFr ASC")
    Page<Rubric> findRubricsWithMostItems(Pageable pageable);

    /**
     * Find rubrics by priority level (based on category keywords)
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "(:priority = 'CRITICAL' AND " +
           "(LOWER(r.designationFr) LIKE '%sécurité%' OR LOWER(r.designationFr) LIKE '%conformité%' OR " +
           "LOWER(r.designationEn) LIKE '%security%' OR LOWER(r.designationEn) LIKE '%compliance%')) OR " +
           "(:priority = 'HIGH' AND " +
           "(LOWER(r.designationFr) LIKE '%exigence%' OR LOWER(r.designationFr) LIKE '%qualité%' OR " +
           "LOWER(r.designationEn) LIKE '%requirement%' OR LOWER(r.designationEn) LIKE '%quality%')) OR " +
           "(:priority = 'MEDIUM' AND " +
           "(LOWER(r.designationFr) LIKE '%performance%' OR LOWER(r.designationFr) LIKE '%technique%' OR " +
           "LOWER(r.designationEn) LIKE '%performance%' OR LOWER(r.designationEn) LIKE '%technical%'))")
    Page<Rubric> findByPriorityLevel(@Param("priority") String priority, Pageable pageable);

    /**
     * Find rubrics requiring critical attention (security, compliance)
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.designationFr) LIKE '%sécurité%' OR LOWER(r.designationFr) LIKE '%conformité%' OR " +
           "LOWER(r.designationFr) LIKE '%audit%' OR " +
           "LOWER(r.designationEn) LIKE '%security%' OR LOWER(r.designationEn) LIKE '%compliance%' OR " +
           "LOWER(r.designationEn) LIKE '%audit%'")
    Page<Rubric> findRequiringCriticalAttention(Pageable pageable);

    /**
     * Get average items count per rubric
     */
    @Query("SELECT AVG(SIZE(r.items)) FROM Rubric r")
    Double getAverageItemsPerRubric();

    /**
     * Get maximum items count
     */
    @Query("SELECT MAX(SIZE(r.items)) FROM Rubric r")
    Integer getMaxItemsCount();

    /**
     * Get minimum items count (excluding zero)
     */
    @Query("SELECT MIN(SIZE(r.items)) FROM Rubric r WHERE SIZE(r.items) > 0")
    Integer getMinItemsCountExcludingZero();

    /**
     * Find rubrics by domain category
     */
    @Query("SELECT r FROM Rubric r WHERE " +
           "LOWER(r.domain.designationFr) LIKE LOWER(CONCAT('%', :category, '%')) OR " +
           "LOWER(r.domain.designationEn) LIKE LOWER(CONCAT('%', :category, '%')) " +
           "ORDER BY r.domain.designationFr ASC, r.designationFr ASC")
    Page<Rubric> findByDomainCategory(@Param("category") String category, Pageable pageable);
}
