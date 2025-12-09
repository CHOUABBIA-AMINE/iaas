/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AwardMethodController
 *	@CreatedOn	: 10-19-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.service.AwardMethodService;
import dz.mdn.iaas.business.consultation.dto.AwardMethodDTO;
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
 * AwardMethod REST Controller
 * Handles award method operations: post one, get metadata, delete one, get all
 * Based on exact AwardMethod model: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * Required fields: F_03 (designationFr), F_06 (acronymFr) with unique constraints
 */
@RestController
@RequestMapping("/awardMethod")
@RequiredArgsConstructor
@Slf4j
public class AwardMethodController {

    private final AwardMethodService awardMethodService;

    // ========== POST ONE AWARD METHOD ==========

    /**
     * Create new award method
     * Creates award method with multilingual designations and acronyms
     */
    @PostMapping
    public ResponseEntity<AwardMethodDTO> createAwardMethod(@Valid @RequestBody AwardMethodDTO awardMethodDTO) {
        log.info("Creating award method with French acronym: {} and designation: {}",
                awardMethodDTO.getAcronymFr(), awardMethodDTO.getDesignationFr());

        AwardMethodDTO createdAwardMethod = awardMethodService.createAwardMethod(awardMethodDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAwardMethod);
    }

    // ========== GET METADATA ==========

    /**
     * Get award method metadata by ID
     * Returns award method information with all designations and acronyms
     */
    @GetMapping("/{id}")
    public ResponseEntity<AwardMethodDTO> getAwardMethodMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for award method ID: {}", id);

        AwardMethodDTO awardMethodMetadata = awardMethodService.getAwardMethodById(id);

