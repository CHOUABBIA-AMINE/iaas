/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StationTypeDTO
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
import dz.mdn.iaas.network.type.model.StationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * StationType Data Transfer Object - Extends GenericDTO
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
public class StationTypeDTO extends GenericDTO<StationType> {

    @NotBlank(message = "Designation is required")
    @Size(min = 2, max = 100, message = "Designation must be between 2 and 100 characters")
    private String designation;

    @Override
    public StationType toEntity() {
        StationType type = new StationType();
        type.setId(getId());
        type.setDesignation(this.designation);
        return type;
    }

    @Override
    public void updateEntity(StationType type) {
        if (this.designation != null) type.setDesignation(this.designation);
    }

    public static StationTypeDTO fromEntity(StationType type) {
        if (type == null) return null;
        
        return StationTypeDTO.builder()
                .id(type.getId())
                .designation(type.getDesignation())
                .build();
    }
}
