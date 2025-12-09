/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentDTO
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.document.model.Document;
import dz.mdn.iaas.common.document.model.DocumentType;
import dz.mdn.iaas.system.utility.model.File;
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
public class DocumentDTO {

    private Long id; // F_00

    @Size(max = 500, message = "Reference must not exceed 500 characters")
    private String reference; // F_01 - optional

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issueDate; // F_02 - optional

    @NotNull(message = "Document type ID is required")
    private Long documentTypeId; // F_03 - foreign key to DocumentType (required)

    private Long fileId; // F_04 - foreign key to File (optional)

    // Additional fields for display purposes (from related entities)
    private String documentTypeDesignationAr;
    private String documentTypeDesignationEn;
    private String documentTypeDesignationFr;
    private Integer documentTypeScope;
    private String documentTypeDisplayText;
    
    private String fileName;
    private String fileExtension;
    private Long fileSize;
    private String fileDisplayText;
    
    // Combined display information
    private String displayReference;
    private String fullDisplayText;

    public static DocumentDTO fromEntity(Document document) {
        if (document == null) return null;
        
        DocumentDTO.DocumentDTOBuilder builder = DocumentDTO.builder()
                .id(document.getId())
                .reference(document.getReference())
                .issueDate(document.getIssueDate());

        // Handle DocumentType relationship (required)
        if (document.getDocumentType() != null) {
            builder.documentTypeId(document.getDocumentType().getId())
                   .documentTypeDesignationAr(document.getDocumentType().getDesignationAr())
                   .documentTypeDesignationEn(document.getDocumentType().getDesignationEn())
                   .documentTypeDesignationFr(document.getDocumentType().getDesignationFr())
                   .documentTypeScope(document.getDocumentType().getScope())
                   .documentTypeDisplayText(buildDocumentTypeDisplayText(document.getDocumentType()));
        }

        // Handle File relationship (optional)
        if (document.getFile() != null) {
            builder.fileId(document.getFile().getId())
                   .fileName(getFileName(document.getFile()))
                   .fileExtension(document.getFile().getExtension())
                   .fileSize(document.getFile().getSize())
                   .fileDisplayText(buildFileDisplayText(document.getFile()));
        }

        DocumentDTO dto = builder.build();
        dto.setDisplayReference(buildDisplayReference(dto));
        dto.setFullDisplayText(buildFullDisplayText(dto));
        
        return dto;
    }

    public Document toEntity() {
        Document document = new Document();
        document.setId(this.id);
        document.setReference(this.reference);
        document.setIssueDate(this.issueDate);
        // Note: documentType and file must be set by service layer using IDs
        return document;
    }

    public void updateEntity(Document document) {
        if (this.reference != null) {
            document.setReference(this.reference);
        }
        if (this.issueDate != null) {
            document.setIssueDate(this.issueDate);
        }
        // Note: relationship updates must be handled by service layer using IDs
    }

    public String getDefaultReference() {
        if (reference != null && !reference.trim().isEmpty()) {
            return reference;
        }
        return "DOC-" + (id != null ? id : "NEW");
    }

    public String getFormattedIssueDate() {
        if (issueDate == null) return "No date";
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(issueDate);
    }

    public boolean hasFile() {
        return fileId != null;
    }

    public boolean isComplete() {
        return documentTypeId != null;
    }

    public Long getDocumentAgeInDays() {
        if (issueDate == null) return null;
        
        long diffInMillies = new Date().getTime() - issueDate.getTime();
        return diffInMillies / (1000 * 60 * 60 * 24);
    }

    public String getDocumentStatus() {
        if (hasFile()) {
            return "WITH_FILE";
        }
        return "METADATA_ONLY";
    }

    public static DocumentDTO createSimple(Long id, String reference, Date issueDate, 
                                         Long documentTypeId, Long fileId) {
        return DocumentDTO.builder()
                .id(id)
                .reference(reference)
                .issueDate(issueDate)
                .documentTypeId(documentTypeId)
                .fileId(fileId)
                .displayReference(reference != null && !reference.trim().isEmpty() ? 
                                reference : "DOC-" + id)
                .build();
    }

    // ========== HELPER METHODS ==========

    private static String buildDocumentTypeDisplayText(DocumentType documentType) {
        if (documentType.getDesignationFr() != null && !documentType.getDesignationFr().trim().isEmpty()) {
            return documentType.getDesignationFr();
        }
        if (documentType.getDesignationEn() != null && !documentType.getDesignationEn().trim().isEmpty()) {
            return documentType.getDesignationEn();
        }
        if (documentType.getDesignationAr() != null && !documentType.getDesignationAr().trim().isEmpty()) {
            return documentType.getDesignationAr();
        }
        return "DocumentType #" + documentType.getId();
    }

    private static String buildFileDisplayText(File file) {
        String extension = file.getExtension() != null ? file.getExtension() : "";
        String sizeText = formatFileSize(file.getSize());
        return String.format("File #%d (%s) - %s", file.getId(), extension.toUpperCase(), sizeText);
    }

    private static String getFileName(File file) {
        // Assuming file name is derived from ID and extension
        return "file_" + file.getId() + "." + (file.getExtension() != null ? file.getExtension() : "bin");
    }

    private static String formatFileSize(Long size) {
        if (size == null || size == 0) return "0 B";
        
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double fileSize = size.doubleValue();
        
        while (fileSize >= 1024 && unitIndex < units.length - 1) {
            fileSize /= 1024;
            unitIndex++;
        }
        
        return String.format("%.1f %s", fileSize, units[unitIndex]);
    }

    private static String buildDisplayReference(DocumentDTO dto) {
        if (dto.getReference() != null && !dto.getReference().trim().isEmpty()) {
            return dto.getReference();
        }
        return "DOC-" + (dto.getId() != null ? dto.getId() : "NEW");
    }

    private static String buildFullDisplayText(DocumentDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getDisplayReference());
        
        if (dto.getDocumentTypeDisplayText() != null) {
            sb.append(" (").append(dto.getDocumentTypeDisplayText()).append(")");
        }
        
        if (dto.getIssueDate() != null) {
            sb.append(" - ").append(dto.getFormattedIssueDate());
        }
        
        if (dto.hasFile()) {
            sb.append(" [With File]");
        }
        
        return sb.toString();
    }
}
