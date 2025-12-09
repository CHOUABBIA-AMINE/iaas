/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperationService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.plan.dto.FinancialOperationDTO;
import dz.mdn.iaas.business.plan.model.FinancialOperation;
import dz.mdn.iaas.business.plan.repository.BudgetTypeRepository;
import dz.mdn.iaas.business.plan.repository.FinancialOperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Financial Operation Service with CRUD operations
 * Handles financial operation management operations with budget intelligence and fiscal year tracking
 * Based on exact field names and business rules for financial operation management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FinancialOperationService {

    private final FinancialOperationRepository financialOperationRepository;
    
    // Repository bean for related entity (injected as needed)
    private final BudgetTypeRepository budgetTypeRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new financial operation
     */
    public FinancialOperationDTO createFinancialOperation(FinancialOperationDTO financialOperationDTO) {
        log.info("Creating financial operation: {} for budget year: {}", 
                financialOperationDTO.getOperation(), financialOperationDTO.getBudgetYear());

        // Validate required fields and business rules
        validateRequiredFields(financialOperationDTO, "create");
        validateBusinessRules(financialOperationDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(financialOperationDTO, null);

        // Create entity with exact field mapping
        FinancialOperation financialOperation = new FinancialOperation();
        mapDtoToEntity(financialOperationDTO, financialOperation);

        // Handle foreign key relationships
        setEntityRelationships(financialOperationDTO, financialOperation);

        FinancialOperation savedFinancialOperation = financialOperationRepository.save(financialOperation);
        log.info("Successfully created financial operation with ID: {}", savedFinancialOperation.getId());

        return FinancialOperationDTO.fromEntityWithRelations(savedFinancialOperation);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get financial operation by ID
     */
    @Transactional(readOnly = true)
    public FinancialOperationDTO getFinancialOperationById(Long id) {
        log.debug("Getting financial operation with ID: {}", id);

        FinancialOperation financialOperation = financialOperationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Financial operation not found with ID: " + id));

        return FinancialOperationDTO.fromEntityWithRelations(financialOperation);
    }

    /**
     * Get financial operation entity by ID
     */
    @Transactional(readOnly = true)
    public FinancialOperation getFinancialOperationEntityById(Long id) {
        return financialOperationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Financial operation not found with ID: " + id));
    }

    /**
     * Get all financial operations with pagination
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getAllFinancialOperations(Pageable pageable) {
        log.debug("Getting all financial operations with pagination");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findAllOrderByBudgetYearAndOperation(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Find one financial operation by ID
     */
    @Transactional(readOnly = true)
    public Optional<FinancialOperationDTO> findOne(Long id) {
        log.debug("Finding financial operation by ID: {}", id);

        return financialOperationRepository.findById(id)
                .map(FinancialOperationDTO::fromEntityWithRelations);
    }

    /**
     * Find financial operation by operation name (unique)
     */
    @Transactional(readOnly = true)
    public Optional<FinancialOperationDTO> findByOperation(String operation) {
        log.debug("Finding financial operation by operation: {}", operation);

        return financialOperationRepository.findByOperation(operation)
                .map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Search financial operations by operation name
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> searchFinancialOperationsByOperation(String searchTerm, Pageable pageable) {
        log.debug("Searching financial operations by operation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllFinancialOperations(pageable);
        }

        Page<FinancialOperation> financialOperations = financialOperationRepository.searchByOperation(searchTerm.trim(), pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get financial operations by budget year
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getFinancialOperationsByBudgetYear(String budgetYear, Pageable pageable) {
        log.debug("Getting financial operations for budget year: {}", budgetYear);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findByBudgetYear(budgetYear, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get financial operations by budget type
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getFinancialOperationsByBudgetType(Long budgetTypeId, Pageable pageable) {
        log.debug("Getting financial operations for budget type ID: {}", budgetTypeId);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findByBudgetType(budgetTypeId, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get financial operations by budget year and budget type
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getFinancialOperationsByBudgetYearAndType(String budgetYear, Long budgetTypeId, Pageable pageable) {
        log.debug("Getting financial operations for budget year: {} and budget type ID: {}", budgetYear, budgetTypeId);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findByBudgetYearAndBudgetType(budgetYear, budgetTypeId, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get current year financial operations
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getCurrentYearOperations(Pageable pageable) {
        String currentYear = String.valueOf(Year.now().getValue());
        log.debug("Getting current year financial operations for year: {}", currentYear);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findCurrentYearOperations(currentYear, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get future year financial operations
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getFutureYearOperations(Pageable pageable) {
        String currentYear = String.valueOf(Year.now().getValue());
        log.debug("Getting future year financial operations after year: {}", currentYear);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findFutureYearOperations(currentYear, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get past year financial operations
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getPastYearOperations(Pageable pageable) {
        String currentYear = String.valueOf(Year.now().getValue());
        log.debug("Getting past year financial operations before year: {}", currentYear);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findPastYearOperations(currentYear, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get operations by category
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getBudgetAllocationOperations(Pageable pageable) {
        log.debug("Getting budget allocation financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findBudgetAllocationOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getExpenditureOperations(Pageable pageable) {
        log.debug("Getting expenditure financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findExpenditureOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getRevenueOperations(Pageable pageable) {
        log.debug("Getting revenue financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findRevenueOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getTransferOperations(Pageable pageable) {
        log.debug("Getting transfer financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findTransferOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getInvestmentOperations(Pageable pageable) {
        log.debug("Getting investment financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findInvestmentOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getProcurementOperations(Pageable pageable) {
        log.debug("Getting procurement financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findProcurementOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getPaymentOperations(Pageable pageable) {
        log.debug("Getting payment financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findPaymentOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getAdjustmentOperations(Pageable pageable) {
        log.debug("Getting adjustment financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findAdjustmentOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get high priority operations
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getHighPriorityOperations(Pageable pageable) {
        log.debug("Getting high priority financial operations");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findHighPriorityOperations(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get operations requiring executive approval
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getOperationsRequiringExecutiveApproval(Pageable pageable) {
        log.debug("Getting financial operations requiring executive approval");

        Page<FinancialOperation> financialOperations = financialOperationRepository.findRequiringExecutiveApproval(pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get operations by budget year range
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getOperationsByBudgetYearRange(String startYear, String endYear, Pageable pageable) {
        log.debug("Getting financial operations between years: {} and {}", startYear, endYear);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findByBudgetYearRange(startYear, endYear, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    /**
     * Get operations by fiscal period
     */
    @Transactional(readOnly = true)
    public Page<FinancialOperationDTO> getOperationsByFiscalPeriod(String period, Pageable pageable) {
        String currentYear = String.valueOf(Year.now().getValue());
        log.debug("Getting financial operations for fiscal period: {}", period);

        Page<FinancialOperation> financialOperations = financialOperationRepository.findByFiscalPeriod(period, currentYear, pageable);
        return financialOperations.map(FinancialOperationDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update financial operation
     */
    public FinancialOperationDTO updateFinancialOperation(Long id, FinancialOperationDTO financialOperationDTO) {
        log.info("Updating financial operation with ID: {}", id);

        FinancialOperation existingFinancialOperation = getFinancialOperationEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(financialOperationDTO, "update");
        validateBusinessRules(financialOperationDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(financialOperationDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(financialOperationDTO, existingFinancialOperation);

        // Handle foreign key relationships
        setEntityRelationships(financialOperationDTO, existingFinancialOperation);

        FinancialOperation updatedFinancialOperation = financialOperationRepository.save(existingFinancialOperation);
        log.info("Successfully updated financial operation with ID: {}", id);

        return FinancialOperationDTO.fromEntityWithRelations(updatedFinancialOperation);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete financial operation
     */
    public void deleteFinancialOperation(Long id) {
        log.info("Deleting financial operation with ID: {}", id);

        FinancialOperation financialOperation = getFinancialOperationEntityById(id);
        financialOperationRepository.delete(financialOperation);

        log.info("Successfully deleted financial operation with ID: {}", id);
    }

    /**
     * Delete financial operation by ID (direct)
     */
    public void deleteFinancialOperationById(Long id) {
        log.info("Deleting financial operation by ID: {}", id);

        if (!financialOperationRepository.existsById(id)) {
            throw new RuntimeException("Financial operation not found with ID: " + id);
        }

        financialOperationRepository.deleteById(id);
        log.info("Successfully deleted financial operation with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if financial operation exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return financialOperationRepository.existsById(id);
    }

    /**
     * Check if operation name exists
     */
    @Transactional(readOnly = true)
    public boolean existsByOperation(String operation) {
        return financialOperationRepository.existsByOperation(operation);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countOperationsByBudgetYear(String budgetYear) {
        return financialOperationRepository.countByBudgetYear(budgetYear);
    }

    @Transactional(readOnly = true)
    public Long countOperationsByBudgetType(Long budgetTypeId) {
        return financialOperationRepository.countByBudgetType(budgetTypeId);
    }

    @Transactional(readOnly = true)
    public Long countAllOperations() {
        return financialOperationRepository.countAllOperations();
    }

    @Transactional(readOnly = true)
    public Long countCurrentYearOperations() {
        String currentYear = String.valueOf(Year.now().getValue());
        return financialOperationRepository.countCurrentYearOperations(currentYear);
    }

    @Transactional(readOnly = true)
    public Long countBudgetAllocationOperations() {
        return financialOperationRepository.countBudgetAllocationOperations();
    }

    @Transactional(readOnly = true)
    public Long countExpenditureOperations() {
        return financialOperationRepository.countExpenditureOperations();
    }

    /**
     * Get distinct budget years
     */
    @Transactional(readOnly = true)
    public List<String> getDistinctBudgetYears() {
        return financialOperationRepository.findDistinctBudgetYears();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(FinancialOperationDTO dto, FinancialOperation entity) {
        entity.setOperation(dto.getOperation()); // F_01
        entity.setBudgetYear(dto.getBudgetYear()); // F_02
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(FinancialOperationDTO dto, FinancialOperation entity) {
        // F_03 - BudgetType (required)
        if (dto.getBudgetTypeId() != null) {
            entity.setBudgetType(budgetTypeRepository.findById(dto.getBudgetTypeId())
                    .orElseThrow(() -> new RuntimeException("Budget type not found with ID: " + dto.getBudgetTypeId())));
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(FinancialOperationDTO dto, String operation) {
        if (dto.getOperation() == null || dto.getOperation().trim().isEmpty()) {
            throw new RuntimeException("Operation is required for " + operation);
        }
        if (dto.getBudgetYear() == null || dto.getBudgetYear().trim().isEmpty()) {
            throw new RuntimeException("Budget year is required for " + operation);
        }
        if (dto.getBudgetTypeId() == null) {
            throw new RuntimeException("Budget type is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(FinancialOperationDTO dto, String operation) {
        // Validate operation length
        if (dto.getOperation() != null && dto.getOperation().length() > 200) {
            throw new RuntimeException("Operation cannot exceed 200 characters for " + operation);
        }

        // Validate budget year format
        if (dto.getBudgetYear() != null) {
            if (dto.getBudgetYear().length() != 4) {
                throw new RuntimeException("Budget year must be exactly 4 characters for " + operation);
            }
            if (!dto.getBudgetYear().matches("^[0-9]{4}$")) {
                throw new RuntimeException("Budget year must be a valid 4-digit year for " + operation);
            }
            
            // Validate year range (reasonable range)
            int year = Integer.parseInt(dto.getBudgetYear());
            int currentYear = Year.now().getValue();
            if (year < 2000 || year > currentYear + 10) {
                throw new RuntimeException("Budget year must be between 2000 and " + (currentYear + 10) + " for " + operation);
            }
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(FinancialOperationDTO dto, Long excludeId) {
        // Check operation name uniqueness (T_02_02_03_UK_01)
        if (dto.getOperation() != null && !dto.getOperation().trim().isEmpty()) {
            if (excludeId == null) {
                if (financialOperationRepository.existsByOperation(dto.getOperation())) {
                    throw new RuntimeException("Financial operation with name '" + 
                        dto.getOperation() + "' already exists");
                }
            } else {
                if (financialOperationRepository.existsByOperationAndIdNot(dto.getOperation(), excludeId)) {
                    throw new RuntimeException("Another financial operation with name '" + 
                        dto.getOperation() + "' already exists");
                }
            }
        }
    }
}
