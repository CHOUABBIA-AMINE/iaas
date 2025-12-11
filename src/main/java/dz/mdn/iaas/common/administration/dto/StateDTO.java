/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StateDTO
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
import dz.mdn.iaas.common.administration.model.State;
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
public class StateDTO extends GenericDTO<State> {

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

    @NotNull(message = "Country ID is required")
    private Long countryId; // F_05 - foreign key to Country (required)

    // Additional fields for display purposes
    private String countryCode;
    private String countryDesignationFr;
    private String countryDisplayText;

    @Override
    public State toEntity() {
        State state = new State();
        state.setId(getId());
        state.setDesignationAr(this.designationAr);
        state.setDesignationEn(this.designationEn);
        state.setDesignationFr(this.designationFr);
        state.setCode(this.code);
        // Note: country must be set by service layer using countryId
        return state;
    }

    @Override
    public void updateEntity(State state) {
        if (this.designationAr != null) {
            state.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            state.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            state.setDesignationFr(this.designationFr);
        }
        if (this.code != null) {
            state.setCode(this.code);
        }
        // Note: country update must be handled by service layer using countryId
    }

    public static StateDTO fromEntity(State state) {
        if (state == null) return null;
        
        StateDTOBuilder builder = StateDTO.builder()
                .id(state.getId())
                .designationAr(state.getDesignationAr())
                .designationEn(state.getDesignationEn())
                .designationFr(state.getDesignationFr())
                .code(state.getCode());

        // Handle country relationship
        if (state.getCountry() != null) {
            builder.countryId(state.getCountry().getId())
                   .countryCode(state.getCountry().getCode())
                   .countryDesignationFr(state.getCountry().getDesignationFr())
                   .countryDisplayText(state.getCountry().getCode() + " - " + state.getCountry().getDesignationFr());
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
        if (countryDisplayText != null) {
            return String.format("%s (%s)", getDisplayText(), countryDisplayText);
        }
        return getDisplayText();
    }

    public boolean isComplete() {
        return code != null && !code.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty() &&
               countryId != null;
    }
}