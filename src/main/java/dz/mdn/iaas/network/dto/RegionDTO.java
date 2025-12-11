/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RegionDTO
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
import dz.mdn.iaas.network.model.Region;
import dz.mdn.iaas.network.model.Zone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Region Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 * - zoneId (F_02) - Zone relationship ID, required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO extends GenericDTO<Region> {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Zone is required")
    private Long zoneId;

    // Nested zone for read operations
    private ZoneDTO zone;

    @Override
    public Region toEntity() {
        Region region = new Region();
        region.setId(getId());
        region.setName(this.name);
        if (this.zoneId != null) {
            Zone zone = new Zone();
            zone.setId(this.zoneId);
            region.setZone(zone);
        }
        return region;
    }

    @Override
    public void updateEntity(Region region) {
        if (this.name != null) {
            region.setName(this.name);
        }
        if (this.zoneId != null) {
            Zone zone = new Zone();
            zone.setId(this.zoneId);
            region.setZone(zone);
        }
    }

    public static RegionDTO fromEntity(Region region) {
        if (region == null) return null;
        
        return RegionDTO.builder()
                .id(region.getId())
                .name(region.getName())
                .zoneId(region.getZone() != null ? region.getZone().getId() : null)
                .zone(region.getZone() != null ? ZoneDTO.fromEntity(region.getZone()) : null)
                .build();
    }
}
