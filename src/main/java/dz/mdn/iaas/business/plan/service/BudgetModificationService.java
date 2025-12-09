/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.plan.dto.BudgetModificationDTO;
import dz.mdn.iaas.business.plan.model.BudgetModification;
import dz.mdn.iaas.business.plan.repository.BudgetModificationRepository;
import dz.mdn.iaas.common.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BudgetModification Service with CRUD operations
 * Handles budget modification operations with approval workflow and document management
 * Based on exact field names and business rules for budget modification management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BudgetModificationService {

    private final BudgetModificationRepository budgetModificationRepository;
    
    // Repository bean for related entity (injected as needed)
    private final DocumentRepository documentRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new budget modification
     */
    public BudgetModificationDTO createBudgetModification(BudgetModificationDTO budgetModificationDTO) {
        log.info("Creating budget modification with object: {} for demande ID: {}", 
                budgetModificationDTO.getObject(), budgetModificationDTO.getDemandeId());

        // Validate required fields and business rules
        validateRequiredFields(budgetModificationDTO, "create");
        validateBusinessRules(budgetModificationDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(budgetModificationDTO, null);

        // Create entity with exact field mapping
        BudgetModification budgetModification = new BudgetModification();
        mapDtoToEntity(budgetModificationDTO, budgetModification);

        // Handle foreign key relationships
        setEntityRelationships(budgetModificationDTO, budgetModification);

        BudgetModification savedBudgetModification = budgetModificationRepository.save(budgetModification);
        log.info("Successfully created budget modification with ID: {}", savedBudgetModification.getId());

        return BudgetModificationDTO.fromEntityWithRelations(savedBudgetModification);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get budget modification by ID
     */
    @Transactional(readOnly = true)
    public BudgetModificationDTO getBudgetModificationById(Long id) {
        log.debug("Getting budget modification with ID: {}", id);

        BudgetModification budgetModification = budgetModificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget modification not found with ID: " + id));

        return BudgetModificationDTO.fromEntityWithRelations(budgetModification);
    }

    /**
     * Get budget modification entity by ID
     */
    @Transactional(readOnly = true)
    public BudgetModification getBudgetModificationEntityById(Long id) {
        return budgetModificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget modification not found with ID: " + id));
    }

    /**
     * Get all budget modifications with pagination
     */
    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getAllBudgetModifications(Pageable pageable) {
        log.debug("Getting all budget modifications with pagination");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findAllOrderByApprovalDate(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    /**
     * Find one budget modification by ID
     */
    @Transactional(readOnly = true)
    public Optional<BudgetModificationDTO> findOne(Long id) {
        log.debug("Finding budget modification by ID: {}", id);

        return budgetModificationRepository.findById(id)
                .map(BudgetModificationDTO::fromEntityWithRelations);
    }

    /**
     * Search budget modifications by object or description
     */
    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> searchBudgetModifications(String searchTerm, Pageable pageable) {
        log.debug("Searching budget modifications with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBudgetModifications(pageable);
        }

        Page<BudgetModification> budgetModifications = budgetModificationRepository.searchByObjectOrDescription(searchTerm.trim(), pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    /**
     * Get budget modifications by document relationships
     */
    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetModificationsByDemande(Long demandeId, Pageable pageable) {
        log.debug("Getting budget modifications for demande ID: {}", demandeId);

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findByDemande(demandeId, pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetModificationsByResponse(Long responseId, Pageable pageable) {
        log.debug("Getting budget modifications for response ID: {}", responseId);

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findByResponse(responseId, pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    /**
     * Get budget modifications by approval status
     */
    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getPendingModifications(Pageable pageable) {
        log.debug("Getting pending budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findPendingModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getApprovedModifications(Pageable pageable) {
        log.debug("Getting approved budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findApprovedModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getScheduledModifications(Pageable pageable) {
        log.debug("Getting scheduled budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findScheduledModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    /**
     * Get budget modifications by date ranges
     */
    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetModificationsByApprovalDateRange(Date startDate, Date endDate, Pageable pageable) {
        log.debug("Getting budget modifications between {} and {}", startDate, endDate);

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findByApprovalDateRange(startDate, endDate, pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getCurrentYearModifications(Pageable pageable) {
        log.debug("Getting current year budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findCurrentYearModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetModificationsByYear(int year, Pageable pageable) {
        log.debug("Getting budget modifications for year: {}", year);

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findByYear(year, pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getCurrentMonthModifications(Pageable pageable) {
        log.debug("Getting current month budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findCurrentMonthModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getRecentModifications(Pageable pageable) {
        log.debug("Getting recent budget modifications");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date thirtyDaysAgo = cal.getTime();

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findRecentModifications(thirtyDaysAgo, pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    /**
     * Get budget modifications by type
     */
    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetIncreaseModifications(Pageable pageable) {
        log.debug("Getting budget increase modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findBudgetIncreaseModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetDecreaseModifications(Pageable pageable) {
        log.debug("Getting budget decrease modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findBudgetDecreaseModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetReallocationModifications(Pageable pageable) {
        log.debug("Getting budget reallocation modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findBudgetReallocationModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getEmergencyModifications(Pageable pageable) {
        log.debug("Getting emergency budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findEmergencyModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getCorrectionModifications(Pageable pageable) {
        log.debug("Getting correction budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findCorrectionModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    /**
     * Get special categories
     */
    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getOverdueModifications(Pageable pageable) {
        log.debug("Getting overdue budget modifications");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findOverdueModifications(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getModificationsRequiringImmediateAttention(Pageable pageable) {
        log.debug("Getting budget modifications requiring immediate attention");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findRequiringImmediateAttention(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getBudgetModificationsByDocumentType(String documentType, Pageable pageable) {
        log.debug("Getting budget modifications by document type: {}", documentType);

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findByDocumentType(documentType, pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BudgetModificationDTO> getModificationsWithMissingInformation(Pageable pageable) {
        log.debug("Getting budget modifications with missing information");

        Page<BudgetModification> budgetModifications = budgetModificationRepository.findWithMissingInformation(pageable);
        return budgetModifications.map(BudgetModificationDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update budget modification
     */
    public BudgetModificationDTO updateBudgetModification(Long id, BudgetModificationDTO budgetModificationDTO) {
        log.info("Updating budget modification with ID: {}", id);

        BudgetModification existingBudgetModification = getBudgetModificationEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(budgetModificationDTO, "update");
        validateBusinessRules(budgetModificationDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(budgetModificationDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(budgetModificationDTO, existingBudgetModification);

        // Handle foreign key relationships
        setEntityRelationships(budgetModificationDTO, existingBudgetModification);

        BudgetModification updatedBudgetModification = budgetModificationRepository.save(existingBudgetModification);
        log.info("Successfully updated budget modification with ID: {}", id);

        return BudgetModificationDTO.fromEntityWithRelations(updatedBudgetModification);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete budget modification
     */
    public void deleteBudgetModification(Long id) {
        log.info("Deleting budget modification with ID: {}", id);

        BudgetModification budgetModification = getBudgetModificationEntityById(id);
        
        // Check if budget modification is referenced by planned items before deletion
        // Note: This would require a check in PlannedItem repository if needed
        
        budgetModificationRepository.delete(budgetModification);

        log.info("Successfully deleted budget modification with ID: {}", id);
    }

    /**
     * Delete budget modification by ID (direct)
     */
    public void deleteBudgetModificationById(Long id) {
        log.info("Deleting budget modification by ID: {}", id);

        if (!budgetModificationRepository.existsById(id)) {
            throw new RuntimeException("Budget modification not found with ID: " + id);
        }

        budgetModificationRepository.deleteById(id);
        log.info("Successfully deleted budget modification with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if budget modification exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return budgetModificationRepository.existsById(id);
    }

    /**
     * Check if unique constraint exists
     */
    @Transactional(readOnly = true)
    public boolean existsByApprovalDateAndDemande(Date approvalDate, Long demandeId) {
        return budgetModificationRepository.existsByApprovalDateAndDemande(approvalDate, demandeId);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countBudgetModificationsByDemande(Long demandeId) {
        return budgetModificationRepository.countByDemande(demandeId);
    }

    @Transactional(readOnly = true)
    public Long countBudgetModificationsByResponse(Long responseId) {
        return budgetModificationRepository.countByResponse(responseId);
    }

    @Transactional(readOnly = true)
    public Long countAllBudgetModifications() {
        return budgetModificationRepository.countAllBudgetModifications();
    }

    @Transactional(readOnly = true)
    public Long countPendingModifications() {
        return budgetModificationRepository.countPendingModifications();
    }

    @Transactional(readOnly = true)
    public Long countApprovedModifications() {
        return budgetModificationRepository.countApprovedModifications();
    }

    @Transactional(readOnly = true)
    public Long countScheduledModifications() {
        return budgetModificationRepository.countScheduledModifications();
    }

    @Transactional(readOnly = true)
    public Long countCurrentYearModifications() {
        return budgetModificationRepository.countCurrentYearModifications();
    }

    @Transactional(readOnly = true)
    public Long countBudgetModificationsByYear(int year) {
        return budgetModificationRepository.countByYear(year);
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(BudgetModificationDTO dto, BudgetModification entity) {
        entity.setObject(dto.getObject()); // F_01
        entity.setDescription(dto.getDescription()); // F_02
        entity.setApprovalDate(dto.getApprovalDate()); // F_03
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(BudgetModificationDTO dto, BudgetModification entity) {
        // F_04 - Demande Document (required)
        if (dto.getDemandeId() != null) {
            entity.setDemande(documentRepository.findById(dto.getDemandeId())
                    .orElseThrow(() -> new RuntimeException("Demande document not found with ID: " + dto.getDemandeId())));
        }

        // F_05 - Response Document (required)
        if (dto.getResponseId() != null) {
            entity.setResponse(documentRepository.findById(dto.getResponseId())
                    .orElseThrow(() -> new RuntimeException("Response document not found with ID: " + dto.getResponseId())));
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(BudgetModificationDTO dto, String operation) {
        if (dto.getDemandeId() == null) {
            throw new RuntimeException("Demande document is required for " + operation);
        }
        if (dto.getResponseId() == null) {
            throw new RuntimeException("Response document is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(BudgetModificationDTO dto, String operation) {
        // Validate field lengths
        if (dto.getObject() != null && dto.getObject().length() > 200) {
            throw new RuntimeException("Object cannot exceed 200 characters for " + operation);
        }
        
        if (dto.getDescription() != null && dto.getDescription().length() > 500) {
            throw new RuntimeException("Description cannot exceed 500 characters for " + operation);
        }

        // Validate approval date logic
        if (dto.getApprovalDate() != null) {
            //Date now = new Date();
            // Allow approval dates in the past (already approved) or future (scheduled)
            // No specific restrictions based on current requirements
            
            // Optional: Add business rules for approval date validation
            // For example, prevent approval dates too far in the future
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 2); // 2 years in the future
            Date maxFutureDate = cal.getTime();
            
            if (dto.getApprovalDate().after(maxFutureDate)) {
                log.warn("Approval date is more than 2 years in the future for {}: {}", operation, dto.getApprovalDate());
            }
        }

        // Business validation: Check for meaningful content
        if ((dto.getObject() == null || dto.getObject().trim().isEmpty()) && 
            (dto.getDescription() == null || dto.getDescription().trim().isEmpty())) {
            log.warn("Budget modification has no object or description for {}", operation);
        }
    }

    /**
     * Validate unique constraints (T_02_02_07_UK_01: approvalDate + demande)
     */
    private void validateUniqueConstraints(BudgetModificationDTO dto, Long excludeId) {
        // Check unique constraint: approval date + demande combination
        if (dto.getApprovalDate() != null && dto.getDemandeId() != null) {
            if (excludeId == null) {
                if (budgetModificationRepository.existsByApprovalDateAndDemande(dto.getApprovalDate(), dto.getDemandeId())) {
                    throw new RuntimeException("Budget modification with approval date '" + 
                        dto.getApprovalDate() + "' and demande ID '" + dto.getDemandeId() + "' already exists");
                }
            } else {
                if (budgetModificationRepository.existsByApprovalDateAndDemandeAndIdNot(dto.getApprovalDate(), dto.getDemandeId(), excludeId)) {
                    throw new RuntimeException("Another budget modification with approval date '" + 
                        dto.getApprovalDate() + "' and demande ID '" + dto.getDemandeId() + "' already exists");
                }
            }
        }
    }
}