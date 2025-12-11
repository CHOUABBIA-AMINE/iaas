/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: SubmissionController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Controller
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.dto.SubmissionDTO;
import dz.mdn.iaas.business.consultation.service.SubmissionService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Submission REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus submission-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /submission                 Create submission
 * - GET    /submission/{id}            Get by ID
 * - GET    /submission                 Get all (paginated)
 * - GET    /submission/all             Get all (non-paginated)
 * - PUT    /submission/{id}            Update submission
 * - DELETE /submission/{id}            Delete submission
 * - GET    /submission/search?q=...    Global search
 * - GET    /submission/{id}/exists     Check existence
 * - GET    /submission/count           Total count
 */
@RestController
@RequestMapping("/submission")
@Slf4j
public class SubmissionController extends GenericController<SubmissionDTO, Long> {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        super(submissionService, "Submission");
        this.submissionService = submissionService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<SubmissionDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return submissionService.getAll(pageable);
        }
        return submissionService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all submissions without pagination (custom implementation)
     * GET /submission/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<SubmissionDTO>> getAllList() {
        log.debug("GET /submission/list - Getting all submissions as list");
        List<SubmissionDTO> submissions = submissionService.getAll();
        return success(submissions);
    }
}
