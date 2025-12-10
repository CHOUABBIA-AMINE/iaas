/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementNatureService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Service
 *	@Layer		: Business / Core
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

import dz.mdn.iaas.business.core.dto.ProcurementNatureDTO;
import dz.mdn.iaas.business.core.model.ProcurementNature;
import dz.mdn.iaas.business.core.repository.ProcurementNatureRepository;
import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.exception.BusinessValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Simplified ProcurementNature Service - Essential CRUD Operations Only
 * Methods: create, update, getById, getAll (paginated & non-paginated), delete, globalSearch
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProcurementNatureService extends GenericService<ProcurementNature, ProcurementNatureDTO, Long> {

    private final ProcurementNatureRepository procurementNatureRepository;

    @Override
    protected JpaRepository<ProcurementNature, Long> getRepository() {
        return procurementNatureRepository;
    }

    @Override
    protected String getEntityName() {
        return "ProcurementNature";
    }

    @Override
    protected ProcurementNatureDTO toDTO(ProcurementNature entity) {
        return ProcurementNatureDTO.fromEntity(entity);
    }

    @Override
    protected ProcurementNature toEntity(ProcurementNatureDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(ProcurementNature entity, ProcurementNatureDTO dto) {
        dto.updateEntity(entity);
    }

    // ========== CREATE ==========

    @Override
    @Transactional
    public ProcurementNatureDTO create(ProcurementNatureDTO dto) {
        log.info("Creating procurement nature: designationFr={}, code={}", dto.getDesignationFr());
        
        // Validate unique constraints
        if (procurementNatureRepository.existsByDesignationFr(dto.getDesignationFr())) {
            throw new BusinessValidationException("French designation '" + dto.getDesignationFr() + "' already exists");
        }
        
        return super.create(dto);
    }

    // ========== UPDATE ==========

    @Override
    @Transactional
    public ProcurementNatureDTO update(Long id, ProcurementNatureDTO dto) {
        log.info("Updating procurement nature with ID: {}", id);
        
        // Validate unique constraints
        if (procurementNatureRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), id)) {
            throw new BusinessValidationException("French designation '" + dto.getDesignationFr() + "' already exists");
        }
        
        return super.update(id, dto);
    }

    // ========== GET ALL (NON-PAGINATED) ==========

    public List<ProcurementNatureDTO> getAll() {
        log.debug("Getting all procurement natures without pagination");
        return procurementNatureRepository.findAll().stream()
                .map(ProcurementNatureDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ========== GLOBAL SEARCH ==========

    public Page<ProcurementNatureDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for procurement natures with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> procurementNatureRepository.searchByDesignation(searchTerm.trim(), p), pageable);
    }
}
