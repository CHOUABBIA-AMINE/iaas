/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentPhaseController
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import dz.mdn.iaas.business.amendment.service.AmendmentPhaseService;
import dz.mdn.iaas.business.amendment.dto.AmendmentPhaseDTO;
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
 * AmendmentPhase REST Controller
 * Handles amendment phase operations: post one, get metadata, delete one, get all
 * Based on exact AmendmentPhase model: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr
 * Required field: F_03 (designationFr) with unique constraint
 */
@RestController
@RequestMapping("/amendmentPhase")
@RequiredArgsConstructor
@Slf4j
public class AmendmentPhaseController {

    private final AmendmentPhaseService amendmentPhaseService;

    // ========== POST ONE CONSULTATION PHASE ==========

    /**
     * Create new amendment phase
     * Creates amendment phase with multilingual designations
     */
    @PostMapping
    public ResponseEntity<AmendmentPhaseDTO> createAmendmentPhase(@Valid @RequestBody AmendmentPhaseDTO amendmentPhaseDTO) {
        log.info("Creating amendment phase with French designation: {}", 
                amendmentPhaseDTO.getDesignationFr());

        AmendmentPhaseDTO createdAmendmentPhase = amendmentPhaseService.createAmendmentPhase(amendmentPhaseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAmendmentPhase);
    }

    // ========== GET METADATA ==========

    /**
     * Get amendment phase metadata by ID
     * Returns amendment phase information with all designations
     */
    @GetMapping("/{id}")
    public ResponseEntity<AmendmentPhaseDTO> getAmendmentPhaseMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for amendment phase ID: {}", id);

        AmendmentPhaseDTO amendmentPhaseMetadata = amendmentPhaseService.getAmendmentPhaseById(id);

