/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CurrencyDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.core.model.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Currency Data Transfer Object
 * Maps exactly to Currency model fields: F_00=id, F_01=designationAr, F_02=designationEn, 
 * F_03=designationFr, F_04=codeAr, F_05=codeLt
 * All fields F_01 through F_05 have unique constraints and are required
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyDTO {

    private Long id; // F_00

    @NotBlank(message = "English designation is required")
    @Size(max = 20, message = "English designation must not exceed 50 characters")
    private String code; // F_01 - required and unique

    @NotBlank(message = "Arabic designation is required")
    @Size(max = 50, message = "Arabic designation must not exceed 50 characters")
    private String designationAr; // F_02 - required and unique

    @NotBlank(message = "English designation is required")
    @Size(max = 50, message = "English designation must not exceed 50 characters")
    private String designationEn; // F_03 - required and unique

    @NotBlank(message = "French designation is required")
    @Size(max = 50, message = "French designation must not exceed 50 characters")
    private String designationFr; // F_04 - required and unique

    @NotBlank(message = "Arabic code is required")
    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    private String acronymAr; // F_05 - required and unique

    @NotBlank(message = "English code is required")
    @Size(max = 20, message = "Latin acronym must not exceed 20 characters")
    private String acronymEn; // F_06 - required and unique

    @NotBlank(message = "French code is required")
    @Size(max = 20, message = "Latin acronym must not exceed 20 characters")
    private String acronymFr; // F_07 - required and unique

    /**
     * Create DTO from entity
     */
    public static CurrencyDTO fromEntity(Currency currency) {
        if (currency == null) return null;
        
        return CurrencyDTO.builder()
                .id(currency.getId())
                .code(currency.getCode())
                .designationAr(currency.getDesignationAr())
                .designationEn(currency.getDesignationEn())
                .designationFr(currency.getDesignationFr())
                .acronymAr(currency.getAcronymAr())
                .acronymEn(currency.getAcronymEn())
                .acronymFr(currency.getAcronymFr())
                .build();
    }

    /**
     * Convert to entity
     */
    public Currency toEntity() {
        Currency currency = new Currency();
        currency.setId(this.id);
        currency.setCode(this.code);
        currency.setDesignationAr(this.designationAr);
        currency.setDesignationEn(this.designationEn);
        currency.setDesignationFr(this.designationFr);
        currency.setAcronymAr(this.acronymAr);
        currency.setAcronymEn(this.acronymEn);
        currency.setAcronymFr(this.acronymFr);
        return currency;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(Currency currency) {
        if (this.code != null) {
            currency.setCode(this.code);
        }
        if (this.designationAr != null) {
            currency.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            currency.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            currency.setDesignationFr(this.designationFr);
        }
        if (this.acronymAr != null) {
            currency.setAcronymAr(this.acronymAr);
        }
        if (this.acronymEn != null) {
            currency.setAcronymEn(this.acronymEn);
        }
        if (this.acronymFr != null) {
            currency.setAcronymFr(this.acronymFr);
        }
    }

    /**
     * Get default designation based on system locale
     */
    public String getDefaultDesignation() {
        // Prioritize French designation as it's commonly used in Algeria
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
     * Get default acronym
     */
    public String getDefaultAcronym() {
    	return acronymFr != null ? acronymFr : 
            (acronymEn != null ? acronymEn : acronymAr);
    }

    /**
     * Get code by script preference
     */
    public String getAcronymByLanguage(String language) {
        if (language == null) return getDefaultDesignation();
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> acronymAr != null ? acronymAr : getDefaultAcronym();
            case "en", "english" -> acronymEn != null ? acronymEn : getDefaultAcronym();
            case "fr", "french" -> acronymFr != null ? acronymFr : getDefaultAcronym();
            default -> getDefaultDesignation();
        };
    }

    /**
     * Get display text with code and designation
     */
    public String getDisplayText() {
        return getDefaultAcronym() + " - " + getDefaultDesignation();
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
     * Check if currency is fully multilingual
     */
    public boolean isMultilingual() {
        return designationAr != null && !designationAr.trim().isEmpty() &&
               designationEn != null && !designationEn.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty();
    }

    /**
     * Check if currency has dual code system
     */
    public boolean isCodeMultilingual() {
        return acronymAr != null && !acronymAr.trim().isEmpty() &&
        		acronymEn != null && !acronymEn.trim().isEmpty() &&
        		acronymFr != null && !acronymFr.trim().isEmpty();
    }

    /**
     * Get available languages for this currency
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
     * Get currency type based on code pattern (ISO 4217 standard analysis)
     */
    public String getCurrencyType() {
        if (acronymFr == null) return "UNKNOWN";
        
        String acronym = acronymFr.toUpperCase();
        
        // Major international currencies
        if ("USD".equals(acronym) || "EUR".equals(acronym) || "GBP".equals(acronym) || 
            "JPY".equals(acronym) || "CHF".equals(acronym) || "CAD".equals(acronym) || 
            "AUD".equals(acronym)) {
            return "MAJOR_CURRENCY";
        }
        
        // Regional currencies (Africa, Middle East)
        if ("DZD".equals(acronym) || "MAD".equals(acronym) || "TND".equals(acronym) || 
            "EGP".equals(acronym) || "SAR".equals(acronym) || "AED".equals(acronym)) {
            return "REGIONAL_CURRENCY";
        }
        
        // Cryptocurrency patterns (if applicable)
        if (acronym.startsWith("BTC") || acronym.startsWith("ETH") || acronym.startsWith("XRP")) {
            return "CRYPTOCURRENCY";
        }
        
        return "OTHER_CURRENCY";
    }

    /**
     * Check if this is a major international currency
     */
    public boolean isMajorCurrency() {
        return "MAJOR_CURRENCY".equals(getCurrencyType());
    }

    /**
     * Check if this is a regional currency
     */
    public boolean isRegionalCurrency() {
        return "REGIONAL_CURRENCY".equals(getCurrencyType());
    }

    /**
     * Get currency symbol based on Latin code (basic mapping)
     */
    public String getCurrencySymbol() {
        if (acronymFr == null) return "";
        
        return switch (acronymFr.toUpperCase()) {
            case "USD" -> "$";
            case "EUR" -> "€";
            case "GBP" -> "£";
            case "JPY" -> "¥";
            case "DZD" -> "د.ج";
            case "MAD" -> "د.م";
            case "TND" -> "د.ت";
            case "SAR" -> "ر.س";
            case "AED" -> "د.إ";
            default -> acronymFr;
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static CurrencyDTO createSimple(Long id, String acronymFr, String designationFr) {
        return CurrencyDTO.builder()
                .id(id)
                .acronymFr(acronymFr)
                .designationFr(designationFr)
                .build();
    }

    /**
     * Validate all required fields are present
     */
    public boolean isValid() {
        return designationAr != null && !designationAr.trim().isEmpty() &&
               designationEn != null && !designationEn.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty() &&
               acronymAr != null && !acronymAr.trim().isEmpty() &&
               acronymEn != null && !acronymEn.trim().isEmpty() &&
               acronymFr != null && !acronymFr.trim().isEmpty() ;
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return acronymFr + " (" + (designationFr.length() > 20 ? 
                designationFr.substring(0, 20) + "..." : designationFr) + ")";
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
     * Get comparison key for sorting (by Latin code)
     */
    public String getComparisonKey() {
        return acronymFr != null ? acronymFr.toUpperCase() : "";
    }
}
