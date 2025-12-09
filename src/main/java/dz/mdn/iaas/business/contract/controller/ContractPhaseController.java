/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractPhaseController
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.service.ContractPhaseService;
import dz.mdn.iaas.business.contract.dto.ContractPhaseDTO;
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
 * ContractPhase REST Controller
 * Handles contract phase operations: post one, get metadata, delete one, get all
 * Based on exact ContractPhase model: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr
 * Required field: F_03 (designationFr) with unique constraint
 */
@RestController
@RequestMapping("/contractPhase")
@RequiredArgsConstructor
@Slf4j
public class ContractPhaseController {

    private final ContractPhaseService contractPhaseService;

    // ========== POST ONE CONSULTATION PHASE ==========

    /**
     * Create new contract phase
     * Creates contract phase with multilingual designations
     */
    @PostMapping
    public ResponseEntity<ContractPhaseDTO> createContractPhase(@Valid @RequestBody ContractPhaseDTO contractPhaseDTO) {
        log.info("Creating contract phase with French designation: {}", 
                contractPhaseDTO.getDesignationFr());

        ContractPhaseDTO createdContractPhase = contractPhaseService.createContractPhase(contractPhaseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdContractPhase);
    }

    // ========== GET METADATA ==========

    /**
     * Get contract phase metadata by ID
     * Returns contract phase information with all designations
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractPhaseDTO> getContractPhaseMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for contract phase ID: {}", id);

        ContractPhaseDTO contractPhaseMetadata = contractPhaseService.getContractPhaseById(id);

        return ResponseEntity.ok(contractPhaseMetadata);
    }

    /**
     * Get contract phase metadata by ID with contract steps info
     */
    @GetMapping("/{id}/with-steps")
    public ResponseEntity<ContractPhaseDTO> getContractPhaseWithSteps(@PathVariable Long id) {
        log.debug("Getting contract phase with steps for ID: {}", id);

        ContractPhaseDTO contractPhaseWithSteps = contractPhaseService.getContractPhaseWithSteps(id);

        return ResponseEntity.ok(contractPhaseWithSteps);
    }

    /**
     * Get contract phase by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ContractPhaseDTO> getContractPhaseByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting contract phase by French designation: {}", designationFr);

        return contractPhaseService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete contract phase by ID
     * Removes contract phase from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractPhase(@PathVariable Long id) {
        log.info("Deleting contract phase with ID: {}", id);

        contractPhaseService.deleteContractPhase(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all contract phases with pagination
     * Returns list of all contract phases ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ContractPhaseDTO>> getAllContractPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all contract phases - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ContractPhaseDTO> contractPhases = contractPhaseService.getAllContractPhases(pageable);

        return ResponseEntity.ok(contractPhases);
    }

    /**
     * Get all contract phases with contract steps info
     */
    @GetMapping("/with-steps")
    public ResponseEntity<Page<ContractPhaseDTO>> getAllContractPhasesWithSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all contract phases with steps - page: {}, size: {}", page, size);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ContractPhaseDTO> contractPhases = contractPhaseService.getAllContractPhasesWithSteps(pageable);

        return ResponseEntity.ok(contractPhases);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search contract phases by any field (all designations)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ContractPhaseDTO>> searchContractPhases(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching contract phases with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ContractPhaseDTO> contractPhases = contractPhaseService.searchContractPhases(query, pageable);

        return ResponseEntity.ok(contractPhases);
    }

    /**
     * Search contract phases by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<ContractPhaseDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching contract phases by designation: {}", designation);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractPhaseDTO> contractPhases = contractPhaseService.searchByDesignation(designation, pageable);

        return ResponseEntity.ok(contractPhases);
    }

    // ========== CATEGORY ENDPOINTS ==========

    /**
     * Get contract phases by type
     */
    @GetMapping("/type/{phaseType}")
    public ResponseEntity<Page<ContractPhaseDTO>> getContractPhasesByType(
            @PathVariable String phaseType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting contract phases by type: {}", phaseType);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractPhaseDTO> contractPhases = contractPhaseService.getContractPhasesByType(phaseType, pageable);

        return ResponseEntity.ok(contractPhases);
    }

