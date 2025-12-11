/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ItemDistributionController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.ItemDistributionDTO;
import dz.mdn.iaas.business.plan.service.ItemDistributionService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ItemDistribution REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus item-distribution-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /itemDistribution                 Create item distribution
 * - GET    /itemDistribution/{id}            Get by ID
 * - GET    /itemDistribution                 Get all (paginated)
 * - GET    /itemDistribution/all             Get all (non-paginated)
 * - PUT    /itemDistribution/{id}            Update item distribution
 * - DELETE /itemDistribution/{id}            Delete item distribution
 * - GET    /itemDistribution/search?q=...    Global search
 * - GET    /itemDistribution/{id}/exists     Check existence
 * - GET    /itemDistribution/count           Total count
 */
@RestController
@RequestMapping("/itemDistribution")
@Slf4j
public class ItemDistributionController extends GenericController<ItemDistributionDTO, Long> {

    private final ItemDistributionService itemDistributionService;

    public ItemDistributionController(ItemDistributionService itemDistributionService) {
        super(itemDistributionService, "ItemDistribution");
        this.itemDistributionService = itemDistributionService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ItemDistributionDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return itemDistributionService.getAll(pageable);
        }
        return itemDistributionService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all item distributions without pagination (custom implementation)
     * GET /itemDistribution/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ItemDistributionDTO>> getAllList() {
        log.debug("GET /itemDistribution/list - Getting all item distributions as list");
        List<ItemDistributionDTO> distributions = itemDistributionService.getAll();
        return success(distributions);
    }
}
