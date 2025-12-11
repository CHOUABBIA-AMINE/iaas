/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.StructureDTO;
import dz.mdn.iaas.common.administration.service.StructureService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Structure REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus structure-specific operations
 */
@RestController
@RequestMapping("/structure")
@Slf4j
public class StructureController extends GenericController<StructureDTO, Long> {

    private final StructureService structureService;

    public StructureController(StructureService structureService) {
        super(structureService, "Structure");
        this.structureService = structureService;
    }

    @Override
    protected Page<StructureDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return structureService.getAll(pageable);
        }
        return structureService.globalSearch(query, pageable);
    }

    /**
     * Get all structures as list (non-paginated)
     * GET /structure/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<StructureDTO>> getAllList() {
        log.debug("GET /structure/list - Getting all structures as list");
        List<StructureDTO> structures = structureService.getAll();
        return success(structures);
    }

    /**
     * Get structures by structure type ID
     * GET /structure/type/{structureTypeId}
     */
    @GetMapping("/type/{structureTypeId}")
    public ResponseEntity<List<StructureDTO>> getByStructureTypeId(@PathVariable Long structureTypeId) {
        log.debug("GET /structure/type/{} - Getting structures by structure type ID", structureTypeId);
        List<StructureDTO> structures = structureService.getByStructureTypeId(structureTypeId);
        return success(structures);
    }

    /**
     * Get structures by parent structure ID
     * GET /structure/parent/{parentStructureId}
     */
    @GetMapping("/parent/{parentStructureId}")
    public ResponseEntity<List<StructureDTO>> getByParentStructureId(@PathVariable Long parentStructureId) {
        log.debug("GET /structure/parent/{} - Getting structures by parent structure ID", parentStructureId);
        List<StructureDTO> structures = structureService.getByParentStructureId(parentStructureId);
        return success(structures);
    }

    /**
     * Get root structures (no parent)
     * GET /structure/root
     */
    @GetMapping("/root")
    public ResponseEntity<List<StructureDTO>> getRootStructures() {
        log.debug("GET /structure/root - Getting root structures");
        List<StructureDTO> structures = structureService.getRootStructures();
        return success(structures);
    }
}
