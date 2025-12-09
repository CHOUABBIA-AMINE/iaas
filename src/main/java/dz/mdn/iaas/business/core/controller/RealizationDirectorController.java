/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationDirectorController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.RealizationDirectorService;
import dz.mdn.iaas.business.core.dto.RealizationDirectorDTO;

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
 * RealizationDirector REST Controller
 * Handles realization director operations: create, get metadata, delete, get all
 * Based on exact RealizationDirector model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/realizationDirector")
@RequiredArgsConstructor
@Slf4j
public class RealizationDirectorController {

    private final RealizationDirectorService realizationDirectorService;

    // ========== POST ONE REALIZATION DIRECTOR ==========

    /**
     * Create new realization director
     * Creates realization director with multilingual designations and organizational classification
     */
    @PostMapping
    public ResponseEntity<RealizationDirectorDTO> createRealizationDirector(@Valid @RequestBody RealizationDirectorDTO realizationDirectorDTO) {
        log.info("Creating realization director with French designation: {} and designations: AR={}, EN={}", 
                realizationDirectorDTO.getDesignationFr(), realizationDirectorDTO.getDesignationAr(), 
                realizationDirectorDTO.getDesignationEn());
        
        RealizationDirectorDTO createdRealizationDirector = realizationDirectorService.createRealizationDirector(realizationDirectorDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRealizationDirector);
    }

    // ========== GET METADATA ==========

    /**
     * Get realization director metadata by ID
     * Returns realization director information with organizational classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<RealizationDirectorDTO> getRealizationDirectorMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for realization director ID: {}", id);
        
        RealizationDirectorDTO realizationDirectorMetadata = realizationDirectorService.getRealizationDirectorById(id);
        
        return ResponseEntity.ok(realizationDirectorMetadata);
    }

    /**
     * Get realization director by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<RealizationDirectorDTO> getRealizationDirectorByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting realization director by French designation: {}", designationFr);
        
        return realizationDirectorService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get realization director by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<RealizationDirectorDTO> getRealizationDirectorByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting realization director by Arabic designation: {}", designationAr);
        
        return realizationDirectorService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get realization director by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<RealizationDirectorDTO> getRealizationDirectorByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting realization director by English designation: {}", designationEn);
        
        return realizationDirectorService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete realization director by ID
     * Removes realization director from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRealizationDirector(@PathVariable Long id) {
        log.info("Deleting realization director with ID: {}", id);
        
        realizationDirectorService.deleteRealizationDirector(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all realization directors with pagination
     * Returns list of all realization directors ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<RealizationDirectorDTO>> getAllRealizationDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all realization directors - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getAllRealizationDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search realization directors by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<RealizationDirectorDTO>> searchRealizationDirectors(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching realization directors with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.searchRealizationDirectors(query, pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    // ========== DIRECTOR TYPE ENDPOINTS ==========

    /**
     * Get multilingual realization directors
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<RealizationDirectorDTO>> getMultilingualRealizationDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual realization directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getMultilingualRealizationDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get executive directors
     */
    @GetMapping("/executive")
    public ResponseEntity<Page<RealizationDirectorDTO>> getExecutiveDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting executive directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getExecutiveDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get technical directors
     */
    @GetMapping("/technical")
    public ResponseEntity<Page<RealizationDirectorDTO>> getTechnicalDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technical directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getTechnicalDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get project directors
     */
    @GetMapping("/project")
    public ResponseEntity<Page<RealizationDirectorDTO>> getProjectDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting project directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getProjectDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get operations directors
     */
    @GetMapping("/operations")
    public ResponseEntity<Page<RealizationDirectorDTO>> getOperationsDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting operations directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getOperationsDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get financial directors
     */
    @GetMapping("/financial")
    public ResponseEntity<Page<RealizationDirectorDTO>> getFinancialDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getFinancialDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get commercial directors
     */
    @GetMapping("/commercial")
    public ResponseEntity<Page<RealizationDirectorDTO>> getCommercialDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commercial directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getCommercialDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get HR directors
     */
    @GetMapping("/hr")
    public ResponseEntity<Page<RealizationDirectorDTO>> getHRDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting HR directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getHRDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get quality directors
     */
    @GetMapping("/quality")
    public ResponseEntity<Page<RealizationDirectorDTO>> getQualityDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting quality directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getQualityDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get regional directors
     */
    @GetMapping("/regional")
    public ResponseEntity<Page<RealizationDirectorDTO>> getRegionalDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting regional directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getRegionalDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get administrative directors
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<RealizationDirectorDTO>> getAdministrativeDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting administrative directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getAdministrativeDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    /**
     * Get high authority directors (executive/senior level)
     */
    @GetMapping("/high-authority")
    public ResponseEntity<Page<RealizationDirectorDTO>> getHighAuthorityDirectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high authority directors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<RealizationDirectorDTO> realizationDirectors = realizationDirectorService.getHighAuthorityDirectors(pageable);
        
