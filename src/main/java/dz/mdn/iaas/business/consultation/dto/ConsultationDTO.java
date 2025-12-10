/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationDTO
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
import dz.mdn.iaas.business.consultation.model.Consultation;
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
public class ConsultationDTO extends GenericDTO<Consultation> {

    private int internalId;
    private String reference;
    private String designationAr;
    private String designationEn;
    @NotNull
    private String designationFr;
    private double estimatedAmount;
    private double minAmountBid;
    private double maxAmountBid;
    private Date launchDate;
    private Date openingDate;
    private Date awardDate;
    private Date approvalDate;
    private Date notifyDate;
    private String observation;
    @NotNull
    private Long procurementMethodId;
    @NotNull
    private Long awardMethodId;
    @NotNull
    private Long consultationStepId;
    @NotNull
    private Long procurementStatusId;
    private Long approvalStatusId;
    @NotNull
    private Long currencyId;

    @Override
    public Consultation toEntity() {
        Consultation entity = new Consultation();
        entity.setId(getId());
        entity.setInternalId(this.internalId);
        entity.setReference(this.reference);
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        entity.setEstimatedAmount(this.estimatedAmount);
        entity.setMinAmountBid(this.minAmountBid);
        entity.setMaxAmountBid(this.maxAmountBid);
        entity.setLaunchDate(this.launchDate);
        entity.setOpeningDate(this.openingDate);
        entity.setAwardDate(this.awardDate);
        entity.setApprovalDate(this.approvalDate);
        entity.setNotifyDate(this.notifyDate);
        entity.setObservation(this.observation);
        return entity;
    }

    @Override
    public void updateEntity(Consultation entity) {
        if (this.reference != null) entity.setReference(this.reference);
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
        entity.setEstimatedAmount(this.estimatedAmount);
        entity.setMinAmountBid(this.minAmountBid);
        entity.setMaxAmountBid(this.maxAmountBid);
        if (this.launchDate != null) entity.setLaunchDate(this.launchDate);
        if (this.openingDate != null) entity.setOpeningDate(this.openingDate);
        if (this.awardDate != null) entity.setAwardDate(this.awardDate);
        if (this.approvalDate != null) entity.setApprovalDate(this.approvalDate);
        if (this.notifyDate != null) entity.setNotifyDate(this.notifyDate);
        if (this.observation != null) entity.setObservation(this.observation);
    }

    public static ConsultationDTO fromEntity(Consultation entity) {
        if (entity == null) return null;
        return ConsultationDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .reference(entity.getReference())
                .designationAr(entity.getDesignationAr())
                .designationEn(entity.getDesignationEn())
                .designationFr(entity.getDesignationFr())
                .estimatedAmount(entity.getEstimatedAmount())
                .minAmountBid(entity.getMinAmountBid())
                .maxAmountBid(entity.getMaxAmountBid())
                .launchDate(entity.getLaunchDate())
                .openingDate(entity.getOpeningDate())
                .awardDate(entity.getAwardDate())
                .approvalDate(entity.getApprovalDate())
                .notifyDate(entity.getNotifyDate())
                .observation(entity.getObservation())
                .build();
    }
}
