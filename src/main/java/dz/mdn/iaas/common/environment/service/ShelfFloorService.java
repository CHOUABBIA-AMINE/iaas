/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorService
 *	@CreatedOn	: 10-15-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Service
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Service
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.dto.ShelfFloorDTO;
import dz.mdn.iaas.common.environment.model.Shelf;
import dz.mdn.iaas.common.environment.model.ShelfFloor;
import dz.mdn.iaas.common.environment.repository.ShelfFloorRepository;
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
 * ShelfFloor Service - Extends GenericService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ShelfFloorService extends GenericService<ShelfFloor, ShelfFloorDTO, Long> {

    private final ShelfFloorRepository shelfFloorRepository;
    private final ShelfService shelfService;

    @Override
    protected JpaRepository<ShelfFloor, Long> getRepository() {
        return shelfFloorRepository;
    }

    @Override
    protected String getEntityName() {
        return "ShelfFloor";
    }

    @Override
    protected ShelfFloorDTO toDTO(ShelfFloor entity) {
        return ShelfFloorDTO.fromEntity(entity);
    }

    @Override
    protected ShelfFloor toEntity(ShelfFloorDTO dto) {
        ShelfFloor entity = dto.toEntity();
        
        // Set relationships
        if (dto.getShelfId() != null) {
            Shelf shelf = shelfService.getEntityById(dto.getShelfId());
            entity.setShelf(shelf);
        }
        
        return entity;
    }

    @Override
    protected void updateEntityFromDTO(ShelfFloor entity, ShelfFloorDTO dto) {
        dto.updateEntity(entity);
        
        // Update relationships
        if (dto.getShelfId() != null) {
            Shelf shelf = shelfService.getEntityById(dto.getShelfId());
            entity.setShelf(shelf);
        }
    }

    @Override
    @Transactional
    public ShelfFloorDTO create(ShelfFloorDTO dto) {
        log.info("Creating shelf floor: code={}, floorLevel={}, shelfId={}", 
                dto.getCode(), dto.getFloorLevel(), dto.getShelfId());
        return super.create(dto);
    }

    @Override
    @Transactional
    public ShelfFloorDTO update(Long id, ShelfFloorDTO dto) {
        log.info("Updating shelf floor with ID: {}", id);
        return super.update(id, dto);
    }

    public List<ShelfFloorDTO> getAll() {
        log.debug("Getting all shelf floors without pagination");
        return shelfFloorRepository.findAll().stream()
                .map(ShelfFloorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<ShelfFloorDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for shelf floors with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return getAll(pageable);
    }

    public List<ShelfFloorDTO> getByShelfId(Long shelfId) {
        log.debug("Getting shelf floors by shelf ID: {}", shelfId);
        return shelfFloorRepository.findByShelfId(shelfId).stream()
                .map(ShelfFloorDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
