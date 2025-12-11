/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: JobController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.JobDTO;
import dz.mdn.iaas.common.administration.service.JobService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Job REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Job management
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController extends GenericController<JobDTO, Long> {

    private final JobService jobService;

    public JobController(JobService jobService) {
        super(jobService, "Job");
        this.jobService = jobService;
    }

    @Override
    protected Page<JobDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return jobService.getAll(pageable);
        }
        return jobService.globalSearch(query, pageable);
    }

    /**
     * Get all jobs as list (non-paginated)
     * GET /job/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<JobDTO>> getAllList() {
        log.debug("GET /job/list - Getting all jobs as list");
        List<JobDTO> jobs = jobService.getAll();
        return success(jobs);
    }
}
