/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentatorController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.service.ProviderRepresentatorService;
import dz.mdn.iaas.business.provider.dto.ProviderRepresentatorDTO;

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

import java.util.List;

/**
 * Provider Representator REST Controller
 * Handles provider representator operations: create, get metadata, delete, get all
 * Based on exact ProviderRepresentator model: F_00=id, F_01=firstname, F_02=lastname, F_03=birthDate, 
 * F_04=birthPlace, F_05=address, F_06=jobTitle, F_07=mobilePhoneNumber, F_08=fixPhoneNumber, F_09=mail, F_10=providerId
 */
@RestController
@RequestMapping("/providerRepresentator")
@RequiredArgsConstructor
@Slf4j
public class ProviderRepresentatorController {

    private final ProviderRepresentatorService providerRepresentatorService;

    // ========== POST ONE PROVIDER REPRESENTATOR ==========

    /**
     * Create new provider representator
     * Creates provider representator with contact validation and professional classification
     */
    @PostMapping
    public ResponseEntity<ProviderRepresentatorDTO> createProviderRepresentator(@Valid @RequestBody ProviderRepresentatorDTO providerRepresentatorDTO) {
        log.info("Creating provider representator: {} {} for provider ID: {}", 
                providerRepresentatorDTO.getFirstname(), providerRepresentatorDTO.getLastname(), 
                providerRepresentatorDTO.getProviderId());
        
        ProviderRepresentatorDTO createdProviderRepresentator = providerRepresentatorService.createProviderRepresentator(providerRepresentatorDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProviderRepresentator);
    }

    // ========== GET METADATA ==========

