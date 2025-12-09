/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusionRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.ProviderExclusion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Provider Exclusion Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=startDate, F_02=endDate, F_03=cause, 
 * F_04=exclusionType, F_05=provider, F_06=reference
 */
@Repository
public interface ProviderExclusionRepository extends JpaRepository<ProviderExclusion, Long> {

    /**
     * Find all provider exclusions ordered by start date (most recent first)
     */
    @Query("SELECT pe FROM ProviderExclusion pe ORDER BY pe.startDate DESC")
    Page<ProviderExclusion> findAllOrderByStartDate(Pageable pageable);

    /**
     * Find provider exclusions by provider
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.provider.id = :providerId ORDER BY pe.startDate DESC")
    Page<ProviderExclusion> findByProvider(@Param("providerId") Long providerId, Pageable pageable);

    /**
     * Find provider exclusions by exclusion type
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.exclusionType.id = :exclusionTypeId ORDER BY pe.startDate DESC")
    Page<ProviderExclusion> findByExclusionType(@Param("exclusionTypeId") Long exclusionTypeId, Pageable pageable);

    /**
     * Find active provider exclusions (started but not ended or not yet expired)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.startDate <= :currentDate AND (pe.endDate IS NULL OR pe.endDate > :currentDate)")
    Page<ProviderExclusion> findActiveExclusions(@Param("currentDate") Date currentDate, Pageable pageable);

    /**
     * Find active exclusions for a specific provider
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.provider.id = :providerId AND pe.startDate <= :currentDate AND (pe.endDate IS NULL OR pe.endDate > :currentDate)")
    List<ProviderExclusion> findActiveExclusionsForProvider(@Param("providerId") Long providerId, @Param("currentDate") Date currentDate);

    /**
     * Find expired provider exclusions
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.endDate IS NOT NULL AND pe.endDate < :currentDate")
    Page<ProviderExclusion> findExpiredExclusions(@Param("currentDate") Date currentDate, Pageable pageable);

    /**
     * Find permanent exclusions (no end date)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.endDate IS NULL")
    Page<ProviderExclusion> findPermanentExclusions(Pageable pageable);

    /**
     * Find future exclusions (start date in the future)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.startDate > :currentDate")
    Page<ProviderExclusion> findFutureExclusions(@Param("currentDate") Date currentDate, Pageable pageable);

    /**
     * Find exclusions expiring soon (within specified days)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.endDate IS NOT NULL AND pe.endDate > :currentDate AND pe.endDate <= :expirationDate")
    Page<ProviderExclusion> findExclusionsExpiringSoon(@Param("currentDate") Date currentDate, @Param("expirationDate") Date expirationDate, Pageable pageable);

    /**
     * Find exclusions by date range
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.startDate BETWEEN :startDate AND :endDate")
    Page<ProviderExclusion> findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Find exclusions that overlap with a specific period
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.startDate <= :endDate AND (pe.endDate IS NULL OR pe.endDate >= :startDate)")
    Page<ProviderExclusion> findOverlappingExclusions(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Search exclusions by cause
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.cause LIKE %:search%")
    Page<ProviderExclusion> searchByCause(@Param("search") String search, Pageable pageable);

    /**
     * Find exclusions by mail reference
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.reference.id = :referenceId")
    List<ProviderExclusion> findByReference(@Param("referenceId") Long referenceId);

    /**
     * Find exclusions with reference (has mail reference)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.reference IS NOT NULL")
    Page<ProviderExclusion> findWithReference(Pageable pageable);

    /**
     * Find exclusions without reference
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.reference IS NULL")
    Page<ProviderExclusion> findWithoutReference(Pageable pageable);

    /**
     * Count active exclusions for a provider
     */
    @Query("SELECT COUNT(pe) FROM ProviderExclusion pe WHERE pe.provider.id = :providerId AND pe.startDate <= :currentDate AND (pe.endDate IS NULL OR pe.endDate > :currentDate)")
    Long countActiveExclusionsForProvider(@Param("providerId") Long providerId, @Param("currentDate") Date currentDate);

