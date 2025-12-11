/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoleController
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
import dz.mdn.iaas.system.security.dto.RoleDTO;
import dz.mdn.iaas.system.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/security/role")
@Slf4j
public class RoleController extends GenericController<RoleDTO, Long> {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        super(roleService, "Role");
        this.roleService = roleService;
    }

    // ========== STANDARD CRUD OPERATIONS (From GenericController) ==========
    // Inherited:
    // - GET    /system/security/role           -> getAll(Pageable)
    // - GET    /system/security/role/{id}      -> getById(Long)
    // - POST   /system/security/role           -> create(RoleDTO)
    // - PUT    /system/security/role/{id}      -> update(Long, RoleDTO)
    // - DELETE /system/security/role/{id}      -> delete(Long)

    // ========== PERMISSION MANAGEMENT ==========

    /**
     * Assign permission to role
     * POST /system/security/role/{roleId}/permissions/{permissionId}
     */
    @PostMapping("/{roleId}/permissions/{permissionId}")
    @PreAuthorize("hasAuthority('ROLE:ADMIN')")
    public ResponseEntity<RoleDTO> assignPermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {
        log.info("REST request to assign permission {} to role {}", permissionId, roleId);
        return ResponseEntity.ok(roleService.assignPermission(roleId, permissionId));
    }

    /**
     * Remove permission from role
     * DELETE /system/security/role/{roleId}/permissions/{permissionId}
     */
    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    @PreAuthorize("hasAuthority('ROLE:ADMIN')")
    public ResponseEntity<RoleDTO> removePermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {
        log.info("REST request to remove permission {} from role {}", permissionId, roleId);
        return ResponseEntity.ok(roleService.removePermission(roleId, permissionId));
    }

    // ========== CUSTOM QUERY OPERATIONS ==========

    /**
     * Find role by name
     * GET /system/security/role/by-name/{name}
     */
    @GetMapping("/by-name/{name}")
    @PreAuthorize("hasAuthority('ROLE:READ')")
    public ResponseEntity<RoleDTO> getByName(@PathVariable String name) {
        log.info("REST request to get Role by name: {}", name);
        return ResponseEntity.ok(roleService.findByName(name));
    }

    /**
     * Find roles by permission
     * GET /system/security/role/by-permission/{permissionId}
     */
    @GetMapping("/by-permission/{permissionId}")
    @PreAuthorize("hasAuthority('ROLE:READ')")
    public ResponseEntity<List<RoleDTO>> getByPermission(@PathVariable Long permissionId) {
        log.info("REST request to get Roles by permission: {}", permissionId);
        return ResponseEntity.ok(roleService.findByPermission(permissionId));
    }

    /**
     * Check if role exists by name
     * GET /system/security/role/exists/{name}
     */
    @GetMapping("/exists/{name}")
    @PreAuthorize("hasAuthority('ROLE:READ')")
    public ResponseEntity<Map<String, Boolean>> checkExists(@PathVariable String name) {
        log.info("REST request to check if Role exists: {}", name);
        boolean exists = roleService.existsByName(name);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
}
