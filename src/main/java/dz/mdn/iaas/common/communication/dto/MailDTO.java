/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailDTO
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.common.communication.model.Mail;
import dz.mdn.iaas.common.communication.model.MailType;
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
public class MailDTO {

    private Long id; // F_00

    @Size(max = 50, message = "Reference must not exceed 50 characters")
    private String reference; // F_01 - unique constraint

    @Size(max = 50, message = "Record number must not exceed 50 characters")
    private String recordNumber; // F_02 - nullable

    @Size(max = 500, message = "Subject must not exceed 500 characters")
    private String subject; // F_03 - optional

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mailDate; // F_04 - optional

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordDate; // F_05 - optional

    @NotNull(message = "Mail nature ID is required")
    private Long mailNatureId; // F_06 - foreign key to MailNature (required)

    @NotNull(message = "Mail type ID is required")
    private Long mailTypeId; // F_07 - foreign key to MailType (required)

    @NotNull(message = "Structure ID is required")
    private Long structureId; // F_08 - foreign key to Structure (required)

    @NotNull(message = "File ID is required")
    private Long fileId; // F_09 - foreign key to File (required)

    private List<Long> referencedMailIds; // ManyToMany relationship with Mail

    // Additional fields for display purposes (from related entities)
    private String mailNatureDesignationFr;
    private String mailNatureDesignationEn;
    private String mailNatureDesignationAr;

    private String mailTypeDesignationFr;
    private String mailTypeDesignationEn;
    private String mailTypeDesignationAr;
    private String mailTypeCategory;

    private String structureName;
    private String structureCode;

    private String fileName;
    private String fileExtension;
    private Long fileSize;

    // Combined display information
    private String displayReference;
    private String fullDisplayText;
    private String mailStatus;

    public static MailDTO fromEntity(Mail mail) {
        if (mail == null) return null;
        
        MailDTO.MailDTOBuilder builder = MailDTO.builder()
                .id(mail.getId())
                .reference(mail.getReference())
                .recordNumber(mail.getRecordNumber())
                .subject(mail.getSubject())
                .mailDate(mail.getMailDate())
                .recordDate(mail.getRecordDate());

        // Handle MailNature relationship (required)
        if (mail.getMailNature() != null) {
            builder.mailNatureId(mail.getMailNature().getId())
                   .mailNatureDesignationFr(mail.getMailNature().getDesignationFr())
                   .mailNatureDesignationEn(mail.getMailNature().getDesignationEn())
                   .mailNatureDesignationAr(mail.getMailNature().getDesignationAr());
        }

        // Handle MailType relationship (required)
        if (mail.getMailType() != null) {
            builder.mailTypeId(mail.getMailType().getId())
                   .mailTypeDesignationFr(mail.getMailType().getDesignationFr())
                   .mailTypeDesignationEn(mail.getMailType().getDesignationEn())
                   .mailTypeDesignationAr(mail.getMailType().getDesignationAr())
                   .mailTypeCategory(getMailTypeCategory(mail.getMailType()));
        }

        // Handle Structure relationship (required)
        if (mail.getStructure() != null) {
            builder.structureId(mail.getStructure().getId())
                   .structureName(getStructureName(mail.getStructure()))
                   .structureCode(getStructureCode(mail.getStructure()));
        }

        // Handle File relationship (required)
        if (mail.getFile() != null) {
            builder.fileId(mail.getFile().getId())
                   .fileName(getFileName(mail.getFile()))
                   .fileExtension(mail.getFile().getExtension())
                   .fileSize(mail.getFile().getSize());
        }

        // Handle ManyToMany relationship with referencedMails
        if (mail.getReferencedMails() != null && !mail.getReferencedMails().isEmpty()) {
            List<Long> referencedIds = mail.getReferencedMails().stream()
                    .map(refMail -> refMail.getId())
                    .toList();
            builder.referencedMailIds(referencedIds);
        }

        MailDTO dto = builder.build();
        dto.setDisplayReference(buildDisplayReference(dto));
        dto.setFullDisplayText(buildFullDisplayText(dto));
        dto.setMailStatus(buildMailStatus(dto));
        
        return dto;
    }

    public Mail toEntity() {
        Mail mail = new Mail();
        mail.setId(this.id);
        mail.setReference(this.reference);
        mail.setRecordNumber(this.recordNumber);
        mail.setSubject(this.subject);
        mail.setMailDate(this.mailDate);
        mail.setRecordDate(this.recordDate);
        // Note: relationships must be set by service layer using IDs
        return mail;
    }

