/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicNatureController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.service.EconomicNatureService;
import dz.mdn.iaas.business.provider.dto.EconomicNatureDTO;

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
 * Economic Nature REST Controller
 * Handles economic nature operations: create, get metadata, delete, get all
 * Based on exact EconomicNature model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_01, F_02, F_04, F_05 are optional
 */
@RestController
@RequestMapping("/economicNature")
@RequiredArgsConstructor
@Slf4j
public class EconomicNatureController {

    private final EconomicNatureService economicNatureService;

    // ========== POST ONE ECONOMIC NATURE ==========

    /**
     * Create new economic nature
     * Creates economic nature with multilingual designations and acronyms for legal entity classification
     */
    @PostMapping
    public ResponseEntity<EconomicNatureDTO> createEconomicNature(@Valid @RequestBody EconomicNatureDTO economicNatureDTO) {
        log.info("Creating economic nature with French designation: {} and designations: AR={}, EN={}, Acronyms: FR={}, AR={}, EN={}", 
                economicNatureDTO.getDesignationFr(), economicNatureDTO.getDesignationAr(), 
                economicNatureDTO.getDesignationEn(), economicNatureDTO.getAcronymFr(),
                economicNatureDTO.getAcronymAr(), economicNatureDTO.getAcronymEn());
        
        EconomicNatureDTO createdEconomicNature = economicNatureService.createEconomicNature(economicNatureDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEconomicNature);
    }

    // ========== GET METADATA ==========

    /**
     * Get economic nature metadata by ID
     * Returns economic nature information with multilingual designations, acronyms, and legal structure intelligence
     */
    @GetMapping("/{id}")
    public ResponseEntity<EconomicNatureDTO> getEconomicNatureMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for economic nature ID: {}", id);
        
        EconomicNatureDTO economicNatureMetadata = economicNatureService.getEconomicNatureById(id);
        
