/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureTypeController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.StructureTypeDTO;
import dz.mdn.iaas.common.administration.service.StructureTypeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * StructureType REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus structure type-specific operations
 */
@RestController
@RequestMapping("/structure-type")
@Slf4j
public class StructureTypeController extends GenericController<StructureTypeDTO, Long> {

    private final StructureTypeService structureTypeService;

    public StructureTypeController(StructureTypeService structureTypeService) {
        super(structureTypeService, "StructureType");
        this.structureTypeService = structureTypeService;
    }

    @Override
    protected Page<StructureTypeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return structureTypeService.getAll(pageable);
        }
        return structureTypeService.globalSearch(query, pageable);
    }

    /**
     * Get all structure types as list (non-paginated)
     * GET /structure-type/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<StructureTypeDTO>> getAllList() {
        log.debug("GET /structure-type/list - Getting all structure types as list");
        List<StructureTypeDTO> types = structureTypeService.getAll();
        return success(types);
    }

    /**
     * Get operational structure types
     * GET /structure-type/operational
     */
    @GetMapping("/operational")
    public ResponseEntity<List<StructureTypeDTO>> getOperationalTypes() {
        log.debug("GET /structure-type/operational - Getting operational structure types");
        List<StructureTypeDTO> types = structureTypeService.getOperationalTypes();
        return success(types);
    }

    /**
     * Get structure types by hierarchy level
     * GET /structure-type/hierarchy/{hierarchyLevel}
     */
    @GetMapping("/hierarchy/{hierarchyLevel}")
    public ResponseEntity<List<StructureTypeDTO>> getByHierarchyLevel(@PathVariable Integer hierarchyLevel) {
        log.debug("GET /structure-type/hierarchy/{} - Getting structure types by hierarchy level", hierarchyLevel);
        List<StructureTypeDTO> types = structureTypeService.getByHierarchyLevel(hierarchyLevel);
        return success(types);
    }
}
