/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.consultation.model.Consultation;
import dz.mdn.iaas.business.consultation.model.Submission;
import dz.mdn.iaas.business.provider.dto.ProviderDTO;
import dz.mdn.iaas.business.provider.model.Provider;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.system.utility.dto.FileDTO;
import dz.mdn.iaas.system.utility.model.File;
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

    private Date submissionDate;
    
    private double financialOffer;
    
    @NotNull(message = "Consultation is required")
    private Long consultationId;
    
    @NotNull(message = "Tender (Provider) is required")
    private Long tenderId;
    
    private Long administrativePartId;
    
    private Long technicalPartId;
    
    private Long financialPartId;
    
    private ConsultationDTO consultation;
    
    private ProviderDTO tender;
    
    private FileDTO administrativePart;
    
    private FileDTO technicalPart;
    
    private FileDTO financialPart;

    @Override
    public Submission toEntity() {
        Submission entity = new Submission();
        entity.setId(getId());
        entity.setSubmissionDate(this.submissionDate);
        entity.setFinancialOffer(this.financialOffer);
        
		if (this.consultationId != null) {
			Consultation consultation = new Consultation();
			consultation.setId(this.consultationId);
		    entity.setConsultation(consultation);
		}
        
		if (this.tenderId != null) {
			Provider tender = new Provider();
			tender.setId(this.tenderId);
		    entity.setTender(tender);
		}
        
		if (this.administrativePartId != null) {
			File administrativePart = new File();
			administrativePart.setId(this.administrativePartId);
		    entity.setAdministrativePart(administrativePart);
		}
        
		if (this.technicalPartId != null) {
			File technicalPart = new File();
			technicalPart.setId(this.technicalPartId);
		    entity.setTechnicalPart(technicalPart);
		}
        
		if (this.financialPartId != null) {
			File financialPart = new File();
			financialPart.setId(this.financialPartId);
		    entity.setFinancialPart(financialPart);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(Submission entity) {
        if (this.submissionDate != null) entity.setSubmissionDate(this.submissionDate);
        entity.setFinancialOffer(this.financialOffer);
        
		if (this.consultationId != null) {
			Consultation consultation = new Consultation();
			consultation.setId(this.consultationId);
		    entity.setConsultation(consultation);
		}
        
		if (this.tenderId != null) {
			Provider tender = new Provider();
			tender.setId(this.tenderId);
		    entity.setTender(tender);
		}
        
		if (this.administrativePartId != null) {
			File administrativePart = new File();
			administrativePart.setId(this.administrativePartId);
		    entity.setAdministrativePart(administrativePart);
		}
        
		if (this.technicalPartId != null) {
			File technicalPart = new File();
			technicalPart.setId(this.technicalPartId);
		    entity.setTechnicalPart(technicalPart);
		}
        
		if (this.financialPartId != null) {
			File financialPart = new File();
			financialPart.setId(this.financialPartId);
		    entity.setFinancialPart(financialPart);
		}
    }

    public static SubmissionDTO fromEntity(Submission entity) {
        if (entity == null) return null;
        return SubmissionDTO.builder()
                .id(entity.getId())
                .submissionDate(entity.getSubmissionDate())
                .financialOffer(entity.getFinancialOffer())
                .consultationId(entity.getConsultation() != null ? entity.getConsultation().getId() : null)
                .tenderId(entity.getTender() != null ? entity.getTender().getId() : null)
                .administrativePartId(entity.getAdministrativePart() != null ? entity.getAdministrativePart().getId() : null)
                .technicalPartId(entity.getTechnicalPart() != null ? entity.getTechnicalPart().getId() : null)
                .financialPartId(entity.getFinancialPart() != null ? entity.getFinancialPart().getId() : null)
                
                .consultation(entity.getConsultation() != null ? ConsultationDTO.fromEntity(entity.getConsultation()) : null)
                .tender(entity.getTender() != null ? ProviderDTO.fromEntity(entity.getTender()) : null)
                .administrativePart(entity.getAdministrativePart() != null ? FileDTO.fromEntity(entity.getAdministrativePart()) : null)
                .technicalPart(entity.getTechnicalPart() != null ? FileDTO.fromEntity(entity.getTechnicalPart()) : null)
                .financialPart(entity.getFinancialPart() != null ? FileDTO.fromEntity(entity.getFinancialPart()) : null)
                .build();
    }
}
