/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ConsultationController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Controller
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.dto.ConsultationDTO;
import dz.mdn.iaas.business.consultation.service.ConsultationService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Consultation REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus consultation-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /consultation                 Create consultation
 * - GET    /consultation/{id}            Get by ID
 * - GET    /consultation                 Get all (paginated)
 * - GET    /consultation/all             Get all (non-paginated)
 * - PUT    /consultation/{id}            Update consultation
 * - DELETE /consultation/{id}            Delete consultation
 * - GET    /consultation/search?q=...    Global search
 * - GET    /consultation/{id}/exists     Check existence
 * - GET    /consultation/count           Total count
 */
@RestController
@RequestMapping("/consultation")
@Slf4j
public class ConsultationController extends GenericController<ConsultationDTO, Long> {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        super(consultationService, "Consultation");
        this.consultationService = consultationService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<ConsultationDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return consultationService.getAll(pageable);
        }
        return consultationService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all consultations without pagination (custom implementation)
     * GET /consultation/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ConsultationDTO>> getAllList() {
        log.debug("GET /consultation/list - Getting all consultations as list");
        List<ConsultationDTO> consultations = consultationService.getAll();
        return success(consultations);
    }
}
