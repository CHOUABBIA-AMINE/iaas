/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class 
 *	@Layer		: Controller
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.service.ShelfService;
import dz.mdn.iaas.common.environment.dto.ShelfDTO;

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
@RequestMapping("/shelf")
@RequiredArgsConstructor
@Slf4j
public class ShelfController {

    private final ShelfService shelfService;

    // ========== POST ONE SHELF ==========

    @PostMapping
    public ResponseEntity<ShelfDTO> createShelf(@Valid @RequestBody ShelfDTO shelfDTO) {
        log.info("Creating shelf with code: {} for room ID: {}", 
                shelfDTO.getCode(), shelfDTO.getRoomId());
        
        ShelfDTO createdShelf = shelfService.createShelf(shelfDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShelf);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<ShelfDTO> getShelfMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for shelf ID: {}", id);
        
        ShelfDTO shelfMetadata = shelfService.getShelfById(id);
        
        return ResponseEntity.ok(shelfMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ShelfDTO> getShelfByCode(@PathVariable String code) {
        log.debug("Getting shelf by code: {}", code);
        
        return shelfService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelf(@PathVariable Long id) {
        log.info("Deleting shelf with ID: {}", id);
        
        shelfService.deleteShelf(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<ShelfDTO>> getAllShelves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all shelves - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.getAllShelves(pageable);
        
        return ResponseEntity.ok(shelves);
    }

    // ========== RELATIONSHIP-BASED ENDPOINTS ==========

    @GetMapping("/by-room/{roomId}")
    public ResponseEntity<Page<ShelfDTO>> getShelvesByRoom(
            @PathVariable Long roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting shelves for room ID: {} - page: {}, size: {}", roomId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.getShelvesByRoomId(roomId, pageable);
        
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/by-bloc/{blocId}")
    public ResponseEntity<Page<ShelfDTO>> getShelvesByBloc(
            @PathVariable Long blocId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting shelves for bloc ID: {} - page: {}, size: {}", blocId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.getShelvesByBlocId(blocId, pageable);
        
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/by-floor/{floorId}")
    public ResponseEntity<Page<ShelfDTO>> getShelvesByFloor(
            @PathVariable Long floorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting shelves for floor ID: {} - page: {}, size: {}", floorId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.getShelvesByFloorId(floorId, pageable);
        
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/by-structure/{structureId}")
    public ResponseEntity<Page<ShelfDTO>> getShelvesByStructure(
            @PathVariable Long structureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting shelves for structure ID: {} - page: {}, size: {}", structureId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.getShelvesByStructureId(structureId, pageable);
        
        return ResponseEntity.ok(shelves);
    }

    // ========== CAPACITY-BASED ENDPOINTS ==========

    @GetMapping("/empty")
    public ResponseEntity<Page<ShelfDTO>> getEmptyShelves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting empty shelves");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.getEmptyShelves(pageable);
        
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/with-archive-boxes")
    public ResponseEntity<Page<ShelfDTO>> getShelvesWithArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting shelves with archive boxes");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.getShelvesWithArchiveBoxes(pageable);
        
        return ResponseEntity.ok(shelves);
    }

    // ========== SHELF TYPE ENDPOINTS ==========

    @GetMapping("/document")
    public ResponseEntity<Page<ShelfDTO>> getDocumentShelves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting document shelves");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfDTO> shelves = shelfService.getDocumentShelves(pageable);
        
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/archive")
    public ResponseEntity<Page<ShelfDTO>> getArchiveShelves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting archive shelves");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfDTO> shelves = shelfService.getArchiveShelves(pageable);
        
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/reference")
    public ResponseEntity<Page<ShelfDTO>> getReferenceShelves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting reference shelves");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfDTO> shelves = shelfService.getReferenceShelves(pageable);
        
        return ResponseEntity.ok(shelves);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<ShelfDTO>> searchShelves(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching shelves with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfDTO> shelves = shelfService.searchShelves(query, pageable);
        
        return ResponseEntity.ok(shelves);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<ShelfDTO> updateShelf(
            @PathVariable Long id,
            @Valid @RequestBody ShelfDTO shelfDTO) {
        
        log.info("Updating shelf with ID: {}", id);
        
        ShelfDTO updatedShelf = shelfService.updateShelf(id, shelfDTO);
        
        return ResponseEntity.ok(updatedShelf);
    }

    @PostMapping("/{shelfId}/move-to-room/{roomId}")
    public ResponseEntity<ShelfDTO> moveShelfToRoom(
            @PathVariable Long shelfId,
            @PathVariable Long roomId) {
        
        log.info("Moving shelf ID: {} to room ID: {}", shelfId, roomId);
        
        ShelfDTO updatedShelf = shelfService.moveShelfToRoom(shelfId, roomId);
        
        return ResponseEntity.ok(updatedShelf);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkShelfExists(@PathVariable Long id) {
        log.debug("Checking existence of shelf ID: {}", id);
        
        boolean exists = shelfService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkShelfExistsByCode(@PathVariable String code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = shelfService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getShelvesCount() {
        log.debug("Getting total count of shelves");
        
        Long count = shelfService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-room/{roomId}")
    public ResponseEntity<Long> getShelvesCountByRoom(@PathVariable Long roomId) {
        log.debug("Getting count of shelves for room ID: {}", roomId);
        
        Long count = shelfService.getCountByRoomId(roomId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-bloc/{blocId}")
    public ResponseEntity<Long> getShelvesCountByBloc(@PathVariable Long blocId) {
        log.debug("Getting count of shelves for bloc ID: {}", blocId);
        
        Long count = shelfService.getCountByBlocId(blocId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-floor/{floorId}")
    public ResponseEntity<Long> getShelvesCountByFloor(@PathVariable Long floorId) {
        log.debug("Getting count of shelves for floor ID: {}", floorId);
        
        Long count = shelfService.getCountByFloorId(floorId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/empty")
    public ResponseEntity<Long> getEmptyShelvesCount() {
        log.debug("Getting count of empty shelves");
        
        Long count = shelfService.getEmptyShelvesCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/with-archive-boxes")
    public ResponseEntity<Long> getShelvesWithArchiveBoxesCount() {
        log.debug("Getting count of shelves with archive boxes");
        
        Long count = shelfService.getShelvesWithArchiveBoxesCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<ShelfInfoResponse> getShelfInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for shelf ID: {}", id);
        
        try {
            return shelfService.findOne(id)
                    .map(shelfDTO -> {
                        ShelfInfoResponse response = ShelfInfoResponse.builder()
                                .shelfMetadata(shelfDTO)
                                .hasCode(shelfDTO.getCode() != null && !shelfDTO.getCode().trim().isEmpty())
                                .hasArchiveBoxes(shelfDTO.hasArchiveBoxes())
                                .isEmpty(shelfDTO.isEmpty())
                                .isFull(shelfDTO.isFull())
                                .needsAttention(shelfDTO.needsAttention())
                                .isComplete(shelfDTO.isComplete())
                                .displayTextWithCode(shelfDTO.getDisplayTextWithCode())
                                .fullDisplayText(shelfDTO.getFullDisplayText())
                                .locationPath(shelfDTO.getLocationPath())
                                .shelfType(shelfDTO.getShelfType())
                                .isDocumentShelf(shelfDTO.isDocumentShelf())
                                .isArchiveShelf(shelfDTO.isArchiveShelf())
                                .priority(shelfDTO.getPriority())
                                .utilizationPercentage(shelfDTO.getUtilizationPercentage())
                                .archiveBoxCount(shelfDTO.getArchiveBoxCount())
                                .shelfCapacityStatus(shelfDTO.getShelfCapacityStatus())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting shelf info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ShelfInfoResponse {
        private ShelfDTO shelfMetadata;
        private Boolean hasCode;
        private Boolean hasArchiveBoxes;
        private Boolean isEmpty;
        private Boolean isFull;
        private Boolean needsAttention;
        private Boolean isComplete;
        private String displayTextWithCode;
        private String fullDisplayText;
        private String locationPath;
        private String shelfType;
        private Boolean isDocumentShelf;
        private Boolean isArchiveShelf;
        private String priority;
        private Double utilizationPercentage;
        private Long archiveBoxCount;
        private String shelfCapacityStatus;
    }
}
