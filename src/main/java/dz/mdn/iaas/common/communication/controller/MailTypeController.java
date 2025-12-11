/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailTypeController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Communication
 *	@Package	: Common / Communication / Controller
 *
 **/

package dz.mdn.iaas.common.communication.controller;

import dz.mdn.iaas.common.communication.dto.MailTypeDTO;
import dz.mdn.iaas.common.communication.service.MailTypeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MailType REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Mail Type management
 */
@RestController
@RequestMapping("/mail-type")
@Slf4j
public class MailTypeController extends GenericController<MailTypeDTO, Long> {

    private final MailTypeService mailTypeService;

    public MailTypeController(MailTypeService mailTypeService) {
        super(mailTypeService, "MailType");
        this.mailTypeService = mailTypeService;
    }

    @Override
    protected Page<MailTypeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return mailTypeService.getAll(pageable);
        }
        return mailTypeService.globalSearch(query, pageable);
    }

    /**
     * Get all mail types as list (non-paginated)
     * GET /mail-type/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<MailTypeDTO>> getAllList() {
        log.debug("GET /mail-type/list - Getting all mail types as list");
        List<MailTypeDTO> mailTypes = mailTypeService.getAll();
        return success(mailTypes);
    }
}
