/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoomController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Controller
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.dto.RoomDTO;
import dz.mdn.iaas.common.environment.service.RoomService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Room REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus room-specific operations
 */
@RestController
@RequestMapping("/room")
@Slf4j
public class RoomController extends GenericController<RoomDTO, Long> {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        super(roomService, "Room");
        this.roomService = roomService;
    }

    @Override
    protected Page<RoomDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return roomService.getAll(pageable);
        }
        return roomService.globalSearch(query, pageable);
    }

    /**
     * Get all rooms as list (non-paginated)
     * GET /room/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<RoomDTO>> getAllList() {
        log.debug("GET /room/list - Getting all rooms as list");
        List<RoomDTO> rooms = roomService.getAll();
        return success(rooms);
    }

    /**
     * Get rooms by floor ID
     * GET /room/floor/{floorId}
     */
    @GetMapping("/floor/{floorId}")
    public ResponseEntity<List<RoomDTO>> getByFloorId(@PathVariable Long floorId) {
        log.debug("GET /room/floor/{} - Getting rooms by floor ID", floorId);
        List<RoomDTO> rooms = roomService.getByFloorId(floorId);
        return success(rooms);
    }
}
