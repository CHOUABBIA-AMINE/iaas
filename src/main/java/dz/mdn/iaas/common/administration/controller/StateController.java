/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StateController
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.StateService;
import dz.mdn.iaas.common.administration.dto.StateDTO;

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
@RequestMapping("/state")
@RequiredArgsConstructor
@Slf4j
public class StateController {

    private final StateService stateService;

    // ========== POST ONE STATE ==========

    @PostMapping
    public ResponseEntity<StateDTO> createState(@Valid @RequestBody StateDTO stateDTO) {
        log.info("Creating state with code: {} and designations: {} | {}", 
                stateDTO.getCode(), stateDTO.getDesignationAr(), stateDTO.getDesignationLt());
        
        StateDTO createdState = stateService.createState(stateDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdState);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> getStateMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for state ID: {}", id);
        
        StateDTO stateMetadata = stateService.getStateById(id);
        
        return ResponseEntity.ok(stateMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<StateDTO> getStateByCode(@PathVariable String code) {
        log.debug("Getting state by code: {}", code);
        
        return stateService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<StateDTO> getStateByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting state by Arabic designation: {}", designationAr);
        
        return stateService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/designation-lt/{designationLt}")
    public ResponseEntity<StateDTO> getStateByDesignationLt(@PathVariable String designationLt) {
        log.debug("Getting state by Latin designation: {}", designationLt);
        
        return stateService.findByDesignationLt(designationLt)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        log.info("Deleting state with ID: {}", id);
        
        stateService.deleteState(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<StateDTO>> getAllStates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all states - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StateDTO> states = stateService.getAllStates(pageable);
        
        return ResponseEntity.ok(states);
    }

    // ========== ADDITIONAL UTILITY ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<StateDTO>> searchStates(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching states with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StateDTO> states = stateService.searchStates(query, pageable);
        
        return ResponseEntity.ok(states);
    }

    @GetMapping("/search/code")
    public ResponseEntity<Page<StateDTO>> searchByCode(
            @RequestParam int code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching states by code: {}", code);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<StateDTO> states = stateService.searchByCode(code, pageable);
        
        return ResponseEntity.ok(states);
    }

    @GetMapping("/search/arabic")
    public ResponseEntity<Page<StateDTO>> searchByArabicDesignation(
            @RequestParam String designationAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching states by Arabic designation: {}", designationAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationAr"));
        Page<StateDTO> states = stateService.searchByDesignationAr(designationAr, pageable);
        
        return ResponseEntity.ok(states);
    }

    @GetMapping("/search/latin")
    public ResponseEntity<Page<StateDTO>> searchByLatinDesignation(
            @RequestParam String designationLt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching states by Latin designation: {}", designationLt);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationLt"));
        Page<StateDTO> states = stateService.searchByDesignationLt(designationLt, pageable);
        
        return ResponseEntity.ok(states);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> updateState(
            @PathVariable Long id,
            @Valid @RequestBody StateDTO stateDTO) {
        
        log.info("Updating state with ID: {}", id);
        
        StateDTO updatedState = stateService.updateState(id, stateDTO);
        
        return ResponseEntity.ok(updatedState);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StateDTO> partialUpdateState(
            @PathVariable Long id,
            @RequestBody StateDTO stateDTO) {
        
        log.info("Partially updating state with ID: {}", id);
        
        StateDTO updatedState = stateService.partialUpdateState(id, stateDTO);
        
        return ResponseEntity.ok(updatedState);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkStateExists(@PathVariable Long id) {
        log.debug("Checking existence of state ID: {}", id);
        
        boolean exists = stateService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkStateExistsByCode(@PathVariable int code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = stateService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-ar/{designationAr}")
    public ResponseEntity<Boolean> checkStateExistsByDesignationAr(@PathVariable String designationAr) {
        log.debug("Checking existence by Arabic designation: {}", designationAr);
        
        boolean exists = stateService.existsByDesignationAr(designationAr);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-lt/{designationLt}")
    public ResponseEntity<Boolean> checkStateExistsByDesignationLt(@PathVariable String designationLt) {
        log.debug("Checking existence by Latin designation: {}", designationLt);
        
        boolean exists = stateService.existsByDesignationLt(designationLt);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getStatesCount() {
        log.debug("Getting total count of states");
        
        Long count = stateService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<StateInfoResponse> getStateInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for state ID: {}", id);
        
        try {
            return stateService.findOne(id)
                    .map(stateDTO -> {
                        StateInfoResponse response = StateInfoResponse.builder()
                                .stateMetadata(stateDTO)
                                .hasCode(stateDTO.getCode() != 0 )
                                .hasArabicDesignation(stateDTO.getDesignationAr() != null && !stateDTO.getDesignationAr().trim().isEmpty())
                                .hasLatinDesignation(stateDTO.getDesignationLt() != null && !stateDTO.getDesignationLt().trim().isEmpty())
                                .isComplete(stateDTO.isComplete())
                                .displayText(stateDTO.getDisplayText())
                                .displayTextAr(stateDTO.getDisplayTextAr())
                                .availableLanguages(getAvailableLanguages(stateDTO))
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting state info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== HELPER METHODS ==========

    private String[] getAvailableLanguages(StateDTO state) {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (state.getCode() != 0) {
            languages.add("code");
        }
        if (state.getDesignationAr() != null && !state.getDesignationAr().trim().isEmpty()) {
            languages.add("arabic");
        }
        if (state.getDesignationLt() != null && !state.getDesignationLt().trim().isEmpty()) {
            languages.add("latin");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class StateInfoResponse {
        private StateDTO stateMetadata;
        private Boolean hasCode;
        private Boolean hasArabicDesignation;
        private Boolean hasLatinDesignation;
        private Boolean isComplete;
        private String displayText;
        private String displayTextAr;
        private String[] availableLanguages;
    }
}
