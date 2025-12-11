/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProviderRepresentatorController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Controller
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.dto.ProviderRepresentatorDTO;
import dz.mdn.iaas.business.provider.service.ProviderRepresentatorService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProviderRepresentator REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus provider-representator-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /providerRepresentator                 Create provider representator
 * - GET    /providerRepresentator/{id}            Get by ID
 * - GET    /providerRepresentator                 Get all (paginated)
 * - GET    /providerRepresentator/all             Get all (non-paginated)
 * - PUT    /providerRepresentator/{id}            Update provider representator
 * - DELETE /providerRepresentator/{id}            Delete provider representator
 * - GET    /providerRepresentator/search?q=...    Global search
 * - GET    /providerRepresentator/{id}/exists     Check existence
 * - GET    /providerRepresentator/count           Total count
 */
@RestController
@RequestMapping("/providerRepresentator")
@Slf4j
public class ProviderRepresentatorController extends GenericController<ProviderRepresentatorDTO, Long> {

    private final ProviderRepresentatorService providerRepresentatorService;

    public ProviderRepresentatorController(ProviderRepresentatorService providerRepresentatorService) {
        super(providerRepresentatorService, "ProviderRepresentator");
        this.providerRepresentatorService = providerRepresentatorService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ProviderRepresentatorDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return providerRepresentatorService.getAll(pageable);
        }
        return providerRepresentatorService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all provider representators without pagination (custom implementation)
     * GET /providerRepresentator/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProviderRepresentatorDTO>> getAllList() {
        log.debug("GET /providerRepresentator/list - Getting all provider representators as list");
        List<ProviderRepresentatorDTO> providerRepresentators = providerRepresentatorService.getAll();
        return success(providerRepresentators);
    }
}
