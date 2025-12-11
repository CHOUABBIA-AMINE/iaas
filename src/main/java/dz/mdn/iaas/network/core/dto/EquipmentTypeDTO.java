/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentTypeDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.model.EquipmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * EquipmentType Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - code (F_01) - required
 * - designation (F_02) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentTypeDTO extends GenericDTO<EquipmentType> {

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "Designation is required")
    @Size(max = 100, message = "Designation must not exceed 100 characters")
    private String designation;

    @Override
    public EquipmentType toEntity() {
        EquipmentType type = new EquipmentType();
        type.setId(getId());
        type.setCode(this.code);
        type.setDesignation(this.designation);
        return type;
    }

    @Override
    public void updateEntity(EquipmentType type) {
        if (this.code != null) type.setCode(this.code);
        if (this.designation != null) type.setDesignation(this.designation);
    }

    public static EquipmentTypeDTO fromEntity(EquipmentType type) {
        if (type == null) return null;
        
        return EquipmentTypeDTO.builder()
                .id(type.getId())
                .code(type.getCode())
                .designation(type.getDesignation())
                .build();
    }
}
