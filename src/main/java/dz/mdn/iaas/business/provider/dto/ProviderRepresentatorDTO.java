/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentatorDTO
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
import dz.mdn.iaas.business.provider.model.ProviderRepresentator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ProviderRepresentator Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderRepresentatorDTO extends GenericDTO<ProviderRepresentator> {

    private int internalId;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    private String position;
    private String nationalId;
    
    @Email(message = "Invalid email format")
    private String email;
    
    private String phone;
    private String mobile;
    private Date appointmentDate;
    private boolean isPrimary;
    private String observation;

    @Override
    public ProviderRepresentator toEntity() {
        ProviderRepresentator entity = new ProviderRepresentator();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        entity.setFirstName(this.firstName);
        entity.setLastName(this.lastName);
        entity.setPosition(this.position);
        entity.setNationalId(this.nationalId);
        return entity;
    }

    @Override
    public void updateEntity(ProviderRepresentator entity) {
        if (this.internalId > 0) entity.setInternalId(this.internalId);
        if (this.firstName != null) entity.setFirstName(this.firstName);
        if (this.lastName != null) entity.setLastName(this.lastName);
        if (this.position != null) entity.setPosition(this.position);
        if (this.nationalId != null) entity.setNationalId(this.nationalId);
    }
}
