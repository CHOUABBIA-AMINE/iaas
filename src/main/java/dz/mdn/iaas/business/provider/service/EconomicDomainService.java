/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicDomainService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.service;

import dz.mdn.iaas.business.provider.model.EconomicDomain;
import dz.mdn.iaas.business.provider.repository.EconomicDomainRepository;
import dz.mdn.iaas.business.provider.dto.EconomicDomainDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Economic Domain Service with CRUD operations
 * Handles economic domain management operations with multilingual support
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01, F_02 are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EconomicDomainService {

    private final EconomicDomainRepository economicDomainRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new economic domain
     */
    public EconomicDomainDTO createEconomicDomain(EconomicDomainDTO economicDomainDTO) {
        log.info("Creating economic domain with code, French designation: {} and designations: AR={}, EN={}", 
        		economicDomainDTO.getCode(), economicDomainDTO.getDesignationFr(), economicDomainDTO.getDesignationAr(), 
                economicDomainDTO.getDesignationEn());

        // Validate required fields
        validateRequiredFields(economicDomainDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(economicDomainDTO, null);

        // Create entity with exact field mapping
        EconomicDomain economicDomain = new EconomicDomain();
        economicDomain.setCode(economicDomainDTO.getCode()); // F_01
        economicDomain.setDesignationAr(economicDomainDTO.getDesignationAr()); // F_02
        economicDomain.setDesignationEn(economicDomainDTO.getDesignationEn()); // F_03
        economicDomain.setDesignationFr(economicDomainDTO.getDesignationFr()); // F_04

        EconomicDomain savedEconomicDomain = economicDomainRepository.save(economicDomain);
        log.info("Successfully created economic domain with ID: {}", savedEconomicDomain.getId());

        return EconomicDomainDTO.fromEntity(savedEconomicDomain);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get economic domain by ID
     */
    @Transactional(readOnly = true)
    public EconomicDomainDTO getEconomicDomainById(Long id) {
        log.debug("Getting economic domain with ID: {}", id);

        EconomicDomain economicDomain = economicDomainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Economic domain not found with ID: " + id));

        return EconomicDomainDTO.fromEntity(economicDomain);
    }

    /**
     * Get economic domain entity by ID
     */
    @Transactional(readOnly = true)
    public EconomicDomain getEconomicDomainEntityById(Long id) {
        return economicDomainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Economic domain not found with ID: " + id));
    }

    /**
     * Find economic domain by French designation (unique field F_01)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicDomainDTO> findByCode(String code) {
        log.debug("Finding economic domain with French designation: {}", code);

        return economicDomainRepository.findByCode(code)
                .map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Find economic domain by French designation (unique field F_04)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicDomainDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding economic domain with French designation: {}", designationFr);

        return economicDomainRepository.findByDesignationFr(designationFr)
                .map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Find economic domain by Arabic designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicDomainDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding economic domain with Arabic designation: {}", designationAr);

        return economicDomainRepository.findByDesignationAr(designationAr)
                .map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Find economic domain by English designation (F_03)
     */
    @Transactional(readOnly = true)
    public Optional<EconomicDomainDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding economic domain with English designation: {}", designationEn);

        return economicDomainRepository.findByDesignationEn(designationEn)
                .map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get all economic domains with pagination
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getAllEconomicDomains(Pageable pageable) {
        log.debug("Getting all economic domains with pagination");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findAllOrderByDesignationFr(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Find one economic domain by ID
     */
    @Transactional(readOnly = true)
    public Optional<EconomicDomainDTO> findOne(Long id) {
        log.debug("Finding economic domain by ID: {}", id);

        return economicDomainRepository.findById(id)
                .map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Search economic domains by designation
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> searchEconomicDomains(String searchTerm, Pageable pageable) {
        log.debug("Searching economic domains with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllEconomicDomains(pageable);
        }

        Page<EconomicDomain> economicDomains = economicDomainRepository.searchByDesignation(searchTerm.trim(), pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get multilingual economic domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getMultilingualEconomicDomains(Pageable pageable) {
        log.debug("Getting multilingual economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findMultilingualEconomicDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get agriculture domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getAgricultureDomains(Pageable pageable) {
        log.debug("Getting agriculture economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findAgricultureDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get industry domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getIndustryDomains(Pageable pageable) {
        log.debug("Getting industry economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findIndustryDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get service domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getServiceDomains(Pageable pageable) {
        log.debug("Getting service economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findServiceDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get energy domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getEnergyDomains(Pageable pageable) {
        log.debug("Getting energy economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findEnergyDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get technology domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getTechnologyDomains(Pageable pageable) {
        log.debug("Getting technology economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findTechnologyDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get finance domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getFinanceDomains(Pageable pageable) {
        log.debug("Getting finance economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findFinanceDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get healthcare domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getHealthcareDomains(Pageable pageable) {
        log.debug("Getting healthcare economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findHealthcareDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get education domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getEducationDomains(Pageable pageable) {
        log.debug("Getting education economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findEducationDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get tourism domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getTourismDomains(Pageable pageable) {
        log.debug("Getting tourism economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findTourismDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get construction domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getConstructionDomains(Pageable pageable) {
        log.debug("Getting construction economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findConstructionDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get transport domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getTransportDomains(Pageable pageable) {
        log.debug("Getting transport economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findTransportDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get mining domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getMiningDomains(Pageable pageable) {
        log.debug("Getting mining economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findMiningDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get strategic domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getStrategicDomains(Pageable pageable) {
        log.debug("Getting strategic economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findStrategicDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get export-oriented domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getExportOrientedDomains(Pageable pageable) {
        log.debug("Getting export-oriented economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findExportOrientedDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get high investment domains
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getHighInvestmentDomains(Pageable pageable) {
        log.debug("Getting high investment economic domains");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findHighInvestmentDomains(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    /**
     * Get economic domains missing translations
     */
    @Transactional(readOnly = true)
    public Page<EconomicDomainDTO> getEconomicDomainsMissingTranslations(Pageable pageable) {
        log.debug("Getting economic domains missing translations");

        Page<EconomicDomain> economicDomains = economicDomainRepository.findMissingTranslations(pageable);
        return economicDomains.map(EconomicDomainDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update economic domain
     */
    public EconomicDomainDTO updateEconomicDomain(Long id, EconomicDomainDTO economicDomainDTO) {
        log.info("Updating economic domain with ID: {}", id);

        EconomicDomain existingEconomicDomain = getEconomicDomainEntityById(id);

        // Validate required fields
        validateRequiredFields(economicDomainDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(economicDomainDTO, id);

        // Update fields with exact field mapping
        existingEconomicDomain.setDesignationAr(economicDomainDTO.getDesignationAr()); // F_01
        existingEconomicDomain.setDesignationEn(economicDomainDTO.getDesignationEn()); // F_02
        existingEconomicDomain.setDesignationFr(economicDomainDTO.getDesignationFr()); // F_03

        EconomicDomain updatedEconomicDomain = economicDomainRepository.save(existingEconomicDomain);
        log.info("Successfully updated economic domain with ID: {}", id);

        return EconomicDomainDTO.fromEntity(updatedEconomicDomain);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete economic domain
     */
    public void deleteEconomicDomain(Long id) {
        log.info("Deleting economic domain with ID: {}", id);

        EconomicDomain economicDomain = getEconomicDomainEntityById(id);
        economicDomainRepository.delete(economicDomain);

        log.info("Successfully deleted economic domain with ID: {}", id);
    }

    /**
     * Delete economic domain by ID (direct)
     */
    public void deleteEconomicDomainById(Long id) {
        log.info("Deleting economic domain by ID: {}", id);

        if (!economicDomainRepository.existsById(id)) {
            throw new RuntimeException("Economic domain not found with ID: " + id);
        }

        economicDomainRepository.deleteById(id);
        log.info("Successfully deleted economic domain with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if economic domain exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return economicDomainRepository.existsById(id);
    }

    /**
     * Check if economic domain exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return economicDomainRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of economic domains
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return economicDomainRepository.countAllEconomicDomains();
    }

    /**
     * Get count of primary sector domains
     */
    @Transactional(readOnly = true)
    public Long getPrimarySectorCount() {
        return economicDomainRepository.countPrimarySectorDomains();
    }

    /**
     * Get count of secondary sector domains
     */
    @Transactional(readOnly = true)
    public Long getSecondarySectorCount() {
        return economicDomainRepository.countSecondarySectorDomains();
    }

    /**
     * Get count of tertiary sector domains
     */
    @Transactional(readOnly = true)
    public Long getTertiarySectorCount() {
        return economicDomainRepository.countTertiarySectorDomains();
    }

    /**
     * Get count of quaternary sector domains
     */
    @Transactional(readOnly = true)
    public Long getQuaternarySectorCount() {
        return economicDomainRepository.countQuaternarySectorDomains();
    }

    /**
     * Get count of multilingual economic domains
     */
    @Transactional(readOnly = true)
    public Long getMultilingualCount() {
        return economicDomainRepository.countMultilingualEconomicDomains();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(EconomicDomainDTO economicDomainDTO, String operation) {
        if (economicDomainDTO.getDesignationFr() == null || economicDomainDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(EconomicDomainDTO economicDomainDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (economicDomainRepository.existsByDesignationFr(economicDomainDTO.getDesignationFr())) {
                throw new RuntimeException("Economic domain with French designation '" + economicDomainDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (economicDomainRepository.existsByDesignationFrAndIdNot(economicDomainDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another economic domain with French designation '" + economicDomainDTO.getDesignationFr() + "' already exists");
            }
        }
    }
}