    /**
     * Count total exclusions for a provider
     */
    @Query("SELECT COUNT(pe) FROM ProviderExclusion pe WHERE pe.provider.id = :providerId")
    Long countTotalExclusionsForProvider(@Param("providerId") Long providerId);

    /**
     * Count exclusions by type
     */
    @Query("SELECT COUNT(pe) FROM ProviderExclusion pe WHERE pe.exclusionType.id = :exclusionTypeId")
    Long countByExclusionType(@Param("exclusionTypeId") Long exclusionTypeId);

    /**
     * Count active exclusions
     */
    @Query("SELECT COUNT(pe) FROM ProviderExclusion pe WHERE pe.startDate <= :currentDate AND (pe.endDate IS NULL OR pe.endDate > :currentDate)")
    Long countActiveExclusions(@Param("currentDate") Date currentDate);

    /**
     * Count permanent exclusions
     */
    @Query("SELECT COUNT(pe) FROM ProviderExclusion pe WHERE pe.endDate IS NULL")
    Long countPermanentExclusions();

    /**
     * Count expired exclusions
     */
    @Query("SELECT COUNT(pe) FROM ProviderExclusion pe WHERE pe.endDate IS NOT NULL AND pe.endDate < :currentDate")
    Long countExpiredExclusions(@Param("currentDate") Date currentDate);

    /**
     * Find exclusions by exclusion category (through exclusion type)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE %:category%")
    Page<ProviderExclusion> findByExclusionCategory(@Param("category") String category, Pageable pageable);

    /**
     * Find criminal exclusions
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%criminel%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%pénal%'")
    Page<ProviderExclusion> findCriminalExclusions(Pageable pageable);

    /**
     * Find financial exclusions
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%financier%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%fiscal%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%faillite%'")
    Page<ProviderExclusion> findFinancialExclusions(Pageable pageable);

    /**
     * Find legal exclusions
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%judiciaire%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%juridique%'")
    Page<ProviderExclusion> findLegalExclusions(Pageable pageable);

    /**
     * Find administrative exclusions
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%administratif%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%licence%'")
    Page<ProviderExclusion> findAdministrativeExclusions(Pageable pageable);

    /**
     * Find security exclusions
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%sécurité%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%secret%'")
    Page<ProviderExclusion> findSecurityExclusions(Pageable pageable);

    /**
     * Find exclusions affecting public contracts
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%public%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%criminel%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%fiscal%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%faillite%'")
    Page<ProviderExclusion> findPublicContractExclusions(Pageable pageable);

    /**
     * Find exclusions requiring legal review
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%judiciaire%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%criminel%' OR " +
           "LOWER(pe.exclusionType.designationFr) LIKE '%sécurité%'")
    Page<ProviderExclusion> findLegalReviewExclusions(Pageable pageable);

    /**
     * Find long-term exclusions (duration > 1 year)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "pe.endDate IS NULL OR " +
           "(pe.endDate IS NOT NULL AND pe.startDate IS NOT NULL AND " +
           "DATEDIFF(pe.endDate, pe.startDate) > 365)")
    Page<ProviderExclusion> findLongTermExclusions(Pageable pageable);

    /**
     * Find short-term exclusions (duration <= 1 year)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "pe.endDate IS NOT NULL AND pe.startDate IS NOT NULL AND " +
           "DATEDIFF(pe.endDate, pe.startDate) <= 365")
    Page<ProviderExclusion> findShortTermExclusions(Pageable pageable);

    /**
     * Find exclusions by duration range (in days)
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE " +
           "pe.endDate IS NOT NULL AND pe.startDate IS NOT NULL AND " +
           "DATEDIFF(pe.endDate, pe.startDate) BETWEEN :minDays AND :maxDays")
    Page<ProviderExclusion> findByDurationRange(@Param("minDays") Integer minDays, @Param("maxDays") Integer maxDays, Pageable pageable);

    /**
     * Check if provider has any active exclusions
     */
    @Query("SELECT CASE WHEN COUNT(pe) > 0 THEN true ELSE false END FROM ProviderExclusion pe WHERE pe.provider.id = :providerId AND pe.startDate <= :currentDate AND (pe.endDate IS NULL OR pe.endDate > :currentDate)")
    boolean hasActiveExclusions(@Param("providerId") Long providerId, @Param("currentDate") Date currentDate);

