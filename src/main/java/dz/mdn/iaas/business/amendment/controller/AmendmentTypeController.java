/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: AmendmentTypeController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Amendment
 *	@Package	: Business / Amendment / Controller
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import dz.mdn.iaas.business.amendment.dto.AmendmentTypeDTO;
import dz.mdn.iaas.business.amendment.service.AmendmentTypeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AmendmentType REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus amendment-type-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /amendmentType                 Create amendment type
 * - GET    /amendmentType/{id}            Get by ID
 * - GET    /amendmentType                 Get all (paginated)
 * - GET    /amendmentType/all             Get all (non-paginated)
 * - PUT    /amendmentType/{id}            Update amendment type
 * - DELETE /amendmentType/{id}            Delete amendment type
 * - GET    /amendmentType/search?q=...    Global search
 * - GET    /amendmentType/{id}/exists     Check existence
 * - GET    /amendmentType/count           Total count
 */
@RestController
@RequestMapping("/amendmentType")
@Slf4j
public class AmendmentTypeController extends GenericController<AmendmentTypeDTO, Long> {

    private final AmendmentTypeService amendmentTypeService;

    public AmendmentTypeController(AmendmentTypeService amendmentTypeService) {
        super(amendmentTypeService, "AmendmentType");
        this.amendmentTypeService = amendmentTypeService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<AmendmentTypeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return amendmentTypeService.getAll(pageable);
        }
        return amendmentTypeService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all amendment types without pagination (custom implementation)
     * GET /amendmentType/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<AmendmentTypeDTO>> getAllList() {
        log.debug("GET /amendmentType/list - Getting all amendment types as list");
        List<AmendmentTypeDTO> amendmentTypes = amendmentTypeService.getAll();
        return success(amendmentTypes);
    }
}
