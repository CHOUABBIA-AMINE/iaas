/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: AmendmentStepController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Amendment
 *	@Package	: Business / Amendment / Controller
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import dz.mdn.iaas.business.amendment.dto.AmendmentStepDTO;
import dz.mdn.iaas.business.amendment.service.AmendmentStepService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AmendmentStep REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus amendment-step-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /amendmentStep                 Create amendment step
 * - GET    /amendmentStep/{id}            Get by ID
 * - GET    /amendmentStep                 Get all (paginated)
 * - GET    /amendmentStep/all             Get all (non-paginated)
 * - PUT    /amendmentStep/{id}            Update amendment step
 * - DELETE /amendmentStep/{id}            Delete amendment step
 * - GET    /amendmentStep/search?q=...    Global search
 * - GET    /amendmentStep/{id}/exists     Check existence
 * - GET    /amendmentStep/count           Total count
 */
@RestController
@RequestMapping("/amendmentStep")
@Slf4j
public class AmendmentStepController extends GenericController<AmendmentStepDTO, Long> {

    private final AmendmentStepService amendmentStepService;

    public AmendmentStepController(AmendmentStepService amendmentStepService) {
        super(amendmentStepService, "AmendmentStep");
        this.amendmentStepService = amendmentStepService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<AmendmentStepDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return amendmentStepService.getAll(pageable);
        }
        return amendmentStepService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all amendment steps without pagination (custom implementation)
     * GET /amendmentStep/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<AmendmentStepDTO>> getAllList() {
        log.debug("GET /amendmentStep/list - Getting all amendment steps as list");
        List<AmendmentStepDTO> amendmentSteps = amendmentStepService.getAll();
        return success(amendmentSteps);
    }
}
