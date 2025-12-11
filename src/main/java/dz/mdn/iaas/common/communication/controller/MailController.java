/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Communication
 *	@Package	: Common / Communication / Controller
 *
 **/

package dz.mdn.iaas.common.communication.controller;

import dz.mdn.iaas.common.communication.dto.MailDTO;
import dz.mdn.iaas.common.communication.service.MailService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Mail REST Controller - Extends GenericController
 * Provides standard CRUD endpoints for Mail management
 */
@RestController
@RequestMapping("/mail")
@Slf4j
public class MailController extends GenericController<MailDTO, Long> {

    private final MailService mailService;

    public MailController(MailService mailService) {
        super(mailService, "Mail");
        this.mailService = mailService;
    }

    @Override
    protected Page<MailDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return mailService.getAll(pageable);
        }
        return mailService.globalSearch(query, pageable);
    }

    /**
     * Get all mails as list (non-paginated)
     * GET /mail/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<MailDTO>> getAllList() {
        log.debug("GET /mail/list - Getting all mails as list");
        List<MailDTO> mails = mailService.getAll();
        return success(mails);
    }
}
