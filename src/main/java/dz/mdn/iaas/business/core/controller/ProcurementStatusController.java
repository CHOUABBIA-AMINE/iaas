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
 * - POST   /procurement-status                 Create
 * - GET    /procurement-status/{id}            Get by ID
 * - GET    /procurement-status                 Get all (paginated)
 * - GET    /procurement-status/all             Get all (non-paginated)
 * - PUT    /procurement-status/{id}            Update
 * - DELETE /procurement-status/{id}            Delete
 * - GET    /procurement-status/search?q=...    Global search
 * - GET    /procurement-status/{id}/exists     Check existence
 * - GET    /procurement-status/count           Total count
 */
@RestController
@RequestMapping("/procurement-status")
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
     * GET /procurement-status/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProcurementStatusDTO>> getAllList() {
        log.debug("GET /procurement-status/list - Getting all procurement statuses as list");
        List<ProcurementStatusDTO> statuses = procurementStatusService.getAll();
        return success(statuses);
    }

    /**
     * Check if code exists
     * GET /procurement-status/exists/code/{code}
     */
    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> existsByCode(@PathVariable String code) {
        log.debug("GET /procurement-status/exists/code/{}", code);
        boolean exists = procurementStatusService.existsByCode(code);
        return success(exists);
    }

    /**
     * Check if designation exists
     * GET /procurement-status/exists/designation/{designation}
     */
    @GetMapping("/exists/designation/{designation}")
    public ResponseEntity<Boolean> existsByDesignation(@PathVariable String designation) {
        log.debug("GET /procurement-status/exists/designation/{}", designation);
        boolean exists = procurementStatusService.existsByDesignationFr(designation);
        return success(exists);
    }
}
