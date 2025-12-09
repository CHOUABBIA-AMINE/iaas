/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailNatureRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.repository;

import dz.mdn.iaas.common.communication.model.MailNature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailNatureRepository extends JpaRepository<MailNature, Long> {

    @Query("SELECT mn FROM MailNature mn WHERE mn.designationFr = :designationFr")
    Optional<MailNature> findByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(mn) > 0 THEN true ELSE false END FROM MailNature mn WHERE mn.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(mn) > 0 THEN true ELSE false END FROM MailNature mn WHERE mn.designationFr = :designationFr AND mn.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    Page<MailNature> findAll(Pageable pageable);

    @Query("SELECT mn FROM MailNature mn WHERE mn.designationAr LIKE %:designationAr%")
    Page<MailNature> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT mn FROM MailNature mn WHERE mn.designationEn LIKE %:designationEn%")
    Page<MailNature> findByDesignationEnContaining(@Param("designationEn") String designationEn, Pageable pageable);

    @Query("SELECT mn FROM MailNature mn WHERE mn.designationFr LIKE %:designationFr%")
    Page<MailNature> findByDesignationFrContaining(@Param("designationFr") String designationFr, Pageable pageable);

    @Query("SELECT mn FROM MailNature mn WHERE " +
           "mn.designationAr LIKE %:search% OR " +
           "mn.designationEn LIKE %:search% OR " +
           "mn.designationFr LIKE %:search%")
    Page<MailNature> searchByAnyDesignation(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(mn) FROM MailNature mn")
    Long countAllMailNatures();

    @Query("SELECT mn FROM MailNature mn ORDER BY mn.designationFr ASC")
    Page<MailNature> findAllOrderByDesignationFr(Pageable pageable);

    @Query("SELECT mn FROM MailNature mn WHERE mn.designationAr IS NOT NULL AND mn.designationAr != ''")
    Page<MailNature> findWithArabicDesignation(Pageable pageable);

    @Query("SELECT mn FROM MailNature mn WHERE mn.designationEn IS NOT NULL AND mn.designationEn != ''")
    Page<MailNature> findWithEnglishDesignation(Pageable pageable);

    @Query("SELECT mn FROM MailNature mn WHERE " +
           "(mn.designationAr IS NOT NULL AND mn.designationAr != '' AND mn.designationEn IS NOT NULL AND mn.designationEn != '') OR " +
           "(mn.designationAr IS NOT NULL AND mn.designationAr != '' AND mn.designationFr IS NOT NULL AND mn.designationFr != '') OR " +
           "(mn.designationEn IS NOT NULL AND mn.designationEn != '' AND mn.designationFr IS NOT NULL AND mn.designationFr != '')")
    Page<MailNature> findMultilingualMailNatures(Pageable pageable);
}
