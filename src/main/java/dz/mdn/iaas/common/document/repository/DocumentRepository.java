/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.repository;

import dz.mdn.iaas.common.document.model.Document;
import dz.mdn.iaas.common.document.model.DocumentType;
import dz.mdn.iaas.system.utility.model.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE d.reference = :reference")
    Optional<Document> findByReference(@Param("reference") String reference);

    @Query("SELECT d FROM Document d WHERE d.reference LIKE %:reference%")
    Page<Document> findByReferenceContaining(@Param("reference") String reference, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.documentType = :documentType")
    Page<Document> findByDocumentType(@Param("documentType") DocumentType documentType, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.documentType.id = :documentTypeId")
    Page<Document> findByDocumentTypeId(@Param("documentTypeId") Long documentTypeId, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.documentType.id = :documentTypeId ORDER BY d.issueDate DESC, d.reference")
    List<Document> findByDocumentTypeIdOrderByIssueDateDesc(@Param("documentTypeId") Long documentTypeId);

    @Query("SELECT d FROM Document d WHERE d.file = :file")
    Optional<Document> findByFile(@Param("file") File file);

    @Query("SELECT d FROM Document d WHERE d.file.id = :fileId")
    Optional<Document> findByFileId(@Param("fileId") Long fileId);

    @Query("SELECT d FROM Document d WHERE d.issueDate BETWEEN :startDate AND :endDate")
    Page<Document> findByIssueDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.issueDate >= :date")
    Page<Document> findByIssueDateAfter(@Param("date") Date date, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.issueDate <= :date")
    Page<Document> findByIssueDateBefore(@Param("date") Date date, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.file IS NOT NULL")
    Page<Document> findDocumentsWithFiles(Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.file IS NULL")
    Page<Document> findDocumentsWithoutFiles(Pageable pageable);

    @Query("SELECT d FROM Document d WHERE " +
           "d.reference LIKE %:search% OR " +
           "d.documentType.designationAr LIKE %:search% OR " +
           "d.documentType.designationEn LIKE %:search% OR " +
           "d.documentType.designationFr LIKE %:search%")
    Page<Document> searchByReferenceOrDocumentType(@Param("search") String search, Pageable pageable);

    @Query("SELECT d FROM Document d LEFT JOIN FETCH d.documentType LEFT JOIN FETCH d.file")
    Page<Document> findAllWithRelationships(Pageable pageable);

    @Query("SELECT d FROM Document d LEFT JOIN FETCH d.documentType LEFT JOIN FETCH d.file WHERE d.documentType.id = :documentTypeId")
    Page<Document> findByDocumentTypeIdWithRelationships(@Param("documentTypeId") Long documentTypeId, Pageable pageable);

    @Query("SELECT COUNT(d) FROM Document d WHERE d.documentType.id = :documentTypeId")
    Long countByDocumentTypeId(@Param("documentTypeId") Long documentTypeId);

    @Query("SELECT COUNT(d) FROM Document d WHERE d.file IS NOT NULL")
    Long countDocumentsWithFiles();

    @Query("SELECT COUNT(d) FROM Document d WHERE d.file IS NULL")
    Long countDocumentsWithoutFiles();

    @Query("SELECT COUNT(d) FROM Document d")
    Long countAllDocuments();

    @Query("SELECT d FROM Document d WHERE d.documentType.scope = :scope")
    Page<Document> findByDocumentTypeScope(@Param("scope") Integer scope, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.documentType.id = :documentTypeId AND (" +
           "d.reference LIKE %:search%)")
    Page<Document> searchByReferenceAndDocumentTypeId(@Param("search") String search, @Param("documentTypeId") Long documentTypeId, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.issueDate >= :cutoffDate ORDER BY d.issueDate DESC")
    Page<Document> findRecentDocuments(@Param("cutoffDate") Date cutoffDate, Pageable pageable);

    @Query("SELECT d FROM Document d ORDER BY d.issueDate DESC NULLS LAST, d.id DESC")
    Page<Document> findAllOrderByIssueDateDesc(Pageable pageable);

    @Query("SELECT d FROM Document d ORDER BY d.reference NULLS LAST, d.id")
    Page<Document> findAllOrderByReference(Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Document d WHERE d.documentType.id = :documentTypeId")
    boolean hasDocumentsForDocumentType(@Param("documentTypeId") Long documentTypeId);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Document d WHERE d.file.id = :fileId")
    boolean isFileUsedByDocument(@Param("fileId") Long fileId);

    @Query("SELECT d.documentType.id, COUNT(d) FROM Document d GROUP BY d.documentType.id")
    List<Object[]> getDocumentCountPerType();

    @Query("SELECT d FROM Document d WHERE d.documentType IS NULL")
    Page<Document> findOrphanedDocuments(Pageable pageable);

    @Query("SELECT d FROM Document d WHERE " +
           "(:reference IS NULL OR d.reference LIKE %:reference%) AND " +
           "(:documentTypeId IS NULL OR d.documentType.id = :documentTypeId) AND " +
           "(:startDate IS NULL OR d.issueDate >= :startDate) AND " +
           "(:endDate IS NULL OR d.issueDate <= :endDate) AND " +
           "(:hasFile IS NULL OR (:hasFile = true AND d.file IS NOT NULL) OR (:hasFile = false AND d.file IS NULL))")
    Page<Document> findByCriteria(@Param("reference") String reference,
                                @Param("documentTypeId") Long documentTypeId,
                                @Param("startDate") Date startDate,
                                @Param("endDate") Date endDate,
                                @Param("hasFile") Boolean hasFile,
                                Pageable pageable);
}
