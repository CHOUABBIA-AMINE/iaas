/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentTypeController
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.controller;

import dz.mdn.iaas.common.document.service.DocumentTypeService;
import dz.mdn.iaas.common.document.dto.DocumentTypeDTO;

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
@RequestMapping("/documentType")
@RequiredArgsConstructor
@Slf4j
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    // ========== POST ONE DOCUMENT TYPE ==========

    @PostMapping
    public ResponseEntity<DocumentTypeDTO> createDocumentType(@Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
        log.info("Creating document type with designations: {} | {} | {} and scope: {}", 
                documentTypeDTO.getDesignationAr(), documentTypeDTO.getDesignationEn(), 
                documentTypeDTO.getDesignationFr(), documentTypeDTO.getScope());
        
        DocumentTypeDTO createdDocumentType = documentTypeService.createDocumentType(documentTypeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocumentType);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> getDocumentTypeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for document type ID: {}", id);
        
        DocumentTypeDTO documentTypeMetadata = documentTypeService.getDocumentTypeById(id);
        
        return ResponseEntity.ok(documentTypeMetadata);
    }

    @GetMapping("/designation-fr/{designationFr}/scope/{scope}")
    public ResponseEntity<DocumentTypeDTO> getDocumentTypeByDesignationFrAndScope(
            @PathVariable String designationFr, 
            @PathVariable Integer scope) {
        log.debug("Getting document type by French designation: {} and scope: {}", designationFr, scope);
        
        return documentTypeService.findByDesignationFrAndScope(designationFr, scope)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) {
        log.info("Deleting document type with ID: {}", id);
        
        documentTypeService.deleteDocumentType(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<DocumentTypeDTO>> getAllDocumentTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all document types - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentTypeDTO> documentTypes = documentTypeService.getAllDocumentTypes(pageable);
        
        return ResponseEntity.ok(documentTypes);
    }

    // ========== ADDITIONAL SCOPE-RELATED ENDPOINTS ==========

    @GetMapping("/by-scope/{scope}")
    public ResponseEntity<Page<DocumentTypeDTO>> getDocumentTypesByScope(
            @PathVariable Integer scope,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting document types for scope: {} - page: {}, size: {}", scope, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentTypeDTO> documentTypes = documentTypeService.getDocumentTypesByScope(scope, pageable);
        
        return ResponseEntity.ok(documentTypes);
    }

    @GetMapping("/by-scope/{scope}/list")
    public ResponseEntity<List<DocumentTypeDTO>> getDocumentTypesByScopeAsList(@PathVariable Integer scope) {
        log.debug("Getting document types list for scope: {}", scope);
        
        List<DocumentTypeDTO> documentTypes = documentTypeService.getDocumentTypesByScopeAsList(scope);
        
        return ResponseEntity.ok(documentTypes);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<DocumentTypeDTO>> searchDocumentTypes(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching document types with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentTypeDTO> documentTypes = documentTypeService.searchDocumentTypes(query, pageable);
        
        return ResponseEntity.ok(documentTypes);
    }

    @GetMapping("/search/by-scope/{scope}")
    public ResponseEntity<Page<DocumentTypeDTO>> searchDocumentTypesInScope(
            @PathVariable Integer scope,
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching document types with query: {} in scope: {}", query, scope);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentTypeDTO> documentTypes = documentTypeService.searchDocumentTypesInScope(query, scope, pageable);
        
        return ResponseEntity.ok(documentTypes);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> updateDocumentType(
            @PathVariable Long id,
            @Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
        
        log.info("Updating document type with ID: {}", id);
        
        DocumentTypeDTO updatedDocumentType = documentTypeService.updateDocumentType(id, documentTypeDTO);
        
        return ResponseEntity.ok(updatedDocumentType);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> partialUpdateDocumentType(
            @PathVariable Long id,
            @RequestBody DocumentTypeDTO documentTypeDTO) {
        
        log.info("Partially updating document type with ID: {}", id);
        
        DocumentTypeDTO updatedDocumentType = documentTypeService.partialUpdateDocumentType(id, documentTypeDTO);
        
        return ResponseEntity.ok(updatedDocumentType);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkDocumentTypeExists(@PathVariable Long id) {
        log.debug("Checking existence of document type ID: {}", id);
        
        boolean exists = documentTypeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-fr/{designationFr}/scope/{scope}")
    public ResponseEntity<Boolean> checkDocumentTypeExistsByDesignationFrAndScope(
            @PathVariable String designationFr, 
            @PathVariable Integer scope) {
        log.debug("Checking existence by French designation: {} and scope: {}", designationFr, scope);
        
        boolean exists = documentTypeService.existsByDesignationFrAndScope(designationFr, scope);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getDocumentTypesCount() {
        log.debug("Getting total count of document types");
        
        Long count = documentTypeService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-scope/{scope}")
    public ResponseEntity<Long> getDocumentTypesCountByScope(@PathVariable Integer scope) {
        log.debug("Getting count of document types for scope: {}", scope);
        
        Long count = documentTypeService.getCountByScope(scope);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/scopes")
    public ResponseEntity<List<Integer>> getDistinctScopes() {
        log.debug("Getting distinct scopes in use");
        
        List<Integer> scopes = documentTypeService.getDistinctScopes();
        
        return ResponseEntity.ok(scopes);
    }

    @GetMapping("/scope/{scope}/in-use")
    public ResponseEntity<Boolean> checkScopeInUse(@PathVariable Integer scope) {
        log.debug("Checking if scope: {} is in use", scope);
        
        boolean inUse = documentTypeService.isScopeInUse(scope);
        
        return ResponseEntity.ok(inUse);
    }

    // ========== DOCUMENT RELATIONSHIP ENDPOINTS ==========

    @GetMapping("/with-documents")
    public ResponseEntity<Page<DocumentTypeDTO>> getDocumentTypesWithDocuments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting document types that have documents");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<DocumentTypeDTO> documentTypes = documentTypeService.getDocumentTypesWithDocuments(pageable);
        
        return ResponseEntity.ok(documentTypes);
    }

    @GetMapping("/without-documents")
    public ResponseEntity<Page<DocumentTypeDTO>> getDocumentTypesWithoutDocuments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting document types that have no documents");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<DocumentTypeDTO> documentTypes = documentTypeService.getDocumentTypesWithoutDocuments(pageable);
        
        return ResponseEntity.ok(documentTypes);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<DocumentTypeInfoResponse> getDocumentTypeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for document type ID: {}", id);
        
        try {
            return documentTypeService.findOne(id)
                    .map(documentTypeDTO -> {
                        DocumentTypeInfoResponse response = DocumentTypeInfoResponse.builder()
                                .documentTypeMetadata(documentTypeDTO)
                                .hasArabicDesignation(documentTypeDTO.getDesignationAr() != null && !documentTypeDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(documentTypeDTO.getDesignationEn() != null && !documentTypeDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(documentTypeDTO.getDesignationFr() != null && !documentTypeDTO.getDesignationFr().trim().isEmpty())
                                .hasScope(documentTypeDTO.getScope() != null)
                                .isComplete(documentTypeDTO.isComplete())
                                .defaultDesignation(documentTypeDTO.getDefaultDesignation())
                                .displayText(documentTypeDTO.getDisplayTextWithScope())
                                .fullDisplayText(documentTypeDTO.getFullDisplayText())
                                .scopeDescription(documentTypeDTO.getScopeDescription())
                                .availableLanguages(documentTypeDTO.getAvailableLanguages())
                                .documentCount(documentTypeDTO.getDocumentCount())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting document type info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class DocumentTypeInfoResponse {
        private DocumentTypeDTO documentTypeMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean hasScope;
        private Boolean isComplete;
        private String defaultDesignation;
        private String displayText;
        private String fullDisplayText;
        private String scopeDescription;
        private String[] availableLanguages;
        private Long documentCount;
    }
}
