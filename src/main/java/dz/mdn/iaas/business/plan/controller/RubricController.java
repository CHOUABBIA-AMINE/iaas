/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RubricController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.RubricService;
import dz.mdn.iaas.business.plan.dto.RubricDTO;

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
 * Rubric REST Controller
 * Handles rubric operations: create, get metadata, delete, get all
 * Based on exact Rubric model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique), F_04=domainId
 * Includes many-to-one relationship with Domain and one-to-many relationship with Items
 */
@RestController
@RequestMapping("/rubric")
@RequiredArgsConstructor
@Slf4j
public class RubricController {

    private final RubricService rubricService;

    // ========== POST ONE RUBRIC ==========

    /**
     * Create new rubric
     * Creates rubric with multilingual support, domain relationship, and classification
     */
    @PostMapping
    public ResponseEntity<RubricDTO> createRubric(@Valid @RequestBody RubricDTO rubricDTO) {
        log.info("Creating rubric with French designation: {} for domain ID: {}", 
                rubricDTO.getDesignationFr(), rubricDTO.getDomainId());
        
        RubricDTO createdRubric = rubricService.createRubric(rubricDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRubric);
    }

    // ========== GET METADATA ==========

    /**
     * Get rubric metadata by ID
     * Returns rubric information with domain details, items details, and classification
     */
    @GetMapping("/{id}")
    public ResponseEntity<RubricDTO> getRubricMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for rubric ID: {}", id);
        
        RubricDTO rubricMetadata = rubricService.getRubricById(id);
        
