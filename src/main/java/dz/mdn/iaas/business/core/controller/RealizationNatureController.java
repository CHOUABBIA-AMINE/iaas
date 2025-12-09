/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationNatureController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.RealizationNatureService;
import dz.mdn.iaas.business.core.dto.RealizationNatureDTO;

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
 * RealizationNature REST Controller
 * Handles realization nature operations: create, get metadata, delete, get all
 * Based on exact RealizationNature model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/realizationNature")
@RequiredArgsConstructor
@Slf4j
public class RealizationNatureController {

    private final RealizationNatureService realizationNatureService;

    // ========== POST ONE REALIZATION NATURE ==========

    /**
     * Create new realization nature
     * Creates realization nature with multilingual designations and project classification
     */
    @PostMapping
    public ResponseEntity<RealizationNatureDTO> createRealizationNature(@Valid @RequestBody RealizationNatureDTO realizationNatureDTO) {
        log.info("Creating realization nature with French designation: {} and designations: AR={}, EN={}", 
                realizationNatureDTO.getDesignationFr(), realizationNatureDTO.getDesignationAr(), 
                realizationNatureDTO.getDesignationEn());
        
        RealizationNatureDTO createdRealizationNature = realizationNatureService.createRealizationNature(realizationNatureDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRealizationNature);
    }

    // ========== GET METADATA ==========

    /**
     * Get realization nature metadata by ID
     * Returns realization nature information with project classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<RealizationNatureDTO> getRealizationNatureMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for realization nature ID: {}", id);
        
        RealizationNatureDTO realizationNatureMetadata = realizationNatureService.getRealizationNatureById(id);
        
        return ResponseEntity.ok(realizationNatureMetadata);
    }

    /**
     * Get realization nature by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<RealizationNatureDTO> getRealizationNatureByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting realization nature by French designation: {}", designationFr);
        
        return realizationNatureService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get realization nature by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<RealizationNatureDTO> getRealizationNatureByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting realization nature by Arabic designation: {}", designationAr);
        
        return realizationNatureService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get realization nature by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<RealizationNatureDTO> getRealizationNatureByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting realization nature by English designation: {}", designationEn);
        
        return realizationNatureService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete realization nature by ID
     * Removes realization nature from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRealizationNature(@PathVariable Long id) {
        log.info("Deleting realization nature with ID: {}", id);
        
        realizationNatureService.deleteRealizationNature(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all realization natures with pagination
     * Returns list of all realization natures ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<RealizationNatureDTO>> getAllRealizationNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all realization natures - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getAllRealizationNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search realization natures by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<RealizationNatureDTO>> searchRealizationNatures(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching realization natures with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.searchRealizationNatures(query, pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    // ========== NATURE CATEGORY ENDPOINTS ==========

    /**
     * Get multilingual realization natures
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<RealizationNatureDTO>> getMultilingualRealizationNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual realization natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getMultilingualRealizationNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get infrastructure natures
     */
    @GetMapping("/infrastructure")
    public ResponseEntity<Page<RealizationNatureDTO>> getInfrastructureNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting infrastructure natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getInfrastructureNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get technology natures
     */
    @GetMapping("/technology")
    public ResponseEntity<Page<RealizationNatureDTO>> getTechnologyNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technology natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getTechnologyNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get service natures
     */
    @GetMapping("/services")
    public ResponseEntity<Page<RealizationNatureDTO>> getServiceNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting service natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getServiceNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get manufacturing natures
     */
    @GetMapping("/manufacturing")
    public ResponseEntity<Page<RealizationNatureDTO>> getManufacturingNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting manufacturing natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getManufacturingNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get research and development natures
     */
    @GetMapping("/research-development")
    public ResponseEntity<Page<RealizationNatureDTO>> getResearchDevelopmentNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting research and development natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getResearchDevelopmentNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get energy and utilities natures
     */
    @GetMapping("/energy-utilities")
    public ResponseEntity<Page<RealizationNatureDTO>> getEnergyUtilitiesNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting energy and utilities natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getEnergyUtilitiesNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get environmental natures
     */
    @GetMapping("/environmental")
    public ResponseEntity<Page<RealizationNatureDTO>> getEnvironmentalNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting environmental natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getEnvironmentalNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get commercial natures
     */
    @GetMapping("/commercial")
    public ResponseEntity<Page<RealizationNatureDTO>> getCommercialNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commercial natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getCommercialNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get education natures
     */
    @GetMapping("/education")
    public ResponseEntity<Page<RealizationNatureDTO>> getEducationNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting education natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getEducationNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get health and medical natures
     */
    @GetMapping("/health-medical")
    public ResponseEntity<Page<RealizationNatureDTO>> getHealthMedicalNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting health and medical natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getHealthMedicalNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get transportation natures
     */
    @GetMapping("/transportation")
    public ResponseEntity<Page<RealizationNatureDTO>> getTransportationNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting transportation natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getTransportationNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get agricultural natures
     */
    @GetMapping("/agricultural")
    public ResponseEntity<Page<RealizationNatureDTO>> getAgriculturalNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting agricultural natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getAgriculturalNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    // ========== COMPLEXITY AND CLASSIFICATION ENDPOINTS ==========

