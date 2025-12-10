/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionDTO
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
import dz.mdn.iaas.business.consultation.model.Submission;
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
public class SubmissionDTO extends GenericDTO<Submission> {

    private int internalId;
    @NotNull
    private Long providerId;
    @NotNull
    private Long consultationId;
    private Long submissionStatusId;
    private double financialAmount;
    private double technicalScore;
    private Date submissionDate;
    private Date evaluationDate;
    private boolean isWinner;
    private String observation;

    @Override
    public Submission toEntity() {
        Submission entity = new Submission();
        entity.setId(getId());
        entity.setInternalId(this.internalId);
        entity.setFinancialAmount(this.financialAmount);
        entity.setTechnicalScore(this.technicalScore);
        entity.setSubmissionDate(this.submissionDate);
        entity.setEvaluationDate(this.evaluationDate);
        entity.setWinner(this.isWinner);
        entity.setObservation(this.observation);
        return entity;
    }

    @Override
    public void updateEntity(Submission entity) {
        entity.setFinancialAmount(this.financialAmount);
        entity.setTechnicalScore(this.technicalScore);
        if (this.submissionDate != null) entity.setSubmissionDate(this.submissionDate);
        if (this.evaluationDate != null) entity.setEvaluationDate(this.evaluationDate);
        entity.setWinner(this.isWinner);
        if (this.observation != null) entity.setObservation(this.observation);
    }

    public static SubmissionDTO fromEntity(Submission entity) {
        if (entity == null) return null;
        return SubmissionDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .financialAmount(entity.getFinancialAmount())
                .technicalScore(entity.getTechnicalScore())
                .submissionDate(entity.getSubmissionDate())
                .evaluationDate(entity.getEvaluationDate())
                .isWinner(entity.isWinner())
                .observation(entity.getObservation())
                .build();
    }
}
