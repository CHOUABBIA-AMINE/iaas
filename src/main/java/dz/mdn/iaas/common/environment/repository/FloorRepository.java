/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FloorRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Floor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Query("SELECT f FROM Floor f WHERE f.code = :code")
    Optional<Floor> findByCode(@Param("code") String code);

    @Query("SELECT f FROM Floor f WHERE f.designationFr = :designationFr")
    Optional<Floor> findByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Floor f WHERE f.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Floor f WHERE f.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Floor f WHERE f.code = :code AND f.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Floor f WHERE f.designationFr = :designationFr AND f.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    Page<Floor> findAll(Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE f.designationAr LIKE %:designationAr%")
    Page<Floor> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE f.designationEn LIKE %:designationEn%")
    Page<Floor> findByDesignationEnContaining(@Param("designationEn") String designationEn, Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE f.designationFr LIKE %:designationFr%")
    Page<Floor> findByDesignationFrContaining(@Param("designationFr") String designationFr, Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE f.code LIKE %:code%")
    Page<Floor> findByCodeContaining(@Param("code") String code, Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE " +
           "f.code LIKE %:search% OR " +
           "f.designationAr LIKE %:search% OR " +
           "f.designationEn LIKE %:search% OR " +
           "f.designationFr LIKE %:search%")
    Page<Floor> searchByAnyField(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(f) FROM Floor f")
    Long countAllFloors();

    @Query("SELECT f FROM Floor f ORDER BY f.code ASC")
    Page<Floor> findAllOrderByCode(Pageable pageable);

    @Query("SELECT f FROM Floor f ORDER BY f.designationFr ASC")
    Page<Floor> findAllOrderByDesignationFr(Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE f.designationAr IS NOT NULL AND f.designationAr != ''")
    Page<Floor> findWithArabicDesignation(Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE f.designationEn IS NOT NULL AND f.designationEn != ''")
    Page<Floor> findWithEnglishDesignation(Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE " +
           "(f.designationAr IS NOT NULL AND f.designationAr != '' AND f.designationEn IS NOT NULL AND f.designationEn != '') OR " +
           "(f.designationAr IS NOT NULL AND f.designationAr != '' AND f.designationFr IS NOT NULL AND f.designationFr != '') OR " +
           "(f.designationEn IS NOT NULL AND f.designationEn != '' AND f.designationFr IS NOT NULL AND f.designationFr != '')")
    Page<Floor> findMultilingualFloors(Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE " +
           "f.designationAr LIKE %:designation% OR " +
           "f.designationEn LIKE %:designation% OR " +
           "f.designationFr LIKE %:designation%")
    Page<Floor> findByDesignationPattern(@Param("designation") String designation, Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE " +
           "LOWER(f.code) LIKE '%ground%' OR LOWER(f.code) LIKE '%gf%' OR LOWER(f.code) LIKE '%0%' OR " +
           "LOWER(f.designationEn) LIKE '%ground%' OR " +
           "LOWER(f.designationFr) LIKE '%rez%' OR " +
           "LOWER(f.designationAr) LIKE '%أرضي%'")
    Page<Floor> findGroundFloors(Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE " +
           "LOWER(f.code) LIKE '%basement%' OR LOWER(f.code) LIKE '%b%' OR " +
           "LOWER(f.designationEn) LIKE '%basement%' OR " +
           "LOWER(f.designationFr) LIKE '%sous-sol%' OR " +
           "LOWER(f.designationAr) LIKE '%قبو%'")
    Page<Floor> findBasementFloors(Pageable pageable);

    @Query("SELECT f FROM Floor f WHERE " +
           //"f.code REGEXP '[1-9]' OR " +
           "LOWER(f.designationEn) LIKE '%first%' OR LOWER(f.designationEn) LIKE '%second%' OR LOWER(f.designationEn) LIKE '%upper%' OR " +
           "LOWER(f.designationFr) LIKE '%premier%' OR LOWER(f.designationFr) LIKE '%étage%' OR " +
           "LOWER(f.designationAr) LIKE '%أول%' OR LOWER(f.designationAr) LIKE '%ثاني%'")
    Page<Floor> findUpperFloors(Pageable pageable);
}
