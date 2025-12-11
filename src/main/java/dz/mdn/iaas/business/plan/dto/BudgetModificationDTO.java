/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationDTO
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
import dz.mdn.iaas.business.plan.model.BudgetModification;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * BudgetModification Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetModificationDTO extends GenericDTO<BudgetModification> {

    private int internalId;
    private String reference;
    private Date modificationDate;
    private double previousAmount;
    private double newAmount;
    private double differenceAmount;
    private String reason;
    private String observation;
    
    @NotNull(message = "Budget type is required")
    private Long budgetTypeId;
    
    @NotNull(message = "Item distribution is required")
    private Long itemDistributionId;
    
    private Long approvalStatusId;

    @Override
    public BudgetModification toEntity() {
        BudgetModification entity = new BudgetModification();
        entity.setId(this.getId());
        return entity;
    }

    @Override
    public void updateEntity(BudgetModification entity) {
        // Update logic for fields present in model
    }
}
