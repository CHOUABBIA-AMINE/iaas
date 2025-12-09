/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CurrencyController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.service.CurrencyService;
import dz.mdn.iaas.business.core.dto.CurrencyDTO;

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
 * Currency REST Controller
 * Handles currency operations: create, get metadata, delete, get all
 * Based on exact Currency model: F_00=id, F_01=code, F_02=designationAr, F_03=designationEn,
 * F_04=designationFr, F_05=codeAr, F_06=codeEn, F_07=codeFr
 * All fields F_01 through F_05 have unique constraints and are required
 */
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    // ========== POST ONE CURRENCY ==========

    /**
     * Create new currency
     * Creates currency with multilingual designations and dual code system
     */
    @PostMapping
    public ResponseEntity<CurrencyDTO> createCurrency(@Valid @RequestBody CurrencyDTO currencyDTO) {
        log.info("Creating currency with code: {} and designations: AR={}, EN={}, FR={}", 
                currencyDTO.getCode(), currencyDTO.getDesignationAr(), 
                currencyDTO.getDesignationEn(), currencyDTO.getDesignationFr());
        
        CurrencyDTO createdCurrency = currencyService.createCurrency(currencyDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCurrency);
    }

    // ========== GET METADATA ==========

    /**
     * Get currency metadata by ID
     * Returns currency information with all designations and codes
     */
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> getCurrencyMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for currency ID: {}", id);
        
        CurrencyDTO currencyMetadata = currencyService.getCurrencyById(id);
        
        return ResponseEntity.ok(currencyMetadata);
    }

    /**
     * Get currency by Arabic designation (unique field F_01)
     */
    @GetMapping("/designation-ar/{code}")
    public ResponseEntity<CurrencyDTO> getCurrencyByCode(@PathVariable String code) {
        log.debug("Getting currency by code: {}", code);
        
        return currencyService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get currency by Arabic designation (unique field F_02)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<CurrencyDTO> getCurrencyByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting currency by Arabic designation: {}", designationAr);
        
        return currencyService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get currency by English designation (unique field F_03)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<CurrencyDTO> getCurrencyByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting currency by English designation: {}", designationEn);
        
        return currencyService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get currency by French designation (unique field F_04)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<CurrencyDTO> getCurrencyByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting currency by French designation: {}", designationFr);
        
        return currencyService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get currency by Arabic code (unique field F_05)
     */
    @GetMapping("/code-ar/{acronymAr}")
    public ResponseEntity<CurrencyDTO> getCurrencyByAcronymAr(@PathVariable String acronymAr) {
        log.debug("Getting currency by Arabic acronym: {}", acronymAr);
        
        return currencyService.findByAcronymAr(acronymAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get currency by English acronym (unique field F_06)
     */
    @GetMapping("/acronym-en/{acronymEn}")
    public ResponseEntity<CurrencyDTO> getCurrencyByAcronymEn(@PathVariable String acronymEn) {
        log.debug("Getting currency by English acronym: {}", acronymEn);
        
        return currencyService.findByAcronymEn(acronymEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get currency by French acronym (unique field F_07)
     */
    @GetMapping("/acronym-fr/{acronymFr}")
    public ResponseEntity<CurrencyDTO> getCurrencyByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Getting currency by French acronym: {}", acronymFr);
        
        return currencyService.findByAcronymFr(acronymFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete currency by ID
     * Removes currency from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        log.info("Deleting currency with ID: {}", id);
        
        currencyService.deleteCurrency(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all currencies with pagination
     * Returns list of all currencies ordered by Latin code
     */
    @GetMapping
    public ResponseEntity<Page<CurrencyDTO>> getAllCurrencies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all currencies - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<CurrencyDTO> currencies = currencyService.getAllCurrencies(pageable);
        
        return ResponseEntity.ok(currencies);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search currencies by any field (all designations and codes)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<CurrencyDTO>> searchCurrencies(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching currencies with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<CurrencyDTO> currencies = currencyService.searchCurrencies(query, pageable);
        
        return ResponseEntity.ok(currencies);
    }

    /**
     * Search currencies by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<CurrencyDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching currencies by designation: {}", designation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<CurrencyDTO> currencies = currencyService.searchByDesignation(designation, pageable);
        
        return ResponseEntity.ok(currencies);
    }

    /**
     * Search currencies by acronym 
     */
    @GetMapping("/search/acronym")
    public ResponseEntity<Page<CurrencyDTO>> searchByAcronym(
            @RequestParam String acronym,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching currencies by acronym: {}", acronym);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<CurrencyDTO> currencies = currencyService.searchByAcronym(acronym, pageable);
        
        return ResponseEntity.ok(currencies);
    }

    // ========== CURRENCY TYPE ENDPOINTS ==========

    /**
     * Get major international currencies
     */
    @GetMapping("/major")
    public ResponseEntity<Page<CurrencyDTO>> getMajorCurrencies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting major international currencies");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "codeLt"));
        Page<CurrencyDTO> currencies = currencyService.getMajorCurrencies(pageable);
        
        return ResponseEntity.ok(currencies);
    }

    /**
     * Get regional currencies (Africa, Middle East)
     */
    @GetMapping("/regional")
    public ResponseEntity<Page<CurrencyDTO>> getRegionalCurrencies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting regional currencies");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "codeLt"));
        Page<CurrencyDTO> currencies = currencyService.getRegionalCurrencies(pageable);
        
        return ResponseEntity.ok(currencies);
    }

    /**
     * Get ISO standard currencies (3-letter Latin codes)
     */
    /*@GetMapping("/iso-standard")
    public ResponseEntity<Page<CurrencyDTO>> getISOStandardCurrencies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting ISO standard currencies");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "codeLt"));
        Page<CurrencyDTO> currencies = currencyService.getISOStandardCurrencies(pageable);
        
        return ResponseEntity.ok(currencies);
    }*/

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update currency metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<CurrencyDTO> updateCurrency(
            @PathVariable Long id,
            @Valid @RequestBody CurrencyDTO currencyDTO) {
        
        log.info("Updating currency with ID: {}", id);
        
        CurrencyDTO updatedCurrency = currencyService.updateCurrency(id, currencyDTO);
        
        return ResponseEntity.ok(updatedCurrency);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if currency exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkCurrencyExists(@PathVariable Long id) {
        log.debug("Checking existence of currency ID: {}", id);
        
        boolean exists = currencyService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if currency exists by code
     */
    @GetMapping("/exists/code-ar/{code}")
    public ResponseEntity<Boolean> checkCurrencyExistsByCode(@PathVariable String code) {
        log.debug("Checking existence by Arabic code: {}", code);
        
        boolean exists = currencyService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if currency exists by Arabic designation
     */
    @GetMapping("/exists/designation-ar/{designationAr}")
    public ResponseEntity<Boolean> checkCurrencyExistsByDesignationAr(@PathVariable String designationAr) {
        log.debug("Checking existence by Arabic designation: {}", designationAr);
        
        boolean exists = currencyService.existsByDesignationAr(designationAr);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if currency exists by English designation
     */
    @GetMapping("/exists/designation-en/{designationEn}")
    public ResponseEntity<Boolean> checkCurrencyExistsByDesignationEn(@PathVariable String designationEn) {
        log.debug("Checking existence by English designation: {}", designationEn);
        
        boolean exists = currencyService.existsByDesignationEn(designationEn);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if currency exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkCurrencyExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = currencyService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if currency exists by Arabic acronym
     */
    @GetMapping("/exists/code-ar/{acronymAr}")
    public ResponseEntity<Boolean> checkCurrencyExistsByAcronymAr(@PathVariable String acronymAr) {
        log.debug("Checking existence by Arabic code: {}", acronymAr);
        
        boolean exists = currencyService.existsByAcronymAr(acronymAr);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if currency exists by French acronym
     */
    @GetMapping("/exists/code-ar/{acronymEn}")
    public ResponseEntity<Boolean> checkCurrencyExistsByAcronymEn(@PathVariable String acronymEn) {
        log.debug("Checking existence by Arabic code: {}", acronymEn);
        
        boolean exists = currencyService.existsByAcronymEn(acronymEn);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if currency exists by English acronym
     */
    @GetMapping("/exists/code-ar/{acronymFr}")
    public ResponseEntity<Boolean> checkCurrencyExistsByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Checking existence by Arabic code: {}", acronymFr);
        
        boolean exists = currencyService.existsByAcronymFr(acronymFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of currencies
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getCurrenciesCount() {
        log.debug("Getting total count of currencies");
        
        Long count = currencyService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get currency info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<CurrencyInfoResponse> getCurrencyInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for currency ID: {}", id);
        
        try {
            return currencyService.findOne(id)
                    .map(currencyDTO -> {
                        CurrencyInfoResponse response = CurrencyInfoResponse.builder()
                                .currencyMetadata(currencyDTO)
                                .hasCode(currencyDTO.getCode() != null && !currencyDTO.getCode().trim().isEmpty())
                                .hasArabicDesignation(currencyDTO.getDesignationAr() != null && !currencyDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(currencyDTO.getDesignationEn() != null && !currencyDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(currencyDTO.getDesignationFr() != null && !currencyDTO.getDesignationFr().trim().isEmpty())
                                .hasArabicAcronym(currencyDTO.getAcronymAr() != null && !currencyDTO.getAcronymAr().trim().isEmpty())
                                .hasEnglishAcronym(currencyDTO.getAcronymEn() != null && !currencyDTO.getAcronymEn().trim().isEmpty())
                                .hasFrenchAcronym(currencyDTO.getAcronymFr() != null && !currencyDTO.getAcronymFr().trim().isEmpty())
                                .isMultilingual(currencyDTO.isMultilingual())
                                .isCodeMultilingual(currencyDTO.isCodeMultilingual())
                                .isMajorCurrency(currencyDTO.isMajorCurrency())
                                .isRegionalCurrency(currencyDTO.isRegionalCurrency())
                                .isValid(currencyDTO.isValid())
                                .defaultDesignation(currencyDTO.getDefaultDesignation())
                                .defaultAcronym(currencyDTO.getDefaultAcronym())
                                .displayText(currencyDTO.getDisplayText())
                                .currencyType(currencyDTO.getCurrencyType())
                                .currencySymbol(currencyDTO.getCurrencySymbol())
                                .shortDisplay(currencyDTO.getShortDisplay())
                                .fullDisplay(currencyDTO.getFullDisplay())
                                .availableLanguages(currencyDTO.getAvailableLanguages())
                                .comparisonKey(currencyDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting currency info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class CurrencyInfoResponse {
        private CurrencyDTO currencyMetadata;
        private Boolean hasCode;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean hasArabicAcronym;
        private Boolean hasEnglishAcronym;
        private Boolean hasFrenchAcronym;
        private Boolean isMultilingual;
        private Boolean isCodeMultilingual;
        private Boolean isMajorCurrency;
        private Boolean isRegionalCurrency;
        private Boolean isValid;
        private String defaultDesignation;
        private String defaultAcronym;
        private String displayText;
        private String currencyType;
        private String currencySymbol;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
