/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicNatureDTO
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

/**
 * Economic Nature Class
 * Maps exactly to EconomicNature model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_01, F_02, F_04, F_05 are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EconomicNatureDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique

    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    private String acronymAr; // F_04 - optional

    @Size(max = 20, message = "English acronym must not exceed 20 characters")
    private String acronymEn; // F_05 - optional

    @NotBlank(message = "French acronym is required")
    @Size(max = 20, message = "French acronym must not exceed 20 characters")
    private String acronymFr; // F_06 - required and unique

    /**
     * Create DTO from entity
     */
    public static EconomicNatureDTO fromEntity(dz.mdn.iaas.business.provider.model.EconomicNature economicNature) {
        if (economicNature == null) return null;
        
        return EconomicNatureDTO.builder()
                .id(economicNature.getId())
                .designationAr(economicNature.getDesignationAr())
                .designationEn(economicNature.getDesignationEn())
                .designationFr(economicNature.getDesignationFr())
                .acronymAr(economicNature.getAcronymAr())
                .acronymEn(economicNature.getAcronymEn())
                .acronymFr(economicNature.getAcronymFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public dz.mdn.iaas.business.provider.model.EconomicNature toEntity() {
        dz.mdn.iaas.business.provider.model.EconomicNature economicNature = 
            new dz.mdn.iaas.business.provider.model.EconomicNature();
        economicNature.setId(this.id);
        economicNature.setDesignationAr(this.designationAr);
        economicNature.setDesignationEn(this.designationEn);
        economicNature.setDesignationFr(this.designationFr);
        economicNature.setAcronymAr(this.acronymAr);
        economicNature.setAcronymEn(this.acronymEn);
        economicNature.setAcronymFr(this.acronymFr);
        return economicNature;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(dz.mdn.iaas.business.provider.model.EconomicNature economicNature) {
        if (this.designationAr != null) {
            economicNature.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            economicNature.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            economicNature.setDesignationFr(this.designationFr);
        }
        if (this.acronymAr != null) {
            economicNature.setAcronymAr(this.acronymAr);
        }
        if (this.acronymEn != null) {
            economicNature.setAcronymEn(this.acronymEn);
        }
        if (this.acronymFr != null) {
            economicNature.setAcronymFr(this.acronymFr);
        }
    }

    /**
     * Get default designation (French as it's required)
     */
    public String getDefaultDesignation() {
        return designationFr;
    }

    /**
     * Get default acronym (French as it's required)
     */
    public String getDefaultAcronym() {
        return acronymFr;
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
     * Get acronym by language preference
     */
    public String getAcronymByLanguage(String language) {
        if (language == null) return acronymFr;
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> acronymAr != null ? acronymAr : acronymFr;
            case "en", "english" -> acronymEn != null ? acronymEn : acronymFr;
            case "fr", "french" -> acronymFr;
            default -> acronymFr;
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
     * Get display acronym
     */
    public String getDisplayAcronym() {
        if (acronymFr != null && !acronymFr.trim().isEmpty()) {
            return acronymFr;
        }
        if (acronymEn != null && !acronymEn.trim().isEmpty()) {
            return acronymEn;
        }
        if (acronymAr != null && !acronymAr.trim().isEmpty()) {
            return acronymAr;
        }
        return "N/A";
    }

    /**
     * Check if economic nature has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this economic nature
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
     * Get economic nature type based on French designation and acronym analysis
     */
    public String getNatureType() {
        if (designationFr == null && acronymFr == null) return "OTHER";
        
        String designation = (designationFr != null ? designationFr : "").toLowerCase();
        String acronym = (acronymFr != null ? acronymFr : "").toLowerCase();
        
        // Public sector entities
        if (designation.contains("public") || designation.contains("état") || designation.contains("administration")) {
            return "PUBLIC_SECTOR";
        }
        if (acronym.equals("epa") || acronym.equals("epic") || designation.contains("établissement public")) {
            return "PUBLIC_ESTABLISHMENT";
        }
        
        // Private sector entities
        if (designation.contains("privé") || designation.contains("particulier")) {
            return "PRIVATE_SECTOR";
        }
        if (acronym.equals("sarl") || designation.contains("société à responsabilité limitée")) {
            return "LIMITED_LIABILITY_COMPANY";
        }
        if (acronym.equals("spa") || designation.contains("société par actions")) {
            return "JOINT_STOCK_COMPANY";
        }
        if (acronym.equals("eurl") || designation.contains("entreprise unipersonnelle")) {
            return "SINGLE_MEMBER_COMPANY";
        }
        if (acronym.equals("snc") || designation.contains("société en nom collectif")) {
            return "GENERAL_PARTNERSHIP";
        }
        if (acronym.equals("scs") || designation.contains("société en commandite")) {
            return "LIMITED_PARTNERSHIP";
        }
        
        // Mixed economy
        if (designation.contains("mixte") || designation.contains("économie mixte")) {
            return "MIXED_ECONOMY";
        }
        
        // Cooperative sector
        if (designation.contains("coopératif") || designation.contains("coopérative")) {
            return "COOPERATIVE";
        }
        if (acronym.equals("scoop") || acronym.equals("coop")) {
            return "COOPERATIVE";
        }
        
        // Individual enterprise
        if (designation.contains("individuel") || designation.contains("personnel")) {
            return "INDIVIDUAL_ENTERPRISE";
        }
        
        // Non-profit organizations
        if (designation.contains("association") || designation.contains("ong") || designation.contains("but non lucratif")) {
            return "NON_PROFIT";
        }
        
        // Foreign entities
        if (designation.contains("étranger") || designation.contains("international") || designation.contains("multinational")) {
            return "FOREIGN_ENTITY";
        }
        
        return "OTHER";
    }

    /**
     * Get ownership structure
     */
    public String getOwnershipStructure() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "STATE_OWNED";
            case "PRIVATE_SECTOR", "LIMITED_LIABILITY_COMPANY", "JOINT_STOCK_COMPANY", 
                 "SINGLE_MEMBER_COMPANY", "GENERAL_PARTNERSHIP", "LIMITED_PARTNERSHIP" -> "PRIVATELY_OWNED";
            case "MIXED_ECONOMY" -> "MIXED_OWNERSHIP";
            case "COOPERATIVE" -> "MEMBER_OWNED";
            case "INDIVIDUAL_ENTERPRISE" -> "SOLE_PROPRIETORSHIP";
            case "NON_PROFIT" -> "NON_PROFIT_ORGANIZATION";
            case "FOREIGN_ENTITY" -> "FOREIGN_OWNED";
            default -> "UNSPECIFIED";
        };
    }

    /**
     * Get legal framework
     */
    public String getLegalFramework() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "PUBLIC_LAW";
            case "LIMITED_LIABILITY_COMPANY", "JOINT_STOCK_COMPANY", "SINGLE_MEMBER_COMPANY" -> "COMMERCIAL_LAW";
            case "GENERAL_PARTNERSHIP", "LIMITED_PARTNERSHIP" -> "PARTNERSHIP_LAW";
            case "COOPERATIVE" -> "COOPERATIVE_LAW";
            case "INDIVIDUAL_ENTERPRISE" -> "INDIVIDUAL_BUSINESS_LAW";
            case "NON_PROFIT" -> "ASSOCIATION_LAW";
            case "MIXED_ECONOMY" -> "MIXED_ECONOMY_LAW";
            default -> "GENERAL_BUSINESS_LAW";
        };
    }

    /**
     * Get economic nature priority (lower number = higher priority for business importance)
     */
    public Integer getNaturePriority() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "PUBLIC_SECTOR" -> 1;
            case "PUBLIC_ESTABLISHMENT" -> 2;
            case "JOINT_STOCK_COMPANY" -> 3;
            case "LIMITED_LIABILITY_COMPANY" -> 4;
            case "MIXED_ECONOMY" -> 5;
            case "COOPERATIVE" -> 6;
            case "SINGLE_MEMBER_COMPANY" -> 7;
            case "GENERAL_PARTNERSHIP" -> 8;
            case "LIMITED_PARTNERSHIP" -> 9;
            case "INDIVIDUAL_ENTERPRISE" -> 10;
            case "FOREIGN_ENTITY" -> 11;
            case "NON_PROFIT" -> 12;
            default -> 99;
        };
    }

    /**
     * Check if nature is government-related
     */
    public boolean isGovernmentRelated() {
        String natureType = getNatureType();
        return "PUBLIC_SECTOR".equals(natureType) || "PUBLIC_ESTABLISHMENT".equals(natureType) || 
               "MIXED_ECONOMY".equals(natureType);
    }

    /**
     * Check if nature requires special registration
     */
    public boolean requiresSpecialRegistration() {
        String natureType = getNatureType();
        return switch (natureType) {
            case "PUBLIC_ESTABLISHMENT", "JOINT_STOCK_COMPANY", "COOPERATIVE", 
                 "FOREIGN_ENTITY", "NON_PROFIT" -> true;
            default -> false;
        };
    }

    /**
     * Check if nature has limited liability
     */
    public boolean hasLimitedLiability() {
        String natureType = getNatureType();
        return switch (natureType) {
            case "LIMITED_LIABILITY_COMPANY", "JOINT_STOCK_COMPANY", "SINGLE_MEMBER_COMPANY", 
                 "LIMITED_PARTNERSHIP", "PUBLIC_ESTABLISHMENT", "COOPERATIVE" -> true;
            default -> false;
        };
    }

    /**
     * Get minimum capital requirement category
     */
    public String getMinimumCapitalCategory() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "JOINT_STOCK_COMPANY" -> "HIGH_CAPITAL"; // 5M+ DZD
            case "LIMITED_LIABILITY_COMPANY" -> "MEDIUM_CAPITAL"; // 100K+ DZD
            case "SINGLE_MEMBER_COMPANY" -> "LOW_CAPITAL"; // 100K+ DZD
            case "PUBLIC_ESTABLISHMENT", "MIXED_ECONOMY" -> "STATE_DETERMINED";
            case "COOPERATIVE" -> "MEMBER_CONTRIBUTIONS";
            case "INDIVIDUAL_ENTERPRISE", "GENERAL_PARTNERSHIP" -> "NO_MINIMUM";
            case "NON_PROFIT" -> "DONATION_BASED";
            default -> "VARIABLE";
        };
    }

    /**
     * Get taxation regime
     */
    public String getTaxationRegime() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "TAX_EXEMPT";
            case "JOINT_STOCK_COMPANY", "LIMITED_LIABILITY_COMPANY" -> "CORPORATE_TAX";
            case "INDIVIDUAL_ENTERPRISE" -> "INDIVIDUAL_TAX";
            case "COOPERATIVE" -> "COOPERATIVE_TAX";
            case "NON_PROFIT" -> "TAX_EXEMPT_ELIGIBLE";
            case "MIXED_ECONOMY" -> "MIXED_TAX_REGIME";
            default -> "STANDARD_BUSINESS_TAX";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static EconomicNatureDTO createSimple(Long id, String designationFr, String acronymFr) {
        return EconomicNatureDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .acronymFr(acronymFr)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() && 
               acronymFr != null && !acronymFr.trim().isEmpty();
    }

    /**
     * Get short display for lists (acronym - designation)
     */
    public String getShortDisplay() {
        return getDisplayAcronym() + " - " + getDisplayText();
    }

    /**
     * Get full display with all languages and nature type
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
        
        sb.append(" (").append(acronymFr).append(")");
        
        String natureType = getNatureType();
        if (!"OTHER".equals(natureType)) {
            sb.append(" - ").append(natureType.replace("_", " "));
        }
        
        return sb.toString();
    }

    /**
     * Get comparison key for sorting (by priority, then designation)
     */
    public String getComparisonKey() {
        Integer priority = getNaturePriority();
        return String.format("%02d_%s", priority, designationFr != null ? designationFr.toLowerCase() : "zzz");
    }

    /**
     * Get display with nature type
     */
    public String getDisplayWithType() {
        String natureType = getNatureType();
        String typeDisplay = natureType.replace("_", " ").toLowerCase();
        return getDisplayText() + " (" + typeDisplay + ")";
    }

    /**
     * Get display with acronym and type
     */
    public String getDisplayWithAcronymAndType() {
        String natureType = getNatureType();
        String typeDisplay = natureType.replace("_", " ").toLowerCase();
        return getDisplayAcronym() + " - " + getDisplayText() + " (" + typeDisplay + ")";
    }

    /**
     * Get formal business display
     */
    public String getFormalBusinessDisplay() {
        return getDisplayText() + " (" + getDisplayAcronym() + ")";
    }

    /**
     * Get regulatory compliance level
     */
    public String getRegulatoryCompliance() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "HIGH_REGULATION";
            case "JOINT_STOCK_COMPANY", "FOREIGN_ENTITY" -> "STRICT_REGULATION";
            case "LIMITED_LIABILITY_COMPANY", "MIXED_ECONOMY" -> "STANDARD_REGULATION";
            case "COOPERATIVE", "NON_PROFIT" -> "MODERATE_REGULATION";
            case "INDIVIDUAL_ENTERPRISE" -> "BASIC_REGULATION";
            default -> "VARIABLE_REGULATION";
        };
    }

    /**
     * Get business flexibility level
     */
    public String getBusinessFlexibility() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "INDIVIDUAL_ENTERPRISE" -> "HIGH_FLEXIBILITY";
            case "GENERAL_PARTNERSHIP", "LIMITED_PARTNERSHIP" -> "MEDIUM_FLEXIBILITY";
            case "LIMITED_LIABILITY_COMPANY", "SINGLE_MEMBER_COMPANY" -> "STANDARD_FLEXIBILITY";
            case "JOINT_STOCK_COMPANY", "COOPERATIVE" -> "MODERATE_FLEXIBILITY";
            case "PUBLIC_ESTABLISHMENT", "MIXED_ECONOMY" -> "LIMITED_FLEXIBILITY";
            case "PUBLIC_SECTOR" -> "LOW_FLEXIBILITY";
            default -> "VARIABLE_FLEXIBILITY";
        };
    }

    /**
     * Get funding access level
     */
    public String getFundingAccess() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "JOINT_STOCK_COMPANY" -> "CAPITAL_MARKETS";
            case "PUBLIC_ESTABLISHMENT", "PUBLIC_SECTOR" -> "GOVERNMENT_FUNDING";
            case "LIMITED_LIABILITY_COMPANY" -> "BANK_FINANCING";
            case "COOPERATIVE" -> "MEMBER_FUNDING";
            case "NON_PROFIT" -> "GRANTS_DONATIONS";
            case "INDIVIDUAL_ENTERPRISE" -> "PERSONAL_FINANCING";
            default -> "MIXED_FUNDING";
        };
    }

    /**
     * Get management structure
     */
    public String getManagementStructure() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "JOINT_STOCK_COMPANY" -> "BOARD_OF_DIRECTORS";
            case "LIMITED_LIABILITY_COMPANY" -> "MANAGER_MANAGED";
            case "PUBLIC_ESTABLISHMENT" -> "GOVERNMENT_APPOINTED";
            case "COOPERATIVE" -> "MEMBER_ELECTED";
            case "INDIVIDUAL_ENTERPRISE" -> "SOLE_OWNER";
            case "GENERAL_PARTNERSHIP" -> "PARTNER_MANAGED";
            default -> "VARIABLE_MANAGEMENT";
        };
    }

    /**
     * Get profit distribution model
     */
    public String getProfitDistribution() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "JOINT_STOCK_COMPANY", "LIMITED_LIABILITY_COMPANY" -> "SHAREHOLDER_DIVIDENDS";
            case "COOPERATIVE" -> "MEMBER_BENEFITS";
            case "INDIVIDUAL_ENTERPRISE" -> "OWNER_PROFITS";
            case "GENERAL_PARTNERSHIP", "LIMITED_PARTNERSHIP" -> "PARTNER_SHARES";
            case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "REINVESTMENT";
            case "NON_PROFIT" -> "NON_DISTRIBUTION";
            default -> "VARIABLE_DISTRIBUTION";
        };
    }

    /**
     * Get liability structure
     */
    public String getLiabilityStructure() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "LIMITED_LIABILITY_COMPANY", "JOINT_STOCK_COMPANY", "SINGLE_MEMBER_COMPANY" -> "LIMITED_LIABILITY";
            case "INDIVIDUAL_ENTERPRISE", "GENERAL_PARTNERSHIP" -> "UNLIMITED_LIABILITY";
            case "LIMITED_PARTNERSHIP" -> "MIXED_LIABILITY";
            case "PUBLIC_ESTABLISHMENT", "COOPERATIVE" -> "INSTITUTIONAL_LIABILITY";
            case "PUBLIC_SECTOR" -> "STATE_LIABILITY";
            default -> "VARIABLE_LIABILITY";
        };
    }

    /**
     * Get business registration authority
     */
    public String getRegistrationAuthority() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "MINISTRY_LEVEL";
            case "JOINT_STOCK_COMPANY" -> "SECURITIES_COMMISSION";
            case "COOPERATIVE" -> "COOPERATIVE_AUTHORITY";
            case "NON_PROFIT" -> "ASSOCIATION_REGISTRY";
            case "FOREIGN_ENTITY" -> "FOREIGN_INVESTMENT_AGENCY";
            default -> "COMMERCIAL_REGISTRY";
        };
    }

    /**
     * Get dissolution process complexity
     */
    public String getDissolutionComplexity() {
        String natureType = getNatureType();
        
        return switch (natureType) {
            case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "GOVERNMENTAL_PROCESS";
            case "JOINT_STOCK_COMPANY" -> "COMPLEX_PROCEDURE";
            case "LIMITED_LIABILITY_COMPANY", "COOPERATIVE" -> "STANDARD_PROCEDURE";
            case "INDIVIDUAL_ENTERPRISE" -> "SIMPLE_PROCEDURE";
            case "NON_PROFIT" -> "REGULATORY_APPROVAL";
            default -> "VARIABLE_PROCEDURE";
        };
    }
}