/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.document.dto.DocumentDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BudgetModification Data Transfer Object
 * Maps exactly to BudgetModification model fields: F_00=id, F_01=object, F_02=description, 
 * F_03=approvalDate, F_04=demandeId, F_05=responseId
 * Includes unique constraint on F_03+F_04 (approvalDate+demande) and many-to-one relationships with Documents
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetModificationDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Object must not exceed 200 characters")
    private String object; // F_01 - optional

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description; // F_02 - optional

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date approvalDate; // F_03 - part of unique constraint

    @NotNull(message = "Demande document is required")
    private Long demandeId; // F_04 - Document foreign key (required, part of unique constraint)

    @NotNull(message = "Response document is required")
    private Long responseId; // F_05 - Document foreign key (required)

    // Related entities (populated when needed)
    private DocumentDTO demande; // Many-to-one relationship
    private DocumentDTO response; // Many-to-one relationship

    /**
     * Create DTO from entity
     */
    public static BudgetModificationDTO fromEntity(dz.mdn.iaas.business.plan.model.BudgetModification budgetModification) {
        if (budgetModification == null) return null;
        
        BudgetModificationDTO.BudgetModificationDTOBuilder builder = BudgetModificationDTO.builder()
                .id(budgetModification.getId())
                .object(budgetModification.getObject())
                .description(budgetModification.getDescription())
                .approvalDate(budgetModification.getApprovalDate());

        // Handle foreign key relationships
        if (budgetModification.getDemande() != null) {
            builder.demandeId(budgetModification.getDemande().getId());
        }
        if (budgetModification.getResponse() != null) {
            builder.responseId(budgetModification.getResponse().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static BudgetModificationDTO fromEntityWithRelations(dz.mdn.iaas.business.plan.model.BudgetModification budgetModification) {
        BudgetModificationDTO dto = fromEntity(budgetModification);
        if (dto == null) return null;

        // Populate related DTOs
        if (budgetModification.getDemande() != null) {
            dto.setDemande(DocumentDTO.fromEntity(budgetModification.getDemande()));
        }
        if (budgetModification.getResponse() != null) {
            dto.setResponse(DocumentDTO.fromEntity(budgetModification.getResponse()));
        }

        return dto;
    }

    /**
     * Get display text for the budget modification
     */
    public String getDisplayText() {
        if (object != null && !object.trim().isEmpty()) {
            return object;
        }
        if (description != null && !description.trim().isEmpty()) {
            String shortDesc = description.length() > 50 ? description.substring(0, 47) + "..." : description;
            return shortDesc;
        }
        return "Budget Modification #" + (id != null ? id : "N/A");
    }

    /**
     * Get modification status based on approval date
     */
    public String getModificationStatus() {
        if (approvalDate == null) {
            return "PENDING_APPROVAL";
        }
        
        Date now = new Date();
        if (approvalDate.after(now)) {
            return "SCHEDULED_FOR_APPROVAL";
        } else {
            return "APPROVED";
        }
    }

    /**
     * Get modification type based on object content
     */
    public String getModificationType() {
        if (object == null || object.trim().isEmpty()) {
            return "GENERAL_MODIFICATION";
        }
        
        String objectLower = object.toLowerCase();
        
        // Budget increase modifications
        if (objectLower.contains("augmentation") || objectLower.contains("increase") ||
            objectLower.contains("ajout") || objectLower.contains("addition") ||
            objectLower.contains("زيادة") || objectLower.contains("إضافة")) {
            return "BUDGET_INCREASE";
        }
        
        // Budget decrease modifications
        if (objectLower.contains("réduction") || objectLower.contains("reduction") ||
            objectLower.contains("diminution") || objectLower.contains("decrease") ||
            objectLower.contains("تقليل") || objectLower.contains("خفض")) {
            return "BUDGET_DECREASE";
        }
        
        // Budget reallocation modifications
        if (objectLower.contains("réallocation") || objectLower.contains("reallocation") ||
            objectLower.contains("transfert") || objectLower.contains("transfer") ||
            objectLower.contains("virement") || objectLower.contains("إعادة تخصيص")) {
            return "BUDGET_REALLOCATION";
        }
        
        // Emergency modifications
        if (objectLower.contains("urgence") || objectLower.contains("emergency") ||
            objectLower.contains("urgent") || objectLower.contains("critique") ||
            objectLower.contains("critical") || objectLower.contains("طوارئ")) {
            return "EMERGENCY_MODIFICATION";
        }
        
        // Correction modifications
        if (objectLower.contains("correction") || objectLower.contains("rectification") ||
            objectLower.contains("ajustement") || objectLower.contains("adjustment") ||
            objectLower.contains("تصحيح") || objectLower.contains("تعديل")) {
            return "CORRECTION_MODIFICATION";
        }
        
        // Revision modifications
        if (objectLower.contains("révision") || objectLower.contains("revision") ||
            objectLower.contains("mise à jour") || objectLower.contains("update") ||
            objectLower.contains("مراجعة") || objectLower.contains("تحديث")) {
            return "REVISION_MODIFICATION";
        }
        
        return "STANDARD_MODIFICATION";
    }

    /**
     * Get modification priority based on type and content
     */
    public String getModificationPriority() {
        String type = getModificationType();
        
        switch (type) {
            case "EMERGENCY_MODIFICATION":
                return "CRITICAL_PRIORITY";
            case "BUDGET_INCREASE":
            case "BUDGET_DECREASE":
                return "HIGH_PRIORITY";
            case "BUDGET_REALLOCATION":
            case "CORRECTION_MODIFICATION":
                return "MEDIUM_PRIORITY";
            case "REVISION_MODIFICATION":
            case "STANDARD_MODIFICATION":
                return "NORMAL_PRIORITY";
            default:
                return "LOW_PRIORITY";
        }
    }

    /**
     * Get modification urgency level
     */
    public String getUrgencyLevel() {
        String priority = getModificationPriority();
        String status = getModificationStatus();
        
        if ("CRITICAL_PRIORITY".equals(priority)) {
            return "IMMEDIATE_ACTION";
        } else if ("HIGH_PRIORITY".equals(priority) && "PENDING_APPROVAL".equals(status)) {
            return "URGENT_ACTION";
        } else if ("MEDIUM_PRIORITY".equals(priority)) {
            return "TIMELY_ACTION";
        } else {
            return "STANDARD_ACTION";
        }
    }

    /**
     * Check if modification is pending
     */
    public boolean isPending() {
        return "PENDING_APPROVAL".equals(getModificationStatus());
    }

    /**
     * Check if modification is approved
     */
    public boolean isApproved() {
        return "APPROVED".equals(getModificationStatus());
    }

    /**
     * Check if modification is scheduled
     */
    public boolean isScheduled() {
        return "SCHEDULED_FOR_APPROVAL".equals(getModificationStatus());
    }

    /**
     * Get days until/since approval
     */
    public long getDaysToApproval() {
        if (approvalDate == null) {
            return -1; // Pending
        }
        
        Date now = new Date();
        long diffInMs = approvalDate.getTime() - now.getTime();
        return diffInMs / (1000 * 60 * 60 * 24); // Convert to days
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String status = getModificationStatus();
        sb.append(" [").append(status.replace("_", " ")).append("]");
        
        String type = getModificationType();
        if (!"STANDARD_MODIFICATION".equals(type)) {
            sb.append(" (").append(type.replace("_", " ")).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get full display with all available information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (object != null && !object.trim().isEmpty()) {
            sb.append("Object: ").append(object);
        }
        
        if (description != null && !description.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Description: ").append(description);
        }
        
        if (approvalDate != null) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Approval: ").append(approvalDate);
        }
        
        return sb.toString();
    }

    /**
     * Get modification display with status and priority
     */
    public String getModificationDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String type = getModificationType();
        sb.append(" - Type: ").append(type.replace("_", " "));
        
        String priority = getModificationPriority();
        sb.append(", Priority: ").append(priority.replace("_", " "));
        
        String status = getModificationStatus();
        sb.append(", Status: ").append(status.replace("_", " "));
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static BudgetModificationDTO createSimple(Long id, String object, Date approvalDate, String status) {
        return BudgetModificationDTO.builder()
                .id(id)
                .object(object)
                .approvalDate(approvalDate)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return demandeId != null && responseId != null;
    }

    /**
     * Get validation errors
     */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();
        
        if (demandeId == null) {
            errors.add("Demande document is required");
        }
        
        if (responseId == null) {
            errors.add("Response document is required");
        }
        
        if (object != null && object.length() > 200) {
            errors.add("Object cannot exceed 200 characters");
        }
        
        if (description != null && description.length() > 500) {
            errors.add("Description cannot exceed 500 characters");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting
     */
    public String getComparisonKey() {
        Date sortDate = approvalDate != null ? approvalDate : new Date(0);
        return sortDate.toString() + "_" + getDisplayText().toLowerCase();
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (object != null && !object.trim().isEmpty()) {
            sb.append(object);
        } else {
            sb.append("Budget Modification");
        }
        
        if (id != null) {
            sb.append(" (ID: ").append(id).append(")");
        }
        
        if (approvalDate != null) {
            sb.append(" - Approved: ").append(approvalDate);
        }
        
        return sb.toString();
    }

    /**
     * Get budget modification classification for reports
     */
    public String getBudgetModificationClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Budget Modification: ").append(getDisplayText()).append("\n");
        sb.append("Type: ").append(getModificationType().replace("_", " ")).append("\n");
        sb.append("Priority: ").append(getModificationPriority().replace("_", " ")).append("\n");
        sb.append("Status: ").append(getModificationStatus().replace("_", " ")).append("\n");
        sb.append("Urgency Level: ").append(getUrgencyLevel().replace("_", " ")).append("\n");
        sb.append("Approval Date: ").append(approvalDate != null ? approvalDate.toString() : "Pending").append("\n");
        sb.append("Days to Approval: ").append(getDaysToApproval() >= 0 ? getDaysToApproval() + " days" : "Pending").append("\n");
        sb.append("Is Pending: ").append(isPending() ? "Yes" : "No").append("\n");
        sb.append("Is Approved: ").append(isApproved() ? "Yes" : "No").append("\n");
        sb.append("Is Scheduled: ").append(isScheduled() ? "Yes" : "No");
        
        return sb.toString();
    }

    /**
     * Get modification impact assessment
     */
    public String getImpactAssessment() {
        String type = getModificationType();
        String priority = getModificationPriority();
        
        StringBuilder sb = new StringBuilder();
        
        // Type-based impact
        switch (type) {
            case "BUDGET_INCREASE":
                sb.append("Positive financial impact, Resource expansion, Enhanced capabilities");
                break;
            case "BUDGET_DECREASE":
                sb.append("Negative financial impact, Resource constraints, Capability limitations");
                break;
            case "BUDGET_REALLOCATION":
                sb.append("Neutral financial impact, Resource redistribution, Priority rebalancing");
                break;
            case "EMERGENCY_MODIFICATION":
                sb.append("Critical operational impact, Immediate resource needs, Priority override");
                break;
            case "CORRECTION_MODIFICATION":
                sb.append("Corrective impact, Error resolution, Process improvement");
                break;
            default:
                sb.append("Standard impact, Routine adjustment, Normal operations");
        }
        
        // Priority-based additional impact
        if ("CRITICAL_PRIORITY".equals(priority)) {
            sb.append(", High organizational impact, Executive attention required");
        } else if ("HIGH_PRIORITY".equals(priority)) {
            sb.append(", Significant impact, Management oversight needed");
        }
        
        return sb.toString();
    }

    /**
     * Get approval requirements
     */
    public String getApprovalRequirements() {
        String priority = getModificationPriority();
        String type = getModificationType();
        
        StringBuilder sb = new StringBuilder();
        
        // Priority-based requirements
        switch (priority) {
            case "CRITICAL_PRIORITY":
                sb.append("Executive approval, Board notification, Immediate processing");
                break;
            case "HIGH_PRIORITY":
                sb.append("Senior management approval, Department head review, Priority processing");
                break;
            case "MEDIUM_PRIORITY":
                sb.append("Management approval, Supervisor review, Standard processing");
                break;
            case "NORMAL_PRIORITY":
                sb.append("Supervisor approval, Standard review, Normal processing");
                break;
            default:
                sb.append("Basic approval, Routine review, Standard processing");
        }
        
        // Type-based additional requirements
        if ("EMERGENCY_MODIFICATION".equals(type)) {
            sb.append(", Emergency protocol activation, Expedited review");
        } else if ("BUDGET_INCREASE".equals(type) || "BUDGET_DECREASE".equals(type)) {
            sb.append(", Financial impact analysis, Budget committee review");
        }
        
        return sb.toString();
    }

    /**
     * Get processing timeline
     */
    public String getProcessingTimeline() {
        String priority = getModificationPriority();
        String type = getModificationType();
        
        StringBuilder sb = new StringBuilder();
        
        // Priority-based timeline
        switch (priority) {
            case "CRITICAL_PRIORITY":
                sb.append("Immediate processing (0-1 days)");
                break;
            case "HIGH_PRIORITY":
                sb.append("Expedited processing (1-3 days)");
                break;
            case "MEDIUM_PRIORITY":
                sb.append("Priority processing (3-7 days)");
                break;
            case "NORMAL_PRIORITY":
                sb.append("Standard processing (7-14 days)");
                break;
            default:
                sb.append("Routine processing (14-30 days)");
        }
        
        // Type-based adjustments
        if ("EMERGENCY_MODIFICATION".equals(type)) {
            sb.append(", Emergency fast-track");
        } else if ("CORRECTION_MODIFICATION".equals(type)) {
            sb.append(", Correction review cycle");
        }
        
        return sb.toString();
    }

    /**
     * Get risk factors
     */
    public String getRiskFactors() {
        String type = getModificationType();
        String status = getModificationStatus();
        
        StringBuilder sb = new StringBuilder();
        
        // Status-based risks
        if ("PENDING_APPROVAL".equals(status)) {
            sb.append("Approval delay risk, Implementation postponement");
        } else if ("SCHEDULED_FOR_APPROVAL".equals(status)) {
            sb.append("Schedule adherence risk, Timing dependency");
        } else {
            sb.append("Implementation risk, Change management challenges");
        }
        
        // Type-based risks
        switch (type) {
            case "EMERGENCY_MODIFICATION":
                sb.append(", High execution risk, Resource strain");
                break;
            case "BUDGET_DECREASE":
                sb.append(", Capability reduction risk, Morale impact");
                break;
            case "BUDGET_REALLOCATION":
                sb.append(", Priority conflicts, Resource disputes");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get success metrics
     */
    public String getSuccessMetrics() {
        String type = getModificationType();
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("Approval timeliness, Implementation accuracy, Stakeholder satisfaction");
        
        // Type-specific metrics
        switch (type) {
            case "BUDGET_INCREASE":
                sb.append(", Resource utilization, Capability enhancement");
                break;
            case "BUDGET_DECREASE":
                sb.append(", Cost savings achieved, Efficiency improvements");
                break;
            case "BUDGET_REALLOCATION":
                sb.append(", Priority alignment, Resource optimization");
                break;
            case "EMERGENCY_MODIFICATION":
                sb.append(", Response time, Crisis resolution");
                break;
            case "CORRECTION_MODIFICATION":
                sb.append(", Error resolution, Process improvement");
                break;
        }
        
        return sb.toString();
    }
}