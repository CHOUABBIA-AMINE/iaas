/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PersonController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.PersonDTO;
import dz.mdn.iaas.common.administration.service.PersonService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Person REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Person management
 */
@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController extends GenericController<PersonDTO, Long> {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        super(personService, "Person");
        this.personService = personService;
    }

    @Override
    protected Page<PersonDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return personService.getAll(pageable);
        }
        return personService.globalSearch(query, pageable);
    }

    /**
     * Get all persons as list (non-paginated)
     * GET /person/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<PersonDTO>> getAllList() {
        log.debug("GET /person/list - Getting all persons as list");
        List<PersonDTO> persons = personService.getAll();
        return success(persons);
    }
}
