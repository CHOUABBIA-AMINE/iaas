/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ItemController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.ItemDTO;
import dz.mdn.iaas.business.plan.service.ItemService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Item REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus item-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /item                 Create item
 * - GET    /item/{id}            Get by ID
 * - GET    /item                 Get all (paginated)
 * - GET    /item/all             Get all (non-paginated)
 * - PUT    /item/{id}            Update item
 * - DELETE /item/{id}            Delete item
 * - GET    /item/search?q=...    Global search
 * - GET    /item/{id}/exists     Check existence
 * - GET    /item/count           Total count
 */
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController extends GenericController<ItemDTO, Long> {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        super(itemService, "Item");
        this.itemService = itemService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ItemDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return itemService.getAll(pageable);
        }
        return itemService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all items without pagination (custom implementation)
     * GET /item/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ItemDTO>> getAllList() {
        log.debug("GET /item/list - Getting all items as list");
        List<ItemDTO> items = itemService.getAll();
        return success(items);
    }
}
