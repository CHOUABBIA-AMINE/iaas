/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemStatusRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Item Status Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique)
 */
@Repository
public interface ItemStatusRepository extends JpaRepository<ItemStatus, Long> {

    /**
     * Find item status by French designation (F_03) - unique constraint
     */
    @Query("SELECT its FROM ItemStatus its WHERE its.designationFr = :designationFr")
    Optional<ItemStatus> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find all item statuses ordered by French designation
     */
    @Query("SELECT its FROM ItemStatus its ORDER BY its.designationFr ASC")
    Page<ItemStatus> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search item statuses by designation (any language)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(its.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(its.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<ItemStatus> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find active item statuses (based on designation patterns)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%actif%' OR " +
           "LOWER(its.designationEn) LIKE '%active%' OR " +
           "LOWER(its.designationFr) LIKE '%disponible%' OR " +
           "LOWER(its.designationEn) LIKE '%available%' OR " +
           "LOWER(its.designationFr) LIKE '%en stock%' OR " +
           "LOWER(its.designationEn) LIKE '%in stock%'")
    Page<ItemStatus> findActiveStatuses(Pageable pageable);

    /**
     * Find pending item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%en cours%' OR " +
           "LOWER(its.designationEn) LIKE '%pending%' OR " +
           "LOWER(its.designationFr) LIKE '%attente%' OR " +
           "LOWER(its.designationEn) LIKE '%waiting%' OR " +
           "LOWER(its.designationFr) LIKE '%traitement%' OR " +
           "LOWER(its.designationEn) LIKE '%processing%'")
    Page<ItemStatus> findPendingStatuses(Pageable pageable);

    /**
     * Find reserved item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%réservé%' OR " +
           "LOWER(its.designationEn) LIKE '%reserved%' OR " +
           "LOWER(its.designationFr) LIKE '%alloué%' OR " +
           "LOWER(its.designationEn) LIKE '%allocated%' OR " +
           "LOWER(its.designationFr) LIKE '%assigné%' OR " +
           "LOWER(its.designationEn) LIKE '%assigned%'")
    Page<ItemStatus> findReservedStatuses(Pageable pageable);

    /**
     * Find maintenance item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%maintenance%' OR " +
           "LOWER(its.designationEn) LIKE '%maintenance%' OR " +
           "LOWER(its.designationFr) LIKE '%réparation%' OR " +
           "LOWER(its.designationEn) LIKE '%repair%' OR " +
           "LOWER(its.designationFr) LIKE '%entretien%' OR " +
           "LOWER(its.designationFr) LIKE '%révision%' OR " +
           "LOWER(its.designationEn) LIKE '%revision%'")
    Page<ItemStatus> findMaintenanceStatuses(Pageable pageable);

    /**
     * Find damaged item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%endommagé%' OR " +
           "LOWER(its.designationEn) LIKE '%damaged%' OR " +
           "LOWER(its.designationFr) LIKE '%défectueux%' OR " +
           "LOWER(its.designationEn) LIKE '%defective%' OR " +
           "LOWER(its.designationFr) LIKE '%cassé%' OR " +
           "LOWER(its.designationEn) LIKE '%broken%'")
    Page<ItemStatus> findDamagedStatuses(Pageable pageable);

    /**
     * Find obsolete item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%obsolète%' OR " +
           "LOWER(its.designationEn) LIKE '%obsolete%' OR " +
           "LOWER(its.designationFr) LIKE '%retiré%' OR " +
           "LOWER(its.designationEn) LIKE '%retired%' OR " +
           "LOWER(its.designationFr) LIKE '%périmé%' OR " +
           "LOWER(its.designationEn) LIKE '%expired%'")
    Page<ItemStatus> findObsoleteStatuses(Pageable pageable);

    /**
     * Find disposed item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%éliminé%' OR " +
           "LOWER(its.designationEn) LIKE '%disposed%' OR " +
           "LOWER(its.designationFr) LIKE '%mis au rebut%' OR " +
           "LOWER(its.designationEn) LIKE '%scrapped%' OR " +
           "LOWER(its.designationFr) LIKE '%détruit%' OR " +
           "LOWER(its.designationEn) LIKE '%destroyed%'")
    Page<ItemStatus> findDisposedStatuses(Pageable pageable);

    /**
     * Find lost item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%perdu%' OR " +
           "LOWER(its.designationEn) LIKE '%lost%' OR " +
           "LOWER(its.designationFr) LIKE '%manquant%' OR " +
           "LOWER(its.designationEn) LIKE '%missing%' OR " +
           "LOWER(its.designationFr) LIKE '%introuvable%' OR " +
           "LOWER(its.designationEn) LIKE '%not found%'")
    Page<ItemStatus> findLostStatuses(Pageable pageable);

    /**
     * Find procurement item statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%commandé%' OR " +
           "LOWER(its.designationEn) LIKE '%ordered%' OR " +
           "LOWER(its.designationFr) LIKE '%en commande%' OR " +
           "LOWER(its.designationEn) LIKE '%on order%' OR " +
           "LOWER(its.designationFr) LIKE '%approvisionnement%' OR " +
           "LOWER(its.designationEn) LIKE '%procurement%'")
    Page<ItemStatus> findProcurementStatuses(Pageable pageable);

    /**
     * Find operational statuses (active and available)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%actif%' OR LOWER(its.designationFr) LIKE '%disponible%' OR " +
           "LOWER(its.designationEn) LIKE '%active%' OR LOWER(its.designationEn) LIKE '%available%' OR " +
           "LOWER(its.designationFr) LIKE '%opérationnel%' OR " +
           "LOWER(its.designationEn) LIKE '%operational%'")
    Page<ItemStatus> findOperationalStatuses(Pageable pageable);

    /**
     * Find non-operational statuses
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%endommagé%' OR LOWER(its.designationFr) LIKE '%cassé%' OR " +
           "LOWER(its.designationFr) LIKE '%défectueux%' OR LOWER(its.designationFr) LIKE '%perdu%' OR " +
           "LOWER(its.designationEn) LIKE '%damaged%' OR LOWER(its.designationEn) LIKE '%broken%' OR " +
           "LOWER(its.designationEn) LIKE '%defective%' OR LOWER(its.designationEn) LIKE '%lost%'")
    Page<ItemStatus> findNonOperationalStatuses(Pageable pageable);

    /**
     * Find statuses requiring action
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%maintenance%' OR LOWER(its.designationFr) LIKE '%réparation%' OR " +
           "LOWER(its.designationFr) LIKE '%endommagé%' OR LOWER(its.designationFr) LIKE '%perdu%' OR " +
           "LOWER(its.designationFr) LIKE '%en cours%' OR " +
           "LOWER(its.designationEn) LIKE '%maintenance%' OR LOWER(its.designationEn) LIKE '%repair%' OR " +
           "LOWER(its.designationEn) LIKE '%damaged%' OR LOWER(its.designationEn) LIKE '%lost%' OR " +
           "LOWER(its.designationEn) LIKE '%pending%'")
    Page<ItemStatus> findStatusesRequiringAction(Pageable pageable);

    /**
     * Find multilingual item statuses (have designations in multiple languages)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "its.designationFr IS NOT NULL AND its.designationFr != '' AND " +
           "(its.designationEn IS NOT NULL AND its.designationEn != '' OR " +
           "its.designationAr IS NOT NULL AND its.designationAr != '')")
    Page<ItemStatus> findMultilingualStatuses(Pageable pageable);

    /**
     * Find item statuses with Arabic designations
     */
    @Query("SELECT its FROM ItemStatus its WHERE its.designationAr IS NOT NULL AND its.designationAr != ''")
    Page<ItemStatus> findWithArabicDesignation(Pageable pageable);

    /**
     * Find item statuses with English designations
     */
    @Query("SELECT its FROM ItemStatus its WHERE its.designationEn IS NOT NULL AND its.designationEn != ''")
    Page<ItemStatus> findWithEnglishDesignation(Pageable pageable);

    /**
     * Check if French designation exists (for uniqueness validation)
     */
    @Query("SELECT CASE WHEN COUNT(its) > 0 THEN true ELSE false END FROM ItemStatus its WHERE its.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if French designation exists excluding current ID (for update validation)
     */
    @Query("SELECT CASE WHEN COUNT(its) > 0 THEN true ELSE false END FROM ItemStatus its WHERE its.designationFr = :designationFr AND its.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find item statuses by designation pattern (French)
     */
    @Query("SELECT its FROM ItemStatus its WHERE LOWER(its.designationFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<ItemStatus> findByDesignationFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find item statuses with complete information (all required fields filled)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "its.designationFr IS NOT NULL AND its.designationFr != ''")
    Page<ItemStatus> findWithCompleteInformation(Pageable pageable);

    /**
     * Find critical priority statuses (damaged, lost)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%endommagé%' OR LOWER(its.designationFr) LIKE '%perdu%' OR " +
           "LOWER(its.designationFr) LIKE '%cassé%' OR LOWER(its.designationFr) LIKE '%défectueux%' OR " +
           "LOWER(its.designationEn) LIKE '%damaged%' OR LOWER(its.designationEn) LIKE '%lost%' OR " +
           "LOWER(its.designationEn) LIKE '%broken%' OR LOWER(its.designationEn) LIKE '%defective%'")
    Page<ItemStatus> findCriticalPriorityStatuses(Pageable pageable);

    /**
     * Find high priority statuses (maintenance, pending)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%maintenance%' OR LOWER(its.designationFr) LIKE '%en cours%' OR " +
           "LOWER(its.designationFr) LIKE '%attente%' OR LOWER(its.designationFr) LIKE '%réparation%' OR " +
           "LOWER(its.designationEn) LIKE '%maintenance%' OR LOWER(its.designationEn) LIKE '%pending%' OR " +
           "LOWER(its.designationEn) LIKE '%waiting%' OR LOWER(its.designationEn) LIKE '%repair%'")
    Page<ItemStatus> findHighPriorityStatuses(Pageable pageable);

    /**
     * Count total item statuses
     */
    @Query("SELECT COUNT(its) FROM ItemStatus its")
    Long countAllItemStatuses();

    /**
     * Count active statuses
     */
    @Query("SELECT COUNT(its) FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%actif%' OR " +
           "LOWER(its.designationEn) LIKE '%active%' OR " +
           "LOWER(its.designationFr) LIKE '%disponible%' OR " +
           "LOWER(its.designationEn) LIKE '%available%'")
    Long countActiveStatuses();

    /**
     * Count operational statuses
     */
    @Query("SELECT COUNT(its) FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%actif%' OR LOWER(its.designationFr) LIKE '%disponible%' OR " +
           "LOWER(its.designationEn) LIKE '%active%' OR LOWER(its.designationEn) LIKE '%available%' OR " +
           "LOWER(its.designationFr) LIKE '%opérationnel%' OR " +
           "LOWER(its.designationEn) LIKE '%operational%'")
    Long countOperationalStatuses();

    /**
     * Count non-operational statuses
     */
    @Query("SELECT COUNT(its) FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) LIKE '%endommagé%' OR LOWER(its.designationFr) LIKE '%cassé%' OR " +
           "LOWER(its.designationFr) LIKE '%défectueux%' OR LOWER(its.designationFr) LIKE '%perdu%' OR " +
           "LOWER(its.designationEn) LIKE '%damaged%' OR LOWER(its.designationEn) LIKE '%broken%' OR " +
           "LOWER(its.designationEn) LIKE '%defective%' OR LOWER(its.designationEn) LIKE '%lost%'")
    Long countNonOperationalStatuses();

    /**
     * Find similar statuses (for duplicate detection)
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "LOWER(its.designationFr) = LOWER(:designation)")
    List<ItemStatus> findSimilarStatuses(@Param("designation") String designation);

    /**
     * Get item status statistics by category
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(its.designationFr) LIKE '%actif%' OR LOWER(its.designationEn) LIKE '%active%' THEN 'ACTIVE' " +
           "WHEN LOWER(its.designationFr) LIKE '%en cours%' OR LOWER(its.designationEn) LIKE '%pending%' THEN 'PENDING' " +
           "WHEN LOWER(its.designationFr) LIKE '%réservé%' OR LOWER(its.designationEn) LIKE '%reserved%' THEN 'RESERVED' " +
           "WHEN LOWER(its.designationFr) LIKE '%maintenance%' OR LOWER(its.designationEn) LIKE '%maintenance%' THEN 'MAINTENANCE' " +
           "WHEN LOWER(its.designationFr) LIKE '%endommagé%' OR LOWER(its.designationEn) LIKE '%damaged%' THEN 'DAMAGED' " +
           "WHEN LOWER(its.designationFr) LIKE '%obsolète%' OR LOWER(its.designationEn) LIKE '%obsolete%' THEN 'OBSOLETE' " +
           "WHEN LOWER(its.designationFr) LIKE '%perdu%' OR LOWER(its.designationEn) LIKE '%lost%' THEN 'LOST' " +
           "ELSE 'OTHER' " +
           "END, COUNT(its) " +
           "FROM ItemStatus its " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(its.designationFr) LIKE '%actif%' OR LOWER(its.designationEn) LIKE '%active%' THEN 'ACTIVE' " +
           "WHEN LOWER(its.designationFr) LIKE '%en cours%' OR LOWER(its.designationEn) LIKE '%pending%' THEN 'PENDING' " +
           "WHEN LOWER(its.designationFr) LIKE '%réservé%' OR LOWER(its.designationEn) LIKE '%reserved%' THEN 'RESERVED' " +
           "WHEN LOWER(its.designationFr) LIKE '%maintenance%' OR LOWER(its.designationEn) LIKE '%maintenance%' THEN 'MAINTENANCE' " +
           "WHEN LOWER(its.designationFr) LIKE '%endommagé%' OR LOWER(its.designationEn) LIKE '%damaged%' THEN 'DAMAGED' " +
           "WHEN LOWER(its.designationFr) LIKE '%obsolète%' OR LOWER(its.designationEn) LIKE '%obsolete%' THEN 'OBSOLETE' " +
           "WHEN LOWER(its.designationFr) LIKE '%perdu%' OR LOWER(its.designationEn) LIKE '%lost%' THEN 'LOST' " +
           "ELSE 'OTHER' " +
           "END")
    List<Object[]> getItemStatusStatisticsByCategory();

    /**
     * Find most recently added item statuses
     */
    @Query("SELECT its FROM ItemStatus its ORDER BY its.id DESC")
    Page<ItemStatus> findMostRecentStatuses(Pageable pageable);

    /**
     * Find statuses by priority level
     */
    @Query("SELECT its FROM ItemStatus its WHERE " +
           "(:priority = 'CRITICAL' AND " +
           "(LOWER(its.designationFr) LIKE '%endommagé%' OR LOWER(its.designationFr) LIKE '%perdu%' OR " +
           "LOWER(its.designationEn) LIKE '%damaged%' OR LOWER(its.designationEn) LIKE '%lost%')) OR " +
           "(:priority = 'HIGH' AND " +
           "(LOWER(its.designationFr) LIKE '%maintenance%' OR LOWER(its.designationFr) LIKE '%en cours%' OR " +
           "LOWER(its.designationEn) LIKE '%maintenance%' OR LOWER(its.designationEn) LIKE '%pending%')) OR " +
           "(:priority = 'MEDIUM' AND " +
           "(LOWER(its.designationFr) LIKE '%réservé%' OR LOWER(its.designationFr) LIKE '%commandé%' OR " +
           "LOWER(its.designationEn) LIKE '%reserved%' OR LOWER(its.designationEn) LIKE '%ordered%')) OR " +
           "(:priority = 'NORMAL' AND " +
           "(LOWER(its.designationFr) LIKE '%actif%' OR LOWER(its.designationFr) LIKE '%disponible%' OR " +
           "LOWER(its.designationEn) LIKE '%active%' OR LOWER(its.designationEn) LIKE '%available%'))")
    Page<ItemStatus> findByPriorityLevel(@Param("priority") String priority, Pageable pageable);
}
