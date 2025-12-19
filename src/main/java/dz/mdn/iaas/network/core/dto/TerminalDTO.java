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
        terminal.setDecommissioningDate(getDecommissioningDate());
        terminal.setLocationId(getLocationId());
        terminal.setFacilityTypeId(getFacilityTypeId());
        return terminal;
    }

    @Override
    public void updateEntity(Terminal terminal) {
        super.updateEntity((org.hibernate.mapping.Subclass) terminal);
        if (this.stationTypeId != null) {
            // Station type would be set via relationship
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
                .locationId(terminal.getLocation() != null ? terminal.getLocation().getId() : null)
                .facilityTypeId(terminal.getFacilityType() != null ? terminal.getFacilityType().getId() : null)
                .operationalStatusId(terminal.getOperationalStatus() != null ? terminal.getOperationalStatus().getId() : null)
                .stationTypeId(terminal.getStationType() != null ? terminal.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
