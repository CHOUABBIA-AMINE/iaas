/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CurrencyDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Business / Core / DTO
 *	@Package	: Business / Core / DTO
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
 * 
 * Fields:
 * - id (F_00)
 * - code (F_01) - unique, required
 * - designationAr (F_02) - unique, required
 * - designationEn (F_03) - unique, required
 * - designationFr (F_04) - unique, required
 * - acronymAr (F_05) - unique, required
 * - acronymEn (F_06) - unique, required
 * - acronymFr (F_07) - unique, required
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyDTO {

    private Long id;

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Arabic designation is required")
    @Size(max = 50, message = "Arabic designation must not exceed 50 characters")
    private String designationAr;

    @NotBlank(message = "English designation is required")
    @Size(max = 50, message = "English designation must not exceed 50 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 50, message = "French designation must not exceed 50 characters")
    private String designationFr;

    @NotBlank(message = "Arabic acronym is required")
    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    private String acronymAr;

    @NotBlank(message = "English acronym is required")
    @Size(max = 20, message = "English acronym must not exceed 20 characters")
    private String acronymEn;

    @NotBlank(message = "French acronym is required")
    @Size(max = 20, message = "French acronym must not exceed 20 characters")
    private String acronymFr;

    // ========== ENTITY MAPPING ==========

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
     * Convert DTO to entity
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
}
