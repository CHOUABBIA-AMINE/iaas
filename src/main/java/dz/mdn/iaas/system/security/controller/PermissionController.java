/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PermissionController
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
import dz.mdn.iaas.system.security.dto.PermissionDTO;
import dz.mdn.iaas.system.security.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/security/permission")
@Slf4j
public class PermissionController extends GenericController<PermissionDTO, Long> {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        super(permissionService, "Permission");
        this.permissionService = permissionService;
    }

    // ========== STANDARD CRUD OPERATIONS (From GenericController) ==========
    // Inherited:
    // - GET    /system/security/permission           -> getAll(Pageable)
    // - GET    /system/security/permission/{id}      -> getById(Long)
    // - POST   /system/security/permission           -> create(PermissionDTO)
    // - PUT    /system/security/permission/{id}      -> update(Long, PermissionDTO)
    // - DELETE /system/security/permission/{id}      -> delete(Long)

    // ========== CUSTOM QUERY OPERATIONS ==========

    /**
     * Find permission by name
     * GET /system/security/permission/by-name/{name}
     */
    @GetMapping("/by-name/{name}")
    @PreAuthorize("hasAuthority('PERMISSION:READ')")
    public ResponseEntity<PermissionDTO> getByName(@PathVariable String name) {
        log.info("REST request to get Permission by name: {}", name);
        return ResponseEntity.ok(permissionService.findByName(name));
    }

    /**
     * Find permissions by resource
     * GET /system/security/permission/by-resource/{resource}
     */
    @GetMapping("/by-resource/{resource}")
    @PreAuthorize("hasAuthority('PERMISSION:READ')")
    public ResponseEntity<List<PermissionDTO>> getByResource(@PathVariable String resource) {
        log.info("REST request to get Permissions by resource: {}", resource);
        return ResponseEntity.ok(permissionService.findByResource(resource));
    }

    /**
     * Find permissions by action
     * GET /system/security/permission/by-action/{action}
     */
    @GetMapping("/by-action/{action}")
    @PreAuthorize("hasAuthority('PERMISSION:READ')")
    public ResponseEntity<List<PermissionDTO>> getByAction(@PathVariable String action) {
        log.info("REST request to get Permissions by action: {}", action);
        return ResponseEntity.ok(permissionService.findByAction(action));
    }

    /**
     * Find permissions by resource and action
     * GET /system/security/permission/by-resource-action?resource=X&action=Y
     */
    @GetMapping("/by-resource-action")
    @PreAuthorize("hasAuthority('PERMISSION:READ')")
    public ResponseEntity<List<PermissionDTO>> getByResourceAndAction(
            @RequestParam String resource,
            @RequestParam String action) {
        log.info("REST request to get Permissions by resource: {} and action: {}", resource, action);
        return ResponseEntity.ok(permissionService.findByResourceAndAction(resource, action));
    }

    /**
     * Check if permission exists by name
     * GET /system/security/permission/exists/{name}
     */
    @GetMapping("/exists/{name}")
    @PreAuthorize("hasAuthority('PERMISSION:READ')")
    public ResponseEntity<Map<String, Boolean>> checkExists(@PathVariable String name) {
        log.info("REST request to check if Permission exists: {}", name);
        boolean exists = permissionService.existsByName(name);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
}
