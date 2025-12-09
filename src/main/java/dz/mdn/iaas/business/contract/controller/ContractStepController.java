/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStepController
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.service.ContractStepService;
import dz.mdn.iaas.business.contract.dto.ContractStepDTO;
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
 * ContractStep REST Controller
 * Handles contract step operations: post one, get metadata, delete one, get all
 * Based on exact ContractStep model: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=contractPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (contractPhase)
 */
@RestController
@RequestMapping("/contractStep")
@RequiredArgsConstructor
@Slf4j
public class ContractStepController {

    private final ContractStepService contractStepService;

    // ========== POST ONE CONSULTATION STEP ==========

    /**
     * Create new contract step
     * Creates contract step with multilingual designations and contract phase association
     */
    @PostMapping
    public ResponseEntity<ContractStepDTO> createContractStep(@Valid @RequestBody ContractStepDTO contractStepDTO) {
        log.info("Creating contract step with French designation: {} for contract phase ID: {}", 
                contractStepDTO.getDesignationFr(), contractStepDTO.getContractPhaseId());

        ContractStepDTO createdContractStep = contractStepService.createContractStep(contractStepDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdContractStep);
    }

    // ========== GET METADATA ==========

    /**
     * Get contract step metadata by ID
     * Returns contract step information with all designations
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractStepDTO> getContractStepMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for contract step ID: {}", id);

        ContractStepDTO contractStepMetadata = contractStepService.getContractStepById(id);

        return ResponseEntity.ok(contractStepMetadata);
    }

    /**
     * Get contract step metadata by ID with contract phase info
     */
    @GetMapping("/{id}/with-phase")
    public ResponseEntity<ContractStepDTO> getContractStepWithPhase(@PathVariable Long id) {
        log.debug("Getting contract step with phase for ID: {}", id);

        ContractStepDTO contractStepWithPhase = contractStepService.getContractStepWithPhase(id);

        return ResponseEntity.ok(contractStepWithPhase);
    }

    /**
     * Get contract step by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ContractStepDTO> getContractStepByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting contract step by French designation: {}", designationFr);

        return contractStepService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete contract step by ID
     * Removes contract step from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractStep(@PathVariable Long id) {
        log.info("Deleting contract step with ID: {}", id);

        contractStepService.deleteContractStep(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all contract steps with pagination
     * Returns list of all contract steps ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ContractStepDTO>> getAllContractSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all contract steps - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ContractStepDTO> contractSteps = contractStepService.getAllContractSteps(pageable);

        return ResponseEntity.ok(contractSteps);
    }

    /**
     * Get all contract steps with contract phase info
     */
    @GetMapping("/with-phase")
    public ResponseEntity<Page<ContractStepDTO>> getAllContractStepsWithPhase(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all contract steps with phase - page: {}, size: {}", page, size);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ContractStepDTO> contractSteps = contractStepService.getAllContractStepsWithPhase(pageable);

        return ResponseEntity.ok(contractSteps);
    }

