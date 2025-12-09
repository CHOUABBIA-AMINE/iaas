/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.Clearance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Clearance Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=startDate, F_02=endDate, 
 * F_03=provider, F_04=providerRepresentator, F_05=reference
 */
@Repository
public interface ClearanceRepository extends JpaRepository<Clearance, Long> {

    /**
     * Find all clearances ordered by start date (most recent first)
     */
    @Query("SELECT c FROM Clearance c ORDER BY c.startDate DESC")
    Page<Clearance> findAllOrderByStartDate(Pageable pageable);

    /**
     * Find clearances by provider
     */
    @Query("SELECT c FROM Clearance c WHERE c.provider.id = :providerId ORDER BY c.startDate DESC")
    Page<Clearance> findByProvider(@Param("providerId") Long providerId, Pageable pageable);

    /**
     * Find clearances by provider representator
     */
    @Query("SELECT c FROM Clearance c WHERE c.providerRepresentator.id = :representatorId ORDER BY c.startDate DESC")
    Page<Clearance> findByProviderRepresentator(@Param("representatorId") Long representatorId, Pageable pageable);

    /**
     * Find clearances by provider and representator
     */
    @Query("SELECT c FROM Clearance c WHERE c.provider.id = :providerId AND c.providerRepresentator.id = :representatorId ORDER BY c.startDate DESC")
    Page<Clearance> findByProviderAndRepresentator(@Param("providerId") Long providerId, @Param("representatorId") Long representatorId, Pageable pageable);

