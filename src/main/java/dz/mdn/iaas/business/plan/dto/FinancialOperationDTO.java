/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperationDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.plan.model.FinancialOperation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * FinancialOperation Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinancialOperationDTO extends GenericDTO<FinancialOperation> {

    private int internalId;
    private String reference;
    private String designationAr;
    private String designationEn;
    
    @NotNull(message = "French designation is required")
    private String designationFr;
    
    private double initialAmount;
    private double currentAmount;
    private double consumedAmount;
    private double remainingAmount;
    private Date startDate;
    private Date endDate;
    private String observation;
    
    @NotNull(message = "Domain is required")
    private Long domainId;
    
    @NotNull(message = "Budget type is required")
    private Long budgetTypeId;
    
    private Long approvalStatusId;

    @Override
    public FinancialOperation toEntity() {
        FinancialOperation entity = new FinancialOperation();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        entity.setReference(this.reference);
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        return entity;
    }

    @Override
    public void updateEntity(FinancialOperation entity) {
        if (this.internalId > 0) entity.setInternalId(this.internalId);
        if (this.reference != null) entity.setReference(this.reference);
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
    }
}