    /**
     * Check if provider has any permanent exclusions
     */
    @Query("SELECT CASE WHEN COUNT(pe) > 0 THEN true ELSE false END FROM ProviderExclusion pe WHERE pe.provider.id = :providerId AND pe.endDate IS NULL")
    boolean hasPermanentExclusions(@Param("providerId") Long providerId);

    /**
     * Find most recent exclusion for a provider
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.provider.id = :providerId ORDER BY pe.startDate DESC")
    List<ProviderExclusion> findMostRecentForProvider(@Param("providerId") Long providerId, Pageable pageable);

    /**
     * Find exclusions created in the last N days
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.startDate >= :sinceDate")
    Page<ProviderExclusion> findRecentExclusions(@Param("sinceDate") Date sinceDate, Pageable pageable);

    /**
     * Find exclusions by provider and type
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.provider.id = :providerId AND pe.exclusionType.id = :exclusionTypeId")
    List<ProviderExclusion> findByProviderAndType(@Param("providerId") Long providerId, @Param("exclusionTypeId") Long exclusionTypeId);

    /**
     * Find overlapping exclusions for a provider
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.provider.id = :providerId AND pe.startDate <= :endDate AND (pe.endDate IS NULL OR pe.endDate >= :startDate)")
    List<ProviderExclusion> findOverlappingExclusionsForProvider(@Param("providerId") Long providerId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * Find exclusions by multiple providers
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.provider.id IN :providerIds")
    Page<ProviderExclusion> findByProviders(@Param("providerIds") List<Long> providerIds, Pageable pageable);

    /**
     * Find exclusions by multiple exclusion types
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.exclusionType.id IN :exclusionTypeIds")
    Page<ProviderExclusion> findByExclusionTypes(@Param("exclusionTypeIds") List<Long> exclusionTypeIds, Pageable pageable);

    /**
     * Find exclusions with cause containing specific text
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.cause IS NOT NULL AND LOWER(pe.cause) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    Page<ProviderExclusion> findByCauseContaining(@Param("searchText") String searchText, Pageable pageable);

    /**
     * Find exclusions without cause specified
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.cause IS NULL OR pe.cause = ''")
    Page<ProviderExclusion> findWithoutCause(Pageable pageable);

    /**
     * Get exclusion statistics by month
     */
    @Query("SELECT YEAR(pe.startDate), MONTH(pe.startDate), COUNT(pe) FROM ProviderExclusion pe GROUP BY YEAR(pe.startDate), MONTH(pe.startDate) ORDER BY YEAR(pe.startDate) DESC, MONTH(pe.startDate) DESC")
    List<Object[]> getExclusionStatisticsByMonth();

    /**
     * Get exclusion statistics by exclusion type
     */
    @Query("SELECT pe.exclusionType.designationFr, COUNT(pe) FROM ProviderExclusion pe GROUP BY pe.exclusionType.designationFr ORDER BY COUNT(pe) DESC")
    List<Object[]> getExclusionStatisticsByType();

    /**
     * Find duplicate exclusions (same provider and type with overlapping periods)
     */
    @Query("SELECT pe1 FROM ProviderExclusion pe1, ProviderExclusion pe2 WHERE " +
           "pe1.id != pe2.id AND " +
           "pe1.provider.id = pe2.provider.id AND " +
           "pe1.exclusionType.id = pe2.exclusionType.id AND " +
           "pe1.startDate <= COALESCE(pe2.endDate, pe1.startDate) AND " +
           "COALESCE(pe1.endDate, pe2.startDate) >= pe2.startDate")
    List<ProviderExclusion> findDuplicateExclusions();
}