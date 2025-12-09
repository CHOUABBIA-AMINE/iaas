/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ExclusionTypeController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.service.ExclusionTypeService;
import dz.mdn.iaas.business.provider.dto.ExclusionTypeDTO;

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
 * Exclusion Type REST Controller
 * Handles exclusion type operations: create, get metadata, delete, get all
 * Based on exact ExclusionType model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@RestController
@RequestMapping("/exclusionType")
@RequiredArgsConstructor
@Slf4j
public class ExclusionTypeController {

    private final ExclusionTypeService exclusionTypeService;

    // ========== POST ONE EXCLUSION TYPE ==========

    /**
     * Create new exclusion type
     * Creates exclusion type with multilingual designations for business exclusion classification
     */
    @PostMapping
    public ResponseEntity<ExclusionTypeDTO> createExclusionType(@Valid @RequestBody ExclusionTypeDTO exclusionTypeDTO) {
        log.info("Creating exclusion type with French designation: {} and designations: AR={}, EN={}", 
                exclusionTypeDTO.getDesignationFr(), exclusionTypeDTO.getDesignationAr(), 
                exclusionTypeDTO.getDesignationEn());
        
        ExclusionTypeDTO createdExclusionType = exclusionTypeService.createExclusionType(exclusionTypeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExclusionType);
    }

    // ========== GET METADATA ==========

    /**
     * Get exclusion type metadata by ID
     * Returns exclusion type information with multilingual designations and exclusion intelligence
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExclusionTypeDTO> getExclusionTypeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for exclusion type ID: {}", id);
        
        ExclusionTypeDTO exclusionTypeMetadata = exclusionTypeService.getExclusionTypeById(id);
        
        return ResponseEntity.ok(exclusionTypeMetadata);
    }

    /**
     * Get exclusion type by French designation (F_03) - unique field
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ExclusionTypeDTO> getExclusionTypeByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting exclusion type by French designation: {}", designationFr);
        
        return exclusionTypeService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get exclusion type by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<ExclusionTypeDTO> getExclusionTypeByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting exclusion type by Arabic designation: {}", designationAr);
        
        return exclusionTypeService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get exclusion type by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<ExclusionTypeDTO> getExclusionTypeByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting exclusion type by English designation: {}", designationEn);
        
        return exclusionTypeService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete exclusion type by ID
     * Removes exclusion type from the business exclusion classification system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExclusionType(@PathVariable Long id) {
        log.info("Deleting exclusion type with ID: {}", id);
        
        exclusionTypeService.deleteExclusionType(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all exclusion types with pagination
     * Returns list of all exclusion types ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ExclusionTypeDTO>> getAllExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all exclusion types - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getAllExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search exclusion types by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ExclusionTypeDTO>> searchExclusionTypes(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching exclusion types with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.searchExclusionTypes(query, pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    // ========== EXCLUSION CATEGORY CLASSIFICATION ENDPOINTS ==========

    /**
     * Get multilingual exclusion types
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ExclusionTypeDTO>> getMultilingualExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getMultilingualExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get legal exclusion types
     */
    @GetMapping("/legal")
    public ResponseEntity<Page<ExclusionTypeDTO>> getLegalExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting legal exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getLegalExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get criminal exclusion types
     */
    @GetMapping("/criminal")
    public ResponseEntity<Page<ExclusionTypeDTO>> getCriminalExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting criminal exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getCriminalExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get financial exclusion types
     */
    @GetMapping("/financial")
    public ResponseEntity<Page<ExclusionTypeDTO>> getFinancialExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getFinancialExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get tax exclusion types
     */
    @GetMapping("/tax")
    public ResponseEntity<Page<ExclusionTypeDTO>> getTaxExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting tax exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getTaxExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get administrative exclusion types
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<ExclusionTypeDTO>> getAdministrativeExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting administrative exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getAdministrativeExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get security exclusion types
     */
    @GetMapping("/security")
    public ResponseEntity<Page<ExclusionTypeDTO>> getSecurityExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting security exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getSecurityExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    // ========== SPECIALIZED EXCLUSION ENDPOINTS ==========

