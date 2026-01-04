/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationDTO
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

import dz.mdn.iaas.business.consultation.model.AwardMethod;
import dz.mdn.iaas.business.consultation.model.Consultation;
import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import dz.mdn.iaas.business.core.dto.ApprovalStatusDTO;
import dz.mdn.iaas.business.core.dto.ProcurementDirectorDTO;
import dz.mdn.iaas.business.core.dto.ProcurementNatureDTO;
import dz.mdn.iaas.business.core.dto.ProcurementStatusDTO;
import dz.mdn.iaas.business.core.model.ApprovalStatus;
import dz.mdn.iaas.business.core.model.ProcurementDirector;
import dz.mdn.iaas.business.core.model.ProcurementNature;
import dz.mdn.iaas.business.core.model.ProcurementStatus;
import dz.mdn.iaas.business.plan.dto.BudgetTypeDTO;
import dz.mdn.iaas.business.plan.model.BudgetType;
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
public class ConsultationDTO extends GenericDTO<Consultation> {

    @NotBlank(message = "Internal ID is required")
    @Size(max = 3, message = "Internal ID must not exceed 3 characters")
    private String internalId;
    
    @NotBlank(message = "Consultation year is required")
    @Size(max = 4, message = "Consultation year must not exceed 4 characters")
    private String consultationYear;
    
    @Size(max = 20, message = "Reference must not exceed 20 characters")
    private String reference;
    
    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    private String designationAr;

    @Size(max = 300, message = "English designation must not exceed 300 characters")
    private String designationEn;
    
    @NotBlank(message = "French designation is required")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    private String designationFr;
    
    private double allocatedAmount;
    
    private double financialEstimation;
    
    private Date startDate;
    
    private String approvalReference;
    
    private Date approvalDate;
    
    private Date publishDate;
    
    private Date deadline;

    @Size(max = 500, message = "Observation must not exceed 500 characters")
    private String observation;
    
    @NotNull(message = "Award method is required")
    private Long awardMethodId;
    
    @NotNull(message = "Procurement nature is required")
    private Long procurementNatureId;
    
    @NotNull(message = "Budget type is required")
    private Long budgetTypeId;
    
    @NotNull(message = "Procurement status is required")
    private Long procurementStatusId;
    
    @NotNull(message = "Approval status is required")
    private Long approvalStatusId;
    
    @NotNull(message = "Procurement director is required")
    private Long procurementDirectorId;
    
    @NotNull(message = "Consultation step is required")
    private Long consultationStepId;
    
    private AwardMethodDTO awardMethod;
    
    private ProcurementNatureDTO procurementNature;
    
    private BudgetTypeDTO budgetType;
    
    private ProcurementStatusDTO procurementStatus;
    
    private ApprovalStatusDTO approvalStatus;
    
    private ProcurementDirectorDTO procurementDirector;
    
    private ConsultationStepDTO consultationStep;

