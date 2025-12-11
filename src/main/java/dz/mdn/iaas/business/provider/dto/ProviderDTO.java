/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.provider.model.Provider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Provider Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO extends GenericDTO<Provider> {

    private int internalId;
    
    @NotBlank(message = "Provider name is required")
    private String name;
    
    private String nameAr;
    private String nameEn;
    
    @NotBlank(message = "Tax identification number is required")
    private String taxIdentificationNumber;
    
    private String commercialRegisterNumber;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    
    @Email(message = "Invalid email format")
    private String email;
    
    private String phone;
    private String fax;
    private String website;
    private Date registrationDate;
    private boolean isActive;
    private String observation;
    
    @NotNull(message = "Economic nature is required")
    private Long economicNatureId;

    @Override
    public Provider toEntity() {
        Provider entity = new Provider();
        entity.setId(this.getId());
        entity.setDesignationLt(this.name);
        entity.setDesignationAr(this.nameAr);
        entity.setAddress(this.address);
        entity.setTaxeIdentityNumber(this.taxIdentificationNumber);
        entity.setComercialRegistryNumber(this.commercialRegisterNumber);
        entity.setPhoneNumbers(this.phone);
        entity.setFaxNumbers(this.fax);
        entity.setMail(this.email);
        entity.setWebsite(this.website);
        entity.setComercialRegistryDate(this.registrationDate);
        return entity;
    }

    @Override
    public void updateEntity(Provider entity) {
        if (this.name != null) entity.setDesignationLt(this.name);
        if (this.nameAr != null) entity.setDesignationAr(this.nameAr);
        if (this.address != null) entity.setAddress(this.address);
        if (this.taxIdentificationNumber != null) entity.setTaxeIdentityNumber(this.taxIdentificationNumber);
        if (this.commercialRegisterNumber != null) entity.setComercialRegistryNumber(this.commercialRegisterNumber);
        if (this.phone != null) entity.setPhoneNumbers(this.phone);
        if (this.fax != null) entity.setFaxNumbers(this.fax);
        if (this.email != null) entity.setMail(this.email);
        if (this.website != null) entity.setWebsite(this.website);
        if (this.registrationDate != null) entity.setComercialRegistryDate(this.registrationDate);
    }
}
