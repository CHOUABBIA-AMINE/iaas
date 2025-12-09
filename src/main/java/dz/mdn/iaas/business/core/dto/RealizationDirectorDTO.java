/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationDirectorDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RealizationDirector Data Transfer Object
 * Maps exactly to RealizationDirector model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealizationDirectorDTO {

    private Long id; // F_00

    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 300, message = "English designation must not exceed 300 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    private String designationFr; // F_03 - required and unique

    /**
     * Create DTO from entity
     */
    public static RealizationDirectorDTO fromEntity(dz.mdn.iaas.business.core.model.RealizationDirector realizationDirector) {
        if (realizationDirector == null) return null;
        
        return RealizationDirectorDTO.builder()
                .id(realizationDirector.getId())
                .designationAr(realizationDirector.getDesignationAr())
                .designationEn(realizationDirector.getDesignationEn())
                .designationFr(realizationDirector.getDesignationFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public dz.mdn.iaas.business.core.model.RealizationDirector toEntity() {
        dz.mdn.iaas.business.core.model.RealizationDirector realizationDirector = new dz.mdn.iaas.business.core.model.RealizationDirector();
        realizationDirector.setId(this.id);
        realizationDirector.setDesignationAr(this.designationAr);
        realizationDirector.setDesignationEn(this.designationEn);
        realizationDirector.setDesignationFr(this.designationFr);
        return realizationDirector;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(dz.mdn.iaas.business.core.model.RealizationDirector realizationDirector) {
        if (this.designationAr != null) {
            realizationDirector.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            realizationDirector.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            realizationDirector.setDesignationFr(this.designationFr);
        }
    }

    /**
     * Get default designation (French as it's required)
     */
    public String getDefaultDesignation() {
        return designationFr;
    }

    /**
     * Get designation by language preference
     */
    public String getDesignationByLanguage(String language) {
        if (language == null) return designationFr;
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> designationAr != null ? designationAr : designationFr;
            case "en", "english" -> designationEn != null ? designationEn : designationFr;
            case "fr", "french" -> designationFr;
            default -> designationFr;
        };
    }

    /**
     * Get display text with priority: French designation > English designation > Arabic designation
     */
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
        return "N/A";
    }

    /**
     * Check if realization director has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this realization director
     */
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

    /**
     * Get director type based on French designation analysis
     */
    public String getDirectorType() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Executive level directors
        if (designation.contains("directeur général") || designation.contains("dg") || 
            designation.contains("ceo") || designation.contains("président")) {
            return "EXECUTIVE_DIRECTOR";
        }
        
        // Technical directors
        if (designation.contains("technique") || designation.contains("technical") || 
            designation.contains("ingénieur") || designation.contains("engineer")) {
            return "TECHNICAL_DIRECTOR";
        }
        
        // Project directors
        if (designation.contains("projet") || designation.contains("project") || 
            designation.contains("programme") || designation.contains("program")) {
            return "PROJECT_DIRECTOR";
        }
        
        // Operations directors
        if (designation.contains("opération") || designation.contains("operations") || 
            designation.contains("exploitation") || designation.contains("production")) {
            return "OPERATIONS_DIRECTOR";
        }
        
        // Financial directors
        if (designation.contains("financier") || designation.contains("financial") || 
            designation.contains("comptable") || designation.contains("finance")) {
            return "FINANCIAL_DIRECTOR";
        }
        
        // Commercial/Sales directors
        if (designation.contains("commercial") || designation.contains("vente") || 
            designation.contains("sales") || designation.contains("marketing")) {
            return "COMMERCIAL_DIRECTOR";
        }
        
        // Human resources directors
        if (designation.contains("ressources humaines") || designation.contains("rh") || 
            designation.contains("human resources") || designation.contains("hr")) {
            return "HR_DIRECTOR";
        }
        
        // Quality directors
        if (designation.contains("qualité") || designation.contains("quality") || 
            designation.contains("qhse") || designation.contains("assurance")) {
            return "QUALITY_DIRECTOR";
        }
        
        // Regional directors
        if (designation.contains("régional") || designation.contains("regional") || 
            designation.contains("zone") || designation.contains("territorial")) {
            return "REGIONAL_DIRECTOR";
        }
        
        // Administrative directors
        if (designation.contains("administratif") || designation.contains("administrative") || 
            designation.contains("administration") || designation.contains("admin")) {
            return "ADMINISTRATIVE_DIRECTOR";
        }
        
        return "GENERAL_DIRECTOR";
    }

    /**
     * Check if this is an executive level director
     */
    public boolean isExecutiveLevel() {
        String type = getDirectorType();
        return "EXECUTIVE_DIRECTOR".equals(type) || "GENERAL_DIRECTOR".equals(type);
    }

    /**
     * Check if this is a technical director
     */
    public boolean isTechnicalDirector() {
        return "TECHNICAL_DIRECTOR".equals(getDirectorType());
    }

    /**
     * Check if this is a project director
     */
    public boolean isProjectDirector() {
        return "PROJECT_DIRECTOR".equals(getDirectorType());
    }

    /**
     * Get director level based on type
     */
    public String getDirectorLevel() {
        return switch (getDirectorType()) {
            case "EXECUTIVE_DIRECTOR" -> "EXECUTIVE";
            case "GENERAL_DIRECTOR" -> "SENIOR";
            case "TECHNICAL_DIRECTOR", "FINANCIAL_DIRECTOR", "OPERATIONS_DIRECTOR" -> "SENIOR";
            case "PROJECT_DIRECTOR", "COMMERCIAL_DIRECTOR", "HR_DIRECTOR" -> "MIDDLE";
            case "QUALITY_DIRECTOR", "REGIONAL_DIRECTOR", "ADMINISTRATIVE_DIRECTOR" -> "MIDDLE";
            default -> "STANDARD";
        };
    }

    /**
     * Get director priority for organizational hierarchy
     */
    public int getDirectorPriority() {
        return switch (getDirectorType()) {
            case "EXECUTIVE_DIRECTOR" -> 1;
            case "GENERAL_DIRECTOR" -> 2;
            case "TECHNICAL_DIRECTOR" -> 3;
            case "FINANCIAL_DIRECTOR" -> 4;
            case "OPERATIONS_DIRECTOR" -> 5;
            case "PROJECT_DIRECTOR" -> 6;
            case "COMMERCIAL_DIRECTOR" -> 7;
            case "HR_DIRECTOR" -> 8;
            case "QUALITY_DIRECTOR" -> 9;
            case "REGIONAL_DIRECTOR" -> 10;
            case "ADMINISTRATIVE_DIRECTOR" -> 11;
            default -> 12;
        };
    }

    /**
     * Get organizational department
     */
    public String getDepartment() {
        return switch (getDirectorType()) {
            case "EXECUTIVE_DIRECTOR", "GENERAL_DIRECTOR" -> "EXECUTIVE";
            case "TECHNICAL_DIRECTOR" -> "TECHNICAL";
            case "FINANCIAL_DIRECTOR" -> "FINANCE";
            case "OPERATIONS_DIRECTOR" -> "OPERATIONS";
            case "PROJECT_DIRECTOR" -> "PROJECTS";
            case "COMMERCIAL_DIRECTOR" -> "COMMERCIAL";
            case "HR_DIRECTOR" -> "HUMAN_RESOURCES";
            case "QUALITY_DIRECTOR" -> "QUALITY";
            case "REGIONAL_DIRECTOR" -> "REGIONAL";
            case "ADMINISTRATIVE_DIRECTOR" -> "ADMINISTRATION";
            default -> "GENERAL";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static RealizationDirectorDTO createSimple(Long id, String designationFr) {
        return RealizationDirectorDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty();
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return designationFr != null && designationFr.length() > 50 ? 
                designationFr.substring(0, 50) + "..." : designationFr;
    }

    /**
     * Get full display with all languages
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(designationFr);
        
        if (designationEn != null && !designationEn.equals(designationFr)) {
            sb.append(" / ").append(designationEn);
        }
        
        if (designationAr != null) {
            sb.append(" / ").append(designationAr);
        }
        
        return sb.toString();
    }

    /**
     * Get comparison key for sorting (by French designation)
     */
    public String getComparisonKey() {
        return designationFr != null ? designationFr.toLowerCase() : "";
    }

    /**
     * Get director initials
     */
    public String getInitials() {
        if (designationFr == null || designationFr.trim().isEmpty()) return "";
        
        String[] words = designationFr.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty() && Character.isLetter(word.charAt(0))) {
                initials.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        
        return initials.toString();
    }

    /**
     * Get formal title for official documents
     */
    public String getFormalTitle() {
        if (designationFr == null) return "Directeur";
        
        // Ensure proper French title formatting
        String title = designationFr.trim();
        if (!title.toLowerCase().startsWith("directeur") && 
            !title.toLowerCase().startsWith("président") &&
            !title.toLowerCase().startsWith("chef")) {
            return "Directeur " + title;
        }
        return title;
    }

    /**
     * Check if director has authority level
     */
    public boolean hasHighAuthority() {
        String level = getDirectorLevel();
        return "EXECUTIVE".equals(level) || "SENIOR".equals(level);
    }

    /**
     * Get display with type and level
     */
    public String getDisplayWithType() {
        return designationFr + " (" + getDirectorType().replace("_", " ").toLowerCase() + ")";
    }
}
