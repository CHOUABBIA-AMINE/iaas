/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FloorDTO
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
public class FloorDTO {

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

    public static FloorDTO fromEntity(dz.mdn.iaas.common.environment.model.Floor floor) {
        if (floor == null) return null;
        
        return FloorDTO.builder()
                .id(floor.getId())
                .code(floor.getCode())
                .designationAr(floor.getDesignationAr())
                .designationEn(floor.getDesignationEn())
                .designationFr(floor.getDesignationFr())
                .build();
    }

    public dz.mdn.iaas.common.environment.model.Floor toEntity() {
        dz.mdn.iaas.common.environment.model.Floor floor = new dz.mdn.iaas.common.environment.model.Floor();
        floor.setId(this.id);
        floor.setCode(this.code);
        floor.setDesignationAr(this.designationAr);
        floor.setDesignationEn(this.designationEn);
        floor.setDesignationFr(this.designationFr);
        return floor;
    }

    public void updateEntity(dz.mdn.iaas.common.environment.model.Floor floor) {
        if (this.code != null) {
            floor.setCode(this.code);
        }
        if (this.designationAr != null) {
            floor.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            floor.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            floor.setDesignationFr(this.designationFr);
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

    public static FloorDTO createSimple(Long id, String code, String designationFr) {
        return FloorDTO.builder()
                .id(id)
                .code(code)
                .designationFr(designationFr)
                .build();
    }

    /**
     * Validate all required fields are present
     */
    public boolean isValid() {
        return code != null && !code.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }

    public String getFloorType() {
        if (code == null && designationFr == null) return "UNKNOWN";
        
        String checkText = (code + " " + designationFr).toLowerCase();
        
        if (checkText.contains("ground") || checkText.contains("rez") || checkText.contains("gf") || 
            checkText.contains("0") || checkText.contains("أرضي")) {
            return "GROUND_FLOOR";
        } else if (checkText.contains("basement") || checkText.contains("sous-sol") || checkText.contains("b") || 
                  checkText.contains("قبو")) {
            return "BASEMENT";
        } else if (checkText.contains("1") || checkText.contains("first") || checkText.contains("premier") || 
                  checkText.contains("أول")) {
            return "FIRST_FLOOR";
        } else if (checkText.matches(".*[2-9].*") || checkText.contains("upper") || checkText.contains("étage")) {
            return "UPPER_FLOOR";
        }
        return "OTHER";
    }

    /**
     * Check if this is a ground floor
     */
    public boolean isGroundFloor() {
        return "GROUND_FLOOR".equals(getFloorType());
    }

    /**
     * Check if this is a basement
     */
    public boolean isBasement() {
        return "BASEMENT".equals(getFloorType());
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return code + " - " + (designationFr != null && designationFr.length() > 30 ? 
                designationFr.substring(0, 30) + "..." : designationFr);
    }
}
