/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FloorController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.service.FloorService;
import dz.mdn.iaas.common.environment.dto.FloorDTO;

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
@RequestMapping("/floor")
@RequiredArgsConstructor
@Slf4j
public class FloorController {

    private final FloorService floorService;

    // ========== POST ONE FLOOR ==========

    @PostMapping
    public ResponseEntity<FloorDTO> createFloor(@Valid @RequestBody FloorDTO floorDTO) {
        log.info("Creating floor with code: {} and French designation: {}", 
                floorDTO.getCode(), floorDTO.getDesignationFr());
        
        FloorDTO createdFloor = floorService.createFloor(floorDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFloor);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<FloorDTO> getFloorMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for floor ID: {}", id);
        
        FloorDTO floorMetadata = floorService.getFloorById(id);
        
        return ResponseEntity.ok(floorMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<FloorDTO> getFloorByCode(@PathVariable String code) {
        log.debug("Getting floor by code: {}", code);
        
        return floorService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<FloorDTO> getFloorByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting floor by French designation: {}", designationFr);
        
        return floorService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFloor(@PathVariable Long id) {
        log.info("Deleting floor with ID: {}", id);
        
        floorService.deleteFloor(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<FloorDTO>> getAllFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all floors - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FloorDTO> floors = floorService.getAllFloors(pageable);
        
        return ResponseEntity.ok(floors);
    }

    // ========== ADDITIONAL SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<FloorDTO>> searchFloors(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching floors with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FloorDTO> floors = floorService.searchFloors(query, pageable);
        
        return ResponseEntity.ok(floors);
    }

    @GetMapping("/search/code")
    public ResponseEntity<Page<FloorDTO>> searchByCode(
            @RequestParam String code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching floors by code: {}", code);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FloorDTO> floors = floorService.searchByCode(code, pageable);
        
        return ResponseEntity.ok(floors);
    }

    @GetMapping("/search/designation")
    public ResponseEntity<Page<FloorDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching floors by designation: {}", designation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<FloorDTO> floors = floorService.searchByDesignation(designation, pageable);
        
        return ResponseEntity.ok(floors);
    }

    // ========== FLOOR TYPE ENDPOINTS ==========

    @GetMapping("/multilingual")
    public ResponseEntity<Page<FloorDTO>> getMultilingualFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FloorDTO> floors = floorService.getMultilingualFloors(pageable);
        
        return ResponseEntity.ok(floors);
    }

    @GetMapping("/ground")
    public ResponseEntity<Page<FloorDTO>> getGroundFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting ground floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FloorDTO> floors = floorService.getGroundFloors(pageable);
        
        return ResponseEntity.ok(floors);
    }

    @GetMapping("/basement")
    public ResponseEntity<Page<FloorDTO>> getBasementFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting basement floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FloorDTO> floors = floorService.getBasementFloors(pageable);
        
        return ResponseEntity.ok(floors);
    }

    @GetMapping("/upper")
    public ResponseEntity<Page<FloorDTO>> getUpperFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting upper floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FloorDTO> floors = floorService.getUpperFloors(pageable);
        
        return ResponseEntity.ok(floors);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<FloorDTO> updateFloor(
            @PathVariable Long id,
            @Valid @RequestBody FloorDTO floorDTO) {
        
        log.info("Updating floor with ID: {}", id);
        
        FloorDTO updatedFloor = floorService.updateFloor(id, floorDTO);
        
        return ResponseEntity.ok(updatedFloor);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FloorDTO> partialUpdateFloor(
            @PathVariable Long id,
            @RequestBody FloorDTO floorDTO) {
        
        log.info("Partially updating floor with ID: {}", id);
        
        FloorDTO updatedFloor = floorService.partialUpdateFloor(id, floorDTO);
        
        return ResponseEntity.ok(updatedFloor);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkFloorExists(@PathVariable Long id) {
        log.debug("Checking existence of floor ID: {}", id);
        
        boolean exists = floorService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkFloorExistsByCode(@PathVariable String code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = floorService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkFloorExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = floorService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getFloorsCount() {
        log.debug("Getting total count of floors");
        
        Long count = floorService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<FloorInfoResponse> getFloorInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for floor ID: {}", id);
        
        try {
            return floorService.findOne(id)
                    .map(floorDTO -> {
                        FloorInfoResponse response = FloorInfoResponse.builder()
                                .floorMetadata(floorDTO)
                                .hasCode(floorDTO.getCode() != null && !floorDTO.getCode().trim().isEmpty())
                                .hasArabicDesignation(floorDTO.getDesignationAr() != null && !floorDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(floorDTO.getDesignationEn() != null && !floorDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(floorDTO.getDesignationFr() != null && !floorDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(floorDTO.isMultilingual())
                                .isValid(floorDTO.isValid())
                                .defaultDesignation(floorDTO.getDefaultDesignation())
                                .displayText(floorDTO.getDisplayText())
                                .displayTextWithCode(floorDTO.getDisplayTextWithCode())
                                .fullDisplayText(floorDTO.getFullDisplayText())
                                .shortDisplay(floorDTO.getShortDisplay())
                                .floorType(floorDTO.getFloorType())
                                .isGroundFloor(floorDTO.isGroundFloor())
                                .isBasement(floorDTO.isBasement())
                                .availableLanguages(floorDTO.getAvailableLanguages())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting floor info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class FloorInfoResponse {
        private FloorDTO floorMetadata;
        private Boolean hasCode;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String displayTextWithCode;
        private String fullDisplayText;
        private String shortDisplay;
        private String floorType;
        private Boolean isGroundFloor;
        private Boolean isBasement;
        private String[] availableLanguages;
    }
}
