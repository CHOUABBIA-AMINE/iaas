/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Controller
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.dto.ShelfFloorDTO;
import dz.mdn.iaas.common.environment.service.ShelfFloorService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ShelfFloor REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus shelf floor-specific operations
 */
@RestController
@RequestMapping("/shelf-floor")
@Slf4j
public class ShelfFloorController extends GenericController<ShelfFloorDTO, Long> {

    private final ShelfFloorService shelfFloorService;

    public ShelfFloorController(ShelfFloorService shelfFloorService) {
        super(shelfFloorService, "ShelfFloor");
        this.shelfFloorService = shelfFloorService;
    }

    @Override
    protected Page<ShelfFloorDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return shelfFloorService.getAll(pageable);
        }
        return shelfFloorService.globalSearch(query, pageable);
    }

    /**
     * Get all shelf floors as list (non-paginated)
     * GET /shelf-floor/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ShelfFloorDTO>> getAllList() {
        log.debug("GET /shelf-floor/list - Getting all shelf floors as list");
        List<ShelfFloorDTO> shelfFloors = shelfFloorService.getAll();
        return success(shelfFloors);
    }

    /**
     * Get shelf floors by shelf ID
     * GET /shelf-floor/shelf/{shelfId}
     */
    @GetMapping("/shelf/{shelfId}")
    public ResponseEntity<List<ShelfFloorDTO>> getByShelfId(@PathVariable Long shelfId) {
        log.debug("GET /shelf-floor/shelf/{} - Getting shelf floors by shelf ID", shelfId);
        List<ShelfFloorDTO> shelfFloors = shelfFloorService.getByShelfId(shelfId);
        return success(shelfFloors);
    }
}