    /**
     * Find active clearances (started but not ended or not yet expired)
     */
    @Query("SELECT c FROM Clearance c WHERE (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    Page<Clearance> findActiveClearances(@Param("currentDate") Date currentDate, Pageable pageable);

    /**
     * Find active clearances for a specific provider
     */
    @Query("SELECT c FROM Clearance c WHERE c.provider.id = :providerId AND (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    List<Clearance> findActiveClearancesForProvider(@Param("providerId") Long providerId, @Param("currentDate") Date currentDate);

    /**
     * Find active clearances for a specific representator
     */
    @Query("SELECT c FROM Clearance c WHERE c.providerRepresentator.id = :representatorId AND (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    List<Clearance> findActiveClearancesForRepresentator(@Param("representatorId") Long representatorId, @Param("currentDate") Date currentDate);

    /**
     * Find expired clearances
     */
    @Query("SELECT c FROM Clearance c WHERE c.endDate IS NOT NULL AND c.endDate < :currentDate")
    Page<Clearance> findExpiredClearances(@Param("currentDate") Date currentDate, Pageable pageable);

    /**
     * Find permanent clearances (no end date)
     */
    @Query("SELECT c FROM Clearance c WHERE c.endDate IS NULL")
    Page<Clearance> findPermanentClearances(Pageable pageable);

    /**
     * Find future clearances (start date in the future)
     */
    @Query("SELECT c FROM Clearance c WHERE c.startDate IS NOT NULL AND c.startDate > :currentDate")
    Page<Clearance> findFutureClearances(@Param("currentDate") Date currentDate, Pageable pageable);

    /**
     * Find clearances expiring soon (within specified days)
     */
    @Query("SELECT c FROM Clearance c WHERE c.endDate IS NOT NULL AND c.endDate > :currentDate AND c.endDate <= :expirationDate")
    Page<Clearance> findClearancesExpiringSoon(@Param("currentDate") Date currentDate, @Param("expirationDate") Date expirationDate, Pageable pageable);

    /**
     * Find clearances by date range (start date within range)
     */
    @Query("SELECT c FROM Clearance c WHERE c.startDate BETWEEN :startDate AND :endDate")
    Page<Clearance> findByStartDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Find clearances that overlap with a specific period
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "(c.startDate IS NULL OR c.startDate <= :endDate) AND " +
           "(c.endDate IS NULL OR c.endDate >= :startDate)")
    Page<Clearance> findOverlappingClearances(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Find clearances by mail reference
     */
    @Query("SELECT c FROM Clearance c WHERE c.reference.id = :referenceId")
    List<Clearance> findByReference(@Param("referenceId") Long referenceId);

    /**
     * Find clearances with reference (has mail reference)
     */
    @Query("SELECT c FROM Clearance c WHERE c.reference IS NOT NULL")
    Page<Clearance> findWithReference(Pageable pageable);

    /**
     * Find clearances without reference
     */
    @Query("SELECT c FROM Clearance c WHERE c.reference IS NULL")
    Page<Clearance> findWithoutReference(Pageable pageable);

    /**
     * Count active clearances for a provider
     */
    @Query("SELECT COUNT(c) FROM Clearance c WHERE c.provider.id = :providerId AND (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    Long countActiveClearancesForProvider(@Param("providerId") Long providerId, @Param("currentDate") Date currentDate);

    /**
     * Count active clearances for a representator
     */
    @Query("SELECT COUNT(c) FROM Clearance c WHERE c.providerRepresentator.id = :representatorId AND (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    Long countActiveClearancesForRepresentator(@Param("representatorId") Long representatorId, @Param("currentDate") Date currentDate);

    /**
     * Count total clearances for a provider
     */
    @Query("SELECT COUNT(c) FROM Clearance c WHERE c.provider.id = :providerId")
    Long countTotalClearancesForProvider(@Param("providerId") Long providerId);

    /**
     * Count total clearances for a representator
     */
    @Query("SELECT COUNT(c) FROM Clearance c WHERE c.providerRepresentator.id = :representatorId")
    Long countTotalClearancesForRepresentator(@Param("representatorId") Long representatorId);

    /**
     * Count active clearances
     */
    @Query("SELECT COUNT(c) FROM Clearance c WHERE (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    Long countActiveClearances(@Param("currentDate") Date currentDate);

    /**
     * Count permanent clearances
     */
    @Query("SELECT COUNT(c) FROM Clearance c WHERE c.endDate IS NULL")
    Long countPermanentClearances();

    /**
     * Count expired clearances
     */
    @Query("SELECT COUNT(c) FROM Clearance c WHERE c.endDate IS NOT NULL AND c.endDate < :currentDate")
    Long countExpiredClearances(@Param("currentDate") Date currentDate);

    /**
     * Find clearances by validity duration (short-term, medium-term, long-term)
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "c.startDate IS NOT NULL AND c.endDate IS NOT NULL AND " +
           "DATEDIFF(c.endDate, c.startDate) BETWEEN :minDays AND :maxDays")
    Page<Clearance> findByValidityDuration(@Param("minDays") Integer minDays, @Param("maxDays") Integer maxDays, Pageable pageable);

    /**
     * Find short-term clearances (duration <= 30 days)
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "c.startDate IS NOT NULL AND c.endDate IS NOT NULL AND " +
           "DATEDIFF(c.endDate, c.startDate) <= 30")
    Page<Clearance> findShortTermClearances(Pageable pageable);

    /**
     * Find medium-term clearances (duration 31-365 days)
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "c.startDate IS NOT NULL AND c.endDate IS NOT NULL AND " +
           "DATEDIFF(c.endDate, c.startDate) BETWEEN 31 AND 365")
    Page<Clearance> findMediumTermClearances(Pageable pageable);

    /**
     * Find long-term clearances (duration > 365 days)
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "c.startDate IS NOT NULL AND c.endDate IS NOT NULL AND " +
           "DATEDIFF(c.endDate, c.startDate) > 365")
    Page<Clearance> findLongTermClearances(Pageable pageable);

    /**
     * Check if provider has any active clearances
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Clearance c WHERE c.provider.id = :providerId AND (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    boolean hasActiveClearances(@Param("providerId") Long providerId, @Param("currentDate") Date currentDate);

    /**
     * Check if representator has any active clearances
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Clearance c WHERE c.providerRepresentator.id = :representatorId AND (c.startDate IS NULL OR c.startDate <= :currentDate) AND (c.endDate IS NULL OR c.endDate > :currentDate)")
    boolean representatorHasActiveClearances(@Param("representatorId") Long representatorId, @Param("currentDate") Date currentDate);

    /**
     * Check if provider has any permanent clearances
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Clearance c WHERE c.provider.id = :providerId AND c.endDate IS NULL")
    boolean hasPermanentClearances(@Param("providerId") Long providerId);

    /**
     * Find most recent clearance for a provider
     */
    @Query("SELECT c FROM Clearance c WHERE c.provider.id = :providerId ORDER BY c.startDate DESC, c.id DESC")
    List<Clearance> findMostRecentForProvider(@Param("providerId") Long providerId, Pageable pageable);

    /**
     * Find most recent clearance for a representator
     */
    @Query("SELECT c FROM Clearance c WHERE c.providerRepresentator.id = :representatorId ORDER BY c.startDate DESC, c.id DESC")
    List<Clearance> findMostRecentForRepresentator(@Param("representatorId") Long representatorId, Pageable pageable);

    /**
     * Find clearances created recently (within N days)
     */
    @Query("SELECT c FROM Clearance c WHERE c.startDate >= :sinceDate OR (c.startDate IS NULL AND c.id IN (SELECT MAX(c2.id) FROM Clearance c2 WHERE c2.startDate IS NULL))")
    Page<Clearance> findRecentClearances(@Param("sinceDate") Date sinceDate, Pageable pageable);

    /**
     * Find overlapping clearances for the same provider and representator
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "c.provider.id = :providerId AND c.providerRepresentator.id = :representatorId AND " +
           "(c.startDate IS NULL OR c.startDate <= :endDate) AND " +
           "(c.endDate IS NULL OR c.endDate >= :startDate)")
    List<Clearance> findOverlappingClearancesForProviderAndRepresentator(
            @Param("providerId") Long providerId, 
            @Param("representatorId") Long representatorId, 
            @Param("startDate") Date startDate, 
            @Param("endDate") Date endDate);

    /**
     * Find clearances by multiple providers
     */
    @Query("SELECT c FROM Clearance c WHERE c.provider.id IN :providerIds ORDER BY c.startDate DESC")
    Page<Clearance> findByProviders(@Param("providerIds") List<Long> providerIds, Pageable pageable);

    /**
     * Find clearances by multiple representators
     */
    @Query("SELECT c FROM Clearance c WHERE c.providerRepresentator.id IN :representatorIds ORDER BY c.startDate DESC")
    Page<Clearance> findByRepresentators(@Param("representatorIds") List<Long> representatorIds, Pageable pageable);

    /**
     * Find clearances that cover a specific date
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "(c.startDate IS NULL OR c.startDate <= :date) AND " +
           "(c.endDate IS NULL OR c.endDate >= :date)")
    Page<Clearance> findCoveringDate(@Param("date") Date date, Pageable pageable);

    /**
     * Find clearances without start date (immediate effect)
     */
    @Query("SELECT c FROM Clearance c WHERE c.startDate IS NULL")
    Page<Clearance> findImmediateClearances(Pageable pageable);

    /**
     * Find clearances with both start and end dates
     */
    @Query("SELECT c FROM Clearance c WHERE c.startDate IS NOT NULL AND c.endDate IS NOT NULL")
    Page<Clearance> findTimeBoundedClearances(Pageable pageable);

    /**
     * Get clearance statistics by month (creation)
     */
    @Query("SELECT YEAR(c.startDate), MONTH(c.startDate), COUNT(c) FROM Clearance c WHERE c.startDate IS NOT NULL GROUP BY YEAR(c.startDate), MONTH(c.startDate) ORDER BY YEAR(c.startDate) DESC, MONTH(c.startDate) DESC")
    List<Object[]> getClearanceStatisticsByMonth();

    /**
     * Get clearance statistics by provider
     */
    @Query("SELECT c.provider.designationLt, COUNT(c) FROM Clearance c WHERE c.provider.designationLt IS NOT NULL GROUP BY c.provider.designationLt ORDER BY COUNT(c) DESC")
    List<Object[]> getClearanceStatisticsByProvider();

    /**
     * Get clearance statistics by duration type
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN c.endDate IS NULL THEN 'PERMANENT' " +
           "WHEN DATEDIFF(c.endDate, c.startDate) <= 30 THEN 'SHORT_TERM' " +
           "WHEN DATEDIFF(c.endDate, c.startDate) <= 365 THEN 'MEDIUM_TERM' " +
           "ELSE 'LONG_TERM' " +
           "END, COUNT(c) " +
           "FROM Clearance c " +
           "GROUP BY " +
           "CASE " +
           "WHEN c.endDate IS NULL THEN 'PERMANENT' " +
           "WHEN DATEDIFF(c.endDate, c.startDate) <= 30 THEN 'SHORT_TERM' " +
           "WHEN DATEDIFF(c.endDate, c.startDate) <= 365 THEN 'MEDIUM_TERM' " +
           "ELSE 'LONG_TERM' " +
           "END")
    List<Object[]> getClearanceStatisticsByDurationType();

    /**
     * Find duplicate clearances (same provider and representator with overlapping periods)
     */
    @Query("SELECT c1 FROM Clearance c1, Clearance c2 WHERE " +
           "c1.id != c2.id AND " +
           "c1.provider.id = c2.provider.id AND " +
           "c1.providerRepresentator.id = c2.providerRepresentator.id AND " +
           "(c1.startDate IS NULL OR c2.endDate IS NULL OR c1.startDate <= c2.endDate) AND " +
           "(c1.endDate IS NULL OR c2.startDate IS NULL OR c1.endDate >= c2.startDate)")
    List<Clearance> findDuplicateClearances();

    /**
     * Find clearances requiring urgent renewal (expiring within 7 days)
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "c.endDate IS NOT NULL AND " +
           "c.endDate > :currentDate AND " +
           "c.endDate <= :urgentDate")
    Page<Clearance> findClearancesRequiringUrgentRenewal(@Param("currentDate") Date currentDate, @Param("urgentDate") Date urgentDate, Pageable pageable);

    /**
     * Find clearances by representator's authority level (through job title)
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%directeur%' OR " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%director%' OR " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%président%' OR " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%gérant%'")
    Page<Clearance> findExecutiveClearances(Pageable pageable);

    /**
     * Find clearances for legal representatives
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%représentant%' OR " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%mandataire%' OR " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%agent%'")
    Page<Clearance> findLegalRepresentativeClearances(Pageable pageable);

    /**
     * Find clearances for technical representatives
     */
    @Query("SELECT c FROM Clearance c WHERE " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%technique%' OR " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%ingénieur%' OR " +
           "LOWER(c.providerRepresentator.jobTitle) LIKE '%chef de projet%'")
    Page<Clearance> findTechnicalRepresentativeClearances(Pageable pageable);
}
