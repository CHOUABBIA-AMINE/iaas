/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementStatusRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Repository
 *	@Pakage		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.ProcurementStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ProcurementStatus Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Repository
public interface ProcurementStatusRepository extends JpaRepository<ProcurementStatus, Long> {

    /**
     * Find procurement status by French designation (F_03) - unique field
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationFr = :designationFr")
    Optional<ProcurementStatus> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find procurement status by Arabic designation (F_01)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationAr = :designationAr")
    Optional<ProcurementStatus> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find procurement status by English designation (F_02)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationEn = :designationEn")
    Optional<ProcurementStatus> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if procurement status exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ProcurementStatus r WHERE r.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ProcurementStatus r WHERE r.designationFr = :designationFr AND r.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all procurement statuses with pagination ordered by French designation
     */
    @Query("SELECT r FROM ProcurementStatus r ORDER BY r.designationFr ASC")
    Page<ProcurementStatus> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search procurement statuses by any designation field
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "r.designationAr LIKE %:search% OR " +
           "r.designationEn LIKE %:search% OR " +
           "r.designationFr LIKE %:search%")
    Page<ProcurementStatus> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find procurement statuses by French designation pattern (F_03)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationFr LIKE %:pattern%")
    Page<ProcurementStatus> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find procurement statuses by Arabic designation pattern (F_01)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationAr LIKE %:pattern%")
    Page<ProcurementStatus> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find procurement statuses by English designation pattern (F_02)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationEn LIKE %:pattern%")
    Page<ProcurementStatus> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total procurement statuses
     */
    @Query("SELECT COUNT(r) FROM ProcurementStatus r")
    Long countAllProcurementStatuses();

    /**
     * Find procurement statuses that have Arabic designation
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationAr IS NOT NULL AND r.designationAr != ''")
    Page<ProcurementStatus> findWithArabicDesignation(Pageable pageable);

    /**
     * Find procurement statuses that have English designation
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE r.designationEn IS NOT NULL AND r.designationEn != ''")
    Page<ProcurementStatus> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual procurement statuses (have at least 2 designations)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationEn IS NOT NULL AND r.designationEn != '') OR " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationFr IS NOT NULL AND r.designationFr != '') OR " +
           "(r.designationEn IS NOT NULL AND r.designationEn != '' AND r.designationFr IS NOT NULL AND r.designationFr != '')")
    Page<ProcurementStatus> findMultilingualProcurementStatuses(Pageable pageable);

    /**
     * Find planning statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%initial%' OR LOWER(r.designationFr) LIKE '%planification%' OR " +
           "LOWER(r.designationFr) LIKE '%préparation%' OR LOWER(r.designationFr) LIKE '%conception%'")
    Page<ProcurementStatus> findPlanningStatuses(Pageable pageable);

    /**
     * Find in-progress statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en cours%' OR LOWER(r.designationFr) LIKE '%actif%' OR " +
           "LOWER(r.designationFr) LIKE '%exécution%' OR LOWER(r.designationFr) LIKE '%réalisation%'")
    Page<ProcurementStatus> findInProgressStatuses(Pageable pageable);

    /**
     * Find completed statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%' OR " +
           "LOWER(r.designationFr) LIKE '%complété%' OR LOWER(r.designationFr) LIKE '%finalisé%'")
    Page<ProcurementStatus> findCompletedStatuses(Pageable pageable);

    /**
     * Find suspended statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%suspendu%' OR LOWER(r.designationFr) LIKE '%en pause%' OR " +
           "LOWER(r.designationFr) LIKE '%interrompu%' OR LOWER(r.designationFr) LIKE '%gelé%'")
    Page<ProcurementStatus> findSuspendedStatuses(Pageable pageable);

    /**
     * Find cancelled statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%abandonné%' OR " +
           "LOWER(r.designationFr) LIKE '%arrêté%' OR LOWER(r.designationFr) LIKE '%supprimé%'")
    Page<ProcurementStatus> findCancelledStatuses(Pageable pageable);

    /**
     * Find review statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%révision%' OR LOWER(r.designationFr) LIKE '%validation%' OR " +
           "LOWER(r.designationFr) LIKE '%vérification%' OR LOWER(r.designationFr) LIKE '%contrôle%'")
    Page<ProcurementStatus> findReviewStatuses(Pageable pageable);

    /**
     * Find approved statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%approuvé%' OR LOWER(r.designationFr) LIKE '%validé%' OR " +
           "LOWER(r.designationFr) LIKE '%accepté%' OR LOWER(r.designationFr) LIKE '%autorisé%'")
    Page<ProcurementStatus> findApprovedStatuses(Pageable pageable);

    /**
     * Find rejected statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%rejeté%' OR LOWER(r.designationFr) LIKE '%refusé%' OR " +
           "LOWER(r.designationFr) LIKE '%non approuvé%' OR LOWER(r.designationFr) LIKE '%declined%'")
    Page<ProcurementStatus> findRejectedStatuses(Pageable pageable);

    /**
     * Find on-hold statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en attente%' OR LOWER(r.designationFr) LIKE '%standby%' OR " +
           "LOWER(r.designationFr) LIKE '%différé%' OR LOWER(r.designationFr) LIKE '%reporté%'")
    Page<ProcurementStatus> findOnHoldStatuses(Pageable pageable);

    /**
     * Find active statuses (planning, in-progress, under review, approved)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%planification%' OR LOWER(r.designationFr) LIKE '%en cours%' OR " +
           "LOWER(r.designationFr) LIKE '%révision%' OR LOWER(r.designationFr) LIKE '%approuvé%'")
    Page<ProcurementStatus> findActiveStatuses(Pageable pageable);

    /**
     * Find final statuses (completed, cancelled, rejected)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%' OR " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%rejeté%'")
    Page<ProcurementStatus> findFinalStatuses(Pageable pageable);

    /**
     * Find transitional statuses (allow further state changes)
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE NOT (" +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%' OR " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%rejeté%')")
    Page<ProcurementStatus> findTransitionalStatuses(Pageable pageable);

    /**
     * Find procurement statuses ordered by designation in specific language
     */
    @Query("SELECT r FROM ProcurementStatus r ORDER BY r.designationAr ASC")
    Page<ProcurementStatus> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT r FROM ProcurementStatus r ORDER BY r.designationEn ASC")
    Page<ProcurementStatus> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count statuses by category
     */
    @Query("SELECT COUNT(r) FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%planification%' OR LOWER(r.designationFr) LIKE '%initial%'")
    Long countPlanningStatuses();

    @Query("SELECT COUNT(r) FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en cours%' OR LOWER(r.designationFr) LIKE '%exécution%'")
    Long countInProgressStatuses();

    @Query("SELECT COUNT(r) FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%'")
    Long countCompletedStatuses();

    @Query("SELECT COUNT(r) FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%suspendu%' OR LOWER(r.designationFr) LIKE '%en pause%'")
    Long countSuspendedStatuses();

    @Query("SELECT COUNT(r) FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%abandonné%'")
    Long countCancelledStatuses();

    /**
     * Find statuses by project phase
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%initial%' OR LOWER(r.designationFr) LIKE '%approuvé%'")
    Page<ProcurementStatus> findInitiationPhaseStatuses(Pageable pageable);

    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en cours%' OR LOWER(r.designationFr) LIKE '%exécution%'")
    Page<ProcurementStatus> findExecutionPhaseStatuses(Pageable pageable);

    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%révision%' OR LOWER(r.designationFr) LIKE '%contrôle%'")
    Page<ProcurementStatus> findMonitoringPhaseStatuses(Pageable pageable);

    @Query("SELECT r FROM ProcurementStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%'")
    Page<ProcurementStatus> findClosurePhaseStatuses(Pageable pageable);

    /**
     * Search statuses by category pattern
     */
    @Query("SELECT r FROM ProcurementStatus r WHERE LOWER(r.designationFr) LIKE %:categoryPattern%")
    Page<ProcurementStatus> findByStatusCategory(@Param("categoryPattern") String categoryPattern, Pageable pageable);
}
