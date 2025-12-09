/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorDTO
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
public class ShelfFloorDTO {

    private Long id; // F_00

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code; // F_01 - required and unique

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_02 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_03 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_04 - required and unique

    public static ShelfFloorDTO fromEntity(dz.mdn.iaas.common.environment.model.ShelfFloor shelfFloor) {
        if (shelfFloor == null) return null;
        
        return ShelfFloorDTO.builder()
                .id(shelfFloor.getId())
                .code(shelfFloor.getCode())
                .designationAr(shelfFloor.getDesignationAr())
                .designationEn(shelfFloor.getDesignationEn())
                .designationFr(shelfFloor.getDesignationFr())
                .build();
    }

    public dz.mdn.iaas.common.environment.model.ShelfFloor toEntity() {
        dz.mdn.iaas.common.environment.model.ShelfFloor shelfFloor = new dz.mdn.iaas.common.environment.model.ShelfFloor();
        shelfFloor.setId(this.id);
        shelfFloor.setCode(this.code);
        shelfFloor.setDesignationAr(this.designationAr);
        shelfFloor.setDesignationEn(this.designationEn);
        shelfFloor.setDesignationFr(this.designationFr);
        return shelfFloor;
    }

    public void updateEntity(dz.mdn.iaas.common.environment.model.ShelfFloor shelfFloor) {
        if (this.code != null) {
            shelfFloor.setCode(this.code);
        }
        if (this.designationAr != null) {
            shelfFloor.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            shelfFloor.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            shelfFloor.setDesignationFr(this.designationFr);
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
        return code;
    }

    public String getDisplayTextWithCode() {
        return code + " - " + getDisplayText();
    }

    public String getFullDisplayText() {
        return String.format("%s - %s", code, designationFr);
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
        
        if (code != null && !code.trim().isEmpty()) {
            languages.add("code");
        }
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

    public static ShelfFloorDTO createSimple(Long id, String code, String designationFr) {
        return ShelfFloorDTO.builder()
                .id(id)
                .code(code)
                .designationFr(designationFr)
                .build();
    }

    public boolean isValid() {
        return code != null && !code.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }

    public String getShelfLevel() {
        if (code == null && designationFr == null) return "UNKNOWN";
        
        String checkText = (code + " " + designationFr).toLowerCase();
        
        if (checkText.contains("top") || checkText.contains("haut") || checkText.contains("upper") || 
            checkText.contains("5") || checkText.contains("علوي")) {
            return "TOP_SHELF";
        } else if (checkText.contains("middle") || checkText.contains("milieu") || checkText.contains("center") || 
                  checkText.contains("3") || checkText.contains("4") || checkText.contains("وسط")) {
            return "MIDDLE_SHELF";
        } else if (checkText.contains("bottom") || checkText.contains("bas") || checkText.contains("lower") || 
                  checkText.contains("1") || checkText.contains("2") || checkText.contains("سفلي")) {
            return "BOTTOM_SHELF";
        } else if (checkText.contains("eye") || checkText.contains("niveau") || checkText.contains("sight") || 
                  checkText.contains("نظر")) {
            return "EYE_LEVEL";
        }
        return "OTHER";
    }

    public boolean isTopLevel() {
        return "TOP_SHELF".equals(getShelfLevel());
    }

    public boolean isBottomLevel() {
        return "BOTTOM_SHELF".equals(getShelfLevel());
    }

    public boolean isEyeLevel() {
        return "EYE_LEVEL".equals(getShelfLevel());
    }

    public String getAccessibilityRating() {
        return switch (getShelfLevel()) {
            case "EYE_LEVEL" -> "HIGH";
            case "MIDDLE_SHELF" -> "MEDIUM";
            case "BOTTOM_SHELF" -> "LOW";
            case "TOP_SHELF" -> "LOW";
            default -> "UNKNOWN";
        };
    }

    public String getShortDisplay() {
        return code + " - " + (designationFr != null && designationFr.length() > 30 ? 
                designationFr.substring(0, 30) + "..." : designationFr);
    }

    public Integer getLevelNumber() {
        if (code == null) return null;
        
        // Try to extract number from code
        try {
            String numericPart = code.replaceAll("[^0-9]", "");
            if (!numericPart.isEmpty()) {
                return Integer.parseInt(numericPart);
            }
        } catch (NumberFormatException e) {
            // Ignore and return null
        }
        
        return null;
    }
}
