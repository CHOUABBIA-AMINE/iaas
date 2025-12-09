/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationPhaseController
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.service.ConsultationPhaseService;
import dz.mdn.iaas.business.consultation.dto.ConsultationPhaseDTO;
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
 * ConsultationPhase REST Controller
 * Handles consultation phase operations: post one, get metadata, delete one, get all
 * Based on exact ConsultationPhase model: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr
 * Required field: F_03 (designationFr) with unique constraint
 */
@RestController
@RequestMapping("/consultationPhase")
@RequiredArgsConstructor
@Slf4j
public class ConsultationPhaseController {

    private final ConsultationPhaseService consultationPhaseService;

    // ========== POST ONE CONSULTATION PHASE ==========

    /**
     * Create new consultation phase
     * Creates consultation phase with multilingual designations
     */
    @PostMapping
    public ResponseEntity<ConsultationPhaseDTO> createConsultationPhase(@Valid @RequestBody ConsultationPhaseDTO consultationPhaseDTO) {
        log.info("Creating consultation phase with French designation: {}", 
                consultationPhaseDTO.getDesignationFr());

        ConsultationPhaseDTO createdConsultationPhase = consultationPhaseService.createConsultationPhase(consultationPhaseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsultationPhase);
    }

    // ========== GET METADATA ==========

    /**
     * Get consultation phase metadata by ID
     * Returns consultation phase information with all designations
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationPhaseDTO> getConsultationPhaseMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for consultation phase ID: {}", id);

        ConsultationPhaseDTO consultationPhaseMetadata = consultationPhaseService.getConsultationPhaseById(id);

        return ResponseEntity.ok(consultationPhaseMetadata);
    }

    /**
     * Get consultation phase metadata by ID with consultation steps info
     */
    @GetMapping("/{id}/with-steps")
    public ResponseEntity<ConsultationPhaseDTO> getConsultationPhaseWithSteps(@PathVariable Long id) {
        log.debug("Getting consultation phase with steps for ID: {}", id);

        ConsultationPhaseDTO consultationPhaseWithSteps = consultationPhaseService.getConsultationPhaseWithSteps(id);

        return ResponseEntity.ok(consultationPhaseWithSteps);
    }

    /**
     * Get consultation phase by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ConsultationPhaseDTO> getConsultationPhaseByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting consultation phase by French designation: {}", designationFr);

        return consultationPhaseService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete consultation phase by ID
     * Removes consultation phase from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultationPhase(@PathVariable Long id) {
        log.info("Deleting consultation phase with ID: {}", id);

        consultationPhaseService.deleteConsultationPhase(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all consultation phases with pagination
     * Returns list of all consultation phases ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ConsultationPhaseDTO>> getAllConsultationPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all consultation phases - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getAllConsultationPhases(pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    /**
     * Get all consultation phases with consultation steps info
     */
    @GetMapping("/with-steps")
    public ResponseEntity<Page<ConsultationPhaseDTO>> getAllConsultationPhasesWithSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all consultation phases with steps - page: {}, size: {}", page, size);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getAllConsultationPhasesWithSteps(pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search consultation phases by any field (all designations)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ConsultationPhaseDTO>> searchConsultationPhases(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching consultation phases with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.searchConsultationPhases(query, pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    /**
     * Search consultation phases by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<ConsultationPhaseDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching consultation phases by designation: {}", designation);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.searchByDesignation(designation, pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    // ========== CATEGORY ENDPOINTS ==========

