/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PartnerTypeDTO
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
import dz.mdn.iaas.network.type.model.PartnerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * PartnerType Data Transfer Object - Extends GenericDTO
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
public class PartnerTypeDTO extends GenericDTO<PartnerType> {

    @NotBlank(message = "Designation is required")
    @Size(min = 2, max = 100, message = "Designation must be between 2 and 100 characters")
    private String designation;

    @Override
    public PartnerType toEntity() {
        PartnerType type = new PartnerType();
        type.setId(getId());
        type.setDesignation(this.designation);
        return type;
    }

    @Override
    public void updateEntity(PartnerType type) {
        if (this.designation != null) type.setDesignation(this.designation);
    }

    public static PartnerTypeDTO fromEntity(PartnerType type) {
        if (type == null) return null;
        
        return PartnerTypeDTO.builder()
                .id(type.getId())
                .designation(type.getDesignation())
                .build();
    }
}
