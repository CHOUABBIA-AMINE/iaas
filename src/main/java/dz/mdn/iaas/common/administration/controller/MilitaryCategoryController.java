/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryCategoryController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.MilitaryCategoryService;
import dz.mdn.iaas.common.administration.dto.MilitaryCategoryDTO;

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
 * Military Category REST Controller
 * Handles military category operations: create, get metadata, delete, get all
 * Based on exact MilitaryCategory model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=abbreviationAr, F_05=abbreviationEn, F_06=abbreviationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (abbreviationFr) is required
 * F_01, F_02, F_04, F_05 are optional
 */
@RestController
@RequestMapping("/militaryCategory")
@RequiredArgsConstructor
@Slf4j
public class MilitaryCategoryController {

    private final MilitaryCategoryService militaryCategoryService;

    // ========== POST ONE MILITARY CATEGORY ==========

    /**
     * Create new military category
     * Creates military category with multilingual designations and abbreviations
     */
    @PostMapping
    public ResponseEntity<MilitaryCategoryDTO> createMilitaryCategory(@Valid @RequestBody MilitaryCategoryDTO militaryCategoryDTO) {
        log.info("Creating military category with French designation: {} and designations: AR={}, EN={}, Abbreviations: FR={}, AR={}, EN={}", 
                militaryCategoryDTO.getDesignationFr(), militaryCategoryDTO.getDesignationAr(), 
                militaryCategoryDTO.getDesignationEn());
        
        MilitaryCategoryDTO createdMilitaryCategory = militaryCategoryService.createMilitaryCategory(militaryCategoryDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMilitaryCategory);
    }

    // ========== GET METADATA ==========

    /**
     * Get military category metadata by ID
     * Returns military category information with multilingual designations, abbreviations, and organizational intelligence
     */
    @GetMapping("/{id}")
    public ResponseEntity<MilitaryCategoryDTO> getMilitaryCategoryMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for military category ID: {}", id);
        
        MilitaryCategoryDTO militaryCategoryMetadata = militaryCategoryService.getMilitaryCategoryById(id);
        
