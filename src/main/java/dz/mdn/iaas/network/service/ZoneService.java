/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ZoneService
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
import dz.mdn.iaas.network.dto.ZoneDTO;
import dz.mdn.iaas.network.model.Zone;
import dz.mdn.iaas.network.repository.ZoneRepository;
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
public class ZoneService extends GenericService<Zone, ZoneDTO, Long> {

    private final ZoneRepository zoneRepository;

    @Override
    protected JpaRepository<Zone, Long> getRepository() {
        return zoneRepository;
    }

    @Override
    protected String getEntityName() {
        return "Zone";
    }

    @Override
    protected ZoneDTO toDTO(Zone entity) {
        return ZoneDTO.fromEntity(entity);
    }

    @Override
    protected Zone toEntity(ZoneDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(Zone entity, ZoneDTO dto) {
        dto.updateEntity(entity);
    }

    @Override
    @Transactional
    public ZoneDTO create(ZoneDTO dto) {
        log.info("Creating zone: name={}", dto.getName());
        
        if (zoneRepository.existsByName(dto.getName())) {
            throw new BusinessValidationException("Zone with name '" + dto.getName() + "' already exists");
        }
        
        return super.create(dto);
    }

    @Override
    @Transactional
    public ZoneDTO update(Long id, ZoneDTO dto) {
        log.info("Updating zone with ID: {}", id);
        
        if (zoneRepository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new BusinessValidationException("Zone with name '" + dto.getName() + "' already exists");
        }
        
        return super.update(id, dto);
    }

    public List<ZoneDTO> getAll() {
        log.debug("Getting all zones without pagination");
        return zoneRepository.findAll().stream()
                .map(ZoneDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<ZoneDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for zones with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> zoneRepository.searchByName(searchTerm.trim(), p), pageable);
    }
}
