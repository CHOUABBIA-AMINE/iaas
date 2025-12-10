/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementDirectorDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Data Transfer Object
 *	@Layer		: Business / Core / DTO
 *	@Package	: Business / Core / DTO
 *
 **/

package dz.mdn.iaas.business.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.business.core.model.ProcurementDirector;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ProcurementDirector Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - designation (F_01) - unique, required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcurementDirectorDTO extends GenericDTO<ProcurementDirector> {

    @NotBlank(message = "Designation is required")
    @Size(max = 200, message = "Designation must not exceed 200 characters")
    private String designation;

    // ========== GENERIC DTO IMPLEMENTATION ==========

    @Override
    public ProcurementDirector toEntity() {
        ProcurementDirector procurementDirector = new ProcurementDirector();
        procurementDirector.setId(getId());
        procurementDirector.setDesignation(this.designation);
        return procurementDirector;
    }

    @Override
    public void updateEntity(ProcurementDirector procurementDirector) {
        if (this.designation != null) {
            procurementDirector.setDesignation(this.designation);
        }
    }

    // ========== FACTORY METHOD ==========

    /**
     * Create DTO from entity
     */
    public static ProcurementDirectorDTO fromEntity(ProcurementDirector procurementDirector) {
        if (procurementDirector == null) return null;
        
        return ProcurementDirectorDTO.builder()
                .id(procurementDirector.getId())
                .designation(procurementDirector.getDesignation())
                .build();
    }
}
