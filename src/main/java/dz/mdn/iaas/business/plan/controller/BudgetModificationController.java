/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: BudgetModificationController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.BudgetModificationDTO;
import dz.mdn.iaas.business.plan.service.BudgetModificationService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BudgetModification REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus budget-modification-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /budgetModification                 Create budget modification
 * - GET    /budgetModification/{id}            Get by ID
 * - GET    /budgetModification                 Get all (paginated)
 * - GET    /budgetModification/all             Get all (non-paginated)
 * - PUT    /budgetModification/{id}            Update budget modification
 * - DELETE /budgetModification/{id}            Delete budget modification
 * - GET    /budgetModification/search?q=...    Global search
 * - GET    /budgetModification/{id}/exists     Check existence
 * - GET    /budgetModification/count           Total count
 */
@RestController
@RequestMapping("/budgetModification")
@Slf4j
public class BudgetModificationController extends GenericController<BudgetModificationDTO, Long> {

    private final BudgetModificationService budgetModificationService;

    public BudgetModificationController(BudgetModificationService budgetModificationService) {
        super(budgetModificationService, "BudgetModification");
        this.budgetModificationService = budgetModificationService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<BudgetModificationDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return budgetModificationService.getAll(pageable);
        }
        return budgetModificationService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all budget modifications without pagination (custom implementation)
     * GET /budgetModification/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<BudgetModificationDTO>> getAllList() {
        log.debug("GET /budgetModification/list - Getting all budget modifications as list");
        List<BudgetModificationDTO> modifications = budgetModificationService.getAll();
        return success(modifications);
    }
}
