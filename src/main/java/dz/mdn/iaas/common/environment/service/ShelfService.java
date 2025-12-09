/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.model.Shelf;
import dz.mdn.iaas.common.environment.model.Room;
import dz.mdn.iaas.common.environment.repository.ShelfRepository;
import dz.mdn.iaas.common.environment.repository.RoomRepository;
import dz.mdn.iaas.common.environment.dto.ShelfDTO;

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
public class ShelfService {

    private final ShelfRepository shelfRepository;
    private final RoomRepository roomRepository;

    // ========== CREATE OPERATIONS ==========

    public ShelfDTO createShelf(ShelfDTO shelfDTO) {
        log.info("Creating shelf with code: {} for room ID: {}", 
                shelfDTO.getCode(), shelfDTO.getRoomId());

        // Validate required fields
        validateRequiredFields(shelfDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(shelfDTO, null);

        // Validate required relationship
        Room room = validateAndGetRoom(shelfDTO.getRoomId());

        // Create entity with exact field mapping
        Shelf shelf = new Shelf();
        shelf.setCode(shelfDTO.getCode()); // F_01
        shelf.setRoom(room); // F_02

        Shelf savedShelf = shelfRepository.save(shelf);
        log.info("Successfully created shelf with ID: {}", savedShelf.getId());

        return ShelfDTO.fromEntity(savedShelf);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public ShelfDTO getShelfById(Long id) {
        log.debug("Getting shelf with ID: {}", id);

        Shelf shelf = shelfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelf not found with ID: " + id));

        return ShelfDTO.fromEntity(shelf);
    }

    @Transactional(readOnly = true)
    public Shelf getShelfEntityById(Long id) {
        return shelfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelf not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<ShelfDTO> findByCode(String code) {
        log.debug("Finding shelf with code: {}", code);

        return shelfRepository.findByCode(code)
                .map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getAllShelves(Pageable pageable) {
        log.debug("Getting all shelves with pagination");

        Page<Shelf> shelves = shelfRepository.findAllWithRelationships(pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<ShelfDTO> findOne(Long id) {
        log.debug("Finding shelf by ID: {}", id);

        return shelfRepository.findById(id)
                .map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getShelvesByRoomId(Long roomId, Pageable pageable) {
        log.debug("Getting shelves for room ID: {}", roomId);

        validateRoomExists(roomId);
        Page<Shelf> shelves = shelfRepository.findByRoomIdWithRelationships(roomId, pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getShelvesByBlocId(Long blocId, Pageable pageable) {
        log.debug("Getting shelves for bloc ID: {}", blocId);

        Page<Shelf> shelves = shelfRepository.findByBlocId(blocId, pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getShelvesByFloorId(Long floorId, Pageable pageable) {
        log.debug("Getting shelves for floor ID: {}", floorId);

        Page<Shelf> shelves = shelfRepository.findByFloorId(floorId, pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getShelvesByStructureId(Long structureId, Pageable pageable) {
        log.debug("Getting shelves for structure ID: {}", structureId);

        Page<Shelf> shelves = shelfRepository.findByStructureId(structureId, pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getEmptyShelves(Pageable pageable) {
        log.debug("Getting empty shelves");

        Page<Shelf> shelves = shelfRepository.findEmptyShelves(pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getShelvesWithArchiveBoxes(Pageable pageable) {
        log.debug("Getting shelves with archive boxes");

        Page<Shelf> shelves = shelfRepository.findShelvesWithArchiveBoxes(pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getDocumentShelves(Pageable pageable) {
        log.debug("Getting document shelves");

        Page<Shelf> shelves = shelfRepository.findDocumentShelves(pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getArchiveShelves(Pageable pageable) {
        log.debug("Getting archive shelves");

        Page<Shelf> shelves = shelfRepository.findArchiveShelves(pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> getReferenceShelves(Pageable pageable) {
        log.debug("Getting reference shelves");

        Page<Shelf> shelves = shelfRepository.findReferenceShelves(pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ShelfDTO> searchShelves(String searchTerm, Pageable pageable) {
        log.debug("Searching shelves with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllShelves(pageable);
        }

        Page<Shelf> shelves = shelfRepository.searchByCodeOrRoomInfo(searchTerm.trim(), pageable);
        return shelves.map(ShelfDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public ShelfDTO updateShelf(Long id, ShelfDTO shelfDTO) {
        log.info("Updating shelf with ID: {}", id);

        Shelf existingShelf = getShelfEntityById(id);

        // Validate required fields
        validateRequiredFields(shelfDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(shelfDTO, id);

        // Validate required relationship
        Room room = validateAndGetRoom(shelfDTO.getRoomId());

        // Update fields with exact field mapping
        existingShelf.setCode(shelfDTO.getCode()); // F_01
        existingShelf.setRoom(room); // F_02

        Shelf updatedShelf = shelfRepository.save(existingShelf);
        log.info("Successfully updated shelf with ID: {}", id);

        return ShelfDTO.fromEntity(updatedShelf);
    }

    public ShelfDTO moveShelfToRoom(Long shelfId, Long newRoomId) {
        log.info("Moving shelf ID: {} to room ID: {}", shelfId, newRoomId);

        Shelf shelf = getShelfEntityById(shelfId);
        Room newRoom = validateAndGetRoom(newRoomId);

        shelf.setRoom(newRoom); // F_02
        Shelf updatedShelf = shelfRepository.save(shelf);

        log.info("Successfully moved shelf to new room");
        return ShelfDTO.fromEntity(updatedShelf);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteShelf(Long id) {
        log.info("Deleting shelf with ID: {}", id);

        Shelf shelf = getShelfEntityById(id);
        shelfRepository.delete(shelf);

        log.info("Successfully deleted shelf with ID: {}", id);
    }

    public void deleteShelfById(Long id) {
        log.info("Deleting shelf by ID: {}", id);

        if (!shelfRepository.existsById(id)) {
            throw new RuntimeException("Shelf not found with ID: " + id);
        }

        shelfRepository.deleteById(id);
        log.info("Successfully deleted shelf with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return shelfRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return shelfRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return shelfRepository.countAllShelves();
    }

    @Transactional(readOnly = true)
    public Long getCountByRoomId(Long roomId) {
        return shelfRepository.countByRoomId(roomId);
    }

    @Transactional(readOnly = true)
    public Long getCountByBlocId(Long blocId) {
        return shelfRepository.countByBlocId(blocId);
    }

    @Transactional(readOnly = true)
    public Long getCountByFloorId(Long floorId) {
        return shelfRepository.countByFloorId(floorId);
    }

    @Transactional(readOnly = true)
    public Long getEmptyShelvesCount() {
        return shelfRepository.countEmptyShelves();
    }

    @Transactional(readOnly = true)
    public Long getShelvesWithArchiveBoxesCount() {
        return shelfRepository.countShelvesWithArchiveBoxes();
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(ShelfDTO shelfDTO, String operation) {
        if (shelfDTO.getCode() == null || shelfDTO.getCode().trim().isEmpty()) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (shelfDTO.getRoomId() == null) {
            throw new RuntimeException("Room ID is required for " + operation);
        }
    }

    private void validateUniqueConstraints(ShelfDTO shelfDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (shelfRepository.existsByCode(shelfDTO.getCode())) {
                throw new RuntimeException("Shelf with code '" + shelfDTO.getCode() + "' already exists");
            }
        } else {
            if (shelfRepository.existsByCodeAndIdNot(shelfDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another shelf with code '" + shelfDTO.getCode() + "' already exists");
            }
        }
    }

    private Room validateAndGetRoom(Long roomId) {
        if (roomId == null) {
            throw new RuntimeException("Room ID is required");
        }
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
    }

    private void validateRoomExists(Long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new RuntimeException("Room not found with ID: " + roomId);
        }
    }
}
