/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Employee Data Transfer Object
 * Maps exactly to Employee model fields: F_00=id, F_01=serial, F_02=hiringDate, 
 * F_03=personId, F_04=militaryRankId, F_05=jobId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

    private Long id; // F_00

    private String serial; // F_01 - optional

    private Date hiringDate; // F_02 - optional

    @NotNull(message = "Person is required")
    private Long personId; // F_03 - Person foreign key (required)

    @NotNull(message = "Military rank is required")
    private Long militaryRankId; // F_04 - MilitaryRank foreign key (required)

    private Long jobId; // F_05 - Job foreign key (optional)

    // Related entity DTOs for display (populated when needed)
    private PersonDTO person;
    private MilitaryRankDTO militaryRank;
    private JobDTO job;

    /**
     * Create DTO from entity
     */
    public static EmployeeDTO fromEntity(dz.mdn.iaas.common.administration.model.Employee employee) {
        if (employee == null) return null;
        
        EmployeeDTO.EmployeeDTOBuilder builder = EmployeeDTO.builder()
                .id(employee.getId())
                .serial(employee.getSerial())
                .hiringDate(employee.getHiringDate());

        // Handle foreign key relationships
        if (employee.getPerson() != null) {
            builder.personId(employee.getPerson().getId());
        }
        if (employee.getMilitaryRank() != null) {
            builder.militaryRankId(employee.getMilitaryRank().getId());
        }
        if (employee.getJob() != null) {
            builder.jobId(employee.getJob().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static EmployeeDTO fromEntityWithRelations(dz.mdn.iaas.common.administration.model.Employee employee) {
        EmployeeDTO dto = fromEntity(employee);
        if (dto == null) return null;

        // Populate related DTOs
        if (employee.getPerson() != null) {
            dto.setPerson(PersonDTO.fromEntity(employee.getPerson()));
        }
        if (employee.getMilitaryRank() != null) {
            dto.setMilitaryRank(MilitaryRankDTO.fromEntity(employee.getMilitaryRank()));
        }
        if (employee.getJob() != null) {
            dto.setJob(JobDTO.fromEntity(employee.getJob()));
        }

        return dto;
    }

    /**
     * Get employee display name
     */
    public String getDisplayName() {
        if (person != null) {
            return person.getDisplayName();
        }
        if (serial != null && !serial.trim().isEmpty()) {
            return "Employee " + serial;
        }
        return "Employee ID: " + personId;
    }

    /**
     * Get employee full display with rank and serial
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        // Add military rank if available
        if (militaryRank != null) {
            sb.append(militaryRank.getDisplayAbbreviation()).append(" ");
        }
        
        // Add person name
        if (person != null) {
            sb.append(person.getDisplayName());
        }
        
        // Add serial if available
        if (serial != null && !serial.trim().isEmpty()) {
            sb.append(" (").append(serial).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get military display with rank and name
     */
    public String getMilitaryDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (militaryRank != null) {
            sb.append(militaryRank.getDisplayText()).append(" ");
        }
        
        if (person != null) {
            sb.append(person.getDisplayName());
        }
        
        if (serial != null && !serial.trim().isEmpty()) {
            sb.append(" - ").append(serial);
        }
        
        return sb.toString();
    }

    /**
     * Get years of service
     */
    public Long getYearsOfService() {
        if (hiringDate == null) {
            return null;
        }
        
        Date now = new Date();
        if (hiringDate.after(now)) {
            return 0L; // Future hiring date
        }
        
        long diffInMillies = now.getTime() - hiringDate.getTime();
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return days / 365; // Approximate years
    }

    /**
     * Get months of service
     */
    public Long getMonthsOfService() {
        if (hiringDate == null) {
            return null;
        }
        
        Date now = new Date();
        if (hiringDate.after(now)) {
            return 0L; // Future hiring date
        }
        
        long diffInMillies = now.getTime() - hiringDate.getTime();
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (days * 12) / 365; // Approximate months
    }

    /**
     * Get days of service
     */
    public Long getDaysOfService() {
        if (hiringDate == null) {
            return null;
        }
        
        Date now = new Date();
        if (hiringDate.after(now)) {
            return 0L; // Future hiring date
        }
        
        long diffInMillies = now.getTime() - hiringDate.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get service category based on years of service
     */
    public String getServiceCategory() {
        Long years = getYearsOfService();
        if (years == null) {
            return "NEW_EMPLOYEE";
        }
        
        if (years < 1) {
            return "PROBATIONARY";
        } else if (years < 5) {
            return "JUNIOR";
        } else if (years < 15) {
            return "EXPERIENCED";
        } else if (years < 25) {
            return "SENIOR";
        } else {
            return "VETERAN";
        }
    }

    /**
     * Get employee rank level based on military rank
     */
    public String getRankLevel() {
        if (militaryRank != null) {
            return militaryRank.getRankLevel();
        }
        return "UNKNOWN_RANK";
    }

    /**
     * Get employee authority level based on military rank
     */
    public String getAuthorityLevel() {
        if (militaryRank != null) {
            return militaryRank.getAuthorityLevel();
        }
        return "UNDEFINED_AUTHORITY";
    }

    /**
     * Get service branch based on military rank
     */
    public String getServiceBranch() {
        if (militaryRank != null) {
            return militaryRank.getServiceBranch();
        }
        return "UNKNOWN_BRANCH";
    }

    /**
     * Check if employee can command units
     */
    public boolean canCommandUnits() {
        if (militaryRank != null) {
            return militaryRank.canCommandUnits();
        }
        return false;
    }

    /**
     * Check if employee is commissioned officer
     */
    public boolean isCommissionedOfficer() {
        if (militaryRank != null) {
            return militaryRank.isCommissionedOfficer();
        }
        return false;
    }

    /**
     * Get employee status based on available information
     */
    public String getEmployeeStatus() {
        if (person == null || militaryRank == null) {
            return "INCOMPLETE_PROFILE";
        }
        
        if (hiringDate == null) {
            return "PENDING_ASSIGNMENT";
        }
        
        Date now = new Date();
        if (hiringDate.after(now)) {
            return "FUTURE_EMPLOYEE";
        }
        
        if (job != null) {
            return "ACTIVE_ASSIGNED";
        }
        
        return "ACTIVE_UNASSIGNED";
    }

    /**
     * Get retirement eligibility (based on 30 years of service)
     */
    public String getRetirementEligibility() {
        Long years = getYearsOfService();
        if (years == null) {
            return "NOT_APPLICABLE";
        }
        
        if (years >= 30) {
            return "ELIGIBLE_FOR_RETIREMENT";
        } else if (years >= 25) {
            return "APPROACHING_RETIREMENT";
        } else if (years >= 20) {
            return "ELIGIBLE_FOR_EARLY_RETIREMENT";
        } else {
            return "NOT_ELIGIBLE";
        }
    }

    /**
     * Get promotion eligibility based on military rank
     */
    public String getPromotionEligibility() {
        if (militaryRank != null) {
            return militaryRank.getPromotionEligibility();
        }
        return "UNKNOWN_ELIGIBILITY";
    }

    /**
     * Get employee's age (if birth date available through person)
     */
    public Integer getAge() {
        if (person != null && person.getBirthDate() != null) {
            Date birthDate = person.getBirthDate();
            Date now = new Date();
            long ageInMillis = now.getTime() - birthDate.getTime();
            long ageInDays = TimeUnit.DAYS.convert(ageInMillis, TimeUnit.MILLISECONDS);
            return (int) (ageInDays / 365);
        }
        return null;
    }

    /**
     * Check if employee has complete profile
     */
    public boolean hasCompleteProfile() {
        return person != null && 
               militaryRank != null && 
               hiringDate != null &&
               serial != null && !serial.trim().isEmpty();
    }

    /**
     * Check if employee has job assignment
     */
    public boolean hasJobAssignment() {
        return job != null && jobId != null;
    }

    /**
     * Get completeness percentage
     */
    public int getCompletenessPercentage() {
        int totalFields = 5; // person, militaryRank, hiringDate, serial, job
        int completedFields = 0;
        
        if (personId != null) completedFields++;
        if (militaryRankId != null) completedFields++;
        if (hiringDate != null) completedFields++;
        if (serial != null && !serial.trim().isEmpty()) completedFields++;
        if (jobId != null) completedFields++;
        
        return (completedFields * 100) / totalFields;
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (militaryRank != null) {
            sb.append(militaryRank.getDisplayAbbreviation()).append(" ");
        }
        
        if (person != null) {
            sb.append(person.getShortDisplay());
        }
        
        return sb.toString();
    }

    /**
     * Get official display for documents
     */
    public String getOfficialDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (militaryRank != null) {
            sb.append(militaryRank.getFormalDisplay()).append(" ");
        }
        
        if (person != null) {
            sb.append(person.getFormalDisplayName());
        }
        
        if (serial != null && !serial.trim().isEmpty()) {
            sb.append(" - Serial: ").append(serial);
        }
        
        return sb.toString();
    }

    /**
     * Get career summary
     */
    public String getCareerSummary() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Employee: ").append(getDisplayName()).append("\n");
        
        if (militaryRank != null) {
            sb.append("Rank: ").append(militaryRank.getDisplayText()).append("\n");
            sb.append("Service Branch: ").append(getServiceBranch().replace("_", " ")).append("\n");
        }
        
        if (hiringDate != null) {
            sb.append("Hired: ").append(hiringDate).append("\n");
            Long years = getYearsOfService();
            if (years != null) {
                sb.append("Years of Service: ").append(years).append("\n");
            }
        }
        
        sb.append("Service Category: ").append(getServiceCategory().replace("_", " ")).append("\n");
        sb.append("Status: ").append(getEmployeeStatus().replace("_", " "));
        
        if (job != null) {
            sb.append("\nCurrent Job: ").append(job.getDisplayText());
        }
        
        return sb.toString();
    }

    /**
     * Get contact information through person
     */
    public String getContactInfo() {
        if (person != null) {
            return person.getDisplayWithAddress();
        }
        return "No contact information available";
    }

    /**
     * Get military classification
     */
    public String getMilitaryClassification() {
        StringBuilder sb = new StringBuilder();
        
        if (militaryRank != null) {
            sb.append("Rank: ").append(militaryRank.getDisplayText()).append("\n");
            sb.append("Level: ").append(getRankLevel().replace("_", " ")).append("\n");
            sb.append("Authority: ").append(getAuthorityLevel().replace("_", " ")).append("\n");
            sb.append("Branch: ").append(getServiceBranch().replace("_", " ")).append("\n");
            sb.append("Command Authority: ").append(canCommandUnits() ? "Yes" : "No").append("\n");
            sb.append("Officer Status: ").append(isCommissionedOfficer() ? "Commissioned" : "Non-commissioned");
        } else {
            sb.append("Military rank not assigned");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static EmployeeDTO createSimple(Long id, String displayName, String serial) {
        return EmployeeDTO.builder()
                .id(id)
                .serial(serial)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return personId != null && militaryRankId != null;
    }

    /**
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (personId == null) {
            errors.add("Person is required");
        }
        
        if (militaryRankId == null) {
            errors.add("Military rank is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by rank precedence, then by name)
     */
    public String getComparisonKey() {
        Integer precedence = 99; // Default high precedence (low priority)
        if (militaryRank != null) {
            precedence = militaryRank.getRankPrecedence();
        }
        
        String name = "ZZZZ";
        if (person != null) {
            name = person.getDisplayName().toLowerCase();
        }
        
        return String.format("%03d_%s", precedence, name);
    }

    /**
     * Get employee summary for reports
     */
    public String getEmployeeSummary() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Employee Summary:\n");
        sb.append("Name: ").append(getDisplayName()).append("\n");
        
        if (serial != null) {
            sb.append("Serial: ").append(serial).append("\n");
        }
        
        if (militaryRank != null) {
            sb.append("Rank: ").append(militaryRank.getFormalDisplay()).append("\n");
        }
        
        if (hiringDate != null) {
            sb.append("Hiring Date: ").append(hiringDate).append("\n");
            sb.append("Service Period: ").append(getYearsOfService()).append(" years\n");
        }
        
        sb.append("Status: ").append(getEmployeeStatus().replace("_", " ")).append("\n");
        sb.append("Service Category: ").append(getServiceCategory().replace("_", " "));
        
        return sb.toString();
    }

    /**
     * Get next promotion date estimate (every 3-5 years typically)
     */
    public String getNextPromotionEstimate() {
        Long years = getYearsOfService();
        if (years == null) {
            return "Service time unknown";
        }
        
        String rankLevel = getRankLevel();
        
        switch (rankLevel) {
            case "ENLISTED":
                if (years >= 4) return "Eligible for NCO promotion";
                return "Promotion in " + (4 - years) + " years (NCO track)";
                
            case "NON_COMMISSIONED_OFFICER":
                if (years >= 8) return "Eligible for officer track";
                return "Consider officer track in " + (8 - years) + " years";
                
            case "COMPANY_OFFICER":
                if (years >= 6) return "Eligible for senior officer promotion";
                return "Senior officer track in " + (6 - years) + " years";
                
            case "SENIOR_OFFICER":
                if (years >= 15) return "Eligible for general officer consideration";
                return "General officer consideration in " + (15 - years) + " years";
                
            case "GENERAL_OFFICER":
                return "Highest command level achieved";
                
            default:
                return "Promotion timeline varies by rank";
        }
    }

    /**
     * Get security clearance level based on rank
     */
    public String getSecurityClearanceLevel() {
        if (militaryRank != null) {
            return militaryRank.getSecurityClearanceLevel();
        }
        return "UNCLASSIFIED";
    }

    /**
     * Get training requirements based on rank and service time
     */
    public String getTrainingRequirements() {
        Long years = getYearsOfService();
        String rankLevel = getRankLevel();
        
        StringBuilder sb = new StringBuilder();
        
        // Annual mandatory training
        sb.append("Annual: Military protocol, Safety, Security clearance review\n");
        
        // Rank-specific training
        switch (rankLevel) {
            case "ENLISTED":
                sb.append("Rank-specific: Basic military skills, Technical specialization");
                break;
            case "NON_COMMISSIONED_OFFICER":
                sb.append("Rank-specific: Leadership training, Personnel management");
                break;
            case "COMPANY_OFFICER":
                sb.append("Rank-specific: Command training, Tactical operations");
                break;
            case "SENIOR_OFFICER":
                sb.append("Rank-specific: Strategic planning, Advanced leadership");
                break;
            case "GENERAL_OFFICER":
                sb.append("Rank-specific: Executive leadership, Strategic command");
                break;
        }
        
        // Service time milestones
        if (years != null) {
            if (years >= 5 && years < 10) {
                sb.append("\nCareer: Mid-career development program");
            } else if (years >= 15) {
                sb.append("\nCareer: Senior leadership development");
            }
        }
        
        return sb.toString();
    }

    /**
     * Get performance evaluation period
     */
    public String getPerformanceEvaluationPeriod() {
        String rankLevel = getRankLevel();
        
        return switch (rankLevel) {
            case "ENLISTED" -> "Semi-annual evaluation";
            case "NON_COMMISSIONED_OFFICER" -> "Annual evaluation";
            case "COMPANY_OFFICER", "SENIOR_OFFICER" -> "Annual evaluation with 360-degree feedback";
            case "GENERAL_OFFICER" -> "Annual strategic performance review";
            default -> "Standard annual evaluation";
        };
    }
}