    @Override
    public Consultation toEntity() {
        Consultation entity = new Consultation();
        entity.setId(getId());
        entity.setInternalId(this.internalId);
        entity.setConsultationYear(this.consultationYear);
        entity.setReference(this.reference);
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        entity.setAllocatedAmount(this.allocatedAmount);
        entity.setFinancialEstimation(this.financialEstimation);
        entity.setStartDate(this.startDate);
        entity.setApprovalReference(this.approvalReference);
        entity.setApprovalDate(this.approvalDate);
        entity.setPublishDate(this.publishDate);
        entity.setDeadline(this.deadline);
        entity.setObservation(this.observation);
        
		if (this.awardMethodId != null) {
			AwardMethod awardMethod = new AwardMethod();
			awardMethod.setId(this.awardMethodId);
		    entity.setAwardMethod(awardMethod);
		}
        
		if (this.procurementNatureId != null) {
			ProcurementNature procurementNature = new ProcurementNature();
			procurementNature.setId(this.procurementNatureId);
		    entity.setProcurementNature(procurementNature);
		}
        
		if (this.budgetTypeId != null) {
			BudgetType budgetType = new BudgetType();
			budgetType.setId(this.budgetTypeId);
		    entity.setBudgetType(budgetType);
		}
        
		if (this.procurementStatusId != null) {
			ProcurementStatus procurementStatus = new ProcurementStatus();
			procurementStatus.setId(this.procurementStatusId);
		    entity.setProcurementStatus(procurementStatus);
		}
        
		if (this.approvalStatusId != null) {
			ApprovalStatus approvalStatus = new ApprovalStatus();
			approvalStatus.setId(this.approvalStatusId);
		    entity.setApprovalStatus(approvalStatus);
		}
        
		if (this.procurementDirectorId != null) {
			ProcurementDirector procurementDirector = new ProcurementDirector();
			procurementDirector.setId(this.procurementDirectorId);
		    entity.setProcurementDirector(procurementDirector);
		}
        
		if (this.consultationStepId != null) {
			ConsultationStep consultationStep = new ConsultationStep();
			consultationStep.setId(this.consultationStepId);
		    entity.setConsultationStep(consultationStep);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(Consultation entity) {
        if (this.internalId != null) entity.setInternalId(this.internalId);
        if (this.consultationYear != null) entity.setConsultationYear(this.consultationYear);
        if (this.reference != null) entity.setReference(this.reference);
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
        entity.setAllocatedAmount(this.allocatedAmount);
        entity.setFinancialEstimation(this.financialEstimation);
        if (this.startDate != null) entity.setStartDate(this.startDate);
        if (this.approvalReference != null) entity.setApprovalReference(this.approvalReference);
        if (this.approvalDate != null) entity.setApprovalDate(this.approvalDate);
        if (this.publishDate != null) entity.setPublishDate(this.publishDate);
        if (this.deadline != null) entity.setDeadline(this.deadline);
        if (this.observation != null) entity.setObservation(this.observation);
        
		if (this.awardMethodId != null) {
			AwardMethod awardMethod = new AwardMethod();
			awardMethod.setId(this.awardMethodId);
		    entity.setAwardMethod(awardMethod);
		}
        
		if (this.procurementNatureId != null) {
			ProcurementNature procurementNature = new ProcurementNature();
			procurementNature.setId(this.procurementNatureId);
		    entity.setProcurementNature(procurementNature);
		}
        
		if (this.budgetTypeId != null) {
			BudgetType budgetType = new BudgetType();
			budgetType.setId(this.budgetTypeId);
		    entity.setBudgetType(budgetType);
		}
        
		if (this.procurementStatusId != null) {
			ProcurementStatus procurementStatus = new ProcurementStatus();
			procurementStatus.setId(this.procurementStatusId);
		    entity.setProcurementStatus(procurementStatus);
		}
        
		if (this.approvalStatusId != null) {
			ApprovalStatus approvalStatus = new ApprovalStatus();
			approvalStatus.setId(this.approvalStatusId);
		    entity.setApprovalStatus(approvalStatus);
		}
        
		if (this.procurementDirectorId != null) {
			ProcurementDirector procurementDirector = new ProcurementDirector();
			procurementDirector.setId(this.procurementDirectorId);
		    entity.setProcurementDirector(procurementDirector);
		}
        
		if (this.consultationStepId != null) {
			ConsultationStep consultationStep = new ConsultationStep();
			consultationStep.setId(this.consultationStepId);
		    entity.setConsultationStep(consultationStep);
		}
    }

    public static ConsultationDTO fromEntity(Consultation entity) {
        if (entity == null) return null;
        return ConsultationDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .consultationYear(entity.getConsultationYear())
                .reference(entity.getReference())
                .designationAr(entity.getDesignationAr())
                .designationEn(entity.getDesignationEn())
                .designationFr(entity.getDesignationFr())
                .allocatedAmount(entity.getAllocatedAmount())
                .financialEstimation(entity.getFinancialEstimation())
                .startDate(entity.getStartDate())
                .approvalReference(entity.getApprovalReference())
                .approvalDate(entity.getApprovalDate())
                .publishDate(entity.getPublishDate())
                .deadline(entity.getDeadline())
                .observation(entity.getObservation())
                .awardMethodId(entity.getAwardMethod() != null ? entity.getAwardMethod().getId() : null)
                .procurementNatureId(entity.getProcurementNature() != null ? entity.getProcurementNature().getId() : null)
                .budgetTypeId(entity.getBudgetType() != null ? entity.getBudgetType().getId() : null)
                .procurementStatusId(entity.getProcurementStatus() != null ? entity.getProcurementStatus().getId() : null)
                .approvalStatusId(entity.getApprovalStatus() != null ? entity.getApprovalStatus().getId() : null)
                .procurementDirectorId(entity.getProcurementDirector() != null ? entity.getProcurementDirector().getId() : null)
                .consultationStepId(entity.getConsultationStep() != null ? entity.getConsultationStep().getId() : null)
                
                .awardMethod(entity.getAwardMethod() != null ? AwardMethodDTO.fromEntity(entity.getAwardMethod()) : null)
                .procurementNature(entity.getProcurementNature() != null ? ProcurementNatureDTO.fromEntity(entity.getProcurementNature()) : null)
                .budgetType(entity.getBudgetType() != null ? BudgetTypeDTO.fromEntity(entity.getBudgetType()) : null)
                .procurementStatus(entity.getProcurementStatus() != null ? ProcurementStatusDTO.fromEntity(entity.getProcurementStatus()) : null)
                .approvalStatus(entity.getApprovalStatus() != null ? ApprovalStatusDTO.fromEntity(entity.getApprovalStatus()) : null)
                .procurementDirector(entity.getProcurementDirector() != null ? ProcurementDirectorDTO.fromEntity(entity.getProcurementDirector()) : null)
                .consultationStep(entity.getConsultationStep() != null ? ConsultationStepDTO.fromEntity(entity.getConsultationStep()) : null)
                .build();
    }
}
