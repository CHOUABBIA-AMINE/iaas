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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;
    
    @Size(max = 300, message = "Email must not exceed 300 characters")
    private String mail;
    
    @Size(max = 200, message = "Phone number must not exceed 200 characters")
    private String phoneNumber;
    
    private Date birthDate;

    @Override
    public ProviderRepresentator toEntity() {
        ProviderRepresentator entity = new ProviderRepresentator();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        entity.setFirstName(this.firstName);
        entity.setLastName(this.lastName);
        entity.setMail(this.mail);
        entity.setPhoneNumber(this.phoneNumber);
        entity.setBirthDate(this.birthDate);
        return entity;
    }

    @Override
    public void updateEntity(ProviderRepresentator entity) {
        if (this.internalId > 0) entity.setInternalId(this.internalId);
        if (this.firstName != null) entity.setFirstName(this.firstName);
        if (this.lastName != null) entity.setLastName(this.lastName);
        if (this.mail != null) entity.setMail(this.mail);
        if (this.phoneNumber != null) entity.setPhoneNumber(this.phoneNumber);
        if (this.birthDate != null) entity.setBirthDate(this.birthDate);
    }

    public static ProviderRepresentatorDTO fromEntity(ProviderRepresentator entity) {
        if (entity == null) return null;
        
        return ProviderRepresentatorDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .providerId(entity.getProvider() != null ? entity.getProvider().getId() : null)
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .mail(entity.getMail())
                .phoneNumber(entity.getPhoneNumber())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
