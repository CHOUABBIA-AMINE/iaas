/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetTypeRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.BudgetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Budget Type Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique), 
 * F_04=acronymAr, F_05=acronymEn, F_06=acronymFr (unique)
 */
@Repository
public interface BudgetTypeRepository extends JpaRepository<BudgetType, Long> {

    /**
     * Find budget type by French designation (F_03) - unique constraint
     */
    @Query("SELECT bt FROM BudgetType bt WHERE bt.designationFr = :designationFr")
    Optional<BudgetType> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find budget type by French acronym (F_06) - unique constraint
     */
    @Query("SELECT bt FROM BudgetType bt WHERE bt.acronymFr = :acronymFr")
    Optional<BudgetType> findByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Find all budget types ordered by French designation
     */
    @Query("SELECT bt FROM BudgetType bt ORDER BY bt.designationFr ASC")
    Page<BudgetType> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search budget types by designation (any language)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BudgetType> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Search budget types by acronym (any language)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.acronymFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.acronymEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.acronymAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BudgetType> searchByAcronym(@Param("search") String search, Pageable pageable);

    /**
     * Search budget types by any field
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.designationAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.acronymFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.acronymEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bt.acronymAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BudgetType> searchByAnyField(@Param("search") String search, Pageable pageable);

    /**
     * Find investment budget types (based on designation patterns)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%investissement%' OR " +
           "LOWER(bt.designationEn) LIKE '%investment%' OR " +
           "LOWER(bt.designationFr) LIKE '%capital%' OR " +
           "LOWER(bt.designationEn) LIKE '%capital%' OR " +
           "LOWER(bt.designationFr) LIKE '%équipement%' OR " +
           "LOWER(bt.designationEn) LIKE '%equipment%'")
    Page<BudgetType> findInvestmentBudgetTypes(Pageable pageable);

    /**
     * Find operating budget types
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%fonctionnement%' OR " +
           "LOWER(bt.designationEn) LIKE '%operating%' OR " +
           "LOWER(bt.designationEn) LIKE '%operational%' OR " +
           "LOWER(bt.designationFr) LIKE '%exploitation%'")
    Page<BudgetType> findOperatingBudgetTypes(Pageable pageable);

    /**
     * Find personnel budget types
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%personnel%' OR " +
           "LOWER(bt.designationEn) LIKE '%personnel%' OR " +
           "LOWER(bt.designationFr) LIKE '%salaire%' OR " +
           "LOWER(bt.designationEn) LIKE '%salary%' OR " +
           "LOWER(bt.designationEn) LIKE '%wages%'")
    Page<BudgetType> findPersonnelBudgetTypes(Pageable pageable);

    /**
     * Find maintenance budget types
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%maintenance%' OR " +
           "LOWER(bt.designationEn) LIKE '%maintenance%' OR " +
           "LOWER(bt.designationFr) LIKE '%entretien%' OR " +
           "LOWER(bt.designationFr) LIKE '%réparation%' OR " +
           "LOWER(bt.designationEn) LIKE '%repair%'")
    Page<BudgetType> findMaintenanceBudgetTypes(Pageable pageable);

    /**
     * Find research & development budget types
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%recherche%' OR " +
           "LOWER(bt.designationEn) LIKE '%research%' OR " +
           "LOWER(bt.designationFr) LIKE '%développement%' OR " +
           "LOWER(bt.designationEn) LIKE '%development%' OR " +
           "LOWER(bt.designationFr) LIKE '%innovation%' OR " +
           "LOWER(bt.designationEn) LIKE '%innovation%'")
    Page<BudgetType> findResearchDevelopmentBudgetTypes(Pageable pageable);

    /**
     * Find defense budget types
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%défense%' OR " +
           "LOWER(bt.designationEn) LIKE '%defense%' OR " +
           "LOWER(bt.designationFr) LIKE '%militaire%' OR " +
           "LOWER(bt.designationEn) LIKE '%military%' OR " +
           "LOWER(bt.designationFr) LIKE '%sécurité%' OR " +
           "LOWER(bt.designationEn) LIKE '%security%'")
    Page<BudgetType> findDefenseBudgetTypes(Pageable pageable);

    /**
     * Find training budget types
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%formation%' OR " +
           "LOWER(bt.designationEn) LIKE '%training%' OR " +
           "LOWER(bt.designationFr) LIKE '%éducation%' OR " +
           "LOWER(bt.designationEn) LIKE '%education%'")
    Page<BudgetType> findTrainingBudgetTypes(Pageable pageable);

    /**
     * Find emergency budget types
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%urgence%' OR " +
           "LOWER(bt.designationEn) LIKE '%emergency%' OR " +
           "LOWER(bt.designationFr) LIKE '%contingence%' OR " +
           "LOWER(bt.designationEn) LIKE '%contingency%'")
    Page<BudgetType> findEmergencyBudgetTypes(Pageable pageable);

    /**
     * Find budget types by scope (national, regional, local)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE LOWER(CONCAT('%', :scope, '%')) OR " +
           "LOWER(bt.designationEn) LIKE LOWER(CONCAT('%', :scope, '%'))")
    Page<BudgetType> findByScope(@Param("scope") String scope, Pageable pageable);

    /**
     * Find multilingual budget types (have designations in multiple languages)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "bt.designationFr IS NOT NULL AND bt.designationFr != '' AND " +
           "(bt.designationEn IS NOT NULL AND bt.designationEn != '' OR " +
           "bt.designationAr IS NOT NULL AND bt.designationAr != '')")
    Page<BudgetType> findMultilingualBudgetTypes(Pageable pageable);

    /**
     * Find budget types with Arabic designations
     */
    @Query("SELECT bt FROM BudgetType bt WHERE bt.designationAr IS NOT NULL AND bt.designationAr != ''")
    Page<BudgetType> findWithArabicDesignation(Pageable pageable);

