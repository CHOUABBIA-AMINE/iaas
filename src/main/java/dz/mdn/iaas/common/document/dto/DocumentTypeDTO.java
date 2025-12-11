/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentTypeDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.common.document.model.DocumentType;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DocumentType Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentTypeDTO extends GenericDTO<DocumentType> {

    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    private String designationAr;

    @Size(max = 100, message = "English designation must not exceed 100 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    private String designationFr;

    @Size(max = 100, message = "Arabic acronym must not exceed 100 characters")
    private String acronymAr;

    @Size(max = 100, message = "English acronym must not exceed 100 characters")
    private String acronymEn;

    @NotBlank(message = "French acronym is required")
    @Size(max = 100, message = "French acronym must not exceed 100 characters")
    private String acronymFr;

    @NotBlank(message = "Code is required")
    @Size(max = 10, message = "Code must not exceed 10 characters")
    private String code;

    private Boolean requiresApproval;

    private Boolean isConfidential;

    @Override
    public DocumentType toEntity() {
        DocumentType entity = new DocumentType();
        entity.setId(this.getId());
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        entity.setAcronymAr(this.acronymAr);
        entity.setAcronymEn(this.acronymEn);
        entity.setAcronymFr(this.acronymFr);
        entity.setCode(this.code);
        entity.setRequiresApproval(this.requiresApproval);
        entity.setIsConfidential(this.isConfidential);
        return entity;
    }

    @Override
    public void updateEntity(DocumentType entity) {
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
        if (this.acronymAr != null) entity.setAcronymAr(this.acronymAr);
        if (this.acronymEn != null) entity.setAcronymEn(this.acronymEn);
        if (this.acronymFr != null) entity.setAcronymFr(this.acronymFr);
        if (this.code != null) entity.setCode(this.code);
        if (this.requiresApproval != null) entity.setRequiresApproval(this.requiresApproval);
        if (this.isConfidential != null) entity.setIsConfidential(this.isConfidential);
    }

    public static DocumentTypeDTO fromEntity(DocumentType entity) {
        if (entity == null) return null;
        return DocumentTypeDTO.builder()
                .id(entity.getId())
                .designationAr(entity.getDesignationAr())
                .designationEn(entity.getDesignationEn())
                .designationFr(entity.getDesignationFr())
                .acronymAr(entity.getAcronymAr())
                .acronymEn(entity.getAcronymEn())
                .acronymFr(entity.getAcronymFr())
                .code(entity.getCode())
                .requiresApproval(entity.getRequiresApproval())
                .isConfidential(entity.getIsConfidential())
                .build();
    }
}