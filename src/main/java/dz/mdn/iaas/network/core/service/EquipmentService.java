/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentService
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
import dz.mdn.iaas.network.dto.EquipmentDTO;
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
public class EquipmentService extends GenericService<Equipment, EquipmentDTO, Long> {

    private final EquipmentRepository equipmentRepository;
    private final OperationalStatusRepository operationalStatusRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final FacilityRepository facilityRepository;

    @Override
    protected JpaRepository<Equipment, Long> getRepository() {
        return equipmentRepository;
    }

    @Override
    protected String getEntityName() {
        return "Equipment";
    }

    @Override
    protected EquipmentDTO toDTO(Equipment entity) {
        return EquipmentDTO.fromEntity(entity);
    }

    @Override
    protected Equipment toEntity(EquipmentDTO dto) {
        Equipment entity = dto.toEntity();
        
        if (dto.getOperationalStatusId() != null) {
            OperationalStatus status = operationalStatusRepository.findById(dto.getOperationalStatusId())
                    .orElseThrow(() -> new RuntimeException("Operational status not found with id: " + dto.getOperationalStatusId()));
            entity.setOperationalStatus(status);
        }
        
        if (dto.getEquipmentTypeId() != null) {
            EquipmentType type = equipmentTypeRepository.findById(dto.getEquipmentTypeId())
                    .orElseThrow(() -> new RuntimeException("Equipment type not found with id: " + dto.getEquipmentTypeId()));
            entity.setEquipmentType(type);
        }
        
        if (dto.getFacilityId() != null) {
            Facility facility = facilityRepository.findById(dto.getFacilityId())
                    .orElseThrow(() -> new RuntimeException("Facility not found with id: " + dto.getFacilityId()));
            entity.setFacility(facility);
        }
        
        return entity;
    }

    @Override
    protected void updateEntityFromDTO(Equipment entity, EquipmentDTO dto) {
        dto.updateEntity(entity);
        
        if (dto.getOperationalStatusId() != null) {
            OperationalStatus status = operationalStatusRepository.findById(dto.getOperationalStatusId())
                    .orElseThrow(() -> new RuntimeException("Operational status not found with id: " + dto.getOperationalStatusId()));
            entity.setOperationalStatus(status);
        }
        
        if (dto.getEquipmentTypeId() != null) {
            EquipmentType type = equipmentTypeRepository.findById(dto.getEquipmentTypeId())
                    .orElseThrow(() -> new RuntimeException("Equipment type not found with id: " + dto.getEquipmentTypeId()));
            entity.setEquipmentType(type);
        }
        
        if (dto.getFacilityId() != null) {
            Facility facility = facilityRepository.findById(dto.getFacilityId())
                    .orElseThrow(() -> new RuntimeException("Facility not found with id: " + dto.getFacilityId()));
            entity.setFacility(facility);
        }
    }

    @Override
    @Transactional
    public EquipmentDTO create(EquipmentDTO dto) {
        log.info("Creating equipment: code={}", dto.getCode());
        
        if (equipmentRepository.existsByCode(dto.getCode())) {
            throw new BusinessValidationException("Equipment with code '" + dto.getCode() + "' already exists");
        }
        
        if (equipmentRepository.existsBySerialNumber(dto.getSerialNumber())) {
            throw new BusinessValidationException("Equipment with serial number '" + dto.getSerialNumber() + "' already exists");
        }
        
        return super.create(dto);
    }

    @Override
    @Transactional
    public EquipmentDTO update(Long id, EquipmentDTO dto) {
        log.info("Updating equipment with ID: {}", id);
        
        if (equipmentRepository.existsByCodeAndIdNot(dto.getCode(), id)) {
            throw new BusinessValidationException("Equipment with code '" + dto.getCode() + "' already exists");
        }
        
        if (equipmentRepository.existsBySerialNumberAndIdNot(dto.getSerialNumber(), id)) {
            throw new BusinessValidationException("Equipment with serial number '" + dto.getSerialNumber() + "' already exists");
        }
        
        return super.update(id, dto);
    }

    public List<EquipmentDTO> getAll() {
        log.debug("Getting all equipment without pagination");
        return equipmentRepository.findAll().stream()
                .map(EquipmentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<EquipmentDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for equipment with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> equipmentRepository.searchByAnyField(searchTerm.trim(), p), pageable);
    }

    public List<EquipmentDTO> findByFacility(Long facilityId) {
        log.debug("Finding equipment by facility id: {}", facilityId);
        return equipmentRepository.findByFacilityId(facilityId).stream()
                .map(EquipmentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<EquipmentDTO> findByEquipmentType(Long typeId) {
        log.debug("Finding equipment by equipment type id: {}", typeId);
        return equipmentRepository.findByEquipmentTypeId(typeId).stream()
                .map(EquipmentDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
