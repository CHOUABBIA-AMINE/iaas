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
 * - POST   /procurementNature                 Create
 * - GET    /procurementNature/{id}            Get by ID
 * - GET    /procurementNature                 Get all (paginated)
 * - GET    /procurementNature/all             Get all (non-paginated)
 * - PUT    /procurementNature/{id}            Update
 * - DELETE /procurementNature/{id}            Delete
 * - GET    /procurementNature/search?q=...    Global search
 * - GET    /procurementNature/{id}/exists     Check existence
 * - GET    /procurementNature/count           Total count
 */
@RestController
@RequestMapping("/procurementNature")
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
     * GET /procurementNature/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProcurementNatureDTO>> getAllList() {
        log.debug("GET /procurementNature/list - Getting all procurement natures as list");
        List<ProcurementNatureDTO> natures = procurementNatureService.getAll();
        return success(natures);
    }

    /**
     * Check if designation exists
     * GET /procurementNature/exists/designation/{designation}
     */
    @GetMapping("/exists/designation/{designation}")
    public ResponseEntity<Boolean> existsByDesignation(@PathVariable String designation) {
        log.debug("GET /procurementNature/exists/designation/{}", designation);
        boolean exists = procurementNatureService.existsByDesignationFr(designation);
        return success(exists);
    }
}
