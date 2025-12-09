/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicDomainController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.service.EconomicDomainService;
import dz.mdn.iaas.business.provider.dto.EconomicDomainDTO;

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
 * Economic Domain REST Controller
 * Handles economic domain operations: create, get metadata, delete, get all
 * Based on exact EconomicDomain model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@RestController
@RequestMapping("/economicDomain")
@RequiredArgsConstructor
@Slf4j
public class EconomicDomainController {

    private final EconomicDomainService economicDomainService;

    // ========== POST ONE ECONOMIC DOMAIN ==========

    /**
     * Create new economic domain
     * Creates economic domain with multilingual designations for business sector classification
     */
    @PostMapping
    public ResponseEntity<EconomicDomainDTO> createEconomicDomain(@Valid @RequestBody EconomicDomainDTO economicDomainDTO) {
        log.info("Creating economic domain with French designation: {} and designations: AR={}, EN={}", 
        		economicDomainDTO.getCode(), economicDomainDTO.getDesignationFr(), economicDomainDTO.getDesignationAr(), 
                economicDomainDTO.getDesignationEn());
        
        EconomicDomainDTO createdEconomicDomain = economicDomainService.createEconomicDomain(economicDomainDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEconomicDomain);
    }

    // ========== GET METADATA ==========

    /**
     * Get economic domain metadata by ID
     * Returns economic domain information with multilingual designations and economic intelligence
     */
    @GetMapping("/{id}")
    public ResponseEntity<EconomicDomainDTO> getEconomicDomainMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for economic domain ID: {}", id);
        
        EconomicDomainDTO economicDomainMetadata = economicDomainService.getEconomicDomainById(id);
        
