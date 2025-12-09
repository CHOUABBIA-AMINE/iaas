/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CountryDTO
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDTO {

    private Long id; // F_00

    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    private String designationAr; // F_01

    @Size(max = 100, message = "English designation must not exceed 100 characters")
    private String designationEn; // F_02

    @NotBlank(message = "French designation is required")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    private String designationFr; // F_03 - required field

    public static CountryDTO fromEntity(dz.mdn.iaas.common.administration.model.Country country) {
        if (country == null) return null;
        
        return CountryDTO.builder()
                .id(country.getId())
                .designationAr(country.getDesignationAr())
                .designationEn(country.getDesignationEn())
                .designationFr(country.getDesignationFr())
                .build();
    }

    public dz.mdn.iaas.common.administration.model.Country toEntity() {
        dz.mdn.iaas.common.administration.model.Country country = new dz.mdn.iaas.common.administration.model.Country();
        country.setId(this.id);
        country.setDesignationAr(this.designationAr);
        country.setDesignationEn(this.designationEn);
        country.setDesignationFr(this.designationFr);
        return country;
    }

    public void updateEntity(dz.mdn.iaas.common.administration.model.Country country) {
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
