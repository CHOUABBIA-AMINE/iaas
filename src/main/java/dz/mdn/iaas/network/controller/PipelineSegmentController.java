/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineSegmentController
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
import dz.mdn.iaas.network.dto.PipelineSegmentDTO;
import dz.mdn.iaas.network.service.PipelineSegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/pipeline-segment")
@Slf4j
public class PipelineSegmentController extends GenericController<PipelineSegmentDTO, Long> {

    private final PipelineSegmentService pipelineSegmentService;

    public PipelineSegmentController(PipelineSegmentService pipelineSegmentService) {
        super(pipelineSegmentService, "PipelineSegment");
        this.pipelineSegmentService = pipelineSegmentService;
    }

    @GetMapping("/by-pipeline/{pipelineId}")
    public ResponseEntity<List<PipelineSegmentDTO>> getByPipeline(@PathVariable Long pipelineId) {
        log.info("REST request to get Pipeline Segments by pipeline id: {}", pipelineId);
        return ResponseEntity.ok(pipelineSegmentService.findByPipeline(pipelineId));
    }
}
