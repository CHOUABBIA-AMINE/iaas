/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ItemDistribution Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDistributionDTO extends GenericDTO {

    private int internalId;
    
    @NotNull(message = "Planned item is required")
    private Long plannedItemId;
    
    @NotNull(message = "Financial operation is required")
    private Long financialOperationId;
    
    private double allocatedAmount;
    private double consumedAmount;
    private double remainingAmount;
    private Date allocationDate;
    private String observation;

}
