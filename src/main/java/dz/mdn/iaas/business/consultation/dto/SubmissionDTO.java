/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ConsultationDTO
 *	@CreatedOn	: 10-12-2025
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.provider.dto.ProviderDTO;
import dz.mdn.iaas.system.utility.dto.FileDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Submission Data Transfer Object
 * Maps exactly to Submission model fields: F_00=id, F_01=submissionDate, F_02=financialOffer, 
 * F_03=consultation (foreign key), F_04=tender (Provider foreign key), F_05=administrativePart (File foreign key),
 * F_06=technicalPart (File foreign key), F_07=financialPart (File foreign key)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmissionDTO {

    private Long id; // F_00

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submissionDate; // F_01

    @DecimalMin(value = "0.0", message = "Financial offer must be positive")
    private Double financialOffer; // F_02

    @NotNull(message = "Consultation ID is required")
    private Long consultationId; // F_03 foreign key

    @NotNull(message = "Tender (Provider) ID is required") 
    private Long tenderId; // F_04 foreign key (tender is Provider)

    private Long administrativePartId; // F_05 foreign key (File)

    private Long technicalPartId; // F_06 foreign key (File)

    private Long financialPartId; // F_07 foreign key (File)

    // Nested objects for response (populated when needed)
    private ConsultationDTO consultation;
    private ProviderDTO tender; // Using existing ProviderDTO
    private FileDTO administrativePart; // Using existing FileDTO
    private FileDTO technicalPart; // Using existing FileDTO
    private FileDTO financialPart; // Using existing FileDTO

    /**
     * Create DTO from entity
     */
    public static SubmissionDTO fromEntity(dz.mdn.iaas.business.consultation.model.Submission submission) {
        if (submission == null) return null;
        
        SubmissionDTO.SubmissionDTOBuilder builder = SubmissionDTO.builder()
                .id(submission.getId())
                .submissionDate(submission.getSubmissionDate())
                .financialOffer(submission.getFinancialOffer());

        // Handle foreign key relationships
        if (submission.getConsultation() != null) {
            builder.consultationId(submission.getConsultation().getId());
        }
        if (submission.getTender() != null) {
            builder.tenderId(submission.getTender().getId());
        }
        if (submission.getAdministrativePart() != null) {
            builder.administrativePartId(submission.getAdministrativePart().getId());
        }
        if (submission.getTechnicalPart() != null) {
            builder.technicalPartId(submission.getTechnicalPart().getId());
        }
        if (submission.getFinancialPart() != null) {
            builder.financialPartId(submission.getFinancialPart().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static SubmissionDTO fromEntityWithRelations(dz.mdn.iaas.business.consultation.model.Submission submission) {
        SubmissionDTO dto = fromEntity(submission);
        if (dto == null) return null;

        // Populate related DTOs using existing fromEntity methods
        if (submission.getConsultation() != null) {
            dto.setConsultation(ConsultationDTO.fromEntity(submission.getConsultation()));
        }
        if (submission.getTender() != null) {
            dto.setTender(ProviderDTO.fromEntity(submission.getTender()));
        }
        if (submission.getAdministrativePart() != null) {
            dto.setAdministrativePart(FileDTO.fromEntity(submission.getAdministrativePart()));
        }
        if (submission.getTechnicalPart() != null) {
            dto.setTechnicalPart(FileDTO.fromEntity(submission.getTechnicalPart()));
        }
        if (submission.getFinancialPart() != null) {
            dto.setFinancialPart(FileDTO.fromEntity(submission.getFinancialPart()));
        }

        return dto;
    }

    /**
     * Convert to entity (for create/update operations)
     */
    public dz.mdn.iaas.business.consultation.model.Submission toEntity() {
        dz.mdn.iaas.business.consultation.model.Submission submission = 
            new dz.mdn.iaas.business.consultation.model.Submission();
        
        submission.setId(this.id);
        submission.setSubmissionDate(this.submissionDate);
        submission.setFinancialOffer(this.financialOffer != null ? this.financialOffer : 0.0);
        
        return submission;
    }

    /**
     * Get tender display name
     */
    public String getTenderDisplayName() {
        return tender != null ? tender.getDisplayText() : "N/A";
    }

    /**
     * Get consultation reference
     */
    public String getConsultationReference() {
        return consultation != null ? consultation.getReference() : "N/A";
    }

    /**
     * Check if submission has complete documentation
     */
    public boolean hasCompleteDocumentation() {
        return administrativePartId != null && technicalPartId != null && financialPartId != null;
    }

    /**
     * Get submission completeness percentage
     */
    public int getCompletenessPercentage() {
        int totalParts = 5; // submissionDate, financialOffer, administrativePart, technicalPart, financialPart
        int completedParts = 0;
        
        if (submissionDate != null) completedParts++;
        if (financialOffer != null && financialOffer > 0) completedParts++;
        if (administrativePartId != null) completedParts++;
        if (technicalPartId != null) completedParts++;
        if (financialPartId != null) completedParts++;
        
        return (completedParts * 100) / totalParts;
    }

    /**
     * Get submission status based on completeness
     */
    public String getSubmissionStatus() {
        if (hasCompleteDocumentation() && financialOffer != null && financialOffer > 0) {
            return "COMPLETE";
        }
        if (financialOffer != null && financialOffer > 0) {
            return "FINANCIAL_SUBMITTED";
        }
        if (submissionDate != null) {
            return "INITIATED";
        }
        return "DRAFT";
    }

    /**
     * Check if submission is valid (has required fields)
     */
    public boolean isValid() {
        return consultationId != null && tenderId != null;
    }

    /**
     * Get display text for lists and dropdowns
     */
    public String getDisplayText() {
        String tenderName = getTenderDisplayName();
        String consultationRef = getConsultationReference();
        return tenderName + " - " + consultationRef;
    }

    /**
     * Get financial offer formatted as currency
     */
    public String getFinancialOfferFormatted() {
        if (financialOffer == null || financialOffer <= 0) return "Not specified";
        return String.format("%,.2f DZD", financialOffer);
    }

    /**
     * Get submission summary for display
     */
    public String getSubmissionSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTenderDisplayName());
        
        if (financialOffer != null && financialOffer > 0) {
            sb.append(" - ").append(getFinancialOfferFormatted());
        }
        
        sb.append(" (").append(getSubmissionStatus()).append(")");
        
        return sb.toString();
    }

    /**
     * Get parts summary (which parts are submitted)
     */
    public String getPartsSummary() {
        StringBuilder sb = new StringBuilder();
        
        if (administrativePartId != null) {
            sb.append("Administrative");
        }
        
        if (technicalPartId != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("Technical");
        }
        
        if (financialPartId != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append("Financial");
        }
        
        return sb.length() > 0 ? sb.toString() : "No parts submitted";
    }

    /**
     * Get submission date formatted
     */
    public String getSubmissionDateFormatted() {
        if (submissionDate == null) return "Not submitted";
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(submissionDate);
    }

    /**
     * Check if submission has any file attachments
     */
    public boolean hasAttachments() {
        return administrativePartId != null || technicalPartId != null || financialPartId != null;
    }

    /**
     * Get attachment count
     */
    public int getAttachmentCount() {
        int count = 0;
        if (administrativePartId != null) count++;
        if (technicalPartId != null) count++;
        if (financialPartId != null) count++;
        return count;
    }

    /**
     * Create simplified DTO for dropdowns and lists
     */
    public static SubmissionDTO createSimple(Long id, String tenderName, String consultationReference) {
        return SubmissionDTO.builder()
                .id(id)
                .tender(ProviderDTO.builder().designationLt(tenderName).build())
                .consultation(ConsultationDTO.builder().reference(consultationReference).build())
                .build();
    }

    /**
     * Get comparison key for sorting (by tender name, then by offer amount)
     */
    public String getComparisonKey() {
        String tenderName = getTenderDisplayName().toLowerCase();
        double offer = financialOffer != null ? financialOffer : 0.0;
        return tenderName + "_" + String.format("%020.2f", offer);
    }

    /**
     * Check if submission is competitive (has financial offer)
     */
    public boolean isCompetitive() {
        return financialOffer != null && financialOffer > 0;
    }

    /**
     * Get submission type based on completeness
     */
    public String getSubmissionType() {
        if (hasCompleteDocumentation()) {
            return "FULL_SUBMISSION";
        }
        if (hasAttachments()) {
            return "PARTIAL_SUBMISSION";
        }
        if (isCompetitive()) {
            return "FINANCIAL_ONLY";
        }
        return "REGISTRATION_ONLY";
    }

    /**
     * Get short display for compact views
     */
    public String getShortDisplay() {
        String tenderName = tender != null ? 
            (tender.getAcronymLt() != null && !tender.getAcronymLt().isEmpty() ? 
             tender.getAcronymLt() : tender.getDisplayText()) : "N/A";
        
        if (financialOffer != null && financialOffer > 0) {
            return tenderName + " (" + getFinancialOfferFormatted() + ")";
        }
        
        return tenderName;
    }

    /**
     * Get full display with all information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Submission by ").append(getTenderDisplayName());
        
        if (consultation != null && consultation.getReference() != null) {
            sb.append(" for ").append(consultation.getReference());
        }
        
        if (financialOffer != null && financialOffer > 0) {
            sb.append(" - Offer: ").append(getFinancialOfferFormatted());
        }
        
        if (submissionDate != null) {
            sb.append(" (").append(getSubmissionDateFormatted()).append(")");
        }
        
        sb.append(" - Status: ").append(getSubmissionStatus());
        
        return sb.toString();
    }

    /**
     * Validate business rules
     */
    public java.util.List<String> validateBusinessRules() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (consultationId == null) {
            errors.add("Consultation is required");
        }
        
        if (tenderId == null) {
            errors.add("Tender (Provider) is required");
        }
        
        if (financialOffer != null && financialOffer < 0) {
            errors.add("Financial offer cannot be negative");
        }
        
        return errors;
    }

    /**
     * Check if submission can be modified
     */
    public boolean canBeModified() {
        // Business rule: submissions can be modified if not yet complete
        return !getSubmissionStatus().equals("COMPLETE");
    }

    /**
     * Get risk assessment based on offer completeness
     */
    public String getRiskAssessment() {
        int completeness = getCompletenessPercentage();
        
        if (completeness >= 80) return "LOW_RISK";
        if (completeness >= 60) return "MEDIUM_RISK";
        if (completeness >= 40) return "HIGH_RISK";
        return "VERY_HIGH_RISK";
    }
}