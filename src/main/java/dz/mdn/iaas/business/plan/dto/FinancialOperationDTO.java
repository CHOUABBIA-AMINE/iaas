/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperationDTO
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
 * FinancialOperation Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinancialOperationDTO extends GenericDTO {

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

}
