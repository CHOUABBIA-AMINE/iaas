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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ContractStep Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractStepDTO extends GenericDTO {

    private int internalId;
    
    @NotNull(message = "Contract is required")
    private Long contractId;
    
    @NotNull(message = "Contract phase is required")
    private Long contractPhaseId;
    
    private Date date;
    private String observation;

}
