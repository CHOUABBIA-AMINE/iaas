/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityService
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Service
 *	@Layer		: Network / Service
 *	@Package	: Network / Service
 *
 **/

package dz.mdn.iaas.network.service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.exception.BusinessValidationException;
import dz.mdn.iaas.network.dto.FacilityDTO;
import dz.mdn.iaas.network.model.*;
import dz.mdn.iaas.network.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FacilityService extends GenericService<Facility, FacilityDTO, Long> {

    private final FacilityRepository facilityRepository;
    private final OperationalStatusRepository operationalStatusRepository;
    private final LocationRepository locationRepository;
    private final FacilityTypeRepository facilityTypeRepository;

    @Override
    protected JpaRepository<Facility, Long> getRepository() {
        return facilityRepository;
    }

    @Override
    protected String getEntityName() {
        return "Facility";
    }

    @Override
    protected FacilityDTO toDTO(Facility entity) {
        return FacilityDTO.fromEntity(entity);
    }

    @Override
    protected Facility toEntity(FacilityDTO dto) {
        Facility entity = dto.toEntity();
        
        if (dto.getOperationalStatusId() != null) {
            OperationalStatus status = operationalStatusRepository.findById(dto.getOperationalStatusId())
                    .orElseThrow(() -> new RuntimeException("Operational status not found with id: " + dto.getOperationalStatusId()));
            entity.setOperationalStatus(status);
        }
        
        if (dto.getLocationId() != null) {
            Location location = locationRepository.findById(dto.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Location not found with id: " + dto.getLocationId()));
            entity.setLocation(location);
        }
        
        if (dto.getFacilityTypeId() != null) {
            FacilityType type = facilityTypeRepository.findById(dto.getFacilityTypeId())
                    .orElseThrow(() -> new RuntimeException("Facility type not found with id: " + dto.getFacilityTypeId()));
            entity.setFacilityType(type);
        }
        
        return entity;
    }

    @Override
    protected void updateEntityFromDTO(Facility entity, FacilityDTO dto) {
        dto.updateEntity(entity);
        
        if (dto.getOperationalStatusId() != null) {
            OperationalStatus status = operationalStatusRepository.findById(dto.getOperationalStatusId())
                    .orElseThrow(() -> new RuntimeException("Operational status not found with id: " + dto.getOperationalStatusId()));
            entity.setOperationalStatus(status);
        }
        
        if (dto.getLocationId() != null) {
            Location location = locationRepository.findById(dto.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Location not found with id: " + dto.getLocationId()));
            entity.setLocation(location);
        }
        
        if (dto.getFacilityTypeId() != null) {
            FacilityType type = facilityTypeRepository.findById(dto.getFacilityTypeId())
                    .orElseThrow(() -> new RuntimeException("Facility type not found with id: " + dto.getFacilityTypeId()));
            entity.setFacilityType(type);
        }
    }

    @Override
    @Transactional
    public FacilityDTO create(FacilityDTO dto) {
        log.info("Creating facility: code={}", dto.getCode());
        
        if (facilityRepository.existsByCode(dto.getCode())) {
            throw new BusinessValidationException("Facility with code '" + dto.getCode() + "' already exists");
        }
        
        return super.create(dto);
    }

    @Override
    @Transactional
    public FacilityDTO update(Long id, FacilityDTO dto) {
        log.info("Updating facility with ID: {}", id);
        
        if (facilityRepository.existsByCodeAndIdNot(dto.getCode(), id)) {
            throw new BusinessValidationException("Facility with code '" + dto.getCode() + "' already exists");
        }
        
        return super.update(id, dto);
    }

    public List<FacilityDTO> getAll() {
        log.debug("Getting all facilities without pagination");
        return facilityRepository.findAll().stream()
                .map(FacilityDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<FacilityDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for facilities with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> facilityRepository.searchByAnyField(searchTerm.trim(), p), pageable);
    }

    public List<FacilityDTO> findByLocation(Long locationId) {
        log.debug("Finding facilities by location id: {}", locationId);
        return facilityRepository.findByLocationId(locationId).stream()
                .map(FacilityDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<FacilityDTO> findByFacilityType(Long typeId) {
        log.debug("Finding facilities by facility type id: {}", typeId);
        return facilityRepository.findByFacilityTypeId(typeId).stream()
                .map(FacilityDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
