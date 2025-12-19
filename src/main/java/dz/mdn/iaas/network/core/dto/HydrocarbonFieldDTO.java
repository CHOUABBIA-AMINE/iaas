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

import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.model.Location;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.common.model.Vendor;
import dz.mdn.iaas.network.core.model.HydrocarbonField;
import dz.mdn.iaas.network.type.model.HydrocarbonFieldType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @NotNull(message = "HydrocarbonFieldType type ID is required")
    private Long hydrocarbonFieldTypeId;

    @Builder.Default
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
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            field.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            Vendor vendor = new Vendor();
            vendor.setId(this.vendorId);
            field.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            Location location = new Location();
            location.setId(this.locationId);
            field.setLocation(location);
        }
        
        if (this.hydrocarbonFieldTypeId != null) {
        	HydrocarbonFieldType hydrocarbonFieldType = new HydrocarbonFieldType();
            hydrocarbonFieldType.setId(this.hydrocarbonFieldTypeId);
            field.setHydrocarbonFieldType(hydrocarbonFieldType);
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
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            field.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            Vendor vendor = new Vendor();
            vendor.setId(this.vendorId);
            field.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            Location location = new Location();
            location.setId(this.locationId);
            field.setLocation(location);
        }
        
        if (this.hydrocarbonFieldTypeId != null) {
        	HydrocarbonFieldType hydrocarbonFieldType = new HydrocarbonFieldType();
            hydrocarbonFieldType.setId(this.hydrocarbonFieldTypeId);
            field.setHydrocarbonFieldType(hydrocarbonFieldType);
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
                .hydrocarbonFieldTypeId(field.getHydrocarbonFieldType() != null ? field.getHydrocarbonFieldType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