        return ResponseEntity.ok(awardMethodMetadata);
    }

    /**
     * Get award method by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<AwardMethodDTO> getAwardMethodByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting award method by French designation: {}", designationFr);

        return awardMethodService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get award method by French acronym (unique field F_06)
     */
    @GetMapping("/acronym-fr/{acronymFr}")
    public ResponseEntity<AwardMethodDTO> getAwardMethodByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Getting award method by French acronym: {}", acronymFr);

        return awardMethodService.findByAcronymFr(acronymFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete award method by ID
     * Removes award method from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAwardMethod(@PathVariable Long id) {
        log.info("Deleting award method with ID: {}", id);

        awardMethodService.deleteAwardMethod(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all award methods with pagination
     * Returns list of all award methods ordered by French acronym
     */
    @GetMapping
    public ResponseEntity<Page<AwardMethodDTO>> getAllAwardMethods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "acronymFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all award methods - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AwardMethodDTO> awardMethods = awardMethodService.getAllAwardMethods(pageable);

        return ResponseEntity.ok(awardMethods);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search award methods by any field (all designations and acronyms)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<AwardMethodDTO>> searchAwardMethods(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "acronymFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching award methods with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<AwardMethodDTO> awardMethods = awardMethodService.searchAwardMethods(query, pageable);

        return ResponseEntity.ok(awardMethods);
    }

    /**
     * Search award methods by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<AwardMethodDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching award methods by designation: {}", designation);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<AwardMethodDTO> awardMethods = awardMethodService.searchByDesignation(designation, pageable);

        return ResponseEntity.ok(awardMethods);
    }

    /**
     * Search award methods by acronym (all languages)
     */
    @GetMapping("/search/acronym")
    public ResponseEntity<Page<AwardMethodDTO>> searchByAcronym(
            @RequestParam String acronym,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Searching award methods by acronym: {}", acronym);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "acronymFr"));
        Page<AwardMethodDTO> awardMethods = awardMethodService.searchByAcronym(acronym, pageable);

        return ResponseEntity.ok(awardMethods);
    }

    // ========== CATEGORY ENDPOINTS ==========

    /**
     * Get award methods by category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<AwardMethodDTO>> getAwardMethodsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting award methods by category: {}", category);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "acronymFr"));
        Page<AwardMethodDTO> awardMethods = awardMethodService.getAwardMethodsByCategory(category, pageable);

        return ResponseEntity.ok(awardMethods);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update award method metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<AwardMethodDTO> updateAwardMethod(
            @PathVariable Long id,
            @Valid @RequestBody AwardMethodDTO awardMethodDTO) {

        log.info("Updating award method with ID: {}", id);

        AwardMethodDTO updatedAwardMethod = awardMethodService.updateAwardMethod(id, awardMethodDTO);

        return ResponseEntity.ok(updatedAwardMethod);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if award method exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkAwardMethodExists(@PathVariable Long id) {
        log.debug("Checking existence of award method ID: {}", id);

        boolean exists = awardMethodService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if award method exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkAwardMethodExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);

        boolean exists = awardMethodService.existsByDesignationFr(designationFr);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if award method exists by French acronym
     */
    @GetMapping("/exists/acronym-fr/{acronymFr}")
    public ResponseEntity<Boolean> checkAwardMethodExistsByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Checking existence by French acronym: {}", acronymFr);

        boolean exists = awardMethodService.existsByAcronymFr(acronymFr);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of award methods
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getAwardMethodsCount() {
        log.debug("Getting total count of award methods");

        Long count = awardMethodService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get award method info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<AwardMethodInfoResponse> getAwardMethodInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for award method ID: {}", id);

        try {
            return awardMethodService.findOne(id)
                    .map(awardMethodDTO -> {
                        AwardMethodInfoResponse response = AwardMethodInfoResponse.builder()
                                .awardMethodMetadata(awardMethodDTO)
                                .hasArabicDesignation(awardMethodDTO.getDesignationAr() != null && !awardMethodDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(awardMethodDTO.getDesignationEn() != null && !awardMethodDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(awardMethodDTO.getDesignationFr() != null && !awardMethodDTO.getDesignationFr().trim().isEmpty())
                                .hasArabicAcronym(awardMethodDTO.getAcronymAr() != null && !awardMethodDTO.getAcronymAr().trim().isEmpty())
                                .hasEnglishAcronym(awardMethodDTO.getAcronymEn() != null && !awardMethodDTO.getAcronymEn().trim().isEmpty())
                                .hasFrenchAcronym(awardMethodDTO.getAcronymFr() != null && !awardMethodDTO.getAcronymFr().trim().isEmpty())
                                .isMultilingual(awardMethodDTO.isMultilingual())
                                .hasMultilingualAcronyms(awardMethodDTO.hasMultilingualAcronyms())
                                .isValid(awardMethodDTO.isValid())
                                .isOpenTenderMethod(awardMethodDTO.isOpenTenderMethod())
                                .isNegotiatedProcedure(awardMethodDTO.isNegotiatedProcedure())
                                .defaultDesignation(awardMethodDTO.getDefaultDesignation())
                                .defaultAcronym(awardMethodDTO.getDefaultAcronym())
                                .displayText(awardMethodDTO.getDisplayText())
                                .awardMethodCategory(awardMethodDTO.getAwardMethodCategory())
                                .shortDisplay(awardMethodDTO.getShortDisplay())
                                .fullDisplay(awardMethodDTO.getFullDisplay())
                                .availableLanguages(awardMethodDTO.getAvailableLanguages())
                                .comparisonKey(awardMethodDTO.getComparisonKey())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting award method info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class AwardMethodInfoResponse {
        private AwardMethodDTO awardMethodMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean hasArabicAcronym;
        private Boolean hasEnglishAcronym;
        private Boolean hasFrenchAcronym;
        private Boolean isMultilingual;
        private Boolean hasMultilingualAcronyms;
        private Boolean isValid;
        private Boolean isOpenTenderMethod;
        private Boolean isNegotiatedProcedure;
        private String defaultDesignation;
        private String defaultAcronym;
        private String displayText;
        private String awardMethodCategory;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}