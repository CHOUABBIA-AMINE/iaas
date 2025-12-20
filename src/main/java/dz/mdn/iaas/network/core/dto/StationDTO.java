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

import dz.mdn.iaas.common.administration.dto.LocalityDTO;
import dz.mdn.iaas.common.administration.model.Locality;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.common.dto.VendorDTO;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.common.model.Vendor;
import dz.mdn.iaas.network.core.model.Station;
import dz.mdn.iaas.network.type.model.StationType;
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
    
    @NotBlank(message = "Place name is required")
    @Size(max = 100, message = "PlaceName must not exceed 100 characters")
    private String placeName;
    
    @NotNull(message = "Latitude thickness is required")
    private Double latitude;
    
    @NotNull(message = "Longitude thickness is required")
    private Double longitude;
    
    @NotNull(message = "Elevation is required")
    private Double elevation;

    @NotNull(message = "Operational status ID is required")
    private Long operationalStatusId;

    @Size(max = 100, message = "provider must not exceed 100 characters")
    private Long vendorId;

    @NotNull(message = "Locality is required")
    private Long localityId;

    @NotNull(message = "Station type ID is required")
    private Long stationTypeId;
    
    private OperationalStatusDTO operationalStatus;
    private VendorDTO vendor;
    private LocalityDTO locality;

    @Builder.Default
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
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            station.setOperationalStatus(status);
        }
        
        station.setPlaceName(this.placeName);
        station.setLatitude(this.latitude);
        station.setLongitude(this.longitude);
        station.setElevation(this.elevation);
        
        if (this.vendorId != null) {
        	Vendor vendor = new Vendor();
        	vendor.setId(this.vendorId);
        	station.setVendor(vendor);
        }
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	station.setLocality(locality);
        }
        
        if (this.stationTypeId != null) {
            StationType stationType = new StationType();
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
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            station.setOperationalStatus(status);
        }
        
        if (this.placeName != null) station.setPlaceName(this.placeName);
        if (this.latitude != null) station.setLatitude(this.latitude);
        if (this.longitude != null) station.setLongitude(this.longitude);
        if (this.elevation != null) station.setElevation(this.elevation);
        
        if (this.vendorId != null) {
        	Vendor vendor = new Vendor();
        	vendor.setId(this.vendorId);
        	station.setVendor(vendor);
        }
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	station.setLocality(locality);
        }
        
        if (this.stationTypeId != null) {
            StationType stationType = new StationType();
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
                .placeName(station.getPlaceName())
                .latitude(station.getLatitude())
                .longitude(station.getLongitude())
                .elevation(station.getElevation())
                
                .operationalStatusId(station.getOperationalStatus() != null ? station.getOperationalStatus().getId() : null)
                .vendorId(station.getVendor() != null ? station.getVendor().getId() : null)
                .localityId(station.getLocality() != null ? station.getLocality().getId() : null)
                .stationTypeId(station.getStationType() != null ? station.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                
                .operationalStatus(station.getOperationalStatus() != null ? OperationalStatusDTO.fromEntity(station.getOperationalStatus()) : null)
                .vendor(station.getVendor() != null ? VendorDTO.fromEntity(station.getVendor()) : null)
                .locality(station.getLocality() != null ? LocalityDTO.fromEntity(station.getLocality()) : null)
                .build();
    }
}
