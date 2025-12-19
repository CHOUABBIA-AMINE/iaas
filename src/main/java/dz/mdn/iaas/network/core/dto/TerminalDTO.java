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

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.network.core.model.Terminal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Terminal Data Transfer Object - Extends FacilityDTO
 * Inherits all fields from parent Facility class
 * 
 * Inheritance chain: GenericDTO -> FacilityDTO -> TerminalDTO
 * Inherits from Facility: code, name, installationDate, commissioningDate, decommissioningDate,
 *                        operationalStatusId, locationId, facilityTypeId, vendorId
 * 
 * Additional Fields:
 * - stationTypeId - Type of terminal (required)
 * - pipelineIds - Associated pipelines
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TerminalDTO extends FacilityDTO {

    @NotNull(message = "Station type ID is required")
    private Long stationTypeId;

    @SuperBuilder.Default
    private Set<Long> pipelineIds = new HashSet<>();

    @Override
    public Terminal toEntity() {
        Terminal terminal = new Terminal();
        terminal.setId(getId());
        terminal.setCode(getCode());
        terminal.setName(getName());
        terminal.setInstallationDate(getInstallationDate());
        terminal.setCommissioningDate(getCommissioningDate());
        terminal.setRetirementDate(getRetirementDate());
        terminal.setProvider(getProvider());
        
        // Set relationships from parent DTO
        if (getOperationalStatusId() != null) {
            dz.mdn.iaas.network.common.model.OperationalStatus status = 
                new dz.mdn.iaas.network.common.model.OperationalStatus();
            status.setId(getOperationalStatusId());
            terminal.setOperationalStatus(status);
        }
        
        if (getLocationId() != null) {
            dz.mdn.iaas.network.common.model.Location location = 
                new dz.mdn.iaas.network.common.model.Location();
            location.setId(getLocationId());
            terminal.setLocation(location);
        }
        
        if (getVendorId() != null) {
            dz.mdn.iaas.network.common.model.Vendor vendor = 
                new dz.mdn.iaas.network.common.model.Vendor();
            vendor.setId(getVendorId());
            terminal.setVendor(vendor);
        }
        
        return terminal;
    }

    @Override
    public void updateEntity(Terminal terminal) {
        super.updateEntity((Facility) terminal);
        // Additional update logic for Terminal-specific fields can go here
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
                .retirementDate(terminal.getRetirementDate())
                .provider(terminal.getProvider())
                .operationalStatusId(terminal.getOperationalStatus() != null ? terminal.getOperationalStatus().getId() : null)
                .locationId(terminal.getLocation() != null ? terminal.getLocation().getId() : null)
                .vendorId(terminal.getVendor() != null ? terminal.getVendor().getId() : null)
                .stationTypeId(terminal.getStationType() != null ? terminal.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
