/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBoxController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Controller
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.dto.ArchiveBoxDTO;
import dz.mdn.iaas.common.environment.service.ArchiveBoxService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArchiveBox REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus archive box-specific operations
 */
@RestController
@RequestMapping("/archive-box")
@Slf4j
public class ArchiveBoxController extends GenericController<ArchiveBoxDTO, Long> {

    private final ArchiveBoxService archiveBoxService;

    public ArchiveBoxController(ArchiveBoxService archiveBoxService) {
        super(archiveBoxService, "ArchiveBox");
        this.archiveBoxService = archiveBoxService;
    }

    @Override
    protected Page<ArchiveBoxDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return archiveBoxService.getAll(pageable);
        }
        return archiveBoxService.globalSearch(query, pageable);
    }

    /**
     * Get all archive boxes as list (non-paginated)
     * GET /archive-box/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ArchiveBoxDTO>> getAllList() {
        log.debug("GET /archive-box/list - Getting all archive boxes as list");
        List<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getAll();
        return success(archiveBoxes);
    }

    /**
     * Get archive boxes by shelf floor ID
     * GET /archive-box/shelf-floor/{shelfFloorId}
     */
    @GetMapping("/shelf-floor/{shelfFloorId}")
    public ResponseEntity<List<ArchiveBoxDTO>> getByShelfFloorId(@PathVariable Long shelfFloorId) {
        log.debug("GET /archive-box/shelf-floor/{} - Getting archive boxes by shelf floor ID", shelfFloorId);
        List<ArchiveBoxDTO> archiveBoxes = archiveBoxService.getByShelfFloorId(shelfFloorId);
        return success(archiveBoxes);
    }
}
