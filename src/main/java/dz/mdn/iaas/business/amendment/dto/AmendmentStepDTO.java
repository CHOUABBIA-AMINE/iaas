/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepDTO
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.amendment.model.AmendmentStep;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AmendmentStep Data Transfer Object
 * Maps exactly to AmendmentStep model fields: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=amendmentPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (amendmentPhase)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmendmentStepDTO {
    
    private Long id; // F_00
    
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - nullable
    
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - nullable
    
    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique
    
    @NotNull(message = "Amendment phase is required")
    private Long amendmentPhaseId; // F_04 - required foreign key
    
    // Additional fields for enriched responses
    private AmendmentPhaseDTO amendmentPhase;
    private String amendmentPhaseDesignation;
    private Boolean isActive;

    /**
     * Create DTO from entity
     */
    public static AmendmentStepDTO fromEntity(AmendmentStep amendmentStep) {
        if (amendmentStep == null) return null;
        
        return AmendmentStepDTO.builder()
                .id(amendmentStep.getId())
                .designationAr(amendmentStep.getDesignationAr())
                .designationEn(amendmentStep.getDesignationEn())
                .designationFr(amendmentStep.getDesignationFr())
                .amendmentPhaseId(amendmentStep.getAmendmentPhase() != null ? 
                                   amendmentStep.getAmendmentPhase().getId() : null)
                .build();
    }
    
    /**
     * Create enriched DTO from entity with amendment phase info
     */
    public static AmendmentStepDTO fromEntityWithPhase(AmendmentStep amendmentStep) {
        if (amendmentStep == null) return null;
        
        AmendmentStepDTO dto = fromEntity(amendmentStep);
        if (amendmentStep.getAmendmentPhase() != null) {
            dto.setAmendmentPhase(AmendmentPhaseDTO.fromEntity(amendmentStep.getAmendmentPhase()));
            dto.setAmendmentPhaseDesignation(amendmentStep.getAmendmentPhase().getDesignationFr());
        }
        
        return dto;
    }
    
    /**
     * Convert to entity (without setting amendment phase)
     */
    public AmendmentStep toEntity() {
        AmendmentStep amendmentStep = new AmendmentStep();
        amendmentStep.setId(this.id);
        amendmentStep.setDesignationAr(this.designationAr);
        amendmentStep.setDesignationEn(this.designationEn);
        amendmentStep.setDesignationFr(this.designationFr);
        // Note: amendmentPhase must be set separately in service layer
        return amendmentStep;
    }
    
    /**
     * Update entity from DTO (without updating amendment phase)
     */
    public void updateEntity(AmendmentStep amendmentStep) {
        if (this.designationAr != null) {
            amendmentStep.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            amendmentStep.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            amendmentStep.setDesignationFr(this.designationFr);
        }
        // Note: amendmentPhase update must be handled separately in service layer
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
     * Check if amendment step is fully multilingual
     */
    public boolean isMultilingual() {
        return designationAr != null && !designationAr.trim().isEmpty() &&
               designationEn != null && !designationEn.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }
    
    /**
     * Get available languages for this amendment step
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
     * Get amendment step type based on French designation patterns
     */
    public String getAmendmentStepType() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Common amendment step types in Algeria's public procurement
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
        String type = getAmendmentStepType();
        return "VALIDATION".equals(type) || "VERIFICATION".equals(type) || 
               "TRANSMISSION".equals(type) || "ARCHIVING".equals(type);
    }
    
    /**
     * Check if this is an operational step
     */
    public boolean isOperationalStep() {
        String type = getAmendmentStepType();
        return "DRAFTING".equals(type) || "ANALYSIS".equals(type) || "DECISION".equals(type);
    }
    
    /**
     * Create simplified DTO for dropdowns
     */
    public static AmendmentStepDTO createSimple(Long id, String designationFr, Long amendmentPhaseId) {
        return AmendmentStepDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .amendmentPhaseId(amendmentPhaseId)
                .build();
    }
    
    /**
     * Validate all required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() &&
               amendmentPhaseId != null;
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
     * Get display text with amendment phase
     */
    public String getDisplayWithPhase() {
        String display = getShortDisplay();
        if (amendmentPhaseDesignation != null) {
            display += " (" + amendmentPhaseDesignation + ")";
        }
        return display;
    }
    
    /**
     * Check if amendment step is active
     */
    public boolean isActiveStep() {
        return isActive != null && isActive;
    }
    
    /**
     * Get step priority based on typical amendment workflow
     */
    public Integer getStepPriority() {
        String type = getAmendmentStepType();
        
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
        String type = getAmendmentStepType();
        return "VALIDATION".equals(type) || "DECISION".equals(type);
    }
    
    /**
     * Check if step can be automated
     */
    public boolean canBeAutomated() {
        String type = getAmendmentStepType();
        return "TRANSMISSION".equals(type) || "RECEPTION".equals(type) || "ARCHIVING".equals(type);
    }
    
    /**
     * Get estimated duration in hours (basic mapping)
     */
    public Integer getEstimatedDurationHours() {
        String type = getAmendmentStepType();
        
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