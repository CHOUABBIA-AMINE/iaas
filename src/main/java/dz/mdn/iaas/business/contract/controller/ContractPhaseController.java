/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ContractPhaseController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Contract
 *	@Package	: Business / Contract / Controller
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.dto.ContractPhaseDTO;
import dz.mdn.iaas.business.contract.service.ContractPhaseService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ContractPhase REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus contract-phase-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /contractPhase                 Create contract phase
 * - GET    /contractPhase/{id}            Get by ID
 * - GET    /contractPhase                 Get all (paginated)
 * - GET    /contractPhase/all             Get all (non-paginated)
 * - PUT    /contractPhase/{id}            Update contract phase
 * - DELETE /contractPhase/{id}            Delete contract phase
 * - GET    /contractPhase/search?q=...    Global search
 * - GET    /contractPhase/{id}/exists     Check existence
 * - GET    /contractPhase/count           Total count
 */
@RestController
@RequestMapping("/contractPhase")
@Slf4j
public class ContractPhaseController extends GenericController<ContractPhaseDTO, Long> {

    private final ContractPhaseService contractPhaseService;

    public ContractPhaseController(ContractPhaseService contractPhaseService) {
        super(contractPhaseService, "ContractPhase");
        this.contractPhaseService = contractPhaseService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ContractPhaseDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return contractPhaseService.getAll(pageable);
        }
        return contractPhaseService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all contract phases without pagination (custom implementation)
     * GET /contractPhase/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ContractPhaseDTO>> getAllList() {
        log.debug("GET /contractPhase/list - Getting all contract phases as list");
        List<ContractPhaseDTO> contractPhases = contractPhaseService.getAll();
        return success(contractPhases);
    }
}
