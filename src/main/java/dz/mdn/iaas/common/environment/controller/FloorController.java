/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FloorController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Controller
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.dto.FloorDTO;
import dz.mdn.iaas.common.environment.service.FloorService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Floor REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus floor-specific operations
 */
@RestController
@RequestMapping("/floor")
@Slf4j
public class FloorController extends GenericController<FloorDTO, Long> {

    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        super(floorService, "Floor");
        this.floorService = floorService;
    }

    @Override
    protected Page<FloorDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return floorService.getAll(pageable);
        }
        return floorService.globalSearch(query, pageable);
    }

    /**
     * Get all floors as list (non-paginated)
     * GET /floor/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<FloorDTO>> getAllList() {
        log.debug("GET /floor/list - Getting all floors as list");
        List<FloorDTO> floors = floorService.getAll();
        return success(floors);
    }

    /**
     * Get floors by bloc ID
     * GET /floor/bloc/{blocId}
     */
    @GetMapping("/bloc/{blocId}")
    public ResponseEntity<List<FloorDTO>> getByBlocId(@PathVariable Long blocId) {
        log.debug("GET /floor/bloc/{} - Getting floors by bloc ID", blocId);
        List<FloorDTO> floors = floorService.getByBlocId(blocId);
        return success(floors);
    }
}
