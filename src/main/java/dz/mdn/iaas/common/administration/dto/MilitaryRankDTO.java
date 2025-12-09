/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRankDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Military Rank Data Transfer Object
 * Maps exactly to MilitaryRank model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, 
 * F_04=abbreviationAr, F_05=abbreviationEn, F_06=abbreviationFr, F_07=militaryCategoryId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MilitaryRankDTO {

    private Long id; // F_00

    @Size(max = 50, message = "Arabic designation must not exceed 50 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 50, message = "English designation must not exceed 50 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 50, message = "French designation must not exceed 50 characters")
    private String designationFr; // F_03 - required, unique

    @Size(max = 10, message = "Arabic abbreviation must not exceed 10 characters")
    private String abbreviationAr; // F_04 - optional

    @Size(max = 10, message = "English abbreviation must not exceed 10 characters")
    private String abbreviationEn; // F_05 - optional

    @NotBlank(message = "French abbreviation is required")
    @Size(max = 10, message = "French abbreviation must not exceed 10 characters")
    private String abbreviationFr; // F_06 - required

    @NotNull(message = "Military category is required")
    private Long militaryCategoryId; // F_07 - MilitaryCategory foreign key (required)

    // Related entity DTO for display (populated when needed)
    private MilitaryCategoryDTO militaryCategory;

    /**
     * Create DTO from entity
     */
    public static MilitaryRankDTO fromEntity(dz.mdn.iaas.common.administration.model.MilitaryRank militaryRank) {
        if (militaryRank == null) return null;
        
        MilitaryRankDTO.MilitaryRankDTOBuilder builder = MilitaryRankDTO.builder()
                .id(militaryRank.getId())
                .designationAr(militaryRank.getDesignationAr())
                .designationEn(militaryRank.getDesignationEn())
                .designationFr(militaryRank.getDesignationFr())
                .abbreviationAr(militaryRank.getAbbreviationAr())
                .abbreviationEn(militaryRank.getAbbreviationEn())
                .abbreviationFr(militaryRank.getAbbreviationFr());

        // Handle foreign key relationship
        if (militaryRank.getMilitaryCategory() != null) {
            builder.militaryCategoryId(militaryRank.getMilitaryCategory().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static MilitaryRankDTO fromEntityWithRelations(dz.mdn.iaas.common.administration.model.MilitaryRank militaryRank) {
        MilitaryRankDTO dto = fromEntity(militaryRank);
        if (dto == null) return null;

        // Populate related DTOs
        if (militaryRank.getMilitaryCategory() != null) {
            dto.setMilitaryCategory(MilitaryCategoryDTO.fromEntity(militaryRank.getMilitaryCategory()));
        }

        return dto;
    }

    /**
     * Get default designation (French first, then English, then Arabic)
     */
    public String getDefaultDesignation() {
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
     * Get default abbreviation (French first, then English, then Arabic)
     */
    public String getDefaultAbbreviation() {
        if (abbreviationFr != null && !abbreviationFr.trim().isEmpty()) {
            return abbreviationFr;
        }
        if (abbreviationEn != null && !abbreviationEn.trim().isEmpty()) {
            return abbreviationEn;
        }
        if (abbreviationAr != null && !abbreviationAr.trim().isEmpty()) {
            return abbreviationAr;
        }
        return "N/A";
    }

    /**
     * Get display text with priority: French designation > English designation > Arabic designation
     */
    public String getDisplayText() {
        return getDefaultDesignation();
    }

    /**
     * Get display abbreviation
     */
    public String getDisplayAbbreviation() {
        return getDefaultAbbreviation();
    }

    /**
     * Check if military rank has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this military rank
     */
    public String[] getAvailableLanguages() {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            languages.add("french");
        }
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            languages.add("english");
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            languages.add("arabic");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    /**
     * Get rank level based on military category and designation
     */
    public String getRankLevel() {
        if (militaryCategory != null) {
            //String categoryType = militaryCategory.getCategoryType();
            String designation = getDefaultDesignation().toLowerCase();
            
            // Analyze rank level based on designation keywords
            if (designation.contains("général") || designation.contains("general") || 
                designation.contains("amiral") || designation.contains("admiral") ||
                designation.contains("عميد") || designation.contains("لواء")) {
                return "GENERAL_OFFICER";
            }
            if (designation.contains("colonel") || designation.contains("عقيد") ||
                designation.contains("capitaine de vaisseau") || designation.contains("نقيب")) {
                return "SENIOR_OFFICER";
            }
            if (designation.contains("commandant") || designation.contains("major") ||
                designation.contains("capitaine") || designation.contains("captain") ||
                designation.contains("lieutenant") || designation.contains("ملازم")) {
                return "COMPANY_OFFICER";
            }
            if (designation.contains("sous-officier") || designation.contains("sergent") ||
                designation.contains("adjudant") || designation.contains("رقيب")) {
                return "NON_COMMISSIONED_OFFICER";
            }
            if (designation.contains("soldat") || designation.contains("matelot") ||
                designation.contains("جندي") || designation.contains("بحار")) {
                return "ENLISTED";
            }
        }
        return "UNKNOWN_RANK";
    }

    /**
     * Get rank precedence (lower number = higher rank)
     */
    public Integer getRankPrecedence() {
        String level = getRankLevel();
        
        switch (level) {
            case "GENERAL_OFFICER":
                // Further analyze for specific general ranks
                String designation = getDefaultDesignation().toLowerCase();
                if (designation.contains("général de corps d'armée") || designation.contains("فريق")) return 1;
                if (designation.contains("général de division") || designation.contains("لواء")) return 2;
                if (designation.contains("général de brigade") || designation.contains("عميد")) return 3;
                return 1; // Default for general officers
                
            case "SENIOR_OFFICER":
                return 10;
                
            case "COMPANY_OFFICER":
                String companyDesignation = getDefaultDesignation().toLowerCase();
                if (companyDesignation.contains("commandant") || companyDesignation.contains("major")) return 20;
                if (companyDesignation.contains("capitaine")) return 21;
                if (companyDesignation.contains("lieutenant")) return 22;
                return 20;
                
            case "NON_COMMISSIONED_OFFICER":
                return 30;
                
            case "ENLISTED":
                return 40;
                
            default:
                return 99;
        }
    }

    /**
     * Get military service branch based on category
     */
    public String getServiceBranch() {
        if (militaryCategory != null) {
            String categoryDesignation = militaryCategory.getDefaultDesignation().toLowerCase();
            
            if (categoryDesignation.contains("terre") || categoryDesignation.contains("army") || 
                categoryDesignation.contains("جيش") || categoryDesignation.contains("برية")) {
                return "ARMY";
            }
            if (categoryDesignation.contains("marine") || categoryDesignation.contains("navy") ||
                categoryDesignation.contains("بحرية")) {
                return "NAVY";
            }
            if (categoryDesignation.contains("air") || categoryDesignation.contains("جوية")) {
                return "AIR_FORCE";
            }
            if (categoryDesignation.contains("gendarmerie") || categoryDesignation.contains("درك")) {
                return "GENDARMERIE";
            }
        }
        return "UNKNOWN_BRANCH";
    }

    /**
     * Get rank authority level
     */
    public String getAuthorityLevel() {
        String level = getRankLevel();
        
        return switch (level) {
            case "GENERAL_OFFICER" -> "STRATEGIC_COMMAND";
            case "SENIOR_OFFICER" -> "OPERATIONAL_COMMAND";
            case "COMPANY_OFFICER" -> "TACTICAL_COMMAND";
            case "NON_COMMISSIONED_OFFICER" -> "SUPERVISORY";
            case "ENLISTED" -> "OPERATIONAL";
            default -> "UNDEFINED";
        };
    }

    /**
     * Check if rank can command units
     */
    public boolean canCommandUnits() {
        String level = getRankLevel();
        return "GENERAL_OFFICER".equals(level) || 
               "SENIOR_OFFICER".equals(level) || 
               "COMPANY_OFFICER".equals(level);
    }

    /**
     * Check if rank is commissioned officer
     */
    public boolean isCommissionedOfficer() {
        String level = getRankLevel();
        return "GENERAL_OFFICER".equals(level) || 
               "SENIOR_OFFICER".equals(level) || 
               "COMPANY_OFFICER".equals(level);
    }

    /**
     * Get short display for lists (abbreviation - designation)
     */
    public String getShortDisplay() {
        String abbr = getDefaultAbbreviation();
        String designation = getDisplayText();
        return !"N/A".equals(abbr) ? abbr + " - " + designation : designation;
    }

    /**
     * Get full display with all available information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            sb.append(designationFr);
        }
        
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" / ");
            sb.append(designationEn);
        }
        
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" / ");
            sb.append(designationAr);
        }
        
        String abbr = getDefaultAbbreviation();
        if (!"N/A".equals(abbr)) {
            sb.append(" (").append(abbr).append(")");
        }
        
        if (militaryCategory != null) {
            sb.append(" - ").append(militaryCategory.getDisplayText());
        }
        
        return sb.toString();
    }

    /**
     * Get military display with rank level and service branch
     */
    public String getMilitaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String abbr = getDefaultAbbreviation();
        if (!"N/A".equals(abbr)) {
            sb.append(" (").append(abbr).append(")");
        }
        
        String branch = getServiceBranch();
        if (!"UNKNOWN_BRANCH".equals(branch)) {
            sb.append(" - ").append(branch.replace("_", " "));
        }
        
        String level = getRankLevel();
        if (!"UNKNOWN_RANK".equals(level)) {
            sb.append(" [").append(level.replace("_", " ")).append("]");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static MilitaryRankDTO createSimple(Long id, String designationFr, String abbreviationFr) {
        return MilitaryRankDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .abbreviationFr(abbreviationFr)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() && 
               abbreviationFr != null && !abbreviationFr.trim().isEmpty() &&
               militaryCategoryId != null;
    }

    /**
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (designationFr == null || designationFr.trim().isEmpty()) {
            errors.add("French designation is required");
        }
        
        if (abbreviationFr == null || abbreviationFr.trim().isEmpty()) {
            errors.add("French abbreviation is required");
        }
        
        if (militaryCategoryId == null) {
            errors.add("Military category is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by precedence, then by designation)
     */
    public String getComparisonKey() {
        Integer precedence = getRankPrecedence();
        String designation = getDisplayText();
        return String.format("%03d_%s", precedence, designation.toLowerCase());
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            sb.append(designationFr);
        }
        
        String abbr = getDefaultAbbreviation();
        if (!"N/A".equals(abbr)) {
            sb.append(" (").append(abbr).append(")");
        }
        
        if (militaryCategory != null) {
            sb.append(" - ").append(militaryCategory.getFormalMilitaryDisplay());
        }
        
        return sb.toString();
    }

    /**
     * Get rank classification for reports
     */
    public String getRankClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Rank: ").append(getDisplayText()).append("\n");
        sb.append("Abbreviation: ").append(getDefaultAbbreviation()).append("\n");
        sb.append("Level: ").append(getRankLevel().replace("_", " ")).append("\n");
        sb.append("Authority: ").append(getAuthorityLevel().replace("_", " ")).append("\n");
        sb.append("Service Branch: ").append(getServiceBranch().replace("_", " ")).append("\n");
        sb.append("Precedence: ").append(getRankPrecedence()).append("\n");
        sb.append("Command Authority: ").append(canCommandUnits() ? "Yes" : "No").append("\n");
        sb.append("Commissioned: ").append(isCommissionedOfficer() ? "Yes" : "No");
        
        return sb.toString();
    }

    /**
     * Get promotion eligibility based on rank level
     */
    public String getPromotionEligibility() {
        String level = getRankLevel();
        Integer precedence = getRankPrecedence();
        
        if ("ENLISTED".equals(level)) {
            return "ELIGIBLE_FOR_NCO";
        }
        if ("NON_COMMISSIONED_OFFICER".equals(level)) {
            return "ELIGIBLE_FOR_OFFICER";
        }
        if ("COMPANY_OFFICER".equals(level)) {
            return "ELIGIBLE_FOR_SENIOR_OFFICER";
        }
        if ("SENIOR_OFFICER".equals(level)) {
            return "ELIGIBLE_FOR_GENERAL";
        }
        if ("GENERAL_OFFICER".equals(level) && precedence > 1) {
            return "ELIGIBLE_FOR_HIGHER_GENERAL";
        }
        
        return "MAX_RANK_REACHED";
    }

    /**
     * Get operational responsibility scope
     */
    public String getResponsibilityScope() {
        String level = getRankLevel();
        
        return switch (level) {
            case "GENERAL_OFFICER" -> "Army/Division/Brigade command and strategic planning";
            case "SENIOR_OFFICER" -> "Regiment/Battalion command and operational planning";
            case "COMPANY_OFFICER" -> "Company/Platoon command and tactical operations";
            case "NON_COMMISSIONED_OFFICER" -> "Squad/Section leadership and training";
            case "ENLISTED" -> "Individual specialist tasks and support";
            default -> "Scope undefined";
        };
    }

    /**
     * Get typical unit command based on rank
     */
    public String getTypicalUnitCommand() {
        String designation = getDefaultDesignation().toLowerCase();
        
        if (designation.contains("général de corps") || designation.contains("فريق")) {
            return "CORPS";
        }
        if (designation.contains("général de division") || designation.contains("لواء")) {
            return "DIVISION";
        }
        if (designation.contains("général de brigade") || designation.contains("عميد")) {
            return "BRIGADE";
        }
        if (designation.contains("colonel") || designation.contains("عقيد")) {
            return "REGIMENT";
        }
        if (designation.contains("commandant") || designation.contains("major")) {
            return "BATTALION";
        }
        if (designation.contains("capitaine") && !designation.contains("vaisseau")) {
            return "COMPANY";
        }
        if (designation.contains("lieutenant")) {
            return "PLATOON";
        }
        
        return "NONE";
    }

    /**
     * Get equivalent civilian rank for comparison
     */
    public String getCivilianEquivalent() {
        String level = getRankLevel();
        
        return switch (level) {
            case "GENERAL_OFFICER" -> "EXECUTIVE_DIRECTOR";
            case "SENIOR_OFFICER" -> "DEPARTMENT_DIRECTOR";
            case "COMPANY_OFFICER" -> "DIVISION_MANAGER";
            case "NON_COMMISSIONED_OFFICER" -> "SECTION_SUPERVISOR";
            case "ENLISTED" -> "SPECIALIST_EMPLOYEE";
            default -> "NO_EQUIVALENT";
        };
    }

    /**
     * Get years of service requirement estimate
     */
    public String getServiceRequirement() {
        String level = getRankLevel();
        
        return switch (level) {
            case "GENERAL_OFFICER" -> "25-30+ years";
            case "SENIOR_OFFICER" -> "18-25 years";
            case "COMPANY_OFFICER" -> "8-18 years";
            case "NON_COMMISSIONED_OFFICER" -> "4-12 years";
            case "ENLISTED" -> "0-6 years";
            default -> "Variable";
        };
    }

    /**
     * Check if rank requires military academy graduation
     */
    public boolean requiresAcademyGraduation() {
        return isCommissionedOfficer();
    }

    /**
     * Get security clearance level typically required
     */
    public String getSecurityClearanceLevel() {
        String level = getRankLevel();
        
        return switch (level) {
            case "GENERAL_OFFICER" -> "TOP_SECRET";
            case "SENIOR_OFFICER" -> "SECRET";
            case "COMPANY_OFFICER" -> "CONFIDENTIAL";
            case "NON_COMMISSIONED_OFFICER" -> "RESTRICTED";
            case "ENLISTED" -> "UNCLASSIFIED";
            default -> "UNCLASSIFIED";
        };
    }
}