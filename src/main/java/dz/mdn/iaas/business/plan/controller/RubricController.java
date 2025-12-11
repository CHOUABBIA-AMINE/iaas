/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: RubricController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.RubricDTO;
import dz.mdn.iaas.business.plan.service.RubricService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rubric REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus rubric-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /rubric                 Create rubric
 * - GET    /rubric/{id}            Get by ID
 * - GET    /rubric                 Get all (paginated)
 * - GET    /rubric/all             Get all (non-paginated)
 * - PUT    /rubric/{id}            Update rubric
 * - DELETE /rubric/{id}            Delete rubric
 * - GET    /rubric/search?q=...    Global search
 * - GET    /rubric/{id}/exists     Check existence
 * - GET    /rubric/count           Total count
 */
@RestController
@RequestMapping("/rubric")
@Slf4j
public class RubricController extends GenericController<RubricDTO, Long> {

    private final RubricService rubricService;

    public RubricController(RubricService rubricService) {
        super(rubricService, "Rubric");
        this.rubricService = rubricService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<RubricDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return rubricService.getAll(pageable);
        }
        return rubricService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all rubrics without pagination (custom implementation)
     * GET /rubric/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<RubricDTO>> getAllList() {
        log.debug("GET /rubric/list - Getting all rubrics as list");
        List<RubricDTO> rubrics = rubricService.getAll();
        return success(rubrics);
    }
}
