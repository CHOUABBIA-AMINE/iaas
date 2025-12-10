/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionDTO
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
 * Submission Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmissionDTO extends GenericDTO {

    private int internalId;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    @NotNull(message = "Consultation is required")
    private Long consultationId;
    
    private Long submissionStatusId;
    private double financialAmount;
    private double technicalScore;
    private Date submissionDate;
    private Date evaluationDate;
    private boolean isWinner;
    private String observation;

}
