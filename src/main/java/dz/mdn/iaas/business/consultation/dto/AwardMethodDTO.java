/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AwardMethodDTO
 *	@CreatedOn	: 10-19-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.business.consultation.model.AwardMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AwardMethod Data Transfer Object
 * Maps exactly to AwardMethod model fields: F_00=id, F_01=designationAr, F_02=designationEn,
 * F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * Required fields: F_03 (designationFr), F_06 (acronymFr) with unique constraints
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AwardMethodDTO {
    
    private Long id; // F_00
    
    @Size(max = 150, message = "Arabic designation must not exceed 150 characters")
    private String designationAr; // F_01 - nullable
    
    @Size(max = 150, message = "English designation must not exceed 150 characters")
    private String designationEn; // F_02 - nullable
    
    @NotBlank(message = "French designation is required")
    @Size(max = 150, message = "French designation must not exceed 150 characters")
    private String designationFr; // F_03 - required and unique
    
    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    private String acronymAr; // F_04 - nullable
    
    @Size(max = 20, message = "English acronym must not exceed 20 characters")
    private String acronymEn; // F_05 - nullable
    
    @NotBlank(message = "French acronym is required")
    @Size(max = 20, message = "French acronym must not exceed 20 characters")
    private String acronymFr; // F_06 - required and unique

    /**
     * Create DTO from entity
     */
    public static AwardMethodDTO fromEntity(AwardMethod awardMethod) {
        if (awardMethod == null) return null;
        
        return AwardMethodDTO.builder()
                .id(awardMethod.getId())
                .designationAr(awardMethod.getDesignationAr())
                .designationEn(awardMethod.getDesignationEn())
                .designationFr(awardMethod.getDesignationFr())
                .acronymAr(awardMethod.getAcronymAr())
                .acronymEn(awardMethod.getAcronymEn())
                .acronymFr(awardMethod.getAcronymFr())
                .build();
    }
    
    /**
     * Convert to entity
     */
    public AwardMethod toEntity() {
        AwardMethod awardMethod = new AwardMethod();
        awardMethod.setId(this.id);
        awardMethod.setDesignationAr(this.designationAr);
        awardMethod.setDesignationEn(this.designationEn);
        awardMethod.setDesignationFr(this.designationFr);
        awardMethod.setAcronymAr(this.acronymAr);
        awardMethod.setAcronymEn(this.acronymEn);
        awardMethod.setAcronymFr(this.acronymFr);
        return awardMethod;
    }
    
    /**
     * Update entity from DTO
     */
    public void updateEntity(AwardMethod awardMethod) {
        if (this.designationAr != null) {
            awardMethod.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            awardMethod.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            awardMethod.setDesignationFr(this.designationFr);
        }
        if (this.acronymAr != null) {
            awardMethod.setAcronymAr(this.acronymAr);
        }
        if (this.acronymEn != null) {
            awardMethod.setAcronymEn(this.acronymEn);
        }
        if (this.acronymFr != null) {
            awardMethod.setAcronymFr(this.acronymFr);
        }
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
     * Get default acronym (French acronym is required)
     */
    public String getDefaultAcronym() {
        return acronymFr;
    }
    
    /**
     * Get acronym by language preference
     */
    public String getAcronymByLanguage(String language) {
        if (language == null) return getDefaultAcronym();
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> acronymAr != null ? acronymAr : acronymFr;
            case "en", "english" -> acronymEn != null ? acronymEn : acronymFr;
            case "fr", "french" -> acronymFr;
            default -> acronymFr;
        };
    }
    
    /**
     * Get display text with acronym and designation
     */
    public String getDisplayText() {
        return acronymFr + " - " + getDefaultDesignation();
    }
    
    /**
     * Get display text for specific language
     */
    public String getDisplayTextByLanguage(String language) {
        String acronym = getAcronymByLanguage(language);
        String designation = getDesignationByLanguage(language);
        return acronym + " - " + designation;
    }
    
    /**
     * Check if award method is fully multilingual
     */
    public boolean isMultilingual() {
        return designationAr != null && !designationAr.trim().isEmpty() &&
               designationEn != null && !designationEn.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }
    
    /**
     * Check if award method has multilingual acronyms
     */
    public boolean hasMultilingualAcronyms() {
        return acronymAr != null && !acronymAr.trim().isEmpty() &&
               acronymEn != null && !acronymEn.trim().isEmpty() &&
               acronymFr != null && !acronymFr.trim().isEmpty();
    }
    
    /**
     * Get available languages for this award method
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
     * Create simplified DTO for dropdowns
     */
    public static AwardMethodDTO createSimple(Long id, String acronymFr, String designationFr) {
        return AwardMethodDTO.builder()
                .id(id)
                .acronymFr(acronymFr)
                .designationFr(designationFr)
                .build();
    }
    
    /**
     * Validate all required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() &&
               acronymFr != null && !acronymFr.trim().isEmpty();
    }
    
    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return acronymFr + " (" + (designationFr.length() > 30 ? 
               designationFr.substring(0, 30) + "..." : designationFr) + ")";
    }
    
    /**
     * Get full display with all languages
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(acronymFr).append(" - ");
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
     * Get comparison key for sorting (by French acronym)
     */
    public String getComparisonKey() {
        return acronymFr != null ? acronymFr.toUpperCase() : "";
    }
    
    /**
     * Check if this award method is for specific procurement type
     */
    public boolean isForProcurementType(String procurementType) {
        if (procurementType == null) return false;
        
        String lowerAcronym = acronymFr.toLowerCase();
        String lowerType = procurementType.toLowerCase();
        
        return switch (lowerType) {
            case "open", "ouvert", "مفتوح" -> lowerAcronym.contains("ao") || lowerAcronym.contains("open");
            case "restricted", "restreint", "مقيد" -> lowerAcronym.contains("ar") || lowerAcronym.contains("restr");
            case "negotiated", "negocie", "تفاوضي" -> lowerAcronym.contains("gre") || lowerAcronym.contains("neg");
            case "competitive", "concurrentiel", "تنافسي" -> lowerAcronym.contains("cc") || lowerAcronym.contains("comp");
            case "direct", "مباشر" -> lowerAcronym.contains("gd") || lowerAcronym.contains("direct");
            default -> false;
        };
    }
    
    /**
     * Get award method category based on French acronym
     */
    public String getAwardMethodCategory() {
        if (acronymFr == null) return "UNKNOWN";
        
        String acronym = acronymFr.toUpperCase();
        
        // Common French public procurement award methods
        if (acronym.startsWith("AO")) return "APPEL_OFFRES"; // Appel d'Offres
        if (acronym.startsWith("CC")) return "CONCOURS"; // Concours
        if (acronym.startsWith("GRE")) return "MARCHE_NEGOCIE"; // Gré à Gré/Marché Négocié
        if (acronym.startsWith("CP")) return "CONSULTATION_PRIX"; // Consultation des Prix
        if (acronym.startsWith("DU")) return "DEMANDE_UNIQUE"; // Demande Unique
        if (acronym.startsWith("AC")) return "ACCORD_CADRE"; // Accord Cadre
        
        return "OTHER";
    }
    
    /**
     * Check if this is an open tender method
     */
    public boolean isOpenTenderMethod() {
        return "APPEL_OFFRES".equals(getAwardMethodCategory());
    }
    
    /**
     * Check if this is a negotiated procedure
     */
    public boolean isNegotiatedProcedure() {
        return "MARCHE_NEGOCIE".equals(getAwardMethodCategory());
    }
}