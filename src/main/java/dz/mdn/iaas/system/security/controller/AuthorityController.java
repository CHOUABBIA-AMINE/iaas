/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AuthorityController
 *	@CreatedOn	: 11-18-2025
 *	@Updated	: 12-12-2025
 *
 *	@Type		: Controller
 *	@Layer		: Controller
 *	@Package	: System / Security
 *
 **/

package dz.mdn.iaas.system.security.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.system.security.dto.AuthorityDTO;
import dz.mdn.iaas.system.security.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/security/authority")
@Slf4j
public class AuthorityController extends GenericController<AuthorityDTO, Long> {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        super(authorityService, "Authority");
        this.authorityService = authorityService;
    }

    // ========== STANDARD CRUD OPERATIONS (From GenericController) ==========
    // Inherited:
    // - GET    /system/security/authority           -> getAll(Pageable)
    // - GET    /system/security/authority/{id}      -> getById(Long)
    // - POST   /system/security/authority           -> create(AuthorityDTO)
    // - PUT    /system/security/authority/{id}      -> update(Long, AuthorityDTO)
    // - DELETE /system/security/authority/{id}      -> delete(Long)

    // ========== CUSTOM QUERY OPERATIONS ==========

    /**
     * Find authority by name
     * GET /system/security/authority/by-name/{name}
     */
    @GetMapping("/by-name/{name}")
    @PreAuthorize("hasAuthority('AUTHORITY:READ')")
    public ResponseEntity<AuthorityDTO> getByName(@PathVariable String name) {
        log.info("REST request to get Authority by name: {}", name);
        return ResponseEntity.ok(authorityService.findByName(name));
    }

    /**
     * Find authorities by type
     * GET /system/security/authority/by-type/{type}
     */
    @GetMapping("/by-type/{type}")
    @PreAuthorize("hasAuthority('AUTHORITY:READ')")
    public ResponseEntity<List<AuthorityDTO>> getByType(@PathVariable String type) {
        log.info("REST request to get Authorities by type: {}", type);
        return ResponseEntity.ok(authorityService.findByType(type));
    }

    /**
     * Check if authority exists by name
     * GET /system/security/authority/exists/{name}
     */
    @GetMapping("/exists/{name}")
    @PreAuthorize("hasAuthority('AUTHORITY:READ')")
    public ResponseEntity<Map<String, Boolean>> checkExists(@PathVariable String name) {
        log.info("REST request to check if Authority exists: {}", name);
        boolean exists = authorityService.existsByName(name);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
}
