/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentTypeController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Document
 *	@Package	: Common / Document / Controller
 *
 **/

package dz.mdn.iaas.common.document.controller;

import dz.mdn.iaas.common.document.dto.DocumentTypeDTO;
import dz.mdn.iaas.common.document.service.DocumentTypeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DocumentType REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Document Type management
 */
@RestController
@RequestMapping("/document-type")
@Slf4j
public class DocumentTypeController extends GenericController<DocumentTypeDTO, Long> {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        super(documentTypeService, "DocumentType");
        this.documentTypeService = documentTypeService;
    }

    @Override
    protected Page<DocumentTypeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return documentTypeService.getAll(pageable);
        }
        return documentTypeService.globalSearch(query, pageable);
    }

    /**
     * Get all document types as list (non-paginated)
     * GET /document-type/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<DocumentTypeDTO>> getAllList() {
        log.debug("GET /document-type/list - Getting all document types as list");
        List<DocumentTypeDTO> documentTypes = documentTypeService.getAll();
        return success(documentTypes);
    }
}
