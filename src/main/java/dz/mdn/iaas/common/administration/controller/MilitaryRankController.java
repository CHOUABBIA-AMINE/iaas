/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRankController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.MilitaryRankDTO;
import dz.mdn.iaas.common.administration.service.MilitaryRankService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MilitaryRank REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus military rank-specific operations
 */
@RestController
@RequestMapping("/military-rank")
@Slf4j
public class MilitaryRankController extends GenericController<MilitaryRankDTO, Long> {

    private final MilitaryRankService militaryRankService;

    public MilitaryRankController(MilitaryRankService militaryRankService) {
        super(militaryRankService, "MilitaryRank");
        this.militaryRankService = militaryRankService;
    }

    @Override
    protected Page<MilitaryRankDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return militaryRankService.getAll(pageable);
        }
        return militaryRankService.globalSearch(query, pageable);
    }

    /**
     * Get all military ranks as list (non-paginated)
     * GET /military-rank/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<MilitaryRankDTO>> getAllList() {
        log.debug("GET /military-rank/list - Getting all military ranks as list");
        List<MilitaryRankDTO> ranks = militaryRankService.getAll();
        return success(ranks);
    }

    /**
     * Get military ranks by category ID
     * GET /military-rank/category/{categoryId}
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<MilitaryRankDTO>> getByCategoryId(@PathVariable Long categoryId) {
        log.debug("GET /military-rank/category/{} - Getting military ranks by category ID", categoryId);
        List<MilitaryRankDTO> ranks = militaryRankService.getByCategoryId(categoryId);
        return success(ranks);
    }

    /**
     * Get military ranks by hierarchy level
     * GET /military-rank/hierarchy/{hierarchyLevel}
     */
    @GetMapping("/hierarchy/{hierarchyLevel}")
    public ResponseEntity<List<MilitaryRankDTO>> getByHierarchyLevel(@PathVariable Integer hierarchyLevel) {
        log.debug("GET /military-rank/hierarchy/{} - Getting military ranks by hierarchy level", hierarchyLevel);
        List<MilitaryRankDTO> ranks = militaryRankService.getByHierarchyLevel(hierarchyLevel);
        return success(ranks);
    }
}
