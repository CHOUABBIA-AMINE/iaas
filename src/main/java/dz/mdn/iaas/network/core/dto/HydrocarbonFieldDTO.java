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

import dz.mdn.iaas.common.administration.dto.LocalityDTO;
import dz.mdn.iaas.common.administration.model.Locality;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.common.dto.VendorDTO;
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

    @NotNull(message = "HydrocarbonFieldType type ID is required")
    private Long hydrocarbonFieldTypeId;
    
    private OperationalStatusDTO operationalStatus;
    private VendorDTO vendor;
    private LocalityDTO locality;

    @Builder.Default
    private Set<Long> pipelineIds = new HashSet<>();
	
	/*@Builder.Default
    private Set<Long> partnerIds = new HashSet<>();
	
	@Builder.Default
    private Set<Long> productIds = new HashSet<>();*/

    @Override
    public HydrocarbonField toEntity() {
        HydrocarbonField field = new HydrocarbonField();
        field.setId(getId());
        field.setCode(this.code);
        field.setName(this.name);
        field.setInstallationDate(this.installationDate);
        field.setCommissioningDate(this.commissioningDate);
        field.setDecommissioningDate(this.decommissioningDate);
        field.setPlaceName(this.placeName);
        field.setLatitude(this.latitude);
        field.setLongitude(this.longitude);
        field.setElevation(this.elevation);
        
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
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	field.setLocality(locality);
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
        if (this.placeName != null) field.setPlaceName(this.placeName);
        if (this.latitude != null) field.setLatitude(this.latitude);
        if (this.longitude != null) field.setLongitude(this.longitude);
        if (this.elevation != null) field.setElevation(this.elevation);
        
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
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	field.setLocality(locality);
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
		
		/*Set<Long> partnerIds = new HashSet<>();
        if (field.getPartners() != null) {
            field.getPartners().forEach(p -> partnerIds.add(p.getId()));
        }
		
		Set<Long> productIds = new HashSet<>();
        if (field.getProducts() != null) {
            field.getProducts().forEach(p -> productIds.add(p.getId()));
        }*/
        
        return HydrocarbonFieldDTO.builder()
                .id(field.getId())
                .code(field.getCode())
                .name(field.getName())
                .installationDate(field.getInstallationDate())
                .commissioningDate(field.getCommissioningDate())
                .decommissioningDate(field.getDecommissioningDate())
                .placeName(field.getPlaceName())
                .latitude(field.getLatitude())
                .longitude(field.getLongitude())
                .elevation(field.getElevation())
                
                .operationalStatusId(field.getOperationalStatus() != null ? field.getOperationalStatus().getId() : null)
                .vendorId(field.getVendor() != null ? field.getVendor().getId() : null)
                .localityId(field.getLocality() != null ? field.getLocality().getId() : null)
                .hydrocarbonFieldTypeId(field.getHydrocarbonFieldType() != null ? field.getHydrocarbonFieldType().getId() : null)
                .pipelineIds(pipelineIds)
                //.partnerIds(partnerIds)
                //.productIds(productIds)
                
                .operationalStatus(field.getOperationalStatus() != null ? OperationalStatusDTO.fromEntity(field.getOperationalStatus()) : null)
                .vendor(field.getVendor() != null ? VendorDTO.fromEntity(field.getVendor()) : null)
                .locality(field.getLocality() != null ? LocalityDTO.fromEntity(field.getLocality()) : null)
                .build();
    }
}
