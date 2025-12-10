/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CurrencyService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.core.dto.CurrencyDTO;
import dz.mdn.iaas.business.core.model.Currency;
import dz.mdn.iaas.business.core.repository.CurrencyRepository;
import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.exception.BusinessValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Simplified Currency Service - Essential CRUD Operations Only
 * Methods: create, update, getById, getAll (paginated & non-paginated), delete, globalSearch
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CurrencyService extends GenericService<Currency, CurrencyDTO, Long> {

    private final CurrencyRepository currencyRepository;

    @Override
    protected JpaRepository<Currency, Long> getRepository() {
        return currencyRepository;
    }

    @Override
    protected String getEntityName() {
        return "Currency";
    }

    @Override
    protected CurrencyDTO toDTO(Currency entity) {
        return CurrencyDTO.fromEntity(entity);
    }

    @Override
    protected Currency toEntity(CurrencyDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(Currency entity, CurrencyDTO dto) {
        dto.updateEntity(entity);
    }

    // ========== CREATE ==========

    @Override
    @Transactional
    public CurrencyDTO create(CurrencyDTO dto) {
        log.info("Creating currency: code={}", dto.getCode());
        
        // Validate unique constraints
        validateUniqueConstraintsForCreate(dto);
        
        return super.create(dto);
    }

    // ========== UPDATE ==========

    @Override
    @Transactional
    public CurrencyDTO update(Long id, CurrencyDTO dto) {
        log.info("Updating currency with ID: {}", id);
        
        // Validate unique constraints
        validateUniqueConstraintsForUpdate(dto, id);
        
        return super.update(id, dto);
    }

    // ========== GET ALL (NON-PAGINATED) ==========

    public List<CurrencyDTO> getAll() {
        log.debug("Getting all currencies without pagination");
        return currencyRepository.findAll().stream()
                .map(CurrencyDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ========== GLOBAL SEARCH ==========

    public Page<CurrencyDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for currencies with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> currencyRepository.searchByAnyField(searchTerm.trim(), p), pageable);
    }

    // ========== VALIDATION ==========

    private void validateUniqueConstraintsForCreate(CurrencyDTO dto) {
        if (currencyRepository.existsByCode(dto.getCode())) {
            throw new BusinessValidationException("Code '" + dto.getCode() + "' already exists");
        }
        if (currencyRepository.existsByDesignationAr(dto.getDesignationAr())) {
            throw new BusinessValidationException("Arabic designation '" + dto.getDesignationAr() + "' already exists");
        }
        if (currencyRepository.existsByDesignationEn(dto.getDesignationEn())) {
            throw new BusinessValidationException("English designation '" + dto.getDesignationEn() + "' already exists");
        }
        if (currencyRepository.existsByDesignationFr(dto.getDesignationFr())) {
            throw new BusinessValidationException("French designation '" + dto.getDesignationFr() + "' already exists");
        }
        if (currencyRepository.existsByAcronymAr(dto.getAcronymAr())) {
            throw new BusinessValidationException("Arabic acronym '" + dto.getAcronymAr() + "' already exists");
        }
        if (currencyRepository.existsByAcronymEn(dto.getAcronymEn())) {
            throw new BusinessValidationException("English acronym '" + dto.getAcronymEn() + "' already exists");
        }
        if (currencyRepository.existsByAcronymFr(dto.getAcronymFr())) {
            throw new BusinessValidationException("French acronym '" + dto.getAcronymFr() + "' already exists");
        }
    }

    private void validateUniqueConstraintsForUpdate(CurrencyDTO dto, Long id) {
        if (currencyRepository.existsByCodeAndIdNot(dto.getCode(), id)) {
            throw new BusinessValidationException("Code '" + dto.getCode() + "' already exists");
        }
        if (currencyRepository.existsByDesignationArAndIdNot(dto.getDesignationAr(), id)) {
            throw new BusinessValidationException("Arabic designation '" + dto.getDesignationAr() + "' already exists");
        }
        if (currencyRepository.existsByDesignationEnAndIdNot(dto.getDesignationEn(), id)) {
            throw new BusinessValidationException("English designation '" + dto.getDesignationEn() + "' already exists");
        }
        if (currencyRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), id)) {
            throw new BusinessValidationException("French designation '" + dto.getDesignationFr() + "' already exists");
        }
        if (currencyRepository.existsByAcronymArAndIdNot(dto.getAcronymAr(), id)) {
            throw new BusinessValidationException("Arabic acronym '" + dto.getAcronymAr() + "' already exists");
        }
        if (currencyRepository.existsByAcronymEnAndIdNot(dto.getAcronymEn(), id)) {
            throw new BusinessValidationException("English acronym '" + dto.getAcronymEn() + "' already exists");
        }
        if (currencyRepository.existsByAcronymFrAndIdNot(dto.getAcronymFr(), id)) {
            throw new BusinessValidationException("French acronym '" + dto.getAcronymFr() + "' already exists");
        }
    }
}