    /**
     * Find budget types with English designations
     */
    @Query("SELECT bt FROM BudgetType bt WHERE bt.designationEn IS NOT NULL AND bt.designationEn != ''")
    Page<BudgetType> findWithEnglishDesignation(Pageable pageable);

    /**
     * Check if French designation exists (for uniqueness validation)
     */
    @Query("SELECT CASE WHEN COUNT(bt) > 0 THEN true ELSE false END FROM BudgetType bt WHERE bt.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if French designation exists excluding current ID (for update validation)
     */
    @Query("SELECT CASE WHEN COUNT(bt) > 0 THEN true ELSE false END FROM BudgetType bt WHERE bt.designationFr = :designationFr AND bt.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Check if French acronym exists (for uniqueness validation)
     */
    @Query("SELECT CASE WHEN COUNT(bt) > 0 THEN true ELSE false END FROM BudgetType bt WHERE bt.acronymFr = :acronymFr")
    boolean existsByAcronymFr(@Param("acronymFr") String acronymFr);

    /**
     * Check if French acronym exists excluding current ID (for update validation)
     */
    @Query("SELECT CASE WHEN COUNT(bt) > 0 THEN true ELSE false END FROM BudgetType bt WHERE bt.acronymFr = :acronymFr AND bt.id != :id")
    boolean existsByAcronymFrAndIdNot(@Param("acronymFr") String acronymFr, @Param("id") Long id);

    /**
     * Find budget types by designation pattern (French)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE LOWER(bt.designationFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<BudgetType> findByDesignationFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find budget types by acronym pattern (French)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE LOWER(bt.acronymFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<BudgetType> findByAcronymFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find budget types with complete information (all required fields filled)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "bt.designationFr IS NOT NULL AND bt.designationFr != '' AND " +
           "bt.acronymFr IS NOT NULL AND bt.acronymFr != ''")
    Page<BudgetType> findWithCompleteInformation(Pageable pageable);

    /**
     * Find budget types by priority level (based on keywords)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%défense%' OR LOWER(bt.designationFr) LIKE '%urgence%' OR " +
           "LOWER(bt.designationEn) LIKE '%defense%' OR LOWER(bt.designationEn) LIKE '%emergency%'")
    Page<BudgetType> findCriticalPriorityBudgetTypes(Pageable pageable);

    /**
     * Find budget types by planning cycle
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%annuel%' OR LOWER(bt.designationEn) LIKE '%annual%' OR " +
           "LOWER(bt.designationFr) LIKE '%mensuel%' OR LOWER(bt.designationEn) LIKE '%monthly%' OR " +
           "LOWER(bt.designationFr) LIKE '%trimestriel%' OR LOWER(bt.designationEn) LIKE '%quarterly%'")
    Page<BudgetType> findByPlanningCycle(Pageable pageable);

    /**
     * Count total budget types
     */
    @Query("SELECT COUNT(bt) FROM BudgetType bt")
    Long countAllBudgetTypes();

    /**
     * Count investment budget types
     */
    @Query("SELECT COUNT(bt) FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%investissement%' OR " +
           "LOWER(bt.designationEn) LIKE '%investment%' OR " +
           "LOWER(bt.designationFr) LIKE '%capital%'")
    Long countInvestmentBudgetTypes();

    /**
     * Count operating budget types
     */
    @Query("SELECT COUNT(bt) FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%fonctionnement%' OR " +
           "LOWER(bt.designationEn) LIKE '%operating%'")
    Long countOperatingBudgetTypes();

    /**
     * Count defense budget types
     */
    @Query("SELECT COUNT(bt) FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%défense%' OR " +
           "LOWER(bt.designationEn) LIKE '%defense%' OR " +
           "LOWER(bt.designationFr) LIKE '%militaire%' OR " +
           "LOWER(bt.designationEn) LIKE '%military%'")
    Long countDefenseBudgetTypes();

    /**
     * Find similar budget types (for duplicate detection)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) = LOWER(:designation) OR " +
           "LOWER(bt.acronymFr) = LOWER(:acronym)")
    List<BudgetType> findSimilarBudgetTypes(@Param("designation") String designation, @Param("acronym") String acronym);

    /**
     * Find budget types requiring approval (critical and high priority)
     */
    @Query("SELECT bt FROM BudgetType bt WHERE " +
           "LOWER(bt.designationFr) LIKE '%défense%' OR LOWER(bt.designationFr) LIKE '%urgence%' OR " +
           "LOWER(bt.designationFr) LIKE '%personnel%' OR LOWER(bt.designationFr) LIKE '%investissement%' OR " +
           "LOWER(bt.designationEn) LIKE '%defense%' OR LOWER(bt.designationEn) LIKE '%emergency%' OR " +
           "LOWER(bt.designationEn) LIKE '%personnel%' OR LOWER(bt.designationEn) LIKE '%investment%'")
    Page<BudgetType> findRequiringApproval(Pageable pageable);

    /**
     * Get budget type statistics by category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(bt.designationFr) LIKE '%investissement%' OR LOWER(bt.designationEn) LIKE '%investment%' THEN 'INVESTMENT' " +
           "WHEN LOWER(bt.designationFr) LIKE '%fonctionnement%' OR LOWER(bt.designationEn) LIKE '%operating%' THEN 'OPERATING' " +
           "WHEN LOWER(bt.designationFr) LIKE '%personnel%' OR LOWER(bt.designationEn) LIKE '%personnel%' THEN 'PERSONNEL' " +
           "WHEN LOWER(bt.designationFr) LIKE '%défense%' OR LOWER(bt.designationEn) LIKE '%defense%' THEN 'DEFENSE' " +
           "WHEN LOWER(bt.designationFr) LIKE '%maintenance%' OR LOWER(bt.designationEn) LIKE '%maintenance%' THEN 'MAINTENANCE' " +
           "ELSE 'OTHER' " +
           "END, COUNT(bt) " +
           "FROM BudgetType bt " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(bt.designationFr) LIKE '%investissement%' OR LOWER(bt.designationEn) LIKE '%investment%' THEN 'INVESTMENT' " +
           "WHEN LOWER(bt.designationFr) LIKE '%fonctionnement%' OR LOWER(bt.designationEn) LIKE '%operating%' THEN 'OPERATING' " +
           "WHEN LOWER(bt.designationFr) LIKE '%personnel%' OR LOWER(bt.designationEn) LIKE '%personnel%' THEN 'PERSONNEL' " +
           "WHEN LOWER(bt.designationFr) LIKE '%défense%' OR LOWER(bt.designationEn) LIKE '%defense%' THEN 'DEFENSE' " +
           "WHEN LOWER(bt.designationFr) LIKE '%maintenance%' OR LOWER(bt.designationEn) LIKE '%maintenance%' THEN 'MAINTENANCE' " +
           "ELSE 'OTHER' " +
           "END")
    List<Object[]> getBudgetTypeStatisticsByCategory();

    /**
     * Find most recently added budget types
     */
    @Query("SELECT bt FROM BudgetType bt ORDER BY bt.id DESC")
    Page<BudgetType> findMostRecentBudgetTypes(Pageable pageable);
}
