/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.administration.model.Structure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Structure Data Transfer Object
 * Maps exactly to Structure model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr, F_07=structureType, F_08=structureUp
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_07 (structureType) is required foreign key
 * F_08 (structureUp) is optional foreign key (self-reference for hierarchy)
 * F_01 (designationAr), F_02 (designationEn), F_04 (acronymAr), F_05 (acronymEn) are optional
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StructureDTO {

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

    @NotNull(message = "Structure type is required")
    private Long structureTypeId; // F_07 - required foreign key

    private Long structureUpId; // F_08 - optional foreign key (parent structure)

    // Nested structure type information for display purposes
    private StructureTypeInfo structureType;

    // Nested parent structure information for display purposes
    private StructureInfo structureUp;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class StructureTypeInfo {
        private Long id;
        private String designationFr;
        private String designationEn;
        private String designationAr;
        private String acronymFr;
        private String acronymEn;
        private String acronymAr;
    }

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
    }

    /**
     * Create DTO from entity
     */
    public static StructureDTO fromEntity(Structure structure) {
        if (structure == null) return null;
        
        StructureTypeInfo typeInfo = null;
        if (structure.getStructureType() != null) {
            typeInfo = StructureTypeInfo.builder()
                    .id(structure.getStructureType().getId())
                    .designationFr(structure.getStructureType().getDesignationFr())
                    .designationEn(structure.getStructureType().getDesignationEn())
                    .designationAr(structure.getStructureType().getDesignationAr())
                    .build();
        }
        
        StructureInfo parentInfo = null;
        if (structure.getStructureUp() != null) {
            parentInfo = StructureInfo.builder()
                    .id(structure.getStructureUp().getId())
                    .designationFr(structure.getStructureUp().getDesignationFr())
                    .designationEn(structure.getStructureUp().getDesignationEn())
                    .designationAr(structure.getStructureUp().getDesignationAr())
                    .acronymFr(structure.getStructureUp().getAcronymFr())
                    .acronymEn(structure.getStructureUp().getAcronymEn())
                    .acronymAr(structure.getStructureUp().getAcronymAr())
                    .build();
        }
        
        return StructureDTO.builder()
                .id(structure.getId())
                .designationAr(structure.getDesignationAr())
                .designationEn(structure.getDesignationEn())
                .designationFr(structure.getDesignationFr())
                .acronymAr(structure.getAcronymAr())
                .acronymEn(structure.getAcronymEn())
                .acronymFr(structure.getAcronymFr())
                .structureTypeId(structure.getStructureType() != null ? 
                               structure.getStructureType().getId() : null)
                .structureUpId(structure.getStructureUp() != null ? 
                             structure.getStructureUp().getId() : null)
                .structureType(typeInfo)
                .structureUp(parentInfo)
                .build();
    }

    /**
     * Convert to entity (without setting foreign key relationships - use service for that)
     */
    public Structure toEntity() {
        Structure structure = new Structure();
        structure.setId(this.id);
        structure.setDesignationAr(this.designationAr);
        structure.setDesignationEn(this.designationEn);
        structure.setDesignationFr(this.designationFr);
        structure.setAcronymAr(this.acronymAr);
        structure.setAcronymEn(this.acronymEn);
        structure.setAcronymFr(this.acronymFr);
        // Note: structureType and structureUp should be set by the service layer
        return structure;
    }

    /**
     * Update entity from DTO (without updating foreign key relationships - use service for that)
     */
    public void updateEntity(dz.mdn.iaas.common.administration.model.Structure structure) {
        if (this.designationAr != null) {
            structure.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            structure.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            structure.setDesignationFr(this.designationFr);
        }
        if (this.acronymAr != null) {
            structure.setAcronymAr(this.acronymAr);
        }
        if (this.acronymEn != null) {
            structure.setAcronymEn(this.acronymEn);
        }
        if (this.acronymFr != null) {
            structure.setAcronymFr(this.acronymFr);
        }
        // Note: structureType and structureUp should be updated by the service layer
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
     * Get display acronym with priority: French acronym > English acronym > Arabic acronym
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
     * Check if structure has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this structure
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
     * Get structure type designation if available
     */
    public String getStructureTypeDesignation() {
        return structureType != null ? structureType.getDesignationFr() : null;
    }

    /**
     * Get structure type acronym if available
     */
    public String getStructureTypeAcronym() {
        return structureType != null ? structureType.getAcronymFr() : null;
    }

    /**
     * Get parent structure designation if available
     */
    public String getParentStructureDesignation() {
        return structureUp != null ? structureUp.getDesignationFr() : null;
    }

    /**
     * Get parent structure acronym if available
     */
    public String getParentStructureAcronym() {
        return structureUp != null ? structureUp.getAcronymFr() : null;
    }

    /**
     * Check if structure has parent
     */
    public boolean hasParent() {
        return structureUpId != null;
    }

    /**
     * Check if this is a root structure (no parent)
     */
    public boolean isRoot() {
        return structureUpId == null;
    }

    /**
     * Get hierarchy level (0 for root, increases with depth)
     */
    public int getHierarchyLevel() {
        // This would need to be calculated by the service with actual hierarchy traversal
        // For now, return basic level based on parent existence
        return hasParent() ? 1 : 0;
    }

    /**
     * Get full hierarchy path (simplified - would need service calculation for complete path)
     */
    public String getHierarchyPath() {
        if (!hasParent()) {
            return acronymFr;
        }
        // This is simplified - actual implementation would traverse full hierarchy
        return (structureUp != null ? structureUp.getAcronymFr() : "?") + " > " + acronymFr;
    }

    /**
     * Get organizational position based on parent-child relationship
     */
    public String getOrganizationalPosition() {
        if (isRoot()) {
            return "TOP_LEVEL";
        }
        // This would need service logic to determine if it has children
        return "SUB_STRUCTURE";
    }

    /**
     * Get command chain level
     */
    public String getCommandChainLevel() {
        if (isRoot()) {
            return "SUPREME_COMMAND";
        }
        return hasParent() ? "SUBORDINATE_COMMAND" : "INDEPENDENT_COMMAND";
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static StructureDTO createSimple(Long id, String designationFr, String acronymFr, Long structureTypeId) {
        return StructureDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .acronymFr(acronymFr)
                .structureTypeId(structureTypeId)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() && 
               acronymFr != null && !acronymFr.trim().isEmpty() &&
               structureTypeId != null;
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        return acronymFr + " - " + (designationFr != null && designationFr.length() > 30 ? 
                designationFr.substring(0, 30) + "..." : designationFr);
    }

    /**
     * Get full display with all languages and hierarchy
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        // Add hierarchy prefix if has parent
        if (hasParent() && structureUp != null) {
            sb.append(structureUp.getAcronymFr()).append(" > ");
        }
        
        sb.append(acronymFr).append(" - ").append(designationFr);
        
        if (designationEn != null && !designationEn.equals(designationFr)) {
            sb.append(" / ").append(acronymEn != null ? acronymEn : "").append(" - ").append(designationEn);
        }
        
        if (designationAr != null) {
            sb.append(" / ").append(acronymAr != null ? acronymAr : "").append(" - ").append(designationAr);
        }
        
        if (structureType != null) {
            sb.append(" (").append(structureType.getAcronymFr()).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get comparison key for sorting (by hierarchy then French designation)
     */
    public String getComparisonKey() {
        String parentKey = hasParent() && structureUp != null ? 
                          structureUp.getAcronymFr() + "_" : "000_";
        return parentKey + (designationFr != null ? designationFr.toLowerCase() : "");
    }

    /**
     * Get display with structure type
     */
    public String getDisplayWithType() {
        String typeAcronym = structureType != null ? structureType.getAcronymFr() : "?";
        return acronymFr + " - " + designationFr + " (" + typeAcronym + ")";
    }

    /**
     * Get display with acronym and designation
     */
    public String getDisplayWithAcronym() {
        return acronymFr + " - " + designationFr;
    }

    /**
     * Get display with hierarchy context
     */
    public String getDisplayWithHierarchy() {
        if (!hasParent()) {
            return "📋 " + acronymFr + " - " + designationFr + " (Root)";
        }
        String parentAcronym = structureUp != null ? structureUp.getAcronymFr() : "?";
        return "  └─ " + acronymFr + " - " + designationFr + " (under " + parentAcronym + ")";
    }

    /**
     * Check if structure can have subordinates (based on type or level)
     */
    public boolean canHaveSubordinates() {
        // This would typically depend on structure type and organizational rules
        return true; // Simplified - actual logic would check structure type rules
    }

    /**
     * Get structure scope based on hierarchy and type
     */
    public String getStructureScope() {
        if (isRoot()) {
            return "ORGANIZATIONAL";
        }
        return hasParent() ? "DEPARTMENTAL" : "DIVISIONAL";
    }

    /**
     * Get reporting level
     */
    public String getReportingLevel() {
        if (isRoot()) {
            return "EXECUTIVE";
        }
        return "OPERATIONAL";
    }

    /**
     * Get authority level based on position in hierarchy
     */
    public String getAuthorityLevel() {
        if (isRoot()) {
            return "FULL_AUTHORITY";
        }
        return hasParent() ? "DELEGATED_AUTHORITY" : "LIMITED_AUTHORITY";
    }

    /**
     * Check if structure requires parent approval for decisions
     */
    public boolean requiresParentApproval() {
        return hasParent();
    }

    /**
     * Get structure establishment date (would be added as field in future)
     */
    public String getEstablishmentStatus() {
        return "ACTIVE"; // Simplified - would come from actual field
    }

    /**
     * Get structure operational status
     */
    public String getOperationalStatus() {
        return "OPERATIONAL"; // Simplified - would come from actual field or business logic
    }

    /**
     * Check if structure can be restructured (moved in hierarchy)
     */
    public boolean canBeRestructured() {
        return !isRoot(); // Root structures typically cannot be moved
    }

    /**
     * Get structure classification for security purposes
     */
    public String getSecurityClassification() {
        if (structureType != null) {
            String typeDesignation = structureType.getDesignationFr();
            if (typeDesignation != null && typeDesignation.toLowerCase().contains("renseignement")) {
                return "CLASSIFIED";
            }
            if (typeDesignation != null && typeDesignation.toLowerCase().contains("commandement")) {
                return "RESTRICTED";
            }
        }
        return "INTERNAL";
    }
}
