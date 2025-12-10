/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementStatusController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Controller
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Controller
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.dto.ProcurementStatusDTO;
import dz.mdn.iaas.business.core.service.ProcurementStatusService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProcurementStatus REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for procurement statuses
 * 
 * Inherited Endpoints:
 * - POST   /procurementStatus                 Create
 * - GET    /procurementStatus/{id}            Get by ID
 * - GET    /procurementStatus                 Get all (paginated)
 * - GET    /procurementStatus/all             Get all (non-paginated)
 * - PUT    /procurementStatus/{id}            Update
 * - DELETE /procurementStatus/{id}            Delete
 * - GET    /procurementStatus/search?q=...    Global search
 * - GET    /procurementStatus/{id}/exists     Check existence
 * - GET    /procurementStatus/count           Total count
 */
@RestController
@RequestMapping("/procurementStatus")
@Slf4j
public class ProcurementStatusController extends GenericController<ProcurementStatusDTO, Long> {

    private final ProcurementStatusService procurementStatusService;

    public ProcurementStatusController(ProcurementStatusService procurementStatusService) {
        super(procurementStatusService, "ProcurementStatus");
        this.procurementStatusService = procurementStatusService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ProcurementStatusDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return procurementStatusService.getAll(pageable);
        }
        return procurementStatusService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all procurement statuses as list
     * GET /procurementStatus/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProcurementStatusDTO>> getAllList() {
        log.debug("GET /procurementStatus/list - Getting all procurement statuses as list");
        List<ProcurementStatusDTO> statuses = procurementStatusService.getAll();
        return success(statuses);
    }

    /**
     * Check if designation exists
     * GET /procurementStatus/exists/designation/{designation}
     */
    @GetMapping("/exists/designation/{designation}")
    public ResponseEntity<Boolean> existsByDesignation(@PathVariable String designation) {
        log.debug("GET /procurementStatus/exists/designation/{}", designation);
        boolean exists = procurementStatusService.existsByDesignationFr(designation);
        return success(exists);
    }
}
