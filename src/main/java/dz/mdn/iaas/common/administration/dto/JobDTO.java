/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: JobDTO
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

/**
 * Job Data Transfer Object
 * Maps exactly to Job model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=structure
 * F_03 (designationFr) has unique constraint and is required
 * F_04 (structure) is required foreign key
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required and unique

    @NotNull(message = "Structure is required")
    private Long structureId; // F_04 - required foreign key

    // Nested structure information for display purposes
    private StructureInfo structure;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class StructureInfo {
        private Long id;
        private String designationFr;
        private String designationEn;
        private String designationAr;
        private String acronymFr;
        private String acronymEn;
        private String acronymAr;
        private String structureTypeDesignation;
        private String structureTypeAcronym;
        private String parentStructureDesignation;
        private String parentStructureAcronym;
    }

    /**
     * Create DTO from entity
     */
    public static JobDTO fromEntity(dz.mdn.iaas.common.administration.model.Job job) {
        if (job == null) return null;
        
        StructureInfo structureInfo = null;
        if (job.getStructure() != null) {
            structureInfo = StructureInfo.builder()
                    .id(job.getStructure().getId())
                    .designationFr(job.getStructure().getDesignationFr())
                    .designationEn(job.getStructure().getDesignationEn())
                    .designationAr(job.getStructure().getDesignationAr())
                    .acronymFr(job.getStructure().getAcronymFr())
                    .acronymEn(job.getStructure().getAcronymEn())
                    .acronymAr(job.getStructure().getAcronymAr())
                    .structureTypeDesignation(job.getStructure().getStructureType() != null ? 
                                            job.getStructure().getStructureType().getDesignationFr() : null)
                    .parentStructureDesignation(job.getStructure().getStructureUp() != null ? 
                                               job.getStructure().getStructureUp().getDesignationFr() : null)
                    .parentStructureAcronym(job.getStructure().getStructureUp() != null ? 
                                          job.getStructure().getStructureUp().getAcronymFr() : null)
                    .build();
        }
        
        return JobDTO.builder()
                .id(job.getId())
                .designationAr(job.getDesignationAr())
                .designationEn(job.getDesignationEn())
                .designationFr(job.getDesignationFr())
                .structureId(job.getStructure() != null ? job.getStructure().getId() : null)
                .structure(structureInfo)
                .build();
    }

    /**
     * Convert to entity (without setting Structure - use service for that)
     */
    public dz.mdn.iaas.common.administration.model.Job toEntity() {
        dz.mdn.iaas.common.administration.model.Job job = 
            new dz.mdn.iaas.common.administration.model.Job();
        job.setId(this.id);
        job.setDesignationAr(this.designationAr);
        job.setDesignationEn(this.designationEn);
        job.setDesignationFr(this.designationFr);
        // Note: structure should be set by the service layer
        return job;
    }

    /**
     * Update entity from DTO (without updating Structure - use service for that)
     */
    public void updateEntity(dz.mdn.iaas.common.administration.model.Job job) {
        if (this.designationAr != null) {
            job.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            job.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            job.setDesignationFr(this.designationFr);
        }
        // Note: structure should be updated by the service layer
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
     * Check if job has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this job
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
     * Get job category based on French designation analysis
     */
    public String getJobCategory() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Leadership positions
        if (designation.contains("commandant") || designation.contains("chef") || 
            designation.contains("directeur") || designation.contains("responsable")) {
            return "LEADERSHIP";
        }
        
        // Administrative positions
        if (designation.contains("secrétaire") || designation.contains("assistant") || 
            designation.contains("administrateur") || designation.contains("gestionnaire")) {
            return "ADMINISTRATIVE";
        }
        
        // Technical positions
        if (designation.contains("ingénieur") || designation.contains("technicien") || 
            designation.contains("spécialiste") || designation.contains("expert")) {
            return "TECHNICAL";
        }
        
        // Operational positions
        if (designation.contains("opérateur") || designation.contains("pilote") || 
            designation.contains("conducteur") || designation.contains("agent")) {
            return "OPERATIONAL";
        }
        
        // Security positions
        if (designation.contains("garde") || designation.contains("sécurité") || 
            designation.contains("surveillant") || designation.contains("contrôleur")) {
            return "SECURITY";
        }
        
        // Medical positions
        if (designation.contains("médecin") || designation.contains("infirmier") || 
            designation.contains("dentiste") || designation.contains("pharmacien")) {
            return "MEDICAL";
        }
        
        // Legal positions
        if (designation.contains("juriste") || designation.contains("avocat") || 
            designation.contains("conseiller juridique") || designation.contains("magistrat")) {
            return "LEGAL";
        }
        
        // Financial positions
        if (designation.contains("comptable") || designation.contains("financier") || 
            designation.contains("trésorier") || designation.contains("auditeur")) {
            return "FINANCIAL";
        }
        
        // Human Resources positions
        if (designation.contains("ressources humaines") || designation.contains("rh") || 
            designation.contains("personnel") || designation.contains("recruteur")) {
            return "HUMAN_RESOURCES";
        }
        
        // Communication positions
        if (designation.contains("communication") || designation.contains("relations publiques") || 
            designation.contains("journaliste") || designation.contains("porte-parole")) {
            return "COMMUNICATION";
        }
        
        // Logistics positions
        if (designation.contains("logistique") || designation.contains("approvisionnement") || 
            designation.contains("magasinier") || designation.contains("transport")) {
            return "LOGISTICS";
        }
        
        // Training positions
        if (designation.contains("formateur") || designation.contains("instructeur") || 
            designation.contains("enseignant") || designation.contains("professeur")) {
            return "TRAINING";
        }
        
        return "GENERAL";
    }

    /**
     * Get job level based on designation analysis
     */
    public String getJobLevel() {
        if (designationFr == null) return "UNKNOWN";
        
        String designation = designationFr.toLowerCase();
        
        // Executive level
        if (designation.contains("directeur général") || designation.contains("président") || 
            designation.contains("commandant en chef") || designation.contains("secrétaire général")) {
            return "EXECUTIVE";
        }
        
        // Senior management
        if (designation.contains("directeur") || designation.contains("chef de service") || 
            designation.contains("commandant") || designation.contains("responsable")) {
            return "SENIOR_MANAGEMENT";
        }
        
        // Middle management
        if (designation.contains("chef") || designation.contains("superviseur") || 
            designation.contains("coordinateur") || designation.contains("adjoint")) {
            return "MIDDLE_MANAGEMENT";
        }
        
        // Senior specialist
        if (designation.contains("expert") || designation.contains("spécialiste senior") || 
            designation.contains("ingénieur principal") || designation.contains("conseiller")) {
            return "SENIOR_SPECIALIST";
        }
        
        // Specialist
        if (designation.contains("spécialiste") || designation.contains("ingénieur") || 
            designation.contains("analyste") || designation.contains("technicien")) {
            return "SPECIALIST";
        }
        
        // Operational
        if (designation.contains("opérateur") || designation.contains("agent") || 
            designation.contains("assistant") || designation.contains("employé")) {
            return "OPERATIONAL";
        }
        
        // Entry level
        if (designation.contains("stagiaire") || designation.contains("apprenti") || 
            designation.contains("junior") || designation.contains("débutant")) {
            return "ENTRY_LEVEL";
        }
        
        return "INTERMEDIATE";
    }

    /**
     * Get structure designation if available
     */
    public String getStructureDesignation() {
        return structure != null ? structure.getDesignationFr() : null;
    }

    /**
     * Get structure acronym if available
     */
    public String getStructureAcronym() {
        return structure != null ? structure.getAcronymFr() : null;
    }

    /**
     * Get structure type designation if available
     */
    public String getStructureTypeDesignation() {
        return structure != null ? structure.getStructureTypeDesignation() : null;
    }

    /**
     * Get structure type acronym if available
     */
    public String getStructureTypeAcronym() {
        return structure != null ? structure.getStructureTypeAcronym() : null;
    }

    /**
     * Get job priority based on category and level
     */
    public int getJobPriority() {
        String category = getJobCategory();
        String level = getJobLevel();
        
        int categoryPriority = switch (category) {
            case "LEADERSHIP" -> 1;
            case "SECURITY" -> 2;
            case "MEDICAL" -> 3;
            case "TECHNICAL" -> 4;
            case "ADMINISTRATIVE" -> 5;
            case "OPERATIONAL" -> 6;
            case "FINANCIAL" -> 7;
            case "LEGAL" -> 8;
            case "HUMAN_RESOURCES" -> 9;
            case "COMMUNICATION" -> 10;
            case "LOGISTICS" -> 11;
            case "TRAINING" -> 12;
            default -> 13;
        };
        
        int levelPriority = switch (level) {
            case "EXECUTIVE" -> 1;
            case "SENIOR_MANAGEMENT" -> 2;
            case "MIDDLE_MANAGEMENT" -> 3;
            case "SENIOR_SPECIALIST" -> 4;
            case "SPECIALIST" -> 5;
            case "OPERATIONAL" -> 6;
            case "ENTRY_LEVEL" -> 7;
            default -> 8;
        };
        
        return (categoryPriority * 10) + levelPriority;
    }

    /**
     * Check if job requires security clearance
     */
    public boolean requiresSecurityClearance() {
        String category = getJobCategory();
        String level = getJobLevel();
        return "LEADERSHIP".equals(category) || "SECURITY".equals(category) || 
               "EXECUTIVE".equals(level) || "SENIOR_MANAGEMENT".equals(level);
    }

    /**
     * Get typical education requirements
     */
    public String getEducationRequirement() {
        return switch (getJobCategory()) {
            case "LEADERSHIP" -> "MASTER_OR_HIGHER";
            case "MEDICAL" -> "MEDICAL_DEGREE";
            case "LEGAL" -> "LAW_DEGREE";
            case "TECHNICAL" -> "ENGINEERING_DEGREE";
            case "FINANCIAL" -> "FINANCE_DEGREE";
            case "ADMINISTRATIVE" -> "BACHELOR_DEGREE";
            case "OPERATIONAL" -> "HIGH_SCHOOL_OR_VOCATIONAL";
            case "SECURITY" -> "HIGH_SCHOOL_PLUS_TRAINING";
            case "LOGISTICS" -> "BACHELOR_OR_VOCATIONAL";
            case "TRAINING" -> "BACHELOR_PLUS_CERTIFICATION";
            case "COMMUNICATION" -> "BACHELOR_DEGREE";
            case "HUMAN_RESOURCES" -> "BACHELOR_DEGREE";
            default -> "HIGH_SCHOOL";
        };
    }

    /**
     * Get typical experience requirements
     */
    public String getExperienceRequirement() {
        return switch (getJobLevel()) {
            case "EXECUTIVE" -> "15_PLUS_YEARS";
            case "SENIOR_MANAGEMENT" -> "10_TO_15_YEARS";
            case "MIDDLE_MANAGEMENT" -> "5_TO_10_YEARS";
            case "SENIOR_SPECIALIST" -> "7_TO_12_YEARS";
            case "SPECIALIST" -> "3_TO_7_YEARS";
            case "OPERATIONAL" -> "1_TO_3_YEARS";
            case "ENTRY_LEVEL" -> "0_TO_1_YEAR";
            default -> "2_TO_5_YEARS";
        };
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static JobDTO createSimple(Long id, String designationFr, Long structureId) {
        return JobDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .structureId(structureId)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() && 
               structureId != null;
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return designationFr != null && designationFr.length() > 40 ? 
                designationFr.substring(0, 40) + "..." : designationFr;
    }

    /**
     * Get full display with all languages and structure context
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
        
        if (structure != null && structure.getAcronymFr() != null) {
            sb.append(" (").append(structure.getAcronymFr()).append(")");
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
     * Get display with job category
     */
    public String getDisplayWithCategory() {
        return designationFr + " (" + getJobCategory().replace("_", " ").toLowerCase() + ")";
    }

    /**
     * Get display with structure context
     */
    public String getDisplayWithStructure() {
        String structureContext = structure != null ? structure.getAcronymFr() : "?";
        return designationFr + " @ " + structureContext;
    }

    /**
     * Get job classification for organizational purposes
     */
    public String getJobClassification() {
        String category = getJobCategory();
        String level = getJobLevel();
        return category + "_" + level;
    }

    /**
     * Check if job is in management track
     */
    public boolean isManagementTrack() {
        String level = getJobLevel();
        return "EXECUTIVE".equals(level) || "SENIOR_MANAGEMENT".equals(level) || 
               "MIDDLE_MANAGEMENT".equals(level);
    }

    /**
     * Check if job is in specialist track
     */
    public boolean isSpecialistTrack() {
        String level = getJobLevel();
        return "SENIOR_SPECIALIST".equals(level) || "SPECIALIST".equals(level);
    }

    /**
     * Check if job is operational
     */
    public boolean isOperational() {
        String category = getJobCategory();
        String level = getJobLevel();
        return "OPERATIONAL".equals(category) || "OPERATIONAL".equals(level);
    }

    /**
     * Get command responsibility level
     */
    public String getCommandResponsibility() {
        return switch (getJobLevel()) {
            case "EXECUTIVE" -> "STRATEGIC_COMMAND";
            case "SENIOR_MANAGEMENT" -> "OPERATIONAL_COMMAND";
            case "MIDDLE_MANAGEMENT" -> "TACTICAL_COMMAND";
            case "SENIOR_SPECIALIST" -> "TECHNICAL_LEADERSHIP";
            case "SPECIALIST" -> "TASK_LEADERSHIP";
            default -> "NO_COMMAND";
        };
    }

    /**
     * Get reporting relationships
     */
    public String getReportingLevel() {
        return switch (getJobLevel()) {
            case "EXECUTIVE" -> "BOARD_LEVEL";
            case "SENIOR_MANAGEMENT" -> "EXECUTIVE_LEVEL";
            case "MIDDLE_MANAGEMENT" -> "SENIOR_MANAGEMENT_LEVEL";
            case "SENIOR_SPECIALIST", "SPECIALIST" -> "MANAGEMENT_LEVEL";
            case "OPERATIONAL" -> "SUPERVISORY_LEVEL";
            case "ENTRY_LEVEL" -> "OPERATIONAL_LEVEL";
            default -> "INTERMEDIATE_LEVEL";
        };
    }

    /**
     * Check if job allows remote work
     */
    public boolean allowsRemoteWork() {
        String category = getJobCategory();
        return "ADMINISTRATIVE".equals(category) || "TECHNICAL".equals(category) || 
               "COMMUNICATION".equals(category) || "FINANCIAL".equals(category);
    }

    /**
     * Get typical work schedule
     */
    public String getWorkSchedule() {
        String category = getJobCategory();
        return switch (category) {
            case "LEADERSHIP", "ADMINISTRATIVE", "FINANCIAL", "LEGAL", "HUMAN_RESOURCES", "COMMUNICATION" -> "STANDARD_HOURS";
            case "MEDICAL" -> "SHIFT_WORK";
            case "SECURITY" -> "24_7_COVERAGE";
            case "OPERATIONAL", "LOGISTICS" -> "OPERATIONAL_HOURS";
            case "TECHNICAL" -> "FLEXIBLE_HOURS";
            case "TRAINING" -> "SCHEDULED_SESSIONS";
            default -> "STANDARD_HOURS";
        };
    }

    /**
     * Get career advancement path
     */
    public String[] getCareerPath() {
        String category = getJobCategory();
        return switch (category) {
            case "LEADERSHIP" -> new String[]{"SENIOR_MANAGEMENT", "EXECUTIVE"};
            case "ADMINISTRATIVE" -> new String[]{"SUPERVISOR", "MANAGER", "DIRECTOR"};
            case "TECHNICAL" -> new String[]{"SENIOR_SPECIALIST", "TECHNICAL_LEAD", "CHIEF_TECHNICAL_OFFICER"};
            case "MEDICAL" -> new String[]{"SENIOR_DOCTOR", "DEPARTMENT_HEAD", "MEDICAL_DIRECTOR"};
            case "LEGAL" -> new String[]{"SENIOR_COUNSEL", "GENERAL_COUNSEL"};
            case "FINANCIAL" -> new String[]{"SENIOR_ANALYST", "MANAGER", "CFO"};
            default -> new String[]{"SPECIALIST", "SENIOR_SPECIALIST", "MANAGER"};
        };
    }
}
