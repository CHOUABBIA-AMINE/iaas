/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BlocController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class 
 *	@Layer		: Controller
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.service.BlocService;
import dz.mdn.iaas.common.environment.dto.BlocDTO;

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
@RequestMapping("/bloc")
@RequiredArgsConstructor
@Slf4j
public class BlocController {

    private final BlocService blocService;

    // ========== POST ONE BLOC ==========

    @PostMapping
    public ResponseEntity<BlocDTO> createBloc(@Valid @RequestBody BlocDTO blocDTO) {
        log.info("Creating bloc with codes: {} (AR) | {} (LT)", 
                blocDTO.getCodeAr(), blocDTO.getCodeLt());
        
        BlocDTO createdBloc = blocService.createBloc(blocDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBloc);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<BlocDTO> getBlocMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for bloc ID: {}", id);
        
        BlocDTO blocMetadata = blocService.getBlocById(id);
        
        return ResponseEntity.ok(blocMetadata);
    }

    @GetMapping("/code-ar/{codeAr}")
    public ResponseEntity<BlocDTO> getBlocByCodeAr(@PathVariable String codeAr) {
        log.debug("Getting bloc by Arabic code: {}", codeAr);
        
        return blocService.findByCodeAr(codeAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code-lt/{codeLt}")
    public ResponseEntity<BlocDTO> getBlocByCodeLt(@PathVariable String codeLt) {
        log.debug("Getting bloc by Latin code: {}", codeLt);
        
        return blocService.findByCodeLt(codeLt)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloc(@PathVariable Long id) {
        log.info("Deleting bloc with ID: {}", id);
        
        blocService.deleteBloc(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<BlocDTO>> getAllBlocs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "codeLt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all blocs - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<BlocDTO> blocs = blocService.getAllBlocs(pageable);
        
        return ResponseEntity.ok(blocs);
    }

    // ========== ADDITIONAL SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<BlocDTO>> searchBlocs(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "codeLt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching blocs with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<BlocDTO> blocs = blocService.searchBlocs(query, pageable);
        
        return ResponseEntity.ok(blocs);
    }

    @GetMapping("/search/code-ar")
    public ResponseEntity<Page<BlocDTO>> searchByArabicCode(
            @RequestParam String codeAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching blocs by Arabic code: {}", codeAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "codeAr"));
        Page<BlocDTO> blocs = blocService.searchByCodeAr(codeAr, pageable);
        
        return ResponseEntity.ok(blocs);
    }

    @GetMapping("/search/code-lt")
    public ResponseEntity<Page<BlocDTO>> searchByLatinCode(
            @RequestParam String codeLt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching blocs by Latin code: {}", codeLt);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "codeLt"));
        Page<BlocDTO> blocs = blocService.searchByCodeLt(codeLt, pageable);
        
        return ResponseEntity.ok(blocs);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<BlocDTO> updateBloc(
            @PathVariable Long id,
            @Valid @RequestBody BlocDTO blocDTO) {
        
        log.info("Updating bloc with ID: {}", id);
        
        BlocDTO updatedBloc = blocService.updateBloc(id, blocDTO);
        
        return ResponseEntity.ok(updatedBloc);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlocDTO> partialUpdateBloc(
            @PathVariable Long id,
            @RequestBody BlocDTO blocDTO) {
        
        log.info("Partially updating bloc with ID: {}", id);
        
        BlocDTO updatedBloc = blocService.partialUpdateBloc(id, blocDTO);
        
        return ResponseEntity.ok(updatedBloc);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkBlocExists(@PathVariable Long id) {
        log.debug("Checking existence of bloc ID: {}", id);
        
        boolean exists = blocService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code-ar/{codeAr}")
    public ResponseEntity<Boolean> checkBlocExistsByCodeAr(@PathVariable String codeAr) {
        log.debug("Checking existence by Arabic code: {}", codeAr);
        
        boolean exists = blocService.existsByCodeAr(codeAr);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code-lt/{codeLt}")
    public ResponseEntity<Boolean> checkBlocExistsByCodeLt(@PathVariable String codeLt) {
        log.debug("Checking existence by Latin code: {}", codeLt);
        
        boolean exists = blocService.existsByCodeLt(codeLt);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getBlocsCount() {
        log.debug("Getting total count of blocs");
        
        Long count = blocService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<BlocInfoResponse> getBlocInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for bloc ID: {}", id);
        
        try {
            return blocService.findOne(id)
                    .map(blocDTO -> {
                        BlocInfoResponse response = BlocInfoResponse.builder()
                                .blocMetadata(blocDTO)
                                .hasArabicCode(blocDTO.getCodeAr() != null && !blocDTO.getCodeAr().trim().isEmpty())
                                .hasLatinCode(blocDTO.getCodeLt() != null && !blocDTO.getCodeLt().trim().isEmpty())
                                .isValid(blocDTO.isValid())
                                .displayText(blocDTO.getDisplayText())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting bloc info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class BlocInfoResponse {
        private BlocDTO blocMetadata;
        private Boolean hasArabicCode;
        private Boolean hasLatinCode;
        private Boolean isValid;
        private String displayText;
    }
}
