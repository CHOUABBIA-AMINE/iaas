/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionRepository
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

import dz.mdn.iaas.business.plan.model.ItemDistribution;

/**
 * ItemDistribution Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=quantity, F_02=plannedItem, F_03=structure
 * Includes many-to-one relationships with PlannedItem and Structure
 */
@Repository
public interface ItemDistributionRepository extends JpaRepository<ItemDistribution, Long> {

    /**
     * Find all item distributions ordered by structure, then by planned item
     */
    @Query("SELECT i FROM ItemDistribution i ORDER BY i.structure.designationFr ASC, i.plannedItem.designation ASC")
    Page<ItemDistribution> findAllOrderByStructureAndPlannedItem(Pageable pageable);

    /**
     * Find item distributions by planned item
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.id = :plannedItemId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByPlannedItem(@Param("plannedItemId") Long plannedItemId, Pageable pageable);

    /**
     * Find item distributions by structure
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.structure.id = :structureId ORDER BY i.plannedItem.designation ASC")
    Page<ItemDistribution> findByStructure(@Param("structureId") Long structureId, Pageable pageable);

    /**
     * Find item distributions by quantity range
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity BETWEEN :minQuantity AND :maxQuantity ORDER BY i.quantity DESC")
    Page<ItemDistribution> findByQuantityRange(@Param("minQuantity") float minQuantity, @Param("maxQuantity") float maxQuantity, Pageable pageable);

    /**
     * Find small distributions (quantity <= 10)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity <= 10 ORDER BY i.quantity ASC")
    Page<ItemDistribution> findSmallDistributions(Pageable pageable);

    /**
     * Find medium distributions (quantity between 10 and 50)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity > 10 AND i.quantity <= 50 ORDER BY i.quantity ASC")
    Page<ItemDistribution> findMediumDistributions(Pageable pageable);

    /**
     * Find large distributions (quantity between 50 and 100)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity > 50 AND i.quantity <= 100 ORDER BY i.quantity DESC")
    Page<ItemDistribution> findLargeDistributions(Pageable pageable);

    /**
     * Find bulk distributions (quantity > 100)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity > 100 ORDER BY i.quantity DESC")
    Page<ItemDistribution> findBulkDistributions(Pageable pageable);

    /**
     * Find distributions by item (through planned item)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.item.id = :itemId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByItem(@Param("itemId") Long itemId, Pageable pageable);

    /**
     * Find distributions by rubric (through planned item → item)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.item.rubric.id = :rubricId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByRubric(@Param("rubricId") Long rubricId, Pageable pageable);

    /**
     * Find distributions by domain (through planned item → item → rubric)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.item.rubric.domain.id = :domainId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByDomain(@Param("domainId") Long domainId, Pageable pageable);

    /**
     * Find distributions by financial operation (through planned item)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.financialOperation.id = :financialOperationId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByFinancialOperation(@Param("financialOperationId") Long financialOperationId, Pageable pageable);

    /**
     * Find distributions by budget type (through planned item → financial operation)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.financialOperation.budgetType.id = :budgetTypeId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByBudgetType(@Param("budgetTypeId") Long budgetTypeId, Pageable pageable);

    /**
     * Find distributions by item status (through planned item)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.itemStatus.id = :itemStatusId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByItemStatus(@Param("itemStatusId") Long itemStatusId, Pageable pageable);

    /**
     * Find distributions by structure type
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.structure.structureType = :structureType ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByStructureType(@Param("structureType") String structureType, Pageable pageable);

    /**
     * Find distributions by structure level
     */
    //@Query("SELECT i FROM ItemDistribution i WHERE i.structure.level = :level ORDER BY i.structure.designationFr ASC")
    //Page<ItemDistribution> findByStructureLevel(@Param("level") int level, Pageable pageable);

