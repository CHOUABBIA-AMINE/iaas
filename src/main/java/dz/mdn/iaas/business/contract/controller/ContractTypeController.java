/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractTypeController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.business.contract.dto.ContractTypeDTO;
import dz.mdn.iaas.business.contract.service.ContractTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ContractType REST Controller
 * Handles approval status operations: create, get metadata, delete, get all
 * Based on exact ContractType model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/contractType")
@RequiredArgsConstructor
@Slf4j
public class ContractTypeController {

    private final ContractTypeService contractTypeService;

    // ========== POST ONE APPROVAL STATUS ==========

    /**
     * Create new approval status
     * Creates approval status with multilingual designations and workflow classification
     */
    @PostMapping
    public ResponseEntity<ContractTypeDTO> createContractType(@Valid @RequestBody ContractTypeDTO contractTypeDTO) {
        log.info("Creating approval status with French designation: {} and designations: AR={}, EN={}", 
                contractTypeDTO.getDesignationFr(), contractTypeDTO.getDesignationAr(), 
                contractTypeDTO.getDesignationEn());
        
        ContractTypeDTO createdContractType = contractTypeService.createContractType(contractTypeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContractType);
    }

    // ========== GET METADATA ==========

    /**
     * Get approval status metadata by ID
     * Returns approval status information with workflow classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractTypeDTO> getContractTypeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for approval status ID: {}", id);
        
        ContractTypeDTO contractTypeMetadata = contractTypeService.getContractTypeById(id);
        
        return ResponseEntity.ok(contractTypeMetadata);
    }

    /**
     * Get approval status by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ContractTypeDTO> getContractTypeByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting approval status by French designation: {}", designationFr);
        
        return contractTypeService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get approval status by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<ContractTypeDTO> getContractTypeByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting approval status by Arabic designation: {}", designationAr);
        
        return contractTypeService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get approval status by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<ContractTypeDTO> getContractTypeByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting approval status by English designation: {}", designationEn);
        
        return contractTypeService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete approval status by ID
     * Removes approval status from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractType(@PathVariable Long id) {
        log.info("Deleting approval status with ID: {}", id);
        
        contractTypeService.deleteContractType(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all approval statuses with pagination
     * Returns list of all approval statuses ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ContractTypeDTO>> getAllContractTypees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all approval statuses - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ContractTypeDTO> contractTypees = contractTypeService.getAllContractTypees(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search approval statuses by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ContractTypeDTO>> searchContractTypees(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching approval statuses with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ContractTypeDTO> contractTypees = contractTypeService.searchContractTypees(query, pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    // ========== APPROVAL STATUS TYPE ENDPOINTS ==========

    /**
     * Get multilingual approval statuses
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ContractTypeDTO>> getMultilingualContractTypees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual approval statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getMultilingualContractTypees(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    /**
     * Get approved statuses
     */
    @GetMapping("/approved")
    public ResponseEntity<Page<ContractTypeDTO>> getApprovedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting approved statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getApprovedStatuses(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    /**
     * Get rejected statuses
     */
    @GetMapping("/rejected")
    public ResponseEntity<Page<ContractTypeDTO>> getRejectedStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting rejected statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getRejectedStatuses(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    /**
     * Get pending statuses
     */
    @GetMapping("/pending")
    public ResponseEntity<Page<ContractTypeDTO>> getPendingStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting pending statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getPendingStatuses(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    /**
     * Get draft statuses
     */
    @GetMapping("/draft")
    public ResponseEntity<Page<ContractTypeDTO>> getDraftStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting draft statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getDraftStatuses(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    /**
     * Get review statuses
     */
    @GetMapping("/review")
    public ResponseEntity<Page<ContractTypeDTO>> getReviewStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting review statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getReviewStatuses(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    /**
     * Get final statuses (approved, rejected, cancelled)
     */
    @GetMapping("/final")
    public ResponseEntity<Page<ContractTypeDTO>> getFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getFinalStatuses(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    /**
     * Get non-final statuses (pending, draft, under review)
     */
    @GetMapping("/non-final")
    public ResponseEntity<Page<ContractTypeDTO>> getNonFinalStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting non-final statuses");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ContractTypeDTO> contractTypees = contractTypeService.getNonFinalStatuses(pageable);
        
        return ResponseEntity.ok(contractTypees);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update approval status metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractTypeDTO> updateContractType(
            @PathVariable Long id,
            @Valid @RequestBody ContractTypeDTO contractTypeDTO) {
        
        log.info("Updating approval status with ID: {}", id);
        
        ContractTypeDTO updatedContractType = contractTypeService.updateContractType(id, contractTypeDTO);
        
        return ResponseEntity.ok(updatedContractType);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if approval status exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkContractTypeExists(@PathVariable Long id) {
        log.debug("Checking existence of approval status ID: {}", id);
        
        boolean exists = contractTypeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if approval status exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkContractTypeExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = contractTypeService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of approval statuses
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getContractTypeesCount() {
        log.debug("Getting total count of approval statuses");
        
        Long count = contractTypeService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of approved statuses
     */
    @GetMapping("/count/approved")
    public ResponseEntity<Long> getApprovedStatusesCount() {
        log.debug("Getting count of approved statuses");
        
        Long count = contractTypeService.getApprovedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of rejected statuses
     */
    @GetMapping("/count/rejected")
    public ResponseEntity<Long> getRejectedStatusesCount() {
        log.debug("Getting count of rejected statuses");
        
        Long count = contractTypeService.getRejectedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of pending statuses
     */
    @GetMapping("/count/pending")
    public ResponseEntity<Long> getPendingStatusesCount() {
        log.debug("Getting count of pending statuses");
        
        Long count = contractTypeService.getPendingCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get approval status info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ContractTypeInfoResponse> getContractTypeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for approval status ID: {}", id);
        
        try {
            return contractTypeService.findOne(id)
                    .map(contractTypeDTO -> {
                        ContractTypeInfoResponse response = ContractTypeInfoResponse.builder()
                                .contractTypeMetadata(contractTypeDTO)
                                .hasArabicDesignation(contractTypeDTO.getDesignationAr() != null && !contractTypeDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(contractTypeDTO.getDesignationEn() != null && !contractTypeDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(contractTypeDTO.getDesignationFr() != null && !contractTypeDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(contractTypeDTO.isMultilingual())
                                .isApproved(contractTypeDTO.isApproved())
                                .isRejected(contractTypeDTO.isRejected())
                                .isPending(contractTypeDTO.isPending())
                                .isFinal(contractTypeDTO.isFinal())
                                .isValid(contractTypeDTO.isValid())
                                .defaultDesignation(contractTypeDTO.getDefaultDesignation())
                                .displayText(contractTypeDTO.getDisplayText())
                                .contractTypeType(contractTypeDTO.getContractTypeType())
                                .statusPriority(contractTypeDTO.getStatusPriority())
                                .statusColor(contractTypeDTO.getStatusColor())
                                .workflowStage(contractTypeDTO.getWorkflowStage())
                                .allowsTransition(contractTypeDTO.allowsTransition())
                                .shortDisplay(contractTypeDTO.getShortDisplay())
                                .fullDisplay(contractTypeDTO.getFullDisplay())
                                .availableLanguages(contractTypeDTO.getAvailableLanguages())
                                .comparisonKey(contractTypeDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting approval status info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ContractTypeInfoResponse {
        private ContractTypeDTO contractTypeMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isApproved;
        private Boolean isRejected;
        private Boolean isPending;
        private Boolean isFinal;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String contractTypeType;
        private Integer statusPriority;
        private String statusColor;
        private Integer workflowStage;
        private Boolean allowsTransition;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
