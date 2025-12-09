/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CountryController
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.CountryService;
import dz.mdn.iaas.common.administration.dto.CountryDTO;

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

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
@Slf4j
public class CountryController {

    private final CountryService countryService;

    // ========== POST ONE COUNTRY ==========

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@Valid @RequestBody CountryDTO countryDTO) {
        log.info("Creating country with French designation: {}", countryDTO.getDesignationFr());
        
        CountryDTO createdCountry = countryService.createCountry(countryDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for country ID: {}", id);
        
        CountryDTO countryMetadata = countryService.getCountryById(id);
        
        return ResponseEntity.ok(countryMetadata);
    }

    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<CountryDTO> getCountryByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting country by French designation: {}", designationFr);
        
        return countryService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        log.info("Deleting country with ID: {}", id);
        
        countryService.deleteCountry(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<CountryDTO>> getAllCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all countries - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<CountryDTO> countries = countryService.getAllCountries(pageable);
        
        return ResponseEntity.ok(countries);
    }

    // ========== ADDITIONAL UTILITY ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<CountryDTO>> searchCountries(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching countries with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<CountryDTO> countries = countryService.searchCountries(query, pageable);
        
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/search/arabic")
    public ResponseEntity<Page<CountryDTO>> searchByArabicDesignation(
            @RequestParam String designationAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching countries by Arabic designation: {}", designationAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationAr"));
        Page<CountryDTO> countries = countryService.searchByDesignationAr(designationAr, pageable);
        
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/search/english")
    public ResponseEntity<Page<CountryDTO>> searchByEnglishDesignation(
            @RequestParam String designationEn,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching countries by English designation: {}", designationEn);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationEn"));
        Page<CountryDTO> countries = countryService.searchByDesignationEn(designationEn, pageable);
        
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/search/french")
    public ResponseEntity<Page<CountryDTO>> searchByFrenchDesignation(
            @RequestParam String designationFr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching countries by French designation: {}", designationFr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<CountryDTO> countries = countryService.searchByDesignationFr(designationFr, pageable);
        
        return ResponseEntity.ok(countries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(
            @PathVariable Long id,
            @Valid @RequestBody CountryDTO countryDTO) {
        
        log.info("Updating country with ID: {}", id);
        
        CountryDTO updatedCountry = countryService.updateCountry(id, countryDTO);
        
        return ResponseEntity.ok(updatedCountry);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CountryDTO> partialUpdateCountry(
            @PathVariable Long id,
            @RequestBody CountryDTO countryDTO) {
        
        log.info("Partially updating country with ID: {}", id);
        
        CountryDTO updatedCountry = countryService.partialUpdateCountry(id, countryDTO);
        
        return ResponseEntity.ok(updatedCountry);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkCountryExists(@PathVariable Long id) {
        log.debug("Checking existence of country ID: {}", id);
        
        boolean exists = countryService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkCountryExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = countryService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCountriesCount() {
        log.debug("Getting total count of countries");
        
        Long count = countryService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<CountryInfoResponse> getCountryInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for country ID: {}", id);
        
        try {
            return countryService.findOne(id)
                    .map(countryDTO -> {
                        CountryInfoResponse response = CountryInfoResponse.builder()
                                .countryMetadata(countryDTO)
                                .hasArabicDesignation(countryDTO.getDesignationAr() != null)
                                .hasEnglishDesignation(countryDTO.getDesignationEn() != null)
                                .hasFrenchDesignation(countryDTO.getDesignationFr() != null)
                                .defaultLanguage("french")
                                .availableLanguages(getAvailableLanguages(countryDTO))
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting country info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== HELPER METHODS ==========

    private String[] getAvailableLanguages(CountryDTO country) {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (country.getDesignationAr() != null && !country.getDesignationAr().trim().isEmpty()) {
            languages.add("arabic");
        }
        if (country.getDesignationEn() != null && !country.getDesignationEn().trim().isEmpty()) {
            languages.add("english");
        }
        if (country.getDesignationFr() != null && !country.getDesignationFr().trim().isEmpty()) {
            languages.add("french");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class CountryInfoResponse {
        private CountryDTO countryMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private String defaultLanguage;
        private String[] availableLanguages;
    }
}
