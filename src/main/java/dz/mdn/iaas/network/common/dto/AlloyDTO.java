/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AlloyDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.model.Alloy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Alloy Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 * - code (F_02) - unique, required
 * - density (F_03) - required
 * - viscosity (F_04) - required
 * - flashPoint (F_05) - required
 * - sulfurContent (F_06) - required
 * - isHazardous (F_07) - required
 * - description (F_08) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlloyDTO extends GenericDTO<Alloy> {

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Override
    public Alloy toEntity() {
        Alloy alloy = new Alloy();
        alloy.setId(getId());
        alloy.setCode(this.code);
        alloy.setDescription(this.description);
        return alloy;
    }

    @Override
    public void updateEntity(Alloy alloy) {
        if (this.code != null) alloy.setCode(this.code);
        if (this.description != null) alloy.setDescription(this.description);
    }

    public static AlloyDTO fromEntity(Alloy alloy) {
        if (alloy == null) return null;
        
        return AlloyDTO.builder()
                .id(alloy.getId())
                .code(alloy.getCode())
                .description(alloy.getDescription())
                .build();
    }
}
