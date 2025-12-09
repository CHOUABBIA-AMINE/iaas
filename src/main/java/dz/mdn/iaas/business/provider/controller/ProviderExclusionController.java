/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusionController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.service.ProviderExclusionService;
import dz.mdn.iaas.business.provider.dto.ProviderExclusionDTO;

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
 * Provider Exclusion REST Controller
 * Handles provider exclusion operations: create, get metadata, delete, get all
 * Based on exact ProviderExclusion model: F_00=id, F_01=startDate, F_02=endDate, F_03=cause, 
 * F_04=exclusionTypeId, F_05=providerId, F_06=referenceId
 */
@RestController
@RequestMapping("/providerExclusion")
@RequiredArgsConstructor
@Slf4j
public class ProviderExclusionController {

    private final ProviderExclusionService providerExclusionService;

    // ========== POST ONE PROVIDER EXCLUSION ==========

    /**
     * Create new provider exclusion
     * Creates provider exclusion with temporal validation and compliance monitoring
     */
    @PostMapping
    public ResponseEntity<ProviderExclusionDTO> createProviderExclusion(@Valid @RequestBody ProviderExclusionDTO providerExclusionDTO) {
        log.info("Creating provider exclusion for provider ID: {}, exclusion type ID: {}, start date: {}", 
                providerExclusionDTO.getProviderId(), providerExclusionDTO.getExclusionTypeId(), 
                providerExclusionDTO.getStartDate());
        
        ProviderExclusionDTO createdProviderExclusion = providerExclusionService.createProviderExclusion(providerExclusionDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProviderExclusion);
    }

    // ========== GET METADATA ==========

    /**
     * Get provider exclusion metadata by ID
     * Returns provider exclusion information with temporal status and compliance analysis
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProviderExclusionDTO> getProviderExclusionMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for provider exclusion ID: {}", id);
        
        ProviderExclusionDTO providerExclusionMetadata = providerExclusionService.getProviderExclusionById(id);
        
        return ResponseEntity.ok(providerExclusionMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete provider exclusion by ID
     * Removes provider exclusion from the compliance management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProviderExclusion(@PathVariable Long id) {
        log.info("Deleting provider exclusion with ID: {}", id);
        
        providerExclusionService.deleteProviderExclusion(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all provider exclusions with pagination
     * Returns list of all provider exclusions ordered by start date (most recent first)
     */
    @GetMapping
    public ResponseEntity<Page<ProviderExclusionDTO>> getAllProviderExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "startDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting all provider exclusions - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getAllProviderExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    // ========== PROVIDER-SPECIFIC ENDPOINTS ==========

