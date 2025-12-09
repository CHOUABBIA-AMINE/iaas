/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementNatureDTO
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
 * ProcurementNature Data Transfer Object
 * Maps exactly to ProcurementNature model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcurementNatureDTO {

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
    public static ProcurementNatureDTO fromEntity(dz.mdn.iaas.business.core.model.ProcurementNature procurementNature) {
        if (procurementNature == null) return null;
        
        return ProcurementNatureDTO.builder()
                .id(procurementNature.getId())
                .designationAr(procurementNature.getDesignationAr())
                .designationEn(procurementNature.getDesignationEn())
                .designationFr(procurementNature.getDesignationFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public dz.mdn.iaas.business.core.model.ProcurementNature toEntity() {
        dz.mdn.iaas.business.core.model.ProcurementNature procurementNature = new dz.mdn.iaas.business.core.model.ProcurementNature();
        procurementNature.setId(this.id);
        procurementNature.setDesignationAr(this.designationAr);
        procurementNature.setDesignationEn(this.designationEn);
        procurementNature.setDesignationFr(this.designationFr);
        return procurementNature;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(dz.mdn.iaas.business.core.model.ProcurementNature procurementNature) {
        if (this.designationAr != null) {
            procurementNature.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            procurementNature.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            procurementNature.setDesignationFr(this.designationFr);
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
     * Check if procurement nature has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this procurement nature
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
     * Get procurement nature category based on French designation analysis
     */
    public String getNatureCategory() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Infrastructure and construction
        if (designation.contains("infrastructure") || designation.contains("construction") || 
            designation.contains("bâtiment") || designation.contains("ouvrage")) {
            return "INFRASTRUCTURE";
        }
        
        // Technology and IT
        if (designation.contains("technologie") || designation.contains("informatique") || 
            designation.contains("numérique") || designation.contains("digital")) {
            return "TECHNOLOGY";
        }
        
        // Services
        if (designation.contains("service") || designation.contains("prestation") || 
            designation.contains("conseil") || designation.contains("consultation")) {
            return "SERVICES";
        }
        
        // Manufacturing and production
        if (designation.contains("fabrication") || designation.contains("production") || 
            designation.contains("manufacturier") || designation.contains("industriel")) {
            return "MANUFACTURING";
        }
        
        // Research and development
        if (designation.contains("recherche") || designation.contains("développement") || 
            designation.contains("innovation") || designation.contains("r&d")) {
            return "RESEARCH_DEVELOPMENT";
        }
        
        // Energy and utilities
        if (designation.contains("énergie") || designation.contains("électrique") || 
            designation.contains("hydraulique") || designation.contains("utilities")) {
            return "ENERGY_UTILITIES";
        }
        
        // Environmental
        if (designation.contains("environnement") || designation.contains("écologique") || 
            designation.contains("durable") || designation.contains("vert")) {
            return "ENVIRONMENTAL";
        }
        
        // Commercial and business
        if (designation.contains("commercial") || designation.contains("affaires") || 
            designation.contains("business") || designation.contains("marché")) {
            return "COMMERCIAL";
        }
        
        // Education and training
        if (designation.contains("éducation") || designation.contains("formation") || 
            designation.contains("enseignement") || designation.contains("pédagogique")) {
            return "EDUCATION";
        }
        
        // Health and medical
        if (designation.contains("santé") || designation.contains("médical") || 
            designation.contains("hospitalier") || designation.contains("thérapeutique")) {
            return "HEALTH_MEDICAL";
        }
        
        // Transportation
        if (designation.contains("transport") || designation.contains("logistique") || 
            designation.contains("mobilité") || designation.contains("circulation")) {
            return "TRANSPORTATION";
        }
        
        // Agricultural
        if (designation.contains("agricole") || designation.contains("rural") || 
            designation.contains("agronomique") || designation.contains("cultivation")) {
            return "AGRICULTURAL";
        }
        
        return "GENERAL";
    }

    /**
     * Get complexity level based on nature category
     */
    public String getComplexityLevel() {
        return switch (getNatureCategory()) {
            case "INFRASTRUCTURE" -> "HIGH";
            case "TECHNOLOGY", "RESEARCH_DEVELOPMENT" -> "HIGH";
            case "MANUFACTURING", "ENERGY_UTILITIES" -> "MEDIUM";
            case "SERVICES", "COMMERCIAL" -> "LOW";
            case "EDUCATION", "HEALTH_MEDICAL" -> "MEDIUM";
            case "TRANSPORTATION", "ENVIRONMENTAL" -> "MEDIUM";
            case "AGRICULTURAL" -> "LOW";
            default -> "MEDIUM";
        };
    }

    /**
     * Get typical duration category for this nature
     */
    public String getDurationCategory() {
        return switch (getNatureCategory()) {
            case "INFRASTRUCTURE", "ENERGY_UTILITIES" -> "LONG_TERM";
            case "TECHNOLOGY", "RESEARCH_DEVELOPMENT" -> "MEDIUM_TERM";
            case "MANUFACTURING" -> "MEDIUM_TERM";
            case "SERVICES", "COMMERCIAL" -> "SHORT_TERM";
            case "EDUCATION" -> "MEDIUM_TERM";
            case "HEALTH_MEDICAL" -> "MEDIUM_TERM";
            case "TRANSPORTATION" -> "LONG_TERM";
            case "ENVIRONMENTAL" -> "LONG_TERM";
            case "AGRICULTURAL" -> "SEASONAL";
            default -> "MEDIUM_TERM";
        };
    }

    /**
     * Get stakeholder involvement level
     */
    public String getStakeholderLevel() {
        return switch (getNatureCategory()) {
            case "INFRASTRUCTURE", "TRANSPORTATION" -> "HIGH";
            case "ENERGY_UTILITIES", "ENVIRONMENTAL" -> "HIGH";
            case "HEALTH_MEDICAL", "EDUCATION" -> "HIGH";
            case "TECHNOLOGY", "RESEARCH_DEVELOPMENT" -> "MEDIUM";
            case "MANUFACTURING", "AGRICULTURAL" -> "MEDIUM";
            case "SERVICES", "COMMERCIAL" -> "LOW";
            default -> "MEDIUM";
        };
    }

    /**
     * Check if this nature requires environmental assessment
     */
    public boolean requiresEnvironmentalAssessment() {
        String category = getNatureCategory();
        return "INFRASTRUCTURE".equals(category) || 
               "ENERGY_UTILITIES".equals(category) || 
               "ENVIRONMENTAL".equals(category) || 
               "MANUFACTURING".equals(category) ||
               "TRANSPORTATION".equals(category);
    }

    /**
     * Check if this nature requires technical expertise
     */
    public boolean requiresTechnicalExpertise() {
        String category = getNatureCategory();
        return "TECHNOLOGY".equals(category) || 
               "RESEARCH_DEVELOPMENT".equals(category) || 
               "MANUFACTURING".equals(category) ||
               "ENERGY_UTILITIES".equals(category) ||
               "INFRASTRUCTURE".equals(category);
    }

    /**
     * Get regulatory compliance level
     */
    public String getRegulatoryCompliance() {
        return switch (getNatureCategory()) {
            case "HEALTH_MEDICAL", "ENERGY_UTILITIES" -> "STRICT";
            case "INFRASTRUCTURE", "TRANSPORTATION" -> "STRICT";
            case "ENVIRONMENTAL", "MANUFACTURING" -> "STRICT";
            case "EDUCATION", "TECHNOLOGY" -> "MODERATE";
            case "SERVICES", "COMMERCIAL" -> "STANDARD";
            case "AGRICULTURAL", "RESEARCH_DEVELOPMENT" -> "MODERATE";
            default -> "STANDARD";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ProcurementNatureDTO createSimple(Long id, String designationFr) {
        return ProcurementNatureDTO.builder()
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
     * Get display with nature category
     */
    public String getDisplayWithCategory() {
        return designationFr + " (" + getNatureCategory().replace("_", " ").toLowerCase() + ")";
    }

    /**
     * Get nature priority for project planning
     */
    public int getNaturePriority() {
        return switch (getNatureCategory()) {
            case "HEALTH_MEDICAL" -> 1;
            case "INFRASTRUCTURE" -> 2;
            case "ENERGY_UTILITIES" -> 3;
            case "EDUCATION" -> 4;
            case "TRANSPORTATION" -> 5;
            case "ENVIRONMENTAL" -> 6;
            case "TECHNOLOGY" -> 7;
            case "RESEARCH_DEVELOPMENT" -> 8;
            case "MANUFACTURING" -> 9;
            case "AGRICULTURAL" -> 10;
            case "SERVICES" -> 11;
            case "COMMERCIAL" -> 12;
            default -> 13;
        };
    }

    /**
     * Check if nature involves public interest
     */
    public boolean involvesPublicInterest() {
        String category = getNatureCategory();
        return "INFRASTRUCTURE".equals(category) || 
               "HEALTH_MEDICAL".equals(category) || 
               "EDUCATION".equals(category) ||
               "TRANSPORTATION".equals(category) ||
               "ENERGY_UTILITIES".equals(category) ||
               "ENVIRONMENTAL".equals(category);
    }

    /**
     * Get risk assessment level
     */
    public String getRiskLevel() {
        return switch (getComplexityLevel()) {
            case "HIGH" -> "HIGH";
            case "MEDIUM" -> "MEDIUM";
            case "LOW" -> "LOW";
            default -> "MEDIUM";
        };
    }
}
