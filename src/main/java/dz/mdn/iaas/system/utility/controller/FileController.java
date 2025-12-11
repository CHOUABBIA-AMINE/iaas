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
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // ========== PHYSICAL FILE OPERATIONS ==========

    /**
     * Upload file
     * POST /system/file/upload
     */
    @PostMapping("/upload")
    public ResponseEntity<FileDTO> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "fileType", required = false) String fileType) {
        log.info("REST request to upload file: {}", file.getOriginalFilename());
        
        FileDTO uploadedFile = fileService.uploadFile(file, fileType);
        
        return ResponseEntity.ok(uploadedFile);
    }

    /**
     * Download file
     * GET /system/file/{id}/download
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        log.info("REST request to download file with id: {}", id);
        
        Resource resource = fileService.downloadFile(id);
        FileDTO fileDTO = fileService.getById(id);
        
        // Determine content type
        String contentType = determineContentType(fileDTO.getExtension());
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * Delete file physically
     * DELETE /system/file/{id}/physical
     */
    @DeleteMapping("/{id}/physical")
    public ResponseEntity<Void> deleteFilePhysically(@PathVariable Long id) {
        log.info("REST request to delete file physically with id: {}", id);
        
        fileService.deleteFilePhysically(id);
        
        return ResponseEntity.noContent().build();
    }

    /**
     * Check if physical file exists
     * GET /system/file/{id}/exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Map<String, Boolean>> checkPhysicalFileExists(@PathVariable Long id) {
        log.info("REST request to check if physical file exists with id: {}", id);
        
        boolean exists = fileService.physicalFileExists(id);
        
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    /**
     * Get physical file size
     * GET /system/file/{id}/physical-size
     */
    @GetMapping("/{id}/physical-size")
    public ResponseEntity<Map<String, Object>> getPhysicalFileSize(@PathVariable Long id) {
        log.info("REST request to get physical file size with id: {}", id);
        
        Long size = fileService.getPhysicalFileSize(id);
        
        return ResponseEntity.ok(Map.of(
            "size", size != null ? size : 0,
            "formatted", formatSize(size)
        ));
    }

    // ========== METADATA QUERY ENDPOINTS ==========

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

    // ========== HELPER METHODS ==========

    private String determineContentType(String extension) {
        if (extension == null) return "application/octet-stream";
        
        return switch (extension.toLowerCase()) {
            case "pdf" -> "application/pdf";
            case "txt" -> "text/plain";
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            case "json" -> "application/json";
            case "xml" -> "application/xml";
            case "zip" -> "application/zip";
            case "doc", "docx" -> "application/msword";
            case "xls", "xlsx" -> "application/vnd.ms-excel";
            case "ppt", "pptx" -> "application/vnd.ms-powerpoint";
            default -> "application/octet-stream";
        };
    }

    private String formatSize(Long size) {
        if (size == null || size == 0) return "0 B";
        if (size < 1024) return size + " B";
        if (size < 1024 * 1024) return String.format("%.1f KB", size / 1024.0);
        if (size < 1024 * 1024 * 1024) return String.format("%.1f MB", size / (1024.0 * 1024));
        return String.format("%.1f GB", size / (1024.0 * 1024 * 1024));
    }
}
