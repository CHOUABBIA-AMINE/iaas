/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: ProcurementDirectorService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *	@Type		: Service
 *	@Layer		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.service;

import dz.mdn.iaas.business.core.dto.ProcurementDirectorDTO;
import dz.mdn.iaas.business.core.model.ProcurementDirector;
import dz.mdn.iaas.business.core.repository.ProcurementDirectorRepository;
import dz.mdn.iaas.common.service.GenericService;
import dz.mdn.iaas.common.validator.UniqueFieldValidator;
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
 * Simplified ProcurementDirector Service - Essential CRUD Operations Only
 * Methods: create, update, getById, getAll (paginated & non-paginated), delete, globalSearch
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProcurementDirectorService extends GenericService<ProcurementDirector, ProcurementDirectorDTO, Long> {

    private final ProcurementDirectorRepository procurementDirectorRepository;
    private final UniqueFieldValidator uniqueFieldValidator;

    @Override
    protected JpaRepository<ProcurementDirector, Long> getRepository() {
        return procurementDirectorRepository;
    }

    @Override
    protected String getEntityName() {
        return "ProcurementDirector";
    }

    @Override
    protected ProcurementDirectorDTO toDTO(ProcurementDirector entity) {
        return ProcurementDirectorDTO.fromEntity(entity);
    }

    @Override
    protected ProcurementDirector toEntity(ProcurementDirectorDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(ProcurementDirector entity, ProcurementDirectorDTO dto) {
        dto.updateEntity(entity);
    }

    // ========== CREATE ==========

    @Override
    @Transactional
    public ProcurementDirectorDTO create(ProcurementDirectorDTO dto) {
        log.info("Creating procurement director: designation={}", dto.getDesignation());
        
        uniqueFieldValidator.validateUniqueForCreate(
            "Designation", 
            dto.getDesignation(), 
            procurementDirectorRepository::existsByDesignation
        );
        
        return super.create(dto);
    }

    // ========== UPDATE ==========

    @Override
    @Transactional
    public ProcurementDirectorDTO update(Long id, ProcurementDirectorDTO dto) {
        log.info("Updating procurement director with ID: {}", id);
        
        uniqueFieldValidator.validateUniqueForUpdate(
            "Designation",
            dto.getDesignation(),
            id,
            procurementDirectorRepository::existsByDesignationAndIdNot
        );
        
        return super.update(id, dto);
    }

    // ========== GET BY ID (inherited) ==========

    // ========== GET ALL (PAGINATED) (inherited) ==========

    // ========== GET ALL (NON-PAGINATED) ==========

    public List<ProcurementDirectorDTO> getAll() {
        log.debug("Getting all procurement directors without pagination");
        return procurementDirectorRepository.findAll().stream()
                .map(ProcurementDirectorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ========== DELETE (inherited) ==========

    // ========== GLOBAL SEARCH ==========

    public Page<ProcurementDirectorDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for procurement directors with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> procurementDirectorRepository.searchByDesignation(searchTerm.trim(), p), pageable);
    }
}
