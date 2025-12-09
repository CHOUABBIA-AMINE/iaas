/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RubricDTO
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
 * Rubric Data Transfer Object
 * Maps exactly to Rubric model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=domainId
 * Includes many-to-one relationship with Domain and one-to-many relationship with Items
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RubricDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required, unique

    @NotNull(message = "Domain is required")
    private Long domainId; // F_04 - Domain foreign key (required)

    // Related entities (populated when needed)
    private DomainDTO domain; // Many-to-one relationship
    private List<ItemDTO> items; // One-to-many relationship

    // Summary information
    private Integer itemsCount;

    /**
     * Create DTO from entity
     */
    public static RubricDTO fromEntity(dz.mdn.iaas.business.plan.model.Rubric rubric) {
        if (rubric == null) return null;
        
        RubricDTO.RubricDTOBuilder builder = RubricDTO.builder()
                .id(rubric.getId())
                .designationAr(rubric.getDesignationAr())
                .designationEn(rubric.getDesignationEn())
                .designationFr(rubric.getDesignationFr());

        // Handle foreign key relationship
        if (rubric.getDomain() != null) {
            builder.domainId(rubric.getDomain().getId());
        }

        // Add items count if available
        if (rubric.getItems() != null) {
            builder.itemsCount(rubric.getItems().size());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static RubricDTO fromEntityWithRelations(dz.mdn.iaas.business.plan.model.Rubric rubric) {
        RubricDTO dto = fromEntity(rubric);
        if (dto == null) return null;

        // Populate related DTOs
        if (rubric.getDomain() != null) {
            dto.setDomain(DomainDTO.fromEntity(rubric.getDomain()));
        }

        if (rubric.getItems() != null && !rubric.getItems().isEmpty()) {
            List<ItemDTO> itemDTOs = new ArrayList<>();
            for (var item : rubric.getItems()) {
                itemDTOs.add(ItemDTO.fromEntity(item));
            }
            dto.setItems(itemDTOs);
            dto.setItemsCount(itemDTOs.size());
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
     * Check if rubric has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this rubric
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
     * Get rubric category based on designation keywords
     */
    public String getRubricCategory() {
        String designation = getDefaultDesignation().toLowerCase();
        
        // Requirements rubrics
        if (designation.contains("exigence") || designation.contains("requirement") ||
            designation.contains("spécification") || designation.contains("specification") ||
            designation.contains("besoin") || designation.contains("need") ||
            designation.contains("متطلب") || designation.contains("مواصفة")) {
            return "REQUIREMENTS_RUBRIC";
        }
        
        // Quality rubrics
        if (designation.contains("qualité") || designation.contains("quality") ||
            designation.contains("norme") || designation.contains("standard") ||
            designation.contains("conformité") || designation.contains("compliance") ||
            designation.contains("جودة") || designation.contains("معيار")) {
            return "QUALITY_RUBRIC";
        }
        
        // Performance rubrics
        if (designation.contains("performance") || designation.contains("efficacité") ||
            designation.contains("efficiency") || designation.contains("rendement") ||
            designation.contains("productivité") || designation.contains("productivity") ||
            designation.contains("أداء") || designation.contains("كفاءة")) {
            return "PERFORMANCE_RUBRIC";
        }
        
        // Security rubrics
        if (designation.contains("sécurité") || designation.contains("security") ||
            designation.contains("protection") || designation.contains("confidentialité") ||
            designation.contains("confidentiality") || designation.contains("أمن") ||
            designation.contains("حماية")) {
            return "SECURITY_RUBRIC";
        }
        
        // Compliance rubrics
        if (designation.contains("conformité") || designation.contains("compliance") ||
            designation.contains("réglementation") || designation.contains("regulation") ||
            designation.contains("audit") || designation.contains("contrôle") ||
            designation.contains("امتثال") || designation.contains("مراجعة")) {
            return "COMPLIANCE_RUBRIC";
        }
        
        // Technical rubrics
        if (designation.contains("technique") || designation.contains("technical") ||
            designation.contains("technologie") || designation.contains("technology") ||
            designation.contains("système") || designation.contains("system") ||
            designation.contains("تقني") || designation.contains("نظام")) {
            return "TECHNICAL_RUBRIC";
        }
        
        // Operational rubrics
        if (designation.contains("opérationnel") || designation.contains("operational") ||
            designation.contains("processus") || designation.contains("process") ||
            designation.contains("procédure") || designation.contains("procedure") ||
            designation.contains("تشغيلي") || designation.contains("عملية")) {
            return "OPERATIONAL_RUBRIC";
        }
        
        // Training rubrics
        if (designation.contains("formation") || designation.contains("training") ||
            designation.contains("compétence") || designation.contains("competency") ||
            designation.contains("apprentissage") || designation.contains("learning") ||
            designation.contains("تدريب") || designation.contains("مهارة")) {
            return "TRAINING_RUBRIC";
        }
        
        // Documentation rubrics
        if (designation.contains("documentation") || designation.contains("document") ||
            designation.contains("manuel") || designation.contains("manual") ||
            designation.contains("guide") || designation.contains("instruction") ||
            designation.contains("توثيق") || designation.contains("دليل")) {
            return "DOCUMENTATION_RUBRIC";
        }
        
        return "GENERAL_RUBRIC";
    }

    /**
     * Get rubric priority level based on category
     */
    public String getRubricPriority() {
        String category = getRubricCategory();
        
        switch (category) {
            case "SECURITY_RUBRIC":
            case "COMPLIANCE_RUBRIC":
                return "CRITICAL_PRIORITY";
            case "REQUIREMENTS_RUBRIC":
            case "QUALITY_RUBRIC":
                return "HIGH_PRIORITY";
            case "PERFORMANCE_RUBRIC":
            case "TECHNICAL_RUBRIC":
                return "MEDIUM_PRIORITY";
            case "OPERATIONAL_RUBRIC":
            case "TRAINING_RUBRIC":
                return "NORMAL_PRIORITY";
            case "DOCUMENTATION_RUBRIC":
                return "LOW_PRIORITY";
            default:
                return "NORMAL_PRIORITY";
        }
    }

    /**
     * Get rubric application scope
     */
    public String getApplicationScope() {
        String category = getRubricCategory();
        
        switch (category) {
            case "SECURITY_RUBRIC":
            case "COMPLIANCE_RUBRIC":
                return "ENTERPRISE_SCOPE";
            case "REQUIREMENTS_RUBRIC":
            case "QUALITY_RUBRIC":
                return "PROJECT_SCOPE";
            case "PERFORMANCE_RUBRIC":
            case "OPERATIONAL_RUBRIC":
                return "DEPARTMENT_SCOPE";
            case "TECHNICAL_RUBRIC":
            case "TRAINING_RUBRIC":
                return "TEAM_SCOPE";
            case "DOCUMENTATION_RUBRIC":
                return "INDIVIDUAL_SCOPE";
            default:
                return "DEPARTMENT_SCOPE";
        }
    }

    /**
     * Check if rubric has items
     */
    public boolean hasItems() {
        return itemsCount != null && itemsCount > 0;
    }

    /**
     * Get items count (safe)
     */
    public int getItemsCountSafe() {
        return itemsCount != null ? itemsCount : 0;
    }

    /**
     * Get rubric complexity based on items count
     */
    public String getRubricComplexity() {
        int count = getItemsCountSafe();
        
        if (count == 0) {
            return "NO_COMPLEXITY";
        } else if (count <= 3) {
            return "LOW_COMPLEXITY";
        } else if (count <= 8) {
            return "MEDIUM_COMPLEXITY";
        } else if (count <= 15) {
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
        
        if (hasItems()) {
            sb.append(" (").append(getItemsCountSafe()).append(" items)");
        }
        
        if (domain != null) {
            sb.append(" - ").append(domain.getDisplayText());
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
     * Get rubric display with category and items info
     */
    public String getRubricDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String category = getRubricCategory();
        if (!"GENERAL_RUBRIC".equals(category)) {
            sb.append(" - ").append(category.replace("_", " "));
        }
        
        if (hasItems()) {
            sb.append(" [").append(getItemsCountSafe()).append(" items]");
        }
        
        String complexity = getRubricComplexity();
        if (!"NO_COMPLEXITY".equals(complexity)) {
            sb.append(" (").append(complexity.replace("_", " ")).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static RubricDTO createSimple(Long id, String designationFr, Long domainId, Integer itemsCount) {
        return RubricDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .domainId(domainId)
                .itemsCount(itemsCount)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() && 
               domainId != null;
    }

    /**
     * Get validation errors
     */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();
        
        if (designationFr == null || designationFr.trim().isEmpty()) {
            errors.add("French designation is required");
        }
        
        if (domainId == null) {
            errors.add("Domain is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by domain, then by designation)
     */
    public String getComparisonKey() {
        String domainName = domain != null ? domain.getDisplayText() : "zzz";
        String rubricName = getDisplayText().toLowerCase();
        return domainName.toLowerCase() + "_" + rubricName;
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            sb.append(designationFr);
        }
        
        if (domain != null) {
            sb.append(" - ").append(domain.getFormalDisplay());
        }
        
        return sb.toString();
    }

    /**
     * Get rubric classification for reports
     */
    public String getRubricClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Rubric: ").append(getDisplayText()).append("\n");
        sb.append("Domain: ").append(domain != null ? domain.getDisplayText() : "N/A").append("\n");
        sb.append("Category: ").append(getRubricCategory().replace("_", " ")).append("\n");
        sb.append("Priority: ").append(getRubricPriority().replace("_", " ")).append("\n");
        sb.append("Application Scope: ").append(getApplicationScope().replace("_", " ")).append("\n");
        sb.append("Items Count: ").append(getItemsCountSafe()).append("\n");
        sb.append("Complexity: ").append(getRubricComplexity().replace("_", " ")).append("\n");
        sb.append("Has Items: ").append(hasItems() ? "Yes" : "No").append("\n");
        sb.append("Multilingual: ").append(isMultilingual() ? "Yes" : "No");
        
        return sb.toString();
    }

    /**
     * Get rubric usage context
     */
    public String getRubricUsageContext() {
        String category = getRubricCategory();
        
        return switch (category) {
            case "REQUIREMENTS_RUBRIC" -> "Specification management, needs analysis, and requirement tracking";
            case "QUALITY_RUBRIC" -> "Quality assurance, standard compliance, and quality control";
            case "PERFORMANCE_RUBRIC" -> "Performance measurement, efficiency tracking, and productivity analysis";
            case "SECURITY_RUBRIC" -> "Security protocols, protection measures, and confidentiality management";
            case "COMPLIANCE_RUBRIC" -> "Regulatory compliance, audit preparation, and control implementation";
            case "TECHNICAL_RUBRIC" -> "Technical specifications, system requirements, and technology standards";
            case "OPERATIONAL_RUBRIC" -> "Process management, operational procedures, and workflow control";
            case "TRAINING_RUBRIC" -> "Competency development, skill assessment, and learning objectives";
            case "DOCUMENTATION_RUBRIC" -> "Document management, manual creation, and information organization";
            default -> "General rubric application and item organization";
        };
    }

    /**
     * Get rubric implementation requirements
     */
    public String getImplementationRequirements() {
        String priority = getRubricPriority();
        String complexity = getRubricComplexity();
        
        StringBuilder sb = new StringBuilder();
        
        // Priority-based requirements
        switch (priority) {
            case "CRITICAL_PRIORITY":
                sb.append("Executive approval required, Immediate implementation, Continuous monitoring");
                break;
            case "HIGH_PRIORITY":
                sb.append("Management approval required, Scheduled implementation, Regular monitoring");
                break;
            case "MEDIUM_PRIORITY":
                sb.append("Supervisor approval, Planned implementation, Periodic monitoring");
                break;
            case "NORMAL_PRIORITY":
                sb.append("Team leader approval, Standard implementation, Routine monitoring");
                break;
            default:
                sb.append("Basic approval, Standard implementation, Basic monitoring");
        }
        
        // Complexity-based additional requirements
        switch (complexity) {
            case "VERY_HIGH_COMPLEXITY":
            case "HIGH_COMPLEXITY":
                sb.append(", Specialized resources, Expert consultation, Phased approach");
                break;
            case "MEDIUM_COMPLEXITY":
                sb.append(", Adequate resources, Training support, Structured approach");
                break;
            case "LOW_COMPLEXITY":
                sb.append(", Standard resources, Basic training, Direct approach");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get rubric assessment criteria
     */
    public String getAssessmentCriteria() {
        String category = getRubricCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Category-specific criteria
        switch (category) {
            case "REQUIREMENTS_RUBRIC":
                sb.append("Completeness, Clarity, Traceability, Feasibility");
                break;
            case "QUALITY_RUBRIC":
                sb.append("Conformance, Reliability, Consistency, Excellence");
                break;
            case "PERFORMANCE_RUBRIC":
                sb.append("Efficiency, Effectiveness, Timeliness, Accuracy");
                break;
            case "SECURITY_RUBRIC":
                sb.append("Confidentiality, Integrity, Availability, Authentication");
                break;
            case "COMPLIANCE_RUBRIC":
                sb.append("Regulatory adherence, Audit readiness, Documentation, Controls");
                break;
            case "TECHNICAL_RUBRIC":
                sb.append("Functionality, Reliability, Performance, Maintainability");
                break;
            case "OPERATIONAL_RUBRIC":
                sb.append("Process adherence, Efficiency, Consistency, Effectiveness");
                break;
            case "TRAINING_RUBRIC":
                sb.append("Knowledge acquisition, Skill development, Competency demonstration");
                break;
            case "DOCUMENTATION_RUBRIC":
                sb.append("Completeness, Accuracy, Accessibility, Currency");
                break;
            default:
                sb.append("Relevance, Completeness, Quality, Effectiveness");
        }
        
        // Add items-based criteria
        if (hasItems()) {
            sb.append(", Item coverage, Item quality, Item consistency");
        }
        
        return sb.toString();
    }

    /**
     * Get rubric review frequency
     */
    public String getReviewFrequency() {
        String priority = getRubricPriority();
        
        return switch (priority) {
            case "CRITICAL_PRIORITY" -> "CONTINUOUS_REVIEW";
            case "HIGH_PRIORITY" -> "MONTHLY_REVIEW";
            case "MEDIUM_PRIORITY" -> "QUARTERLY_REVIEW";
            case "NORMAL_PRIORITY" -> "SEMI_ANNUAL_REVIEW";
            default -> "ANNUAL_REVIEW";
        };
    }

    /**
     * Get rubric stakeholders
     */
    public String getRubricStakeholders() {
        String category = getRubricCategory();
        String scope = getApplicationScope();
        
        StringBuilder sb = new StringBuilder();
        
        // Scope-based stakeholders
        switch (scope) {
            case "ENTERPRISE_SCOPE":
                sb.append("Executive team, Department heads, Compliance officers");
                break;
            case "PROJECT_SCOPE":
                sb.append("Project managers, Team leads, Quality assurance");
                break;
            case "DEPARTMENT_SCOPE":
                sb.append("Department managers, Team supervisors, Process owners");
                break;
            case "TEAM_SCOPE":
                sb.append("Team leads, Technical specialists, Subject matter experts");
                break;
            case "INDIVIDUAL_SCOPE":
                sb.append("Individual contributors, Direct supervisors, Trainers");
                break;
        }
        
        // Category-specific additional stakeholders
        if ("SECURITY_RUBRIC".equals(category)) {
            sb.append(", Security officers, Risk managers");
        } else if ("COMPLIANCE_RUBRIC".equals(category)) {
            sb.append(", Auditors, Regulatory liaisons");
        }
        
        return sb.toString();
    }

    /**
     * Get rubric success metrics
     */
    public String getSuccessMetrics() {
        String category = getRubricCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Category-specific metrics
        switch (category) {
            case "REQUIREMENTS_RUBRIC":
                sb.append("Requirements coverage, Traceability rate, Change impact");
                break;
            case "QUALITY_RUBRIC":
                sb.append("Defect rate, Quality score, Customer satisfaction");
                break;
            case "PERFORMANCE_RUBRIC":
                sb.append("Performance targets, Efficiency gains, Response times");
                break;
            case "SECURITY_RUBRIC":
                sb.append("Security incidents, Vulnerability count, Compliance rate");
                break;
            case "COMPLIANCE_RUBRIC":
                sb.append("Audit findings, Compliance score, Regulatory adherence");
                break;
            case "TECHNICAL_RUBRIC":
                sb.append("System performance, Reliability metrics, User satisfaction");
                break;
            case "OPERATIONAL_RUBRIC":
                sb.append("Process efficiency, Error rates, Cycle times");
                break;
            case "TRAINING_RUBRIC":
                sb.append("Competency levels, Training completion, Skill assessments");
                break;
            case "DOCUMENTATION_RUBRIC":
                sb.append("Documentation coverage, Accuracy rate, Usage statistics");
                break;
            default:
                sb.append("Implementation rate, Quality measures, Stakeholder satisfaction");
        }
        
        // Add items-based metrics
        if (hasItems()) {
            sb.append(", Item completion rate, Item quality score");
        }
        
        return sb.toString();
    }

    /**
     * Get rubric risk factors
     */
    public String getRiskFactors() {
        String complexity = getRubricComplexity();
        String priority = getRubricPriority();
        
        StringBuilder sb = new StringBuilder();
        
        // Complexity-based risks
        switch (complexity) {
            case "VERY_HIGH_COMPLEXITY":
                sb.append("High implementation risk, Resource dependencies, Integration challenges");
                break;
            case "HIGH_COMPLEXITY":
                sb.append("Implementation challenges, Resource contention, Coordination risks");
                break;
            case "MEDIUM_COMPLEXITY":
                sb.append("Moderate implementation risk, Standard resource needs");
                break;
            case "LOW_COMPLEXITY":
                sb.append("Low implementation risk, Minimal resource dependencies");
                break;
            default:
                sb.append("Minimal operational risk");
        }
        
        // Priority-based risks
        if ("CRITICAL_PRIORITY".equals(priority)) {
            sb.append(", High impact failure risk, Compliance violations");
        } else if ("HIGH_PRIORITY".equals(priority)) {
            sb.append(", Moderate impact risk, Quality degradation");
        }
        
        return sb.toString();
    }
}