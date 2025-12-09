/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RubricService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.plan.dto.RubricDTO;
import dz.mdn.iaas.business.plan.model.Rubric;
import dz.mdn.iaas.business.plan.repository.DomainRepository;
import dz.mdn.iaas.business.plan.repository.RubricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Rubric Service with CRUD operations
 * Handles rubric management operations with multilingual support, domain relationship, and rubric classification
 * Based on exact field names and business rules for rubric management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RubricService {

    private final RubricRepository rubricRepository;
    
    // Repository bean for related entity (injected as needed)
    private final DomainRepository domainRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new rubric
     */
    public RubricDTO createRubric(RubricDTO rubricDTO) {
        log.info("Creating rubric with French designation: {} for domain ID: {}", 
                rubricDTO.getDesignationFr(), rubricDTO.getDomainId());

        // Validate required fields and business rules
        validateRequiredFields(rubricDTO, "create");
        validateBusinessRules(rubricDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(rubricDTO, null);

        // Create entity with exact field mapping
        Rubric rubric = new Rubric();
        mapDtoToEntity(rubricDTO, rubric);

        // Handle foreign key relationships
        setEntityRelationships(rubricDTO, rubric);

        Rubric savedRubric = rubricRepository.save(rubric);
        log.info("Successfully created rubric with ID: {}", savedRubric.getId());

        return RubricDTO.fromEntityWithRelations(savedRubric);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get rubric by ID
     */
    @Transactional(readOnly = true)
    public RubricDTO getRubricById(Long id) {
        log.debug("Getting rubric with ID: {}", id);

        Rubric rubric = rubricRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rubric not found with ID: " + id));

        return RubricDTO.fromEntityWithRelations(rubric);
    }

    /**
     * Get rubric entity by ID
     */
    @Transactional(readOnly = true)
    public Rubric getRubricEntityById(Long id) {
        return rubricRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rubric not found with ID: " + id));
    }

    /**
     * Get all rubrics with pagination
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getAllRubrics(Pageable pageable) {
        log.debug("Getting all rubrics with pagination");

        Page<Rubric> rubrics = rubricRepository.findAllOrderByDomainAndDesignation(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Find one rubric by ID
     */
    @Transactional(readOnly = true)
    public Optional<RubricDTO> findOne(Long id) {
        log.debug("Finding rubric by ID: {}", id);

        return rubricRepository.findById(id)
                .map(RubricDTO::fromEntityWithRelations);
    }

    /**
     * Find rubric by French designation (unique)
     */
    @Transactional(readOnly = true)
    public Optional<RubricDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding rubric by French designation: {}", designationFr);

        return rubricRepository.findByDesignationFr(designationFr)
                .map(RubricDTO::fromEntity);
    }

    /**
     * Search rubrics by designation
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> searchRubricsByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching rubrics by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllRubrics(pageable);
        }

        Page<Rubric> rubrics = rubricRepository.searchByDesignation(searchTerm.trim(), pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get rubrics by domain
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRubricsByDomain(Long domainId, Pageable pageable) {
        log.debug("Getting rubrics for domain ID: {}", domainId);

        Page<Rubric> rubrics = rubricRepository.findByDomain(domainId, pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get rubrics with items
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRubricsWithItems(Pageable pageable) {
        log.debug("Getting rubrics with items");

        Page<Rubric> rubrics = rubricRepository.findRubricsWithItems(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get rubrics without items
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRubricsWithoutItems(Pageable pageable) {
        log.debug("Getting rubrics without items");

        Page<Rubric> rubrics = rubricRepository.findRubricsWithoutItems(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get rubrics by items count range
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRubricsByItemsCountRange(int minCount, int maxCount, Pageable pageable) {
        log.debug("Getting rubrics with items count between {} and {}", minCount, maxCount);

        Page<Rubric> rubrics = rubricRepository.findByItemsCountRange(minCount, maxCount, pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get rubrics by category
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRequirementsRubrics(Pageable pageable) {
        log.debug("Getting requirements rubrics");

        Page<Rubric> rubrics = rubricRepository.findRequirementsRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getQualityRubrics(Pageable pageable) {
        log.debug("Getting quality rubrics");

        Page<Rubric> rubrics = rubricRepository.findQualityRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getPerformanceRubrics(Pageable pageable) {
        log.debug("Getting performance rubrics");

        Page<Rubric> rubrics = rubricRepository.findPerformanceRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getSecurityRubrics(Pageable pageable) {
        log.debug("Getting security rubrics");

        Page<Rubric> rubrics = rubricRepository.findSecurityRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getComplianceRubrics(Pageable pageable) {
        log.debug("Getting compliance rubrics");

        Page<Rubric> rubrics = rubricRepository.findComplianceRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getTechnicalRubrics(Pageable pageable) {
        log.debug("Getting technical rubrics");

        Page<Rubric> rubrics = rubricRepository.findTechnicalRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getOperationalRubrics(Pageable pageable) {
        log.debug("Getting operational rubrics");

        Page<Rubric> rubrics = rubricRepository.findOperationalRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getTrainingRubrics(Pageable pageable) {
        log.debug("Getting training rubrics");

        Page<Rubric> rubrics = rubricRepository.findTrainingRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getDocumentationRubrics(Pageable pageable) {
        log.debug("Getting documentation rubrics");

        Page<Rubric> rubrics = rubricRepository.findDocumentationRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get complexity-based rubrics
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getHighComplexityRubrics(Pageable pageable) {
        log.debug("Getting high complexity rubrics");

        Page<Rubric> rubrics = rubricRepository.findHighComplexityRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getMediumComplexityRubrics(Pageable pageable) {
        log.debug("Getting medium complexity rubrics");

        Page<Rubric> rubrics = rubricRepository.findMediumComplexityRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RubricDTO> getLowComplexityRubrics(Pageable pageable) {
        log.debug("Getting low complexity rubrics");

        Page<Rubric> rubrics = rubricRepository.findLowComplexityRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get priority-based rubrics
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRubricsByPriorityLevel(String priority, Pageable pageable) {
        log.debug("Getting rubrics by priority level: {}", priority);

        Page<Rubric> rubrics = rubricRepository.findByPriorityLevel(priority, pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get rubrics requiring critical attention
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRubricsRequiringCriticalAttention(Pageable pageable) {
        log.debug("Getting rubrics requiring critical attention");

        Page<Rubric> rubrics = rubricRepository.findRequiringCriticalAttention(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get multilingual rubrics
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getMultilingualRubrics(Pageable pageable) {
        log.debug("Getting multilingual rubrics");

        Page<Rubric> rubrics = rubricRepository.findMultilingualRubrics(pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    /**
     * Get rubrics by domain category
     */
    @Transactional(readOnly = true)
    public Page<RubricDTO> getRubricsByDomainCategory(String category, Pageable pageable) {
        log.debug("Getting rubrics by domain category: {}", category);

        Page<Rubric> rubrics = rubricRepository.findByDomainCategory(category, pageable);
        return rubrics.map(RubricDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update rubric
     */
    public RubricDTO updateRubric(Long id, RubricDTO rubricDTO) {
        log.info("Updating rubric with ID: {}", id);

        Rubric existingRubric = getRubricEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(rubricDTO, "update");
        validateBusinessRules(rubricDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(rubricDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(rubricDTO, existingRubric);

        // Handle foreign key relationships
        setEntityRelationships(rubricDTO, existingRubric);

        Rubric updatedRubric = rubricRepository.save(existingRubric);
        log.info("Successfully updated rubric with ID: {}", id);

        return RubricDTO.fromEntityWithRelations(updatedRubric);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete rubric
     */
    public void deleteRubric(Long id) {
        log.info("Deleting rubric with ID: {}", id);

        Rubric rubric = getRubricEntityById(id);
        
        // Check if rubric has items before deletion
        if (rubric.getItems() != null && !rubric.getItems().isEmpty()) {
            throw new RuntimeException("Cannot delete rubric with ID " + id + 
                " because it has " + rubric.getItems().size() + " associated items");
        }
        
        rubricRepository.delete(rubric);

        log.info("Successfully deleted rubric with ID: {}", id);
    }

    /**
     * Delete rubric by ID (direct)
     */
    public void deleteRubricById(Long id) {
        log.info("Deleting rubric by ID: {}", id);

        if (!rubricRepository.existsById(id)) {
            throw new RuntimeException("Rubric not found with ID: " + id);
        }

        // Check for associated items
        Rubric rubric = getRubricEntityById(id);
        if (rubric.getItems() != null && !rubric.getItems().isEmpty()) {
            throw new RuntimeException("Cannot delete rubric with ID " + id + 
                " because it has " + rubric.getItems().size() + " associated items");
        }

        rubricRepository.deleteById(id);
        log.info("Successfully deleted rubric with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if rubric exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return rubricRepository.existsById(id);
    }

    /**
     * Check if French designation exists
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return rubricRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countRubricsByDomain(Long domainId) {
        return rubricRepository.countByDomain(domainId);
    }

    @Transactional(readOnly = true)
    public Long countAllRubrics() {
        return rubricRepository.countAllRubrics();
    }

    @Transactional(readOnly = true)
    public Long countRubricsWithItems() {
        return rubricRepository.countRubricsWithItems();
    }

    @Transactional(readOnly = true)
    public Long countRubricsWithoutItems() {
        return rubricRepository.countRubricsWithoutItems();
    }

    @Transactional(readOnly = true)
    public Long countRequirementsRubrics() {
        return rubricRepository.countRequirementsRubrics();
    }

    @Transactional(readOnly = true)
    public Long countQualityRubrics() {
        return rubricRepository.countQualityRubrics();
    }

    /**
     * Get items statistics
     */
    @Transactional(readOnly = true)
    public Double getAverageItemsPerRubric() {
        return rubricRepository.getAverageItemsPerRubric();
    }

    @Transactional(readOnly = true)
    public Integer getMaxItemsCount() {
        return rubricRepository.getMaxItemsCount();
    }

    @Transactional(readOnly = true)
    public Integer getMinItemsCountExcludingZero() {
        return rubricRepository.getMinItemsCountExcludingZero();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(RubricDTO dto, Rubric entity) {
        entity.setDesignationAr(dto.getDesignationAr()); // F_01
        entity.setDesignationEn(dto.getDesignationEn()); // F_02
        entity.setDesignationFr(dto.getDesignationFr()); // F_03
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(RubricDTO dto, Rubric entity) {
        // F_04 - Domain (required)
        if (dto.getDomainId() != null) {
            entity.setDomain(domainRepository.findById(dto.getDomainId())
                    .orElseThrow(() -> new RuntimeException("Domain not found with ID: " + dto.getDomainId())));
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(RubricDTO dto, String operation) {
        if (dto.getDesignationFr() == null || dto.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (dto.getDomainId() == null) {
            throw new RuntimeException("Domain is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(RubricDTO dto, String operation) {
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

        // Validate at least one designation is provided
        boolean hasDesignation = (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty());
        if (!hasDesignation) {
            throw new RuntimeException("At least French designation must be provided for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(RubricDTO dto, Long excludeId) {
        // Check French designation uniqueness (T_02_02_05_UK_01)
        if (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty()) {
            if (excludeId == null) {
                if (rubricRepository.existsByDesignationFr(dto.getDesignationFr())) {
                    throw new RuntimeException("Rubric with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            } else {
                if (rubricRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), excludeId)) {
                    throw new RuntimeException("Another rubric with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            }
        }
    }
}