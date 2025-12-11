/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailNatureController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Communication
 *	@Package	: Common / Communication / Controller
 *
 **/

package dz.mdn.iaas.common.communication.controller;

import dz.mdn.iaas.common.communication.dto.MailNatureDTO;
import dz.mdn.iaas.common.communication.service.MailNatureService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MailNature REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Mail Nature management
 */
@RestController
@RequestMapping("/mail-nature")
@Slf4j
public class MailNatureController extends GenericController<MailNatureDTO, Long> {

    private final MailNatureService mailNatureService;

    public MailNatureController(MailNatureService mailNatureService) {
        super(mailNatureService, "MailNature");
        this.mailNatureService = mailNatureService;
    }

    @Override
    protected Page<MailNatureDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return mailNatureService.getAll(pageable);
        }
        return mailNatureService.globalSearch(query, pageable);
    }

    /**
     * Get all mail natures as list (non-paginated)
     * GET /mail-nature/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<MailNatureDTO>> getAllList() {
        log.debug("GET /mail-nature/list - Getting all mail natures as list");
        List<MailNatureDTO> mailNatures = mailNatureService.getAll();
        return success(mailNatures);
    }
}
