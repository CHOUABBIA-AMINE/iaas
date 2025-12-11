/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: AmendmentController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Amendment
 *	@Package	: Business / Amendment / Controller
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import dz.mdn.iaas.business.amendment.dto.AmendmentDTO;
import dz.mdn.iaas.business.amendment.service.AmendmentService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Amendment REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus amendment-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /amendment                 Create amendment
 * - GET    /amendment/{id}            Get by ID
 * - GET    /amendment                 Get all (paginated)
 * - GET    /amendment/all             Get all (non-paginated)
 * - PUT    /amendment/{id}            Update amendment
 * - DELETE /amendment/{id}            Delete amendment
 * - GET    /amendment/search?q=...    Global search
 * - GET    /amendment/{id}/exists     Check existence
 * - GET    /amendment/count           Total count
 */
@RestController
@RequestMapping("/amendment")
@Slf4j
public class AmendmentController extends GenericController<AmendmentDTO, Long> {

    private final AmendmentService amendmentService;

    public AmendmentController(AmendmentService amendmentService) {
        super(amendmentService, "Amendment");
        this.amendmentService = amendmentService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<AmendmentDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return amendmentService.getAll(pageable);
        }
        return amendmentService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all amendments without pagination (custom implementation)
     * GET /amendment/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<AmendmentDTO>> getAllList() {
        log.debug("GET /amendment/list - Getting all amendments as list");
        List<AmendmentDTO> amendments = amendmentService.getAll();
        return success(amendments);
    }
}
