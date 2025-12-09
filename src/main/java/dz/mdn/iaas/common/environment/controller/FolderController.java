/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FolderController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.service.FolderService;
import dz.mdn.iaas.common.environment.dto.FolderDTO;

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
@RequestMapping("/folder")
@RequiredArgsConstructor
@Slf4j
public class FolderController {

    private final FolderService folderService;

    // ========== POST ONE FOLDER ==========

    @PostMapping
    public ResponseEntity<FolderDTO> createFolder(@Valid @RequestBody FolderDTO folderDTO) {
        log.info("Creating folder with code: {} and French designation: {} for archive box ID: {}", 
                folderDTO.getCode(), folderDTO.getDesignationFr(), folderDTO.getArchiveBoxId());
        
        FolderDTO createdFolder = folderService.createFolder(folderDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFolder);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<FolderDTO> getFolderMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for folder ID: {}", id);
        
        FolderDTO folderMetadata = folderService.getFolderById(id);
        
        return ResponseEntity.ok(folderMetadata);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<FolderDTO> getFolderByCode(@PathVariable String code) {
        log.debug("Getting folder by code: {}", code);
        
        return folderService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<FolderDTO> getFolderByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting folder by French designation: {}", designationFr);
        
        return folderService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
        log.info("Deleting folder with ID: {}", id);
        
        folderService.deleteFolder(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<FolderDTO>> getAllFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all folders - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.getAllFolders(pageable);
        
        return ResponseEntity.ok(folders);
    }

    // ========== RELATIONSHIP-BASED ENDPOINTS ==========

    @GetMapping("/by-archive-box/{archiveBoxId}")
    public ResponseEntity<Page<FolderDTO>> getFoldersByArchiveBox(
            @PathVariable Long archiveBoxId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting folders for archive box ID: {} - page: {}, size: {}", archiveBoxId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.getFoldersByArchiveBoxId(archiveBoxId, pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/by-shelf/{shelfId}")
    public ResponseEntity<Page<FolderDTO>> getFoldersByShelf(
            @PathVariable Long shelfId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting folders for shelf ID: {} - page: {}, size: {}", shelfId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.getFoldersByShelfId(shelfId, pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/by-room/{roomId}")
    public ResponseEntity<Page<FolderDTO>> getFoldersByRoom(
            @PathVariable Long roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting folders for room ID: {} - page: {}, size: {}", roomId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.getFoldersByRoomId(roomId, pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/by-bloc/{blocId}")
    public ResponseEntity<Page<FolderDTO>> getFoldersByBloc(
            @PathVariable Long blocId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting folders for bloc ID: {} - page: {}, size: {}", blocId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.getFoldersByBlocId(blocId, pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/by-floor/{floorId}")
    public ResponseEntity<Page<FolderDTO>> getFoldersByFloor(
            @PathVariable Long floorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting folders for floor ID: {} - page: {}, size: {}", floorId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.getFoldersByFloorId(floorId, pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/by-structure/{structureId}")
    public ResponseEntity<Page<FolderDTO>> getFoldersByStructure(
            @PathVariable Long structureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting folders for structure ID: {} - page: {}, size: {}", structureId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.getFoldersByStructureId(structureId, pageable);
        
        return ResponseEntity.ok(folders);
    }

    // ========== FOLDER TYPE ENDPOINTS ==========

    @GetMapping("/multilingual")
    public ResponseEntity<Page<FolderDTO>> getMultilingualFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual folders");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FolderDTO> folders = folderService.getMultilingualFolders(pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/urgent")
    public ResponseEntity<Page<FolderDTO>> getUrgentFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting urgent folders");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FolderDTO> folders = folderService.getUrgentFolders(pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/confidential")
    public ResponseEntity<Page<FolderDTO>> getConfidentialFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting confidential folders");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FolderDTO> folders = folderService.getConfidentialFolders(pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/temporary")
    public ResponseEntity<Page<FolderDTO>> getTemporaryFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting temporary folders");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FolderDTO> folders = folderService.getTemporaryFolders(pageable);
        
        return ResponseEntity.ok(folders);
    }

    @GetMapping("/document")
    public ResponseEntity<Page<FolderDTO>> getDocumentFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting document folders");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code"));
        Page<FolderDTO> folders = folderService.getDocumentFolders(pageable);
        
        return ResponseEntity.ok(folders);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<FolderDTO>> searchFolders(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching folders with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<FolderDTO> folders = folderService.searchFolders(query, pageable);
        
        return ResponseEntity.ok(folders);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<FolderDTO> updateFolder(
            @PathVariable Long id,
            @Valid @RequestBody FolderDTO folderDTO) {
        
        log.info("Updating folder with ID: {}", id);
        
        FolderDTO updatedFolder = folderService.updateFolder(id, folderDTO);
        
        return ResponseEntity.ok(updatedFolder);
    }

    @PostMapping("/{folderId}/move-to-archive-box/{archiveBoxId}")
    public ResponseEntity<FolderDTO> moveFolderToArchiveBox(
            @PathVariable Long folderId,
            @PathVariable Long archiveBoxId) {
        
        log.info("Moving folder ID: {} to archive box ID: {}", folderId, archiveBoxId);
        
        FolderDTO updatedFolder = folderService.moveFolderToArchiveBox(folderId, archiveBoxId);
        
        return ResponseEntity.ok(updatedFolder);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkFolderExists(@PathVariable Long id) {
        log.debug("Checking existence of folder ID: {}", id);
        
        boolean exists = folderService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> checkFolderExistsByCode(@PathVariable String code) {
        log.debug("Checking existence by code: {}", code);
        
        boolean exists = folderService.existsByCode(code);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkFolderExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = folderService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getFoldersCount() {
        log.debug("Getting total count of folders");
        
        Long count = folderService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-archive-box/{archiveBoxId}")
    public ResponseEntity<Long> getFoldersCountByArchiveBox(@PathVariable Long archiveBoxId) {
        log.debug("Getting count of folders for archive box ID: {}", archiveBoxId);
        
        Long count = folderService.getCountByArchiveBoxId(archiveBoxId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-shelf/{shelfId}")
    public ResponseEntity<Long> getFoldersCountByShelf(@PathVariable Long shelfId) {
        log.debug("Getting count of folders for shelf ID: {}", shelfId);
        
        Long count = folderService.getCountByShelfId(shelfId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-room/{roomId}")
    public ResponseEntity<Long> getFoldersCountByRoom(@PathVariable Long roomId) {
        log.debug("Getting count of folders for room ID: {}", roomId);
        
        Long count = folderService.getCountByRoomId(roomId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/by-bloc/{blocId}")
    public ResponseEntity<Long> getFoldersCountByBloc(@PathVariable Long blocId) {
        log.debug("Getting count of folders for bloc ID: {}", blocId);
        
        Long count = folderService.getCountByBlocId(blocId);
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<FolderInfoResponse> getFolderInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for folder ID: {}", id);
        
        try {
            return folderService.findOne(id)
                    .map(folderDTO -> {
                        FolderInfoResponse response = FolderInfoResponse.builder()
                                .folderMetadata(folderDTO)
                                .hasCode(folderDTO.getCode() != null && !folderDTO.getCode().trim().isEmpty())
                                .hasArabicDesignation(folderDTO.getDesignationAr() != null && !folderDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(folderDTO.getDesignationEn() != null && !folderDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(folderDTO.getDesignationFr() != null && !folderDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(folderDTO.isMultilingual())
                                .isComplete(folderDTO.isComplete())
                                .isConfidential(folderDTO.isConfidential())
                                .isTemporary(folderDTO.isTemporary())
                                .isUrgent(folderDTO.isUrgent())
                                .isValid(folderDTO.isValid())
                                .defaultDesignation(folderDTO.getDefaultDesignation())
                                .displayTextWithCode(folderDTO.getDisplayTextWithCode())
                                .fullDisplayText(folderDTO.getFullDisplayText())
                                .locationPath(folderDTO.getLocationPath())
                                .folderType(folderDTO.getFolderType())
                                .priority(folderDTO.getPriority())
                                .shortDisplay(folderDTO.getShortDisplay())
                                .availableLanguages(folderDTO.getAvailableLanguages())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting folder info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class FolderInfoResponse {
        private FolderDTO folderMetadata;
        private Boolean hasCode;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isComplete;
        private Boolean isConfidential;
        private Boolean isTemporary;
        private Boolean isUrgent;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayTextWithCode;
        private String fullDisplayText;
        private String locationPath;
        private String folderType;
        private String priority;
        private String shortDisplay;
        private String[] availableLanguages;
    }
}
