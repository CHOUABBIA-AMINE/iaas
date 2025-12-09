/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
* Consultation Repository with essential CRUD operations
* Based on exact field names: F_00=id, F_01=internalId, F_02=consultationYear, F_03=reference,
* F_04=designationAr, F_05=designationEn, F_06=designationFr, and all foreign keys F_15 to F_21
* Unique constraint: F_01 + F_02 (internalId + consultationYear)
*/
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

   /**
    * Find consultation by internal ID and year (unique constraint F_01 + F_02)
    */
   @Query("SELECT c FROM Consultation c WHERE c.internalId = :internalId AND c.consultationYear = :consultationYear")
   Optional<Consultation> findByInternalIdAndConsultationYear(@Param("internalId") String internalId, 
                                                             @Param("consultationYear") String consultationYear);

   /**
    * Find consultation by reference (F_03)
    */
   @Query("SELECT c FROM Consultation c WHERE c.reference = :reference")
   Optional<Consultation> findByReference(@Param("reference") String reference);

   /**
    * Find consultations by year (F_02)
    */
   @Query("SELECT c FROM Consultation c WHERE c.consultationYear = :consultationYear ORDER BY c.internalId ASC")
   Page<Consultation> findByConsultationYear(@Param("consultationYear") String consultationYear, Pageable pageable);

   /**
    * Find consultation by ID with all relationships loaded
    */
   @Query("SELECT c FROM Consultation c " +
          "LEFT JOIN FETCH c.awardMethod " +
          "LEFT JOIN FETCH c.procurementNature " +
          "LEFT JOIN FETCH c.budgetType " +
          "LEFT JOIN FETCH c.procurementStatus " +
          "LEFT JOIN FETCH c.approvalStatus " +
          "LEFT JOIN FETCH c.procurementDirector " +
          "LEFT JOIN FETCH c.consultationStep " +
          "LEFT JOIN FETCH c.documents " +
          "LEFT JOIN FETCH c.referencedMails " +
          "LEFT JOIN FETCH c.plannedItems " +
          "WHERE c.id = :id")
   Optional<Consultation> findByIdWithDetails(@Param("id") Long id);

   /**
    * Search consultations by designation (F_04, F_05, F_06)
    */
   @Query("SELECT c FROM Consultation c WHERE " +
          "LOWER(c.designationAr) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
          "LOWER(c.designationEn) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
          "LOWER(c.designationFr) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
          "LOWER(c.reference) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
          "LOWER(c.approvalReference) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
          "ORDER BY c.startDate DESC")
   Page<Consultation> searchByDesignation(@Param("searchTerm") String searchTerm, Pageable pageable);

   /**
    * Find consultations by procurement status (F_18)
    */
   @Query("SELECT c FROM Consultation c WHERE c.procurementStatus.id = :statusId ORDER BY c.startDate DESC")
   Page<Consultation> findByProcurementStatusId(@Param("statusId") Long statusId, Pageable pageable);

   /**
    * Find consultations by approval status (F_19)
    */
   @Query("SELECT c FROM Consultation c WHERE c.approvalStatus.id = :statusId ORDER BY c.approvalDate DESC")
   Page<Consultation> findByApprovalStatusId(@Param("statusId") Long statusId, Pageable pageable);

   /**
    * Find consultations by award method (F_15)
    */
   @Query("SELECT c FROM Consultation c WHERE c.awardMethod.id = :awardMethodId ORDER BY c.publishDate DESC")
   Page<Consultation> findByAwardMethodId(@Param("awardMethodId") Long awardMethodId, Pageable pageable);

   /**
    * Find consultations by consultation step (F_21)
    */
   @Query("SELECT c FROM Consultation c WHERE c.consultationStep.id = :stepId ORDER BY c.publishDate DESC")
   Page<Consultation> findByConsultationStepId(@Param("stepId") Long stepId, Pageable pageable);

   /**
    * Find consultations by date range (F_09 - startDate)
    */
   @Query("SELECT c FROM Consultation c WHERE c.startDate BETWEEN :startDate AND :endDate ORDER BY c.startDate ASC")
   Page<Consultation> findByStartDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

   /**
    * Find consultations by deadline range (F_13)
    */
   @Query("SELECT c FROM Consultation c WHERE c.deadline BETWEEN :fromDate AND :toDate ORDER BY c.deadline ASC")
   Page<Consultation> findByDeadlineBetween(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);

   /**
    * Find consultations by allocated amount range (F_07)
    */
   @Query("SELECT c FROM Consultation c WHERE c.allocatedAmount BETWEEN :minAmount AND :maxAmount ORDER BY c.allocatedAmount DESC")
   Page<Consultation> findByAllocatedAmountBetween(@Param("minAmount") double minAmount, @Param("maxAmount") double maxAmount, Pageable pageable);

   /**
    * Find expired consultations (deadline passed)
    */
   @Query("SELECT c FROM Consultation c WHERE c.deadline < :currentDate ORDER BY c.deadline ASC")
   Page<Consultation> findExpiredConsultations(@Param("currentDate") Date currentDate, Pageable pageable);

   /**
    * Find consultations expiring soon
    */
   @Query("SELECT c FROM Consultation c WHERE c.deadline BETWEEN :currentDate AND :futureDate ORDER BY c.deadline ASC")
   Page<Consultation> findConsultationsExpiringSoon(@Param("currentDate") Date currentDate, 
                                                   @Param("futureDate") Date futureDate, Pageable pageable);

   /**
    * Find active consultations (published and not expired)
    */
   @Query("SELECT c FROM Consultation c WHERE c.publishDate <= :currentDate AND " +
          "(c.deadline IS NULL OR c.deadline > :currentDate) ORDER BY c.publishDate DESC")
   Page<Consultation> findActiveConsultations(@Param("currentDate") Date currentDate, Pageable pageable);

   /**
    * Get max internal ID for a year (for generating next ID)
    */
   @Query("SELECT CAST(MAX(c.internalId) AS long) FROM Consultation c WHERE c.consultationYear = :year")
   Long findMaxInternalIdByYear(@Param("year") String consultationYear);

   /**
    * Count consultations by year
    */
   @Query("SELECT COUNT(c) FROM Consultation c WHERE c.consultationYear = :year")
   Long countByConsultationYear(@Param("year") String consultationYear);

   /**
    * Sum allocated amount by year
    */
   @Query("SELECT COALESCE(SUM(c.allocatedAmount), 0.0) FROM Consultation c WHERE c.consultationYear = :year")
   Double sumAllocatedAmountByYear(@Param("year") String consultationYear);

   /**
    * Sum financial estimation by year
    */
   @Query("SELECT COALESCE(SUM(c.financialEstimation), 0.0) FROM Consultation c WHERE c.consultationYear = :year")
   Double sumFinancialEstimationByYear(@Param("year") String consultationYear);

   /**
    * Find consultations with most submissions
    */
   @Query("SELECT c FROM Consultation c ORDER BY SIZE(c.submissions) DESC")
   Page<Consultation> findConsultationsWithMostSubmissions(Pageable pageable);

   /**
    * Find consultations without submissions
    */
   @Query("SELECT c FROM Consultation c WHERE SIZE(c.submissions) = 0 ORDER BY c.publishDate DESC")
   Page<Consultation> findConsultationsWithoutSubmissions(Pageable pageable);

   /**
    * Find high-value consultations (above threshold)
    */
   @Query("SELECT c FROM Consultation c WHERE c.allocatedAmount > :threshold ORDER BY c.allocatedAmount DESC")
   Page<Consultation> findHighValueConsultations(@Param("threshold") double threshold, Pageable pageable);

   /**
    * Find consultations with budget overrun
    */
   @Query("SELECT c FROM Consultation c WHERE c.financialEstimation > c.allocatedAmount ORDER BY (c.financialEstimation - c.allocatedAmount) DESC")
   Page<Consultation> findConsultationsWithBudgetOverrun(Pageable pageable);

   /**
    * Find consultations by procurement director (F_20)
    */
   @Query("SELECT c FROM Consultation c WHERE c.procurementDirector.id = :directorId ORDER BY c.startDate DESC")
   Page<Consultation> findByProcurementDirectorId(@Param("directorId") Long directorId, Pageable pageable);

   /**
    * Find consultations by budget type (F_17)
    */
   @Query("SELECT c FROM Consultation c WHERE c.budgetType.id = :budgetTypeId ORDER BY c.allocatedAmount DESC")
   Page<Consultation> findByBudgetTypeId(@Param("budgetTypeId") Long budgetTypeId, Pageable pageable);

   /**
    * Find consultations by procurement nature (F_16)
    */
   @Query("SELECT c FROM Consultation c WHERE c.procurementNature.id = :natureId ORDER BY c.startDate DESC")
   Page<Consultation> findByProcurementNatureId(@Param("natureId") Long natureId, Pageable pageable);

   /**
    * Check if consultation exists by internal ID and year (for validation)
    */
   @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Consultation c " +
          "WHERE c.internalId = :internalId AND c.consultationYear = :consultationYear")
   boolean existsByInternalIdAndYear(@Param("internalId") String internalId, 
                                    @Param("consultationYear") String consultationYear);

   /**
    * Check if consultation exists by internal ID and year excluding specific ID (for updates)
    */
   @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Consultation c " +
          "WHERE c.internalId = :internalId AND c.consultationYear = :consultationYear AND c.id != :excludeId")
   boolean existsByInternalIdAndYearAndIdNot(@Param("internalId") String internalId, 
                                            @Param("consultationYear") String consultationYear,
                                            @Param("excludeId") Long excludeId);

   /**
    * Count consultations by status
    */
   @Query("SELECT c.procurementStatus.designationFr, COUNT(c) FROM Consultation c GROUP BY c.procurementStatus.designationFr ORDER BY COUNT(c) DESC")
   java.util.List<Object[]> countConsultationsByStatus();

   /**
    * Count consultations by award method
    */
   @Query("SELECT c.awardMethod.designationFr, COUNT(c) FROM Consultation c GROUP BY c.awardMethod.designationFr ORDER BY COUNT(c) DESC")
   java.util.List<Object[]> countConsultationsByAwardMethod();

   /**
    * Get average consultation duration
    */
   @Query("SELECT AVG(DATEDIFF(c.deadline, c.startDate)) FROM Consultation c WHERE c.startDate IS NOT NULL AND c.deadline IS NOT NULL")
   Double getAverageConsultationDuration();

   /**
    * Find consultations with missing required dates
    */
   @Query("SELECT c FROM Consultation c WHERE c.startDate IS NULL OR c.publishDate IS NULL OR c.deadline IS NULL")
   Page<Consultation> findConsultationsWithMissingDates(Pageable pageable);

   /**
    * Find consultations by French designation pattern
    */
   @Query("SELECT c FROM Consultation c WHERE c.designationFr LIKE %:pattern% ORDER BY c.designationFr ASC")
   Page<Consultation> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);
   
	// ========== STATISTICS REPOSITORY METHODS ==========
	
	/**
	 * Count active consultations by year
	 */
	@Query("SELECT COUNT(c) FROM Consultation c WHERE c.consultationYear = :year AND " +
	       "c.publishDate <= :currentDate AND (c.deadline IS NULL OR c.deadline > :currentDate)")
	Long countActiveConsultationsByYear(@Param("year") String year, @Param("currentDate") Date currentDate);
	
	/**
	 * Count expired consultations by year
	 */
	@Query("SELECT COUNT(c) FROM Consultation c WHERE c.consultationYear = :year AND c.deadline < :currentDate")
	Long countExpiredConsultationsByYear(@Param("year") String year, @Param("currentDate") Date currentDate);
	
	/**
	 * Count consultations with submissions by year
	 */
	@Query("SELECT COUNT(DISTINCT c) FROM Consultation c WHERE c.consultationYear = :year AND SIZE(c.submissions) > 0")
	Long countConsultationsWithSubmissionsByYear(@Param("year") String year);
	
	/**
	 * Count high-value consultations by year
	 */
	@Query("SELECT COUNT(c) FROM Consultation c WHERE c.consultationYear = :year AND c.allocatedAmount > :threshold")
	Long countHighValueConsultationsByYear(@Param("year") String year, @Param("threshold") double threshold);
	
	/**
	 * Sum all allocated amounts (all years)
	 */
	@Query("SELECT COALESCE(SUM(c.allocatedAmount), 0.0) FROM Consultation c")
	Double sumAllAllocatedAmount();
	
	/**
	 * Sum all financial estimations (all years)
	 */
	@Query("SELECT COALESCE(SUM(c.financialEstimation), 0.0) FROM Consultation c")
	Double sumAllFinancialEstimation();
	
	/**
	 * Count all active consultations
	 */
	@Query("SELECT COUNT(c) FROM Consultation c WHERE c.publishDate <= :currentDate AND " +
	       "(c.deadline IS NULL OR c.deadline > :currentDate)")
	Long countAllActiveConsultations(@Param("currentDate") Date currentDate);
	
	/**
	 * Count all expired consultations
	 */
	@Query("SELECT COUNT(c) FROM Consultation c WHERE c.deadline < :currentDate")
	Long countAllExpiredConsultations(@Param("currentDate") Date currentDate);
	
	/**
	 * Count all consultations with submissions
	 */
	@Query("SELECT COUNT(DISTINCT c) FROM Consultation c WHERE SIZE(c.submissions) > 0")
	Long countAllConsultationsWithSubmissions();
	
	/**
	 * Count all high-value consultations
	 */
	@Query("SELECT COUNT(c) FROM Consultation c WHERE c.allocatedAmount > :threshold")
	Long countAllHighValueConsultations(@Param("threshold") double threshold);
	
	/**
	 * Get consultation count by month for a year (for trending analysis)
	 */
	@Query("SELECT MONTH(c.startDate) as month, COUNT(c) FROM Consultation c " +
	       "WHERE c.consultationYear = :year AND c.startDate IS NOT NULL " +
	       "GROUP BY MONTH(c.startDate) ORDER BY MONTH(c.startDate)")
	java.util.List<Object[]> countConsultationsByMonthForYear(@Param("year") String year);
	
	/**
	 * Get total allocated amount by month for a year
	 */
	@Query("SELECT MONTH(c.startDate) as month, COALESCE(SUM(c.allocatedAmount), 0.0) FROM Consultation c " +
	       "WHERE c.consultationYear = :year AND c.startDate IS NOT NULL " +
	       "GROUP BY MONTH(c.startDate) ORDER BY MONTH(c.startDate)")
	java.util.List<Object[]> sumAllocatedAmountByMonthForYear(@Param("year") String year);
	
	/**
	 * Get consultation efficiency (financial estimation vs allocated amount) statistics
	 */
	@Query("SELECT AVG(c.financialEstimation / c.allocatedAmount) FROM Consultation c " +
	       "WHERE c.allocatedAmount > 0 AND c.financialEstimation > 0")
	Double getAverageFinancialEfficiency();
	
	/**
	 * Find top spending procurement directors
	 */
	@Query("SELECT c.procurementDirector.designationFr, COALESCE(SUM(c.allocatedAmount), 0.0) FROM Consultation c " +
	       "GROUP BY c.procurementDirector.designationFr ORDER BY SUM(c.allocatedAmount) DESC")
	java.util.List<Object[]> getTopSpendingDirectors();
	
	/**
	 * Find most active award methods (by consultation count)
	 */
	@Query("SELECT c.awardMethod.designationFr, COUNT(c) FROM Consultation c " +
	       "GROUP BY c.awardMethod.designationFr ORDER BY COUNT(c) DESC")
	java.util.List<Object[]> getMostActiveAwardMethods();
	
	/**
	 * Get budget type distribution
	 */
	@Query("SELECT c.budgetType.designationFr, COUNT(c), COALESCE(SUM(c.allocatedAmount), 0.0) FROM Consultation c " +
	       "GROUP BY c.budgetType.designationFr ORDER BY SUM(c.allocatedAmount) DESC")
	java.util.List<Object[]> getBudgetTypeDistribution();
	
	/**
	 * Get procurement nature statistics
	 */
	@Query("SELECT c.procurementNature.designationFr, COUNT(c), COALESCE(AVG(c.allocatedAmount), 0.0) FROM Consultation c " +
	       "GROUP BY c.procurementNature.designationFr ORDER BY COUNT(c) DESC")
	java.util.List<Object[]> getProcurementNatureStats();
	
	/**
	 * Find consultations with longest duration
	 */
	@Query("SELECT c FROM Consultation c WHERE c.startDate IS NOT NULL AND c.deadline IS NOT NULL " +
	       "ORDER BY DATEDIFF(c.deadline, c.startDate) DESC")
	Page<Consultation> findConsultationsWithLongestDuration(Pageable pageable);
	
	/**
	 * Find consultations with shortest duration
	 */
	@Query("SELECT c FROM Consultation c WHERE c.startDate IS NOT NULL AND c.deadline IS NOT NULL " +
	       "ORDER BY DATEDIFF(c.deadline, c.startDate) ASC")
	Page<Consultation> findConsultationsWithShortestDuration(Pageable pageable);
	
	/**
	 * Get success rate by consultation step (consultations with submissions)
	 */
	@Query("SELECT c.consultationStep.designationFr, " +
	       "COUNT(c) as total, " +
	       "COUNT(CASE WHEN SIZE(c.submissions) > 0 THEN 1 END) as withSubmissions, " +
	       "(COUNT(CASE WHEN SIZE(c.submissions) > 0 THEN 1 END) * 100.0 / COUNT(c)) as successRate " +
	       "FROM Consultation c " +
	       "GROUP BY c.consultationStep.designationFr " +
	       "ORDER BY successRate DESC")
	java.util.List<Object[]> getSuccessRateByConsultationStep();
	
	/**
	 * Get quarterly statistics for a year
	 */
	@Query("SELECT " +
	       "CASE " +
	       "WHEN MONTH(c.startDate) IN (1,2,3) THEN 'Q1' " +
	       "WHEN MONTH(c.startDate) IN (4,5,6) THEN 'Q2' " +
	       "WHEN MONTH(c.startDate) IN (7,8,9) THEN 'Q3' " +
	       "WHEN MONTH(c.startDate) IN (10,11,12) THEN 'Q4' " +
	       "END as quarter, " +
	       "COUNT(c) as consultationCount, " +
	       "COALESCE(SUM(c.allocatedAmount), 0.0) as totalAmount, " +
	       "COALESCE(AVG(c.allocatedAmount), 0.0) as avgAmount " +
	       "FROM Consultation c " +
	       "WHERE c.consultationYear = :year AND c.startDate IS NOT NULL " +
	       "GROUP BY " +
	       "CASE " +
	       "WHEN MONTH(c.startDate) IN (1,2,3) THEN 'Q1' " +
	       "WHEN MONTH(c.startDate) IN (4,5,6) THEN 'Q2' " +
	       "WHEN MONTH(c.startDate) IN (7,8,9) THEN 'Q3' " +
	       "WHEN MONTH(c.startDate) IN (10,11,12) THEN 'Q4' " +
	       "END " +
	       "ORDER BY quarter")
	java.util.List<Object[]> getQuarterlyStatistics(@Param("year") String year);
	
	/**
	 * Find consultations requiring urgent attention (expiring within days and no submissions)
	 */
	@Query("SELECT c FROM Consultation c WHERE c.deadline BETWEEN :currentDate AND :futureDate " +
	       "AND SIZE(c.submissions) = 0 ORDER BY c.deadline ASC")
	Page<Consultation> findUrgentConsultationsWithoutSubmissions(@Param("currentDate") Date currentDate,
	                                                            @Param("futureDate") Date futureDate, 
	                                                            Pageable pageable);
	
	/**
	 * Get performance metrics by procurement director
	 */
	@Query("SELECT c.procurementDirector.designationFr, " +
	       "COUNT(c) as totalConsultations, " +
	       "COALESCE(SUM(c.allocatedAmount), 0.0) as totalAmount, " +
	       "COALESCE(AVG(c.allocatedAmount), 0.0) as avgAmount, " +
	       "COUNT(CASE WHEN SIZE(c.submissions) > 0 THEN 1 END) as successfulConsultations, " +
	       "(COUNT(CASE WHEN SIZE(c.submissions) > 0 THEN 1 END) * 100.0 / COUNT(c)) as successRate " +
	       "FROM Consultation c " +
	       "GROUP BY c.procurementDirector.designationFr " +
	       "ORDER BY totalAmount DESC")
	java.util.List<Object[]> getPerformanceMetricsByDirector();
}