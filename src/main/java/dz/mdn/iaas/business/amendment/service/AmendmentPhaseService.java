/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentPhaseService
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.service;

import dz.mdn.iaas.business.amendment.model.AmendmentPhase;
import dz.mdn.iaas.business.amendment.repository.AmendmentPhaseRepository;
import dz.mdn.iaas.business.amendment.dto.AmendmentPhaseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * AmendmentPhase Service with CRUD operations
 * Handles amendment phase management operations with unique constraint
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * Required field: F_03 (designationFr) with unique constraint
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AmendmentPhaseService {

    private final AmendmentPhaseRepository amendmentPhaseRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new amendment phase
     */
    public AmendmentPhaseDTO createAmendmentPhase(AmendmentPhaseDTO amendmentPhaseDTO) {
        log.info("Creating amendment phase with French designation: {}", 
                amendmentPhaseDTO.getDesignationFr());

        // Validate required fields
        validateRequiredFields(amendmentPhaseDTO, "create");
        
        // Check for unique constraint violation
        validateUniqueConstraint(amendmentPhaseDTO, null);

        // Create entity with exact field mapping
        AmendmentPhase amendmentPhase = new AmendmentPhase();
        amendmentPhase.setDesignationAr(amendmentPhaseDTO.getDesignationAr()); // F_01
        amendmentPhase.setDesignationEn(amendmentPhaseDTO.getDesignationEn()); // F_02
        amendmentPhase.setDesignationFr(amendmentPhaseDTO.getDesignationFr()); // F_03 - required, unique

        AmendmentPhase savedAmendmentPhase = amendmentPhaseRepository.save(amendmentPhase);
        log.info("Successfully created amendment phase with ID: {}", savedAmendmentPhase.getId());
        
        return AmendmentPhaseDTO.fromEntity(savedAmendmentPhase);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get amendment phase by ID
     */
    @Transactional(readOnly = true)
    public AmendmentPhaseDTO getAmendmentPhaseById(Long id) {
        log.debug("Getting amendment phase with ID: {}", id);
        AmendmentPhase amendmentPhase = amendmentPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AmendmentPhase not found with ID: " + id));
        return AmendmentPhaseDTO.fromEntity(amendmentPhase);
    }

    /**
     * Get amendment phase entity by ID
     */
    @Transactional(readOnly = true)
    public AmendmentPhase getAmendmentPhaseEntityById(Long id) {
        return amendmentPhaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AmendmentPhase not found with ID: " + id));
    }

    /**
     * Get amendment phase by ID with amendment steps info
     */
    @Transactional(readOnly = true)
    public AmendmentPhaseDTO getAmendmentPhaseWithSteps(Long id) {
        log.debug("Getting amendment phase with steps for ID: {}", id);
        AmendmentPhase amendmentPhase = amendmentPhaseRepository.findByIdWithSteps(id)
                .orElseThrow(() -> new RuntimeException("AmendmentPhase not found with ID: " + id));
        return AmendmentPhaseDTO.fromEntityWithSteps(amendmentPhase);
    }

    /**
     * Find amendment phase by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentPhaseDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding amendment phase with French designation: {}", designationFr);
        return amendmentPhaseRepository.findByDesignationFr(designationFr)
                .map(AmendmentPhaseDTO::fromEntity);
    }

    /**
     * Get all amendment phases with pagination
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> getAllAmendmentPhases(Pageable pageable) {
        log.debug("Getting all amendment phases with pagination");
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.findAllOrderByDesignationFr(pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntity);
    }

    /**
     * Get all amendment phases with amendment steps info
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> getAllAmendmentPhasesWithSteps(Pageable pageable) {
        log.debug("Getting all amendment phases with steps info");
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.findAllWithStepsCount(pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntityWithSteps);
    }

    /**
     * Find one amendment phase by ID
     */
    @Transactional(readOnly = true)
    public Optional<AmendmentPhaseDTO> findOne(Long id) {
        log.debug("Finding amendment phase by ID: {}", id);
        return amendmentPhaseRepository.findById(id)
                .map(AmendmentPhaseDTO::fromEntity);
    }

    /**
     * Search amendment phases by any field
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> searchAmendmentPhases(String searchTerm, Pageable pageable) {
        log.debug("Searching amendment phases with term: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllAmendmentPhases(pageable);
        }
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.searchByAnyField(searchTerm.trim(), pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntity);
    }

    /**
     * Search amendment phases by designation
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching amendment phases by designation: {}", designation);
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.searchByDesignation(designation, pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntity);
    }

    /**
     * Get amendment phases by type
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> getAmendmentPhasesByType(String phaseType, Pageable pageable) {
        log.debug("Getting amendment phases by type: {}", phaseType);
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.findByPhaseType(phaseType, pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update amendment phase
     */
    public AmendmentPhaseDTO updateAmendmentPhase(Long id, AmendmentPhaseDTO amendmentPhaseDTO) {
        log.info("Updating amendment phase with ID: {}", id);
        AmendmentPhase existingAmendmentPhase = getAmendmentPhaseEntityById(id);

        // Validate required fields
        validateRequiredFields(amendmentPhaseDTO, "update");
        
        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraint(amendmentPhaseDTO, id);

        // Update fields with exact field mapping
        existingAmendmentPhase.setDesignationAr(amendmentPhaseDTO.getDesignationAr()); // F_01
        existingAmendmentPhase.setDesignationEn(amendmentPhaseDTO.getDesignationEn()); // F_02
        existingAmendmentPhase.setDesignationFr(amendmentPhaseDTO.getDesignationFr()); // F_03 - required, unique

        AmendmentPhase updatedAmendmentPhase = amendmentPhaseRepository.save(existingAmendmentPhase);
        log.info("Successfully updated amendment phase with ID: {}", id);
        
        return AmendmentPhaseDTO.fromEntity(updatedAmendmentPhase);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete amendment phase
     */
    public void deleteAmendmentPhase(Long id) {
        log.info("Deleting amendment phase with ID: {}", id);
        AmendmentPhase amendmentPhase = getAmendmentPhaseEntityById(id);
        
        // Check if amendment phase is being used in amendment steps
        if (amendmentPhaseRepository.hasAmendmentSteps(id)) {
            throw new RuntimeException("Cannot delete amendment phase as it has amendment steps");
        }
        
        amendmentPhaseRepository.delete(amendmentPhase);
        log.info("Successfully deleted amendment phase with ID: {}", id);
    }

    /**
     * Delete amendment phase by ID (direct)
     */
    public void deleteAmendmentPhaseById(Long id) {
        log.info("Deleting amendment phase by ID: {}", id);
        if (!amendmentPhaseRepository.existsById(id)) {
            throw new RuntimeException("AmendmentPhase not found with ID: " + id);
        }
        
        // Check if amendment phase is being used in amendment steps
        if (amendmentPhaseRepository.hasAmendmentSteps(id)) {
            throw new RuntimeException("Cannot delete amendment phase as it has amendment steps");
        }
        
        amendmentPhaseRepository.deleteById(id);
        log.info("Successfully deleted amendment phase with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if amendment phase exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return amendmentPhaseRepository.existsById(id);
    }

    /**
     * Check if amendment phase exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return amendmentPhaseRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of amendment phases
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return amendmentPhaseRepository.count();
    }

    /**
     * Get amendment phases with steps
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> getAmendmentPhasesWithSteps(Pageable pageable) {
        log.debug("Getting amendment phases that have amendment steps");
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.findPhasesWithSteps(pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntityWithSteps);
    }

    /**
     * Get amendment phases without steps
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> getAmendmentPhasesWithoutSteps(Pageable pageable) {
        log.debug("Getting amendment phases that have no amendment steps");
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.findPhasesWithoutSteps(pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntity);
    }

    /**
     * Get pre-award amendment phases
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> getPreAwardPhases(Pageable pageable) {
        log.debug("Getting pre-award amendment phases");
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.findPreAwardPhases(pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntity);
    }

    /**
     * Get post-award amendment phases
     */
    @Transactional(readOnly = true)
    public Page<AmendmentPhaseDTO> getPostAwardPhases(Pageable pageable) {
        log.debug("Getting post-award amendment phases");
        Page<AmendmentPhase> amendmentPhases = amendmentPhaseRepository.findPostAwardPhases(pageable);
        return amendmentPhases.map(AmendmentPhaseDTO::fromEntity);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(AmendmentPhaseDTO amendmentPhaseDTO, String operation) {
        if (amendmentPhaseDTO.getDesignationFr() == null || amendmentPhaseDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraint
     */
    private void validateUniqueConstraint(AmendmentPhaseDTO amendmentPhaseDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (amendmentPhaseRepository.existsByDesignationFr(amendmentPhaseDTO.getDesignationFr())) {
                throw new RuntimeException("AmendmentPhase with French designation '" + amendmentPhaseDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (amendmentPhaseRepository.existsByDesignationFrAndIdNot(amendmentPhaseDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another amendment phase with French designation '" + amendmentPhaseDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}