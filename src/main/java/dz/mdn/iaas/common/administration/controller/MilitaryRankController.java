/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRankController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.MilitaryRankService;
import dz.mdn.iaas.common.administration.dto.MilitaryRankDTO;

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
 * Military Rank REST Controller
 * Handles military rank operations: create, get metadata, delete, get all
 * Based on exact MilitaryRank model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique), 
 * F_04=abbreviationAr, F_05=abbreviationEn, F_06=abbreviationFr, F_07=militaryCategoryId
 */
@RestController
@RequestMapping("/militaryRank")
@RequiredArgsConstructor
@Slf4j
public class MilitaryRankController {

    private final MilitaryRankService militaryRankService;

    // ========== POST ONE MILITARY RANK ==========

    /**
     * Create new military rank
     * Creates military rank with multilingual support and military hierarchy validation
     */
    @PostMapping
    public ResponseEntity<MilitaryRankDTO> createMilitaryRank(@Valid @RequestBody MilitaryRankDTO militaryRankDTO) {
        log.info("Creating military rank with French designation: {}, military category ID: {}", 
                militaryRankDTO.getDesignationFr(), militaryRankDTO.getMilitaryCategoryId());
        
        MilitaryRankDTO createdMilitaryRank = militaryRankService.createMilitaryRank(militaryRankDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMilitaryRank);
    }

    // ========== GET METADATA ==========

    /**
     * Get military rank metadata by ID
     * Returns military rank information with multilingual details and military hierarchy analysis
     */
    @GetMapping("/{id}")
    public ResponseEntity<MilitaryRankDTO> getMilitaryRankMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for military rank ID: {}", id);
        
        MilitaryRankDTO militaryRankMetadata = militaryRankService.getMilitaryRankById(id);
        
