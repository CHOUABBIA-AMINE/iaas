/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocalityController
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.LocalityService;
import dz.mdn.iaas.common.administration.dto.LocalityDTO;

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

import java.util.List;

@RestController
@RequestMapping("/locality")
@RequiredArgsConstructor
@Slf4j
public class LocalityController {

    private final LocalityService localityService;

    // ========== POST ONE LOCALITY ==========

    @PostMapping
    public ResponseEntity<LocalityDTO> createLocality(@Valid @RequestBody LocalityDTO localityDTO) {
        log.info("Creating locality with code: {} and designations: {} | {} for state ID: {}", 
                localityDTO.getCode(), localityDTO.getDesignationAr(), localityDTO.getDesignationLt(), localityDTO.getStateId());
        
        LocalityDTO createdLocality = localityService.createLocality(localityDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocality);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<LocalityDTO> getLocalityMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for locality ID: {}", id);
        
        LocalityDTO localityMetadata = localityService.getLocalityById(id);
        
        return ResponseEntity.ok(localityMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<LocalityDTO> getLocalityByCode(@PathVariable int code) {
        log.debug("Getting locality by code: {}", code);
        
        return localityService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<LocalityDTO> getLocalityByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting locality by Arabic designation: {}", designationAr);
        
        return localityService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/designation-lt/{designationLt}")
    public ResponseEntity<LocalityDTO> getLocalityByDesignationLt(@PathVariable String designationLt) {
        log.debug("Getting locality by Latin designation: {}", designationLt);
        
        return localityService.findByDesignationLt(designationLt)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocality(@PathVariable Long id) {
        log.info("Deleting locality with ID: {}", id);
        
        localityService.deleteLocality(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<LocalityDTO>> getAllLocalities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all localities - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<LocalityDTO> localities = localityService.getAllLocalities(pageable);
        
        return ResponseEntity.ok(localities);
    }

    // ========== ADDITIONAL STATE-RELATED ENDPOINTS ==========

    @GetMapping("/by-state/{stateId}")
    public ResponseEntity<Page<LocalityDTO>> getLocalitiesByState(
            @PathVariable Long stateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting localities for state ID: {} - page: {}, size: {}", stateId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<LocalityDTO> localities = localityService.getLocalitiesByStateId(stateId, pageable);
        
        return ResponseEntity.ok(localities);
    }

    @GetMapping("/by-state/{stateId}/list")
    public ResponseEntity<List<LocalityDTO>> getLocalitiesByStateAsList(@PathVariable Long stateId) {
        log.debug("Getting localities list for state ID: {}", stateId);
        
        List<LocalityDTO> localities = localityService.getLocalitiesByStateIdAsList(stateId);
        
        return ResponseEntity.ok(localities);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<LocalityDTO>> searchLocalities(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching localities with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<LocalityDTO> localities = localityService.searchLocalities(query, pageable);
        
        return ResponseEntity.ok(localities);
    }

    @GetMapping("/search/by-state/{stateId}")
    public ResponseEntity<Page<LocalityDTO>> searchLocalitiesInState(
            @PathVariable Long stateId,
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching localities with query: {} in state ID: {}", query, stateId);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<LocalityDTO> localities = localityService.searchLocalitiesInState(query, stateId, pageable);
        
        return ResponseEntity.ok(localities);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<LocalityDTO> updateLocality(
            @PathVariable Long id,
            @Valid @RequestBody LocalityDTO localityDTO) {
        
        log.info("Updating locality with ID: {}", id);
        
        LocalityDTO updatedLocality = localityService.updateLocality(id, localityDTO);
        
        return ResponseEntity.ok(updatedLocality);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LocalityDTO> partialUpdateLocality(
            @PathVariable Long id,
            @RequestBody LocalityDTO localityDTO) {
        
        log.info("Partially updating locality with ID: {}", id);
        
        LocalityDTO updatedLocality = localityService.partialUpdateLocality(id, localityDTO);
        
        return ResponseEntity.ok(updatedLocality);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkLocalityExists(@PathVariable Long id) {
        log.debug("Checking existence of locality ID: {}", id);
        
        boolean exists = localityService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkLocalityExistsByCode(@PathVariable int code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = localityService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-ar/{designationAr}")
    public ResponseEntity<Boolean> checkLocalityExistsByDesignationAr(@PathVariable String designationAr) {
        log.debug("Checking existence by Arabic designation: {}", designationAr);
        
        boolean exists = localityService.existsByDesignationAr(designationAr);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-lt/{designationLt}")
    public ResponseEntity<Boolean> checkLocalityExistsByDesignationLt(@PathVariable String designationLt) {
        log.debug("Checking existence by Latin designation: {}", designationLt);
        
        boolean exists = localityService.existsByDesignationLt(designationLt);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getLocalitiesCount() {
        log.debug("Getting total count of localities");
        
        Long count = localityService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-state/{stateId}")
    public ResponseEntity<Long> getLocalitiesCountByState(@PathVariable Long stateId) {
        log.debug("Getting count of localities for state ID: {}", stateId);
        
        Long count = localityService.getCountByStateId(stateId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/state/{stateId}/has-localities")
    public ResponseEntity<Boolean> checkStateHasLocalities(@PathVariable Long stateId) {
        log.debug("Checking if state ID: {} has localities", stateId);
        
        boolean hasLocalities = localityService.hasLocalitiesInState(stateId);
        
        return ResponseEntity.ok(hasLocalities);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<LocalityInfoResponse> getLocalityInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for locality ID: {}", id);
        
        try {
            return localityService.findOne(id)
                    .map(localityDTO -> {
                        LocalityInfoResponse response = LocalityInfoResponse.builder()
                                .localityMetadata(localityDTO)
                                .hasCode(localityDTO.getCode() != 0)
                                .hasArabicDesignation(localityDTO.getDesignationAr() != null && !localityDTO.getDesignationAr().trim().isEmpty())
                                .hasLatinDesignation(localityDTO.getDesignationLt() != null && !localityDTO.getDesignationLt().trim().isEmpty())
                                .hasStateReference(localityDTO.getStateId() != null)
                                .isComplete(localityDTO.isComplete())
                                .displayText(localityDTO.getDisplayText())
                                .displayTextAr(localityDTO.getDisplayTextAr())
                                .fullDisplayText(localityDTO.getFullDisplayText())
                                .fullDisplayTextAr(localityDTO.getFullDisplayTextAr())
                                .availableLanguages(getAvailableLanguages(localityDTO))
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting locality info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== HELPER METHODS ==========

    private String[] getAvailableLanguages(LocalityDTO locality) {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (locality.getCode() != 0 ) {
            languages.add("code");
        }
        if (locality.getDesignationAr() != null && !locality.getDesignationAr().trim().isEmpty()) {
            languages.add("arabic");
        }
        if (locality.getDesignationLt() != null && !locality.getDesignationLt().trim().isEmpty()) {
            languages.add("latin");
        }
        if (locality.getStateId() != null) {
            languages.add("state_reference");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class LocalityInfoResponse {
        private LocalityDTO localityMetadata;
        private Boolean hasCode;
        private Boolean hasArabicDesignation;
        private Boolean hasLatinDesignation;
        private Boolean hasStateReference;
        private Boolean isComplete;
        private String displayText;
        private String displayTextAr;
        private String fullDisplayText;
        private String fullDisplayTextAr;
        private String[] availableLanguages;
    }
}
