/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicDomainDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.provider.model.EconomicDomain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Economic Domain Data Transfer Object
 * Maps exactly to EconomicDomain model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EconomicDomainDTO {

    private Long id; // F_00

    @NotBlank(message = "Code is required")
    private int code; // F_03 - required and unique

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
    public static EconomicDomainDTO fromEntity(EconomicDomain economicDomain) {
        if (economicDomain == null) return null;
        
        return EconomicDomainDTO.builder()
                .id(economicDomain.getId())
                .code(economicDomain.getCode())
                .designationAr(economicDomain.getDesignationAr())
                .designationEn(economicDomain.getDesignationEn())
                .designationFr(economicDomain.getDesignationFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public EconomicDomain toEntity() {
        EconomicDomain economicDomain = new EconomicDomain();
        economicDomain.setId(this.id);
        economicDomain.setCode(this.code);
        economicDomain.setDesignationAr(this.designationAr);
        economicDomain.setDesignationEn(this.designationEn);
        economicDomain.setDesignationFr(this.designationFr);
        return economicDomain;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(EconomicDomain economicDomain) {
        if (this.code != 0) {
            economicDomain.setCode(this.code);
        }
        if (this.designationAr != null) {
            economicDomain.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            economicDomain.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            economicDomain.setDesignationFr(this.designationFr);
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
            return code + "" + designationFr;
        }
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            return code + "" + designationEn;
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            return code + "" + designationAr;
        }
        return "N/A";
    }

    /**
     * Check if economic domain has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this economic domain
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
     * Get economic domain type based on French designation analysis
     */
    public String getDomainType() {
        if (designationFr == null) return "OTHER";
        
        String designation = designationFr.toLowerCase();
        
        // Primary sectors
        if (designation.contains("agriculture") || designation.contains("agricole")) {
            return "AGRICULTURE";
        }
        if (designation.contains("pêche") || designation.contains("pisciculture")) {
            return "FISHERIES";
        }
        if (designation.contains("forestier") || designation.contains("forêt")) {
            return "FORESTRY";
        }
        if (designation.contains("minier") || designation.contains("mine") || designation.contains("extraction")) {
            return "MINING";
        }
        if (designation.contains("pétrole") || designation.contains("gazier") || designation.contains("hydrocarbure")) {
            return "OIL_GAS";
        }
        
        // Secondary sectors
        if (designation.contains("industrie") || designation.contains("industriel")) {
            if (designation.contains("alimentaire") || designation.contains("agroalimentaire")) {
                return "FOOD_INDUSTRY";
            }
            if (designation.contains("textile") || designation.contains("vêtement")) {
                return "TEXTILE";
            }
            if (designation.contains("automobile") || designation.contains("mécanique")) {
                return "AUTOMOTIVE";
            }
            if (designation.contains("chimique") || designation.contains("pharmaceutique")) {
                return "CHEMICAL_PHARMA";
            }
            if (designation.contains("sidérurgie") || designation.contains("métallurgie")) {
                return "METALLURGY";
            }
            if (designation.contains("électronique") || designation.contains("informatique")) {
                return "ELECTRONICS_IT";
            }
            return "MANUFACTURING";
        }
        if (designation.contains("construction") || designation.contains("bâtiment") || designation.contains("travaux")) {
            return "CONSTRUCTION";
        }
        if (designation.contains("énergie") || designation.contains("électricité")) {
            return "ENERGY";
        }
        
        // Tertiary sectors
        if (designation.contains("commerce") || designation.contains("commercial")) {
            return "COMMERCE";
        }
        if (designation.contains("transport") || designation.contains("logistique")) {
            return "TRANSPORT_LOGISTICS";
        }
        if (designation.contains("banque") || designation.contains("bancaire") || designation.contains("finance")) {
            return "BANKING_FINANCE";
        }
        if (designation.contains("assurance")) {
            return "INSURANCE";
        }
        if (designation.contains("immobilier")) {
            return "REAL_ESTATE";
        }
        if (designation.contains("tourisme") || designation.contains("hôtellerie") || designation.contains("restauration")) {
            return "TOURISM_HOSPITALITY";
        }
        if (designation.contains("santé") || designation.contains("médical")) {
            return "HEALTHCARE";
        }
        if (designation.contains("éducation") || designation.contains("enseignement")) {
            return "EDUCATION";
        }
        if (designation.contains("télécommunication") || designation.contains("communication")) {
            return "TELECOMMUNICATIONS";
        }
        if (designation.contains("service")) {
            return "SERVICES";
        }
        
        // Quaternary sectors
        if (designation.contains("recherche") || designation.contains("développement") || designation.contains("innovation")) {
            return "RESEARCH_DEVELOPMENT";
        }
        if (designation.contains("technologie") || designation.contains("numérique") || designation.contains("digital")) {
            return "TECHNOLOGY";
        }
        
        return "OTHER";
    }

    /**
     * Get economic sector classification
     */
    public String getEconomicSector() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "AGRICULTURE", "FISHERIES", "FORESTRY", "MINING", "OIL_GAS" -> "PRIMARY";
            case "MANUFACTURING", "FOOD_INDUSTRY", "TEXTILE", "AUTOMOTIVE", "CHEMICAL_PHARMA", 
                 "METALLURGY", "ELECTRONICS_IT", "CONSTRUCTION", "ENERGY" -> "SECONDARY";
            case "COMMERCE", "TRANSPORT_LOGISTICS", "BANKING_FINANCE", "INSURANCE", "REAL_ESTATE",
                 "TOURISM_HOSPITALITY", "HEALTHCARE", "EDUCATION", "TELECOMMUNICATIONS", "SERVICES" -> "TERTIARY";
            case "RESEARCH_DEVELOPMENT", "TECHNOLOGY" -> "QUATERNARY";
            default -> "UNCLASSIFIED";
        };
    }

    /**
     * Get domain priority (lower number = higher priority for national economy)
     */
    public Integer getDomainPriority() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "OIL_GAS" -> 1;
            case "AGRICULTURE" -> 2;
            case "MINING" -> 3;
            case "MANUFACTURING" -> 4;
            case "ENERGY" -> 5;
            case "CONSTRUCTION" -> 6;
            case "BANKING_FINANCE" -> 7;
            case "TRANSPORT_LOGISTICS" -> 8;
            case "TELECOMMUNICATIONS" -> 9;
            case "HEALTHCARE" -> 10;
            case "EDUCATION" -> 11;
            case "TOURISM_HOSPITALITY" -> 12;
            case "TECHNOLOGY" -> 13;
            case "RESEARCH_DEVELOPMENT" -> 14;
            case "COMMERCE" -> 15;
            case "SERVICES" -> 16;
            default -> 99;
        };
    }

    /**
     * Check if domain is strategic for national economy
     */
    public boolean isStrategicDomain() {
        String domainType = getDomainType();
        return switch (domainType) {
            case "OIL_GAS", "AGRICULTURE", "MINING", "ENERGY", "BANKING_FINANCE", 
                 "TELECOMMUNICATIONS", "HEALTHCARE", "EDUCATION" -> true;
            default -> false;
        };
    }

    /**
     * Check if domain is export-oriented
     */
    public boolean isExportOriented() {
        String domainType = getDomainType();
        return switch (domainType) {
            case "OIL_GAS", "MINING", "AGRICULTURE", "MANUFACTURING", "TEXTILE", 
                 "AUTOMOTIVE", "CHEMICAL_PHARMA", "TOURISM_HOSPITALITY" -> true;
            default -> false;
        };
    }

    /**
     * Check if domain requires high investment
     */
    public boolean requiresHighInvestment() {
        String domainType = getDomainType();
        return switch (domainType) {
            case "OIL_GAS", "MINING", "ENERGY", "MANUFACTURING", "AUTOMOTIVE", 
                 "CHEMICAL_PHARMA", "METALLURGY", "CONSTRUCTION", "TELECOMMUNICATIONS", 
                 "RESEARCH_DEVELOPMENT", "TECHNOLOGY" -> true;
            default -> false;
        };
    }

    /**
     * Get typical business license category
     */
    public String getBusinessLicenseCategory() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "OIL_GAS", "MINING" -> "EXTRACTIVE_INDUSTRY";
            case "MANUFACTURING", "FOOD_INDUSTRY", "TEXTILE", "AUTOMOTIVE", 
                 "CHEMICAL_PHARMA", "METALLURGY", "ELECTRONICS_IT" -> "INDUSTRIAL";
            case "CONSTRUCTION" -> "CONSTRUCTION_PERMIT";
            case "BANKING_FINANCE", "INSURANCE" -> "FINANCIAL_SERVICES";
            case "HEALTHCARE" -> "HEALTH_SERVICES";
            case "EDUCATION" -> "EDUCATIONAL_SERVICES";
            case "TELECOMMUNICATIONS" -> "TELECOM_LICENSE";
            case "TRANSPORT_LOGISTICS" -> "TRANSPORT_PERMIT";
            case "TOURISM_HOSPITALITY" -> "HOSPITALITY_LICENSE";
            case "COMMERCE" -> "COMMERCIAL_LICENSE";
            default -> "GENERAL_BUSINESS";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static EconomicDomainDTO createSimple(Long id, String designationFr) {
        return EconomicDomainDTO.builder()
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
        String text = getDisplayText();
        return text.length() > 50 ? text.substring(0, 50) + "..." : text;
    }

    /**
     * Get full display with all languages and domain type
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
        
        String domainType = getDomainType();
        if (!"OTHER".equals(domainType)) {
            sb.append(" (").append(domainType.replace("_", " ")).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get comparison key for sorting (by priority, then designation)
     */
    public String getComparisonKey() {
        Integer priority = getDomainPriority();
        return String.format("%02d_%s", priority, designationFr != null ? designationFr.toLowerCase() : "zzz");
    }

    /**
     * Get display with domain type
     */
    public String getDisplayWithType() {
        String domainType = getDomainType();
        String typeDisplay = domainType.replace("_", " ").toLowerCase();
        return getDisplayText() + " (" + typeDisplay + ")";
    }

    /**
     * Get display with economic sector
     */
    public String getDisplayWithSector() {
        String sector = getEconomicSector();
        return getDisplayText() + " [" + sector + "]";
    }

    /**
     * Get regulatory framework
     */
    public String getRegulatoryFramework() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "OIL_GAS" -> "MINISTRY_OF_ENERGY";
            case "MINING" -> "MINISTRY_OF_MINES";
            case "AGRICULTURE", "FISHERIES", "FORESTRY" -> "MINISTRY_OF_AGRICULTURE";
            case "BANKING_FINANCE" -> "CENTRAL_BANK";
            case "INSURANCE" -> "INSURANCE_SUPERVISION";
            case "TELECOMMUNICATIONS" -> "TELECOM_AUTHORITY";
            case "HEALTHCARE" -> "MINISTRY_OF_HEALTH";
            case "EDUCATION" -> "MINISTRY_OF_EDUCATION";
            case "TRANSPORT_LOGISTICS" -> "MINISTRY_OF_TRANSPORT";
            case "TOURISM_HOSPITALITY" -> "MINISTRY_OF_TOURISM";
            case "CONSTRUCTION" -> "MINISTRY_OF_HOUSING";
            default -> "MINISTRY_OF_COMMERCE";
        };
    }

    /**
     * Get employment impact level
     */
    public String getEmploymentImpact() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "AGRICULTURE", "CONSTRUCTION", "SERVICES", "COMMERCE", "TOURISM_HOSPITALITY" -> "HIGH";
            case "MANUFACTURING", "TRANSPORT_LOGISTICS", "HEALTHCARE", "EDUCATION" -> "MEDIUM";
            case "OIL_GAS", "MINING", "BANKING_FINANCE", "TELECOMMUNICATIONS", "TECHNOLOGY" -> "LOW";
            default -> "VARIABLE";
        };
    }

    /**
     * Get GDP contribution level
     */
    public String getGDPContribution() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "OIL_GAS", "MANUFACTURING", "BANKING_FINANCE" -> "HIGH";
            case "AGRICULTURE", "CONSTRUCTION", "COMMERCE", "TRANSPORT_LOGISTICS" -> "MEDIUM";
            case "SERVICES", "HEALTHCARE", "EDUCATION", "TOURISM_HOSPITALITY" -> "LOW";
            default -> "MINIMAL";
        };
    }

    /**
     * Get foreign investment attractiveness
     */
    public String getForeignInvestmentAttractiveness() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "TECHNOLOGY", "TELECOMMUNICATIONS", "BANKING_FINANCE", "MANUFACTURING", 
                 "TOURISM_HOSPITALITY", "ENERGY" -> "HIGH";
            case "HEALTHCARE", "EDUCATION", "TRANSPORT_LOGISTICS", "AGRICULTURE" -> "MEDIUM";
            case "OIL_GAS", "MINING" -> "RESTRICTED";
            default -> "LOW";
        };
    }

    /**
     * Get digitalization readiness
     */
    public String getDigitalizationReadiness() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "TECHNOLOGY", "BANKING_FINANCE", "TELECOMMUNICATIONS", "EDUCATION" -> "HIGH";
            case "COMMERCE", "HEALTHCARE", "SERVICES", "MANUFACTURING" -> "MEDIUM";
            case "CONSTRUCTION", "TRANSPORT_LOGISTICS", "TOURISM_HOSPITALITY" -> "LOW";
            case "AGRICULTURE", "MINING", "OIL_GAS", "FISHERIES", "FORESTRY" -> "MINIMAL";
            default -> "UNKNOWN";
        };
    }

    /**
     * Get environmental impact level
     */
    public String getEnvironmentalImpact() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "OIL_GAS", "MINING", "CHEMICAL_PHARMA", "METALLURGY" -> "HIGH";
            case "MANUFACTURING", "CONSTRUCTION", "ENERGY", "TRANSPORT_LOGISTICS" -> "MEDIUM";
            case "AGRICULTURE", "FISHERIES", "FORESTRY" -> "VARIABLE";
            case "SERVICES", "BANKING_FINANCE", "EDUCATION", "HEALTHCARE", "TECHNOLOGY" -> "LOW";
            default -> "UNKNOWN";
        };
    }

    /**
     * Get skill requirements level
     */
    public String getSkillRequirements() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "TECHNOLOGY", "RESEARCH_DEVELOPMENT", "BANKING_FINANCE", "HEALTHCARE", 
                 "EDUCATION", "TELECOMMUNICATIONS" -> "HIGH_SKILLED";
            case "MANUFACTURING", "CHEMICAL_PHARMA", "ELECTRONICS_IT", "ENERGY" -> "SKILLED";
            case "COMMERCE", "SERVICES", "TOURISM_HOSPITALITY", "TRANSPORT_LOGISTICS" -> "SEMI_SKILLED";
            case "CONSTRUCTION", "AGRICULTURE", "MINING" -> "MIXED";
            default -> "VARIABLE";
        };
    }

    /**
     * Get market competition level
     */
    public String getMarketCompetition() {
        String domainType = getDomainType();
        
        return switch (domainType) {
            case "COMMERCE", "SERVICES", "TOURISM_HOSPITALITY", "TECHNOLOGY" -> "HIGH";
            case "MANUFACTURING", "CONSTRUCTION", "TRANSPORT_LOGISTICS", "HEALTHCARE" -> "MEDIUM";
            case "BANKING_FINANCE", "TELECOMMUNICATIONS", "ENERGY", "EDUCATION" -> "REGULATED";
            case "OIL_GAS", "MINING" -> "MONOPOLISTIC";
            default -> "VARIABLE";
        };
    }
}
