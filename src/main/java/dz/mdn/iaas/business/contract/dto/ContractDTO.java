/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractDTO
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
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Contract Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDTO extends GenericDTO {

    @NotBlank(message = "Internal ID is required")
    private String internalId;
    
    private String contractYear;
    private String reference;
    private String designationAr;
    private String designationEn;
    
    @NotBlank(message = "French designation is required")
    private String designationFr;
    
    private double amount;
    private double transferableAmount;
    private Date startDate;
    private String approvalReference;
    private Date approvalDate;
    private Date contractDate;
    private Date notifyDate;
    private int contractDuration;
    private String observation;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    @NotNull(message = "Contract type is required")
    private Long contractTypeId;
    
    @NotNull(message = "Contract step is required")
    private Long contractStepId;
    
    @NotNull(message = "Procurement status is required")
    private Long procurementStatusId;
    
    private Long approvalStatusId;
    
    @NotNull(message = "Currency is required")
    private Long currencyId;
    
    private Long consultationId;
    
    private Long contractUpId;

}
