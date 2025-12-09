/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoomService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.model.Room;
import dz.mdn.iaas.common.environment.model.Bloc;
import dz.mdn.iaas.common.environment.model.Floor;
import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.common.environment.repository.RoomRepository;
import dz.mdn.iaas.common.environment.repository.BlocRepository;
import dz.mdn.iaas.common.environment.repository.FloorRepository;
import dz.mdn.iaas.common.administration.repository.StructureRepository;
import dz.mdn.iaas.common.environment.dto.RoomDTO;

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
public class RoomService {

    private final RoomRepository roomRepository;
    private final BlocRepository blocRepository;
    private final FloorRepository floorRepository;
    private final StructureRepository structureRepository;

    // ========== CREATE OPERATIONS ==========

    public RoomDTO createRoom(RoomDTO roomDTO) {
        log.info("Creating room with code: {} for bloc ID: {}, floor ID: {}", roomDTO.getCode(), roomDTO.getBlocId(), roomDTO.getFloorId());

        // Validate required fields
        validateRequiredFields(roomDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(roomDTO, null);

        // Validate required relationships
        Bloc bloc = validateAndGetBloc(roomDTO.getBlocId());
        Floor floor = validateAndGetFloor(roomDTO.getFloorId());
        Structure structure = null;
        if (roomDTO.getStructureId() != null) {
            structure = validateAndGetStructure(roomDTO.getStructureId());
        }

        // Create entity with exact field mapping
        Room room = new Room();
        room.setCode(roomDTO.getCode()); // F_01
        room.setBloc(bloc); // F_02
        room.setFloor(floor); // F_03
        room.setStructure(structure); // F_07

        Room savedRoom = roomRepository.save(room);
        log.info("Successfully created room with ID: {}", savedRoom.getId());

        return RoomDTO.fromEntity(savedRoom);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public RoomDTO getRoomById(Long id) {
        log.debug("Getting room with ID: {}", id);

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));

        return RoomDTO.fromEntity(room);
    }