        return ResponseEntity.ok(realizationDirectors);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update realization director metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<RealizationDirectorDTO> updateRealizationDirector(
            @PathVariable Long id,
            @Valid @RequestBody RealizationDirectorDTO realizationDirectorDTO) {
        
        log.info("Updating realization director with ID: {}", id);
        
        RealizationDirectorDTO updatedRealizationDirector = realizationDirectorService.updateRealizationDirector(id, realizationDirectorDTO);
        
        return ResponseEntity.ok(updatedRealizationDirector);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if realization director exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkRealizationDirectorExists(@PathVariable Long id) {
        log.debug("Checking existence of realization director ID: {}", id);
        
        boolean exists = realizationDirectorService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if realization director exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkRealizationDirectorExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = realizationDirectorService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of realization directors
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getRealizationDirectorsCount() {
        log.debug("Getting total count of realization directors");
        
        Long count = realizationDirectorService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of executive directors
     */
    @GetMapping("/count/executive")
    public ResponseEntity<Long> getExecutiveDirectorsCount() {
        log.debug("Getting count of executive directors");
        
        Long count = realizationDirectorService.getExecutiveCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of technical directors
     */
    @GetMapping("/count/technical")
    public ResponseEntity<Long> getTechnicalDirectorsCount() {
        log.debug("Getting count of technical directors");
        
        Long count = realizationDirectorService.getTechnicalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of project directors
     */
    @GetMapping("/count/project")
    public ResponseEntity<Long> getProjectDirectorsCount() {
        log.debug("Getting count of project directors");
        
        Long count = realizationDirectorService.getProjectCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get realization director info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<RealizationDirectorInfoResponse> getRealizationDirectorInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for realization director ID: {}", id);
        
        try {
            return realizationDirectorService.findOne(id)
                    .map(realizationDirectorDTO -> {
                        RealizationDirectorInfoResponse response = RealizationDirectorInfoResponse.builder()
                                .realizationDirectorMetadata(realizationDirectorDTO)
                                .hasArabicDesignation(realizationDirectorDTO.getDesignationAr() != null && !realizationDirectorDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(realizationDirectorDTO.getDesignationEn() != null && !realizationDirectorDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(realizationDirectorDTO.getDesignationFr() != null && !realizationDirectorDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(realizationDirectorDTO.isMultilingual())
                                .isExecutiveLevel(realizationDirectorDTO.isExecutiveLevel())
                                .isTechnicalDirector(realizationDirectorDTO.isTechnicalDirector())
                                .isProjectDirector(realizationDirectorDTO.isProjectDirector())
                                .hasHighAuthority(realizationDirectorDTO.hasHighAuthority())
                                .isValid(realizationDirectorDTO.isValid())
                                .defaultDesignation(realizationDirectorDTO.getDefaultDesignation())
                                .displayText(realizationDirectorDTO.getDisplayText())
                                .directorType(realizationDirectorDTO.getDirectorType())
                                .directorLevel(realizationDirectorDTO.getDirectorLevel())
                                .directorPriority(realizationDirectorDTO.getDirectorPriority())
                                .department(realizationDirectorDTO.getDepartment())
                                .initials(realizationDirectorDTO.getInitials())
                                .formalTitle(realizationDirectorDTO.getFormalTitle())
                                .shortDisplay(realizationDirectorDTO.getShortDisplay())
                                .fullDisplay(realizationDirectorDTO.getFullDisplay())
                                .displayWithType(realizationDirectorDTO.getDisplayWithType())
                                .availableLanguages(realizationDirectorDTO.getAvailableLanguages())
                                .comparisonKey(realizationDirectorDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting realization director info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class RealizationDirectorInfoResponse {
        private RealizationDirectorDTO realizationDirectorMetadata;
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
