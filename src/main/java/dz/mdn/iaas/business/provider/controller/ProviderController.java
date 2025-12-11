/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProviderController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Controller
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.dto.ProviderDTO;
import dz.mdn.iaas.business.provider.service.ProviderService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Provider REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus provider-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /provider                 Create provider
 * - GET    /provider/{id}            Get by ID
 * - GET    /provider                 Get all (paginated)
 * - GET    /provider/all             Get all (non-paginated)
 * - PUT    /provider/{id}            Update provider
 * - DELETE /provider/{id}            Delete provider
 * - GET    /provider/search?q=...    Global search
 * - GET    /provider/{id}/exists     Check existence
 * - GET    /provider/count           Total count
 */
@RestController
@RequestMapping("/provider")
@Slf4j
public class ProviderController extends GenericController<ProviderDTO, Long> {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        super(providerService, "Provider");
        this.providerService = providerService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ProviderDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return providerService.getAll(pageable);
        }
        return providerService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all providers without pagination (custom implementation)
     * GET /provider/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProviderDTO>> getAllList() {
        log.debug("GET /provider/list - Getting all providers as list");
        List<ProviderDTO> providers = providerService.getAll();
        return success(providers);
    }
}
