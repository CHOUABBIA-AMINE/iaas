/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: PlannedItemDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: DTO
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / DTO
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.plan.model.FinancialOperation;
import dz.mdn.iaas.business.plan.model.Item;
import dz.mdn.iaas.business.plan.model.ItemStatus;
import dz.mdn.iaas.business.plan.model.PlannedItem;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Designation is required")
    @Size(max = 200, message = "Designation must not exceed 200 characters")
    private String designation;
    
    private double unitairCost;
    
    private double planedQuantity;
    
    private double allocatedAmount;
    
    @NotNull(message = "Item status is required")
    private Long itemStatusId;
    
    @NotNull(message = "Item is required")
    private Long itemId;
    
    @NotNull(message = "Financial Operation Id is required")
    private Long financialOperationId;
    
    private ItemStatusDTO itemStatus;

    private ItemDTO item;
    
    private FinancialOperationDTO financialOperation;

    @Override
    public PlannedItem toEntity() {
        PlannedItem entity = new PlannedItem();
        entity.setId(this.getId());
        entity.setDesignation(this.designation);
        entity.setUnitairCost(this.unitairCost);
        entity.setPlanedQuantity(this.planedQuantity);
        entity.setAllocatedAmount(this.allocatedAmount);
        
		if (this.itemStatusId != null) {
			ItemStatus itemStatus = new ItemStatus();
			itemStatus.setId(this.itemStatusId);
		    entity.setItemStatus(itemStatus);
		}
        
		if (this.itemId != null) {
			Item item = new Item();
			item.setId(this.itemId);
		    entity.setItem(item);
		}
        
		if (this.financialOperationId != null) {
			FinancialOperation financialOperation = new FinancialOperation();
			financialOperation.setId(this.financialOperationId);
		    entity.setFinancialOperation(financialOperation);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(PlannedItem entity) {
        if (this.designation != null) entity.setDesignation(this.designation);
        entity.setUnitairCost(this.unitairCost);
        entity.setPlanedQuantity(this.planedQuantity);
        entity.setAllocatedAmount(this.allocatedAmount);
        
		if (this.itemStatusId != null) {
			ItemStatus itemStatus = new ItemStatus();
			itemStatus.setId(this.itemStatusId);
		    entity.setItemStatus(itemStatus);
		}
        
		if (this.itemId != null) {
			Item item = new Item();
			item.setId(this.itemId);
		    entity.setItem(item);
		}
        
		if (this.financialOperationId != null) {
			FinancialOperation financialOperation = new FinancialOperation();
			financialOperation.setId(this.financialOperationId);
		    entity.setFinancialOperation(financialOperation);
		}
    }

    public static PlannedItemDTO fromEntity(PlannedItem entity) {
        if (entity == null) return null;
        return PlannedItemDTO.builder()
                .id(entity.getId())
                .designation(entity.getDesignation())
                .unitairCost(entity.getUnitairCost())
                .planedQuantity(entity.getPlanedQuantity())
                .allocatedAmount(entity.getAllocatedAmount())
                .itemStatusId(entity.getItemStatus() != null ? entity.getItemStatus().getId() : null)
                .itemId(entity.getItem() != null ? entity.getItem().getId() : null)
                .financialOperationId(entity.getFinancialOperation() != null ? entity.getFinancialOperation().getId() : null)
                
                .itemStatus(entity.getItemStatus() != null ? ItemStatusDTO.fromEntity(entity.getItemStatus()) : null)
                .item(entity.getItem() != null ? ItemDTO.fromEntity(entity.getItem()) : null)
                .financialOperation(entity.getFinancialOperation() != null ? FinancialOperationDTO.fromEntity(entity.getFinancialOperation()) : null)
                .build();
    }
}
