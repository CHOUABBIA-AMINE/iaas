/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemRepository
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

import dz.mdn.iaas.business.plan.model.PlannedItem;

/**
 * PlannedItem Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designation, F_02=unitairCost, F_03=planedQuantity, 
 * F_04=allocatedAmount, F_05=itemStatus, F_06=item, F_07=financialOperation, F_08=budgetModification
 * Includes multiple many-to-one relationships and one-to-many relationship with ItemDistributions
 */
@Repository
public interface PlannedItemRepository extends JpaRepository<PlannedItem, Long> {

    /**
     * Find all planned items ordered by designation
     */
    @Query("SELECT p FROM PlannedItem p ORDER BY p.designation ASC")
    Page<PlannedItem> findAllOrderByDesignation(Pageable pageable);

    /**
     * Find planned items by item
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.item.id = :itemId ORDER BY p.designation ASC")
    Page<PlannedItem> findByItem(@Param("itemId") Long itemId, Pageable pageable);

    /**
     * Find planned items by item status
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.itemStatus.id = :itemStatusId ORDER BY p.designation ASC")
    Page<PlannedItem> findByItemStatus(@Param("itemStatusId") Long itemStatusId, Pageable pageable);

    /**
     * Find planned items by financial operation
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.financialOperation.id = :financialOperationId ORDER BY p.designation ASC")
    Page<PlannedItem> findByFinancialOperation(@Param("financialOperationId") Long financialOperationId, Pageable pageable);

    /**
     * Find planned items by budget modification
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.budgetModification.id = :budgetModificationId ORDER BY p.designation ASC")
    Page<PlannedItem> findByBudgetModification(@Param("budgetModificationId") Long budgetModificationId, Pageable pageable);

    /**
     * Search planned items by designation
     */
    @Query("SELECT p FROM PlannedItem p WHERE LOWER(p.designation) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<PlannedItem> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find planned items with item distributions (has child distributions)
     */
    @Query("SELECT DISTINCT p FROM PlannedItem p JOIN p.itemDistribution id ORDER BY p.designation ASC")
    Page<PlannedItem> findPlannedItemsWithDistributions(Pageable pageable);

    /**
     * Find planned items without item distributions (no child distributions)
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.itemDistribution IS EMPTY ORDER BY p.designation ASC")
    Page<PlannedItem> findPlannedItemsWithoutDistributions(Pageable pageable);

    /**
     * Find planned items by distributions count range
     */
    @Query("SELECT p FROM PlannedItem p WHERE SIZE(p.itemDistribution) BETWEEN :minCount AND :maxCount ORDER BY SIZE(p.itemDistribution) DESC, p.designation ASC")
    Page<PlannedItem> findByDistributionsCountRange(@Param("minCount") int minCount, @Param("maxCount") int maxCount, Pageable pageable);

    /**
     * Find planned items by cost range
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.unitairCost BETWEEN :minCost AND :maxCost ORDER BY p.unitairCost ASC")
    Page<PlannedItem> findByCostRange(@Param("minCost") double minCost, @Param("maxCost") double maxCost, Pageable pageable);

    /**
     * Find planned items by quantity range
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.planedQuantity BETWEEN :minQuantity AND :maxQuantity ORDER BY p.planedQuantity DESC")
    Page<PlannedItem> findByQuantityRange(@Param("minQuantity") double minQuantity, @Param("maxQuantity") double maxQuantity, Pageable pageable);

    /**
     * Find planned items by allocated amount range
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.allocatedAmount BETWEEN :minAmount AND :maxAmount ORDER BY p.allocatedAmount DESC")
    Page<PlannedItem> findByAllocatedAmountRange(@Param("minAmount") double minAmount, @Param("maxAmount") double maxAmount, Pageable pageable);

    /**
     * Find high cost planned items
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.unitairCost > 1000 ORDER BY p.unitairCost DESC")
    Page<PlannedItem> findHighCostItems(Pageable pageable);

    /**
     * Find large quantity planned items
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.planedQuantity > 100 ORDER BY p.planedQuantity DESC")
    Page<PlannedItem> findLargeQuantityItems(Pageable pageable);

    /**
     * Find over-budget planned items (where total cost > allocated amount)
     */
    @Query("SELECT p FROM PlannedItem p WHERE (p.unitairCost * p.planedQuantity) > p.allocatedAmount ORDER BY ((p.unitairCost * p.planedQuantity) - p.allocatedAmount) DESC")
    Page<PlannedItem> findOverBudgetItems(Pageable pageable);

    /**
     * Find under-budget planned items (where total cost < allocated amount)
     */
    @Query("SELECT p FROM PlannedItem p WHERE (p.unitairCost * p.planedQuantity) < p.allocatedAmount ORDER BY (p.allocatedAmount - (p.unitairCost * p.planedQuantity)) DESC")
    Page<PlannedItem> findUnderBudgetItems(Pageable pageable);

    /**
     * Find well-budgeted planned items (where total cost is within 10% of allocated amount)
     */
    @Query("SELECT p FROM PlannedItem p WHERE ABS((p.unitairCost * p.planedQuantity) - p.allocatedAmount) <= (p.allocatedAmount * 0.1) ORDER BY p.designation ASC")
    Page<PlannedItem> findWellBudgetedItems(Pageable pageable);

    /**
     * Find planned items by rubric (through item)
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.item.rubric.id = :rubricId ORDER BY p.designation ASC")
    Page<PlannedItem> findByRubric(@Param("rubricId") Long rubricId, Pageable pageable);

    /**
     * Find planned items by domain (through item → rubric)
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.item.rubric.domain.id = :domainId ORDER BY p.designation ASC")
    Page<PlannedItem> findByDomain(@Param("domainId") Long domainId, Pageable pageable);

    /**
     * Find planned items by budget type (through financial operation)
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.financialOperation.budgetType.id = :budgetTypeId ORDER BY p.designation ASC")
    Page<PlannedItem> findByBudgetType(@Param("budgetTypeId") Long budgetTypeId, Pageable pageable);

    /**
     * Find planned items without budget modification
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.budgetModification IS NULL ORDER BY p.designation ASC")
    Page<PlannedItem> findWithoutBudgetModification(Pageable pageable);

    /**
     * Find planned items with budget modification
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.budgetModification IS NOT NULL ORDER BY p.designation ASC")
    Page<PlannedItem> findWithBudgetModification(Pageable pageable);

    /**
     * Find planned items by total cost calculation (unit cost * quantity)
     */
    @Query("SELECT p FROM PlannedItem p WHERE (p.unitairCost * p.planedQuantity) BETWEEN :minTotal AND :maxTotal ORDER BY (p.unitairCost * p.planedQuantity) DESC")
    Page<PlannedItem> findByTotalCostRange(@Param("minTotal") double minTotal, @Param("maxTotal") double maxTotal, Pageable pageable);

    /**
     * Find most expensive planned items (by total cost)
     */
    @Query("SELECT p FROM PlannedItem p ORDER BY (p.unitairCost * p.planedQuantity) DESC")
    Page<PlannedItem> findMostExpensiveItems(Pageable pageable);

    /**
     * Find items requiring immediate attention (over budget by more than 20%)
     */
    @Query("SELECT p FROM PlannedItem p WHERE ((p.unitairCost * p.planedQuantity) - p.allocatedAmount) > (p.allocatedAmount * 0.2) ORDER BY ((p.unitairCost * p.planedQuantity) - p.allocatedAmount) DESC")
    Page<PlannedItem> findRequiringImmediateAttention(Pageable pageable);

    /**
     * Count planned items by item
     */
    @Query("SELECT COUNT(p) FROM PlannedItem p WHERE p.item.id = :itemId")
    Long countByItem(@Param("itemId") Long itemId);

    /**
     * Count planned items by item status
     */
    @Query("SELECT COUNT(p) FROM PlannedItem p WHERE p.itemStatus.id = :itemStatusId")
    Long countByItemStatus(@Param("itemStatusId") Long itemStatusId);

    /**
     * Count planned items by financial operation
     */
    @Query("SELECT COUNT(p) FROM PlannedItem p WHERE p.financialOperation.id = :financialOperationId")
    Long countByFinancialOperation(@Param("financialOperationId") Long financialOperationId);

    /**
     * Count total planned items
     */
    @Query("SELECT COUNT(p) FROM PlannedItem p")
    Long countAllPlannedItems();

    /**
     * Count planned items with distributions
     */
    @Query("SELECT COUNT(DISTINCT p) FROM PlannedItem p JOIN p.itemDistribution id")
    Long countPlannedItemsWithDistributions();

    /**
     * Count planned items without distributions
     */
    @Query("SELECT COUNT(p) FROM PlannedItem p WHERE p.itemDistribution IS EMPTY")
    Long countPlannedItemsWithoutDistributions();

    /**
     * Get sum of allocated amounts
     */
    @Query("SELECT SUM(p.allocatedAmount) FROM PlannedItem p")
    Double getSumAllocatedAmount();

    /**
     * Get sum of total costs (unit cost * quantity)
     */
    @Query("SELECT SUM(p.unitairCost * p.planedQuantity) FROM PlannedItem p")
    Double getSumTotalCost();

    /**
     * Get average unit cost
     */
    @Query("SELECT AVG(p.unitairCost) FROM PlannedItem p")
    Double getAverageUnitCost();

    /**
     * Get average planned quantity
     */
    @Query("SELECT AVG(p.planedQuantity) FROM PlannedItem p")
    Double getAveragePlannedQuantity();

    /**
     * Get average allocated amount
     */
    @Query("SELECT AVG(p.allocatedAmount) FROM PlannedItem p")
    Double getAverageAllocatedAmount();

    /**
     * Get maximum unit cost
     */
    @Query("SELECT MAX(p.unitairCost) FROM PlannedItem p")
    Double getMaxUnitCost();

    /**
     * Get maximum planned quantity
     */
    @Query("SELECT MAX(p.planedQuantity) FROM PlannedItem p")
    Double getMaxPlannedQuantity();

    /**
     * Get maximum allocated amount
     */
    @Query("SELECT MAX(p.allocatedAmount) FROM PlannedItem p")
    Double getMaxAllocatedAmount();

    /**
     * Get average distributions count per planned item
     */
    @Query("SELECT AVG(SIZE(p.itemDistribution)) FROM PlannedItem p")
    Double getAverageDistributionsPerPlannedItem();

    /**
     * Get maximum distributions count
     */
    @Query("SELECT MAX(SIZE(p.itemDistribution)) FROM PlannedItem p")
    Integer getMaxDistributionsCount();

    /**
     * Get planned item statistics by cost category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN p.unitairCost <= 100 THEN 'LOW_COST' " +
           "WHEN p.unitairCost <= 1000 THEN 'MEDIUM_COST' " +
           "WHEN p.unitairCost <= 10000 THEN 'HIGH_COST' " +
           "ELSE 'VERY_HIGH_COST' " +
           "END, COUNT(p) " +
           "FROM PlannedItem p " +
           "GROUP BY " +
           "CASE " +
           "WHEN p.unitairCost <= 100 THEN 'LOW_COST' " +
           "WHEN p.unitairCost <= 1000 THEN 'MEDIUM_COST' " +
           "WHEN p.unitairCost <= 10000 THEN 'HIGH_COST' " +
           "ELSE 'VERY_HIGH_COST' " +
           "END")
    List<Object[]> getPlannedItemStatisticsByCostCategory();

    /**
     * Get planned item statistics by quantity scale
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN p.planedQuantity <= 10 THEN 'SMALL_SCALE' " +
           "WHEN p.planedQuantity <= 100 THEN 'MEDIUM_SCALE' " +
           "WHEN p.planedQuantity <= 1000 THEN 'LARGE_SCALE' " +
           "ELSE 'VERY_LARGE_SCALE' " +
           "END, COUNT(p) " +
           "FROM PlannedItem p " +
           "GROUP BY " +
           "CASE " +
           "WHEN p.planedQuantity <= 10 THEN 'SMALL_SCALE' " +
           "WHEN p.planedQuantity <= 100 THEN 'MEDIUM_SCALE' " +
           "WHEN p.planedQuantity <= 1000 THEN 'LARGE_SCALE' " +
           "ELSE 'VERY_LARGE_SCALE' " +
           "END")
    List<Object[]> getPlannedItemStatisticsByQuantityScale();

    /**
     * Get planned item statistics by item status
     */
    @Query("SELECT p.itemStatus.designationFr, COUNT(p) FROM PlannedItem p GROUP BY p.itemStatus.designationFr ORDER BY COUNT(p) DESC")
    List<Object[]> getPlannedItemStatisticsByItemStatus();

    /**
     * Find most recently added planned items
     */
    @Query("SELECT p FROM PlannedItem p ORDER BY p.id DESC")
    Page<PlannedItem> findMostRecentPlannedItems(Pageable pageable);

    /**
     * Find planned items with most distributions
     */
    @Query("SELECT p FROM PlannedItem p WHERE SIZE(p.itemDistribution) > 0 ORDER BY SIZE(p.itemDistribution) DESC, p.designation ASC")
    Page<PlannedItem> findPlannedItemsWithMostDistributions(Pageable pageable);

    /**
     * Find planned items by budget variance (difference between allocated and actual cost)
     */
    @Query("SELECT p FROM PlannedItem p WHERE ABS((p.unitairCost * p.planedQuantity) - p.allocatedAmount) > :varianceThreshold ORDER BY ABS((p.unitairCost * p.planedQuantity) - p.allocatedAmount) DESC")
    Page<PlannedItem> findByBudgetVariance(@Param("varianceThreshold") double varianceThreshold, Pageable pageable);

    /**
     * Get budget utilization statistics
     */
    @Query("SELECT " +
           "COUNT(p), " +
           "AVG((p.unitairCost * p.planedQuantity) / p.allocatedAmount), " +
           "MIN((p.unitairCost * p.planedQuantity) / p.allocatedAmount), " +
           "MAX((p.unitairCost * p.planedQuantity) / p.allocatedAmount) " +
           "FROM PlannedItem p WHERE p.allocatedAmount > 0")
    List<Object[]> getBudgetUtilizationStatistics();

    /**
     * Find planned items by current year (based on creation or modification date if available)
     */
    @Query("SELECT p FROM PlannedItem p ORDER BY p.id DESC")
    Page<PlannedItem> findCurrentYearPlannedItems(Pageable pageable);

    /**
     * Find critical planned items (high cost and large quantity)
     */
    @Query("SELECT p FROM PlannedItem p WHERE p.unitairCost > 1000 AND p.planedQuantity > 50 ORDER BY (p.unitairCost * p.planedQuantity) DESC")
    Page<PlannedItem> findCriticalPlannedItems(Pageable pageable);
}