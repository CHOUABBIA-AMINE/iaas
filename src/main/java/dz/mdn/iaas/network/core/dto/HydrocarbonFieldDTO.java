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
 * Inheritance chain: GenericDTO -> FacilityDTO -> HydrocarbonFieldDTO
 * Inherits from Facility: code, name, installationDate, commissioningDate, decommissioningDate,
 *                        operationalStatusId, locationId, facilityTypeId, vendorId
 * 
 * Additional Fields:
 * - stationTypeId - Type of hydrocarbon field (required)
 * - pipelineIds - Associated pipelines
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

    @Override
    public HydrocarbonField toEntity() {
        HydrocarbonField field = new HydrocarbonField();
        field.setId(getId());
        field.setCode(getCode());
        field.setName(getName());
        field.setInstallationDate(getInstallationDate());
        field.setCommissioningDate(getCommissioningDate());
        field.setRetirementDate(getRetirementDate());
        field.setProvider(getProvider());
        
        // Set relationships from parent DTO
        if (getOperationalStatusId() != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(getOperationalStatusId());
            field.setOperationalStatus(status);
        }
        
        if (getLocationId() != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(getLocationId());
            field.setLocation(location);
        }
        
        if (getVendorId() != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(getVendorId());
            field.setVendor(vendor);
        }
        
        return field;
    }

    @Override
    public void updateEntity(HydrocarbonField field) {
        super.updateEntity((Facility) field);
        // Additional update logic for HydrocarbonField-specific fields can go here
    }

    public static HydrocarbonFieldDTO fromEntity(HydrocarbonField field) {
        if (field == null) return null;
        
        Set<Long> pipelineIds = new HashSet<>();
        if (field.getPipelines() != null) {
            field.getPipelines().forEach(p -> pipelineIds.add(p.getId()));
        }
        
        return HydrocarbonFieldDTO.builder()
                .id(field.getId())
                .code(field.getCode())
                .name(field.getName())
                .installationDate(field.getInstallationDate())
                .commissioningDate(field.getCommissioningDate())
                .retirementDate(field.getRetirementDate())
                .provider(field.getProvider())
                .operationalStatusId(field.getOperationalStatus() != null ? field.getOperationalStatus().getId() : null)
                .locationId(field.getLocation() != null ? field.getLocation().getId() : null)
                .vendorId(field.getVendor() != null ? field.getVendor().getId() : null)
                .stationTypeId(field.getStationType() != null ? field.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
