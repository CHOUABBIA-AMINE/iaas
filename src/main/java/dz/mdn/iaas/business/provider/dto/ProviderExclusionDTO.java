/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusionDTO
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

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Provider Exclusion Class
 * Maps exactly to ProviderExclusion model fields: F_00=id, F_01=startDate, F_02=endDate, F_03=cause, 
 * F_04=exclusionTypeId, F_05=providerId, F_06=referenceId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderExclusionDTO {

    private Long id; // F_00

    @NotNull(message = "Start date is required")
    private Date startDate; // F_01 - required

    private Date endDate; // F_02 - optional

    @Size(max = 1000, message = "Cause must not exceed 1000 characters")
    private String cause; // F_03 - optional

    @NotNull(message = "Exclusion type is required")
    private Long exclusionTypeId; // F_04 - ExclusionType foreign key (required)

    @NotNull(message = "Provider is required")
    private Long providerId; // F_05 - Provider foreign key (required)

    private Long referenceId; // F_06 - Mail foreign key (optional)

    // Related entity DTOs for display (populated when needed)
    private ExclusionTypeDTO exclusionType;
    private ProviderDTO provider;
    private dz.mdn.iaas.common.communication.dto.MailDTO reference;

    /**
     * Create DTO from entity
     */
    public static ProviderExclusionDTO fromEntity(dz.mdn.iaas.business.provider.model.ProviderExclusion providerExclusion) {
        if (providerExclusion == null) return null;
        
        ProviderExclusionDTO.ProviderExclusionDTOBuilder builder = ProviderExclusionDTO.builder()
                .id(providerExclusion.getId())
                .startDate(providerExclusion.getStartDate())
                .endDate(providerExclusion.getEndDate())
                .cause(providerExclusion.getCause());

        // Handle foreign key relationships
        if (providerExclusion.getExclusionType() != null) {
            builder.exclusionTypeId(providerExclusion.getExclusionType().getId());
        }
        if (providerExclusion.getProvider() != null) {
            builder.providerId(providerExclusion.getProvider().getId());
        }
        if (providerExclusion.getReference() != null) {
            builder.referenceId(providerExclusion.getReference().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static ProviderExclusionDTO fromEntityWithRelations(dz.mdn.iaas.business.provider.model.ProviderExclusion providerExclusion) {
        ProviderExclusionDTO dto = fromEntity(providerExclusion);
        if (dto == null) return null;

        // Populate related DTOs
        if (providerExclusion.getExclusionType() != null) {
            dto.setExclusionType(ExclusionTypeDTO.fromEntity(providerExclusion.getExclusionType()));
        }
        if (providerExclusion.getProvider() != null) {
            dto.setProvider(ProviderDTO.fromEntity(providerExclusion.getProvider()));
        }
        if (providerExclusion.getReference() != null) {
            dto.setReference(dz.mdn.iaas.common.communication.dto.MailDTO.fromEntity(providerExclusion.getReference()));
        }

        return dto;
    }

    /**
     * Check if exclusion is currently active
     */
    public boolean isActive() {
        Date now = new Date();
        if (startDate == null || startDate.after(now)) {
            return false;
        }
        return endDate == null || endDate.after(now);
    }

    /**
     * Check if exclusion is permanent (no end date)
     */
    public boolean isPermanent() {
        return endDate == null;
    }

    /**
     * Check if exclusion has expired
     */
    public boolean isExpired() {
        if (endDate == null) {
            return false; // Permanent exclusions don't expire
        }
        Date now = new Date();
        return endDate.before(now);
    }

    /**
     * Check if exclusion is future (not yet started)
     */
    public boolean isFuture() {
        Date now = new Date();
        return startDate != null && startDate.after(now);
    }

    /**
     * Get exclusion status
     */
    public String getExclusionStatus() {
        if (isFuture()) {
            return "FUTURE";
        }
        if (isActive()) {
            return "ACTIVE";
        }
        if (isExpired()) {
            return "EXPIRED";
        }
        return "INACTIVE";
    }

    /**
     * Get exclusion duration in days
     */
    public Long getDurationInDays() {
        if (startDate == null) {
            return null;
        }
        
        Date effectiveEndDate = endDate != null ? endDate : new Date();
        long diffInMillies = Math.abs(effectiveEndDate.getTime() - startDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get remaining days until expiration
     */
    public Long getRemainingDays() {
        if (endDate == null) {
            return null; // Permanent exclusion
        }
        
        Date now = new Date();
        if (endDate.before(now)) {
            return 0L; // Already expired
        }
        
        long diffInMillies = endDate.getTime() - now.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get days since start
     */
    public Long getDaysSinceStart() {
        if (startDate == null) {
            return null;
        }
        
        Date now = new Date();
        if (startDate.after(now)) {
            return 0L; // Future exclusion
        }
        
        long diffInMillies = now.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get exclusion severity based on exclusion type
     */
    public String getExclusionSeverity() {
        if (exclusionType != null) {
            return exclusionType.getSeverityLevel();
        }
        return "UNKNOWN";
    }

    /**
     * Get exclusion category based on exclusion type
     */
    public String getExclusionCategory() {
        if (exclusionType != null) {
            return exclusionType.getExclusionCategory();
        }
        return "UNKNOWN";
    }

    /**
     * Check if exclusion affects public contracts
     */
    public boolean affectsPublicContracts() {
        if (exclusionType != null) {
            return exclusionType.affectsPublicContracts();
        }
        return false;
    }

    /**
     * Check if exclusion requires legal review
     */
    public boolean requiresLegalReview() {
        if (exclusionType != null) {
            return exclusionType.requiresLegalReview();
        }
        return false;
    }

    /**
     * Get exclusion display text
     */
    public String getDisplayText() {
        StringBuilder sb = new StringBuilder();
        
        if (provider != null) {
            sb.append(provider.getDisplayText());
        } else {
            sb.append("Provider ID: ").append(providerId);
        }
        
        sb.append(" - ");
        
        if (exclusionType != null) {
            sb.append(exclusionType.getDisplayText());
        } else {
            sb.append("Exclusion Type ID: ").append(exclusionTypeId);
        }
        
        return sb.toString();
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (exclusionType != null) {
            sb.append(exclusionType.getDisplayText());
        }
        
        sb.append(" (").append(getExclusionStatus()).append(")");
        
        if (isPermanent()) {
            sb.append(" - Permanent");
        } else if (endDate != null) {
            sb.append(" - Until ").append(endDate);
        }
        
        return sb.toString();
    }

    /**
     * Get full display with all information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (provider != null) {
            sb.append("Provider: ").append(provider.getDisplayText()).append("\n");
        }
        
        if (exclusionType != null) {
            sb.append("Type: ").append(exclusionType.getFullDisplay()).append("\n");
        }
        
        sb.append("Status: ").append(getExclusionStatus()).append("\n");
        sb.append("Start: ").append(startDate).append("\n");
        
        if (endDate != null) {
            sb.append("End: ").append(endDate).append("\n");
        } else {
            sb.append("Duration: Permanent\n");
        }
        
        if (cause != null && !cause.trim().isEmpty()) {
            sb.append("Cause: ").append(cause);
        }
        
        return sb.toString();
    }

    /**
     * Get exclusion period description
     */
    public String getPeriodDescription() {
        if (startDate == null) {
            return "Invalid period";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("From ").append(startDate);
        
        if (endDate != null) {
            sb.append(" to ").append(endDate);
            Long duration = getDurationInDays();
            if (duration != null) {
                sb.append(" (").append(duration).append(" days)");
            }
        } else {
            sb.append(" (Permanent)");
        }
        
        return sb.toString();
    }

    /**
     * Get exclusion priority based on type and status
     */
    public Integer getExclusionPriority() {
        int priority = 50; // Default priority
        
        // Adjust based on exclusion type priority
        if (exclusionType != null && exclusionType.getExclusionPriority() != null) {
            priority = exclusionType.getExclusionPriority();
        }
        
        // Adjust based on status
        String status = getExclusionStatus();
        switch (status) {
            case "ACTIVE" -> priority -= 20; // Higher priority for active exclusions
            case "FUTURE" -> priority -= 10; // Medium priority for future exclusions
            case "EXPIRED" -> priority += 30; // Lower priority for expired exclusions
        }
        
        // Adjust based on permanence
        if (isPermanent()) {
            priority -= 15; // Higher priority for permanent exclusions
        }
        
        return Math.max(1, priority); // Ensure priority is at least 1
    }

    /**
     * Get business impact assessment
     */
    public String getBusinessImpact() {
        if (exclusionType != null) {
            String impact = exclusionType.getBusinessImpact();
            if (isActive()) {
                return impact;
            } else if (isExpired()) {
                return "NO_CURRENT_IMPACT";
            } else if (isFuture()) {
                return "FUTURE_" + impact;
            }
        }
        return "UNKNOWN_IMPACT";
    }

    /**
     * Get compliance action required
     */
    public String getComplianceActionRequired() {
        if (!isActive()) {
            return "NO_ACTION_REQUIRED";
        }
        
        if (exclusionType != null) {
            String severity = exclusionType.getSeverityLevel();
            return switch (severity) {
                case "SEVERE" -> "IMMEDIATE_ACTION_REQUIRED";
                case "HIGH" -> "URGENT_ACTION_REQUIRED";
                case "MEDIUM" -> "ACTION_REQUIRED";
                case "LOW" -> "MONITORING_REQUIRED";
                default -> "REVIEW_REQUIRED";
            };
        }
        
        return "REVIEW_REQUIRED";
    }

    /**
     * Get remediation process
     */
    public String getRemediationProcess() {
        if (exclusionType != null) {
            return exclusionType.getRemediationProcess();
        }
        return "COMPLIANCE_VERIFICATION";
    }

    /**
     * Get monitoring requirement
     */
    public String getMonitoringRequirement() {
        if (exclusionType != null) {
            return exclusionType.getMonitoringRequirement();
        }
        return "STANDARD_MONITORING";
    }

    /**
     * Check if exclusion has supporting documentation
     */
    public boolean hasReference() {
        return referenceId != null && reference != null;
    }

    /**
     * Get reference summary
     */
    public String getReferenceSummary() {
        if (reference != null) {
            return "Mail Ref: " + reference.getSubject();
        }
        if (referenceId != null) {
            return "Mail ID: " + referenceId;
        }
        return "No reference";
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ProviderExclusionDTO createSimple(Long id, String displayText) {
        return ProviderExclusionDTO.builder()
                .id(id)
                .build();
    }

    /**
     * Validate business rules
     */
    public boolean isValidBusinessRules() {
        // Start date is required
        if (startDate == null) {
            return false;
        }
        
        // End date must be after start date if provided
        if (endDate != null && endDate.before(startDate)) {
            return false;
        }
        
        // Required foreign keys must be present
        if (exclusionTypeId == null || providerId == null) {
            return false;
        }
        
        return true;
    }

    /**
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (startDate == null) {
            errors.add("Start date is required");
        }
        
        if (endDate != null && startDate != null && endDate.before(startDate)) {
            errors.add("End date must be after start date");
        }
        
        if (exclusionTypeId == null) {
            errors.add("Exclusion type is required");
        }
        
        if (providerId == null) {
            errors.add("Provider is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by priority, then by start date)
     */
    public String getComparisonKey() {
        Integer priority = getExclusionPriority();
        String startDateStr = startDate != null ? startDate.toString() : "9999-12-31";
        return String.format("%03d_%s", priority, startDateStr);
    }

    /**
     * Get exclusion summary for reports
     */
    public String getExclusionSummary() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Exclusion: ").append(getDisplayText()).append("\n");
        sb.append("Period: ").append(getPeriodDescription()).append("\n");
        sb.append("Status: ").append(getExclusionStatus()).append("\n");
        sb.append("Severity: ").append(getExclusionSeverity()).append("\n");
        
        if (cause != null && !cause.trim().isEmpty()) {
            sb.append("Cause: ").append(cause).append("\n");
        }
        
        sb.append("Business Impact: ").append(getBusinessImpact()).append("\n");
        sb.append("Action Required: ").append(getComplianceActionRequired());
        
        return sb.toString();
    }

    /**
     * Get exclusion alert level
     */
    public String getAlertLevel() {
        if (!isActive()) {
            return "NONE";
        }
        
        String severity = getExclusionSeverity();
        return switch (severity) {
            case "SEVERE" -> "CRITICAL";
            case "HIGH" -> "HIGH";
            case "MEDIUM" -> "MEDIUM";
            case "LOW" -> "LOW";
            default -> "INFO";
        };
    }

    /**
     * Check if exclusion is close to expiration (within 30 days)
     */
    public boolean isCloseToExpiration() {
        Long remaining = getRemainingDays();
        return remaining != null && remaining > 0 && remaining <= 30;
    }

    /**
     * Get expiration warning
     */
    public String getExpirationWarning() {
        if (isPermanent()) {
            return "Permanent exclusion - no expiration";
        }
        
        if (isExpired()) {
            return "Exclusion has expired";
        }
        
        Long remaining = getRemainingDays();
        if (remaining == null) {
            return "No expiration date set";
        }
        
        if (remaining <= 0) {
            return "Exclusion expires today";
        }
        
        if (remaining <= 7) {
            return "Exclusion expires in " + remaining + " days - URGENT";
        }
        
        if (remaining <= 30) {
            return "Exclusion expires in " + remaining + " days - WARNING";
        }
        
        return "Exclusion expires in " + remaining + " days";
    }

    /**
     * Get formatted display for contracts and official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (provider != null) {
            sb.append("Provider: ").append(provider.getFormalDisplay());
        }
        
        sb.append(" | Exclusion: ");
        if (exclusionType != null) {
            sb.append(exclusionType.getFormalExclusionDisplay());
        }
        
        sb.append(" | Status: ").append(getExclusionStatus());
        sb.append(" | Period: ").append(getPeriodDescription());
        
        return sb.toString();
    }
}
