/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.document.model.Document;
import dz.mdn.iaas.common.document.model.DocumentType;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.system.utility.dto.FileDTO;
import dz.mdn.iaas.system.utility.model.File;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Document Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentDTO extends GenericDTO<Document> {

    @NotBlank(message = "Reference is required")
    @Size(max = 50, message = "Reference must not exceed 50 characters")
    private String reference;

    @Size(max = 500, message = "Subject must not exceed 500 characters")
    private String subject;

    private Date documentDate;

    @NotNull(message = "Document type ID is required")
    private Long documentTypeId;

    @NotNull(message = "File ID is required")
    private Long fileId;
    
    private DocumentTypeDTO documentType;
    
    private FileDTO file;

    @Override
    public Document toEntity() {
        Document entity = new Document();
        entity.setId(this.getId());
        entity.setReference(this.reference);
        entity.setSubject(this.subject);
        entity.setDocumentDate(this.documentDate);
        
        if (this.documentTypeId != null) {
        	DocumentType documentType = new DocumentType();
        	documentType.setId(this.documentTypeId);
            entity.setDocumentType(documentType);
        }
        
        if (this.fileId != null) {
        	File file = new File();
        	file.setId(this.fileId);
            entity.setFile(file);
        }
        
        return entity;
    }

    @Override
    public void updateEntity(Document entity) {
        if (this.reference != null) entity.setReference(this.reference);
        if (this.subject != null) entity.setSubject(this.subject);
        if (this.documentDate != null) entity.setDocumentDate(this.documentDate);
        
        if (this.documentTypeId != null) {
        	DocumentType documentType = new DocumentType();
        	documentType.setId(this.documentTypeId);
            entity.setDocumentType(documentType);
        }
        
        if (this.fileId != null) {
        	File file = new File();
        	file.setId(this.fileId);
            entity.setFile(file);
        }
    }

    public static DocumentDTO fromEntity(Document entity) {
        if (entity == null) return null;
        return DocumentDTO.builder()
                .id(entity.getId())
                .reference(entity.getReference())
                .subject(entity.getSubject())
                .documentDate(entity.getDocumentDate())
                .documentTypeId(entity.getDocumentType() != null ? entity.getDocumentType().getId() : null)
                .fileId(entity.getFile() != null ? entity.getFile().getId() : null)
                
                .documentType(entity.getDocumentType() != null ? DocumentTypeDTO.fromEntity(entity.getDocumentType()) : null)
                .file(entity.getFile() != null ? FileDTO.fromEntity(entity.getFile()) : null)
                .build();
    }
}