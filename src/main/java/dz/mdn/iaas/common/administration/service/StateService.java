/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StateService
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.model.State;
import dz.mdn.iaas.common.administration.repository.StateRepository;
import dz.mdn.iaas.common.administration.dto.StateDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StateService {

    private final StateRepository stateRepository;

    // ========== CREATE OPERATIONS ==========

    public StateDTO createState(StateDTO stateDTO) {
        log.info("Creating state with code: {} and designations: {} | {}", 
                stateDTO.getCode(), stateDTO.getDesignationAr(), stateDTO.getDesignationLt());

        // Validate all required fields
        validateRequiredFields(stateDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(stateDTO, null);

        // Create entity with exact field mapping
        State state = new State();
        state.setCode(stateDTO.getCode()); // F_01
        state.setDesignationAr(stateDTO.getDesignationAr()); // F_02
        state.setDesignationLt(stateDTO.getDesignationLt()); // F_03

        State savedState = stateRepository.save(state);
        log.info("Successfully created state with ID: {}", savedState.getId());

        return StateDTO.fromEntity(savedState);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public StateDTO getStateById(Long id) {
        log.debug("Getting state with ID: {}", id);

        State state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));

        return StateDTO.fromEntity(state);
    }

    @Transactional(readOnly = true)
    public State getStateEntityById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<StateDTO> findByCode(String code) {
        log.debug("Finding state with code: {}", code);

        return stateRepository.findByCode(code)
                .map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<StateDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding state with Arabic designation: {}", designationAr);

        return stateRepository.findByDesignationAr(designationAr)
                .map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<StateDTO> findByDesignationLt(String designationLt) {
        log.debug("Finding state with Latin designation: {}", designationLt);

        return stateRepository.findByDesignationLt(designationLt)
                .map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<StateDTO> getAllStates(Pageable pageable) {
        log.debug("Getting all states with pagination");

        Page<State> states = stateRepository.findAll(pageable);
        return states.map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<StateDTO> findOne(Long id) {
        log.debug("Finding state by ID: {}", id);

        return stateRepository.findById(id)
                .map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<StateDTO> searchStates(String searchTerm, Pageable pageable) {
        log.debug("Searching states with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllStates(pageable);
        }

        Page<State> states = stateRepository.searchByAnyField(searchTerm.trim(), pageable);
        return states.map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<StateDTO> searchByCode(int code, Pageable pageable) {
        log.debug("Searching states by code: {}", code);

        Page<State> states = stateRepository.findByCode(code, pageable);
        return states.map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<StateDTO> searchByDesignationAr(String designationAr, Pageable pageable) {
        log.debug("Searching states by Arabic designation: {}", designationAr);

        Page<State> states = stateRepository.findByDesignationArContaining(designationAr, pageable);
        return states.map(StateDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<StateDTO> searchByDesignationLt(String designationLt, Pageable pageable) {
        log.debug("Searching states by Latin designation: {}", designationLt);

        Page<State> states = stateRepository.findByDesignationLtContaining(designationLt, pageable);
        return states.map(StateDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public StateDTO updateState(Long id, StateDTO stateDTO) {
        log.info("Updating state with ID: {}", id);

        State existingState = getStateEntityById(id);

        // Validate all required fields
        validateRequiredFields(stateDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(stateDTO, id);

        // Update fields with exact field mapping
        existingState.setCode(stateDTO.getCode()); // F_01
        existingState.setDesignationAr(stateDTO.getDesignationAr()); // F_02
        existingState.setDesignationLt(stateDTO.getDesignationLt()); // F_03

        State updatedState = stateRepository.save(existingState);
        log.info("Successfully updated state with ID: {}", id);

        return StateDTO.fromEntity(updatedState);
    }

    public StateDTO partialUpdateState(Long id, StateDTO stateDTO) {
        log.info("Partially updating state with ID: {}", id);

        State existingState = getStateEntityById(id);

        // Update only non-null fields
        if (stateDTO.getCode() != 0) {
            if (stateRepository.existsByCodeAndIdNot(stateDTO.getCode(), id)) {
                throw new RuntimeException("State with code '" + stateDTO.getCode() + "' already exists");
            }
            existingState.setCode(stateDTO.getCode()); // F_01
        }

        if (stateDTO.getDesignationAr() != null) {
            if (stateDTO.getDesignationAr().trim().isEmpty()) {
                throw new RuntimeException("Arabic designation cannot be empty");
            }
            if (stateRepository.existsByDesignationArAndIdNot(stateDTO.getDesignationAr(), id)) {
                throw new RuntimeException("State with Arabic designation '" + stateDTO.getDesignationAr() + "' already exists");
            }
            existingState.setDesignationAr(stateDTO.getDesignationAr()); // F_02
        }

        if (stateDTO.getDesignationLt() != null) {
            if (stateDTO.getDesignationLt().trim().isEmpty()) {
                throw new RuntimeException("Latin designation cannot be empty");
            }
            if (stateRepository.existsByDesignationLtAndIdNot(stateDTO.getDesignationLt(), id)) {
                throw new RuntimeException("State with Latin designation '" + stateDTO.getDesignationLt() + "' already exists");
            }
            existingState.setDesignationLt(stateDTO.getDesignationLt()); // F_03
        }

        State updatedState = stateRepository.save(existingState);
        log.info("Successfully partially updated state with ID: {}", id);

        return StateDTO.fromEntity(updatedState);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteState(Long id) {
        log.info("Deleting state with ID: {}", id);

        State state = getStateEntityById(id);
        stateRepository.delete(state);

        log.info("Successfully deleted state with ID: {}", id);
    }

    public void deleteStateById(Long id) {
        log.info("Deleting state by ID: {}", id);

        if (!stateRepository.existsById(id)) {
            throw new RuntimeException("State not found with ID: " + id);
        }

        stateRepository.deleteById(id);
        log.info("Successfully deleted state with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return stateRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(int code) {
        return stateRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationAr(String designationAr) {
        return stateRepository.existsByDesignationAr(designationAr);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationLt(String designationLt) {
        return stateRepository.existsByDesignationLt(designationLt);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return stateRepository.countAllStates();
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(StateDTO stateDTO, String operation) {
        if (stateDTO.getCode() == 0 ) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (stateDTO.getDesignationAr() == null || stateDTO.getDesignationAr().trim().isEmpty()) {
            throw new RuntimeException("Arabic designation is required for " + operation);
        }

        if (stateDTO.getDesignationLt() == null || stateDTO.getDesignationLt().trim().isEmpty()) {
            throw new RuntimeException("Latin designation is required for " + operation);
        }
    }

    private void validateUniqueConstraints(StateDTO stateDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (stateRepository.existsByCode(stateDTO.getCode())) {
                throw new RuntimeException("State with code '" + stateDTO.getCode() + "' already exists");
            }
        } else {
            if (stateRepository.existsByCodeAndIdNot(stateDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another state with code '" + stateDTO.getCode() + "' already exists");
            }
        }

        // Check Arabic designation uniqueness (F_02)
        if (excludeId == null) {
            if (stateRepository.existsByDesignationAr(stateDTO.getDesignationAr())) {
                throw new RuntimeException("State with Arabic designation '" + stateDTO.getDesignationAr() + "' already exists");
            }
        } else {
            if (stateRepository.existsByDesignationArAndIdNot(stateDTO.getDesignationAr(), excludeId)) {
                throw new RuntimeException("Another state with Arabic designation '" + stateDTO.getDesignationAr() + "' already exists");
            }
        }

        // Check Latin designation uniqueness (F_03)
        if (excludeId == null) {
            if (stateRepository.existsByDesignationLt(stateDTO.getDesignationLt())) {
                throw new RuntimeException("State with Latin designation '" + stateDTO.getDesignationLt() + "' already exists");
            }
        } else {
            if (stateRepository.existsByDesignationLtAndIdNot(stateDTO.getDesignationLt(), excludeId)) {
                throw new RuntimeException("Another state with Latin designation '" + stateDTO.getDesignationLt() + "' already exists");
            }
        }
    }
}
