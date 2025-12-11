/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: AmendmentPhaseController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Amendment
 *	@Package	: Business / Amendment / Controller
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import dz.mdn.iaas.business.amendment.dto.AmendmentPhaseDTO;
import dz.mdn.iaas.business.amendment.service.AmendmentPhaseService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AmendmentPhase REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus amendment-phase-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /amendmentPhase                 Create amendment phase
 * - GET    /amendmentPhase/{id}            Get by ID
 * - GET    /amendmentPhase                 Get all (paginated)
 * - GET    /amendmentPhase/all             Get all (non-paginated)
 * - PUT    /amendmentPhase/{id}            Update amendment phase
 * - DELETE /amendmentPhase/{id}            Delete amendment phase
 * - GET    /amendmentPhase/search?q=...    Global search
 * - GET    /amendmentPhase/{id}/exists     Check existence
 * - GET    /amendmentPhase/count           Total count
 */
@RestController
@RequestMapping("/amendmentPhase")
@Slf4j
public class AmendmentPhaseController extends GenericController<AmendmentPhaseDTO, Long> {

    private final AmendmentPhaseService amendmentPhaseService;

    public AmendmentPhaseController(AmendmentPhaseService amendmentPhaseService) {
        super(amendmentPhaseService, "AmendmentPhase");
        this.amendmentPhaseService = amendmentPhaseService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<AmendmentPhaseDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return amendmentPhaseService.getAll(pageable);
        }
        return amendmentPhaseService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all amendment phases without pagination (custom implementation)
     * GET /amendmentPhase/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<AmendmentPhaseDTO>> getAllList() {
        log.debug("GET /amendmentPhase/list - Getting all amendment phases as list");
        List<AmendmentPhaseDTO> amendmentPhases = amendmentPhaseService.getAll();
        return success(amendmentPhases);
    }
}
