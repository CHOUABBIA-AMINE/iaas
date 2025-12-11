/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ContractItemController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Contract
 *	@Package	: Business / Contract / Controller
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.dto.ContractItemDTO;
import dz.mdn.iaas.business.contract.service.ContractItemService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ContractItem REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus contract-item-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /contractItem                 Create contract item
 * - GET    /contractItem/{id}            Get by ID
 * - GET    /contractItem                 Get all (paginated)
 * - GET    /contractItem/all             Get all (non-paginated)
 * - PUT    /contractItem/{id}            Update contract item
 * - DELETE /contractItem/{id}            Delete contract item
 * - GET    /contractItem/search?q=...    Global search
 * - GET    /contractItem/{id}/exists     Check existence
 * - GET    /contractItem/count           Total count
 */
@RestController
@RequestMapping("/contractItem")
@Slf4j
public class ContractItemController extends GenericController<ContractItemDTO, Long> {

    private final ContractItemService contractItemService;

    public ContractItemController(ContractItemService contractItemService) {
        super(contractItemService, "ContractItem");
        this.contractItemService = contractItemService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ContractItemDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return contractItemService.getAll(pageable);
        }
        return contractItemService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all contract items without pagination (custom implementation)
     * GET /contractItem/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ContractItemDTO>> getAllList() {
        log.debug("GET /contractItem/list - Getting all contract items as list");
        List<ContractItemDTO> contractItems = contractItemService.getAll();
        return success(contractItems);
    }
}
