/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentatorDTO
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
 * Provider Representator Data Transfer Object
 * Maps exactly to ProviderRepresentator model fields: F_00=id, F_01=firstname, F_02=lastname, F_03=birthDate, 
 * F_04=birthPlace, F_05=address, F_06=jobTitle, F_07=mobilePhoneNumber, F_08=fixPhoneNumber, F_09=mail, F_10=providerId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderRepresentatorDTO {

    private Long id; // F_00

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstname; // F_01 - required

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastname; // F_02 - required

    @Size(max = 200, message = "Birth date must not exceed 200 characters")
    private String birthDate; // F_03 - optional

    @Size(max = 100, message = "Birth place must not exceed 100 characters")
    private String birthPlace; // F_04 - optional

    @Size(max = 100, message = "Address must not exceed 100 characters")
    private String address; // F_05 - optional

    @Size(max = 50, message = "Job title must not exceed 50 characters")
    private String jobTitle; // F_06 - optional

    @Size(max = 100, message = "Mobile phone number must not exceed 100 characters")
    private String mobilePhoneNumber; // F_07 - optional

    @Size(max = 100, message = "Fix phone number must not exceed 100 characters")
    private String fixPhoneNumber; // F_08 - optional

    @Size(max = 100, message = "Mail must not exceed 100 characters")
    @Email(message = "Email should be valid")
    private String mail; // F_09 - optional

    @NotNull(message = "Provider is required")
    private Long providerId; // F_10 - Provider foreign key (required)

    // Related entity DTO for display (populated when needed)
    private ProviderDTO provider;

    /**
     * Create DTO from entity
     */
    public static ProviderRepresentatorDTO fromEntity(dz.mdn.iaas.business.provider.model.ProviderRepresentator providerRepresentator) {
        if (providerRepresentator == null) return null;
        
        ProviderRepresentatorDTO.ProviderRepresentatorDTOBuilder builder = ProviderRepresentatorDTO.builder()
                .id(providerRepresentator.getId())
                .firstname(providerRepresentator.getFirstname())
                .lastname(providerRepresentator.getLastname())
                .birthDate(providerRepresentator.getBirthDate())
                .birthPlace(providerRepresentator.getBirthPlace())
                .address(providerRepresentator.getAddress())
                .jobTitle(providerRepresentator.getJobTitle())
                .mobilePhoneNumber(providerRepresentator.getMobilePhoneNumber())
                .fixPhoneNumber(providerRepresentator.getFixPhoneNumber())
                .mail(providerRepresentator.getMail());

        // Handle foreign key relationship
        if (providerRepresentator.getProvider() != null) {
            builder.providerId(providerRepresentator.getProvider().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static ProviderRepresentatorDTO fromEntityWithRelations(dz.mdn.iaas.business.provider.model.ProviderRepresentator providerRepresentator) {
        ProviderRepresentatorDTO dto = fromEntity(providerRepresentator);
        if (dto == null) return null;

        // Populate related DTOs
        if (providerRepresentator.getProvider() != null) {
            dto.setProvider(ProviderDTO.fromEntity(providerRepresentator.getProvider()));
        }

        return dto;
    }

    /**
     * Get full name
     */
    public String getFullName() {
        if (firstname != null && lastname != null) {
            return firstname + " " + lastname;
        }
        if (firstname != null) {
            return firstname;
        }
        if (lastname != null) {
            return lastname;
        }
        return "N/A";
    }

    /**
     * Get display name (Last name, First name)
     */
    public String getDisplayName() {
        if (lastname != null && firstname != null) {
            return lastname + ", " + firstname;
        }
        return getFullName();
    }

    /**
     * Get initials
     */
    public String getInitials() {
        StringBuilder initials = new StringBuilder();
        if (firstname != null && !firstname.trim().isEmpty()) {
            initials.append(firstname.charAt(0));
        }
        if (lastname != null && !lastname.trim().isEmpty()) {
            initials.append(lastname.charAt(0));
        }
        return initials.toString().toUpperCase();
    }

    /**
     * Check if representator has contact information
     */
    public boolean hasContactInfo() {
        return (mobilePhoneNumber != null && !mobilePhoneNumber.trim().isEmpty()) ||
               (fixPhoneNumber != null && !fixPhoneNumber.trim().isEmpty()) ||
               (mail != null && !mail.trim().isEmpty());
    }

    /**
     * Check if representator has complete personal information
     */
    public boolean hasCompletePersonalInfo() {
        return firstname != null && !firstname.trim().isEmpty() &&
               lastname != null && !lastname.trim().isEmpty() &&
               birthDate != null && !birthDate.trim().isEmpty() &&
               birthPlace != null && !birthPlace.trim().isEmpty();
    }

    /**
     * Check if representator has address information
     */
    public boolean hasAddressInfo() {
        return address != null && !address.trim().isEmpty();
    }

    /**
     * Check if representator has professional information
     */
    public boolean hasProfessionalInfo() {
        return jobTitle != null && !jobTitle.trim().isEmpty();
    }

    /**
     * Get representator status based on available information
     */
    public String getRepresentatorStatus() {
        if (hasCompletePersonalInfo() && hasContactInfo() && hasProfessionalInfo()) {
            return "COMPLETE";
        }
        if (firstname != null && lastname != null && hasContactInfo()) {
            return "BASIC_COMPLETE";
        }
        if (firstname != null && lastname != null) {
            return "MINIMAL";
        }
        return "INCOMPLETE";
    }

    /**
     * Get contact summary
     */
    public String getContactSummary() {
        StringBuilder sb = new StringBuilder();
        
        if (mobilePhoneNumber != null && !mobilePhoneNumber.trim().isEmpty()) {
            sb.append("Mobile: ").append(mobilePhoneNumber);
        }
        
        if (fixPhoneNumber != null && !fixPhoneNumber.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Fix: ").append(fixPhoneNumber);
        }
        
        if (mail != null && !mail.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append("Email: ").append(mail);
        }
        
        return sb.length() > 0 ? sb.toString() : "No contact information";
    }

    /**
     * Get personal information summary
     */
    public String getPersonalSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullName());
        
        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            sb.append(" - ").append(jobTitle);
        }
        
        if (birthDate != null && !birthDate.trim().isEmpty()) {
            sb.append(" (Born: ").append(birthDate);
            if (birthPlace != null && !birthPlace.trim().isEmpty()) {
                sb.append(" in ").append(birthPlace);
            }
            sb.append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get validation completeness percentage
     */
    public int getCompletenessPercentage() {
        int totalFields = 9; // Total number of optional fields (excluding id and providerId)
        int completedFields = 0;
        
        if (firstname != null && !firstname.trim().isEmpty()) completedFields++;
        if (lastname != null && !lastname.trim().isEmpty()) completedFields++;
        if (birthDate != null && !birthDate.trim().isEmpty()) completedFields++;
        if (birthPlace != null && !birthPlace.trim().isEmpty()) completedFields++;
        if (address != null && !address.trim().isEmpty()) completedFields++;
        if (jobTitle != null && !jobTitle.trim().isEmpty()) completedFields++;
        if (mobilePhoneNumber != null && !mobilePhoneNumber.trim().isEmpty()) completedFields++;
        if (fixPhoneNumber != null && !fixPhoneNumber.trim().isEmpty()) completedFields++;
        if (mail != null && !mail.trim().isEmpty()) completedFields++;
        
        return (completedFields * 100) / totalFields;
    }

    /**
     * Get primary contact method
     */
    public String getPrimaryContact() {
        if (mail != null && !mail.trim().isEmpty()) {
            return "Email: " + mail;
        }
        if (mobilePhoneNumber != null && !mobilePhoneNumber.trim().isEmpty()) {
            return "Mobile: " + mobilePhoneNumber;
        }
        if (fixPhoneNumber != null && !fixPhoneNumber.trim().isEmpty()) {
            return "Phone: " + fixPhoneNumber;
        }
        return "No contact available";
    }

    /**
     * Get representator type based on job title
     */
    public String getRepresentatorType() {
        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            return "UNSPECIFIED";
        }
        
        String title = jobTitle.toLowerCase();
        
        // Executive level
        if (title.contains("directeur") || title.contains("director") || 
            title.contains("président") || title.contains("president") ||
            title.contains("gérant") || title.contains("manager") ||
            title.contains("pdg") || title.contains("ceo")) {
            return "EXECUTIVE";
        }
        
        // Legal representative
        if (title.contains("représentant") || title.contains("representative") ||
            title.contains("mandataire") || title.contains("agent") ||
            title.contains("délégué") || title.contains("delegate")) {
            return "LEGAL_REPRESENTATIVE";
        }
        
        // Technical representative
        if (title.contains("technique") || title.contains("technical") ||
            title.contains("ingénieur") || title.contains("engineer") ||
            title.contains("chef de projet") || title.contains("project manager")) {
            return "TECHNICAL_REPRESENTATIVE";
        }
        
        // Commercial representative
        if (title.contains("commercial") || title.contains("sales") ||
            title.contains("ventes") || title.contains("marketing") ||
            title.contains("client") || title.contains("customer")) {
            return "COMMERCIAL_REPRESENTATIVE";
        }
        
        // Administrative representative
        if (title.contains("administratif") || title.contains("administrative") ||
            title.contains("secrétaire") || title.contains("secretary") ||
            title.contains("assistant") || title.contains("coordinateur")) {
            return "ADMINISTRATIVE_REPRESENTATIVE";
        }
        
        // Financial representative
        if (title.contains("financier") || title.contains("financial") ||
            title.contains("comptable") || title.contains("accountant") ||
            title.contains("trésorier") || title.contains("treasurer")) {
            return "FINANCIAL_REPRESENTATIVE";
        }
        
        return "OTHER";
    }

    /**
     * Get authority level based on representator type
     */
    public String getAuthorityLevel() {
        String type = getRepresentatorType();
        
        return switch (type) {
            case "EXECUTIVE" -> "HIGH_AUTHORITY";
            case "LEGAL_REPRESENTATIVE" -> "LEGAL_AUTHORITY";
            case "TECHNICAL_REPRESENTATIVE" -> "TECHNICAL_AUTHORITY";
            case "COMMERCIAL_REPRESENTATIVE" -> "COMMERCIAL_AUTHORITY";
            case "FINANCIAL_REPRESENTATIVE" -> "FINANCIAL_AUTHORITY";
            case "ADMINISTRATIVE_REPRESENTATIVE" -> "ADMINISTRATIVE_AUTHORITY";
            default -> "GENERAL_AUTHORITY";
        };
    }

    /**
     * Check if representator can sign contracts
     */
    public boolean canSignContracts() {
        String type = getRepresentatorType();
        return "EXECUTIVE".equals(type) || "LEGAL_REPRESENTATIVE".equals(type);
    }

    /**
     * Check if representator can handle technical matters
     */
    public boolean canHandleTechnicalMatters() {
        String type = getRepresentatorType();
        return "EXECUTIVE".equals(type) || "TECHNICAL_REPRESENTATIVE".equals(type);
    }

    /**
     * Check if representator can handle commercial matters
     */
    public boolean canHandleCommercialMatters() {
        String type = getRepresentatorType();
        return "EXECUTIVE".equals(type) || "COMMERCIAL_REPRESENTATIVE".equals(type) || 
               "LEGAL_REPRESENTATIVE".equals(type);
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullName());
        
        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            sb.append(" (").append(jobTitle).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get full display with all information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPersonalSummary());
        
        if (hasContactInfo()) {
            sb.append("\nContact: ").append(getContactSummary());
        }
        
        if (address != null && !address.trim().isEmpty()) {
            sb.append("\nAddress: ").append(address);
        }
        
        return sb.toString();
    }

    /**
     * Get business card display
     */
    public String getBusinessCardDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullName());
        
        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            sb.append("\n").append(jobTitle);
        }
        
        if (provider != null) {
            sb.append("\n").append(provider.getDisplayText());
        }
        
        sb.append("\n").append(getPrimaryContact());
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ProviderRepresentatorDTO createSimple(Long id, String firstname, String lastname, String jobTitle) {
        return ProviderRepresentatorDTO.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .jobTitle(jobTitle)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return firstname != null && !firstname.trim().isEmpty() && 
               lastname != null && !lastname.trim().isEmpty() &&
               providerId != null;
    }

    /**
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (firstname == null || firstname.trim().isEmpty()) {
            errors.add("First name is required");
        }
        
        if (lastname == null || lastname.trim().isEmpty()) {
            errors.add("Last name is required");
        }
        
        if (providerId == null) {
            errors.add("Provider is required");
        }
        
        if (mail != null && !mail.trim().isEmpty()) {
            // Basic email validation
            if (!mail.contains("@") || !mail.contains(".")) {
                errors.add("Invalid email format");
            }
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by last name, then first name)
     */
    public String getComparisonKey() {
        String lastNameKey = lastname != null ? lastname.toLowerCase() : "zzz";
        String firstNameKey = firstname != null ? firstname.toLowerCase() : "zzz";
        return lastNameKey + "_" + firstNameKey;
    }

    /**
     * Get formal display for contracts and official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (lastname != null) {
            sb.append(lastname.toUpperCase());
        }
        
        if (firstname != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(firstname);
        }
        
        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            sb.append(" - ").append(jobTitle);
        }
        
        if (provider != null) {
            sb.append(" (").append(provider.getDisplayText()).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get communication preference based on available contact methods
     */
    public String getCommunicationPreference() {
        if (mail != null && !mail.trim().isEmpty()) {
            return "EMAIL";
        }
        if (mobilePhoneNumber != null && !mobilePhoneNumber.trim().isEmpty()) {
            return "MOBILE_PHONE";
        }
        if (fixPhoneNumber != null && !fixPhoneNumber.trim().isEmpty()) {
            return "FIXED_PHONE";
        }
        return "NO_PREFERENCE";
    }

    /**
     * Get professional summary for reports
     */
    public String getProfessionalSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Representative: ").append(getFullName());
        
        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            sb.append("\nPosition: ").append(jobTitle);
        }
        
        sb.append("\nType: ").append(getRepresentatorType().replace("_", " "));
        sb.append("\nAuthority: ").append(getAuthorityLevel().replace("_", " "));
        sb.append("\nStatus: ").append(getRepresentatorStatus());
        sb.append("\nContact: ").append(getPrimaryContact());
        
        return sb.toString();
    }

    /**
     * Get responsibility scope based on representator type
     */
    public String getResponsibilityScope() {
        String type = getRepresentatorType();
        
        return switch (type) {
            case "EXECUTIVE" -> "Overall management and strategic decisions";
            case "LEGAL_REPRESENTATIVE" -> "Legal matters and contract signing";
            case "TECHNICAL_REPRESENTATIVE" -> "Technical specifications and project management";
            case "COMMERCIAL_REPRESENTATIVE" -> "Sales, marketing, and client relations";
            case "FINANCIAL_REPRESENTATIVE" -> "Financial management and accounting";
            case "ADMINISTRATIVE_REPRESENTATIVE" -> "Administrative tasks and coordination";
            default -> "General representation duties";
        };
    }

    /**
     * Check if representator has emergency contact
     */
    public boolean hasEmergencyContact() {
        return mobilePhoneNumber != null && !mobilePhoneNumber.trim().isEmpty();
    }

    /**
     * Get contact priority order
     */
    public String[] getContactPriorityOrder() {
        java.util.List<String> contacts = new java.util.ArrayList<>();
        
        if (mail != null && !mail.trim().isEmpty()) {
            contacts.add("Email: " + mail);
        }
        if (mobilePhoneNumber != null && !mobilePhoneNumber.trim().isEmpty()) {
            contacts.add("Mobile: " + mobilePhoneNumber);
        }
        if (fixPhoneNumber != null && !fixPhoneNumber.trim().isEmpty()) {
            contacts.add("Phone: " + fixPhoneNumber);
        }
        
        return contacts.stream().toArray(String[]::new);
    }

    /**
     * Get age estimate (if birth date contains year)
     */
    public String getAgeEstimate() {
        if (birthDate == null || birthDate.trim().isEmpty()) {
            return "Age not available";
        }
        
        // Try to extract year from birthDate string
        try {
            if (birthDate.contains("/")) {
                String[] parts = birthDate.split("/");
                if (parts.length >= 3) {
                    int year = Integer.parseInt(parts[2]);
                    int currentYear = java.time.Year.now().getValue();
                    int age = currentYear - year;
                    return "Approximately " + age + " years old";
                }
            }
            if (birthDate.contains("-")) {
                String[] parts = birthDate.split("-");
                if (parts.length >= 3) {
                    int year = Integer.parseInt(parts[2]);
                    int currentYear = java.time.Year.now().getValue();
                    int age = currentYear - year;
                    return "Approximately " + age + " years old";
                }
            }
        } catch (NumberFormatException e) {
            // Ignore parsing errors
        }
        
        return "Age calculation not possible";
    }
}
