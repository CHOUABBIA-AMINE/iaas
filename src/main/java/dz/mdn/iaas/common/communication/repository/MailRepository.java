/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.common.communication.model.Mail;
import dz.mdn.iaas.common.communication.model.MailNature;
import dz.mdn.iaas.common.communication.model.MailType;
import dz.mdn.iaas.system.utility.model.File;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

    @Query("SELECT m FROM Mail m WHERE m.reference = :reference")
    Optional<Mail> findByReference(@Param("reference") String reference);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mail m WHERE m.reference = :reference")
    boolean existsByReference(@Param("reference") String reference);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mail m WHERE m.reference = :reference AND m.id != :id")
    boolean existsByReferenceAndIdNot(@Param("reference") String reference, @Param("id") Long id);

    @Query("SELECT m FROM Mail m WHERE m.recordNumber = :recordNumber")
    Optional<Mail> findByRecordNumber(@Param("recordNumber") String recordNumber);

    @Query("SELECT m FROM Mail m WHERE m.subject LIKE %:subject%")
    Page<Mail> findBySubjectContaining(@Param("subject") String subject, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.mailNature = :mailNature")
    Page<Mail> findByMailNature(@Param("mailNature") MailNature mailNature, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.mailNature.id = :mailNatureId")
    Page<Mail> findByMailNatureId(@Param("mailNatureId") Long mailNatureId, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.mailType = :mailType")
    Page<Mail> findByMailType(@Param("mailType") MailType mailType, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.mailType.id = :mailTypeId")
    Page<Mail> findByMailTypeId(@Param("mailTypeId") Long mailTypeId, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.structure = :structure")
    Page<Mail> findByStructure(@Param("structure") Structure structure, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.structure.id = :structureId")
    Page<Mail> findByStructureId(@Param("structureId") Long structureId, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.file = :file")
    Optional<Mail> findByFile(@Param("file") File file);

    @Query("SELECT m FROM Mail m WHERE m.file.id = :fileId")
    Optional<Mail> findByFileId(@Param("fileId") Long fileId);

    @Query("SELECT m FROM Mail m WHERE m.mailDate BETWEEN :startDate AND :endDate")
    Page<Mail> findByMailDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.recordDate BETWEEN :startDate AND :endDate")
    Page<Mail> findByRecordDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.recordNumber IS NOT NULL AND m.recordNumber != '' AND m.recordDate IS NOT NULL")
    Page<Mail> findRecordedMails(Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.recordNumber IS NULL OR m.recordNumber = '' OR m.recordDate IS NULL")
    Page<Mail> findUnrecordedMails(Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE " +
           "m.reference LIKE %:search% OR " +
           "m.subject LIKE %:search% OR " +
           "m.recordNumber LIKE %:search%")
    Page<Mail> searchByReferenceSubjectOrRecordNumber(@Param("search") String search, Pageable pageable);

    @Query("SELECT m FROM Mail m " +
           "LEFT JOIN FETCH m.mailNature " +
           "LEFT JOIN FETCH m.mailType " +
           "LEFT JOIN FETCH m.structure " +
           "LEFT JOIN FETCH m.file")
    Page<Mail> findAllWithRelationships(Pageable pageable);

    @Query("SELECT m FROM Mail m " +
           "LEFT JOIN FETCH m.mailNature " +
           "LEFT JOIN FETCH m.mailType " +
           "LEFT JOIN FETCH m.structure " +
           "LEFT JOIN FETCH m.file " +
           "WHERE (:mailNatureId IS NULL OR m.mailNature.id = :mailNatureId) " +
           "AND (:mailTypeId IS NULL OR m.mailType.id = :mailTypeId) " +
           "AND (:structureId IS NULL OR m.structure.id = :structureId)")
    Page<Mail> findByCriteriaWithRelationships(@Param("mailNatureId") Long mailNatureId,
                                             @Param("mailTypeId") Long mailTypeId,
                                             @Param("structureId") Long structureId,
                                             Pageable pageable);

    @Query("SELECT m FROM Mail m JOIN m.referencedMails rm WHERE rm.id = :referencedMailId")
    Page<Mail> findMailsReferencingMail(@Param("referencedMailId") Long referencedMailId, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Mail m WHERE m.mailNature.id = :mailNatureId")
    Long countByMailNatureId(@Param("mailNatureId") Long mailNatureId);

    @Query("SELECT COUNT(m) FROM Mail m WHERE m.mailType.id = :mailTypeId")
    Long countByMailTypeId(@Param("mailTypeId") Long mailTypeId);

    @Query("SELECT COUNT(m) FROM Mail m WHERE m.structure.id = :structureId")
    Long countByStructureId(@Param("structureId") Long structureId);

    @Query("SELECT COUNT(m) FROM Mail m WHERE m.recordNumber IS NOT NULL AND m.recordNumber != '' AND m.recordDate IS NOT NULL")
    Long countRecordedMails();

    @Query("SELECT COUNT(m) FROM Mail m WHERE m.recordNumber IS NULL OR m.recordNumber = '' OR m.recordDate IS NULL")
    Long countUnrecordedMails();

    @Query("SELECT COUNT(m) FROM Mail m")
    Long countAllMails();

    @Query("SELECT m FROM Mail m WHERE m.mailDate >= :cutoffDate ORDER BY m.mailDate DESC")
    Page<Mail> findRecentMails(@Param("cutoffDate") Date cutoffDate, Pageable pageable);

    @Query("SELECT m FROM Mail m ORDER BY m.mailDate DESC NULLS LAST, m.id DESC")
    Page<Mail> findAllOrderByMailDateDesc(Pageable pageable);

    @Query("SELECT m FROM Mail m ORDER BY m.recordDate DESC NULLS LAST, m.id DESC")
    Page<Mail> findAllOrderByRecordDateDesc(Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mail m WHERE m.file.id = :fileId")
    boolean isFileUsedByMail(@Param("fileId") Long fileId);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mail m WHERE m.mailNature.id = :mailNatureId")
    boolean hasMailsForMailNature(@Param("mailNatureId") Long mailNatureId);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mail m WHERE m.mailType.id = :mailTypeId")
    boolean hasMailsForMailType(@Param("mailTypeId") Long mailTypeId);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mail m WHERE m.structure.id = :structureId")
    boolean hasMailsForStructure(@Param("structureId") Long structureId);
}
