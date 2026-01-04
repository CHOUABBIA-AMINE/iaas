/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.plan.model.ItemDistribution;
import dz.mdn.iaas.business.plan.model.PlannedItem;
import dz.mdn.iaas.common.administration.dto.StructureDTO;
import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ItemDistribution Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDistributionDTO extends GenericDTO<ItemDistribution> {

    private float quantity;
    
    @NotNull(message = "Planned item is required")
    private Long plannedItemId;
    
    @NotNull(message = "Structure is required")
    private Long structureId;
    
    private PlannedItemDTO plannedItem;
    
    private StructureDTO structure;

    @Override
    public ItemDistribution toEntity() {
        ItemDistribution entity = new ItemDistribution();
        entity.setId(this.getId());
        entity.setQuantity(this.quantity);
        
		if (this.plannedItemId != null) {
			PlannedItem plannedItem = new PlannedItem();
			plannedItem.setId(this.plannedItemId);
		    entity.setPlannedItem(plannedItem);
		}
        
		if (this.structureId != null) {
			Structure structure = new Structure();
			structure.setId(this.structureId);
		    entity.setStructure(structure);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(ItemDistribution entity) {
        entity.setQuantity(this.quantity);
        
		if (this.plannedItemId != null) {
			PlannedItem plannedItem = new PlannedItem();
			plannedItem.setId(this.plannedItemId);
		    entity.setPlannedItem(plannedItem);
		}
        
		if (this.structureId != null) {
			Structure structure = new Structure();
			structure.setId(this.structureId);
		    entity.setStructure(structure);
		}
    }

    public static ItemDistributionDTO fromEntity(ItemDistribution entity) {
        if (entity == null) return null;
        return ItemDistributionDTO.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .plannedItemId(entity.getPlannedItem() != null ? entity.getPlannedItem().getId() : null)
                .structureId(entity.getStructure() != null ? entity.getStructure().getId() : null)
                
                .plannedItem(entity.getPlannedItem() != null ? PlannedItemDTO.fromEntity(entity.getPlannedItem()) : null)
                .structure(entity.getStructure() != null ? StructureDTO.fromEntity(entity.getStructure()) : null)
                .build();
    }
}
