/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StateController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.StateDTO;
import dz.mdn.iaas.common.administration.service.StateService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * State REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus state-specific operations
 */
@RestController
@RequestMapping("/state")
@Slf4j
public class StateController extends GenericController<StateDTO, Long> {

    private final StateService stateService;

    public StateController(StateService stateService) {
        super(stateService, "State");
        this.stateService = stateService;
    }

    @Override
    protected Page<StateDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return stateService.getAll(pageable);
        }
        return stateService.globalSearch(query, pageable);
    }

    /**
     * Get all states as list (non-paginated)
     * GET /state/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<StateDTO>> getAllList() {
        log.debug("GET /state/list - Getting all states as list");
        List<StateDTO> states = stateService.getAll();
        return success(states);
    }

    /**
     * Get states by country ID
     * GET /state/country/{countryId}
     */
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<StateDTO>> getByCountryId(@PathVariable Long countryId) {
        log.debug("GET /state/country/{} - Getting states by country ID", countryId);
        List<StateDTO> states = stateService.getByCountryId(countryId);
        return success(states);
    }
}
