/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CountryController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.CountryDTO;
import dz.mdn.iaas.common.administration.service.CountryService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Country REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Country management
 */
@RestController
@RequestMapping("/country")
@Slf4j
public class CountryController extends GenericController<CountryDTO, Long> {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        super(countryService, "Country");
        this.countryService = countryService;
    }

    @Override
    protected Page<CountryDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return countryService.getAll(pageable);
        }
        return countryService.globalSearch(query, pageable);
    }

    /**
     * Get all countries as list (non-paginated)
     * GET /country/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<CountryDTO>> getAllList() {
        log.debug("GET /country/list - Getting all countries as list");
        List<CountryDTO> countries = countryService.getAll();
        return success(countries);
    }
}
