/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Document
 *	@Package	: Common / Document / Controller
 *
 **/

package dz.mdn.iaas.common.document.controller;

import dz.mdn.iaas.common.document.dto.DocumentDTO;
import dz.mdn.iaas.common.document.service.DocumentService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Document REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Document management
 */
@RestController
@RequestMapping("/document")
@Slf4j
public class DocumentController extends GenericController<DocumentDTO, Long> {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        super(documentService, "Document");
        this.documentService = documentService;
    }

    @Override
    protected Page<DocumentDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return documentService.getAll(pageable);
        }
        return documentService.globalSearch(query, pageable);
    }

    /**
     * Get all documents as list (non-paginated)
     * GET /document/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<DocumentDTO>> getAllList() {
        log.debug("GET /document/list - Getting all documents as list");
        List<DocumentDTO> documents = documentService.getAll();
        return success(documents);
    }
}
