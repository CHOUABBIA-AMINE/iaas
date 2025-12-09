/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusionService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.provider.dto.ProviderExclusionDTO;
import dz.mdn.iaas.business.provider.model.ProviderExclusion;
import dz.mdn.iaas.business.provider.repository.ExclusionTypeRepository;
import dz.mdn.iaas.business.provider.repository.ProviderExclusionRepository;
import dz.mdn.iaas.business.provider.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Provider Exclusion Service with CRUD operations
 * Handles provider exclusion management operations with temporal logic and compliance monitoring
 * Based on exact field names and business rules for exclusion management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProviderExclusionService {

    private final ProviderExclusionRepository providerExclusionRepository;
    
    // Repository beans for related entities (injected as needed)
    private final ExclusionTypeRepository exclusionTypeRepository;
    private final ProviderRepository providerRepository;
    private final dz.mdn.iaas.common.communication.repository.MailRepository mailRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new provider exclusion
     */
    public ProviderExclusionDTO createProviderExclusion(ProviderExclusionDTO providerExclusionDTO) {
        log.info("Creating provider exclusion for provider ID: {}, exclusion type ID: {}", 
                providerExclusionDTO.getProviderId(), providerExclusionDTO.getExclusionTypeId());

        // Validate required fields and business rules
        validateRequiredFields(providerExclusionDTO, "create");
        validateBusinessRules(providerExclusionDTO, "create");

        // Check for overlapping exclusions
        validateNoOverlappingExclusions(providerExclusionDTO, null);

        // Create entity with exact field mapping
        ProviderExclusion providerExclusion = new ProviderExclusion();
        mapDtoToEntity(providerExclusionDTO, providerExclusion);

        // Handle foreign key relationships
        setEntityRelationships(providerExclusionDTO, providerExclusion);

        ProviderExclusion savedProviderExclusion = providerExclusionRepository.save(providerExclusion);
        log.info("Successfully created provider exclusion with ID: {}", savedProviderExclusion.getId());

        return ProviderExclusionDTO.fromEntityWithRelations(savedProviderExclusion);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get provider exclusion by ID
     */
    @Transactional(readOnly = true)
    public ProviderExclusionDTO getProviderExclusionById(Long id) {
        log.debug("Getting provider exclusion with ID: {}", id);

        ProviderExclusion providerExclusion = providerExclusionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider exclusion not found with ID: " + id));

        return ProviderExclusionDTO.fromEntityWithRelations(providerExclusion);
    }

    /**
     * Get provider exclusion entity by ID
     */
    @Transactional(readOnly = true)
    public ProviderExclusion getProviderExclusionEntityById(Long id) {
        return providerExclusionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider exclusion not found with ID: " + id));
    }

    /**
     * Get all provider exclusions with pagination
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getAllProviderExclusions(Pageable pageable) {
        log.debug("Getting all provider exclusions with pagination");

        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findAllOrderByStartDate(pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Find one provider exclusion by ID
     */
    @Transactional(readOnly = true)
    public Optional<ProviderExclusionDTO> findOne(Long id) {
        log.debug("Finding provider exclusion by ID: {}", id);

        return providerExclusionRepository.findById(id)
                .map(ProviderExclusionDTO::fromEntityWithRelations);
    }

    /**
     * Get provider exclusions by provider
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getProviderExclusionsByProvider(Long providerId, Pageable pageable) {
        log.debug("Getting provider exclusions for provider ID: {}", providerId);

        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findByProvider(providerId, pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Get provider exclusions by exclusion type
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getProviderExclusionsByType(Long exclusionTypeId, Pageable pageable) {
        log.debug("Getting provider exclusions for exclusion type ID: {}", exclusionTypeId);

        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findByExclusionType(exclusionTypeId, pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Get active provider exclusions
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getActiveProviderExclusions(Pageable pageable) {
        log.debug("Getting active provider exclusions");

        Date currentDate = new Date();
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findActiveExclusions(currentDate, pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Get active exclusions for a specific provider
     */
    @Transactional(readOnly = true)
    public List<ProviderExclusionDTO> getActiveExclusionsForProvider(Long providerId) {
        log.debug("Getting active exclusions for provider ID: {}", providerId);

        Date currentDate = new Date();
        List<ProviderExclusion> providerExclusions = providerExclusionRepository.findActiveExclusionsForProvider(providerId, currentDate);
        return providerExclusions.stream().map(ProviderExclusionDTO::fromEntity).toList();
    }

    /**
     * Get expired provider exclusions
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getExpiredProviderExclusions(Pageable pageable) {
        log.debug("Getting expired provider exclusions");

        Date currentDate = new Date();
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findExpiredExclusions(currentDate, pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Get permanent exclusions
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getPermanentExclusions(Pageable pageable) {
        log.debug("Getting permanent provider exclusions");

        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findPermanentExclusions(pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Get future exclusions
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getFutureExclusions(Pageable pageable) {
        log.debug("Getting future provider exclusions");

        Date currentDate = new Date();
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findFutureExclusions(currentDate, pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Get exclusions expiring soon (within 30 days)
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getExclusionsExpiringSoon(Pageable pageable) {
        log.debug("Getting exclusions expiring soon");

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + (30L * 24 * 60 * 60 * 1000)); // 30 days from now
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findExclusionsExpiringSoon(currentDate, expirationDate, pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Get exclusions by category
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getCriminalExclusions(Pageable pageable) {
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findCriminalExclusions(pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getFinancialExclusions(Pageable pageable) {
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findFinancialExclusions(pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getLegalExclusions(Pageable pageable) {
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findLegalExclusions(pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getAdministrativeExclusions(Pageable pageable) {
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findAdministrativeExclusions(pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> getSecurityExclusions(Pageable pageable) {
        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.findSecurityExclusions(pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    /**
     * Search exclusions by cause
     */
    @Transactional(readOnly = true)
    public Page<ProviderExclusionDTO> searchExclusionsByCause(String searchTerm, Pageable pageable) {
        log.debug("Searching exclusions by cause with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProviderExclusions(pageable);
        }

        Page<ProviderExclusion> providerExclusions = providerExclusionRepository.searchByCause(searchTerm.trim(), pageable);
        return providerExclusions.map(ProviderExclusionDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update provider exclusion
     */
    public ProviderExclusionDTO updateProviderExclusion(Long id, ProviderExclusionDTO providerExclusionDTO) {
        log.info("Updating provider exclusion with ID: {}", id);

        ProviderExclusion existingProviderExclusion = getProviderExclusionEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(providerExclusionDTO, "update");
        validateBusinessRules(providerExclusionDTO, "update");

        // Check for overlapping exclusions (excluding current record)
        validateNoOverlappingExclusions(providerExclusionDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(providerExclusionDTO, existingProviderExclusion);

        // Handle foreign key relationships
        setEntityRelationships(providerExclusionDTO, existingProviderExclusion);

        ProviderExclusion updatedProviderExclusion = providerExclusionRepository.save(existingProviderExclusion);
        log.info("Successfully updated provider exclusion with ID: {}", id);

        return ProviderExclusionDTO.fromEntityWithRelations(updatedProviderExclusion);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete provider exclusion
     */
    public void deleteProviderExclusion(Long id) {
        log.info("Deleting provider exclusion with ID: {}", id);

        ProviderExclusion providerExclusion = getProviderExclusionEntityById(id);
        providerExclusionRepository.delete(providerExclusion);

        log.info("Successfully deleted provider exclusion with ID: {}", id);
    }

    /**
     * Delete provider exclusion by ID (direct)
     */
    public void deleteProviderExclusionById(Long id) {
        log.info("Deleting provider exclusion by ID: {}", id);

        if (!providerExclusionRepository.existsById(id)) {
            throw new RuntimeException("Provider exclusion not found with ID: " + id);
        }

        providerExclusionRepository.deleteById(id);
        log.info("Successfully deleted provider exclusion with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if provider exclusion exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return providerExclusionRepository.existsById(id);
    }

    /**
     * Check if provider has active exclusions
     */
    @Transactional(readOnly = true)
    public boolean hasActiveExclusions(Long providerId) {
        Date currentDate = new Date();
        return providerExclusionRepository.hasActiveExclusions(providerId, currentDate);
    }

    /**
     * Check if provider has permanent exclusions
     */
    @Transactional(readOnly = true)
    public boolean hasPermanentExclusions(Long providerId) {
        return providerExclusionRepository.hasPermanentExclusions(providerId);
    }

    /**
     * Get count of active exclusions for provider
     */
    @Transactional(readOnly = true)
    public Long countActiveExclusionsForProvider(Long providerId) {
        Date currentDate = new Date();
        return providerExclusionRepository.countActiveExclusionsForProvider(providerId, currentDate);
    }

    /**
     * Get total count of exclusions for provider
     */
    @Transactional(readOnly = true)
    public Long countTotalExclusionsForProvider(Long providerId) {
        return providerExclusionRepository.countTotalExclusionsForProvider(providerId);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countActiveExclusions() {
        Date currentDate = new Date();
        return providerExclusionRepository.countActiveExclusions(currentDate);
    }

    @Transactional(readOnly = true)
    public Long countPermanentExclusions() {
        return providerExclusionRepository.countPermanentExclusions();
    }

    @Transactional(readOnly = true)
    public Long countExpiredExclusions() {
        Date currentDate = new Date();
        return providerExclusionRepository.countExpiredExclusions(currentDate);
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(ProviderExclusionDTO dto, ProviderExclusion entity) {
        entity.setStartDate(dto.getStartDate()); // F_01
        entity.setEndDate(dto.getEndDate()); // F_02
        entity.setCause(dto.getCause()); // F_03
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(ProviderExclusionDTO dto, ProviderExclusion entity) {
        // F_04 - ExclusionType (required)
        if (dto.getExclusionTypeId() != null) {
            entity.setExclusionType(exclusionTypeRepository.findById(dto.getExclusionTypeId())
                    .orElseThrow(() -> new RuntimeException("Exclusion type not found with ID: " + dto.getExclusionTypeId())));
        }

        // F_05 - Provider (required)
        if (dto.getProviderId() != null) {
            entity.setProvider(providerRepository.findById(dto.getProviderId())
                    .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + dto.getProviderId())));
        }

        // F_06 - Mail reference (optional)
        if (dto.getReferenceId() != null) {
            entity.setReference(mailRepository.findById(dto.getReferenceId())
                    .orElseThrow(() -> new RuntimeException("Mail reference not found with ID: " + dto.getReferenceId())));
        } else {
            entity.setReference(null);
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(ProviderExclusionDTO dto, String operation) {
        if (dto.getStartDate() == null) {
            throw new RuntimeException("Start date is required for " + operation);
        }
        if (dto.getExclusionTypeId() == null) {
            throw new RuntimeException("Exclusion type is required for " + operation);
        }
        if (dto.getProviderId() == null) {
            throw new RuntimeException("Provider is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(ProviderExclusionDTO dto, String operation) {
        // End date must be after start date if provided
        if (dto.getEndDate() != null && dto.getStartDate() != null && dto.getEndDate().before(dto.getStartDate())) {
            throw new RuntimeException("End date must be after start date for " + operation);
        }

        // Start date cannot be too far in the future (more than 1 year)
        if (dto.getStartDate() != null) {
            Date oneYearFromNow = new Date(System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000));
            if (dto.getStartDate().after(oneYearFromNow)) {
                throw new RuntimeException("Start date cannot be more than one year in the future for " + operation);
            }
        }
    }

    /**
     * Validate no overlapping exclusions of the same type for the same provider
     */
    private void validateNoOverlappingExclusions(ProviderExclusionDTO dto, Long excludeId) {
        if (dto.getProviderId() == null || dto.getExclusionTypeId() == null) {
            return; // Cannot check without these IDs
        }

        List<ProviderExclusion> existingExclusions = providerExclusionRepository.findByProviderAndType(
                dto.getProviderId(), dto.getExclusionTypeId());

        for (ProviderExclusion existing : existingExclusions) {
            // Skip the current record when updating
            if (excludeId != null && existing.getId().equals(excludeId)) {
                continue;
            }

            // Check for overlap
            Date existingStart = existing.getStartDate();
            Date existingEnd = existing.getEndDate(); // May be null for permanent exclusions
            
            Date newStart = dto.getStartDate();
            Date newEnd = dto.getEndDate(); // May be null for permanent exclusions

            // If either exclusion is permanent (no end date), check if they overlap at start
            if (existingEnd == null || newEnd == null) {
                if (newStart.equals(existingStart) || 
                    (newStart.before(existingStart) && (newEnd == null || newEnd.after(existingStart))) ||
                    (newStart.after(existingStart) && existingEnd == null)) {
                    throw new RuntimeException("Overlapping exclusion of the same type already exists for this provider");
                }
            } else {
                // Both have end dates, check for period overlap
                if (newStart.before(existingEnd) && newEnd.after(existingStart)) {
                    throw new RuntimeException("Overlapping exclusion of the same type already exists for this provider");
                }
            }
        }
    }
}