/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItemDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.contract.model.ContractItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ContractItem Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractItemDTO extends GenericDTO<ContractItem> {

    private int internalId;
    
    @NotNull(message = "Contract is required")
    private Long contractId;
    
    @NotNull(message = "Planned item is required")
    private Long plannedItemId;

    @Override
    public ContractItem toEntity() {
        ContractItem entity = new ContractItem();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        return entity;
    }

    @Override
    public void updateEntity(ContractItem entity) {
        if (this.internalId > 0) {
            entity.setInternalId(this.internalId);
        }
    }
}
