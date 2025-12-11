/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: EconomicNatureController
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Controller
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Controller
 *
 **/

package dz.mdn.iaas.business.provider.controller;

import dz.mdn.iaas.business.provider.dto.EconomicNatureDTO;
import dz.mdn.iaas.business.provider.service.EconomicNatureService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EconomicNature REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus economic-nature-specific operations
 * 
 * Inherited Endpoints:
 * - POST   /economicNature                 Create economic nature
 * - GET    /economicNature/{id}            Get by ID
 * - GET    /economicNature                 Get all (paginated)
 * - GET    /economicNature/all             Get all (non-paginated)
 * - PUT    /economicNature/{id}            Update economic nature
 * - DELETE /economicNature/{id}            Delete economic nature
 * - GET    /economicNature/search?q=...    Global search
 * - GET    /economicNature/{id}/exists     Check existence
 * - GET    /economicNature/count           Total count
 */
@RestController
@RequestMapping("/economicNature")
@Slf4j
public class EconomicNatureController extends GenericController<EconomicNatureDTO, Long> {

    private final EconomicNatureService economicNatureService;

    public EconomicNatureController(EconomicNatureService economicNatureService) {
        super(economicNatureService, "EconomicNature");
        this.economicNatureService = economicNatureService;
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<EconomicNatureDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return economicNatureService.getAll(pageable);
        }
        return economicNatureService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get all economic natures without pagination (custom implementation)
     * GET /economicNature/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<EconomicNatureDTO>> getAllList() {
        log.debug("GET /economicNature/list - Getting all economic natures as list");
        List<EconomicNatureDTO> economicNatures = economicNatureService.getAll();
        return success(economicNatures);
    }
}
