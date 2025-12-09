/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DomainController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.DomainService;
import dz.mdn.iaas.business.plan.dto.DomainDTO;

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
 * Domain REST Controller
 * Handles domain operations: create, get metadata, delete, get all
 * Based on exact Domain model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique)
 * Includes one-to-many relationship with Rubrics
 */
@RestController
@RequestMapping("/domain")
@RequiredArgsConstructor
@Slf4j
public class DomainController {

    private final DomainService domainService;

    // ========== POST ONE DOMAIN ==========

    /**
     * Create new domain
     * Creates domain with multilingual support and organizational classification
     */
    @PostMapping
    public ResponseEntity<DomainDTO> createDomain(@Valid @RequestBody DomainDTO domainDTO) {
        log.info("Creating domain with French designation: {}", 
                domainDTO.getDesignationFr());
        
        DomainDTO createdDomain = domainService.createDomain(domainDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDomain);
    }

    // ========== GET METADATA ==========

    /**
     * Get domain metadata by ID
     * Returns domain information with rubrics details and organizational classification
     */
    @GetMapping("/{id}")
    public ResponseEntity<DomainDTO> getDomainMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for domain ID: {}", id);
        
        DomainDTO domainMetadata = domainService.getDomainById(id);
        
        return ResponseEntity.ok(domainMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete domain by ID
     * Removes domain from the organizational management system
     * Note: Cannot delete domains with associated rubrics
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        log.info("Deleting domain with ID: {}", id);
        
        domainService.deleteDomain(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all domains with pagination
     * Returns list of all domains ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<DomainDTO>> getAllDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all domains - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DomainDTO> domains = domainService.getAllDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search domains by designation (any language)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<DomainDTO>> searchDomainsByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching domains by designation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.searchDomainsByDesignation(query, pageable);
        
        return ResponseEntity.ok(domains);
    }

    // ========== RUBRICS RELATIONSHIP ENDPOINTS ==========

    /**
     * Get domains with rubrics
     */
    @GetMapping("/with-rubrics")
    public ResponseEntity<Page<DomainDTO>> getDomainsWithRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting domains with rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getDomainsWithRubrics(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get domains without rubrics
     */
    @GetMapping("/without-rubrics")
    public ResponseEntity<Page<DomainDTO>> getDomainsWithoutRubrics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting domains without rubrics");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getDomainsWithoutRubrics(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get domains by rubrics count range
     */
    @GetMapping("/rubrics-count-range")
    public ResponseEntity<Page<DomainDTO>> getDomainsByRubricsCountRange(
            @RequestParam int minCount,
            @RequestParam int maxCount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting domains with rubrics count between {} and {}", minCount, maxCount);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rubricsCount"));
        Page<DomainDTO> domains = domainService.getDomainsByRubricsCountRange(minCount, maxCount, pageable);
        
        return ResponseEntity.ok(domains);
    }

    // ========== DOMAIN CATEGORY ENDPOINTS ==========

    /**
     * Get technical domains
     */
    @GetMapping("/category/technical")
    public ResponseEntity<Page<DomainDTO>> getTechnicalDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technical domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getTechnicalDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get administrative domains
     */
    @GetMapping("/category/administrative")
    public ResponseEntity<Page<DomainDTO>> getAdministrativeDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting administrative domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getAdministrativeDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get operational domains
     */
    @GetMapping("/category/operational")
    public ResponseEntity<Page<DomainDTO>> getOperationalDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting operational domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getOperationalDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get strategic domains
     */
    @GetMapping("/category/strategic")
    public ResponseEntity<Page<DomainDTO>> getStrategicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting strategic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getStrategicDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get financial domains
     */
    @GetMapping("/category/financial")
    public ResponseEntity<Page<DomainDTO>> getFinancialDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getFinancialDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get security domains
     */
    @GetMapping("/category/security")
    public ResponseEntity<Page<DomainDTO>> getSecurityDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting security domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getSecurityDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get HR domains
     */
    @GetMapping("/category/hr")
    public ResponseEntity<Page<DomainDTO>> getHRDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting HR domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getHRDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get logistics domains
     */
    @GetMapping("/category/logistics")
    public ResponseEntity<Page<DomainDTO>> getLogisticsDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting logistics domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getLogisticsDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get training domains
     */
    @GetMapping("/category/training")
    public ResponseEntity<Page<DomainDTO>> getTrainingDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting training domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getTrainingDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    // ========== COMPLEXITY-BASED ENDPOINTS ==========

