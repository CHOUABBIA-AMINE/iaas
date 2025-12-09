/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentatorService
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

import dz.mdn.iaas.business.provider.dto.ProviderRepresentatorDTO;
import dz.mdn.iaas.business.provider.model.ProviderRepresentator;
import dz.mdn.iaas.business.provider.repository.ProviderRepository;
import dz.mdn.iaas.business.provider.repository.ProviderRepresentatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Provider Representator Service with CRUD operations
 * Handles provider representator management operations with contact validation and professional classification
 * Based on exact field names and business rules for representative management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProviderRepresentatorService {

    private final ProviderRepresentatorRepository providerRepresentatorRepository;
    
    // Repository bean for related entity (injected as needed)
    private final ProviderRepository providerRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new provider representator
     */
    public ProviderRepresentatorDTO createProviderRepresentator(ProviderRepresentatorDTO providerRepresentatorDTO) {
        log.info("Creating provider representator: {} {} for provider ID: {}", 
                providerRepresentatorDTO.getFirstname(), providerRepresentatorDTO.getLastname(), 
                providerRepresentatorDTO.getProviderId());

        // Validate required fields and business rules
        validateRequiredFields(providerRepresentatorDTO, "create");
        validateBusinessRules(providerRepresentatorDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(providerRepresentatorDTO, null);

        // Create entity with exact field mapping
        ProviderRepresentator providerRepresentator = new ProviderRepresentator();
        mapDtoToEntity(providerRepresentatorDTO, providerRepresentator);

        // Handle foreign key relationship
        setEntityRelationships(providerRepresentatorDTO, providerRepresentator);

        ProviderRepresentator savedProviderRepresentator = providerRepresentatorRepository.save(providerRepresentator);
        log.info("Successfully created provider representator with ID: {}", savedProviderRepresentator.getId());

        return ProviderRepresentatorDTO.fromEntityWithRelations(savedProviderRepresentator);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get provider representator by ID
     */
    @Transactional(readOnly = true)
    public ProviderRepresentatorDTO getProviderRepresentatorById(Long id) {
        log.debug("Getting provider representator with ID: {}", id);

        ProviderRepresentator providerRepresentator = providerRepresentatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider representator not found with ID: " + id));

        return ProviderRepresentatorDTO.fromEntityWithRelations(providerRepresentator);
    }

    /**
     * Get provider representator entity by ID
     */
    @Transactional(readOnly = true)
    public ProviderRepresentator getProviderRepresentatorEntityById(Long id) {
        return providerRepresentatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider representator not found with ID: " + id));
    }

    /**
     * Get all provider representators with pagination
     */
    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getAllProviderRepresentators(Pageable pageable) {
        log.debug("Getting all provider representators with pagination");

        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findAllOrderByName(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Find one provider representator by ID
     */
    @Transactional(readOnly = true)
    public Optional<ProviderRepresentatorDTO> findOne(Long id) {
        log.debug("Finding provider representator by ID: {}", id);

        return providerRepresentatorRepository.findById(id)
                .map(ProviderRepresentatorDTO::fromEntityWithRelations);
    }

    /**
     * Get provider representators by provider
     */
    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getProviderRepresentatorsByProvider(Long providerId, Pageable pageable) {
        log.debug("Getting provider representators for provider ID: {}", providerId);

        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findByProvider(providerId, pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Get all representators for a specific provider (without pagination)
     */
    @Transactional(readOnly = true)
    public List<ProviderRepresentatorDTO> getAllRepresentatorsForProvider(Long providerId) {
        log.debug("Getting all representators for provider ID: {}", providerId);

        List<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findAllByProvider(providerId);
        return providerRepresentators.stream().map(ProviderRepresentatorDTO::fromEntity).toList();
    }

    /**
     * Search provider representators by name
     */
    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> searchRepresentatorsByName(String searchTerm, Pageable pageable) {
        log.debug("Searching representators by name with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProviderRepresentators(pageable);
        }

        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.searchByName(searchTerm.trim(), pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Search provider representators by any field
     */
    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> searchRepresentatorsByAnyField(String searchTerm, Pageable pageable) {
        log.debug("Searching representators by any field with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProviderRepresentators(pageable);
        }

        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.searchByAnyField(searchTerm.trim(), pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Find representator by full name and provider
     */
    @Transactional(readOnly = true)
    public Optional<ProviderRepresentatorDTO> findByFullNameAndProvider(String firstname, String lastname, Long providerId) {
        log.debug("Finding representator by name: {} {} for provider ID: {}", firstname, lastname, providerId);

        return providerRepresentatorRepository.findByFullNameAndProvider(firstname, lastname, providerId)
                .map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Get representators by professional category
     */
    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getExecutiveRepresentators(Pageable pageable) {
        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findExecutiveRepresentators(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getLegalRepresentators(Pageable pageable) {
        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findLegalRepresentators(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getTechnicalRepresentators(Pageable pageable) {
        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findTechnicalRepresentators(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getCommercialRepresentators(Pageable pageable) {
        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findCommercialRepresentators(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getAdministrativeRepresentators(Pageable pageable) {
        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findAdministrativeRepresentators(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getFinancialRepresentators(Pageable pageable) {
        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findFinancialRepresentators(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Get representators with contact information
     */
    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getRepresentatorsWithContactInfo(Pageable pageable) {
        log.debug("Getting representators with contact information");

        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findWithContactInfo(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Get representators without contact information
     */
    @Transactional(readOnly = true)
    public Page<ProviderRepresentatorDTO> getRepresentatorsWithoutContactInfo(Pageable pageable) {
        log.debug("Getting representators without contact information");

        Page<ProviderRepresentator> providerRepresentators = providerRepresentatorRepository.findWithoutContactInfo(pageable);
        return providerRepresentators.map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Find representator by email
     */
    @Transactional(readOnly = true)
    public Optional<ProviderRepresentatorDTO> findByEmail(String email) {
        log.debug("Finding representator by email: {}", email);

        return providerRepresentatorRepository.findByEmail(email)
                .map(ProviderRepresentatorDTO::fromEntity);
    }

    /**
     * Find representator by mobile phone number
     */
    @Transactional(readOnly = true)
    public Optional<ProviderRepresentatorDTO> findByMobilePhoneNumber(String mobilePhone) {
        log.debug("Finding representator by mobile phone: {}", mobilePhone);

        return providerRepresentatorRepository.findByMobilePhoneNumber(mobilePhone)
                .map(ProviderRepresentatorDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update provider representator
     */
    public ProviderRepresentatorDTO updateProviderRepresentator(Long id, ProviderRepresentatorDTO providerRepresentatorDTO) {
        log.info("Updating provider representator with ID: {}", id);

        ProviderRepresentator existingProviderRepresentator = getProviderRepresentatorEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(providerRepresentatorDTO, "update");
        validateBusinessRules(providerRepresentatorDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(providerRepresentatorDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(providerRepresentatorDTO, existingProviderRepresentator);

        // Handle foreign key relationship
        setEntityRelationships(providerRepresentatorDTO, existingProviderRepresentator);

        ProviderRepresentator updatedProviderRepresentator = providerRepresentatorRepository.save(existingProviderRepresentator);
        log.info("Successfully updated provider representator with ID: {}", id);

        return ProviderRepresentatorDTO.fromEntityWithRelations(updatedProviderRepresentator);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete provider representator
     */
    public void deleteProviderRepresentator(Long id) {
        log.info("Deleting provider representator with ID: {}", id);

        ProviderRepresentator providerRepresentator = getProviderRepresentatorEntityById(id);
        providerRepresentatorRepository.delete(providerRepresentator);

        log.info("Successfully deleted provider representator with ID: {}", id);
    }

    /**
     * Delete provider representator by ID (direct)
     */
    public void deleteProviderRepresentatorById(Long id) {
        log.info("Deleting provider representator by ID: {}", id);

        if (!providerRepresentatorRepository.existsById(id)) {
            throw new RuntimeException("Provider representator not found with ID: " + id);
        }

        providerRepresentatorRepository.deleteById(id);
        log.info("Successfully deleted provider representator with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if provider representator exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return providerRepresentatorRepository.existsById(id);
    }

    /**
     * Check if email exists
     */
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return providerRepresentatorRepository.existsByEmail(email);
    }

    /**
     * Check if mobile phone exists
     */
    @Transactional(readOnly = true)
    public boolean existsByMobilePhoneNumber(String mobilePhone) {
        return providerRepresentatorRepository.existsByMobilePhoneNumber(mobilePhone);
    }

    /**
     * Get count of representators by provider
     */
    @Transactional(readOnly = true)
    public Long countRepresentatorsByProvider(Long providerId) {
        return providerRepresentatorRepository.countByProvider(providerId);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countRepresentatorsWithContactInfo() {
        return providerRepresentatorRepository.countWithContactInfo();
    }

    @Transactional(readOnly = true)
    public Long countExecutiveRepresentators() {
        return providerRepresentatorRepository.countExecutiveRepresentators();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(ProviderRepresentatorDTO dto, ProviderRepresentator entity) {
        entity.setFirstname(dto.getFirstname()); // F_01
        entity.setLastname(dto.getLastname()); // F_02
        entity.setBirthDate(dto.getBirthDate()); // F_03
        entity.setBirthPlace(dto.getBirthPlace()); // F_04
        entity.setAddress(dto.getAddress()); // F_05
        entity.setJobTitle(dto.getJobTitle()); // F_06
        entity.setMobilePhoneNumber(dto.getMobilePhoneNumber()); // F_07
        entity.setFixPhoneNumber(dto.getFixPhoneNumber()); // F_08
        entity.setMail(dto.getMail()); // F_09
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(ProviderRepresentatorDTO dto, ProviderRepresentator entity) {
        // F_10 - Provider (required)
        if (dto.getProviderId() != null) {
            entity.setProvider(providerRepository.findById(dto.getProviderId())
                    .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + dto.getProviderId())));
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ProviderRepresentatorDTO dto, String operation) {
        if (dto.getFirstname() == null || dto.getFirstname().trim().isEmpty()) {
            throw new RuntimeException("First name is required for " + operation);
        }
        if (dto.getLastname() == null || dto.getLastname().trim().isEmpty()) {
            throw new RuntimeException("Last name is required for " + operation);
        }
        if (dto.getProviderId() == null) {
            throw new RuntimeException("Provider is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(ProviderRepresentatorDTO dto, String operation) {
        // Email format validation (if provided)
        if (dto.getMail() != null && !dto.getMail().trim().isEmpty()) {
            if (!dto.getMail().contains("@") || !dto.getMail().contains(".")) {
                throw new RuntimeException("Invalid email format for " + operation);
            }
        }

        // Phone number format validation (basic check)
        if (dto.getMobilePhoneNumber() != null && !dto.getMobilePhoneNumber().trim().isEmpty()) {
            String mobile = dto.getMobilePhoneNumber().replaceAll("[\\s\\-\\(\\)]", "");
            if (mobile.length() < 8 || !mobile.matches(".*\\d.*")) {
                throw new RuntimeException("Invalid mobile phone number format for " + operation);
            }
        }

        if (dto.getFixPhoneNumber() != null && !dto.getFixPhoneNumber().trim().isEmpty()) {
            String fixPhone = dto.getFixPhoneNumber().replaceAll("[\\s\\-\\(\\)]", "");
            if (fixPhone.length() < 8 || !fixPhone.matches(".*\\d.*")) {
                throw new RuntimeException("Invalid fix phone number format for " + operation);
            }
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ProviderRepresentatorDTO dto, Long excludeId) {
        // Check email uniqueness (if provided)
        if (dto.getMail() != null && !dto.getMail().trim().isEmpty()) {
            if (excludeId == null) {
                if (providerRepresentatorRepository.existsByEmail(dto.getMail())) {
                    throw new RuntimeException("Email '" + dto.getMail() + "' is already used by another representator");
                }
            } else {
                if (providerRepresentatorRepository.existsByEmailAndIdNot(dto.getMail(), excludeId)) {
                    throw new RuntimeException("Email '" + dto.getMail() + "' is already used by another representator");
                }
            }
        }

        // Check mobile phone uniqueness (if provided)
        if (dto.getMobilePhoneNumber() != null && !dto.getMobilePhoneNumber().trim().isEmpty()) {
            if (excludeId == null) {
                if (providerRepresentatorRepository.existsByMobilePhoneNumber(dto.getMobilePhoneNumber())) {
                    throw new RuntimeException("Mobile phone '" + dto.getMobilePhoneNumber() + "' is already used by another representator");
                }
            } else {
                if (providerRepresentatorRepository.existsByMobilePhoneNumberAndIdNot(dto.getMobilePhoneNumber(), excludeId)) {
                    throw new RuntimeException("Mobile phone '" + dto.getMobilePhoneNumber() + "' is already used by another representator");
                }
            }
        }
    }
}
