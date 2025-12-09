/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailTypeService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.service;

import dz.mdn.iaas.common.communication.model.MailType;
import dz.mdn.iaas.common.communication.repository.MailTypeRepository;
import dz.mdn.iaas.common.communication.dto.MailTypeDTO;

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
public class MailTypeService {

    private final MailTypeRepository mailTypeRepository;

    // ========== CREATE OPERATIONS ==========

    public MailTypeDTO createMailType(MailTypeDTO mailTypeDTO) {
        log.info("Creating mail type with French designation: {}", mailTypeDTO.getDesignationFr());

        // Validate required field
        if (mailTypeDTO.getDesignationFr() == null || mailTypeDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required");
        }

        // Check for duplicate French designation (unique constraint)
        if (mailTypeRepository.existsByDesignationFr(mailTypeDTO.getDesignationFr())) {
            throw new RuntimeException("Mail type with French designation '" + mailTypeDTO.getDesignationFr() + "' already exists");
        }

        // Create entity with exact field mapping
        MailType mailType = new MailType();
        mailType.setDesignationAr(mailTypeDTO.getDesignationAr()); // F_01
        mailType.setDesignationEn(mailTypeDTO.getDesignationEn()); // F_02
        mailType.setDesignationFr(mailTypeDTO.getDesignationFr()); // F_03

        MailType savedMailType = mailTypeRepository.save(mailType);
        log.info("Successfully created mail type with ID: {}", savedMailType.getId());

        return MailTypeDTO.fromEntity(savedMailType);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public MailTypeDTO getMailTypeById(Long id) {
        log.debug("Getting mail type with ID: {}", id);

        MailType mailType = mailTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MailType not found with ID: " + id));

        return MailTypeDTO.fromEntity(mailType);
    }

    @Transactional(readOnly = true)
    public MailType getMailTypeEntityById(Long id) {
        return mailTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MailType not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<MailTypeDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding mail type with French designation: {}", designationFr);

        return mailTypeRepository.findByDesignationFr(designationFr)
                .map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> getAllMailTypes(Pageable pageable) {
        log.debug("Getting all mail types with pagination");

        Page<MailType> mailTypes = mailTypeRepository.findAllOrderByDesignationFr(pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<MailTypeDTO> findOne(Long id) {
        log.debug("Finding mail type by ID: {}", id);

        return mailTypeRepository.findById(id)
                .map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> searchMailTypes(String searchTerm, Pageable pageable) {
        log.debug("Searching mail types with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllMailTypes(pageable);
        }

        Page<MailType> mailTypes = mailTypeRepository.searchByAnyDesignation(searchTerm.trim(), pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> searchByDesignationAr(String designationAr, Pageable pageable) {
        log.debug("Searching mail types by Arabic designation: {}", designationAr);

        Page<MailType> mailTypes = mailTypeRepository.findByDesignationArContaining(designationAr, pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> searchByDesignationEn(String designationEn, Pageable pageable) {
        log.debug("Searching mail types by English designation: {}", designationEn);

        Page<MailType> mailTypes = mailTypeRepository.findByDesignationEnContaining(designationEn, pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> searchByDesignationFr(String designationFr, Pageable pageable) {
        log.debug("Searching mail types by French designation: {}", designationFr);

        Page<MailType> mailTypes = mailTypeRepository.findByDesignationFrContaining(designationFr, pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> getMultilingualMailTypes(Pageable pageable) {
        log.debug("Getting multilingual mail types");

        Page<MailType> mailTypes = mailTypeRepository.findMultilingualMailTypes(pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> getIncomingMailTypes(Pageable pageable) {
        log.debug("Getting incoming mail types");

        Page<MailType> mailTypes = mailTypeRepository.findIncomingMailTypes(pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> getOutgoingMailTypes(Pageable pageable) {
        log.debug("Getting outgoing mail types");

        Page<MailType> mailTypes = mailTypeRepository.findOutgoingMailTypes(pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MailTypeDTO> getInternalMailTypes(Pageable pageable) {
        log.debug("Getting internal mail types");

        Page<MailType> mailTypes = mailTypeRepository.findInternalMailTypes(pageable);
        return mailTypes.map(MailTypeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public MailTypeDTO updateMailType(Long id, MailTypeDTO mailTypeDTO) {
        log.info("Updating mail type with ID: {}", id);

        MailType existingMailType = getMailTypeEntityById(id);

        // Validate required French designation
        if (mailTypeDTO.getDesignationFr() == null || mailTypeDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required");
        }

        // Check for duplicate French designation (excluding current record)
        if (mailTypeRepository.existsByDesignationFrAndIdNot(mailTypeDTO.getDesignationFr(), id)) {
            throw new RuntimeException("Another mail type with French designation '" + mailTypeDTO.getDesignationFr() + "' already exists");
        }

        // Update fields with exact field mapping
        existingMailType.setDesignationAr(mailTypeDTO.getDesignationAr()); // F_01
        existingMailType.setDesignationEn(mailTypeDTO.getDesignationEn()); // F_02
        existingMailType.setDesignationFr(mailTypeDTO.getDesignationFr()); // F_03

        MailType updatedMailType = mailTypeRepository.save(existingMailType);
        log.info("Successfully updated mail type with ID: {}", id);

        return MailTypeDTO.fromEntity(updatedMailType);
    }

    public MailTypeDTO partialUpdateMailType(Long id, MailTypeDTO mailTypeDTO) {
        log.info("Partially updating mail type with ID: {}", id);

        MailType existingMailType = getMailTypeEntityById(id);

        // Update only non-null fields
        if (mailTypeDTO.getDesignationAr() != null) {
            existingMailType.setDesignationAr(mailTypeDTO.getDesignationAr()); // F_01
        }

        if (mailTypeDTO.getDesignationEn() != null) {
            existingMailType.setDesignationEn(mailTypeDTO.getDesignationEn()); // F_02
        }

        if (mailTypeDTO.getDesignationFr() != null) {
            // Validate required field
            if (mailTypeDTO.getDesignationFr().trim().isEmpty()) {
                throw new RuntimeException("French designation cannot be empty");
            }

            // Check for duplicate French designation (excluding current record)
            if (mailTypeRepository.existsByDesignationFrAndIdNot(mailTypeDTO.getDesignationFr(), id)) {
                throw new RuntimeException("Another mail type with French designation '" + mailTypeDTO.getDesignationFr() + "' already exists");
            }

            existingMailType.setDesignationFr(mailTypeDTO.getDesignationFr()); // F_03
        }

        MailType updatedMailType = mailTypeRepository.save(existingMailType);
        log.info("Successfully partially updated mail type with ID: {}", id);

        return MailTypeDTO.fromEntity(updatedMailType);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteMailType(Long id) {
        log.info("Deleting mail type with ID: {}", id);

        MailType mailType = getMailTypeEntityById(id);
        mailTypeRepository.delete(mailType);

        log.info("Successfully deleted mail type with ID: {}", id);
    }

    public void deleteMailTypeById(Long id) {
        log.info("Deleting mail type by ID: {}", id);

        if (!mailTypeRepository.existsById(id)) {
            throw new RuntimeException("MailType not found with ID: " + id);
        }

        mailTypeRepository.deleteById(id);
        log.info("Successfully deleted mail type with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return mailTypeRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return mailTypeRepository.existsByDesignationFr(designationFr);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return mailTypeRepository.countAllMailTypes();
    }
}
