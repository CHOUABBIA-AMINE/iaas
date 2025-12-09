/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.service.ProviderService;
import dz.mdn.iaas.business.provider.dto.ProviderDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Provider REST Controller
 * Handles provider operations: create, get metadata, delete, get all
 * Based on exact Provider model with all 21 fields and complex relationships
 * F_00=id through F_21=stateId, plus many-to-many with EconomicDomain and one-to-many relationships
 */
@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
@Slf4j
public class ProviderController {

    private final ProviderService providerService;

    // ========== POST ONE PROVIDER ==========

    /**
     * Create new provider
     * Creates provider with multilingual designations and complete business information
     */
    @PostMapping
    public ResponseEntity<ProviderDTO> createProvider(@Valid @RequestBody ProviderDTO providerDTO) {
        log.info("Creating provider with designations: LT={}, AR={}, economic nature ID: {}, country ID: {}", 
                providerDTO.getDesignationLt(), providerDTO.getDesignationAr(), 
                providerDTO.getEconomicNatureId(), providerDTO.getCountryId());
        
        ProviderDTO createdProvider = providerService.createProvider(providerDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProvider);
    }

    // ========== GET METADATA ==========

    /**
     * Get provider metadata by ID
     * Returns provider information with complete business details and relationships
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> getProviderMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for provider ID: {}", id);
        
        ProviderDTO providerMetadata = providerService.getProviderById(id);
        
        return ResponseEntity.ok(providerMetadata);
    }

    /**
     * Get provider by commercial registry number (F_07)
     */
    @GetMapping("/registry/{comercialRegistryNumber}")
    public ResponseEntity<ProviderDTO> getProviderByComercialRegistryNumber(@PathVariable String comercialRegistryNumber) {
        log.debug("Getting provider by commercial registry number: {}", comercialRegistryNumber);
        
        return providerService.findByComercialRegistryNumber(comercialRegistryNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get provider by tax identity number (F_09)
     */
    @GetMapping("/tax-id/{taxeIdentityNumber}")
    public ResponseEntity<ProviderDTO> getProviderByTaxeIdentityNumber(@PathVariable String taxeIdentityNumber) {
        log.debug("Getting provider by tax identity number: {}", taxeIdentityNumber);
        
        return providerService.findByTaxeIdentityNumber(taxeIdentityNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get provider by stat identity number (F_10)
     */
    @GetMapping("/stat-id/{statIdentityNumber}")
    public ResponseEntity<ProviderDTO> getProviderByStatIdentityNumber(@PathVariable String statIdentityNumber) {
        log.debug("Getting provider by stat identity number: {}", statIdentityNumber);
        
        return providerService.findByStatIdentityNumber(statIdentityNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete provider by ID
     * Removes provider from the business management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {
        log.info("Deleting provider with ID: {}", id);
        
        providerService.deleteProvider(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all providers with pagination
     * Returns list of all providers ordered by designation
     */
    @GetMapping
    public ResponseEntity<Page<ProviderDTO>> getAllProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationLt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all providers - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProviderDTO> providers = providerService.getAllProviders(pageable);
        
        return ResponseEntity.ok(providers);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search providers by designation or acronym (Latin and Arabic)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProviderDTO>> searchProviders(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationLt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching providers with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProviderDTO> providers = providerService.searchProviders(query, pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Advanced search providers by any field
     */
    @GetMapping("/search/advanced")
    public ResponseEntity<Page<ProviderDTO>> searchProvidersByAnyField(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Advanced searching providers with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.searchProvidersByAnyField(query, pageable);
        
        return ResponseEntity.ok(providers);
    }

    // ========== BUSINESS CLASSIFICATION ENDPOINTS ==========

    /**
     * Get providers by economic nature
     */
    @GetMapping("/economic-nature/{economicNatureId}")
    public ResponseEntity<Page<ProviderDTO>> getProvidersByEconomicNature(
            @PathVariable Long economicNatureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers by economic nature ID: {}", economicNatureId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getProvidersByEconomicNature(economicNatureId, pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get providers by economic domain
     */
    @GetMapping("/economic-domain/{economicDomainId}")
    public ResponseEntity<Page<ProviderDTO>> getProvidersByEconomicDomain(
            @PathVariable Long economicDomainId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers by economic domain ID: {}", economicDomainId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getProvidersByEconomicDomain(economicDomainId, pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get providers by country
     */
    @GetMapping("/country/{countryId}")
    public ResponseEntity<Page<ProviderDTO>> getProvidersByCountry(
            @PathVariable Long countryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers by country ID: {}", countryId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getProvidersByCountry(countryId, pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get providers by state
     */
    @GetMapping("/state/{stateId}")
    public ResponseEntity<Page<ProviderDTO>> getProvidersByState(
            @PathVariable Long stateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers by state ID: {}", stateId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getProvidersByState(stateId, pageable);
        
        return ResponseEntity.ok(providers);
    }

    // ========== BUSINESS SIZE CLASSIFICATION ENDPOINTS ==========

    /**
     * Get large enterprises (capital >= 1B DZD)
     */
    @GetMapping("/large-enterprises")
    public ResponseEntity<Page<ProviderDTO>> getLargeEnterprises(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting large enterprises");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "capital"));
        Page<ProviderDTO> providers = providerService.getLargeEnterprises(pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get medium enterprises (capital 100M-1B DZD)
     */
    @GetMapping("/medium-enterprises")
    public ResponseEntity<Page<ProviderDTO>> getMediumEnterprises(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting medium enterprises");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "capital"));
        Page<ProviderDTO> providers = providerService.getMediumEnterprises(pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get small enterprises (capital 10M-100M DZD)
     */
    @GetMapping("/small-enterprises")
    public ResponseEntity<Page<ProviderDTO>> getSmallEnterprises(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting small enterprises");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "capital"));
        Page<ProviderDTO> providers = providerService.getSmallEnterprises(pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get micro enterprises (capital < 10M DZD)
     */
    @GetMapping("/micro-enterprises")
    public ResponseEntity<Page<ProviderDTO>> getMicroEnterprises(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting micro enterprises");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "capital"));
        Page<ProviderDTO> providers = providerService.getMicroEnterprises(pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get providers by capital range
     */
    @GetMapping("/capital-range")
    public ResponseEntity<Page<ProviderDTO>> getProvidersByCapitalRange(
            @RequestParam Double minCapital,
            @RequestParam Double maxCapital,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers by capital range: {} - {}", minCapital, maxCapital);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "capital"));
        Page<ProviderDTO> providers = providerService.getProvidersByCapitalRange(minCapital, maxCapital, pageable);
        
        return ResponseEntity.ok(providers);
    }

    // ========== REGISTRATION STATUS ENDPOINTS ==========

    /**
     * Get providers with complete registration
     */
    @GetMapping("/complete-registration")
    public ResponseEntity<Page<ProviderDTO>> getProvidersWithCompleteRegistration(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers with complete registration");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getProvidersWithCompleteRegistration(pageable);
        
        return ResponseEntity.ok(providers);
    }

    // ========== SECTOR CLASSIFICATION ENDPOINTS ==========

    /**
     * Get multilingual providers
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ProviderDTO>> getMultilingualProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual providers");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getMultilingualProviders(pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get public sector providers
     */
    @GetMapping("/public-sector")
    public ResponseEntity<Page<ProviderDTO>> getPublicSectorProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting public sector providers");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getPublicSectorProviders(pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get private sector providers
     */
    @GetMapping("/private-sector")
    public ResponseEntity<Page<ProviderDTO>> getPrivateSectorProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting private sector providers");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getPrivateSectorProviders(pageable);
        
        return ResponseEntity.ok(providers);
    }

    // ========== EXCLUSION STATUS ENDPOINTS ==========

    /**
     * Get providers with exclusions
     */
    @GetMapping("/with-exclusions")
    public ResponseEntity<Page<ProviderDTO>> getProvidersWithExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers with exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getProvidersWithExclusions(pageable);
        
        return ResponseEntity.ok(providers);
    }

    /**
     * Get providers without exclusions
     */
    @GetMapping("/without-exclusions")
    public ResponseEntity<Page<ProviderDTO>> getProvidersWithoutExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting providers without exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<ProviderDTO> providers = providerService.getProvidersWithoutExclusions(pageable);
        
        return ResponseEntity.ok(providers);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update provider metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> updateProvider(
            @PathVariable Long id,
            @Valid @RequestBody ProviderDTO providerDTO) {
        
        log.info("Updating provider with ID: {}", id);
        
        ProviderDTO updatedProvider = providerService.updateProvider(id, providerDTO);
        
        return ResponseEntity.ok(updatedProvider);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if provider exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkProviderExists(@PathVariable Long id) {
        log.debug("Checking existence of provider ID: {}", id);
        
        boolean exists = providerService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if commercial registry number exists
     */
    @GetMapping("/exists/registry/{comercialRegistryNumber}")
    public ResponseEntity<Boolean> checkProviderExistsByComercialRegistryNumber(@PathVariable String comercialRegistryNumber) {
        log.debug("Checking existence by commercial registry number: {}", comercialRegistryNumber);
        
        boolean exists = providerService.existsByComercialRegistryNumber(comercialRegistryNumber);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of providers
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getProvidersCount() {
        log.debug("Getting total count of providers");
        
        Long count = providerService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of large enterprises
     */
    @GetMapping("/count/large-enterprises")
    public ResponseEntity<Long> getLargeEnterprisesCount() {
        log.debug("Getting count of large enterprises");
        
        Long count = providerService.getLargeEnterprisesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of medium enterprises
     */
    @GetMapping("/count/medium-enterprises")
    public ResponseEntity<Long> getMediumEnterprisesCount() {
        log.debug("Getting count of medium enterprises");
        
        Long count = providerService.getMediumEnterprisesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of small enterprises
     */
    @GetMapping("/count/small-enterprises")
    public ResponseEntity<Long> getSmallEnterprisesCount() {
        log.debug("Getting count of small enterprises");
        
        Long count = providerService.getSmallEnterprisesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of micro enterprises
     */
    @GetMapping("/count/micro-enterprises")
    public ResponseEntity<Long> getMicroEnterprisesCount() {
        log.debug("Getting count of micro enterprises");
        
        Long count = providerService.getMicroEnterprisesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get provider info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ProviderInfoResponse> getProviderInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for provider ID: {}", id);
        
        try {
            return providerService.findOne(id)
                    .map(providerDTO -> {
                        ProviderInfoResponse response = ProviderInfoResponse.builder()
                                .providerMetadata(providerDTO)
                                .defaultDesignation(providerDTO.getDefaultDesignation())
                                .defaultAcronym(providerDTO.getDefaultAcronym())
                                .displayText(providerDTO.getDisplayText())
                                .isMultilingual(providerDTO.isMultilingual())
                                .availableLanguages(providerDTO.getAvailableLanguages())
                                .hasCompleteRegistration(providerDTO.hasCompleteRegistration())
                                .hasBankingInfo(providerDTO.hasBankingInfo())
                                .hasContactInfo(providerDTO.hasContactInfo())
                                .providerStatus(providerDTO.getProviderStatus())
                                .businessSizeCategory(providerDTO.getBusinessSizeCategory())
                                .providerType(providerDTO.getProviderType())
                                .isInternational(providerDTO.isInternational())
                                .completenessPercentage(providerDTO.getCompletenessPercentage())
                                .shortDisplay(providerDTO.getShortDisplay())
                                .fullDisplay(providerDTO.getFullDisplay())
                                .businessDisplay(providerDTO.getBusinessDisplay())
                                .contactSummary(providerDTO.getContactSummary())
                                .bankingSummary(providerDTO.getBankingSummary())
                                .registrationSummary(providerDTO.getRegistrationSummary())
                                .activitySummary(providerDTO.getActivitySummary())
                                .hasExclusions(providerDTO.hasExclusions())
                                .hasRepresentatives(providerDTO.hasRepresentatives())
                                .hasClearances(providerDTO.hasClearances())
                                .hasSubmissions(providerDTO.hasSubmissions())
                                .formalDisplay(providerDTO.getFormalDisplay())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting provider info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ProviderInfoResponse {
        private ProviderDTO providerMetadata;
        private String defaultDesignation;
        private String defaultAcronym;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private Boolean hasCompleteRegistration;
        private Boolean hasBankingInfo;
        private Boolean hasContactInfo;
        private String providerStatus;
        private String businessSizeCategory;
        private String providerType;
        private Boolean isInternational;
        private Integer completenessPercentage;
        private String shortDisplay;
        private String fullDisplay;
        private String businessDisplay;
        private String contactSummary;
        private String bankingSummary;
        private String registrationSummary;
        private String activitySummary;
        private Boolean hasExclusions;
        private Boolean hasRepresentatives;
        private Boolean hasClearances;
        private Boolean hasSubmissions;
        private String formalDisplay;
    }
}