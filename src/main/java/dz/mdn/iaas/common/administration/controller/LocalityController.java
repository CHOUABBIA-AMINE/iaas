/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocalityController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.LocalityDTO;
import dz.mdn.iaas.common.administration.service.LocalityService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Locality REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus locality-specific operations
 */
@RestController
@RequestMapping("/locality")
@Slf4j
public class LocalityController extends GenericController<LocalityDTO, Long> {

    private final LocalityService localityService;

    public LocalityController(LocalityService localityService) {
        super(localityService, "Locality");
        this.localityService = localityService;
    }

    @Override
    protected Page<LocalityDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return localityService.getAll(pageable);
        }
        return localityService.globalSearch(query, pageable);
    }

    /**
     * Get all localities as list (non-paginated)
     * GET /locality/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<LocalityDTO>> getAllList() {
        log.debug("GET /locality/list - Getting all localities as list");
        List<LocalityDTO> localities = localityService.getAll();
        return success(localities);
    }

    /**
     * Get localities by state ID
     * GET /locality/state/{stateId}
     */
    @GetMapping("/state/{stateId}")
    public ResponseEntity<List<LocalityDTO>> getByStateId(@PathVariable Long stateId) {
        log.debug("GET /locality/state/{} - Getting localities by state ID", stateId);
        List<LocalityDTO> localities = localityService.getByStateId(stateId);
        return success(localities);
    }
}
