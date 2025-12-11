/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: GroupController
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
import dz.mdn.iaas.system.security.dto.GroupDTO;
import dz.mdn.iaas.system.security.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/security/group")
@Slf4j
public class GroupController extends GenericController<GroupDTO, Long> {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        super(groupService, "Group");
        this.groupService = groupService;
    }

    // ========== STANDARD CRUD OPERATIONS (From GenericController) ==========
    // Inherited:
    // - GET    /system/security/group           -> getAll(Pageable)
    // - GET    /system/security/group/{id}      -> getById(Long)
    // - POST   /system/security/group           -> create(GroupDTO)
    // - PUT    /system/security/group/{id}      -> update(Long, GroupDTO)
    // - DELETE /system/security/group/{id}      -> delete(Long)

    // ========== ROLE MANAGEMENT ==========

    /**
     * Assign role to group
     * POST /system/security/group/{groupId}/roles/{roleId}
     */
    @PostMapping("/{groupId}/roles/{roleId}")
    @PreAuthorize("hasAuthority('GROUP:ADMIN')")
    public ResponseEntity<GroupDTO> assignRole(
            @PathVariable Long groupId,
            @PathVariable Long roleId) {
        log.info("REST request to assign role {} to group {}", roleId, groupId);
        return ResponseEntity.ok(groupService.assignRole(groupId, roleId));
    }

    /**
     * Remove role from group
     * DELETE /system/security/group/{groupId}/roles/{roleId}
     */
    @DeleteMapping("/{groupId}/roles/{roleId}")
    @PreAuthorize("hasAuthority('GROUP:ADMIN')")
    public ResponseEntity<GroupDTO> removeRole(
            @PathVariable Long groupId,
            @PathVariable Long roleId) {
        log.info("REST request to remove role {} from group {}", roleId, groupId);
        return ResponseEntity.ok(groupService.removeRole(groupId, roleId));
    }

    // ========== CUSTOM QUERY OPERATIONS ==========

    /**
     * Find group by name
     * GET /system/security/group/by-name/{name}
     */
    @GetMapping("/by-name/{name}")
    @PreAuthorize("hasAuthority('GROUP:READ')")
    public ResponseEntity<GroupDTO> getByName(@PathVariable String name) {
        log.info("REST request to get Group by name: {}", name);
        return ResponseEntity.ok(groupService.findByName(name));
    }

    /**
     * Find groups by role
     * GET /system/security/group/by-role/{roleId}
     */
    @GetMapping("/by-role/{roleId}")
    @PreAuthorize("hasAuthority('GROUP:READ')")
    public ResponseEntity<List<GroupDTO>> getByRole(@PathVariable Long roleId) {
        log.info("REST request to get Groups by role: {}", roleId);
        return ResponseEntity.ok(groupService.findByRole(roleId));
    }

    /**
     * Check if group exists by name
     * GET /system/security/group/exists/{name}
     */
    @GetMapping("/exists/{name}")
    @PreAuthorize("hasAuthority('GROUP:READ')")
    public ResponseEntity<Map<String, Boolean>> checkExists(@PathVariable String name) {
        log.info("REST request to check if Group exists: {}", name);
        boolean exists = groupService.existsByName(name);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
}
