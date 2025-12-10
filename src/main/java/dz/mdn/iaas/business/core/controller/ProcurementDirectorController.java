/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementDirectorController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Controller
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Controller
 *
 **/

package dz.mdn.iaas.business.core.controller;

import dz.mdn.iaas.business.core.dto.ProcurementDirectorDTO;
import dz.mdn.iaas.business.core.service.ProcurementDirectorService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProcurementDirector REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for procurement directors
 * 
 * Inherited Endpoints:
 * - POST   /procurement-director                 Create
 * - GET    /procurement-director/{id}            Get by ID
 * - GET    /procurement-director                 Get all (paginated)
 * - GET    /procurement-director/all             Get all (non-paginated)
 * - PUT    /procurement-director/{id}            Update
 * - DELETE /procurement-director/{id}            Delete
 * - GET    /procurement-director/search?q=...    Global search
 * - GET    /procurement-director/{id}/exists     Check existence
 * - GET    /procurement-director/count           Total count
 */
@RestController
@RequestMapping("/procurement-director")
@Slf4j
public class ProcurementDirectorController extends GenericController<ProcurementDirectorDTO, Long> {

    private final ProcurementDirectorService procurementDirectorService;

    public ProcurementDirectorController(ProcurementDirectorService procurementDirectorService) {
        super(procurementDirectorService, "ProcurementDirector");
        this.procurementDirectorService = procurementDirectorService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ProcurementDirectorDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return procurementDirectorService.getAll(pageable);
        }
        return procurementDirectorService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all procurement directors as list
     * GET /procurement-director/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProcurementDirectorDTO>> getAllList() {
        log.debug("GET /procurement-director/list - Getting all procurement directors as list");
        List<ProcurementDirectorDTO> directors = procurementDirectorService.getAll();
        return success(directors);
    }

    /**
     * Check if designation exists
     * GET /procurement-director/exists/designation/{designation}
     */
    @GetMapping("/exists/designation/{designation}")
    public ResponseEntity<Boolean> existsByDesignation(@PathVariable String designation) {
        log.debug("GET /procurement-director/exists/designation/{}", designation);
        boolean exists = procurementDirectorService.existsByDesignation(designation);
        return success(exists);
    }
}
