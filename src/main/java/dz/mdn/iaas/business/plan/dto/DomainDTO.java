/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DomainDTO
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

import java.util.List;
import java.util.ArrayList;

/**
 * Domain Data Transfer Object
 * Maps exactly to Domain model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * Includes one-to-many relationship with Rubrics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DomainDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required, unique

    // Related entities (populated when needed)
    private List<RubricDTO> rubrics; // One-to-many relationship

    // Summary information
    private Integer rubricsCount;

    /**
     * Create DTO from entity
     */
    public static DomainDTO fromEntity(dz.mdn.iaas.business.plan.model.Domain domain) {
        if (domain == null) return null;
        
        DomainDTO.DomainDTOBuilder builder = DomainDTO.builder()
                .id(domain.getId())
                .designationAr(domain.getDesignationAr())
                .designationEn(domain.getDesignationEn())
                .designationFr(domain.getDesignationFr());

        // Add rubrics count if available
        if (domain.getRubrics() != null) {
            builder.rubricsCount(domain.getRubrics().size());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static DomainDTO fromEntityWithRelations(dz.mdn.iaas.business.plan.model.Domain domain) {
        DomainDTO dto = fromEntity(domain);
        if (dto == null) return null;

        // Populate related DTOs
        if (domain.getRubrics() != null && !domain.getRubrics().isEmpty()) {
            List<RubricDTO> rubricDTOs = new ArrayList<>();
            for (var rubric : domain.getRubrics()) {
                rubricDTOs.add(RubricDTO.fromEntity(rubric));
            }
            dto.setRubrics(rubricDTOs);
            dto.setRubricsCount(rubricDTOs.size());
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
     * Get display text with priority: French designation > English designation > Arabic designation
     */
    public String getDisplayText() {
        return getDefaultDesignation();
    }

    /**
     * Check if domain has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this domain
     */
    public String[] getAvailableLanguages() {
        List<String> languages = new ArrayList<>();
        
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
     * Get domain category based on designation keywords
     */
    public String getDomainCategory() {
        String designation = getDefaultDesignation().toLowerCase();
        
        // Technical domains
        if (designation.contains("technique") || designation.contains("technical") ||
            designation.contains("technologie") || designation.contains("technology") ||
            designation.contains("ingénierie") || designation.contains("engineering") ||
            designation.contains("تقني") || designation.contains("تكنولوجيا")) {
            return "TECHNICAL_DOMAIN";
        }
        
        // Administrative domains
        if (designation.contains("administratif") || designation.contains("administrative") ||
            designation.contains("administration") || designation.contains("gestion") ||
            designation.contains("management") || designation.contains("إداري") ||
            designation.contains("إدارة")) {
            return "ADMINISTRATIVE_DOMAIN";
        }
        
        // Operational domains
        if (designation.contains("opérationnel") || designation.contains("operational") ||
            designation.contains("opération") || designation.contains("operation") ||
            designation.contains("mission") || designation.contains("تشغيلي") ||
            designation.contains("عملياتي")) {
            return "OPERATIONAL_DOMAIN";
        }
        
        // Strategic domains
        if (designation.contains("stratégique") || designation.contains("strategic") ||
            designation.contains("stratégie") || designation.contains("strategy") ||
            designation.contains("planification") || designation.contains("planning") ||
            designation.contains("استراتيجي") || designation.contains("تخطيط")) {
            return "STRATEGIC_DOMAIN";
        }
        
        // Financial domains
        if (designation.contains("financier") || designation.contains("financial") ||
            designation.contains("finance") || designation.contains("budget") ||
            designation.contains("économique") || designation.contains("economic") ||
            designation.contains("مالي") || designation.contains("اقتصادي")) {
            return "FINANCIAL_DOMAIN";
        }
        
        // Human Resources domains
        if (designation.contains("ressources humaines") || designation.contains("human resources") ||
            designation.contains("personnel") || designation.contains("rh") ||
            designation.contains("موارد بشرية") || designation.contains("أفراد")) {
            return "HR_DOMAIN";
        }
        
        // Security domains
        if (designation.contains("sécurité") || designation.contains("security") ||
            designation.contains("défense") || designation.contains("defense") ||
            designation.contains("protection") || designation.contains("أمن") ||
            designation.contains("دفاع")) {
            return "SECURITY_DOMAIN";
        }
        
        // Logistics domains
        if (designation.contains("logistique") || designation.contains("logistics") ||
            designation.contains("approvisionnement") || designation.contains("supply") ||
            designation.contains("transport") || designation.contains("لوجستيات") ||
            designation.contains("إمداد")) {
            return "LOGISTICS_DOMAIN";
        }
        
        // Training domains
        if (designation.contains("formation") || designation.contains("training") ||
            designation.contains("éducation") || designation.contains("education") ||
            designation.contains("apprentissage") || designation.contains("learning") ||
            designation.contains("تدريب") || designation.contains("تعليم")) {
            return "TRAINING_DOMAIN";
        }
        
        return "GENERAL_DOMAIN";
    }

    /**
     * Get domain priority level based on category
     */
    public String getDomainPriority() {
        String category = getDomainCategory();
        
        switch (category) {
            case "SECURITY_DOMAIN":
            case "STRATEGIC_DOMAIN":
                return "CRITICAL_PRIORITY";
            case "OPERATIONAL_DOMAIN":
            case "TECHNICAL_DOMAIN":
                return "HIGH_PRIORITY";
            case "FINANCIAL_DOMAIN":
            case "ADMINISTRATIVE_DOMAIN":
                return "MEDIUM_PRIORITY";
            case "HR_DOMAIN":
            case "LOGISTICS_DOMAIN":
                return "NORMAL_PRIORITY";
            case "TRAINING_DOMAIN":
                return "LOW_PRIORITY";
            default:
                return "NORMAL_PRIORITY";
        }
    }

    /**
     * Get domain scope based on category
     */
    public String getDomainScope() {
        String category = getDomainCategory();
        
        switch (category) {
            case "STRATEGIC_DOMAIN":
                return "ORGANIZATIONAL_SCOPE";
            case "OPERATIONAL_DOMAIN":
            case "SECURITY_DOMAIN":
                return "DEPARTMENTAL_SCOPE";
            case "TECHNICAL_DOMAIN":
            case "FINANCIAL_DOMAIN":
                return "FUNCTIONAL_SCOPE";
            case "ADMINISTRATIVE_DOMAIN":
            case "HR_DOMAIN":
                return "SUPPORT_SCOPE";
            case "LOGISTICS_DOMAIN":
            case "TRAINING_DOMAIN":
                return "SERVICE_SCOPE";
            default:
                return "GENERAL_SCOPE";
        }
    }

    /**
     * Check if domain has rubrics
     */
    public boolean hasRubrics() {
        return rubricsCount != null && rubricsCount > 0;
    }

    /**
     * Get rubrics count (safe)
     */
    public int getRubricsCountSafe() {
        return rubricsCount != null ? rubricsCount : 0;
    }

    /**
     * Get domain management complexity based on rubrics count
     */
    public String getManagementComplexity() {
        int count = getRubricsCountSafe();
        
        if (count == 0) {
            return "NO_COMPLEXITY";
        } else if (count <= 5) {
            return "LOW_COMPLEXITY";
        } else if (count <= 15) {
            return "MEDIUM_COMPLEXITY";
        } else if (count <= 30) {
            return "HIGH_COMPLEXITY";
        } else {
            return "VERY_HIGH_COMPLEXITY";
        }
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        if (hasRubrics()) {
            sb.append(" (").append(getRubricsCountSafe()).append(" rubrics)");
        }
        
        return sb.toString();
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
        
        return sb.toString();
    }

    /**
     * Get domain display with category and rubrics info
     */
    public String getDomainDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String category = getDomainCategory();
        if (!"GENERAL_DOMAIN".equals(category)) {
            sb.append(" - ").append(category.replace("_", " "));
        }
        
        if (hasRubrics()) {
            sb.append(" [").append(getRubricsCountSafe()).append(" rubrics]");
        }
        
        String complexity = getManagementComplexity();
        if (!"NO_COMPLEXITY".equals(complexity)) {
            sb.append(" (").append(complexity.replace("_", " ")).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static DomainDTO createSimple(Long id, String designationFr, Integer rubricsCount) {
        return DomainDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .rubricsCount(rubricsCount)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty();
    }

    /**
     * Get validation errors
     */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();
        
        if (designationFr == null || designationFr.trim().isEmpty()) {
            errors.add("French designation is required");
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
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            return designationFr;
        }
        return getDisplayText();
    }

    /**
     * Get domain classification for reports
     */
    public String getDomainClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Domain: ").append(getDisplayText()).append("\n");
        sb.append("Category: ").append(getDomainCategory().replace("_", " ")).append("\n");
        sb.append("Priority: ").append(getDomainPriority().replace("_", " ")).append("\n");
        sb.append("Scope: ").append(getDomainScope().replace("_", " ")).append("\n");
        sb.append("Rubrics Count: ").append(getRubricsCountSafe()).append("\n");
        sb.append("Management Complexity: ").append(getManagementComplexity().replace("_", " ")).append("\n");
        sb.append("Has Rubrics: ").append(hasRubrics() ? "Yes" : "No").append("\n");
        sb.append("Multilingual: ").append(isMultilingual() ? "Yes" : "No");
        
        return sb.toString();
    }

    /**
     * Get domain usage context
     */
    public String getDomainUsageContext() {
        String category = getDomainCategory();
        
        return switch (category) {
            case "TECHNICAL_DOMAIN" -> "Technical operations, engineering, and technology management";
            case "ADMINISTRATIVE_DOMAIN" -> "Administrative processes, documentation, and organizational management";
            case "OPERATIONAL_DOMAIN" -> "Day-to-day operations, mission execution, and tactical activities";
            case "STRATEGIC_DOMAIN" -> "Strategic planning, long-term vision, and organizational direction";
            case "FINANCIAL_DOMAIN" -> "Financial management, budgeting, and economic planning";
            case "HR_DOMAIN" -> "Human resources management, personnel, and workforce development";
            case "SECURITY_DOMAIN" -> "Security operations, defense, and protection activities";
            case "LOGISTICS_DOMAIN" -> "Supply chain, transportation, and logistical support";
            case "TRAINING_DOMAIN" -> "Education, training programs, and skill development";
            default -> "General organizational activities and processes";
        };
    }

    /**
     * Get domain management requirements
     */
    public String getManagementRequirements() {
        String priority = getDomainPriority();
        String complexity = getManagementComplexity();
        
        StringBuilder sb = new StringBuilder();
        
        // Priority-based requirements
        switch (priority) {
            case "CRITICAL_PRIORITY":
                sb.append("Executive oversight, Real-time monitoring, Strategic alignment");
                break;
            case "HIGH_PRIORITY":
                sb.append("Senior management oversight, Regular monitoring");
                break;
            case "MEDIUM_PRIORITY":
                sb.append("Departmental oversight, Periodic monitoring");
                break;
            case "NORMAL_PRIORITY":
                sb.append("Standard oversight, Routine monitoring");
                break;
            default:
                sb.append("Basic oversight, Scheduled monitoring");
        }
        
        // Complexity-based additional requirements
        switch (complexity) {
            case "VERY_HIGH_COMPLEXITY":
            case "HIGH_COMPLEXITY":
                sb.append(", Specialized expertise, Dedicated resources");
                break;
            case "MEDIUM_COMPLEXITY":
                sb.append(", Coordinated management, Regular reviews");
                break;
            case "LOW_COMPLEXITY":
                sb.append(", Standard management, Basic coordination");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get domain governance model
     */
    public String getGovernanceModel() {
        String category = getDomainCategory();
        String priority = getDomainPriority();
        
        if ("CRITICAL_PRIORITY".equals(priority)) {
            return "EXECUTIVE_GOVERNANCE";
        } else if ("HIGH_PRIORITY".equals(priority)) {
            return "SENIOR_GOVERNANCE";
        } else if ("STRATEGIC_DOMAIN".equals(category)) {
            return "STRATEGIC_GOVERNANCE";
        } else if ("OPERATIONAL_DOMAIN".equals(category)) {
            return "OPERATIONAL_GOVERNANCE";
        } else {
            return "STANDARD_GOVERNANCE";
        }
    }

    /**
     * Get domain reporting frequency
     */
    public String getReportingFrequency() {
        String priority = getDomainPriority();
        
        return switch (priority) {
            case "CRITICAL_PRIORITY" -> "DAILY_REPORTING";
            case "HIGH_PRIORITY" -> "WEEKLY_REPORTING";
            case "MEDIUM_PRIORITY" -> "MONTHLY_REPORTING";
            case "NORMAL_PRIORITY" -> "QUARTERLY_REPORTING";
            default -> "ANNUAL_REPORTING";
        };
    }

    /**
     * Get domain stakeholders
     */
    public String getDomainStakeholders() {
        String category = getDomainCategory();
        
        return switch (category) {
            case "STRATEGIC_DOMAIN" -> "Executive leadership, Board members, Strategic planners";
            case "OPERATIONAL_DOMAIN" -> "Operations managers, Team leads, Field personnel";
            case "TECHNICAL_DOMAIN" -> "Engineers, Technical specialists, IT personnel";
            case "FINANCIAL_DOMAIN" -> "Financial officers, Controllers, Budget managers";
            case "ADMINISTRATIVE_DOMAIN" -> "Administrative staff, Process owners, Compliance officers";
            case "HR_DOMAIN" -> "HR managers, Team supervisors, Training coordinators";
            case "SECURITY_DOMAIN" -> "Security officers, Risk managers, Compliance teams";
            case "LOGISTICS_DOMAIN" -> "Supply managers, Transportation coordinators, Warehouse staff";
            case "TRAINING_DOMAIN" -> "Training managers, Instructors, Learning coordinators";
            default -> "General staff, Department heads, Process coordinators";
        };
    }

    /**
     * Get domain success metrics
     */
    public String getSuccessMetrics() {
        String category = getDomainCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Category-specific metrics
        switch (category) {
            case "STRATEGIC_DOMAIN":
                sb.append("Strategic goal achievement, Vision alignment, Long-term impact");
                break;
            case "OPERATIONAL_DOMAIN":
                sb.append("Operational efficiency, Performance targets, Mission success rate");
                break;
            case "TECHNICAL_DOMAIN":
                sb.append("Technical performance, Innovation metrics, System reliability");
                break;
            case "FINANCIAL_DOMAIN":
                sb.append("Budget compliance, Cost efficiency, Financial performance");
                break;
            case "ADMINISTRATIVE_DOMAIN":
                sb.append("Process efficiency, Compliance rate, Administrative accuracy");
                break;
            case "HR_DOMAIN":
                sb.append("Employee satisfaction, Retention rate, Skills development");
                break;
            case "SECURITY_DOMAIN":
                sb.append("Security incidents, Compliance level, Risk mitigation");
                break;
            case "LOGISTICS_DOMAIN":
                sb.append("Supply efficiency, Delivery performance, Cost optimization");
                break;
            case "TRAINING_DOMAIN":
                sb.append("Training effectiveness, Skill acquisition, Competency levels");
                break;
            default:
                sb.append("Performance indicators, Quality metrics, Stakeholder satisfaction");
        }
        
        // Add rubrics-based metrics
        if (hasRubrics()) {
            sb.append(", Rubric coverage, Domain completeness");
        }
        
        return sb.toString();
    }

    /**
     * Get domain risk factors
     */
    public String getRiskFactors() {
        String complexity = getManagementComplexity();
        String priority = getDomainPriority();
        
        StringBuilder sb = new StringBuilder();
        
        // Complexity-based risks
        switch (complexity) {
            case "VERY_HIGH_COMPLEXITY":
                sb.append("High coordination risk, Resource contention, Integration challenges");
                break;
            case "HIGH_COMPLEXITY":
                sb.append("Coordination challenges, Resource dependencies, Complexity overhead");
                break;
            case "MEDIUM_COMPLEXITY":
                sb.append("Moderate coordination needs, Standard resource risks");
                break;
            case "LOW_COMPLEXITY":
                sb.append("Low coordination risk, Minimal resource dependencies");
                break;
            default:
                sb.append("Minimal operational risk");
        }
        
        // Priority-based risks
        if ("CRITICAL_PRIORITY".equals(priority)) {
            sb.append(", High impact failure risk, Operational disruption potential");
        } else if ("HIGH_PRIORITY".equals(priority)) {
            sb.append(", Moderate impact risk, Performance degradation potential");
        }
        
        return sb.toString();
    }
}