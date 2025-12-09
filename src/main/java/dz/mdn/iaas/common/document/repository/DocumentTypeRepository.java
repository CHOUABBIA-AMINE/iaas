/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentTypeRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.repository;

import dz.mdn.iaas.common.document.model.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    @Query("SELECT dt FROM DocumentType dt WHERE dt.designationFr = :designationFr AND dt.scope = :scope")
    Optional<DocumentType> findByDesignationFrAndScope(@Param("designationFr") String designationFr, @Param("scope") Integer scope);

    @Query("SELECT CASE WHEN COUNT(dt) > 0 THEN true ELSE false END FROM DocumentType dt WHERE dt.designationFr = :designationFr AND dt.scope = :scope")
    boolean existsByDesignationFrAndScope(@Param("designationFr") String designationFr, @Param("scope") Integer scope);

    @Query("SELECT CASE WHEN COUNT(dt) > 0 THEN true ELSE false END FROM DocumentType dt WHERE dt.designationFr = :designationFr AND dt.scope = :scope AND dt.id != :id")
    boolean existsByDesignationFrAndScopeAndIdNot(@Param("designationFr") String designationFr, @Param("scope") Integer scope, @Param("id") Long id);

    @Query("SELECT dt FROM DocumentType dt WHERE dt.scope = :scope")
    Page<DocumentType> findByScope(@Param("scope") Integer scope, Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt WHERE dt.scope = :scope ORDER BY dt.designationFr, dt.designationEn, dt.designationAr")
    List<DocumentType> findByScopeOrderByDesignation(@Param("scope") Integer scope);

    @Query("SELECT dt FROM DocumentType dt WHERE dt.designationAr LIKE %:designationAr%")
    Page<DocumentType> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt WHERE dt.designationEn LIKE %:designationEn%")
    Page<DocumentType> findByDesignationEnContaining(@Param("designationEn") String designationEn, Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt WHERE dt.designationFr LIKE %:designationFr%")
    Page<DocumentType> findByDesignationFrContaining(@Param("designationFr") String designationFr, Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt WHERE " +
           "dt.designationAr LIKE %:search% OR " +
           "dt.designationEn LIKE %:search% OR " +
           "dt.designationFr LIKE %:search%")
    Page<DocumentType> searchByAnyDesignation(@Param("search") String search, Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt WHERE dt.scope = :scope AND (" +
           "dt.designationAr LIKE %:search% OR " +
           "dt.designationEn LIKE %:search% OR " +
           "dt.designationFr LIKE %:search%)")
    Page<DocumentType> searchByAnyDesignationAndScope(@Param("search") String search, @Param("scope") Integer scope, Pageable pageable);

    Page<DocumentType> findAll(Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt LEFT JOIN FETCH dt.documents")
    Page<DocumentType> findAllWithDocuments(Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt LEFT JOIN FETCH dt.documents WHERE dt.scope = :scope")
    Page<DocumentType> findByScopeWithDocuments(@Param("scope") Integer scope, Pageable pageable);

    @Query("SELECT COUNT(dt) FROM DocumentType dt WHERE dt.scope = :scope")
    Long countByScope(@Param("scope") Integer scope);

    @Query("SELECT COUNT(dt) FROM DocumentType dt")
    Long countAllDocumentTypes();

    @Query("SELECT DISTINCT dt.scope FROM DocumentType dt ORDER BY dt.scope")
    List<Integer> findDistinctScopes();

    @Query("SELECT dt FROM DocumentType dt WHERE SIZE(dt.documents) > 0")
    Page<DocumentType> findDocumentTypesWithDocuments(Pageable pageable);

    @Query("SELECT dt FROM DocumentType dt WHERE SIZE(dt.documents) = 0")
    Page<DocumentType> findDocumentTypesWithoutDocuments(Pageable pageable);

    @Query("SELECT dt.id, COUNT(d) FROM DocumentType dt LEFT JOIN dt.documents d GROUP BY dt.id")
    List<Object[]> countDocumentsPerType();

    @Query("SELECT dt FROM DocumentType dt ORDER BY " +
           "CASE WHEN dt.designationFr IS NOT NULL AND dt.designationFr != '' THEN dt.designationFr " +
           "     WHEN dt.designationEn IS NOT NULL AND dt.designationEn != '' THEN dt.designationEn " +
           "     ELSE dt.designationAr END")
    Page<DocumentType> findAllOrderByPrimaryDesignation(Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(dt) > 0 THEN true ELSE false END FROM DocumentType dt WHERE dt.scope = :scope")
    boolean isScopeInUse(@Param("scope") Integer scope);
}
