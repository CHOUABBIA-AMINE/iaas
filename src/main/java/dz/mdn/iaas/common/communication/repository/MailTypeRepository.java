/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Mail
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.repository;

import dz.mdn.iaas.common.communication.model.MailType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailTypeRepository extends JpaRepository<MailType, Long> {

    @Query("SELECT mt FROM MailType mt WHERE mt.designationFr = :designationFr")
    Optional<MailType> findByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(mt) > 0 THEN true ELSE false END FROM MailType mt WHERE mt.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(mt) > 0 THEN true ELSE false END FROM MailType mt WHERE mt.designationFr = :designationFr AND mt.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    Page<MailType> findAll(Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE mt.designationAr LIKE %:designationAr%")
    Page<MailType> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE mt.designationEn LIKE %:designationEn%")
    Page<MailType> findByDesignationEnContaining(@Param("designationEn") String designationEn, Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE mt.designationFr LIKE %:designationFr%")
    Page<MailType> findByDesignationFrContaining(@Param("designationFr") String designationFr, Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE " +
           "mt.designationAr LIKE %:search% OR " +
           "mt.designationEn LIKE %:search% OR " +
           "mt.designationFr LIKE %:search%")
    Page<MailType> searchByAnyDesignation(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(mt) FROM MailType mt")
    Long countAllMailTypes();

    @Query("SELECT mt FROM MailType mt ORDER BY mt.designationFr ASC")
    Page<MailType> findAllOrderByDesignationFr(Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE mt.designationAr IS NOT NULL AND mt.designationAr != ''")
    Page<MailType> findWithArabicDesignation(Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE mt.designationEn IS NOT NULL AND mt.designationEn != ''")
    Page<MailType> findWithEnglishDesignation(Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE " +
           "(mt.designationAr IS NOT NULL AND mt.designationAr != '' AND mt.designationEn IS NOT NULL AND mt.designationEn != '') OR " +
           "(mt.designationAr IS NOT NULL AND mt.designationAr != '' AND mt.designationFr IS NOT NULL AND mt.designationFr != '') OR " +
           "(mt.designationEn IS NOT NULL AND mt.designationEn != '' AND mt.designationFr IS NOT NULL AND mt.designationFr != '')")
    Page<MailType> findMultilingualMailTypes(Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE mt.designationFr LIKE %:pattern%")
    Page<MailType> findByFrenchDesignationPattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE " +
           "LOWER(mt.designationFr) LIKE '%entrant%' OR " +
           "LOWER(mt.designationEn) LIKE '%incoming%' OR " +
           "LOWER(mt.designationAr) LIKE '%وارد%'")
    Page<MailType> findIncomingMailTypes(Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE " +
           "LOWER(mt.designationFr) LIKE '%sortant%' OR " +
           "LOWER(mt.designationEn) LIKE '%outgoing%' OR " +
           "LOWER(mt.designationAr) LIKE '%صادر%'")
    Page<MailType> findOutgoingMailTypes(Pageable pageable);

    @Query("SELECT mt FROM MailType mt WHERE " +
           "LOWER(mt.designationFr) LIKE '%interne%' OR " +
           "LOWER(mt.designationEn) LIKE '%internal%' OR " +
           "LOWER(mt.designationAr) LIKE '%داخلي%'")
    Page<MailType> findInternalMailTypes(Pageable pageable);
}