    /**
     * Get contract steps by contract phase ID
     */
    @GetMapping("/phase/{contractPhaseId}")
    public ResponseEntity<Page<ContractStepDTO>> getContractStepsByPhaseId(
            @PathVariable Long contractPhaseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting contract steps for contract phase ID: {}", contractPhaseId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractStepDTO> contractSteps = contractStepService.getContractStepsByPhaseId(contractPhaseId, pageable);

        return ResponseEntity.ok(contractSteps);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search contract steps by any field (all designations and phase)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ContractStepDTO>> searchContractSteps(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching contract steps with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ContractStepDTO> contractSteps = contractStepService.searchContractSteps(query, pageable);

        return ResponseEntity.ok(contractSteps);
    }

    /**
     * Search contract steps by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<ContractStepDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching contract steps by designation: {}", designation);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractStepDTO> contractSteps = contractStepService.searchByDesignation(designation, pageable);

        return ResponseEntity.ok(contractSteps);
    }

    // ========== CATEGORY ENDPOINTS ==========

    /**
     * Get contract steps by type
     */
    @GetMapping("/type/{stepType}")
    public ResponseEntity<Page<ContractStepDTO>> getContractStepsByType(
            @PathVariable String stepType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting contract steps by type: {}", stepType);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractStepDTO> contractSteps = contractStepService.getContractStepsByType(stepType, pageable);

        return ResponseEntity.ok(contractSteps);
    }

    /**
     * Get administrative contract steps
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<ContractStepDTO>> getAdministrativeSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting administrative contract steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractStepDTO> contractSteps = contractStepService.getAdministrativeSteps(pageable);

        return ResponseEntity.ok(contractSteps);
    }

    /**
     * Get operational contract steps
     */
    @GetMapping("/operational")
    public ResponseEntity<Page<ContractStepDTO>> getOperationalSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting operational contract steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractStepDTO> contractSteps = contractStepService.getOperationalSteps(pageable);

        return ResponseEntity.ok(contractSteps);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update contract step metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractStepDTO> updateContractStep(
            @PathVariable Long id,
            @Valid @RequestBody ContractStepDTO contractStepDTO) {

        log.info("Updating contract step with ID: {}", id);

        ContractStepDTO updatedContractStep = contractStepService.updateContractStep(id, contractStepDTO);

        return ResponseEntity.ok(updatedContractStep);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if contract step exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkContractStepExists(@PathVariable Long id) {
        log.debug("Checking existence of contract step ID: {}", id);

        boolean exists = contractStepService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if contract step exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkContractStepExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);

        boolean exists = contractStepService.existsByDesignationFr(designationFr);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of contract steps
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getContractStepsCount() {
        log.debug("Getting total count of contract steps");

        Long count = contractStepService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get count of contract steps by contract phase
     */
    @GetMapping("/count/phase/{contractPhaseId}")
    public ResponseEntity<Long> getCountByContractPhase(@PathVariable Long contractPhaseId) {
        log.debug("Getting count of contract steps for contract phase ID: {}", contractPhaseId);

        Long count = contractStepService.getCountByContractPhase(contractPhaseId);

        return ResponseEntity.ok(count);
    }

    /**
     * Get contract step info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ContractStepInfoResponse> getContractStepInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for contract step ID: {}", id);

        try {
            return contractStepService.findOne(id)
                    .map(contractStepDTO -> {
                        ContractStepInfoResponse response = ContractStepInfoResponse.builder()
                                .contractStepMetadata(contractStepDTO)
                                .hasArabicDesignation(contractStepDTO.getDesignationAr() != null && !contractStepDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(contractStepDTO.getDesignationEn() != null && !contractStepDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(contractStepDTO.getDesignationFr() != null && !contractStepDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(contractStepDTO.isMultilingual())
                                .isValid(contractStepDTO.isValid())
                                .isAdministrativeStep(contractStepDTO.isAdministrativeStep())
                                .isOperationalStep(contractStepDTO.isOperationalStep())
                                .requiresApproval(contractStepDTO.requiresApproval())
                                .canBeAutomated(contractStepDTO.canBeAutomated())
                                .defaultDesignation(contractStepDTO.getDefaultDesignation())
                                .contractStepType(contractStepDTO.getContractStepType())
                                .shortDisplay(contractStepDTO.getShortDisplay())
                                .fullDisplay(contractStepDTO.getFullDisplay())
                                .availableLanguages(contractStepDTO.getAvailableLanguages())
                                .comparisonKey(contractStepDTO.getComparisonKey())
                                .stepPriority(contractStepDTO.getStepPriority())
                                .estimatedDurationHours(contractStepDTO.getEstimatedDurationHours())
                                .displayWithPhase(contractStepDTO.getDisplayWithPhase())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting contract step info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ContractStepInfoResponse {
        private ContractStepDTO contractStepMetadata;
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
        private String contractStepType;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
        private Integer stepPriority;
        private Integer estimatedDurationHours;
        private String displayWithPhase;
    }
}