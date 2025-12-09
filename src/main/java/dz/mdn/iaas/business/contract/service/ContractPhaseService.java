/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractPhaseService
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.service;

import dz.mdn.iaas.business.contract.model.ContractPhase;
import dz.mdn.iaas.business.contract.repository.ContractPhaseRepository;
import dz.mdn.iaas.business.contract.dto.ContractPhaseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ContractPhase Service with CRUD operations
 * Handles contract phase management operations with unique constraint
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * Required field: F_03 (designationFr) with unique constraint
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContractPhaseService {

    private final ContractPhaseRepository contractPhaseRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new contract phase
     */
    public ContractPhaseDTO createContractPhase(ContractPhaseDTO contractPhaseDTO) {
        log.info("Creating contract phase with French designation: {}", 
                contractPhaseDTO.getDesignationFr());

        // Validate required fields
        validateRequiredFields(contractPhaseDTO, "create");
        
        // Check for unique constraint violation
        validateUniqueConstraint(contractPhaseDTO, null);

        // Create entity with exact field mapping
        ContractPhase contractPhase = new ContractPhase();
        contractPhase.setDesignationAr(contractPhaseDTO.getDesignationAr()); // F_01
        contractPhase.setDesignationEn(contractPhaseDTO.getDesignationEn()); // F_02
        contractPhase.setDesignationFr(contractPhaseDTO.getDesignationFr()); // F_03 - required, unique

        ContractPhase savedContractPhase = contractPhaseRepository.save(contractPhase);
        log.info("Successfully created contract phase with ID: {}", savedContractPhase.getId());
        
        return ContractPhaseDTO.fromEntity(savedContractPhase);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get contract phase by ID
     */
    @Transactional(readOnly = true)
    public ContractPhaseDTO getContractPhaseById(Long id) {
        log.debug("Getting contract phase with ID: {}", id);
        ContractPhase contractPhase = contractPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ContractPhase not found with ID: " + id));
        return ContractPhaseDTO.fromEntity(contractPhase);
    }

    /**
     * Get contract phase entity by ID
     */
    @Transactional(readOnly = true)
    public ContractPhase getContractPhaseEntityById(Long id) {
        return contractPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ContractPhase not found with ID: " + id));
    }

    /**
     * Get contract phase by ID with contract steps info
     */
    @Transactional(readOnly = true)
    public ContractPhaseDTO getContractPhaseWithSteps(Long id) {
        log.debug("Getting contract phase with steps for ID: {}", id);
        ContractPhase contractPhase = contractPhaseRepository.findByIdWithSteps(id)
                .orElseThrow(() -> new RuntimeException("ContractPhase not found with ID: " + id));
        return ContractPhaseDTO.fromEntityWithSteps(contractPhase);
    }

    /**
     * Find contract phase by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ContractPhaseDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding contract phase with French designation: {}", designationFr);
        return contractPhaseRepository.findByDesignationFr(designationFr)
                .map(ContractPhaseDTO::fromEntity);
    }

    /**
     * Get all contract phases with pagination
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> getAllContractPhases(Pageable pageable) {
        log.debug("Getting all contract phases with pagination");
        Page<ContractPhase> contractPhases = contractPhaseRepository.findAllOrderByDesignationFr(pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntity);
    }

    /**
     * Get all contract phases with contract steps info
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> getAllContractPhasesWithSteps(Pageable pageable) {
        log.debug("Getting all contract phases with steps info");
        Page<ContractPhase> contractPhases = contractPhaseRepository.findAllWithStepsCount(pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntityWithSteps);
    }

    /**
     * Find one contract phase by ID
     */
    @Transactional(readOnly = true)
    public Optional<ContractPhaseDTO> findOne(Long id) {
        log.debug("Finding contract phase by ID: {}", id);
        return contractPhaseRepository.findById(id)
                .map(ContractPhaseDTO::fromEntity);
    }

    /**
     * Search contract phases by any field
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> searchContractPhases(String searchTerm, Pageable pageable) {
        log.debug("Searching contract phases with term: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllContractPhases(pageable);
        }
        Page<ContractPhase> contractPhases = contractPhaseRepository.searchByAnyField(searchTerm.trim(), pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntity);
    }

    /**
     * Search contract phases by designation
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching contract phases by designation: {}", designation);
        Page<ContractPhase> contractPhases = contractPhaseRepository.searchByDesignation(designation, pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntity);
    }

    /**
     * Get contract phases by type
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> getContractPhasesByType(String phaseType, Pageable pageable) {
        log.debug("Getting contract phases by type: {}", phaseType);
        Page<ContractPhase> contractPhases = contractPhaseRepository.findByPhaseType(phaseType, pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update contract phase
     */
    public ContractPhaseDTO updateContractPhase(Long id, ContractPhaseDTO contractPhaseDTO) {
        log.info("Updating contract phase with ID: {}", id);
        ContractPhase existingContractPhase = getContractPhaseEntityById(id);

        // Validate required fields
        validateRequiredFields(contractPhaseDTO, "update");
        
        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraint(contractPhaseDTO, id);

        // Update fields with exact field mapping
        existingContractPhase.setDesignationAr(contractPhaseDTO.getDesignationAr()); // F_01
        existingContractPhase.setDesignationEn(contractPhaseDTO.getDesignationEn()); // F_02
        existingContractPhase.setDesignationFr(contractPhaseDTO.getDesignationFr()); // F_03 - required, unique

        ContractPhase updatedContractPhase = contractPhaseRepository.save(existingContractPhase);
        log.info("Successfully updated contract phase with ID: {}", id);
        
        return ContractPhaseDTO.fromEntity(updatedContractPhase);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete contract phase
     */
    public void deleteContractPhase(Long id) {
        log.info("Deleting contract phase with ID: {}", id);
        ContractPhase contractPhase = getContractPhaseEntityById(id);
        
        // Check if contract phase is being used in contract steps
        if (contractPhaseRepository.hasContractSteps(id)) {
            throw new RuntimeException("Cannot delete contract phase as it has contract steps");
        }
        
        contractPhaseRepository.delete(contractPhase);
        log.info("Successfully deleted contract phase with ID: {}", id);
    }

    /**
     * Delete contract phase by ID (direct)
     */
    public void deleteContractPhaseById(Long id) {
        log.info("Deleting contract phase by ID: {}", id);
        if (!contractPhaseRepository.existsById(id)) {
            throw new RuntimeException("ContractPhase not found with ID: " + id);
        }
        
        // Check if contract phase is being used in contract steps
        if (contractPhaseRepository.hasContractSteps(id)) {
            throw new RuntimeException("Cannot delete contract phase as it has contract steps");
        }
        
        contractPhaseRepository.deleteById(id);
        log.info("Successfully deleted contract phase with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if contract phase exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return contractPhaseRepository.existsById(id);
    }

    /**
     * Check if contract phase exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return contractPhaseRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of contract phases
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return contractPhaseRepository.count();
    }

    /**
     * Get contract phases with steps
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> getContractPhasesWithSteps(Pageable pageable) {
        log.debug("Getting contract phases that have contract steps");
        Page<ContractPhase> contractPhases = contractPhaseRepository.findPhasesWithSteps(pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntityWithSteps);
    }

    /**
     * Get contract phases without steps
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> getContractPhasesWithoutSteps(Pageable pageable) {
        log.debug("Getting contract phases that have no contract steps");
        Page<ContractPhase> contractPhases = contractPhaseRepository.findPhasesWithoutSteps(pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntity);
    }

    /**
     * Get pre-award contract phases
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> getPreAwardPhases(Pageable pageable) {
        log.debug("Getting pre-award contract phases");
        Page<ContractPhase> contractPhases = contractPhaseRepository.findPreAwardPhases(pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntity);
    }

    /**
     * Get post-award contract phases
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> getPostAwardPhases(Pageable pageable) {
        log.debug("Getting post-award contract phases");
        Page<ContractPhase> contractPhases = contractPhaseRepository.findPostAwardPhases(pageable);
        return contractPhases.map(ContractPhaseDTO::fromEntity);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ContractPhaseDTO contractPhaseDTO, String operation) {
        if (contractPhaseDTO.getDesignationFr() == null || contractPhaseDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraint
     */
    private void validateUniqueConstraint(ContractPhaseDTO contractPhaseDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (contractPhaseRepository.existsByDesignationFr(contractPhaseDTO.getDesignationFr())) {
                throw new RuntimeException("ContractPhase with French designation '" + contractPhaseDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (contractPhaseRepository.existsByDesignationFrAndIdNot(contractPhaseDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another contract phase with French designation '" + contractPhaseDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}