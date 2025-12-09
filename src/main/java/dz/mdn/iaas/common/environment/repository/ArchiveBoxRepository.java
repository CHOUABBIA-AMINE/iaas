/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBoxRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.ArchiveBox;
import dz.mdn.iaas.common.environment.model.Shelf;
import dz.mdn.iaas.common.environment.model.ShelfFloor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchiveBoxRepository extends JpaRepository<ArchiveBox, Long> {

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.code = :code")
    Optional<ArchiveBox> findByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(ab) > 0 THEN true ELSE false END FROM ArchiveBox ab WHERE ab.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(ab) > 0 THEN true ELSE false END FROM ArchiveBox ab WHERE ab.code = :code AND ab.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT ab FROM ArchiveBox ab " +
           "LEFT JOIN FETCH ab.shelf s " +
           "LEFT JOIN FETCH s.room r " +
           "LEFT JOIN FETCH r.bloc " +
           "LEFT JOIN FETCH r.floor " +
           "LEFT JOIN FETCH r.structure " +
           "LEFT JOIN FETCH ab.shelfFloor")
    Page<ArchiveBox> findAllWithRelationships(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelf = :shelf")
    Page<ArchiveBox> findByShelf(@Param("shelf") Shelf shelf, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelf.id = :shelfId")
    Page<ArchiveBox> findByShelfId(@Param("shelfId") Long shelfId, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelfFloor = :shelfFloor")
    Page<ArchiveBox> findByShelfFloor(@Param("shelfFloor") ShelfFloor shelfFloor, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelfFloor.id = :shelfFloorId")
    Page<ArchiveBox> findByShelfFloorId(@Param("shelfFloorId") Long shelfFloorId, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelf.id = :shelfId AND ab.shelfFloor.id = :shelfFloorId")
    Page<ArchiveBox> findByShelfIdAndShelfFloorId(@Param("shelfId") Long shelfId, 
                                                  @Param("shelfFloorId") Long shelfFloorId, 
                                                  Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab " +
           "LEFT JOIN FETCH ab.shelf s " +
           "LEFT JOIN FETCH s.room r " +
           "LEFT JOIN FETCH r.bloc " +
           "LEFT JOIN FETCH r.floor " +
           "LEFT JOIN FETCH r.structure " +
           "LEFT JOIN FETCH ab.shelfFloor " +
           "WHERE ab.shelf.id = :shelfId")
    Page<ArchiveBox> findByShelfIdWithRelationships(@Param("shelfId") Long shelfId, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.code LIKE %:code%")
    Page<ArchiveBox> findByCodeContaining(@Param("code") String code, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab ORDER BY ab.code ASC")
    Page<ArchiveBox> findAllOrderByCode(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelf.room.id = :roomId")
    Page<ArchiveBox> findByRoomId(@Param("roomId") Long roomId, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelf.room.bloc.id = :blocId")
    Page<ArchiveBox> findByBlocId(@Param("blocId") Long blocId, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelf.room.floor.id = :floorId")
    Page<ArchiveBox> findByFloorId(@Param("floorId") Long floorId, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE ab.shelf.room.structure.id = :structureId")
    Page<ArchiveBox> findByStructureId(@Param("structureId") Long structureId, Pageable pageable);

    @Query("SELECT COUNT(ab) FROM ArchiveBox ab WHERE ab.shelf.id = :shelfId")
    Long countByShelfId(@Param("shelfId") Long shelfId);

    @Query("SELECT COUNT(ab) FROM ArchiveBox ab WHERE ab.shelfFloor.id = :shelfFloorId")
    Long countByShelfFloorId(@Param("shelfFloorId") Long shelfFloorId);

    @Query("SELECT COUNT(ab) FROM ArchiveBox ab")
    Long countAllArchiveBoxes();

    @Query("SELECT CASE WHEN COUNT(ab) > 0 THEN true ELSE false END FROM ArchiveBox ab WHERE ab.shelf.id = :shelfId")
    boolean hasArchiveBoxesForShelf(@Param("shelfId") Long shelfId);

    @Query("SELECT CASE WHEN COUNT(ab) > 0 THEN true ELSE false END FROM ArchiveBox ab WHERE ab.shelfFloor.id = :shelfFloorId")
    boolean hasArchiveBoxesForShelfFloor(@Param("shelfFloorId") Long shelfFloorId);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE %:pattern%")
    Page<ArchiveBox> findByCodeTypePattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%DOC%' OR UPPER(ab.code) LIKE '%DOCUMENT%'")
    Page<ArchiveBox> findDocumentArchiveBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%ARCH%' OR UPPER(ab.code) LIKE '%ARCHIVE%'")
    Page<ArchiveBox> findArchiveStorageBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%CONF%' OR UPPER(ab.code) LIKE '%CONFIDENTIAL%' OR UPPER(ab.code) LIKE '%SECRET%'")
    Page<ArchiveBox> findConfidentialArchiveBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%TEMP%' OR UPPER(ab.code) LIKE '%TEMPORARY%' OR UPPER(ab.code) LIKE '%TMP%'")
    Page<ArchiveBox> findTemporaryArchiveBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%URGENT%' OR UPPER(ab.code) LIKE '%URG%'")
    Page<ArchiveBox> findUrgentArchiveBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%REF%' OR UPPER(ab.code) LIKE '%REFERENCE%'")
    Page<ArchiveBox> findReferenceArchiveBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%LEGAL%' OR UPPER(ab.code) LIKE '%LAW%'")
    Page<ArchiveBox> findLegalArchiveBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE UPPER(ab.code) LIKE '%FINANCIAL%' OR UPPER(ab.code) LIKE '%FINANCE%' OR UPPER(ab.code) LIKE '%FIN%'")
    Page<ArchiveBox> findFinancialArchiveBoxes(Pageable pageable);

    @Query("SELECT COUNT(ab) FROM ArchiveBox ab WHERE ab.shelf.room.id = :roomId")
    Long countByRoomId(@Param("roomId") Long roomId);

    @Query("SELECT COUNT(ab) FROM ArchiveBox ab WHERE ab.shelf.room.bloc.id = :blocId")
    Long countByBlocId(@Param("blocId") Long blocId);

    @Query("SELECT COUNT(ab) FROM ArchiveBox ab WHERE ab.shelf.room.floor.id = :floorId")
    Long countByFloorId(@Param("floorId") Long floorId);

    @Query("SELECT ab FROM ArchiveBox ab WHERE " +
           "ab.code LIKE %:search% OR " +
           "ab.shelf.code LIKE %:search% OR " +
           "ab.shelfFloor.code LIKE %:search% OR " +
           "ab.shelfFloor.designationFr LIKE %:search%")
    Page<ArchiveBox> searchByCodeOrShelfInfo(@Param("search") String search, Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE " +
           "LOWER(ab.shelfFloor.code) LIKE '%eye%' OR " +
           "LOWER(ab.shelfFloor.designationEn) LIKE '%eye%' OR " +
           "LOWER(ab.shelfFloor.designationFr) LIKE '%niveau%'")
    Page<ArchiveBox> findHighAccessibilityArchiveBoxes(Pageable pageable);

    @Query("SELECT ab FROM ArchiveBox ab WHERE " +
           "LOWER(ab.shelfFloor.code) LIKE '%top%' OR LOWER(ab.shelfFloor.code) LIKE '%bottom%' OR " +
           "LOWER(ab.shelfFloor.designationEn) LIKE '%top%' OR LOWER(ab.shelfFloor.designationEn) LIKE '%bottom%' OR " +
           "LOWER(ab.shelfFloor.designationFr) LIKE '%haut%' OR LOWER(ab.shelfFloor.designationFr) LIKE '%bas%'")
    Page<ArchiveBox> findLowAccessibilityArchiveBoxes(Pageable pageable);

    @Query("SELECT DISTINCT sf FROM ShelfFloor sf WHERE sf.id NOT IN " +
           "(SELECT ab.shelfFloor.id FROM ArchiveBox ab WHERE ab.shelf.id = :shelfId)")
    Page<ShelfFloor> getAvailableShelfFloorsForShelf(@Param("shelfId") Long shelfId, Pageable pageable);
}
