/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.business.consultation.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    // ========================================
    // Basic Finder Methods
    // ========================================

    /**
     * Find submissions by consultation ID
     * Maps to F_03 foreign key
     */
    List<Submission> findByConsultationId(Long consultationId);

    /**
     * Find submissions by tender (provider) ID
     * Maps to F_04 foreign key
     */
    List<Submission> findByTenderId(Long tenderId);

    /**
     * Find submissions by submission date
     * Maps to F_01 field
     */
    List<Submission> findBySubmissionDate(Date submissionDate);

    /**
     * Find submissions by submission date range
     * Maps to F_01 field
     */
    List<Submission> findBySubmissionDateBetween(Date startDate, Date endDate);

    // ========================================
    // Unique Constraint Support (F_03 + F_04)
    // ========================================

    /**
     * Check if submission exists for consultation and tender combination
     * Supports the unique constraint: T_02_04_05_UK_01 on columns F_03, F_04
     */
    boolean existsByConsultationIdAndTenderId(Long consultationId, Long tenderId);

    /**
     * Find submission by consultation and tender (unique constraint)
     * Supports the unique constraint validation
     */
    Optional<Submission> findByConsultationIdAndTenderId(Long consultationId, Long tenderId);

    // ========================================
    // Financial Offer Queries (F_02)
    // ========================================

    /**
     * Find submissions with financial offer greater than specified amount
     * Maps to F_02 field
     */
    List<Submission> findByFinancialOfferGreaterThan(double amount);

    /**
     * Find submissions with financial offer less than specified amount
     * Maps to F_02 field
     */
    List<Submission> findByFinancialOfferLessThan(double amount);

    /**
     * Find submissions with financial offer between range
     * Maps to F_02 field
     */
    List<Submission> findByFinancialOfferBetween(double minAmount, double maxAmount);


    // ========================================
    // File Attachment Queries (F_05, F_06, F_07)
    // ========================================

    /**
     * Find submissions that have administrative part attached
     * Maps to F_05 foreign key
     */
    List<Submission> findByAdministrativePartIsNotNull();

    /**
     * Find submissions that have technical part attached
     * Maps to F_06 foreign key
     */
    List<Submission> findByTechnicalPartIsNotNull();

    /**
     * Find submissions that have financial part attached
     * Maps to F_07 foreign key
     */
    List<Submission> findByFinancialPartIsNotNull();

    /**
     * Find submissions by administrative part file ID
     * Maps to F_05 foreign key
     */
    List<Submission> findByAdministrativePartId(Long fileId);

    /**
     * Find submissions by technical part file ID
     * Maps to F_06 foreign key
     */
    List<Submission> findByTechnicalPartId(Long fileId);

    /**
     * Find submissions by financial part file ID
     * Maps to F_07 foreign key
     */
    List<Submission> findByFinancialPartId(Long fileId);

    // ========================================
    // Custom Queries with @Query
    // ========================================

    /**
     * Count submissions for a consultation
     * Custom query for performance
     */
    @Query("SELECT COUNT(s) FROM Submission s WHERE s.consultation.id = :consultationId")
    long countByConsultationId(@Param("consultationId") Long consultationId);

    /**
     * Count submissions for a tender (provider)
     * Custom query for performance
     */
    @Query("SELECT COUNT(s) FROM Submission s WHERE s.tender.id = :tenderId")
    long countByTenderId(@Param("tenderId") Long tenderId);

    /**
     * Find submissions with complete documentation (all three parts attached)
     * Custom query combining F_05, F_06, F_07
     */
    @Query("SELECT s FROM Submission s WHERE s.administrativePart IS NOT NULL " +
           "AND s.technicalPart IS NOT NULL AND s.financialPart IS NOT NULL")
    List<Submission> findCompleteSubmissions();

    /**
     * Find submissions with partial documentation (at least one part attached)
     * Custom query for business logic
     */
    @Query("SELECT s FROM Submission s WHERE s.administrativePart IS NOT NULL " +
           "OR s.technicalPart IS NOT NULL OR s.financialPart IS NOT NULL")
    List<Submission> findPartialSubmissions();

    /**
     * Find submissions without any attachments
     * Custom query for business logic
     */
    @Query("SELECT s FROM Submission s WHERE s.administrativePart IS NULL " +
           "AND s.technicalPart IS NULL AND s.financialPart IS NULL")
    List<Submission> findSubmissionsWithoutAttachments();

    /**
     * Find submissions by consultation with financial offers within range
     * Complex query combining multiple fields
     */
    @Query("SELECT s FROM Submission s WHERE s.consultation.id = :consultationId " +
           "AND s.financialOffer BETWEEN :minOffer AND :maxOffer " +
           "ORDER BY s.financialOffer ASC")
    List<Submission> findByConsultationIdAndFinancialOfferBetween(
            @Param("consultationId") Long consultationId,
            @Param("minOffer") double minOffer,
            @Param("maxOffer") double maxOffer);

    /**
     * Get financial offer statistics for a consultation
     * Returns min, max, avg offers
     */
    @Query("SELECT MIN(s.financialOffer), MAX(s.financialOffer), AVG(s.financialOffer) " +
           "FROM Submission s WHERE s.consultation.id = :consultationId AND s.financialOffer > 0")
    Object[] getFinancialOfferStatistics(@Param("consultationId") Long consultationId);

    /**
     * Find lowest financial offer for a consultation
     * Business query for award evaluation
     */
    @Query("SELECT s FROM Submission s WHERE s.consultation.id = :consultationId " +
           "AND s.financialOffer = (SELECT MIN(s2.financialOffer) FROM Submission s2 " +
           "WHERE s2.consultation.id = :consultationId AND s2.financialOffer > 0)")
    List<Submission> findLowestOffersByConsultation(@Param("consultationId") Long consultationId);

    /**
     * Find submissions by tender sorted by financial offer
     * Business query for tender performance analysis
     */
    @Query("SELECT s FROM Submission s WHERE s.tender.id = :tenderId " +
           "ORDER BY s.financialOffer ASC")
    List<Submission> findByTenderIdOrderByFinancialOffer(@Param("tenderId") Long tenderId);

    /**
     * Find recent submissions (last N days)
     * Date-based query for monitoring
     */
    @Query("SELECT s FROM Submission s WHERE s.submissionDate >= :dateFrom " +
           "ORDER BY s.submissionDate DESC")
    List<Submission> findRecentSubmissions(@Param("dateFrom") Date dateFrom);

    /**
     * Find submissions by consultation year
     * Complex query joining with consultation
     */
    @Query("SELECT s FROM Submission s JOIN s.consultation c " +
           "WHERE c.consultationYear = :year")
    List<Submission> findByConsultationYear(@Param("year") String year);

    /**
     * Find competitive submissions (with financial offers) for consultation
     * Business query for evaluation process
     */
    @Query("SELECT s FROM Submission s WHERE s.consultation.id = :consultationId " +
           "AND s.financialOffer > 0 ORDER BY s.financialOffer ASC")
    List<Submission> findCompetitiveSubmissionsByConsultation(@Param("consultationId") Long consultationId);

    /**
     * Check if tender has any submissions
     * Existence check query
     */
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
           "FROM Submission s WHERE s.tender.id = :tenderId")
    boolean existsByTenderId(@Param("tenderId") Long tenderId);

    /**
     * Find submissions by provider economic nature
     * Complex join query for analysis
     */
    @Query("SELECT s FROM Submission s JOIN s.tender t JOIN t.economicNature en " +
           "WHERE en.id = :economicNatureId")
    List<Submission> findByTenderEconomicNature(@Param("economicNatureId") Long economicNatureId);

    /**
     * Count submissions by submission date range
     * Aggregation query for reporting
     */
    @Query("SELECT COUNT(s) FROM Submission s WHERE s.submissionDate BETWEEN :startDate AND :endDate")
    long countBySubmissionDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * Find submissions with all required parts for evaluation
     * Business rule query for award process
     */
    @Query("SELECT s FROM Submission s WHERE s.consultation.id = :consultationId " +
           "AND s.administrativePart IS NOT NULL AND s.technicalPart IS NOT NULL " +
           "AND s.financialPart IS NOT NULL AND s.financialOffer > 0")
    List<Submission> findEvaluableSubmissionsByConsultation(@Param("consultationId") Long consultationId);

    // ========================================
    // Delete Operations
    // ========================================

    /**
     * Delete submissions by consultation ID
     * Cascade delete support
     */
    void deleteByConsultationId(Long consultationId);

    /**
     * Delete submissions by tender ID
     * Cascade delete support
     */
    void deleteByTenderId(Long tenderId);

    /**
     * Delete submissions by file ID (any part)
     * Cleanup when files are deleted
     */
    @Query("DELETE FROM Submission s WHERE s.administrativePart.id = :fileId " +
           "OR s.technicalPart.id = :fileId OR s.financialPart.id = :fileId")
    void deleteByAnyFileId(@Param("fileId") Long fileId);
}
