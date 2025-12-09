/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStepDTO
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ConsultationStep Data Transfer Object
 * Maps exactly to ConsultationStep model fields: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=consultationPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (consultationPhase)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationStepDTO {
    
    private Long id; // F_00
    
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - nullable
    
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - nullable
    
    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique
    
    @NotNull(message = "Consultation phase is required")
    private Long consultationPhaseId; // F_04 - required foreign key
    
    // Additional fields for enriched responses
    private ConsultationPhaseDTO consultationPhase;
    private String consultationPhaseDesignation;
    private Boolean isActive;

    /**
     * Create DTO from entity
     */
    public static ConsultationStepDTO fromEntity(ConsultationStep consultationStep) {
        if (consultationStep == null) return null;
        
        return ConsultationStepDTO.builder()
                .id(consultationStep.getId())
                .designationAr(consultationStep.getDesignationAr())
                .designationEn(consultationStep.getDesignationEn())
                .designationFr(consultationStep.getDesignationFr())
                .consultationPhaseId(consultationStep.getConsultationPhase() != null ? 
                                   consultationStep.getConsultationPhase().getId() : null)
                .build();
    }
    
    /**
     * Create enriched DTO from entity with consultation phase info
     */
    public static ConsultationStepDTO fromEntityWithPhase(ConsultationStep consultationStep) {
        if (consultationStep == null) return null;
        
        ConsultationStepDTO dto = fromEntity(consultationStep);
        if (consultationStep.getConsultationPhase() != null) {
            dto.setConsultationPhase(ConsultationPhaseDTO.fromEntity(consultationStep.getConsultationPhase()));
            dto.setConsultationPhaseDesignation(consultationStep.getConsultationPhase().getDesignationFr());
        }
        
        return dto;
    }
    
    /**
     * Convert to entity (without setting consultation phase)
     */
    public ConsultationStep toEntity() {
        ConsultationStep consultationStep = new ConsultationStep();
        consultationStep.setId(this.id);
        consultationStep.setDesignationAr(this.designationAr);
        consultationStep.setDesignationEn(this.designationEn);
        consultationStep.setDesignationFr(this.designationFr);
        // Note: consultationPhase must be set separately in service layer
        return consultationStep;
    }
    
    /**
     * Update entity from DTO (without updating consultation phase)
     */
    public void updateEntity(ConsultationStep consultationStep) {
        if (this.designationAr != null) {
            consultationStep.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            consultationStep.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            consultationStep.setDesignationFr(this.designationFr);
        }
        // Note: consultationPhase update must be handled separately in service layer
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
     * Check if consultation step is fully multilingual
     */
    public boolean isMultilingual() {
        return designationAr != null && !designationAr.trim().isEmpty() &&
               designationEn != null && !designationEn.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }
    
    /**
     * Get available languages for this consultation step
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
     * Get consultation step type based on French designation patterns
     */
    public String getConsultationStepType() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Common consultation step types in Algeria's public procurement
        if (designation.contains("rédaction") || designation.contains("élaboration")) {
            return "DRAFTING";
        }
        if (designation.contains("validation") || designation.contains("approbation")) {
            return "VALIDATION";
        }
        if (designation.contains("vérification") || designation.contains("contrôle")) {
            return "VERIFICATION";
        }
        if (designation.contains("transmission") || designation.contains("envoi")) {
            return "TRANSMISSION";
        }
        if (designation.contains("réception") || designation.contains("accusé")) {
            return "RECEPTION";
        }
        if (designation.contains("analyse") || designation.contains("examen")) {
            return "ANALYSIS";
        }
        if (designation.contains("décision") || designation.contains("choix")) {
            return "DECISION";
        }
        if (designation.contains("archive") || designation.contains("classement")) {
            return "ARCHIVING";
        }
        
        return "OTHER";
    }
    
    /**
     * Check if this is an administrative step
     */
    public boolean isAdministrativeStep() {
        String type = getConsultationStepType();
        return "VALIDATION".equals(type) || "VERIFICATION".equals(type) || 
               "TRANSMISSION".equals(type) || "ARCHIVING".equals(type);
    }
    
    /**
     * Check if this is an operational step
     */
    public boolean isOperationalStep() {
        String type = getConsultationStepType();
        return "DRAFTING".equals(type) || "ANALYSIS".equals(type) || "DECISION".equals(type);
    }
    
    /**
     * Create simplified DTO for dropdowns
     */
    public static ConsultationStepDTO createSimple(Long id, String designationFr, Long consultationPhaseId) {
        return ConsultationStepDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .consultationPhaseId(consultationPhaseId)
                .build();
    }
    
    /**
     * Validate all required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() &&
               consultationPhaseId != null;
    }
    
    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return designationFr != null && designationFr.length() > 50 ? 
               designationFr.substring(0, 50) + "..." : designationFr;
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
        return designationFr != null ? designationFr.toUpperCase() : "";
    }
    
    /**
     * Get display text with consultation phase
     */
    public String getDisplayWithPhase() {
        String display = getShortDisplay();
        if (consultationPhaseDesignation != null) {
            display += " (" + consultationPhaseDesignation + ")";
        }
        return display;
    }
    
    /**
     * Check if consultation step is active
     */
    public boolean isActiveStep() {
        return isActive != null && isActive;
    }
    
    /**
     * Get step priority based on typical consultation workflow
     */
    public Integer getStepPriority() {
        String type = getConsultationStepType();
        
        return switch (type) {
            case "DRAFTING" -> 1;
            case "VERIFICATION" -> 2;
            case "VALIDATION" -> 3;
            case "TRANSMISSION" -> 4;
            case "RECEPTION" -> 5;
            case "ANALYSIS" -> 6;
            case "DECISION" -> 7;
            case "ARCHIVING" -> 8;
            default -> 999; // OTHER types at the end
        };
    }
    
    /**
     * Check if step requires approval
     */
    public boolean requiresApproval() {
        String type = getConsultationStepType();
        return "VALIDATION".equals(type) || "DECISION".equals(type);
    }
    
    /**
     * Check if step can be automated
     */
    public boolean canBeAutomated() {
        String type = getConsultationStepType();
        return "TRANSMISSION".equals(type) || "RECEPTION".equals(type) || "ARCHIVING".equals(type);
    }
    
    /**
     * Get estimated duration in hours (basic mapping)
     */
    public Integer getEstimatedDurationHours() {
        String type = getConsultationStepType();
        
        return switch (type) {
            case "DRAFTING" -> 24; // 1 day
            case "VERIFICATION" -> 4; // 4 hours
            case "VALIDATION" -> 2; // 2 hours
            case "TRANSMISSION" -> 1; // 1 hour
            case "RECEPTION" -> 1; // 1 hour
            case "ANALYSIS" -> 48; // 2 days
            case "DECISION" -> 8; // 8 hours
            case "ARCHIVING" -> 1; // 1 hour
            default -> 4; // Default 4 hours
        };
    }
}