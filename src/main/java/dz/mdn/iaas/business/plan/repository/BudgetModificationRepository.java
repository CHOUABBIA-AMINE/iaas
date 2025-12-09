/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.business.plan.model.BudgetModification;

/**
 * BudgetModification Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=object, F_02=description, F_03=approvalDate, F_04=demande, F_05=response
 * Includes unique constraint on F_03+F_04 (approvalDate+demande) and many-to-one relationships with Documents
 */
@Repository
public interface BudgetModificationRepository extends JpaRepository<BudgetModification, Long> {

    /**
     * Find all budget modifications ordered by approval date descending (most recent first)
     */
    @Query("SELECT b FROM BudgetModification b ORDER BY b.approvalDate DESC NULLS LAST, b.id DESC")
    Page<BudgetModification> findAllOrderByApprovalDate(Pageable pageable);

    /**
     * Find budget modifications by demande document
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.demande.id = :demandeId ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findByDemande(@Param("demandeId") Long demandeId, Pageable pageable);

    /**
     * Find budget modifications by response document
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.response.id = :responseId ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findByResponse(@Param("responseId") Long responseId, Pageable pageable);

    /**
     * Search budget modifications by object
     */
    @Query("SELECT b FROM BudgetModification b WHERE LOWER(b.object) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BudgetModification> searchByObject(@Param("search") String search, Pageable pageable);

    /**
     * Search budget modifications by description
     */
    @Query("SELECT b FROM BudgetModification b WHERE LOWER(b.description) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BudgetModification> searchByDescription(@Param("search") String search, Pageable pageable);

    /**
     * Search budget modifications by object or description
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "LOWER(b.object) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.description) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BudgetModification> searchByObjectOrDescription(@Param("search") String search, Pageable pageable);

    /**
     * Find budget modifications by approval date range
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate BETWEEN :startDate AND :endDate ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findByApprovalDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Find pending budget modifications (no approval date)
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate IS NULL ORDER BY b.id DESC")
    Page<BudgetModification> findPendingModifications(Pageable pageable);

    /**
     * Find approved budget modifications (has approval date <= today)
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate IS NOT NULL AND b.approvalDate <= CURRENT_DATE ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findApprovedModifications(Pageable pageable);

    /**
     * Find scheduled budget modifications (approval date > today)
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate IS NOT NULL AND b.approvalDate > CURRENT_DATE ORDER BY b.approvalDate ASC")
    Page<BudgetModification> findScheduledModifications(Pageable pageable);

    /**
     * Find budget modifications by approval date (specific date)
     */
    @Query("SELECT b FROM BudgetModification b WHERE DATE(b.approvalDate) = DATE(:approvalDate) ORDER BY b.id DESC")
    Page<BudgetModification> findByApprovalDate(@Param("approvalDate") Date approvalDate, Pageable pageable);

    /**
     * Find budget modifications approved before date
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate IS NOT NULL AND b.approvalDate < :date ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findApprovedBefore(@Param("date") Date date, Pageable pageable);

    /**
     * Find budget modifications approved after date
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate IS NOT NULL AND b.approvalDate > :date ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findApprovedAfter(@Param("date") Date date, Pageable pageable);

    /**
     * Find budget modifications by current year
     */
    @Query("SELECT b FROM BudgetModification b WHERE YEAR(b.approvalDate) = YEAR(CURRENT_DATE) ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findCurrentYearModifications(Pageable pageable);

    /**
     * Find budget modifications by specific year
     */
    @Query("SELECT b FROM BudgetModification b WHERE YEAR(b.approvalDate) = :year ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findByYear(@Param("year") int year, Pageable pageable);

    /**
     * Find budget modifications by current month
     */
    @Query("SELECT b FROM BudgetModification b WHERE YEAR(b.approvalDate) = YEAR(CURRENT_DATE) AND MONTH(b.approvalDate) = MONTH(CURRENT_DATE) ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findCurrentMonthModifications(Pageable pageable);

    /**
     * Find budget increase modifications (based on object content)
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "LOWER(b.object) LIKE '%augmentation%' OR " +
           "LOWER(b.object) LIKE '%increase%' OR " +
           "LOWER(b.object) LIKE '%ajout%' OR " +
           "LOWER(b.object) LIKE '%addition%' " +
           "ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findBudgetIncreaseModifications(Pageable pageable);

    /**
     * Find budget decrease modifications
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "LOWER(b.object) LIKE '%réduction%' OR " +
           "LOWER(b.object) LIKE '%reduction%' OR " +
           "LOWER(b.object) LIKE '%diminution%' OR " +
           "LOWER(b.object) LIKE '%decrease%' " +
           "ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findBudgetDecreaseModifications(Pageable pageable);

    /**
     * Find budget reallocation modifications
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "LOWER(b.object) LIKE '%réallocation%' OR " +
           "LOWER(b.object) LIKE '%reallocation%' OR " +
           "LOWER(b.object) LIKE '%transfert%' OR " +
           "LOWER(b.object) LIKE '%transfer%' OR " +
           "LOWER(b.object) LIKE '%virement%' " +
           "ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findBudgetReallocationModifications(Pageable pageable);

    /**
     * Find emergency modifications
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "LOWER(b.object) LIKE '%urgence%' OR " +
           "LOWER(b.object) LIKE '%emergency%' OR " +
           "LOWER(b.object) LIKE '%urgent%' OR " +
           "LOWER(b.object) LIKE '%critique%' OR " +
           "LOWER(b.object) LIKE '%critical%' " +
           "ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findEmergencyModifications(Pageable pageable);

    /**
     * Find correction modifications
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "LOWER(b.object) LIKE '%correction%' OR " +
           "LOWER(b.object) LIKE '%rectification%' OR " +
           "LOWER(b.object) LIKE '%ajustement%' OR " +
           "LOWER(b.object) LIKE '%adjustment%' " +
           "ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findCorrectionModifications(Pageable pageable);

    /**
     * Find recent modifications (last 30 days)
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate >= :thirtyDaysAgo ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findRecentModifications(@Param("thirtyDaysAgo") Date thirtyDaysAgo, Pageable pageable);

    /**
     * Find overdue modifications (pending and should have been approved)
     */
    @Query("SELECT b FROM BudgetModification b WHERE b.approvalDate IS NULL AND b.id IN " +
           "(SELECT p.budgetModification.id FROM PlannedItem p WHERE p.budgetModification IS NOT NULL) " +
           "ORDER BY b.id DESC")
    Page<BudgetModification> findOverdueModifications(Pageable pageable);

    /**
     * Check if unique constraint exists (approvalDate + demande combination)
     */
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BudgetModification b WHERE b.approvalDate = :approvalDate AND b.demande.id = :demandeId")
    boolean existsByApprovalDateAndDemande(@Param("approvalDate") Date approvalDate, @Param("demandeId") Long demandeId);

    /**
     * Check if unique constraint exists excluding current ID (for updates)
     */
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BudgetModification b WHERE b.approvalDate = :approvalDate AND b.demande.id = :demandeId AND b.id != :id")
    boolean existsByApprovalDateAndDemandeAndIdNot(@Param("approvalDate") Date approvalDate, @Param("demandeId") Long demandeId, @Param("id") Long id);

    /**
     * Count budget modifications by demande document
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b WHERE b.demande.id = :demandeId")
    Long countByDemande(@Param("demandeId") Long demandeId);

    /**
     * Count budget modifications by response document
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b WHERE b.response.id = :responseId")
    Long countByResponse(@Param("responseId") Long responseId);

    /**
     * Count total budget modifications
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b")
    Long countAllBudgetModifications();

    /**
     * Count pending budget modifications
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b WHERE b.approvalDate IS NULL")
    Long countPendingModifications();

    /**
     * Count approved budget modifications
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b WHERE b.approvalDate IS NOT NULL AND b.approvalDate <= CURRENT_DATE")
    Long countApprovedModifications();

    /**
     * Count scheduled budget modifications
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b WHERE b.approvalDate IS NOT NULL AND b.approvalDate > CURRENT_DATE")
    Long countScheduledModifications();

    /**
     * Count modifications by year
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b WHERE YEAR(b.approvalDate) = :year")
    Long countByYear(@Param("year") int year);

    /**
     * Count modifications by current year
     */
    @Query("SELECT COUNT(b) FROM BudgetModification b WHERE YEAR(b.approvalDate) = YEAR(CURRENT_DATE)")
    Long countCurrentYearModifications();

    /**
     * Get budget modification statistics by type
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(b.object) LIKE '%augmentation%' OR LOWER(b.object) LIKE '%increase%' THEN 'INCREASE' " +
           "WHEN LOWER(b.object) LIKE '%réduction%' OR LOWER(b.object) LIKE '%reduction%' THEN 'DECREASE' " +
           "WHEN LOWER(b.object) LIKE '%réallocation%' OR LOWER(b.object) LIKE '%reallocation%' THEN 'REALLOCATION' " +
           "WHEN LOWER(b.object) LIKE '%urgence%' OR LOWER(b.object) LIKE '%emergency%' THEN 'EMERGENCY' " +
           "WHEN LOWER(b.object) LIKE '%correction%' OR LOWER(b.object) LIKE '%correction%' THEN 'CORRECTION' " +
           "ELSE 'OTHER' " +
           "END, COUNT(b) " +
           "FROM BudgetModification b " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(b.object) LIKE '%augmentation%' OR LOWER(b.object) LIKE '%increase%' THEN 'INCREASE' " +
           "WHEN LOWER(b.object) LIKE '%réduction%' OR LOWER(b.object) LIKE '%reduction%' THEN 'DECREASE' " +
           "WHEN LOWER(b.object) LIKE '%réallocation%' OR LOWER(b.object) LIKE '%reallocation%' THEN 'REALLOCATION' " +
           "WHEN LOWER(b.object) LIKE '%urgence%' OR LOWER(b.object) LIKE '%emergency%' THEN 'EMERGENCY' " +
           "WHEN LOWER(b.object) LIKE '%correction%' OR LOWER(b.object) LIKE '%correction%' THEN 'CORRECTION' " +
           "ELSE 'OTHER' " +
           "END")
    List<Object[]> getBudgetModificationStatisticsByType();

    /**
     * Get budget modification statistics by status
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN b.approvalDate IS NULL THEN 'PENDING' " +
           "WHEN b.approvalDate > CURRENT_DATE THEN 'SCHEDULED' " +
           "ELSE 'APPROVED' " +
           "END, COUNT(b) " +
           "FROM BudgetModification b " +
           "GROUP BY " +
           "CASE " +
           "WHEN b.approvalDate IS NULL THEN 'PENDING' " +
           "WHEN b.approvalDate > CURRENT_DATE THEN 'SCHEDULED' " +
           "ELSE 'APPROVED' " +
           "END")
    List<Object[]> getBudgetModificationStatisticsByStatus();

    /**
     * Get budget modification statistics by month (current year)
     */
    @Query("SELECT MONTH(b.approvalDate), COUNT(b) FROM BudgetModification b WHERE YEAR(b.approvalDate) = YEAR(CURRENT_DATE) GROUP BY MONTH(b.approvalDate) ORDER BY MONTH(b.approvalDate)")
    List<Object[]> getBudgetModificationStatisticsByMonth();

    /**
     * Find most recently added budget modifications
     */
    @Query("SELECT b FROM BudgetModification b ORDER BY b.id DESC")
    Page<BudgetModification> findMostRecentBudgetModifications(Pageable pageable);

    /**
     * Find budget modifications requiring immediate attention (emergency + pending)
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "b.approvalDate IS NULL AND " +
           "(LOWER(b.object) LIKE '%urgence%' OR " +
           "LOWER(b.object) LIKE '%emergency%' OR " +
           "LOWER(b.object) LIKE '%urgent%' OR " +
           "LOWER(b.object) LIKE '%critique%' OR " +
           "LOWER(b.object) LIKE '%critical%') " +
           "ORDER BY b.id DESC")
    Page<BudgetModification> findRequiringImmediateAttention(Pageable pageable);

    /**
     * Find budget modifications by document type (through document relationship)
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "b.demande.documentType = :documentType OR " +
           "b.response.documentType = :documentType " +
           "ORDER BY b.approvalDate DESC")
    Page<BudgetModification> findByDocumentType(@Param("documentType") String documentType, Pageable pageable);

    /**
     * Find budget modifications with missing information (empty object and description)
     */
    @Query("SELECT b FROM BudgetModification b WHERE " +
           "(b.object IS NULL OR b.object = '') AND " +
           "(b.description IS NULL OR b.description = '') " +
           "ORDER BY b.id DESC")
    Page<BudgetModification> findWithMissingInformation(Pageable pageable);
}