/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailController
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.controller;

import dz.mdn.iaas.common.communication.dto.MailDTO;
import dz.mdn.iaas.common.communication.service.MailService;
import dz.mdn.iaas.configuration.template.GenericController;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // ========== SECURED CRUD OPERATIONS ==========

    @Override
    @PreAuthorize("hasAuthority('MAIL:READ')")
    public ResponseEntity<MailDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:READ')")
    public ResponseEntity<Page<MailDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return super.getAll(page, size, sortBy, sortDir);
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:READ')")
    public ResponseEntity<List<MailDTO>> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:ADMIN')")
    public ResponseEntity<MailDTO> create(@Valid @RequestBody MailDTO dto) {
        return super.create(dto);
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:ADMIN')")
    public ResponseEntity<MailDTO> update(@PathVariable Long id, @Valid @RequestBody MailDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:READ')")
    public ResponseEntity<Page<MailDTO>> search(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return super.search(q, page, size, sortBy, sortDir);
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:READ')")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return super.exists(id);
    }

    @Override
    @PreAuthorize("hasAuthority('MAIL:READ')")
    public ResponseEntity<Long> count() {
        return super.count();
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
    @PreAuthorize("hasAuthority('MAIL:READ')")
    public ResponseEntity<List<MailDTO>> getAllList() {
        log.debug("GET /mail/list - Getting all mails as list");
        List<MailDTO> mails = mailService.getAll();
        return success(mails);
    }
}
