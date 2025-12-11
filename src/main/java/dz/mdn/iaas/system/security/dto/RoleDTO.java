/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoleDTO
 *	@CreatedOn	: 11-18-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: DTO
 *	@Layer		: DTO
 *	@Package	: System / Security
 *
 **/

package dz.mdn.iaas.system.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.system.security.model.Permission;
import dz.mdn.iaas.system.security.model.Role;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO extends GenericDTO<Role> {
    
    private String name;
    private String description;
    private Set<PermissionDTO> permissions;

    @Builder
    public RoleDTO(Long id, String name, String description, Set<PermissionDTO> permissions) {
        super(id);
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    @Override
    public Role toEntity() {
        Role role = Role.builder()
                .name(this.name)
                .description(this.description)
                .build();
        
        if (this.permissions != null && !this.permissions.isEmpty()) {
            Set<Permission> permissionEntities = this.permissions.stream()
                    .map(PermissionDTO::toEntity)
                    .collect(Collectors.toSet());
            role.setPermissions(permissionEntities);
        }
        
        return role;
    }

    @Override
    public void updateEntity(Role entity) {
        if (this.name != null) entity.setName(this.name);
        if (this.description != null) entity.setDescription(this.description);
        
        if (this.permissions != null) {
            Set<Permission> permissionEntities = this.permissions.stream()
                    .map(PermissionDTO::toEntity)
                    .collect(Collectors.toSet());
            entity.setPermissions(permissionEntities);
        }
    }

    public static RoleDTO fromEntity(Role entity) {
        return fromEntity(entity, true);
    }

    public static RoleDTO fromEntity(Role entity, boolean includePermissions) {
        if (entity == null) return null;
        
        Set<PermissionDTO> permissionDTOs = null;
        if (includePermissions && entity.getPermissions() != null) {
            permissionDTOs = entity.getPermissions().stream()
                    .map(PermissionDTO::fromEntity)
                    .collect(Collectors.toSet());
        }
        
        return RoleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .permissions(permissionDTOs)
                .build();
    }
}
