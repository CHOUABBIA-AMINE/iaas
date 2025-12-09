/**
 *
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AwardMethodService
 *	@CreatedOn	: 10-19-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.service;

import dz.mdn.iaas.business.consultation.model.AwardMethod;
import dz.mdn.iaas.business.consultation.repository.AwardMethodRepository;
import dz.mdn.iaas.business.consultation.dto.AwardMethodDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * AwardMethod Service with CRUD operations
 * Handles award method management operations with unique constraints
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr,
 * F_04=acronymAr, F_05=acronymEn, F_06=acronymFr
 * Required fields: F_03 (designationFr), F_06 (acronymFr) with unique constraints
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AwardMethodService {

    private final AwardMethodRepository awardMethodRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new award method
     */
    public AwardMethodDTO createAwardMethod(AwardMethodDTO awardMethodDTO) {
        log.info("Creating award method with French acronym: {} and designation: {}",
                awardMethodDTO.getAcronymFr(), awardMethodDTO.getDesignationFr());

        // Validate required fields
        validateRequiredFields(awardMethodDTO, "create");
        
        // Check for unique constraint violations
        validateUniqueConstraints(awardMethodDTO, null);

        // Create entity with exact field mapping
        AwardMethod awardMethod = new AwardMethod();
        awardMethod.setDesignationAr(awardMethodDTO.getDesignationAr()); // F_01
        awardMethod.setDesignationEn(awardMethodDTO.getDesignationEn()); // F_02
        awardMethod.setDesignationFr(awardMethodDTO.getDesignationFr()); // F_03 - required, unique
        awardMethod.setAcronymAr(awardMethodDTO.getAcronymAr()); // F_04
        awardMethod.setAcronymEn(awardMethodDTO.getAcronymEn()); // F_05
        awardMethod.setAcronymFr(awardMethodDTO.getAcronymFr()); // F_06 - required, unique

        AwardMethod savedAwardMethod = awardMethodRepository.save(awardMethod);
        log.info("Successfully created award method with ID: {}", savedAwardMethod.getId());
        
        return AwardMethodDTO.fromEntity(savedAwardMethod);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get award method by ID
     */
    @Transactional(readOnly = true)
    public AwardMethodDTO getAwardMethodById(Long id) {
        log.debug("Getting award method with ID: {}", id);
        AwardMethod awardMethod = awardMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AwardMethod not found with ID: " + id));
        return AwardMethodDTO.fromEntity(awardMethod);
    }

    /**
     * Get award method entity by ID
     */
    @Transactional(readOnly = true)
    public AwardMethod getAwardMethodEntityById(Long id) {
        return awardMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AwardMethod not found with ID: " + id));
    }

    /**
     * Find award method by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<AwardMethodDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding award method with French designation: {}", designationFr);
        return awardMethodRepository.findByDesignationFr(designationFr)
                .map(AwardMethodDTO::fromEntity);
    }

    /**
     * Find award method by French acronym (unique field F_06)
     */
    @Transactional(readOnly = true)
    public Optional<AwardMethodDTO> findByAcronymFr(String acronymFr) {
        log.debug("Finding award method with French acronym: {}", acronymFr);
        return awardMethodRepository.findByAcronymFr(acronymFr)
                .map(AwardMethodDTO::fromEntity);
    }

    /**
     * Get all award methods with pagination
     */
    @Transactional(readOnly = true)
    public Page<AwardMethodDTO> getAllAwardMethods(Pageable pageable) {
        log.debug("Getting all award methods with pagination");
        Page<AwardMethod> awardMethods = awardMethodRepository.findAllOrderByAcronymFr(pageable);
        return awardMethods.map(AwardMethodDTO::fromEntity);
    }

    /**
     * Find one award method by ID
     */
    @Transactional(readOnly = true)
    public Optional<AwardMethodDTO> findOne(Long id) {
        log.debug("Finding award method by ID: {}", id);
        return awardMethodRepository.findById(id)
                .map(AwardMethodDTO::fromEntity);
    }

    /**
     * Search award methods by any field
     */
    @Transactional(readOnly = true)
    public Page<AwardMethodDTO> searchAwardMethods(String searchTerm, Pageable pageable) {
        log.debug("Searching award methods with term: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllAwardMethods(pageable);
        }
        Page<AwardMethod> awardMethods = awardMethodRepository.searchByAnyField(searchTerm.trim(), pageable);
        return awardMethods.map(AwardMethodDTO::fromEntity);
    }

    /**
     * Search award methods by designation
     */
    @Transactional(readOnly = true)
    public Page<AwardMethodDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching award methods by designation: {}", designation);
        Page<AwardMethod> awardMethods = awardMethodRepository.searchByDesignation(designation, pageable);
        return awardMethods.map(AwardMethodDTO::fromEntity);
    }

    /**
     * Search award methods by acronym
     */
    @Transactional(readOnly = true)
    public Page<AwardMethodDTO> searchByAcronym(String acronym, Pageable pageable) {
        log.debug("Searching award methods by acronym: {}", acronym);
        Page<AwardMethod> awardMethods = awardMethodRepository.searchByAcronym(acronym, pageable);
        return awardMethods.map(AwardMethodDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update award method
     */
    public AwardMethodDTO updateAwardMethod(Long id, AwardMethodDTO awardMethodDTO) {
        log.info("Updating award method with ID: {}", id);
        AwardMethod existingAwardMethod = getAwardMethodEntityById(id);

        // Validate required fields
        validateRequiredFields(awardMethodDTO, "update");
        
        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(awardMethodDTO, id);

        // Update fields with exact field mapping
        existingAwardMethod.setDesignationAr(awardMethodDTO.getDesignationAr()); // F_01
        existingAwardMethod.setDesignationEn(awardMethodDTO.getDesignationEn()); // F_02
        existingAwardMethod.setDesignationFr(awardMethodDTO.getDesignationFr()); // F_03 - required, unique
        existingAwardMethod.setAcronymAr(awardMethodDTO.getAcronymAr()); // F_04
        existingAwardMethod.setAcronymEn(awardMethodDTO.getAcronymEn()); // F_05
        existingAwardMethod.setAcronymFr(awardMethodDTO.getAcronymFr()); // F_06 - required, unique

        AwardMethod updatedAwardMethod = awardMethodRepository.save(existingAwardMethod);
        log.info("Successfully updated award method with ID: {}", id);
        
        return AwardMethodDTO.fromEntity(updatedAwardMethod);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete award method
     */
    public void deleteAwardMethod(Long id) {
        log.info("Deleting award method with ID: {}", id);
        AwardMethod awardMethod = getAwardMethodEntityById(id);
        
        // Check if award method is being used in consultations
        if (awardMethodRepository.isUsedInConsultations(id)) {
            throw new RuntimeException("Cannot delete award method as it is being used in consultations");
        }
        
        awardMethodRepository.delete(awardMethod);
        log.info("Successfully deleted award method with ID: {}", id);
    }

    /**
     * Delete award method by ID (direct)
     */
    public void deleteAwardMethodById(Long id) {
        log.info("Deleting award method by ID: {}", id);
        if (!awardMethodRepository.existsById(id)) {
            throw new RuntimeException("AwardMethod not found with ID: " + id);
        }
        
        // Check if award method is being used in consultations
        if (awardMethodRepository.isUsedInConsultations(id)) {
            throw new RuntimeException("Cannot delete award method as it is being used in consultations");
        }
        
        awardMethodRepository.deleteById(id);
        log.info("Successfully deleted award method with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if award method exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return awardMethodRepository.existsById(id);
    }

    /**
     * Check if award method exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return awardMethodRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Check if award method exists by French acronym
     */
    @Transactional(readOnly = true)
    public boolean existsByAcronymFr(String acronymFr) {
        return awardMethodRepository.existsByAcronymFr(acronymFr);
    }

    /**
     * Get total count of award methods
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return awardMethodRepository.count();
    }

    /**
     * Get award methods by category
     */
    @Transactional(readOnly = true)
    public Page<AwardMethodDTO> getAwardMethodsByCategory(String category, Pageable pageable) {
        log.debug("Getting award methods by category: {}", category);
        Page<AwardMethod> awardMethods = awardMethodRepository.findByCategory(category, pageable);
        return awardMethods.map(AwardMethodDTO::fromEntity);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(AwardMethodDTO awardMethodDTO, String operation) {
        if (awardMethodDTO.getDesignationFr() == null || awardMethodDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (awardMethodDTO.getAcronymFr() == null || awardMethodDTO.getAcronymFr().trim().isEmpty()) {
            throw new RuntimeException("French acronym is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(AwardMethodDTO awardMethodDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (awardMethodRepository.existsByDesignationFr(awardMethodDTO.getDesignationFr())) {
                throw new RuntimeException("AwardMethod with French designation '" + awardMethodDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (awardMethodRepository.existsByDesignationFrAndIdNot(awardMethodDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another award method with French designation '" + awardMethodDTO.getDesignationFr() + "' already exists");
            }
        }

        // Check French acronym uniqueness (F_06)
        if (excludeId == null) {
            if (awardMethodRepository.existsByAcronymFr(awardMethodDTO.getAcronymFr())) {
                throw new RuntimeException("AwardMethod with French acronym '" + awardMethodDTO.getAcronymFr() + "' already exists");
            }
        } else {
            if (awardMethodRepository.existsByAcronymFrAndIdNot(awardMethodDTO.getAcronymFr(), excludeId)) {
                throw new RuntimeException("Another award method with French acronym '" + awardMethodDTO.getAcronymFr() + "' already exists");
            }
        }
    }
}