    /**
     * Get provider representator metadata by ID
     * Returns provider representator information with contact details and professional classification
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProviderRepresentatorDTO> getProviderRepresentatorMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for provider representator ID: {}", id);
        
        ProviderRepresentatorDTO providerRepresentatorMetadata = providerRepresentatorService.getProviderRepresentatorById(id);
        
        return ResponseEntity.ok(providerRepresentatorMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete provider representator by ID
     * Removes provider representator from the representative management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProviderRepresentator(@PathVariable Long id) {
        log.info("Deleting provider representator with ID: {}", id);
        
        providerRepresentatorService.deleteProviderRepresentator(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all provider representators with pagination
     * Returns list of all provider representators ordered by lastname, firstname
     */
    @GetMapping
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getAllProviderRepresentators(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "lastname") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all provider representators - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getAllProviderRepresentators(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    // ========== PROVIDER-SPECIFIC ENDPOINTS ==========

    /**
     * Get provider representators by provider
     */
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getProviderRepresentatorsByProvider(
            @PathVariable Long providerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting provider representators for provider ID: {}", providerId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getProviderRepresentatorsByProvider(providerId, pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Get all representators for a specific provider (without pagination)
     */
    @GetMapping("/provider/{providerId}/all")
    public ResponseEntity<List<ProviderRepresentatorDTO>> getAllRepresentatorsForProvider(@PathVariable Long providerId) {
        log.debug("Getting all representators for provider ID: {}", providerId);
        
        List<ProviderRepresentatorDTO> representators = providerRepresentatorService.getAllRepresentatorsForProvider(providerId);
        
        return ResponseEntity.ok(representators);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search provider representators by name (firstname or lastname)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> searchRepresentatorsByName(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching representators by name with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.searchRepresentatorsByName(query, pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Advanced search provider representators by any field
     */
    @GetMapping("/search/advanced")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> searchRepresentatorsByAnyField(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Advanced searching representators with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.searchRepresentatorsByAnyField(query, pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    // ========== CONTACT INFORMATION ENDPOINTS ==========

    /**
     * Get representators with contact information
     */
    @GetMapping("/with-contact")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getRepresentatorsWithContactInfo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting representators with contact information");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getRepresentatorsWithContactInfo(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Get representators without contact information
     */
    @GetMapping("/without-contact")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getRepresentatorsWithoutContactInfo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting representators without contact information");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getRepresentatorsWithoutContactInfo(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Find representator by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ProviderRepresentatorDTO> getRepresentatorByEmail(@PathVariable String email) {
        log.debug("Getting representator by email: {}", email);
        
        return providerRepresentatorService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Find representator by mobile phone number
     */
    @GetMapping("/mobile/{mobilePhone}")
    public ResponseEntity<ProviderRepresentatorDTO> getRepresentatorByMobilePhone(@PathVariable String mobilePhone) {
        log.debug("Getting representator by mobile phone: {}", mobilePhone);
        
        return providerRepresentatorService.findByMobilePhoneNumber(mobilePhone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== PROFESSIONAL CLASSIFICATION ENDPOINTS ==========

    /**
     * Get executive representators
     */
    @GetMapping("/executive")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getExecutiveRepresentators(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting executive representators");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getExecutiveRepresentators(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Get legal representators
     */
    @GetMapping("/legal")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getLegalRepresentators(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting legal representators");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getLegalRepresentators(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Get technical representators
     */
    @GetMapping("/technical")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getTechnicalRepresentators(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technical representators");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getTechnicalRepresentators(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Get commercial representators
     */
    @GetMapping("/commercial")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getCommercialRepresentators(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commercial representators");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getCommercialRepresentators(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Get administrative representators
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getAdministrativeRepresentators(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting administrative representators");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getAdministrativeRepresentators(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    /**
     * Get financial representators
     */
    @GetMapping("/financial")
    public ResponseEntity<Page<ProviderRepresentatorDTO>> getFinancialRepresentators(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial representators");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastname"));
        Page<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getFinancialRepresentators(pageable);
        
        return ResponseEntity.ok(providerRepresentators);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find representator by full name and provider
     */
    @GetMapping("/lookup")
    public ResponseEntity<ProviderRepresentatorDTO> findByFullNameAndProvider(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam Long providerId) {
        
        log.debug("Finding representator by name: {} {} for provider ID: {}", firstname, lastname, providerId);
        
        return providerRepresentatorService.findByFullNameAndProvider(firstname, lastname, providerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update provider representator metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProviderRepresentatorDTO> updateProviderRepresentator(
            @PathVariable Long id,
            @Valid @RequestBody ProviderRepresentatorDTO providerRepresentatorDTO) {
        
        log.info("Updating provider representator with ID: {}", id);
        
        ProviderRepresentatorDTO updatedProviderRepresentator = providerRepresentatorService.updateProviderRepresentator(id, providerRepresentatorDTO);
        
        return ResponseEntity.ok(updatedProviderRepresentator);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if provider representator exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkProviderRepresentatorExists(@PathVariable Long id) {
        log.debug("Checking existence of provider representator ID: {}", id);
        
        boolean exists = providerRepresentatorService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if email exists
     */
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        log.debug("Checking if email exists: {}", email);
        
        boolean exists = providerRepresentatorService.existsByEmail(email);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if mobile phone exists
     */
    @GetMapping("/exists/mobile/{mobilePhone}")
    public ResponseEntity<Boolean> checkMobilePhoneExists(@PathVariable String mobilePhone) {
        log.debug("Checking if mobile phone exists: {}", mobilePhone);
        
        boolean exists = providerRepresentatorService.existsByMobilePhoneNumber(mobilePhone);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of representators by provider
     */
    @GetMapping("/provider/{providerId}/count")
    public ResponseEntity<Long> countRepresentatorsByProvider(@PathVariable Long providerId) {
        log.debug("Getting count of representators for provider ID: {}", providerId);
        
        Long count = providerRepresentatorService.countRepresentatorsByProvider(providerId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of representators with contact information
     */
    @GetMapping("/count/with-contact")
    public ResponseEntity<Long> countRepresentatorsWithContact() {
        log.debug("Getting count of representators with contact information");
        
        Long count = providerRepresentatorService.countRepresentatorsWithContactInfo();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of executive representators
     */
    @GetMapping("/count/executive")
    public ResponseEntity<Long> countExecutiveRepresentators() {
        log.debug("Getting count of executive representators");
        
        Long count = providerRepresentatorService.countExecutiveRepresentators();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get provider representator info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ProviderRepresentatorInfoResponse> getProviderRepresentatorInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for provider representator ID: {}", id);
        
        try {
            return providerRepresentatorService.findOne(id)
                    .map(providerRepresentatorDTO -> {
                        ProviderRepresentatorInfoResponse response = ProviderRepresentatorInfoResponse.builder()
                                .providerRepresentatorMetadata(providerRepresentatorDTO)
                                .fullName(providerRepresentatorDTO.getFullName())
                                .displayName(providerRepresentatorDTO.getDisplayName())
                                .initials(providerRepresentatorDTO.getInitials())
                                .hasContactInfo(providerRepresentatorDTO.hasContactInfo())
                                .hasCompletePersonalInfo(providerRepresentatorDTO.hasCompletePersonalInfo())
                                .hasAddressInfo(providerRepresentatorDTO.hasAddressInfo())
                                .hasProfessionalInfo(providerRepresentatorDTO.hasProfessionalInfo())
                                .representatorStatus(providerRepresentatorDTO.getRepresentatorStatus())
                                .contactSummary(providerRepresentatorDTO.getContactSummary())
                                .personalSummary(providerRepresentatorDTO.getPersonalSummary())
                                .completenessPercentage(providerRepresentatorDTO.getCompletenessPercentage())
                                .primaryContact(providerRepresentatorDTO.getPrimaryContact())
                                .representatorType(providerRepresentatorDTO.getRepresentatorType())
                                .authorityLevel(providerRepresentatorDTO.getAuthorityLevel())
                                .canSignContracts(providerRepresentatorDTO.canSignContracts())
                                .canHandleTechnicalMatters(providerRepresentatorDTO.canHandleTechnicalMatters())
                                .canHandleCommercialMatters(providerRepresentatorDTO.canHandleCommercialMatters())
                                .shortDisplay(providerRepresentatorDTO.getShortDisplay())
                                .fullDisplay(providerRepresentatorDTO.getFullDisplay())
                                .businessCardDisplay(providerRepresentatorDTO.getBusinessCardDisplay())
                                .formalDisplay(providerRepresentatorDTO.getFormalDisplay())
                                .communicationPreference(providerRepresentatorDTO.getCommunicationPreference())
                                .professionalSummary(providerRepresentatorDTO.getProfessionalSummary())
                                .responsibilityScope(providerRepresentatorDTO.getResponsibilityScope())
                                .hasEmergencyContact(providerRepresentatorDTO.hasEmergencyContact())
                                .contactPriorityOrder(providerRepresentatorDTO.getContactPriorityOrder())
                                .ageEstimate(providerRepresentatorDTO.getAgeEstimate())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting provider representator info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ProviderRepresentatorInfoResponse {
        private ProviderRepresentatorDTO providerRepresentatorMetadata;
        private String fullName;
        private String displayName;
        private String initials;
        private Boolean hasContactInfo;
        private Boolean hasCompletePersonalInfo;
        private Boolean hasAddressInfo;
        private Boolean hasProfessionalInfo;
        private String representatorStatus;
        private String contactSummary;
        private String personalSummary;
        private Integer completenessPercentage;
        private String primaryContact;
        private String representatorType;
        private String authorityLevel;
        private Boolean canSignContracts;
        private Boolean canHandleTechnicalMatters;
        private Boolean canHandleCommercialMatters;
        private String shortDisplay;
        private String fullDisplay;
        private String businessCardDisplay;
        private String formalDisplay;
        private String communicationPreference;
        private String professionalSummary;
        private String responsibilityScope;
        private Boolean hasEmergencyContact;
        private String[] contactPriorityOrder;
        private String ageEstimate;
    }
}