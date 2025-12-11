/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: PlannedItemController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.PlannedItemDTO;
import dz.mdn.iaas.business.plan.service.PlannedItemService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PlannedItem REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus planned-item-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /plannedItem                 Create planned item
 * - GET    /plannedItem/{id}            Get by ID
 * - GET    /plannedItem                 Get all (paginated)
 * - GET    /plannedItem/all             Get all (non-paginated)
 * - PUT    /plannedItem/{id}            Update planned item
 * - DELETE /plannedItem/{id}            Delete planned item
 * - GET    /plannedItem/search?q=...    Global search
 * - GET    /plannedItem/{id}/exists     Check existence
 * - GET    /plannedItem/count           Total count
 */
@RestController
@RequestMapping("/plannedItem")
@Slf4j
public class PlannedItemController extends GenericController<PlannedItemDTO, Long> {

    private final PlannedItemService plannedItemService;

    public PlannedItemController(PlannedItemService plannedItemService) {
        super(plannedItemService, "PlannedItem");
        this.plannedItemService = plannedItemService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<PlannedItemDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return plannedItemService.getAll(pageable);
        }
        return plannedItemService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all planned items without pagination (custom implementation)
     * GET /plannedItem/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<PlannedItemDTO>> getAllList() {
        log.debug("GET /plannedItem/list - Getting all planned items as list");
        List<PlannedItemDTO> plannedItems = plannedItemService.getAll();
        return success(plannedItems);
    }

    /**
     * Get planned items by item status
     * GET /plannedItem/by-status/{itemStatusId}
     */
    @GetMapping("/by-status/{itemStatusId}")
    public ResponseEntity<List<PlannedItemDTO>> getByItemStatus(@PathVariable Long itemStatusId) {
        log.debug("GET /plannedItem/by-status/{} - Getting planned items by item status", itemStatusId);
        List<PlannedItemDTO> plannedItems = plannedItemService.findByItemStatus(itemStatusId);
        return success(plannedItems);
    }
    
    /**
     * Get planned items by item
     * GET /plannedItem/by-item/{itemId}
     */
    @GetMapping("/by-item/{itemId}")
    public ResponseEntity<List<PlannedItemDTO>> getByItem(@PathVariable Long itemId) {
        log.debug("GET /plannedItem/by-item/{} - Getting planned items by item", itemId);
        List<PlannedItemDTO> plannedItems = plannedItemService.findByItem(itemId);
        return success(plannedItems);
    }
}
