/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStepService
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.service;

import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import dz.mdn.iaas.business.consultation.model.ConsultationPhase;
import dz.mdn.iaas.business.consultation.repository.ConsultationStepRepository;
import dz.mdn.iaas.business.consultation.repository.ConsultationPhaseRepository;
import dz.mdn.iaas.business.consultation.dto.ConsultationStepDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ConsultationStep Service with CRUD operations
 * Handles consultation step management operations with unique constraint and foreign key relationship
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=consultationPhase
 * Required fields: F_03 (designationFr) with unique constraint, F_04 (consultationPhase)
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsultationStepService {

    private final ConsultationStepRepository consultationStepRepository;
    private final ConsultationPhaseRepository consultationPhaseRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new consultation step
     */
    public ConsultationStepDTO createConsultationStep(ConsultationStepDTO consultationStepDTO) {
        log.info("Creating consultation step with French designation: {} for consultation phase ID: {}", 
                consultationStepDTO.getDesignationFr(), consultationStepDTO.getConsultationPhaseId());

        // Validate required fields
        validateRequiredFields(consultationStepDTO, "create");
        
        // Check for unique constraint violation
        validateUniqueConstraint(consultationStepDTO, null);
        
        // Validate consultation phase exists
        ConsultationPhase consultationPhase = validateAndGetConsultationPhase(consultationStepDTO.getConsultationPhaseId());

        // Create entity with exact field mapping
        ConsultationStep consultationStep = new ConsultationStep();
        consultationStep.setDesignationAr(consultationStepDTO.getDesignationAr()); // F_01
        consultationStep.setDesignationEn(consultationStepDTO.getDesignationEn()); // F_02
        consultationStep.setDesignationFr(consultationStepDTO.getDesignationFr()); // F_03 - required, unique
        consultationStep.setConsultationPhase(consultationPhase); // F_04 - required foreign key

        ConsultationStep savedConsultationStep = consultationStepRepository.save(consultationStep);
        log.info("Successfully created consultation step with ID: {}", savedConsultationStep.getId());
        
        return ConsultationStepDTO.fromEntity(savedConsultationStep);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get consultation step by ID
     */
    @Transactional(readOnly = true)
    public ConsultationStepDTO getConsultationStepById(Long id) {
        log.debug("Getting consultation step with ID: {}", id);
        ConsultationStep consultationStep = consultationStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ConsultationStep not found with ID: " + id));
        return ConsultationStepDTO.fromEntity(consultationStep);
    }

    /**
     * Get consultation step entity by ID
     */
    @Transactional(readOnly = true)
    public ConsultationStep getConsultationStepEntityById(Long id) {
        return consultationStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ConsultationStep not found with ID: " + id));
    }

    /**
     * Get consultation step by ID with consultation phase info
     */
    @Transactional(readOnly = true)
    public ConsultationStepDTO getConsultationStepWithPhase(Long id) {
        log.debug("Getting consultation step with phase for ID: {}", id);
        ConsultationStep consultationStep = consultationStepRepository.findByIdWithPhase(id)
                .orElseThrow(() -> new RuntimeException("ConsultationStep not found with ID: " + id));
        return ConsultationStepDTO.fromEntityWithPhase(consultationStep);
    }

    /**
     * Find consultation step by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ConsultationStepDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding consultation step with French designation: {}", designationFr);
        return consultationStepRepository.findByDesignationFr(designationFr)
                .map(ConsultationStepDTO::fromEntity);
    }

    /**
     * Get all consultation steps with pagination
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> getAllConsultationSteps(Pageable pageable) {
        log.debug("Getting all consultation steps with pagination");
        Page<ConsultationStep> consultationSteps = consultationStepRepository.findAllOrderByDesignationFr(pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntity);
    }

    /**
     * Get all consultation steps with consultation phase info
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> getAllConsultationStepsWithPhase(Pageable pageable) {
        log.debug("Getting all consultation steps with phase info");
        Page<ConsultationStep> consultationSteps = consultationStepRepository.findAllWithPhase(pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntityWithPhase);
    }

    /**
     * Find one consultation step by ID
     */
    @Transactional(readOnly = true)
    public Optional<ConsultationStepDTO> findOne(Long id) {
        log.debug("Finding consultation step by ID: {}", id);
        return consultationStepRepository.findById(id)
                .map(ConsultationStepDTO::fromEntity);
    }

    /**
     * Get consultation steps by consultation phase ID
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> getConsultationStepsByPhaseId(Long consultationPhaseId, Pageable pageable) {
        log.debug("Getting consultation steps for consultation phase ID: {}", consultationPhaseId);
        Page<ConsultationStep> consultationSteps = consultationStepRepository.findByConsultationPhaseId(consultationPhaseId, pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntity);
    }

    /**
     * Search consultation steps by any field
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> searchConsultationSteps(String searchTerm, Pageable pageable) {
        log.debug("Searching consultation steps with term: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllConsultationSteps(pageable);
        }
        Page<ConsultationStep> consultationSteps = consultationStepRepository.searchByAnyField(searchTerm.trim(), pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntity);
    }

    /**
     * Search consultation steps by designation
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching consultation steps by designation: {}", designation);
        Page<ConsultationStep> consultationSteps = consultationStepRepository.searchByDesignation(designation, pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntity);
    }

    /**
     * Get consultation steps by type
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> getConsultationStepsByType(String stepType, Pageable pageable) {
        log.debug("Getting consultation steps by type: {}", stepType);
        Page<ConsultationStep> consultationSteps = consultationStepRepository.findByStepType(stepType, pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update consultation step
     */
    public ConsultationStepDTO updateConsultationStep(Long id, ConsultationStepDTO consultationStepDTO) {
        log.info("Updating consultation step with ID: {}", id);
        ConsultationStep existingConsultationStep = getConsultationStepEntityById(id);

        // Validate required fields
        validateRequiredFields(consultationStepDTO, "update");
        
        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraint(consultationStepDTO, id);
        
        // Validate consultation phase if being changed
        ConsultationPhase consultationPhase = null;
        if (consultationStepDTO.getConsultationPhaseId() != null && 
            !consultationStepDTO.getConsultationPhaseId().equals(existingConsultationStep.getConsultationPhase().getId())) {
            consultationPhase = validateAndGetConsultationPhase(consultationStepDTO.getConsultationPhaseId());
        }

        // Update fields with exact field mapping
        existingConsultationStep.setDesignationAr(consultationStepDTO.getDesignationAr()); // F_01
        existingConsultationStep.setDesignationEn(consultationStepDTO.getDesignationEn()); // F_02
        existingConsultationStep.setDesignationFr(consultationStepDTO.getDesignationFr()); // F_03 - required, unique
        
        if (consultationPhase != null) {
            existingConsultationStep.setConsultationPhase(consultationPhase); // F_04 - required foreign key
        }

        ConsultationStep updatedConsultationStep = consultationStepRepository.save(existingConsultationStep);
        log.info("Successfully updated consultation step with ID: {}", id);
        
        return ConsultationStepDTO.fromEntity(updatedConsultationStep);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete consultation step
     */
    public void deleteConsultationStep(Long id) {
        log.info("Deleting consultation step with ID: {}", id);
        ConsultationStep consultationStep = getConsultationStepEntityById(id);
        
        consultationStepRepository.delete(consultationStep);
        log.info("Successfully deleted consultation step with ID: {}", id);
    }

    /**
     * Delete consultation step by ID (direct)
     */
    public void deleteConsultationStepById(Long id) {
        log.info("Deleting consultation step by ID: {}", id);
        if (!consultationStepRepository.existsById(id)) {
            throw new RuntimeException("ConsultationStep not found with ID: " + id);
        }
        
        consultationStepRepository.deleteById(id);
        log.info("Successfully deleted consultation step with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if consultation step exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return consultationStepRepository.existsById(id);
    }

    /**
     * Check if consultation step exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return consultationStepRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of consultation steps
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return consultationStepRepository.count();
    }

    /**
     * Get count of consultation steps by consultation phase
     */
    @Transactional(readOnly = true)
    public Long getCountByConsultationPhase(Long consultationPhaseId) {
        return consultationStepRepository.countByConsultationPhaseId(consultationPhaseId);
    }

    /**
     * Get administrative consultation steps
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> getAdministrativeSteps(Pageable pageable) {
        log.debug("Getting administrative consultation steps");
        Page<ConsultationStep> consultationSteps = consultationStepRepository.findAdministrativeSteps(pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntity);
    }

    /**
     * Get operational consultation steps
     */
    @Transactional(readOnly = true)
    public Page<ConsultationStepDTO> getOperationalSteps(Pageable pageable) {
        log.debug("Getting operational consultation steps");
        Page<ConsultationStep> consultationSteps = consultationStepRepository.findOperationalSteps(pageable);
        return consultationSteps.map(ConsultationStepDTO::fromEntity);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ConsultationStepDTO consultationStepDTO, String operation) {
        if (consultationStepDTO.getDesignationFr() == null || consultationStepDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (consultationStepDTO.getConsultationPhaseId() == null) {
            throw new RuntimeException("Consultation phase is required for " + operation);
        }
    }

    /**
     * Validate unique constraint
     */
    private void validateUniqueConstraint(ConsultationStepDTO consultationStepDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (consultationStepRepository.existsByDesignationFr(consultationStepDTO.getDesignationFr())) {
                throw new RuntimeException("ConsultationStep with French designation '" + consultationStepDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (consultationStepRepository.existsByDesignationFrAndIdNot(consultationStepDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another consultation step with French designation '" + consultationStepDTO.getDesignationFr() + "' already exists");
            }
        }
    }

    /**
     * Validate and get consultation phase
     */
    private ConsultationPhase validateAndGetConsultationPhase(Long consultationPhaseId) {
        return consultationPhaseRepository.findById(consultationPhaseId)
                .orElseThrow(() -> new RuntimeException("ConsultationPhase not found with ID: " + consultationPhaseId));
    }
}