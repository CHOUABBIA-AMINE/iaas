/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentDTO
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.amendment.model.Amendment;
import dz.mdn.iaas.business.contract.dto.ContractDTO;
import dz.mdn.iaas.business.core.dto.ApprovalStatusDTO;
import dz.mdn.iaas.business.core.dto.CurrencyDTO;
import dz.mdn.iaas.business.core.dto.ProcurementStatusDTO;
import dz.mdn.iaas.common.communication.dto.MailDTO;
import dz.mdn.iaas.common.document.dto.DocumentDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Amendment Data Transfer Object
 * Maps directly to Amendment entity fields (F_00 → id, etc.)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmendmentDTO {

    private Long id; // F_00

    private Integer internalId; // F_01

    @NotBlank(message = "Reference is required")
    @Size(max = 100, message = "Reference must not exceed 100 characters")
    private String reference; // F_02

    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    private String designationAr; // F_03

    @Size(max = 300, message = "English designation must not exceed 300 characters")
    private String designationEn; // F_04

    @NotBlank(message = "French designation is required")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    private String designationFr; // F_05

    @DecimalMin(value = "0.0", message = "Amount must be non-negative")
    private Double amount; // F_06

    @DecimalMin(value = "0.0", message = "Transferable amount must be non-negative")
    private Double transferableAmount; // F_07

    private Date startDate; // F_08
    private Date approvalDate; // F_09
    private Date notifyDate; // F_10

    @Size(max = 500, message = "Observation must not exceed 500 characters")
    private String observation; // F_11

    // Foreign key references (IDs only)
    @NotNull(message = "Contract is required")
    private Long contractId; // F_12

    @NotNull(message = "Amendment type is required")
    private Long amendmentTypeId; // F_13

    @NotNull(message = "Procurement status is required")
    private Long procurementStatusId; // F_14

    @NotNull(message = "Amendment phase is required")
    private Long amendmentStepId; // F_15

    private Long approvalStatusId; // F_16 (optional)
    @NotNull(message = "Currency is required")
    private Long currencyId; // F_17

    // Collections (IDs only)
    private List<Long> documentIds; // many-to-many
    private List<Long> referencedMailIds; // many-to-many

    // Related DTOs for extended mapping
    private ContractDTO contract;
    private AmendmentTypeDTO amendmentType;
    private ProcurementStatusDTO procurementStatus;
    private AmendmentPhaseDTO amendmentStep;
    private ApprovalStatusDTO approvalStatus;
    private CurrencyDTO currency;
    private List<DocumentDTO> documents;
    private List<MailDTO> referencedMails;

    /* -------------------------------------------------------------------------
       Static conversion methods
       ------------------------------------------------------------------------- */

    public static AmendmentDTO fromEntity(Amendment amendment) {
        if (amendment == null) return null;

        AmendmentDTO.AmendmentDTOBuilder builder = AmendmentDTO.builder()
                .id(amendment.getId())
                .internalId(amendment.getInternalId())
                .reference(amendment.getReference())
                .designationAr(amendment.getDesignationAr())
                .designationEn(amendment.getDesignationEn())
                .designationFr(amendment.getDesignationFr())
                .amount(amendment.getAmount())
                .transferableAmount(amendment.getTransferableAmount())
                .startDate(amendment.getStartDate())
                .approvalDate(amendment.getApprovalDate())
                .notifyDate(amendment.getNotifyDate())
                .observation(amendment.getObservation());

        // Foreign keys
        if (amendment.getContract() != null) builder.contractId(amendment.getContract().getId());
        if (amendment.getAmendmentType() != null) builder.amendmentTypeId(amendment.getAmendmentType().getId());
        if (amendment.getProcurementStatus() != null) builder.procurementStatusId(amendment.getProcurementStatus().getId());
        if (amendment.getAmendmentStep() != null) builder.amendmentStepId(amendment.getAmendmentStep().getId());
        if (amendment.getApprovalStatus() != null) builder.approvalStatusId(amendment.getApprovalStatus().getId());
        if (amendment.getCurrency() != null) builder.currencyId(amendment.getCurrency().getId());

        // Collections
        if (amendment.getDocuments() != null)
            builder.documentIds(amendment.getDocuments().stream().map(d -> d.getId()).toList());
        if (amendment.getReferencedMails() != null)
            builder.referencedMailIds(amendment.getReferencedMails().stream().map(m -> m.getId()).toList());

        return builder.build();
    }

    public static AmendmentDTO fromEntityWithRelations(Amendment amendment) {
        AmendmentDTO dto = fromEntity(amendment);
        if (dto == null) return null;

        // Populate related DTOs
        if (amendment.getContract() != null)
            dto.setContract(ContractDTO.fromEntity(amendment.getContract()));
        if (amendment.getAmendmentType() != null)
            dto.setAmendmentType(AmendmentTypeDTO.fromEntity(amendment.getAmendmentType()));
        if (amendment.getProcurementStatus() != null)
            dto.setProcurementStatus(ProcurementStatusDTO.fromEntity(amendment.getProcurementStatus()));
        if (amendment.getAmendmentStep() != null)
            dto.setAmendmentStep(AmendmentPhaseDTO.fromEntity(amendment.getAmendmentStep()));
        if (amendment.getApprovalStatus() != null)
            dto.setApprovalStatus(ApprovalStatusDTO.fromEntity(amendment.getApprovalStatus()));
        if (amendment.getCurrency() != null)
            dto.setCurrency(CurrencyDTO.fromEntity(amendment.getCurrency()));

        if (amendment.getDocuments() != null)
            dto.setDocuments(amendment.getDocuments().stream().map(DocumentDTO::fromEntity).toList());
        if (amendment.getReferencedMails() != null)
            dto.setReferencedMails(amendment.getReferencedMails().stream().map(MailDTO::fromEntity).toList());

        return dto;
    }

    /* -------------------------------------------------------------------------
       Helper & display methods
       ------------------------------------------------------------------------- */

    public String getDisplayText() {
        if (designationFr != null && !designationFr.isBlank()) return designationFr;
        if (designationEn != null && !designationEn.isBlank()) return designationEn;
        if (designationAr != null && !designationAr.isBlank()) return designationAr;
        return reference != null ? reference : "N/A";
    }

    public String getShortDisplay() {
        return (reference != null ? reference + " - " : "") + getDisplayText();
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        if (amount != null) sb.append(" | Amount: ").append(amount);
        if (currency != null) sb.append(" ").append(currency.getAcronymFr());
        if (contract != null) sb.append(" | Contract: ").append(contract.getShortDisplay());
        return sb.toString();
    }

    public boolean isApproved() {
        return approvalStatus != null && "APPROVED".equalsIgnoreCase(approvalStatus.getDesignationFr());
    }

    public String getStatusSummary() {
        if (isApproved()) return "APPROVED";
        return procurementStatus != null ? procurementStatus.getDesignationFr() : "PENDING";
    }
}
