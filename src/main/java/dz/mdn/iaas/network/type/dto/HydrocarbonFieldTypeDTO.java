/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: HydrocarbonFieldTypeDTO
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
import dz.mdn.iaas.network.type.model.HydrocarbonFieldType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * HydrocarbonFieldType Data Transfer Object - Extends GenericDTO
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
public class HydrocarbonFieldTypeDTO extends GenericDTO<HydrocarbonFieldType> {

    @NotBlank(message = "Designation is required")
    @Size(min = 2, max = 100, message = "Designation must be between 2 and 100 characters")
    private String designation;

    @Override
    public HydrocarbonFieldType toEntity() {
        HydrocarbonFieldType type = new HydrocarbonFieldType();
        type.setId(getId());
        type.setDesignation(this.designation);
        return type;
    }

    @Override
    public void updateEntity(HydrocarbonFieldType type) {
        if (this.designation != null) type.setDesignation(this.designation);
    }

    public static HydrocarbonFieldTypeDTO fromEntity(HydrocarbonFieldType type) {
        if (type == null) return null;
        
        return HydrocarbonFieldTypeDTO.builder()
                .id(type.getId())
                .designation(type.getDesignation())
                .build();
    }
}
