/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.amendment.model.AmendmentStep;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * AmendmentStep Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmendmentStepDTO extends GenericDTO<AmendmentStep> {

    private int internalId;
    
    @NotNull(message = "Amendment is required")
    private Long amendmentId;
    
    @NotNull(message = "Amendment phase is required")
    private Long amendmentPhaseId;
    
    private Date date;
    private String observation;

    @Override
    public AmendmentStep toEntity() {
        AmendmentStep entity = new AmendmentStep();
        entity.setId(getId());
        entity.setInternalId(this.internalId);
        entity.setDate(this.date);
        entity.setObservation(this.observation);
        return entity;
    }

    @Override
    public void updateEntity(AmendmentStep entity) {
        if (this.date != null) entity.setDate(this.date);
        if (this.observation != null) entity.setObservation(this.observation);
    }

    public static AmendmentStepDTO fromEntity(AmendmentStep entity) {
        if (entity == null) return null;
        
        return AmendmentStepDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .date(entity.getDate())
                .observation(entity.getObservation())
                .build();
    }

}
