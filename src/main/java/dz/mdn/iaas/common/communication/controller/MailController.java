/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.controller;

import dz.mdn.iaas.common.communication.service.MailService;
import dz.mdn.iaas.common.communication.dto.MailDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    // ========== POST ONE MAIL ==========

    @PostMapping
    public ResponseEntity<MailDTO> createMail(@Valid @RequestBody MailDTO mailDTO) {
        log.info("Creating mail with reference: {} for structure ID: {}", 
                mailDTO.getReference(), mailDTO.getStructureId());
        
        MailDTO createdMail = mailService.createMail(mailDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMail);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<MailDTO> getMailMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for mail ID: {}", id);
        
        MailDTO mailMetadata = mailService.getMailById(id);
        
        return ResponseEntity.ok(mailMetadata);
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<MailDTO> getMailByReference(@PathVariable String reference) {
        log.debug("Getting mail by reference: {}", reference);
        
        return mailService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/record-number/{recordNumber}")
    public ResponseEntity<MailDTO> getMailByRecordNumber(@PathVariable String recordNumber) {
        log.debug("Getting mail by record number: {}", recordNumber);
        
        return mailService.findByRecordNumber(recordNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<MailDTO> getMailByFileId(@PathVariable Long fileId) {
        log.debug("Getting mail by file ID: {}", fileId);
        
        return mailService.findByFileId(fileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMail(@PathVariable Long id) {
        log.info("Deleting mail with ID: {}", id);
        
        mailService.deleteMail(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<MailDTO>> getAllMails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "mailDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting all mails - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.getAllMails(pageable);
        
        return ResponseEntity.ok(mails);
    }

    // ========== RELATIONSHIP-BASED ENDPOINTS ==========

    @GetMapping("/by-mail-nature/{mailNatureId}")
    public ResponseEntity<Page<MailDTO>> getMailsByMailNature(
            @PathVariable Long mailNatureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "mailDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting mails for mail nature ID: {} - page: {}, size: {}", mailNatureId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.getMailsByMailNatureId(mailNatureId, pageable);
        
        return ResponseEntity.ok(mails);
    }

    @GetMapping("/by-mail-type/{mailTypeId}")
    public ResponseEntity<Page<MailDTO>> getMailsByMailType(
            @PathVariable Long mailTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "mailDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting mails for mail type ID: {} - page: {}, size: {}", mailTypeId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.getMailsByMailTypeId(mailTypeId, pageable);
        
        return ResponseEntity.ok(mails);
    }

    @GetMapping("/by-structure/{structureId}")
    public ResponseEntity<Page<MailDTO>> getMailsByStructure(
            @PathVariable Long structureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "mailDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting mails for structure ID: {} - page: {}, size: {}", structureId, page, size);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.getMailsByStructureId(structureId, pageable);
        
        return ResponseEntity.ok(mails);
    }

    // ========== SEARCH ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<MailDTO>> searchMails(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "mailDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Searching mails with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.searchMails(query, pageable);
        
        return ResponseEntity.ok(mails);
    }

    // ========== DATE-BASED ENDPOINTS ==========

    @GetMapping("/by-date-range")
    public ResponseEntity<Page<MailDTO>> getMailsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "mailDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting mails between {} and {}", startDate, endDate);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.getMailsByDateRange(startDate, endDate, pageable);
        
        return ResponseEntity.ok(mails);
    }

    @GetMapping("/recent/{days}")
    public ResponseEntity<Page<MailDTO>> getRecentMails(
            @PathVariable int days,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting mails from last {} days", days);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "mailDate"));
        Page<MailDTO> mails = mailService.getRecentMails(days, pageable);
        
        return ResponseEntity.ok(mails);
    }

    // ========== RECORD STATUS ENDPOINTS ==========

    @GetMapping("/recorded")
    public ResponseEntity<Page<MailDTO>> getRecordedMails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "recordDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting recorded mails");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.getRecordedMails(pageable);
        
        return ResponseEntity.ok(mails);
    }

    @GetMapping("/unrecorded")
    public ResponseEntity<Page<MailDTO>> getUnrecordedMails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "mailDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting unrecorded mails");
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailDTO> mails = mailService.getUnrecordedMails(pageable);
        
        return ResponseEntity.ok(mails);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<MailDTO> updateMail(
            @PathVariable Long id,
            @Valid @RequestBody MailDTO mailDTO) {
        
        log.info("Updating mail with ID: {}", id);
        
        MailDTO updatedMail = mailService.updateMail(id, mailDTO);
        
        return ResponseEntity.ok(updatedMail);
    }

    @PostMapping("/{id}/record")
    public ResponseEntity<MailDTO> recordMail(
            @PathVariable Long id,
            @RequestParam String recordNumber) {
        
        log.info("Recording mail ID: {} with record number: {}", id, recordNumber);
        
        MailDTO recordedMail = mailService.recordMail(id, recordNumber);
        
        return ResponseEntity.ok(recordedMail);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkMailExists(@PathVariable Long id) {
        log.debug("Checking existence of mail ID: {}", id);
        
        boolean exists = mailService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/reference/{reference}")
    public ResponseEntity<Boolean> checkReferenceExists(@PathVariable String reference) {
        log.debug("Checking existence of reference: {}", reference);
        
        boolean exists = mailService.existsByReference(reference);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    @GetMapping("/count")
    public ResponseEntity<Long> getMailsCount() {
        log.debug("Getting total count of mails");
        
        Long count = mailService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/recorded")
    public ResponseEntity<Long> getRecordedMailsCount() {
        log.debug("Getting count of recorded mails");
        
        Long count = mailService.getRecordedCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/unrecorded")
    public ResponseEntity<Long> getUnrecordedMailsCount() {
        log.debug("Getting count of unrecorded mails");
        
        Long count = mailService.getUnrecordedCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<MailInfoResponse> getMailInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for mail ID: {}", id);
        
        try {
            return mailService.findOne(id)
                    .map(mailDTO -> {
                        MailInfoResponse response = MailInfoResponse.builder()
                                .mailMetadata(mailDTO)
                                .hasReference(mailDTO.getReference() != null && !mailDTO.getReference().trim().isEmpty())
                                .hasRecordNumber(mailDTO.getRecordNumber() != null && !mailDTO.getRecordNumber().trim().isEmpty())
                                .hasSubject(mailDTO.getSubject() != null && !mailDTO.getSubject().trim().isEmpty())
                                .hasMailDate(mailDTO.getMailDate() != null)
                                .hasRecordDate(mailDTO.getRecordDate() != null)
                                .hasReferencedMails(mailDTO.hasReferencedMails())
                                .isRecorded(mailDTO.isRecorded())
                                .isComplete(mailDTO.isComplete())
                                .displayReference(mailDTO.getDefaultReference())
                                .formattedMailDate(mailDTO.getFormattedMailDate())
                                .formattedRecordDate(mailDTO.getFormattedRecordDate())
                                .mailAgeInDays(mailDTO.getMailAgeInDays())
                                .recordAgeInDays(mailDTO.getRecordAgeInDays())
                                .mailStatus(mailDTO.getMailStatus())
                                .referencedMailsCount(mailDTO.getReferencedMailsCount())
                                .fullDisplayText(mailDTO.getFullDisplayText())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting mail info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class MailInfoResponse {
        private MailDTO mailMetadata;
        private Boolean hasReference;
        private Boolean hasRecordNumber;
        private Boolean hasSubject;
        private Boolean hasMailDate;
        private Boolean hasRecordDate;
        private Boolean hasReferencedMails;
        private Boolean isRecorded;
        private Boolean isComplete;
        private String displayReference;
        private String formattedMailDate;
        private String formattedRecordDate;
        private Long mailAgeInDays;
        private Long recordAgeInDays;
        private String mailStatus;
        private Integer referencedMailsCount;
        private String fullDisplayText;
    }
}