    public void updateEntity(Mail mail) {
        if (this.reference != null) {
            mail.setReference(this.reference);
        }
        if (this.recordNumber != null) {
            mail.setRecordNumber(this.recordNumber);
        }
        if (this.subject != null) {
            mail.setSubject(this.subject);
        }
        if (this.mailDate != null) {
            mail.setMailDate(this.mailDate);
        }
        if (this.recordDate != null) {
            mail.setRecordDate(this.recordDate);
        }
        // Note: relationship updates must be handled by service layer using IDs
    }

    public String getDefaultReference() {
        if (reference != null && !reference.trim().isEmpty()) {
            return reference;
        }
        return "MAIL-" + (id != null ? id : "NEW");
    }

    public String getFormattedMailDate() {
        if (mailDate == null) return "No date";
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(mailDate);
    }

    public String getFormattedRecordDate() {
        if (recordDate == null) return "No date";
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(recordDate);
    }

    public boolean hasReferencedMails() {
        return referencedMailIds != null && !referencedMailIds.isEmpty();
    }

    public int getReferencedMailsCount() {
        return referencedMailIds != null ? referencedMailIds.size() : 0;
    }

    public boolean isComplete() {
        return mailNatureId != null && mailTypeId != null && 
               structureId != null && fileId != null;
    }

    public Long getMailAgeInDays() {
        if (mailDate == null) return null;
        
        long diffInMillies = new Date().getTime() - mailDate.getTime();
        return diffInMillies / (1000 * 60 * 60 * 24);
    }

    public Long getRecordAgeInDays() {
        if (recordDate == null) return null;
        
        long diffInMillies = new Date().getTime() - recordDate.getTime();
        return diffInMillies / (1000 * 60 * 60 * 24);
    }

    public boolean isRecorded() {
        return recordNumber != null && !recordNumber.trim().isEmpty() && recordDate != null;
    }

    public static MailDTO createSimple(Long id, String reference, String subject, Date mailDate, 
                                     Long mailNatureId, Long mailTypeId, Long structureId, Long fileId) {
        return MailDTO.builder()
                .id(id)
                .reference(reference)
                .subject(subject)
                .mailDate(mailDate)
                .mailNatureId(mailNatureId)
                .mailTypeId(mailTypeId)
                .structureId(structureId)
                .fileId(fileId)
                .displayReference(reference != null && !reference.trim().isEmpty() ? 
                                reference : "MAIL-" + id)
                .build();
    }

    // ========== HELPER METHODS ==========

    private static String getMailTypeCategory(MailType mailType) {
        if (mailType.getDesignationFr() == null) return "OTHER";
        
        String designation = mailType.getDesignationFr().toLowerCase();
        if (designation.contains("entrant") || designation.contains("incoming")) {
            return "INCOMING";
        } else if (designation.contains("sortant") || designation.contains("outgoing")) {
            return "OUTGOING";
        } else if (designation.contains("interne") || designation.contains("internal")) {
            return "INTERNAL";
        }
        return "OTHER";
    }

    private static String getStructureName(Structure structure) {
        // Assuming structure has name field - adjust based on actual Structure model
        return "Structure #" + structure.getId();
    }

    private static String getStructureCode(Structure structure) {
        // Assuming structure has code field - adjust based on actual Structure model
        return "STR-" + structure.getId();
    }

    private static String getFileName(File file) {
        return "file_" + file.getId() + "." + (file.getExtension() != null ? file.getExtension() : "bin");
    }

    private static String buildDisplayReference(MailDTO dto) {
        if (dto.getReference() != null && !dto.getReference().trim().isEmpty()) {
            return dto.getReference();
        }
        return "MAIL-" + (dto.getId() != null ? dto.getId() : "NEW");
    }

    private static String buildFullDisplayText(MailDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getDisplayReference());
        
        if (dto.getSubject() != null && !dto.getSubject().trim().isEmpty()) {
            sb.append(" - ").append(dto.getSubject().length() > 50 ? 
                     dto.getSubject().substring(0, 50) + "..." : dto.getSubject());
        }
        
        if (dto.getMailDate() != null) {
            sb.append(" (").append(dto.getFormattedMailDate()).append(")");
        }
        
        return sb.toString();
    }

    private static String buildMailStatus(MailDTO dto) {
        if (dto.isRecorded()) {
            return "RECORDED";
        } else if (dto.getMailDate() != null) {
            return "RECEIVED";
        }
        return "DRAFT";
    }
}
