/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemStatusDTO
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
 * Item Status Data Transfer Object
 * Maps exactly to ItemStatus model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemStatusDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required, unique

    /**
     * Create DTO from entity
     */
    public static ItemStatusDTO fromEntity(dz.mdn.iaas.business.plan.model.ItemStatus itemStatus) {
        if (itemStatus == null) return null;
        
        return ItemStatusDTO.builder()
                .id(itemStatus.getId())
                .designationAr(itemStatus.getDesignationAr())
                .designationEn(itemStatus.getDesignationEn())
                .designationFr(itemStatus.getDesignationFr())
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
     * Get display text with priority: French designation > English designation > Arabic designation
     */
    public String getDisplayText() {
        return getDefaultDesignation();
    }

    /**
     * Check if item status has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this item status
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
     * Get item status category based on designation keywords
     */
    public String getStatusCategory() {
        String designation = getDefaultDesignation().toLowerCase();
        
        // Active/Available statuses
        if (designation.contains("actif") || designation.contains("active") ||
            designation.contains("disponible") || designation.contains("available") ||
            designation.contains("en stock") || designation.contains("in stock") ||
            designation.contains("متاح") || designation.contains("نشط")) {
            return "ACTIVE_STATUS";
        }
        
        // Pending/In Progress statuses
        if (designation.contains("en cours") || designation.contains("pending") ||
            designation.contains("attente") || designation.contains("waiting") ||
            designation.contains("traitement") || designation.contains("processing") ||
            designation.contains("قيد المعالجة") || designation.contains("في الانتظار")) {
            return "PENDING_STATUS";
        }
        
        // Reserved/Allocated statuses
        if (designation.contains("réservé") || designation.contains("reserved") ||
            designation.contains("alloué") || designation.contains("allocated") ||
            designation.contains("assigné") || designation.contains("assigned") ||
            designation.contains("محجوز") || designation.contains("مخصص")) {
            return "RESERVED_STATUS";
        }
        
        // Maintenance/Repair statuses
        if (designation.contains("maintenance") || designation.contains("réparation") ||
            designation.contains("repair") || designation.contains("entretien") ||
            designation.contains("révision") || designation.contains("revision") ||
            designation.contains("صيانة") || designation.contains("إصلاح")) {
            return "MAINTENANCE_STATUS";
        }
        
        // Damaged/Defective statuses
        if (designation.contains("endommagé") || designation.contains("damaged") ||
            designation.contains("défectueux") || designation.contains("defective") ||
            designation.contains("cassé") || designation.contains("broken") ||
            designation.contains("تالف") || designation.contains("معطل")) {
            return "DAMAGED_STATUS";
        }
        
        // Obsolete/Retired statuses
        if (designation.contains("obsolète") || designation.contains("obsolete") ||
            designation.contains("retiré") || designation.contains("retired") ||
            designation.contains("périmé") || designation.contains("expired") ||
            designation.contains("منتهي الصلاحية") || designation.contains("مستبعد")) {
            return "OBSOLETE_STATUS";
        }
        
        // Disposed/Scrapped statuses
        if (designation.contains("éliminé") || designation.contains("disposed") ||
            designation.contains("mis au rebut") || designation.contains("scrapped") ||
            designation.contains("détruit") || designation.contains("destroyed") ||
            designation.contains("مُتخلص منه") || designation.contains("مدمر")) {
            return "DISPOSED_STATUS";
        }
        
        // Lost/Missing statuses
        if (designation.contains("perdu") || designation.contains("lost") ||
            designation.contains("manquant") || designation.contains("missing") ||
            designation.contains("introuvable") || designation.contains("not found") ||
            designation.contains("مفقود") || designation.contains("غائب")) {
            return "LOST_STATUS";
        }
        
        // On Order/Procurement statuses
        if (designation.contains("commandé") || designation.contains("ordered") ||
            designation.contains("en commande") || designation.contains("on order") ||
            designation.contains("approvisionnement") || designation.contains("procurement") ||
            designation.contains("مطلوب") || designation.contains("قيد الطلب")) {
            return "PROCUREMENT_STATUS";
        }
        
        return "GENERAL_STATUS";
    }

    /**
     * Get status priority level based on category
     */
    public String getStatusPriority() {
        String category = getStatusCategory();
        
        switch (category) {
            case "DAMAGED_STATUS":
            case "LOST_STATUS":
                return "CRITICAL_PRIORITY";
            case "MAINTENANCE_STATUS":
            case "PENDING_STATUS":
                return "HIGH_PRIORITY";
            case "RESERVED_STATUS":
            case "PROCUREMENT_STATUS":
                return "MEDIUM_PRIORITY";
            case "ACTIVE_STATUS":
                return "NORMAL_PRIORITY";
            case "OBSOLETE_STATUS":
            case "DISPOSED_STATUS":
                return "LOW_PRIORITY";
            default:
                return "NORMAL_PRIORITY";
        }
    }

    /**
     * Get status operational impact
     */
    public String getOperationalImpact() {
        String category = getStatusCategory();
        
        switch (category) {
            case "ACTIVE_STATUS":
                return "OPERATIONAL_READY";
            case "RESERVED_STATUS":
                return "TEMPORARILY_UNAVAILABLE";
            case "MAINTENANCE_STATUS":
                return "UNDER_MAINTENANCE";
            case "DAMAGED_STATUS":
                return "NON_OPERATIONAL";
            case "LOST_STATUS":
                return "MISSING_FROM_INVENTORY";
            case "OBSOLETE_STATUS":
                return "END_OF_LIFE";
            case "DISPOSED_STATUS":
                return "REMOVED_FROM_SERVICE";
            case "PENDING_STATUS":
                return "STATUS_PENDING";
            case "PROCUREMENT_STATUS":
                return "AWAITING_DELIVERY";
            default:
                return "UNKNOWN_IMPACT";
        }
    }

    /**
     * Check if status allows item usage
     */
    public boolean allowsUsage() {
        String category = getStatusCategory();
        return "ACTIVE_STATUS".equals(category);
    }

    /**
     * Check if status requires action
     */
    public boolean requiresAction() {
        String category = getStatusCategory();
        return "DAMAGED_STATUS".equals(category) || 
               "LOST_STATUS".equals(category) ||
               "MAINTENANCE_STATUS".equals(category) ||
               "PENDING_STATUS".equals(category);
    }

    /**
     * Check if status indicates item availability
     */
    public boolean isAvailable() {
        String category = getStatusCategory();
        return "ACTIVE_STATUS".equals(category);
    }

    /**
     * Get status workflow stage
     */
    public String getWorkflowStage() {
        String category = getStatusCategory();
        
        switch (category) {
            case "PROCUREMENT_STATUS":
                return "ACQUISITION_STAGE";
            case "PENDING_STATUS":
                return "PROCESSING_STAGE";
            case "ACTIVE_STATUS":
                return "OPERATIONAL_STAGE";
            case "RESERVED_STATUS":
                return "ALLOCATION_STAGE";
            case "MAINTENANCE_STATUS":
                return "SERVICE_STAGE";
            case "DAMAGED_STATUS":
                return "REPAIR_STAGE";
            case "OBSOLETE_STATUS":
                return "RETIREMENT_STAGE";
            case "DISPOSED_STATUS":
                return "DISPOSAL_STAGE";
            case "LOST_STATUS":
                return "INVESTIGATION_STAGE";
            default:
                return "UNKNOWN_STAGE";
        }
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return getDisplayText();
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
     * Get status display with category and impact
     */
    public String getStatusDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String category = getStatusCategory();
        if (!"GENERAL_STATUS".equals(category)) {
            sb.append(" - ").append(category.replace("_", " "));
        }
        
        String impact = getOperationalImpact();
        if (!"UNKNOWN_IMPACT".equals(impact)) {
            sb.append(" [").append(impact.replace("_", " ")).append("]");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ItemStatusDTO createSimple(Long id, String designationFr) {
        return ItemStatusDTO.builder()
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
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
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
     * Get status classification for reports
     */
    public String getStatusClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Status: ").append(getDisplayText()).append("\n");
        sb.append("Category: ").append(getStatusCategory().replace("_", " ")).append("\n");
        sb.append("Priority: ").append(getStatusPriority().replace("_", " ")).append("\n");
        sb.append("Operational Impact: ").append(getOperationalImpact().replace("_", " ")).append("\n");
        sb.append("Workflow Stage: ").append(getWorkflowStage().replace("_", " ")).append("\n");
        sb.append("Allows Usage: ").append(allowsUsage() ? "Yes" : "No").append("\n");
        sb.append("Requires Action: ").append(requiresAction() ? "Yes" : "No").append("\n");
        sb.append("Available: ").append(isAvailable() ? "Yes" : "No");
        
        return sb.toString();
    }

    /**
     * Get status usage context
     */
    public String getStatusUsageContext() {
        String category = getStatusCategory();
        
        return switch (category) {
            case "ACTIVE_STATUS" -> "Item is operational and ready for use";
            case "PENDING_STATUS" -> "Item status is being processed or awaiting decision";
            case "RESERVED_STATUS" -> "Item is allocated but not currently in use";
            case "MAINTENANCE_STATUS" -> "Item is undergoing maintenance or repair";
            case "DAMAGED_STATUS" -> "Item requires repair before use";
            case "OBSOLETE_STATUS" -> "Item has reached end of useful life";
            case "DISPOSED_STATUS" -> "Item has been removed from inventory";
            case "LOST_STATUS" -> "Item cannot be located in inventory";
            case "PROCUREMENT_STATUS" -> "Item is on order or being procured";
            default -> "General status classification";
        };
    }

    /**
     * Get status monitoring requirements
     */
    public String getMonitoringRequirements() {
        String priority = getStatusPriority();
        
        return switch (priority) {
            case "CRITICAL_PRIORITY" -> "IMMEDIATE_MONITORING";
            case "HIGH_PRIORITY" -> "DAILY_MONITORING";
            case "MEDIUM_PRIORITY" -> "WEEKLY_MONITORING";
            case "NORMAL_PRIORITY" -> "MONTHLY_MONITORING";
            default -> "PERIODIC_MONITORING";
        };
    }

    /**
     * Get recommended actions based on status
     */
    public String getRecommendedActions() {
        String category = getStatusCategory();
        
        return switch (category) {
            case "ACTIVE_STATUS" -> "Continue monitoring for maintenance needs";
            case "PENDING_STATUS" -> "Follow up on status determination";
            case "RESERVED_STATUS" -> "Track allocation and usage schedule";
            case "MAINTENANCE_STATUS" -> "Monitor maintenance progress and completion";
            case "DAMAGED_STATUS" -> "Assess repair feasibility and schedule service";
            case "OBSOLETE_STATUS" -> "Plan replacement and disposal procedures";
            case "DISPOSED_STATUS" -> "Update inventory records and documentation";
            case "LOST_STATUS" -> "Conduct investigation and update security protocols";
            case "PROCUREMENT_STATUS" -> "Track delivery schedule and prepare for receipt";
            default -> "Review status and determine appropriate actions";
        };
    }

    /**
     * Get status reporting frequency
     */
    public String getReportingFrequency() {
        String priority = getStatusPriority();
        
        return switch (priority) {
            case "CRITICAL_PRIORITY" -> "REAL_TIME_REPORTING";
            case "HIGH_PRIORITY" -> "DAILY_REPORTING";
            case "MEDIUM_PRIORITY" -> "WEEKLY_REPORTING";
            case "NORMAL_PRIORITY" -> "MONTHLY_REPORTING";
            default -> "QUARTERLY_REPORTING";
        };
    }

    /**
     * Get alert level for monitoring systems
     */
    public String getAlertLevel() {
        String category = getStatusCategory();
        
        return switch (category) {
            case "DAMAGED_STATUS", "LOST_STATUS" -> "CRITICAL_ALERT";
            case "MAINTENANCE_STATUS", "PENDING_STATUS" -> "WARNING_ALERT";
            case "RESERVED_STATUS", "PROCUREMENT_STATUS" -> "INFO_ALERT";
            default -> "NO_ALERT";
        };
    }

    /**
     * Get status transition possibilities
     */
    public String[] getPossibleTransitions() {
        String category = getStatusCategory();
        
        return switch (category) {
            case "ACTIVE_STATUS" -> new String[]{"RESERVED_STATUS", "MAINTENANCE_STATUS", "DAMAGED_STATUS", "OBSOLETE_STATUS"};
            case "PENDING_STATUS" -> new String[]{"ACTIVE_STATUS", "RESERVED_STATUS", "DAMAGED_STATUS"};
            case "RESERVED_STATUS" -> new String[]{"ACTIVE_STATUS", "MAINTENANCE_STATUS"};
            case "MAINTENANCE_STATUS" -> new String[]{"ACTIVE_STATUS", "DAMAGED_STATUS", "OBSOLETE_STATUS"};
            case "DAMAGED_STATUS" -> new String[]{"ACTIVE_STATUS", "MAINTENANCE_STATUS", "OBSOLETE_STATUS", "DISPOSED_STATUS"};
            case "OBSOLETE_STATUS" -> new String[]{"DISPOSED_STATUS"};
            case "PROCUREMENT_STATUS" -> new String[]{"ACTIVE_STATUS", "PENDING_STATUS"};
            case "LOST_STATUS" -> new String[]{"ACTIVE_STATUS", "DISPOSED_STATUS"};
            default -> new String[]{"ACTIVE_STATUS"};
        };
    }

    /**
     * Check if transition to another status is valid
     */
    public boolean canTransitionTo(String targetCategory) {
        String[] possibleTransitions = getPossibleTransitions();
        for (String transition : possibleTransitions) {
            if (transition.equals(targetCategory)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get status lifecycle stage
     */
    public String getLifecycleStage() {
        String category = getStatusCategory();
        
        return switch (category) {
            case "PROCUREMENT_STATUS" -> "PRE_SERVICE";
            case "PENDING_STATUS", "ACTIVE_STATUS", "RESERVED_STATUS" -> "IN_SERVICE";
            case "MAINTENANCE_STATUS", "DAMAGED_STATUS" -> "SERVICE_INTERRUPTION";
            case "OBSOLETE_STATUS", "DISPOSED_STATUS", "LOST_STATUS" -> "END_OF_SERVICE";
            default -> "UNDEFINED_STAGE";
        };
    }

    /**
     * Get inventory management impact
     */
    public String getInventoryImpact() {
        String category = getStatusCategory();
        
        return switch (category) {
            case "ACTIVE_STATUS" -> "AVAILABLE_FOR_USE";
            case "RESERVED_STATUS" -> "ALLOCATED_NOT_AVAILABLE";
            case "MAINTENANCE_STATUS" -> "TEMPORARILY_UNAVAILABLE";
            case "DAMAGED_STATUS" -> "OUT_OF_SERVICE_REPAIRABLE";
            case "OBSOLETE_STATUS" -> "OUT_OF_SERVICE_PERMANENT";
            case "DISPOSED_STATUS" -> "REMOVED_FROM_INVENTORY";
            case "LOST_STATUS" -> "MISSING_FROM_INVENTORY";
            case "PROCUREMENT_STATUS" -> "EXPECTED_ADDITION";
            case "PENDING_STATUS" -> "STATUS_UNDER_REVIEW";
            default -> "UNKNOWN_INVENTORY_IMPACT";
        };
    }
}
