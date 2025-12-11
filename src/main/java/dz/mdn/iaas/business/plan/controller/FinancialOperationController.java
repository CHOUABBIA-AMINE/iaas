/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: FinancialOperationController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.FinancialOperationDTO;
import dz.mdn.iaas.business.plan.service.FinancialOperationService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FinancialOperation REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus financial-operation-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /financialOperation                 Create financial operation
 * - GET    /financialOperation/{id}            Get by ID
 * - GET    /financialOperation                 Get all (paginated)
 * - GET    /financialOperation/all             Get all (non-paginated)
 * - PUT    /financialOperation/{id}            Update financial operation
 * - DELETE /financialOperation/{id}            Delete financial operation
 * - GET    /financialOperation/search?q=...    Global search
 * - GET    /financialOperation/{id}/exists     Check existence
 * - GET    /financialOperation/count           Total count
 */
@RestController
@RequestMapping("/financialOperation")
@Slf4j
public class FinancialOperationController extends GenericController<FinancialOperationDTO, Long> {

    private final FinancialOperationService financialOperationService;

    public FinancialOperationController(FinancialOperationService financialOperationService) {
        super(financialOperationService, "FinancialOperation");
        this.financialOperationService = financialOperationService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<FinancialOperationDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return financialOperationService.getAll(pageable);
        }
        return financialOperationService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all financial operations without pagination (custom implementation)
     * GET /financialOperation/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<FinancialOperationDTO>> getAllList() {
        log.debug("GET /financialOperation/list - Getting all financial operations as list");
        List<FinancialOperationDTO> operations = financialOperationService.getAll();
        return success(operations);
    }
}
