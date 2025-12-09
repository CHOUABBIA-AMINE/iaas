/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentatorRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.ProviderRepresentator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Provider Representator Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=firstname, F_02=lastname, F_03=birthDate, 
 * F_04=birthPlace, F_05=address, F_06=jobTitle, F_07=mobilePhoneNumber, F_08=fixPhoneNumber, 
 * F_09=mail, F_10=provider
 */
@Repository
public interface ProviderRepresentatorRepository extends JpaRepository<ProviderRepresentator, Long> {

    /**
     * Find all provider representators ordered by lastname, firstname
     */
    @Query("SELECT pr FROM ProviderRepresentator pr ORDER BY pr.lastname ASC, pr.firstname ASC")
    Page<ProviderRepresentator> findAllOrderByName(Pageable pageable);

    /**
     * Find provider representators by provider
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.provider.id = :providerId ORDER BY pr.lastname ASC, pr.firstname ASC")
    Page<ProviderRepresentator> findByProvider(@Param("providerId") Long providerId, Pageable pageable);

    /**
     * Find all representators for a specific provider (without pagination)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.provider.id = :providerId ORDER BY pr.lastname ASC, pr.firstname ASC")
    List<ProviderRepresentator> findAllByProvider(@Param("providerId") Long providerId);

    /**
     * Search provider representators by name (firstname or lastname)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.firstname) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(pr.lastname) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<ProviderRepresentator> searchByName(@Param("search") String search, Pageable pageable);

    /**
     * Search provider representators by any field
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.firstname) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(pr.lastname) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(pr.jobTitle) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(pr.mail) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "pr.mobilePhoneNumber LIKE CONCAT('%', :search, '%') OR " +
           "pr.fixPhoneNumber LIKE CONCAT('%', :search, '%')")
    Page<ProviderRepresentator> searchByAnyField(@Param("search") String search, Pageable pageable);

    /**
     * Find provider representator by full name and provider
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.firstname = :firstname AND pr.lastname = :lastname AND pr.provider.id = :providerId")
    Optional<ProviderRepresentator> findByFullNameAndProvider(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("providerId") Long providerId);

    /**
     * Find provider representators by job title
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE LOWER(pr.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%'))")
    Page<ProviderRepresentator> findByJobTitleContaining(@Param("jobTitle") String jobTitle, Pageable pageable);

    /**
     * Find provider representators by email
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.mail = :email")
    Optional<ProviderRepresentator> findByEmail(@Param("email") String email);

    /**
     * Find provider representators by mobile phone number
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.mobilePhoneNumber = :mobilePhone")
    Optional<ProviderRepresentator> findByMobilePhoneNumber(@Param("mobilePhone") String mobilePhone);

    /**
     * Find provider representators with email addresses
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.mail IS NOT NULL AND pr.mail != ''")
    Page<ProviderRepresentator> findWithEmail(Pageable pageable);

    /**
     * Find provider representators with mobile phone numbers
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.mobilePhoneNumber IS NOT NULL AND pr.mobilePhoneNumber != ''")
    Page<ProviderRepresentator> findWithMobilePhone(Pageable pageable);

    /**
     * Find provider representators with complete contact information
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "(pr.mobilePhoneNumber IS NOT NULL AND pr.mobilePhoneNumber != '') OR " +
           "(pr.fixPhoneNumber IS NOT NULL AND pr.fixPhoneNumber != '') OR " +
           "(pr.mail IS NOT NULL AND pr.mail != '')")
    Page<ProviderRepresentator> findWithContactInfo(Pageable pageable);

    /**
     * Find provider representators without contact information
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "(pr.mobilePhoneNumber IS NULL OR pr.mobilePhoneNumber = '') AND " +
           "(pr.fixPhoneNumber IS NULL OR pr.fixPhoneNumber = '') AND " +
           "(pr.mail IS NULL OR pr.mail = '')")
    Page<ProviderRepresentator> findWithoutContactInfo(Pageable pageable);

    /**
     * Find provider representators by birth place
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE LOWER(pr.birthPlace) LIKE LOWER(CONCAT('%', :birthPlace, '%'))")
    Page<ProviderRepresentator> findByBirthPlaceContaining(@Param("birthPlace") String birthPlace, Pageable pageable);

    /**
     * Find executive representators (based on job title patterns)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%directeur%' OR LOWER(pr.jobTitle) LIKE '%director%' OR " +
           "LOWER(pr.jobTitle) LIKE '%président%' OR LOWER(pr.jobTitle) LIKE '%president%' OR " +
           "LOWER(pr.jobTitle) LIKE '%gérant%' OR LOWER(pr.jobTitle) LIKE '%manager%' OR " +
           "LOWER(pr.jobTitle) LIKE '%pdg%' OR LOWER(pr.jobTitle) LIKE '%ceo%'")
    Page<ProviderRepresentator> findExecutiveRepresentators(Pageable pageable);

    /**
     * Find legal representators (based on job title patterns)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%représentant%' OR LOWER(pr.jobTitle) LIKE '%representative%' OR " +
           "LOWER(pr.jobTitle) LIKE '%mandataire%' OR LOWER(pr.jobTitle) LIKE '%agent%' OR " +
           "LOWER(pr.jobTitle) LIKE '%délégué%' OR LOWER(pr.jobTitle) LIKE '%delegate%'")
    Page<ProviderRepresentator> findLegalRepresentators(Pageable pageable);

    /**
     * Find technical representators (based on job title patterns)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%technique%' OR LOWER(pr.jobTitle) LIKE '%technical%' OR " +
           "LOWER(pr.jobTitle) LIKE '%ingénieur%' OR LOWER(pr.jobTitle) LIKE '%engineer%' OR " +
           "LOWER(pr.jobTitle) LIKE '%chef de projet%' OR LOWER(pr.jobTitle) LIKE '%project manager%'")
    Page<ProviderRepresentator> findTechnicalRepresentators(Pageable pageable);

    /**
     * Find commercial representators (based on job title patterns)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%commercial%' OR LOWER(pr.jobTitle) LIKE '%sales%' OR " +
           "LOWER(pr.jobTitle) LIKE '%ventes%' OR LOWER(pr.jobTitle) LIKE '%marketing%' OR " +
           "LOWER(pr.jobTitle) LIKE '%client%' OR LOWER(pr.jobTitle) LIKE '%customer%'")
    Page<ProviderRepresentator> findCommercialRepresentators(Pageable pageable);

    /**
     * Find administrative representators (based on job title patterns)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%administratif%' OR LOWER(pr.jobTitle) LIKE '%administrative%' OR " +
           "LOWER(pr.jobTitle) LIKE '%secrétaire%' OR LOWER(pr.jobTitle) LIKE '%secretary%' OR " +
           "LOWER(pr.jobTitle) LIKE '%assistant%' OR LOWER(pr.jobTitle) LIKE '%coordinateur%'")
    Page<ProviderRepresentator> findAdministrativeRepresentators(Pageable pageable);

    /**
     * Find financial representators (based on job title patterns)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%financier%' OR LOWER(pr.jobTitle) LIKE '%financial%' OR " +
           "LOWER(pr.jobTitle) LIKE '%comptable%' OR LOWER(pr.jobTitle) LIKE '%accountant%' OR " +
           "LOWER(pr.jobTitle) LIKE '%trésorier%' OR LOWER(pr.jobTitle) LIKE '%treasurer%'")
    Page<ProviderRepresentator> findFinancialRepresentators(Pageable pageable);

    /**
     * Count representators by provider
     */
    @Query("SELECT COUNT(pr) FROM ProviderRepresentator pr WHERE pr.provider.id = :providerId")
    Long countByProvider(@Param("providerId") Long providerId);

