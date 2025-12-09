/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailNatureService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.service;

import dz.mdn.iaas.common.communication.model.MailNature;
import dz.mdn.iaas.common.communication.repository.MailNatureRepository;
import dz.mdn.iaas.common.communication.dto.MailNatureDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MailNatureService {

    private final MailNatureRepository mailNatureRepository;

    // ========== CREATE OPERATIONS ==========

    public MailNatureDTO createMailNature(MailNatureDTO mailNatureDTO) {
        log.info("Creating mail nature with French designation: {}", mailNatureDTO.getDesignationFr());

        // Validate required field
        if (mailNatureDTO.getDesignationFr() == null || mailNatureDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required");
        }

        // Check for duplicate French designation (unique constraint)
        if (mailNatureRepository.existsByDesignationFr(mailNatureDTO.getDesignationFr())) {
            throw new RuntimeException("Mail nature with French designation '" + mailNatureDTO.getDesignationFr() + "' already exists");
        }

        // Create entity with exact field mapping
        MailNature mailNature = new MailNature();
        mailNature.setDesignationAr(mailNatureDTO.getDesignationAr()); // F_01
        mailNature.setDesignationEn(mailNatureDTO.getDesignationEn()); // F_02
        mailNature.setDesignationFr(mailNatureDTO.getDesignationFr()); // F_03

        MailNature savedMailNature = mailNatureRepository.save(mailNature);
        log.info("Successfully created mail nature with ID: {}", savedMailNature.getId());

        return MailNatureDTO.fromEntity(savedMailNature);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public MailNatureDTO getMailNatureById(Long id) {
        log.debug("Getting mail nature with ID: {}", id);

        MailNature mailNature = mailNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MailNature not found with ID: " + id));

        return MailNatureDTO.fromEntity(mailNature);
    }

    @Transactional(readOnly = true)
    public MailNature getMailNatureEntityById(Long id) {
        return mailNatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MailNature not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<MailNatureDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding mail nature with French designation: {}", designationFr);

        return mailNatureRepository.findByDesignationFr(designationFr)
                .map(MailNatureDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailNatureDTO> getAllMailNatures(Pageable pageable) {
        log.debug("Getting all mail natures with pagination");

        Page<MailNature> mailNatures = mailNatureRepository.findAllOrderByDesignationFr(pageable);
        return mailNatures.map(MailNatureDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<MailNatureDTO> findOne(Long id) {
        log.debug("Finding mail nature by ID: {}", id);

        return mailNatureRepository.findById(id)
                .map(MailNatureDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailNatureDTO> searchMailNatures(String searchTerm, Pageable pageable) {
        log.debug("Searching mail natures with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllMailNatures(pageable);
        }

        Page<MailNature> mailNatures = mailNatureRepository.searchByAnyDesignation(searchTerm.trim(), pageable);
        return mailNatures.map(MailNatureDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailNatureDTO> searchByDesignationAr(String designationAr, Pageable pageable) {
        log.debug("Searching mail natures by Arabic designation: {}", designationAr);

        Page<MailNature> mailNatures = mailNatureRepository.findByDesignationArContaining(designationAr, pageable);
        return mailNatures.map(MailNatureDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailNatureDTO> searchByDesignationEn(String designationEn, Pageable pageable) {
        log.debug("Searching mail natures by English designation: {}", designationEn);

        Page<MailNature> mailNatures = mailNatureRepository.findByDesignationEnContaining(designationEn, pageable);
        return mailNatures.map(MailNatureDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailNatureDTO> searchByDesignationFr(String designationFr, Pageable pageable) {
        log.debug("Searching mail natures by French designation: {}", designationFr);

        Page<MailNature> mailNatures = mailNatureRepository.findByDesignationFrContaining(designationFr, pageable);
        return mailNatures.map(MailNatureDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailNatureDTO> getMultilingualMailNatures(Pageable pageable) {
        log.debug("Getting multilingual mail natures");

        Page<MailNature> mailNatures = mailNatureRepository.findMultilingualMailNatures(pageable);
        return mailNatures.map(MailNatureDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public MailNatureDTO updateMailNature(Long id, MailNatureDTO mailNatureDTO) {
        log.info("Updating mail nature with ID: {}", id);

        MailNature existingMailNature = getMailNatureEntityById(id);

        // Validate required French designation
        if (mailNatureDTO.getDesignationFr() == null || mailNatureDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required");
        }

        // Check for duplicate French designation (excluding current record)
        if (mailNatureRepository.existsByDesignationFrAndIdNot(mailNatureDTO.getDesignationFr(), id)) {
            throw new RuntimeException("Another mail nature with French designation '" + mailNatureDTO.getDesignationFr() + "' already exists");
        }

        // Update fields with exact field mapping
        existingMailNature.setDesignationAr(mailNatureDTO.getDesignationAr()); // F_01
        existingMailNature.setDesignationEn(mailNatureDTO.getDesignationEn()); // F_02
        existingMailNature.setDesignationFr(mailNatureDTO.getDesignationFr()); // F_03

        MailNature updatedMailNature = mailNatureRepository.save(existingMailNature);
        log.info("Successfully updated mail nature with ID: {}", id);

        return MailNatureDTO.fromEntity(updatedMailNature);
    }

    public MailNatureDTO partialUpdateMailNature(Long id, MailNatureDTO mailNatureDTO) {
        log.info("Partially updating mail nature with ID: {}", id);

        MailNature existingMailNature = getMailNatureEntityById(id);

        // Update only non-null fields
        if (mailNatureDTO.getDesignationAr() != null) {
            existingMailNature.setDesignationAr(mailNatureDTO.getDesignationAr()); // F_01
        }

        if (mailNatureDTO.getDesignationEn() != null) {
            existingMailNature.setDesignationEn(mailNatureDTO.getDesignationEn()); // F_02
        }

        if (mailNatureDTO.getDesignationFr() != null) {
            // Validate required field
            if (mailNatureDTO.getDesignationFr().trim().isEmpty()) {
                throw new RuntimeException("French designation cannot be empty");
            }

            // Check for duplicate French designation (excluding current record)
            if (mailNatureRepository.existsByDesignationFrAndIdNot(mailNatureDTO.getDesignationFr(), id)) {
                throw new RuntimeException("Another mail nature with French designation '" + mailNatureDTO.getDesignationFr() + "' already exists");
            }

            existingMailNature.setDesignationFr(mailNatureDTO.getDesignationFr()); // F_03
        }

        MailNature updatedMailNature = mailNatureRepository.save(existingMailNature);
        log.info("Successfully partially updated mail nature with ID: {}", id);

        return MailNatureDTO.fromEntity(updatedMailNature);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteMailNature(Long id) {
        log.info("Deleting mail nature with ID: {}", id);

        MailNature mailNature = getMailNatureEntityById(id);
        mailNatureRepository.delete(mailNature);

        log.info("Successfully deleted mail nature with ID: {}", id);
    }

    public void deleteMailNatureById(Long id) {
        log.info("Deleting mail nature by ID: {}", id);

        if (!mailNatureRepository.existsById(id)) {
            throw new RuntimeException("MailNature not found with ID: " + id);
        }

        mailNatureRepository.deleteById(id);
        log.info("Successfully deleted mail nature with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return mailNatureRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return mailNatureRepository.existsByDesignationFr(designationFr);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return mailNatureRepository.countAllMailNatures();
    }
}