    /**
     * Get high complexity natures
     */
    @GetMapping("/high-complexity")
    public ResponseEntity<Page<RealizationNatureDTO>> getHighComplexityNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high complexity natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getHighComplexityNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get public interest natures
     */
    @GetMapping("/public-interest")
    public ResponseEntity<Page<RealizationNatureDTO>> getPublicInterestNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting public interest natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getPublicInterestNatures(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get natures requiring environmental assessment
     */
    @GetMapping("/environmental-assessment-required")
    public ResponseEntity<Page<RealizationNatureDTO>> getNaturesRequiringEnvironmentalAssessment(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting natures requiring environmental assessment");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getNaturesRequiringEnvironmentalAssessment(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    /**
     * Get natures requiring technical expertise
     */
    @GetMapping("/technical-expertise-required")
    public ResponseEntity<Page<RealizationNatureDTO>> getNaturesRequiringTechnicalExpertise(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting natures requiring technical expertise");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationNatureDTO> realizationNatures = realizationNatureService.getNaturesRequiringTechnicalExpertise(pageable);
        
        return ResponseEntity.ok(realizationNatures);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update realization nature metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<RealizationNatureDTO> updateRealizationNature(
            @PathVariable Long id,
            @Valid @RequestBody RealizationNatureDTO realizationNatureDTO) {
        
        log.info("Updating realization nature with ID: {}", id);
        
        RealizationNatureDTO updatedRealizationNature = realizationNatureService.updateRealizationNature(id, realizationNatureDTO);
        
        return ResponseEntity.ok(updatedRealizationNature);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if realization nature exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkRealizationNatureExists(@PathVariable Long id) {
        log.debug("Checking existence of realization nature ID: {}", id);
        
        boolean exists = realizationNatureService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if realization nature exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkRealizationNatureExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = realizationNatureService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of realization natures
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getRealizationNaturesCount() {
        log.debug("Getting total count of realization natures");
        
        Long count = realizationNatureService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of infrastructure natures
     */
    @GetMapping("/count/infrastructure")
    public ResponseEntity<Long> getInfrastructureNaturesCount() {
        log.debug("Getting count of infrastructure natures");
        
        Long count = realizationNatureService.getInfrastructureCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of technology natures
     */
    @GetMapping("/count/technology")
    public ResponseEntity<Long> getTechnologyNaturesCount() {
        log.debug("Getting count of technology natures");
        
        Long count = realizationNatureService.getTechnologyCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of service natures
     */
    @GetMapping("/count/services")
    public ResponseEntity<Long> getServiceNaturesCount() {
        log.debug("Getting count of service natures");
        
        Long count = realizationNatureService.getServiceCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get realization nature info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<RealizationNatureInfoResponse> getRealizationNatureInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for realization nature ID: {}", id);
        
        try {
            return realizationNatureService.findOne(id)
                    .map(realizationNatureDTO -> {
                        RealizationNatureInfoResponse response = RealizationNatureInfoResponse.builder()
                                .realizationNatureMetadata(realizationNatureDTO)
                                .hasArabicDesignation(realizationNatureDTO.getDesignationAr() != null && !realizationNatureDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(realizationNatureDTO.getDesignationEn() != null && !realizationNatureDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(realizationNatureDTO.getDesignationFr() != null && !realizationNatureDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(realizationNatureDTO.isMultilingual())
                                .requiresEnvironmentalAssessment(realizationNatureDTO.requiresEnvironmentalAssessment())
                                .requiresTechnicalExpertise(realizationNatureDTO.requiresTechnicalExpertise())
                                .involvesPublicInterest(realizationNatureDTO.involvesPublicInterest())
                                .isValid(realizationNatureDTO.isValid())
                                .defaultDesignation(realizationNatureDTO.getDefaultDesignation())
                                .displayText(realizationNatureDTO.getDisplayText())
                                .natureCategory(realizationNatureDTO.getNatureCategory())
                                .complexityLevel(realizationNatureDTO.getComplexityLevel())
                                .durationCategory(realizationNatureDTO.getDurationCategory())
                                .stakeholderLevel(realizationNatureDTO.getStakeholderLevel())
                                .regulatoryCompliance(realizationNatureDTO.getRegulatoryCompliance())
                                .naturePriority(realizationNatureDTO.getNaturePriority())
                                .riskLevel(realizationNatureDTO.getRiskLevel())
                                .shortDisplay(realizationNatureDTO.getShortDisplay())
                                .fullDisplay(realizationNatureDTO.getFullDisplay())
                                .displayWithCategory(realizationNatureDTO.getDisplayWithCategory())
                                .availableLanguages(realizationNatureDTO.getAvailableLanguages())
                                .comparisonKey(realizationNatureDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting realization nature info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class RealizationNatureInfoResponse {
        private RealizationNatureDTO realizationNatureMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean requiresEnvironmentalAssessment;
        private Boolean requiresTechnicalExpertise;
        private Boolean involvesPublicInterest;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String natureCategory;
        private String complexityLevel;
        private String durationCategory;
        private String stakeholderLevel;
        private String regulatoryCompliance;
        private Integer naturePriority;
        private String riskLevel;
        private String shortDisplay;
        private String fullDisplay;
        private String displayWithCategory;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
