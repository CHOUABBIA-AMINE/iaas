/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: BudgetTypeService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Service
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Service
 *
 **/

package dz.mdn.iaas.business.plan.service;

import dz.mdn.iaas.business.plan.dto.BudgetTypeDTO;
import dz.mdn.iaas.business.plan.model.BudgetType;
import dz.mdn.iaas.business.plan.repository.BudgetTypeRepository;
import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.exception.BusinessValidationException;
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
 * BudgetType Service - Extends GenericService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BudgetTypeService extends GenericService<BudgetType, BudgetTypeDTO, Long> {

    private final BudgetTypeRepository budgetTypeRepository;

    @Override
    protected JpaRepository<BudgetType, Long> getRepository() {
        return budgetTypeRepository;
    }

    @Override
    protected String getEntityName() {
        return "BudgetType";
    }

    @Override
    protected BudgetTypeDTO toDTO(BudgetType entity) {
        return BudgetTypeDTO.fromEntity(entity);
    }

    @Override
    protected BudgetType toEntity(BudgetTypeDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(BudgetType entity, BudgetTypeDTO dto) {
        dto.updateEntity(entity);
    }

    @Override
    @Transactional
    public BudgetTypeDTO create(BudgetTypeDTO dto) {
        log.info("Creating budget type: designationFr={}", dto.getDesignationFr());
        
        if (budgetTypeRepository.existsByDesignationFr(dto.getDesignationFr())) {
            throw new BusinessValidationException("French designation '" + dto.getDesignationFr() + "' already exists");
        }
        
        if (dto.getAcronymFr() != null && budgetTypeRepository.existsByAcronymFr(dto.getAcronymFr())) {
            throw new BusinessValidationException("French acronym '" + dto.getAcronymFr() + "' already exists");
        }
        
        return super.create(dto);
    }

    @Override
    @Transactional
    public BudgetTypeDTO update(Long id, BudgetTypeDTO dto) {
        log.info("Updating budget type with ID: {}", id);
        
        if (budgetTypeRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), id)) {
            throw new BusinessValidationException("French designation '" + dto.getDesignationFr() + "' already exists");
        }
        
        if (dto.getAcronymFr() != null && budgetTypeRepository.existsByAcronymFrAndIdNot(dto.getAcronymFr(), id)) {
            throw new BusinessValidationException("French acronym '" + dto.getAcronymFr() + "' already exists");
        }
        
        return super.update(id, dto);
    }

    public List<BudgetTypeDTO> getAll() {
        log.debug("Getting all budget types without pagination");
        return budgetTypeRepository.findAll().stream()
                .map(BudgetTypeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<BudgetTypeDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for budget types with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> budgetTypeRepository.searchByAnyField(searchTerm.trim(), p), pageable);
    }

    public boolean existsByDesignationFr(String designationFr) {
        return budgetTypeRepository.existsByDesignationFr(designationFr);
    }

    public boolean existsByAcronymFr(String acronymFr) {
        return budgetTypeRepository.existsByAcronymFr(acronymFr);
    }
}
