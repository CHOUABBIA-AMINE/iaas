/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ApprovalStatusController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Controller
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Controller
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.dto.ApprovalStatusDTO;
import dz.mdn.iaas.business.core.service.ApprovalStatusService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ApprovalStatus REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for approval statuses
 * 
 * Inherited Endpoints:
 * - POST   /approval-status                 Create
 * - GET    /approval-status/{id}            Get by ID
 * - GET    /approval-status                 Get all (paginated)
 * - GET    /approval-status/all             Get all (non-paginated)
 * - PUT    /approval-status/{id}            Update
 * - DELETE /approval-status/{id}            Delete
 * - GET    /approval-status/search?q=...    Global search
 * - GET    /approval-status/{id}/exists     Check existence
 * - GET    /approval-status/count           Total count
 */
@RestController
@RequestMapping("/approval-status")
@Slf4j
public class ApprovalStatusController extends GenericController<ApprovalStatusDTO, Long> {

    private final ApprovalStatusService approvalStatusService;

    public ApprovalStatusController(ApprovalStatusService approvalStatusService) {
        super(approvalStatusService, "ApprovalStatus");
        this.approvalStatusService = approvalStatusService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ApprovalStatusDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return approvalStatusService.getAll(pageable);
        }
        return approvalStatusService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all approval statuses as list
     * GET /approval-status/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ApprovalStatusDTO>> getAllList() {
        log.debug("GET /approval-status/list - Getting all approval statuses as list");
        List<ApprovalStatusDTO> statuses = approvalStatusService.getAll();
        return success(statuses);
    }

    /**
     * Check if designation exists
     * GET /approval-status/exists/designation/{designation}
     */
    @GetMapping("/exists/designation/{designation}")
    public ResponseEntity<Boolean> existsByDesignation(@PathVariable String designation) {
        log.debug("GET /approval-status/exists/designation/{}", designation);
        boolean exists = approvalStatusService.existsByDesignationFr(designation);
        return success(exists);
    }
}
