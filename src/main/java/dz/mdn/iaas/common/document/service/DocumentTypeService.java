/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentTypeService
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.service;

import dz.mdn.iaas.common.document.model.DocumentType;
import dz.mdn.iaas.common.document.repository.DocumentTypeRepository;
import dz.mdn.iaas.common.document.dto.DocumentTypeDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    // ========== CREATE OPERATIONS ==========

    public DocumentTypeDTO createDocumentType(DocumentTypeDTO documentTypeDTO) {
        log.info("Creating document type with designations: {} | {} | {} and scope: {}", 
                documentTypeDTO.getDesignationAr(), documentTypeDTO.getDesignationEn(), 
                documentTypeDTO.getDesignationFr(), documentTypeDTO.getScope());

        // Validate required fields
        validateRequiredFields(documentTypeDTO, "create");

        // Check for unique constraint violation (designationFr + scope)
        validateUniqueConstraint(documentTypeDTO, null);

        // Create entity with exact field mapping
        DocumentType documentType = new DocumentType();
        documentType.setDesignationAr(documentTypeDTO.getDesignationAr()); // F_01
        documentType.setDesignationEn(documentTypeDTO.getDesignationEn()); // F_02
        documentType.setDesignationFr(documentTypeDTO.getDesignationFr()); // F_03
        documentType.setScope(documentTypeDTO.getScope()); // F_04

        DocumentType savedDocumentType = documentTypeRepository.save(documentType);
        log.info("Successfully created document type with ID: {}", savedDocumentType.getId());

        return DocumentTypeDTO.fromEntity(savedDocumentType);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public DocumentTypeDTO getDocumentTypeById(Long id) {
        log.debug("Getting document type with ID: {}", id);

        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentType not found with ID: " + id));

        return DocumentTypeDTO.fromEntity(documentType);
    }

    @Transactional(readOnly = true)
    public DocumentType getDocumentTypeEntityById(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentType not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<DocumentTypeDTO> findByDesignationFrAndScope(String designationFr, Integer scope) {
        log.debug("Finding document type with French designation: {} and scope: {}", designationFr, scope);

        return documentTypeRepository.findByDesignationFrAndScope(designationFr, scope)
                .map(DocumentTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentTypeDTO> getAllDocumentTypes(Pageable pageable) {
        log.debug("Getting all document types with pagination");

        Page<DocumentType> documentTypes = documentTypeRepository.findAllOrderByPrimaryDesignation(pageable);
        return documentTypes.map(DocumentTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<DocumentTypeDTO> findOne(Long id) {
        log.debug("Finding document type by ID: {}", id);

        return documentTypeRepository.findById(id)
                .map(DocumentTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentTypeDTO> getDocumentTypesByScope(Integer scope, Pageable pageable) {
        log.debug("Getting document types for scope: {}", scope);

        Page<DocumentType> documentTypes = documentTypeRepository.findByScope(scope, pageable);
        return documentTypes.map(DocumentTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<DocumentTypeDTO> getDocumentTypesByScopeAsList(Integer scope) {
        log.debug("Getting document types list for scope: {}", scope);

        List<DocumentType> documentTypes = documentTypeRepository.findByScopeOrderByDesignation(scope);
        return documentTypes.stream().map(DocumentTypeDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Page<DocumentTypeDTO> searchDocumentTypes(String searchTerm, Pageable pageable) {
        log.debug("Searching document types with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllDocumentTypes(pageable);
        }

        Page<DocumentType> documentTypes = documentTypeRepository.searchByAnyDesignation(searchTerm.trim(), pageable);
        return documentTypes.map(DocumentTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentTypeDTO> searchDocumentTypesInScope(String searchTerm, Integer scope, Pageable pageable) {
        log.debug("Searching document types with term: {} in scope: {}", searchTerm, scope);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getDocumentTypesByScope(scope, pageable);
        }

        Page<DocumentType> documentTypes = documentTypeRepository.searchByAnyDesignationAndScope(searchTerm.trim(), scope, pageable);
        return documentTypes.map(DocumentTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentTypeDTO> getDocumentTypesWithDocuments(Pageable pageable) {
        log.debug("Getting document types that have documents");

        Page<DocumentType> documentTypes = documentTypeRepository.findDocumentTypesWithDocuments(pageable);
        return documentTypes.map(DocumentTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DocumentTypeDTO> getDocumentTypesWithoutDocuments(Pageable pageable) {
        log.debug("Getting document types that have no documents");

        Page<DocumentType> documentTypes = documentTypeRepository.findDocumentTypesWithoutDocuments(pageable);
        return documentTypes.map(DocumentTypeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public DocumentTypeDTO updateDocumentType(Long id, DocumentTypeDTO documentTypeDTO) {
        log.info("Updating document type with ID: {}", id);

        DocumentType existingDocumentType = getDocumentTypeEntityById(id);

        // Validate required fields
        validateRequiredFields(documentTypeDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraint(documentTypeDTO, id);

        // Update fields with exact field mapping
        existingDocumentType.setDesignationAr(documentTypeDTO.getDesignationAr()); // F_01
        existingDocumentType.setDesignationEn(documentTypeDTO.getDesignationEn()); // F_02
        existingDocumentType.setDesignationFr(documentTypeDTO.getDesignationFr()); // F_03
        existingDocumentType.setScope(documentTypeDTO.getScope()); // F_04

        DocumentType updatedDocumentType = documentTypeRepository.save(existingDocumentType);
        log.info("Successfully updated document type with ID: {}", id);

        return DocumentTypeDTO.fromEntity(updatedDocumentType);
    }

    public DocumentTypeDTO partialUpdateDocumentType(Long id, DocumentTypeDTO documentTypeDTO) {
        log.info("Partially updating document type with ID: {}", id);

        DocumentType existingDocumentType = getDocumentTypeEntityById(id);
        boolean updated = false;

        // Update only non-null fields
        if (documentTypeDTO.getDesignationAr() != null) {
            existingDocumentType.setDesignationAr(documentTypeDTO.getDesignationAr()); // F_01
            updated = true;
        }

        if (documentTypeDTO.getDesignationEn() != null) {
            existingDocumentType.setDesignationEn(documentTypeDTO.getDesignationEn()); // F_02
            updated = true;
        }

        if (documentTypeDTO.getDesignationFr() != null || documentTypeDTO.getScope() != null) {
            // If either part of unique constraint is being updated, validate the constraint
            String newDesignationFr = documentTypeDTO.getDesignationFr() != null ? 
                    documentTypeDTO.getDesignationFr() : existingDocumentType.getDesignationFr();
            Integer newScope = documentTypeDTO.getScope() != null ? 
                    documentTypeDTO.getScope() : existingDocumentType.getScope();

            /*if (newScope == null) {
                throw new RuntimeException("Scope cannot be null");
            }*/

            if (documentTypeRepository.existsByDesignationFrAndScopeAndIdNot(newDesignationFr, newScope, id)) {
                throw new RuntimeException("Another document type with French designation '" + newDesignationFr + 
                                         "' and scope '" + newScope + "' already exists");
            }

            if (documentTypeDTO.getDesignationFr() != null) {
                existingDocumentType.setDesignationFr(documentTypeDTO.getDesignationFr()); // F_03
            }
            if (documentTypeDTO.getScope() != null) {
                existingDocumentType.setScope(documentTypeDTO.getScope()); // F_04
            }
            updated = true;
        }

        if (updated) {
            DocumentType updatedDocumentType = documentTypeRepository.save(existingDocumentType);
            log.info("Successfully partially updated document type with ID: {}", id);
            return DocumentTypeDTO.fromEntity(updatedDocumentType);
        } else {
            log.debug("No fields to update for document type with ID: {}", id);
            return DocumentTypeDTO.fromEntity(existingDocumentType);
        }
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteDocumentType(Long id) {
        log.info("Deleting document type with ID: {}", id);

        DocumentType documentType = getDocumentTypeEntityById(id);
        
        // Check if document type has associated documents
        if (documentType.getDocuments() != null && !documentType.getDocuments().isEmpty()) {
            throw new RuntimeException("Cannot delete document type with ID " + id + " because it has " + 
                                     documentType.getDocuments().size() + " associated documents");
        }

        documentTypeRepository.delete(documentType);
        log.info("Successfully deleted document type with ID: {}", id);
    }

    public void deleteDocumentTypeById(Long id) {
        log.info("Deleting document type by ID: {}", id);

        if (!documentTypeRepository.existsById(id)) {
            throw new RuntimeException("DocumentType not found with ID: " + id);
        }

        // Check for associated documents using repository method
        DocumentType documentType = documentTypeRepository.findById(id).get();
        if (documentType.getDocuments() != null && !documentType.getDocuments().isEmpty()) {
            throw new RuntimeException("Cannot delete document type with ID " + id + " because it has associated documents");
        }

        documentTypeRepository.deleteById(id);
        log.info("Successfully deleted document type with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return documentTypeRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFrAndScope(String designationFr, Integer scope) {
        return documentTypeRepository.existsByDesignationFrAndScope(designationFr, scope);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return documentTypeRepository.countAllDocumentTypes();
    }

    @Transactional(readOnly = true)
    public Long getCountByScope(Integer scope) {
        return documentTypeRepository.countByScope(scope);
    }

    @Transactional(readOnly = true)
    public List<Integer> getDistinctScopes() {
        return documentTypeRepository.findDistinctScopes();
    }

    @Transactional(readOnly = true)
    public boolean isScopeInUse(Integer scope) {
        return documentTypeRepository.isScopeInUse(scope);
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(DocumentTypeDTO documentTypeDTO, String operation) {
        if (documentTypeDTO.getScope() == null) {
            throw new RuntimeException("Scope is required for " + operation);
        }

        // At least one designation should be provided
        boolean hasDesignation = (documentTypeDTO.getDesignationAr() != null && !documentTypeDTO.getDesignationAr().trim().isEmpty()) ||
                               (documentTypeDTO.getDesignationEn() != null && !documentTypeDTO.getDesignationEn().trim().isEmpty()) ||
                               (documentTypeDTO.getDesignationFr() != null && !documentTypeDTO.getDesignationFr().trim().isEmpty());

        if (!hasDesignation) {
            throw new RuntimeException("At least one designation (Arabic, English, or French) is required for " + operation);
        }
    }

    private void validateUniqueConstraint(DocumentTypeDTO documentTypeDTO, Long excludeId) {
        if (documentTypeDTO.getDesignationFr() == null) {
            return; // No French designation, so no unique constraint issue
        }

        if (excludeId == null) {
            if (documentTypeRepository.existsByDesignationFrAndScope(documentTypeDTO.getDesignationFr(), documentTypeDTO.getScope())) {
                throw new RuntimeException("Document type with French designation '" + documentTypeDTO.getDesignationFr() + 
                                         "' and scope '" + documentTypeDTO.getScope() + "' already exists");
            }
        } else {
            if (documentTypeRepository.existsByDesignationFrAndScopeAndIdNot(documentTypeDTO.getDesignationFr(), 
                                                                            documentTypeDTO.getScope(), excludeId)) {
                throw new RuntimeException("Another document type with French designation '" + documentTypeDTO.getDesignationFr() + 
                                         "' and scope '" + documentTypeDTO.getScope() + "' already exists");
            }
        }
    }
}
