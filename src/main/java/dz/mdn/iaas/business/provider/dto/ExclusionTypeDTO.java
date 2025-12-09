/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ExclusionTypeDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Exclusion Type Data Transfer Object
 * Maps exactly to ExclusionType model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExclusionTypeDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique

    /**
     * Create DTO from entity
     */
    public static ExclusionTypeDTO fromEntity(dz.mdn.iaas.business.provider.model.ExclusionType exclusionType) {
        if (exclusionType == null) return null;
        
        return ExclusionTypeDTO.builder()
                .id(exclusionType.getId())
                .designationAr(exclusionType.getDesignationAr())
                .designationEn(exclusionType.getDesignationEn())
                .designationFr(exclusionType.getDesignationFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public dz.mdn.iaas.business.provider.model.ExclusionType toEntity() {
        dz.mdn.iaas.business.provider.model.ExclusionType exclusionType = 
            new dz.mdn.iaas.business.provider.model.ExclusionType();
        exclusionType.setId(this.id);
        exclusionType.setDesignationAr(this.designationAr);
        exclusionType.setDesignationEn(this.designationEn);
        exclusionType.setDesignationFr(this.designationFr);
        return exclusionType;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(dz.mdn.iaas.business.provider.model.ExclusionType exclusionType) {
        if (this.designationAr != null) {
            exclusionType.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            exclusionType.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            exclusionType.setDesignationFr(this.designationFr);
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
     * Check if exclusion type has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this exclusion type
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
     * Get exclusion type category based on French designation analysis
     */
    public String getExclusionCategory() {
        if (designationFr == null) return "OTHER";
        
        String designation = designationFr.toLowerCase();
        
        // Legal exclusions
        if (designation.contains("judiciaire") || designation.contains("juridique") || designation.contains("tribunal")) {
            return "LEGAL_EXCLUSION";
        }
        if (designation.contains("faillite") || designation.contains("insolvabilité") || designation.contains("liquidation")) {
            return "BANKRUPTCY_EXCLUSION";
        }
        if (designation.contains("criminel") || designation.contains("pénal") || designation.contains("condamnation")) {
            return "CRIMINAL_EXCLUSION";
        }
        
        // Financial exclusions
        if (designation.contains("financier") || designation.contains("crédit") || designation.contains("endettement")) {
            return "FINANCIAL_EXCLUSION";
        }
        if (designation.contains("fiscal") || designation.contains("impôt") || designation.contains("taxe")) {
            return "TAX_EXCLUSION";
        }
        
        // Administrative exclusions
        if (designation.contains("administratif") || designation.contains("réglementaire")) {
            return "ADMINISTRATIVE_EXCLUSION";
        }
        if (designation.contains("licence") || designation.contains("autorisation") || designation.contains("agrément")) {
            return "LICENSE_EXCLUSION";
        }
        
        // Sectoral exclusions
        if (designation.contains("sectoriel") || designation.contains("activité") || designation.contains("domaine")) {
            return "SECTORAL_EXCLUSION";
        }
        
        // Geographical exclusions
        if (designation.contains("géographique") || designation.contains("territorial") || designation.contains("région")) {
            return "GEOGRAPHICAL_EXCLUSION";
        }
        
        // Temporal exclusions
        if (designation.contains("temporaire") || designation.contains("période") || designation.contains("durée")) {
            return "TEMPORAL_EXCLUSION";
        }
        
        // Qualification exclusions
        if (designation.contains("qualification") || designation.contains("compétence") || designation.contains("expérience")) {
            return "QUALIFICATION_EXCLUSION";
        }
        
        // Security exclusions
        if (designation.contains("sécurité") || designation.contains("secret") || designation.contains("confidentiel")) {
            return "SECURITY_EXCLUSION";
        }
        
        // Conflict of interest
        if (designation.contains("conflit") || designation.contains("intérêt") || designation.contains("incompatibilité")) {
            return "CONFLICT_EXCLUSION";
        }
        
        return "OTHER";
    }

    /**
     * Get exclusion severity level
     */
    public String getSeverityLevel() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION", "LEGAL_EXCLUSION" -> "SEVERE";
            case "BANKRUPTCY_EXCLUSION", "FINANCIAL_EXCLUSION" -> "HIGH";
            case "LICENSE_EXCLUSION", "ADMINISTRATIVE_EXCLUSION" -> "MEDIUM";
            case "TAX_EXCLUSION", "SECTORAL_EXCLUSION" -> "MEDIUM";
            case "SECURITY_EXCLUSION", "CONFLICT_EXCLUSION" -> "HIGH";
            case "QUALIFICATION_EXCLUSION", "TEMPORAL_EXCLUSION" -> "LOW";
            case "GEOGRAPHICAL_EXCLUSION" -> "LOW";
            default -> "VARIABLE";
        };
    }

    /**
     * Get exclusion duration type
     */
    public String getDurationType() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION" -> "PERMANENT";
            case "BANKRUPTCY_EXCLUSION" -> "LONG_TERM"; // 5-10 years
            case "FINANCIAL_EXCLUSION", "TAX_EXCLUSION" -> "MEDIUM_TERM"; // 1-5 years
            case "TEMPORAL_EXCLUSION" -> "SHORT_TERM"; // Months to 1 year
            case "LICENSE_EXCLUSION", "ADMINISTRATIVE_EXCLUSION" -> "CONDITIONAL"; // Until resolved
            case "QUALIFICATION_EXCLUSION" -> "CONDITIONAL"; // Until qualified
            case "SECURITY_EXCLUSION" -> "CLASSIFIED"; // Variable
            default -> "VARIABLE";
        };
    }

    /**
     * Get exclusion priority (lower number = higher priority for processing)
     */
    public Integer getExclusionPriority() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "SECURITY_EXCLUSION" -> 1;
            case "CRIMINAL_EXCLUSION" -> 2;
            case "LEGAL_EXCLUSION" -> 3;
            case "BANKRUPTCY_EXCLUSION" -> 4;
            case "CONFLICT_EXCLUSION" -> 5;
            case "FINANCIAL_EXCLUSION" -> 6;
            case "LICENSE_EXCLUSION" -> 7;
            case "TAX_EXCLUSION" -> 8;
            case "ADMINISTRATIVE_EXCLUSION" -> 9;
            case "SECTORAL_EXCLUSION" -> 10;
            case "QUALIFICATION_EXCLUSION" -> 11;
            case "GEOGRAPHICAL_EXCLUSION" -> 12;
            case "TEMPORAL_EXCLUSION" -> 13;
            default -> 99;
        };
    }

    /**
     * Check if exclusion is permanent
     */
    public boolean isPermanentExclusion() {
        String category = getExclusionCategory();
        return "CRIMINAL_EXCLUSION".equals(category) || 
               (designationFr != null && designationFr.toLowerCase().contains("permanent"));
    }

    /**
     * Check if exclusion is conditional (can be resolved)
     */
    public boolean isConditionalExclusion() {
        String durationType = getDurationType();
        return "CONDITIONAL".equals(durationType);
    }

    /**
     * Check if exclusion requires legal review
     */
    public boolean requiresLegalReview() {
        String category = getExclusionCategory();
        return switch (category) {
            case "LEGAL_EXCLUSION", "CRIMINAL_EXCLUSION", "SECURITY_EXCLUSION", 
                 "CONFLICT_EXCLUSION" -> true;
            default -> false;
        };
    }

    /**
     * Check if exclusion affects public contracts
     */
    public boolean affectsPublicContracts() {
        String category = getExclusionCategory();
        return switch (category) {
            case "CRIMINAL_EXCLUSION", "LEGAL_EXCLUSION", "BANKRUPTCY_EXCLUSION", 
                 "TAX_EXCLUSION", "CONFLICT_EXCLUSION" -> true;
            default -> false;
        };
    }

    /**
     * Get appeal authority
     */
    public String getAppealAuthority() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION", "LEGAL_EXCLUSION" -> "JUDICIAL_COURT";
            case "TAX_EXCLUSION" -> "TAX_TRIBUNAL";
            case "ADMINISTRATIVE_EXCLUSION", "LICENSE_EXCLUSION" -> "ADMINISTRATIVE_COURT";
            case "BANKRUPTCY_EXCLUSION" -> "COMMERCIAL_COURT";
            case "SECURITY_EXCLUSION" -> "NATIONAL_SECURITY_COUNCIL";
            case "CONFLICT_EXCLUSION" -> "ETHICS_COMMITTEE";
            default -> "REGULATORY_AUTHORITY";
        };
    }

    /**
     * Get responsible ministry
     */
    public String getResponsibleMinistry() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION", "LEGAL_EXCLUSION" -> "MINISTRY_OF_JUSTICE";
            case "TAX_EXCLUSION", "FINANCIAL_EXCLUSION" -> "MINISTRY_OF_FINANCE";
            case "SECURITY_EXCLUSION" -> "MINISTRY_OF_DEFENSE";
            case "LICENSE_EXCLUSION", "ADMINISTRATIVE_EXCLUSION" -> "MINISTRY_OF_COMMERCE";
            case "SECTORAL_EXCLUSION" -> "SECTOR_MINISTRY";
            default -> "MINISTRY_OF_COMMERCE";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ExclusionTypeDTO createSimple(Long id, String designationFr) {
        return ExclusionTypeDTO.builder()
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
        String text = getDisplayText();
        return text.length() > 50 ? text.substring(0, 50) + "..." : text;
    }

    /**
     * Get full display with all languages and exclusion category
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
        
        String category = getExclusionCategory();
        if (!"OTHER".equals(category)) {
            sb.append(" [").append(category.replace("_", " ")).append("]");
        }
        
        return sb.toString();
    }

    /**
     * Get comparison key for sorting (by priority, then designation)
     */
    public String getComparisonKey() {
        Integer priority = getExclusionPriority();
        return String.format("%02d_%s", priority, designationFr != null ? designationFr.toLowerCase() : "zzz");
    }

    /**
     * Get display with exclusion category
     */
    public String getDisplayWithCategory() {
        String category = getExclusionCategory();
        String categoryDisplay = category.replace("_", " ").toLowerCase();
        return getDisplayText() + " (" + categoryDisplay + ")";
    }

    /**
     * Get display with severity level
     */
    public String getDisplayWithSeverity() {
        String severity = getSeverityLevel();
        return getDisplayText() + " [" + severity + "]";
    }

    /**
     * Get formal exclusion display
     */
    public String getFormalExclusionDisplay() {
        return getDisplayText() + " - " + getExclusionCategory().replace("_", " ");
    }

    /**
     * Get regulatory impact assessment
     */
    public String getRegulatoryImpact() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION", "SECURITY_EXCLUSION" -> "NATIONAL_SECURITY";
            case "LEGAL_EXCLUSION", "BANKRUPTCY_EXCLUSION" -> "LEGAL_COMPLIANCE";
            case "TAX_EXCLUSION", "FINANCIAL_EXCLUSION" -> "FISCAL_COMPLIANCE";
            case "LICENSE_EXCLUSION" -> "REGULATORY_COMPLIANCE";
            case "SECTORAL_EXCLUSION" -> "SECTOR_SPECIFIC";
            case "CONFLICT_EXCLUSION" -> "ETHICS_COMPLIANCE";
            default -> "ADMINISTRATIVE_COMPLIANCE";
        };
    }

    /**
     * Get business impact level
     */
    public String getBusinessImpact() {
        String category = getExclusionCategory();
        String severity = getSeverityLevel();
        
        if ("SEVERE".equals(severity) || "HIGH".equals(severity)) {
            return switch (category) {
                case "CRIMINAL_EXCLUSION", "SECURITY_EXCLUSION" -> "TOTAL_EXCLUSION";
                case "BANKRUPTCY_EXCLUSION", "LEGAL_EXCLUSION" -> "MAJOR_RESTRICTION";
                case "FINANCIAL_EXCLUSION", "TAX_EXCLUSION" -> "FINANCIAL_RESTRICTION";
                default -> "SIGNIFICANT_IMPACT";
            };
        }
        
        return switch (category) {
            case "QUALIFICATION_EXCLUSION" -> "CONDITIONAL_PARTICIPATION";
            case "TEMPORAL_EXCLUSION" -> "TEMPORARY_RESTRICTION";
            case "GEOGRAPHICAL_EXCLUSION" -> "LOCATION_RESTRICTION";
            case "SECTORAL_EXCLUSION" -> "ACTIVITY_RESTRICTION";
            default -> "LIMITED_IMPACT";
        };
    }

    /**
     * Get remediation process
     */
    public String getRemediationProcess() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION" -> "JUDICIAL_REHABILITATION";
            case "LEGAL_EXCLUSION" -> "COURT_CLEARANCE";
            case "BANKRUPTCY_EXCLUSION" -> "FINANCIAL_REHABILITATION";
            case "TAX_EXCLUSION" -> "TAX_CLEARANCE";
            case "FINANCIAL_EXCLUSION" -> "DEBT_SETTLEMENT";
            case "LICENSE_EXCLUSION" -> "LICENSE_RENEWAL";
            case "QUALIFICATION_EXCLUSION" -> "SKILL_CERTIFICATION";
            case "ADMINISTRATIVE_EXCLUSION" -> "ADMINISTRATIVE_COMPLIANCE";
            case "SECURITY_EXCLUSION" -> "SECURITY_CLEARANCE";
            case "CONFLICT_EXCLUSION" -> "ETHICS_CLEARANCE";
            case "TEMPORAL_EXCLUSION" -> "TIME_EXPIRY";
            default -> "COMPLIANCE_VERIFICATION";
        };
    }

    /**
     * Get monitoring requirement
     */
    public String getMonitoringRequirement() {
        String severity = getSeverityLevel();
        
        return switch (severity) {
            case "SEVERE" -> "CONTINUOUS_MONITORING";
            case "HIGH" -> "REGULAR_MONITORING";
            case "MEDIUM" -> "PERIODIC_MONITORING";
            case "LOW" -> "BASIC_MONITORING";
            default -> "NO_MONITORING";
        };
    }

    /**
     * Get notification requirement
     */
    public String getNotificationRequirement() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION", "SECURITY_EXCLUSION" -> "IMMEDIATE_NOTIFICATION";
            case "LEGAL_EXCLUSION", "BANKRUPTCY_EXCLUSION" -> "URGENT_NOTIFICATION";
            case "FINANCIAL_EXCLUSION", "TAX_EXCLUSION" -> "STANDARD_NOTIFICATION";
            case "CONFLICT_EXCLUSION", "LICENSE_EXCLUSION" -> "STANDARD_NOTIFICATION";
            default -> "ROUTINE_NOTIFICATION";
        };
    }

    /**
     * Get documentation requirement
     */
    public String getDocumentationRequirement() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION" -> "CRIMINAL_RECORD_CERTIFICATE";
            case "LEGAL_EXCLUSION" -> "COURT_DECISION_COPY";
            case "BANKRUPTCY_EXCLUSION" -> "BANKRUPTCY_CERTIFICATE";
            case "TAX_EXCLUSION" -> "TAX_CLEARANCE_CERTIFICATE";
            case "FINANCIAL_EXCLUSION" -> "FINANCIAL_STATEMENT";
            case "LICENSE_EXCLUSION" -> "LICENSE_STATUS_CERTIFICATE";
            case "QUALIFICATION_EXCLUSION" -> "QUALIFICATION_CERTIFICATE";
            case "SECURITY_EXCLUSION" -> "SECURITY_CLEARANCE_DOCUMENT";
            default -> "COMPLIANCE_CERTIFICATE";
        };
    }

    /**
     * Get review frequency
     */
    public String getReviewFrequency() {
        String durationType = getDurationType();
        String severity = getSeverityLevel();
        
        if ("PERMANENT".equals(durationType)) {
            return "ANNUAL_REVIEW";
        }
        
        return switch (severity) {
            case "SEVERE" -> "QUARTERLY_REVIEW";
            case "HIGH" -> "SEMI_ANNUAL_REVIEW";
            case "MEDIUM" -> "ANNUAL_REVIEW";
            case "LOW" -> "BIENNIAL_REVIEW";
            default -> "AS_NEEDED";
        };
    }

    /**
     * Get exclusion scope
     */
    public String getExclusionScope() {
        String category = getExclusionCategory();
        
        return switch (category) {
            case "CRIMINAL_EXCLUSION", "SECURITY_EXCLUSION" -> "ALL_PUBLIC_CONTRACTS";
            case "LEGAL_EXCLUSION", "BANKRUPTCY_EXCLUSION" -> "ALL_GOVERNMENT_BUSINESS";
            case "TAX_EXCLUSION", "FINANCIAL_EXCLUSION" -> "TAX_RELATED_CONTRACTS";
            case "LICENSE_EXCLUSION" -> "LICENSED_ACTIVITIES";
            case "SECTORAL_EXCLUSION" -> "SPECIFIC_SECTOR";
            case "GEOGRAPHICAL_EXCLUSION" -> "SPECIFIC_REGION";
            case "QUALIFICATION_EXCLUSION" -> "SKILL_REQUIRED_CONTRACTS";
            default -> "LIMITED_SCOPE";
        };
    }
}
