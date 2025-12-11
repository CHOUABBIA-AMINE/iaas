/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FolderController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Controller
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.dto.FolderDTO;
import dz.mdn.iaas.common.environment.service.FolderService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Folder REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus folder-specific operations
 */
@RestController
@RequestMapping("/folder")
@Slf4j
public class FolderController extends GenericController<FolderDTO, Long> {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        super(folderService, "Folder");
        this.folderService = folderService;
    }

    @Override
    protected Page<FolderDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return folderService.getAll(pageable);
        }
        return folderService.globalSearch(query, pageable);
    }

    /**
     * Get all folders as list (non-paginated)
     * GET /folder/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<FolderDTO>> getAllList() {
        log.debug("GET /folder/list - Getting all folders as list");
        List<FolderDTO> folders = folderService.getAll();
        return success(folders);
    }

    /**
     * Get folders by archive box ID
     * GET /folder/archive-box/{archiveBoxId}
     */
    @GetMapping("/archive-box/{archiveBoxId}")
    public ResponseEntity<List<FolderDTO>> getByArchiveBoxId(@PathVariable Long archiveBoxId) {
        log.debug("GET /folder/archive-box/{} - Getting folders by archive box ID", archiveBoxId);
        List<FolderDTO> folders = folderService.getByArchiveBoxId(archiveBoxId);
        return success(folders);
    }
}
