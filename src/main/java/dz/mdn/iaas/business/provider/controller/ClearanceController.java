/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ClearanceController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Controller
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.dto.ClearanceDTO;
import dz.mdn.iaas.business.provider.service.ClearanceService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clearance REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus clearance-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /clearance                 Create clearance
 * - GET    /clearance/{id}            Get by ID
 * - GET    /clearance                 Get all (paginated)
 * - GET    /clearance/all             Get all (non-paginated)
 * - PUT    /clearance/{id}            Update clearance
 * - DELETE /clearance/{id}            Delete clearance
 * - GET    /clearance/search?q=...    Global search
 * - GET    /clearance/{id}/exists     Check existence
 * - GET    /clearance/count           Total count
 */
@RestController
@RequestMapping("/clearance")
@Slf4j
public class ClearanceController extends GenericController<ClearanceDTO, Long> {

    private final ClearanceService clearanceService;

    public ClearanceController(ClearanceService clearanceService) {
        super(clearanceService, "Clearance");
        this.clearanceService = clearanceService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ClearanceDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return clearanceService.getAll(pageable);
        }
        return clearanceService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all clearances without pagination (custom implementation)
     * GET /clearance/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ClearanceDTO>> getAllList() {
        log.debug("GET /clearance/list - Getting all clearances as list");
        List<ClearanceDTO> clearances = clearanceService.getAll();
        return success(clearances);
    }
}
