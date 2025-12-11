/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: OperationalStatusDTO
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
import dz.mdn.iaas.network.model.OperationalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * OperationalStatus Data Transfer Object - Extends GenericDTO
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
public class OperationalStatusDTO extends GenericDTO<OperationalStatus> {

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "Designation is required")
    @Size(max = 100, message = "Designation must not exceed 100 characters")
    private String designation;

    @Override
    public OperationalStatus toEntity() {
        OperationalStatus status = new OperationalStatus();
        status.setId(getId());
        status.setCode(this.code);
        status.setDesignation(this.designation);
        return status;
    }

    @Override
    public void updateEntity(OperationalStatus status) {
        if (this.code != null) status.setCode(this.code);
        if (this.designation != null) status.setDesignation(this.designation);
    }

    public static OperationalStatusDTO fromEntity(OperationalStatus status) {
        if (status == null) return null;
        
        return OperationalStatusDTO.builder()
                .id(status.getId())
                .code(status.getCode())
                .designation(status.getDesignation())
                .build();
    }
}
