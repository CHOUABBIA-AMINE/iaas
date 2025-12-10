/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementStatusDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Data Transfer Object
 *	@Layer		: Business / Core / DTO
 *	@Package	: Business / Core / DTO
 *
 **/

package dz.mdn.iaas.business.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.business.core.model.ProcurementStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProcurementStatus Data Transfer Object
 * 
 * Fields:
 * - id (F_00)
 * - code (F_01) - unique, required
 * - designationAr (F_02) - optional
 * - designationEn (F_03) - optional
 * - designationFr (F_04) - unique, required
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcurementStatusDTO {

    private Long id;

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code;

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr;

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr;

    // ========== ENTITY MAPPING ==========

    /**
     * Create DTO from entity
     */
    public static ProcurementStatusDTO fromEntity(ProcurementStatus procurementStatus) {
        if (procurementStatus == null) return null;
        
        return ProcurementStatusDTO.builder()
                .id(procurementStatus.getId())
                .code(procurementStatus.getCode())
                .designationAr(procurementStatus.getDesignationAr())
                .designationEn(procurementStatus.getDesignationEn())
                .designationFr(procurementStatus.getDesignationFr())
                .build();
    }

    /**
     * Convert DTO to entity
     */
    public ProcurementStatus toEntity() {
        ProcurementStatus procurementStatus = new ProcurementStatus();
        procurementStatus.setId(this.id);
        procurementStatus.setCode(this.code);
        procurementStatus.setDesignationAr(this.designationAr);
        procurementStatus.setDesignationEn(this.designationEn);
        procurementStatus.setDesignationFr(this.designationFr);
        return procurementStatus;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(ProcurementStatus procurementStatus) {
        if (this.code != null) {
            procurementStatus.setCode(this.code);
        }
        if (this.designationAr != null) {
            procurementStatus.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            procurementStatus.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            procurementStatus.setDesignationFr(this.designationFr);
        }
    }
}
