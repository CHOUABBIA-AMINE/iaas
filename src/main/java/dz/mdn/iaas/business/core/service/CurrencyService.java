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

import dz.mdn.iaas.business.core.dto.CurrencyDTO;
import dz.mdn.iaas.business.core.model.Currency;
import dz.mdn.iaas.business.core.repository.CurrencyRepository;
import dz.mdn.iaas.common.service.GenericService;
import dz.mdn.iaas.common.validator.UniqueFieldValidator;
import dz.mdn.iaas.common.validator.UniqueFieldValidator.UniqueFieldUpdateValidation;
import dz.mdn.iaas.common.validator.UniqueFieldValidator.UniqueFieldValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    private final UniqueFieldValidator uniqueFieldValidator;

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
        
        uniqueFieldValidator.validateMultipleForCreate(
            UniqueFieldValidation.of("Code", dto.getCode(), currencyRepository::existsByCode),
            UniqueFieldValidation.of("Arabic designation", dto.getDesignationAr(), currencyRepository::existsByDesignationAr),
            UniqueFieldValidation.of("English designation", dto.getDesignationEn(), currencyRepository::existsByDesignationEn),
            UniqueFieldValidation.of("French designation", dto.getDesignationFr(), currencyRepository::existsByDesignationFr),
            UniqueFieldValidation.of("Arabic acronym", dto.getAcronymAr(), currencyRepository::existsByAcronymAr),
            UniqueFieldValidation.of("English acronym", dto.getAcronymEn(), currencyRepository::existsByAcronymEn),
            UniqueFieldValidation.of("French acronym", dto.getAcronymFr(), currencyRepository::existsByAcronymFr)
        );
        
        return super.create(dto);
    }

    // ========== UPDATE ==========

    @Override
    @Transactional
    public CurrencyDTO update(Long id, CurrencyDTO dto) {
        log.info("Updating currency with ID: {}", id);
        
        uniqueFieldValidator.validateMultipleForUpdate(
            UniqueFieldUpdateValidation.of("Code", dto.getCode(), id, currencyRepository::existsByCodeAndIdNot),
            UniqueFieldUpdateValidation.of("Arabic designation", dto.getDesignationAr(), id, currencyRepository::existsByDesignationArAndIdNot),
            UniqueFieldUpdateValidation.of("English designation", dto.getDesignationEn(), id, currencyRepository::existsByDesignationEnAndIdNot),
            UniqueFieldUpdateValidation.of("French designation", dto.getDesignationFr(), id, currencyRepository::existsByDesignationFrAndIdNot),
            UniqueFieldUpdateValidation.of("Arabic acronym", dto.getAcronymAr(), id, currencyRepository::existsByAcronymArAndIdNot),
            UniqueFieldUpdateValidation.of("English acronym", dto.getAcronymEn(), id, currencyRepository::existsByAcronymEnAndIdNot),
            UniqueFieldUpdateValidation.of("French acronym", dto.getAcronymFr(), id, currencyRepository::existsByAcronymFrAndIdNot)
        );
        
        return super.update(id, dto);
    }

    // ========== GET BY ID (inherited from GenericService) ==========
    // public CurrencyDTO getById(Long id)

    // ========== GET ALL (PAGINATED) (inherited from GenericService) ==========
    // public Page<CurrencyDTO> getAll(Pageable pageable)

    // ========== GET ALL (NON-PAGINATED) ==========

    public List<CurrencyDTO> getAll() {
        log.debug("Getting all currencies without pagination");
        return currencyRepository.findAll().stream()
                .map(CurrencyDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ========== DELETE (inherited from GenericService) ==========
    // public void delete(Long id)

    // ========== GLOBAL SEARCH ==========

    public Page<CurrencyDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for currencies with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> currencyRepository.searchByAnyField(searchTerm.trim(), p), pageable);
    }
}
