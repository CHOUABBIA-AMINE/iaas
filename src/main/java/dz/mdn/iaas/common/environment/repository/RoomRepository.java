/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoomRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Room;
import dz.mdn.iaas.common.environment.model.Bloc;
import dz.mdn.iaas.common.environment.model.Floor;
import dz.mdn.iaas.common.administration.model.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.code = :code")
    Optional<Room> findByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r WHERE r.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r WHERE r.code = :code AND r.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT r FROM Room r LEFT JOIN FETCH r.bloc LEFT JOIN FETCH r.floor LEFT JOIN FETCH r.structure")
    Page<Room> findAllWithRelationships(Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.bloc = :bloc")
    Page<Room> findByBloc(@Param("bloc") Bloc bloc, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.bloc.id = :blocId")
    Page<Room> findByBlocId(@Param("blocId") Long blocId, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.floor = :floor")
    Page<Room> findByFloor(@Param("floor") Floor floor, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.floor.id = :floorId")
    Page<Room> findByFloorId(@Param("floorId") Long floorId, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.structure = :structure")
    Page<Room> findByStructure(@Param("structure") Structure structure, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.structure.id = :structureId")
    Page<Room> findByStructureId(@Param("structureId") Long structureId, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.structure IS NULL")
    Page<Room> findRoomsWithoutStructure(Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.structure IS NOT NULL")
    Page<Room> findRoomsWithStructure(Pageable pageable);

    @Query("SELECT r FROM Room r WHERE " +
           "r.code LIKE %:search%")
    Page<Room> searchByCode(@Param("search") String search, Pageable pageable);

    @Query("SELECT r FROM Room r ORDER BY r.code ASC")
    Page<Room> findAllOrderByCode(Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.bloc.id = :blocId AND r.floor.id = :floorId")
    Page<Room> findByBlocIdAndFloorId(@Param("blocId") Long blocId, @Param("floorId") Long floorId, Pageable pageable);

    @Query("SELECT COUNT(r) FROM Room r WHERE r.bloc.id = :blocId")
    Long countByBlocId(@Param("blocId") Long blocId);

    @Query("SELECT COUNT(r) FROM Room r WHERE r.floor.id = :floorId")
    Long countByFloorId(@Param("floorId") Long floorId);

    @Query("SELECT COUNT(r) FROM Room r WHERE r.structure.id = :structureId")
    Long countByStructureId(@Param("structureId") Long structureId);

    @Query("SELECT COUNT(r) FROM Room r WHERE r.structure IS NULL")
    Long countRoomsWithoutStructure();

    @Query("SELECT COUNT(r) FROM Room r")
    Long countAllRooms();

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r WHERE r.bloc.id = :blocId")
    boolean hasRoomsForBloc(@Param("blocId") Long blocId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r WHERE r.floor.id = :floorId")
    boolean hasRoomsForFloor(@Param("floorId") Long floorId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Room r WHERE r.structure.id = :structureId")
    boolean hasRoomsForStructure(@Param("structureId") Long structureId);

    @Query("SELECT r FROM Room r " +
           "LEFT JOIN FETCH r.bloc " +
           "LEFT JOIN FETCH r.floor " +
           "LEFT JOIN FETCH r.structure " +
           "WHERE (:blocId IS NULL OR r.bloc.id = :blocId) " +
           "AND (:floorId IS NULL OR r.floor.id = :floorId) " +
           "AND (:structureId IS NULL OR r.structure.id = :structureId)")
    Page<Room> findByCriteriaWithRelationships(@Param("blocId") Long blocId,
                                             @Param("floorId") Long floorId,
                                             @Param("structureId") Long structureId,
                                             Pageable pageable);
}
