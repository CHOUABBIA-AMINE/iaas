/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocalityDTO
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.administration.model.Locality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalityDTO {

    private Long id; // F_00

    @NotBlank(message = "Code is required")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    private int code; // F_01 - required and unique

    @NotBlank(message = "Arabic designation is required")
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    private String designationAr; // F_02 - required and unique

    @NotBlank(message = "Latin designation is required")
    @Size(max = 100, message = "Latin designation must not exceed 100 characters")
    private String designationLt; // F_03 - required and unique

    @NotNull(message = "State ID is required")
    private Long stateId; // F_04 - foreign key to State (required)

    // Additional fields for display purposes
    private int stateCode;
    private String stateDesignationAr;
    private String stateDesignationLt;
    private String stateDisplayText;

    public static LocalityDTO fromEntity(Locality locality) {
        if (locality == null) return null;
        
        LocalityDTO.LocalityDTOBuilder builder = LocalityDTO.builder()
                .id(locality.getId())
                .code(locality.getCode())
                .designationAr(locality.getDesignationAr())
                .designationLt(locality.getDesignationLt());

        // Handle state relationship
        if (locality.getState() != null) {
            builder.stateId(locality.getState().getId())
                   .stateCode(locality.getState().getCode())
                   .stateDesignationAr(locality.getState().getDesignationAr())
                   .stateDesignationLt(locality.getState().getDesignationLt())
                   .stateDisplayText(locality.getState().getCode() + " - " + locality.getState().getDesignationLt());
        }
        
        return builder.build();
    }

    public Locality toEntity() {
        Locality locality = new Locality();
        locality.setId(this.id);
        locality.setCode(this.code);
        locality.setDesignationAr(this.designationAr);
        locality.setDesignationLt(this.designationLt);
        // Note: state must be set by service layer using stateId
        return locality;
    }

    public void updateEntity(Locality locality) {
        if (this.code != 0) {
            locality.setCode(this.code);
        }
        if (this.designationAr != null) {
            locality.setDesignationAr(this.designationAr);
        }
        if (this.designationLt != null) {
            locality.setDesignationLt(this.designationLt);
        }
        // Note: state update must be handled by service layer using stateId
    }

    public String getDefaultDesignation() {
        return designationLt;
    }

    public String getDesignationByLanguage(String language) {
        if (language == null) return designationLt;
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> designationAr;
            case "lt", "latin", "en", "english" -> designationLt;
            default -> designationLt;
        };
    }

    public String getDisplayText() {
        return String.format("%s - %s", code, designationLt);
    }

    public String getDisplayTextAr() {
        return String.format("%s - %s", code, designationAr);
    }

    public String getFullDisplayText() {
        if (stateDisplayText != null) {
            return String.format("%s (%s)", getDisplayText(), stateDisplayText);
        }
        return getDisplayText();
    }

    public String getFullDisplayTextAr() {
        if (stateDesignationAr != null && stateCode != 0) {
            return String.format("%s (%s - %s)", getDisplayTextAr(), stateCode, stateDesignationAr);
        }
        return getDisplayTextAr();
    }

    public boolean isComplete() {
        return code != 0 &&
               designationAr != null && !designationAr.trim().isEmpty() &&
               designationLt != null && !designationLt.trim().isEmpty() &&
               stateId != null;
    }

    public static LocalityDTO createSimple(Long id, int code, String designationAr, String designationLt, Long stateId) {
        return LocalityDTO.builder()
                .id(id)
                .code(code)
                .designationAr(designationAr)
                .designationLt(designationLt)
                .stateId(stateId)
                .build();
    }
}
