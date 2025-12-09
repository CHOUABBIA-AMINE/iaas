/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentTypeDTO
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.document.model.DocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentTypeDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - part of unique constraint with scope

    @NotNull(message = "Scope is required")
    private Integer scope; // F_04 - part of unique constraint with designationFr

    // Additional fields for display and statistics
    private Long documentCount; // Count of associated documents
    private String displayText; // Formatted display text

    public static DocumentTypeDTO fromEntity(DocumentType documentType) {
        if (documentType == null) return null;
        
        return DocumentTypeDTO.builder()
                .id(documentType.getId())
                .designationAr(documentType.getDesignationAr())
                .designationEn(documentType.getDesignationEn())
                .designationFr(documentType.getDesignationFr())
                .scope(documentType.getScope())
                .documentCount(documentType.getDocuments() != null ? (long) documentType.getDocuments().size() : 0L)
                .displayText(buildDisplayText(documentType))
                .build();
    }

    public DocumentType toEntity() {
        DocumentType documentType = new DocumentType();
        documentType.setId(this.id);
        documentType.setDesignationAr(this.designationAr);
        documentType.setDesignationEn(this.designationEn);
        documentType.setDesignationFr(this.designationFr);
        documentType.setScope(this.scope);
        return documentType;
    }

    public void updateEntity(dz.mdn.iaas.common.document.model.DocumentType documentType) {
        if (this.designationAr != null) {
            documentType.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            documentType.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            documentType.setDesignationFr(this.designationFr);
        }
        if (this.scope != null) {
            documentType.setScope(this.scope);
        }
    }

    public String getDefaultDesignation() {
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            return designationFr;
        }
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            return designationEn;
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            return designationAr;
        }
        return "DocumentType #" + id;
    }

    public String getDesignationByLanguage(String language) {
        if (language == null) return getDefaultDesignation();
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> designationAr != null && !designationAr.trim().isEmpty() ? 
                    designationAr : getDefaultDesignation();
            case "en", "english" -> designationEn != null && !designationEn.trim().isEmpty() ? 
                    designationEn : getDefaultDesignation();
            case "fr", "french" -> designationFr != null && !designationFr.trim().isEmpty() ? 
                    designationFr : getDefaultDesignation();
            default -> getDefaultDesignation();
        };
    }

    public String getDisplayTextWithScope() {
        return String.format("%s (Scope: %d)", getDefaultDesignation(), scope);
    }

    public String getScopeDescription() {
        if (scope == null) return "Unknown";
        
        return switch (scope) {
            case 0 -> "Global";
            case 1 -> "System";
            case 2 -> "Department";
            case 3 -> "User";
            default -> "Scope " + scope;
        };
    }

    public String getFullDisplayText() {
        return String.format("%s (%s)", getDefaultDesignation(), getScopeDescription());
    }

    public boolean hasDesignation() {
        return (designationAr != null && !designationAr.trim().isEmpty()) ||
               (designationEn != null && !designationEn.trim().isEmpty()) ||
               (designationFr != null && !designationFr.trim().isEmpty());
    }

    public boolean isComplete() {
        return scope != null && hasDesignation();
    }

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

    public static DocumentTypeDTO createSimple(Long id, String designationAr, String designationEn, 
                                             String designationFr, Integer scope) {
        return DocumentTypeDTO.builder()
                .id(id)
                .designationAr(designationAr)
                .designationEn(designationEn)
                .designationFr(designationFr)
                .scope(scope)
                .displayText(designationFr != null ? designationFr : 
                           (designationEn != null ? designationEn : designationAr))
                .build();
    }

    private static String buildDisplayText(DocumentType documentType) {
        String designation = documentType.getDesignationFr();
        if (designation == null || designation.trim().isEmpty()) {
            designation = documentType.getDesignationEn();
        }
        if (designation == null || designation.trim().isEmpty()) {
            designation = documentType.getDesignationAr();
        }
        if (designation == null || designation.trim().isEmpty()) {
            designation = "DocumentType #" + documentType.getId();
        }
        return designation;
    }
}
