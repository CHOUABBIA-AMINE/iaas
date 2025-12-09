/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementStatusDTO
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
 * ProcurementStatus Data Transfer Object
 * Maps exactly to ProcurementStatus model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcurementStatusDTO {

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
    public static ProcurementStatusDTO fromEntity(dz.mdn.iaas.business.core.model.ProcurementStatus procurementStatus) {
        if (procurementStatus == null) return null;
        
        return ProcurementStatusDTO.builder()
                .id(procurementStatus.getId())
                .designationAr(procurementStatus.getDesignationAr())
                .designationEn(procurementStatus.getDesignationEn())
                .designationFr(procurementStatus.getDesignationFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public dz.mdn.iaas.business.core.model.ProcurementStatus toEntity() {
        dz.mdn.iaas.business.core.model.ProcurementStatus procurementStatus = new dz.mdn.iaas.business.core.model.ProcurementStatus();
        procurementStatus.setId(this.id);
        procurementStatus.setDesignationAr(this.designationAr);
        procurementStatus.setDesignationEn(this.designationEn);
        procurementStatus.setDesignationFr(this.designationFr);
        return procurementStatus;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(dz.mdn.iaas.business.core.model.ProcurementStatus procurementStatus) {
        if (this.designationAr != null) {
            procurementStatus.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            procurementStatus.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            procurementStatus.setDesignationFr(this.designationFr);
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
     * Check if procurement status has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this procurement status
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
     * Get procurement status category based on French designation analysis
     */
    public String getStatusCategory() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Initialization and planning statuses
        if (designation.contains("initial") || designation.contains("planification") || 
            designation.contains("préparation") || designation.contains("conception")) {
            return "PLANNING";
        }
        
        // Active execution statuses
        if (designation.contains("en cours") || designation.contains("actif") || 
            designation.contains("exécution") || designation.contains("réalisation")) {
            return "IN_PROGRESS";
        }
        
        // Completion statuses
        if (designation.contains("terminé") || designation.contains("achevé") || 
            designation.contains("complété") || designation.contains("finalisé")) {
            return "COMPLETED";
        }
        
        // Suspended or paused statuses
        if (designation.contains("suspendu") || designation.contains("en pause") || 
            designation.contains("interrompu") || designation.contains("gelé")) {
            return "SUSPENDED";
        }
        
        // Cancelled statuses
        if (designation.contains("annulé") || designation.contains("abandonné") || 
            designation.contains("arrêté") || designation.contains("supprimé")) {
            return "CANCELLED";
        }
        
        // Review and validation statuses
        if (designation.contains("révision") || designation.contains("validation") || 
            designation.contains("vérification") || designation.contains("contrôle")) {
            return "UNDER_REVIEW";
        }
        
        // Approval statuses
        if (designation.contains("approuvé") || designation.contains("validé") || 
            designation.contains("accepté") || designation.contains("autorisé")) {
            return "APPROVED";
        }
        
        // Rejected statuses
        if (designation.contains("rejeté") || designation.contains("refusé") || 
            designation.contains("non approuvé") || designation.contains("declined")) {
            return "REJECTED";
        }
        
        // On hold statuses
        if (designation.contains("en attente") || designation.contains("standby") || 
            designation.contains("différé") || designation.contains("reporté")) {
            return "ON_HOLD";
        }
        
        return "ACTIVE";
    }

    /**
     * Check if this is an active status
     */
    public boolean isActive() {
        String category = getStatusCategory();
        return "PLANNING".equals(category) || "IN_PROGRESS".equals(category) || 
               "UNDER_REVIEW".equals(category) || "APPROVED".equals(category) ||
               "ACTIVE".equals(category);
    }

    /**
     * Check if this is a final status (completed, cancelled, rejected)
     */
    public boolean isFinal() {
        String category = getStatusCategory();
        return "COMPLETED".equals(category) || "CANCELLED".equals(category) || "REJECTED".equals(category);
    }

    /**
     * Check if this is a completion status
     */
    public boolean isCompleted() {
        return "COMPLETED".equals(getStatusCategory());
    }

    /**
     * Check if this is a suspension status
     */
    public boolean isSuspended() {
        return "SUSPENDED".equals(getStatusCategory());
    }

    /**
     * Check if this is an in-progress status
     */
    public boolean isInProgress() {
        return "IN_PROGRESS".equals(getStatusCategory());
    }

    /**
     * Get status phase in project lifecycle
     */
    public String getProjectPhase() {
        return switch (getStatusCategory()) {
            case "PLANNING" -> "INITIATION";
            case "APPROVED" -> "INITIATION";
            case "IN_PROGRESS" -> "EXECUTION";
            case "UNDER_REVIEW" -> "MONITORING";
            case "COMPLETED" -> "CLOSURE";
            case "CANCELLED", "REJECTED" -> "TERMINATION";
            case "SUSPENDED", "ON_HOLD" -> "CONTROL";
            default -> "EXECUTION";
        };
    }

    /**
     * Get status priority for project management
     */
    public int getStatusPriority() {
        return switch (getStatusCategory()) {
            case "PLANNING" -> 1;
            case "APPROVED" -> 2;
            case "IN_PROGRESS" -> 3;
            case "UNDER_REVIEW" -> 4;
            case "ON_HOLD" -> 5;
            case "SUSPENDED" -> 6;
            case "COMPLETED" -> 7;
            case "CANCELLED" -> 8;
            case "REJECTED" -> 9;
            default -> 10;
        };
    }

    /**
     * Get status color for UI display
     */
    public String getStatusColor() {
        return switch (getStatusCategory()) {
            case "PLANNING" -> "BLUE";
            case "APPROVED" -> "GREEN";
            case "IN_PROGRESS" -> "ORANGE";
            case "UNDER_REVIEW" -> "PURPLE";
            case "COMPLETED" -> "DARK_GREEN";
            case "SUSPENDED" -> "YELLOW";
            case "ON_HOLD" -> "GRAY";
            case "CANCELLED" -> "RED";
            case "REJECTED" -> "DARK_RED";
            default -> "BLACK";
        };
    }

    /**
     * Get typical duration for this status type
     */
    public String getTypicalDuration() {
        return switch (getStatusCategory()) {
            case "PLANNING" -> "WEEKS";
            case "APPROVED" -> "DAYS";
            case "IN_PROGRESS" -> "MONTHS";
            case "UNDER_REVIEW" -> "WEEKS";
            case "ON_HOLD" -> "VARIABLE";
            case "SUSPENDED" -> "VARIABLE";
            case "COMPLETED", "CANCELLED", "REJECTED" -> "PERMANENT";
            default -> "WEEKS";
        };
    }

    /**
     * Check if status allows transitions
     */
    public boolean allowsTransition() {
        String category = getStatusCategory();
        return !"COMPLETED".equals(category) && !"CANCELLED".equals(category) && !"REJECTED".equals(category);
    }

    /**
     * Get next possible statuses
     */
    public String[] getNextPossibleStatuses() {
        return switch (getStatusCategory()) {
            case "PLANNING" -> new String[]{"APPROVED", "REJECTED", "ON_HOLD"};
            case "APPROVED" -> new String[]{"IN_PROGRESS", "SUSPENDED", "CANCELLED"};
            case "IN_PROGRESS" -> new String[]{"UNDER_REVIEW", "COMPLETED", "SUSPENDED", "ON_HOLD"};
            case "UNDER_REVIEW" -> new String[]{"APPROVED", "REJECTED", "IN_PROGRESS"};
            case "ON_HOLD" -> new String[]{"IN_PROGRESS", "CANCELLED"};
            case "SUSPENDED" -> new String[]{"IN_PROGRESS", "CANCELLED"};
            default -> new String[]{};
        };
    }

    /**
     * Get milestone type for this status
     */
    public String getMilestoneType() {
        return switch (getStatusCategory()) {
            case "PLANNING" -> "START_MILESTONE";
            case "APPROVED" -> "APPROVAL_MILESTONE";
            case "IN_PROGRESS" -> "PROGRESS_MILESTONE";
            case "UNDER_REVIEW" -> "REVIEW_MILESTONE";
            case "COMPLETED" -> "COMPLETION_MILESTONE";
            case "CANCELLED", "REJECTED" -> "TERMINATION_MILESTONE";
            default -> "INTERMEDIATE_MILESTONE";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ProcurementStatusDTO createSimple(Long id, String designationFr) {
        return ProcurementStatusDTO.builder()
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
        return designationFr != null && designationFr.length() > 30 ? 
                designationFr.substring(0, 30) + "..." : designationFr;
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
     * Get display with status category
     */
    public String getDisplayWithCategory() {
        return designationFr + " (" + getStatusCategory().replace("_", " ").toLowerCase() + ")";
    }

    /**
     * Get progress percentage estimate based on status
     */
    public int getProgressPercentage() {
        return switch (getStatusCategory()) {
            case "PLANNING" -> 10;
            case "APPROVED" -> 20;
            case "IN_PROGRESS" -> 50;
            case "UNDER_REVIEW" -> 80;
            case "COMPLETED" -> 100;
            case "SUSPENDED", "ON_HOLD" -> -1; // Undefined progress
            case "CANCELLED", "REJECTED" -> 0;
            default -> 25;
        };
    }

    /**
     * Check if status requires documentation
     */
    public boolean requiresDocumentation() {
        String category = getStatusCategory();
        return "COMPLETED".equals(category) || "CANCELLED".equals(category) || 
               "REJECTED".equals(category) || "UNDER_REVIEW".equals(category);
    }

    /**
     * Get notification level for status changes
     */
    public String getNotificationLevel() {
        return switch (getStatusCategory()) {
            case "COMPLETED" -> "HIGH";
            case "CANCELLED", "REJECTED" -> "CRITICAL";
            case "SUSPENDED" -> "HIGH";
            case "APPROVED" -> "MEDIUM";
            case "IN_PROGRESS" -> "LOW";
            default -> "MEDIUM";
        };
    }
}
