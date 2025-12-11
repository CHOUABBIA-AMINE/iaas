/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ContractStepController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Contract
 *	@Package	: Business / Contract / Controller
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.dto.ContractStepDTO;
import dz.mdn.iaas.business.contract.service.ContractStepService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ContractStep REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus contract-step-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /contractStep                 Create contract step
 * - GET    /contractStep/{id}            Get by ID
 * - GET    /contractStep                 Get all (paginated)
 * - GET    /contractStep/all             Get all (non-paginated)
 * - PUT    /contractStep/{id}            Update contract step
 * - DELETE /contractStep/{id}            Delete contract step
 * - GET    /contractStep/search?q=...    Global search
 * - GET    /contractStep/{id}/exists     Check existence
 * - GET    /contractStep/count           Total count
 */
@RestController
@RequestMapping("/contractStep")
@Slf4j
public class ContractStepController extends GenericController<ContractStepDTO, Long> {

    private final ContractStepService contractStepService;

    public ContractStepController(ContractStepService contractStepService) {
        super(contractStepService, "ContractStep");
        this.contractStepService = contractStepService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ContractStepDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return contractStepService.getAll(pageable);
        }
        return contractStepService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all contract steps without pagination (custom implementation)
     * GET /contractStep/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ContractStepDTO>> getAllList() {
        log.debug("GET /contractStep/list - Getting all contract steps as list");
        List<ContractStepDTO> contractSteps = contractStepService.getAll();
        return success(contractSteps);
    }
}
