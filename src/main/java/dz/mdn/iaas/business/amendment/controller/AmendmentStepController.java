/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepController
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import dz.mdn.iaas.business.amendment.service.AmendmentStepService;
import dz.mdn.iaas.business.amendment.dto.AmendmentStepDTO;
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
 * AmendmentStep REST Controller
 * Handles amendment step operations: post one, get metadata, delete one, get all
 * Based on exact AmendmentStep model: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=amendmentPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (amendmentPhase)
 */
@RestController
@RequestMapping("/amendmentStep")
@RequiredArgsConstructor
@Slf4j
public class AmendmentStepController {

    private final AmendmentStepService amendmentStepService;

    // ========== POST ONE CONSULTATION STEP ==========

    /**
     * Create new amendment step
     * Creates amendment step with multilingual designations and amendment phase association
     */
    @PostMapping
    public ResponseEntity<AmendmentStepDTO> createAmendmentStep(@Valid @RequestBody AmendmentStepDTO amendmentStepDTO) {
        log.info("Creating amendment step with French designation: {} for amendment phase ID: {}", 
                amendmentStepDTO.getDesignationFr(), amendmentStepDTO.getAmendmentPhaseId());

        AmendmentStepDTO createdAmendmentStep = amendmentStepService.createAmendmentStep(amendmentStepDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAmendmentStep);
    }

    // ========== GET METADATA ==========

    /**
     * Get amendment step metadata by ID
     * Returns amendment step information with all designations
     */
    @GetMapping("/{id}")
    public ResponseEntity<AmendmentStepDTO> getAmendmentStepMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for amendment step ID: {}", id);

        AmendmentStepDTO amendmentStepMetadata = amendmentStepService.getAmendmentStepById(id);

