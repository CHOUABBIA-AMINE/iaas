/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineSystemController
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
import dz.mdn.iaas.network.dto.PipelineSystemDTO;
import dz.mdn.iaas.network.service.PipelineSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/pipeline-system")
@Slf4j
public class PipelineSystemController extends GenericController<PipelineSystemDTO, Long> {

    private final PipelineSystemService pipelineSystemService;

    public PipelineSystemController(PipelineSystemService pipelineSystemService) {
        super(pipelineSystemService);
        this.pipelineSystemService = pipelineSystemService;
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<PipelineSystemDTO>> getByProduct(@PathVariable Long productId) {
        log.info("REST request to get Pipeline Systems by product id: {}", productId);
        return ResponseEntity.ok(pipelineSystemService.findByProduct(productId));
    }
}
