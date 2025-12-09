/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementNatureController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.ProcurementNatureService;
import dz.mdn.iaas.business.core.dto.ProcurementNatureDTO;

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
 * ProcurementNature REST Controller
 * Handles procurement nature operations: create, get metadata, delete, get all
 * Based on exact ProcurementNature model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/procurementNature")
@RequiredArgsConstructor
@Slf4j
public class ProcurementNatureController {

    private final ProcurementNatureService procurementNatureService;

    // ========== POST ONE REALIZATION NATURE ==========

    /**
     * Create new procurement nature
     * Creates procurement nature with multilingual designations and project classification
     */
    @PostMapping
    public ResponseEntity<ProcurementNatureDTO> createProcurementNature(@Valid @RequestBody ProcurementNatureDTO procurementNatureDTO) {
        log.info("Creating procurement nature with French designation: {} and designations: AR={}, EN={}", 
                procurementNatureDTO.getDesignationFr(), procurementNatureDTO.getDesignationAr(), 
                procurementNatureDTO.getDesignationEn());
        
        ProcurementNatureDTO createdProcurementNature = procurementNatureService.createProcurementNature(procurementNatureDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcurementNature);
    }

    // ========== GET METADATA ==========

    /**
     * Get procurement nature metadata by ID
     * Returns procurement nature information with project classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProcurementNatureDTO> getProcurementNatureMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for procurement nature ID: {}", id);
        
        ProcurementNatureDTO procurementNatureMetadata = procurementNatureService.getProcurementNatureById(id);
        
        return ResponseEntity.ok(procurementNatureMetadata);
    }

    /**
     * Get procurement nature by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ProcurementNatureDTO> getProcurementNatureByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting procurement nature by French designation: {}", designationFr);
        
        return procurementNatureService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get procurement nature by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<ProcurementNatureDTO> getProcurementNatureByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting procurement nature by Arabic designation: {}", designationAr);
        
        return procurementNatureService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get procurement nature by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<ProcurementNatureDTO> getProcurementNatureByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting procurement nature by English designation: {}", designationEn);
        
        return procurementNatureService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete procurement nature by ID
     * Removes procurement nature from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcurementNature(@PathVariable Long id) {
        log.info("Deleting procurement nature with ID: {}", id);
        
        procurementNatureService.deleteProcurementNature(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all procurement natures with pagination
     * Returns list of all procurement natures ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ProcurementNatureDTO>> getAllProcurementNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all procurement natures - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getAllProcurementNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search procurement natures by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProcurementNatureDTO>> searchProcurementNatures(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching procurement natures with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.searchProcurementNatures(query, pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    // ========== NATURE CATEGORY ENDPOINTS ==========

    /**
     * Get multilingual procurement natures
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ProcurementNatureDTO>> getMultilingualProcurementNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual procurement natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getMultilingualProcurementNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get infrastructure natures
     */
    @GetMapping("/infrastructure")
    public ResponseEntity<Page<ProcurementNatureDTO>> getInfrastructureNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting infrastructure natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getInfrastructureNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get technology natures
     */
    @GetMapping("/technology")
    public ResponseEntity<Page<ProcurementNatureDTO>> getTechnologyNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technology natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getTechnologyNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get service natures
     */
    @GetMapping("/services")
    public ResponseEntity<Page<ProcurementNatureDTO>> getServiceNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting service natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getServiceNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get manufacturing natures
     */
    @GetMapping("/manufacturing")
    public ResponseEntity<Page<ProcurementNatureDTO>> getManufacturingNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting manufacturing natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getManufacturingNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get research and development natures
     */
    @GetMapping("/research-development")
    public ResponseEntity<Page<ProcurementNatureDTO>> getResearchDevelopmentNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting research and development natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getResearchDevelopmentNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get energy and utilities natures
     */
    @GetMapping("/energy-utilities")
    public ResponseEntity<Page<ProcurementNatureDTO>> getEnergyUtilitiesNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting energy and utilities natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getEnergyUtilitiesNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get environmental natures
     */
    @GetMapping("/environmental")
    public ResponseEntity<Page<ProcurementNatureDTO>> getEnvironmentalNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting environmental natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getEnvironmentalNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get commercial natures
     */
    @GetMapping("/commercial")
    public ResponseEntity<Page<ProcurementNatureDTO>> getCommercialNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commercial natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getCommercialNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get education natures
     */
    @GetMapping("/education")
    public ResponseEntity<Page<ProcurementNatureDTO>> getEducationNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting education natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getEducationNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get health and medical natures
     */
    @GetMapping("/health-medical")
    public ResponseEntity<Page<ProcurementNatureDTO>> getHealthMedicalNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting health and medical natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getHealthMedicalNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get transportation natures
     */
    @GetMapping("/transportation")
    public ResponseEntity<Page<ProcurementNatureDTO>> getTransportationNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting transportation natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getTransportationNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get agricultural natures
     */
    @GetMapping("/agricultural")
    public ResponseEntity<Page<ProcurementNatureDTO>> getAgriculturalNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting agricultural natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getAgriculturalNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    // ========== COMPLEXITY AND CLASSIFICATION ENDPOINTS ==========

