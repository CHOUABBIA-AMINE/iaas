/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractTypeDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.contract.model.ContractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ContractType Data Transfer Object
 * Maps exactly to ContractType model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractTypeDTO {

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
    public static ContractTypeDTO fromEntity(ContractType contractType) {
        if (contractType == null) return null;
        
        return ContractTypeDTO.builder()
                .id(contractType.getId())
                .designationAr(contractType.getDesignationAr())
                .designationEn(contractType.getDesignationEn())
                .designationFr(contractType.getDesignationFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public ContractType toEntity() {
        ContractType contractType = new ContractType();
        contractType.setId(this.id);
        contractType.setDesignationAr(this.designationAr);
        contractType.setDesignationEn(this.designationEn);
        contractType.setDesignationFr(this.designationFr);
        return contractType;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(ContractType contractType) {
        if (this.designationAr != null) {
            contractType.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            contractType.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            contractType.setDesignationFr(this.designationFr);
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
     * Check if approval status has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this approval status
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
     * Get approval status type based on French designation analysis
     */
    public String getContractTypeType() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        if (designation.contains("approuvé") || designation.contains("approved") || 
            designation.contains("accepté") || designation.contains("validé")) {
            return "APPROVED";
        } else if (designation.contains("refusé") || designation.contains("rejected") || 
                  designation.contains("rejeté") || designation.contains("declined")) {
            return "REJECTED";
        } else if (designation.contains("en attente") || designation.contains("pending") || 
                  designation.contains("en cours") || designation.contains("processing")) {
            return "PENDING";
        } else if (designation.contains("brouillon") || designation.contains("draft") || 
                  designation.contains("temporaire")) {
            return "DRAFT";
        } else if (designation.contains("suspendu") || designation.contains("suspended") || 
                  designation.contains("gelé")) {
            return "SUSPENDED";
        } else if (designation.contains("annulé") || designation.contains("cancelled") || 
                  designation.contains("canceled")) {
            return "CANCELLED";
        } else if (designation.contains("révision") || designation.contains("review") || 
                  designation.contains("vérification")) {
            return "UNDER_REVIEW";
        }
        return "OTHER";
    }

    /**
     * Check if this is an approved status
     */
    public boolean isApproved() {
        return "APPROVED".equals(getContractTypeType());
    }

    /**
     * Check if this is a rejected status
     */
    public boolean isRejected() {
        return "REJECTED".equals(getContractTypeType());
    }

    /**
     * Check if this is a pending status
     */
    public boolean isPending() {
        return "PENDING".equals(getContractTypeType());
    }

    /**
     * Check if this is a final status (approved or rejected)
     */
    public boolean isFinal() {
        String type = getContractTypeType();
        return "APPROVED".equals(type) || "REJECTED".equals(type) || "CANCELLED".equals(type);
    }

    /**
     * Get status priority for workflow ordering
     */
    public int getStatusPriority() {
        return switch (getContractTypeType()) {
            case "DRAFT" -> 1;
            case "PENDING" -> 2;
            case "UNDER_REVIEW" -> 3;
            case "APPROVED" -> 4;
            case "REJECTED" -> 5;
            case "SUSPENDED" -> 6;
            case "CANCELLED" -> 7;
            default -> 8;
        };
    }

    /**
     * Get status color code for UI display
     */
    public String getStatusColor() {
        return switch (getContractTypeType()) {
            case "APPROVED" -> "GREEN";
            case "REJECTED" -> "RED";
            case "PENDING" -> "ORANGE";
            case "UNDER_REVIEW" -> "BLUE";
            case "DRAFT" -> "GRAY";
            case "SUSPENDED" -> "YELLOW";
            case "CANCELLED" -> "DARK_RED";
            default -> "BLACK";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ContractTypeDTO createSimple(Long id, String designationFr) {
        return ContractTypeDTO.builder()
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
     * Check if status allows transitions to other statuses
     */
    public boolean allowsTransition() {
        String type = getContractTypeType();
        return !"APPROVED".equals(type) && !"REJECTED".equals(type) && !"CANCELLED".equals(type);
    }

    /**
     * Get workflow stage number
     */
    public int getWorkflowStage() {
        return switch (getContractTypeType()) {
            case "DRAFT" -> 1;
            case "PENDING", "UNDER_REVIEW" -> 2;
            case "APPROVED", "REJECTED", "CANCELLED" -> 3;
            default -> 0;
        };
    }
}
