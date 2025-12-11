/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
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
import dz.mdn.iaas.business.provider.model.Clearance;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Clearance Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClearanceDTO extends GenericDTO<Clearance> {

    private int internalId;
    private String reference;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    private Date issueDate;
    private Date expiryDate;
    private String issuingAuthority;
    private boolean isValid;
    private String observation;

    @Override
    public Clearance toEntity() {
        Clearance entity = new Clearance();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        entity.setReference(this.reference);
        return entity;
    }

    @Override
    public void updateEntity(Clearance entity) {
        if (this.internalId > 0) entity.setInternalId(this.internalId);
        if (this.reference != null) entity.setReference(this.reference);
    }
}
