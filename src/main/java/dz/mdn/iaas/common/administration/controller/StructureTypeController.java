/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureTypeController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.StructureTypeService;
import dz.mdn.iaas.common.administration.dto.StructureTypeDTO;

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
 * StructureType REST Controller
 * Handles structure type operations: create, get metadata, delete, get all
 * Based on exact StructureType model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr), F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/structureType")
@RequiredArgsConstructor
@Slf4j
public class StructureTypeController {

    private final StructureTypeService structureTypeService;

    // ========== POST ONE STRUCTURE TYPE ==========

    /**
     * Create new structure type
     * Creates structure type with multilingual designations, acronyms, and organizational classification
     */
    @PostMapping
    public ResponseEntity<StructureTypeDTO> createStructureType(@Valid @RequestBody StructureTypeDTO structureTypeDTO) {
        log.info("Creating structure type with French designation: {} , designations: AR={}, EN={}", 
                structureTypeDTO.getDesignationFr(),
                structureTypeDTO.getDesignationAr(), structureTypeDTO.getDesignationEn());
        
        StructureTypeDTO createdStructureType = structureTypeService.createStructureType(structureTypeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStructureType);
    }

    // ========== GET METADATA ==========

    /**
     * Get structure type metadata by ID
     * Returns structure type information with organizational classification and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<StructureTypeDTO> getStructureTypeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for structure type ID: {}", id);
        
        StructureTypeDTO structureTypeMetadata = structureTypeService.getStructureTypeById(id);
        
        return ResponseEntity.ok(structureTypeMetadata);
    }

    /**
     * Get structure type by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<StructureTypeDTO> getStructureTypeByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting structure type by French designation: {}", designationFr);
        
        return structureTypeService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get structure type by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<StructureTypeDTO> getStructureTypeByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting structure type by Arabic designation: {}", designationAr);
        
        return structureTypeService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get structure type by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<StructureTypeDTO> getStructureTypeByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting structure type by English designation: {}", designationEn);
        
        return structureTypeService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete structure type by ID
     * Removes structure type from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStructureType(@PathVariable Long id) {
        log.info("Deleting structure type with ID: {}", id);
        
        structureTypeService.deleteStructureType(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all structure types with pagination
     * Returns list of all structure types ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<StructureTypeDTO>> getAllStructureTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all structure types - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StructureTypeDTO> structureTypes = structureTypeService.getAllStructureTypes(pageable);
        
        return ResponseEntity.ok(structureTypes);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search structure types by designation (all languages)
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<StructureTypeDTO>> searchStructureTypesByDesignation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching structure types by designation with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StructureTypeDTO> structureTypes = structureTypeService.searchStructureTypesByDesignation(query, pageable);
        
        return ResponseEntity.ok(structureTypes);
    }

    /**
     * Search structure types by designation m
     */
    @GetMapping("/search")
    public ResponseEntity<Page<StructureTypeDTO>> searchStructureTypes(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching structure types with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StructureTypeDTO> structureTypes = structureTypeService.searchStructureTypes(query, pageable);
        
        return ResponseEntity.ok(structureTypes);
    }

    // ========== STRUCTURE CATEGORY ENDPOINTS ==========

    /**
     * Get multilingual structure types
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<StructureTypeDTO>> getMultilingualStructureTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual structure types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureTypeDTO> structureTypes = structureTypeService.getMultilingualStructureTypes(pageable);
        
        return ResponseEntity.ok(structureTypes);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update structure type metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<StructureTypeDTO> updateStructureType(
            @PathVariable Long id,
            @Valid @RequestBody StructureTypeDTO structureTypeDTO) {
        
        log.info("Updating structure type with ID: {}", id);
        
        StructureTypeDTO updatedStructureType = structureTypeService.updateStructureType(id, structureTypeDTO);
        
        return ResponseEntity.ok(updatedStructureType);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if structure type exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkStructureTypeExists(@PathVariable Long id) {
        log.debug("Checking existence of structure type ID: {}", id);
        
        boolean exists = structureTypeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if structure type exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkStructureTypeExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = structureTypeService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of structure types
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getStructureTypesCount() {
        log.debug("Getting total count of structure types");
        
        Long count = structureTypeService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get structure type info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<StructureTypeInfoResponse> getStructureTypeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for structure type ID: {}", id);
        
        try {
            return structureTypeService.findOne(id)
                    .map(structureTypeDTO -> {
                        StructureTypeInfoResponse response = StructureTypeInfoResponse.builder()
                                .structureTypeMetadata(structureTypeDTO)
                                .hasArabicDesignation(structureTypeDTO.getDesignationAr() != null && !structureTypeDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(structureTypeDTO.getDesignationEn() != null && !structureTypeDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(structureTypeDTO.getDesignationFr() != null && !structureTypeDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(structureTypeDTO.isMultilingual())
                                .isValid(structureTypeDTO.isValid())
                                .defaultDesignation(structureTypeDTO.getDefaultDesignation())
                                .displayText(structureTypeDTO.getDisplayText())
                                .shortDisplay(structureTypeDTO.getShortDisplay())
                                .fullDisplay(structureTypeDTO.getFullDisplay())
                                .availableLanguages(structureTypeDTO.getAvailableLanguages())
                                .comparisonKey(structureTypeDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting structure type info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class StructureTypeInfoResponse {
        private StructureTypeDTO structureTypeMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String shortDisplay;
        private String fullDisplay;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
