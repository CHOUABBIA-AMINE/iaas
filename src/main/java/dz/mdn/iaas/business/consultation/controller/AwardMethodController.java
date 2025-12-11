/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: AwardMethodController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Controller
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import dz.mdn.iaas.business.consultation.dto.AwardMethodDTO;
import dz.mdn.iaas.business.consultation.service.AwardMethodService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AwardMethod REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus award-method-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /awardMethod                 Create award method
 * - GET    /awardMethod/{id}            Get by ID
 * - GET    /awardMethod                 Get all (paginated)
 * - GET    /awardMethod/all             Get all (non-paginated)
 * - PUT    /awardMethod/{id}            Update award method
 * - DELETE /awardMethod/{id}            Delete award method
 * - GET    /awardMethod/search?q=...    Global search
 * - GET    /awardMethod/{id}/exists     Check existence
 * - GET    /awardMethod/count           Total count
 */
@RestController
@RequestMapping("/awardMethod")
@Slf4j
public class AwardMethodController extends GenericController<AwardMethodDTO, Long> {

    private final AwardMethodService awardMethodService;

    public AwardMethodController(AwardMethodService awardMethodService) {
        super(awardMethodService, "AwardMethod");
        this.awardMethodService = awardMethodService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<AwardMethodDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return awardMethodService.getAll(pageable);
        }
        return awardMethodService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all award methods without pagination (custom implementation)
     * GET /awardMethod/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<AwardMethodDTO>> getAllList() {
        log.debug("GET /awardMethod/list - Getting all award methods as list");
        List<AwardMethodDTO> awardMethods = awardMethodService.getAll();
        return success(awardMethods);
    }
}
