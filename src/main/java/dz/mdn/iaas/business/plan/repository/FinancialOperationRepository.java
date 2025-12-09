/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperationRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.FinancialOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Financial Operation Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=operation (unique), F_02=budgetYear, F_03=budgetType
 */
@Repository
public interface FinancialOperationRepository extends JpaRepository<FinancialOperation, Long> {

    /**
     * Find financial operation by operation name (F_01) - unique constraint
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.operation = :operation")
    Optional<FinancialOperation> findByOperation(@Param("operation") String operation);

    /**
     * Find all financial operations ordered by budget year desc, then by operation
     */
    @Query("SELECT fo FROM FinancialOperation fo ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findAllOrderByBudgetYearAndOperation(Pageable pageable);

    /**
     * Find financial operations by budget year
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetYear = :budgetYear ORDER BY fo.operation ASC")
    Page<FinancialOperation> findByBudgetYear(@Param("budgetYear") String budgetYear, Pageable pageable);

    /**
     * Find financial operations by budget type
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetType.id = :budgetTypeId ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findByBudgetType(@Param("budgetTypeId") Long budgetTypeId, Pageable pageable);

    /**
     * Find financial operations by budget year and budget type
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetYear = :budgetYear AND fo.budgetType.id = :budgetTypeId ORDER BY fo.operation ASC")
    Page<FinancialOperation> findByBudgetYearAndBudgetType(@Param("budgetYear") String budgetYear, @Param("budgetTypeId") Long budgetTypeId, Pageable pageable);

    /**
     * Search financial operations by operation name
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE LOWER(fo.operation) LIKE LOWER(CONCAT('%', :search, '%')) ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> searchByOperation(@Param("search") String search, Pageable pageable);

    /**
     * Find current year operations (based on current year)
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetYear = :currentYear ORDER BY fo.operation ASC")
    Page<FinancialOperation> findCurrentYearOperations(@Param("currentYear") String currentYear, Pageable pageable);

    /**
     * Find future year operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetYear > :currentYear ORDER BY fo.budgetYear ASC, fo.operation ASC")
    Page<FinancialOperation> findFutureYearOperations(@Param("currentYear") String currentYear, Pageable pageable);

    /**
     * Find past year operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetYear < :currentYear ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findPastYearOperations(@Param("currentYear") String currentYear, Pageable pageable);

    /**
     * Find budget allocation operations (based on operation patterns)
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%allocation%' OR LOWER(fo.operation) LIKE '%budget%' OR " +
           "LOWER(fo.operation) LIKE '%affectation%' OR LOWER(fo.operation) LIKE '%dotation%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findBudgetAllocationOperations(Pageable pageable);

    /**
     * Find expenditure operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%expenditure%' OR LOWER(fo.operation) LIKE '%expense%' OR " +
           "LOWER(fo.operation) LIKE '%dépense%' OR LOWER(fo.operation) LIKE '%coût%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findExpenditureOperations(Pageable pageable);

    /**
     * Find revenue operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%revenue%' OR LOWER(fo.operation) LIKE '%income%' OR " +
           "LOWER(fo.operation) LIKE '%recette%' OR LOWER(fo.operation) LIKE '%revenu%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findRevenueOperations(Pageable pageable);

    /**
     * Find transfer operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%transfer%' OR LOWER(fo.operation) LIKE '%virement%' OR " +
           "LOWER(fo.operation) LIKE '%transfert%' OR LOWER(fo.operation) LIKE '%réaffectation%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findTransferOperations(Pageable pageable);

    /**
     * Find investment operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%investment%' OR LOWER(fo.operation) LIKE '%capital%' OR " +
           "LOWER(fo.operation) LIKE '%investissement%' OR LOWER(fo.operation) LIKE '%équipement%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findInvestmentOperations(Pageable pageable);

    /**
     * Find procurement operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%procurement%' OR LOWER(fo.operation) LIKE '%purchase%' OR " +
           "LOWER(fo.operation) LIKE '%acquisition%' OR LOWER(fo.operation) LIKE '%achat%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findProcurementOperations(Pageable pageable);

    /**
     * Find payment operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%payment%' OR LOWER(fo.operation) LIKE '%paiement%' OR " +
           "LOWER(fo.operation) LIKE '%versement%' OR LOWER(fo.operation) LIKE '%règlement%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findPaymentOperations(Pageable pageable);

    /**
     * Find adjustment operations
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%adjustment%' OR LOWER(fo.operation) LIKE '%correction%' OR " +
           "LOWER(fo.operation) LIKE '%ajustement%' OR LOWER(fo.operation) LIKE '%rectification%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findAdjustmentOperations(Pageable pageable);

    /**
     * Find operations by budget year range
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetYear BETWEEN :startYear AND :endYear ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findByBudgetYearRange(@Param("startYear") String startYear, @Param("endYear") String endYear, Pageable pageable);

    /**
     * Find high priority operations (budget allocation, payment)
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%allocation%' OR LOWER(fo.operation) LIKE '%budget%' OR " +
           "LOWER(fo.operation) LIKE '%payment%' OR LOWER(fo.operation) LIKE '%paiement%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findHighPriorityOperations(Pageable pageable);

    /**
     * Find operations by budget type category (investment, operating, etc.)
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.budgetType.designationFr) LIKE LOWER(CONCAT('%', :category, '%')) OR " +
           "LOWER(fo.budgetType.designationEn) LIKE LOWER(CONCAT('%', :category, '%')) " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findByBudgetTypeCategory(@Param("category") String category, Pageable pageable);

    /**
     * Check if operation exists (for uniqueness validation)
     */
    @Query("SELECT CASE WHEN COUNT(fo) > 0 THEN true ELSE false END FROM FinancialOperation fo WHERE fo.operation = :operation")
    boolean existsByOperation(@Param("operation") String operation);

    /**
     * Check if operation exists excluding current ID (for update validation)
     */
    @Query("SELECT CASE WHEN COUNT(fo) > 0 THEN true ELSE false END FROM FinancialOperation fo WHERE fo.operation = :operation AND fo.id != :id")
    boolean existsByOperationAndIdNot(@Param("operation") String operation, @Param("id") Long id);

    /**
     * Count operations by budget year
     */
    @Query("SELECT COUNT(fo) FROM FinancialOperation fo WHERE fo.budgetYear = :budgetYear")
    Long countByBudgetYear(@Param("budgetYear") String budgetYear);

    /**
     * Count operations by budget type
     */
    @Query("SELECT COUNT(fo) FROM FinancialOperation fo WHERE fo.budgetType.id = :budgetTypeId")
    Long countByBudgetType(@Param("budgetTypeId") Long budgetTypeId);

    /**
     * Count total financial operations
     */
    @Query("SELECT COUNT(fo) FROM FinancialOperation fo")
    Long countAllOperations();

    /**
     * Count current year operations
     */
    @Query("SELECT COUNT(fo) FROM FinancialOperation fo WHERE fo.budgetYear = :currentYear")
    Long countCurrentYearOperations(@Param("currentYear") String currentYear);

    /**
     * Count budget allocation operations
     */
    @Query("SELECT COUNT(fo) FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%allocation%' OR LOWER(fo.operation) LIKE '%budget%' OR " +
           "LOWER(fo.operation) LIKE '%affectation%' OR LOWER(fo.operation) LIKE '%dotation%'")
    Long countBudgetAllocationOperations();

    /**
     * Count expenditure operations
     */
    @Query("SELECT COUNT(fo) FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%expenditure%' OR LOWER(fo.operation) LIKE '%expense%' OR " +
           "LOWER(fo.operation) LIKE '%dépense%' OR LOWER(fo.operation) LIKE '%coût%'")
    Long countExpenditureOperations();

    /**
     * Get operation statistics by budget year
     */
    @Query("SELECT fo.budgetYear, COUNT(fo) FROM FinancialOperation fo GROUP BY fo.budgetYear ORDER BY fo.budgetYear DESC")
    List<Object[]> getOperationStatisticsByYear();

    /**
     * Get operation statistics by budget type
     */
    @Query("SELECT fo.budgetType.designationFr, COUNT(fo) FROM FinancialOperation fo GROUP BY fo.budgetType.designationFr ORDER BY COUNT(fo) DESC")
    List<Object[]> getOperationStatisticsByBudgetType();

    /**
     * Get operation statistics by category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(fo.operation) LIKE '%allocation%' OR LOWER(fo.operation) LIKE '%budget%' THEN 'BUDGET_ALLOCATION' " +
           "WHEN LOWER(fo.operation) LIKE '%expenditure%' OR LOWER(fo.operation) LIKE '%expense%' THEN 'EXPENDITURE' " +
           "WHEN LOWER(fo.operation) LIKE '%revenue%' OR LOWER(fo.operation) LIKE '%income%' THEN 'REVENUE' " +
           "WHEN LOWER(fo.operation) LIKE '%transfer%' OR LOWER(fo.operation) LIKE '%virement%' THEN 'TRANSFER' " +
           "WHEN LOWER(fo.operation) LIKE '%investment%' OR LOWER(fo.operation) LIKE '%capital%' THEN 'INVESTMENT' " +
           "WHEN LOWER(fo.operation) LIKE '%procurement%' OR LOWER(fo.operation) LIKE '%purchase%' THEN 'PROCUREMENT' " +
           "WHEN LOWER(fo.operation) LIKE '%payment%' OR LOWER(fo.operation) LIKE '%paiement%' THEN 'PAYMENT' " +
           "ELSE 'OTHER' " +
           "END, COUNT(fo) " +
           "FROM FinancialOperation fo " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(fo.operation) LIKE '%allocation%' OR LOWER(fo.operation) LIKE '%budget%' THEN 'BUDGET_ALLOCATION' " +
           "WHEN LOWER(fo.operation) LIKE '%expenditure%' OR LOWER(fo.operation) LIKE '%expense%' THEN 'EXPENDITURE' " +
           "WHEN LOWER(fo.operation) LIKE '%revenue%' OR LOWER(fo.operation) LIKE '%income%' THEN 'REVENUE' " +
           "WHEN LOWER(fo.operation) LIKE '%transfer%' OR LOWER(fo.operation) LIKE '%virement%' THEN 'TRANSFER' " +
           "WHEN LOWER(fo.operation) LIKE '%investment%' OR LOWER(fo.operation) LIKE '%capital%' THEN 'INVESTMENT' " +
           "WHEN LOWER(fo.operation) LIKE '%procurement%' OR LOWER(fo.operation) LIKE '%purchase%' THEN 'PROCUREMENT' " +
           "WHEN LOWER(fo.operation) LIKE '%payment%' OR LOWER(fo.operation) LIKE '%paiement%' THEN 'PAYMENT' " +
           "ELSE 'OTHER' " +
           "END " +
           "ORDER BY COUNT(fo) DESC")
    List<Object[]> getOperationStatisticsByCategory();

    /**
     * Find distinct budget years
     */
    @Query("SELECT DISTINCT fo.budgetYear FROM FinancialOperation fo ORDER BY fo.budgetYear DESC")
    List<String> findDistinctBudgetYears();

    /**
     * Find operations with complete information
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "fo.operation IS NOT NULL AND fo.operation != '' AND " +
           "fo.budgetYear IS NOT NULL AND fo.budgetYear != '' AND " +
           "fo.budgetType IS NOT NULL " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findWithCompleteInformation(Pageable pageable);

    /**
     * Find operations by multiple budget types
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetType.id IN :budgetTypeIds ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findByMultipleBudgetTypes(@Param("budgetTypeIds") List<Long> budgetTypeIds, Pageable pageable);

    /**
     * Find operations by multiple budget years
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE fo.budgetYear IN :budgetYears ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findByMultipleBudgetYears(@Param("budgetYears") List<String> budgetYears, Pageable pageable);

    /**
     * Find most recent operations (by ID)
     */
    @Query("SELECT fo FROM FinancialOperation fo ORDER BY fo.id DESC")
    Page<FinancialOperation> findMostRecentOperations(Pageable pageable);

    /**
     * Find similar operations (for duplicate detection)
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE LOWER(fo.operation) = LOWER(:operation)")
    List<FinancialOperation> findSimilarOperations(@Param("operation") String operation);

    /**
     * Find operations requiring executive approval (high priority)
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "LOWER(fo.operation) LIKE '%allocation%' OR LOWER(fo.operation) LIKE '%budget%' OR " +
           "LOWER(fo.operation) LIKE '%investment%' OR LOWER(fo.operation) LIKE '%capital%' OR " +
           "LOWER(fo.operation) LIKE '%payment%' OR LOWER(fo.operation) LIKE '%paiement%' " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findRequiringExecutiveApproval(Pageable pageable);

    /**
     * Find operations by fiscal period
     */
    @Query("SELECT fo FROM FinancialOperation fo WHERE " +
           "(:period = 'CURRENT' AND fo.budgetYear = :currentYear) OR " +
           "(:period = 'FUTURE' AND fo.budgetYear > :currentYear) OR " +
           "(:period = 'PAST' AND fo.budgetYear < :currentYear) " +
           "ORDER BY fo.budgetYear DESC, fo.operation ASC")
    Page<FinancialOperation> findByFiscalPeriod(@Param("period") String period, @Param("currentYear") String currentYear, Pageable pageable);
}
