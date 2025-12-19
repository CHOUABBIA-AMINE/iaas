/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PartnerDTO
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.model.Partner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Partner Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - optional
 * - shortName (F_02) - unique, required
 * - partnerTypeId (F_03) - required
 * - countryId (F_04) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerDTO extends GenericDTO<Partner> {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Short name is required")
    @Size(min = 2, max = 20, message = "Short name must be between 2 and 20 characters")
    private String shortName;

    @NotNull(message = "Partner type ID is required")
    private Long partnerTypeId;

    @NotNull(message = "Country ID is required")
    private Long countryId;

    @Override
    public Partner toEntity() {
        Partner partner = new Partner();
        partner.setId(getId());
        partner.setName(this.name);
        partner.setShortName(this.shortName);
        return partner;
    }

    @Override
    public void updateEntity(Partner partner) {
        if (this.name != null) partner.setName(this.name);
        if (this.shortName != null) partner.setShortName(this.shortName);
    }

    public static PartnerDTO fromEntity(Partner partner) {
        if (partner == null) return null;
        
        return PartnerDTO.builder()
                .id(partner.getId())
                .name(partner.getName())
                .shortName(partner.getShortName())
                .partnerTypeId(partner.getPartnerType() != null ? partner.getPartnerType().getId() : null)
                .countryId(partner.getCountry() != null ? partner.getCountry().getId() : null)
                .build();
    }
}
