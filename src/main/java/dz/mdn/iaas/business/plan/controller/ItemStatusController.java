/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ItemStatusController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.ItemStatusDTO;
import dz.mdn.iaas.business.plan.service.ItemStatusService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ItemStatus REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus item-status-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /itemStatus                 Create item status
 * - GET    /itemStatus/{id}            Get by ID
 * - GET    /itemStatus                 Get all (paginated)
 * - GET    /itemStatus/all             Get all (non-paginated)
 * - PUT    /itemStatus/{id}            Update item status
 * - DELETE /itemStatus/{id}            Delete item status
 * - GET    /itemStatus/search?q=...    Global search
 * - GET    /itemStatus/{id}/exists     Check existence
 * - GET    /itemStatus/count           Total count
 */
@RestController
@RequestMapping("/itemStatus")
@Slf4j
public class ItemStatusController extends GenericController<ItemStatusDTO, Long> {

    private final ItemStatusService itemStatusService;

    public ItemStatusController(ItemStatusService itemStatusService) {
        super(itemStatusService, "ItemStatus");
        this.itemStatusService = itemStatusService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ItemStatusDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return itemStatusService.getAll(pageable);
        }
        return itemStatusService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all item statuses without pagination (custom implementation)
     * GET /itemStatus/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ItemStatusDTO>> getAllList() {
        log.debug("GET /itemStatus/list - Getting all item statuses as list");
        List<ItemStatusDTO> itemStatuses = itemStatusService.getAll();
        return success(itemStatuses);
    }
}
