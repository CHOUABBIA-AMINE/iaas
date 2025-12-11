/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CountryDTO
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.common.administration.model.Country;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDTO extends GenericDTO<Country> {

    @NotBlank(message = "Code is required")
    @Size(max = 3, message = "Code must not exceed 3 characters")
    private String code; // F_01

    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    private String designationAr; // F_02

    @Size(max = 100, message = "English designation must not exceed 100 characters")
    private String designationEn; // F_03

    @NotBlank(message = "French designation is required")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    private String designationFr; // F_04 - required field

    @Override
    public Country toEntity() {
        Country country = new Country();
        country.setId(getId());
        country.setCode(this.code);
        country.setDesignationAr(this.designationAr);
        country.setDesignationEn(this.designationEn);
        country.setDesignationFr(this.designationFr);
        return country;
    }

    @Override
    public void updateEntity(Country country) {
        if (this.code != null) {
            country.setCode(this.code);
        }
        if (this.designationAr != null) {
            country.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            country.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            country.setDesignationFr(this.designationFr);
        }
    }

    public static CountryDTO fromEntity(Country country) {
        if (country == null) return null;
        
        return CountryDTO.builder()
                .id(country.getId())
                .code(country.getCode())
                .designationAr(country.getDesignationAr())
                .designationEn(country.getDesignationEn())
                .designationFr(country.getDesignationFr())
                .build();
    }

    public String getDefaultDesignation() {
        return designationFr;
    }

    public String getDesignationByLanguage(String language) {
        if (language == null) return designationFr;
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> designationAr != null ? designationAr : designationFr;
            case "en", "english" -> designationEn != null ? designationEn : designationFr;
            case "fr", "french" -> designationFr;
            default -> designationFr;
        };
    }
}