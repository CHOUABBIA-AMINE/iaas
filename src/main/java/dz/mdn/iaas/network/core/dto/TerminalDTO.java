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

import dz.mdn.iaas.network.core.model.Terminal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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

    @NotNull(message = "Operational status ID is required")
    private Long operationalStatusId;

    // Facility fields
    @NotNull(message = "Vendor ID is required")
    private Long vendorId;

    @NotNull(message = "Location ID is required")
    private Long locationId;

    // Terminal specific fields
    @NotNull(message = "Station type ID is required")
    private Long stationTypeId;

    @SuperBuilder.Default
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
        
        if (this.operationalStatusId != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(this.operationalStatusId);
            terminal.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(this.vendorId);
            terminal.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(this.locationId);
            terminal.setLocation(location);
        }
        
        if (this.stationTypeId != null) {
            dz.mdn.iaas.network.type.model.StationType stationType = 
                new dz.mdn.iaas.network.type.model.StationType();
            stationType.setId(this.stationTypeId);
            terminal.setStationType(stationType);
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
        
        if (this.operationalStatusId != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(this.operationalStatusId);
            terminal.setOperationalStatus(status);
        }
        
        if (this.vendorId != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(this.vendorId);
            terminal.setVendor(vendor);
        }
        
        if (this.locationId != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(this.locationId);
            terminal.setLocation(location);
        }
        
        if (this.stationTypeId != null) {
            dz.mdn.iaas.network.type.model.StationType stationType = 
                new dz.mdn.iaas.network.type.model.StationType();
            stationType.setId(this.stationTypeId);
            terminal.setStationType(stationType);
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
                .operationalStatusId(terminal.getOperationalStatus() != null ? terminal.getOperationalStatus().getId() : null)
                .vendorId(terminal.getVendor() != null ? terminal.getVendor().getId() : null)
                .locationId(terminal.getLocation() != null ? terminal.getLocation().getId() : null)
                .stationTypeId(terminal.getStationType() != null ? terminal.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
