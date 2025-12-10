/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Consultation Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationDTO extends GenericDTO {

    private int internalId;
    private String reference;
    private String designationAr;
    private String designationEn;
    
    @NotNull(message = "French designation is required")
    private String designationFr;
    
    private double estimatedAmount;
    private double minAmountBid;
    private double maxAmountBid;
    private Date launchDate;
    private Date openingDate;
    private Date awardDate;
    private Date approvalDate;
    private Date notifyDate;
    private String observation;
    
    @NotNull(message = "Procurement method is required")
    private Long procurementMethodId;
    
    @NotNull(message = "Award method is required")
    private Long awardMethodId;
    
    @NotNull(message = "Consultation step is required")
    private Long consultationStepId;
    
    @NotNull(message = "Procurement status is required")
    private Long procurementStatusId;
    
    private Long approvalStatusId;
    
    @NotNull(message = "Currency is required")
    private Long currencyId;

}
