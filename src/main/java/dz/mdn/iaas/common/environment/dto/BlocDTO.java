/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BlocDTO
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.dto;

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
public class BlocDTO {

    private Long id; // F_00

    @NotBlank(message = "Arabic code is required")
    @Size(max = 20, message = "Arabic code must not exceed 20 characters")
    private String codeAr; // F_01 - required and unique

    @NotBlank(message = "Latin code is required")
    @Size(max = 20, message = "Latin code must not exceed 20 characters")
    private String codeLt; // F_02 - required and unique

    public static BlocDTO fromEntity(dz.mdn.iaas.common.environment.model.Bloc bloc) {
        if (bloc == null) return null;
        
        return BlocDTO.builder()
                .id(bloc.getId())
                .codeAr(bloc.getCodeAr())
                .codeLt(bloc.getCodeLt())
                .build();
    }

    public dz.mdn.iaas.common.environment.model.Bloc toEntity() {
        dz.mdn.iaas.common.environment.model.Bloc bloc = new dz.mdn.iaas.common.environment.model.Bloc();
        bloc.setId(this.id);
        bloc.setCodeAr(this.codeAr);
        bloc.setCodeLt(this.codeLt);
        return bloc;
    }

    public void updateEntity(dz.mdn.iaas.common.environment.model.Bloc bloc) {
        if (this.codeAr != null) {
            bloc.setCodeAr(this.codeAr);
        }
        if (this.codeLt != null) {
            bloc.setCodeLt(this.codeLt);
        }
    }

    public String getCodeByLanguage(String language) {
        if (language == null) return codeLt;
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> codeAr;
            case "lt", "latin", "en", "english", "fr", "french" -> codeLt;
            default -> codeLt;
        };
    }

    public String getDisplayText() {

        return codeLt;
    }

    public String getFullDisplayText() {
        return String.format("%s (%s) - %s", codeLt, codeAr, getDisplayText());
    }

    public String[] getAvailableLanguages() {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (codeAr != null && !codeAr.trim().isEmpty()) {
            languages.add("arabic_code");
        }
        if (codeLt != null && !codeLt.trim().isEmpty()) {
            languages.add("latin_code");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    public static BlocDTO createSimple(Long id, String codeLt, String codeAr, String designationFr) {
        return BlocDTO.builder()
                .id(id)
                .codeLt(codeLt)
                .codeAr(codeAr)
                .build();
    }

    public boolean isValid() {
        return codeAr != null && !codeAr.trim().isEmpty() &&
               codeLt != null && !codeLt.trim().isEmpty();
    }

    public String getShortDisplay() {
        return codeLt;
    }
}
