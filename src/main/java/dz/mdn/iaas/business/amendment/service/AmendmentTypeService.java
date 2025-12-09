/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentTypeService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.amendment.dto.AmendmentTypeDTO;
import dz.mdn.iaas.business.amendment.model.AmendmentType;
import dz.mdn.iaas.business.amendment.repository.AmendmentTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AmendmentType Service with CRUD operations
 * Handles approval status management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AmendmentTypeService {

    private final AmendmentTypeRepository amendmentTypeRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new approval status
     */
    public AmendmentTypeDTO createAmendmentType(AmendmentTypeDTO amendmentTypeDTO) {
        log.info("Creating approval status with French designation: {} and designations: AR={}, EN={}", 
                amendmentTypeDTO.getDesignationFr(), amendmentTypeDTO.getDesignationAr(), 
                amendmentTypeDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(amendmentTypeDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(amendmentTypeDTO, null);

        // Create entity with exact field mapping
        AmendmentType amendmentType = new AmendmentType();
        amendmentType.setDesignationAr(amendmentTypeDTO.getDesignationAr()); // F_01
        amendmentType.setDesignationEn(amendmentTypeDTO.getDesignationEn()); // F_02
        amendmentType.setDesignationFr(amendmentTypeDTO.getDesignationFr()); // F_03

        AmendmentType savedAmendmentType = amendmentTypeRepository.save(amendmentType);
        log.info("Successfully created approval status with ID: {}", savedAmendmentType.getId());

        return AmendmentTypeDTO.fromEntity(savedAmendmentType);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get approval status by ID
     */
    @Transactional(readOnly = true)
    public AmendmentTypeDTO getAmendmentTypeById(Long id) {
        log.debug("Getting approval status with ID: {}", id);

        AmendmentType amendmentType = amendmentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval status not found with ID: " + id));

        return AmendmentTypeDTO.fromEntity(amendmentType);
    }

    /**
     * Get approval status entity by ID
     */
    @Transactional(readOnly = true)
    public AmendmentType getAmendmentTypeEntityById(Long id) {
        return amendmentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval status not found with ID: " + id));
    }

    /**
     * Find approval status by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentTypeDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding approval status with French designation: {}", designationFr);

        return amendmentTypeRepository.findByDesignationFr(designationFr)
                .map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Find approval status by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentTypeDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding approval status with Arabic designation: {}", designationAr);

        return amendmentTypeRepository.findByDesignationAr(designationAr)
                .map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Find approval status by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentTypeDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding approval status with English designation: {}", designationEn);

        return amendmentTypeRepository.findByDesignationEn(designationEn)
                .map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get all approval statuses with pagination
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getAllAmendmentTypees(Pageable pageable) {
        log.debug("Getting all approval statuses with pagination");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findAllOrderByDesignationFr(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Find one approval status by ID
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentTypeDTO> findOne(Long id) {
        log.debug("Finding approval status by ID: {}", id);

        return amendmentTypeRepository.findById(id)
                .map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Search approval statuses by designation
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> searchAmendmentTypees(String searchTerm, Pageable pageable) {
        log.debug("Searching approval statuses with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllAmendmentTypees(pageable);
        }

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.searchByDesignation(searchTerm.trim(), pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get multilingual approval statuses
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getMultilingualAmendmentTypees(Pageable pageable) {
        log.debug("Getting multilingual approval statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findMultilingualAmendmentTypees(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get approved statuses
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getApprovedStatuses(Pageable pageable) {
        log.debug("Getting approved statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findApprovedStatuses(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get rejected statuses
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getRejectedStatuses(Pageable pageable) {
        log.debug("Getting rejected statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findRejectedStatuses(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get pending statuses
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getPendingStatuses(Pageable pageable) {
        log.debug("Getting pending statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findPendingStatuses(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get draft statuses
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getDraftStatuses(Pageable pageable) {
        log.debug("Getting draft statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findDraftStatuses(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get review statuses
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getReviewStatuses(Pageable pageable) {
        log.debug("Getting review statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findReviewStatuses(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get final statuses (approved, rejected, cancelled)
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getFinalStatuses(Pageable pageable) {
        log.debug("Getting final statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findFinalStatuses(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    /**
     * Get non-final statuses (pending, draft, under review)
     */
    @Transactional(readOnly = true)
    public Page<AmendmentTypeDTO> getNonFinalStatuses(Pageable pageable) {
        log.debug("Getting non-final statuses");

        Page<AmendmentType> amendmentTypees = amendmentTypeRepository.findNonFinalStatuses(pageable);
        return amendmentTypees.map(AmendmentTypeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update approval status
     */
    public AmendmentTypeDTO updateAmendmentType(Long id, AmendmentTypeDTO amendmentTypeDTO) {
        log.info("Updating approval status with ID: {}", id);

        AmendmentType existingAmendmentType = getAmendmentTypeEntityById(id);

        // Validate required fields
        validateRequiredFields(amendmentTypeDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(amendmentTypeDTO, id);

        // Update fields with exact field mapping
        existingAmendmentType.setDesignationAr(amendmentTypeDTO.getDesignationAr()); // F_01
        existingAmendmentType.setDesignationEn(amendmentTypeDTO.getDesignationEn()); // F_02
        existingAmendmentType.setDesignationFr(amendmentTypeDTO.getDesignationFr()); // F_03

        AmendmentType updatedAmendmentType = amendmentTypeRepository.save(existingAmendmentType);
        log.info("Successfully updated approval status with ID: {}", id);

        return AmendmentTypeDTO.fromEntity(updatedAmendmentType);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete approval status
     */
    public void deleteAmendmentType(Long id) {
        log.info("Deleting approval status with ID: {}", id);

        AmendmentType amendmentType = getAmendmentTypeEntityById(id);
        amendmentTypeRepository.delete(amendmentType);

        log.info("Successfully deleted approval status with ID: {}", id);
    }

    /**
     * Delete approval status by ID (direct)
     */
    public void deleteAmendmentTypeById(Long id) {
        log.info("Deleting approval status by ID: {}", id);

        if (!amendmentTypeRepository.existsById(id)) {
            throw new RuntimeException("Approval status not found with ID: " + id);
        }

        amendmentTypeRepository.deleteById(id);
        log.info("Successfully deleted approval status with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if approval status exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return amendmentTypeRepository.existsById(id);
    }

    /**
     * Check if approval status exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return amendmentTypeRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of approval statuses
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return amendmentTypeRepository.countAllAmendmentTypees();
    }

    /**
     * Get count of approved statuses
     */
    @Transactional(readOnly = true)
    public Long getApprovedCount() {
        return amendmentTypeRepository.countApprovedStatuses();
    }

    /**
     * Get count of rejected statuses
     */
    @Transactional(readOnly = true)
    public Long getRejectedCount() {
        return amendmentTypeRepository.countRejectedStatuses();
    }

    /**
     * Get count of pending statuses
     */
    @Transactional(readOnly = true)
    public Long getPendingCount() {
        return amendmentTypeRepository.countPendingStatuses();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(AmendmentTypeDTO amendmentTypeDTO, String operation) {
        if (amendmentTypeDTO.getDesignationFr() == null || amendmentTypeDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(AmendmentTypeDTO amendmentTypeDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (amendmentTypeRepository.existsByDesignationFr(amendmentTypeDTO.getDesignationFr())) {
                throw new RuntimeException("Approval status with French designation '" + amendmentTypeDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (amendmentTypeRepository.existsByDesignationFrAndIdNot(amendmentTypeDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another approval status with French designation '" + amendmentTypeDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}