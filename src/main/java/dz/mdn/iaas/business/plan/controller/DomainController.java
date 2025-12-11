/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: DomainController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Controller
 *
 **/

package dz.mdn.iaas.business.plan.controller;

import dz.mdn.iaas.business.plan.dto.DomainDTO;
import dz.mdn.iaas.business.plan.service.DomainService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Domain REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus domain-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /domain                 Create domain
 * - GET    /domain/{id}            Get by ID
 * - GET    /domain                 Get all (paginated)
 * - GET    /domain/all             Get all (non-paginated)
 * - PUT    /domain/{id}            Update domain
 * - DELETE /domain/{id}            Delete domain
 * - GET    /domain/search?q=...    Global search
 * - GET    /domain/{id}/exists     Check existence
 * - GET    /domain/count           Total count
 */
@RestController
@RequestMapping("/domain")
@Slf4j
public class DomainController extends GenericController<DomainDTO, Long> {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        super(domainService, "Domain");
        this.domainService = domainService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<DomainDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return domainService.getAll(pageable);
        }
        return domainService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all domains without pagination (custom implementation)
     * GET /domain/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<DomainDTO>> getAllList() {
        log.debug("GET /domain/list - Getting all domains as list");
        List<DomainDTO> domains = domainService.getAll();
        return success(domains);
    }
}