    /**
     * Get high complexity domains (many rubrics)
     */
    @GetMapping("/complexity/high")
    public ResponseEntity<Page<DomainDTO>> getHighComplexityDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high complexity domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rubricsCount"));
        Page<DomainDTO> domains = domainService.getHighComplexityDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get medium complexity domains
     */
    @GetMapping("/complexity/medium")
    public ResponseEntity<Page<DomainDTO>> getMediumComplexityDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting medium complexity domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rubricsCount"));
        Page<DomainDTO> domains = domainService.getMediumComplexityDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get low complexity domains
     */
    @GetMapping("/complexity/low")
    public ResponseEntity<Page<DomainDTO>> getLowComplexityDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting low complexity domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "rubricsCount"));
        Page<DomainDTO> domains = domainService.getLowComplexityDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    // ========== PRIORITY-BASED ENDPOINTS ==========

    /**
     * Get domains by priority level
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<Page<DomainDTO>> getDomainsByPriorityLevel(
            @PathVariable String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting domains by priority level: {}", priority);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getDomainsByPriorityLevel(priority.toUpperCase(), pageable);
        
        return ResponseEntity.ok(domains);
    }

    /**
     * Get domains requiring executive oversight
     */
    @GetMapping("/requiring-executive-oversight")
    public ResponseEntity<Page<DomainDTO>> getDomainsRequiringExecutiveOversight(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting domains requiring executive oversight");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getDomainsRequiringExecutiveOversight(pageable);
        
        return ResponseEntity.ok(domains);
    }

    // ========== LANGUAGE SPECIFIC ENDPOINTS ==========

    /**
     * Get multilingual domains
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<DomainDTO>> getMultilingualDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<DomainDTO> domains = domainService.getMultilingualDomains(pageable);
        
        return ResponseEntity.ok(domains);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find domain by French designation (unique)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<DomainDTO> getDomainByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting domain by French designation: {}", designationFr);
        
        return domainService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update domain metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<DomainDTO> updateDomain(
            @PathVariable Long id,
            @Valid @RequestBody DomainDTO domainDTO) {
        
        log.info("Updating domain with ID: {}", id);
        
        DomainDTO updatedDomain = domainService.updateDomain(id, domainDTO);
        
        return ResponseEntity.ok(updatedDomain);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if domain exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkDomainExists(@PathVariable Long id) {
        log.debug("Checking existence of domain ID: {}", id);
        
        boolean exists = domainService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if French designation exists
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkDesignationFrExists(@PathVariable String designationFr) {
        log.debug("Checking if French designation exists: {}", designationFr);
        
        boolean exists = domainService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of all domains
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllDomains() {
        log.debug("Getting count of all domains");
        
        Long count = domainService.countAllDomains();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of domains with rubrics
     */
    @GetMapping("/count/with-rubrics")
    public ResponseEntity<Long> countDomainsWithRubrics() {
        log.debug("Getting count of domains with rubrics");
        
        Long count = domainService.countDomainsWithRubrics();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of domains without rubrics
     */
    @GetMapping("/count/without-rubrics")
    public ResponseEntity<Long> countDomainsWithoutRubrics() {
        log.debug("Getting count of domains without rubrics");
        
        Long count = domainService.countDomainsWithoutRubrics();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of technical domains
     */
    @GetMapping("/count/technical")
    public ResponseEntity<Long> countTechnicalDomains() {
        log.debug("Getting count of technical domains");
        
        Long count = domainService.countTechnicalDomains();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of operational domains
     */
    @GetMapping("/count/operational")
    public ResponseEntity<Long> countOperationalDomains() {
        log.debug("Getting count of operational domains");
        
        Long count = domainService.countOperationalDomains();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get average rubrics per domain
     */
    @GetMapping("/rubrics/average")
    public ResponseEntity<Double> getAverageRubricsPerDomain() {
        log.debug("Getting average rubrics per domain");
        
        Double average = domainService.getAverageRubricsPerDomain();
        
        return ResponseEntity.ok(average != null ? average : 0.0);
    }

    /**
     * Get maximum rubrics count
     */
    @GetMapping("/rubrics/max")
    public ResponseEntity<Integer> getMaxRubricsCount() {
        log.debug("Getting maximum rubrics count");
        
        Integer max = domainService.getMaxRubricsCount();
        
        return ResponseEntity.ok(max != null ? max : 0);
    }

    /**
     * Get minimum rubrics count (excluding zero)
     */
    @GetMapping("/rubrics/min-excluding-zero")
    public ResponseEntity<Integer> getMinRubricsCountExcludingZero() {
        log.debug("Getting minimum rubrics count excluding zero");
        
        Integer min = domainService.getMinRubricsCountExcludingZero();
        
        return ResponseEntity.ok(min != null ? min : 0);
    }

    /**
     * Get domain info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<DomainInfoResponse> getDomainInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for domain ID: {}", id);
        
        try {
            return domainService.findOne(id)
                    .map(domainDTO -> {
                        DomainInfoResponse response = DomainInfoResponse.builder()
                                .domainMetadata(domainDTO)
                                .defaultDesignation(domainDTO.getDefaultDesignation())
                                .displayText(domainDTO.getDisplayText())
                                .isMultilingual(domainDTO.isMultilingual())
                                .availableLanguages(domainDTO.getAvailableLanguages())
                                .domainCategory(domainDTO.getDomainCategory())
                                .domainPriority(domainDTO.getDomainPriority())
                                .domainScope(domainDTO.getDomainScope())
                                .hasRubrics(domainDTO.hasRubrics())
                                .rubricsCountSafe(domainDTO.getRubricsCountSafe())
                                .managementComplexity(domainDTO.getManagementComplexity())
                                .shortDisplay(domainDTO.getShortDisplay())
                                .fullDisplay(domainDTO.getFullDisplay())
                                .domainDisplay(domainDTO.getDomainDisplay())
                                .formalDisplay(domainDTO.getFormalDisplay())
                                .domainClassification(domainDTO.getDomainClassification())
                                .domainUsageContext(domainDTO.getDomainUsageContext())
                                .managementRequirements(domainDTO.getManagementRequirements())
                                .governanceModel(domainDTO.getGovernanceModel())
                                .reportingFrequency(domainDTO.getReportingFrequency())
                                .domainStakeholders(domainDTO.getDomainStakeholders())
                                .successMetrics(domainDTO.getSuccessMetrics())
                                .riskFactors(domainDTO.getRiskFactors())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting domain info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class DomainInfoResponse {
        private DomainDTO domainMetadata;
        private String defaultDesignation;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String domainCategory;
        private String domainPriority;
        private String domainScope;
        private Boolean hasRubrics;
        private Integer rubricsCountSafe;
        private String managementComplexity;
        private String shortDisplay;
        private String fullDisplay;
        private String domainDisplay;
        private String formalDisplay;
        private String domainClassification;
        private String domainUsageContext;
        private String managementRequirements;
        private String governanceModel;
        private String reportingFrequency;
        private String domainStakeholders;
        private String successMetrics;
        private String riskFactors;
    }
}