    @Transactional(readOnly = true)
    public Room getRoomEntityById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<RoomDTO> findByCode(String code) {
        log.debug("Finding room with code: {}", code);

        return roomRepository.findByCode(code)
                .map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> getAllRooms(Pageable pageable) {
        log.debug("Getting all rooms with pagination");

        Page<Room> rooms = roomRepository.findAllWithRelationships(pageable);
        return rooms.map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<RoomDTO> findOne(Long id) {
        log.debug("Finding room by ID: {}", id);

        return roomRepository.findById(id)
                .map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> getRoomsByBlocId(Long blocId, Pageable pageable) {
        log.debug("Getting rooms for bloc ID: {}", blocId);

        validateBlocExists(blocId);
        Page<Room> rooms = roomRepository.findByBlocId(blocId, pageable);
        return rooms.map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> getRoomsByFloorId(Long floorId, Pageable pageable) {
        log.debug("Getting rooms for floor ID: {}", floorId);

        validateFloorExists(floorId);
        Page<Room> rooms = roomRepository.findByFloorId(floorId, pageable);
        return rooms.map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> getRoomsByStructureId(Long structureId, Pageable pageable) {
        log.debug("Getting rooms for structure ID: {}", structureId);

        validateStructureExists(structureId);
        Page<Room> rooms = roomRepository.findByStructureId(structureId, pageable);
        return rooms.map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> getRoomsWithoutStructure(Pageable pageable) {
        log.debug("Getting rooms without structure assigned");

        Page<Room> rooms = roomRepository.findRoomsWithoutStructure(pageable);
        return rooms.map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> getRoomsWithStructure(Pageable pageable) {
        log.debug("Getting rooms with structure assigned");

        Page<Room> rooms = roomRepository.findRoomsWithStructure(pageable);
        return rooms.map(RoomDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> searchRooms(String searchTerm, Pageable pageable) {
        log.debug("Searching rooms with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllRooms(pageable);
        }

        Page<Room> rooms = roomRepository.searchByCode(searchTerm.trim(), pageable);
        return rooms.map(RoomDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        log.info("Updating room with ID: {}", id);

        Room existingRoom = getRoomEntityById(id);

        // Validate required fields
        validateRequiredFields(roomDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(roomDTO, id);

        // Validate required relationships
        Bloc bloc = validateAndGetBloc(roomDTO.getBlocId());
        Floor floor = validateAndGetFloor(roomDTO.getFloorId());
        Structure structure = null;
        if (roomDTO.getStructureId() != null) {
            structure = validateAndGetStructure(roomDTO.getStructureId());
        }

        // Update fields with exact field mapping
        existingRoom.setCode(roomDTO.getCode()); // F_01
        existingRoom.setBloc(bloc); // F_02
        existingRoom.setFloor(floor); // F_03
        existingRoom.setStructure(structure); // F_04

        Room updatedRoom = roomRepository.save(existingRoom);
        log.info("Successfully updated room with ID: {}", id);

        return RoomDTO.fromEntity(updatedRoom);
    }

    public RoomDTO assignStructure(Long roomId, Long structureId) {
        log.info("Assigning structure ID: {} to room ID: {}", structureId, roomId);

        Room room = getRoomEntityById(roomId);
        Structure structure = validateAndGetStructure(structureId);

        room.setStructure(structure); // F_07
        Room updatedRoom = roomRepository.save(room);

        log.info("Successfully assigned structure to room with ID: {}", roomId);
        return RoomDTO.fromEntity(updatedRoom);
    }

    public RoomDTO unassignStructure(Long roomId) {
        log.info("Unassigning structure from room ID: {}", roomId);

        Room room = getRoomEntityById(roomId);
        room.setStructure(null); // F_07
        Room updatedRoom = roomRepository.save(room);

        log.info("Successfully unassigned structure from room with ID: {}", roomId);
        return RoomDTO.fromEntity(updatedRoom);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteRoom(Long id) {
        log.info("Deleting room with ID: {}", id);

        Room room = getRoomEntityById(id);
        roomRepository.delete(room);

        log.info("Successfully deleted room with ID: {}", id);
    }

    public void deleteRoomById(Long id) {
        log.info("Deleting room by ID: {}", id);

        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found with ID: " + id);
        }

        roomRepository.deleteById(id);
        log.info("Successfully deleted room with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return roomRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return roomRepository.existsByCode(code);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return roomRepository.countAllRooms();
    }

    @Transactional(readOnly = true)
    public Long getCountByBlocId(Long blocId) {
        return roomRepository.countByBlocId(blocId);
    }

    @Transactional(readOnly = true)
    public Long getCountByFloorId(Long floorId) {
        return roomRepository.countByFloorId(floorId);
    }

    @Transactional(readOnly = true)
    public Long getCountByStructureId(Long structureId) {
        return roomRepository.countByStructureId(structureId);
    }

    @Transactional(readOnly = true)
    public Long getCountWithoutStructure() {
        return roomRepository.countRoomsWithoutStructure();
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(RoomDTO roomDTO, String operation) {
        if (roomDTO.getCode() == null || roomDTO.getCode().trim().isEmpty()) {
            throw new RuntimeException("Code is required for " + operation);
        }

        if (roomDTO.getBlocId() == null) {
            throw new RuntimeException("Bloc ID is required for " + operation);
        }

        if (roomDTO.getFloorId() == null) {
            throw new RuntimeException("Floor ID is required for " + operation);
        }
    }

    private void validateUniqueConstraints(RoomDTO roomDTO, Long excludeId) {
        // Check code uniqueness (F_01)
        if (excludeId == null) {
            if (roomRepository.existsByCode(roomDTO.getCode())) {
                throw new RuntimeException("Room with code '" + roomDTO.getCode() + "' already exists");
            }
        } else {
            if (roomRepository.existsByCodeAndIdNot(roomDTO.getCode(), excludeId)) {
                throw new RuntimeException("Another room with code '" + roomDTO.getCode() + "' already exists");
            }
        }
    }

    private Bloc validateAndGetBloc(Long blocId) {
        if (blocId == null) {
            throw new RuntimeException("Bloc ID is required");
        }
        return blocRepository.findById(blocId)
                .orElseThrow(() -> new RuntimeException("Bloc not found with ID: " + blocId));
    }

    private Floor validateAndGetFloor(Long floorId) {
        if (floorId == null) {
            throw new RuntimeException("Floor ID is required");
        }
        return floorRepository.findById(floorId)
                .orElseThrow(() -> new RuntimeException("Floor not found with ID: " + floorId));
    }

    private Structure validateAndGetStructure(Long structureId) {
        if (structureId == null) {
            return null;
        }
        return structureRepository.findById(structureId)
                .orElseThrow(() -> new RuntimeException("Structure not found with ID: " + structureId));
    }

    private void validateBlocExists(Long blocId) {
        if (!blocRepository.existsById(blocId)) {
            throw new RuntimeException("Bloc not found with ID: " + blocId);
        }
    }

    private void validateFloorExists(Long floorId) {
        if (!floorRepository.existsById(floorId)) {
            throw new RuntimeException("Floor not found with ID: " + floorId);
        }
    }

    private void validateStructureExists(Long structureId) {
        if (!structureRepository.existsById(structureId)) {
            throw new RuntimeException("Structure not found with ID: " + structureId);
        }
    }
}
