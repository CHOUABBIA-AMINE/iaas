/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.provider.dto.ProviderDTO;
import dz.mdn.iaas.business.provider.model.Provider;
import dz.mdn.iaas.business.provider.repository.EconomicDomainRepository;
import dz.mdn.iaas.business.provider.repository.EconomicNatureRepository;
import dz.mdn.iaas.business.provider.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Provider Service with CRUD operations
 * Handles provider management operations with multilingual support and complex business logic
 * Based on exact field names and all relationships including many-to-many with EconomicDomain
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProviderService {

    private final ProviderRepository providerRepository;

    // Repository beans for related entities (injected as needed)
    private final dz.mdn.iaas.system.utility.repository.FileRepository fileRepository;
    private final EconomicNatureRepository economicNatureRepository;
    private final dz.mdn.iaas.common.administration.repository.CountryRepository countryRepository;
    private final dz.mdn.iaas.common.administration.repository.StateRepository stateRepository;
    private final EconomicDomainRepository economicDomainRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new provider
     */
    public ProviderDTO createProvider(ProviderDTO providerDTO) {
        log.info("Creating provider with designation: LT={}, AR={}", 
                providerDTO.getDesignationLt(), providerDTO.getDesignationAr());

        // Validate required fields
        validateRequiredFields(providerDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(providerDTO, null);

        // Create entity with exact field mapping
        Provider provider = new Provider();
        mapDtoToEntity(providerDTO, provider);

        // Handle foreign key relationships
        setEntityRelationships(providerDTO, provider);

        Provider savedProvider = providerRepository.save(provider);

        // Handle many-to-many relationships
        handleEconomicDomainsRelationship(providerDTO, savedProvider);

        log.info("Successfully created provider with ID: {}", savedProvider.getId());

        return ProviderDTO.fromEntityWithRelations(savedProvider);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get provider by ID
     */
    @Transactional(readOnly = true)
    public ProviderDTO getProviderById(Long id) {
        log.debug("Getting provider with ID: {}", id);

        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + id));

        return ProviderDTO.fromEntityWithRelations(provider);
    }

    /**
     * Get provider entity by ID
     */
    @Transactional(readOnly = true)
    public Provider getProviderEntityById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + id));
    }

    /**
     * Find provider by commercial registry number
     */
    @Transactional(readOnly = true)
    public Optional<ProviderDTO> findByComercialRegistryNumber(String comercialRegistryNumber) {
        log.debug("Finding provider with commercial registry number: {}", comercialRegistryNumber);

        return providerRepository.findByComercialRegistryNumber(comercialRegistryNumber)
                .map(ProviderDTO::fromEntityWithRelations);
    }

    /**
     * Find provider by tax identity number
     */
    @Transactional(readOnly = true)
    public Optional<ProviderDTO> findByTaxeIdentityNumber(String taxeIdentityNumber) {
        log.debug("Finding provider with tax identity number: {}", taxeIdentityNumber);

        return providerRepository.findByTaxeIdentityNumber(taxeIdentityNumber)
                .map(ProviderDTO::fromEntityWithRelations);
    }

    /**
     * Find provider by stat identity number
     */
    @Transactional(readOnly = true)
    public Optional<ProviderDTO> findByStatIdentityNumber(String statIdentityNumber) {
        log.debug("Finding provider with stat identity number: {}", statIdentityNumber);

        return providerRepository.findByStatIdentityNumber(statIdentityNumber)
                .map(ProviderDTO::fromEntityWithRelations);
    }

    /**
     * Get all providers with pagination
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getAllProviders(Pageable pageable) {
        log.debug("Getting all providers with pagination");

        Page<Provider> providers = providerRepository.findAllOrderByDesignation(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Find one provider by ID
     */
    @Transactional(readOnly = true)
    public Optional<ProviderDTO> findOne(Long id) {
        log.debug("Finding provider by ID: {}", id);

        return providerRepository.findById(id)
                .map(ProviderDTO::fromEntityWithRelations);
    }

    /**
     * Search providers by designation or acronym
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> searchProviders(String searchTerm, Pageable pageable) {
        log.debug("Searching providers with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProviders(pageable);
        }

        Page<Provider> providers = providerRepository.searchByDesignationOrAcronym(searchTerm.trim(), pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Search providers by any field
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> searchProvidersByAnyField(String searchTerm, Pageable pageable) {
        log.debug("Searching providers by any field with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProviders(pageable);
        }

        Page<Provider> providers = providerRepository.searchByAnyField(searchTerm.trim(), pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers by economic nature
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersByEconomicNature(Long economicNatureId, Pageable pageable) {
        log.debug("Getting providers by economic nature ID: {}", economicNatureId);

        Page<Provider> providers = providerRepository.findByEconomicNature(economicNatureId, pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers by country
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersByCountry(Long countryId, Pageable pageable) {
        log.debug("Getting providers by country ID: {}", countryId);

        Page<Provider> providers = providerRepository.findByCountry(countryId, pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers by state
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersByState(Long stateId, Pageable pageable) {
        log.debug("Getting providers by state ID: {}", stateId);

        Page<Provider> providers = providerRepository.findByState(stateId, pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers by economic domain
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersByEconomicDomain(Long economicDomainId, Pageable pageable) {
        log.debug("Getting providers by economic domain ID: {}", economicDomainId);

        Page<Provider> providers = providerRepository.findByEconomicDomain(economicDomainId, pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers with complete registration
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersWithCompleteRegistration(Pageable pageable) {
        log.debug("Getting providers with complete registration");

        Page<Provider> providers = providerRepository.findWithCompleteRegistration(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers by business size
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getLargeEnterprises(Pageable pageable) {
        Page<Provider> providers = providerRepository.findLargeEnterprises(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderDTO> getMediumEnterprises(Pageable pageable) {
        Page<Provider> providers = providerRepository.findMediumEnterprises(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderDTO> getSmallEnterprises(Pageable pageable) {
        Page<Provider> providers = providerRepository.findSmallEnterprises(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderDTO> getMicroEnterprises(Pageable pageable) {
        Page<Provider> providers = providerRepository.findMicroEnterprises(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers by capital range
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersByCapitalRange(Double minCapital, Double maxCapital, Pageable pageable) {
        log.debug("Getting providers by capital range: {} - {}", minCapital, maxCapital);

        Page<Provider> providers = providerRepository.findByCapitalRange(minCapital, maxCapital, pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get multilingual providers
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getMultilingualProviders(Pageable pageable) {
        log.debug("Getting multilingual providers");

        Page<Provider> providers = providerRepository.findMultilingualProviders(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get public sector providers
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getPublicSectorProviders(Pageable pageable) {
        log.debug("Getting public sector providers");

        Page<Provider> providers = providerRepository.findPublicSectorProviders(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get private sector providers
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getPrivateSectorProviders(Pageable pageable) {
        log.debug("Getting private sector providers");

        Page<Provider> providers = providerRepository.findPrivateSectorProviders(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers with exclusions
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersWithExclusions(Pageable pageable) {
        log.debug("Getting providers with exclusions");

        Page<Provider> providers = providerRepository.findProvidersWithExclusions(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    /**
     * Get providers without exclusions
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> getProvidersWithoutExclusions(Pageable pageable) {
        log.debug("Getting providers without exclusions");

        Page<Provider> providers = providerRepository.findProvidersWithoutExclusions(pageable);
        return providers.map(ProviderDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update provider
     */
    public ProviderDTO updateProvider(Long id, ProviderDTO providerDTO) {
        log.info("Updating provider with ID: {}", id);

        Provider existingProvider = getProviderEntityById(id);

        // Validate required fields
        validateRequiredFields(providerDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(providerDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(providerDTO, existingProvider);

        // Handle foreign key relationships
        setEntityRelationships(providerDTO, existingProvider);

        Provider updatedProvider = providerRepository.save(existingProvider);

        // Handle many-to-many relationships
        handleEconomicDomainsRelationship(providerDTO, updatedProvider);

        log.info("Successfully updated provider with ID: {}", id);

        return ProviderDTO.fromEntityWithRelations(updatedProvider);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete provider
     */
    public void deleteProvider(Long id) {
        log.info("Deleting provider with ID: {}", id);

        Provider provider = getProviderEntityById(id);
        providerRepository.delete(provider);

        log.info("Successfully deleted provider with ID: {}", id);
    }

    /**
     * Delete provider by ID (direct)
     */
    public void deleteProviderById(Long id) {
        log.info("Deleting provider by ID: {}", id);

        if (!providerRepository.existsById(id)) {
            throw new RuntimeException("Provider not found with ID: " + id);
        }

        providerRepository.deleteById(id);
        log.info("Successfully deleted provider with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if provider exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return providerRepository.existsById(id);
    }

    /**
     * Check if commercial registry number exists
     */
    @Transactional(readOnly = true)
    public boolean existsByComercialRegistryNumber(String comercialRegistryNumber) {
        return providerRepository.existsByComercialRegistryNumber(comercialRegistryNumber);
    }

    /**
     * Get total count of providers
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return providerRepository.countAllProviders();
    }

    /**
     * Get count by business size
     */
    @Transactional(readOnly = true)
    public Long getLargeEnterprisesCount() {
        return providerRepository.countLargeEnterprises();
    }

    @Transactional(readOnly = true)
    public Long getMediumEnterprisesCount() {
        return providerRepository.countMediumEnterprises();
    }

    @Transactional(readOnly = true)
    public Long getSmallEnterprisesCount() {
        return providerRepository.countSmallEnterprises();
    }

    @Transactional(readOnly = true)
    public Long getMicroEnterprisesCount() {
        return providerRepository.countMicroEnterprises();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(ProviderDTO dto, Provider entity) {
        entity.setDesignationLt(dto.getDesignationLt()); // F_01
        entity.setDesignationAr(dto.getDesignationAr()); // F_02
        entity.setAcronymLt(dto.getAcronymLt()); // F_03
        entity.setAcronymAr(dto.getAcronymAr()); // F_04
        entity.setAddress(dto.getAddress()); // F_05
        entity.setCapital(dto.getCapital()); // F_06
        entity.setComercialRegistryNumber(dto.getComercialRegistryNumber()); // F_07
        entity.setComercialRegistryDate(dto.getComercialRegistryDate()); // F_08
        entity.setTaxeIdentityNumber(dto.getTaxeIdentityNumber()); // F_09
        entity.setStatIdentityNumber(dto.getStatIdentityNumber()); // F_10
        entity.setBank(dto.getBank()); // F_11
        entity.setBankAccount(dto.getBankAccount()); // F_12
        entity.setSwiftNumber(dto.getSwiftNumber()); // F_13
        entity.setPhoneNumbers(dto.getPhoneNumbers()); // F_14
        entity.setFaxNumbers(dto.getFaxNumbers()); // F_15
        entity.setMail(dto.getMail()); // F_16
        entity.setWebsite(dto.getWebsite()); // F_17
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(ProviderDTO dto, Provider entity) {
        // F_18 - Logo (optional)
        if (dto.getLogoId() != null) {
            entity.setLogo(fileRepository.findById(dto.getLogoId())
                    .orElseThrow(() -> new RuntimeException("Logo file not found with ID: " + dto.getLogoId())));
        } else {
            entity.setLogo(null);
        }

        // F_19 - EconomicNature (required)
        if (dto.getEconomicNatureId() != null) {
            entity.setEconomicNature(economicNatureRepository.findById(dto.getEconomicNatureId())
                    .orElseThrow(() -> new RuntimeException("Economic nature not found with ID: " + dto.getEconomicNatureId())));
        }

        // F_20 - Country (required)
        if (dto.getCountryId() != null) {
            entity.setCountry(countryRepository.findById(dto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found with ID: " + dto.getCountryId())));
        }

        // F_21 - State (optional)
        if (dto.getStateId() != null) {
            entity.setState(stateRepository.findById(dto.getStateId())
                    .orElseThrow(() -> new RuntimeException("State not found with ID: " + dto.getStateId())));
        } else {
            entity.setState(null);
        }
    }

    /**
     * Handle many-to-many relationship with EconomicDomain
     */
    private void handleEconomicDomainsRelationship(ProviderDTO dto, Provider entity) {
        if (dto.getEconomicDomainIds() != null) {
            List<dz.mdn.iaas.business.provider.model.EconomicDomain> economicDomains = 
                economicDomainRepository.findAllById(dto.getEconomicDomainIds());
            
            if (economicDomains.size() != dto.getEconomicDomainIds().size()) {
                throw new RuntimeException("Some economic domains were not found");
            }
            
            entity.setEconomicDomains(economicDomains);
        } else {
            entity.setEconomicDomains(java.util.Arrays.asList());
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ProviderDTO dto, String operation) {
        if (dto.getEconomicNatureId() == null) {
            throw new RuntimeException("Economic nature is required for " + operation);
        }
        if (dto.getCountryId() == null) {
            throw new RuntimeException("Country is required for " + operation);
        }
        if ((dto.getDesignationLt() == null || dto.getDesignationLt().trim().isEmpty()) &&
            (dto.getDesignationAr() == null || dto.getDesignationAr().trim().isEmpty())) {
            throw new RuntimeException("At least one designation (Latin or Arabic) is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ProviderDTO dto, Long excludeId) {
        // Check commercial registry number uniqueness
        if (dto.getComercialRegistryNumber() != null && !dto.getComercialRegistryNumber().trim().isEmpty()) {
            if (excludeId == null) {
                if (providerRepository.existsByComercialRegistryNumber(dto.getComercialRegistryNumber())) {
                    throw new RuntimeException("Provider with commercial registry number '" + 
                        dto.getComercialRegistryNumber() + "' already exists");
                }
            } else {
                if (providerRepository.existsByComercialRegistryNumberAndIdNot(dto.getComercialRegistryNumber(), excludeId)) {
                    throw new RuntimeException("Another provider with commercial registry number '" + 
                        dto.getComercialRegistryNumber() + "' already exists");
                }
            }
        }

        // Check tax identity number uniqueness
        if (dto.getTaxeIdentityNumber() != null && !dto.getTaxeIdentityNumber().trim().isEmpty()) {
            if (excludeId == null) {
                if (providerRepository.existsByTaxeIdentityNumber(dto.getTaxeIdentityNumber())) {
                    throw new RuntimeException("Provider with tax identity number '" + 
                        dto.getTaxeIdentityNumber() + "' already exists");
                }
            } else {
                if (providerRepository.existsByTaxeIdentityNumberAndIdNot(dto.getTaxeIdentityNumber(), excludeId)) {
                    throw new RuntimeException("Another provider with tax identity number '" + 
                        dto.getTaxeIdentityNumber() + "' already exists");
                }
            }
        }

        // Check stat identity number uniqueness
        if (dto.getStatIdentityNumber() != null && !dto.getStatIdentityNumber().trim().isEmpty()) {
            if (excludeId == null) {
                if (providerRepository.existsByStatIdentityNumber(dto.getStatIdentityNumber())) {
                    throw new RuntimeException("Provider with stat identity number '" + 
                        dto.getStatIdentityNumber() + "' already exists");
                }
            } else {
                if (providerRepository.existsByStatIdentityNumberAndIdNot(dto.getStatIdentityNumber(), excludeId)) {
                    throw new RuntimeException("Another provider with stat identity number '" + 
                        dto.getStatIdentityNumber() + "' already exists");
                }
            }
        }
    }
}
