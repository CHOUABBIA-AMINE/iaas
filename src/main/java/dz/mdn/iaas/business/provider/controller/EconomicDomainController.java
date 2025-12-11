/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: EconomicDomainController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Controller
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.dto.EconomicDomainDTO;
import dz.mdn.iaas.business.provider.service.EconomicDomainService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EconomicDomain REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus economic-domain-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /economicDomain                 Create economic domain
 * - GET    /economicDomain/{id}            Get by ID
 * - GET    /economicDomain                 Get all (paginated)
 * - GET    /economicDomain/all             Get all (non-paginated)
 * - PUT    /economicDomain/{id}            Update economic domain
 * - DELETE /economicDomain/{id}            Delete economic domain
 * - GET    /economicDomain/search?q=...    Global search
 * - GET    /economicDomain/{id}/exists     Check existence
 * - GET    /economicDomain/count           Total count
 */
@RestController
@RequestMapping("/economicDomain")
@Slf4j
public class EconomicDomainController extends GenericController<EconomicDomainDTO, Long> {

    private final EconomicDomainService economicDomainService;

    public EconomicDomainController(EconomicDomainService economicDomainService) {
        super(economicDomainService, "EconomicDomain");
        this.economicDomainService = economicDomainService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<EconomicDomainDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return economicDomainService.getAll(pageable);
        }
        return economicDomainService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all economic domains without pagination (custom implementation)
     * GET /economicDomain/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<EconomicDomainDTO>> getAllList() {
        log.debug("GET /economicDomain/list - Getting all economic domains as list");
        List<EconomicDomainDTO> economicDomains = economicDomainService.getAll();
        return success(economicDomains);
    }
}
