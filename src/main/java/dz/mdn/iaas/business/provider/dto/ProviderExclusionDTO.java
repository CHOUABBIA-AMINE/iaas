/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusionDTO
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

import dz.mdn.iaas.business.provider.model.ExclusionType;
import dz.mdn.iaas.business.provider.model.Provider;
import dz.mdn.iaas.business.provider.model.ProviderExclusion;
import dz.mdn.iaas.common.communication.dto.MailDTO;
import dz.mdn.iaas.common.communication.model.Mail;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ProviderExclusion Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderExclusionDTO extends GenericDTO<ProviderExclusion> {

    @NotNull(message = "Start date is required")
    private Date startDate;
    
    private Date endDate;
    
    private String cause;
    
    @NotNull(message = "Exclusion type is required")
    private Long exclusionTypeId;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    private Long referenceId;
    
    private ExclusionTypeDTO exclusionType;
    
    private ProviderDTO provider;
    
    private MailDTO reference;

    @Override
    public ProviderExclusion toEntity() {
        ProviderExclusion entity = new ProviderExclusion();
        entity.setId(this.getId());
        entity.setStartDate(this.startDate);
        entity.setEndDate(this.endDate);
        entity.setCause(this.cause);
        
		if (this.exclusionTypeId != null) {
			ExclusionType exclusionType = new ExclusionType();
			exclusionType.setId(this.exclusionTypeId);
		    entity.setExclusionType(exclusionType);
		}
        
		if (this.providerId != null) {
			Provider provider = new Provider();
			provider.setId(this.providerId);
		    entity.setProvider(provider);
		}
        
		if (this.referenceId != null) {
			Mail reference = new Mail();
			reference.setId(this.referenceId);
		    entity.setReference(reference);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(ProviderExclusion entity) {
        if (this.startDate != null) entity.setStartDate(this.startDate);
        if (this.endDate != null) entity.setEndDate(this.endDate);
        if (this.cause != null) entity.setCause(this.cause);
        
		if (this.exclusionTypeId != null) {
			ExclusionType exclusionType = new ExclusionType();
			exclusionType.setId(this.exclusionTypeId);
		    entity.setExclusionType(exclusionType);
		}
        
		if (this.providerId != null) {
			Provider provider = new Provider();
			provider.setId(this.providerId);
		    entity.setProvider(provider);
		}
        
		if (this.referenceId != null) {
			Mail reference = new Mail();
			reference.setId(this.referenceId);
		    entity.setReference(reference);
		}
    }

    public static ProviderExclusionDTO fromEntity(ProviderExclusion entity) {
        if (entity == null) return null;
        
        return ProviderExclusionDTO.builder()
                .id(entity.getId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .cause(entity.getCause())
                .exclusionTypeId(entity.getExclusionType() != null ? entity.getExclusionType().getId() : null)
                .providerId(entity.getProvider() != null ? entity.getProvider().getId() : null)
                .referenceId(entity.getReference() != null ? entity.getReference().getId() : null)
                
                .exclusionType(entity.getExclusionType() != null ? ExclusionTypeDTO.fromEntity(entity.getExclusionType()) : null)
                .provider(entity.getProvider() != null ? ProviderDTO.fromEntity(entity.getProvider()) : null)
                .reference(entity.getReference() != null ? MailDTO.fromEntity(entity.getReference()) : null)
                .build();
    }
}
