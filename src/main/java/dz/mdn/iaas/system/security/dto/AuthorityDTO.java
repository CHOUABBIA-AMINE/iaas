/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AuthorityDTO
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
import dz.mdn.iaas.system.security.model.Authority;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorityDTO extends GenericDTO<Authority> {
    
    private String name;
    private String description;
    private String type;

    @Builder
    public AuthorityDTO(Long id, String name, String description, String type) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public Authority toEntity() {
        return Authority.builder()
                .name(this.name)
                .description(this.description)
                .type(this.type)
                .build();
    }

    @Override
    public void updateEntity(Authority entity) {
        if (this.name != null) entity.setName(this.name);
        if (this.description != null) entity.setDescription(this.description);
        if (this.type != null) entity.setType(this.type);
    }

    public static AuthorityDTO fromEntity(Authority entity) {
        if (entity == null) return null;
        
        return AuthorityDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .type(entity.getType())
                .build();
    }
}
