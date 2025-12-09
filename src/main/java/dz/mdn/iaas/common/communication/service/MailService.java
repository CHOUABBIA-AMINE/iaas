/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.service;

import dz.mdn.iaas.common.communication.model.Mail;
import dz.mdn.iaas.common.communication.model.MailNature;
import dz.mdn.iaas.common.communication.model.MailType;
import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.system.utility.model.File;
import dz.mdn.iaas.common.communication.repository.MailRepository;
import dz.mdn.iaas.common.communication.repository.MailNatureRepository;
import dz.mdn.iaas.common.communication.repository.MailTypeRepository;
import dz.mdn.iaas.common.administration.repository.StructureRepository;
import dz.mdn.iaas.system.utility.repository.FileRepository;
import dz.mdn.iaas.common.communication.dto.MailDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MailService {

    private final MailRepository mailRepository;
    private final MailNatureRepository mailNatureRepository;
    private final MailTypeRepository mailTypeRepository;
    private final StructureRepository structureRepository;
    private final FileRepository fileRepository;

    // ========== CREATE OPERATIONS ==========

    public MailDTO createMail(MailDTO mailDTO) {
        log.info("Creating mail with reference: {} for structure ID: {}", 
                mailDTO.getReference(), mailDTO.getStructureId());

        // Validate unique constraint on reference if provided
        if (mailDTO.getReference() != null && !mailDTO.getReference().trim().isEmpty()) {
            if (mailRepository.existsByReference(mailDTO.getReference())) {
                throw new RuntimeException("Mail with reference '" + mailDTO.getReference() + "' already exists");
            }
        }

        // Validate required relationships
        MailNature mailNature = validateAndGetMailNature(mailDTO.getMailNatureId());
        MailType mailType = validateAndGetMailType(mailDTO.getMailTypeId());
        Structure structure = validateAndGetStructure(mailDTO.getStructureId());
        File file = validateAndGetFile(mailDTO.getFileId());

        // Create entity with exact field mapping
        Mail mail = new Mail();
        mail.setReference(mailDTO.getReference()); // F_01
        mail.setRecordNumber(mailDTO.getRecordNumber()); // F_02
        mail.setSubject(mailDTO.getSubject()); // F_03
        mail.setMailDate(mailDTO.getMailDate() != null ? mailDTO.getMailDate() : new Date()); // F_04
        mail.setRecordDate(mailDTO.getRecordDate()); // F_05
        mail.setMailNature(mailNature); // F_06
        mail.setMailType(mailType); // F_07
        mail.setStructure(structure); // F_08
        mail.setFile(file); // F_09

        // Handle ManyToMany relationship with referencedMails
        if (mailDTO.getReferencedMailIds() != null && !mailDTO.getReferencedMailIds().isEmpty()) {
            List<Mail> referencedMails = mailRepository.findAllById(mailDTO.getReferencedMailIds());
            mail.setReferencedMails(referencedMails);
        }

        Mail savedMail = mailRepository.save(mail);
        log.info("Successfully created mail with ID: {}", savedMail.getId());

        return MailDTO.fromEntity(savedMail);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public MailDTO getMailById(Long id) {
        log.debug("Getting mail with ID: {}", id);

        Mail mail = mailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mail not found with ID: " + id));

        return MailDTO.fromEntity(mail);
    }

    @Transactional(readOnly = true)
    public Mail getMailEntityById(Long id) {
        return mailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mail not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<MailDTO> findByReference(String reference) {
        log.debug("Finding mail with reference: {}", reference);

        return mailRepository.findByReference(reference)
                .map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<MailDTO> findByRecordNumber(String recordNumber) {
        log.debug("Finding mail with record number: {}", recordNumber);

        return mailRepository.findByRecordNumber(recordNumber)
                .map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<MailDTO> findByFileId(Long fileId) {
        log.debug("Finding mail with file ID: {}", fileId);

        return mailRepository.findByFileId(fileId)
                .map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getAllMails(Pageable pageable) {
        log.debug("Getting all mails with pagination");

        Page<Mail> mails = mailRepository.findAllWithRelationships(pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<MailDTO> findOne(Long id) {
        log.debug("Finding mail by ID: {}", id);

        return mailRepository.findById(id)
                .map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getMailsByMailNatureId(Long mailNatureId, Pageable pageable) {
        log.debug("Getting mails for mail nature ID: {}", mailNatureId);

        validateMailNatureExists(mailNatureId);
        Page<Mail> mails = mailRepository.findByMailNatureId(mailNatureId, pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getMailsByMailTypeId(Long mailTypeId, Pageable pageable) {
        log.debug("Getting mails for mail type ID: {}", mailTypeId);

        validateMailTypeExists(mailTypeId);
        Page<Mail> mails = mailRepository.findByMailTypeId(mailTypeId, pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getMailsByStructureId(Long structureId, Pageable pageable) {
        log.debug("Getting mails for structure ID: {}", structureId);

        validateStructureExists(structureId);
        Page<Mail> mails = mailRepository.findByStructureId(structureId, pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> searchMails(String searchTerm, Pageable pageable) {
        log.debug("Searching mails with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllMails(pageable);
        }

        Page<Mail> mails = mailRepository.searchByReferenceSubjectOrRecordNumber(searchTerm.trim(), pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getMailsByDateRange(Date startDate, Date endDate, Pageable pageable) {
        log.debug("Getting mails between {} and {}", startDate, endDate);

        Page<Mail> mails = mailRepository.findByMailDateBetween(startDate, endDate, pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getRecordedMails(Pageable pageable) {
        log.debug("Getting recorded mails");

        Page<Mail> mails = mailRepository.findRecordedMails(pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getUnrecordedMails(Pageable pageable) {
        log.debug("Getting unrecorded mails");

        Page<Mail> mails = mailRepository.findUnrecordedMails(pageable);
        return mails.map(MailDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailDTO> getRecentMails(int days, Pageable pageable) {
        log.debug("Getting mails from last {} days", days);

        Date cutoffDate = new Date(System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L));
        Page<Mail> mails = mailRepository.findRecentMails(cutoffDate, pageable);
        return mails.map(MailDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public MailDTO updateMail(Long id, MailDTO mailDTO) {
        log.info("Updating mail with ID: {}", id);

        Mail existingMail = getMailEntityById(id);

        // Validate unique constraint on reference if changed
        if (mailDTO.getReference() != null && 
            !mailDTO.getReference().equals(existingMail.getReference()) &&
            mailRepository.existsByReferenceAndIdNot(mailDTO.getReference(), id)) {
            throw new RuntimeException("Another mail with reference '" + mailDTO.getReference() + "' already exists");
        }

        // Validate required relationships
        MailNature mailNature = validateAndGetMailNature(mailDTO.getMailNatureId());
        MailType mailType = validateAndGetMailType(mailDTO.getMailTypeId());
        Structure structure = validateAndGetStructure(mailDTO.getStructureId());
        File file = validateAndGetFile(mailDTO.getFileId());

        // Update fields with exact field mapping
        existingMail.setReference(mailDTO.getReference()); // F_01
        existingMail.setRecordNumber(mailDTO.getRecordNumber()); // F_02
        existingMail.setSubject(mailDTO.getSubject()); // F_03
        existingMail.setMailDate(mailDTO.getMailDate()); // F_04
        existingMail.setRecordDate(mailDTO.getRecordDate()); // F_05
        existingMail.setMailNature(mailNature); // F_06
        existingMail.setMailType(mailType); // F_07
        existingMail.setStructure(structure); // F_08
        existingMail.setFile(file); // F_09

        // Update ManyToMany relationship with referencedMails
        if (mailDTO.getReferencedMailIds() != null) {
            List<Mail> referencedMails = mailRepository.findAllById(mailDTO.getReferencedMailIds());
            existingMail.setReferencedMails(referencedMails);
        }

        Mail updatedMail = mailRepository.save(existingMail);
        log.info("Successfully updated mail with ID: {}", id);

        return MailDTO.fromEntity(updatedMail);
    }

    public MailDTO recordMail(Long id, String recordNumber) {
        log.info("Recording mail ID: {} with record number: {}", id, recordNumber);

        Mail mail = getMailEntityById(id);
        mail.setRecordNumber(recordNumber); // F_02
        mail.setRecordDate(new Date()); // F_05

        Mail recordedMail = mailRepository.save(mail);
        log.info("Successfully recorded mail with ID: {}", id);

        return MailDTO.fromEntity(recordedMail);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteMail(Long id) {
        log.info("Deleting mail with ID: {}", id);

        Mail mail = getMailEntityById(id);
        mailRepository.delete(mail);

        log.info("Successfully deleted mail with ID: {}", id);
    }

    public void deleteMailById(Long id) {
        log.info("Deleting mail by ID: {}", id);

        if (!mailRepository.existsById(id)) {
            throw new RuntimeException("Mail not found with ID: " + id);
        }

        mailRepository.deleteById(id);
        log.info("Successfully deleted mail with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return mailRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByReference(String reference) {
        return mailRepository.existsByReference(reference);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return mailRepository.countAllMails();
    }

    @Transactional(readOnly = true)
    public Long getRecordedCount() {
        return mailRepository.countRecordedMails();
    }

    @Transactional(readOnly = true)
    public Long getUnrecordedCount() {
        return mailRepository.countUnrecordedMails();
    }

    // ========== VALIDATION METHODS ==========

    private MailNature validateAndGetMailNature(Long mailNatureId) {
        if (mailNatureId == null) {
            throw new RuntimeException("Mail nature ID is required");
        }
        return mailNatureRepository.findById(mailNatureId)
                .orElseThrow(() -> new RuntimeException("MailNature not found with ID: " + mailNatureId));
    }

    private MailType validateAndGetMailType(Long mailTypeId) {
        if (mailTypeId == null) {
            throw new RuntimeException("Mail type ID is required");
        }
        return mailTypeRepository.findById(mailTypeId)
                .orElseThrow(() -> new RuntimeException("MailType not found with ID: " + mailTypeId));
    }

    private Structure validateAndGetStructure(Long structureId) {
        if (structureId == null) {
            throw new RuntimeException("Structure ID is required");
        }
        return structureRepository.findById(structureId)
                .orElseThrow(() -> new RuntimeException("Structure not found with ID: " + structureId));
    }

    private File validateAndGetFile(Long fileId) {
        if (fileId == null) {
            throw new RuntimeException("File ID is required");
        }
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with ID: " + fileId));
    }

    private void validateMailNatureExists(Long mailNatureId) {
        if (!mailNatureRepository.existsById(mailNatureId)) {
            throw new RuntimeException("MailNature not found with ID: " + mailNatureId);
        }
    }

    private void validateMailTypeExists(Long mailTypeId) {
        if (!mailTypeRepository.existsById(mailTypeId)) {
            throw new RuntimeException("MailType not found with ID: " + mailTypeId);
        }
    }

    private void validateStructureExists(Long structureId) {
        if (!structureRepository.existsById(structureId)) {
            throw new RuntimeException("Structure not found with ID: " + structureId);
        }
    }
}
