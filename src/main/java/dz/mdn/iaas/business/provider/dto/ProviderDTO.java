/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderDTO
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

import java.util.Date;
import java.util.List;

/**
 * Provider Data Transfer Object
 * Maps exactly to Provider model fields: F_00=id, F_01=designationLt, F_02=designationAr, F_03=acronymLt, F_04=acronymAr, 
 * F_05=address, F_06=capital, F_07=comercialRegistryNumber, F_08=comercialRegistryDate, F_09=taxeIdentityNumber, 
 * F_10=statIdentityNumber, F_11=bank, F_12=bankAccount, F_13=swiftNumber, F_14=phoneNumbers, F_15=faxNumbers, 
 * F_16=mail, F_17=website, F_18=logoId, F_19=economicNatureId, F_20=countryId, F_21=stateId
 * Many-to-Many: economicDomainIds
 * One-to-Many: providerExclusionIds, providerRepresentatorIds, clearanceIds, submissionIds
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Latin designation must not exceed 200 characters")
    private String designationLt; // F_01 - optional

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_02 - optional

    @Size(max = 20, message = "Latin acronym must not exceed 20 characters")
    private String acronymLt; // F_03 - optional

    @Size(max = 20, message = "Arabic acronym must not exceed 20 characters")
    private String acronymAr; // F_04 - optional

    @Size(max = 200, message = "Address must not exceed 200 characters")
    private String address; // F_05 - optional

    @DecimalMin(value = "0.0", message = "Capital must be non-negative")
    private Double capital; // F_06 - optional

    @Size(max = 200, message = "Commercial registry number must not exceed 200 characters")
    private String comercialRegistryNumber; // F_07 - optional

    private Date comercialRegistryDate; // F_08 - optional

    @Size(max = 200, message = "Tax identity number must not exceed 200 characters")
    private String taxeIdentityNumber; // F_09 - optional

    @Size(max = 200, message = "Stat identity number must not exceed 200 characters")
    private String statIdentityNumber; // F_10 - optional

    @Size(max = 200, message = "Bank must not exceed 200 characters")
    private String bank; // F_11 - optional

    @Size(max = 50, message = "Bank account must not exceed 50 characters")
    private String bankAccount; // F_12 - optional

    @Size(max = 50, message = "SWIFT number must not exceed 50 characters")
    private String swiftNumber; // F_13 - optional

    @Size(max = 200, message = "Phone numbers must not exceed 200 characters")
    private String phoneNumbers; // F_14 - optional

    @Size(max = 200, message = "Fax numbers must not exceed 200 characters")
    private String faxNumbers; // F_15 - optional

    @Size(max = 300, message = "Mail must not exceed 300 characters")
    @Email(message = "Email should be valid")
    private String mail; // F_16 - optional

    @Size(max = 200, message = "Website must not exceed 200 characters")
    private String website; // F_17 - optional

    private Long logoId; // F_18 - File foreign key (optional)

    @NotNull(message = "Economic nature is required")
    private Long economicNatureId; // F_19 - EconomicNature foreign key (required)

    @NotNull(message = "Country is required")
    private Long countryId; // F_20 - Country foreign key (required)

    private Long stateId; // F_21 - State foreign key (optional)

    // Many-to-Many relationship
    private List<Long> economicDomainIds; // EconomicDomain many-to-many

    // One-to-Many relationships (for reference)
    private List<Long> providerExclusionIds; // ProviderExclusion one-to-many
    private List<Long> providerRepresentatorIds; // ProviderRepresentator one-to-many
    private List<Long> clearanceIds; // Clearance one-to-many
    private List<Long> submissionIds; // Submission one-to-many

    // Related entity DTOs for display (populated when needed)
    private dz.mdn.iaas.system.utility.dto.FileDTO logo;
    private EconomicNatureDTO economicNature;
    private dz.mdn.iaas.common.administration.dto.CountryDTO country;
    private dz.mdn.iaas.common.administration.dto.StateDTO state;
    private List<EconomicDomainDTO> economicDomains;

    /**
     * Create DTO from entity
     */
    public static ProviderDTO fromEntity(dz.mdn.iaas.business.provider.model.Provider provider) {
        if (provider == null) return null;
        
        ProviderDTO.ProviderDTOBuilder builder = ProviderDTO.builder()
                .id(provider.getId())
                .designationLt(provider.getDesignationLt())
                .designationAr(provider.getDesignationAr())
                .acronymLt(provider.getAcronymLt())
                .acronymAr(provider.getAcronymAr())
                .address(provider.getAddress())
                .capital(provider.getCapital())
                .comercialRegistryNumber(provider.getComercialRegistryNumber())
                .comercialRegistryDate(provider.getComercialRegistryDate())
                .taxeIdentityNumber(provider.getTaxeIdentityNumber())
                .statIdentityNumber(provider.getStatIdentityNumber())
                .bank(provider.getBank())
                .bankAccount(provider.getBankAccount())
                .swiftNumber(provider.getSwiftNumber())
                .phoneNumbers(provider.getPhoneNumbers())
                .faxNumbers(provider.getFaxNumbers())
                .mail(provider.getMail())
                .website(provider.getWebsite());

        // Handle foreign key relationships
        if (provider.getLogo() != null) {
            builder.logoId(provider.getLogo().getId());
        }
        if (provider.getEconomicNature() != null) {
            builder.economicNatureId(provider.getEconomicNature().getId());
        }
        if (provider.getCountry() != null) {
            builder.countryId(provider.getCountry().getId());
        }
        if (provider.getState() != null) {
            builder.stateId(provider.getState().getId());
        }

        // Handle many-to-many relationships
        if (provider.getEconomicDomains() != null) {
            builder.economicDomainIds(provider.getEconomicDomains().stream()
                    .map(domain -> domain.getId()).toList());
        }

        // Handle one-to-many relationships (IDs only)
        if (provider.getProviderExclusions() != null) {
            builder.providerExclusionIds(provider.getProviderExclusions().stream()
                    .map(exclusion -> exclusion.getId()).toList());
        }
        if (provider.getProviderRepresentators() != null) {
            builder.providerRepresentatorIds(provider.getProviderRepresentators().stream()
                    .map(rep -> rep.getId()).toList());
        }
        if (provider.getClearances() != null) {
            builder.clearanceIds(provider.getClearances().stream()
                    .map(clearance -> clearance.getId()).toList());
        }
        if (provider.getSubmissions() != null) {
            builder.submissionIds(provider.getSubmissions().stream()
                    .map(submission -> submission.getId()).toList());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static ProviderDTO fromEntityWithRelations(dz.mdn.iaas.business.provider.model.Provider provider) {
        ProviderDTO dto = fromEntity(provider);
        if (dto == null) return null;

        // Populate related DTOs
        if (provider.getLogo() != null) {
            dto.setLogo(dz.mdn.iaas.system.utility.dto.FileDTO.fromEntity(provider.getLogo()));
        }
        if (provider.getEconomicNature() != null) {
            dto.setEconomicNature(EconomicNatureDTO.fromEntity(provider.getEconomicNature()));
        }
        if (provider.getCountry() != null) {
            dto.setCountry(dz.mdn.iaas.common.administration.dto.CountryDTO.fromEntity(provider.getCountry()));
        }
        if (provider.getState() != null) {
            dto.setState(dz.mdn.iaas.common.administration.dto.StateDTO.fromEntity(provider.getState()));
        }
        if (provider.getEconomicDomains() != null) {
            dto.setEconomicDomains(provider.getEconomicDomains().stream()
                    .map(EconomicDomainDTO::fromEntity).toList());
        }

        return dto;
    }

    /**
     * Get default designation (Latin first, then Arabic)
     */
    public String getDefaultDesignation() {
        if (designationLt != null && !designationLt.trim().isEmpty()) {
            return designationLt;
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            return designationAr;
        }
        return "N/A";
    }

    /**
     * Get default acronym (Latin first, then Arabic)
     */
    public String getDefaultAcronym() {
        if (acronymLt != null && !acronymLt.trim().isEmpty()) {
            return acronymLt;
        }
        if (acronymAr != null && !acronymAr.trim().isEmpty()) {
            return acronymAr;
        }
        return "N/A";
    }

    /**
     * Get display text with priority: Latin designation > Arabic designation
     */
    public String getDisplayText() {
        if (designationLt != null && !designationLt.trim().isEmpty()) {
            return designationLt;
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            return designationAr;
        }
        return "N/A";
    }

    /**
     * Check if provider has multiple language support
     */
    public boolean isMultilingual() {
        boolean hasLatin = designationLt != null && !designationLt.trim().isEmpty();
        boolean hasArabic = designationAr != null && !designationAr.trim().isEmpty();
        return hasLatin && hasArabic;
    }

    /**
     * Get available languages for this provider
     */
    public String[] getAvailableLanguages() {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (designationLt != null && !designationLt.trim().isEmpty()) {
            languages.add("latin");
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            languages.add("arabic");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    /**
     * Check if provider has complete registration information
     */
    public boolean hasCompleteRegistration() {
        return comercialRegistryNumber != null && !comercialRegistryNumber.trim().isEmpty() &&
               comercialRegistryDate != null &&
               taxeIdentityNumber != null && !taxeIdentityNumber.trim().isEmpty();
    }

    /**
     * Check if provider has banking information
     */
    public boolean hasBankingInfo() {
        return bank != null && !bank.trim().isEmpty() &&
               bankAccount != null && !bankAccount.trim().isEmpty();
    }

    /**
     * Check if provider has contact information
     */
    public boolean hasContactInfo() {
        return (phoneNumbers != null && !phoneNumbers.trim().isEmpty()) ||
               (mail != null && !mail.trim().isEmpty()) ||
               (website != null && !website.trim().isEmpty());
    }

    /**
     * Get provider status based on available information
     */
    public String getProviderStatus() {
        if (hasCompleteRegistration() && hasBankingInfo() && hasContactInfo()) {
            return "COMPLETE";
        }
        if (hasCompleteRegistration()) {
            return "REGISTERED";
        }
        if (designationLt != null || designationAr != null) {
            return "DRAFT";
        }
        return "INCOMPLETE";
    }

    /**
     * Get business size category based on capital
     */
    public String getBusinessSizeCategory() {
        if (capital == null || capital <= 0) {
            return "UNSPECIFIED";
        }
        
        // Algerian business size categories (in DZD)
        if (capital >= 1000000000) { // 1 billion DZD
            return "LARGE_ENTERPRISE";
        } else if (capital >= 100000000) { // 100 million DZD
            return "MEDIUM_ENTERPRISE";
        } else if (capital >= 10000000) { // 10 million DZD
            return "SMALL_ENTERPRISE";
        } else {
            return "MICRO_ENTERPRISE";
        }
    }

    /**
     * Get provider type based on economic nature
     */
    public String getProviderType() {
        if (economicNature != null) {
            String natureType = economicNature.getNatureType();
            return switch (natureType) {
                case "PUBLIC_SECTOR", "PUBLIC_ESTABLISHMENT" -> "PUBLIC_PROVIDER";
                case "PRIVATE_SECTOR", "LIMITED_LIABILITY_COMPANY", "JOINT_STOCK_COMPANY" -> "PRIVATE_PROVIDER";
                case "MIXED_ECONOMY" -> "MIXED_PROVIDER";
                case "COOPERATIVE" -> "COOPERATIVE_PROVIDER";
                case "INDIVIDUAL_ENTERPRISE" -> "INDIVIDUAL_PROVIDER";
                case "FOREIGN_ENTITY" -> "FOREIGN_PROVIDER";
                case "NON_PROFIT" -> "NON_PROFIT_PROVIDER";
                default -> "OTHER_PROVIDER";
            };
        }
        return "UNKNOWN_PROVIDER";
    }

    /**
     * Check if provider is international (foreign or has international banking)
     */
    public boolean isInternational() {
        if (economicNature != null && "FOREIGN_ENTITY".equals(economicNature.getNatureType())) {
            return true;
        }
        return swiftNumber != null && !swiftNumber.trim().isEmpty();
    }

    /**
     * Get validation completeness percentage
     */
    public int getCompletenessPercentage() {
        int totalFields = 21; // Total number of main fields
        int completedFields = 0;
        
        if (designationLt != null && !designationLt.trim().isEmpty()) completedFields++;
        if (designationAr != null && !designationAr.trim().isEmpty()) completedFields++;
        if (acronymLt != null && !acronymLt.trim().isEmpty()) completedFields++;
        if (acronymAr != null && !acronymAr.trim().isEmpty()) completedFields++;
        if (address != null && !address.trim().isEmpty()) completedFields++;
        if (capital != null && capital > 0) completedFields++;
        if (comercialRegistryNumber != null && !comercialRegistryNumber.trim().isEmpty()) completedFields++;
        if (comercialRegistryDate != null) completedFields++;
        if (taxeIdentityNumber != null && !taxeIdentityNumber.trim().isEmpty()) completedFields++;
        if (statIdentityNumber != null && !statIdentityNumber.trim().isEmpty()) completedFields++;
        if (bank != null && !bank.trim().isEmpty()) completedFields++;
        if (bankAccount != null && !bankAccount.trim().isEmpty()) completedFields++;
        if (swiftNumber != null && !swiftNumber.trim().isEmpty()) completedFields++;
        if (phoneNumbers != null && !phoneNumbers.trim().isEmpty()) completedFields++;
        if (faxNumbers != null && !faxNumbers.trim().isEmpty()) completedFields++;
        if (mail != null && !mail.trim().isEmpty()) completedFields++;
        if (website != null && !website.trim().isEmpty()) completedFields++;
        if (logoId != null) completedFields++;
        if (economicNatureId != null) completedFields++;
        if (countryId != null) completedFields++;
        if (stateId != null) completedFields++;
        
        return (completedFields * 100) / totalFields;
    }

    /**
     * Get short display for lists (acronym - designation)
     */
    public String getShortDisplay() {
        String acronym = getDefaultAcronym();
        String designation = getDisplayText();
        return !"N/A".equals(acronym) ? acronym + " - " + designation : designation;
    }

    /**
     * Get full display with all available information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (designationLt != null && !designationLt.trim().isEmpty()) {
            sb.append(designationLt);
        }
        
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" / ");
            sb.append(designationAr);
        }
        
        String acronym = getDefaultAcronym();
        if (!"N/A".equals(acronym)) {
            sb.append(" (").append(acronym).append(")");
        }
        
        if (economicNature != null) {
            sb.append(" - ").append(economicNature.getDisplayText());
        }
        
        return sb.toString();
    }

    /**
     * Get business display with economic information
     */
    public String getBusinessDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        if (economicNature != null) {
            sb.append(" [").append(economicNature.getDisplayAcronym()).append("]");
        }
        
        String sizeCategory = getBusinessSizeCategory();
        if (!"UNSPECIFIED".equals(sizeCategory)) {
            sb.append(" (").append(sizeCategory.replace("_", " ")).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get contact summary
     */
    public String getContactSummary() {
        StringBuilder sb = new StringBuilder();
        
        if (phoneNumbers != null && !phoneNumbers.trim().isEmpty()) {
            sb.append("Tel: ").append(phoneNumbers);
        }
        
        if (mail != null && !mail.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Email: ").append(mail);
        }
        
        if (website != null && !website.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Web: ").append(website);
        }
        
        return sb.length() > 0 ? sb.toString() : "No contact information";
    }

    /**
     * Get banking summary
     */
    public String getBankingSummary() {
        StringBuilder sb = new StringBuilder();
        
        if (bank != null && !bank.trim().isEmpty()) {
            sb.append("Bank: ").append(bank);
        }
        
        if (bankAccount != null && !bankAccount.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Account: ").append(bankAccount);
        }
        
        if (swiftNumber != null && !swiftNumber.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("SWIFT: ").append(swiftNumber);
        }
        
        return sb.length() > 0 ? sb.toString() : "No banking information";
    }

    /**
     * Get registration summary
     */
    public String getRegistrationSummary() {
        StringBuilder sb = new StringBuilder();
        
        if (comercialRegistryNumber != null && !comercialRegistryNumber.trim().isEmpty()) {
            sb.append("RC: ").append(comercialRegistryNumber);
        }
        
        if (taxeIdentityNumber != null && !taxeIdentityNumber.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Tax ID: ").append(taxeIdentityNumber);
        }
        
        if (statIdentityNumber != null && !statIdentityNumber.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Stat ID: ").append(statIdentityNumber);
        }
        
        return sb.length() > 0 ? sb.toString() : "No registration information";
    }

    /**
     * Check if provider has exclusions
     */
    public boolean hasExclusions() {
        return providerExclusionIds != null && !providerExclusionIds.isEmpty();
    }

    /**
     * Check if provider has representatives
     */
    public boolean hasRepresentatives() {
        return providerRepresentatorIds != null && !providerRepresentatorIds.isEmpty();
    }

    /**
     * Check if provider has clearances
     */
    public boolean hasClearances() {
        return clearanceIds != null && !clearanceIds.isEmpty();
    }

    /**
     * Check if provider has submissions
     */
    public boolean hasSubmissions() {
        return submissionIds != null && !submissionIds.isEmpty();
    }

    /**
     * Get activity summary based on economic domains
     */
    public String getActivitySummary() {
        if (economicDomains == null || economicDomains.isEmpty()) {
            return "No activity domains specified";
        }
        
        if (economicDomains.size() == 1) {
            return economicDomains.get(0).getDisplayText();
        }
        
        return economicDomains.size() + " activity domains";
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ProviderDTO createSimple(Long id, String designationLt, String designationAr) {
        return ProviderDTO.builder()
                .id(id)
                .designationLt(designationLt)
                .designationAr(designationAr)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return economicNatureId != null && countryId != null && 
               (designationLt != null && !designationLt.trim().isEmpty() || 
                designationAr != null && !designationAr.trim().isEmpty());
    }

    /**
     * Get comparison key for sorting (by designation, then by ID)
     */
    public String getComparisonKey() {
        String designation = getDisplayText();
        return designation.toLowerCase() + "_" + (id != null ? id : 0);
    }

    /**
     * Get formal provider display for contracts
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        if (economicNature != null) {
            sb.append(" (").append(economicNature.getDisplayAcronym()).append(")");
        }
        
        if (comercialRegistryNumber != null && !comercialRegistryNumber.trim().isEmpty()) {
            sb.append(" - RC: ").append(comercialRegistryNumber);
        }
        
        return sb.toString();
    }
}