    /**
     * Get provider exclusions by provider
     */
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<Page<ProviderExclusionDTO>> getProviderExclusionsByProvider(
            @PathVariable Long providerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting provider exclusions for provider ID: {}", providerId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getProviderExclusionsByProvider(providerId, pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get active exclusions for a specific provider
     */
    @GetMapping("/provider/{providerId}/active")
    public ResponseEntity<List<ProviderExclusionDTO>> getActiveExclusionsForProvider(@PathVariable Long providerId) {
        log.debug("Getting active exclusions for provider ID: {}", providerId);
        
        List<ProviderExclusionDTO> activeExclusions = providerExclusionService.getActiveExclusionsForProvider(providerId);
        
        return ResponseEntity.ok(activeExclusions);
    }

    // ========== EXCLUSION TYPE ENDPOINTS ==========

    /**
     * Get provider exclusions by exclusion type
     */
    @GetMapping("/type/{exclusionTypeId}")
    public ResponseEntity<Page<ProviderExclusionDTO>> getProviderExclusionsByType(
            @PathVariable Long exclusionTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting provider exclusions for exclusion type ID: {}", exclusionTypeId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getProviderExclusionsByType(exclusionTypeId, pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    // ========== STATUS-BASED ENDPOINTS ==========

    /**
     * Get active provider exclusions
     */
    @GetMapping("/active")
    public ResponseEntity<Page<ProviderExclusionDTO>> getActiveProviderExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting active provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getActiveProviderExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get expired provider exclusions
     */
    @GetMapping("/expired")
    public ResponseEntity<Page<ProviderExclusionDTO>> getExpiredProviderExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting expired provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "endDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getExpiredProviderExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get permanent exclusions
     */
    @GetMapping("/permanent")
    public ResponseEntity<Page<ProviderExclusionDTO>> getPermanentExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting permanent provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getPermanentExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get future exclusions
     */
    @GetMapping("/future")
    public ResponseEntity<Page<ProviderExclusionDTO>> getFutureExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting future provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getFutureExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get exclusions expiring soon (within 30 days)
     */
    @GetMapping("/expiring-soon")
    public ResponseEntity<Page<ProviderExclusionDTO>> getExclusionsExpiringSoon(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting exclusions expiring soon");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "endDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getExclusionsExpiringSoon(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    // ========== CATEGORY-BASED ENDPOINTS ==========

    /**
     * Get criminal exclusions
     */
    @GetMapping("/criminal")
    public ResponseEntity<Page<ProviderExclusionDTO>> getCriminalExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting criminal provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getCriminalExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get financial exclusions
     */
    @GetMapping("/financial")
    public ResponseEntity<Page<ProviderExclusionDTO>> getFinancialExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getFinancialExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get legal exclusions
     */
    @GetMapping("/legal")
    public ResponseEntity<Page<ProviderExclusionDTO>> getLegalExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting legal provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getLegalExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get administrative exclusions
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<ProviderExclusionDTO>> getAdministrativeExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting administrative provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getAdministrativeExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    /**
     * Get security exclusions
     */
    @GetMapping("/security")
    public ResponseEntity<Page<ProviderExclusionDTO>> getSecurityExclusions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting security provider exclusions");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.getSecurityExclusions(pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search exclusions by cause
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProviderExclusionDTO>> searchExclusionsByCause(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching exclusions by cause with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ProviderExclusionDTO> providerExclusions = providerExclusionService.searchExclusionsByCause(query, pageable);
        
        return ResponseEntity.ok(providerExclusions);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update provider exclusion metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProviderExclusionDTO> updateProviderExclusion(
            @PathVariable Long id,
            @Valid @RequestBody ProviderExclusionDTO providerExclusionDTO) {
        
        log.info("Updating provider exclusion with ID: {}", id);
        
        ProviderExclusionDTO updatedProviderExclusion = providerExclusionService.updateProviderExclusion(id, providerExclusionDTO);
        
        return ResponseEntity.ok(updatedProviderExclusion);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if provider exclusion exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkProviderExclusionExists(@PathVariable Long id) {
        log.debug("Checking existence of provider exclusion ID: {}", id);
        
        boolean exists = providerExclusionService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if provider has active exclusions
     */
    @GetMapping("/provider/{providerId}/has-active")
    public ResponseEntity<Boolean> checkProviderHasActiveExclusions(@PathVariable Long providerId) {
        log.debug("Checking if provider ID: {} has active exclusions", providerId);
        
        boolean hasActive = providerExclusionService.hasActiveExclusions(providerId);
        
        return ResponseEntity.ok(hasActive);
    }

    /**
     * Check if provider has permanent exclusions
     */
    @GetMapping("/provider/{providerId}/has-permanent")
    public ResponseEntity<Boolean> checkProviderHasPermanentExclusions(@PathVariable Long providerId) {
        log.debug("Checking if provider ID: {} has permanent exclusions", providerId);
        
        boolean hasPermanent = providerExclusionService.hasPermanentExclusions(providerId);
        
        return ResponseEntity.ok(hasPermanent);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of active exclusions for provider
     */
    @GetMapping("/provider/{providerId}/count/active")
    public ResponseEntity<Long> countActiveExclusionsForProvider(@PathVariable Long providerId) {
        log.debug("Getting count of active exclusions for provider ID: {}", providerId);
        
        Long count = providerExclusionService.countActiveExclusionsForProvider(providerId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get total count of exclusions for provider
     */
    @GetMapping("/provider/{providerId}/count/total")
    public ResponseEntity<Long> countTotalExclusionsForProvider(@PathVariable Long providerId) {
        log.debug("Getting total count of exclusions for provider ID: {}", providerId);
        
        Long count = providerExclusionService.countTotalExclusionsForProvider(providerId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of active exclusions
     */
    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveExclusions() {
        log.debug("Getting count of active exclusions");
        
        Long count = providerExclusionService.countActiveExclusions();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of permanent exclusions
     */
    @GetMapping("/count/permanent")
    public ResponseEntity<Long> countPermanentExclusions() {
        log.debug("Getting count of permanent exclusions");
        
        Long count = providerExclusionService.countPermanentExclusions();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of expired exclusions
     */
    @GetMapping("/count/expired")
    public ResponseEntity<Long> countExpiredExclusions() {
        log.debug("Getting count of expired exclusions");
        
        Long count = providerExclusionService.countExpiredExclusions();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get provider exclusion info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ProviderExclusionInfoResponse> getProviderExclusionInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for provider exclusion ID: {}", id);
        
        try {
            return providerExclusionService.findOne(id)
                    .map(providerExclusionDTO -> {
                        ProviderExclusionInfoResponse response = ProviderExclusionInfoResponse.builder()
                                .providerExclusionMetadata(providerExclusionDTO)
                                .isActive(providerExclusionDTO.isActive())
                                .isPermanent(providerExclusionDTO.isPermanent())
                                .isExpired(providerExclusionDTO.isExpired())
                                .isFuture(providerExclusionDTO.isFuture())
                                .exclusionStatus(providerExclusionDTO.getExclusionStatus())
                                .durationInDays(providerExclusionDTO.getDurationInDays())
                                .remainingDays(providerExclusionDTO.getRemainingDays())
                                .daysSinceStart(providerExclusionDTO.getDaysSinceStart())
                                .exclusionSeverity(providerExclusionDTO.getExclusionSeverity())
                                .exclusionCategory(providerExclusionDTO.getExclusionCategory())
                                .affectsPublicContracts(providerExclusionDTO.affectsPublicContracts())
                                .requiresLegalReview(providerExclusionDTO.requiresLegalReview())
                                .displayText(providerExclusionDTO.getDisplayText())
                                .shortDisplay(providerExclusionDTO.getShortDisplay())
                                .fullDisplay(providerExclusionDTO.getFullDisplay())
                                .periodDescription(providerExclusionDTO.getPeriodDescription())
                                .exclusionPriority(providerExclusionDTO.getExclusionPriority())
                                .businessImpact(providerExclusionDTO.getBusinessImpact())
                                .complianceActionRequired(providerExclusionDTO.getComplianceActionRequired())
                                .remediationProcess(providerExclusionDTO.getRemediationProcess())
                                .monitoringRequirement(providerExclusionDTO.getMonitoringRequirement())
                                .hasReference(providerExclusionDTO.hasReference())
                                .referenceSummary(providerExclusionDTO.getReferenceSummary())
                                .exclusionSummary(providerExclusionDTO.getExclusionSummary())
                                .alertLevel(providerExclusionDTO.getAlertLevel())
                                .isCloseToExpiration(providerExclusionDTO.isCloseToExpiration())
                                .expirationWarning(providerExclusionDTO.getExpirationWarning())
                                .formalDisplay(providerExclusionDTO.getFormalDisplay())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting provider exclusion info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ProviderExclusionInfoResponse {
        private ProviderExclusionDTO providerExclusionMetadata;
        private Boolean isActive;
        private Boolean isPermanent;
        private Boolean isExpired;
        private Boolean isFuture;
        private String exclusionStatus;
        private Long durationInDays;
        private Long remainingDays;
        private Long daysSinceStart;
        private String exclusionSeverity;
        private String exclusionCategory;
        private Boolean affectsPublicContracts;
        private Boolean requiresLegalReview;
        private String displayText;
        private String shortDisplay;
        private String fullDisplay;
        private String periodDescription;
        private Integer exclusionPriority;
        private String businessImpact;
        private String complianceActionRequired;
        private String remediationProcess;
        private String monitoringRequirement;
        private Boolean hasReference;
        private String referenceSummary;
        private String exclusionSummary;
        private String alertLevel;
        private Boolean isCloseToExpiration;
        private String expirationWarning;
        private String formalDisplay;
    }
}