/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: TerminalTypeDTO
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.type.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.type.model.TerminalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * TerminalType Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - designation (F_01) - type designation (unique, required)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TerminalTypeDTO extends GenericDTO<TerminalType> {

    @NotBlank(message = "Designation is required")
    @Size(min = 2, max = 100, message = "Designation must be between 2 and 100 characters")
    private String designation;

    @Override
    public TerminalType toEntity() {
        TerminalType type = new TerminalType();
        type.setId(getId());
        type.setDesignation(this.designation);
        return type;
    }

    @Override
    public void updateEntity(TerminalType type) {
        if (this.designation != null) type.setDesignation(this.designation);
    }

    public static TerminalTypeDTO fromEntity(TerminalType type) {
        if (type == null) return null;
        
        return TerminalTypeDTO.builder()
                .id(type.getId())
                .designation(type.getDesignation())
                .build();
    }
}
