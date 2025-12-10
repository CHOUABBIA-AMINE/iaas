/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementStatusService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Service
 *	@Layer		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.dto.ProcurementStatusDTO;
import dz.mdn.iaas.business.core.model.ProcurementStatus;
import dz.mdn.iaas.business.core.repository.ProcurementStatusRepository;
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
 * Simplified ProcurementStatus Service - Essential CRUD Operations Only
 * Methods: create, update, getById, getAll (paginated & non-paginated), delete, globalSearch
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProcurementStatusService extends GenericService<ProcurementStatus, ProcurementStatusDTO, Long> {

    private final ProcurementStatusRepository procurementStatusRepository;
    private final UniqueFieldValidator uniqueFieldValidator;

    @Override
    protected JpaRepository<ProcurementStatus, Long> getRepository() {
        return procurementStatusRepository;
    }

    @Override
    protected String getEntityName() {
        return "ProcurementStatus";
    }

    @Override
    protected ProcurementStatusDTO toDTO(ProcurementStatus entity) {
        return ProcurementStatusDTO.fromEntity(entity);
    }

    @Override
    protected ProcurementStatus toEntity(ProcurementStatusDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(ProcurementStatus entity, ProcurementStatusDTO dto) {
        dto.updateEntity(entity);
    }

    // ========== CREATE ==========

    @Override
    @Transactional
    public ProcurementStatusDTO create(ProcurementStatusDTO dto) {
        log.info("Creating procurement status: designationFr={}", dto.getDesignationFr());
        
        uniqueFieldValidator.validateMultipleForCreate(
            UniqueFieldValidation.of("French designation", dto.getDesignationFr(), procurementStatusRepository::existsByDesignationFr),
            UniqueFieldValidation.of("Code", dto.getCode(), procurementStatusRepository::existsByCode)
        );
        
        return super.create(dto);
    }

    // ========== UPDATE ==========

    @Override
    @Transactional
    public ProcurementStatusDTO update(Long id, ProcurementStatusDTO dto) {
        log.info("Updating procurement status with ID: {}", id);
        
        uniqueFieldValidator.validateMultipleForUpdate(
            UniqueFieldUpdateValidation.of("French designation", dto.getDesignationFr(), id, procurementStatusRepository::existsByDesignationFrAndIdNot),
            UniqueFieldUpdateValidation.of("Code", dto.getCode(), id, procurementStatusRepository::existsByCodeAndIdNot)
        );
        
        return super.update(id, dto);
    }

    // ========== GET BY ID (inherited) ==========

    // ========== GET ALL (PAGINATED) (inherited) ==========

    // ========== GET ALL (NON-PAGINATED) ==========

    public List<ProcurementStatusDTO> getAll() {
        log.debug("Getting all procurement statuses without pagination");
        return procurementStatusRepository.findAll().stream()
                .map(ProcurementStatusDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ========== DELETE (inherited) ==========

    // ========== GLOBAL SEARCH ==========

    public Page<ProcurementStatusDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for procurement statuses with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> procurementStatusRepository.searchByDesignation(searchTerm.trim(), p), pageable);
    }
}
