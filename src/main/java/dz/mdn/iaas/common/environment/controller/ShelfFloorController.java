/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.service.ShelfFloorService;
import dz.mdn.iaas.common.environment.dto.ShelfFloorDTO;

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
@RequestMapping("/shelfFloor")
@RequiredArgsConstructor
@Slf4j
public class ShelfFloorController {

    private final ShelfFloorService shelfFloorService;

    // ========== POST ONE SHELF FLOOR ==========

    @PostMapping
    public ResponseEntity<ShelfFloorDTO> createShelfFloor(@Valid @RequestBody ShelfFloorDTO shelfFloorDTO) {
        log.info("Creating shelf floor with code: {} and French designation: {}", 
                shelfFloorDTO.getCode(), shelfFloorDTO.getDesignationFr());
        
        ShelfFloorDTO createdShelfFloor = shelfFloorService.createShelfFloor(shelfFloorDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShelfFloor);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<ShelfFloorDTO> getShelfFloorMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for shelf floor ID: {}", id);
        
        ShelfFloorDTO shelfFloorMetadata = shelfFloorService.getShelfFloorById(id);
        
        return ResponseEntity.ok(shelfFloorMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ShelfFloorDTO> getShelfFloorByCode(@PathVariable String code) {
        log.debug("Getting shelf floor by code: {}", code);
        
        return shelfFloorService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<ShelfFloorDTO> getShelfFloorByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting shelf floor by French designation: {}", designationFr);
        
        return shelfFloorService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelfFloor(@PathVariable Long id) {
        log.info("Deleting shelf floor with ID: {}", id);
        
        shelfFloorService.deleteShelfFloor(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<ShelfFloorDTO>> getAllShelfFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all shelf floors - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.getAllShelfFloors(pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    // ========== ADDITIONAL SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<ShelfFloorDTO>> searchShelfFloors(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching shelf floors with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.searchShelfFloors(query, pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    @GetMapping("/search/code")
    public ResponseEntity<Page<ShelfFloorDTO>> searchByCode(
            @RequestParam String code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching shelf floors by code: {}", code);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.searchByCode(code, pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    @GetMapping("/search/designation")
    public ResponseEntity<Page<ShelfFloorDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching shelf floors by designation: {}", designation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.searchByDesignation(designation, pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    // ========== SHELF LEVEL TYPE ENDPOINTS ==========

    @GetMapping("/multilingual")
    public ResponseEntity<Page<ShelfFloorDTO>> getMultilingualShelfFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual shelf floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.getMultilingualShelfFloors(pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    @GetMapping("/top")
    public ResponseEntity<Page<ShelfFloorDTO>> getTopShelfFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting top shelf floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.getTopShelfFloors(pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    @GetMapping("/bottom")
    public ResponseEntity<Page<ShelfFloorDTO>> getBottomShelfFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting bottom shelf floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.getBottomShelfFloors(pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    @GetMapping("/middle")
    public ResponseEntity<Page<ShelfFloorDTO>> getMiddleShelfFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting middle shelf floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.getMiddleShelfFloors(pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    @GetMapping("/eye-level")
    public ResponseEntity<Page<ShelfFloorDTO>> getEyeLevelShelfFloors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting eye-level shelf floors");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<ShelfFloorDTO> shelfFloors = shelfFloorService.getEyeLevelShelfFloors(pageable);
        
        return ResponseEntity.ok(shelfFloors);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<ShelfFloorDTO> updateShelfFloor(
            @PathVariable Long id,
            @Valid @RequestBody ShelfFloorDTO shelfFloorDTO) {
        
        log.info("Updating shelf floor with ID: {}", id);
        
        ShelfFloorDTO updatedShelfFloor = shelfFloorService.updateShelfFloor(id, shelfFloorDTO);
        
        return ResponseEntity.ok(updatedShelfFloor);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ShelfFloorDTO> partialUpdateShelfFloor(
            @PathVariable Long id,
            @RequestBody ShelfFloorDTO shelfFloorDTO) {
        
        log.info("Partially updating shelf floor with ID: {}", id);
        
        ShelfFloorDTO updatedShelfFloor = shelfFloorService.partialUpdateShelfFloor(id, shelfFloorDTO);
        
        return ResponseEntity.ok(updatedShelfFloor);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkShelfFloorExists(@PathVariable Long id) {
        log.debug("Checking existence of shelf floor ID: {}", id);
        
        boolean exists = shelfFloorService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkShelfFloorExistsByCode(@PathVariable String code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = shelfFloorService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkShelfFloorExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = shelfFloorService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getShelfFloorsCount() {
        log.debug("Getting total count of shelf floors");
        
        Long count = shelfFloorService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<ShelfFloorInfoResponse> getShelfFloorInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for shelf floor ID: {}", id);
        
        try {
            return shelfFloorService.findOne(id)
                    .map(shelfFloorDTO -> {
                        ShelfFloorInfoResponse response = ShelfFloorInfoResponse.builder()
                                .shelfFloorMetadata(shelfFloorDTO)
                                .hasCode(shelfFloorDTO.getCode() != null && !shelfFloorDTO.getCode().trim().isEmpty())
                                .hasArabicDesignation(shelfFloorDTO.getDesignationAr() != null && !shelfFloorDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(shelfFloorDTO.getDesignationEn() != null && !shelfFloorDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(shelfFloorDTO.getDesignationFr() != null && !shelfFloorDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(shelfFloorDTO.isMultilingual())
                                .isValid(shelfFloorDTO.isValid())
                                .defaultDesignation(shelfFloorDTO.getDefaultDesignation())
                                .displayText(shelfFloorDTO.getDisplayText())
                                .displayTextWithCode(shelfFloorDTO.getDisplayTextWithCode())
                                .fullDisplayText(shelfFloorDTO.getFullDisplayText())
                                .shortDisplay(shelfFloorDTO.getShortDisplay())
                                .shelfLevel(shelfFloorDTO.getShelfLevel())
                                .isTopLevel(shelfFloorDTO.isTopLevel())
                                .isBottomLevel(shelfFloorDTO.isBottomLevel())
                                .isEyeLevel(shelfFloorDTO.isEyeLevel())
                                .accessibilityRating(shelfFloorDTO.getAccessibilityRating())
                                .levelNumber(shelfFloorDTO.getLevelNumber())
                                .availableLanguages(shelfFloorDTO.getAvailableLanguages())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting shelf floor info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ShelfFloorInfoResponse {
        private ShelfFloorDTO shelfFloorMetadata;
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
        private String shelfLevel;
        private Boolean isTopLevel;
        private Boolean isBottomLevel;
        private Boolean isEyeLevel;
        private String accessibilityRating;
        private Integer levelNumber;
        private String[] availableLanguages;
    }
}
