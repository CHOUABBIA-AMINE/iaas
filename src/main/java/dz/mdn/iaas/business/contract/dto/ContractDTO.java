/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.consultation.dto.ConsultationDTO;
import dz.mdn.iaas.business.consultation.model.Consultation;
import dz.mdn.iaas.business.contract.model.Contract;
import dz.mdn.iaas.business.contract.model.ContractStep;
import dz.mdn.iaas.business.contract.model.ContractType;
import dz.mdn.iaas.business.core.dto.ApprovalStatusDTO;
import dz.mdn.iaas.business.core.dto.CurrencyDTO;
import dz.mdn.iaas.business.core.dto.ProcurementStatusDTO;
import dz.mdn.iaas.business.core.model.ApprovalStatus;
import dz.mdn.iaas.business.core.model.Currency;
import dz.mdn.iaas.business.core.model.ProcurementStatus;
import dz.mdn.iaas.business.provider.dto.ProviderDTO;
import dz.mdn.iaas.business.provider.model.Provider;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Contract Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDTO extends GenericDTO<Contract> {

    @NotBlank(message = "Internal ID is required")
    private String internalId;
    
    private String contractYear;
    private String reference;
    private String designationAr;
    private String designationEn;
    
    @NotBlank(message = "French designation is required")
    private String designationFr;
    
    private double amount;
    private double transferableAmount;
    private Date startDate;
    private String approvalReference;
    private Date approvalDate;
    private Date contractDate;
    private Date notifyDate;
    private int contractDuration;
    private String observation;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    @NotNull(message = "Contract type is required")
    private Long contractTypeId;
    
    @NotNull(message = "Contract step is required")
    private Long contractStepId;
    
    @NotNull(message = "Procurement status is required")
    private Long procurementStatusId;
    
    private Long approvalStatusId;
    
    @NotNull(message = "Currency is required")
    private Long currencyId;
    
    private Long consultationId;
    
    private Long contractUpId;
    
    private ProviderDTO provider;
    
    private ContractTypeDTO contractType;
    
    private ContractStepDTO contractStep;
    
    private ProcurementStatusDTO procurementStatus;
    
    private ApprovalStatusDTO approvalStatus;
    
    private CurrencyDTO currency;
    
    private ConsultationDTO consultation;
    
    private ContractDTO contractUp;

    @Override
    public Contract toEntity() {
        Contract entity = new Contract();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        entity.setContractYear(this.contractYear);
        entity.setReference(this.reference);
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        entity.setAmount(this.amount);
        entity.setTransferableAmount(this.transferableAmount);
        entity.setStartDate(this.startDate);
        entity.setApprovalReference(this.approvalReference);
        entity.setApprovalDate(this.approvalDate);
        entity.setContractDate(this.contractDate);
        entity.setNotifyDate(this.notifyDate);
        entity.setContractDuration(this.contractDuration);
        entity.setObservation(this.observation);
        
		if (this.providerId != null) {
			Provider provider = new Provider();
			provider.setId(this.providerId);
		    entity.setProvider(provider);
		}
        
		if (this.contractTypeId != null) {
			ContractType contractType = new ContractType();
			contractType.setId(this.contractTypeId);
		    entity.setContractType(contractType);
		}
        
		if (this.contractStepId != null) {
			ContractStep contractStep = new ContractStep();
			contractStep.setId(this.contractStepId);
		    entity.setContractStep(contractStep);
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
        
		if (this.currencyId != null) {
			Currency currency = new Currency();
			currency.setId(this.currencyId);
		    entity.setCurrency(currency);
		}
        
		if (this.consultationId != null) {
			Consultation consultation = new Consultation();
			consultation.setId(this.consultationId);
		    entity.setConsultation(consultation);
		}
        
		if (this.contractUpId != null) {
			Contract contractUp = new Contract();
			contractUp.setId(this.contractUpId);
		    entity.setContractUp(contractUp);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(Contract entity) {
        if (this.internalId != null) {entity.setInternalId(this.internalId);}
        if (this.contractYear != null) {entity.setContractYear(this.contractYear);}
        if (this.reference != null) {entity.setReference(this.reference);}
        if (this.designationAr != null) {entity.setDesignationAr(this.designationAr);}
        if (this.designationEn != null) {entity.setDesignationEn(this.designationEn);}
        if (this.designationFr != null) {entity.setDesignationFr(this.designationFr);}
        if (this.amount > 0) {entity.setAmount(this.amount);}
        if (this.transferableAmount >= 0) {entity.setTransferableAmount(this.transferableAmount);}
        if (this.startDate != null) {entity.setStartDate(this.startDate);}
        if (this.approvalReference != null) {entity.setApprovalReference(this.approvalReference);}
        if (this.approvalDate != null) {entity.setApprovalDate(this.approvalDate);}
        if (this.contractDate != null) {entity.setContractDate(this.contractDate);}
        if (this.notifyDate != null) {entity.setNotifyDate(this.notifyDate);}
        if (this.contractDuration > 0) {entity.setContractDuration(this.contractDuration);}
        if (this.observation != null) {entity.setObservation(this.observation);}
        
		if (this.providerId != null) {
			Provider provider = new Provider();
			provider.setId(this.providerId);
		    entity.setProvider(provider);
		}
        
		if (this.contractTypeId != null) {
			ContractType contractType = new ContractType();
			contractType.setId(this.contractTypeId);
		    entity.setContractType(contractType);
		}
        
		if (this.contractStepId != null) {
			ContractStep contractStep = new ContractStep();
			contractStep.setId(this.contractStepId);
		    entity.setContractStep(contractStep);
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
        
		if (this.currencyId != null) {
			Currency currency = new Currency();
			currency.setId(this.currencyId);
		    entity.setCurrency(currency);
		}
        
		if (this.consultationId != null) {
			Consultation consultation = new Consultation();
			consultation.setId(this.consultationId);
		    entity.setConsultation(consultation);
		}
        
		if (this.contractUpId != null) {
			Contract contractUp = new Contract();
			contractUp.setId(this.contractUpId);
		    entity.setContractUp(contractUp);
		}
    }

    public static ContractDTO fromEntity(Contract entity) {
        if (entity == null) return null;
        return ContractDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .contractYear(entity.getContractYear())
                .reference(entity.getReference())
                .designationAr(entity.getDesignationAr())
                .designationEn(entity.getDesignationEn())
                .designationFr(entity.getDesignationFr())
                .amount(entity.getAmount())
                .transferableAmount(entity.getTransferableAmount())
                .startDate(entity.getStartDate())
                .approvalReference(entity.getApprovalReference())
                .approvalDate(entity.getApprovalDate())
                .contractDate(entity.getContractDate())
                .notifyDate(entity.getNotifyDate())
                .contractDuration(entity.getContractDuration())
                .observation(entity.getObservation())
                .providerId(entity.getProvider() != null ? entity.getProvider().getId() : null)
                .contractTypeId(entity.getContractType() != null ? entity.getContractType().getId() : null)
                .contractStepId(entity.getContractStep() != null ? entity.getContractStep().getId() : null)
                .procurementStatusId(entity.getProcurementStatus() != null ? entity.getProcurementStatus().getId() : null)
                .approvalStatusId(entity.getApprovalStatus() != null ? entity.getApprovalStatus().getId() : null)
                .currencyId(entity.getCurrency() != null ? entity.getCurrency().getId() : null)
                .consultationId(entity.getConsultation() != null ? entity.getConsultation().getId() : null)
                .contractUpId(entity.getContractUp() != null ? entity.getContractUp().getId() : null)
                
                .provider(entity.getProvider() != null ? ProviderDTO.fromEntity(entity.getProvider()) : null)
                .contractType(entity.getContractType() != null ? ContractTypeDTO.fromEntity(entity.getContractType()) : null)
                .contractStep(entity.getContractStep() != null ? ContractStepDTO.fromEntity(entity.getContractStep()) : null)
                .procurementStatus(entity.getProcurementStatus() != null ? ProcurementStatusDTO.fromEntity(entity.getProcurementStatus()) : null)
                .approvalStatus(entity.getApprovalStatus() != null ? ApprovalStatusDTO.fromEntity(entity.getApprovalStatus()) : null)
                .currency(entity.getCurrency() != null ? CurrencyDTO.fromEntity(entity.getCurrency()) : null)
                .consultation(entity.getConsultation() != null ? ConsultationDTO.fromEntity(entity.getConsultation()) : null)
                .contractUp(entity.getContractUp() != null ? ContractDTO.fromEntity(entity.getContractUp()) : null)
                .build();
    }
}
