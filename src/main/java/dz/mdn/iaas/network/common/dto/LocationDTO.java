/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocationDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.model.Location;
import dz.mdn.iaas.network.model.Region;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Location Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 * - code (F_02) - unique, required
 * - latitude (F_03) - required
 * - longitude (F_04) - required
 * - regionId (F_05) - Region relationship ID, required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDTO extends GenericDTO<Location> {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @NotNull(message = "Region is required")
    private Long regionId;

    private RegionDTO region;

    @Override
    public Location toEntity() {
        Location location = new Location();
        location.setId(getId());
        location.setName(this.name);
        location.setCode(this.code);
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);
        if (this.regionId != null) {
            Region region = new Region();
            region.setId(this.regionId);
            location.setRegion(region);
        }
        return location;
    }

    @Override
    public void updateEntity(Location location) {
        if (this.name != null) location.setName(this.name);
        if (this.code != null) location.setCode(this.code);
        if (this.latitude != null) location.setLatitude(this.latitude);
        if (this.longitude != null) location.setLongitude(this.longitude);
        if (this.regionId != null) {
            Region region = new Region();
            region.setId(this.regionId);
            location.setRegion(region);
        }
    }

    public static LocationDTO fromEntity(Location location) {
        if (location == null) return null;
        
        return LocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .code(location.getCode())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .regionId(location.getRegion() != null ? location.getRegion().getId() : null)
                .region(location.getRegion() != null ? RegionDTO.fromEntity(location.getRegion()) : null)
                .build();
    }
}
