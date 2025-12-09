/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.business.plan.model.Item;

/**
 * Item Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=rubric
 * Includes many-to-one relationship with Rubric and one-to-many relationship with PlannedItems
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Find all items ordered by rubric designation, then by item designation
     */
    @Query("SELECT i FROM Item i ORDER BY i.rubric.designationFr ASC, i.designationFr ASC")
    Page<Item> findAllOrderByRubricAndDesignation(Pageable pageable);

    /**
     * Find items by rubric
     */
    @Query("SELECT i FROM Item i WHERE i.rubric.id = :rubricId ORDER BY i.designationFr ASC")
    Page<Item> findByRubric(@Param("rubricId") Long rubricId, Pageable pageable);

    /**
     * Search items by designation (any language)
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(i.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(i.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Item> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find items with planned items (has child planned items)
     */
    @Query("SELECT DISTINCT i FROM Item i JOIN i.plannedItems pi ORDER BY i.rubric.designationFr ASC, i.designationFr ASC")
    Page<Item> findItemsWithPlannedItems(Pageable pageable);

    /**
     * Find items without planned items (no child planned items)
     */
    @Query("SELECT i FROM Item i WHERE i.plannedItems IS EMPTY ORDER BY i.rubric.designationFr ASC, i.designationFr ASC")
    Page<Item> findItemsWithoutPlannedItems(Pageable pageable);

    /**
     * Find items by planned items count range
     */
    @Query("SELECT i FROM Item i WHERE SIZE(i.plannedItems) BETWEEN :minCount AND :maxCount ORDER BY SIZE(i.plannedItems) DESC, i.designationFr ASC")
    Page<Item> findByPlannedItemsCountRange(@Param("minCount") int minCount, @Param("maxCount") int maxCount, Pageable pageable);

    /**
     * Find items by minimum planned items count
     */
    @Query("SELECT i FROM Item i WHERE SIZE(i.plannedItems) >= :minCount ORDER BY SIZE(i.plannedItems) DESC, i.designationFr ASC")
    Page<Item> findByMinPlannedItemsCount(@Param("minCount") int minCount, Pageable pageable);

    /**
     * Find equipment items (based on designation patterns)
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%équipement%' OR " +
           "LOWER(i.designationEn) LIKE '%equipment%' OR " +
           "LOWER(i.designationFr) LIKE '%matériel%' OR " +
           "LOWER(i.designationEn) LIKE '%material%' OR " +
           "LOWER(i.designationFr) LIKE '%outil%' OR " +
           "LOWER(i.designationEn) LIKE '%tool%'")
    Page<Item> findEquipmentItems(Pageable pageable);

    /**
     * Find resource items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%ressource%' OR " +
           "LOWER(i.designationEn) LIKE '%resource%' OR " +
           "LOWER(i.designationFr) LIKE '%personnel%' OR " +
           "LOWER(i.designationEn) LIKE '%personnel%' OR " +
           "LOWER(i.designationFr) LIKE '%humain%' OR " +
           "LOWER(i.designationEn) LIKE '%human%'")
    Page<Item> findResourceItems(Pageable pageable);

    /**
     * Find service items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%service%' OR " +
           "LOWER(i.designationEn) LIKE '%service%' OR " +
           "LOWER(i.designationFr) LIKE '%prestation%' OR " +
           "LOWER(i.designationEn) LIKE '%assistance%' OR " +
           "LOWER(i.designationFr) LIKE '%support%' OR " +
           "LOWER(i.designationEn) LIKE '%support%'")
    Page<Item> findServiceItems(Pageable pageable);

    /**
     * Find infrastructure items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%infrastructure%' OR " +
           "LOWER(i.designationEn) LIKE '%infrastructure%' OR " +
           "LOWER(i.designationFr) LIKE '%installation%' OR " +
           "LOWER(i.designationEn) LIKE '%facility%' OR " +
           "LOWER(i.designationFr) LIKE '%bâtiment%' OR " +
           "LOWER(i.designationEn) LIKE '%building%'")
    Page<Item> findInfrastructureItems(Pageable pageable);

    /**
     * Find technology items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%technologie%' OR " +
           "LOWER(i.designationEn) LIKE '%technology%' OR " +
           "LOWER(i.designationFr) LIKE '%logiciel%' OR " +
           "LOWER(i.designationEn) LIKE '%software%' OR " +
           "LOWER(i.designationFr) LIKE '%système%' OR " +
           "LOWER(i.designationEn) LIKE '%system%'")
    Page<Item> findTechnologyItems(Pageable pageable);

    /**
     * Find consumable items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%consommable%' OR " +
           "LOWER(i.designationEn) LIKE '%consumable%' OR " +
           "LOWER(i.designationFr) LIKE '%fourniture%' OR " +
           "LOWER(i.designationEn) LIKE '%supply%' OR " +
           "LOWER(i.designationFr) LIKE '%carburant%' OR " +
           "LOWER(i.designationEn) LIKE '%fuel%'")
    Page<Item> findConsumableItems(Pageable pageable);

    /**
     * Find training items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%formation%' OR " +
           "LOWER(i.designationEn) LIKE '%training%' OR " +
           "LOWER(i.designationFr) LIKE '%cours%' OR " +
           "LOWER(i.designationEn) LIKE '%course%' OR " +
           "LOWER(i.designationFr) LIKE '%apprentissage%' OR " +
           "LOWER(i.designationEn) LIKE '%learning%'")
    Page<Item> findTrainingItems(Pageable pageable);

    /**
     * Find document items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%document%' OR " +
           "LOWER(i.designationEn) LIKE '%document%' OR " +
           "LOWER(i.designationFr) LIKE '%manuel%' OR " +
           "LOWER(i.designationEn) LIKE '%manual%' OR " +
           "LOWER(i.designationFr) LIKE '%guide%' OR " +
           "LOWER(i.designationEn) LIKE '%guide%'")
    Page<Item> findDocumentItems(Pageable pageable);

    /**
     * Find vehicle items
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%véhicule%' OR " +
           "LOWER(i.designationEn) LIKE '%vehicle%' OR " +
           "LOWER(i.designationFr) LIKE '%transport%' OR " +
           "LOWER(i.designationEn) LIKE '%transport%' OR " +
           "LOWER(i.designationFr) LIKE '%camion%' OR " +
           "LOWER(i.designationEn) LIKE '%truck%'")
    Page<Item> findVehicleItems(Pageable pageable);

    /**
     * Find multilingual items (have designations in multiple languages)
     */
    @Query("SELECT i FROM Item i WHERE " +
           "i.designationFr IS NOT NULL AND i.designationFr != '' AND " +
           "(i.designationEn IS NOT NULL AND i.designationEn != '' OR " +
           "i.designationAr IS NOT NULL AND i.designationAr != '')")
    Page<Item> findMultilingualItems(Pageable pageable);

    /**
     * Find items with Arabic designations
     */
    @Query("SELECT i FROM Item i WHERE i.designationAr IS NOT NULL AND i.designationAr != ''")
    Page<Item> findWithArabicDesignation(Pageable pageable);

    /**
     * Find items with English designations
     */
    @Query("SELECT i FROM Item i WHERE i.designationEn IS NOT NULL AND i.designationEn != ''")
    Page<Item> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find items by designation pattern (French)
     */
    @Query("SELECT i FROM Item i WHERE LOWER(i.designationFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<Item> findByDesignationFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find items with complete information (all required fields filled)
     */
    @Query("SELECT i FROM Item i WHERE " +
           "i.designationFr IS NOT NULL AND i.designationFr != '' AND " +
           "i.rubric IS NOT NULL")
    Page<Item> findWithCompleteInformation(Pageable pageable);

    /**
     * Find complex planning items (many planned items)
     */
    @Query("SELECT i FROM Item i WHERE SIZE(i.plannedItems) > 5 ORDER BY SIZE(i.plannedItems) DESC, i.designationFr ASC")
    Page<Item> findComplexPlanningItems(Pageable pageable);

    /**
     * Find moderate planning items
     */
    @Query("SELECT i FROM Item i WHERE SIZE(i.plannedItems) BETWEEN 2 AND 5 ORDER BY SIZE(i.plannedItems) DESC, i.designationFr ASC")
    Page<Item> findModeratePlanningItems(Pageable pageable);

    /**
     * Find simple planning items
     */
    @Query("SELECT i FROM Item i WHERE SIZE(i.plannedItems) = 1 ORDER BY i.designationFr ASC")
    Page<Item> findSimplePlanningItems(Pageable pageable);

    /**
     * Count items by rubric
     */
    @Query("SELECT COUNT(i) FROM Item i WHERE i.rubric.id = :rubricId")
    Long countByRubric(@Param("rubricId") Long rubricId);

    /**
     * Count total items
     */
    @Query("SELECT COUNT(i) FROM Item i")
    Long countAllItems();

    /**
     * Count items with planned items
     */
    @Query("SELECT COUNT(DISTINCT i) FROM Item i JOIN i.plannedItems pi")
    Long countItemsWithPlannedItems();

    /**
     * Count items without planned items
     */
    @Query("SELECT COUNT(i) FROM Item i WHERE i.plannedItems IS EMPTY")
    Long countItemsWithoutPlannedItems();

    /**
     * Count equipment items
     */
    @Query("SELECT COUNT(i) FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%équipement%' OR " +
           "LOWER(i.designationEn) LIKE '%equipment%' OR " +
           "LOWER(i.designationFr) LIKE '%matériel%' OR " +
           "LOWER(i.designationEn) LIKE '%material%'")
    Long countEquipmentItems();

    /**
     * Count resource items
     */
    @Query("SELECT COUNT(i) FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%ressource%' OR " +
           "LOWER(i.designationEn) LIKE '%resource%' OR " +
           "LOWER(i.designationFr) LIKE '%personnel%' OR " +
           "LOWER(i.designationEn) LIKE '%personnel%'")
    Long countResourceItems();

    /**
     * Get item statistics by category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(i.designationFr) LIKE '%équipement%' OR LOWER(i.designationEn) LIKE '%equipment%' THEN 'EQUIPMENT' " +
           "WHEN LOWER(i.designationFr) LIKE '%ressource%' OR LOWER(i.designationEn) LIKE '%resource%' THEN 'RESOURCE' " +
           "WHEN LOWER(i.designationFr) LIKE '%service%' OR LOWER(i.designationEn) LIKE '%service%' THEN 'SERVICE' " +
           "WHEN LOWER(i.designationFr) LIKE '%infrastructure%' OR LOWER(i.designationEn) LIKE '%infrastructure%' THEN 'INFRASTRUCTURE' " +
           "WHEN LOWER(i.designationFr) LIKE '%technologie%' OR LOWER(i.designationEn) LIKE '%technology%' THEN 'TECHNOLOGY' " +
           "WHEN LOWER(i.designationFr) LIKE '%véhicule%' OR LOWER(i.designationEn) LIKE '%vehicle%' THEN 'VEHICLE' " +
           "ELSE 'OTHER' " +
           "END, COUNT(i) " +
           "FROM Item i " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(i.designationFr) LIKE '%équipement%' OR LOWER(i.designationEn) LIKE '%equipment%' THEN 'EQUIPMENT' " +
           "WHEN LOWER(i.designationFr) LIKE '%ressource%' OR LOWER(i.designationEn) LIKE '%resource%' THEN 'RESOURCE' " +
           "WHEN LOWER(i.designationFr) LIKE '%service%' OR LOWER(i.designationEn) LIKE '%service%' THEN 'SERVICE' " +
           "WHEN LOWER(i.designationFr) LIKE '%infrastructure%' OR LOWER(i.designationEn) LIKE '%infrastructure%' THEN 'INFRASTRUCTURE' " +
           "WHEN LOWER(i.designationFr) LIKE '%technologie%' OR LOWER(i.designationEn) LIKE '%technology%' THEN 'TECHNOLOGY' " +
           "WHEN LOWER(i.designationFr) LIKE '%véhicule%' OR LOWER(i.designationEn) LIKE '%vehicle%' THEN 'VEHICLE' " +
           "ELSE 'OTHER' " +
           "END")
    List<Object[]> getItemStatisticsByCategory();

    /**
     * Get item statistics by planning complexity
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN SIZE(i.plannedItems) = 0 THEN 'NO_PLANNING' " +
           "WHEN SIZE(i.plannedItems) <= 2 THEN 'SIMPLE_PLANNING' " +
           "WHEN SIZE(i.plannedItems) <= 5 THEN 'MODERATE_PLANNING' " +
           "WHEN SIZE(i.plannedItems) <= 10 THEN 'COMPLEX_PLANNING' " +
           "ELSE 'VERY_COMPLEX_PLANNING' " +
           "END, COUNT(i) " +
           "FROM Item i " +
           "GROUP BY " +
           "CASE " +
           "WHEN SIZE(i.plannedItems) = 0 THEN 'NO_PLANNING' " +
           "WHEN SIZE(i.plannedItems) <= 2 THEN 'SIMPLE_PLANNING' " +
           "WHEN SIZE(i.plannedItems) <= 5 THEN 'MODERATE_PLANNING' " +
           "WHEN SIZE(i.plannedItems) <= 10 THEN 'COMPLEX_PLANNING' " +
           "ELSE 'VERY_COMPLEX_PLANNING' " +
           "END")
    List<Object[]> getItemStatisticsByPlanningComplexity();

    /**
     * Get item statistics by rubric
     */
    @Query("SELECT i.rubric.designationFr, COUNT(i) FROM Item i GROUP BY i.rubric.designationFr ORDER BY COUNT(i) DESC")
    List<Object[]> getItemStatisticsByRubric();

    /**
     * Find most recently added items
     */
    @Query("SELECT i FROM Item i ORDER BY i.id DESC")
    Page<Item> findMostRecentItems(Pageable pageable);

    /**
     * Find items with most planned items
     */
    @Query("SELECT i FROM Item i WHERE SIZE(i.plannedItems) > 0 ORDER BY SIZE(i.plannedItems) DESC, i.designationFr ASC")
    Page<Item> findItemsWithMostPlannedItems(Pageable pageable);

    /**
     * Find items by priority level (based on category keywords)
     */
    @Query("SELECT i FROM Item i WHERE " +
           "(:priority = 'HIGH' AND " +
           "(LOWER(i.designationFr) LIKE '%équipement%' OR LOWER(i.designationFr) LIKE '%infrastructure%' OR " +
           "LOWER(i.designationEn) LIKE '%equipment%' OR LOWER(i.designationEn) LIKE '%infrastructure%')) OR " +
           "(:priority = 'MEDIUM' AND " +
           "(LOWER(i.designationFr) LIKE '%service%' OR LOWER(i.designationFr) LIKE '%véhicule%' OR " +
           "LOWER(i.designationEn) LIKE '%service%' OR LOWER(i.designationEn) LIKE '%vehicle%')) OR " +
           "(:priority = 'LOW' AND " +
           "(LOWER(i.designationFr) LIKE '%consommable%' OR " +
           "LOWER(i.designationEn) LIKE '%consumable%'))")
    Page<Item> findByPriorityLevel(@Param("priority") String priority, Pageable pageable);

    /**
     * Find high priority items (equipment, infrastructure)
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.designationFr) LIKE '%équipement%' OR LOWER(i.designationFr) LIKE '%infrastructure%' OR " +
           "LOWER(i.designationFr) LIKE '%ressource%' OR " +
           "LOWER(i.designationEn) LIKE '%equipment%' OR LOWER(i.designationEn) LIKE '%infrastructure%' OR " +
           "LOWER(i.designationEn) LIKE '%resource%'")
    Page<Item> findHighPriorityItems(Pageable pageable);

    /**
     * Get average planned items count per item
     */
    @Query("SELECT AVG(SIZE(i.plannedItems)) FROM Item i")
    Double getAveragePlannedItemsPerItem();

    /**
     * Get maximum planned items count
     */
    @Query("SELECT MAX(SIZE(i.plannedItems)) FROM Item i")
    Integer getMaxPlannedItemsCount();

    /**
     * Get minimum planned items count (excluding zero)
     */
    @Query("SELECT MIN(SIZE(i.plannedItems)) FROM Item i WHERE SIZE(i.plannedItems) > 0")
    Integer getMinPlannedItemsCountExcludingZero();

    /**
     * Find items by rubric category
     */
    @Query("SELECT i FROM Item i WHERE " +
           "LOWER(i.rubric.designationFr) LIKE LOWER(CONCAT('%', :category, '%')) OR " +
           "LOWER(i.rubric.designationEn) LIKE LOWER(CONCAT('%', :category, '%')) " +
           "ORDER BY i.rubric.designationFr ASC, i.designationFr ASC")
    Page<Item> findByRubricCategory(@Param("category") String category, Pageable pageable);

    /**
     * Find items by domain (through rubric relationship)
     */
    @Query("SELECT i FROM Item i WHERE i.rubric.domain.id = :domainId ORDER BY i.rubric.designationFr ASC, i.designationFr ASC")
    Page<Item> findByDomain(@Param("domainId") Long domainId, Pageable pageable);

    /**
     * Count items by domain
     */
    @Query("SELECT COUNT(i) FROM Item i WHERE i.rubric.domain.id = :domainId")
    Long countByDomain(@Param("domainId") Long domainId);

    /**
     * Find items requiring immediate planning (no planned items but high priority)
     */
    @Query("SELECT i FROM Item i WHERE SIZE(i.plannedItems) = 0 AND " +
           "(LOWER(i.designationFr) LIKE '%équipement%' OR LOWER(i.designationFr) LIKE '%infrastructure%' OR " +
           "LOWER(i.designationEn) LIKE '%equipment%' OR LOWER(i.designationEn) LIKE '%infrastructure%') " +
           "ORDER BY i.designationFr ASC")
    Page<Item> findRequiringImmediatePlanning(Pageable pageable);
}
