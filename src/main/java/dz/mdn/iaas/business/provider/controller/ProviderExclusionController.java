/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProviderExclusionController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Controller
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.dto.ProviderExclusionDTO;
import dz.mdn.iaas.business.provider.service.ProviderExclusionService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProviderExclusion REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus provider-exclusion-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /providerExclusion                 Create provider exclusion
 * - GET    /providerExclusion/{id}            Get by ID
 * - GET    /providerExclusion                 Get all (paginated)
 * - GET    /providerExclusion/all             Get all (non-paginated)
 * - PUT    /providerExclusion/{id}            Update provider exclusion
 * - DELETE /providerExclusion/{id}            Delete provider exclusion
 * - GET    /providerExclusion/search?q=...    Global search
 * - GET    /providerExclusion/{id}/exists     Check existence
 * - GET    /providerExclusion/count           Total count
 */
@RestController
@RequestMapping("/providerExclusion")
@Slf4j
public class ProviderExclusionController extends GenericController<ProviderExclusionDTO, Long> {

    private final ProviderExclusionService providerExclusionService;

    public ProviderExclusionController(ProviderExclusionService providerExclusionService) {
        super(providerExclusionService, "ProviderExclusion");
        this.providerExclusionService = providerExclusionService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ProviderExclusionDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return providerExclusionService.getAll(pageable);
        }
        return providerExclusionService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all provider exclusions without pagination (custom implementation)
     * GET /providerExclusion/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProviderExclusionDTO>> getAllList() {
        log.debug("GET /providerExclusion/list - Getting all provider exclusions as list");
        List<ProviderExclusionDTO> providerExclusions = providerExclusionService.getAll();
        return success(providerExclusions);
    }
}
