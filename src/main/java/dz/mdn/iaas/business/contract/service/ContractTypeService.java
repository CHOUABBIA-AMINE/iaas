/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractTypeService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.contract.dto.ContractTypeDTO;
import dz.mdn.iaas.business.contract.model.ContractType;
import dz.mdn.iaas.business.contract.repository.ContractTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ContractType Service with CRUD operations
 * Handles approval status management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContractTypeService {

    private final ContractTypeRepository contractTypeRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new approval status
     */
    public ContractTypeDTO createContractType(ContractTypeDTO contractTypeDTO) {
        log.info("Creating approval status with French designation: {} and designations: AR={}, EN={}", 
                contractTypeDTO.getDesignationFr(), contractTypeDTO.getDesignationAr(), 
                contractTypeDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(contractTypeDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(contractTypeDTO, null);

        // Create entity with exact field mapping
        ContractType contractType = new ContractType();
        contractType.setDesignationAr(contractTypeDTO.getDesignationAr()); // F_01
        contractType.setDesignationEn(contractTypeDTO.getDesignationEn()); // F_02
        contractType.setDesignationFr(contractTypeDTO.getDesignationFr()); // F_03

        ContractType savedContractType = contractTypeRepository.save(contractType);
        log.info("Successfully created approval status with ID: {}", savedContractType.getId());

        return ContractTypeDTO.fromEntity(savedContractType);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get approval status by ID
     */
    @Transactional(readOnly = true)
    public ContractTypeDTO getContractTypeById(Long id) {
        log.debug("Getting approval status with ID: {}", id);

        ContractType contractType = contractTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval status not found with ID: " + id));

        return ContractTypeDTO.fromEntity(contractType);
    }

    /**
     * Get approval status entity by ID
     */
    @Transactional(readOnly = true)
    public ContractType getContractTypeEntityById(Long id) {
        return contractTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval status not found with ID: " + id));
    }

    /**
     * Find approval status by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ContractTypeDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding approval status with French designation: {}", designationFr);

        return contractTypeRepository.findByDesignationFr(designationFr)
                .map(ContractTypeDTO::fromEntity);
    }

    /**
     * Find approval status by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<ContractTypeDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding approval status with Arabic designation: {}", designationAr);

        return contractTypeRepository.findByDesignationAr(designationAr)
                .map(ContractTypeDTO::fromEntity);
    }

    /**
     * Find approval status by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<ContractTypeDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding approval status with English designation: {}", designationEn);

        return contractTypeRepository.findByDesignationEn(designationEn)
                .map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get all approval statuses with pagination
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getAllContractTypees(Pageable pageable) {
        log.debug("Getting all approval statuses with pagination");

        Page<ContractType> contractTypees = contractTypeRepository.findAllOrderByDesignationFr(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Find one approval status by ID
     */
    @Transactional(readOnly = true)
    public Optional<ContractTypeDTO> findOne(Long id) {
        log.debug("Finding approval status by ID: {}", id);

        return contractTypeRepository.findById(id)
                .map(ContractTypeDTO::fromEntity);
    }

    /**
     * Search approval statuses by designation
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> searchContractTypees(String searchTerm, Pageable pageable) {
        log.debug("Searching approval statuses with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllContractTypees(pageable);
        }

        Page<ContractType> contractTypees = contractTypeRepository.searchByDesignation(searchTerm.trim(), pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get multilingual approval statuses
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getMultilingualContractTypees(Pageable pageable) {
        log.debug("Getting multilingual approval statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findMultilingualContractTypees(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get approved statuses
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getApprovedStatuses(Pageable pageable) {
        log.debug("Getting approved statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findApprovedStatuses(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get rejected statuses
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getRejectedStatuses(Pageable pageable) {
        log.debug("Getting rejected statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findRejectedStatuses(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get pending statuses
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getPendingStatuses(Pageable pageable) {
        log.debug("Getting pending statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findPendingStatuses(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get draft statuses
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getDraftStatuses(Pageable pageable) {
        log.debug("Getting draft statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findDraftStatuses(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get review statuses
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getReviewStatuses(Pageable pageable) {
        log.debug("Getting review statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findReviewStatuses(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get final statuses (approved, rejected, cancelled)
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getFinalStatuses(Pageable pageable) {
        log.debug("Getting final statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findFinalStatuses(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    /**
     * Get non-final statuses (pending, draft, under review)
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> getNonFinalStatuses(Pageable pageable) {
        log.debug("Getting non-final statuses");

        Page<ContractType> contractTypees = contractTypeRepository.findNonFinalStatuses(pageable);
        return contractTypees.map(ContractTypeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update approval status
     */
    public ContractTypeDTO updateContractType(Long id, ContractTypeDTO contractTypeDTO) {
        log.info("Updating approval status with ID: {}", id);

        ContractType existingContractType = getContractTypeEntityById(id);

        // Validate required fields
        validateRequiredFields(contractTypeDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(contractTypeDTO, id);

        // Update fields with exact field mapping
        existingContractType.setDesignationAr(contractTypeDTO.getDesignationAr()); // F_01
        existingContractType.setDesignationEn(contractTypeDTO.getDesignationEn()); // F_02
        existingContractType.setDesignationFr(contractTypeDTO.getDesignationFr()); // F_03

        ContractType updatedContractType = contractTypeRepository.save(existingContractType);
        log.info("Successfully updated approval status with ID: {}", id);

        return ContractTypeDTO.fromEntity(updatedContractType);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete approval status
     */
    public void deleteContractType(Long id) {
        log.info("Deleting approval status with ID: {}", id);

        ContractType contractType = getContractTypeEntityById(id);
        contractTypeRepository.delete(contractType);

        log.info("Successfully deleted approval status with ID: {}", id);
    }

    /**
     * Delete approval status by ID (direct)
     */
    public void deleteContractTypeById(Long id) {
        log.info("Deleting approval status by ID: {}", id);

        if (!contractTypeRepository.existsById(id)) {
            throw new RuntimeException("Approval status not found with ID: " + id);
        }

        contractTypeRepository.deleteById(id);
        log.info("Successfully deleted approval status with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if approval status exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return contractTypeRepository.existsById(id);
    }

    /**
     * Check if approval status exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return contractTypeRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of approval statuses
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return contractTypeRepository.countAllContractTypees();
    }

    /**
     * Get count of approved statuses
     */
    @Transactional(readOnly = true)
    public Long getApprovedCount() {
        return contractTypeRepository.countApprovedStatuses();
    }

    /**
     * Get count of rejected statuses
     */
    @Transactional(readOnly = true)
    public Long getRejectedCount() {
        return contractTypeRepository.countRejectedStatuses();
    }

    /**
     * Get count of pending statuses
     */
    @Transactional(readOnly = true)
    public Long getPendingCount() {
        return contractTypeRepository.countPendingStatuses();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ContractTypeDTO contractTypeDTO, String operation) {
        if (contractTypeDTO.getDesignationFr() == null || contractTypeDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(ContractTypeDTO contractTypeDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (contractTypeRepository.existsByDesignationFr(contractTypeDTO.getDesignationFr())) {
                throw new RuntimeException("Approval status with French designation '" + contractTypeDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (contractTypeRepository.existsByDesignationFrAndIdNot(contractTypeDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another approval status with French designation '" + contractTypeDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}