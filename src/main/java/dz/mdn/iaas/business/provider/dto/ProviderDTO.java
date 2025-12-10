/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Provider Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO extends GenericDTO {

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

}
