/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailTypeDTO
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.communication.model.MailType;
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
public class MailTypeDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique

    public static MailTypeDTO fromEntity(MailType mailType) {
        if (mailType == null) return null;
        
        return MailTypeDTO.builder()
                .id(mailType.getId())
                .designationAr(mailType.getDesignationAr())
                .designationEn(mailType.getDesignationEn())
                .designationFr(mailType.getDesignationFr())
                .build();
    }

    public MailType toEntity() {
        MailType mailType = new MailType();
        mailType.setId(this.id);
        mailType.setDesignationAr(this.designationAr);
        mailType.setDesignationEn(this.designationEn);
        mailType.setDesignationFr(this.designationFr);
        return mailType;
    }

    public void updateEntity(MailType mailType) {
        if (this.designationAr != null) {
            mailType.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            mailType.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            mailType.setDesignationFr(this.designationFr);
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
        return "MailType #" + id;
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

    public static MailTypeDTO createSimple(Long id, String designationFr) {
        return MailTypeDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .build();
    }

    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty();
    }

    public String getCategory() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        if (designation.contains("entrant") || designation.contains("incoming")) {
            return "INCOMING";
        } else if (designation.contains("sortant") || designation.contains("outgoing")) {
            return "OUTGOING";
        } else if (designation.contains("interne") || designation.contains("internal")) {
            return "INTERNAL";
        }
        return "OTHER";
    }

    public boolean isIncoming() {
        return "INCOMING".equals(getCategory());
    }

    public boolean isOutgoing() {
        return "OUTGOING".equals(getCategory());
    }

    public boolean isInternal() {
        return "INTERNAL".equals(getCategory());
    }
}
