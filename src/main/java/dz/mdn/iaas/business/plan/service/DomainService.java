/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DomainService
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

import dz.mdn.iaas.business.plan.dto.DomainDTO;
import dz.mdn.iaas.business.plan.model.Domain;
import dz.mdn.iaas.business.plan.repository.DomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Domain Service with CRUD operations
 * Handles domain management operations with multilingual support and domain classification
 * Based on exact field names and business rules for organizational domain management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DomainService {

    private final DomainRepository domainRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new domain
     */
    public DomainDTO createDomain(DomainDTO domainDTO) {
        log.info("Creating domain with French designation: {}", 
                domainDTO.getDesignationFr());

        // Validate required fields and business rules
        validateRequiredFields(domainDTO, "create");
        validateBusinessRules(domainDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(domainDTO, null);

        // Create entity with exact field mapping
        Domain domain = new Domain();
        mapDtoToEntity(domainDTO, domain);

        Domain savedDomain = domainRepository.save(domain);
        log.info("Successfully created domain with ID: {}", savedDomain.getId());

        return DomainDTO.fromEntity(savedDomain);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get domain by ID
     */
    @Transactional(readOnly = true)
    public DomainDTO getDomainById(Long id) {
        log.debug("Getting domain with ID: {}", id);

        Domain domain = domainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domain not found with ID: " + id));

        return DomainDTO.fromEntityWithRelations(domain);
    }

    /**
     * Get domain entity by ID
     */
    @Transactional(readOnly = true)
    public Domain getDomainEntityById(Long id) {
        return domainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domain not found with ID: " + id));
    }

    /**
     * Get all domains with pagination
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getAllDomains(Pageable pageable) {
        log.debug("Getting all domains with pagination");

        Page<Domain> domains = domainRepository.findAllOrderByDesignationFr(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Find one domain by ID
     */
    @Transactional(readOnly = true)
    public Optional<DomainDTO> findOne(Long id) {
        log.debug("Finding domain by ID: {}", id);

        return domainRepository.findById(id)
                .map(DomainDTO::fromEntityWithRelations);
    }

    /**
     * Find domain by French designation (unique)
     */
    @Transactional(readOnly = true)
    public Optional<DomainDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding domain by French designation: {}", designationFr);

        return domainRepository.findByDesignationFr(designationFr)
                .map(DomainDTO::fromEntity);
    }

    /**
     * Search domains by designation
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> searchDomainsByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching domains by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllDomains(pageable);
        }

        Page<Domain> domains = domainRepository.searchByDesignation(searchTerm.trim(), pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get domains with rubrics
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getDomainsWithRubrics(Pageable pageable) {
        log.debug("Getting domains with rubrics");

        Page<Domain> domains = domainRepository.findDomainsWithRubrics(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get domains without rubrics
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getDomainsWithoutRubrics(Pageable pageable) {
        log.debug("Getting domains without rubrics");

        Page<Domain> domains = domainRepository.findDomainsWithoutRubrics(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get domains by rubrics count range
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getDomainsByRubricsCountRange(int minCount, int maxCount, Pageable pageable) {
        log.debug("Getting domains with rubrics count between {} and {}", minCount, maxCount);

        Page<Domain> domains = domainRepository.findByRubricsCountRange(minCount, maxCount, pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get domains by category
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getTechnicalDomains(Pageable pageable) {
        log.debug("Getting technical domains");

        Page<Domain> domains = domainRepository.findTechnicalDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getAdministrativeDomains(Pageable pageable) {
        log.debug("Getting administrative domains");

        Page<Domain> domains = domainRepository.findAdministrativeDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getOperationalDomains(Pageable pageable) {
        log.debug("Getting operational domains");

        Page<Domain> domains = domainRepository.findOperationalDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getStrategicDomains(Pageable pageable) {
        log.debug("Getting strategic domains");

        Page<Domain> domains = domainRepository.findStrategicDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getFinancialDomains(Pageable pageable) {
        log.debug("Getting financial domains");

        Page<Domain> domains = domainRepository.findFinancialDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getSecurityDomains(Pageable pageable) {
        log.debug("Getting security domains");

        Page<Domain> domains = domainRepository.findSecurityDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getHRDomains(Pageable pageable) {
        log.debug("Getting HR domains");

        Page<Domain> domains = domainRepository.findHRDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getLogisticsDomains(Pageable pageable) {
        log.debug("Getting logistics domains");

        Page<Domain> domains = domainRepository.findLogisticsDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getTrainingDomains(Pageable pageable) {
        log.debug("Getting training domains");

        Page<Domain> domains = domainRepository.findTrainingDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get complexity-based domains
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getHighComplexityDomains(Pageable pageable) {
        log.debug("Getting high complexity domains");

        Page<Domain> domains = domainRepository.findHighComplexityDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getMediumComplexityDomains(Pageable pageable) {
        log.debug("Getting medium complexity domains");

        Page<Domain> domains = domainRepository.findMediumComplexityDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<DomainDTO> getLowComplexityDomains(Pageable pageable) {
        log.debug("Getting low complexity domains");

        Page<Domain> domains = domainRepository.findLowComplexityDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get priority-based domains
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getDomainsByPriorityLevel(String priority, Pageable pageable) {
        log.debug("Getting domains by priority level: {}", priority);

        Page<Domain> domains = domainRepository.findByPriorityLevel(priority, pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get domains requiring executive oversight
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getDomainsRequiringExecutiveOversight(Pageable pageable) {
        log.debug("Getting domains requiring executive oversight");

        Page<Domain> domains = domainRepository.findRequiringExecutiveOversight(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    /**
     * Get multilingual domains
     */
    @Transactional(readOnly = true)
    public Page<DomainDTO> getMultilingualDomains(Pageable pageable) {
        log.debug("Getting multilingual domains");

        Page<Domain> domains = domainRepository.findMultilingualDomains(pageable);
        return domains.map(DomainDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update domain
     */
    public DomainDTO updateDomain(Long id, DomainDTO domainDTO) {
        log.info("Updating domain with ID: {}", id);

        Domain existingDomain = getDomainEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(domainDTO, "update");
        validateBusinessRules(domainDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(domainDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(domainDTO, existingDomain);

        Domain updatedDomain = domainRepository.save(existingDomain);
        log.info("Successfully updated domain with ID: {}", id);

        return DomainDTO.fromEntityWithRelations(updatedDomain);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete domain
     */
    public void deleteDomain(Long id) {
        log.info("Deleting domain with ID: {}", id);

        Domain domain = getDomainEntityById(id);
        
        // Check if domain has rubrics before deletion
        if (domain.getRubrics() != null && !domain.getRubrics().isEmpty()) {
            throw new RuntimeException("Cannot delete domain with ID " + id + 
                " because it has " + domain.getRubrics().size() + " associated rubrics");
        }
        
        domainRepository.delete(domain);

        log.info("Successfully deleted domain with ID: {}", id);
    }

    /**
     * Delete domain by ID (direct)
     */
    public void deleteDomainById(Long id) {
        log.info("Deleting domain by ID: {}", id);

        if (!domainRepository.existsById(id)) {
            throw new RuntimeException("Domain not found with ID: " + id);
        }

        // Check for associated rubrics
        Domain domain = getDomainEntityById(id);
        if (domain.getRubrics() != null && !domain.getRubrics().isEmpty()) {
            throw new RuntimeException("Cannot delete domain with ID " + id + 
                " because it has " + domain.getRubrics().size() + " associated rubrics");
        }

        domainRepository.deleteById(id);
        log.info("Successfully deleted domain with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if domain exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return domainRepository.existsById(id);
    }

    /**
     * Check if French designation exists
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return domainRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countAllDomains() {
        return domainRepository.countAllDomains();
    }

    @Transactional(readOnly = true)
    public Long countDomainsWithRubrics() {
        return domainRepository.countDomainsWithRubrics();
    }

    @Transactional(readOnly = true)
    public Long countDomainsWithoutRubrics() {
        return domainRepository.countDomainsWithoutRubrics();
    }

    @Transactional(readOnly = true)
    public Long countTechnicalDomains() {
        return domainRepository.countTechnicalDomains();
    }

    @Transactional(readOnly = true)
    public Long countOperationalDomains() {
        return domainRepository.countOperationalDomains();
    }

    /**
     * Get rubrics statistics
     */
    @Transactional(readOnly = true)
    public Double getAverageRubricsPerDomain() {
        return domainRepository.getAverageRubricsPerDomain();
    }

    @Transactional(readOnly = true)
    public Integer getMaxRubricsCount() {
        return domainRepository.getMaxRubricsCount();
    }

    @Transactional(readOnly = true)
    public Integer getMinRubricsCountExcludingZero() {
        return domainRepository.getMinRubricsCountExcludingZero();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(DomainDTO dto, Domain entity) {
        entity.setDesignationAr(dto.getDesignationAr()); // F_01
        entity.setDesignationEn(dto.getDesignationEn()); // F_02
        entity.setDesignationFr(dto.getDesignationFr()); // F_03
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(DomainDTO dto, String operation) {
        if (dto.getDesignationFr() == null || dto.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(DomainDTO dto, String operation) {
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
    private void validateUniqueConstraints(DomainDTO dto, Long excludeId) {
        // Check French designation uniqueness (T_02_02_04_UK_01)
        if (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty()) {
            if (excludeId == null) {
                if (domainRepository.existsByDesignationFr(dto.getDesignationFr())) {
                    throw new RuntimeException("Domain with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            } else {
                if (domainRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), excludeId)) {
                    throw new RuntimeException("Another domain with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            }
        }
    }
}