/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationStatusRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Repository
 *	@Pakage		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.RealizationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RealizationStatus Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Repository
public interface RealizationStatusRepository extends JpaRepository<RealizationStatus, Long> {

    /**
     * Find realization status by French designation (F_03) - unique field
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationFr = :designationFr")
    Optional<RealizationStatus> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find realization status by Arabic designation (F_01)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationAr = :designationAr")
    Optional<RealizationStatus> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find realization status by English designation (F_02)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationEn = :designationEn")
    Optional<RealizationStatus> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if realization status exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RealizationStatus r WHERE r.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RealizationStatus r WHERE r.designationFr = :designationFr AND r.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all realization statuses with pagination ordered by French designation
     */
    @Query("SELECT r FROM RealizationStatus r ORDER BY r.designationFr ASC")
    Page<RealizationStatus> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search realization statuses by any designation field
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "r.designationAr LIKE %:search% OR " +
           "r.designationEn LIKE %:search% OR " +
           "r.designationFr LIKE %:search%")
    Page<RealizationStatus> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find realization statuses by French designation pattern (F_03)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationFr LIKE %:pattern%")
    Page<RealizationStatus> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find realization statuses by Arabic designation pattern (F_01)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationAr LIKE %:pattern%")
    Page<RealizationStatus> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find realization statuses by English designation pattern (F_02)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationEn LIKE %:pattern%")
    Page<RealizationStatus> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total realization statuses
     */
    @Query("SELECT COUNT(r) FROM RealizationStatus r")
    Long countAllRealizationStatuses();

    /**
     * Find realization statuses that have Arabic designation
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationAr IS NOT NULL AND r.designationAr != ''")
    Page<RealizationStatus> findWithArabicDesignation(Pageable pageable);

    /**
     * Find realization statuses that have English designation
     */
    @Query("SELECT r FROM RealizationStatus r WHERE r.designationEn IS NOT NULL AND r.designationEn != ''")
    Page<RealizationStatus> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual realization statuses (have at least 2 designations)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationEn IS NOT NULL AND r.designationEn != '') OR " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationFr IS NOT NULL AND r.designationFr != '') OR " +
           "(r.designationEn IS NOT NULL AND r.designationEn != '' AND r.designationFr IS NOT NULL AND r.designationFr != '')")
    Page<RealizationStatus> findMultilingualRealizationStatuses(Pageable pageable);

    /**
     * Find planning statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%initial%' OR LOWER(r.designationFr) LIKE '%planification%' OR " +
           "LOWER(r.designationFr) LIKE '%préparation%' OR LOWER(r.designationFr) LIKE '%conception%'")
    Page<RealizationStatus> findPlanningStatuses(Pageable pageable);

    /**
     * Find in-progress statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en cours%' OR LOWER(r.designationFr) LIKE '%actif%' OR " +
           "LOWER(r.designationFr) LIKE '%exécution%' OR LOWER(r.designationFr) LIKE '%réalisation%'")
    Page<RealizationStatus> findInProgressStatuses(Pageable pageable);

    /**
     * Find completed statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%' OR " +
           "LOWER(r.designationFr) LIKE '%complété%' OR LOWER(r.designationFr) LIKE '%finalisé%'")
    Page<RealizationStatus> findCompletedStatuses(Pageable pageable);

    /**
     * Find suspended statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%suspendu%' OR LOWER(r.designationFr) LIKE '%en pause%' OR " +
           "LOWER(r.designationFr) LIKE '%interrompu%' OR LOWER(r.designationFr) LIKE '%gelé%'")
    Page<RealizationStatus> findSuspendedStatuses(Pageable pageable);

    /**
     * Find cancelled statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%abandonné%' OR " +
           "LOWER(r.designationFr) LIKE '%arrêté%' OR LOWER(r.designationFr) LIKE '%supprimé%'")
    Page<RealizationStatus> findCancelledStatuses(Pageable pageable);

    /**
     * Find review statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%révision%' OR LOWER(r.designationFr) LIKE '%validation%' OR " +
           "LOWER(r.designationFr) LIKE '%vérification%' OR LOWER(r.designationFr) LIKE '%contrôle%'")
    Page<RealizationStatus> findReviewStatuses(Pageable pageable);

    /**
     * Find approved statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%approuvé%' OR LOWER(r.designationFr) LIKE '%validé%' OR " +
           "LOWER(r.designationFr) LIKE '%accepté%' OR LOWER(r.designationFr) LIKE '%autorisé%'")
    Page<RealizationStatus> findApprovedStatuses(Pageable pageable);

    /**
     * Find rejected statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%rejeté%' OR LOWER(r.designationFr) LIKE '%refusé%' OR " +
           "LOWER(r.designationFr) LIKE '%non approuvé%' OR LOWER(r.designationFr) LIKE '%declined%'")
    Page<RealizationStatus> findRejectedStatuses(Pageable pageable);

    /**
     * Find on-hold statuses (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en attente%' OR LOWER(r.designationFr) LIKE '%standby%' OR " +
           "LOWER(r.designationFr) LIKE '%différé%' OR LOWER(r.designationFr) LIKE '%reporté%'")
    Page<RealizationStatus> findOnHoldStatuses(Pageable pageable);

    /**
     * Find active statuses (planning, in-progress, under review, approved)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%planification%' OR LOWER(r.designationFr) LIKE '%en cours%' OR " +
           "LOWER(r.designationFr) LIKE '%révision%' OR LOWER(r.designationFr) LIKE '%approuvé%'")
    Page<RealizationStatus> findActiveStatuses(Pageable pageable);

    /**
     * Find final statuses (completed, cancelled, rejected)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%' OR " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%rejeté%'")
    Page<RealizationStatus> findFinalStatuses(Pageable pageable);

    /**
     * Find transitional statuses (allow further state changes)
     */
    @Query("SELECT r FROM RealizationStatus r WHERE NOT (" +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%' OR " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%rejeté%')")
    Page<RealizationStatus> findTransitionalStatuses(Pageable pageable);

    /**
     * Find realization statuses ordered by designation in specific language
     */
    @Query("SELECT r FROM RealizationStatus r ORDER BY r.designationAr ASC")
    Page<RealizationStatus> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT r FROM RealizationStatus r ORDER BY r.designationEn ASC")
    Page<RealizationStatus> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count statuses by category
     */
    @Query("SELECT COUNT(r) FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%planification%' OR LOWER(r.designationFr) LIKE '%initial%'")
    Long countPlanningStatuses();

    @Query("SELECT COUNT(r) FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en cours%' OR LOWER(r.designationFr) LIKE '%exécution%'")
    Long countInProgressStatuses();

    @Query("SELECT COUNT(r) FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%'")
    Long countCompletedStatuses();

    @Query("SELECT COUNT(r) FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%suspendu%' OR LOWER(r.designationFr) LIKE '%en pause%'")
    Long countSuspendedStatuses();

    @Query("SELECT COUNT(r) FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%annulé%' OR LOWER(r.designationFr) LIKE '%abandonné%'")
    Long countCancelledStatuses();

    /**
     * Find statuses by project phase
     */
    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%initial%' OR LOWER(r.designationFr) LIKE '%approuvé%'")
    Page<RealizationStatus> findInitiationPhaseStatuses(Pageable pageable);

    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%en cours%' OR LOWER(r.designationFr) LIKE '%exécution%'")
    Page<RealizationStatus> findExecutionPhaseStatuses(Pageable pageable);

    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%révision%' OR LOWER(r.designationFr) LIKE '%contrôle%'")
    Page<RealizationStatus> findMonitoringPhaseStatuses(Pageable pageable);

    @Query("SELECT r FROM RealizationStatus r WHERE " +
           "LOWER(r.designationFr) LIKE '%terminé%' OR LOWER(r.designationFr) LIKE '%achevé%'")
    Page<RealizationStatus> findClosurePhaseStatuses(Pageable pageable);

    /**
     * Search statuses by category pattern
     */
    @Query("SELECT r FROM RealizationStatus r WHERE LOWER(r.designationFr) LIKE %:categoryPattern%")
    Page<RealizationStatus> findByStatusCategory(@Param("categoryPattern") String categoryPattern, Pageable pageable);
}
