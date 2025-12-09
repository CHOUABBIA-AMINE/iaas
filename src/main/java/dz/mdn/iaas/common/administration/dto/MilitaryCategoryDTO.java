/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryCategoryDTO
 *	@CreatedOn	: 10-16-2025
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

/**
 * Military Category Data Transfer Object
 * Maps exactly to MilitaryCategory model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=abbreviationAr, F_05=abbreviationEn, F_06=abbreviationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (abbreviationFr) is required
 * F_01, F_02, F_04, F_05 are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MilitaryCategoryDTO {

    private Long id; // F_00

    @Size(max = 50, message = "Arabic designation must not exceed 50 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 50, message = "English designation must not exceed 50 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 50, message = "French designation must not exceed 50 characters")
    private String designationFr; // F_03 - required and unique

    /**
     * Create DTO from entity
     */
    public static MilitaryCategoryDTO fromEntity(dz.mdn.iaas.common.administration.model.MilitaryCategory militaryCategory) {
        if (militaryCategory == null) return null;
        
        return MilitaryCategoryDTO.builder()
                .id(militaryCategory.getId())
                .designationAr(militaryCategory.getDesignationAr())
                .designationEn(militaryCategory.getDesignationEn())
                .designationFr(militaryCategory.getDesignationFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public dz.mdn.iaas.common.administration.model.MilitaryCategory toEntity() {
        dz.mdn.iaas.common.administration.model.MilitaryCategory militaryCategory = 
            new dz.mdn.iaas.common.administration.model.MilitaryCategory();
        militaryCategory.setId(this.id);
        militaryCategory.setDesignationAr(this.designationAr);
        militaryCategory.setDesignationEn(this.designationEn);
        militaryCategory.setDesignationFr(this.designationFr);
        return militaryCategory;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(dz.mdn.iaas.common.administration.model.MilitaryCategory militaryCategory) {
        if (this.designationAr != null) {
            militaryCategory.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            militaryCategory.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            militaryCategory.setDesignationFr(this.designationFr);
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
     * Check if military category has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this military category
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
     * Get military category type based on French designation analysis
     */
    public String getCategoryType() {
        if (designationFr == null) return "OTHER";
        
        String designation = designationFr.toLowerCase();
        
        if (designation.contains("armée") && designation.contains("terre")) {
            return "ARMY";
        }
        if (designation.contains("marine") || designation.contains("naval")) {
            return "NAVY";
        }
        if (designation.contains("air") || designation.contains("aérienne")) {
            return "AIR_FORCE";
        }
        if (designation.contains("gendarmerie")) {
            return "GENDARMERIE";
        }
        if (designation.contains("garde") && designation.contains("républicaine")) {
            return "REPUBLICAN_GUARD";
        }
        if (designation.contains("sécurité")) {
            return "SECURITY";
        }
        if (designation.contains("logistique")) {
            return "LOGISTICS";
        }
        if (designation.contains("médical") || designation.contains("santé")) {
            return "MEDICAL";
        }
        if (designation.contains("communication") || designation.contains("transmission")) {
            return "COMMUNICATIONS";
        }
        if (designation.contains("renseignement")) {
            return "INTELLIGENCE";
        }
        
        return "OTHER";
    }

    /**
     * Get military category priority (lower number = higher priority)
     */
    public Integer getCategoryPriority() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY" -> 1;
            case "NAVY" -> 2;
            case "AIR_FORCE" -> 3;
            case "GENDARMERIE" -> 4;
            case "REPUBLICAN_GUARD" -> 5;
            case "SECURITY" -> 6;
            case "INTELLIGENCE" -> 7;
            case "COMMUNICATIONS" -> 8;
            case "MEDICAL" -> 9;
            case "LOGISTICS" -> 10;
            default -> 99;
        };
    }

    /**
     * Check if category is a main service branch
     */
    public boolean isMainServiceBranch() {
        String type = getCategoryType();
        return "ARMY".equals(type) || "NAVY".equals(type) || "AIR_FORCE".equals(type);
    }

    /**
     * Check if category is a support service
     */
    public boolean isSupportService() {
        String type = getCategoryType();
        return "LOGISTICS".equals(type) || "MEDICAL".equals(type) || "COMMUNICATIONS".equals(type);
    }

    /**
     * Check if category is a security service
     */
    public boolean isSecurityService() {
        String type = getCategoryType();
        return "GENDARMERIE".equals(type) || "REPUBLICAN_GUARD".equals(type) || 
               "SECURITY".equals(type) || "INTELLIGENCE".equals(type);
    }

    /**
     * Get organizational level
     */
    public String getOrganizationalLevel() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY", "NAVY", "AIR_FORCE" -> "SERVICE_BRANCH";
            case "GENDARMERIE", "REPUBLICAN_GUARD" -> "PARAMILITARY";
            case "SECURITY", "INTELLIGENCE" -> "SPECIAL_FORCES";
            case "MEDICAL", "LOGISTICS", "COMMUNICATIONS" -> "SUPPORT_SERVICES";
            default -> "AUXILIARY";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static MilitaryCategoryDTO createSimple(Long id, String designationFr, String abbreviationFr) {
        return MilitaryCategoryDTO.builder()
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
     * Get full display with all languages and category type
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
        
        String categoryType = getCategoryType();
        if (!"OTHER".equals(categoryType)) {
            sb.append(" - ").append(categoryType.replace("_", " "));
        }
        
        return sb.toString();
    }

    /**
     * Get comparison key for sorting (by priority, then designation)
     */
    public String getComparisonKey() {
        Integer priority = getCategoryPriority();
        return String.format("%02d_%s", priority, designationFr != null ? designationFr.toLowerCase() : "zzz");
    }

    /**
     * Get display with category type
     */
    public String getDisplayWithType() {
        String categoryType = getCategoryType();
        String typeDisplay = categoryType.replace("_", " ").toLowerCase();
        return getDisplayText() + " (" + typeDisplay + ")";
    }

    /**
     * Get formal military display
     */
    public String getFormalMilitaryDisplay() {
        return getDisplayText();
    }

    /**
     * Get command structure level
     */
    public String getCommandStructureLevel() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY", "NAVY", "AIR_FORCE" -> "STRATEGIC";
            case "GENDARMERIE", "REPUBLICAN_GUARD" -> "OPERATIONAL";
            case "SECURITY", "INTELLIGENCE" -> "TACTICAL";
            default -> "SUPPORT";
        };
    }

    /**
     * Get personnel size category (estimated)
     */
    public String getPersonnelSizeCategory() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY" -> "LARGE"; // >50,000
            case "NAVY", "AIR_FORCE" -> "MEDIUM"; // 10,000-50,000
            case "GENDARMERIE" -> "MEDIUM";
            case "REPUBLICAN_GUARD" -> "SMALL"; // <10,000
            default -> "SMALL";
        };
    }

    /**
     * Get deployment type
     */
    public String getDeploymentType() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY" -> "GROUND_OPERATIONS";
            case "NAVY" -> "NAVAL_OPERATIONS";
            case "AIR_FORCE" -> "AIR_OPERATIONS";
            case "GENDARMERIE" -> "LAW_ENFORCEMENT";
            case "REPUBLICAN_GUARD" -> "CEREMONIAL_SECURITY";
            case "SECURITY" -> "PROTECTIVE_SERVICES";
            case "INTELLIGENCE" -> "INFORMATION_OPERATIONS";
            case "MEDICAL" -> "HEALTHCARE_SUPPORT";
            case "LOGISTICS" -> "SUPPLY_SUPPORT";
            case "COMMUNICATIONS" -> "SIGNAL_SUPPORT";
            default -> "GENERAL_SUPPORT";
        };
    }

    /**
     * Get typical headquarters location type
     */
    public String getHeadquartersType() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY", "NAVY", "AIR_FORCE" -> "MINISTRY_LEVEL";
            case "GENDARMERIE", "REPUBLICAN_GUARD" -> "NATIONAL_DIRECTORATE";
            case "SECURITY", "INTELLIGENCE" -> "CLASSIFIED_LOCATION";
            default -> "REGIONAL_CENTER";
        };
    }

    /**
     * Get recruitment profile
     */
    public String getRecruitmentProfile() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY" -> "GENERAL_MILITARY";
            case "NAVY" -> "MARITIME_SPECIALIST";
            case "AIR_FORCE" -> "AVIATION_TECHNICAL";
            case "GENDARMERIE" -> "LAW_ENFORCEMENT";
            case "REPUBLICAN_GUARD" -> "ELITE_CEREMONIAL";
            case "SECURITY" -> "PROTECTIVE_SPECIALIST";
            case "INTELLIGENCE" -> "ANALYTICAL_SPECIALIST";
            case "MEDICAL" -> "HEALTHCARE_PROFESSIONAL";
            case "LOGISTICS" -> "SUPPLY_SPECIALIST";
            case "COMMUNICATIONS" -> "TECHNICAL_SPECIALIST";
            default -> "GENERAL_SUPPORT";
        };
    }

    /**
     * Get training emphasis
     */
    public String getTrainingEmphasis() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY" -> "COMBAT_OPERATIONS";
            case "NAVY" -> "MARITIME_WARFARE";
            case "AIR_FORCE" -> "AEROSPACE_OPERATIONS";
            case "GENDARMERIE" -> "LAW_ENFORCEMENT";
            case "REPUBLICAN_GUARD" -> "CEREMONIAL_PROTOCOL";
            case "SECURITY" -> "PROTECTIVE_TACTICS";
            case "INTELLIGENCE" -> "INFORMATION_ANALYSIS";
            case "MEDICAL" -> "MEDICAL_TRAINING";
            case "LOGISTICS" -> "SUPPLY_MANAGEMENT";
            case "COMMUNICATIONS" -> "SIGNAL_OPERATIONS";
            default -> "BASIC_MILITARY";
        };
    }

    /**
     * Get category description
     */
    public String getCategoryDescription() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY" -> "Land-based military forces responsible for ground operations and territorial defense";
            case "NAVY" -> "Maritime military forces responsible for naval operations and coastal defense";
            case "AIR_FORCE" -> "Aviation military forces responsible for air operations and aerospace defense";
            case "GENDARMERIE" -> "Paramilitary law enforcement force with both military and civilian duties";
            case "REPUBLICAN_GUARD" -> "Elite ceremonial and protective force for high-level government security";
            case "SECURITY" -> "Specialized security forces for critical infrastructure and personnel protection";
            case "INTELLIGENCE" -> "Military intelligence services for information gathering and analysis";
            case "MEDICAL" -> "Military medical services providing healthcare support to armed forces";
            case "LOGISTICS" -> "Military supply and logistics services ensuring operational readiness";
            case "COMMUNICATIONS" -> "Military communications and signal services for command and control";
            default -> "Other military organizational units and support services";
        };
    }

    /**
     * Get uniform color scheme (traditional)
     */
    public String getUniformColorScheme() {
        String type = getCategoryType();
        
        return switch (type) {
            case "ARMY" -> "OLIVE_GREEN";
            case "NAVY" -> "NAVY_BLUE";
            case "AIR_FORCE" -> "AIR_FORCE_BLUE";
            case "GENDARMERIE" -> "DARK_BLUE";
            case "REPUBLICAN_GUARD" -> "CEREMONIAL_RED";
            case "SECURITY" -> "BLACK";
            case "INTELLIGENCE" -> "CIVILIAN_DRESS";
            case "MEDICAL" -> "WHITE_CROSS";
            case "LOGISTICS" -> "BROWN";
            case "COMMUNICATIONS" -> "SIGNAL_YELLOW";
            default -> "STANDARD_MILITARY";
        };
    }
}