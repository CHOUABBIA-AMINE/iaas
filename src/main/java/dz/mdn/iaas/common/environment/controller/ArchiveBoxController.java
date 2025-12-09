/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBoxController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class 
 *	@Layer		: Controller
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.service.ArchiveBoxService;
import dz.mdn.iaas.common.environment.dto.ArchiveBoxDTO;

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
@RequestMapping("/archiveBox")
@RequiredArgsConstructor
@Slf4j
public class ArchiveBoxController {

    private final ArchiveBoxService archiveBoxService;

    // ========== POST ONE ARCHIVE BOX ==========

    @PostMapping
    public ResponseEntity<ArchiveBoxDTO> createArchiveBox(@Valid @RequestBody ArchiveBoxDTO archiveBoxDTO) {
        log.info("Creating archive box with code: {} for shelf ID: {} and shelf floor ID: {}", 
                archiveBoxDTO.getCode(), archiveBoxDTO.getShelfId(), archiveBoxDTO.getShelfFloorId());
        
        ArchiveBoxDTO createdArchiveBox = archiveBoxService.createArchiveBox(archiveBoxDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArchiveBox);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<ArchiveBoxDTO> getArchiveBoxMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for archive box ID: {}", id);
        
        ArchiveBoxDTO archiveBoxMetadata = archiveBoxService.getArchiveBoxById(id);
        
        return ResponseEntity.ok(archiveBoxMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ArchiveBoxDTO> getArchiveBoxByCode(@PathVariable String code) {
        log.debug("Getting archive box by code: {}", code);
        
        return archiveBoxService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArchiveBox(@PathVariable Long id) {
        log.info("Deleting archive box with ID: {}", id);
        
        archiveBoxService.deleteArchiveBox(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<ArchiveBoxDTO>> getAllArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all archive boxes - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getAllArchiveBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    // ========== RELATIONSHIP-BASED ENDPOINTS ==========

    @GetMapping("/by-shelf/{shelfId}")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveBoxesByShelf(
            @PathVariable Long shelfId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting archive boxes for shelf ID: {} - page: {}, size: {}", shelfId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveBoxesByShelfId(shelfId, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/by-shelf-floor/{shelfFloorId}")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveBoxesByShelfFloor(
            @PathVariable Long shelfFloorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting archive boxes for shelf floor ID: {} - page: {}, size: {}", shelfFloorId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveBoxesByShelfFloorId(shelfFloorId, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/by-shelf-and-floor/{shelfId}/{shelfFloorId}")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveBoxesByShelfAndShelfFloor(
            @PathVariable Long shelfId,
            @PathVariable Long shelfFloorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting archive boxes for shelf ID: {} and shelf floor ID: {}", shelfId, shelfFloorId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveBoxesByShelfAndShelfFloor(shelfId, shelfFloorId, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/by-room/{roomId}")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveBoxesByRoom(
            @PathVariable Long roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting archive boxes for room ID: {} - page: {}, size: {}", roomId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveBoxesByRoomId(roomId, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/by-bloc/{blocId}")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveBoxesByBloc(
            @PathVariable Long blocId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting archive boxes for bloc ID: {} - page: {}, size: {}", blocId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveBoxesByBlocId(blocId, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/by-floor/{floorId}")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveBoxesByFloor(
            @PathVariable Long floorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting archive boxes for floor ID: {} - page: {}, size: {}", floorId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveBoxesByFloorId(floorId, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/by-structure/{structureId}")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveBoxesByStructure(
            @PathVariable Long structureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting archive boxes for structure ID: {} - page: {}, size: {}", structureId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveBoxesByStructureId(structureId, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    // ========== ARCHIVE BOX TYPE ENDPOINTS ==========

    @GetMapping("/document")
    public ResponseEntity<Page<ArchiveBoxDTO>> getDocumentArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting document archive boxes");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getDocumentArchiveBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/archive")
    public ResponseEntity<Page<ArchiveBoxDTO>> getArchiveStorageBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting archive storage boxes");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getArchiveStorageBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/confidential")
    public ResponseEntity<Page<ArchiveBoxDTO>> getConfidentialArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting confidential archive boxes");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getConfidentialArchiveBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/temporary")
    public ResponseEntity<Page<ArchiveBoxDTO>> getTemporaryArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting temporary archive boxes");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getTemporaryArchiveBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/urgent")
    public ResponseEntity<Page<ArchiveBoxDTO>> getUrgentArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting urgent archive boxes");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getUrgentArchiveBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    // ========== ACCESSIBILITY ENDPOINTS ==========

    @GetMapping("/high-access")
    public ResponseEntity<Page<ArchiveBoxDTO>> getHighAccessibilityArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting high accessibility archive boxes");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getHighAccessibilityArchiveBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    @GetMapping("/low-access")
    public ResponseEntity<Page<ArchiveBoxDTO>> getLowAccessibilityArchiveBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting low accessibility archive boxes");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getLowAccessibilityArchiveBoxes(pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<ArchiveBoxDTO>> searchArchiveBoxes(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching archive boxes with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ArchiveBoxDTO> archiveBoxes = archiveBoxService.searchArchiveBoxes(query, pageable);
        
        return ResponseEntity.ok(archiveBoxes);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<ArchiveBoxDTO> updateArchiveBox(
            @PathVariable Long id,
            @Valid @RequestBody ArchiveBoxDTO archiveBoxDTO) {
        
        log.info("Updating archive box with ID: {}", id);
        
        ArchiveBoxDTO updatedArchiveBox = archiveBoxService.updateArchiveBox(id, archiveBoxDTO);
        
        return ResponseEntity.ok(updatedArchiveBox);
    }

    @PostMapping("/{archiveBoxId}/move-to-shelf/{shelfId}")
    public ResponseEntity<ArchiveBoxDTO> moveArchiveBoxToShelf(
            @PathVariable Long archiveBoxId,
            @PathVariable Long shelfId) {
        
        log.info("Moving archive box ID: {} to shelf ID: {}", archiveBoxId, shelfId);
        
        ArchiveBoxDTO updatedArchiveBox = archiveBoxService.moveArchiveBoxToShelf(archiveBoxId, shelfId);
        
        return ResponseEntity.ok(updatedArchiveBox);
    }

    @PostMapping("/{archiveBoxId}/move-to-shelf-floor/{shelfFloorId}")
    public ResponseEntity<ArchiveBoxDTO> moveArchiveBoxToShelfFloor(
            @PathVariable Long archiveBoxId,
            @PathVariable Long shelfFloorId) {
        
        log.info("Moving archive box ID: {} to shelf floor ID: {}", archiveBoxId, shelfFloorId);
        
        ArchiveBoxDTO updatedArchiveBox = archiveBoxService.moveArchiveBoxToShelfFloor(archiveBoxId, shelfFloorId);
        
        return ResponseEntity.ok(updatedArchiveBox);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkArchiveBoxExists(@PathVariable Long id) {
        log.debug("Checking existence of archive box ID: {}", id);
        
        boolean exists = archiveBoxService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkArchiveBoxExistsByCode(@PathVariable String code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = archiveBoxService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getArchiveBoxesCount() {
        log.debug("Getting total count of archive boxes");
        
        Long count = archiveBoxService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-shelf/{shelfId}")
    public ResponseEntity<Long> getArchiveBoxesCountByShelf(@PathVariable Long shelfId) {
        log.debug("Getting count of archive boxes for shelf ID: {}", shelfId);
        
        Long count = archiveBoxService.getCountByShelfId(shelfId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-shelf-floor/{shelfFloorId}")
    public ResponseEntity<Long> getArchiveBoxesCountByShelfFloor(@PathVariable Long shelfFloorId) {
        log.debug("Getting count of archive boxes for shelf floor ID: {}", shelfFloorId);
        
        Long count = archiveBoxService.getCountByShelfFloorId(shelfFloorId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-room/{roomId}")
    public ResponseEntity<Long> getArchiveBoxesCountByRoom(@PathVariable Long roomId) {
        log.debug("Getting count of archive boxes for room ID: {}", roomId);
        
        Long count = archiveBoxService.getCountByRoomId(roomId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-bloc/{blocId}")
    public ResponseEntity<Long> getArchiveBoxesCountByBloc(@PathVariable Long blocId) {
        log.debug("Getting count of archive boxes for bloc ID: {}", blocId);
        
        Long count = archiveBoxService.getCountByBlocId(blocId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<ArchiveBoxInfoResponse> getArchiveBoxInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for archive box ID: {}", id);
        
        try {
            return archiveBoxService.findOne(id)
                    .map(archiveBoxDTO -> {
                        ArchiveBoxInfoResponse response = ArchiveBoxInfoResponse.builder()
                                .archiveBoxMetadata(archiveBoxDTO)
                                .hasCode(archiveBoxDTO.getCode() != null && !archiveBoxDTO.getCode().trim().isEmpty())
                                .isComplete(archiveBoxDTO.isComplete())
                                .isConfidential(archiveBoxDTO.isConfidential())
                                .isTemporary(archiveBoxDTO.isTemporary())
                                .isUrgent(archiveBoxDTO.isUrgent())
                                .isValid(archiveBoxDTO.isValid())
                                .displayTextWithCode(archiveBoxDTO.getDisplayTextWithCode())
                                .fullDisplayText(archiveBoxDTO.getFullDisplayText())
                                .locationPath(archiveBoxDTO.getLocationPath())
                                .archiveBoxType(archiveBoxDTO.getArchiveBoxType())
                                .capacityStatus(archiveBoxDTO.getCapacityStatus())
                                .priority(archiveBoxDTO.getPriority())
                                .accessibilityRating(archiveBoxDTO.getAccessibilityRating())
                                .shortDisplay(archiveBoxDTO.getShortDisplay())
                                .detailedInfo(archiveBoxDTO.getDetailedInfo())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting archive box info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ArchiveBoxInfoResponse {
        private ArchiveBoxDTO archiveBoxMetadata;
        private Boolean hasCode;
        private Boolean isComplete;
        private Boolean isConfidential;
        private Boolean isTemporary;
        private Boolean isUrgent;
        private Boolean isValid;
        private String displayTextWithCode;
        private String fullDisplayText;
        private String locationPath;
        private String archiveBoxType;
        private String capacityStatus;
        private String priority;
        private String accessibilityRating;
        private String shortDisplay;
        private String detailedInfo;
    }
}
