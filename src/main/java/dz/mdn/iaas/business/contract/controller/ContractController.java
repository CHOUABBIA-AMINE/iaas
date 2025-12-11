/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ContractController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Contract
 *	@Package	: Business / Contract / Controller
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.dto.ContractDTO;
import dz.mdn.iaas.business.contract.service.ContractService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contract REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus contract-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /contract                 Create contract
 * - GET    /contract/{id}            Get by ID
 * - GET    /contract                 Get all (paginated)
 * - GET    /contract/all             Get all (non-paginated)
 * - PUT    /contract/{id}            Update contract
 * - DELETE /contract/{id}            Delete contract
 * - GET    /contract/search?q=...    Global search
 * - GET    /contract/{id}/exists     Check existence
 * - GET    /contract/count           Total count
 */
@RestController
@RequestMapping("/contract")
@Slf4j
public class ContractController extends GenericController<ContractDTO, Long> {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        super(contractService, "Contract");
        this.contractService = contractService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ContractDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return contractService.getAll(pageable);
        }
        return contractService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all contracts without pagination (custom implementation)
     * GET /contract/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ContractDTO>> getAllList() {
        log.debug("GET /contract/list - Getting all contracts as list");
        List<ContractDTO> contracts = contractService.getAll();
        return success(contracts);
    }
}
