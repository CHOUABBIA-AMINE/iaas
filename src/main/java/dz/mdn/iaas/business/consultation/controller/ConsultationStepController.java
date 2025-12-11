/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ConsultationStepController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Controller
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.dto.ConsultationStepDTO;
import dz.mdn.iaas.business.consultation.service.ConsultationStepService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ConsultationStep REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus consultation-step-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /consultationStep                 Create consultation step
 * - GET    /consultationStep/{id}            Get by ID
 * - GET    /consultationStep                 Get all (paginated)
 * - GET    /consultationStep/all             Get all (non-paginated)
 * - PUT    /consultationStep/{id}            Update consultation step
 * - DELETE /consultationStep/{id}            Delete consultation step
 * - GET    /consultationStep/search?q=...    Global search
 * - GET    /consultationStep/{id}/exists     Check existence
 * - GET    /consultationStep/count           Total count
 */
@RestController
@RequestMapping("/consultationStep")
@Slf4j
public class ConsultationStepController extends GenericController<ConsultationStepDTO, Long> {

    private final ConsultationStepService consultationStepService;

    public ConsultationStepController(ConsultationStepService consultationStepService) {
        super(consultationStepService, "ConsultationStep");
        this.consultationStepService = consultationStepService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ConsultationStepDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return consultationStepService.getAll(pageable);
        }
        return consultationStepService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all consultation steps without pagination (custom implementation)
     * GET /consultationStep/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ConsultationStepDTO>> getAllList() {
        log.debug("GET /consultationStep/list - Getting all consultation steps as list");
        List<ConsultationStepDTO> consultationSteps = consultationStepService.getAll();
        return success(consultationSteps);
    }
}
