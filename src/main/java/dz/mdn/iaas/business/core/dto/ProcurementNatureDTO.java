/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementNatureDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Data Transfer Object
 *	@Layer		: Business / Core / DTO
 *	@Package	: Business / Core / DTO
 *
 **/

package dz.mdn.iaas.business.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.business.core.model.ProcurementNature;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProcurementNature Data Transfer Object
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
public class ProcurementNatureDTO {

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
    public static ProcurementNatureDTO fromEntity(ProcurementNature procurementNature) {
        if (procurementNature == null) return null;
        
        return ProcurementNatureDTO.builder()
                .id(procurementNature.getId())
                .code(procurementNature.getCode())
                .designationAr(procurementNature.getDesignationAr())
                .designationEn(procurementNature.getDesignationEn())
                .designationFr(procurementNature.getDesignationFr())
                .build();
    }

    /**
     * Convert DTO to entity
     */
    public ProcurementNature toEntity() {
        ProcurementNature procurementNature = new ProcurementNature();
        procurementNature.setId(this.id);
        procurementNature.setCode(this.code);
        procurementNature.setDesignationAr(this.designationAr);
        procurementNature.setDesignationEn(this.designationEn);
        procurementNature.setDesignationFr(this.designationFr);
        return procurementNature;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(ProcurementNature procurementNature) {
        if (this.code != null) {
            procurementNature.setCode(this.code);
        }
        if (this.designationAr != null) {
            procurementNature.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            procurementNature.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            procurementNature.setDesignationFr(this.designationFr);
        }
    }
}