        return ResponseEntity.ok(amendmentPhaseMetadata);
    }

    /**
     * Get amendment phase metadata by ID with amendment steps info
     */
    @GetMapping("/{id}/with-steps")
    public ResponseEntity<AmendmentPhaseDTO> getAmendmentPhaseWithSteps(@PathVariable Long id) {
        log.debug("Getting amendment phase with steps for ID: {}", id);

        AmendmentPhaseDTO amendmentPhaseWithSteps = amendmentPhaseService.getAmendmentPhaseWithSteps(id);

        return ResponseEntity.ok(amendmentPhaseWithSteps);
    }

    /**
     * Get amendment phase by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<AmendmentPhaseDTO> getAmendmentPhaseByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting amendment phase by French designation: {}", designationFr);

        return amendmentPhaseService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete amendment phase by ID
     * Removes amendment phase from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmendmentPhase(@PathVariable Long id) {
        log.info("Deleting amendment phase with ID: {}", id);

        amendmentPhaseService.deleteAmendmentPhase(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all amendment phases with pagination
     * Returns list of all amendment phases ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<AmendmentPhaseDTO>> getAllAmendmentPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all amendment phases - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getAllAmendmentPhases(pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    /**
     * Get all amendment phases with amendment steps info
     */
    @GetMapping("/with-steps")
    public ResponseEntity<Page<AmendmentPhaseDTO>> getAllAmendmentPhasesWithSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all amendment phases with steps - page: {}, size: {}", page, size);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getAllAmendmentPhasesWithSteps(pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search amendment phases by any field (all designations)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<AmendmentPhaseDTO>> searchAmendmentPhases(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching amendment phases with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.searchAmendmentPhases(query, pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    /**
     * Search amendment phases by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<AmendmentPhaseDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching amendment phases by designation: {}", designation);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.searchByDesignation(designation, pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    // ========== CATEGORY ENDPOINTS ==========

    /**
     * Get amendment phases by type
     */
    @GetMapping("/type/{phaseType}")
    public ResponseEntity<Page<AmendmentPhaseDTO>> getAmendmentPhasesByType(
            @PathVariable String phaseType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting amendment phases by type: {}", phaseType);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getAmendmentPhasesByType(phaseType, pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    /**
     * Get amendment phases that have amendment steps
     */
    @GetMapping("/with-amendment-steps")
    public ResponseEntity<Page<AmendmentPhaseDTO>> getAmendmentPhasesWithSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting amendment phases with amendment steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getAmendmentPhasesWithSteps(pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    /**
     * Get amendment phases that have no amendment steps
     */
    @GetMapping("/without-amendment-steps")
    public ResponseEntity<Page<AmendmentPhaseDTO>> getAmendmentPhasesWithoutSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting amendment phases without amendment steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getAmendmentPhasesWithoutSteps(pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    /**
     * Get pre-award amendment phases
     */
    @GetMapping("/pre-award")
    public ResponseEntity<Page<AmendmentPhaseDTO>> getPreAwardPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting pre-award amendment phases");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getPreAwardPhases(pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    /**
     * Get post-award amendment phases
     */
    @GetMapping("/post-award")
    public ResponseEntity<Page<AmendmentPhaseDTO>> getPostAwardPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting post-award amendment phases");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getPostAwardPhases(pageable);

        return ResponseEntity.ok(amendmentPhases);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update amendment phase metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<AmendmentPhaseDTO> updateAmendmentPhase(
            @PathVariable Long id,
            @Valid @RequestBody AmendmentPhaseDTO amendmentPhaseDTO) {

        log.info("Updating amendment phase with ID: {}", id);

        AmendmentPhaseDTO updatedAmendmentPhase = amendmentPhaseService.updateAmendmentPhase(id, amendmentPhaseDTO);

        return ResponseEntity.ok(updatedAmendmentPhase);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if amendment phase exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkAmendmentPhaseExists(@PathVariable Long id) {
        log.debug("Checking existence of amendment phase ID: {}", id);

        boolean exists = amendmentPhaseService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if amendment phase exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkAmendmentPhaseExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);

        boolean exists = amendmentPhaseService.existsByDesignationFr(designationFr);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of amendment phases
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getAmendmentPhasesCount() {
        log.debug("Getting total count of amendment phases");

        Long count = amendmentPhaseService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get amendment phase info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<AmendmentPhaseInfoResponse> getAmendmentPhaseInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for amendment phase ID: {}", id);

        try {
            return amendmentPhaseService.findOne(id)
                    .map(amendmentPhaseDTO -> {
                        AmendmentPhaseInfoResponse response = AmendmentPhaseInfoResponse.builder()
                                .amendmentPhaseMetadata(amendmentPhaseDTO)
                                .hasArabicDesignation(amendmentPhaseDTO.getDesignationAr() != null && !amendmentPhaseDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(amendmentPhaseDTO.getDesignationEn() != null && !amendmentPhaseDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(amendmentPhaseDTO.getDesignationFr() != null && !amendmentPhaseDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(amendmentPhaseDTO.isMultilingual())
                                .isValid(amendmentPhaseDTO.isValid())
                                .isPreAwardPhase(amendmentPhaseDTO.isPreAwardPhase())
                                .isPostAwardPhase(amendmentPhaseDTO.isPostAwardPhase())
                                .allowsStepModifications(amendmentPhaseDTO.allowsStepModifications())
                                .defaultDesignation(amendmentPhaseDTO.getDefaultDesignation())
                                .amendmentPhaseType(amendmentPhaseDTO.getAmendmentPhaseType())
                                .shortDisplay(amendmentPhaseDTO.getShortDisplay())
                                .fullDisplay(amendmentPhaseDTO.getFullDisplay())
                                .availableLanguages(amendmentPhaseDTO.getAvailableLanguages())
                                .comparisonKey(amendmentPhaseDTO.getComparisonKey())
                                .phaseOrder(amendmentPhaseDTO.getPhaseOrder())
                                .hasActiveSteps(amendmentPhaseDTO.hasActiveSteps())
                                .displayWithStepCount(amendmentPhaseDTO.getDisplayWithStepCount())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting amendment phase info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class AmendmentPhaseInfoResponse {
        private AmendmentPhaseDTO amendmentPhaseMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isValid;
        private Boolean isPreAwardPhase;
        private Boolean isPostAwardPhase;
        private Boolean allowsStepModifications;
        private String defaultDesignation;
        private String amendmentPhaseType;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
        private Integer phaseOrder;
        private Boolean hasActiveSteps;
        private String displayWithStepCount;
    }
}