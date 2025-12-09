/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FolderRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Folder;
import dz.mdn.iaas.common.environment.model.ArchiveBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    @Query("SELECT f FROM Folder f WHERE f.code = :code")
    Optional<Folder> findByCode(@Param("code") String code);

    @Query("SELECT f FROM Folder f WHERE f.designationFr = :designationFr")
    Optional<Folder> findByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Folder f WHERE f.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Folder f WHERE f.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Folder f WHERE f.code = :code AND f.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Folder f WHERE f.designationFr = :designationFr AND f.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    @Query("SELECT f FROM Folder f LEFT JOIN FETCH f.archiveBox ab " +
           "LEFT JOIN FETCH ab.shelf s LEFT JOIN FETCH s.room r " +
           "LEFT JOIN FETCH r.bloc LEFT JOIN FETCH r.floor LEFT JOIN FETCH r.structure " +
           "LEFT JOIN FETCH ab.shelfFloor")
    Page<Folder> findAllWithRelationships(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox = :archiveBox")
    Page<Folder> findByArchiveBox(@Param("archiveBox") ArchiveBox archiveBox, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox.id = :archiveBoxId")
    Page<Folder> findByArchiveBoxId(@Param("archiveBoxId") Long archiveBoxId, Pageable pageable);

    @Query("SELECT f FROM Folder f LEFT JOIN FETCH f.archiveBox ab " +
           "LEFT JOIN FETCH ab.shelf s LEFT JOIN FETCH s.room r " +
           "LEFT JOIN FETCH r.bloc LEFT JOIN FETCH r.floor LEFT JOIN FETCH r.structure " +
           "LEFT JOIN FETCH ab.shelfFloor " +
           "WHERE f.archiveBox.id = :archiveBoxId")
    Page<Folder> findByArchiveBoxIdWithRelationships(@Param("archiveBoxId") Long archiveBoxId, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE " +
           "f.code LIKE %:search% OR " +
           "f.designationAr LIKE %:search% OR " +
           "f.designationEn LIKE %:search% OR " +
           "f.designationFr LIKE %:search%")
    Page<Folder> searchByAnyField(@Param("search") String search, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.code LIKE %:code%")
    Page<Folder> findByCodeContaining(@Param("code") String code, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE " +
           "f.designationAr LIKE %:designation% OR " +
           "f.designationEn LIKE %:designation% OR " +
           "f.designationFr LIKE %:designation%")
    Page<Folder> findByDesignationPattern(@Param("designation") String designation, Pageable pageable);

    @Query("SELECT f FROM Folder f ORDER BY f.code ASC")
    Page<Folder> findAllOrderByCode(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox.shelf.id = :shelfId")
    Page<Folder> findByShelfId(@Param("shelfId") Long shelfId, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox.shelf.room.id = :roomId")
    Page<Folder> findByRoomId(@Param("roomId") Long roomId, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox.shelf.room.bloc.id = :blocId")
    Page<Folder> findByBlocId(@Param("blocId") Long blocId, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox.shelf.room.floor.id = :floorId")
    Page<Folder> findByFloorId(@Param("floorId") Long floorId, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox.shelf.room.structure.id = :structureId")
    Page<Folder> findByStructureId(@Param("structureId") Long structureId, Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE f.archiveBox.shelfFloor.id = :shelfFloorId")
    Page<Folder> findByShelfFloorId(@Param("shelfFloorId") Long shelfFloorId, Pageable pageable);

    @Query("SELECT COUNT(f) FROM Folder f WHERE f.archiveBox.id = :archiveBoxId")
    Long countByArchiveBoxId(@Param("archiveBoxId") Long archiveBoxId);

    @Query("SELECT COUNT(f) FROM Folder f")
    Long countAllFolders();

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Folder f WHERE f.archiveBox.id = :archiveBoxId")
    boolean hasFoldersForArchiveBox(@Param("archiveBoxId") Long archiveBoxId);

    @Query("SELECT f FROM Folder f WHERE " +
           "(f.designationAr IS NOT NULL AND f.designationAr != '' AND f.designationEn IS NOT NULL AND f.designationEn != '') OR " +
           "(f.designationAr IS NOT NULL AND f.designationAr != '' AND f.designationFr IS NOT NULL AND f.designationFr != '') OR " +
           "(f.designationEn IS NOT NULL AND f.designationEn != '' AND f.designationFr IS NOT NULL AND f.designationFr != '')")
    Page<Folder> findMultilingualFolders(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE UPPER(f.code) LIKE '%URGENT%' OR UPPER(f.code) LIKE '%URG%'")
    Page<Folder> findUrgentFolders(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE UPPER(f.code) LIKE '%CONF%' OR UPPER(f.code) LIKE '%CONFIDENTIAL%' OR UPPER(f.code) LIKE '%SECRET%'")
    Page<Folder> findConfidentialFolders(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE UPPER(f.code) LIKE '%TEMP%' OR UPPER(f.code) LIKE '%TEMPORARY%' OR UPPER(f.code) LIKE '%TMP%'")
    Page<Folder> findTemporaryFolders(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE UPPER(f.code) LIKE '%ARCH%' OR UPPER(f.code) LIKE '%ARCHIVE%'")
    Page<Folder> findArchiveFolders(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE UPPER(f.code) LIKE '%DOC%' OR UPPER(f.code) LIKE '%DOCUMENT%'")
    Page<Folder> findDocumentFolders(Pageable pageable);

    @Query("SELECT f FROM Folder f WHERE UPPER(f.code) LIKE '%REF%' OR UPPER(f.code) LIKE '%REFERENCE%'")
    Page<Folder> findReferenceFolders(Pageable pageable);

    @Query("SELECT COUNT(f) FROM Folder f WHERE f.archiveBox.shelf.id = :shelfId")
    Long countByShelfId(@Param("shelfId") Long shelfId);

    @Query("SELECT COUNT(f) FROM Folder f WHERE f.archiveBox.shelf.room.id = :roomId")
    Long countByRoomId(@Param("roomId") Long roomId);

    @Query("SELECT COUNT(f) FROM Folder f WHERE f.archiveBox.shelf.room.bloc.id = :blocId")
    Long countByBlocId(@Param("blocId") Long blocId);

    @Query("SELECT COUNT(f) FROM Folder f WHERE f.archiveBox.shelf.room.floor.id = :floorId")
    Long countByFloorId(@Param("floorId") Long floorId);

    @Query("SELECT f FROM Folder f WHERE " +
           "f.code LIKE %:search% OR " +
           "f.designationFr LIKE %:search% OR " +
           "f.archiveBox.code LIKE %:search%")
    Page<Folder> searchByCodeOrArchiveBoxInfo(@Param("search") String search, Pageable pageable);
}
