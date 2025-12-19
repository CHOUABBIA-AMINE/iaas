/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StationDTO
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
import dz.mdn.iaas.network.core.model.Station;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Station Data Transfer Object - Extends GenericDTO
 * Maps Station entity which extends Facility which extends Infrastructure
 * 
 * Inherited from Infrastructure:
 * - code, name, installationDate, commissioningDate, decommissioningDate, operationalStatusId
 * 
 * Inherited from Facility:
 * - vendor, location (vendorId, locationId)
 * 
 * Station specific:
 * - stationType, pipelines
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationDTO extends GenericDTO<Station> {

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

    // Station specific fields
    @NotNull(message = "Station type ID is required")
    private Long stationTypeId;

    @SuperBuilder.Default
    private Set<Long> pipelineIds = new HashSet<>();

    @Override
    public Station toEntity() {
        Station station = new Station();
        station.setId(getId());
        station.setCode(this.code);
        station.setName(this.name);
        station.setInstallationDate(this.installationDate);
        station.setCommissioningDate(this.commissioningDate);
        station.setDecommissioningDate(this.decommissioningDate);
        
        if (this.operationalStatusId != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(this.operationalStatusId);
            station.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(this.vendorId);
            station.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(this.locationId);
            station.setLocation(location);
        }
        
        if (this.stationTypeId != null) {
            dz.mdn.iaas.network.type.model.StationType stationType = 
                new dz.mdn.iaas.network.type.model.StationType();
            stationType.setId(this.stationTypeId);
            station.setStationType(stationType);
        }
        
        return station;
    }

    @Override
    public void updateEntity(Station station) {
        if (this.code != null) station.setCode(this.code);
        if (this.name != null) station.setName(this.name);
        if (this.installationDate != null) station.setInstallationDate(this.installationDate);
        if (this.commissioningDate != null) station.setCommissioningDate(this.commissioningDate);
        if (this.decommissioningDate != null) station.setDecommissioningDate(this.decommissioningDate);
        
        if (this.operationalStatusId != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(this.operationalStatusId);
            station.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(this.vendorId);
            station.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(this.locationId);
            station.setLocation(location);
        }
        
        if (this.stationTypeId != null) {
            dz.mdn.iaas.network.type.model.StationType stationType = 
                new dz.mdn.iaas.network.type.model.StationType();
            stationType.setId(this.stationTypeId);
            station.setStationType(stationType);
        }
    }

    public static StationDTO fromEntity(Station station) {
        if (station == null) return null;
        
        Set<Long> pipelineIds = new HashSet<>();
        if (station.getPipelines() != null) {
            station.getPipelines().forEach(p -> pipelineIds.add(p.getId()));
        }
        
        return StationDTO.builder()
                .id(station.getId())
                .code(station.getCode())
                .name(station.getName())
                .installationDate(station.getInstallationDate())
                .commissioningDate(station.getCommissioningDate())
                .decommissioningDate(station.getDecommissioningDate())
                .operationalStatusId(station.getOperationalStatus() != null ? station.getOperationalStatus().getId() : null)
                .vendorId(station.getVendor() != null ? station.getVendor().getId() : null)
                .locationId(station.getLocation() != null ? station.getLocation().getId() : null)
                .stationTypeId(station.getStationType() != null ? station.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
