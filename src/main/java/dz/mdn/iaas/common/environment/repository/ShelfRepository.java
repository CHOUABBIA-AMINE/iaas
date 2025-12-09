/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Shelf;
import dz.mdn.iaas.common.environment.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    @Query("SELECT s FROM Shelf s WHERE s.code = :code")
    Optional<Shelf> findByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Shelf s WHERE s.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Shelf s WHERE s.code = :code AND s.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT s FROM Shelf s LEFT JOIN FETCH s.room r " +
           "LEFT JOIN FETCH r.bloc LEFT JOIN FETCH r.floor LEFT JOIN FETCH r.structure")
    Page<Shelf> findAllWithRelationships(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.room = :room")
    Page<Shelf> findByRoom(@Param("room") Room room, Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.room.id = :roomId")
    Page<Shelf> findByRoomId(@Param("roomId") Long roomId, Pageable pageable);

    @Query("SELECT s FROM Shelf s LEFT JOIN FETCH s.room r " +
           "LEFT JOIN FETCH r.bloc LEFT JOIN FETCH r.floor LEFT JOIN FETCH r.structure " +
           "WHERE s.room.id = :roomId")
    Page<Shelf> findByRoomIdWithRelationships(@Param("roomId") Long roomId, Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.code LIKE %:code%")
    Page<Shelf> findByCodeContaining(@Param("code") String code, Pageable pageable);

    @Query("SELECT s FROM Shelf s ORDER BY s.code ASC")
    Page<Shelf> findAllOrderByCode(Pageable pageable);

    @Query("SELECT COUNT(s) FROM Shelf s WHERE s.room.id = :roomId")
    Long countByRoomId(@Param("roomId") Long roomId);

    @Query("SELECT COUNT(s) FROM Shelf s")
    Long countAllShelves();

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Shelf s WHERE s.room.id = :roomId")
    boolean hasShelvesForRoom(@Param("roomId") Long roomId);

    @Query("SELECT s FROM Shelf s WHERE s.room.bloc.id = :blocId")
    Page<Shelf> findByBlocId(@Param("blocId") Long blocId, Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.room.floor.id = :floorId")
    Page<Shelf> findByFloorId(@Param("floorId") Long floorId, Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.room.structure.id = :structureId")
    Page<Shelf> findByStructureId(@Param("structureId") Long structureId, Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.room.structure IS NULL")
    Page<Shelf> findShelvesWithoutStructure(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.archiveBoxs IS EMPTY")
    Page<Shelf> findEmptyShelves(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.archiveBoxs IS NOT EMPTY")
    Page<Shelf> findShelvesWithArchiveBoxes(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE SIZE(s.archiveBoxs) BETWEEN :minCount AND :maxCount")
    Page<Shelf> findShelvesByArchiveBoxCountRange(@Param("minCount") int minCount, 
                                                  @Param("maxCount") int maxCount, 
                                                  Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE UPPER(s.code) LIKE %:pattern%")
    Page<Shelf> findByCodeTypePattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE UPPER(s.code) LIKE '%DOC%' OR UPPER(s.code) LIKE '%DOCUMENT%'")
    Page<Shelf> findDocumentShelves(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE UPPER(s.code) LIKE '%ARCH%' OR UPPER(s.code) LIKE '%ARCHIVE%'")
    Page<Shelf> findArchiveShelves(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE UPPER(s.code) LIKE '%REF%' OR UPPER(s.code) LIKE '%REFERENCE%'")
    Page<Shelf> findReferenceShelves(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE UPPER(s.code) LIKE '%TEMP%' OR UPPER(s.code) LIKE '%TEMPORARY%'")
    Page<Shelf> findTemporaryShelves(Pageable pageable);

    @Query("SELECT COUNT(s) FROM Shelf s WHERE s.room.bloc.id = :blocId")
    Long countByBlocId(@Param("blocId") Long blocId);

    @Query("SELECT COUNT(s) FROM Shelf s WHERE s.room.floor.id = :floorId")
    Long countByFloorId(@Param("floorId") Long floorId);

    @Query("SELECT COUNT(s) FROM Shelf s WHERE s.room.structure.id = :structureId")
    Long countByStructureId(@Param("structureId") Long structureId);

    @Query("SELECT COUNT(s) FROM Shelf s WHERE s.archiveBoxs IS EMPTY")
    Long countEmptyShelves();

    @Query("SELECT COUNT(s) FROM Shelf s WHERE s.archiveBoxs IS NOT EMPTY")
    Long countShelvesWithArchiveBoxes();

    @Query("SELECT s FROM Shelf s LEFT JOIN FETCH s.room r " +
           "LEFT JOIN FETCH s.archiveBoxs " +
           "LEFT JOIN FETCH r.bloc LEFT JOIN FETCH r.floor LEFT JOIN FETCH r.structure")
    Page<Shelf> findAllWithFullDetails(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE " +
           "s.code LIKE %:search% OR " +
           "s.room.code LIKE %:search%")
    Page<Shelf> searchByCodeOrRoomInfo(@Param("search") String search, Pageable pageable);
}
