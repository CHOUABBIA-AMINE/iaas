/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.service.ClearanceService;
import dz.mdn.iaas.business.provider.dto.ClearanceDTO;

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
 * Clearance REST Controller
 * Handles clearance operations: create, get metadata, delete, get all
 * Based on exact Clearance model: F_00=id, F_01=startDate, F_02=endDate, 
 * F_03=providerId, F_04=providerRepresentatorId, F_05=referenceId
 */
@RestController
@RequestMapping("/clearance")
@RequiredArgsConstructor
@Slf4j
public class ClearanceController {

    private final ClearanceService clearanceService;

    // ========== POST ONE CLEARANCE ==========

    /**
     * Create new clearance
     * Creates clearance with temporal validation and validity monitoring
     */
    @PostMapping
    public ResponseEntity<ClearanceDTO> createClearance(@Valid @RequestBody ClearanceDTO clearanceDTO) {
        log.info("Creating clearance for provider ID: {}, representator ID: {}", 
                clearanceDTO.getProviderId(), clearanceDTO.getProviderRepresentatorId());
        
        ClearanceDTO createdClearance = clearanceService.createClearance(clearanceDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClearance);
    }

    // ========== GET METADATA ==========

    /**
     * Get clearance metadata by ID
     * Returns clearance information with validity status and temporal analysis
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClearanceDTO> getClearanceMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for clearance ID: {}", id);
        
        ClearanceDTO clearanceMetadata = clearanceService.getClearanceById(id);
        
        return ResponseEntity.ok(clearanceMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete clearance by ID
     * Removes clearance from the validity management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClearance(@PathVariable Long id) {
        log.info("Deleting clearance with ID: {}", id);
        
        clearanceService.deleteClearance(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all clearances with pagination
     * Returns list of all clearances ordered by start date (most recent first)
     */
    @GetMapping
    public ResponseEntity<Page<ClearanceDTO>> getAllClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "startDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting all clearances - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ClearanceDTO> clearances = clearanceService.getAllClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    // ========== PROVIDER-SPECIFIC ENDPOINTS ==========

