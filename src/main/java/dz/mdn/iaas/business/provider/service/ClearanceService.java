/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceService
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

import dz.mdn.iaas.business.provider.dto.ClearanceDTO;
import dz.mdn.iaas.business.provider.model.Clearance;
import dz.mdn.iaas.business.provider.repository.ClearanceRepository;
import dz.mdn.iaas.business.provider.repository.ProviderRepository;
import dz.mdn.iaas.business.provider.repository.ProviderRepresentatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Clearance Service with CRUD operations
 * Handles clearance management operations with temporal logic and validity monitoring
 * Based on exact field names and business rules for clearance lifecycle management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClearanceService {

    private final ClearanceRepository clearanceRepository;
    
    // Repository beans for related entities (injected as needed)
    private final ProviderRepository providerRepository;
    private final ProviderRepresentatorRepository providerRepresentatorRepository;
    private final dz.mdn.iaas.common.communication.repository.MailRepository mailRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new clearance
     */
    public ClearanceDTO createClearance(ClearanceDTO clearanceDTO) {
        log.info("Creating clearance for provider ID: {}, representator ID: {}", 
                clearanceDTO.getProviderId(), clearanceDTO.getProviderRepresentatorId());

        // Validate required fields and business rules
        validateRequiredFields(clearanceDTO, "create");
        validateBusinessRules(clearanceDTO, "create");

        // Check for overlapping clearances
        validateNoOverlappingClearances(clearanceDTO, null);

        // Create entity with exact field mapping
        Clearance clearance = new Clearance();
        mapDtoToEntity(clearanceDTO, clearance);

        // Handle foreign key relationships
        setEntityRelationships(clearanceDTO, clearance);

        Clearance savedClearance = clearanceRepository.save(clearance);
        log.info("Successfully created clearance with ID: {}", savedClearance.getId());

        return ClearanceDTO.fromEntityWithRelations(savedClearance);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get clearance by ID
     */
    @Transactional(readOnly = true)
    public ClearanceDTO getClearanceById(Long id) {
        log.debug("Getting clearance with ID: {}", id);

        Clearance clearance = clearanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance not found with ID: " + id));

        return ClearanceDTO.fromEntityWithRelations(clearance);
    }

    /**
     * Get clearance entity by ID
     */
    @Transactional(readOnly = true)
    public Clearance getClearanceEntityById(Long id) {
        return clearanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clearance not found with ID: " + id));
    }

    /**
     * Get all clearances with pagination
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getAllClearances(Pageable pageable) {
        log.debug("Getting all clearances with pagination");

        Page<Clearance> clearances = clearanceRepository.findAllOrderByStartDate(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Find one clearance by ID
     */
    @Transactional(readOnly = true)
    public Optional<ClearanceDTO> findOne(Long id) {
        log.debug("Finding clearance by ID: {}", id);

        return clearanceRepository.findById(id)
                .map(ClearanceDTO::fromEntityWithRelations);
    }

    /**
     * Get clearances by provider
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getClearancesByProvider(Long providerId, Pageable pageable) {
        log.debug("Getting clearances for provider ID: {}", providerId);

        Page<Clearance> clearances = clearanceRepository.findByProvider(providerId, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get clearances by provider representator
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getClearancesByRepresentator(Long representatorId, Pageable pageable) {
        log.debug("Getting clearances for representator ID: {}", representatorId);

        Page<Clearance> clearances = clearanceRepository.findByProviderRepresentator(representatorId, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get clearances by provider and representator
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getClearancesByProviderAndRepresentator(Long providerId, Long representatorId, Pageable pageable) {
        log.debug("Getting clearances for provider ID: {} and representator ID: {}", providerId, representatorId);

        Page<Clearance> clearances = clearanceRepository.findByProviderAndRepresentator(providerId, representatorId, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get active clearances
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getActiveClearances(Pageable pageable) {
        log.debug("Getting active clearances");

        Date currentDate = new Date();
        Page<Clearance> clearances = clearanceRepository.findActiveClearances(currentDate, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get active clearances for a specific provider
     */
    @Transactional(readOnly = true)
    public List<ClearanceDTO> getActiveClearancesForProvider(Long providerId) {
        log.debug("Getting active clearances for provider ID: {}", providerId);

        Date currentDate = new Date();
        List<Clearance> clearances = clearanceRepository.findActiveClearancesForProvider(providerId, currentDate);
        return clearances.stream().map(ClearanceDTO::fromEntity).toList();
    }

    /**
     * Get active clearances for a specific representator
     */
    @Transactional(readOnly = true)
    public List<ClearanceDTO> getActiveClearancesForRepresentator(Long representatorId) {
        log.debug("Getting active clearances for representator ID: {}", representatorId);

        Date currentDate = new Date();
        List<Clearance> clearances = clearanceRepository.findActiveClearancesForRepresentator(representatorId, currentDate);
        return clearances.stream().map(ClearanceDTO::fromEntity).toList();
    }

    /**
     * Get expired clearances
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getExpiredClearances(Pageable pageable) {
        log.debug("Getting expired clearances");

        Date currentDate = new Date();
        Page<Clearance> clearances = clearanceRepository.findExpiredClearances(currentDate, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get permanent clearances
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getPermanentClearances(Pageable pageable) {
        log.debug("Getting permanent clearances");

        Page<Clearance> clearances = clearanceRepository.findPermanentClearances(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get future clearances
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getFutureClearances(Pageable pageable) {
        log.debug("Getting future clearances");

        Date currentDate = new Date();
        Page<Clearance> clearances = clearanceRepository.findFutureClearances(currentDate, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get clearances expiring soon (within 30 days)
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getClearancesExpiringSoon(Pageable pageable) {
        log.debug("Getting clearances expiring soon");

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + (30L * 24 * 60 * 60 * 1000)); // 30 days from now
        Page<Clearance> clearances = clearanceRepository.findClearancesExpiringSoon(currentDate, expirationDate, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get clearances requiring urgent renewal (within 7 days)
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getClearancesRequiringUrgentRenewal(Pageable pageable) {
        log.debug("Getting clearances requiring urgent renewal");

        Date currentDate = new Date();
        Date urgentDate = new Date(currentDate.getTime() + (7L * 24 * 60 * 60 * 1000)); // 7 days from now
        Page<Clearance> clearances = clearanceRepository.findClearancesRequiringUrgentRenewal(currentDate, urgentDate, pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get clearances by duration type
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getShortTermClearances(Pageable pageable) {
        Page<Clearance> clearances = clearanceRepository.findShortTermClearances(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getMediumTermClearances(Pageable pageable) {
        Page<Clearance> clearances = clearanceRepository.findMediumTermClearances(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getLongTermClearances(Pageable pageable) {
        Page<Clearance> clearances = clearanceRepository.findLongTermClearances(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    /**
     * Get clearances by representator authority level
     */
    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getExecutiveClearances(Pageable pageable) {
        log.debug("Getting executive clearances");

        Page<Clearance> clearances = clearanceRepository.findExecutiveClearances(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getLegalRepresentativeClearances(Pageable pageable) {
        log.debug("Getting legal representative clearances");

        Page<Clearance> clearances = clearanceRepository.findLegalRepresentativeClearances(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ClearanceDTO> getTechnicalRepresentativeClearances(Pageable pageable) {
        log.debug("Getting technical representative clearances");

        Page<Clearance> clearances = clearanceRepository.findTechnicalRepresentativeClearances(pageable);
        return clearances.map(ClearanceDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update clearance
     */
    public ClearanceDTO updateClearance(Long id, ClearanceDTO clearanceDTO) {
        log.info("Updating clearance with ID: {}", id);

        Clearance existingClearance = getClearanceEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(clearanceDTO, "update");
        validateBusinessRules(clearanceDTO, "update");

        // Check for overlapping clearances (excluding current record)
        validateNoOverlappingClearances(clearanceDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(clearanceDTO, existingClearance);

        // Handle foreign key relationships
        setEntityRelationships(clearanceDTO, existingClearance);

        Clearance updatedClearance = clearanceRepository.save(existingClearance);
        log.info("Successfully updated clearance with ID: {}", id);

        return ClearanceDTO.fromEntityWithRelations(updatedClearance);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete clearance
     */
    public void deleteClearance(Long id) {
        log.info("Deleting clearance with ID: {}", id);

        Clearance clearance = getClearanceEntityById(id);
        clearanceRepository.delete(clearance);

        log.info("Successfully deleted clearance with ID: {}", id);
    }

    /**
     * Delete clearance by ID (direct)
     */
    public void deleteClearanceById(Long id) {
        log.info("Deleting clearance by ID: {}", id);

        if (!clearanceRepository.existsById(id)) {
            throw new RuntimeException("Clearance not found with ID: " + id);
        }

        clearanceRepository.deleteById(id);
        log.info("Successfully deleted clearance with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if clearance exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return clearanceRepository.existsById(id);
    }

    /**
     * Check if provider has active clearances
     */
    @Transactional(readOnly = true)
    public boolean hasActiveClearances(Long providerId) {
        Date currentDate = new Date();
        return clearanceRepository.hasActiveClearances(providerId, currentDate);
    }

    /**
     * Check if representator has active clearances
     */
    @Transactional(readOnly = true)
    public boolean representatorHasActiveClearances(Long representatorId) {
        Date currentDate = new Date();
        return clearanceRepository.representatorHasActiveClearances(representatorId, currentDate);
    }

    /**
     * Check if provider has permanent clearances
     */
    @Transactional(readOnly = true)
    public boolean hasPermanentClearances(Long providerId) {
        return clearanceRepository.hasPermanentClearances(providerId);
    }

    /**
     * Get count of active clearances for provider
     */
    @Transactional(readOnly = true)
    public Long countActiveClearancesForProvider(Long providerId) {
        Date currentDate = new Date();
        return clearanceRepository.countActiveClearancesForProvider(providerId, currentDate);
    }

    /**
     * Get count of active clearances for representator
     */
    @Transactional(readOnly = true)
    public Long countActiveClearancesForRepresentator(Long representatorId) {
        Date currentDate = new Date();
        return clearanceRepository.countActiveClearancesForRepresentator(representatorId, currentDate);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countActiveClearances() {
        Date currentDate = new Date();
        return clearanceRepository.countActiveClearances(currentDate);
    }

    @Transactional(readOnly = true)
    public Long countPermanentClearances() {
        return clearanceRepository.countPermanentClearances();
    }

    @Transactional(readOnly = true)
    public Long countExpiredClearances() {
        Date currentDate = new Date();
        return clearanceRepository.countExpiredClearances(currentDate);
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(ClearanceDTO dto, Clearance entity) {
        entity.setStartDate(dto.getStartDate()); // F_01
        entity.setEndDate(dto.getEndDate()); // F_02
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(ClearanceDTO dto, Clearance entity) {
        // F_03 - Provider (required)
        if (dto.getProviderId() != null) {
            entity.setProvider(providerRepository.findById(dto.getProviderId())
                    .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + dto.getProviderId())));
        }

        // F_04 - ProviderRepresentator (required)
        if (dto.getProviderRepresentatorId() != null) {
            entity.setProviderRepresentator(providerRepresentatorRepository.findById(dto.getProviderRepresentatorId())
                    .orElseThrow(() -> new RuntimeException("Provider representator not found with ID: " + dto.getProviderRepresentatorId())));
        }

        // F_05 - Mail reference (optional)
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
    private void validateRequiredFields(ClearanceDTO dto, String operation) {
        if (dto.getProviderId() == null) {
            throw new RuntimeException("Provider is required for " + operation);
        }
        if (dto.getProviderRepresentatorId() == null) {
            throw new RuntimeException("Provider representator is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(ClearanceDTO dto, String operation) {
        // End date must be after start date if both provided
        if (dto.getStartDate() != null && dto.getEndDate() != null && dto.getEndDate().before(dto.getStartDate())) {
            throw new RuntimeException("End date must be after start date for " + operation);
        }

        // Start date cannot be too far in the future (more than 1 year)
        if (dto.getStartDate() != null) {
            Date oneYearFromNow = new Date(System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000));
            if (dto.getStartDate().after(oneYearFromNow)) {
                throw new RuntimeException("Start date cannot be more than one year in the future for " + operation);
            }
        }

        // Verify that the representator belongs to the provider
        if (dto.getProviderId() != null && dto.getProviderRepresentatorId() != null) {
            // This should be verified in the database, but we can add a check here
            Optional<dz.mdn.iaas.business.provider.model.ProviderRepresentator> representator = 
                providerRepresentatorRepository.findById(dto.getProviderRepresentatorId());
            
            if (representator.isPresent() && !representator.get().getProvider().getId().equals(dto.getProviderId())) {
                throw new RuntimeException("Provider representator does not belong to the specified provider for " + operation);
            }
        }
    }

    /**
     * Validate no overlapping clearances for the same provider and representator
     */
    private void validateNoOverlappingClearances(ClearanceDTO dto, Long excludeId) {
        if (dto.getProviderId() == null || dto.getProviderRepresentatorId() == null) {
            return; // Cannot check without these IDs
        }

        Date startDate = dto.getStartDate();
        Date endDate = dto.getEndDate();

        // If no dates specified, treat as immediate and indefinite
        if (startDate == null && endDate == null) {
            startDate = new Date(); // Now
            endDate = null; // Indefinite
        }

        List<Clearance> existingClearances = clearanceRepository.findOverlappingClearancesForProviderAndRepresentator(
                dto.getProviderId(), dto.getProviderRepresentatorId(), startDate, endDate);

        // Remove the current clearance from the list when updating
        if (excludeId != null) {
            existingClearances = existingClearances.stream()
                    .filter(clearance -> !clearance.getId().equals(excludeId))
                    .toList();
        }

        if (!existingClearances.isEmpty()) {
            throw new RuntimeException("Overlapping clearance already exists for this provider and representator combination");
        }
    }
}
