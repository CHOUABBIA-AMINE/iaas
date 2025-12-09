/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
 * Provider Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationLt, F_02=designationAr, F_03=acronymLt, F_04=acronymAr, 
 * F_05=address, F_06=capital, F_07=comercialRegistryNumber, F_08=comercialRegistryDate, F_09=taxeIdentityNumber, 
 * F_10=statIdentityNumber, F_11=bank, F_12=bankAccount, F_13=swiftNumber, F_14=phoneNumbers, F_15=faxNumbers, 
 * F_16=mail, F_17=website, F_18=logo, F_19=economicNature, F_20=country, F_21=state
 */
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    /**
     * Find provider by commercial registry number (F_07)
     */
    @Query("SELECT p FROM Provider p WHERE p.comercialRegistryNumber = :comercialRegistryNumber")
    Optional<Provider> findByComercialRegistryNumber(@Param("comercialRegistryNumber") String comercialRegistryNumber);

    /**
     * Find provider by tax identity number (F_09)
     */
    @Query("SELECT p FROM Provider p WHERE p.taxeIdentityNumber = :taxeIdentityNumber")
    Optional<Provider> findByTaxeIdentityNumber(@Param("taxeIdentityNumber") String taxeIdentityNumber);

    /**
     * Find provider by stat identity number (F_10)
     */
    @Query("SELECT p FROM Provider p WHERE p.statIdentityNumber = :statIdentityNumber")
    Optional<Provider> findByStatIdentityNumber(@Param("statIdentityNumber") String statIdentityNumber);

    /**
     * Find all providers with pagination ordered by Latin designation
     */
    @Query("SELECT p FROM Provider p ORDER BY p.designationLt ASC, p.designationAr ASC")
    Page<Provider> findAllOrderByDesignation(Pageable pageable);

    /**
     * Search providers by designation or acronym (Latin and Arabic)
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "p.designationLt LIKE %:search% OR " +
           "p.designationAr LIKE %:search% OR " +
           "p.acronymLt LIKE %:search% OR " +
           "p.acronymAr LIKE %:search%")
    Page<Provider> searchByDesignationOrAcronym(@Param("search") String search, Pageable pageable);

    /**
     * Search providers by any field
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "p.designationLt LIKE %:search% OR " +
           "p.designationAr LIKE %:search% OR " +
           "p.acronymLt LIKE %:search% OR " +
           "p.acronymAr LIKE %:search% OR " +
           "p.comercialRegistryNumber LIKE %:search% OR " +
           "p.taxeIdentityNumber LIKE %:search% OR " +
           "p.statIdentityNumber LIKE %:search% OR " +
           "p.mail LIKE %:search%")
    Page<Provider> searchByAnyField(@Param("search") String search, Pageable pageable);

    /**
     * Find providers by economic nature
     */
    @Query("SELECT p FROM Provider p WHERE p.economicNature.id = :economicNatureId")
    Page<Provider> findByEconomicNature(@Param("economicNatureId") Long economicNatureId, Pageable pageable);

    /**
     * Find providers by country
     */
    @Query("SELECT p FROM Provider p WHERE p.country.id = :countryId")
    Page<Provider> findByCountry(@Param("countryId") Long countryId, Pageable pageable);

    /**
     * Find providers by state
     */
    @Query("SELECT p FROM Provider p WHERE p.state.id = :stateId")
    Page<Provider> findByState(@Param("stateId") Long stateId, Pageable pageable);

    /**
     * Find providers by economic domain
     */
    @Query("SELECT DISTINCT p FROM Provider p JOIN p.economicDomains ed WHERE ed.id = :economicDomainId")
    Page<Provider> findByEconomicDomain(@Param("economicDomainId") Long economicDomainId, Pageable pageable);

    /**
     * Find providers with complete registration (has commercial registry number, date, and tax ID)
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "p.comercialRegistryNumber IS NOT NULL AND p.comercialRegistryNumber != '' AND " +
           "p.comercialRegistryDate IS NOT NULL AND " +
           "p.taxeIdentityNumber IS NOT NULL AND p.taxeIdentityNumber != ''")
    Page<Provider> findWithCompleteRegistration(Pageable pageable);

    /**
     * Find providers with banking information
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "p.bank IS NOT NULL AND p.bank != '' AND " +
           "p.bankAccount IS NOT NULL AND p.bankAccount != ''")
    Page<Provider> findWithBankingInfo(Pageable pageable);

    /**
     * Find providers with international banking (SWIFT)
     */
    @Query("SELECT p FROM Provider p WHERE p.swiftNumber IS NOT NULL AND p.swiftNumber != ''")
    Page<Provider> findWithInternationalBanking(Pageable pageable);

    /**
     * Find providers by business size based on capital
     */
    @Query("SELECT p FROM Provider p WHERE p.capital >= :minCapital AND p.capital <= :maxCapital")
    Page<Provider> findByCapitalRange(@Param("minCapital") Double minCapital, @Param("maxCapital") Double maxCapital, Pageable pageable);

    /**
     * Find large enterprises (capital >= 1 billion DZD)
     */
    @Query("SELECT p FROM Provider p WHERE p.capital >= 1000000000")
    Page<Provider> findLargeEnterprises(Pageable pageable);

    /**
     * Find medium enterprises (capital between 100M and 1B DZD)
     */
    @Query("SELECT p FROM Provider p WHERE p.capital >= 100000000 AND p.capital < 1000000000")
    Page<Provider> findMediumEnterprises(Pageable pageable);

    /**
     * Find small enterprises (capital between 10M and 100M DZD)
     */
    @Query("SELECT p FROM Provider p WHERE p.capital >= 10000000 AND p.capital < 100000000")
    Page<Provider> findSmallEnterprises(Pageable pageable);

    /**
     * Find micro enterprises (capital < 10M DZD)
     */
    @Query("SELECT p FROM Provider p WHERE p.capital > 0 AND p.capital < 10000000")
    Page<Provider> findMicroEnterprises(Pageable pageable);

    /**
     * Find providers registered after a specific date
     */
    @Query("SELECT p FROM Provider p WHERE p.comercialRegistryDate >= :date")
    Page<Provider> findRegisteredAfter(@Param("date") Date date, Pageable pageable);

    /**
     * Find providers registered between dates
     */
    @Query("SELECT p FROM Provider p WHERE p.comercialRegistryDate BETWEEN :startDate AND :endDate")
    Page<Provider> findRegisteredBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Find providers with email address
     */
    @Query("SELECT p FROM Provider p WHERE p.mail IS NOT NULL AND p.mail != ''")
    Page<Provider> findWithEmail(Pageable pageable);

    /**
     * Find providers with website
     */
    @Query("SELECT p FROM Provider p WHERE p.website IS NOT NULL AND p.website != ''")
    Page<Provider> findWithWebsite(Pageable pageable);

    /**
     * Find providers with logo
     */
    @Query("SELECT p FROM Provider p WHERE p.logo IS NOT NULL")
    Page<Provider> findWithLogo(Pageable pageable);

    /**
     * Find multilingual providers (have both Latin and Arabic designations)
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "p.designationLt IS NOT NULL AND p.designationLt != '' AND " +
           "p.designationAr IS NOT NULL AND p.designationAr != ''")
    Page<Provider> findMultilingualProviders(Pageable pageable);

    /**
     * Find providers with exclusions
     */
    @Query("SELECT DISTINCT p FROM Provider p JOIN p.providerExclusions pe")
    Page<Provider> findProvidersWithExclusions(Pageable pageable);

    /**
     * Find providers without exclusions
     */
    @Query("SELECT p FROM Provider p WHERE p.providerExclusions IS EMPTY")
    Page<Provider> findProvidersWithoutExclusions(Pageable pageable);

    /**
     * Find providers with representatives
     */
    @Query("SELECT DISTINCT p FROM Provider p JOIN p.providerRepresentators pr")
    Page<Provider> findProvidersWithRepresentatives(Pageable pageable);

    /**
     * Find providers with clearances
     */
    @Query("SELECT DISTINCT p FROM Provider p JOIN p.clearances c")
    Page<Provider> findProvidersWithClearances(Pageable pageable);

    /**
     * Find providers with submissions
     */
    @Query("SELECT DISTINCT p FROM Provider p JOIN p.submissions s")
    Page<Provider> findProvidersWithSubmissions(Pageable pageable);

    /**
     * Count total providers
     */
    @Query("SELECT COUNT(p) FROM Provider p")
    Long countAllProviders();

    /**
     * Count providers by economic nature
     */
    @Query("SELECT COUNT(p) FROM Provider p WHERE p.economicNature.id = :economicNatureId")
    Long countByEconomicNature(@Param("economicNatureId") Long economicNatureId);

    /**
     * Count providers by country
     */
    @Query("SELECT COUNT(p) FROM Provider p WHERE p.country.id = :countryId")
    Long countByCountry(@Param("countryId") Long countryId);

    /**
     * Count providers with complete registration
     */
    @Query("SELECT COUNT(p) FROM Provider p WHERE " +
           "p.comercialRegistryNumber IS NOT NULL AND p.comercialRegistryNumber != '' AND " +
           "p.comercialRegistryDate IS NOT NULL AND " +
           "p.taxeIdentityNumber IS NOT NULL AND p.taxeIdentityNumber != ''")
    Long countWithCompleteRegistration();

    /**
     * Count providers by business size
     */
    @Query("SELECT COUNT(p) FROM Provider p WHERE p.capital >= 1000000000")
    Long countLargeEnterprises();

    @Query("SELECT COUNT(p) FROM Provider p WHERE p.capital >= 100000000 AND p.capital < 1000000000")
    Long countMediumEnterprises();

    @Query("SELECT COUNT(p) FROM Provider p WHERE p.capital >= 10000000 AND p.capital < 100000000")
    Long countSmallEnterprises();

    @Query("SELECT COUNT(p) FROM Provider p WHERE p.capital > 0 AND p.capital < 10000000")
    Long countMicroEnterprises();

    /**
     * Check if commercial registry number exists
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Provider p WHERE p.comercialRegistryNumber = :comercialRegistryNumber")
    boolean existsByComercialRegistryNumber(@Param("comercialRegistryNumber") String comercialRegistryNumber);

    /**
     * Check if commercial registry number exists excluding current ID
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Provider p WHERE p.comercialRegistryNumber = :comercialRegistryNumber AND p.id != :id")
    boolean existsByComercialRegistryNumberAndIdNot(@Param("comercialRegistryNumber") String comercialRegistryNumber, @Param("id") Long id);

    /**
     * Check if tax identity number exists
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Provider p WHERE p.taxeIdentityNumber = :taxeIdentityNumber")
    boolean existsByTaxeIdentityNumber(@Param("taxeIdentityNumber") String taxeIdentityNumber);

    /**
     * Check if tax identity number exists excluding current ID
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Provider p WHERE p.taxeIdentityNumber = :taxeIdentityNumber AND p.id != :id")
    boolean existsByTaxeIdentityNumberAndIdNot(@Param("taxeIdentityNumber") String taxeIdentityNumber, @Param("id") Long id);

    /**
     * Check if stat identity number exists
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Provider p WHERE p.statIdentityNumber = :statIdentityNumber")
    boolean existsByStatIdentityNumber(@Param("statIdentityNumber") String statIdentityNumber);

    /**
     * Check if stat identity number exists excluding current ID
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Provider p WHERE p.statIdentityNumber = :statIdentityNumber AND p.id != :id")
    boolean existsByStatIdentityNumberAndIdNot(@Param("statIdentityNumber") String statIdentityNumber, @Param("id") Long id);

    /**
     * Find providers with incomplete information
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "p.comercialRegistryNumber IS NULL OR p.comercialRegistryNumber = '' OR " +
           "p.comercialRegistryDate IS NULL OR " +
           "p.taxeIdentityNumber IS NULL OR p.taxeIdentityNumber = '' OR " +
           "p.bank IS NULL OR p.bank = '' OR " +
           "p.bankAccount IS NULL OR p.bankAccount = ''")
    Page<Provider> findWithIncompleteInformation(Pageable pageable);

    /**
     * Find providers by email domain
     */
    @Query("SELECT p FROM Provider p WHERE p.mail LIKE %:domain%")
    Page<Provider> findByEmailDomain(@Param("domain") String domain, Pageable pageable);

    /**
     * Find active providers (have recent submissions)
     */
    @Query("SELECT DISTINCT p FROM Provider p JOIN p.submissions s WHERE s.submissionDate >= :date")
    Page<Provider> findActiveProviders(@Param("date") Date date, Pageable pageable);

    /**
     * Find providers by multiple economic domains
     */
    @Query("SELECT DISTINCT p FROM Provider p JOIN p.economicDomains ed WHERE ed.id IN :domainIds")
    Page<Provider> findByEconomicDomains(@Param("domainIds") java.util.List<Long> domainIds, Pageable pageable);

    /**
     * Find public sector providers
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "LOWER(p.economicNature.designationFr) LIKE '%public%' OR " +
           "LOWER(p.economicNature.designationFr) LIKE '%état%' OR " +
           "LOWER(p.economicNature.acronymFr) LIKE '%epa%' OR " +
           "LOWER(p.economicNature.acronymFr) LIKE '%epic%'")
    Page<Provider> findPublicSectorProviders(Pageable pageable);

    /**
     * Find private sector providers
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "LOWER(p.economicNature.designationFr) LIKE '%privé%' OR " +
           "LOWER(p.economicNature.acronymFr) IN ('sarl', 'spa', 'eurl', 'snc', 'scs')")
    Page<Provider> findPrivateSectorProviders(Pageable pageable);

    /**
     * Find foreign providers
     */
    @Query("SELECT p FROM Provider p WHERE " +
           "LOWER(p.economicNature.designationFr) LIKE '%étranger%' OR " +
           "LOWER(p.economicNature.designationFr) LIKE '%international%' OR " +
           "p.country.id != :algerianCountryId")
    Page<Provider> findForeignProviders(@Param("algerianCountryId") Long algerianCountryId, Pageable pageable);
}
