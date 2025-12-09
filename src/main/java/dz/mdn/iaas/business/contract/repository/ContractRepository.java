/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.business.contract.model.Contract;

/**
 * Contract Repository with rich query support
 * Based on exact field names: 
 * F_00=id, F_01=internalId, F_02=contractYear, F_03=reference, F_04=designationAr, F_05=designationEn,
 * F_06=designationFr, F_07=amount, F_08=transferableAmount, F_09=startDate, F_10=approvalReference,
 * F_11=approvalDate, F_12=contractDate, F_13=notifyDate, F_14=contractDuration, F_15=observation,
 * F_16=contractType, F_17=provider, F_18=realizationStatus, F_19=contractStep, F_20=approvalStatus,
 * F_21=currency, F_22=consultation, F_23=contractUp
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    /** Find contract by internal ID (F_01) */
    @Query("SELECT c FROM Contract c WHERE c.internalId = :internalId")
    Optional<Contract> findByInternalId(@Param("internalId") String internalId);

    /** Find contract by reference (F_03) */
    @Query("SELECT c FROM Contract c WHERE c.reference = :reference")
    Optional<Contract> findByReference(@Param("reference") String reference);

    /** Search contracts by designation (FR, EN, AR) */
    @Query("SELECT c FROM Contract c WHERE " +
           "LOWER(c.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Contract> searchByDesignation(@Param("search") String search, Pageable pageable);

    /** Search by any text field */
    @Query("SELECT c FROM Contract c WHERE " +
           "LOWER(c.internalId) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.reference) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.observation) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Contract> searchByAnyField(@Param("search") String search, Pageable pageable);

    /** Find all contracts ordered by date descending */
    @Query("SELECT c FROM Contract c ORDER BY c.contractDate DESC")
    Page<Contract> findAllOrderByContractDate(Pageable pageable);

    /** Find contracts by year (F_02) */
    @Query("SELECT c FROM Contract c WHERE c.contractYear = :year ORDER BY c.contractDate DESC")
    Page<Contract> findByYear(@Param("year") String year, Pageable pageable);

    /** Find contracts by provider (F_17) */
    @Query("SELECT c FROM Contract c WHERE c.provider.id = :providerId")
    Page<Contract> findByProvider(@Param("providerId") Long providerId, Pageable pageable);

    /** Find contracts by contract type (F_16) */
    @Query("SELECT c FROM Contract c WHERE c.contractType.id = :contractTypeId")
    Page<Contract> findByContractType(@Param("contractTypeId") Long contractTypeId, Pageable pageable);

    /** Find contracts by approval status (F_20) */
    @Query("SELECT c FROM Contract c WHERE c.approvalStatus.id = :approvalStatusId")
    Page<Contract> findByApprovalStatus(@Param("approvalStatusId") Long approvalStatusId, Pageable pageable);

    /** Find contracts by realization status (F_18) */
    @Query("SELECT c FROM Contract c WHERE c.realizationStatus.id = :realizationStatusId")
    Page<Contract> findByRealizationStatus(@Param("realizationStatusId") Long realizationStatusId, Pageable pageable);

    /** Find contracts by currency (F_21) */
    @Query("SELECT c FROM Contract c WHERE c.currency.id = :currencyId")
    Page<Contract> findByCurrency(@Param("currencyId") Long currencyId, Pageable pageable);

    /** Find contracts by consultation (F_22) */
    @Query("SELECT c FROM Contract c WHERE c.consultation.id = :consultationId")
    Page<Contract> findByConsultation(@Param("consultationId") Long consultationId, Pageable pageable);

    /** Find sub-contracts (F_23 = parent contract) */
    @Query("SELECT c FROM Contract c WHERE c.contractUp.id = :contractUpId")
    Page<Contract> findSubContracts(@Param("contractUpId") Long contractUpId, Pageable pageable);

    /** Find contracts signed after a specific date */
    @Query("SELECT c FROM Contract c WHERE c.contractDate >= :date")
    Page<Contract> findSignedAfter(@Param("date") Date date, Pageable pageable);

    /** Find contracts between two dates */
    @Query("SELECT c FROM Contract c WHERE c.contractDate BETWEEN :startDate AND :endDate")
    Page<Contract> findBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /** Find active contracts (based on realization status) */
    @Query("SELECT c FROM Contract c WHERE LOWER(c.realizationStatus.designationFr) LIKE '%en cours%'")
    Page<Contract> findActiveContracts(Pageable pageable);

    /** Find completed contracts */
    @Query("SELECT c FROM Contract c WHERE LOWER(c.realizationStatus.designationFr) LIKE '%achevé%'")
    Page<Contract> findCompletedContracts(Pageable pageable);

    /** Find pending approval contracts */
    @Query("SELECT c FROM Contract c WHERE LOWER(c.approvalStatus.designationFr) LIKE '%en attente%'")
    Page<Contract> findPendingApprovalContracts(Pageable pageable);

    /** Find approved contracts */
    @Query("SELECT c FROM Contract c WHERE LOWER(c.approvalStatus.designationFr) LIKE '%approuvé%'")
    Page<Contract> findApprovedContracts(Pageable pageable);

    /** Find contracts exceeding given amount */
    @Query("SELECT c FROM Contract c WHERE c.amount >= :amount ORDER BY c.amount DESC")
    Page<Contract> findHighValueContracts(@Param("amount") double amount, Pageable pageable);

    /** Find contracts by amount range */
    @Query("SELECT c FROM Contract c WHERE c.amount BETWEEN :minAmount AND :maxAmount ORDER BY c.amount DESC")
    Page<Contract> findByAmountRange(@Param("minAmount") double minAmount, @Param("maxAmount") double maxAmount, Pageable pageable);

    /** Find contracts with transferable amount > 0 */
    @Query("SELECT c FROM Contract c WHERE c.transferableAmount > 0")
    Page<Contract> findWithTransferableAmount(Pageable pageable);

    /** Find contracts without approval date */
    @Query("SELECT c FROM Contract c WHERE c.approvalDate IS NULL")
    Page<Contract> findWithoutApprovalDate(Pageable pageable);

    /** Count total contracts */
    @Query("SELECT COUNT(c) FROM Contract c")
    long countAllContracts();

    /** Count by provider */
    @Query("SELECT COUNT(c) FROM Contract c WHERE c.provider.id = :providerId")
    long countByProvider(@Param("providerId") Long providerId);

    /** Count by contract type */
    @Query("SELECT COUNT(c) FROM Contract c WHERE c.contractType.id = :contractTypeId")
    long countByContractType(@Param("contractTypeId") Long contractTypeId);

    /** Count by approval status */
    @Query("SELECT COUNT(c) FROM Contract c WHERE c.approvalStatus.id = :approvalStatusId")
    long countByApprovalStatus(@Param("approvalStatusId") Long approvalStatusId);

    /** Check existence by internal ID */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Contract c WHERE c.internalId = :internalId")
    boolean existsByInternalId(@Param("internalId") String internalId);

    /** Check existence by reference */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Contract c WHERE c.reference = :reference")
    boolean existsByReference(@Param("reference") String reference);

    /** Check existence by internal ID excluding specific ID */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Contract c WHERE c.internalId = :internalId AND c.id != :id")
    boolean existsByInternalIdAndIdNot(@Param("internalId") String internalId, @Param("id") Long id);

    /** Check existence by reference excluding specific ID */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Contract c WHERE c.reference = :reference AND c.id != :id")
    boolean existsByReferenceAndIdNot(@Param("reference") String reference, @Param("id") Long id);

    /** Find contracts with missing required information */
    @Query("SELECT c FROM Contract c WHERE " +
           "c.internalId IS NULL OR c.internalId = '' OR " +
           "c.reference IS NULL OR c.reference = '' OR " +
           "c.designationFr IS NULL OR c.designationFr = '' OR " +
           "c.provider IS NULL OR c.contractType IS NULL")
    Page<Contract> findWithIncompleteInformation(Pageable pageable);

    /** Find expiring contracts (based on duration and start date) */
    @Query("SELECT c FROM Contract c WHERE FUNCTION('DATEDIFF', CURRENT_DATE, c.startDate) >= (c.contractDuration * 30)")
    Page<Contract> findExpiringContracts(Pageable pageable);

    /** Find recently approved contracts */
    @Query("SELECT c FROM Contract c WHERE c.approvalDate >= :recentDate")
    Page<Contract> findRecentlyApproved(@Param("recentDate") Date recentDate, Pageable pageable);
}
