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
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.provider.model.ProviderExclusion;
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

    private int internalId;
    
    @NotNull(message = "Provider is required")
    private Long providerId;
    
    @NotNull(message = "Exclusion type is required")
    private Long exclusionTypeId;
    
    private Date startDate;
    
    private Date endDate;

    @Override
    public ProviderExclusion toEntity() {
        ProviderExclusion entity = new ProviderExclusion();
        entity.setId(this.getId());
        entity.setInternalId(this.internalId);
        entity.setStartDate(this.startDate);
        entity.setEndDate(this.endDate);
        return entity;
    }

    @Override
    public void updateEntity(ProviderExclusion entity) {
        if (this.internalId > 0) entity.setInternalId(this.internalId);
        if (this.startDate != null) entity.setStartDate(this.startDate);
        if (this.endDate != null) entity.setEndDate(this.endDate);
    }

    public static ProviderExclusionDTO fromEntity(ProviderExclusion entity) {
        if (entity == null) return null;
        
        return ProviderExclusionDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .providerId(entity.getProvider() != null ? entity.getProvider().getId() : null)
                .exclusionTypeId(entity.getExclusionType() != null ? entity.getExclusionType().getId() : null)
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
