/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStepDTO
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.contract.model.ContractStep;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ContractStep Data Transfer Object
 * Maps exactly to ContractStep model fields: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=contractPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (contractPhase)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractStepDTO {
    
    private Long id; // F_00
    
    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - nullable
    
    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - nullable
    
    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique
    
    @NotNull(message = "Contract phase is required")
    private Long contractPhaseId; // F_04 - required foreign key
    
    // Additional fields for enriched responses
    private ContractPhaseDTO contractPhase;
    private String contractPhaseDesignation;
    private Boolean isActive;

    /**
     * Create DTO from entity
     */
    public static ContractStepDTO fromEntity(ContractStep contractStep) {
        if (contractStep == null) return null;
        
        return ContractStepDTO.builder()
                .id(contractStep.getId())
                .designationAr(contractStep.getDesignationAr())
                .designationEn(contractStep.getDesignationEn())
                .designationFr(contractStep.getDesignationFr())
                .contractPhaseId(contractStep.getContractPhase() != null ? 
                                   contractStep.getContractPhase().getId() : null)
                .build();
    }
    
    /**
     * Create enriched DTO from entity with contract phase info
     */
    public static ContractStepDTO fromEntityWithPhase(ContractStep contractStep) {
        if (contractStep == null) return null;
        
        ContractStepDTO dto = fromEntity(contractStep);
        if (contractStep.getContractPhase() != null) {
            dto.setContractPhase(ContractPhaseDTO.fromEntity(contractStep.getContractPhase()));
            dto.setContractPhaseDesignation(contractStep.getContractPhase().getDesignationFr());
        }
        
        return dto;
    }
    
    /**
     * Convert to entity (without setting contract phase)
     */
    public ContractStep toEntity() {
        ContractStep contractStep = new ContractStep();
        contractStep.setId(this.id);
        contractStep.setDesignationAr(this.designationAr);
        contractStep.setDesignationEn(this.designationEn);
        contractStep.setDesignationFr(this.designationFr);
        // Note: contractPhase must be set separately in service layer
        return contractStep;
    }
    
    /**
     * Update entity from DTO (without updating contract phase)
     */
    public void updateEntity(ContractStep contractStep) {
        if (this.designationAr != null) {
            contractStep.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            contractStep.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            contractStep.setDesignationFr(this.designationFr);
        }
        // Note: contractPhase update must be handled separately in service layer
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
     * Check if contract step is fully multilingual
     */
    public boolean isMultilingual() {
        return designationAr != null && !designationAr.trim().isEmpty() &&
               designationEn != null && !designationEn.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }
    
    /**
     * Get available languages for this contract step
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
     * Get contract step type based on French designation patterns
     */
    public String getContractStepType() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Common contract step types in Algeria's public procurement
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
        String type = getContractStepType();
        return "VALIDATION".equals(type) || "VERIFICATION".equals(type) || 
               "TRANSMISSION".equals(type) || "ARCHIVING".equals(type);
    }
    
    /**
     * Check if this is an operational step
     */
    public boolean isOperationalStep() {
        String type = getContractStepType();
        return "DRAFTING".equals(type) || "ANALYSIS".equals(type) || "DECISION".equals(type);
    }
    
    /**
     * Create simplified DTO for dropdowns
     */
    public static ContractStepDTO createSimple(Long id, String designationFr, Long contractPhaseId) {
        return ContractStepDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .contractPhaseId(contractPhaseId)
                .build();
    }
    
    /**
     * Validate all required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() &&
               contractPhaseId != null;
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
     * Get display text with contract phase
     */
    public String getDisplayWithPhase() {
        String display = getShortDisplay();
        if (contractPhaseDesignation != null) {
            display += " (" + contractPhaseDesignation + ")";
        }
        return display;
    }
    
    /**
     * Check if contract step is active
     */
    public boolean isActiveStep() {
        return isActive != null && isActive;
    }
    
    /**
     * Get step priority based on typical contract workflow
     */
    public Integer getStepPriority() {
        String type = getContractStepType();
        
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
        String type = getContractStepType();
        return "VALIDATION".equals(type) || "DECISION".equals(type);
    }
    
    /**
     * Check if step can be automated
     */
    public boolean canBeAutomated() {
        String type = getContractStepType();
        return "TRANSMISSION".equals(type) || "RECEPTION".equals(type) || "ARCHIVING".equals(type);
    }
    
    /**
     * Get estimated duration in hours (basic mapping)
     */
    public Integer getEstimatedDurationHours() {
        String type = getContractStepType();
        
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