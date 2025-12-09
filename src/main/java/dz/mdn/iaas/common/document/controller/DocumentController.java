/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentController
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.controller;

import dz.mdn.iaas.common.document.service.DocumentService;
import dz.mdn.iaas.common.document.dto.DocumentDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

    private final DocumentService documentService;

    // ========== POST ONE DOCUMENT ==========

    @PostMapping
    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestBody DocumentDTO documentDTO) {
        log.info("Creating document with reference: {}, document type ID: {}, file ID: {}", 
                documentDTO.getReference(), documentDTO.getDocumentTypeId(), documentDTO.getFileId());
        
        DocumentDTO createdDocument = documentService.createDocument(documentDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for document ID: {}", id);
        
        DocumentDTO documentMetadata = documentService.getDocumentById(id);
        
        return ResponseEntity.ok(documentMetadata);
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<DocumentDTO> getDocumentByReference(@PathVariable String reference) {
        log.debug("Getting document by reference: {}", reference);
        
        return documentService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<DocumentDTO> getDocumentByFileId(@PathVariable Long fileId) {
        log.debug("Getting document by file ID: {}", fileId);
        
        return documentService.findByFileId(fileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        log.info("Deleting document with ID: {}", id);
        
        documentService.deleteDocument(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<DocumentDTO>> getAllDocuments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "issueDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting all documents - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentDTO> documents = documentService.getAllDocuments(pageable);
        
        return ResponseEntity.ok(documents);
    }

    // ========== ADDITIONAL DOCUMENT-TYPE-RELATED ENDPOINTS ==========

    @GetMapping("/by-document-type/{documentTypeId}")
    public ResponseEntity<Page<DocumentDTO>> getDocumentsByDocumentType(
            @PathVariable Long documentTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "issueDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting documents for document type ID: {} - page: {}, size: {}", documentTypeId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentDTO> documents = documentService.getDocumentsByDocumentTypeId(documentTypeId, pageable);
        
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/by-document-type/{documentTypeId}/list")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByDocumentTypeAsList(@PathVariable Long documentTypeId) {
        log.debug("Getting documents list for document type ID: {}", documentTypeId);
        
        List<DocumentDTO> documents = documentService.getDocumentsByDocumentTypeIdAsList(documentTypeId);
        
        return ResponseEntity.ok(documents);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<DocumentDTO>> searchDocuments(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "issueDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Searching documents with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentDTO> documents = documentService.searchDocuments(query, pageable);
        
        return ResponseEntity.ok(documents);
    }

    // ========== DATE-BASED ENDPOINTS ==========

    @GetMapping("/by-date-range")
    public ResponseEntity<Page<DocumentDTO>> getDocumentsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "issueDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting documents between {} and {}", startDate, endDate);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentDTO> documents = documentService.getDocumentsByDateRange(startDate, endDate, pageable);
        
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/recent/{days}")
    public ResponseEntity<Page<DocumentDTO>> getRecentDocuments(
            @PathVariable int days,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting documents from last {} days", days);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "issueDate"));
        Page<DocumentDTO> documents = documentService.getRecentDocuments(days, pageable);
        
        return ResponseEntity.ok(documents);
    }

    // ========== FILE-RELATED ENDPOINTS ==========

    @GetMapping("/with-files")
    public ResponseEntity<Page<DocumentDTO>> getDocumentsWithFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "issueDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting documents with files attached");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentDTO> documents = documentService.getDocumentsWithFiles(pageable);
        
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/without-files")
    public ResponseEntity<Page<DocumentDTO>> getDocumentsWithoutFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "issueDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting documents without files");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<DocumentDTO> documents = documentService.getDocumentsWithoutFiles(pageable);
        
        return ResponseEntity.ok(documents);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> updateDocument(
            @PathVariable Long id,
            @Valid @RequestBody DocumentDTO documentDTO) {
        
        log.info("Updating document with ID: {}", id);
        
        DocumentDTO updatedDocument = documentService.updateDocument(id, documentDTO);
        
        return ResponseEntity.ok(updatedDocument);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DocumentDTO> partialUpdateDocument(
            @PathVariable Long id,
            @RequestBody DocumentDTO documentDTO) {
        
        log.info("Partially updating document with ID: {}", id);
        
        DocumentDTO updatedDocument = documentService.partialUpdateDocument(id, documentDTO);
        
        return ResponseEntity.ok(updatedDocument);
    }

    @PostMapping("/{documentId}/attach-file/{fileId}")
    public ResponseEntity<DocumentDTO> attachFile(
            @PathVariable Long documentId,
            @PathVariable Long fileId) {
        
        log.info("Attaching file ID: {} to document ID: {}", fileId, documentId);
        
        DocumentDTO updatedDocument = documentService.attachFile(documentId, fileId);
        
        return ResponseEntity.ok(updatedDocument);
    }

    @PostMapping("/{documentId}/detach-file")
    public ResponseEntity<DocumentDTO> detachFile(@PathVariable Long documentId) {
        log.info("Detaching file from document ID: {}", documentId);
        
        DocumentDTO updatedDocument = documentService.detachFile(documentId);
        
        return ResponseEntity.ok(updatedDocument);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkDocumentExists(@PathVariable Long id) {
        log.debug("Checking existence of document ID: {}", id);
        
        boolean exists = documentService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/reference/{reference}")
    public ResponseEntity<Boolean> checkReferenceExists(@PathVariable String reference) {
        log.debug("Checking existence of reference: {}", reference);
        
        boolean exists = documentService.existsByReference(reference);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getDocumentsCount() {
        log.debug("Getting total count of documents");
        
        Long count = documentService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-document-type/{documentTypeId}")
    public ResponseEntity<Long> getDocumentsCountByDocumentType(@PathVariable Long documentTypeId) {
        log.debug("Getting count of documents for document type ID: {}", documentTypeId);
        
        Long count = documentService.getCountByDocumentTypeId(documentTypeId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/with-files")
    public ResponseEntity<Long> getDocumentsWithFilesCount() {
        log.debug("Getting count of documents with files");
        
        Long count = documentService.getCountWithFiles();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/without-files")
    public ResponseEntity<Long> getDocumentsWithoutFilesCount() {
        log.debug("Getting count of documents without files");
        
        Long count = documentService.getCountWithoutFiles();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/file/{fileId}/is-used")
    public ResponseEntity<Boolean> checkFileUsedByDocument(@PathVariable Long fileId) {
        log.debug("Checking if file ID: {} is used by any document", fileId);
        
        boolean isUsed = documentService.isFileUsedByDocument(fileId);
        
        return ResponseEntity.ok(isUsed);
    }

    @GetMapping("/document-type/{documentTypeId}/has-documents")
    public ResponseEntity<Boolean> checkDocumentTypeHasDocuments(@PathVariable Long documentTypeId) {
        log.debug("Checking if document type ID: {} has documents", documentTypeId);
        
        boolean hasDocuments = documentService.hasDocumentsForDocumentType(documentTypeId);
        
        return ResponseEntity.ok(hasDocuments);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<DocumentInfoResponse> getDocumentInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for document ID: {}", id);
        
        try {
            return documentService.findOne(id)
                    .map(documentDTO -> {
                        DocumentInfoResponse response = DocumentInfoResponse.builder()
                                .documentMetadata(documentDTO)
                                .hasReference(documentDTO.getReference() != null && !documentDTO.getReference().trim().isEmpty())
                                .hasIssueDate(documentDTO.getIssueDate() != null)
                                .hasDocumentType(documentDTO.getDocumentTypeId() != null)
                                .hasFile(documentDTO.hasFile())
                                .isComplete(documentDTO.isComplete())
                                .displayReference(documentDTO.getDefaultReference())
                                .formattedIssueDate(documentDTO.getFormattedIssueDate())
                                .documentAgeInDays(documentDTO.getDocumentAgeInDays())
                                .documentStatus(documentDTO.getDocumentStatus())
                                .fullDisplayText(documentDTO.getFullDisplayText())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting document info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class DocumentInfoResponse {
        private DocumentDTO documentMetadata;
        private Boolean hasReference;
        private Boolean hasIssueDate;
        private Boolean hasDocumentType;
        private Boolean hasFile;
        private Boolean isComplete;
        private String displayReference;
        private String formattedIssueDate;
        private Long documentAgeInDays;
        private String documentStatus;
        private String fullDisplayText;
    }
}
