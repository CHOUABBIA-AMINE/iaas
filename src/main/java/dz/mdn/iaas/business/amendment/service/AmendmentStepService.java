/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepService
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.service;

import dz.mdn.iaas.business.amendment.model.AmendmentStep;
import dz.mdn.iaas.business.amendment.model.AmendmentPhase;
import dz.mdn.iaas.business.amendment.repository.AmendmentStepRepository;
import dz.mdn.iaas.business.amendment.repository.AmendmentPhaseRepository;
import dz.mdn.iaas.business.amendment.dto.AmendmentStepDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * AmendmentStep Service with CRUD operations
 * Handles amendment step management operations with unique constraint and foreign key relationship
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=amendmentPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (amendmentPhase)
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AmendmentStepService {

    private final AmendmentStepRepository amendmentStepRepository;
    private final AmendmentPhaseRepository amendmentPhaseRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new amendment step
     */
    public AmendmentStepDTO createAmendmentStep(AmendmentStepDTO amendmentStepDTO) {
        log.info("Creating amendment step with French designation: {} for amendment phase ID: {}", 
                amendmentStepDTO.getDesignationFr(), amendmentStepDTO.getAmendmentPhaseId());

        // Validate required fields
        validateRequiredFields(amendmentStepDTO, "create");
        
        // Check for unique constraint violation
        validateUniqueConstraint(amendmentStepDTO, null);
        
        // Validate amendment phase exists
        AmendmentPhase amendmentPhase = validateAndGetAmendmentPhase(amendmentStepDTO.getAmendmentPhaseId());

        // Create entity with exact field mapping
        AmendmentStep amendmentStep = new AmendmentStep();
        amendmentStep.setDesignationAr(amendmentStepDTO.getDesignationAr()); // F_01
        amendmentStep.setDesignationEn(amendmentStepDTO.getDesignationEn()); // F_02
        amendmentStep.setDesignationFr(amendmentStepDTO.getDesignationFr()); // F_03 - required, unique
        amendmentStep.setAmendmentPhase(amendmentPhase); // F_04 - required foreign key

        AmendmentStep savedAmendmentStep = amendmentStepRepository.save(amendmentStep);
        log.info("Successfully created amendment step with ID: {}", savedAmendmentStep.getId());
        
        return AmendmentStepDTO.fromEntity(savedAmendmentStep);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get amendment step by ID
     */
    @Transactional(readOnly = true)
    public AmendmentStepDTO getAmendmentStepById(Long id) {
        log.debug("Getting amendment step with ID: {}", id);
        AmendmentStep amendmentStep = amendmentStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AmendmentStep not found with ID: " + id));
        return AmendmentStepDTO.fromEntity(amendmentStep);
    }

    /**
     * Get amendment step entity by ID
     */
    @Transactional(readOnly = true)
    public AmendmentStep getAmendmentStepEntityById(Long id) {
        return amendmentStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AmendmentStep not found with ID: " + id));
    }

    /**
     * Get amendment step by ID with amendment phase info
     */
    @Transactional(readOnly = true)
    public AmendmentStepDTO getAmendmentStepWithPhase(Long id) {
        log.debug("Getting amendment step with phase for ID: {}", id);
        AmendmentStep amendmentStep = amendmentStepRepository.findByIdWithPhase(id)
                .orElseThrow(() -> new RuntimeException("AmendmentStep not found with ID: " + id));
        return AmendmentStepDTO.fromEntityWithPhase(amendmentStep);
    }

    /**
     * Find amendment step by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentStepDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding amendment step with French designation: {}", designationFr);
        return amendmentStepRepository.findByDesignationFr(designationFr)
                .map(AmendmentStepDTO::fromEntity);
    }

    /**
     * Get all amendment steps with pagination
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> getAllAmendmentSteps(Pageable pageable) {
        log.debug("Getting all amendment steps with pagination");
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.findAllOrderByDesignationFr(pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntity);
    }

    /**
     * Get all amendment steps with amendment phase info
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> getAllAmendmentStepsWithPhase(Pageable pageable) {
        log.debug("Getting all amendment steps with phase info");
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.findAllWithPhase(pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntityWithPhase);
    }

    /**
     * Find one amendment step by ID
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentStepDTO> findOne(Long id) {
        log.debug("Finding amendment step by ID: {}", id);
        return amendmentStepRepository.findById(id)
                .map(AmendmentStepDTO::fromEntity);
    }

    /**
     * Get amendment steps by amendment phase ID
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> getAmendmentStepsByPhaseId(Long amendmentPhaseId, Pageable pageable) {
        log.debug("Getting amendment steps for amendment phase ID: {}", amendmentPhaseId);
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.findByAmendmentPhaseId(amendmentPhaseId, pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntity);
    }

    /**
     * Search amendment steps by any field
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> searchAmendmentSteps(String searchTerm, Pageable pageable) {
        log.debug("Searching amendment steps with term: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllAmendmentSteps(pageable);
        }
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.searchByAnyField(searchTerm.trim(), pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntity);
    }

    /**
     * Search amendment steps by designation
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching amendment steps by designation: {}", designation);
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.searchByDesignation(designation, pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntity);
    }

    /**
     * Get amendment steps by type
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> getAmendmentStepsByType(String stepType, Pageable pageable) {
        log.debug("Getting amendment steps by type: {}", stepType);
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.findByStepType(stepType, pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update amendment step
     */
    public AmendmentStepDTO updateAmendmentStep(Long id, AmendmentStepDTO amendmentStepDTO) {
        log.info("Updating amendment step with ID: {}", id);
        AmendmentStep existingAmendmentStep = getAmendmentStepEntityById(id);

        // Validate required fields
        validateRequiredFields(amendmentStepDTO, "update");
        
        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraint(amendmentStepDTO, id);
        
        // Validate amendment phase if being changed
        AmendmentPhase amendmentPhase = null;
        if (amendmentStepDTO.getAmendmentPhaseId() != null && 
            !amendmentStepDTO.getAmendmentPhaseId().equals(existingAmendmentStep.getAmendmentPhase().getId())) {
            amendmentPhase = validateAndGetAmendmentPhase(amendmentStepDTO.getAmendmentPhaseId());
        }

        // Update fields with exact field mapping
        existingAmendmentStep.setDesignationAr(amendmentStepDTO.getDesignationAr()); // F_01
        existingAmendmentStep.setDesignationEn(amendmentStepDTO.getDesignationEn()); // F_02
        existingAmendmentStep.setDesignationFr(amendmentStepDTO.getDesignationFr()); // F_03 - required, unique
        
        if (amendmentPhase != null) {
            existingAmendmentStep.setAmendmentPhase(amendmentPhase); // F_04 - required foreign key
        }

        AmendmentStep updatedAmendmentStep = amendmentStepRepository.save(existingAmendmentStep);
        log.info("Successfully updated amendment step with ID: {}", id);
        
        return AmendmentStepDTO.fromEntity(updatedAmendmentStep);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete amendment step
     */
    public void deleteAmendmentStep(Long id) {
        log.info("Deleting amendment step with ID: {}", id);
        AmendmentStep amendmentStep = getAmendmentStepEntityById(id);
        
        amendmentStepRepository.delete(amendmentStep);
        log.info("Successfully deleted amendment step with ID: {}", id);
    }

    /**
     * Delete amendment step by ID (direct)
     */
    public void deleteAmendmentStepById(Long id) {
        log.info("Deleting amendment step by ID: {}", id);
        if (!amendmentStepRepository.existsById(id)) {
            throw new RuntimeException("AmendmentStep not found with ID: " + id);
        }
        
        amendmentStepRepository.deleteById(id);
        log.info("Successfully deleted amendment step with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if amendment step exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return amendmentStepRepository.existsById(id);
    }

    /**
     * Check if amendment step exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return amendmentStepRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of amendment steps
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return amendmentStepRepository.count();
    }

    /**
     * Get count of amendment steps by amendment phase
     */
    @Transactional(readOnly = true)
    public Long getCountByAmendmentPhase(Long amendmentPhaseId) {
        return amendmentStepRepository.countByAmendmentPhaseId(amendmentPhaseId);
    }

    /**
     * Get administrative amendment steps
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> getAdministrativeSteps(Pageable pageable) {
        log.debug("Getting administrative amendment steps");
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.findAdministrativeSteps(pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntity);
    }

    /**
     * Get operational amendment steps
     */
    @Transactional(readOnly = true)
    public Page<AmendmentStepDTO> getOperationalSteps(Pageable pageable) {
        log.debug("Getting operational amendment steps");
        Page<AmendmentStep> amendmentSteps = amendmentStepRepository.findOperationalSteps(pageable);
        return amendmentSteps.map(AmendmentStepDTO::fromEntity);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(AmendmentStepDTO amendmentStepDTO, String operation) {
        if (amendmentStepDTO.getDesignationFr() == null || amendmentStepDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (amendmentStepDTO.getAmendmentPhaseId() == null) {
            throw new RuntimeException("Amendment phase is required for " + operation);
        }
    }

    /**
     * Validate unique constraint
     */
    private void validateUniqueConstraint(AmendmentStepDTO amendmentStepDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (amendmentStepRepository.existsByDesignationFr(amendmentStepDTO.getDesignationFr())) {
                throw new RuntimeException("AmendmentStep with French designation '" + amendmentStepDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (amendmentStepRepository.existsByDesignationFrAndIdNot(amendmentStepDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another amendment step with French designation '" + amendmentStepDTO.getDesignationFr() + "' already exists");
            }
        }
    }

    /**
     * Validate and get amendment phase
     */
    private AmendmentPhase validateAndGetAmendmentPhase(Long amendmentPhaseId) {
        return amendmentPhaseRepository.findById(amendmentPhaseId)
                .orElseThrow(() -> new RuntimeException("AmendmentPhase not found with ID: " + amendmentPhaseId));
    }
}