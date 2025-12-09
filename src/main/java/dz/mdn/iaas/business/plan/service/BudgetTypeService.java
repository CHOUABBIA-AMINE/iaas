/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetTypeService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.service;

import dz.mdn.iaas.business.plan.model.BudgetType;
import dz.mdn.iaas.business.plan.repository.BudgetTypeRepository;
import dz.mdn.iaas.business.plan.dto.BudgetTypeDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Budget Type Service with CRUD operations
 * Handles budget type management operations with multilingual support and budget classification
 * Based on exact field names and business rules for budget management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BudgetTypeService {

    private final BudgetTypeRepository budgetTypeRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new budget type
     */
    public BudgetTypeDTO createBudgetType(BudgetTypeDTO budgetTypeDTO) {
        log.info("Creating budget type with French designation: {}", 
                budgetTypeDTO.getDesignationFr());

        // Validate required fields and business rules
        validateRequiredFields(budgetTypeDTO, "create");
        validateBusinessRules(budgetTypeDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(budgetTypeDTO, null);

        // Create entity with exact field mapping
        BudgetType budgetType = new BudgetType();
        mapDtoToEntity(budgetTypeDTO, budgetType);

        BudgetType savedBudgetType = budgetTypeRepository.save(budgetType);
        log.info("Successfully created budget type with ID: {}", savedBudgetType.getId());

        return BudgetTypeDTO.fromEntity(savedBudgetType);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get budget type by ID
     */
    @Transactional(readOnly = true)
    public BudgetTypeDTO getBudgetTypeById(Long id) {
        log.debug("Getting budget type with ID: {}", id);

        BudgetType budgetType = budgetTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget type not found with ID: " + id));

        return BudgetTypeDTO.fromEntity(budgetType);
    }

    /**
     * Get budget type entity by ID
     */
    @Transactional(readOnly = true)
    public BudgetType getBudgetTypeEntityById(Long id) {
        return budgetTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget type not found with ID: " + id));
    }

    /**
     * Get all budget types with pagination
     */
    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getAllBudgetTypes(Pageable pageable) {
        log.debug("Getting all budget types with pagination");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findAllOrderByDesignationFr(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Find one budget type by ID
     */
    @Transactional(readOnly = true)
    public Optional<BudgetTypeDTO> findOne(Long id) {
        log.debug("Finding budget type by ID: {}", id);

        return budgetTypeRepository.findById(id)
                .map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Find budget type by French designation (unique)
     */
    @Transactional(readOnly = true)
    public Optional<BudgetTypeDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding budget type by French designation: {}", designationFr);

        return budgetTypeRepository.findByDesignationFr(designationFr)
                .map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Find budget type by French acronym (unique)
     */
    @Transactional(readOnly = true)
    public Optional<BudgetTypeDTO> findByAcronymFr(String acronymFr) {
        log.debug("Finding budget type by French acronym: {}", acronymFr);

        return budgetTypeRepository.findByAcronymFr(acronymFr)
                .map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Search budget types by designation
     */
    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> searchBudgetTypesByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching budget types by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBudgetTypes(pageable);
        }

        Page<BudgetType> budgetTypes = budgetTypeRepository.searchByDesignation(searchTerm.trim(), pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Search budget types by acronym
     */
    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> searchBudgetTypesByAcronym(String searchTerm, Pageable pageable) {
        log.debug("Searching budget types by acronym with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBudgetTypes(pageable);
        }

        Page<BudgetType> budgetTypes = budgetTypeRepository.searchByAcronym(searchTerm.trim(), pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Search budget types by any field
     */
    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> searchBudgetTypesByAnyField(String searchTerm, Pageable pageable) {
        log.debug("Searching budget types by any field with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBudgetTypes(pageable);
        }

        Page<BudgetType> budgetTypes = budgetTypeRepository.searchByAnyField(searchTerm.trim(), pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Get budget types by category
     */
    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getInvestmentBudgetTypes(Pageable pageable) {
        log.debug("Getting investment budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findInvestmentBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getOperatingBudgetTypes(Pageable pageable) {
        log.debug("Getting operating budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findOperatingBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getPersonnelBudgetTypes(Pageable pageable) {
        log.debug("Getting personnel budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findPersonnelBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getMaintenanceBudgetTypes(Pageable pageable) {
        log.debug("Getting maintenance budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findMaintenanceBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getResearchDevelopmentBudgetTypes(Pageable pageable) {
        log.debug("Getting research & development budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findResearchDevelopmentBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getDefenseBudgetTypes(Pageable pageable) {
        log.debug("Getting defense budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findDefenseBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getTrainingBudgetTypes(Pageable pageable) {
        log.debug("Getting training budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findTrainingBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getEmergencyBudgetTypes(Pageable pageable) {
        log.debug("Getting emergency budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findEmergencyBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Get multilingual budget types
     */
    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getMultilingualBudgetTypes(Pageable pageable) {
        log.debug("Getting multilingual budget types");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findMultilingualBudgetTypes(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    /**
     * Get budget types requiring approval
     */
    @Transactional(readOnly = true)
    public Page<BudgetTypeDTO> getBudgetTypesRequiringApproval(Pageable pageable) {
        log.debug("Getting budget types requiring approval");

        Page<BudgetType> budgetTypes = budgetTypeRepository.findRequiringApproval(pageable);
        return budgetTypes.map(BudgetTypeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update budget type
     */
    public BudgetTypeDTO updateBudgetType(Long id, BudgetTypeDTO budgetTypeDTO) {
        log.info("Updating budget type with ID: {}", id);

        BudgetType existingBudgetType = getBudgetTypeEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(budgetTypeDTO, "update");
        validateBusinessRules(budgetTypeDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(budgetTypeDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(budgetTypeDTO, existingBudgetType);

        BudgetType updatedBudgetType = budgetTypeRepository.save(existingBudgetType);
        log.info("Successfully updated budget type with ID: {}", id);

        return BudgetTypeDTO.fromEntity(updatedBudgetType);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete budget type
     */
    public void deleteBudgetType(Long id) {
        log.info("Deleting budget type with ID: {}", id);

        BudgetType budgetType = getBudgetTypeEntityById(id);
        budgetTypeRepository.delete(budgetType);

        log.info("Successfully deleted budget type with ID: {}", id);
    }

    /**
     * Delete budget type by ID (direct)
     */
    public void deleteBudgetTypeById(Long id) {
        log.info("Deleting budget type by ID: {}", id);

        if (!budgetTypeRepository.existsById(id)) {
            throw new RuntimeException("Budget type not found with ID: " + id);
        }

        budgetTypeRepository.deleteById(id);
        log.info("Successfully deleted budget type with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if budget type exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return budgetTypeRepository.existsById(id);
    }

    /**
     * Check if French designation exists
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return budgetTypeRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Check if French acronym exists
     */
    @Transactional(readOnly = true)
    public boolean existsByAcronymFr(String acronymFr) {
        return budgetTypeRepository.existsByAcronymFr(acronymFr);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countAllBudgetTypes() {
        return budgetTypeRepository.countAllBudgetTypes();
    }

    @Transactional(readOnly = true)
    public Long countInvestmentBudgetTypes() {
        return budgetTypeRepository.countInvestmentBudgetTypes();
    }

    @Transactional(readOnly = true)
    public Long countOperatingBudgetTypes() {
        return budgetTypeRepository.countOperatingBudgetTypes();
    }

    @Transactional(readOnly = true)
    public Long countDefenseBudgetTypes() {
        return budgetTypeRepository.countDefenseBudgetTypes();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(BudgetTypeDTO dto, BudgetType entity) {
        entity.setDesignationAr(dto.getDesignationAr()); // F_01
        entity.setDesignationEn(dto.getDesignationEn()); // F_02
        entity.setDesignationFr(dto.getDesignationFr()); // F_03
        entity.setAcronymAr(dto.getAcronymAr()); // F_04
        entity.setAcronymEn(dto.getAcronymEn()); // F_05
        entity.setAcronymFr(dto.getAcronymFr()); // F_06
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(BudgetTypeDTO dto, String operation) {
        if (dto.getDesignationFr() == null || dto.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (dto.getAcronymFr() == null || dto.getAcronymFr().trim().isEmpty()) {
            throw new RuntimeException("French acronym is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(BudgetTypeDTO dto, String operation) {
        // Validate designation lengths
        if (dto.getDesignationFr() != null && dto.getDesignationFr().length() > 200) {
            throw new RuntimeException("French designation cannot exceed 200 characters for " + operation);
        }
        if (dto.getDesignationEn() != null && dto.getDesignationEn().length() > 200) {
            throw new RuntimeException("English designation cannot exceed 200 characters for " + operation);
        }
        if (dto.getDesignationAr() != null && dto.getDesignationAr().length() > 200) {
            throw new RuntimeException("Arabic designation cannot exceed 200 characters for " + operation);
        }

        // Validate acronym lengths
        if (dto.getAcronymFr() != null && dto.getAcronymFr().length() > 20) {
            throw new RuntimeException("French acronym cannot exceed 20 characters for " + operation);
        }
        if (dto.getAcronymEn() != null && dto.getAcronymEn().length() > 20) {
            throw new RuntimeException("English acronym cannot exceed 20 characters for " + operation);
        }
        if (dto.getAcronymAr() != null && dto.getAcronymAr().length() > 20) {
            throw new RuntimeException("Arabic acronym cannot exceed 20 characters for " + operation);
        }

        // Validate at least one designation is provided
        boolean hasDesignation = (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty());
        if (!hasDesignation) {
            throw new RuntimeException("At least French designation must be provided for " + operation);
        }

        // Validate at least one acronym is provided
        boolean hasAcronym = (dto.getAcronymFr() != null && !dto.getAcronymFr().trim().isEmpty());
        if (!hasAcronym) {
            throw new RuntimeException("At least French acronym must be provided for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(BudgetTypeDTO dto, Long excludeId) {
        // Check French designation uniqueness (T_02_02_01_UK_01)
        if (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty()) {
            if (excludeId == null) {
                if (budgetTypeRepository.existsByDesignationFr(dto.getDesignationFr())) {
                    throw new RuntimeException("Budget type with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            } else {
                if (budgetTypeRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), excludeId)) {
                    throw new RuntimeException("Another budget type with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            }
        }

        // Check French acronym uniqueness (T_02_02_01_UK_02)
        if (dto.getAcronymFr() != null && !dto.getAcronymFr().trim().isEmpty()) {
            if (excludeId == null) {
                if (budgetTypeRepository.existsByAcronymFr(dto.getAcronymFr())) {
                    throw new RuntimeException("Budget type with French acronym '" + 
                        dto.getAcronymFr() + "' already exists");
                }
            } else {
                if (budgetTypeRepository.existsByAcronymFrAndIdNot(dto.getAcronymFr(), excludeId)) {
                    throw new RuntimeException("Another budget type with French acronym '" + 
                        dto.getAcronymFr() + "' already exists");
                }
            }
        }
    }
}
