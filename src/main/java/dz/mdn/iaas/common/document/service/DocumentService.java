/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentService
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.service;

import dz.mdn.iaas.common.document.model.Document;
import dz.mdn.iaas.common.document.model.DocumentType;
import dz.mdn.iaas.common.document.repository.DocumentRepository;
import dz.mdn.iaas.common.document.repository.DocumentTypeRepository;
import dz.mdn.iaas.system.utility.repository.FileRepository;
import dz.mdn.iaas.system.utility.model.File;
import dz.mdn.iaas.common.document.dto.DocumentDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final FileRepository fileRepository;

    // ========== CREATE OPERATIONS ==========

    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        log.info("Creating document with reference: {}, document type ID: {}, file ID: {}", 
                documentDTO.getReference(), documentDTO.getDocumentTypeId(), documentDTO.getFileId());

        // Validate required DocumentType relationship
        DocumentType documentType = validateAndGetDocumentType(documentDTO.getDocumentTypeId());

        // Validate optional File relationship
        File file = null;
        if (documentDTO.getFileId() != null) {
            file = validateAndGetFile(documentDTO.getFileId());
        }

        // Create entity with exact field mapping
        Document document = new Document();
        document.setReference(documentDTO.getReference()); // F_01
        document.setIssueDate(documentDTO.getIssueDate() != null ? documentDTO.getIssueDate() : new Date()); // F_02
        document.setDocumentType(documentType); // F_03
        document.setFile(file); // F_04

        Document savedDocument = documentRepository.save(document);
        log.info("Successfully created document with ID: {}", savedDocument.getId());

        return DocumentDTO.fromEntity(savedDocument);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public DocumentDTO getDocumentById(Long id) {
        log.debug("Getting document with ID: {}", id);

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with ID: " + id));

        return DocumentDTO.fromEntity(document);
    }

    @Transactional(readOnly = true)
    public Document getDocumentEntityById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findByReference(String reference) {
        log.debug("Finding document with reference: {}", reference);

        return documentRepository.findByReference(reference)
                .map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findByFileId(Long fileId) {
        log.debug("Finding document with file ID: {}", fileId);

        return documentRepository.findByFileId(fileId)
                .map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentDTO> getAllDocuments(Pageable pageable) {
        log.debug("Getting all documents with pagination");

        Page<Document> documents = documentRepository.findAllWithRelationships(pageable);
        return documents.map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findOne(Long id) {
        log.debug("Finding document by ID: {}", id);

        return documentRepository.findById(id)
                .map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentDTO> getDocumentsByDocumentTypeId(Long documentTypeId, Pageable pageable) {
        log.debug("Getting documents for document type ID: {}", documentTypeId);

        // Validate document type exists
        validateDocumentTypeExists(documentTypeId);

        Page<Document> documents = documentRepository.findByDocumentTypeIdWithRelationships(documentTypeId, pageable);
        return documents.map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<DocumentDTO> getDocumentsByDocumentTypeIdAsList(Long documentTypeId) {
        log.debug("Getting documents list for document type ID: {}", documentTypeId);

        // Validate document type exists
        validateDocumentTypeExists(documentTypeId);

        List<Document> documents = documentRepository.findByDocumentTypeIdOrderByIssueDateDesc(documentTypeId);
        return documents.stream().map(DocumentDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Page<DocumentDTO> searchDocuments(String searchTerm, Pageable pageable) {
        log.debug("Searching documents with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllDocuments(pageable);
        }

        Page<Document> documents = documentRepository.searchByReferenceOrDocumentType(searchTerm.trim(), pageable);
        return documents.map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentDTO> getDocumentsByDateRange(Date startDate, Date endDate, Pageable pageable) {
        log.debug("Getting documents between {} and {}", startDate, endDate);

        Page<Document> documents = documentRepository.findByIssueDateBetween(startDate, endDate, pageable);
        return documents.map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentDTO> getDocumentsWithFiles(Pageable pageable) {
        log.debug("Getting documents with files attached");

        Page<Document> documents = documentRepository.findDocumentsWithFiles(pageable);
        return documents.map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentDTO> getDocumentsWithoutFiles(Pageable pageable) {
        log.debug("Getting documents without files");

        Page<Document> documents = documentRepository.findDocumentsWithoutFiles(pageable);
        return documents.map(DocumentDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentDTO> getRecentDocuments(int days, Pageable pageable) {
        log.debug("Getting documents from last {} days", days);

        Date cutoffDate = new Date(System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L));
        Page<Document> documents = documentRepository.findRecentDocuments(cutoffDate, pageable);
        return documents.map(DocumentDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public DocumentDTO updateDocument(Long id, DocumentDTO documentDTO) {
        log.info("Updating document with ID: {}", id);

        Document existingDocument = getDocumentEntityById(id);

        // Validate required DocumentType relationship
        DocumentType documentType = validateAndGetDocumentType(documentDTO.getDocumentTypeId());

        // Validate optional File relationship
        File file = null;
        if (documentDTO.getFileId() != null) {
            file = validateAndGetFile(documentDTO.getFileId());
        }

        // Update fields with exact field mapping
        existingDocument.setReference(documentDTO.getReference()); // F_01
        existingDocument.setIssueDate(documentDTO.getIssueDate()); // F_02
        existingDocument.setDocumentType(documentType); // F_03
        existingDocument.setFile(file); // F_04

        Document updatedDocument = documentRepository.save(existingDocument);
        log.info("Successfully updated document with ID: {}", id);

        return DocumentDTO.fromEntity(updatedDocument);
    }

    public DocumentDTO partialUpdateDocument(Long id, DocumentDTO documentDTO) {
        log.info("Partially updating document with ID: {}", id);

        Document existingDocument = getDocumentEntityById(id);
        boolean updated = false;

        // Update only non-null fields
        if (documentDTO.getReference() != null) {
            existingDocument.setReference(documentDTO.getReference()); // F_01
            updated = true;
        }

        if (documentDTO.getIssueDate() != null) {
            existingDocument.setIssueDate(documentDTO.getIssueDate()); // F_02
            updated = true;
        }

        if (documentDTO.getDocumentTypeId() != null) {
            DocumentType documentType = validateAndGetDocumentType(documentDTO.getDocumentTypeId());
            existingDocument.setDocumentType(documentType); // F_03
            updated = true;
        }

        // Handle file update (including removal)
        if (documentDTO.getFileId() != null) {
            File file = validateAndGetFile(documentDTO.getFileId());
            existingDocument.setFile(file); // F_04
            updated = true;
        }

        if (updated) {
            Document updatedDocument = documentRepository.save(existingDocument);
            log.info("Successfully partially updated document with ID: {}", id);
            return DocumentDTO.fromEntity(updatedDocument);
        } else {
            log.debug("No fields to update for document with ID: {}", id);
            return DocumentDTO.fromEntity(existingDocument);
        }
    }

    public DocumentDTO attachFile(Long documentId, Long fileId) {
        log.info("Attaching file ID: {} to document ID: {}", fileId, documentId);

        Document document = getDocumentEntityById(documentId);
        File file = validateAndGetFile(fileId);

        document.setFile(file); // F_04
        Document updatedDocument = documentRepository.save(document);

        log.info("Successfully attached file to document with ID: {}", documentId);
        return DocumentDTO.fromEntity(updatedDocument);
    }

    public DocumentDTO detachFile(Long documentId) {
        log.info("Detaching file from document ID: {}", documentId);

        Document document = getDocumentEntityById(documentId);
        document.setFile(null); // F_04
        Document updatedDocument = documentRepository.save(document);

        log.info("Successfully detached file from document with ID: {}", documentId);
        return DocumentDTO.fromEntity(updatedDocument);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteDocument(Long id) {
        log.info("Deleting document with ID: {}", id);

        Document document = getDocumentEntityById(id);
        documentRepository.delete(document);

        log.info("Successfully deleted document with ID: {}", id);
    }

    public void deleteDocumentById(Long id) {
        log.info("Deleting document by ID: {}", id);

        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document not found with ID: " + id);
        }

        documentRepository.deleteById(id);
        log.info("Successfully deleted document with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return documentRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByReference(String reference) {
        return documentRepository.findByReference(reference).isPresent();
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return documentRepository.countAllDocuments();
    }

    @Transactional(readOnly = true)
    public Long getCountByDocumentTypeId(Long documentTypeId) {
        return documentRepository.countByDocumentTypeId(documentTypeId);
    }

    @Transactional(readOnly = true)
    public Long getCountWithFiles() {
        return documentRepository.countDocumentsWithFiles();
    }

    @Transactional(readOnly = true)
    public Long getCountWithoutFiles() {
        return documentRepository.countDocumentsWithoutFiles();
    }

    @Transactional(readOnly = true)
    public boolean isFileUsedByDocument(Long fileId) {
        return documentRepository.isFileUsedByDocument(fileId);
    }

    @Transactional(readOnly = true)
    public boolean hasDocumentsForDocumentType(Long documentTypeId) {
        return documentRepository.hasDocumentsForDocumentType(documentTypeId);
    }

    // ========== VALIDATION METHODS ==========

    private DocumentType validateAndGetDocumentType(Long documentTypeId) {
        if (documentTypeId == null) {
            throw new RuntimeException("Document type ID is required");
        }
        return documentTypeRepository.findById(documentTypeId)
                .orElseThrow(() -> new RuntimeException("DocumentType not found with ID: " + documentTypeId));
    }

    private void validateDocumentTypeExists(Long documentTypeId) {
        if (!documentTypeRepository.existsById(documentTypeId)) {
            throw new RuntimeException("DocumentType not found with ID: " + documentTypeId);
        }
    }

    private File validateAndGetFile(Long fileId) {
        if (fileId == null) {
            return null;
        }
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with ID: " + fileId));
    }
}
