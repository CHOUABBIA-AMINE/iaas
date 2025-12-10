/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStepDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.contract.model.ContractStep;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ContractStep Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractStepDTO extends GenericDTO<ContractStep> {

    private int internalId;
    
    @NotNull(message = "Contract is required")
    private Long contractId;
    
    @NotNull(message = "Contract phase is required")
    private Long contractPhaseId;
    
    private Date date;
    private String observation;

    @Override
    public ContractStep toEntity() {
        ContractStep entity = new ContractStep();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        entity.setDate(this.date);
        entity.setObservation(this.observation);
        return entity;
    }

    @Override
    public void updateEntity(ContractStep entity) {
        if (this.internalId > 0) {
            entity.setInternalId(this.internalId);
        }
        if (this.date != null) {
            entity.setDate(this.date);
        }
        if (this.observation != null) {
            entity.setObservation(this.observation);
        }
    }
}