    /**
     * Count representators with complete contact information
     */
    @Query("SELECT COUNT(pr) FROM ProviderRepresentator pr WHERE " +
           "(pr.mobilePhoneNumber IS NOT NULL AND pr.mobilePhoneNumber != '') OR " +
           "(pr.fixPhoneNumber IS NOT NULL AND pr.fixPhoneNumber != '') OR " +
           "(pr.mail IS NOT NULL AND pr.mail != '')")
    Long countWithContactInfo();

    /**
     * Count executive representators
     */
    @Query("SELECT COUNT(pr) FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%directeur%' OR LOWER(pr.jobTitle) LIKE '%director%' OR " +
           "LOWER(pr.jobTitle) LIKE '%président%' OR LOWER(pr.jobTitle) LIKE '%president%' OR " +
           "LOWER(pr.jobTitle) LIKE '%gérant%' OR LOWER(pr.jobTitle) LIKE '%manager%' OR " +
           "LOWER(pr.jobTitle) LIKE '%pdg%' OR LOWER(pr.jobTitle) LIKE '%ceo%'")
    Long countExecutiveRepresentators();

    /**
     * Check if email exists
     */
    @Query("SELECT CASE WHEN COUNT(pr) > 0 THEN true ELSE false END FROM ProviderRepresentator pr WHERE pr.mail = :email")
    boolean existsByEmail(@Param("email") String email);

