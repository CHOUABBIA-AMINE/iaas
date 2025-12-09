/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetTypeDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Budget Type Data Transfer Object
 * Maps exactly to BudgetType model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, 
 * F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetTypeDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required, unique

    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    private String acronymAr; // F_04 - optional

    @Size(max = 20, message = "English acronym must not exceed 20 characters")
    private String acronymEn; // F_05 - optional

    @NotBlank(message = "French acronym is required")
    @Size(max = 20, message = "French acronym must not exceed 20 characters")
    private String acronymFr; // F_06 - required, unique

    /**
     * Create DTO from entity
     */
    public static BudgetTypeDTO fromEntity(dz.mdn.iaas.business.plan.model.BudgetType budgetType) {
        if (budgetType == null) return null;
        
        return BudgetTypeDTO.builder()
                .id(budgetType.getId())
                .designationAr(budgetType.getDesignationAr())
                .designationEn(budgetType.getDesignationEn())
                .designationFr(budgetType.getDesignationFr())
                .acronymAr(budgetType.getAcronymAr())
                .acronymEn(budgetType.getAcronymEn())
                .acronymFr(budgetType.getAcronymFr())
                .build();
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
     * Get default acronym (French first, then English, then Arabic)
     */
    public String getDefaultAcronym() {
        if (acronymFr != null && !acronymFr.trim().isEmpty()) {
            return acronymFr;
        }
        if (acronymEn != null && !acronymEn.trim().isEmpty()) {
            return acronymEn;
        }
        if (acronymAr != null && !acronymAr.trim().isEmpty()) {
            return acronymAr;
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
     * Get display acronym
     */
    public String getDisplayAcronym() {
        return getDefaultAcronym();
    }

    /**
     * Check if budget type has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this budget type
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
     * Get budget category based on designation keywords
     */
    public String getBudgetCategory() {
        String designation = getDefaultDesignation().toLowerCase();
        
        // Investment/Capital budget
        if (designation.contains("investissement") || designation.contains("investment") || 
            designation.contains("capital") || designation.contains("équipement") ||
            designation.contains("equipment") || designation.contains("استثمار") ||
            designation.contains("رأسمالي")) {
            return "INVESTMENT_BUDGET";
        }
        
        // Operating budget
        if (designation.contains("fonctionnement") || designation.contains("operating") ||
            designation.contains("operational") || designation.contains("exploitation") ||
            designation.contains("تشغيلي") || designation.contains("تشغيل")) {
            return "OPERATING_BUDGET";
        }
        
        // Personnel budget
        if (designation.contains("personnel") || designation.contains("salaire") ||
            designation.contains("salary") || designation.contains("wages") ||
            designation.contains("رواتب") || designation.contains("أجور")) {
            return "PERSONNEL_BUDGET";
        }
        
        // Maintenance budget
        if (designation.contains("maintenance") || designation.contains("entretien") ||
            designation.contains("réparation") || designation.contains("repair") ||
            designation.contains("صيانة") || designation.contains("إصلاح")) {
            return "MAINTENANCE_BUDGET";
        }
        
        // Research & Development budget
        if (designation.contains("recherche") || designation.contains("research") ||
            designation.contains("développement") || designation.contains("development") ||
            designation.contains("innovation") || designation.contains("بحث") ||
            designation.contains("تطوير")) {
            return "RESEARCH_DEVELOPMENT_BUDGET";
        }
        
        // Defense/Military budget
        if (designation.contains("défense") || designation.contains("defense") ||
            designation.contains("militaire") || designation.contains("military") ||
            designation.contains("sécurité") || designation.contains("security") ||
            designation.contains("دفاع") || designation.contains("عسكري")) {
            return "DEFENSE_BUDGET";
        }
        
        // Training budget
        if (designation.contains("formation") || designation.contains("training") ||
            designation.contains("éducation") || designation.contains("education") ||
            designation.contains("تدريب") || designation.contains("تعليم")) {
            return "TRAINING_BUDGET";
        }
        
        // Emergency/Contingency budget
        if (designation.contains("urgence") || designation.contains("emergency") ||
            designation.contains("contingence") || designation.contains("contingency") ||
            designation.contains("طوارئ") || designation.contains("احتياطي")) {
            return "EMERGENCY_BUDGET";
        }
        
        return "GENERAL_BUDGET";
    }

    /**
     * Get budget priority level based on category and keywords
     */
    public String getBudgetPriority() {
        String category = getBudgetCategory();
        
        switch (category) {
            case "DEFENSE_BUDGET":
            case "EMERGENCY_BUDGET":
                return "CRITICAL_PRIORITY";
            case "PERSONNEL_BUDGET":
            case "OPERATING_BUDGET":
                return "HIGH_PRIORITY";
            case "INVESTMENT_BUDGET":
            case "RESEARCH_DEVELOPMENT_BUDGET":
                return "MEDIUM_PRIORITY";
            case "MAINTENANCE_BUDGET":
            case "TRAINING_BUDGET":
                return "NORMAL_PRIORITY";
            default:
                return "LOW_PRIORITY";
        }
    }

    /**
     * Get budget management scope
     */
    public String getBudgetScope() {
        String designation = getDefaultDesignation().toLowerCase();
        
        if (designation.contains("national") || designation.contains("état") ||
            designation.contains("state") || designation.contains("وطني") ||
            designation.contains("دولة")) {
            return "NATIONAL_SCOPE";
        }
        if (designation.contains("régional") || designation.contains("regional") ||
            designation.contains("إقليمي")) {
            return "REGIONAL_SCOPE";
        }
        if (designation.contains("local") || designation.contains("municipal") ||
            designation.contains("محلي")) {
            return "LOCAL_SCOPE";
        }
        if (designation.contains("projet") || designation.contains("project") ||
            designation.contains("مشروع")) {
            return "PROJECT_SCOPE";
        }
        if (designation.contains("département") || designation.contains("department") ||
            designation.contains("ministère") || designation.contains("ministry") ||
            designation.contains("وزارة") || designation.contains("إدارة")) {
            return "DEPARTMENTAL_SCOPE";
        }
        
        return "ORGANIZATIONAL_SCOPE";
    }

    /**
     * Get budget planning cycle
     */
    public String getBudgetCycle() {
        String category = getBudgetCategory();
        
        switch (category) {
            case "PERSONNEL_BUDGET":
            case "OPERATING_BUDGET":
                return "ANNUAL_CYCLE";
            case "INVESTMENT_BUDGET":
                return "MULTI_YEAR_CYCLE";
            case "EMERGENCY_BUDGET":
                return "AD_HOC_CYCLE";
            case "RESEARCH_DEVELOPMENT_BUDGET":
                return "LONG_TERM_CYCLE";
            case "MAINTENANCE_BUDGET":
                return "QUARTERLY_CYCLE";
            case "TRAINING_BUDGET":
                return "SEMI_ANNUAL_CYCLE";
            default:
                return "ANNUAL_CYCLE";
        }
    }

    /**
     * Check if budget requires approval
     */
    public boolean requiresApproval() {
        String priority = getBudgetPriority();
        return "CRITICAL_PRIORITY".equals(priority) || 
               "HIGH_PRIORITY".equals(priority) ||
               "MEDIUM_PRIORITY".equals(priority);
    }

    /**
     * Get approval level required
     */
    public String getApprovalLevel() {
        String priority = getBudgetPriority();
        String scope = getBudgetScope();
        
        if ("CRITICAL_PRIORITY".equals(priority) && "NATIONAL_SCOPE".equals(scope)) {
            return "MINISTERIAL_APPROVAL";
        }
        if ("CRITICAL_PRIORITY".equals(priority) || "HIGH_PRIORITY".equals(priority)) {
            return "DIRECTORIAL_APPROVAL";
        }
        if ("MEDIUM_PRIORITY".equals(priority)) {
            return "DEPARTMENTAL_APPROVAL";
        }
        
        return "STANDARD_APPROVAL";
    }

    /**
     * Get short display for lists (acronym - designation)
     */
    public String getShortDisplay() {
        String acronym = getDefaultAcronym();
        String designation = getDisplayText();
        return !"N/A".equals(acronym) ? acronym + " - " + designation : designation;
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
        
        String acronym = getDefaultAcronym();
        if (!"N/A".equals(acronym)) {
            sb.append(" (").append(acronym).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get budget display with category and priority
     */
    public String getBudgetDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String acronym = getDefaultAcronym();
        if (!"N/A".equals(acronym)) {
            sb.append(" (").append(acronym).append(")");
        }
        
        String category = getBudgetCategory();
        if (!"GENERAL_BUDGET".equals(category)) {
            sb.append(" - ").append(category.replace("_", " "));
        }
        
        String priority = getBudgetPriority();
        if (!"LOW_PRIORITY".equals(priority)) {
            sb.append(" [").append(priority.replace("_", " ")).append("]");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static BudgetTypeDTO createSimple(Long id, String designationFr, String acronymFr) {
        return BudgetTypeDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .acronymFr(acronymFr)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() && 
               acronymFr != null && !acronymFr.trim().isEmpty();
    }

    /**
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (designationFr == null || designationFr.trim().isEmpty()) {
            errors.add("French designation is required");
        }
        
        if (acronymFr == null || acronymFr.trim().isEmpty()) {
            errors.add("French acronym is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by designation)
     */
    public String getComparisonKey() {
        return getDisplayText().toLowerCase();
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            sb.append(designationFr);
        }
        
        String acronym = getDefaultAcronym();
        if (!"N/A".equals(acronym)) {
            sb.append(" (").append(acronym).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get budget classification for reports
     */
    public String getBudgetClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Budget Type: ").append(getDisplayText()).append("\n");
        sb.append("Acronym: ").append(getDefaultAcronym()).append("\n");
        sb.append("Category: ").append(getBudgetCategory().replace("_", " ")).append("\n");
        sb.append("Priority: ").append(getBudgetPriority().replace("_", " ")).append("\n");
        sb.append("Scope: ").append(getBudgetScope().replace("_", " ")).append("\n");
        sb.append("Planning Cycle: ").append(getBudgetCycle().replace("_", " ")).append("\n");
        sb.append("Requires Approval: ").append(requiresApproval() ? "Yes" : "No").append("\n");
        sb.append("Approval Level: ").append(getApprovalLevel().replace("_", " "));
        
        return sb.toString();
    }

    /**
     * Get budget usage context
     */
    public String getBudgetUsageContext() {
        String category = getBudgetCategory();
        
        return switch (category) {
            case "INVESTMENT_BUDGET" -> "Capital expenditure and asset acquisition";
            case "OPERATING_BUDGET" -> "Day-to-day operational expenses";
            case "PERSONNEL_BUDGET" -> "Salaries, wages, and personnel costs";
            case "MAINTENANCE_BUDGET" -> "Equipment and facility maintenance";
            case "RESEARCH_DEVELOPMENT_BUDGET" -> "Innovation and development projects";
            case "DEFENSE_BUDGET" -> "Military and defense operations";
            case "TRAINING_BUDGET" -> "Education and skill development";
            case "EMERGENCY_BUDGET" -> "Crisis response and contingency funds";
            default -> "General organizational expenses";
        };
    }

    /**
     * Get budget monitoring frequency
     */
    public String getMonitoringFrequency() {
        String priority = getBudgetPriority();
        
        return switch (priority) {
            case "CRITICAL_PRIORITY" -> "DAILY_MONITORING";
            case "HIGH_PRIORITY" -> "WEEKLY_MONITORING";
            case "MEDIUM_PRIORITY" -> "MONTHLY_MONITORING";
            case "NORMAL_PRIORITY" -> "QUARTERLY_MONITORING";
            default -> "ANNUAL_MONITORING";
        };
    }

    /**
     * Get budget allocation method
     */
    public String getAllocationMethod() {
        String category = getBudgetCategory();
        
        return switch (category) {
            case "PERSONNEL_BUDGET" -> "FIXED_ALLOCATION";
            case "OPERATING_BUDGET" -> "PERCENTAGE_BASED";
            case "INVESTMENT_BUDGET" -> "PROJECT_BASED";
            case "EMERGENCY_BUDGET" -> "ON_DEMAND";
            case "RESEARCH_DEVELOPMENT_BUDGET" -> "MILESTONE_BASED";
            default -> "STANDARD_ALLOCATION";
        };
    }

    /**
     * Get budget variance tolerance
     */
    public String getVarianceTolerance() {
        String priority = getBudgetPriority();
        
        return switch (priority) {
            case "CRITICAL_PRIORITY" -> "LOW_TOLERANCE"; // 2-5%
            case "HIGH_PRIORITY" -> "MEDIUM_TOLERANCE"; // 5-10%
            case "MEDIUM_PRIORITY" -> "STANDARD_TOLERANCE"; // 10-15%
            default -> "HIGH_TOLERANCE"; // 15-25%
        };
    }

    /**
     * Get budget reporting requirements
     */
    public String getReportingRequirements() {
        String scope = getBudgetScope();
        String priority = getBudgetPriority();
        
        StringBuilder sb = new StringBuilder();
        
        // Frequency based on priority
        switch (priority) {
            case "CRITICAL_PRIORITY":
                sb.append("Daily reports, Weekly summaries, Monthly analysis");
                break;
            case "HIGH_PRIORITY":
                sb.append("Weekly reports, Monthly summaries");
                break;
            case "MEDIUM_PRIORITY":
                sb.append("Monthly reports, Quarterly analysis");
                break;
            default:
                sb.append("Quarterly reports, Annual analysis");
        }
        
        // Additional requirements based on scope
        if ("NATIONAL_SCOPE".equals(scope)) {
            sb.append(", Parliamentary oversight");
        } else if ("REGIONAL_SCOPE".equals(scope)) {
            sb.append(", Regional authority oversight");
        }
        
        return sb.toString();
    }

    /**
     * Get budget control measures
     */
    public String getBudgetControlMeasures() {
        String priority = getBudgetPriority();
        
        StringBuilder sb = new StringBuilder();
        
        // Standard controls
        sb.append("Expenditure tracking, Budget vs actual analysis");
        
        // Additional controls based on priority
        switch (priority) {
            case "CRITICAL_PRIORITY":
                sb.append(", Real-time monitoring, Executive approval for variances");
                break;
            case "HIGH_PRIORITY":
                sb.append(", Weekly variance analysis, Departmental approval required");
                break;
            case "MEDIUM_PRIORITY":
                sb.append(", Monthly variance review, Supervisory approval");
                break;
        }
        
        return sb.toString();
    }
}
