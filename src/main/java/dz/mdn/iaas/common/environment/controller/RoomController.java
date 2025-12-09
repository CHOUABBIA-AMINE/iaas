/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoomController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.service.RoomService;
import dz.mdn.iaas.common.environment.dto.RoomDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    // ========== POST ONE ROOM ==========

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@Valid @RequestBody RoomDTO roomDTO) {
        log.info("Creating room with code: {} for bloc ID: {}, floor ID: {}", 
                roomDTO.getCode(), roomDTO.getBlocId(), roomDTO.getFloorId());
        
        RoomDTO createdRoom = roomService.createRoom(roomDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for room ID: {}", id);
        
        RoomDTO roomMetadata = roomService.getRoomById(id);
        
        return ResponseEntity.ok(roomMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<RoomDTO> getRoomByCode(@PathVariable String code) {
        log.debug("Getting room by code: {}", code);
        
        return roomService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        log.info("Deleting room with ID: {}", id);
        
        roomService.deleteRoom(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<RoomDTO>> getAllRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all rooms - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RoomDTO> rooms = roomService.getAllRooms(pageable);
        
        return ResponseEntity.ok(rooms);
    }

    // ========== RELATIONSHIP-BASED ENDPOINTS ==========

    @GetMapping("/by-bloc/{blocId}")
    public ResponseEntity<Page<RoomDTO>> getRoomsByBloc(
            @PathVariable Long blocId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting rooms for bloc ID: {} - page: {}, size: {}", blocId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RoomDTO> rooms = roomService.getRoomsByBlocId(blocId, pageable);
        
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/by-floor/{floorId}")
    public ResponseEntity<Page<RoomDTO>> getRoomsByFloor(
            @PathVariable Long floorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting rooms for floor ID: {} - page: {}, size: {}", floorId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RoomDTO> rooms = roomService.getRoomsByFloorId(floorId, pageable);
        
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/by-structure/{structureId}")
    public ResponseEntity<Page<RoomDTO>> getRoomsByStructure(
            @PathVariable Long structureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting rooms for structure ID: {} - page: {}, size: {}", structureId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RoomDTO> rooms = roomService.getRoomsByStructureId(structureId, pageable);
        
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/without-structure")
    public ResponseEntity<Page<RoomDTO>> getRoomsWithoutStructure(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting rooms without structure assigned");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RoomDTO> rooms = roomService.getRoomsWithoutStructure(pageable);
        
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/with-structure")
    public ResponseEntity<Page<RoomDTO>> getRoomsWithStructure(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting rooms with structure assigned");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RoomDTO> rooms = roomService.getRoomsWithStructure(pageable);
        
        return ResponseEntity.ok(rooms);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<RoomDTO>> searchRooms(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching rooms with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<RoomDTO> rooms = roomService.searchRooms(query, pageable);
        
        return ResponseEntity.ok(rooms);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomDTO roomDTO) {
        
        log.info("Updating room with ID: {}", id);
        
        RoomDTO updatedRoom = roomService.updateRoom(id, roomDTO);
        
        return ResponseEntity.ok(updatedRoom);
    }

    @PostMapping("/{roomId}/assign-structure/{structureId}")
    public ResponseEntity<RoomDTO> assignStructure(
            @PathVariable Long roomId,
            @PathVariable Long structureId) {
        
        log.info("Assigning structure ID: {} to room ID: {}", structureId, roomId);
        
        RoomDTO updatedRoom = roomService.assignStructure(roomId, structureId);
        
        return ResponseEntity.ok(updatedRoom);
    }

    @PostMapping("/{roomId}/unassign-structure")
    public ResponseEntity<RoomDTO> unassignStructure(@PathVariable Long roomId) {
        log.info("Unassigning structure from room ID: {}", roomId);
        
        RoomDTO updatedRoom = roomService.unassignStructure(roomId);
        
        return ResponseEntity.ok(updatedRoom);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkRoomExists(@PathVariable Long id) {
        log.debug("Checking existence of room ID: {}", id);
        
        boolean exists = roomService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkRoomExistsByCode(@PathVariable String code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = roomService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getRoomsCount() {
        log.debug("Getting total count of rooms");
        
        Long count = roomService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-bloc/{blocId}")
    public ResponseEntity<Long> getRoomsCountByBloc(@PathVariable Long blocId) {
        log.debug("Getting count of rooms for bloc ID: {}", blocId);
        
        Long count = roomService.getCountByBlocId(blocId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-floor/{floorId}")
    public ResponseEntity<Long> getRoomsCountByFloor(@PathVariable Long floorId) {
        log.debug("Getting count of rooms for floor ID: {}", floorId);
        
        Long count = roomService.getCountByFloorId(floorId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-structure/{structureId}")
    public ResponseEntity<Long> getRoomsCountByStructure(@PathVariable Long structureId) {
        log.debug("Getting count of rooms for structure ID: {}", structureId);
        
        Long count = roomService.getCountByStructureId(structureId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/without-structure")
    public ResponseEntity<Long> getRoomsCountWithoutStructure() {
        log.debug("Getting count of rooms without structure");
        
        Long count = roomService.getCountWithoutStructure();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<RoomInfoResponse> getRoomInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for room ID: {}", id);
        
        try {
            return roomService.findOne(id)
                    .map(roomDTO -> {
                        RoomInfoResponse response = RoomInfoResponse.builder()
                                .roomMetadata(roomDTO)
                                .hasCode(roomDTO.getCode() != null && !roomDTO.getCode().trim().isEmpty())
                                .hasStructure(roomDTO.hasStructure())
                                .hasShelfs(roomDTO.hasShelfs())
                                .isComplete(roomDTO.isComplete())
                                .displayText(roomDTO.getDisplayText())
                                .locationPath(roomDTO.getLocationPath())
                                .capacityStatus(roomDTO.getCapacityStatus())
                                .shelfCount(roomDTO.getShelfCount())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting room info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class RoomInfoResponse {
        private RoomDTO roomMetadata;
        private Boolean hasCode;
        private Boolean hasStructure;
        private Boolean hasShelfs;
        private Boolean isComplete;
        private String displayText;
        private String locationPath;
        private String capacityStatus;
        private Long shelfCount;
    }
}
