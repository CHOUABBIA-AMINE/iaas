/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PersonDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.administration.model.Person;
import dz.mdn.iaas.system.utility.dto.FileDTO;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person Data Transfer Object
 * Maps exactly to Person model fields: F_00=id, F_01=firstnameAr, F_02=lastnameAr, F_03=firstnameLt, F_04=lastnameLt, F_05=birthDate, F_06=birthPlace, F_07=address, F_08=birthState, F_09=addressState, F_10=picture
 * All fields are optional except the basic structure
 * Supports multilingual names (Arabic and Latin scripts)
 * F_08 (birthState), F_09 (addressState), F_10 (picture) are optional foreign keys
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private Long id; // F_00

    @Size(max = 100, message = "Arabic firstname must not exceed 100 characters")
    private String firstnameAr; // F_01 - optional

    @Size(max = 100, message = "Arabic lastname must not exceed 100 characters")
    private String lastnameAr; // F_02 - optional

    @Size(max = 100, message = "Latin firstname must not exceed 100 characters")
    private String firstnameLt; // F_03 - optional

    @Size(max = 100, message = "Latin lastname must not exceed 100 characters")
    private String lastnameLt; // F_04 - optional

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate; // F_05 - optional

    private String birthPlace; // F_06 - optional

    private String address; // F_07 - optional

    private Long birthStateId; // F_08 - optional foreign key

    private Long addressStateId; // F_09 - optional foreign key

    private Long pictureId; // F_10 - optional foreign key

    // Nested information for display purposes - using proper DTO classes
    private StateDTO birthState;
    private StateDTO addressState;
    private FileDTO picture;

    /**
     * Create DTO from entity
     */
    public static PersonDTO fromEntity(Person person) {
        if (person == null) return null;
        
        StateDTO birthStateDTO = null;
        if (person.getBirthState() != null) {
            birthStateDTO = StateDTO.fromEntity(person.getBirthState());
        }
        
        StateDTO addressStateDTO = null;
        if (person.getAddressState() != null) {
            addressStateDTO = StateDTO.fromEntity(person.getAddressState());
        }
        
        FileDTO pictureDTO = null;
        if (person.getPicture() != null) {
            pictureDTO = FileDTO.fromEntity(person.getPicture());
        }
        
        return PersonDTO.builder()
                .id(person.getId())
                .firstnameAr(person.getFirstnameAr())
                .lastnameAr(person.getLastnameAr())
                .firstnameLt(person.getFirstnameLt())
                .lastnameLt(person.getLastnameLt())
                .birthDate(person.getBirthDate())
                .birthPlace(person.getBirthPlace())
                .address(person.getAddress())
                .birthStateId(person.getBirthState() != null ? person.getBirthState().getId() : null)
                .addressStateId(person.getAddressState() != null ? person.getAddressState().getId() : null)
                .pictureId(person.getPicture() != null ? person.getPicture().getId() : null)
                .birthState(birthStateDTO)
                .addressState(addressStateDTO)
                .picture(pictureDTO)
                .build();
    }

    /**
     * Convert to entity (without setting foreign key relationships - use service for that)
     */
    public Person toEntity() {
        Person person = new Person();
        person.setId(this.id);
        person.setFirstnameAr(this.firstnameAr);
        person.setLastnameAr(this.lastnameAr);
        person.setFirstnameLt(this.firstnameLt);
        person.setLastnameLt(this.lastnameLt);
        person.setBirthDate(this.birthDate);
        person.setBirthPlace(this.birthPlace);
        person.setAddress(this.address);
        // Note: birthState, addressState, and picture should be set by the service layer
        return person;
    }

    /**
     * Update entity from DTO (without updating foreign key relationships - use service for that)
     */
    public void updateEntity(Person person) {
        if (this.firstnameAr != null) {
            person.setFirstnameAr(this.firstnameAr);
        }
        if (this.lastnameAr != null) {
            person.setLastnameAr(this.lastnameAr);
        }
        if (this.firstnameLt != null) {
            person.setFirstnameLt(this.firstnameLt);
        }
        if (this.lastnameLt != null) {
            person.setLastnameLt(this.lastnameLt);
        }
        if (this.birthDate != null) {
            person.setBirthDate(this.birthDate);
        }
        if (this.birthPlace != null) {
            person.setBirthPlace(this.birthPlace);
        }
        if (this.address != null) {
            person.setAddress(this.address);
        }
        // Note: birthState, addressState, and picture should be updated by the service layer
    }

    /**
     * Get full name in Arabic
     */
    public String getFullNameAr() {
        StringBuilder name = new StringBuilder();
        if (firstnameAr != null && !firstnameAr.trim().isEmpty()) {
            name.append(firstnameAr);
        }
        if (lastnameAr != null && !lastnameAr.trim().isEmpty()) {
            if (name.length() > 0) name.append(" ");
            name.append(lastnameAr);
        }
        return name.toString().trim();
    }

    /**
     * Get full name in Latin script
     */
    public String getFullNameLt() {
        StringBuilder name = new StringBuilder();
        if (firstnameLt != null && !firstnameLt.trim().isEmpty()) {
            name.append(firstnameLt);
        }
        if (lastnameLt != null && !lastnameLt.trim().isEmpty()) {
            if (name.length() > 0) name.append(" ");
            name.append(lastnameLt);
        }
        return name.toString().trim();
    }

    /**
     * Get default display name (Latin if available, otherwise Arabic)
     */
    public String getDisplayName() {
        String latinName = getFullNameLt();
        if (latinName != null && !latinName.trim().isEmpty()) {
            return latinName;
        }
        String arabicName = getFullNameAr();
        if (arabicName != null && !arabicName.trim().isEmpty()) {
            return arabicName;
        }
        return "N/A";
    }

    /**
     * Get name by language preference
     */
    public String getNameByLanguage(String language) {
        if (language == null) return getDisplayName();
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> {
                String arabicName = getFullNameAr();
                yield arabicName != null && !arabicName.trim().isEmpty() ? arabicName : getDisplayName();
            }
            case "lt", "latin", "en", "fr" -> {
                String latinName = getFullNameLt();
                yield latinName != null && !latinName.trim().isEmpty() ? latinName : getDisplayName();
            }
            default -> getDisplayName();
        };
    }

    /**
     * Check if person has multilingual name support
     */
    public boolean hasMultilingualName() {
        String arabicName = getFullNameAr();
        String latinName = getFullNameLt();
        return (arabicName != null && !arabicName.trim().isEmpty()) && 
               (latinName != null && !latinName.trim().isEmpty());
    }

    /**
     * Get available name languages
     */
    public String[] getAvailableNameLanguages() {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (getFullNameAr() != null && !getFullNameAr().trim().isEmpty()) {
            languages.add("arabic");
        }
        if (getFullNameLt() != null && !getFullNameLt().trim().isEmpty()) {
            languages.add("latin");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    /**
     * Calculate age from birth date
     */
    public Integer getAge() {
        if (birthDate == null) return null;
        
        java.util.Calendar birthCal = java.util.Calendar.getInstance();
        birthCal.setTime(birthDate);
        
        java.util.Calendar now = java.util.Calendar.getInstance();
        
        int age = now.get(java.util.Calendar.YEAR) - birthCal.get(java.util.Calendar.YEAR);
        
        if (now.get(java.util.Calendar.DAY_OF_YEAR) < birthCal.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }
        
        return age;
    }

    /**
     * Get birth year
     */
    public Integer getBirthYear() {
        if (birthDate == null) return null;
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(birthDate);
        return cal.get(java.util.Calendar.YEAR);
    }

    /**
     * Get age group classification
     */
    public String getAgeGroup() {
        Integer age = getAge();
        if (age == null) return "UNKNOWN";
        
        if (age < 18) return "MINOR";
        if (age < 25) return "YOUNG_ADULT";
        if (age < 35) return "ADULT";
        if (age < 50) return "MIDDLE_AGED";
        if (age < 65) return "SENIOR";
        return "ELDERLY";
    }

    /**
     * Get birth state designation if available
     */
    public String getBirthStateDesignation() {
        return birthState != null ? birthState.getDesignationLt() : null;
    }

    /**
     * Get address state designation if available
     */
    public String getAddressStateDesignation() {
        return addressState != null ? addressState.getDesignationLt() : null;
    }

    /**
     * Check if person has a picture
     */
    public boolean hasPicture() {
        return pictureId != null && picture != null;
    }

    /**
     * Get picture URL if available
     */
    public String getPictureUrl() {
        return picture != null ? picture.getDownloadUrl() : null;
    }

    /**
     * Check if birth and address states are the same
     */
    public boolean isSameState() {
        return birthStateId != null && addressStateId != null && 
               birthStateId.equals(addressStateId);
    }

    /**
     * Check if person has complete address information
     */
    public boolean hasCompleteAddress() {
        return address != null && !address.trim().isEmpty() && addressStateId != null;
    }

    /**
     * Check if person has complete birth information
     */
    public boolean hasCompleteBirthInfo() {
        return birthDate != null && birthPlace != null && !birthPlace.trim().isEmpty() && 
               birthStateId != null;
    }

    /**
     * Get generation based on birth year
     */
    public String getGeneration() {
        Integer birthYear = getBirthYear();
        if (birthYear == null) return "UNKNOWN";
        
        if (birthYear >= 1997) return "GEN_Z";
        if (birthYear >= 1981) return "MILLENNIAL";
        if (birthYear >= 1965) return "GEN_X";
        if (birthYear >= 1946) return "BABY_BOOMER";
        if (birthYear >= 1928) return "SILENT_GENERATION";
        return "GREATEST_GENERATION";
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static PersonDTO createSimple(Long id, String firstnameLt, String lastnameLt, String firstnameAr, String lastnameAr) {
        return PersonDTO.builder()
                .id(id)
                .firstnameLt(firstnameLt)
                .lastnameLt(lastnameLt)
                .firstnameAr(firstnameAr)
                .lastnameAr(lastnameAr)
                .build();
    }

    /**
     * Validate if person has at least one name
     */
    public boolean hasValidName() {
        return (firstnameAr != null && !firstnameAr.trim().isEmpty()) ||
               (lastnameAr != null && !lastnameAr.trim().isEmpty()) ||
               (firstnameLt != null && !firstnameLt.trim().isEmpty()) ||
               (lastnameLt != null && !lastnameLt.trim().isEmpty());
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        String name = getDisplayName();
        return name.length() > 30 ? name.substring(0, 30) + "..." : name;
    }

    /**
     * Get full display with all available information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        String latinName = getFullNameLt();
        String arabicName = getFullNameAr();
        
        if (latinName != null && !latinName.trim().isEmpty()) {
            sb.append(latinName);
            if (arabicName != null && !arabicName.trim().isEmpty() && !arabicName.equals(latinName)) {
                sb.append(" (").append(arabicName).append(")");
            }
        } else if (arabicName != null && !arabicName.trim().isEmpty()) {
            sb.append(arabicName);
        }
        
        Integer age = getAge();
        if (age != null) {
            sb.append(" - ").append(age).append(" ans");
        }
        
        if (addressState != null) {
            sb.append(" - ").append(addressState.getDesignationLt());
        }
        
        return sb.toString();
    }

    /**
     * Get comparison key for sorting (by latin name, then arabic name)
     */
    public String getComparisonKey() {
        String latinName = getFullNameLt();
        if (latinName != null && !latinName.trim().isEmpty()) {
            return latinName.toLowerCase();
        }
        String arabicName = getFullNameAr();
        return arabicName != null ? arabicName : "";
    }

    /**
     * Get display with birth information
     */
    public String getDisplayWithBirthInfo() {
        StringBuilder sb = new StringBuilder(getDisplayName());
        
        if (birthDate != null) {
            sb.append(" - né le ").append(new java.text.SimpleDateFormat("dd/MM/yyyy").format(birthDate));
        }
        
        if (birthPlace != null && !birthPlace.trim().isEmpty()) {
            sb.append(" à ").append(birthPlace);
        }
        
        if (birthState != null) {
            sb.append(" (").append(birthState.getDesignationLt()).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get display with address information
     */
    public String getDisplayWithAddress() {
        StringBuilder sb = new StringBuilder(getDisplayName());
        
        if (address != null && !address.trim().isEmpty()) {
            sb.append(" - ").append(address);
        }
        
        if (addressState != null) {
            sb.append(" (").append(addressState.getDesignationLt()).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get formal display name (lastname, firstname)
     */
    public String getFormalDisplayName() {
        String latinLastname = lastnameLt;
        String latinFirstname = firstnameLt;
        
        if (latinLastname != null && !latinLastname.trim().isEmpty() && 
            latinFirstname != null && !latinFirstname.trim().isEmpty()) {
            return latinLastname.toUpperCase() + ", " + latinFirstname;
        }
        
        String arabicLastname = lastnameAr;
        String arabicFirstname = firstnameAr;
        
        if (arabicLastname != null && !arabicLastname.trim().isEmpty() && 
            arabicFirstname != null && !arabicFirstname.trim().isEmpty()) {
            return arabicLastname + ", " + arabicFirstname;
        }
        
        return getDisplayName();
    }

    /**
     * Get initials
     */
    public String getInitials() {
        StringBuilder initials = new StringBuilder();
        
        if (firstnameLt != null && !firstnameLt.trim().isEmpty()) {
            initials.append(firstnameLt.charAt(0));
        }
        if (lastnameLt != null && !lastnameLt.trim().isEmpty()) {
            initials.append(lastnameLt.charAt(0));
        }
        
        if (initials.length() == 0) {
            if (firstnameAr != null && !firstnameAr.trim().isEmpty()) {
                initials.append(firstnameAr.charAt(0));
            }
            if (lastnameAr != null && !lastnameAr.trim().isEmpty()) {
                initials.append(lastnameAr.charAt(0));
            }
        }
        
        return initials.toString().toUpperCase();
    }

    /**
     * Check if person is adult (18+)
     */
    public boolean isAdult() {
        Integer age = getAge();
        return age != null && age >= 18;
    }

    /**
     * Check if person is minor (<18)
     */
    public boolean isMinor() {
        Integer age = getAge();
        return age != null && age < 18;
    }

    /**
     * Get profile completeness percentage
     */
    public double getProfileCompleteness() {
        int totalFields = 10; // Total possible fields
        int filledFields = 0;
        
        if (hasValidName()) filledFields++;
        if (birthDate != null) filledFields++;
        if (birthPlace != null && !birthPlace.trim().isEmpty()) filledFields++;
        if (address != null && !address.trim().isEmpty()) filledFields++;
        if (birthStateId != null) filledFields++;
        if (addressStateId != null) filledFields++;
        if (pictureId != null) filledFields++;
        if (hasMultilingualName()) filledFields += 3; // Bonus for multilingual support
        
        return (double) filledFields / totalFields * 100;
    }

    /**
     * Get profile status based on completeness
     */
    public String getProfileStatus() {
        double completeness = getProfileCompleteness();
        if (completeness >= 90) return "COMPLETE";
        if (completeness >= 70) return "GOOD";
        if (completeness >= 50) return "FAIR";
        if (completeness >= 30) return "BASIC";
        return "INCOMPLETE";
    }
}