    /**
     * Find distributions by parent structure (including sub-structures)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.structure.structureUp.id = :parentStructureId OR i.structure.id = :parentStructureId ORDER BY i.structure.designationFr ASC")
    Page<ItemDistribution> findByParentStructure(@Param("parentStructureId") Long parentStructureId, Pageable pageable);

    /**
     * Find high priority distributions (based on planned item priority)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE " +
           "(LOWER(i.plannedItem.item.designationFr) LIKE '%équipement%' OR " +
           "LOWER(i.plannedItem.item.designationFr) LIKE '%infrastructure%' OR " +
           "LOWER(i.plannedItem.item.designationEn) LIKE '%equipment%' OR " +
           "LOWER(i.plannedItem.item.designationEn) LIKE '%infrastructure%') OR " +
           "i.quantity > 50 " +
           "ORDER BY i.quantity DESC")
    Page<ItemDistribution> findHighPriorityDistributions(Pageable pageable);

    /**
     * Find urgent distributions (high priority + large quantity)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE " +
           "i.quantity > 100 AND " +
           "(LOWER(i.plannedItem.item.designationFr) LIKE '%équipement%' OR " +
           "LOWER(i.plannedItem.item.designationFr) LIKE '%infrastructure%' OR " +
           "LOWER(i.plannedItem.item.designationEn) LIKE '%equipment%' OR " +
           "LOWER(i.plannedItem.item.designationEn) LIKE '%infrastructure%') " +
           "ORDER BY i.quantity DESC")
    Page<ItemDistribution> findUrgentDistributions(Pageable pageable);

    /**
     * Find distributions exceeding planned quantity (potential over-distribution)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity > i.plannedItem.planedQuantity ORDER BY (i.quantity - i.plannedItem.planedQuantity) DESC")
    Page<ItemDistribution> findOverDistributions(Pageable pageable);

    /**
     * Find distributions within planned quantity limits
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity <= i.plannedItem.planedQuantity ORDER BY i.quantity DESC")
    Page<ItemDistribution> findValidDistributions(Pageable pageable);

    /**
     * Find distributions by total cost range (quantity * unit cost)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE (i.quantity * i.plannedItem.unitairCost) BETWEEN :minCost AND :maxCost ORDER BY (i.quantity * i.plannedItem.unitairCost) DESC")
    Page<ItemDistribution> findByTotalCostRange(@Param("minCost") double minCost, @Param("maxCost") double maxCost, Pageable pageable);

    /**
     * Find most expensive distributions (by total cost)
     */
    @Query("SELECT i FROM ItemDistribution i ORDER BY (i.quantity * i.plannedItem.unitairCost) DESC")
    Page<ItemDistribution> findMostExpensiveDistributions(Pageable pageable);

    /**
     * Find distributions by percentage of planned quantity
     */
    @Query("SELECT i FROM ItemDistribution i WHERE (i.quantity / i.plannedItem.planedQuantity * 100) BETWEEN :minPercentage AND :maxPercentage ORDER BY (i.quantity / i.plannedItem.planedQuantity) DESC")
    Page<ItemDistribution> findByDistributionPercentage(@Param("minPercentage") double minPercentage, @Param("maxPercentage") double maxPercentage, Pageable pageable);

    /**
     * Find complete distributions (100% of planned quantity)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity = i.plannedItem.planedQuantity ORDER BY i.quantity DESC")
    Page<ItemDistribution> findCompleteDistributions(Pageable pageable);

    /**
     * Find partial distributions (less than 100% of planned quantity)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.quantity < i.plannedItem.planedQuantity ORDER BY (i.quantity / i.plannedItem.planedQuantity) DESC")
    Page<ItemDistribution> findPartialDistributions(Pageable pageable);

    /**
     * Count distributions by planned item
     */
    @Query("SELECT COUNT(i) FROM ItemDistribution i WHERE i.plannedItem.id = :plannedItemId")
    Long countByPlannedItem(@Param("plannedItemId") Long plannedItemId);

    /**
     * Count distributions by structure
     */
    @Query("SELECT COUNT(i) FROM ItemDistribution i WHERE i.structure.id = :structureId")
    Long countByStructure(@Param("structureId") Long structureId);

    /**
     * Count distributions by item
     */
    @Query("SELECT COUNT(i) FROM ItemDistribution i WHERE i.plannedItem.item.id = :itemId")
    Long countByItem(@Param("itemId") Long itemId);

    /**
     * Count distributions by rubric
     */
    @Query("SELECT COUNT(i) FROM ItemDistribution i WHERE i.plannedItem.item.rubric.id = :rubricId")
    Long countByRubric(@Param("rubricId") Long rubricId);

    /**
     * Count total distributions
     */
    @Query("SELECT COUNT(i) FROM ItemDistribution i")
    Long countAllDistributions();

    /**
     * Get sum of quantities by planned item
     */
    @Query("SELECT SUM(i.quantity) FROM ItemDistribution i WHERE i.plannedItem.id = :plannedItemId")
    Float getSumQuantityByPlannedItem(@Param("plannedItemId") Long plannedItemId);

    /**
     * Get sum of quantities by structure
     */
    @Query("SELECT SUM(i.quantity) FROM ItemDistribution i WHERE i.structure.id = :structureId")
    Float getSumQuantityByStructure(@Param("structureId") Long structureId);

    /**
     * Get sum of total costs by structure (quantity * unit cost)
     */
    @Query("SELECT SUM(i.quantity * i.plannedItem.unitairCost) FROM ItemDistribution i WHERE i.structure.id = :structureId")
    Double getSumTotalCostByStructure(@Param("structureId") Long structureId);

    /**
     * Get average quantity per distribution
     */
    @Query("SELECT AVG(i.quantity) FROM ItemDistribution i")
    Float getAverageQuantity();

    /**
     * Get maximum quantity
     */
    @Query("SELECT MAX(i.quantity) FROM ItemDistribution i")
    Float getMaxQuantity();

