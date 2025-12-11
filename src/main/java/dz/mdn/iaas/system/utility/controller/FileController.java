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
import java.util.Map;

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
     * Find files by extension and file type
     * GET /system/file/by-extension-and-type?extension=...&fileType=...
     */
    @GetMapping("/by-extension-and-type")
    public ResponseEntity<List<FileDTO>> getByExtensionAndFileType(
            @RequestParam String extension,
            @RequestParam String fileType) {
        log.info("REST request to get Files by extension: {} and file type: {}", extension, fileType);
        return ResponseEntity.ok(fileService.findByExtensionAndFileType(extension, fileType));
    }

    /**
     * Find large files
     * GET /system/file/large?minSize=...
     */
    @GetMapping("/large")
    public ResponseEntity<List<FileDTO>> getLargeFiles(@RequestParam Long minSize) {
        log.info("REST request to get large files (min size: {} bytes)", minSize);
        return ResponseEntity.ok(fileService.findLargeFiles(minSize));
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

    /**
     * Get total storage size
     * GET /system/file/total-size
     */
    @GetMapping("/total-size")
    public ResponseEntity<Map<String, Object>> getTotalStorageSize() {
        log.info("REST request to get total storage size");
        Long totalSize = fileService.getTotalStorageSize();
        return ResponseEntity.ok(Map.of(
            "totalSize", totalSize,
            "formatted", formatSize(totalSize)
        ));
    }

    /**
     * Count files by extension
     * GET /system/file/count-by-extension/{extension}
     */
    @GetMapping("/count-by-extension/{extension}")
    public ResponseEntity<Map<String, Object>> countByExtension(@PathVariable String extension) {
        log.info("REST request to count files by extension: {}", extension);
        long count = fileService.countByExtension(extension);
        return ResponseEntity.ok(Map.of(
            "extension", extension,
            "count", count
        ));
    }

    // Helper method
    private String formatSize(Long size) {
        if (size == null || size == 0) return "0 B";
        if (size < 1024) return size + " B";
        if (size < 1024 * 1024) return String.format("%.1f KB", size / 1024.0);
        if (size < 1024 * 1024 * 1024) return String.format("%.1f MB", size / (1024.0 * 1024));
        return String.format("%.1f GB", size / (1024.0 * 1024 * 1024));
    }
}