        return ResponseEntity.ok(amendmentStepMetadata);
    }

    /**
     * Get amendment step metadata by ID with amendment phase info
     */
    @GetMapping("/{id}/with-phase")
    public ResponseEntity<AmendmentStepDTO> getAmendmentStepWithPhase(@PathVariable Long id) {
        log.debug("Getting amendment step with phase for ID: {}", id);

        AmendmentStepDTO amendmentStepWithPhase = amendmentStepService.getAmendmentStepWithPhase(id);

        return ResponseEntity.ok(amendmentStepWithPhase);
    }

    /**
     * Get amendment step by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<AmendmentStepDTO> getAmendmentStepByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting amendment step by French designation: {}", designationFr);

        return amendmentStepService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete amendment step by ID
     * Removes amendment step from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmendmentStep(@PathVariable Long id) {
        log.info("Deleting amendment step with ID: {}", id);

        amendmentStepService.deleteAmendmentStep(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all amendment steps with pagination
     * Returns list of all amendment steps ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<AmendmentStepDTO>> getAllAmendmentSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all amendment steps - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.getAllAmendmentSteps(pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    /**
     * Get all amendment steps with amendment phase info
     */
    @GetMapping("/with-phase")
    public ResponseEntity<Page<AmendmentStepDTO>> getAllAmendmentStepsWithPhase(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all amendment steps with phase - page: {}, size: {}", page, size);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.getAllAmendmentStepsWithPhase(pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    /**
     * Get amendment steps by amendment phase ID
     */
    @GetMapping("/phase/{amendmentPhaseId}")
    public ResponseEntity<Page<AmendmentStepDTO>> getAmendmentStepsByPhaseId(
            @PathVariable Long amendmentPhaseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting amendment steps for amendment phase ID: {}", amendmentPhaseId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.getAmendmentStepsByPhaseId(amendmentPhaseId, pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search amendment steps by any field (all designations and phase)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<AmendmentStepDTO>> searchAmendmentSteps(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching amendment steps with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.searchAmendmentSteps(query, pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    /**
     * Search amendment steps by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<AmendmentStepDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching amendment steps by designation: {}", designation);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.searchByDesignation(designation, pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    // ========== CATEGORY ENDPOINTS ==========

    /**
     * Get amendment steps by type
     */
    @GetMapping("/type/{stepType}")
    public ResponseEntity<Page<AmendmentStepDTO>> getAmendmentStepsByType(
            @PathVariable String stepType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting amendment steps by type: {}", stepType);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.getAmendmentStepsByType(stepType, pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    /**
     * Get administrative amendment steps
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<AmendmentStepDTO>> getAdministrativeSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting administrative amendment steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.getAdministrativeSteps(pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    /**
     * Get operational amendment steps
     */
    @GetMapping("/operational")
    public ResponseEntity<Page<AmendmentStepDTO>> getOperationalSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting operational amendment steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AmendmentStepDTO> amendmentSteps = amendmentStepService.getOperationalSteps(pageable);

        return ResponseEntity.ok(amendmentSteps);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update amendment step metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<AmendmentStepDTO> updateAmendmentStep(
            @PathVariable Long id,
            @Valid @RequestBody AmendmentStepDTO amendmentStepDTO) {

        log.info("Updating amendment step with ID: {}", id);

        AmendmentStepDTO updatedAmendmentStep = amendmentStepService.updateAmendmentStep(id, amendmentStepDTO);

        return ResponseEntity.ok(updatedAmendmentStep);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if amendment step exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkAmendmentStepExists(@PathVariable Long id) {
        log.debug("Checking existence of amendment step ID: {}", id);

        boolean exists = amendmentStepService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if amendment step exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkAmendmentStepExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);

        boolean exists = amendmentStepService.existsByDesignationFr(designationFr);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of amendment steps
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getAmendmentStepsCount() {
        log.debug("Getting total count of amendment steps");

        Long count = amendmentStepService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get count of amendment steps by amendment phase
     */
    @GetMapping("/count/phase/{amendmentPhaseId}")
    public ResponseEntity<Long> getCountByAmendmentPhase(@PathVariable Long amendmentPhaseId) {
        log.debug("Getting count of amendment steps for amendment phase ID: {}", amendmentPhaseId);

        Long count = amendmentStepService.getCountByAmendmentPhase(amendmentPhaseId);

        return ResponseEntity.ok(count);
    }

    /**
     * Get amendment step info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<AmendmentStepInfoResponse> getAmendmentStepInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for amendment step ID: {}", id);

        try {
            return amendmentStepService.findOne(id)
                    .map(amendmentStepDTO -> {
                        AmendmentStepInfoResponse response = AmendmentStepInfoResponse.builder()
                                .amendmentStepMetadata(amendmentStepDTO)
                                .hasArabicDesignation(amendmentStepDTO.getDesignationAr() != null && !amendmentStepDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(amendmentStepDTO.getDesignationEn() != null && !amendmentStepDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(amendmentStepDTO.getDesignationFr() != null && !amendmentStepDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(amendmentStepDTO.isMultilingual())
                                .isValid(amendmentStepDTO.isValid())
                                .isAdministrativeStep(amendmentStepDTO.isAdministrativeStep())
                                .isOperationalStep(amendmentStepDTO.isOperationalStep())
                                .requiresApproval(amendmentStepDTO.requiresApproval())
                                .canBeAutomated(amendmentStepDTO.canBeAutomated())
                                .defaultDesignation(amendmentStepDTO.getDefaultDesignation())
                                .amendmentStepType(amendmentStepDTO.getAmendmentStepType())
                                .shortDisplay(amendmentStepDTO.getShortDisplay())
                                .fullDisplay(amendmentStepDTO.getFullDisplay())
                                .availableLanguages(amendmentStepDTO.getAvailableLanguages())
                                .comparisonKey(amendmentStepDTO.getComparisonKey())
                                .stepPriority(amendmentStepDTO.getStepPriority())
                                .estimatedDurationHours(amendmentStepDTO.getEstimatedDurationHours())
                                .displayWithPhase(amendmentStepDTO.getDisplayWithPhase())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting amendment step info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class AmendmentStepInfoResponse {
        private AmendmentStepDTO amendmentStepMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isValid;
        private Boolean isAdministrativeStep;
        private Boolean isOperationalStep;
        private Boolean requiresApproval;
        private Boolean canBeAutomated;
        private String defaultDesignation;
        private String amendmentStepType;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
        private Integer stepPriority;
        private Integer estimatedDurationHours;
        private String displayWithPhase;
    }
}