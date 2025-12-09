/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CurrencyService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.model.Currency;
import dz.mdn.iaas.business.core.repository.CurrencyRepository;
import dz.mdn.iaas.business.core.dto.CurrencyDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Currency Service with CRUD operations
 * Handles currency management operations with multiple unique constraints
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr,
 * F_04=codeAr, F_05=codeLt
 * All fields F_01 through F_05 have unique constraints and are required
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new currency
     */
    public CurrencyDTO createCurrency(CurrencyDTO currencyDTO) {
        log.info("Creating currency with Latin code: {} and designations: AR={}, EN={}, FR={}", 
                currencyDTO.getAcronymFr(), currencyDTO.getDesignationAr(), 
                currencyDTO.getDesignationEn(), currencyDTO.getDesignationFr());

        // Validate required fields
        validateRequiredFields(currencyDTO, "create");

        // Check for all unique constraint violations
        validateAllUniqueConstraints(currencyDTO, null);

        // Create entity with exact field mapping
        Currency currency = new Currency();
        currency.setCode(currencyDTO.getCode()); // F_01
        currency.setDesignationAr(currencyDTO.getDesignationAr()); // F_01
        currency.setDesignationEn(currencyDTO.getDesignationEn()); // F_02
        currency.setDesignationFr(currencyDTO.getDesignationFr()); // F_03
        currency.setAcronymAr(currencyDTO.getAcronymAr()); // F_04
        currency.setAcronymEn(currencyDTO.getAcronymEn()); // F_04
        currency.setAcronymFr(currencyDTO.getAcronymFr()); // F_05

        Currency savedCurrency = currencyRepository.save(currency);
        log.info("Successfully created currency with ID: {}", savedCurrency.getId());

        return CurrencyDTO.fromEntity(savedCurrency);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get currency by ID
     */
    @Transactional(readOnly = true)
    public CurrencyDTO getCurrencyById(Long id) {
        log.debug("Getting currency with ID: {}", id);

        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + id));

        return CurrencyDTO.fromEntity(currency);
    }

    /**
     * Get currency entity by ID
     */
    @Transactional(readOnly = true)
    public Currency getCurrencyEntityById(Long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found with ID: " + id));
    }

    /**
     * Find currency by code (unique field F_01)
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findByCode(String code) {
        log.debug("Finding currency with code: {}", code);

        return currencyRepository.findByCode(code)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Find currency by Arabic designation (unique field F_02)
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding currency with Arabic designation: {}", designationAr);

        return currencyRepository.findByDesignationAr(designationAr)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Find currency by English designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding currency with English designation: {}", designationEn);

        return currencyRepository.findByDesignationEn(designationEn)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Find currency by French designation (unique field F_04)
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding currency with French designation: {}", designationFr);

        return currencyRepository.findByDesignationFr(designationFr)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Find currency by Arabic acronym (unique field F_05)
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findByAcronymAr(String acronymAr) {
        log.debug("Finding currency with Arabic code: {}", acronymAr);

        return currencyRepository.findByAcronymAr(acronymAr)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Find currency by Latin acronym (unique field F_06)
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findByAcronymEn(String acronymEn) {
        log.debug("Finding currency with Latin code: {}", acronymEn);

        return currencyRepository.findByAcronymEn(acronymEn)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Find currency by Latin acronym (unique field F_07)
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findByAcronymFr(String acronymFr) {
        log.debug("Finding currency with Latin code: {}", acronymFr);

        return currencyRepository.findByAcronymFr(acronymFr)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Get all currencies with pagination
     */
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> getAllCurrencies(Pageable pageable) {
        log.debug("Getting all currencies with pagination");

        Page<Currency> currencies = currencyRepository.findAllOrderByCode(pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }

    /**
     * Find one currency by ID
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findOne(Long id) {
        log.debug("Finding currency by ID: {}", id);

        return currencyRepository.findById(id)
                .map(CurrencyDTO::fromEntity);
    }

    /**
     * Search currencies by any field
     */
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> searchCurrencies(String searchTerm, Pageable pageable) {
        log.debug("Searching currencies with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllCurrencies(pageable);
        }

        Page<Currency> currencies = currencyRepository.searchByAnyField(searchTerm.trim(), pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }

    /**
     * Search currencies by code
     */
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> searchByCode(String code, Pageable pageable) {
        log.debug("Searching currencies by code: {}", code);

        Page<Currency> currencies = currencyRepository.searchByCode(code, pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }

    /**
     * Search currencies by designation
     */
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> searchByDesignation(String designation, Pageable pageable) {
        log.debug("Searching currencies by designation: {}", designation);

        Page<Currency> currencies = currencyRepository.searchByDesignation(designation, pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }

    /**
     * Search currencies by code
     */
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> searchByAcronym(String acronym, Pageable pageable) {
        log.debug("Searching currencies by acronym: {}", acronym);

        Page<Currency> currencies = currencyRepository.searchByAcronym(acronym, pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }

    /**
     * Get major international currencies
     */
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> getMajorCurrencies(Pageable pageable) {
        log.debug("Getting major international currencies");

        Page<Currency> currencies = currencyRepository.findMajorCurrencies(pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }

    /**
     * Get regional currencies
     */
    @Transactional(readOnly = true)
    public Page<CurrencyDTO> getRegionalCurrencies(Pageable pageable) {
        log.debug("Getting regional currencies");

        Page<Currency> currencies = currencyRepository.findRegionalCurrencies(pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }

    /**
     * Get ISO standard currencies (3-letter codes)
     */
    /*@Transactional(readOnly = true)
    public Page<CurrencyDTO> getISOStandardCurrencies(Pageable pageable) {
        log.debug("Getting ISO standard currencies");

        Page<Currency> currencies = currencyRepository.findISOStandardCurrencies(pageable);
        return currencies.map(CurrencyDTO::fromEntity);
    }*/

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update currency
     */
    public CurrencyDTO updateCurrency(Long id, CurrencyDTO currencyDTO) {
        log.info("Updating currency with ID: {}", id);

        Currency existingCurrency = getCurrencyEntityById(id);

        // Validate required fields
        validateRequiredFields(currencyDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateAllUniqueConstraints(currencyDTO, id);

        // Update fields with exact field mapping
        existingCurrency.setCode(currencyDTO.getCode()); // F_01
        existingCurrency.setDesignationAr(currencyDTO.getDesignationAr()); // F_02
        existingCurrency.setDesignationEn(currencyDTO.getDesignationEn()); // F_02
        existingCurrency.setDesignationFr(currencyDTO.getDesignationFr()); // F_03
        existingCurrency.setAcronymAr(currencyDTO.getAcronymAr()); // F_04
        existingCurrency.setAcronymEn(currencyDTO.getAcronymEn()); // F_04
        existingCurrency.setAcronymFr(currencyDTO.getAcronymFr()); // F_04

        Currency updatedCurrency = currencyRepository.save(existingCurrency);
        log.info("Successfully updated currency with ID: {}", id);

        return CurrencyDTO.fromEntity(updatedCurrency);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete currency
     */
    public void deleteCurrency(Long id) {
        log.info("Deleting currency with ID: {}", id);

        Currency currency = getCurrencyEntityById(id);
        currencyRepository.delete(currency);

        log.info("Successfully deleted currency with ID: {}", id);
    }

    /**
     * Delete currency by ID (direct)
     */
    public void deleteCurrencyById(Long id) {
        log.info("Deleting currency by ID: {}", id);

        if (!currencyRepository.existsById(id)) {
            throw new RuntimeException("Currency not found with ID: " + id);
        }

        currencyRepository.deleteById(id);
        log.info("Successfully deleted currency with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if currency exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return currencyRepository.existsById(id);
    }

    /**
     * Check if currency exists by any unique field
     */
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return currencyRepository.existsByCode(code);
    }
    
    @Transactional(readOnly = true)
    public boolean existsByDesignationAr(String designationAr) {
        return currencyRepository.existsByDesignationAr(designationAr);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationEn(String designationEn) {
        return currencyRepository.existsByDesignationEn(designationEn);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return currencyRepository.existsByDesignationFr(designationFr);
    }

    @Transactional(readOnly = true)
    public boolean existsByAcronymAr(String acronymAr) {
        return currencyRepository.existsByAcronymAr(acronymAr);
    }

    @Transactional(readOnly = true)
    public boolean existsByAcronymEn(String acronymEn) {
        return currencyRepository.existsByAcronymEn(acronymEn);
    }

    @Transactional(readOnly = true)
    public boolean existsByAcronymFr(String acronymFr) {
        return currencyRepository.existsByAcronymFr(acronymFr);
    }

    /**
     * Get total count of currencies
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return currencyRepository.countAllCurrencies();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(CurrencyDTO currencyDTO, String operation) {
        if (currencyDTO.getCode() == null || currencyDTO.getCode().trim().isEmpty()) {
            throw new RuntimeException("Code is required for " + operation);
        }
        
        if (currencyDTO.getDesignationAr() == null || currencyDTO.getDesignationAr().trim().isEmpty()) {
            throw new RuntimeException("Arabic designation is required for " + operation);
        }

        if (currencyDTO.getDesignationEn() == null || currencyDTO.getDesignationEn().trim().isEmpty()) {
            throw new RuntimeException("English designation is required for " + operation);
        }

        if (currencyDTO.getDesignationFr() == null || currencyDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }

        if (currencyDTO.getAcronymAr() == null || currencyDTO.getAcronymAr().trim().isEmpty()) {
            throw new RuntimeException("Arabic acronym is required for " + operation);
        }

        if (currencyDTO.getAcronymEn() == null || currencyDTO.getAcronymEn().trim().isEmpty()) {
            throw new RuntimeException("English acronym is required for " + operation);
        }

        if (currencyDTO.getAcronymFr() == null || currencyDTO.getAcronymFr().trim().isEmpty()) {
            throw new RuntimeException("French acronym is required for " + operation);
        }
    }

    /**
     * Validate all unique constraints
     */
    private void validateAllUniqueConstraints(CurrencyDTO currencyDTO, Long excludeId) {
        // Check Arabic designation uniqueness (F_01)
        if (excludeId == null) {
            if (currencyRepository.existsByCode(currencyDTO.getCode())) {
                throw new RuntimeException("Currency with code '" + currencyDTO.getCode() + "' already exists");
            }
        } else {
            if (currencyRepository.existsByCodeAndIdNot(currencyDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another currency with code '" + currencyDTO.getCode() + "' already exists");
            }
        }
        
        // Check Arabic designation uniqueness (F_02)
        if (excludeId == null) {
            if (currencyRepository.existsByDesignationAr(currencyDTO.getDesignationAr())) {
                throw new RuntimeException("Currency with Arabic designation '" + currencyDTO.getDesignationAr() + "' already exists");
            }
        } else {
            if (currencyRepository.existsByDesignationArAndIdNot(currencyDTO.getDesignationAr(), excludeId)) {
                throw new RuntimeException("Another currency with Arabic designation '" + currencyDTO.getDesignationAr() + "' already exists");
            }
        }

        // Check English designation uniqueness (F_03)
        if (excludeId == null) {
            if (currencyRepository.existsByDesignationEn(currencyDTO.getDesignationEn())) {
                throw new RuntimeException("Currency with English designation '" + currencyDTO.getDesignationEn() + "' already exists");
            }
        } else {
            if (currencyRepository.existsByDesignationEnAndIdNot(currencyDTO.getDesignationEn(), excludeId)) {
                throw new RuntimeException("Another currency with English designation '" + currencyDTO.getDesignationEn() + "' already exists");
            }
        }

        // Check French designation uniqueness (F_04)
        if (excludeId == null) {
            if (currencyRepository.existsByDesignationFr(currencyDTO.getDesignationFr())) {
                throw new RuntimeException("Currency with French designation '" + currencyDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (currencyRepository.existsByDesignationFrAndIdNot(currencyDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another currency with French designation '" + currencyDTO.getDesignationFr() + "' already exists");
            }
        }

        // Check Arabic code uniqueness (F_05)
        if (excludeId == null) {
            if (currencyRepository.existsByAcronymAr(currencyDTO.getAcronymAr())) {
                throw new RuntimeException("Currency with Arabic acronym '" + currencyDTO.getAcronymAr() + "' already exists");
            }
        } else {
            if (currencyRepository.existsByAcronymArAndIdNot(currencyDTO.getAcronymAr(), excludeId)) {
                throw new RuntimeException("Another currency with Arabic code '" + currencyDTO.getAcronymAr() + "' already exists");
            }
        }

        // Check Arabic code uniqueness (F_05)
        if (excludeId == null) {
            if (currencyRepository.existsByAcronymEn(currencyDTO.getAcronymEn())) {
                throw new RuntimeException("Currency with English acronym '" + currencyDTO.getAcronymEn() + "' already exists");
            }
        } else {
            if (currencyRepository.existsByAcronymEnAndIdNot(currencyDTO.getAcronymEn(), excludeId)) {
                throw new RuntimeException("Another currency with English code '" + currencyDTO.getAcronymEn() + "' already exists");
            }
        }

        // Check Arabic code uniqueness (F_05)
        if (excludeId == null) {
            if (currencyRepository.existsByAcronymFr(currencyDTO.getAcronymFr())) {
                throw new RuntimeException("Currency with French acronym '" + currencyDTO.getAcronymFr() + "' already exists");
            }
        } else {
            if (currencyRepository.existsByAcronymFrAndIdNot(currencyDTO.getAcronymFr(), excludeId)) {
                throw new RuntimeException("Another currency with French code '" + currencyDTO.getAcronymFr() + "' already exists");
            }
        }
    }
}