    /**
     * Get minimum quantity
     */
    @Query("SELECT MIN(i.quantity) FROM ItemDistribution i")
    Float getMinQuantity();

    /**
     * Get distribution statistics by quantity category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN i.quantity <= 1 THEN 'UNIT' " +
           "WHEN i.quantity <= 10 THEN 'SMALL' " +
           "WHEN i.quantity <= 50 THEN 'MEDIUM' " +
           "WHEN i.quantity <= 100 THEN 'LARGE' " +
           "ELSE 'BULK' " +
           "END, COUNT(i) " +
           "FROM ItemDistribution i " +
           "GROUP BY " +
           "CASE " +
           "WHEN i.quantity <= 1 THEN 'UNIT' " +
           "WHEN i.quantity <= 10 THEN 'SMALL' " +
           "WHEN i.quantity <= 50 THEN 'MEDIUM' " +
           "WHEN i.quantity <= 100 THEN 'LARGE' " +
           "ELSE 'BULK' " +
           "END")
    List<Object[]> getDistributionStatisticsByQuantityCategory();

    /**
     * Get distribution statistics by structure
     */
    @Query("SELECT i.structure.designationFr, COUNT(i), SUM(i.quantity), AVG(i.quantity) FROM ItemDistribution i GROUP BY i.structure.designationFr ORDER BY COUNT(i) DESC")
    List<Object[]> getDistributionStatisticsByStructure();

    /**
     * Get distribution statistics by planned item
     */
    @Query("SELECT i.plannedItem.designation, COUNT(i), SUM(i.quantity) FROM ItemDistribution i GROUP BY i.plannedItem.designation ORDER BY SUM(i.quantity) DESC")
    List<Object[]> getDistributionStatisticsByPlannedItem();

    /**
     * Find most recently added distributions
     */
    @Query("SELECT i FROM ItemDistribution i ORDER BY i.id DESC")
    Page<ItemDistribution> findMostRecentDistributions(Pageable pageable);

    /**
     * Find distributions requiring coordination (multiple distributions per planned item)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.id IN " +
           "(SELECT pi.id FROM ItemDistribution pi GROUP BY pi.plannedItem.id HAVING COUNT(pi) > 1) " +
           "ORDER BY i.plannedItem.id, i.structure.designationFr")
    Page<ItemDistribution> findDistributionsRequiringCoordination(Pageable pageable);

    /**
     * Find single distributions (only one distribution per planned item)
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.id IN " +
           "(SELECT pi.id FROM ItemDistribution pi GROUP BY pi.plannedItem.id HAVING COUNT(pi) = 1) " +
           "ORDER BY i.structure.designationFr")
    Page<ItemDistribution> findSingleDistributions(Pageable pageable);

    /**
     * Find distributions by structure hierarchy level
     */
    //@Query("SELECT i FROM ItemDistribution i WHERE i.structure.level <= :maxLevel ORDER BY i.structure.level ASC, i.structure.designationFr ASC")
    //Page<ItemDistribution> findByMaxStructureLevel(@Param("maxLevel") int maxLevel, Pageable pageable);

    /**
     * Find cross-domain distributions (different domains through planned item → item → rubric)
     */
    //@Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.item.rubric.domain.id != i.structure.domain.id ORDER BY i.plannedItem.item.rubric.domain.designationFr")
    //Page<ItemDistribution> findCrossDomainDistributions(Pageable pageable);

    /**
     * Find same-domain distributions
     */
    //@Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.item.rubric.domain.id = i.structure.domain.id ORDER BY i.structure.designationFr")
    //Page<ItemDistribution> findSameDomainDistributions(Pageable pageable);

    /**
     * Find distributions with budget modifications
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.budgetModification IS NOT NULL ORDER BY i.plannedItem.budgetModification.approvalDate DESC")
    Page<ItemDistribution> findWithBudgetModifications(Pageable pageable);

    /**
     * Find distributions without budget modifications
     */
    @Query("SELECT i FROM ItemDistribution i WHERE i.plannedItem.budgetModification IS NULL ORDER BY i.structure.designationFr")
    Page<ItemDistribution> findWithoutBudgetModifications(Pageable pageable);

    /**
     * Get top structures by distribution count
     */
    @Query("SELECT i.structure, COUNT(i) as distributionCount FROM ItemDistribution i GROUP BY i.structure ORDER BY COUNT(i) DESC")
    Page<Object[]> findTopStructuresByDistributionCount(Pageable pageable);

    /**
     * Get top planned items by distribution count
     */
    @Query("SELECT i.plannedItem, COUNT(i) as distributionCount FROM ItemDistribution i GROUP BY i.plannedItem ORDER BY COUNT(i) DESC")
    Page<Object[]> findTopPlannedItemsByDistributionCount(Pageable pageable);
}