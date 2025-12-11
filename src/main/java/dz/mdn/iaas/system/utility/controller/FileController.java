/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FileController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Controller
 *	@Package	: System / Utility
 *
 **/

package dz.mdn.iaas.system.utility.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.system.utility.dto.FileDTO;
import dz.mdn.iaas.system.utility.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/file")
@Slf4j
public class FileController extends GenericController<FileDTO, Long> {

    private final FileService fileService;

    public FileController(FileService fileService) {
        super(fileService, "File");
        this.fileService = fileService;
    }

    /**
     * Find file by path
     * GET /system/file/by-path?path=...
     */
    @GetMapping("/by-path")
    public ResponseEntity<FileDTO> getByPath(@RequestParam String path) {
        log.info("REST request to get File by path: {}", path);
        return ResponseEntity.ok(fileService.findByPath(path));
    }

    /**
     * Find files by extension
     * GET /system/file/by-extension/{extension}
     */
    @GetMapping("/by-extension/{extension}")
    public ResponseEntity<List<FileDTO>> getByExtension(@PathVariable String extension) {
        log.info("REST request to get Files by extension: {}", extension);
        return ResponseEntity.ok(fileService.findByExtension(extension));
    }

    /**
     * Find files by file type
     * GET /system/file/by-type/{fileType}
     */
    @GetMapping("/by-type/{fileType}")
    public ResponseEntity<List<FileDTO>> getByFileType(@PathVariable String fileType) {
        log.info("REST request to get Files by file type: {}", fileType);
        return ResponseEntity.ok(fileService.findByFileType(fileType));
    }

    /**
     * Get storage statistics
     * GET /system/file/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<FileService.FileStorageStats> getStorageStats() {
        log.info("REST request to get storage statistics");
        return ResponseEntity.ok(fileService.getStorageStats());
    }
}