        return ResponseEntity.ok(economicNatureMetadata);
    }

    /**
     * Get economic nature by French designation (F_03) - unique field
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<EconomicNatureDTO> getEconomicNatureByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting economic nature by French designation: {}", designationFr);
        
        return economicNatureService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic nature by French acronym (F_06) - unique field
     */
    @GetMapping("/acronym-fr/{acronymFr}")
    public ResponseEntity<EconomicNatureDTO> getEconomicNatureByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Getting economic nature by French acronym: {}", acronymFr);
        
        return economicNatureService.findByAcronymFr(acronymFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic nature by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<EconomicNatureDTO> getEconomicNatureByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting economic nature by Arabic designation: {}", designationAr);
        
        return economicNatureService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic nature by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<EconomicNatureDTO> getEconomicNatureByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting economic nature by English designation: {}", designationEn);
        
        return economicNatureService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic nature by Arabic acronym (F_04)
     */
    @GetMapping("/acronym-ar/{acronymAr}")
    public ResponseEntity<EconomicNatureDTO> getEconomicNatureByAcronymAr(@PathVariable String acronymAr) {
        log.debug("Getting economic nature by Arabic acronym: {}", acronymAr);
        
        return economicNatureService.findByAcronymAr(acronymAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get economic nature by English acronym (F_05)
     */
    @GetMapping("/acronym-en/{acronymEn}")
    public ResponseEntity<EconomicNatureDTO> getEconomicNatureByAcronymEn(@PathVariable String acronymEn) {
        log.debug("Getting economic nature by English acronym: {}", acronymEn);
        
        return economicNatureService.findByAcronymEn(acronymEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete economic nature by ID
     * Removes economic nature from the legal entity classification system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEconomicNature(@PathVariable Long id) {
        log.info("Deleting economic nature with ID: {}", id);
        
        economicNatureService.deleteEconomicNature(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all economic natures with pagination
     * Returns list of all economic natures ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<EconomicNatureDTO>> getAllEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all economic natures - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getAllEconomicNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get all economic natures ordered by French acronym
     */
    @GetMapping("/ordered-by-acronym")
    public ResponseEntity<Page<EconomicNatureDTO>> getAllEconomicNaturesOrderedByAcronym(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting all economic natures ordered by French acronym");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getAllEconomicNaturesOrderedByAcronym(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search economic natures by designation or acronym (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<EconomicNatureDTO>> searchEconomicNatures(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching economic natures with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<EconomicNatureDTO> economicNatures = economicNatureService.searchEconomicNatures(query, pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    // ========== LEGAL STRUCTURE CLASSIFICATION ENDPOINTS ==========

    /**
     * Get multilingual economic natures
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<EconomicNatureDTO>> getMultilingualEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getMultilingualEconomicNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get public sector economic natures
     */
    @GetMapping("/public-sector")
    public ResponseEntity<Page<EconomicNatureDTO>> getPublicSectorEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting public sector economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getPublicSectorNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get private sector economic natures
     */
    @GetMapping("/private-sector")
    public ResponseEntity<Page<EconomicNatureDTO>> getPrivateSectorEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting private sector economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getPrivateSectorNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get company economic natures
     */
    @GetMapping("/companies")
    public ResponseEntity<Page<EconomicNatureDTO>> getCompanyEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting company economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getCompanyNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get cooperative economic natures
     */
    @GetMapping("/cooperatives")
    public ResponseEntity<Page<EconomicNatureDTO>> getCooperativeEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting cooperative economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getCooperativeNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get association economic natures
     */
    @GetMapping("/associations")
    public ResponseEntity<Page<EconomicNatureDTO>> getAssociationEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting association economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getAssociationNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get individual enterprise economic natures
     */
    @GetMapping("/individual-enterprises")
    public ResponseEntity<Page<EconomicNatureDTO>> getIndividualEnterpriseEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting individual enterprise economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getIndividualEnterpriseNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get foreign entity economic natures
     */
    @GetMapping("/foreign-entities")
    public ResponseEntity<Page<EconomicNatureDTO>> getForeignEntityEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting foreign entity economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getForeignEntityNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    // ========== LIABILITY STRUCTURE ENDPOINTS ==========

    /**
     * Get limited liability economic natures
     */
    @GetMapping("/limited-liability")
    public ResponseEntity<Page<EconomicNatureDTO>> getLimitedLiabilityEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting limited liability economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getLimitedLiabilityNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get partnership economic natures
     */
    @GetMapping("/partnerships")
    public ResponseEntity<Page<EconomicNatureDTO>> getPartnershipEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting partnership economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getPartnershipNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    // ========== REGULATORY CLASSIFICATION ENDPOINTS ==========

    /**
     * Get government-related economic natures
     */
    @GetMapping("/government-related")
    public ResponseEntity<Page<EconomicNatureDTO>> getGovernmentRelatedEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting government-related economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getGovernmentRelatedNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get commercial economic natures
     */
    @GetMapping("/commercial")
    public ResponseEntity<Page<EconomicNatureDTO>> getCommercialEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commercial economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getCommercialNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    /**
     * Get economic natures with special registration requirements
     */
    @GetMapping("/special-registration")
    public ResponseEntity<Page<EconomicNatureDTO>> getSpecialRegistrationEconomicNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting special registration economic natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getSpecialRegistrationNatures(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    // ========== ADMINISTRATIVE ENDPOINTS ==========

    /**
     * Get economic natures missing translations
     */
    @GetMapping("/missing-translations")
    public ResponseEntity<Page<EconomicNatureDTO>> getEconomicNaturesMissingTranslations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting economic natures missing translations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<EconomicNatureDTO> economicNatures = economicNatureService.getEconomicNaturesMissingTranslations(pageable);
        
        return ResponseEntity.ok(economicNatures);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update economic nature metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<EconomicNatureDTO> updateEconomicNature(
            @PathVariable Long id,
            @Valid @RequestBody EconomicNatureDTO economicNatureDTO) {
        
        log.info("Updating economic nature with ID: {}", id);
        
        EconomicNatureDTO updatedEconomicNature = economicNatureService.updateEconomicNature(id, economicNatureDTO);
        
        return ResponseEntity.ok(updatedEconomicNature);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if economic nature exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkEconomicNatureExists(@PathVariable Long id) {
        log.debug("Checking existence of economic nature ID: {}", id);
        
        boolean exists = economicNatureService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if economic nature exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkEconomicNatureExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = economicNatureService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if economic nature exists by French acronym
     */
    @GetMapping("/exists/acronym-fr/{acronymFr}")
    public ResponseEntity<Boolean> checkEconomicNatureExistsByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Checking existence by French acronym: {}", acronymFr);
        
        boolean exists = economicNatureService.existsByAcronymFr(acronymFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of economic natures
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getEconomicNaturesCount() {
        log.debug("Getting total count of economic natures");
        
        Long count = economicNatureService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of public sector natures
     */
    @GetMapping("/count/public-sector")
    public ResponseEntity<Long> getPublicSectorNaturesCount() {
        log.debug("Getting count of public sector economic natures");
        
        Long count = economicNatureService.getPublicSectorNaturesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of private sector natures
     */
    @GetMapping("/count/private-sector")
    public ResponseEntity<Long> getPrivateSectorNaturesCount() {
        log.debug("Getting count of private sector economic natures");
        
        Long count = economicNatureService.getPrivateSectorNaturesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of cooperative natures
     */
    @GetMapping("/count/cooperatives")
    public ResponseEntity<Long> getCooperativeNaturesCount() {
        log.debug("Getting count of cooperative economic natures");
        
        Long count = economicNatureService.getCooperativeNaturesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of association natures
     */
    @GetMapping("/count/associations")
    public ResponseEntity<Long> getAssociationNaturesCount() {
        log.debug("Getting count of association economic natures");
        
        Long count = economicNatureService.getAssociationNaturesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of multilingual economic natures
     */
    @GetMapping("/count/multilingual")
    public ResponseEntity<Long> getMultilingualCount() {
        log.debug("Getting count of multilingual economic natures");
        
        Long count = economicNatureService.getMultilingualCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get economic nature info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<EconomicNatureInfoResponse> getEconomicNatureInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for economic nature ID: {}", id);
        
        try {
            return economicNatureService.findOne(id)
                    .map(economicNatureDTO -> {
                        EconomicNatureInfoResponse response = EconomicNatureInfoResponse.builder()
                                .economicNatureMetadata(economicNatureDTO)
                                .defaultDesignation(economicNatureDTO.getDefaultDesignation())
                                .defaultAcronym(economicNatureDTO.getDefaultAcronym())
                                .displayText(economicNatureDTO.getDisplayText())
                                .displayAcronym(economicNatureDTO.getDisplayAcronym())
                                .isMultilingual(economicNatureDTO.isMultilingual())
                                .availableLanguages(economicNatureDTO.getAvailableLanguages())
                                .natureType(economicNatureDTO.getNatureType())
                                .ownershipStructure(economicNatureDTO.getOwnershipStructure())
                                .legalFramework(economicNatureDTO.getLegalFramework())
                                .naturePriority(economicNatureDTO.getNaturePriority())
                                .isGovernmentRelated(economicNatureDTO.isGovernmentRelated())
                                .requiresSpecialRegistration(economicNatureDTO.requiresSpecialRegistration())
                                .hasLimitedLiability(economicNatureDTO.hasLimitedLiability())
                                .minimumCapitalCategory(economicNatureDTO.getMinimumCapitalCategory())
                                .taxationRegime(economicNatureDTO.getTaxationRegime())
                                .shortDisplay(economicNatureDTO.getShortDisplay())
                                .fullDisplay(economicNatureDTO.getFullDisplay())
                                .comparisonKey(economicNatureDTO.getComparisonKey())
                                .displayWithType(economicNatureDTO.getDisplayWithType())
                                .displayWithAcronymAndType(economicNatureDTO.getDisplayWithAcronymAndType())
                                .formalBusinessDisplay(economicNatureDTO.getFormalBusinessDisplay())
                                .regulatoryCompliance(economicNatureDTO.getRegulatoryCompliance())
                                .businessFlexibility(economicNatureDTO.getBusinessFlexibility())
                                .fundingAccess(economicNatureDTO.getFundingAccess())
                                .managementStructure(economicNatureDTO.getManagementStructure())
                                .profitDistribution(economicNatureDTO.getProfitDistribution())
                                .liabilityStructure(economicNatureDTO.getLiabilityStructure())
                                .registrationAuthority(economicNatureDTO.getRegistrationAuthority())
                                .dissolutionComplexity(economicNatureDTO.getDissolutionComplexity())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting economic nature info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class EconomicNatureInfoResponse {
        private EconomicNatureDTO economicNatureMetadata;
        private String defaultDesignation;
        private String defaultAcronym;
        private String displayText;
        private String displayAcronym;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String natureType;
        private String ownershipStructure;
        private String legalFramework;
        private Integer naturePriority;
        private Boolean isGovernmentRelated;
        private Boolean requiresSpecialRegistration;
        private Boolean hasLimitedLiability;
        private String minimumCapitalCategory;
        private String taxationRegime;
        private String shortDisplay;
        private String fullDisplay;
        private String comparisonKey;
        private String displayWithType;
        private String displayWithAcronymAndType;
        private String formalBusinessDisplay;
        private String regulatoryCompliance;
        private String businessFlexibility;
        private String fundingAccess;
        private String managementStructure;
        private String profitDistribution;
        private String liabilityStructure;
        private String registrationAuthority;
        private String dissolutionComplexity;
    }
}
