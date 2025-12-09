/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractTypeRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Repository
 *	@Pakage		: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.business.contract.model.ContractType;

/**
 * ContractType Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {

    /**
     * Find approval status by French designation (F_03) - unique field
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationFr = :designationFr")
    Optional<ContractType> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find approval status by Arabic designation (F_01)
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationAr = :designationAr")
    Optional<ContractType> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find approval status by English designation (F_02)
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationEn = :designationEn")
    Optional<ContractType> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if approval status exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM ContractType a WHERE a.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM ContractType a WHERE a.designationFr = :designationFr AND a.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all approval statuses with pagination ordered by French designation
     */
    @Query("SELECT a FROM ContractType a ORDER BY a.designationFr ASC")
    Page<ContractType> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search approval statuses by any designation field
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "a.designationAr LIKE %:search% OR " +
           "a.designationEn LIKE %:search% OR " +
           "a.designationFr LIKE %:search%")
    Page<ContractType> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find approval statuses by French designation pattern (F_03)
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationFr LIKE %:pattern%")
    Page<ContractType> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find approval statuses by Arabic designation pattern (F_01)
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationAr LIKE %:pattern%")
    Page<ContractType> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find approval statuses by English designation pattern (F_02)
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationEn LIKE %:pattern%")
    Page<ContractType> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total approval statuses
     */
    @Query("SELECT COUNT(a) FROM ContractType a")
    Long countAllContractTypees();

    /**
     * Find approval statuses that have Arabic designation
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationAr IS NOT NULL AND a.designationAr != ''")
    Page<ContractType> findWithArabicDesignation(Pageable pageable);

    /**
     * Find approval statuses that have English designation
     */
    @Query("SELECT a FROM ContractType a WHERE a.designationEn IS NOT NULL AND a.designationEn != ''")
    Page<ContractType> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual approval statuses (have at least 2 designations)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "(a.designationAr IS NOT NULL AND a.designationAr != '' AND a.designationEn IS NOT NULL AND a.designationEn != '') OR " +
           "(a.designationAr IS NOT NULL AND a.designationAr != '' AND a.designationFr IS NOT NULL AND a.designationFr != '') OR " +
           "(a.designationEn IS NOT NULL AND a.designationEn != '' AND a.designationFr IS NOT NULL AND a.designationFr != '')")
    Page<ContractType> findMultilingualContractTypees(Pageable pageable);

    /**
     * Find approved statuses (based on French designation patterns)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%approuvé%' OR LOWER(a.designationFr) LIKE '%approved%' OR " +
           "LOWER(a.designationFr) LIKE '%accepté%' OR LOWER(a.designationFr) LIKE '%validé%'")
    Page<ContractType> findApprovedStatuses(Pageable pageable);

    /**
     * Find rejected statuses (based on French designation patterns)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%refusé%' OR LOWER(a.designationFr) LIKE '%rejected%' OR " +
           "LOWER(a.designationFr) LIKE '%rejeté%' OR LOWER(a.designationFr) LIKE '%declined%'")
    Page<ContractType> findRejectedStatuses(Pageable pageable);

    /**
     * Find pending statuses (based on French designation patterns)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%en attente%' OR LOWER(a.designationFr) LIKE '%pending%' OR " +
           "LOWER(a.designationFr) LIKE '%en cours%' OR LOWER(a.designationFr) LIKE '%processing%'")
    Page<ContractType> findPendingStatuses(Pageable pageable);

    /**
     * Find draft statuses (based on French designation patterns)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%brouillon%' OR LOWER(a.designationFr) LIKE '%draft%' OR " +
           "LOWER(a.designationFr) LIKE '%temporaire%'")
    Page<ContractType> findDraftStatuses(Pageable pageable);

    /**
     * Find review statuses (based on French designation patterns)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%révision%' OR LOWER(a.designationFr) LIKE '%review%' OR " +
           "LOWER(a.designationFr) LIKE '%vérification%'")
    Page<ContractType> findReviewStatuses(Pageable pageable);

    /**
     * Find suspended statuses (based on French designation patterns)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%suspendu%' OR LOWER(a.designationFr) LIKE '%suspended%' OR " +
           "LOWER(a.designationFr) LIKE '%gelé%'")
    Page<ContractType> findSuspendedStatuses(Pageable pageable);

    /**
     * Find cancelled statuses (based on French designation patterns)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%annulé%' OR LOWER(a.designationFr) LIKE '%cancelled%' OR " +
           "LOWER(a.designationFr) LIKE '%canceled%'")
    Page<ContractType> findCancelledStatuses(Pageable pageable);

    /**
     * Find final statuses (approved, rejected, or cancelled)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%approuvé%' OR LOWER(a.designationFr) LIKE '%approved%' OR " +
           "LOWER(a.designationFr) LIKE '%refusé%' OR LOWER(a.designationFr) LIKE '%rejected%' OR " +
           "LOWER(a.designationFr) LIKE '%annulé%' OR LOWER(a.designationFr) LIKE '%cancelled%'")
    Page<ContractType> findFinalStatuses(Pageable pageable);

    /**
     * Find non-final statuses (pending, draft, under review)
     */
    @Query("SELECT a FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%en attente%' OR LOWER(a.designationFr) LIKE '%pending%' OR " +
           "LOWER(a.designationFr) LIKE '%brouillon%' OR LOWER(a.designationFr) LIKE '%draft%' OR " +
           "LOWER(a.designationFr) LIKE '%révision%' OR LOWER(a.designationFr) LIKE '%review%'")
    Page<ContractType> findNonFinalStatuses(Pageable pageable);

    /**
     * Find approval statuses ordered by designation in specific language
     */
    @Query("SELECT a FROM ContractType a ORDER BY a.designationAr ASC")
    Page<ContractType> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT a FROM ContractType a ORDER BY a.designationEn ASC")
    Page<ContractType> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count approval statuses by type
     */
    @Query("SELECT COUNT(a) FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%approuvé%' OR LOWER(a.designationFr) LIKE '%approved%'")
    Long countApprovedStatuses();

    @Query("SELECT COUNT(a) FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%refusé%' OR LOWER(a.designationFr) LIKE '%rejected%'")
    Long countRejectedStatuses();

    @Query("SELECT COUNT(a) FROM ContractType a WHERE " +
           "LOWER(a.designationFr) LIKE '%en attente%' OR LOWER(a.designationFr) LIKE '%pending%'")
    Long countPendingStatuses();
}
