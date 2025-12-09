/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.StructureService;
import dz.mdn.iaas.common.administration.dto.StructureDTO;

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

/**
 * Structure REST Controller
 * Handles structure operations: create, get metadata, delete, get all
 * Based on exact Structure model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr, F_07=structureType, F_08=structureUp
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_07 (structureType) is required foreign key
 * F_08 (structureUp) is optional foreign key (self-reference for hierarchy)
 * F_01 (designationAr), F_02 (designationEn), F_04 (acronymAr), F_05 (acronymEn) are optional
 */
@RestController
@RequestMapping("/structure")
@RequiredArgsConstructor
@Slf4j
public class StructureController {

    private final StructureService structureService;

    // ========== POST ONE STRUCTURE ==========

    /**
     * Create new structure
     * Creates structure with multilingual designations, acronyms, organizational hierarchy, and type classification
     */
    @PostMapping
    public ResponseEntity<StructureDTO> createStructure(@Valid @RequestBody StructureDTO structureDTO) {
        log.info("Creating structure with French designation: {} and acronym: {}, Type ID: {}, Parent ID: {}", 
                structureDTO.getDesignationFr(), structureDTO.getAcronymFr(),
                structureDTO.getStructureTypeId(), structureDTO.getStructureUpId());
        
        StructureDTO createdStructure = structureService.createStructure(structureDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStructure);
    }

    // ========== GET METADATA ==========

    /**
     * Get structure metadata by ID
     * Returns structure information with organizational hierarchy, type classification, and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<StructureDTO> getStructureMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for structure ID: {}", id);
        
        StructureDTO structureMetadata = structureService.getStructureById(id);
        
        return ResponseEntity.ok(structureMetadata);
    }

    /**
     * Get structure by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<StructureDTO> getStructureByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting structure by French designation: {}", designationFr);
        
        return structureService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get structure by French acronym (unique field F_06)
     */
    @GetMapping("/acronym-fr/{acronymFr}")
    public ResponseEntity<StructureDTO> getStructureByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Getting structure by French acronym: {}", acronymFr);
        
