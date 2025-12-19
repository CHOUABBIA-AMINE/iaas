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

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.network.core.model.HydrocarbonField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * HydrocarbonField Data Transfer Object - Extends GenericDTO
 * Maps HydrocarbonField entity which extends Facility which extends Infrastructure
 * 
 * Inherited from Infrastructure:
 * - code, name, installationDate, commissioningDate, decommissioningDate, operationalStatusId
 * 
 * Inherited from Facility:
 * - vendor, location (vendorId, locationId)
 * 
 * HydrocarbonField specific:
 * - stationType, pipelines
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HydrocarbonFieldDTO extends GenericDTO<HydrocarbonField> {

    // Infrastructure fields
    @NotBlank(message = "Code is required")
    @Size(min = 2, max = 20, message = "Code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    private LocalDate installationDate;
    private LocalDate commissioningDate;
    private LocalDate decommissioningDate;

    @NotNull(message = "Operational status ID is required")
    private Long operationalStatusId;

    // Facility fields
    @NotNull(message = "Vendor ID is required")
    private Long vendorId;

    @NotNull(message = "Location ID is required")
    private Long locationId;

    // HydrocarbonField specific fields
    @NotNull(message = "Station type ID is required")
    private Long stationTypeId;

    @SuperBuilder.Default
    private Set<Long> pipelineIds = new HashSet<>();

    @Override
    public HydrocarbonField toEntity() {
        HydrocarbonField field = new HydrocarbonField();
        field.setId(getId());
        field.setCode(this.code);
        field.setName(this.name);
        field.setInstallationDate(this.installationDate);
        field.setCommissioningDate(this.commissioningDate);
        field.setDecommissioningDate(this.decommissioningDate);
        
        if (this.operationalStatusId != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(this.operationalStatusId);
            field.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(this.vendorId);
            field.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(this.locationId);
            field.setLocation(location);
        }
        
        if (this.stationTypeId != null) {
            dz.mdn.iaas.network.type.model.StationType stationType = 
                new dz.mdn.iaas.network.type.model.StationType();
            stationType.setId(this.stationTypeId);
            field.setStationType(stationType);
        }
        
        return field;
    }

    @Override
    public void updateEntity(HydrocarbonField field) {
        if (this.code != null) field.setCode(this.code);
        if (this.name != null) field.setName(this.name);
        if (this.installationDate != null) field.setInstallationDate(this.installationDate);
        if (this.commissioningDate != null) field.setCommissioningDate(this.commissioningDate);
        if (this.decommissioningDate != null) field.setDecommissioningDate(this.decommissioningDate);
        
        if (this.operationalStatusId != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(this.operationalStatusId);
            field.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(this.vendorId);
            field.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(this.locationId);
            field.setLocation(location);
        }
        
        if (this.stationTypeId != null) {
            dz.mdn.iaas.network.type.model.StationType stationType = 
                new dz.mdn.iaas.network.type.model.StationType();
            stationType.setId(this.stationTypeId);
            field.setStationType(stationType);
        }
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
                .decommissioningDate(field.getDecommissioningDate())
                .operationalStatusId(field.getOperationalStatus() != null ? field.getOperationalStatus().getId() : null)
                .vendorId(field.getVendor() != null ? field.getVendor().getId() : null)
                .locationId(field.getLocation() != null ? field.getLocation().getId() : null)
                .stationTypeId(field.getStationType() != null ? field.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