        return ResponseEntity.ok(rubricMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete rubric by ID
     * Removes rubric from the rubric management system
     * Note: Cannot delete rubrics with associated items
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRubric(@PathVariable Long id) {
        log.info("Deleting rubric with ID: {}", id);
        
        rubricService.deleteRubric(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all rubrics with pagination
     * Returns list of all rubrics ordered by domain designation, then by rubric designation
     */
    @GetMapping
    public ResponseEntity<Page<RubricDTO>> getAllRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "domain.designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all rubrics - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RubricDTO> rubrics = rubricService.getAllRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search rubrics by designation (any language)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<RubricDTO>> searchRubricsByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching rubrics by designation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "domain.designationFr"));
        Page<RubricDTO> rubrics = rubricService.searchRubricsByDesignation(query, pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== DOMAIN RELATIONSHIP ENDPOINTS ==========

    /**
     * Get rubrics by domain
     */
    @GetMapping("/domain/{domainId}")
    public ResponseEntity<Page<RubricDTO>> getRubricsByDomain(
            @PathVariable Long domainId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rubrics for domain ID: {}", domainId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getRubricsByDomain(domainId, pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get rubrics by domain category
     */
    @GetMapping("/domain-category/{category}")
    public ResponseEntity<Page<RubricDTO>> getRubricsByDomainCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rubrics by domain category: {}", category);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "domain.designationFr"));
        Page<RubricDTO> rubrics = rubricService.getRubricsByDomainCategory(category, pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== ITEMS RELATIONSHIP ENDPOINTS ==========

    /**
     * Get rubrics with items
     */
    @GetMapping("/with-items")
    public ResponseEntity<Page<RubricDTO>> getRubricsWithItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rubrics with items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "domain.designationFr"));
        Page<RubricDTO> rubrics = rubricService.getRubricsWithItems(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get rubrics without items
     */
    @GetMapping("/without-items")
    public ResponseEntity<Page<RubricDTO>> getRubricsWithoutItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rubrics without items");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "domain.designationFr"));
        Page<RubricDTO> rubrics = rubricService.getRubricsWithoutItems(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get rubrics by items count range
     */
    @GetMapping("/items-count-range")
    public ResponseEntity<Page<RubricDTO>> getRubricsByItemsCountRange(
            @RequestParam int minCount,
            @RequestParam int maxCount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rubrics with items count between {} and {}", minCount, maxCount);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "itemsCount"));
        Page<RubricDTO> rubrics = rubricService.getRubricsByItemsCountRange(minCount, maxCount, pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== RUBRIC CATEGORY ENDPOINTS ==========

    /**
     * Get requirements rubrics
     */
    @GetMapping("/category/requirements")
    public ResponseEntity<Page<RubricDTO>> getRequirementsRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting requirements rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getRequirementsRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get quality rubrics
     */
    @GetMapping("/category/quality")
    public ResponseEntity<Page<RubricDTO>> getQualityRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting quality rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getQualityRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get performance rubrics
     */
    @GetMapping("/category/performance")
    public ResponseEntity<Page<RubricDTO>> getPerformanceRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting performance rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getPerformanceRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get security rubrics
     */
    @GetMapping("/category/security")
    public ResponseEntity<Page<RubricDTO>> getSecurityRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting security rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getSecurityRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get compliance rubrics
     */
    @GetMapping("/category/compliance")
    public ResponseEntity<Page<RubricDTO>> getComplianceRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting compliance rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getComplianceRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get technical rubrics
     */
    @GetMapping("/category/technical")
    public ResponseEntity<Page<RubricDTO>> getTechnicalRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technical rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getTechnicalRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get operational rubrics
     */
    @GetMapping("/category/operational")
    public ResponseEntity<Page<RubricDTO>> getOperationalRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting operational rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getOperationalRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get training rubrics
     */
    @GetMapping("/category/training")
    public ResponseEntity<Page<RubricDTO>> getTrainingRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting training rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getTrainingRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get documentation rubrics
     */
    @GetMapping("/category/documentation")
    public ResponseEntity<Page<RubricDTO>> getDocumentationRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting documentation rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getDocumentationRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== COMPLEXITY-BASED ENDPOINTS ==========

    /**
     * Get high complexity rubrics (many items)
     */
    @GetMapping("/complexity/high")
    public ResponseEntity<Page<RubricDTO>> getHighComplexityRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high complexity rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "itemsCount"));
        Page<RubricDTO> rubrics = rubricService.getHighComplexityRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get medium complexity rubrics
     */
    @GetMapping("/complexity/medium")
    public ResponseEntity<Page<RubricDTO>> getMediumComplexityRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting medium complexity rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "itemsCount"));
        Page<RubricDTO> rubrics = rubricService.getMediumComplexityRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get low complexity rubrics
     */
    @GetMapping("/complexity/low")
    public ResponseEntity<Page<RubricDTO>> getLowComplexityRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting low complexity rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "itemsCount"));
        Page<RubricDTO> rubrics = rubricService.getLowComplexityRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== PRIORITY-BASED ENDPOINTS ==========

    /**
     * Get rubrics by priority level
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<Page<RubricDTO>> getRubricsByPriorityLevel(
            @PathVariable String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rubrics by priority level: {}", priority);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getRubricsByPriorityLevel(priority.toUpperCase(), pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    /**
     * Get rubrics requiring critical attention
     */
    @GetMapping("/requiring-critical-attention")
    public ResponseEntity<Page<RubricDTO>> getRubricsRequiringCriticalAttention(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rubrics requiring critical attention");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getRubricsRequiringCriticalAttention(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== LANGUAGE SPECIFIC ENDPOINTS ==========

    /**
     * Get multilingual rubrics
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<RubricDTO>> getMultilingualRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RubricDTO> rubrics = rubricService.getMultilingualRubrics(pageable);
        
        return ResponseEntity.ok(rubrics);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find rubric by French designation (unique)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<RubricDTO> getRubricByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting rubric by French designation: {}", designationFr);
        
        return rubricService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update rubric metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<RubricDTO> updateRubric(
            @PathVariable Long id,
            @Valid @RequestBody RubricDTO rubricDTO) {
        
        log.info("Updating rubric with ID: {}", id);
        
        RubricDTO updatedRubric = rubricService.updateRubric(id, rubricDTO);
        
        return ResponseEntity.ok(updatedRubric);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if rubric exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkRubricExists(@PathVariable Long id) {
        log.debug("Checking existence of rubric ID: {}", id);
        
        boolean exists = rubricService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if French designation exists
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkDesignationFrExists(@PathVariable String designationFr) {
        log.debug("Checking if French designation exists: {}", designationFr);
        
        boolean exists = rubricService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of rubrics by domain
     */
    @GetMapping("/domain/{domainId}/count")
    public ResponseEntity<Long> countRubricsByDomain(@PathVariable Long domainId) {
        log.debug("Getting count of rubrics for domain ID: {}", domainId);
        
        Long count = rubricService.countRubricsByDomain(domainId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of all rubrics
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllRubrics() {
        log.debug("Getting count of all rubrics");
        
        Long count = rubricService.countAllRubrics();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of rubrics with items
     */
    @GetMapping("/count/with-items")
    public ResponseEntity<Long> countRubricsWithItems() {
        log.debug("Getting count of rubrics with items");
        
        Long count = rubricService.countRubricsWithItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of rubrics without items
     */
    @GetMapping("/count/without-items")
    public ResponseEntity<Long> countRubricsWithoutItems() {
        log.debug("Getting count of rubrics without items");
        
        Long count = rubricService.countRubricsWithoutItems();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of requirements rubrics
     */
    @GetMapping("/count/requirements")
    public ResponseEntity<Long> countRequirementsRubrics() {
        log.debug("Getting count of requirements rubrics");
        
        Long count = rubricService.countRequirementsRubrics();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of quality rubrics
     */
    @GetMapping("/count/quality")
    public ResponseEntity<Long> countQualityRubrics() {
        log.debug("Getting count of quality rubrics");
        
        Long count = rubricService.countQualityRubrics();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get average items per rubric
     */
    @GetMapping("/items/average")
    public ResponseEntity<Double> getAverageItemsPerRubric() {
        log.debug("Getting average items per rubric");
        
        Double average = rubricService.getAverageItemsPerRubric();
        
        return ResponseEntity.ok(average != null ? average : 0.0);
    }

    /**
     * Get maximum items count
     */
    @GetMapping("/items/max")
    public ResponseEntity<Integer> getMaxItemsCount() {
        log.debug("Getting maximum items count");
        
        Integer max = rubricService.getMaxItemsCount();
        
        return ResponseEntity.ok(max != null ? max : 0);
    }

    /**
     * Get minimum items count (excluding zero)
     */
    @GetMapping("/items/min-excluding-zero")
    public ResponseEntity<Integer> getMinItemsCountExcludingZero() {
        log.debug("Getting minimum items count excluding zero");
        
        Integer min = rubricService.getMinItemsCountExcludingZero();
        
        return ResponseEntity.ok(min != null ? min : 0);
    }

    /**
     * Get rubric info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<RubricInfoResponse> getRubricInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for rubric ID: {}", id);
        
        try {
            return rubricService.findOne(id)
                    .map(rubricDTO -> {
                        RubricInfoResponse response = RubricInfoResponse.builder()
                                .rubricMetadata(rubricDTO)
                                .defaultDesignation(rubricDTO.getDefaultDesignation())
                                .displayText(rubricDTO.getDisplayText())
                                .isMultilingual(rubricDTO.isMultilingual())
                                .availableLanguages(rubricDTO.getAvailableLanguages())
                                .rubricCategory(rubricDTO.getRubricCategory())
                                .rubricPriority(rubricDTO.getRubricPriority())
                                .applicationScope(rubricDTO.getApplicationScope())
                                .hasItems(rubricDTO.hasItems())
                                .itemsCountSafe(rubricDTO.getItemsCountSafe())
                                .rubricComplexity(rubricDTO.getRubricComplexity())
                                .shortDisplay(rubricDTO.getShortDisplay())
                                .fullDisplay(rubricDTO.getFullDisplay())
                                .rubricDisplay(rubricDTO.getRubricDisplay())
                                .formalDisplay(rubricDTO.getFormalDisplay())
                                .rubricClassification(rubricDTO.getRubricClassification())
                                .rubricUsageContext(rubricDTO.getRubricUsageContext())
                                .implementationRequirements(rubricDTO.getImplementationRequirements())
                                .assessmentCriteria(rubricDTO.getAssessmentCriteria())
                                .reviewFrequency(rubricDTO.getReviewFrequency())
                                .rubricStakeholders(rubricDTO.getRubricStakeholders())
                                .successMetrics(rubricDTO.getSuccessMetrics())
                                .riskFactors(rubricDTO.getRiskFactors())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting rubric info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class RubricInfoResponse {
        private RubricDTO rubricMetadata;
        private String defaultDesignation;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String rubricCategory;
        private String rubricPriority;
        private String applicationScope;
        private Boolean hasItems;
        private Integer itemsCountSafe;
        private String rubricComplexity;
        private String shortDisplay;
        private String fullDisplay;
        private String rubricDisplay;
        private String formalDisplay;
        private String rubricClassification;
        private String rubricUsageContext;
        private String implementationRequirements;
        private String assessmentCriteria;
        private String reviewFrequency;
        private String rubricStakeholders;
        private String successMetrics;
        private String riskFactors;
    }
}