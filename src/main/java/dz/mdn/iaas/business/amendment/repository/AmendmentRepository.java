/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.business.amendment.model.Amendment;

/**
 * Amendment Repository with advanced query support.
 * Based on field names:
 * F_00=id, F_01=internalId, F_02=reference, F_03=designationAr, F_04=designationEn, F_05=designationFr,
 * F_06=amount, F_07=transferableAmount, F_08=startDate, F_09=approvalDate, F_10=notifyDate, F_11=observation,
 * F_12=contract, F_13=amendmentType, F_14=procurementStatus, F_15=amendmentStep, F_16=approvalStatus, F_17=currency
 */
@Repository
public interface AmendmentRepository extends JpaRepository<Amendment, Long> {

    /** Find Amendment by internal ID (F_01) */
    @Query("SELECT a FROM Amendment a WHERE a.internalId = :internalId")
    Optional<Amendment> findByInternalId(@Param("internalId") int internalId);

    /** Find Amendment by reference (F_02) */
    @Query("SELECT a FROM Amendment a WHERE a.reference = :reference")
    Optional<Amendment> findByReference(@Param("reference") String reference);

    /** Search Amendments by designation (FR, EN, AR) */
    @Query("SELECT a FROM Amendment a WHERE " +
           "LOWER(a.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Amendment> searchByDesignation(@Param("search") String search, Pageable pageable);

    /** Search Amendments by any textual field */
    @Query("SELECT a FROM Amendment a WHERE " +
           "LOWER(a.reference) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.designationAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.observation) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Amendment> searchByAnyField(@Param("search") String search, Pageable pageable);

    /** Find Amendments by Contract (F_12) */
    @Query("SELECT a FROM Amendment a WHERE a.contract.id = :contractId")
    Page<Amendment> findByContract(@Param("contractId") Long contractId, Pageable pageable);

    /** Find Amendments by Amendment Type (F_13) */
    @Query("SELECT a FROM Amendment a WHERE a.amendmentType.id = :typeId")
    Page<Amendment> findByAmendmentType(@Param("typeId") Long typeId, Pageable pageable);

    /** Find Amendments by Procurement Status (F_14) */
    @Query("SELECT a FROM Amendment a WHERE a.procurementStatus.id = :statusId")
    Page<Amendment> findByProcurementStatus(@Param("statusId") Long statusId, Pageable pageable);

    /** Find Amendments by Approval Status (F_16) */
    @Query("SELECT a FROM Amendment a WHERE a.approvalStatus.id = :approvalStatusId")
    Page<Amendment> findByApprovalStatus(@Param("approvalStatusId") Long approvalStatusId, Pageable pageable);

    /** Find Amendments by Currency (F_17) */
    @Query("SELECT a FROM Amendment a WHERE a.currency.id = :currencyId")
    Page<Amendment> findByCurrency(@Param("currencyId") Long currencyId, Pageable pageable);

    /** Find Amendments by start date range */
    @Query("SELECT a FROM Amendment a WHERE a.startDate BETWEEN :startDate AND :endDate")
    Page<Amendment> findByStartDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /** Find Amendments approved after a given date */
    @Query("SELECT a FROM Amendment a WHERE a.approvalDate >= :date")
    Page<Amendment> findApprovedAfter(@Param("date") Date date, Pageable pageable);

    /** Find Amendments notified after a given date */
    @Query("SELECT a FROM Amendment a WHERE a.notifyDate >= :date")
    Page<Amendment> findNotifiedAfter(@Param("date") Date date, Pageable pageable);

    /** Find Amendments by amount range */
    @Query("SELECT a FROM Amendment a WHERE a.amount BETWEEN :minAmount AND :maxAmount ORDER BY a.amount DESC")
    Page<Amendment> findByAmountRange(@Param("minAmount") double minAmount, @Param("maxAmount") double maxAmount, Pageable pageable);

    /** Find Amendments exceeding given amount */
    @Query("SELECT a FROM Amendment a WHERE a.amount >= :amount ORDER BY a.amount DESC")
    Page<Amendment> findHighValueAmendments(@Param("amount") double amount, Pageable pageable);

    /** Find Amendments with transferable amount > 0 */
    @Query("SELECT a FROM Amendment a WHERE a.transferableAmount > 0")
    Page<Amendment> findWithTransferableAmount(Pageable pageable);

    /** Find Amendments without approval date */
    @Query("SELECT a FROM Amendment a WHERE a.approvalDate IS NULL")
    Page<Amendment> findWithoutApprovalDate(Pageable pageable);

    /** Find pending approval Amendments */
    @Query("SELECT a FROM Amendment a WHERE LOWER(a.approvalStatus.designationFr) LIKE '%en attente%'")
    Page<Amendment> findPendingApprovalAmendments(Pageable pageable);

    /** Find approved Amendments */
    @Query("SELECT a FROM Amendment a WHERE LOWER(a.approvalStatus.designationFr) LIKE '%approuvé%'")
    Page<Amendment> findApprovedAmendments(Pageable pageable);

    /** Find active Amendments */
    @Query("SELECT a FROM Amendment a WHERE LOWER(a.procurementStatus.designationFr) LIKE '%en cours%'")
    Page<Amendment> findActiveAmendments(Pageable pageable);

    /** Find completed Amendments */
    @Query("SELECT a FROM Amendment a WHERE LOWER(a.procurementStatus.designationFr) LIKE '%achevé%'")
    Page<Amendment> findCompletedAmendments(Pageable pageable);

    /** Count total Amendments */
    @Query("SELECT COUNT(a) FROM Amendment a")
    long countAllAmendments();

    /** Count by Contract */
    @Query("SELECT COUNT(a) FROM Amendment a WHERE a.contract.id = :contractId")
    long countByContract(@Param("contractId") Long contractId);

    /** Count by Amendment Type */
    @Query("SELECT COUNT(a) FROM Amendment a WHERE a.amendmentType.id = :typeId")
    long countByAmendmentType(@Param("typeId") Long typeId);

    /** Count by Approval Status */
    @Query("SELECT COUNT(a) FROM Amendment a WHERE a.approvalStatus.id = :approvalStatusId")
    long countByApprovalStatus(@Param("approvalStatusId") Long approvalStatusId);

    /** Check if an Amendment exists by reference */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Amendment a WHERE a.reference = :reference")
    boolean existsByReference(@Param("reference") String reference);

    /** Check if an Amendment exists by internal ID */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Amendment a WHERE a.internalId = :internalId")
    boolean existsByInternalId(@Param("internalId") int internalId);

    /** Check if an Amendment exists by reference excluding a specific ID */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Amendment a WHERE a.reference = :reference AND a.id != :id")
    boolean existsByReferenceAndIdNot(@Param("reference") String reference, @Param("id") Long id);

    /** Find Amendments with missing required information */
    @Query("SELECT a FROM Amendment a WHERE " +
           "a.reference IS NULL OR a.reference = '' OR " +
           "a.designationFr IS NULL OR a.designationFr = '' OR " +
           "a.contract IS NULL OR a.amendmentType IS NULL")
    Page<Amendment> findWithIncompleteInformation(Pageable pageable);

    /** Find recently approved Amendments */
    @Query("SELECT a FROM Amendment a WHERE a.approvalDate >= :recentDate")
    Page<Amendment> findRecentlyApproved(@Param("recentDate") Date recentDate, Pageable pageable);
}
