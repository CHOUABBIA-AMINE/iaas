/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetTypeController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.service.BudgetTypeService;
import dz.mdn.iaas.business.plan.dto.BudgetTypeDTO;

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
 * Budget Type REST Controller
 * Handles budget type operations: create, get metadata, delete, get all
 * Based on exact BudgetType model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique), 
 * F_04=acronymAr, F_05=acronymEn, F_06=acronymFr (unique)
 */
@RestController
@RequestMapping("/budgetType")
@RequiredArgsConstructor
@Slf4j
public class BudgetTypeController {

    private final BudgetTypeService budgetTypeService;

    // ========== POST ONE BUDGET TYPE ==========

    /**
     * Create new budget type
     * Creates budget type with multilingual support and budget classification
     */
    @PostMapping
    public ResponseEntity<BudgetTypeDTO> createBudgetType(@Valid @RequestBody BudgetTypeDTO budgetTypeDTO) {
        log.info("Creating budget type with French designation: {}, French acronym: {}", 
                budgetTypeDTO.getDesignationFr(), budgetTypeDTO.getAcronymFr());
        
        BudgetTypeDTO createdBudgetType = budgetTypeService.createBudgetType(budgetTypeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBudgetType);
    }

    // ========== GET METADATA ==========

    /**
     * Get budget type metadata by ID
     * Returns budget type information with multilingual details and budget classification
     */
    @GetMapping("/{id}")
    public ResponseEntity<BudgetTypeDTO> getBudgetTypeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for budget type ID: {}", id);
        
        BudgetTypeDTO budgetTypeMetadata = budgetTypeService.getBudgetTypeById(id);
        
