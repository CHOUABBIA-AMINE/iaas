/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementDirectorController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.ProcurementDirectorService;
import dz.mdn.iaas.business.core.dto.ProcurementDirectorDTO;

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
 * ProcurementDirector REST Controller
 * Handles procurement director operations: create, get metadata, delete, get all
 * Based on exact ProcurementDirector model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/procurementDirector")
@RequiredArgsConstructor
@Slf4j
public class ProcurementDirectorController {

    private final ProcurementDirectorService procurementDirectorService;

    // ========== POST ONE REALIZATION DIRECTOR ==========

    /**
     * Create new procurement director
     * Creates procurement director with multilingual designations and organizational classification
     */
    @PostMapping
    public ResponseEntity<ProcurementDirectorDTO> createProcurementDirector(@Valid @RequestBody ProcurementDirectorDTO procurementDirectorDTO) {
        log.info("Creating procurement director with French designation: {} and designations: AR={}, EN={}", 
                procurementDirectorDTO.getDesignationFr(), procurementDirectorDTO.getDesignationAr(), 
                procurementDirectorDTO.getDesignationEn());
        
        ProcurementDirectorDTO createdProcurementDirector = procurementDirectorService.createProcurementDirector(procurementDirectorDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcurementDirector);
    }

    // ========== GET METADATA ==========

    /**
     * Get procurement director metadata by ID
     * Returns procurement director information with organizational classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProcurementDirectorDTO> getProcurementDirectorMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for procurement director ID: {}", id);
        
        ProcurementDirectorDTO procurementDirectorMetadata = procurementDirectorService.getProcurementDirectorById(id);
        
        return ResponseEntity.ok(procurementDirectorMetadata);
    }

    /**
     * Get procurement director by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ProcurementDirectorDTO> getProcurementDirectorByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting procurement director by French designation: {}", designationFr);
        
        return procurementDirectorService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get procurement director by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<ProcurementDirectorDTO> getProcurementDirectorByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting procurement director by Arabic designation: {}", designationAr);
        
        return procurementDirectorService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get procurement director by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<ProcurementDirectorDTO> getProcurementDirectorByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting procurement director by English designation: {}", designationEn);
        
        return procurementDirectorService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete procurement director by ID
     * Removes procurement director from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcurementDirector(@PathVariable Long id) {
        log.info("Deleting procurement director with ID: {}", id);
        
        procurementDirectorService.deleteProcurementDirector(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all procurement directors with pagination
     * Returns list of all procurement directors ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<ProcurementDirectorDTO>> getAllProcurementDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all procurement directors - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getAllProcurementDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search procurement directors by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProcurementDirectorDTO>> searchProcurementDirectors(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching procurement directors with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.searchProcurementDirectors(query, pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    // ========== DIRECTOR TYPE ENDPOINTS ==========

    /**
     * Get multilingual procurement directors
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getMultilingualProcurementDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual procurement directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getMultilingualProcurementDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get executive directors
     */
    @GetMapping("/executive")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getExecutiveDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting executive directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getExecutiveDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get technical directors
     */
    @GetMapping("/technical")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getTechnicalDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technical directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getTechnicalDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get project directors
     */
    @GetMapping("/project")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getProjectDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting project directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getProjectDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get operations directors
     */
    @GetMapping("/operations")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getOperationsDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting operations directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getOperationsDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get financial directors
     */
    @GetMapping("/financial")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getFinancialDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getFinancialDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get commercial directors
     */
    @GetMapping("/commercial")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getCommercialDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commercial directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getCommercialDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get HR directors
     */
    @GetMapping("/hr")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getHRDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting HR directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getHRDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get quality directors
     */
    @GetMapping("/quality")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getQualityDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting quality directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getQualityDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get regional directors
     */
    @GetMapping("/regional")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getRegionalDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting regional directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getRegionalDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get administrative directors
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getAdministrativeDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting administrative directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getAdministrativeDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    /**
     * Get high authority directors (executive/senior level)
     */
    @GetMapping("/high-authority")
    public ResponseEntity<Page<ProcurementDirectorDTO>> getHighAuthorityDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high authority directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ProcurementDirectorDTO> procurementDirectors = procurementDirectorService.getHighAuthorityDirectors(pageable);
        
