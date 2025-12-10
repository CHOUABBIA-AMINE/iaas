/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Clearance Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClearanceDTO extends GenericDTO {

    private int internalId;
    private String reference;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    private Date issueDate;
    private Date expiryDate;
    private String issuingAuthority;
    private boolean isValid;
    private String observation;

}