        return ResponseEntity.ok(budgetTypeMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete budget type by ID
     * Removes budget type from the budget management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudgetType(@PathVariable Long id) {
        log.info("Deleting budget type with ID: {}", id);
        
        budgetTypeService.deleteBudgetType(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all budget types with pagination
     * Returns list of all budget types ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<BudgetTypeDTO>> getAllBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all budget types - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getAllBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search budget types by designation (any language)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<BudgetTypeDTO>> searchBudgetTypesByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching budget types by designation with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.searchBudgetTypesByDesignation(query, pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Search budget types by acronym (any language)
     */
    @GetMapping("/search/acronym")
    public ResponseEntity<Page<BudgetTypeDTO>> searchBudgetTypesByAcronym(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching budget types by acronym with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.searchBudgetTypesByAcronym(query, pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Advanced search budget types by any field
     */
    @GetMapping("/search/advanced")
    public ResponseEntity<Page<BudgetTypeDTO>> searchBudgetTypesByAnyField(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Advanced searching budget types with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.searchBudgetTypesByAnyField(query, pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    // ========== CATEGORY-BASED ENDPOINTS ==========

    /**
     * Get investment budget types
     */
    @GetMapping("/category/investment")
    public ResponseEntity<Page<BudgetTypeDTO>> getInvestmentBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting investment budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getInvestmentBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Get operating budget types
     */
    @GetMapping("/category/operating")
    public ResponseEntity<Page<BudgetTypeDTO>> getOperatingBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting operating budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getOperatingBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Get personnel budget types
     */
    @GetMapping("/category/personnel")
    public ResponseEntity<Page<BudgetTypeDTO>> getPersonnelBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting personnel budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getPersonnelBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Get maintenance budget types
     */
    @GetMapping("/category/maintenance")
    public ResponseEntity<Page<BudgetTypeDTO>> getMaintenanceBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting maintenance budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getMaintenanceBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Get research & development budget types
     */
    @GetMapping("/category/research-development")
    public ResponseEntity<Page<BudgetTypeDTO>> getResearchDevelopmentBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting research & development budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getResearchDevelopmentBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Get defense budget types
     */
    @GetMapping("/category/defense")
    public ResponseEntity<Page<BudgetTypeDTO>> getDefenseBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting defense budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getDefenseBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Get training budget types
     */
    @GetMapping("/category/training")
    public ResponseEntity<Page<BudgetTypeDTO>> getTrainingBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting training budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getTrainingBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    /**
     * Get emergency budget types
     */
    @GetMapping("/category/emergency")
    public ResponseEntity<Page<BudgetTypeDTO>> getEmergencyBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting emergency budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getEmergencyBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    // ========== LANGUAGE SPECIFIC ENDPOINTS ==========

    /**
     * Get multilingual budget types
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<BudgetTypeDTO>> getMultilingualBudgetTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual budget types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getMultilingualBudgetTypes(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    // ========== APPROVAL-BASED ENDPOINTS ==========

    /**
     * Get budget types requiring approval
     */
    @GetMapping("/requiring-approval")
    public ResponseEntity<Page<BudgetTypeDTO>> getBudgetTypesRequiringApproval(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting budget types requiring approval");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<BudgetTypeDTO> budgetTypes = budgetTypeService.getBudgetTypesRequiringApproval(pageable);
        
        return ResponseEntity.ok(budgetTypes);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find budget type by French designation (unique)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<BudgetTypeDTO> getBudgetTypeByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting budget type by French designation: {}", designationFr);
        
        return budgetTypeService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Find budget type by French acronym (unique)
     */
    @GetMapping("/acronym-fr/{acronymFr}")
    public ResponseEntity<BudgetTypeDTO> getBudgetTypeByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Getting budget type by French acronym: {}", acronymFr);
        
        return budgetTypeService.findByAcronymFr(acronymFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update budget type metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<BudgetTypeDTO> updateBudgetType(
            @PathVariable Long id,
            @Valid @RequestBody BudgetTypeDTO budgetTypeDTO) {
        
        log.info("Updating budget type with ID: {}", id);
        
        BudgetTypeDTO updatedBudgetType = budgetTypeService.updateBudgetType(id, budgetTypeDTO);
        
        return ResponseEntity.ok(updatedBudgetType);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if budget type exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkBudgetTypeExists(@PathVariable Long id) {
        log.debug("Checking existence of budget type ID: {}", id);
        
        boolean exists = budgetTypeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if French designation exists
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkDesignationFrExists(@PathVariable String designationFr) {
        log.debug("Checking if French designation exists: {}", designationFr);
        
        boolean exists = budgetTypeService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if French acronym exists
     */
    @GetMapping("/exists/acronym-fr/{acronymFr}")
    public ResponseEntity<Boolean> checkAcronymFrExists(@PathVariable String acronymFr) {
        log.debug("Checking if French acronym exists: {}", acronymFr);
        
        boolean exists = budgetTypeService.existsByAcronymFr(acronymFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of all budget types
     */
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllBudgetTypes() {
        log.debug("Getting count of all budget types");
        
        Long count = budgetTypeService.countAllBudgetTypes();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of investment budget types
     */
    @GetMapping("/count/investment")
    public ResponseEntity<Long> countInvestmentBudgetTypes() {
        log.debug("Getting count of investment budget types");
        
        Long count = budgetTypeService.countInvestmentBudgetTypes();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of operating budget types
     */
    @GetMapping("/count/operating")
    public ResponseEntity<Long> countOperatingBudgetTypes() {
        log.debug("Getting count of operating budget types");
        
        Long count = budgetTypeService.countOperatingBudgetTypes();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of defense budget types
     */
    @GetMapping("/count/defense")
    public ResponseEntity<Long> countDefenseBudgetTypes() {
        log.debug("Getting count of defense budget types");
        
        Long count = budgetTypeService.countDefenseBudgetTypes();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get budget type info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<BudgetTypeInfoResponse> getBudgetTypeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for budget type ID: {}", id);
        
        try {
            return budgetTypeService.findOne(id)
                    .map(budgetTypeDTO -> {
                        BudgetTypeInfoResponse response = BudgetTypeInfoResponse.builder()
                                .budgetTypeMetadata(budgetTypeDTO)
                                .defaultDesignation(budgetTypeDTO.getDefaultDesignation())
                                .defaultAcronym(budgetTypeDTO.getDefaultAcronym())
                                .displayText(budgetTypeDTO.getDisplayText())
                                .displayAcronym(budgetTypeDTO.getDisplayAcronym())
                                .isMultilingual(budgetTypeDTO.isMultilingual())
                                .availableLanguages(budgetTypeDTO.getAvailableLanguages())
                                .budgetCategory(budgetTypeDTO.getBudgetCategory())
                                .budgetPriority(budgetTypeDTO.getBudgetPriority())
                                .budgetScope(budgetTypeDTO.getBudgetScope())
                                .budgetCycle(budgetTypeDTO.getBudgetCycle())
                                .requiresApproval(budgetTypeDTO.requiresApproval())
                                .approvalLevel(budgetTypeDTO.getApprovalLevel())
                                .shortDisplay(budgetTypeDTO.getShortDisplay())
                                .fullDisplay(budgetTypeDTO.getFullDisplay())
                                .budgetDisplay(budgetTypeDTO.getBudgetDisplay())
                                .formalDisplay(budgetTypeDTO.getFormalDisplay())
                                .budgetClassification(budgetTypeDTO.getBudgetClassification())
                                .budgetUsageContext(budgetTypeDTO.getBudgetUsageContext())
                                .monitoringFrequency(budgetTypeDTO.getMonitoringFrequency())
                                .allocationMethod(budgetTypeDTO.getAllocationMethod())
                                .varianceTolerance(budgetTypeDTO.getVarianceTolerance())
                                .reportingRequirements(budgetTypeDTO.getReportingRequirements())
                                .budgetControlMeasures(budgetTypeDTO.getBudgetControlMeasures())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting budget type info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class BudgetTypeInfoResponse {
        private BudgetTypeDTO budgetTypeMetadata;
        private String defaultDesignation;
        private String defaultAcronym;
        private String displayText;
        private String displayAcronym;
        private Boolean isMultilingual;
        private String[] availableLanguages;
        private String budgetCategory;
        private String budgetPriority;
        private String budgetScope;
        private String budgetCycle;
        private Boolean requiresApproval;
        private String approvalLevel;
        private String shortDisplay;
        private String fullDisplay;
        private String budgetDisplay;
        private String formalDisplay;
        private String budgetClassification;
        private String budgetUsageContext;
        private String monitoringFrequency;
        private String allocationMethod;
        private String varianceTolerance;
        private String reportingRequirements;
        private String budgetControlMeasures;
    }
}