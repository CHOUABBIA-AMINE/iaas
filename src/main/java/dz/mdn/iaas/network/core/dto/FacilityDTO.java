/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.core.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.administration.dto.LocalityDTO;
import dz.mdn.iaas.common.administration.model.Locality;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.common.dto.VendorDTO;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.common.model.Vendor;
import dz.mdn.iaas.network.core.model.Facility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Facility Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 * - code (F_02) - unique, required
 * - operationalStatusId (F_03) - required
 * - locationId (F_04) - required
 * - facilityTypeId (F_05) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityDTO extends GenericDTO<Facility> {

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
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

    @NotNull(message = "Operational status is required")
    private Long operationalStatusId;

    @NotNull(message = "provider is required")
    private Long vendorId;

    @NotNull(message = "Locality is required")
    private Long localityId;

    // Nested DTOs for read operations
    private OperationalStatusDTO operationalStatus;
    private VendorDTO vendor;
    private LocalityDTO locality;

    @Override
    public Facility toEntity() {
        Facility facility = new Facility();
        facility.setId(getId());
        facility.setCode(this.code);
        facility.setName(this.name);
        facility.setInstallationDate(this.installationDate);
        facility.setCommissioningDate(this.commissioningDate);
        facility.setDecommissioningDate(this.decommissioningDate);
        facility.setPlaceName(this.placeName);
        facility.setLatitude(this.latitude);
        facility.setLongitude(this.longitude);
        facility.setElevation(this.elevation);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            facility.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
        	Vendor vendor = new Vendor();
        	vendor.setId(this.vendorId);
            facility.setVendor(vendor);
        }
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	facility.setLocality(locality);
        }
        
        return facility;
    }

    @Override
    public void updateEntity(Facility facility) {
        if (this.code != null) facility.setCode(this.code);
        if (this.name != null) facility.setName(this.name);
        if (this.installationDate != null) facility.setInstallationDate(this.installationDate);
        if (this.commissioningDate != null) facility.setCommissioningDate(this.commissioningDate);
        if (this.decommissioningDate != null) facility.setDecommissioningDate(this.decommissioningDate);
        if (this.placeName != null) facility.setPlaceName(this.placeName);
        if (this.latitude != null) facility.setLatitude(this.latitude);
        if (this.longitude != null) facility.setLongitude(this.longitude);
        if (this.elevation != null) facility.setElevation(this.elevation);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            facility.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
        	Vendor vendor = new Vendor();
        	vendor.setId(this.vendorId);
            facility.setVendor(vendor);
        }     
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	facility.setLocality(locality);
        }
    }

    public static FacilityDTO fromEntity(Facility facility) {
        if (facility == null) return null;
        
        return FacilityDTO.builder()
                .id(facility.getId())
                .code(facility.getCode())
                .name(facility.getName())
                .installationDate(facility.getInstallationDate())
                .commissioningDate(facility.getCommissioningDate())
                .decommissioningDate(facility.getDecommissioningDate())
                .placeName(facility.getPlaceName())
                .latitude(facility.getLatitude())
                .longitude(facility.getLongitude())
                .elevation(facility.getElevation())
                
                .operationalStatusId(facility.getOperationalStatus() != null ? facility.getOperationalStatus().getId() : null)
                .vendorId(facility.getVendor() != null ? facility.getVendor().getId() : null)
                .localityId(facility.getLocality() != null ? facility.getLocality().getId() : null)
                
                .operationalStatus(facility.getOperationalStatus() != null ? OperationalStatusDTO.fromEntity(facility.getOperationalStatus()) : null)
                .vendor(facility.getVendor() != null ? VendorDTO.fromEntity(facility.getVendor()) : null)
                .locality(facility.getLocality() != null ? LocalityDTO.fromEntity(facility.getLocality()) : null)
                .build();
    }
}