        return ResponseEntity.ok(militaryRankMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete military rank by ID
     * Removes military rank from the military hierarchy management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilitaryRank(@PathVariable Long id) {
        log.info("Deleting military rank with ID: {}", id);
        
        militaryRankService.deleteMilitaryRank(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all military ranks with pagination
     * Returns list of all military ranks ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<MilitaryRankDTO>> getAllMilitaryRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all military ranks - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getAllMilitaryRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    // ========== MILITARY CATEGORY SPECIFIC ENDPOINTS ==========

    /**
     * Get military ranks by military category
     */
    @GetMapping("/military-category/{militaryCategoryId}")
    public ResponseEntity<Page<MilitaryRankDTO>> getRanksByMilitaryCategory(
            @PathVariable Long militaryCategoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting military ranks for military category ID: {}", militaryCategoryId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getRanksByMilitaryCategory(militaryCategoryId, pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get all military ranks by military category (without pagination)
     */
    @GetMapping("/military-category/{militaryCategoryId}/all")
    public ResponseEntity<List<MilitaryRankDTO>> getAllRanksByMilitaryCategory(@PathVariable Long militaryCategoryId) {
        log.debug("Getting all military ranks for military category ID: {}", militaryCategoryId);
        
        List<MilitaryRankDTO> militaryRanks = militaryRankService.getAllRanksByMilitaryCategory(militaryCategoryId);
        
        return ResponseEntity.ok(militaryRanks);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search military ranks by designation (any language)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<MilitaryRankDTO>> searchRanksByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching military ranks by designation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.searchRanksByDesignation(query, pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Search military ranks by abbreviation (any language)
     */
    @GetMapping("/search/abbreviation")
    public ResponseEntity<Page<MilitaryRankDTO>> searchRanksByAbbreviation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching military ranks by abbreviation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.searchRanksByAbbreviation(query, pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Advanced search military ranks by any field
     */
    @GetMapping("/search/advanced")
    public ResponseEntity<Page<MilitaryRankDTO>> searchRanksByAnyField(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Advanced searching military ranks with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.searchRanksByAnyField(query, pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    // ========== HIERARCHY-BASED ENDPOINTS ==========

    /**
     * Get general officer ranks
     */
    @GetMapping("/hierarchy/general-officers")
    public ResponseEntity<Page<MilitaryRankDTO>> getGeneralOfficerRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting general officer ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getGeneralOfficerRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get senior officer ranks
     */
    @GetMapping("/hierarchy/senior-officers")
    public ResponseEntity<Page<MilitaryRankDTO>> getSeniorOfficerRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting senior officer ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getSeniorOfficerRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get company grade officer ranks
     */
    @GetMapping("/hierarchy/company-officers")
    public ResponseEntity<Page<MilitaryRankDTO>> getCompanyGradeOfficerRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting company grade officer ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getCompanyGradeOfficerRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get non-commissioned officer ranks
     */
    @GetMapping("/hierarchy/nco")
    public ResponseEntity<Page<MilitaryRankDTO>> getNonCommissionedOfficerRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting non-commissioned officer ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getNonCommissionedOfficerRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get enlisted ranks
     */
    @GetMapping("/hierarchy/enlisted")
    public ResponseEntity<Page<MilitaryRankDTO>> getEnlistedRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting enlisted ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getEnlistedRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get commissioned officer ranks
     */
    @GetMapping("/hierarchy/commissioned-officers")
    public ResponseEntity<Page<MilitaryRankDTO>> getCommissionedOfficerRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commissioned officer ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getCommissionedOfficerRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get command-eligible ranks
     */
    @GetMapping("/hierarchy/command-eligible")
    public ResponseEntity<Page<MilitaryRankDTO>> getCommandEligibleRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting command-eligible ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getCommandEligibleRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    // ========== SERVICE BRANCH ENDPOINTS ==========

    /**
     * Get army ranks
     */
    @GetMapping("/branch/army")
    public ResponseEntity<Page<MilitaryRankDTO>> getArmyRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting army ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getArmyRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get navy ranks
     */
    @GetMapping("/branch/navy")
    public ResponseEntity<Page<MilitaryRankDTO>> getNavyRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting navy ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getNavyRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get air force ranks
     */
    @GetMapping("/branch/air-force")
    public ResponseEntity<Page<MilitaryRankDTO>> getAirForceRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting air force ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getAirForceRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    /**
     * Get gendarmerie ranks
     */
    @GetMapping("/branch/gendarmerie")
    public ResponseEntity<Page<MilitaryRankDTO>> getGendarmerieRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting gendarmerie ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getGendarmerieRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    // ========== LANGUAGE SPECIFIC ENDPOINTS ==========

    /**
     * Get multilingual military ranks
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<MilitaryRankDTO>> getMultilingualRanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual military ranks");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryRankDTO> militaryRanks = militaryRankService.getMultilingualRanks(pageable);
        
        return ResponseEntity.ok(militaryRanks);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find military rank by French designation (unique)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<MilitaryRankDTO> getRankByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting military rank by French designation: {}", designationFr);
        
        return militaryRankService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update military rank metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<MilitaryRankDTO> updateMilitaryRank(
            @PathVariable Long id,
            @Valid @RequestBody MilitaryRankDTO militaryRankDTO) {
        
        log.info("Updating military rank with ID: {}", id);
        
        MilitaryRankDTO updatedMilitaryRank = militaryRankService.updateMilitaryRank(id, militaryRankDTO);
        
        return ResponseEntity.ok(updatedMilitaryRank);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if military rank exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkMilitaryRankExists(@PathVariable Long id) {
        log.debug("Checking existence of military rank ID: {}", id);
        
        boolean exists = militaryRankService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if French designation exists
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkDesignationFrExists(@PathVariable String designationFr) {
        log.debug("Checking if French designation exists: {}", designationFr);
        
        boolean exists = militaryRankService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of military ranks by military category
     */
    @GetMapping("/military-category/{militaryCategoryId}/count")
    public ResponseEntity<Long> countRanksByMilitaryCategory(@PathVariable Long militaryCategoryId) {
        log.debug("Getting count of military ranks for military category ID: {}", militaryCategoryId);
        
        Long count = militaryRankService.countRanksByMilitaryCategory(militaryCategoryId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of all military ranks
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllMilitaryRanks() {
        log.debug("Getting count of all military ranks");
        
        Long count = militaryRankService.countAllMilitaryRanks();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of general officer ranks
     */
    @GetMapping("/count/general-officers")
    public ResponseEntity<Long> countGeneralOfficerRanks() {
        log.debug("Getting count of general officer ranks");
        
        Long count = militaryRankService.countGeneralOfficerRanks();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of commissioned officer ranks
     */
    @GetMapping("/count/commissioned-officers")
    public ResponseEntity<Long> countCommissionedOfficerRanks() {
        log.debug("Getting count of commissioned officer ranks");
        
        Long count = militaryRankService.countCommissionedOfficerRanks();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get military rank info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<MilitaryRankInfoResponse> getMilitaryRankInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for military rank ID: {}", id);
        
        try {
            return militaryRankService.findOne(id)
                    .map(militaryRankDTO -> {
                        MilitaryRankInfoResponse response = MilitaryRankInfoResponse.builder()
                                .militaryRankMetadata(militaryRankDTO)
                                .defaultDesignation(militaryRankDTO.getDefaultDesignation())
                                .defaultAbbreviation(militaryRankDTO.getDefaultAbbreviation())
                                .displayText(militaryRankDTO.getDisplayText())
                                .displayAbbreviation(militaryRankDTO.getDisplayAbbreviation())
                                .isMultilingual(militaryRankDTO.isMultilingual())
                                .availableLanguages(militaryRankDTO.getAvailableLanguages())
                                .rankLevel(militaryRankDTO.getRankLevel())
                                .rankPrecedence(militaryRankDTO.getRankPrecedence())
                                .serviceBranch(militaryRankDTO.getServiceBranch())
                                .authorityLevel(militaryRankDTO.getAuthorityLevel())
                                .canCommandUnits(militaryRankDTO.canCommandUnits())
                                .isCommissionedOfficer(militaryRankDTO.isCommissionedOfficer())
                                .shortDisplay(militaryRankDTO.getShortDisplay())
                                .fullDisplay(militaryRankDTO.getFullDisplay())
                                .militaryDisplay(militaryRankDTO.getMilitaryDisplay())
                                .formalDisplay(militaryRankDTO.getFormalDisplay())
                                .rankClassification(militaryRankDTO.getRankClassification())
                                .promotionEligibility(militaryRankDTO.getPromotionEligibility())
                                .responsibilityScope(militaryRankDTO.getResponsibilityScope())
                                .typicalUnitCommand(militaryRankDTO.getTypicalUnitCommand())
                                .civilianEquivalent(militaryRankDTO.getCivilianEquivalent())
                                .serviceRequirement(militaryRankDTO.getServiceRequirement())
                                .requiresAcademyGraduation(militaryRankDTO.requiresAcademyGraduation())
                                .securityClearanceLevel(militaryRankDTO.getSecurityClearanceLevel())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting military rank info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class MilitaryRankInfoResponse {
        private MilitaryRankDTO militaryRankMetadata;
        private String defaultDesignation;
        private String defaultAbbreviation;
        private String displayText;
        private String displayAbbreviation;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String rankLevel;
        private Integer rankPrecedence;
        private String serviceBranch;
        private String authorityLevel;
        private Boolean canCommandUnits;
        private Boolean isCommissionedOfficer;
        private String shortDisplay;
        private String fullDisplay;
        private String militaryDisplay;
        private String formalDisplay;
        private String rankClassification;
        private String promotionEligibility;
        private String responsibilityScope;
        private String typicalUnitCommand;
        private String civilianEquivalent;
        private String serviceRequirement;
        private Boolean requiresAcademyGraduation;
        private String securityClearanceLevel;
    }
}