/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: HydrocarbonFieldDTO
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.core.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.network.core.model.HydrocarbonField;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * HydrocarbonField Data Transfer Object - Extends FacilityDTO
 * Inherits all fields from parent Facility class
 * 
 * Additional Fields:
 * - stationTypeId - Type of hydrocarbon field (required)
 * - pipelineIds - Associated pipelines
 * - partnerIds - Associated partners
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HydrocarbonFieldDTO extends FacilityDTO {

    @NotNull(message = "Station type ID is required")
    private Long stationTypeId;

    @SuperBuilder.Default
    private Set<Long> pipelineIds = new HashSet<>();

    @SuperBuilder.Default
    private Set<Long> partnerIds = new HashSet<>();

    @Override
    public HydrocarbonField toEntity() {
        HydrocarbonField field = new HydrocarbonField();
        field.setId(getId());
        field.setCode(getCode());
        field.setName(getName());
        field.setInstallationDate(getInstallationDate());
        field.setCommissioningDate(getCommissioningDate());
        field.setDecommissioningDate(getDecommissioningDate());
        field.setLocationId(getLocationId());
        field.setFacilityTypeId(getFacilityTypeId());
        return field;
    }

    @Override
    public void updateEntity(HydrocarbonField field) {
        super.updateEntity((org.hibernate.mapping.Subclass) field);
        if (this.stationTypeId != null) {
            // Station type would be set via relationship
        }
    }

    public static HydrocarbonFieldDTO fromEntity(HydrocarbonField field) {
        if (field == null) return null;
        
        Set<Long> pipelineIds = new HashSet<>();
        if (field.getPipelines() != null) {
            field.getPipelines().forEach(p -> pipelineIds.add(p.getId()));
        }
        
        Set<Long> partnerIds = new HashSet<>();
        if (field.getPartners() != null) {
            field.getPartners().forEach(p -> partnerIds.add(p.getId()));
        }
        
        return HydrocarbonFieldDTO.builder()
                .id(field.getId())
                .code(field.getCode())
                .name(field.getName())
                .installationDate(field.getInstallationDate())
                .commissioningDate(field.getCommissioningDate())
                .decommissioningDate(field.getDecommissioningDate())
                .locationId(field.getLocation() != null ? field.getLocation().getId() : null)
                .facilityTypeId(field.getFacilityType() != null ? field.getFacilityType().getId() : null)
                .operationalStatusId(field.getOperationalStatus() != null ? field.getOperationalStatus().getId() : null)
                .stationTypeId(field.getStationType() != null ? field.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .partnerIds(partnerIds)
                .build();
    }
}
