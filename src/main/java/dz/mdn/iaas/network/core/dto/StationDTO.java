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

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.network.core.model.Station;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Station Data Transfer Object - Extends FacilityDTO
 * Inherits all fields from parent Facility class
 * 
 * Additional Fields:
 * - stationTypeId - Type of station (required)
 * - pipelineIds - Associated pipelines
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationDTO extends FacilityDTO {

    @NotNull(message = "Station type ID is required")
    private Long stationTypeId;

    @SuperBuilder.Default
    private Set<Long> pipelineIds = new HashSet<>();

    @Override
    public Station toEntity() {
        Station station = new Station();
        station.setId(getId());
        station.setCode(getCode());
        station.setName(getName());
        station.setInstallationDate(getInstallationDate());
        station.setCommissioningDate(getCommissioningDate());
        station.setDecommissioningDate(getDecommissioningDate());
        station.setLocationId(getLocationId());
        station.setFacilityTypeId(getFacilityTypeId());
        return station;
    }

    @Override
    public void updateEntity(Station station) {
        super.updateEntity((org.hibernate.mapping.Subclass) station);
        if (this.stationTypeId != null) {
            // Station type would be set via relationship
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
                .locationId(station.getLocation() != null ? station.getLocation().getId() : null)
                .facilityTypeId(station.getFacilityType() != null ? station.getFacilityType().getId() : null)
                .operationalStatusId(station.getOperationalStatus() != null ? station.getOperationalStatus().getId() : null)
                .stationTypeId(station.getStationType() != null ? station.getStationType().getId() : null)
                .pipelineIds(pipelineIds)
                .build();
    }
}
