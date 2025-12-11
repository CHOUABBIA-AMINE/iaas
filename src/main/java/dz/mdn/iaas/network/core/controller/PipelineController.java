/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Network / Controller
 *	@Package	: Network / Controller
 *
 **/

package dz.mdn.iaas.network.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.dto.PipelineDTO;
import dz.mdn.iaas.network.service.PipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/pipeline")
@Slf4j
public class PipelineController extends GenericController<PipelineDTO, Long> {

    private final PipelineService pipelineService;

    public PipelineController(PipelineService pipelineService) {
        super(pipelineService, "Pipeline");
        this.pipelineService = pipelineService;
    }

    @GetMapping("/by-pipeline-system/{systemId}")
    public ResponseEntity<List<PipelineDTO>> getByPipelineSystem(@PathVariable Long systemId) {
        log.info("REST request to get Pipelines by pipeline system id: {}", systemId);
        return ResponseEntity.ok(pipelineService.findByPipelineSystem(systemId));
    }
}
