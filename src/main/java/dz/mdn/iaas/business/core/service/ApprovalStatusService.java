/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ApprovalStatusService
 *	@CreatedOn	: 10-16-2025
 *	@Type		: Service
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Service
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.ApprovalStatus;
import dz.mdn.iaas.business.core.repository.ApprovalStatusRepository;
import dz.mdn.iaas.business.core.dto.ApprovalStatusDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ApprovalStatus Service with CRUD operations
 * Handles approval status management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ApprovalStatusService {

    private final ApprovalStatusRepository approvalStatusRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new approval status
     */
    public ApprovalStatusDTO createApprovalStatus(ApprovalStatusDTO approvalStatusDTO) {
        log.info("Creating approval status with French designation: {} and designations: AR={}, EN={}", 
                approvalStatusDTO.getDesignationFr(), approvalStatusDTO.getDesignationAr(), 
                approvalStatusDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(approvalStatusDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(approvalStatusDTO, null);

        // Create entity with exact field mapping
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setDesignationAr(approvalStatusDTO.getDesignationAr()); // F_01
        approvalStatus.setDesignationEn(approvalStatusDTO.getDesignationEn()); // F_02
        approvalStatus.setDesignationFr(approvalStatusDTO.getDesignationFr()); // F_03

        ApprovalStatus savedApprovalStatus = approvalStatusRepository.save(approvalStatus);
        log.info("Successfully created approval status with ID: {}", savedApprovalStatus.getId());

        return ApprovalStatusDTO.fromEntity(savedApprovalStatus);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get approval status by ID
     */
    @Transactional(readOnly = true)
    public ApprovalStatusDTO getApprovalStatusById(Long id) {
        log.debug("Getting approval status with ID: {}", id);

        ApprovalStatus approvalStatus = approvalStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval status not found with ID: " + id));

        return ApprovalStatusDTO.fromEntity(approvalStatus);
    }

    /**
     * Get approval status entity by ID
     */
    @Transactional(readOnly = true)
    public ApprovalStatus getApprovalStatusEntityById(Long id) {
        return approvalStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval status not found with ID: " + id));
    }

    /**
     * Find approval status by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ApprovalStatusDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding approval status with French designation: {}", designationFr);

        return approvalStatusRepository.findByDesignationFr(designationFr)
                .map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Find approval status by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<ApprovalStatusDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding approval status with Arabic designation: {}", designationAr);

        return approvalStatusRepository.findByDesignationAr(designationAr)
                .map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Find approval status by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<ApprovalStatusDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding approval status with English designation: {}", designationEn);

        return approvalStatusRepository.findByDesignationEn(designationEn)
                .map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get all approval statuses with pagination
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getAllApprovalStatuses(Pageable pageable) {
        log.debug("Getting all approval statuses with pagination");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findAllOrderByDesignationFr(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Find one approval status by ID
     */
    @Transactional(readOnly = true)
    public Optional<ApprovalStatusDTO> findOne(Long id) {
        log.debug("Finding approval status by ID: {}", id);

        return approvalStatusRepository.findById(id)
                .map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Search approval statuses by designation
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> searchApprovalStatuses(String searchTerm, Pageable pageable) {
        log.debug("Searching approval statuses with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllApprovalStatuses(pageable);
        }

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.searchByDesignation(searchTerm.trim(), pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get multilingual approval statuses
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getMultilingualApprovalStatuses(Pageable pageable) {
        log.debug("Getting multilingual approval statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findMultilingualApprovalStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get approved statuses
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getApprovedStatuses(Pageable pageable) {
        log.debug("Getting approved statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findApprovedStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get rejected statuses
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getRejectedStatuses(Pageable pageable) {
        log.debug("Getting rejected statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findRejectedStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get pending statuses
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getPendingStatuses(Pageable pageable) {
        log.debug("Getting pending statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findPendingStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get draft statuses
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getDraftStatuses(Pageable pageable) {
        log.debug("Getting draft statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findDraftStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get review statuses
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getReviewStatuses(Pageable pageable) {
        log.debug("Getting review statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findReviewStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get final statuses (approved, rejected, cancelled)
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getFinalStatuses(Pageable pageable) {
        log.debug("Getting final statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findFinalStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    /**
     * Get non-final statuses (pending, draft, under review)
     */
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> getNonFinalStatuses(Pageable pageable) {
        log.debug("Getting non-final statuses");

        Page<ApprovalStatus> approvalStatuses = approvalStatusRepository.findNonFinalStatuses(pageable);
        return approvalStatuses.map(ApprovalStatusDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update approval status
     */
    public ApprovalStatusDTO updateApprovalStatus(Long id, ApprovalStatusDTO approvalStatusDTO) {
        log.info("Updating approval status with ID: {}", id);

        ApprovalStatus existingApprovalStatus = getApprovalStatusEntityById(id);

        // Validate required fields
        validateRequiredFields(approvalStatusDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(approvalStatusDTO, id);

        // Update fields with exact field mapping
        existingApprovalStatus.setDesignationAr(approvalStatusDTO.getDesignationAr()); // F_01
        existingApprovalStatus.setDesignationEn(approvalStatusDTO.getDesignationEn()); // F_02
        existingApprovalStatus.setDesignationFr(approvalStatusDTO.getDesignationFr()); // F_03

        ApprovalStatus updatedApprovalStatus = approvalStatusRepository.save(existingApprovalStatus);
        log.info("Successfully updated approval status with ID: {}", id);

        return ApprovalStatusDTO.fromEntity(updatedApprovalStatus);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete approval status
     */
    public void deleteApprovalStatus(Long id) {
        log.info("Deleting approval status with ID: {}", id);

        ApprovalStatus approvalStatus = getApprovalStatusEntityById(id);
        approvalStatusRepository.delete(approvalStatus);

        log.info("Successfully deleted approval status with ID: {}", id);
    }

    /**
     * Delete approval status by ID (direct)
     */
    public void deleteApprovalStatusById(Long id) {
        log.info("Deleting approval status by ID: {}", id);

        if (!approvalStatusRepository.existsById(id)) {
            throw new RuntimeException("Approval status not found with ID: " + id);
        }

        approvalStatusRepository.deleteById(id);
        log.info("Successfully deleted approval status with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if approval status exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return approvalStatusRepository.existsById(id);
    }

    /**
     * Check if approval status exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return approvalStatusRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of approval statuses
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return approvalStatusRepository.countAllApprovalStatuses();
    }

    /**
     * Get count of approved statuses
     */
    @Transactional(readOnly = true)
    public Long getApprovedCount() {
        return approvalStatusRepository.countApprovedStatuses();
    }

    /**
     * Get count of rejected statuses
     */
    @Transactional(readOnly = true)
    public Long getRejectedCount() {
        return approvalStatusRepository.countRejectedStatuses();
    }

    /**
     * Get count of pending statuses
     */
    @Transactional(readOnly = true)
    public Long getPendingCount() {
        return approvalStatusRepository.countPendingStatuses();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ApprovalStatusDTO approvalStatusDTO, String operation) {
        if (approvalStatusDTO.getDesignationFr() == null || approvalStatusDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ApprovalStatusDTO approvalStatusDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (approvalStatusRepository.existsByDesignationFr(approvalStatusDTO.getDesignationFr())) {
                throw new RuntimeException("Approval status with French designation '" + approvalStatusDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (approvalStatusRepository.existsByDesignationFrAndIdNot(approvalStatusDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another approval status with French designation '" + approvalStatusDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
