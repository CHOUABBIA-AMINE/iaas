/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemDTO
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
import dz.mdn.iaas.business.plan.model.PlannedItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * PlannedItem Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlannedItemDTO extends GenericDTO<PlannedItem> {

    private int internalId;
    
    @NotNull(message = "Item is required")
    private Long itemId;
    
    @NotNull(message = "Financial operation is required")
    private Long financialOperationId;
    
    private double plannedQuantity;
    private double unitPrice;
    private double totalAmount;
    private Date plannedDate;
    private int priority;
    private String observation;

    @Override
    public PlannedItem toEntity() {
        PlannedItem entity = new PlannedItem();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        return entity;
    }

    @Override
    public void updateEntity(PlannedItem entity) {
        if (this.internalId > 0) {
            entity.setInternalId(this.internalId);
        }
    }
}
