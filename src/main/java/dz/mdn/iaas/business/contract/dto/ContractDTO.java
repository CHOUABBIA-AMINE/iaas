/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractDTO
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.consultation.dto.ConsultationDTO;
import dz.mdn.iaas.business.contract.model.Contract;
import dz.mdn.iaas.business.core.dto.ApprovalStatusDTO;
import dz.mdn.iaas.business.core.dto.CurrencyDTO;
import dz.mdn.iaas.business.core.dto.RealizationStatusDTO;
import dz.mdn.iaas.business.plan.dto.PlannedItemDTO;
import dz.mdn.iaas.business.provider.dto.ProviderDTO;
import dz.mdn.iaas.common.communication.dto.MailDTO;
import dz.mdn.iaas.common.document.dto.DocumentDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contract Data Transfer Object
 * Maps directly to Contract entity fields (F_00 → id, etc.)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDTO {

    private Long id; // F_00

    @NotBlank(message = "Internal ID is required")
    private String internalId; // F_01

    private String contractYear; // F_02

    @Size(max = 100, message = "Reference must not exceed 100 characters")
    private String reference; // F_03

    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    private String designationAr; // F_04

    @Size(max = 300, message = "English designation must not exceed 300 characters")
    private String designationEn; // F_05

    @NotBlank(message = "French designation is required")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    private String designationFr; // F_06

    @DecimalMin(value = "0.0", message = "Amount must be non-negative")
    private Double amount; // F_07

    @DecimalMin(value = "0.0", message = "Transferable amount must be non-negative")
    private Double transferableAmount; // F_08

    private Date startDate; // F_09
    private String approvalReference; // F_10
    private Date approvalDate; // F_11
    private Date contractDate; // F_12
    private Date notifyDate; // F_13

    @PositiveOrZero(message = "Contract duration must be non-negative")
    private Integer contractDuration; // F_14

    @Size(max = 500, message = "Observation must not exceed 500 characters")
    private String observation; // F_15

    // Foreign key relationships (IDs only)
    @NotNull(message = "Contract type is required")
    private Long contractTypeId; // F_16

    @NotNull(message = "Provider is required")
    private Long providerId; // F_17

    @NotNull(message = "Realization status is required")
    private Long realizationStatusId; // F_18

    @NotNull(message = "Contract step is required")
    private Long contractStepId; // F_19

    private Long approvalStatusId; // F_20 (optional)
    @NotNull(message = "Currency is required")
    private Long currencyId; // F_21
    private Long consultationId; // F_22 (optional)
    private Long contractUpId; // F_23 (optional)

    // Collections
    private List<Long> contractItemIds; // one-to-many
    private List<Long> documentIds; // many-to-many
    private List<Long> referencedMailIds; // many-to-many
    private List<Long> plannedItemIds; // many-to-many

    // Related DTOs for display when loaded with relations
    private ContractTypeDTO contractType;
    private ProviderDTO provider;
    private RealizationStatusDTO realizationStatus;
    private ContractStepDTO contractStep;
    private ApprovalStatusDTO approvalStatus;
    private CurrencyDTO currency;
    private ConsultationDTO consultation;
    private ContractDTO contractUp;
    private List<ContractItemDTO> contractItems;
    private List<DocumentDTO> documents;
    private List<MailDTO> referencedMails;
    private List<PlannedItemDTO> plannedItems;

    /* -------------------------------------------------------------------------
       Static conversion methods
       ------------------------------------------------------------------------- */

    public static ContractDTO fromEntity(dz.mdn.iaas.business.contract.model.Contract contract) {
        if (contract == null) return null;

        ContractDTO.ContractDTOBuilder builder = ContractDTO.builder()
                .id(contract.getId())
                .internalId(contract.getInternalId())
                .contractYear(contract.getContractYear())
                .reference(contract.getReference())
                .designationAr(contract.getDesignationAr())
                .designationEn(contract.getDesignationEn())
                .designationFr(contract.getDesignationFr())
                .amount(contract.getAmount())
                .transferableAmount(contract.getTransferableAmount())
                .startDate(contract.getStartDate())
                .approvalReference(contract.getApprovalReference())
                .approvalDate(contract.getApprovalDate())
                .contractDate(contract.getContractDate())
                .notifyDate(contract.getNotifyDate())
                .contractDuration(contract.getContractDuration())
                .observation(contract.getObservation());

        // Foreign keys
        if (contract.getContractType() != null) builder.contractTypeId(contract.getContractType().getId());
        if (contract.getProvider() != null) builder.providerId(contract.getProvider().getId());
        if (contract.getRealizationStatus() != null) builder.realizationStatusId(contract.getRealizationStatus().getId());
        if (contract.getContractStep() != null) builder.contractStepId(contract.getContractStep().getId());
        if (contract.getApprovalStatus() != null) builder.approvalStatusId(contract.getApprovalStatus().getId());
        if (contract.getCurrency() != null) builder.currencyId(contract.getCurrency().getId());
        if (contract.getConsultation() != null) builder.consultationId(contract.getConsultation().getId());
        if (contract.getContractUp() != null) builder.contractUpId(contract.getContractUp().getId());

        // Collections (IDs only)
        if (contract.getContractItems() != null) {
            builder.contractItemIds(contract.getContractItems().stream().map(c -> c.getId()).toList());
        }
        if (contract.getDocuments() != null) {
            builder.documentIds(contract.getDocuments().stream().map(d -> d.getId()).toList());
        }
        if (contract.getReferencedMails() != null) {
            builder.referencedMailIds(contract.getReferencedMails().stream().map(m -> m.getId()).toList());
        }
        if (contract.getPlannedItems() != null) {
            builder.plannedItemIds(contract.getPlannedItems().stream().map(p -> p.getId()).toList());
        }

        return builder.build();
    }

    public static ContractDTO fromEntityWithRelations(Contract contract) {
        ContractDTO dto = fromEntity(contract);
        if (dto == null) return null;

        // Populate related DTOs
        if (contract.getContractType() != null)
            dto.setContractType(ContractTypeDTO.fromEntity(contract.getContractType()));
        if (contract.getProvider() != null)
            dto.setProvider(ProviderDTO.fromEntity(contract.getProvider()));
        if (contract.getRealizationStatus() != null)
            dto.setRealizationStatus(RealizationStatusDTO.fromEntity(contract.getRealizationStatus()));
        if (contract.getContractStep() != null)
            dto.setContractStep(ContractStepDTO.fromEntity(contract.getContractStep()));
        if (contract.getApprovalStatus() != null)
            dto.setApprovalStatus(ApprovalStatusDTO.fromEntity(contract.getApprovalStatus()));
        if (contract.getCurrency() != null)
            dto.setCurrency(CurrencyDTO.fromEntity(contract.getCurrency()));
        if (contract.getConsultation() != null)
            dto.setConsultation(ConsultationDTO.fromEntity(contract.getConsultation()));
        if (contract.getContractUp() != null)
            dto.setContractUp(fromEntity(contract.getContractUp()));
        if (contract.getContractItems() != null)
            dto.setContractItems(contract.getContractItems().stream().map(ContractItemDTO::fromEntity).toList());
        if (contract.getDocuments() != null)
            dto.setDocuments(contract.getDocuments().stream().map( DocumentDTO::fromEntity).toList());
        if (contract.getReferencedMails() != null)
            dto.setReferencedMails(contract.getReferencedMails().stream().map(MailDTO::fromEntity).toList());
        if (contract.getPlannedItems() != null)
            dto.setPlannedItems(contract.getPlannedItems().stream().map(PlannedItemDTO::fromEntity).toList());

        return dto;
    }

    /* -------------------------------------------------------------------------
       Display & helper methods
       ------------------------------------------------------------------------- */

    public String getDisplayText() {
        if (designationFr != null && !designationFr.isBlank()) return designationFr;
        if (designationEn != null && !designationEn.isBlank()) return designationEn;
        if (designationAr != null && !designationAr.isBlank()) return designationAr;
        return internalId != null ? internalId : "N/A";
    }

    public String getShortDisplay() {
        return (reference != null ? reference + " - " : "") + getDisplayText();
    }

    public String getContractSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        if (amount != null) sb.append(" | Amount: ").append(amount);
        if (currency != null) sb.append(" ").append(currency.getCode());
        if (provider != null) sb.append(" | Provider: ").append(provider.getDisplayText());
        return sb.toString();
    }

    public boolean isApproved() {
        return approvalStatus != null && "APPROVED".equalsIgnoreCase(approvalStatus.getDesignationFr());
    }

    public boolean isExpired() {
        if (notifyDate == null || contractDuration == null) return false;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(notifyDate);
        cal.add(java.util.Calendar.DAY_OF_YEAR, contractDuration);
        return new Date().after(cal.getTime());
    }

    public String getStatusSummary() {
        if (isExpired()) return "EXPIRED";
        if (isApproved()) return "APPROVED";
        return realizationStatus != null ? realizationStatus.getDesignationFr() : "PENDING";
    }
}
