/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryCategoryController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.MilitaryCategoryDTO;
import dz.mdn.iaas.common.administration.service.MilitaryCategoryService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MilitaryCategory REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Military Category management
 */
@RestController
@RequestMapping("/military-category")
@Slf4j
public class MilitaryCategoryController extends GenericController<MilitaryCategoryDTO, Long> {

    private final MilitaryCategoryService militaryCategoryService;

    public MilitaryCategoryController(MilitaryCategoryService militaryCategoryService) {
        super(militaryCategoryService, "MilitaryCategory");
        this.militaryCategoryService = militaryCategoryService;
    }

    @Override
    protected Page<MilitaryCategoryDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return militaryCategoryService.getAll(pageable);
        }
        return militaryCategoryService.globalSearch(query, pageable);
    }

    /**
     * Get all military categories as list (non-paginated)
     * GET /military-category/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<MilitaryCategoryDTO>> getAllList() {
        log.debug("GET /military-category/list - Getting all military categories as list");
        List<MilitaryCategoryDTO> categories = militaryCategoryService.getAll();
        return success(categories);
    }
}
