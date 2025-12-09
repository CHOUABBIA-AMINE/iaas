/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocalityService
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.common.administration.dto.LocalityDTO;
import dz.mdn.iaas.common.administration.model.Locality;
import dz.mdn.iaas.common.administration.model.State;
import dz.mdn.iaas.common.administration.repository.LocalityRepository;
import dz.mdn.iaas.common.administration.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LocalityService {

    private final LocalityRepository localityRepository;
    private final StateRepository stateRepository;

    // ========== CREATE OPERATIONS ==========

    public LocalityDTO createLocality(LocalityDTO localityDTO) {
        log.info("Creating locality with code: {} and designations: {} | {} for state ID: {}", 
                localityDTO.getCode(), localityDTO.getDesignationAr(), localityDTO.getDesignationLt(), localityDTO.getStateId());

        // Validate all required fields
        validateRequiredFields(localityDTO, "create");

        // Validate state exists
        State state = validateAndGetState(localityDTO.getStateId());

        // Check for unique constraint violations
        validateUniqueConstraints(localityDTO, null);

        // Create entity with exact field mapping
        Locality locality = new Locality();
        locality.setCode(localityDTO.getCode()); // F_01
        locality.setDesignationAr(localityDTO.getDesignationAr()); // F_02
        locality.setDesignationLt(localityDTO.getDesignationLt()); // F_03
        locality.setState(state); // F_04

        Locality savedLocality = localityRepository.save(locality);
        log.info("Successfully created locality with ID: {}", savedLocality.getId());

        return LocalityDTO.fromEntity(savedLocality);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public LocalityDTO getLocalityById(Long id) {
        log.debug("Getting locality with ID: {}", id);

        Locality locality = localityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locality not found with ID: " + id));

        return LocalityDTO.fromEntity(locality);
    }

    @Transactional(readOnly = true)
    public Locality getLocalityEntityById(Long id) {
        return localityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locality not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<LocalityDTO> findByCode(int code) {
        log.debug("Finding locality with code: {}", code);

        return localityRepository.findByCode(code)
                .map(LocalityDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<LocalityDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding locality with Arabic designation: {}", designationAr);

        return localityRepository.findByDesignationAr(designationAr)
                .map(LocalityDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<LocalityDTO> findByDesignationLt(String designationLt) {
        log.debug("Finding locality with Latin designation: {}", designationLt);

        return localityRepository.findByDesignationLt(designationLt)
                .map(LocalityDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<LocalityDTO> getAllLocalities(Pageable pageable) {
        log.debug("Getting all localities with pagination");

        Page<Locality> localities = localityRepository.findAllWithState(pageable);
        return localities.map(LocalityDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<LocalityDTO> findOne(Long id) {
        log.debug("Finding locality by ID: {}", id);

        return localityRepository.findById(id)
                .map(LocalityDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<LocalityDTO> getLocalitiesByStateId(Long stateId, Pageable pageable) {
        log.debug("Getting localities for state ID: {}", stateId);

        // Validate state exists
        validateStateExists(stateId);

        Page<Locality> localities = localityRepository.findByStateIdWithState(stateId, pageable);
        return localities.map(LocalityDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<LocalityDTO> getLocalitiesByStateIdAsList(Long stateId) {
        log.debug("Getting localities list for state ID: {}", stateId);

        // Validate state exists
        validateStateExists(stateId);

        List<Locality> localities = localityRepository.findByStateIdOrderByCode(stateId);
        return localities.stream().map(LocalityDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Page<LocalityDTO> searchLocalities(String searchTerm, Pageable pageable) {
        log.debug("Searching localities with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllLocalities(pageable);
        }

        Page<Locality> localities = localityRepository.searchByAnyField(searchTerm.trim(), pageable);
        return localities.map(LocalityDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<LocalityDTO> searchLocalitiesInState(String searchTerm, Long stateId, Pageable pageable) {
        log.debug("Searching localities with term: {} in state ID: {}", searchTerm, stateId);

        // Validate state exists
        validateStateExists(stateId);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getLocalitiesByStateId(stateId, pageable);
        }

        Page<Locality> localities = localityRepository.searchByAnyFieldAndStateId(searchTerm.trim(), stateId, pageable);
        return localities.map(LocalityDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public LocalityDTO updateLocality(Long id, LocalityDTO localityDTO) {
        log.info("Updating locality with ID: {}", id);

        Locality existingLocality = getLocalityEntityById(id);

        // Validate all required fields
        validateRequiredFields(localityDTO, "update");

        // Validate state exists
        State state = validateAndGetState(localityDTO.getStateId());

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(localityDTO, id);

        // Update fields with exact field mapping
        existingLocality.setCode(localityDTO.getCode()); // F_01
        existingLocality.setDesignationAr(localityDTO.getDesignationAr()); // F_02
        existingLocality.setDesignationLt(localityDTO.getDesignationLt()); // F_03
        existingLocality.setState(state); // F_04

        Locality updatedLocality = localityRepository.save(existingLocality);
        log.info("Successfully updated locality with ID: {}", id);

        return LocalityDTO.fromEntity(updatedLocality);
    }

    public LocalityDTO partialUpdateLocality(Long id, LocalityDTO localityDTO) {
        log.info("Partially updating locality with ID: {}", id);

        Locality existingLocality = getLocalityEntityById(id);

        boolean updated = false;

        // Update only non-null fields
        if (localityDTO.getCode() != 0) {
            if (localityRepository.existsByCodeAndIdNot(localityDTO.getCode(), id)) {
                throw new RuntimeException("Locality with code '" + localityDTO.getCode() + "' already exists");
            }
            existingLocality.setCode(localityDTO.getCode()); // F_01
            updated = true;
        }

        if (localityDTO.getDesignationAr() != null) {
            if (localityDTO.getDesignationAr().trim().isEmpty()) {
                throw new RuntimeException("Arabic designation cannot be empty");
            }
            if (localityRepository.existsByDesignationArAndIdNot(localityDTO.getDesignationAr(), id)) {
                throw new RuntimeException("Locality with Arabic designation '" + localityDTO.getDesignationAr() + "' already exists");
            }
            existingLocality.setDesignationAr(localityDTO.getDesignationAr()); // F_02
            updated = true;
        }

        if (localityDTO.getDesignationLt() != null) {
            if (localityDTO.getDesignationLt().trim().isEmpty()) {
                throw new RuntimeException("Latin designation cannot be empty");
            }
            if (localityRepository.existsByDesignationLtAndIdNot(localityDTO.getDesignationLt(), id)) {
                throw new RuntimeException("Locality with Latin designation '" + localityDTO.getDesignationLt() + "' already exists");
            }
            existingLocality.setDesignationLt(localityDTO.getDesignationLt()); // F_03
            updated = true;
        }

        if (localityDTO.getStateId() != null) {
            State state = validateAndGetState(localityDTO.getStateId());
            existingLocality.setState(state); // F_04
            updated = true;
        }

        if (updated) {
            Locality updatedLocality = localityRepository.save(existingLocality);
            log.info("Successfully partially updated locality with ID: {}", id);
            return LocalityDTO.fromEntity(updatedLocality);
        } else {
            log.debug("No fields to update for locality with ID: {}", id);
            return LocalityDTO.fromEntity(existingLocality);
        }
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteLocality(Long id) {
        log.info("Deleting locality with ID: {}", id);

        Locality locality = getLocalityEntityById(id);
        localityRepository.delete(locality);

        log.info("Successfully deleted locality with ID: {}", id);
    }

    public void deleteLocalityById(Long id) {
        log.info("Deleting locality by ID: {}", id);

        if (!localityRepository.existsById(id)) {
            throw new RuntimeException("Locality not found with ID: " + id);
        }

        localityRepository.deleteById(id);
        log.info("Successfully deleted locality with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return localityRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(int code) {
        return localityRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationAr(String designationAr) {
        return localityRepository.existsByDesignationAr(designationAr);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationLt(String designationLt) {
        return localityRepository.existsByDesignationLt(designationLt);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return localityRepository.countAllLocalities();
    }

    @Transactional(readOnly = true)
    public Long getCountByStateId(Long stateId) {
        return localityRepository.countByStateId(stateId);
    }

    @Transactional(readOnly = true)
    public boolean hasLocalitiesInState(Long stateId) {
        return localityRepository.hasLocalitiesInState(stateId);
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(LocalityDTO localityDTO, String operation) {
        if (localityDTO.getCode() == 0 ) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (localityDTO.getDesignationAr() == null || localityDTO.getDesignationAr().trim().isEmpty()) {
            throw new RuntimeException("Arabic designation is required for " + operation);
        }

        if (localityDTO.getDesignationLt() == null || localityDTO.getDesignationLt().trim().isEmpty()) {
            throw new RuntimeException("Latin designation is required for " + operation);
        }

        if (localityDTO.getStateId() == null) {
            throw new RuntimeException("State ID is required for " + operation);
        }
    }

    private void validateUniqueConstraints(LocalityDTO localityDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (localityRepository.existsByCode(localityDTO.getCode())) {
                throw new RuntimeException("Locality with code '" + localityDTO.getCode() + "' already exists");
            }
        } else {
            if (localityRepository.existsByCodeAndIdNot(localityDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another locality with code '" + localityDTO.getCode() + "' already exists");
            }
        }

        // Check Arabic designation uniqueness (F_02)
        if (excludeId == null) {
            if (localityRepository.existsByDesignationAr(localityDTO.getDesignationAr())) {
                throw new RuntimeException("Locality with Arabic designation '" + localityDTO.getDesignationAr() + "' already exists");
            }
        } else {
            if (localityRepository.existsByDesignationArAndIdNot(localityDTO.getDesignationAr(), excludeId)) {
                throw new RuntimeException("Another locality with Arabic designation '" + localityDTO.getDesignationAr() + "' already exists");
            }
        }

        // Check Latin designation uniqueness (F_03)
        if (excludeId == null) {
            if (localityRepository.existsByDesignationLt(localityDTO.getDesignationLt())) {
                throw new RuntimeException("Locality with Latin designation '" + localityDTO.getDesignationLt() + "' already exists");
            }
        } else {
            if (localityRepository.existsByDesignationLtAndIdNot(localityDTO.getDesignationLt(), excludeId)) {
                throw new RuntimeException("Another locality with Latin designation '" + localityDTO.getDesignationLt() + "' already exists");
            }
        }
    }

    private State validateAndGetState(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + stateId));
    }

    private void validateStateExists(Long stateId) {
        if (!stateRepository.existsById(stateId)) {
            throw new RuntimeException("State not found with ID: " + stateId);
        }
    }
}
