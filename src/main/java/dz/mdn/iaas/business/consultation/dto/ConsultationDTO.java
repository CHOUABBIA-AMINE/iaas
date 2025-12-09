/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationDTO
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.business.consultation.model.Consultation;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Enhanced Consultation Data Transfer Object
 * Maps exactly to Consultation model fields: F_00=id, F_01=internalId, F_02=consultationYear,
 * F_03=reference, F_04=designationAr, F_05=designationEn, F_06=designationFr, F_07=allocatedAmount,
 * F_08=financialEstimation, F_09=startDate, F_10=approvalReference, F_11=approvalDate,
 * F_12=publishDate, F_13=deadline, F_14=observation, and all foreign keys F_15 to F_21
 * Required fields: F_01, F_02, F_06, and all foreign keys (F_15 to F_21)
 * Unique constraint: F_01 + F_02 (internalId + consultationYear)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationDTO {
    
    private Long id; // F_00
    
    @NotBlank(message = "Internal ID is required")
    @Size(max = 3, message = "Internal ID must not exceed 3 characters")
    private String internalId; // F_01 - required, part of unique constraint
    
    @NotBlank(message = "Consultation year is required")
    @Pattern(regexp = "\\d{4}", message = "Consultation year must be 4 digits")
    private String consultationYear; // F_02 - required, part of unique constraint
    
    @Size(max = 20, message = "Reference must not exceed 20 characters")
    private String reference; // F_03
    
    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    private String designationAr; // F_04
    
    @Size(max = 300, message = "English designation must not exceed 300 characters")
    private String designationEn; // F_05
    
    @NotBlank(message = "French designation is required")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    private String designationFr; // F_06 - required
    
    @DecimalMin(value = "0.0", message = "Allocated amount must be positive")
    private Double allocatedAmount; // F_07
    
    @DecimalMin(value = "0.0", message = "Financial estimation must be positive")
    private Double financialEstimation; // F_08
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate; // F_09
    
    @Size(max = 50, message = "Approval reference must not exceed 50 characters")
    private String approvalReference; // F_10
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date approvalDate; // F_11
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate; // F_12
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline; // F_13
    
    @Size(max = 500, message = "Observation must not exceed 500 characters")
    private String observation; // F_14
    
    // Foreign key fields - all required
    @NotNull(message = "Award method is required")
    private Long awardMethodId; // F_15 - required foreign key
    
    @NotNull(message = "Realization nature is required")
    private Long realizationNatureId; // F_16 - required foreign key
    
    @NotNull(message = "Budget type is required")
    private Long budgetTypeId; // F_17 - required foreign key
    
    @NotNull(message = "Realization status is required")
    private Long realizationStatusId; // F_18 - required foreign key
    
    @NotNull(message = "Approval status is required")
    private Long approvalStatusId; // F_19 - required foreign key
    
    @NotNull(message = "Realization director is required")
    private Long realizationDirectorId; // F_20 - required foreign key
    
    @NotNull(message = "Consultation step is required")
    private Long consultationStepId; // F_21 - required foreign key
    
    // Additional enrichment fields
    private Integer submissionCount;
    private Double lowestOffer;
    private Double highestOffer;
    private Double averageOffer;
    private Integer daysUntilDeadline;
    private Boolean isExpired;
    private Boolean isActive;
    
    // Related entity designations for display
    private String awardMethodDesignation;
    private String realizationNatureDesignation;
    private String budgetTypeDesignation;
    private String realizationStatusDesignation;
    private String approvalStatusDesignation;
    private String realizationDirectorDesignation;
    private String consultationStepDesignation;

    /**
     * Create DTO from entity
     */
    public static ConsultationDTO fromEntity(Consultation consultation) {
        if (consultation == null) return null;
        
        ConsultationDTO dto = ConsultationDTO.builder()
                .id(consultation.getId())
                .internalId(consultation.getInternalId())
                .consultationYear(consultation.getConsultationYear())
                .reference(consultation.getReference())
                .designationAr(consultation.getDesignationAr())
                .designationEn(consultation.getDesignationEn())
                .designationFr(consultation.getDesignationFr())
                .allocatedAmount(consultation.getAllocatedAmount())
                .financialEstimation(consultation.getFinancialEstimation())
                .startDate(consultation.getStartDate())
                .approvalReference(consultation.getApprovalReference())
                .approvalDate(consultation.getApprovalDate())
                .publishDate(consultation.getPublishDate())
                .deadline(consultation.getDeadline())
                .observation(consultation.getObservation())
                .build();
        
        // Set foreign key IDs and designations
        if (consultation.getAwardMethod() != null) {
            dto.setAwardMethodId(consultation.getAwardMethod().getId());
            dto.setAwardMethodDesignation(consultation.getAwardMethod().getDesignationFr());
        }
        if (consultation.getRealizationNature() != null) {
            dto.setRealizationNatureId(consultation.getRealizationNature().getId());
            dto.setRealizationNatureDesignation(consultation.getRealizationNature().getDesignationFr());
        }
        if (consultation.getBudgetType() != null) {
            dto.setBudgetTypeId(consultation.getBudgetType().getId());
            dto.setBudgetTypeDesignation(consultation.getBudgetType().getDesignationFr());
        }
        if (consultation.getRealizationStatus() != null) {
            dto.setRealizationStatusId(consultation.getRealizationStatus().getId());
            dto.setRealizationStatusDesignation(consultation.getRealizationStatus().getDesignationFr());
        }
        if (consultation.getApprovalStatus() != null) {
            dto.setApprovalStatusId(consultation.getApprovalStatus().getId());
            dto.setApprovalStatusDesignation(consultation.getApprovalStatus().getDesignationFr());
        }
        if (consultation.getRealizationDirector() != null) {
            dto.setRealizationDirectorId(consultation.getRealizationDirector().getId());
            dto.setRealizationDirectorDesignation(consultation.getRealizationDirector().getDesignationFr());
        }
        if (consultation.getConsultationStep() != null) {
            dto.setConsultationStepId(consultation.getConsultationStep().getId());
            dto.setConsultationStepDesignation(consultation.getConsultationStep().getDesignationFr());
        }
        
        return dto;
    }
    
    /**
     * Convert to entity (without setting relationships)
     */
    public Consultation toEntity() {
        Consultation consultation = new Consultation();
        consultation.setId(this.id);
        consultation.setInternalId(this.internalId);
        consultation.setConsultationYear(this.consultationYear);
        consultation.setReference(this.reference);
        consultation.setDesignationAr(this.designationAr);
        consultation.setDesignationEn(this.designationEn);
        consultation.setDesignationFr(this.designationFr);
        consultation.setAllocatedAmount(this.allocatedAmount != null ? this.allocatedAmount : 0.0);
        consultation.setFinancialEstimation(this.financialEstimation != null ? this.financialEstimation : 0.0);
        consultation.setStartDate(this.startDate);
        consultation.setApprovalReference(this.approvalReference);
        consultation.setApprovalDate(this.approvalDate);
        consultation.setPublishDate(this.publishDate);
        consultation.setDeadline(this.deadline);
        consultation.setObservation(this.observation);
        // Note: Foreign key relationships must be set separately in service layer
        return consultation;
    }
    
    /**
     * Get default designation based on system locale
     */
    public String getDefaultDesignation() {
        // Prioritize French designation as it's required and commonly used in Algeria
        return designationFr != null ? designationFr : 
               (designationEn != null ? designationEn : designationAr);
    }
    
    /**
     * Get designation by language preference
     */
    public String getDesignationByLanguage(String language) {
        if (language == null) return getDefaultDesignation();
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> designationAr != null ? designationAr : getDefaultDesignation();
            case "en", "english" -> designationEn != null ? designationEn : getDefaultDesignation();
            case "fr", "french" -> designationFr != null ? designationFr : getDefaultDesignation();
            default -> getDefaultDesignation();
        };
    }
    
    /**
     * Check if consultation is fully multilingual
     */
    public boolean isMultilingual() {
        return designationAr != null && !designationAr.trim().isEmpty() &&
               designationEn != null && !designationEn.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }
    
    /**
     * Validate all required fields are present
     */
    public boolean isValid() {
        return internalId != null && !internalId.trim().isEmpty() &&
               consultationYear != null && !consultationYear.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty() &&
               awardMethodId != null && realizationNatureId != null &&
               budgetTypeId != null && realizationStatusId != null &&
               approvalStatusId != null && realizationDirectorId != null &&
               consultationStepId != null;
    }
    
    /**
     * Get consultation status based on dates and status
     */
    public String getConsultationStatus() {
        Date now = new Date();
        
        if (publishDate == null) {
            return "DRAFT";
        } else if (publishDate.after(now)) {
            return "SCHEDULED";
        } else if (deadline != null && deadline.before(now)) {
            return "EXPIRED";
        } else if (isActive != null && isActive) {
            return "ACTIVE";
        } else {
            return "CLOSED";
        }
    }
    
    /**
     * Get financial efficiency ratio
     */
    public Double getFinancialEfficiencyRatio() {
        if (allocatedAmount != null && financialEstimation != null && 
            allocatedAmount > 0 && financialEstimation > 0) {
            return financialEstimation / allocatedAmount;
        }
        return null;
    }
    
    /**
     * Check if consultation has budget overrun
     */
    public boolean hasBudgetOverrun() {
        if (allocatedAmount != null && financialEstimation != null) {
            return financialEstimation > allocatedAmount;
        }
        return false;
    }
    
    /**
     * Get budget variance percentage
     */
    public Double getBudgetVariancePercentage() {
        if (allocatedAmount != null && financialEstimation != null && allocatedAmount > 0) {
            return ((financialEstimation - allocatedAmount) / allocatedAmount) * 100;
        }
        return null;
    }
    
    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return String.format("%s-%s: %s", internalId, consultationYear, 
                designationFr != null && designationFr.length() > 50 ? 
                designationFr.substring(0, 50) + "..." : designationFr);
    }
    
    /**
     * Get full display with reference
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        if (reference != null) {
            sb.append(reference).append(" - ");
        }
        sb.append(getDefaultDesignation());
        return sb.toString();
    }
    
    /**
     * Get comparison key for sorting
     */
    public String getComparisonKey() {
        return consultationYear + "-" + String.format("%03d", Integer.parseInt(internalId));
    }
    
    /**
     * Check if consultation is urgent (deadline within 7 days)
     */
    public boolean isUrgent() {
        return daysUntilDeadline != null && daysUntilDeadline > 0 && daysUntilDeadline <= 7;
    }
    
    /**
     * Check if consultation has high value (above threshold)
     */
    public boolean isHighValue() {
        double threshold = 1000000.0; // 1M threshold
        return allocatedAmount != null && allocatedAmount > threshold;
    }
    
    /**
     * Get consultation duration in days
     */
    public Integer getConsultationDurationDays() {
        if (startDate != null && deadline != null) {
            long diffInMillies = deadline.getTime() - startDate.getTime();
            return (int) (diffInMillies / (1000 * 60 * 60 * 24));
        }
        return null;
    }
    
    /**
     * Check if consultation has submissions
     */
    public boolean hasSubmissions() {
        return submissionCount != null && submissionCount > 0;
    }
    
    /**
     * Get competitive ratio (number of submissions per million)
     */
    public Double getCompetitiveRatio() {
        if (submissionCount != null && allocatedAmount != null && allocatedAmount > 0) {
            return (submissionCount * 1000000.0) / allocatedAmount;
        }
        return null;
    }
}