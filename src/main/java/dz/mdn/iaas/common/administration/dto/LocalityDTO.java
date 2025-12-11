/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocalityDTO
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
import dz.mdn.iaas.common.administration.model.Locality;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class LocalityDTO extends GenericDTO<Locality> {

    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    private String designationAr; // F_01

    @Size(max = 100, message = "English designation must not exceed 100 characters")
    private String designationEn; // F_02

    @NotBlank(message = "French designation is required")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    private String designationFr; // F_03 - required

    @NotBlank(message = "Code is required")
    @Size(max = 10, message = "Code must not exceed 10 characters")
    private String code; // F_04 - required and unique

    @NotNull(message = "State ID is required")
    private Long stateId; // F_05 - foreign key to State (required)

    // Additional fields for display purposes
    private String stateCode;
    private String stateDesignationFr;
    private String stateDesignationAr;
    private String stateDisplayText;

    @Override
    public Locality toEntity() {
        Locality locality = new Locality();
        locality.setId(getId());
        locality.setDesignationAr(this.designationAr);
        locality.setDesignationEn(this.designationEn);
        locality.setDesignationFr(this.designationFr);
        locality.setCode(this.code);
        // Note: state must be set by service layer using stateId
        return locality;
    }

    @Override
    public void updateEntity(Locality locality) {
        if (this.designationAr != null) {
            locality.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            locality.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            locality.setDesignationFr(this.designationFr);
        }
        if (this.code != null) {
            locality.setCode(this.code);
        }
        // Note: state update must be handled by service layer using stateId
    }

    public static LocalityDTO fromEntity(Locality locality) {
        if (locality == null) return null;
        
        LocalityDTOBuilder builder = LocalityDTO.builder()
                .id(locality.getId())
                .designationAr(locality.getDesignationAr())
                .designationEn(locality.getDesignationEn())
                .designationFr(locality.getDesignationFr())
                .code(locality.getCode());

        // Handle state relationship
        if (locality.getState() != null) {
            builder.stateId(locality.getState().getId())
                   .stateCode(locality.getState().getCode())
                   .stateDesignationFr(locality.getState().getDesignationFr())
                   .stateDesignationAr(locality.getState().getDesignationAr())
                   .stateDisplayText(locality.getState().getCode() + " - " + locality.getState().getDesignationFr());
        }
        
        return builder.build();
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

    public String getDisplayText() {
        return String.format("%s - %s", code, designationFr);
    }

    public String getDisplayTextAr() {
        if (designationAr != null) {
            return String.format("%s - %s", code, designationAr);
        }
        return getDisplayText();
    }

    public String getFullDisplayText() {
        if (stateDisplayText != null) {
            return String.format("%s (%s)", getDisplayText(), stateDisplayText);
        }
        return getDisplayText();
    }

    public String getFullDisplayTextAr() {
        if (stateDesignationAr != null && stateCode != null) {
            return String.format("%s (%s - %s)", getDisplayTextAr(), stateCode, stateDesignationAr);
        }
        return getDisplayTextAr();
    }

    public boolean isComplete() {
        return code != null && !code.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty() &&
               stateId != null;
    }

    public static LocalityDTO createSimple(Long id, String code, String designationFr, Long stateId) {
        return LocalityDTO.builder()
                .id(id)
                .code(code)
                .designationFr(designationFr)
                .stateId(stateId)
                .build();
    }
}