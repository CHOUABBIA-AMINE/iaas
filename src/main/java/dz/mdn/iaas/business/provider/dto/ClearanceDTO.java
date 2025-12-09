/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceDTO
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
 * Clearance Data Transfer Object
 * Maps exactly to Clearance model fields: F_00=id, F_01=startDate, F_02=endDate, 
 * F_03=providerId, F_04=providerRepresentatorId, F_05=referenceId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClearanceDTO {

    private Long id; // F_00

    private Date startDate; // F_01 - optional

    private Date endDate; // F_02 - optional

    @NotNull(message = "Provider is required")
    private Long providerId; // F_03 - Provider foreign key (required)

    @NotNull(message = "Provider representator is required")
    private Long providerRepresentatorId; // F_04 - ProviderRepresentator foreign key (required)

    private Long referenceId; // F_05 - Mail foreign key (optional)

    // Related entity DTOs for display (populated when needed)
    private ProviderDTO provider;
    private ProviderRepresentatorDTO providerRepresentator;
    private dz.mdn.iaas.common.communication.dto.MailDTO reference;

    /**
     * Create DTO from entity
     */
    public static ClearanceDTO fromEntity(dz.mdn.iaas.business.provider.model.Clearance clearance) {
        if (clearance == null) return null;
        
        ClearanceDTO.ClearanceDTOBuilder builder = ClearanceDTO.builder()
                .id(clearance.getId())
                .startDate(clearance.getStartDate())
                .endDate(clearance.getEndDate());

        // Handle foreign key relationships
        if (clearance.getProvider() != null) {
            builder.providerId(clearance.getProvider().getId());
        }
        if (clearance.getProviderRepresentator() != null) {
            builder.providerRepresentatorId(clearance.getProviderRepresentator().getId());
        }
        if (clearance.getReference() != null) {
            builder.referenceId(clearance.getReference().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static ClearanceDTO fromEntityWithRelations(dz.mdn.iaas.business.provider.model.Clearance clearance) {
        ClearanceDTO dto = fromEntity(clearance);
        if (dto == null) return null;

        // Populate related DTOs
        if (clearance.getProvider() != null) {
            dto.setProvider(ProviderDTO.fromEntity(clearance.getProvider()));
        }
        if (clearance.getProviderRepresentator() != null) {
            dto.setProviderRepresentator(ProviderRepresentatorDTO.fromEntity(clearance.getProviderRepresentator()));
        }
        if (clearance.getReference() != null) {
            dto.setReference(dz.mdn.iaas.common.communication.dto.MailDTO.fromEntity(clearance.getReference()));
        }

        return dto;
    }

    /**
     * Check if clearance is currently active
     */
    public boolean isActive() {
        Date now = new Date();
        
        // If no start date, consider inactive
        if (startDate != null && startDate.after(now)) {
            return false; // Future clearance
        }
        
        // If no end date, active indefinitely (if started)
        if (endDate == null) {
            return startDate == null || !startDate.after(now);
        }
        
        // Check if within validity period
        return endDate.after(now) && (startDate == null || !startDate.after(now));
    }

    /**
     * Check if clearance is permanent (no end date)
     */
    public boolean isPermanent() {
        return endDate == null;
    }

    /**
     * Check if clearance has expired
     */
    public boolean isExpired() {
        if (endDate == null) {
            return false; // Permanent clearances don't expire
        }
        Date now = new Date();
        return endDate.before(now);
    }

    /**
     * Check if clearance is future (not yet started)
     */
    public boolean isFuture() {
        if (startDate == null) {
            return false; // No start date means immediate
        }
        Date now = new Date();
        return startDate.after(now);
    }

    /**
     * Get clearance status
     */
    public String getClearanceStatus() {
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
     * Get clearance validity duration in days
     */
    public Long getValidityDurationInDays() {
        if (startDate == null || endDate == null) {
            return null; // Cannot calculate duration
        }
        
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get remaining validity days
     */
    public Long getRemainingValidityDays() {
        if (endDate == null) {
            return null; // Permanent clearance
        }
        
        Date now = new Date();
        if (endDate.before(now)) {
            return 0L; // Already expired
        }
        
        long diffInMillies = endDate.getTime() - now.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get days since clearance started
     */
    public Long getDaysSinceStart() {
        if (startDate == null) {
            return null; // No start date
        }
        
        Date now = new Date();
        if (startDate.after(now)) {
            return 0L; // Future clearance
        }
        
        long diffInMillies = now.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get clearance type based on duration
     */
    public String getClearanceType() {
        if (isPermanent()) {
            return "PERMANENT_CLEARANCE";
        }
        
        Long duration = getValidityDurationInDays();
        if (duration == null) {
            return "INDEFINITE_CLEARANCE";
        }
        
        if (duration <= 30) {
            return "SHORT_TERM_CLEARANCE"; // Up to 30 days
        } else if (duration <= 365) {
            return "MEDIUM_TERM_CLEARANCE"; // Up to 1 year
        } else {
            return "LONG_TERM_CLEARANCE"; // More than 1 year
        }
    }

    /**
     * Get clearance priority based on status and remaining time
     */
    public Integer getClearancePriority() {
        String status = getClearanceStatus();
        
        switch (status) {
            case "ACTIVE":
                Long remaining = getRemainingValidityDays();
                if (remaining != null && remaining <= 7) {
                    return 1; // Expiring soon - highest priority
                } else if (remaining != null && remaining <= 30) {
                    return 2; // Expiring within month - high priority
                }
                return 3; // Active - normal priority
            case "FUTURE":
                return 4; // Future - medium priority
            case "EXPIRED":
                return 6; // Expired - low priority
            default:
                return 5; // Inactive - lower priority
        }
    }

    /**
     * Check if clearance is close to expiration (within 30 days)
     */
    public boolean isCloseToExpiration() {
        Long remaining = getRemainingValidityDays();
        return remaining != null && remaining > 0 && remaining <= 30;
    }

    /**
     * Check if clearance requires urgent renewal (within 7 days or expired)
     */
    public boolean requiresUrgentRenewal() {
        Long remaining = getRemainingValidityDays();
        return remaining != null && remaining <= 7;
    }

    /**
     * Get clearance display text
     */
    public String getDisplayText() {
        StringBuilder sb = new StringBuilder();
        
        if (provider != null) {
            sb.append(provider.getDisplayText());
        } else {
            sb.append("Provider ID: ").append(providerId);
        }
        
        sb.append(" - ");
        
        if (providerRepresentator != null) {
            sb.append(providerRepresentator.getFullName());
        } else {
            sb.append("Representator ID: ").append(providerRepresentatorId);
        }
        
        return sb.toString();
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (providerRepresentator != null) {
            sb.append(providerRepresentator.getFullName());
        }
        
        sb.append(" (").append(getClearanceStatus()).append(")");
        
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
        
        if (providerRepresentator != null) {
            sb.append("Representator: ").append(providerRepresentator.getFullName());
            if (providerRepresentator.getJobTitle() != null) {
                sb.append(" (").append(providerRepresentator.getJobTitle()).append(")");
            }
            sb.append("\n");
        }
        
        sb.append("Status: ").append(getClearanceStatus()).append("\n");
        
        if (startDate != null) {
            sb.append("Start: ").append(startDate).append("\n");
        }
        
        if (endDate != null) {
            sb.append("End: ").append(endDate).append("\n");
        } else {
            sb.append("Duration: Permanent\n");
        }
        
        Long remaining = getRemainingValidityDays();
        if (remaining != null) {
            sb.append("Remaining: ").append(remaining).append(" days");
        }
        
        return sb.toString();
    }

    /**
     * Get clearance period description
     */
    public String getPeriodDescription() {
        StringBuilder sb = new StringBuilder();
        
        if (startDate != null) {
            sb.append("From ").append(startDate);
        } else {
            sb.append("From immediate");
        }
        
        if (endDate != null) {
            sb.append(" to ").append(endDate);
            Long duration = getValidityDurationInDays();
            if (duration != null) {
                sb.append(" (").append(duration).append(" days)");
            }
        } else {
            sb.append(" (Permanent)");
        }
        
        return sb.toString();
    }

    /**
     * Get clearance validity assessment
     */
    public String getValidityAssessment() {
        String status = getClearanceStatus();
        
        switch (status) {
            case "ACTIVE":
                Long remaining = getRemainingValidityDays();
                if (remaining == null) {
                    return "Valid indefinitely";
                } else if (remaining <= 0) {
                    return "Expires today";
                } else if (remaining <= 7) {
                    return "Expires in " + remaining + " days - URGENT renewal required";
                } else if (remaining <= 30) {
                    return "Expires in " + remaining + " days - Renewal recommended";
                } else {
                    return "Valid for " + remaining + " more days";
                }
            case "FUTURE":
                Long daysUntilStart = getDaysUntilStart();
                return "Becomes active in " + (daysUntilStart != null ? daysUntilStart : "unknown") + " days";
            case "EXPIRED":
                Long expiredDays = getDaysExpired();
                return "Expired " + (expiredDays != null ? expiredDays : "unknown") + " days ago";
            default:
                return "Status unclear - verification needed";
        }
    }

    /**
     * Get days until clearance starts (for future clearances)
     */
    public Long getDaysUntilStart() {
        if (startDate == null) {
            return null;
        }
        
        Date now = new Date();
        if (startDate.before(now)) {
            return 0L; // Already started
        }
        
        long diffInMillies = startDate.getTime() - now.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get days since expiration (for expired clearances)
     */
    public Long getDaysExpired() {
        if (endDate == null) {
            return null; // Permanent clearance
        }
        
        Date now = new Date();
        if (endDate.after(now)) {
            return 0L; // Not yet expired
        }
        
        long diffInMillies = now.getTime() - endDate.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get renewal urgency level
     */
    public String getRenewalUrgency() {
        if (isPermanent()) {
            return "NO_RENEWAL_NEEDED";
        }
        
        String status = getClearanceStatus();
        if ("EXPIRED".equals(status)) {
            return "OVERDUE";
        }
        
        if ("ACTIVE".equals(status)) {
            Long remaining = getRemainingValidityDays();
            if (remaining != null) {
                if (remaining <= 0) {
                    return "EXPIRED";
                } else if (remaining <= 7) {
                    return "URGENT";
                } else if (remaining <= 30) {
                    return "RECOMMENDED";
                } else if (remaining <= 90) {
                    return "PLAN_AHEAD";
                }
            }
        }
        
        return "NOT_REQUIRED";
    }

    /**
     * Get action required
     */
    public String getActionRequired() {
        String urgency = getRenewalUrgency();
        
        return switch (urgency) {
            case "OVERDUE" -> "IMMEDIATE_RENEWAL_REQUIRED";
            case "URGENT" -> "URGENT_RENEWAL_REQUIRED";
            case "RECOMMENDED" -> "RENEWAL_RECOMMENDED";
            case "PLAN_AHEAD" -> "PLAN_RENEWAL";
            case "NO_RENEWAL_NEEDED" -> "NO_ACTION_REQUIRED";
            default -> "MONITOR_STATUS";
        };
    }

    /**
     * Check if clearance has supporting documentation
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
     * Get alert level for monitoring systems
     */
    public String getAlertLevel() {
        String urgency = getRenewalUrgency();
        
        return switch (urgency) {
            case "OVERDUE", "URGENT" -> "CRITICAL";
            case "RECOMMENDED" -> "WARNING";
            case "PLAN_AHEAD" -> "INFO";
            default -> "NONE";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ClearanceDTO createSimple(Long id, String displayText) {
        return ClearanceDTO.builder()
                .id(id)
                .build();
    }

    /**
     * Validate business rules
     */
    public boolean isValidBusinessRules() {
        // End date must be after start date if both provided
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            return false;
        }
        
        // Required foreign keys must be present
        if (providerId == null || providerRepresentatorId == null) {
            return false;
        }
        
        return true;
    }

    /**
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            errors.add("End date must be after start date");
        }
        
        if (providerId == null) {
            errors.add("Provider is required");
        }
        
        if (providerRepresentatorId == null) {
            errors.add("Provider representator is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by priority, then by end date)
     */
    public String getComparisonKey() {
        Integer priority = getClearancePriority();
        String endDateStr = endDate != null ? endDate.toString() : "9999-12-31";
        return String.format("%02d_%s", priority, endDateStr);
    }

    /**
     * Get clearance summary for reports
     */
    public String getClearanceSummary() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Clearance: ").append(getDisplayText()).append("\n");
        sb.append("Period: ").append(getPeriodDescription()).append("\n");
        sb.append("Status: ").append(getClearanceStatus()).append("\n");
        sb.append("Type: ").append(getClearanceType()).append("\n");
        sb.append("Validity: ").append(getValidityAssessment()).append("\n");
        sb.append("Action Required: ").append(getActionRequired());
        
        return sb.toString();
    }

    /**
     * Get expiration warning message
     */
    public String getExpirationWarning() {
        if (isPermanent()) {
            return "Permanent clearance - no expiration";
        }
        
        if (isExpired()) {
            return "Clearance has expired - renewal required";
        }
        
        Long remaining = getRemainingValidityDays();
        if (remaining == null) {
            return "No expiration date set";
        }
        
        if (remaining <= 0) {
            return "Clearance expires today - URGENT";
        }
        
        if (remaining <= 7) {
            return "Clearance expires in " + remaining + " days - URGENT";
        }
        
        if (remaining <= 30) {
            return "Clearance expires in " + remaining + " days - WARNING";
        }
        
        return "Clearance expires in " + remaining + " days";
    }

    /**
     * Get formatted display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (provider != null) {
            sb.append("Provider: ").append(provider.getFormalDisplay());
        }
        
        sb.append(" | Representator: ");
        if (providerRepresentator != null) {
            sb.append(providerRepresentator.getFormalDisplay());
        }
        
        sb.append(" | Status: ").append(getClearanceStatus());
        sb.append(" | Period: ").append(getPeriodDescription());
        
        return sb.toString();
    }

    /**
     * Get clearance effectiveness percentage (how much of the period has elapsed)
     */
    public Integer getEffectivenessPercentage() {
        if (startDate == null || endDate == null) {
            return null; // Cannot calculate for indefinite clearances
        }
        
        Date now = new Date();
        if (now.before(startDate)) {
            return 0; // Not yet started
        }
        
        if (now.after(endDate)) {
            return 100; // Completed/expired
        }
        
        long totalDuration = endDate.getTime() - startDate.getTime();
        long elapsedDuration = now.getTime() - startDate.getTime();
        
        return (int) ((elapsedDuration * 100) / totalDuration);
    }

    /**
     * Check if clearance covers a specific date
     */
    public boolean coversDate(Date date) {
        if (date == null) {
            return false;
        }
        
        if (startDate != null && date.before(startDate)) {
            return false;
        }
        
        if (endDate != null && date.after(endDate)) {
            return false;
        }
        
        return true;
    }
}
