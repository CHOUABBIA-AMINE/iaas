/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStepDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import jakarta.validation.constraints.NotNull;
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
public class ConsultationStepDTO extends GenericDTO<ConsultationStep> {

    private int internalId;
    @NotNull
    private Long consultationId;
    @NotNull
    private Long consultationPhaseId;
    private Date date;
    private String observation;

    @Override
    public ConsultationStep toEntity() {
        ConsultationStep entity = new ConsultationStep();
        entity.setId(getId());
        entity.setInternalId(this.internalId);
        entity.setDate(this.date);
        entity.setObservation(this.observation);
        return entity;
    }

    @Override
    public void updateEntity(ConsultationStep entity) {
        if (this.date != null) entity.setDate(this.date);
        if (this.observation != null) entity.setObservation(this.observation);
    }

    public static ConsultationStepDTO fromEntity(ConsultationStep entity) {
        if (entity == null) return null;
        return ConsultationStepDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .date(entity.getDate())
                .observation(entity.getObservation())
                .build();
    }
}
