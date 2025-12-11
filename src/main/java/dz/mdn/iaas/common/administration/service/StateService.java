/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StateService
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Service
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Service
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.dto.StateDTO;
import dz.mdn.iaas.common.administration.model.Country;
import dz.mdn.iaas.common.administration.model.State;
import dz.mdn.iaas.common.administration.repository.StateRepository;
import dz.mdn.iaas.configuration.template.GenericService;
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
 * State Service - Extends GenericService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class StateService extends GenericService<State, StateDTO, Long> {

    private final StateRepository stateRepository;
    private final CountryService countryService;

    @Override
    protected JpaRepository<State, Long> getRepository() {
        return stateRepository;
    }

    @Override
    protected String getEntityName() {
        return "State";
    }

    @Override
    protected StateDTO toDTO(State entity) {
        return StateDTO.fromEntity(entity);
    }

    @Override
    protected State toEntity(StateDTO dto) {
        State entity = dto.toEntity();
        
        // Set relationships
        if (dto.getCountryId() != null) {
            Country country = countryService.getEntityById(dto.getCountryId());
            entity.setCountry(country);
        }
        
        return entity;
    }

    @Override
    protected void updateEntityFromDTO(State entity, StateDTO dto) {
        dto.updateEntity(entity);
        
        // Update relationships
        if (dto.getCountryId() != null) {
            Country country = countryService.getEntityById(dto.getCountryId());
            entity.setCountry(country);
        }
    }

    @Override
    @Transactional
    public StateDTO create(StateDTO dto) {
        log.info("Creating state: code={}, designationFr={}, countryId={}", 
                dto.getCode(), dto.getDesignationFr(), dto.getCountryId());
        return super.create(dto);
    }

    @Override
    @Transactional
    public StateDTO update(Long id, StateDTO dto) {
        log.info("Updating state with ID: {}", id);
        return super.update(id, dto);
    }

    public List<StateDTO> getAll() {
        log.debug("Getting all states without pagination");
        return stateRepository.findAll().stream()
                .map(StateDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<StateDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for states with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return getAll(pageable);
    }

    public List<StateDTO> getByCountryId(Long countryId) {
        log.debug("Getting states by country ID: {}", countryId);
        return stateRepository.findByCountryId(countryId).stream()
                .map(StateDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