    /**
     * Get sectoral exclusion types
     */
    @GetMapping("/sectoral")
    public ResponseEntity<Page<ExclusionTypeDTO>> getSectoralExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting sectoral exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getSectoralExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get geographical exclusion types
     */
    @GetMapping("/geographical")
    public ResponseEntity<Page<ExclusionTypeDTO>> getGeographicalExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting geographical exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getGeographicalExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get temporal exclusion types
     */
    @GetMapping("/temporal")
    public ResponseEntity<Page<ExclusionTypeDTO>> getTemporalExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting temporal exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getTemporalExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get qualification exclusion types
     */
    @GetMapping("/qualification")
    public ResponseEntity<Page<ExclusionTypeDTO>> getQualificationExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting qualification exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getQualificationExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get conflict exclusion types
     */
    @GetMapping("/conflict")
    public ResponseEntity<Page<ExclusionTypeDTO>> getConflictExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting conflict exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getConflictExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    // ========== DURATION AND SEVERITY ENDPOINTS ==========

    /**
     * Get permanent exclusion types
     */
    @GetMapping("/permanent")
    public ResponseEntity<Page<ExclusionTypeDTO>> getPermanentExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting permanent exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getPermanentExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get temporary exclusion types
     */
    @GetMapping("/temporary")
    public ResponseEntity<Page<ExclusionTypeDTO>> getTemporaryExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting temporary exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getTemporaryExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get conditional exclusion types
     */
    @GetMapping("/conditional")
    public ResponseEntity<Page<ExclusionTypeDTO>> getConditionalExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting conditional exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getConditionalExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get high severity exclusion types
     */
    @GetMapping("/high-severity")
    public ResponseEntity<Page<ExclusionTypeDTO>> getHighSeverityExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high severity exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getHighSeverityExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    // ========== REGULATORY IMPACT ENDPOINTS ==========

