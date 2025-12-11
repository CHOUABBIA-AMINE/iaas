/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BlocController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Controller
 *
 **/

package dz.mdn.iaas.common.environment.controller;

import dz.mdn.iaas.common.environment.dto.BlocDTO;
import dz.mdn.iaas.common.environment.service.BlocService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Bloc REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Bloc management
 */
@RestController
@RequestMapping("/bloc")
@Slf4j
public class BlocController extends GenericController<BlocDTO, Long> {

    private final BlocService blocService;

    public BlocController(BlocService blocService) {
        super(blocService, "Bloc");
        this.blocService = blocService;
    }

    @Override
    protected Page<BlocDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return blocService.getAll(pageable);
        }
        return blocService.globalSearch(query, pageable);
    }

    /**
     * Get all blocs as list (non-paginated)
     * GET /bloc/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<BlocDTO>> getAllList() {
        log.debug("GET /bloc/list - Getting all blocs as list");
        List<BlocDTO> blocs = blocService.getAll();
        return success(blocs);
    }
}
