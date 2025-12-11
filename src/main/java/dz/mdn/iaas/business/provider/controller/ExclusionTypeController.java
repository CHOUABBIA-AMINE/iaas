/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ExclusionTypeController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Controller
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.dto.ExclusionTypeDTO;
import dz.mdn.iaas.business.provider.service.ExclusionTypeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ExclusionType REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus exclusion-type-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /exclusionType                 Create exclusion type
 * - GET    /exclusionType/{id}            Get by ID
 * - GET    /exclusionType                 Get all (paginated)
 * - GET    /exclusionType/all             Get all (non-paginated)
 * - PUT    /exclusionType/{id}            Update exclusion type
 * - DELETE /exclusionType/{id}            Delete exclusion type
 * - GET    /exclusionType/search?q=...    Global search
 * - GET    /exclusionType/{id}/exists     Check existence
 * - GET    /exclusionType/count           Total count
 */
@RestController
@RequestMapping("/exclusionType")
@Slf4j
public class ExclusionTypeController extends GenericController<ExclusionTypeDTO, Long> {

    private final ExclusionTypeService exclusionTypeService;

    public ExclusionTypeController(ExclusionTypeService exclusionTypeService) {
        super(exclusionTypeService, "ExclusionType");
        this.exclusionTypeService = exclusionTypeService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ExclusionTypeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return exclusionTypeService.getAll(pageable);
        }
        return exclusionTypeService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all exclusion types without pagination (custom implementation)
     * GET /exclusionType/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ExclusionTypeDTO>> getAllList() {
        log.debug("GET /exclusionType/list - Getting all exclusion types as list");
        List<ExclusionTypeDTO> exclusionTypes = exclusionTypeService.getAll();
        return success(exclusionTypes);
    }
}