        return structureService.findByAcronymFr(acronymFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get structures by structure type ID (F_07)
     */
    @GetMapping("/type/{typeId}")
    public ResponseEntity<Page<StructureDTO>> getStructuresByType(
            @PathVariable Long typeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting structures for type ID: {}", typeId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.findByStructureTypeId(typeId, pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Get structures by parent structure ID (F_08) - direct children
     */
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<Page<StructureDTO>> getStructuresByParent(
            @PathVariable Long parentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting child structures for parent ID: {}", parentId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.findByParentStructureId(parentId, pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Get root structures (no parent - F_08 is null)
     */
    @GetMapping("/roots")
    public ResponseEntity<Page<StructureDTO>> getRootStructures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting root structures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.findRootStructures(pageable);
        
        return ResponseEntity.ok(structures);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete structure by ID
     * Removes structure from the system (prevents deletion if it has children)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStructure(@PathVariable Long id) {
        log.info("Deleting structure with ID: {}", id);
        
        structureService.deleteStructure(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all structures with pagination
     * Returns list of all structures ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<StructureDTO>> getAllStructures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all structures - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StructureDTO> structures = structureService.getAllStructures(pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Get all structures ordered by hierarchy
     */
    @GetMapping("/ordered-by-hierarchy")
    public ResponseEntity<Page<StructureDTO>> getAllStructuresOrderedByHierarchy(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting all structures ordered by hierarchy");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<StructureDTO> structures = structureService.getAllStructuresOrderedByHierarchy(pageable);
        
        return ResponseEntity.ok(structures);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search structures by designation or acronym (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<StructureDTO>> searchStructures(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching structures with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StructureDTO> structures = structureService.searchStructures(query, pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Search structures with type and parent context
     */
    @GetMapping("/search/context")
    public ResponseEntity<Page<StructureDTO>> searchStructuresWithContext(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching structures with context for query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<StructureDTO> structures = structureService.searchStructuresWithContext(query, pageable);
        
        return ResponseEntity.ok(structures);
    }

    // ========== HIERARCHY ENDPOINTS ==========

    /**
     * Get multilingual structures
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<StructureDTO>> getMultilingualStructures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual structures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.getMultilingualStructures(pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Get structures with children
     */
    @GetMapping("/with-children")
    public ResponseEntity<Page<StructureDTO>> getStructuresWithChildren(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting structures with children");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.findStructuresWithChildren(pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Get leaf structures (no children)
     */
    @GetMapping("/leaves")
    public ResponseEntity<Page<StructureDTO>> getLeafStructures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting leaf structures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.findLeafStructures(pageable);
        
        return ResponseEntity.ok(structures);
    }

    // ========== HIERARCHY LEVEL ENDPOINTS ==========

    /**
     * Get structures by hierarchy level
     */
    @GetMapping("/level/{level}")
    public ResponseEntity<Page<StructureDTO>> getStructuresByLevel(
            @PathVariable int level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting structures at level: {}", level);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.findStructuresByLevel(level, pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Get organizational level structures
     */
    @GetMapping("/level/organizational")
    public ResponseEntity<Page<StructureDTO>> getOrganizationalLevelStructures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting organizational level structures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.getOrganizationalLevelStructures(pageable);
        
        return ResponseEntity.ok(structures);
    }

    /**
     * Get departmental level structures
     */
    @GetMapping("/level/departmental")
    public ResponseEntity<Page<StructureDTO>> getDepartmentalLevelStructures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting departmental level structures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.getDepartmentalLevelStructures(pageable);
        
        return ResponseEntity.ok(structures);
    }

    // ========== TYPE-BASED ENDPOINTS ==========

    /**
     * Find structures by structure type designation
     */
    @GetMapping("/type-designation/{typeDesignation}")
    public ResponseEntity<Page<StructureDTO>> getStructuresByTypeDesignation(
            @PathVariable String typeDesignation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Finding structures by type designation: {}", typeDesignation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<StructureDTO> structures = structureService.findByStructureTypeDesignation(typeDesignation, pageable);
        
        return ResponseEntity.ok(structures);
    }

    // ========== UTILITY ENDPOINTS ==========

    /**
     * Get potential parents for a structure (prevent circular references)
     */
    @GetMapping("/{id}/potential-parents")
    public ResponseEntity<List<StructureDTO>> getPotentialParents(@PathVariable Long id) {
        log.debug("Getting potential parents for structure ID: {}", id);
        
        List<StructureDTO> potentialParents = structureService.findPotentialParents(id);
        
        return ResponseEntity.ok(potentialParents);
    }

    /**
     * Get direct children count
     */
    @GetMapping("/{id}/children-count")
    public ResponseEntity<Long> getDirectChildrenCount(@PathVariable Long id) {
        log.debug("Getting direct children count for structure ID: {}", id);
        
        Long count = structureService.getDirectChildrenCount(id);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Check if structure is ancestor of another
     */
    @GetMapping("/{ancestorId}/is-ancestor-of/{descendantId}")
    public ResponseEntity<Boolean> isAncestorOf(@PathVariable Long ancestorId, @PathVariable Long descendantId) {
        log.debug("Checking if structure {} is ancestor of {}", ancestorId, descendantId);
        
        boolean isAncestor = structureService.isAncestorOf(ancestorId, descendantId);
        
        return ResponseEntity.ok(isAncestor);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update structure metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<StructureDTO> updateStructure(
            @PathVariable Long id,
            @Valid @RequestBody StructureDTO structureDTO) {
        
        log.info("Updating structure with ID: {}", id);
        
        StructureDTO updatedStructure = structureService.updateStructure(id, structureDTO);
        
        return ResponseEntity.ok(updatedStructure);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if structure exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkStructureExists(@PathVariable Long id) {
        log.debug("Checking existence of structure ID: {}", id);
        
        boolean exists = structureService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if structure exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkStructureExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = structureService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if structure exists by French acronym
     */
    @GetMapping("/exists/acronym-fr/{acronymFr}")
    public ResponseEntity<Boolean> checkStructureExistsByAcronymFr(@PathVariable String acronymFr) {
        log.debug("Checking existence by French acronym: {}", acronymFr);
        
        boolean exists = structureService.existsByAcronymFr(acronymFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of structures
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getStructuresCount() {
        log.debug("Getting total count of structures");
        
        Long count = structureService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count by structure type
     */
    @GetMapping("/count/type/{typeId}")
    public ResponseEntity<Long> getCountByType(@PathVariable Long typeId) {
        log.debug("Getting count for type ID: {}", typeId);
        
        Long count = structureService.getCountByType(typeId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of root structures
     */
    @GetMapping("/count/roots")
    public ResponseEntity<Long> getRootStructuresCount() {
        log.debug("Getting count of root structures");
        
        Long count = structureService.getRootStructuresCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get structure info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<StructureInfoResponse> getStructureInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for structure ID: {}", id);
        
        try {
            return structureService.findOne(id)
                    .map(structureDTO -> {
                        StructureInfoResponse response = StructureInfoResponse.builder()
                                .structureMetadata(structureDTO)
                                .hasArabicDesignation(structureDTO.getDesignationAr() != null && !structureDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(structureDTO.getDesignationEn() != null && !structureDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(structureDTO.getDesignationFr() != null && !structureDTO.getDesignationFr().trim().isEmpty())
                                .hasArabicAcronym(structureDTO.getAcronymAr() != null && !structureDTO.getAcronymAr().trim().isEmpty())
                                .hasEnglishAcronym(structureDTO.getAcronymEn() != null && !structureDTO.getAcronymEn().trim().isEmpty())
                                .hasFrenchAcronym(structureDTO.getAcronymFr() != null && !structureDTO.getAcronymFr().trim().isEmpty())
                                .isMultilingual(structureDTO.isMultilingual())
                                .hasParent(structureDTO.hasParent())
                                .isRoot(structureDTO.isRoot())
                                .canHaveSubordinates(structureDTO.canHaveSubordinates())
                                .canBeRestructured(structureDTO.canBeRestructured())
                                .requiresParentApproval(structureDTO.requiresParentApproval())
                                .isValid(structureDTO.isValid())
                                .defaultDesignation(structureDTO.getDefaultDesignation())
                                .defaultAcronym(structureDTO.getDefaultAcronym())
                                .displayText(structureDTO.getDisplayText())
                                .displayAcronym(structureDTO.getDisplayAcronym())
                                .structureTypeDesignation(structureDTO.getStructureTypeDesignation())
                                .structureTypeAcronym(structureDTO.getStructureTypeAcronym())
                                .parentStructureDesignation(structureDTO.getParentStructureDesignation())
                                .parentStructureAcronym(structureDTO.getParentStructureAcronym())
                                .hierarchyLevel(structureDTO.getHierarchyLevel())
                                .hierarchyPath(structureDTO.getHierarchyPath())
                                .organizationalPosition(structureDTO.getOrganizationalPosition())
                                .commandChainLevel(structureDTO.getCommandChainLevel())
                                .structureScope(structureDTO.getStructureScope())
                                .reportingLevel(structureDTO.getReportingLevel())
                                .authorityLevel(structureDTO.getAuthorityLevel())
                                .establishmentStatus(structureDTO.getEstablishmentStatus())
                                .operationalStatus(structureDTO.getOperationalStatus())
                                .securityClassification(structureDTO.getSecurityClassification())
                                .shortDisplay(structureDTO.getShortDisplay())
                                .fullDisplay(structureDTO.getFullDisplay())
                                .displayWithType(structureDTO.getDisplayWithType())
                                .displayWithAcronym(structureDTO.getDisplayWithAcronym())
                                .displayWithHierarchy(structureDTO.getDisplayWithHierarchy())
                                .availableLanguages(structureDTO.getAvailableLanguages())
                                .comparisonKey(structureDTO.getComparisonKey())
                                .directChildrenCount(structureService.getDirectChildrenCount(id))
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting structure info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class StructureInfoResponse {
        private StructureDTO structureMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean hasArabicAcronym;
        private Boolean hasEnglishAcronym;
        private Boolean hasFrenchAcronym;
        private Boolean isMultilingual;
        private Boolean hasParent;
        private Boolean isRoot;
        private Boolean canHaveSubordinates;
        private Boolean canBeRestructured;
        private Boolean requiresParentApproval;
        private Boolean isValid;
        private String defaultDesignation;
        private String defaultAcronym;
        private String displayText;
        private String displayAcronym;
        private String structureTypeDesignation;
        private String structureTypeAcronym;
        private String parentStructureDesignation;
        private String parentStructureAcronym;
        private Integer hierarchyLevel;
        private String hierarchyPath;
        private String organizationalPosition;
        private String commandChainLevel;
        private String structureScope;
        private String reportingLevel;
        private String authorityLevel;
        private String establishmentStatus;
        private String operationalStatus;
        private String securityClassification;
        private String shortDisplay;
        private String fullDisplay;
        private String displayWithType;
        private String displayWithAcronym;
        private String displayWithHierarchy;
        private String[] availableLanguages;
        private String comparisonKey;
        private Long directChildrenCount;
    }
}
