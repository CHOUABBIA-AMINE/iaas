/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Amendment Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmendmentDTO extends GenericDTO {

    private int internalId;
    private String reference;
    private String designationAr;
    private String designationEn;
    
    @NotNull(message = "French designation is required")
    private String designationFr;
    
    private double amount;
    private double transferableAmount;
    private Date startDate;
    private Date approvalDate;
    private Date notifyDate;
    private String observation;
    
    @NotNull(message = "Contract is required")
    private Long contractId;
    
    @NotNull(message = "Amendment type is required")
    private Long amendmentTypeId;
    
    @NotNull(message = "Procurement status is required")
    private Long procurementStatusId;
    
    @NotNull(message = "Amendment step is required")
    private Long amendmentStepId;
    
    private Long approvalStatusId;
    
    @NotNull(message = "Currency is required")
    private Long currencyId;

}