    /**
     * Get clearances by provider
     */
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<Page<ClearanceDTO>> getClearancesByProvider(
            @PathVariable Long providerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting clearances for provider ID: {}", providerId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getClearancesByProvider(providerId, pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get active clearances for a specific provider
     */
    @GetMapping("/provider/{providerId}/active")
    public ResponseEntity<List<ClearanceDTO>> getActiveClearancesForProvider(@PathVariable Long providerId) {
        log.debug("Getting active clearances for provider ID: {}", providerId);
        
        List<ClearanceDTO> activeClearances = clearanceService.getActiveClearancesForProvider(providerId);
        
        return ResponseEntity.ok(activeClearances);
    }

    // ========== REPRESENTATOR-SPECIFIC ENDPOINTS ==========

    /**
     * Get clearances by provider representator
     */
    @GetMapping("/representator/{representatorId}")
    public ResponseEntity<Page<ClearanceDTO>> getClearancesByRepresentator(
            @PathVariable Long representatorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting clearances for representator ID: {}", representatorId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getClearancesByRepresentator(representatorId, pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get active clearances for a specific representator
     */
    @GetMapping("/representator/{representatorId}/active")
    public ResponseEntity<List<ClearanceDTO>> getActiveClearancesForRepresentator(@PathVariable Long representatorId) {
        log.debug("Getting active clearances for representator ID: {}", representatorId);
        
        List<ClearanceDTO> activeClearances = clearanceService.getActiveClearancesForRepresentator(representatorId);
        
        return ResponseEntity.ok(activeClearances);
    }

    /**
     * Get clearances by provider and representator
     */
    @GetMapping("/provider/{providerId}/representator/{representatorId}")
    public ResponseEntity<Page<ClearanceDTO>> getClearancesByProviderAndRepresentator(
            @PathVariable Long providerId,
            @PathVariable Long representatorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting clearances for provider ID: {} and representator ID: {}", providerId, representatorId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getClearancesByProviderAndRepresentator(providerId, representatorId, pageable);
        
        return ResponseEntity.ok(clearances);
    }

    // ========== STATUS-BASED ENDPOINTS ==========

    /**
     * Get active clearances
     */
    @GetMapping("/active")
    public ResponseEntity<Page<ClearanceDTO>> getActiveClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting active clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getActiveClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get expired clearances
     */
    @GetMapping("/expired")
    public ResponseEntity<Page<ClearanceDTO>> getExpiredClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting expired clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "endDate"));
        Page<ClearanceDTO> clearances = clearanceService.getExpiredClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get permanent clearances
     */
    @GetMapping("/permanent")
    public ResponseEntity<Page<ClearanceDTO>> getPermanentClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting permanent clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getPermanentClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get future clearances
     */
    @GetMapping("/future")
    public ResponseEntity<Page<ClearanceDTO>> getFutureClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting future clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getFutureClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get clearances expiring soon (within 30 days)
     */
    @GetMapping("/expiring-soon")
    public ResponseEntity<Page<ClearanceDTO>> getClearancesExpiringSoon(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting clearances expiring soon");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "endDate"));
        Page<ClearanceDTO> clearances = clearanceService.getClearancesExpiringSoon(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get clearances requiring urgent renewal (within 7 days)
     */
    @GetMapping("/urgent-renewal")
    public ResponseEntity<Page<ClearanceDTO>> getClearancesRequiringUrgentRenewal(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting clearances requiring urgent renewal");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "endDate"));
        Page<ClearanceDTO> clearances = clearanceService.getClearancesRequiringUrgentRenewal(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    // ========== DURATION-BASED ENDPOINTS ==========

    /**
     * Get short-term clearances (duration <= 30 days)
     */
    @GetMapping("/short-term")
    public ResponseEntity<Page<ClearanceDTO>> getShortTermClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting short-term clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getShortTermClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get medium-term clearances (duration 31-365 days)
     */
    @GetMapping("/medium-term")
    public ResponseEntity<Page<ClearanceDTO>> getMediumTermClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting medium-term clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getMediumTermClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get long-term clearances (duration > 365 days)
     */
    @GetMapping("/long-term")
    public ResponseEntity<Page<ClearanceDTO>> getLongTermClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting long-term clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getLongTermClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    // ========== AUTHORITY-BASED ENDPOINTS ==========

    /**
     * Get executive clearances
     */
    @GetMapping("/executive")
    public ResponseEntity<Page<ClearanceDTO>> getExecutiveClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting executive clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getExecutiveClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get legal representative clearances
     */
    @GetMapping("/legal-representative")
    public ResponseEntity<Page<ClearanceDTO>> getLegalRepresentativeClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting legal representative clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getLegalRepresentativeClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    /**
     * Get technical representative clearances
     */
    @GetMapping("/technical-representative")
    public ResponseEntity<Page<ClearanceDTO>> getTechnicalRepresentativeClearances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technical representative clearances");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ClearanceDTO> clearances = clearanceService.getTechnicalRepresentativeClearances(pageable);
        
        return ResponseEntity.ok(clearances);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update clearance metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClearanceDTO> updateClearance(
            @PathVariable Long id,
            @Valid @RequestBody ClearanceDTO clearanceDTO) {
        
        log.info("Updating clearance with ID: {}", id);
        
        ClearanceDTO updatedClearance = clearanceService.updateClearance(id, clearanceDTO);
        
        return ResponseEntity.ok(updatedClearance);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if clearance exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkClearanceExists(@PathVariable Long id) {
        log.debug("Checking existence of clearance ID: {}", id);
        
        boolean exists = clearanceService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if provider has active clearances
     */
    @GetMapping("/provider/{providerId}/has-active")
    public ResponseEntity<Boolean> checkProviderHasActiveClearances(@PathVariable Long providerId) {
        log.debug("Checking if provider ID: {} has active clearances", providerId);
        
        boolean hasActive = clearanceService.hasActiveClearances(providerId);
        
        return ResponseEntity.ok(hasActive);
    }

    /**
     * Check if representator has active clearances
     */
    @GetMapping("/representator/{representatorId}/has-active")
    public ResponseEntity<Boolean> checkRepresentatorHasActiveClearances(@PathVariable Long representatorId) {
        log.debug("Checking if representator ID: {} has active clearances", representatorId);
        
        boolean hasActive = clearanceService.representatorHasActiveClearances(representatorId);
        
        return ResponseEntity.ok(hasActive);
    }

    /**
     * Check if provider has permanent clearances
     */
    @GetMapping("/provider/{providerId}/has-permanent")
    public ResponseEntity<Boolean> checkProviderHasPermanentClearances(@PathVariable Long providerId) {
        log.debug("Checking if provider ID: {} has permanent clearances", providerId);
        
        boolean hasPermanent = clearanceService.hasPermanentClearances(providerId);
        
        return ResponseEntity.ok(hasPermanent);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of active clearances for provider
     */
    @GetMapping("/provider/{providerId}/count/active")
    public ResponseEntity<Long> countActiveClearancesForProvider(@PathVariable Long providerId) {
        log.debug("Getting count of active clearances for provider ID: {}", providerId);
        
        Long count = clearanceService.countActiveClearancesForProvider(providerId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of active clearances for representator
     */
    @GetMapping("/representator/{representatorId}/count/active")
    public ResponseEntity<Long> countActiveClearancesForRepresentator(@PathVariable Long representatorId) {
        log.debug("Getting count of active clearances for representator ID: {}", representatorId);
        
        Long count = clearanceService.countActiveClearancesForRepresentator(representatorId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of active clearances
     */
    @GetMapping("/count/active")
    public ResponseEntity<Long> countActiveClearances() {
        log.debug("Getting count of active clearances");
        
        Long count = clearanceService.countActiveClearances();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of permanent clearances
     */
    @GetMapping("/count/permanent")
    public ResponseEntity<Long> countPermanentClearances() {
        log.debug("Getting count of permanent clearances");
        
        Long count = clearanceService.countPermanentClearances();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of expired clearances
     */
    @GetMapping("/count/expired")
    public ResponseEntity<Long> countExpiredClearances() {
        log.debug("Getting count of expired clearances");
        
        Long count = clearanceService.countExpiredClearances();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get clearance info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ClearanceInfoResponse> getClearanceInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for clearance ID: {}", id);
        
        try {
            return clearanceService.findOne(id)
                    .map(clearanceDTO -> {
                        ClearanceInfoResponse response = ClearanceInfoResponse.builder()
                                .clearanceMetadata(clearanceDTO)
                                .isActive(clearanceDTO.isActive())
                                .isPermanent(clearanceDTO.isPermanent())
                                .isExpired(clearanceDTO.isExpired())
                                .isFuture(clearanceDTO.isFuture())
                                .clearanceStatus(clearanceDTO.getClearanceStatus())
                                .validityDurationInDays(clearanceDTO.getValidityDurationInDays())
                                .remainingValidityDays(clearanceDTO.getRemainingValidityDays())
                                .daysSinceStart(clearanceDTO.getDaysSinceStart())
                                .clearanceType(clearanceDTO.getClearanceType())
                                .clearancePriority(clearanceDTO.getClearancePriority())
                                .isCloseToExpiration(clearanceDTO.isCloseToExpiration())
                                .requiresUrgentRenewal(clearanceDTO.requiresUrgentRenewal())
                                .displayText(clearanceDTO.getDisplayText())
                                .shortDisplay(clearanceDTO.getShortDisplay())
                                .fullDisplay(clearanceDTO.getFullDisplay())
                                .periodDescription(clearanceDTO.getPeriodDescription())
                                .validityAssessment(clearanceDTO.getValidityAssessment())
                                .daysUntilStart(clearanceDTO.getDaysUntilStart())
                                .daysExpired(clearanceDTO.getDaysExpired())
                                .renewalUrgency(clearanceDTO.getRenewalUrgency())
                                .actionRequired(clearanceDTO.getActionRequired())
                                .hasReference(clearanceDTO.hasReference())
                                .referenceSummary(clearanceDTO.getReferenceSummary())
                                .alertLevel(clearanceDTO.getAlertLevel())
                                .clearanceSummary(clearanceDTO.getClearanceSummary())
                                .expirationWarning(clearanceDTO.getExpirationWarning())
                                .formalDisplay(clearanceDTO.getFormalDisplay())
                                .effectivenessPercentage(clearanceDTO.getEffectivenessPercentage())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting clearance info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ClearanceInfoResponse {
        private ClearanceDTO clearanceMetadata;
        private Boolean isActive;
        private Boolean isPermanent;
        private Boolean isExpired;
        private Boolean isFuture;
        private String clearanceStatus;
        private Long validityDurationInDays;
        private Long remainingValidityDays;
        private Long daysSinceStart;
        private String clearanceType;
        private Integer clearancePriority;
        private Boolean isCloseToExpiration;
        private Boolean requiresUrgentRenewal;
        private String displayText;
        private String shortDisplay;
        private String fullDisplay;
        private String periodDescription;
        private String validityAssessment;
        private Long daysUntilStart;
        private Long daysExpired;
        private String renewalUrgency;
        private String actionRequired;
        private Boolean hasReference;
        private String referenceSummary;
        private String alertLevel;
        private String clearanceSummary;
        private String expirationWarning;
        private String formalDisplay;
        private Integer effectivenessPercentage;
    }
}