        return ResponseEntity.ok(economicDomainMetadata);
    }

    /**
     * Get economic domain by French designation (F_04) - unique field
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<EconomicDomainDTO> getEconomicDomainByCode(@PathVariable String code) {
        log.debug("Getting economic domain by Code: {}", code);
        
        return economicDomainService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic domain by French designation (F_04) - unique field
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<EconomicDomainDTO> getEconomicDomainByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting economic domain by French designation: {}", designationFr);
        
        return economicDomainService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic domain by Arabic designation (F_02)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<EconomicDomainDTO> getEconomicDomainByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting economic domain by Arabic designation: {}", designationAr);
        
        return economicDomainService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic domain by English designation (F_03)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<EconomicDomainDTO> getEconomicDomainByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting economic domain by English designation: {}", designationEn);
        
        return economicDomainService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete economic domain by ID
     * Removes economic domain from the business classification system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEconomicDomain(@PathVariable Long id) {
        log.info("Deleting economic domain with ID: {}", id);
        
        economicDomainService.deleteEconomicDomain(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all economic domains with pagination
     * Returns list of all economic domains ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<EconomicDomainDTO>> getAllEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all economic domains - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getAllEconomicDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search economic domains by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<EconomicDomainDTO>> searchEconomicDomains(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching economic domains with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<EconomicDomainDTO> economicDomains = economicDomainService.searchEconomicDomains(query, pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    // ========== ECONOMIC SECTOR CLASSIFICATION ENDPOINTS ==========

    /**
     * Get multilingual economic domains
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<EconomicDomainDTO>> getMultilingualEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getMultilingualEconomicDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get agriculture economic domains
     */
    @GetMapping("/agriculture")
    public ResponseEntity<Page<EconomicDomainDTO>> getAgricultureEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting agriculture economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getAgricultureDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get industry economic domains
     */
    @GetMapping("/industry")
    public ResponseEntity<Page<EconomicDomainDTO>> getIndustryEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting industry economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getIndustryDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get service economic domains
     */
    @GetMapping("/services")
    public ResponseEntity<Page<EconomicDomainDTO>> getServiceEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting service economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getServiceDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get energy economic domains
     */
    @GetMapping("/energy")
    public ResponseEntity<Page<EconomicDomainDTO>> getEnergyEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting energy economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getEnergyDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get technology economic domains
     */
    @GetMapping("/technology")
    public ResponseEntity<Page<EconomicDomainDTO>> getTechnologyEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technology economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getTechnologyDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get finance economic domains
     */
    @GetMapping("/finance")
    public ResponseEntity<Page<EconomicDomainDTO>> getFinanceEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting finance economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getFinanceDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    // ========== SPECIALIZED DOMAIN ENDPOINTS ==========

    /**
     * Get healthcare economic domains
     */
    @GetMapping("/healthcare")
    public ResponseEntity<Page<EconomicDomainDTO>> getHealthcareEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting healthcare economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getHealthcareDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get education economic domains
     */
    @GetMapping("/education")
    public ResponseEntity<Page<EconomicDomainDTO>> getEducationEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting education economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getEducationDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get tourism economic domains
     */
    @GetMapping("/tourism")
    public ResponseEntity<Page<EconomicDomainDTO>> getTourismEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting tourism economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getTourismDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get construction economic domains
     */
    @GetMapping("/construction")
    public ResponseEntity<Page<EconomicDomainDTO>> getConstructionEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting construction economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getConstructionDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get transport economic domains
     */
    @GetMapping("/transport")
    public ResponseEntity<Page<EconomicDomainDTO>> getTransportEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting transport economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getTransportDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get mining economic domains
     */
    @GetMapping("/mining")
    public ResponseEntity<Page<EconomicDomainDTO>> getMiningEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting mining economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getMiningDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    // ========== STRATEGIC CLASSIFICATION ENDPOINTS ==========

    /**
     * Get strategic economic domains
     */
    @GetMapping("/strategic")
    public ResponseEntity<Page<EconomicDomainDTO>> getStrategicEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting strategic economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getStrategicDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get export-oriented economic domains
     */
    @GetMapping("/export-oriented")
    public ResponseEntity<Page<EconomicDomainDTO>> getExportOrientedEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting export-oriented economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getExportOrientedDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    /**
     * Get high investment economic domains
     */
    @GetMapping("/high-investment")
    public ResponseEntity<Page<EconomicDomainDTO>> getHighInvestmentEconomicDomains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high investment economic domains");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getHighInvestmentDomains(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    // ========== ADMINISTRATIVE ENDPOINTS ==========

    /**
     * Get economic domains missing translations
     */
    @GetMapping("/missing-translations")
    public ResponseEntity<Page<EconomicDomainDTO>> getEconomicDomainsMissingTranslations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting economic domains missing translations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicDomainDTO> economicDomains = economicDomainService.getEconomicDomainsMissingTranslations(pageable);
        
        return ResponseEntity.ok(economicDomains);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update economic domain metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<EconomicDomainDTO> updateEconomicDomain(
            @PathVariable Long id,
            @Valid @RequestBody EconomicDomainDTO economicDomainDTO) {
        
        log.info("Updating economic domain with ID: {}", id);
        
        EconomicDomainDTO updatedEconomicDomain = economicDomainService.updateEconomicDomain(id, economicDomainDTO);
        
        return ResponseEntity.ok(updatedEconomicDomain);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if economic domain exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkEconomicDomainExists(@PathVariable Long id) {
        log.debug("Checking existence of economic domain ID: {}", id);
        
        boolean exists = economicDomainService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if economic domain exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkEconomicDomainExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = economicDomainService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of economic domains
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getEconomicDomainsCount() {
        log.debug("Getting total count of economic domains");
        
        Long count = economicDomainService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of primary sector domains
     */
    @GetMapping("/count/primary-sector")
    public ResponseEntity<Long> getPrimarySectorCount() {
        log.debug("Getting count of primary sector economic domains");
        
        Long count = economicDomainService.getPrimarySectorCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of secondary sector domains
     */
    @GetMapping("/count/secondary-sector")
    public ResponseEntity<Long> getSecondarySectorCount() {
        log.debug("Getting count of secondary sector economic domains");
        
        Long count = economicDomainService.getSecondarySectorCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of tertiary sector domains
     */
    @GetMapping("/count/tertiary-sector")
    public ResponseEntity<Long> getTertiarySectorCount() {
        log.debug("Getting count of tertiary sector economic domains");
        
        Long count = economicDomainService.getTertiarySectorCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of quaternary sector domains
     */
    @GetMapping("/count/quaternary-sector")
    public ResponseEntity<Long> getQuaternarySectorCount() {
        log.debug("Getting count of quaternary sector economic domains");
        
        Long count = economicDomainService.getQuaternarySectorCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of multilingual economic domains
     */
    @GetMapping("/count/multilingual")
    public ResponseEntity<Long> getMultilingualCount() {
        log.debug("Getting count of multilingual economic domains");
        
        Long count = economicDomainService.getMultilingualCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get economic domain info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<EconomicDomainInfoResponse> getEconomicDomainInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for economic domain ID: {}", id);
        
        try {
            return economicDomainService.findOne(id)
                    .map(economicDomainDTO -> {
                        EconomicDomainInfoResponse response = EconomicDomainInfoResponse.builder()
                                .economicDomainMetadata(economicDomainDTO)
                                .defaultDesignation(economicDomainDTO.getDefaultDesignation())
                                .displayText(economicDomainDTO.getDisplayText())
                                .isMultilingual(economicDomainDTO.isMultilingual())
                                .availableLanguages(economicDomainDTO.getAvailableLanguages())
                                .domainType(economicDomainDTO.getDomainType())
                                .economicSector(economicDomainDTO.getEconomicSector())
                                .domainPriority(economicDomainDTO.getDomainPriority())
                                .isStrategicDomain(economicDomainDTO.isStrategicDomain())
                                .isExportOriented(economicDomainDTO.isExportOriented())
                                .requiresHighInvestment(economicDomainDTO.requiresHighInvestment())
                                .businessLicenseCategory(economicDomainDTO.getBusinessLicenseCategory())
                                .shortDisplay(economicDomainDTO.getShortDisplay())
                                .fullDisplay(economicDomainDTO.getFullDisplay())
                                .comparisonKey(economicDomainDTO.getComparisonKey())
                                .displayWithType(economicDomainDTO.getDisplayWithType())
                                .displayWithSector(economicDomainDTO.getDisplayWithSector())
                                .regulatoryFramework(economicDomainDTO.getRegulatoryFramework())
                                .employmentImpact(economicDomainDTO.getEmploymentImpact())
                                .gdpContribution(economicDomainDTO.getGDPContribution())
                                .foreignInvestmentAttractiveness(economicDomainDTO.getForeignInvestmentAttractiveness())
                                .digitalizationReadiness(economicDomainDTO.getDigitalizationReadiness())
                                .environmentalImpact(economicDomainDTO.getEnvironmentalImpact())
                                .skillRequirements(economicDomainDTO.getSkillRequirements())
                                .marketCompetition(economicDomainDTO.getMarketCompetition())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting economic domain info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class EconomicDomainInfoResponse {
        private EconomicDomainDTO economicDomainMetadata;
        private String defaultDesignation;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String domainType;
        private String economicSector;
        private Integer domainPriority;
        private Boolean isStrategicDomain;
        private Boolean isExportOriented;
        private Boolean requiresHighInvestment;
        private String businessLicenseCategory;
        private String shortDisplay;
        private String fullDisplay;
        private String comparisonKey;
        private String displayWithType;
        private String displayWithSector;
        private String regulatoryFramework;
        private String employmentImpact;
        private String gdpContribution;
        private String foreignInvestmentAttractiveness;
        private String digitalizationReadiness;
        private String environmentalImpact;
        private String skillRequirements;
        private String marketCompetition;
    }
}