    /**
     * Get contract phases that have contract steps
     */
    @GetMapping("/with-contract-steps")
    public ResponseEntity<Page<ContractPhaseDTO>> getContractPhasesWithSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting contract phases with contract steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractPhaseDTO> contractPhases = contractPhaseService.getContractPhasesWithSteps(pageable);

        return ResponseEntity.ok(contractPhases);
    }

    /**
     * Get contract phases that have no contract steps
     */
    @GetMapping("/without-contract-steps")
    public ResponseEntity<Page<ContractPhaseDTO>> getContractPhasesWithoutSteps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting contract phases without contract steps");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractPhaseDTO> contractPhases = contractPhaseService.getContractPhasesWithoutSteps(pageable);

        return ResponseEntity.ok(contractPhases);
    }

    /**
     * Get pre-award contract phases
     */
    @GetMapping("/pre-award")
    public ResponseEntity<Page<ContractPhaseDTO>> getPreAwardPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting pre-award contract phases");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractPhaseDTO> contractPhases = contractPhaseService.getPreAwardPhases(pageable);

        return ResponseEntity.ok(contractPhases);
    }

    /**
     * Get post-award contract phases
     */
    @GetMapping("/post-award")
    public ResponseEntity<Page<ContractPhaseDTO>> getPostAwardPhases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting post-award contract phases");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractPhaseDTO> contractPhases = contractPhaseService.getPostAwardPhases(pageable);

        return ResponseEntity.ok(contractPhases);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update contract phase metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractPhaseDTO> updateContractPhase(
            @PathVariable Long id,
            @Valid @RequestBody ContractPhaseDTO contractPhaseDTO) {

        log.info("Updating contract phase with ID: {}", id);

        ContractPhaseDTO updatedContractPhase = contractPhaseService.updateContractPhase(id, contractPhaseDTO);

        return ResponseEntity.ok(updatedContractPhase);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if contract phase exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkContractPhaseExists(@PathVariable Long id) {
        log.debug("Checking existence of contract phase ID: {}", id);

        boolean exists = contractPhaseService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if contract phase exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkContractPhaseExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);

        boolean exists = contractPhaseService.existsByDesignationFr(designationFr);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of contract phases
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getContractPhasesCount() {
        log.debug("Getting total count of contract phases");

        Long count = contractPhaseService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get contract phase info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ContractPhaseInfoResponse> getContractPhaseInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for contract phase ID: {}", id);

        try {
            return contractPhaseService.findOne(id)
                    .map(contractPhaseDTO -> {
                        ContractPhaseInfoResponse response = ContractPhaseInfoResponse.builder()
                                .contractPhaseMetadata(contractPhaseDTO)
                                .hasArabicDesignation(contractPhaseDTO.getDesignationAr() != null && !contractPhaseDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(contractPhaseDTO.getDesignationEn() != null && !contractPhaseDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(contractPhaseDTO.getDesignationFr() != null && !contractPhaseDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(contractPhaseDTO.isMultilingual())
                                .isValid(contractPhaseDTO.isValid())
                                .isPreAwardPhase(contractPhaseDTO.isPreAwardPhase())
                                .isPostAwardPhase(contractPhaseDTO.isPostAwardPhase())
                                .allowsStepModifications(contractPhaseDTO.allowsStepModifications())
                                .defaultDesignation(contractPhaseDTO.getDefaultDesignation())
                                .contractPhaseType(contractPhaseDTO.getContractPhaseType())
                                .shortDisplay(contractPhaseDTO.getShortDisplay())
                                .fullDisplay(contractPhaseDTO.getFullDisplay())
                                .availableLanguages(contractPhaseDTO.getAvailableLanguages())
                                .comparisonKey(contractPhaseDTO.getComparisonKey())
                                .phaseOrder(contractPhaseDTO.getPhaseOrder())
                                .hasActiveSteps(contractPhaseDTO.hasActiveSteps())
                                .displayWithStepCount(contractPhaseDTO.getDisplayWithStepCount())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting contract phase info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ContractPhaseInfoResponse {
        private ContractPhaseDTO contractPhaseMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isValid;
        private Boolean isPreAwardPhase;
        private Boolean isPostAwardPhase;
        private Boolean allowsStepModifications;
        private String defaultDesignation;
        private String contractPhaseType;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
        private Integer phaseOrder;
        private Boolean hasActiveSteps;
        private String displayWithStepCount;
    }
}