/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStepService
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.service;

import dz.mdn.iaas.business.contract.model.ContractStep;
import dz.mdn.iaas.business.contract.model.ContractPhase;
import dz.mdn.iaas.business.contract.repository.ContractStepRepository;
import dz.mdn.iaas.business.contract.repository.ContractPhaseRepository;
import dz.mdn.iaas.business.contract.dto.ContractStepDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ContractStep Service with CRUD operations
 * Handles contract step management operations with unique constraint and foreign key relationship
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=contractPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (contractPhase)
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContractStepService {

    private final ContractStepRepository contractStepRepository;
    private final ContractPhaseRepository contractPhaseRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new contract step
     */
    public ContractStepDTO createContractStep(ContractStepDTO contractStepDTO) {
        log.info("Creating contract step with French designation: {} for contract phase ID: {}", 
                contractStepDTO.getDesignationFr(), contractStepDTO.getContractPhaseId());

        // Validate required fields
        validateRequiredFields(contractStepDTO, "create");
        
        // Check for unique constraint violation
        validateUniqueConstraint(contractStepDTO, null);
        
        // Validate contract phase exists
        ContractPhase contractPhase = validateAndGetContractPhase(contractStepDTO.getContractPhaseId());

        // Create entity with exact field mapping
        ContractStep contractStep = new ContractStep();
        contractStep.setDesignationAr(contractStepDTO.getDesignationAr()); // F_01
        contractStep.setDesignationEn(contractStepDTO.getDesignationEn()); // F_02
        contractStep.setDesignationFr(contractStepDTO.getDesignationFr()); // F_03 - required, unique
        contractStep.setContractPhase(contractPhase); // F_04 - required foreign key

        ContractStep savedContractStep = contractStepRepository.save(contractStep);
        log.info("Successfully created contract step with ID: {}", savedContractStep.getId());
        
        return ContractStepDTO.fromEntity(savedContractStep);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get contract step by ID
     */
    @Transactional(readOnly = true)
    public ContractStepDTO getContractStepById(Long id) {
        log.debug("Getting contract step with ID: {}", id);
        ContractStep contractStep = contractStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ContractStep not found with ID: " + id));
        return ContractStepDTO.fromEntity(contractStep);
    }

    /**
     * Get contract step entity by ID
     */
    @Transactional(readOnly = true)
    public ContractStep getContractStepEntityById(Long id) {
        return contractStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ContractStep not found with ID: " + id));
    }

    /**
     * Get contract step by ID with contract phase info
     */
    @Transactional(readOnly = true)
    public ContractStepDTO getContractStepWithPhase(Long id) {
        log.debug("Getting contract step with phase for ID: {}", id);
        ContractStep contractStep = contractStepRepository.findByIdWithPhase(id)
                .orElseThrow(() -> new RuntimeException("ContractStep not found with ID: " + id));
        return ContractStepDTO.fromEntityWithPhase(contractStep);
    }

    /**
     * Find contract step by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ContractStepDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding contract step with French designation: {}", designationFr);
        return contractStepRepository.findByDesignationFr(designationFr)
                .map(ContractStepDTO::fromEntity);
    }

    /**
     * Get all contract steps with pagination
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> getAllContractSteps(Pageable pageable) {
        log.debug("Getting all contract steps with pagination");
        Page<ContractStep> contractSteps = contractStepRepository.findAllOrderByDesignationFr(pageable);
        return contractSteps.map(ContractStepDTO::fromEntity);
    }

    /**
     * Get all contract steps with contract phase info
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> getAllContractStepsWithPhase(Pageable pageable) {
        log.debug("Getting all contract steps with phase info");
        Page<ContractStep> contractSteps = contractStepRepository.findAllWithPhase(pageable);
        return contractSteps.map(ContractStepDTO::fromEntityWithPhase);
    }

    /**
     * Find one contract step by ID
     */
    @Transactional(readOnly = true)
    public Optional<ContractStepDTO> findOne(Long id) {
        log.debug("Finding contract step by ID: {}", id);
        return contractStepRepository.findById(id)
                .map(ContractStepDTO::fromEntity);
    }

    /**
     * Get contract steps by contract phase ID
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> getContractStepsByPhaseId(Long contractPhaseId, Pageable pageable) {
        log.debug("Getting contract steps for contract phase ID: {}", contractPhaseId);
        Page<ContractStep> contractSteps = contractStepRepository.findByContractPhaseId(contractPhaseId, pageable);
        return contractSteps.map(ContractStepDTO::fromEntity);
    }

    /**
     * Search contract steps by any field
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> searchContractSteps(String searchTerm, Pageable pageable) {
        log.debug("Searching contract steps with term: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllContractSteps(pageable);
        }
        Page<ContractStep> contractSteps = contractStepRepository.searchByAnyField(searchTerm.trim(), pageable);
        return contractSteps.map(ContractStepDTO::fromEntity);
    }

    /**
     * Search contract steps by designation
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching contract steps by designation: {}", designation);
        Page<ContractStep> contractSteps = contractStepRepository.searchByDesignation(designation, pageable);
        return contractSteps.map(ContractStepDTO::fromEntity);
    }

    /**
     * Get contract steps by type
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> getContractStepsByType(String stepType, Pageable pageable) {
        log.debug("Getting contract steps by type: {}", stepType);
        Page<ContractStep> contractSteps = contractStepRepository.findByStepType(stepType, pageable);
        return contractSteps.map(ContractStepDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update contract step
     */
    public ContractStepDTO updateContractStep(Long id, ContractStepDTO contractStepDTO) {
        log.info("Updating contract step with ID: {}", id);
        ContractStep existingContractStep = getContractStepEntityById(id);

        // Validate required fields
        validateRequiredFields(contractStepDTO, "update");
        
        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraint(contractStepDTO, id);
        
        // Validate contract phase if being changed
        ContractPhase contractPhase = null;
        if (contractStepDTO.getContractPhaseId() != null && 
            !contractStepDTO.getContractPhaseId().equals(existingContractStep.getContractPhase().getId())) {
            contractPhase = validateAndGetContractPhase(contractStepDTO.getContractPhaseId());
        }

        // Update fields with exact field mapping
        existingContractStep.setDesignationAr(contractStepDTO.getDesignationAr()); // F_01
        existingContractStep.setDesignationEn(contractStepDTO.getDesignationEn()); // F_02
        existingContractStep.setDesignationFr(contractStepDTO.getDesignationFr()); // F_03 - required, unique
        
        if (contractPhase != null) {
            existingContractStep.setContractPhase(contractPhase); // F_04 - required foreign key
        }

        ContractStep updatedContractStep = contractStepRepository.save(existingContractStep);
        log.info("Successfully updated contract step with ID: {}", id);
        
        return ContractStepDTO.fromEntity(updatedContractStep);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete contract step
     */
    public void deleteContractStep(Long id) {
        log.info("Deleting contract step with ID: {}", id);
        ContractStep contractStep = getContractStepEntityById(id);
        
        contractStepRepository.delete(contractStep);
        log.info("Successfully deleted contract step with ID: {}", id);
    }

    /**
     * Delete contract step by ID (direct)
     */
    public void deleteContractStepById(Long id) {
        log.info("Deleting contract step by ID: {}", id);
        if (!contractStepRepository.existsById(id)) {
            throw new RuntimeException("ContractStep not found with ID: " + id);
        }
        
        contractStepRepository.deleteById(id);
        log.info("Successfully deleted contract step with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if contract step exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return contractStepRepository.existsById(id);
    }

    /**
     * Check if contract step exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return contractStepRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of contract steps
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return contractStepRepository.count();
    }

    /**
     * Get count of contract steps by contract phase
     */
    @Transactional(readOnly = true)
    public Long getCountByContractPhase(Long contractPhaseId) {
        return contractStepRepository.countByContractPhaseId(contractPhaseId);
    }

    /**
     * Get administrative contract steps
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> getAdministrativeSteps(Pageable pageable) {
        log.debug("Getting administrative contract steps");
        Page<ContractStep> contractSteps = contractStepRepository.findAdministrativeSteps(pageable);
        return contractSteps.map(ContractStepDTO::fromEntity);
    }

    /**
     * Get operational contract steps
     */
    @Transactional(readOnly = true)
    public Page<ContractStepDTO> getOperationalSteps(Pageable pageable) {
        log.debug("Getting operational contract steps");
        Page<ContractStep> contractSteps = contractStepRepository.findOperationalSteps(pageable);
        return contractSteps.map(ContractStepDTO::fromEntity);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ContractStepDTO contractStepDTO, String operation) {
        if (contractStepDTO.getDesignationFr() == null || contractStepDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (contractStepDTO.getContractPhaseId() == null) {
            throw new RuntimeException("Contract phase is required for " + operation);
        }
    }

    /**
     * Validate unique constraint
     */
    private void validateUniqueConstraint(ContractStepDTO contractStepDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (contractStepRepository.existsByDesignationFr(contractStepDTO.getDesignationFr())) {
                throw new RuntimeException("ContractStep with French designation '" + contractStepDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (contractStepRepository.existsByDesignationFrAndIdNot(contractStepDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another contract step with French designation '" + contractStepDTO.getDesignationFr() + "' already exists");
            }
        }
    }

    /**
     * Validate and get contract phase
     */
    private ContractPhase validateAndGetContractPhase(Long contractPhaseId) {
        return contractPhaseRepository.findById(contractPhaseId)
                .orElseThrow(() -> new RuntimeException("ContractPhase not found with ID: " + contractPhaseId));
    }
}