        return ResponseEntity.ok(militaryCategoryMetadata);
    }

    /**
     * Get military category by French designation (F_03) - unique field
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<MilitaryCategoryDTO> getMilitaryCategoryByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting military category by French designation: {}", designationFr);
        
        return militaryCategoryService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get military category by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<MilitaryCategoryDTO> getMilitaryCategoryByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting military category by Arabic designation: {}", designationAr);
        
        return militaryCategoryService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get military category by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<MilitaryCategoryDTO> getMilitaryCategoryByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting military category by English designation: {}", designationEn);
        
        return militaryCategoryService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete military category by ID
     * Removes military category from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilitaryCategory(@PathVariable Long id) {
        log.info("Deleting military category with ID: {}", id);
        
        militaryCategoryService.deleteMilitaryCategory(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all military categories with pagination
     * Returns list of all military categories ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<MilitaryCategoryDTO>> getAllMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all military categories - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getAllMilitaryCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search military categories by designation or abbreviation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<MilitaryCategoryDTO>> searchMilitaryCategories(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching military categories with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.searchMilitaryCategories(query, pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    // ========== MILITARY SERVICE CLASSIFICATION ENDPOINTS ==========

    /**
     * Get multilingual military categories
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getMultilingualMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getMultilingualMilitaryCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get main service branches (Army, Navy, Air Force)
     */
    @GetMapping("/main-service-branches")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getMainServiceBranches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting main service branch military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getMainServiceBranches(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get army military categories
     */
    @GetMapping("/army")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getArmyMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting army military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getArmyCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get navy military categories
     */
    @GetMapping("/navy")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getNavyMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting navy military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getNavyCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get air force military categories
     */
    @GetMapping("/air-force")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getAirForceMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting air force military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getAirForceCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get gendarmerie military categories
     */
    @GetMapping("/gendarmerie")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getGendarmerieMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting gendarmerie military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getGendarmerieCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get security military categories
     */
    @GetMapping("/security")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getSecurityMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting security military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getSecurityCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get support military categories
     */
    @GetMapping("/support")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getSupportMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting support military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getSupportCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    // ========== SPECIALIZED SERVICE ENDPOINTS ==========

    /**
     * Get intelligence military categories
     */
    @GetMapping("/intelligence")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getIntelligenceMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting intelligence military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getIntelligenceCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get medical military categories
     */
    @GetMapping("/medical")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getMedicalMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting medical military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getMedicalCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get logistics military categories
     */
    @GetMapping("/logistics")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getLogisticsMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting logistics military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getLogisticsCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get communications military categories
     */
    @GetMapping("/communications")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getCommunicationsMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting communications military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getCommunicationsCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    /**
     * Get republican guard military categories
     */
    @GetMapping("/republican-guard")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getRepublicanGuardMilitaryCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting republican guard military categories");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getRepublicanGuardCategories(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    // ========== ADMINISTRATIVE ENDPOINTS ==========

    /**
     * Get military categories missing translations
     */
    @GetMapping("/missing-translations")
    public ResponseEntity<Page<MilitaryCategoryDTO>> getMilitaryCategoriesMissingTranslations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting military categories missing translations");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MilitaryCategoryDTO> militaryCategories = militaryCategoryService.getMilitaryCategoriesMissingTranslations(pageable);
        
        return ResponseEntity.ok(militaryCategories);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update military category metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<MilitaryCategoryDTO> updateMilitaryCategory(
            @PathVariable Long id,
            @Valid @RequestBody MilitaryCategoryDTO militaryCategoryDTO) {
        
        log.info("Updating military category with ID: {}", id);
        
        MilitaryCategoryDTO updatedMilitaryCategory = militaryCategoryService.updateMilitaryCategory(id, militaryCategoryDTO);
        
        return ResponseEntity.ok(updatedMilitaryCategory);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if military category exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkMilitaryCategoryExists(@PathVariable Long id) {
        log.debug("Checking existence of military category ID: {}", id);
        
        boolean exists = militaryCategoryService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if military category exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkMilitaryCategoryExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = militaryCategoryService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of military categories
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getMilitaryCategoriesCount() {
        log.debug("Getting total count of military categories");
        
        Long count = militaryCategoryService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of main service branches
     */
    @GetMapping("/count/main-service-branches")
    public ResponseEntity<Long> getMainServiceBranchesCount() {
        log.debug("Getting count of main service branch military categories");
        
        Long count = militaryCategoryService.getMainServiceBranchesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of security services
     */
    @GetMapping("/count/security-services")
    public ResponseEntity<Long> getSecurityServicesCount() {
        log.debug("Getting count of security service military categories");
        
        Long count = militaryCategoryService.getSecurityServicesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of support services
     */
    @GetMapping("/count/support-services")
    public ResponseEntity<Long> getSupportServicesCount() {
        log.debug("Getting count of support service military categories");
        
        Long count = militaryCategoryService.getSupportServicesCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of multilingual military categories
     */
    @GetMapping("/count/multilingual")
    public ResponseEntity<Long> getMultilingualCount() {
        log.debug("Getting count of multilingual military categories");
        
        Long count = militaryCategoryService.getMultilingualCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get military category info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<MilitaryCategoryInfoResponse> getMilitaryCategoryInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for military category ID: {}", id);
        
        try {
            return militaryCategoryService.findOne(id)
                    .map(militaryCategoryDTO -> {
                        MilitaryCategoryInfoResponse response = MilitaryCategoryInfoResponse.builder()
                                .militaryCategoryMetadata(militaryCategoryDTO)
                                .defaultDesignation(militaryCategoryDTO.getDefaultDesignation())
                                .displayText(militaryCategoryDTO.getDisplayText())
                                .isMultilingual(militaryCategoryDTO.isMultilingual())
                                .availableLanguages(militaryCategoryDTO.getAvailableLanguages())
                                .categoryType(militaryCategoryDTO.getCategoryType())
                                .categoryPriority(militaryCategoryDTO.getCategoryPriority())
                                .isMainServiceBranch(militaryCategoryDTO.isMainServiceBranch())
                                .isSupportService(militaryCategoryDTO.isSupportService())
                                .isSecurityService(militaryCategoryDTO.isSecurityService())
                                .organizationalLevel(militaryCategoryDTO.getOrganizationalLevel())
                                .fullDisplay(militaryCategoryDTO.getFullDisplay())
                                .comparisonKey(militaryCategoryDTO.getComparisonKey())
                                .displayWithType(militaryCategoryDTO.getDisplayWithType())
                                .formalMilitaryDisplay(militaryCategoryDTO.getFormalMilitaryDisplay())
                                .commandStructureLevel(militaryCategoryDTO.getCommandStructureLevel())
                                .personnelSizeCategory(militaryCategoryDTO.getPersonnelSizeCategory())
                                .deploymentType(militaryCategoryDTO.getDeploymentType())
                                .headquartersType(militaryCategoryDTO.getHeadquartersType())
                                .recruitmentProfile(militaryCategoryDTO.getRecruitmentProfile())
                                .trainingEmphasis(militaryCategoryDTO.getTrainingEmphasis())
                                .categoryDescription(militaryCategoryDTO.getCategoryDescription())
                                .uniformColorScheme(militaryCategoryDTO.getUniformColorScheme())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting military category info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class MilitaryCategoryInfoResponse {
        private MilitaryCategoryDTO militaryCategoryMetadata;
        private String defaultDesignation;
        private String displayText;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String code;
        private String categoryType;
        private Integer categoryPriority;
        private Boolean isMainServiceBranch;
        private Boolean isSupportService;
        private Boolean isSecurityService;
        private String organizationalLevel;
        private String fullDisplay;
        private String comparisonKey;
        private String displayWithType;
        private String formalMilitaryDisplay;
        private String commandStructureLevel;
        private String personnelSizeCategory;
        private String deploymentType;
        private String headquartersType;
        private String recruitmentProfile;
        private String trainingEmphasis;
        private String categoryDescription;
        private String uniformColorScheme;
    }
}