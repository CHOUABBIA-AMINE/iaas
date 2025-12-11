/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ContractTypeController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Contract
 *	@Package	: Business / Contract / Controller
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.dto.ContractTypeDTO;
import dz.mdn.iaas.business.contract.service.ContractTypeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ContractType REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus contract-type-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /contractType                 Create contract type
 * - GET    /contractType/{id}            Get by ID
 * - GET    /contractType                 Get all (paginated)
 * - GET    /contractType/all             Get all (non-paginated)
 * - PUT    /contractType/{id}            Update contract type
 * - DELETE /contractType/{id}            Delete contract type
 * - GET    /contractType/search?q=...    Global search
 * - GET    /contractType/{id}/exists     Check existence
 * - GET    /contractType/count           Total count
 */
@RestController
@RequestMapping("/contractType")
@Slf4j
public class ContractTypeController extends GenericController<ContractTypeDTO, Long> {

    private final ContractTypeService contractTypeService;

    public ContractTypeController(ContractTypeService contractTypeService) {
        super(contractTypeService, "ContractType");
        this.contractTypeService = contractTypeService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ContractTypeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return contractTypeService.getAll(pageable);
        }
        return contractTypeService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all contract types without pagination (custom implementation)
     * GET /contractType/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ContractTypeDTO>> getAllList() {
        log.debug("GET /contractType/list - Getting all contract types as list");
        List<ContractTypeDTO> contractTypes = contractTypeService.getAll();
        return success(contractTypes);
    }
}
