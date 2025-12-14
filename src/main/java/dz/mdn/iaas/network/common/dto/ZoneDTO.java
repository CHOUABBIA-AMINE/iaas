/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ZoneDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.model.Zone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Zone Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZoneDTO extends GenericDTO<Zone> {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;
    
    @NotBlank(message = "Code is required")
    @Size(max = 10, message = "Code must not exceed 10 characters")
    private String code;
    
    @NotBlank(message = "Decsription is required")
    @Size(max = 100, message = "Decsription must not exceed 100 characters")
    private String decsription;

    @Override
    public Zone toEntity() {
        Zone zone = new Zone();
        zone.setId(getId());
        zone.setName(this.name);
        zone.setCode(this.code);
        zone.setDecsription(this.decsription);
        return zone;
    }

    @Override
    public void updateEntity(Zone zone) {
        if (this.name != null) {
            zone.setName(this.name);
        }
        if (this.code != null) {
            zone.setCode(this.code);
        }
        if (this.decsription != null) {
            zone.setDecsription(this.decsription);
        }
    }

    public static ZoneDTO fromEntity(Zone zone) {
        if (zone == null) return null;
        
        return ZoneDTO.builder()
                .id(zone.getId())
                .name(zone.getName())
                .code(zone.getCode())
                .decsription(zone.getDecsription())
                .build();
    }
}
