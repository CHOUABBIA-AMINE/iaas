/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailNatureDTO
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.communication.model.MailNature;
import jakarta.validation.constraints.NotBlank;
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
public class MailNatureDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique

    public static MailNatureDTO fromEntity(MailNature mailNature) {
        if (mailNature == null) return null;
        
        return MailNatureDTO.builder()
                .id(mailNature.getId())
                .designationAr(mailNature.getDesignationAr())
                .designationEn(mailNature.getDesignationEn())
                .designationFr(mailNature.getDesignationFr())
                .build();
    }

    public dz.mdn.iaas.common.communication.model.MailNature toEntity() {
        MailNature mailNature = new MailNature();
        mailNature.setId(this.id);
        mailNature.setDesignationAr(this.designationAr);
        mailNature.setDesignationEn(this.designationEn);
        mailNature.setDesignationFr(this.designationFr);
        return mailNature;
    }

    public void updateEntity(MailNature mailNature) {
        if (this.designationAr != null) {
            mailNature.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            mailNature.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            mailNature.setDesignationFr(this.designationFr);
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

    public String getDisplayText() {
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            return designationFr;
        }
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            return designationEn;
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            return designationAr;
        }
        return "MailNature #" + id;
    }

    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    public String[] getAvailableLanguages() {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            languages.add("arabic");
        }
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            languages.add("english");
        }
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            languages.add("french");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    public static MailNatureDTO createSimple(Long id, String designationFr) {
        return MailNatureDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .build();
    }

    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty();
    }
}
