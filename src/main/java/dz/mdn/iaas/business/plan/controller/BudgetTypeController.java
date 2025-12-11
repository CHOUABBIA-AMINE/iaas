/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: BudgetTypeController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.BudgetTypeDTO;
import dz.mdn.iaas.business.plan.service.BudgetTypeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BudgetType REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus budget-type-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /budgetType                 Create budget type
 * - GET    /budgetType/{id}            Get by ID
 * - GET    /budgetType                 Get all (paginated)
 * - GET    /budgetType/all             Get all (non-paginated)
 * - PUT    /budgetType/{id}            Update budget type
 * - DELETE /budgetType/{id}            Delete budget type
 * - GET    /budgetType/search?q=...    Global search
 * - GET    /budgetType/{id}/exists     Check existence
 * - GET    /budgetType/count           Total count
 */
@RestController
@RequestMapping("/budgetType")
@Slf4j
public class BudgetTypeController extends GenericController<BudgetTypeDTO, Long> {

    private final BudgetTypeService budgetTypeService;

    public BudgetTypeController(BudgetTypeService budgetTypeService) {
        super(budgetTypeService, "BudgetType");
        this.budgetTypeService = budgetTypeService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<BudgetTypeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return budgetTypeService.getAll(pageable);
        }
        return budgetTypeService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all budget types without pagination (custom implementation)
     * GET /budgetType/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<BudgetTypeDTO>> getAllList() {
        log.debug("GET /budgetType/list - Getting all budget types as list");
        List<BudgetTypeDTO> budgetTypes = budgetTypeService.getAll();
        return success(budgetTypes);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if French designation exists
     * GET /budgetType/exists/designation-fr/{designationFr}
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> existsByDesignationFr(@PathVariable String designationFr) {
        log.debug("GET /budgetType/exists/designation-fr/{}", designationFr);
        boolean exists = budgetTypeService.existsByDesignationFr(designationFr);
        return success(exists);
    }

    /**
     * Check if French acronym exists
     * GET /budgetType/exists/acronym-fr/{acronymFr}
     */
    @GetMapping("/exists/acronym-fr/{acronymFr}")
    public ResponseEntity<Boolean> existsByAcronymFr(@PathVariable String acronymFr) {
        log.debug("GET /budgetType/exists/acronym-fr/{}", acronymFr);
        boolean exists = budgetTypeService.existsByAcronymFr(acronymFr);
        return success(exists);
    }
}
