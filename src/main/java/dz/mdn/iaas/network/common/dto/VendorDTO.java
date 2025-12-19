/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: VendorDTO
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
import dz.mdn.iaas.network.common.model.Vendor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Vendor Data Transfer Object - Extends GenericDTO
 * Maps Vendor entity extending GenericModel
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - optional name
 * - shortName (F_02) - optional identifier
 * - vendorTypeId (F_03) - required foreign key to VendorType (unique)
 * - countryId (F_04) - required foreign key to Country
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendorDTO extends GenericDTO<Vendor> {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(min = 2, max = 20, message = "Short name must be between 2 and 20 characters")
    private String shortName;

    @NotNull(message = "Vendor type ID is required")
    private Long vendorTypeId;

    @NotNull(message = "Country ID is required")
    private Long countryId;

    @Override
    public Vendor toEntity() {
        Vendor vendor = new Vendor();
        vendor.setId(getId());
        vendor.setName(this.name);
        vendor.setShortName(this.shortName);
        
        if (this.vendorTypeId != null) {
            dz.mdn.iaas.network.type.model.VendorType type = 
                new dz.mdn.iaas.network.type.model.VendorType();
            type.setId(this.vendorTypeId);
            vendor.setVendorType(type);
        }
        
        if (this.countryId != null) {
            dz.mdn.iaas.common.administration.model.Country country = 
                new dz.mdn.iaas.common.administration.model.Country();
            country.setId(this.countryId);
            vendor.setCountry(country);
        }
        
        return vendor;
    }

    @Override
    public void updateEntity(Vendor vendor) {
        if (this.name != null) vendor.setName(this.name);
        if (this.shortName != null) vendor.setShortName(this.shortName);
        
        if (this.vendorTypeId != null) {
            dz.mdn.iaas.network.type.model.VendorType type = 
                new dz.mdn.iaas.network.type.model.VendorType();
            type.setId(this.vendorTypeId);
            vendor.setVendorType(type);
        }
        
        if (this.countryId != null) {
            dz.mdn.iaas.common.administration.model.Country country = 
                new dz.mdn.iaas.common.administration.model.Country();
            country.setId(this.countryId);
            vendor.setCountry(country);
        }
    }

    public static VendorDTO fromEntity(Vendor vendor) {
        if (vendor == null) return null;
        
        return VendorDTO.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .shortName(vendor.getShortName())
                .vendorTypeId(vendor.getVendorType() != null ? vendor.getVendorType().getId() : null)
                .countryId(vendor.getCountry() != null ? vendor.getCountry().getId() : null)
                .build();
    }
}
