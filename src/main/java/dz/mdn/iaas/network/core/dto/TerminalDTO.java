/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: TerminalDTO
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
import dz.mdn.iaas.network.core.model.Terminal;
import dz.mdn.iaas.network.type.dto.TerminalTypeDTO;
import dz.mdn.iaas.network.type.model.TerminalType;
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
 * Terminal Data Transfer Object - Extends GenericDTO
 * Maps Terminal entity which extends Facility which extends Infrastructure
 * 
 * Inherited from Infrastructure:
 * - code, name, installationDate, commissioningDate, decommissioningDate, operationalStatusId
 * 
 * Inherited from Facility:
 * - vendor, location (vendorId, locationId)
 * 
 * Terminal specific:
 * - stationType, pipelines
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TerminalDTO extends GenericDTO<Terminal> {

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

    @NotNull(message = "provider is required")
    private Long vendorId;

    @NotNull(message = "Locality is required")
    private Long localityId;

    @NotNull(message = "Station type ID is required")
    private Long terminalTypeId;
    
    private OperationalStatusDTO operationalStatus;
    private VendorDTO vendor;
    private LocalityDTO locality;
    private TerminalTypeDTO terminalType;

    @Builder.Default
    private Set<Long> pipelineIds = new HashSet<>();

    @Override
    public Terminal toEntity() {
        Terminal terminal = new Terminal();
        terminal.setId(getId());
        terminal.setCode(this.code);
        terminal.setName(this.name);
        terminal.setInstallationDate(this.installationDate);
        terminal.setCommissioningDate(this.commissioningDate);
        terminal.setDecommissioningDate(this.decommissioningDate);
        terminal.setPlaceName(this.placeName);
        terminal.setLatitude(this.latitude);
        terminal.setLongitude(this.longitude);
        terminal.setElevation(this.elevation);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            terminal.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
        	Vendor vendor = new Vendor();
        	vendor.setId(this.vendorId);
        	terminal.setVendor(vendor);
        }
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	terminal.setLocality(locality);
        }
        
        if (this.terminalTypeId != null) {
        	TerminalType terminalType = new TerminalType();
            terminalType.setId(this.terminalTypeId);
            terminal.setTerminalType(terminalType);
        }
        
        return terminal;
    }

    @Override
    public void updateEntity(Terminal terminal) {
        if (this.code != null) terminal.setCode(this.code);
        if (this.name != null) terminal.setName(this.name);
        if (this.installationDate != null) terminal.setInstallationDate(this.installationDate);
        if (this.commissioningDate != null) terminal.setCommissioningDate(this.commissioningDate);
        if (this.decommissioningDate != null) terminal.setDecommissioningDate(this.decommissioningDate);        
        if (this.placeName != null) terminal.setPlaceName(this.placeName);
        if (this.latitude != null) terminal.setLatitude(this.latitude);
        if (this.longitude != null) terminal.setLongitude(this.longitude);
        if (this.elevation != null) terminal.setElevation(this.elevation);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            terminal.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
        	Vendor vendor = new Vendor();
        	vendor.setId(this.vendorId);
        	terminal.setVendor(vendor);
        }
        
        if (this.localityId != null) {
        	Locality locality = new Locality();
        	locality.setId(this.localityId);
        	terminal.setLocality(locality);
        }
        
        if (this.terminalTypeId != null) {
        	TerminalType terminalType = new TerminalType();
            terminalType.setId(this.terminalTypeId);
            terminal.setTerminalType(terminalType);
        }
    }

    public static TerminalDTO fromEntity(Terminal terminal) {
        if (terminal == null) return null;
        
        Set<Long> pipelineIds = new HashSet<>();
        if (terminal.getPipelines() != null) {
            terminal.getPipelines().forEach(p -> pipelineIds.add(p.getId()));
        }
        
        return TerminalDTO.builder()
                .id(terminal.getId())
                .code(terminal.getCode())
                .name(terminal.getName())
                .installationDate(terminal.getInstallationDate())
                .commissioningDate(terminal.getCommissioningDate())
                .decommissioningDate(terminal.getDecommissioningDate())
                .placeName(terminal.getPlaceName())
                .latitude(terminal.getLatitude())
                .longitude(terminal.getLongitude())
                .elevation(terminal.getElevation())
                .operationalStatusId(terminal.getOperationalStatus() != null ? terminal.getOperationalStatus().getId() : null)
                .vendorId(terminal.getVendor() != null ? terminal.getVendor().getId() : null)
                .localityId(terminal.getLocality() != null ? terminal.getLocality().getId() : null)
                .terminalTypeId(terminal.getTerminalType() != null ? terminal.getTerminalType().getId() : null)
                .pipelineIds(pipelineIds)
                
                .operationalStatus(terminal.getOperationalStatus() != null ? OperationalStatusDTO.fromEntity(terminal.getOperationalStatus()) : null)
                .vendor(terminal.getVendor() != null ? VendorDTO.fromEntity(terminal.getVendor()) : null)
                .locality(terminal.getLocality() != null ? LocalityDTO.fromEntity(terminal.getLocality()) : null)
                .terminalType(terminal.getTerminalType() != null ? TerminalTypeDTO.fromEntity(terminal.getTerminalType()) : null)
                .build();
    }
}