    /**
     * Get public contract exclusion types
     */
    @GetMapping("/public-contract")
    public ResponseEntity<Page<ExclusionTypeDTO>> getPublicContractExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting public contract exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getPublicContractExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    /**
     * Get legal review exclusion types
     */
    @GetMapping("/legal-review")
    public ResponseEntity<Page<ExclusionTypeDTO>> getLegalReviewExclusionTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting legal review exclusion types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getLegalReviewExclusionTypes(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    // ========== ADMINISTRATIVE ENDPOINTS ==========

    /**
     * Get exclusion types missing translations
     */
    @GetMapping("/missing-translations")
    public ResponseEntity<Page<ExclusionTypeDTO>> getExclusionTypesMissingTranslations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting exclusion types missing translations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getExclusionTypesMissingTranslations(pageable);
        
        return ResponseEntity.ok(exclusionTypes);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update exclusion type metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExclusionTypeDTO> updateExclusionType(
            @PathVariable Long id,
            @Valid @RequestBody ExclusionTypeDTO exclusionTypeDTO) {
        
        log.info("Updating exclusion type with ID: {}", id);
        
        ExclusionTypeDTO updatedExclusionType = exclusionTypeService.updateExclusionType(id, exclusionTypeDTO);
        
        return ResponseEntity.ok(updatedExclusionType);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if exclusion type exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkExclusionTypeExists(@PathVariable Long id) {
        log.debug("Checking existence of exclusion type ID: {}", id);
        
        boolean exists = exclusionTypeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if exclusion type exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkExclusionTypeExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = exclusionTypeService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of exclusion types
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getExclusionTypesCount() {
        log.debug("Getting total count of exclusion types");
        
        Long count = exclusionTypeService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of legal exclusion types
     */
    @GetMapping("/count/legal")
    public ResponseEntity<Long> getLegalExclusionTypesCount() {
        log.debug("Getting count of legal exclusion types");
        
        Long count = exclusionTypeService.getLegalExclusionTypesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of financial exclusion types
     */
    @GetMapping("/count/financial")
    public ResponseEntity<Long> getFinancialExclusionTypesCount() {
        log.debug("Getting count of financial exclusion types");
        
        Long count = exclusionTypeService.getFinancialExclusionTypesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of administrative exclusion types
     */
    @GetMapping("/count/administrative")
    public ResponseEntity<Long> getAdministrativeExclusionTypesCount() {
        log.debug("Getting count of administrative exclusion types");
        
        Long count = exclusionTypeService.getAdministrativeExclusionTypesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of security exclusion types
     */
    @GetMapping("/count/security")
    public ResponseEntity<Long> getSecurityExclusionTypesCount() {
        log.debug("Getting count of security exclusion types");
        
        Long count = exclusionTypeService.getSecurityExclusionTypesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of multilingual exclusion types
     */
    @GetMapping("/count/multilingual")
    public ResponseEntity<Long> getMultilingualCount() {
        log.debug("Getting count of multilingual exclusion types");
        
        Long count = exclusionTypeService.getMultilingualCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get exclusion type info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ExclusionTypeInfoResponse> getExclusionTypeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for exclusion type ID: {}", id);
        
        try {
            return exclusionTypeService.findOne(id)
                    .map(exclusionTypeDTO -> {
                        ExclusionTypeInfoResponse response = ExclusionTypeInfoResponse.builder()
                                .exclusionTypeMetadata(exclusionTypeDTO)
                                .defaultDesignation(exclusionTypeDTO.getDefaultDesignation())
                                .displayText(exclusionTypeDTO.getDisplayText())
                                .isMultilingual(exclusionTypeDTO.isMultilingual())
                                .availableLanguages(exclusionTypeDTO.getAvailableLanguages())
                                .exclusionCategory(exclusionTypeDTO.getExclusionCategory())
                                .severityLevel(exclusionTypeDTO.getSeverityLevel())
                                .durationType(exclusionTypeDTO.getDurationType())
                                .exclusionPriority(exclusionTypeDTO.getExclusionPriority())
                                .isPermanentExclusion(exclusionTypeDTO.isPermanentExclusion())
                                .isConditionalExclusion(exclusionTypeDTO.isConditionalExclusion())
                                .requiresLegalReview(exclusionTypeDTO.requiresLegalReview())
                                .affectsPublicContracts(exclusionTypeDTO.affectsPublicContracts())
                                .appealAuthority(exclusionTypeDTO.getAppealAuthority())
                                .responsibleMinistry(exclusionTypeDTO.getResponsibleMinistry())
                                .shortDisplay(exclusionTypeDTO.getShortDisplay())
                                .fullDisplay(exclusionTypeDTO.getFullDisplay())
                                .comparisonKey(exclusionTypeDTO.getComparisonKey())
                                .displayWithCategory(exclusionTypeDTO.getDisplayWithCategory())
                                .displayWithSeverity(exclusionTypeDTO.getDisplayWithSeverity())
                                .formalExclusionDisplay(exclusionTypeDTO.getFormalExclusionDisplay())
                                .regulatoryImpact(exclusionTypeDTO.getRegulatoryImpact())
                                .businessImpact(exclusionTypeDTO.getBusinessImpact())
                                .remediationProcess(exclusionTypeDTO.getRemediationProcess())
                                .monitoringRequirement(exclusionTypeDTO.getMonitoringRequirement())
                                .notificationRequirement(exclusionTypeDTO.getNotificationRequirement())
                                .documentationRequirement(exclusionTypeDTO.getDocumentationRequirement())
                                .reviewFrequency(exclusionTypeDTO.getReviewFrequency())
                                .exclusionScope(exclusionTypeDTO.getExclusionScope())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting exclusion type info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ExclusionTypeInfoResponse {
        private ExclusionTypeDTO exclusionTypeMetadata;
        private String defaultDesignation;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String exclusionCategory;
        private String severityLevel;
        private String durationType;
        private Integer exclusionPriority;
        private Boolean isPermanentExclusion;
        private Boolean isConditionalExclusion;
        private Boolean requiresLegalReview;
        private Boolean affectsPublicContracts;
        private String appealAuthority;
        private String responsibleMinistry;
        private String shortDisplay;
        private String fullDisplay;
        private String comparisonKey;
        private String displayWithCategory;
        private String displayWithSeverity;
        private String formalExclusionDisplay;
        private String regulatoryImpact;
        private String businessImpact;
        private String remediationProcess;
        private String monitoringRequirement;
        private String notificationRequirement;
        private String documentationRequirement;
        private String reviewFrequency;
        private String exclusionScope;
    }
}