    /**
     * Get consultation phases by type
     */
    @GetMapping("/type/{phaseType}")
    public ResponseEntity<Page<ConsultationPhaseDTO>> getConsultationPhasesByType(
            @PathVariable String phaseType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting consultation phases by type: {}", phaseType);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getConsultationPhasesByType(phaseType, pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    /**
     * Get consultation phases that have consultation steps
     */
    @GetMapping("/with-consultation-steps")
    public ResponseEntity<Page<ConsultationPhaseDTO>> getConsultationPhasesWithSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting consultation phases with consultation steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getConsultationPhasesWithSteps(pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    /**
     * Get consultation phases that have no consultation steps
     */
    @GetMapping("/without-consultation-steps")
    public ResponseEntity<Page<ConsultationPhaseDTO>> getConsultationPhasesWithoutSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting consultation phases without consultation steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getConsultationPhasesWithoutSteps(pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    /**
     * Get pre-award consultation phases
     */
    @GetMapping("/pre-award")
    public ResponseEntity<Page<ConsultationPhaseDTO>> getPreAwardPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting pre-award consultation phases");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getPreAwardPhases(pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    /**
     * Get post-award consultation phases
     */
    @GetMapping("/post-award")
    public ResponseEntity<Page<ConsultationPhaseDTO>> getPostAwardPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting post-award consultation phases");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getPostAwardPhases(pageable);

        return ResponseEntity.ok(consultationPhases);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update consultation phase metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationPhaseDTO> updateConsultationPhase(
            @PathVariable Long id,
            @Valid @RequestBody ConsultationPhaseDTO consultationPhaseDTO) {

        log.info("Updating consultation phase with ID: {}", id);

        ConsultationPhaseDTO updatedConsultationPhase = consultationPhaseService.updateConsultationPhase(id, consultationPhaseDTO);

        return ResponseEntity.ok(updatedConsultationPhase);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if consultation phase exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkConsultationPhaseExists(@PathVariable Long id) {
        log.debug("Checking existence of consultation phase ID: {}", id);

        boolean exists = consultationPhaseService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if consultation phase exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkConsultationPhaseExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);

        boolean exists = consultationPhaseService.existsByDesignationFr(designationFr);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of consultation phases
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getConsultationPhasesCount() {
        log.debug("Getting total count of consultation phases");

        Long count = consultationPhaseService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get consultation phase info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ConsultationPhaseInfoResponse> getConsultationPhaseInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for consultation phase ID: {}", id);

        try {
            return consultationPhaseService.findOne(id)
                    .map(consultationPhaseDTO -> {
                        ConsultationPhaseInfoResponse response = ConsultationPhaseInfoResponse.builder()
                                .consultationPhaseMetadata(consultationPhaseDTO)
                                .hasArabicDesignation(consultationPhaseDTO.getDesignationAr() != null && !consultationPhaseDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(consultationPhaseDTO.getDesignationEn() != null && !consultationPhaseDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(consultationPhaseDTO.getDesignationFr() != null && !consultationPhaseDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(consultationPhaseDTO.isMultilingual())
                                .isValid(consultationPhaseDTO.isValid())
                                .isPreAwardPhase(consultationPhaseDTO.isPreAwardPhase())
                                .isPostAwardPhase(consultationPhaseDTO.isPostAwardPhase())
                                .allowsStepModifications(consultationPhaseDTO.allowsStepModifications())
                                .defaultDesignation(consultationPhaseDTO.getDefaultDesignation())
                                .consultationPhaseType(consultationPhaseDTO.getConsultationPhaseType())
                                .shortDisplay(consultationPhaseDTO.getShortDisplay())
                                .fullDisplay(consultationPhaseDTO.getFullDisplay())
                                .availableLanguages(consultationPhaseDTO.getAvailableLanguages())
                                .comparisonKey(consultationPhaseDTO.getComparisonKey())
                                .phaseOrder(consultationPhaseDTO.getPhaseOrder())
                                .hasActiveSteps(consultationPhaseDTO.hasActiveSteps())
                                .displayWithStepCount(consultationPhaseDTO.getDisplayWithStepCount())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting consultation phase info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ConsultationPhaseInfoResponse {
        private ConsultationPhaseDTO consultationPhaseMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isValid;
        private Boolean isPreAwardPhase;
        private Boolean isPostAwardPhase;
        private Boolean allowsStepModifications;
        private String defaultDesignation;
        private String consultationPhaseType;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
        private Integer phaseOrder;
        private Boolean hasActiveSteps;
        private String displayWithStepCount;
    }
}