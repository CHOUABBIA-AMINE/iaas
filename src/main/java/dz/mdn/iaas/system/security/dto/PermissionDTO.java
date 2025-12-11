/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PermissionDTO
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
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDTO extends GenericDTO<Permission> {
    
    private String name;
    private String description;
    private String resource;
    private String action;

    @Builder
    public PermissionDTO(Long id, String name, String description, String resource, String action) {
        super(id);
        this.name = name;
        this.description = description;
        this.resource = resource;
        this.action = action;
    }

    @Override
    public Permission toEntity() {
        return Permission.builder()
                .name(this.name)
                .description(this.description)
                .resource(this.resource)
                .action(this.action)
                .build();
    }

    @Override
    public void updateEntity(Permission entity) {
        if (this.name != null) entity.setName(this.name);
        if (this.description != null) entity.setDescription(this.description);
        if (this.resource != null) entity.setResource(this.resource);
        if (this.action != null) entity.setAction(this.action);
    }

    public static PermissionDTO fromEntity(Permission entity) {
        if (entity == null) return null;
        
        return PermissionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .resource(entity.getResource())
                .action(entity.getAction())
                .build();
    }
}
