/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementNatureController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Controller
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Controller
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.dto.ProcurementNatureDTO;
import dz.mdn.iaas.business.core.service.ProcurementNatureService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProcurementNature REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for procurement natures
 * 
 * Inherited Endpoints:
 * - POST   /procurement-nature                 Create
 * - GET    /procurement-nature/{id}            Get by ID
 * - GET    /procurement-nature                 Get all (paginated)
 * - GET    /procurement-nature/all             Get all (non-paginated)
 * - PUT    /procurement-nature/{id}            Update
 * - DELETE /procurement-nature/{id}            Delete
 * - GET    /procurement-nature/search?q=...    Global search
 * - GET    /procurement-nature/{id}/exists     Check existence
 * - GET    /procurement-nature/count           Total count
 */
@RestController
@RequestMapping("/procurement-nature")
@Slf4j
public class ProcurementNatureController extends GenericController<ProcurementNatureDTO, Long> {

    private final ProcurementNatureService procurementNatureService;

    public ProcurementNatureController(ProcurementNatureService procurementNatureService) {
        super(procurementNatureService, "ProcurementNature");
        this.procurementNatureService = procurementNatureService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ProcurementNatureDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return procurementNatureService.getAll(pageable);
        }
        return procurementNatureService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all procurement natures as list
     * GET /procurement-nature/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProcurementNatureDTO>> getAllList() {
        log.debug("GET /procurement-nature/list - Getting all procurement natures as list");
        List<ProcurementNatureDTO> natures = procurementNatureService.getAll();
        return success(natures);
    }

    /**
     * Check if code exists
     * GET /procurement-nature/exists/code/{code}
     */
    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> existsByCode(@PathVariable String code) {
        log.debug("GET /procurement-nature/exists/code/{}", code);
        boolean exists = procurementNatureService.existsByCode(code);
        return success(exists);
    }

    /**
     * Check if designation exists
     * GET /procurement-nature/exists/designation/{designation}
     */
    @GetMapping("/exists/designation/{designation}")
    public ResponseEntity<Boolean> existsByDesignation(@PathVariable String designation) {
        log.debug("GET /procurement-nature/exists/designation/{}", designation);
        boolean exists = procurementNatureService.existsByDesignationFr(designation);
        return success(exists);
    }
}
