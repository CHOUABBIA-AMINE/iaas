/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStepController
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.service.ConsultationStepService;
import dz.mdn.iaas.business.consultation.dto.ConsultationStepDTO;
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
 * ConsultationStep REST Controller
 * Handles consultation step operations: post one, get metadata, delete one, get all
 * Based on exact ConsultationStep model: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=consultationPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (consultationPhase)
 */
@RestController
@RequestMapping("/consultationStep")
@RequiredArgsConstructor
@Slf4j
public class ConsultationStepController {

    private final ConsultationStepService consultationStepService;

    // ========== POST ONE CONSULTATION STEP ==========

    /**
     * Create new consultation step
     * Creates consultation step with multilingual designations and consultation phase association
     */
    @PostMapping
    public ResponseEntity<ConsultationStepDTO> createConsultationStep(@Valid @RequestBody ConsultationStepDTO consultationStepDTO) {
        log.info("Creating consultation step with French designation: {} for consultation phase ID: {}", 
                consultationStepDTO.getDesignationFr(), consultationStepDTO.getConsultationPhaseId());

        ConsultationStepDTO createdConsultationStep = consultationStepService.createConsultationStep(consultationStepDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsultationStep);
    }

    // ========== GET METADATA ==========

    /**
     * Get consultation step metadata by ID
     * Returns consultation step information with all designations
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationStepDTO> getConsultationStepMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for consultation step ID: {}", id);

        ConsultationStepDTO consultationStepMetadata = consultationStepService.getConsultationStepById(id);

        return ResponseEntity.ok(consultationStepMetadata);
    }

    /**
     * Get consultation step metadata by ID with consultation phase info
     */
    @GetMapping("/{id}/with-phase")
    public ResponseEntity<ConsultationStepDTO> getConsultationStepWithPhase(@PathVariable Long id) {
        log.debug("Getting consultation step with phase for ID: {}", id);

        ConsultationStepDTO consultationStepWithPhase = consultationStepService.getConsultationStepWithPhase(id);

        return ResponseEntity.ok(consultationStepWithPhase);
    }