    /**
     * Get high complexity natures
     */
    @GetMapping("/high-complexity")
    public ResponseEntity<Page<ProcurementNatureDTO>> getHighComplexityNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high complexity natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getHighComplexityNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get public interest natures
     */
    @GetMapping("/public-interest")
    public ResponseEntity<Page<ProcurementNatureDTO>> getPublicInterestNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting public interest natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getPublicInterestNatures(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get natures requiring environmental assessment
     */
    @GetMapping("/environmental-assessment-required")
    public ResponseEntity<Page<ProcurementNatureDTO>> getNaturesRequiringEnvironmentalAssessment(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting natures requiring environmental assessment");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getNaturesRequiringEnvironmentalAssessment(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    /**
     * Get natures requiring technical expertise
     */
    @GetMapping("/technical-expertise-required")
    public ResponseEntity<Page<ProcurementNatureDTO>> getNaturesRequiringTechnicalExpertise(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting natures requiring technical expertise");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementNatureDTO> procurementNatures = procurementNatureService.getNaturesRequiringTechnicalExpertise(pageable);
        
        return ResponseEntity.ok(procurementNatures);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update procurement nature metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProcurementNatureDTO> updateProcurementNature(
            @PathVariable Long id,
            @Valid @RequestBody ProcurementNatureDTO procurementNatureDTO) {
        
        log.info("Updating procurement nature with ID: {}", id);
        
        ProcurementNatureDTO updatedProcurementNature = procurementNatureService.updateProcurementNature(id, procurementNatureDTO);
        
        return ResponseEntity.ok(updatedProcurementNature);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if procurement nature exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkProcurementNatureExists(@PathVariable Long id) {
        log.debug("Checking existence of procurement nature ID: {}", id);
        
        boolean exists = procurementNatureService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if procurement nature exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkProcurementNatureExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = procurementNatureService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of procurement natures
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getProcurementNaturesCount() {
        log.debug("Getting total count of procurement natures");
        
        Long count = procurementNatureService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of infrastructure natures
     */
    @GetMapping("/count/infrastructure")
    public ResponseEntity<Long> getInfrastructureNaturesCount() {
        log.debug("Getting count of infrastructure natures");
        
        Long count = procurementNatureService.getInfrastructureCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of technology natures
     */
    @GetMapping("/count/technology")
    public ResponseEntity<Long> getTechnologyNaturesCount() {
        log.debug("Getting count of technology natures");
        
        Long count = procurementNatureService.getTechnologyCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of service natures
     */
    @GetMapping("/count/services")
    public ResponseEntity<Long> getServiceNaturesCount() {
        log.debug("Getting count of service natures");
        
        Long count = procurementNatureService.getServiceCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get procurement nature info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ProcurementNatureInfoResponse> getProcurementNatureInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for procurement nature ID: {}", id);
        
        try {
            return procurementNatureService.findOne(id)
                    .map(procurementNatureDTO -> {
                        ProcurementNatureInfoResponse response = ProcurementNatureInfoResponse.builder()
                                .procurementNatureMetadata(procurementNatureDTO)
                                .hasArabicDesignation(procurementNatureDTO.getDesignationAr() != null && !procurementNatureDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(procurementNatureDTO.getDesignationEn() != null && !procurementNatureDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(procurementNatureDTO.getDesignationFr() != null && !procurementNatureDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(procurementNatureDTO.isMultilingual())
                                .requiresEnvironmentalAssessment(procurementNatureDTO.requiresEnvironmentalAssessment())
                                .requiresTechnicalExpertise(procurementNatureDTO.requiresTechnicalExpertise())
                                .involvesPublicInterest(procurementNatureDTO.involvesPublicInterest())
                                .isValid(procurementNatureDTO.isValid())
                                .defaultDesignation(procurementNatureDTO.getDefaultDesignation())
                                .displayText(procurementNatureDTO.getDisplayText())
                                .natureCategory(procurementNatureDTO.getNatureCategory())
                                .complexityLevel(procurementNatureDTO.getComplexityLevel())
                                .durationCategory(procurementNatureDTO.getDurationCategory())
                                .stakeholderLevel(procurementNatureDTO.getStakeholderLevel())
                                .regulatoryCompliance(procurementNatureDTO.getRegulatoryCompliance())
                                .naturePriority(procurementNatureDTO.getNaturePriority())
                                .riskLevel(procurementNatureDTO.getRiskLevel())
                                .shortDisplay(procurementNatureDTO.getShortDisplay())
                                .fullDisplay(procurementNatureDTO.getFullDisplay())
                                .displayWithCategory(procurementNatureDTO.getDisplayWithCategory())
                                .availableLanguages(procurementNatureDTO.getAvailableLanguages())
                                .comparisonKey(procurementNatureDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting procurement nature info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ProcurementNatureInfoResponse {
        private ProcurementNatureDTO procurementNatureMetadata;
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