        return ResponseEntity.ok(procurementDirectors);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update procurement director metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProcurementDirectorDTO> updateProcurementDirector(
            @PathVariable Long id,
            @Valid @RequestBody ProcurementDirectorDTO procurementDirectorDTO) {
        
        log.info("Updating procurement director with ID: {}", id);
        
        ProcurementDirectorDTO updatedProcurementDirector = procurementDirectorService.updateProcurementDirector(id, procurementDirectorDTO);
        
        return ResponseEntity.ok(updatedProcurementDirector);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if procurement director exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkProcurementDirectorExists(@PathVariable Long id) {
        log.debug("Checking existence of procurement director ID: {}", id);
        
        boolean exists = procurementDirectorService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if procurement director exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkProcurementDirectorExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = procurementDirectorService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of procurement directors
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getProcurementDirectorsCount() {
        log.debug("Getting total count of procurement directors");
        
        Long count = procurementDirectorService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of executive directors
     */
    @GetMapping("/count/executive")
    public ResponseEntity<Long> getExecutiveDirectorsCount() {
        log.debug("Getting count of executive directors");
        
        Long count = procurementDirectorService.getExecutiveCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of technical directors
     */
    @GetMapping("/count/technical")
    public ResponseEntity<Long> getTechnicalDirectorsCount() {
        log.debug("Getting count of technical directors");
        
        Long count = procurementDirectorService.getTechnicalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of project directors
     */
    @GetMapping("/count/project")
    public ResponseEntity<Long> getProjectDirectorsCount() {
        log.debug("Getting count of project directors");
        
        Long count = procurementDirectorService.getProjectCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get procurement director info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ProcurementDirectorInfoResponse> getProcurementDirectorInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for procurement director ID: {}", id);
        
        try {
            return procurementDirectorService.findOne(id)
                    .map(procurementDirectorDTO -> {
                        ProcurementDirectorInfoResponse response = ProcurementDirectorInfoResponse.builder()
                                .procurementDirectorMetadata(procurementDirectorDTO)
                                .hasArabicDesignation(procurementDirectorDTO.getDesignationAr() != null && !procurementDirectorDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(procurementDirectorDTO.getDesignationEn() != null && !procurementDirectorDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(procurementDirectorDTO.getDesignationFr() != null && !procurementDirectorDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(procurementDirectorDTO.isMultilingual())
                                .isExecutiveLevel(procurementDirectorDTO.isExecutiveLevel())
                                .isTechnicalDirector(procurementDirectorDTO.isTechnicalDirector())
                                .isProjectDirector(procurementDirectorDTO.isProjectDirector())
                                .hasHighAuthority(procurementDirectorDTO.hasHighAuthority())
                                .isValid(procurementDirectorDTO.isValid())
                                .defaultDesignation(procurementDirectorDTO.getDefaultDesignation())
                                .displayText(procurementDirectorDTO.getDisplayText())
                                .directorType(procurementDirectorDTO.getDirectorType())
                                .directorLevel(procurementDirectorDTO.getDirectorLevel())
                                .directorPriority(procurementDirectorDTO.getDirectorPriority())
                                .department(procurementDirectorDTO.getDepartment())
                                .initials(procurementDirectorDTO.getInitials())
                                .formalTitle(procurementDirectorDTO.getFormalTitle())
                                .shortDisplay(procurementDirectorDTO.getShortDisplay())
                                .fullDisplay(procurementDirectorDTO.getFullDisplay())
                                .displayWithType(procurementDirectorDTO.getDisplayWithType())
                                .availableLanguages(procurementDirectorDTO.getAvailableLanguages())
                                .comparisonKey(procurementDirectorDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting procurement director info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ProcurementDirectorInfoResponse {
        private ProcurementDirectorDTO procurementDirectorMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isExecutiveLevel;
        private Boolean isTechnicalDirector;
        private Boolean isProjectDirector;
        private Boolean hasHighAuthority;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String directorType;
        private String directorLevel;
        private Integer directorPriority;
        private String department;
        private String initials;
        private String formalTitle;
        private String shortDisplay;
        private String fullDisplay;
        private String displayWithType;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
