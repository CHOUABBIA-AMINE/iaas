/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.amendment.model.AmendmentPhase;
import dz.mdn.iaas.business.amendment.model.AmendmentStep;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmendmentStepDTO extends GenericDTO<AmendmentStep> {

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr;

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr;
    
    @NotNull(message = "Amendment phase is required")
    private Long amendmentPhaseId;
    
    private AmendmentPhaseDTO amendmentPhase;

    @Override
    public AmendmentStep toEntity() {
        AmendmentStep entity = new AmendmentStep();
        entity.setId(getId());
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        
		if (this.amendmentPhaseId != null) {
			AmendmentPhase amendmentPhase = new AmendmentPhase();
			amendmentPhase.setId(this.amendmentPhaseId);
		    entity.setAmendmentPhase(amendmentPhase);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(AmendmentStep entity) {
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
        
		if (this.amendmentPhaseId != null) {
			AmendmentPhase amendmentPhase = new AmendmentPhase();
			amendmentPhase.setId(this.amendmentPhaseId);
		    entity.setAmendmentPhase(amendmentPhase);
		}
    }

    public static AmendmentStepDTO fromEntity(AmendmentStep entity) {
        if (entity == null) return null;
        return AmendmentStepDTO.builder()
                .id(entity.getId())
                .designationAr(entity.getDesignationAr())
                .designationEn(entity.getDesignationEn())
                .designationFr(entity.getDesignationFr())
                .amendmentPhaseId(entity.getAmendmentPhase() != null ? entity.getAmendmentPhase().getId() : null)
                
                .amendmentPhase(entity.getAmendmentPhase() != null ? AmendmentPhaseDTO.fromEntity(entity.getAmendmentPhase()) : null)
                .build();
    }
}
