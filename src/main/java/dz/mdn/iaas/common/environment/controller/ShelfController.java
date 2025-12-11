/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Controller
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.dto.ShelfDTO;
import dz.mdn.iaas.common.environment.service.ShelfService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Shelf REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus shelf-specific operations
 */
@RestController
@RequestMapping("/shelf")
@Slf4j
public class ShelfController extends GenericController<ShelfDTO, Long> {

    private final ShelfService shelfService;

    public ShelfController(ShelfService shelfService) {
        super(shelfService, "Shelf");
        this.shelfService = shelfService;
    }

    @Override
    protected Page<ShelfDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return shelfService.getAll(pageable);
        }
        return shelfService.globalSearch(query, pageable);
    }

    /**
     * Get all shelves as list (non-paginated)
     * GET /shelf/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ShelfDTO>> getAllList() {
        log.debug("GET /shelf/list - Getting all shelves as list");
        List<ShelfDTO> shelves = shelfService.getAll();
        return success(shelves);
    }

    /**
     * Get shelves by room ID
     * GET /shelf/room/{roomId}
     */
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ShelfDTO>> getByRoomId(@PathVariable Long roomId) {
        log.debug("GET /shelf/room/{} - Getting shelves by room ID", roomId);
        List<ShelfDTO> shelves = shelfService.getByRoomId(roomId);
        return success(shelves);
    }
}
