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
 * - POST   /approvalStatus                 Create
 * - GET    /approvalStatus/{id}            Get by ID
 * - GET    /approvalStatus                 Get all (paginated)
 * - GET    /approvalStatus/all             Get all (non-paginated)
 * - PUT    /approvalStatus/{id}            Update
 * - DELETE /approvalStatus/{id}            Delete
 * - GET    /approvalStatus/search?q=...    Global search
 * - GET    /approvalStatus/{id}/exists     Check existence
 * - GET    /approvalStatus/count           Total count
 */
@RestController
@RequestMapping("/approvalStatus")
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
     * GET /approvalStatus/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ApprovalStatusDTO>> getAllList() {
        log.debug("GET /approvalStatus/list - Getting all approval statuses as list");
        List<ApprovalStatusDTO> statuses = approvalStatusService.getAll();
        return success(statuses);
    }

    /**
     * Check if designation exists
     * GET /approvalStatus/exists/designation/{designation}
     */
    @GetMapping("/exists/designation/{designation}")
    public ResponseEntity<Boolean> existsByDesignation(@PathVariable String designation) {
        log.debug("GET /approvalStatus/exists/designation/{}", designation);
        boolean exists = approvalStatusService.existsByDesignationFr(designation);
        return success(exists);
    }
}