    /**
     * Get consultation step by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ConsultationStepDTO> getConsultationStepByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting consultation step by French designation: {}", designationFr);

        return consultationStepService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete consultation step by ID
     * Removes consultation step from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultationStep(@PathVariable Long id) {
        log.info("Deleting consultation step with ID: {}", id);

        consultationStepService.deleteConsultationStep(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all consultation steps with pagination
     * Returns list of all consultation steps ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ConsultationStepDTO>> getAllConsultationSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all consultation steps - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationStepDTO> consultationSteps = consultationStepService.getAllConsultationSteps(pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    /**
     * Get all consultation steps with consultation phase info
     */
    @GetMapping("/with-phase")
    public ResponseEntity<Page<ConsultationStepDTO>> getAllConsultationStepsWithPhase(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all consultation steps with phase - page: {}, size: {}", page, size);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationStepDTO> consultationSteps = consultationStepService.getAllConsultationStepsWithPhase(pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    /**
     * Get consultation steps by consultation phase ID
     */
    @GetMapping("/phase/{consultationPhaseId}")
    public ResponseEntity<Page<ConsultationStepDTO>> getConsultationStepsByPhaseId(
            @PathVariable Long consultationPhaseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting consultation steps for consultation phase ID: {}", consultationPhaseId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationStepDTO> consultationSteps = consultationStepService.getConsultationStepsByPhaseId(consultationPhaseId, pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search consultation steps by any field (all designations and phase)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ConsultationStepDTO>> searchConsultationSteps(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching consultation steps with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationStepDTO> consultationSteps = consultationStepService.searchConsultationSteps(query, pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    /**
     * Search consultation steps by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<ConsultationStepDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching consultation steps by designation: {}", designation);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationStepDTO> consultationSteps = consultationStepService.searchByDesignation(designation, pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    // ========== CATEGORY ENDPOINTS ==========

    /**
     * Get consultation steps by type
     */
    @GetMapping("/type/{stepType}")
    public ResponseEntity<Page<ConsultationStepDTO>> getConsultationStepsByType(
            @PathVariable String stepType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting consultation steps by type: {}", stepType);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationStepDTO> consultationSteps = consultationStepService.getConsultationStepsByType(stepType, pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    /**
     * Get administrative consultation steps
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<ConsultationStepDTO>> getAdministrativeSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting administrative consultation steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationStepDTO> consultationSteps = consultationStepService.getAdministrativeSteps(pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    /**
     * Get operational consultation steps
     */
    @GetMapping("/operational")
    public ResponseEntity<Page<ConsultationStepDTO>> getOperationalSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting operational consultation steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ConsultationStepDTO> consultationSteps = consultationStepService.getOperationalSteps(pageable);

        return ResponseEntity.ok(consultationSteps);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update consultation step metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationStepDTO> updateConsultationStep(
            @PathVariable Long id,
            @Valid @RequestBody ConsultationStepDTO consultationStepDTO) {

        log.info("Updating consultation step with ID: {}", id);

        ConsultationStepDTO updatedConsultationStep = consultationStepService.updateConsultationStep(id, consultationStepDTO);

        return ResponseEntity.ok(updatedConsultationStep);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if consultation step exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkConsultationStepExists(@PathVariable Long id) {
        log.debug("Checking existence of consultation step ID: {}", id);

        boolean exists = consultationStepService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if consultation step exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkConsultationStepExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);

        boolean exists = consultationStepService.existsByDesignationFr(designationFr);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of consultation steps
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getConsultationStepsCount() {
        log.debug("Getting total count of consultation steps");

        Long count = consultationStepService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get count of consultation steps by consultation phase
     */
    @GetMapping("/count/phase/{consultationPhaseId}")
    public ResponseEntity<Long> getCountByConsultationPhase(@PathVariable Long consultationPhaseId) {
        log.debug("Getting count of consultation steps for consultation phase ID: {}", consultationPhaseId);

        Long count = consultationStepService.getCountByConsultationPhase(consultationPhaseId);

        return ResponseEntity.ok(count);
    }

    /**
     * Get consultation step info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ConsultationStepInfoResponse> getConsultationStepInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for consultation step ID: {}", id);

        try {
            return consultationStepService.findOne(id)
                    .map(consultationStepDTO -> {
                        ConsultationStepInfoResponse response = ConsultationStepInfoResponse.builder()
                                .consultationStepMetadata(consultationStepDTO)
                                .hasArabicDesignation(consultationStepDTO.getDesignationAr() != null && !consultationStepDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(consultationStepDTO.getDesignationEn() != null && !consultationStepDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(consultationStepDTO.getDesignationFr() != null && !consultationStepDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(consultationStepDTO.isMultilingual())
                                .isValid(consultationStepDTO.isValid())
                                .isAdministrativeStep(consultationStepDTO.isAdministrativeStep())
                                .isOperationalStep(consultationStepDTO.isOperationalStep())
                                .requiresApproval(consultationStepDTO.requiresApproval())
                                .canBeAutomated(consultationStepDTO.canBeAutomated())
                                .defaultDesignation(consultationStepDTO.getDefaultDesignation())
                                .consultationStepType(consultationStepDTO.getConsultationStepType())
                                .shortDisplay(consultationStepDTO.getShortDisplay())
                                .fullDisplay(consultationStepDTO.getFullDisplay())
                                .availableLanguages(consultationStepDTO.getAvailableLanguages())
                                .comparisonKey(consultationStepDTO.getComparisonKey())
                                .stepPriority(consultationStepDTO.getStepPriority())
                                .estimatedDurationHours(consultationStepDTO.getEstimatedDurationHours())
                                .displayWithPhase(consultationStepDTO.getDisplayWithPhase())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting consultation step info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ConsultationStepInfoResponse {
        private ConsultationStepDTO consultationStepMetadata;
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
        private String consultationStepType;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
        private Integer stepPriority;
        private Integer estimatedDurationHours;
        private String displayWithPhase;
    }
}