    /**
     * Check if email exists excluding current ID
     */
    @Query("SELECT CASE WHEN COUNT(pr) > 0 THEN true ELSE false END FROM ProviderRepresentator pr WHERE pr.mail = :email AND pr.id != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

    /**
     * Check if mobile phone exists
     */
    @Query("SELECT CASE WHEN COUNT(pr) > 0 THEN true ELSE false END FROM ProviderRepresentator pr WHERE pr.mobilePhoneNumber = :mobilePhone")
    boolean existsByMobilePhoneNumber(@Param("mobilePhone") String mobilePhone);

    /**
     * Check if mobile phone exists excluding current ID
     */
    @Query("SELECT CASE WHEN COUNT(pr) > 0 THEN true ELSE false END FROM ProviderRepresentator pr WHERE pr.mobilePhoneNumber = :mobilePhone AND pr.id != :id")
    boolean existsByMobilePhoneNumberAndIdNot(@Param("mobilePhone") String mobilePhone, @Param("id") Long id);

    /**
     * Find representators with job titles
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.jobTitle IS NOT NULL AND pr.jobTitle != ''")
    Page<ProviderRepresentator> findWithJobTitle(Pageable pageable);

    /**
     * Find representators without job titles
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.jobTitle IS NULL OR pr.jobTitle = ''")
    Page<ProviderRepresentator> findWithoutJobTitle(Pageable pageable);

    /**
     * Find representators with complete personal information
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "pr.firstname IS NOT NULL AND pr.firstname != '' AND " +
           "pr.lastname IS NOT NULL AND pr.lastname != '' AND " +
           "pr.birthDate IS NOT NULL AND pr.birthDate != '' AND " +
           "pr.birthPlace IS NOT NULL AND pr.birthPlace != ''")
    Page<ProviderRepresentator> findWithCompletePersonalInfo(Pageable pageable);

    /**
     * Find representators with address information
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.address IS NOT NULL AND pr.address != ''")
    Page<ProviderRepresentator> findWithAddress(Pageable pageable);

    /**
     * Find representators by multiple providers
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.provider.id IN :providerIds ORDER BY pr.lastname ASC, pr.firstname ASC")
    Page<ProviderRepresentator> findByProviders(@Param("providerIds") List<Long> providerIds, Pageable pageable);

    /**
     * Find representators by firstname pattern
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE LOWER(pr.firstname) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<ProviderRepresentator> findByFirstnameContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find representators by lastname pattern
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE LOWER(pr.lastname) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<ProviderRepresentator> findByLastnameContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find representators with similar names (for duplicate detection)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.firstname) = LOWER(:firstname) AND LOWER(pr.lastname) = LOWER(:lastname)")
    List<ProviderRepresentator> findBySimilarName(@Param("firstname") String firstname, @Param("lastname") String lastname);

    /**
     * Find most recent representator for a provider
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.provider.id = :providerId ORDER BY pr.id DESC")
    List<ProviderRepresentator> findMostRecentForProvider(@Param("providerId") Long providerId, Pageable pageable);

    /**
     * Find primary contact representators (executives or legal representatives)
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE " +
           "LOWER(pr.jobTitle) LIKE '%directeur%' OR LOWER(pr.jobTitle) LIKE '%director%' OR " +
           "LOWER(pr.jobTitle) LIKE '%président%' OR LOWER(pr.jobTitle) LIKE '%president%' OR " +
           "LOWER(pr.jobTitle) LIKE '%gérant%' OR LOWER(pr.jobTitle) LIKE '%manager%' OR " +
           "LOWER(pr.jobTitle) LIKE '%représentant%' OR LOWER(pr.jobTitle) LIKE '%representative%'")
    Page<ProviderRepresentator> findPrimaryContactRepresentators(Pageable pageable);

    /**
     * Get representator statistics by job type
     */
    @Query("SELECT " +
           "CASE " +
           "WHEN LOWER(pr.jobTitle) LIKE '%directeur%' OR LOWER(pr.jobTitle) LIKE '%director%' OR LOWER(pr.jobTitle) LIKE '%président%' OR LOWER(pr.jobTitle) LIKE '%pdg%' THEN 'EXECUTIVE' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%représentant%' OR LOWER(pr.jobTitle) LIKE '%mandataire%' THEN 'LEGAL' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%technique%' OR LOWER(pr.jobTitle) LIKE '%ingénieur%' THEN 'TECHNICAL' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%commercial%' OR LOWER(pr.jobTitle) LIKE '%sales%' THEN 'COMMERCIAL' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%financier%' OR LOWER(pr.jobTitle) LIKE '%comptable%' THEN 'FINANCIAL' " +
           "ELSE 'OTHER' " +
           "END, COUNT(pr) " +
           "FROM ProviderRepresentator pr " +
           "WHERE pr.jobTitle IS NOT NULL AND pr.jobTitle != '' " +
           "GROUP BY " +
           "CASE " +
           "WHEN LOWER(pr.jobTitle) LIKE '%directeur%' OR LOWER(pr.jobTitle) LIKE '%director%' OR LOWER(pr.jobTitle) LIKE '%président%' OR LOWER(pr.jobTitle) LIKE '%pdg%' THEN 'EXECUTIVE' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%représentant%' OR LOWER(pr.jobTitle) LIKE '%mandataire%' THEN 'LEGAL' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%technique%' OR LOWER(pr.jobTitle) LIKE '%ingénieur%' THEN 'TECHNICAL' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%commercial%' OR LOWER(pr.jobTitle) LIKE '%sales%' THEN 'COMMERCIAL' " +
           "WHEN LOWER(pr.jobTitle) LIKE '%financier%' OR LOWER(pr.jobTitle) LIKE '%comptable%' THEN 'FINANCIAL' " +
           "ELSE 'OTHER' " +
           "END")
    List<Object[]> getRepresentatorStatisticsByJobType();
}