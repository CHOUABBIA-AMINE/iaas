/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationPhaseService
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.service;

import dz.mdn.iaas.business.consultation.model.ConsultationPhase;
import dz.mdn.iaas.business.consultation.repository.ConsultationPhaseRepository;
import dz.mdn.iaas.business.consultation.dto.ConsultationPhaseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * ConsultationPhase Service with CRUD operations
 * Handles consultation phase management operations with unique constraint
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * Required field: F_03 (designationFr) with unique constraint
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsultationPhaseService {

    private final ConsultationPhaseRepository consultationPhaseRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new consultation phase
     */
    public ConsultationPhaseDTO createConsultationPhase(ConsultationPhaseDTO consultationPhaseDTO) {
        log.info("Creating consultation phase with French designation: {}", 
                consultationPhaseDTO.getDesignationFr());

        // Validate required fields
        validateRequiredFields(consultationPhaseDTO, "create");
        
        // Check for unique constraint violation
        validateUniqueConstraint(consultationPhaseDTO, null);

        // Create entity with exact field mapping
        ConsultationPhase consultationPhase = new ConsultationPhase();
        consultationPhase.setDesignationAr(consultationPhaseDTO.getDesignationAr()); // F_01
        consultationPhase.setDesignationEn(consultationPhaseDTO.getDesignationEn()); // F_02
        consultationPhase.setDesignationFr(consultationPhaseDTO.getDesignationFr()); // F_03 - required, unique

        ConsultationPhase savedConsultationPhase = consultationPhaseRepository.save(consultationPhase);
        log.info("Successfully created consultation phase with ID: {}", savedConsultationPhase.getId());
        
        return ConsultationPhaseDTO.fromEntity(savedConsultationPhase);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get consultation phase by ID
     */
    @Transactional(readOnly = true)
    public ConsultationPhaseDTO getConsultationPhaseById(Long id) {
        log.debug("Getting consultation phase with ID: {}", id);
        ConsultationPhase consultationPhase = consultationPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ConsultationPhase not found with ID: " + id));
        return ConsultationPhaseDTO.fromEntity(consultationPhase);
    }

    /**
     * Get consultation phase entity by ID
     */
    @Transactional(readOnly = true)
    public ConsultationPhase getConsultationPhaseEntityById(Long id) {
        return consultationPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ConsultationPhase not found with ID: " + id));
    }

    /**
     * Get consultation phase by ID with consultation steps info
     */
    @Transactional(readOnly = true)
    public ConsultationPhaseDTO getConsultationPhaseWithSteps(Long id) {
        log.debug("Getting consultation phase with steps for ID: {}", id);
        ConsultationPhase consultationPhase = consultationPhaseRepository.findByIdWithSteps(id)
                .orElseThrow(() -> new RuntimeException("ConsultationPhase not found with ID: " + id));
        return ConsultationPhaseDTO.fromEntityWithSteps(consultationPhase);
    }

    /**
     * Find consultation phase by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<ConsultationPhaseDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding consultation phase with French designation: {}", designationFr);
        return consultationPhaseRepository.findByDesignationFr(designationFr)
                .map(ConsultationPhaseDTO::fromEntity);
    }

    /**
     * Get all consultation phases with pagination
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> getAllConsultationPhases(Pageable pageable) {
        log.debug("Getting all consultation phases with pagination");
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.findAllOrderByDesignationFr(pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntity);
    }

    /**
     * Get all consultation phases with consultation steps info
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> getAllConsultationPhasesWithSteps(Pageable pageable) {
        log.debug("Getting all consultation phases with steps info");
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.findAllWithStepsCount(pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntityWithSteps);
    }

    /**
     * Find one consultation phase by ID
     */
    @Transactional(readOnly = true)
    public Optional<ConsultationPhaseDTO> findOne(Long id) {
        log.debug("Finding consultation phase by ID: {}", id);
        return consultationPhaseRepository.findById(id)
                .map(ConsultationPhaseDTO::fromEntity);
    }

    /**
     * Search consultation phases by any field
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> searchConsultationPhases(String searchTerm, Pageable pageable) {
        log.debug("Searching consultation phases with term: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllConsultationPhases(pageable);
        }
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.searchByAnyField(searchTerm.trim(), pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntity);
    }

    /**
     * Search consultation phases by designation
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching consultation phases by designation: {}", designation);
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.searchByDesignation(designation, pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntity);
    }

    /**
     * Get consultation phases by type
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> getConsultationPhasesByType(String phaseType, Pageable pageable) {
        log.debug("Getting consultation phases by type: {}", phaseType);
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.findByPhaseType(phaseType, pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update consultation phase
     */
    public ConsultationPhaseDTO updateConsultationPhase(Long id, ConsultationPhaseDTO consultationPhaseDTO) {
        log.info("Updating consultation phase with ID: {}", id);
        ConsultationPhase existingConsultationPhase = getConsultationPhaseEntityById(id);

        // Validate required fields
        validateRequiredFields(consultationPhaseDTO, "update");
        
        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraint(consultationPhaseDTO, id);

        // Update fields with exact field mapping
        existingConsultationPhase.setDesignationAr(consultationPhaseDTO.getDesignationAr()); // F_01
        existingConsultationPhase.setDesignationEn(consultationPhaseDTO.getDesignationEn()); // F_02
        existingConsultationPhase.setDesignationFr(consultationPhaseDTO.getDesignationFr()); // F_03 - required, unique

        ConsultationPhase updatedConsultationPhase = consultationPhaseRepository.save(existingConsultationPhase);
        log.info("Successfully updated consultation phase with ID: {}", id);
        
        return ConsultationPhaseDTO.fromEntity(updatedConsultationPhase);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete consultation phase
     */
    public void deleteConsultationPhase(Long id) {
        log.info("Deleting consultation phase with ID: {}", id);
        ConsultationPhase consultationPhase = getConsultationPhaseEntityById(id);
        
        // Check if consultation phase is being used in consultation steps
        if (consultationPhaseRepository.hasConsultationSteps(id)) {
            throw new RuntimeException("Cannot delete consultation phase as it has consultation steps");
        }
        
        consultationPhaseRepository.delete(consultationPhase);
        log.info("Successfully deleted consultation phase with ID: {}", id);
    }

    /**
     * Delete consultation phase by ID (direct)
     */
    public void deleteConsultationPhaseById(Long id) {
        log.info("Deleting consultation phase by ID: {}", id);
        if (!consultationPhaseRepository.existsById(id)) {
            throw new RuntimeException("ConsultationPhase not found with ID: " + id);
        }
        
        // Check if consultation phase is being used in consultation steps
        if (consultationPhaseRepository.hasConsultationSteps(id)) {
            throw new RuntimeException("Cannot delete consultation phase as it has consultation steps");
        }
        
        consultationPhaseRepository.deleteById(id);
        log.info("Successfully deleted consultation phase with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if consultation phase exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return consultationPhaseRepository.existsById(id);
    }

    /**
     * Check if consultation phase exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return consultationPhaseRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of consultation phases
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return consultationPhaseRepository.count();
    }

    /**
     * Get consultation phases with steps
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> getConsultationPhasesWithSteps(Pageable pageable) {
        log.debug("Getting consultation phases that have consultation steps");
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.findPhasesWithSteps(pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntityWithSteps);
    }

    /**
     * Get consultation phases without steps
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> getConsultationPhasesWithoutSteps(Pageable pageable) {
        log.debug("Getting consultation phases that have no consultation steps");
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.findPhasesWithoutSteps(pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntity);
    }

    /**
     * Get pre-award consultation phases
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> getPreAwardPhases(Pageable pageable) {
        log.debug("Getting pre-award consultation phases");
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.findPreAwardPhases(pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntity);
    }

    /**
     * Get post-award consultation phases
     */
    @Transactional(readOnly = true)
    public Page<ConsultationPhaseDTO> getPostAwardPhases(Pageable pageable) {
        log.debug("Getting post-award consultation phases");
        Page<ConsultationPhase> consultationPhases = consultationPhaseRepository.findPostAwardPhases(pageable);
        return consultationPhases.map(ConsultationPhaseDTO::fromEntity);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ConsultationPhaseDTO consultationPhaseDTO, String operation) {
        if (consultationPhaseDTO.getDesignationFr() == null || consultationPhaseDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraint
     */
    private void validateUniqueConstraint(ConsultationPhaseDTO consultationPhaseDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (consultationPhaseRepository.existsByDesignationFr(consultationPhaseDTO.getDesignationFr())) {
                throw new RuntimeException("ConsultationPhase with French designation '" + consultationPhaseDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (consultationPhaseRepository.existsByDesignationFrAndIdNot(consultationPhaseDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another consultation phase with French designation '" + consultationPhaseDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}