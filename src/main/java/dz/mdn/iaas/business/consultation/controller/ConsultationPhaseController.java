/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ConsultationPhaseController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Controller
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.dto.ConsultationPhaseDTO;
import dz.mdn.iaas.business.consultation.service.ConsultationPhaseService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ConsultationPhase REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus consultation-phase-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /consultationPhase                 Create consultation phase
 * - GET    /consultationPhase/{id}            Get by ID
 * - GET    /consultationPhase                 Get all (paginated)
 * - GET    /consultationPhase/all             Get all (non-paginated)
 * - PUT    /consultationPhase/{id}            Update consultation phase
 * - DELETE /consultationPhase/{id}            Delete consultation phase
 * - GET    /consultationPhase/search?q=...    Global search
 * - GET    /consultationPhase/{id}/exists     Check existence
 * - GET    /consultationPhase/count           Total count
 */
@RestController
@RequestMapping("/consultationPhase")
@Slf4j
public class ConsultationPhaseController extends GenericController<ConsultationPhaseDTO, Long> {

    private final ConsultationPhaseService consultationPhaseService;

    public ConsultationPhaseController(ConsultationPhaseService consultationPhaseService) {
        super(consultationPhaseService, "ConsultationPhase");
        this.consultationPhaseService = consultationPhaseService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ConsultationPhaseDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return consultationPhaseService.getAll(pageable);
        }
        return consultationPhaseService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all consultation phases without pagination (custom implementation)
     * GET /consultationPhase/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ConsultationPhaseDTO>> getAllList() {
        log.debug("GET /consultationPhase/list - Getting all consultation phases as list");
        List<ConsultationPhaseDTO> consultationPhases = consultationPhaseService.getAll();
        return success(consultationPhases);
    }
}
