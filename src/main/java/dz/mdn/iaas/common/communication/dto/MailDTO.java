/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.common.communication.model.Mail;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mail Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MailDTO extends GenericDTO<Mail> {

    @Size(max = 50, message = "Reference must not exceed 50 characters")
    private String reference;

    @Size(max = 50, message = "Record number must not exceed 50 characters")
    private String recordNumber;

    @Size(max = 500, message = "Subject must not exceed 500 characters")
    private String subject;

    private Date mailDate;

    private Date recordDate;

    @NotNull(message = "Mail nature ID is required")
    private Long mailNatureId;

    @NotNull(message = "Mail type ID is required")
    private Long mailTypeId;

    @NotNull(message = "Structure ID is required")
    private Long structureId;

    @NotNull(message = "File ID is required")
    private Long fileId;

    private List<Long> referencedMailIds;

    @Override
    public Mail toEntity() {
        Mail entity = new Mail();
        entity.setId(this.getId());
        entity.setReference(this.reference);
        entity.setRecordNumber(this.recordNumber);
        entity.setSubject(this.subject);
        entity.setMailDate(this.mailDate);
        entity.setRecordDate(this.recordDate);
        return entity;
    }

    @Override
    public void updateEntity(Mail entity) {
        if (this.reference != null) entity.setReference(this.reference);
        if (this.recordNumber != null) entity.setRecordNumber(this.recordNumber);
        if (this.subject != null) entity.setSubject(this.subject);
        if (this.mailDate != null) entity.setMailDate(this.mailDate);
        if (this.recordDate != null) entity.setRecordDate(this.recordDate);
    }

    public static MailDTO fromEntity(Mail entity) {
        if (entity == null) return null;
        return MailDTO.builder()
                .id(entity.getId())
                .reference(entity.getReference())
                .recordNumber(entity.getRecordNumber())
                .subject(entity.getSubject())
                .mailDate(entity.getMailDate())
                .recordDate(entity.getRecordDate())
                .mailNatureId(entity.getMailNature() != null ? entity.getMailNature().getId() : null)
                .mailTypeId(entity.getMailType() != null ? entity.getMailType().getId() : null)
                .structureId(entity.getStructure() != null ? entity.getStructure().getId() : null)
                .fileId(entity.getFile() != null ? entity.getFile().getId() : null)
                .referencedMailIds(entity.getReferencedMails() != null ? 
                        entity.getReferencedMails().stream().map(Mail::getId).collect(Collectors.toList()) : null)
                .build();
    }
}