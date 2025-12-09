/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfFloorRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.ShelfFloor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelfFloorRepository extends JpaRepository<ShelfFloor, Long> {

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.code = :code")
    Optional<ShelfFloor> findByCode(@Param("code") String code);

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.designationFr = :designationFr")
    Optional<ShelfFloor> findByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(sf) > 0 THEN true ELSE false END FROM ShelfFloor sf WHERE sf.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(sf) > 0 THEN true ELSE false END FROM ShelfFloor sf WHERE sf.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(sf) > 0 THEN true ELSE false END FROM ShelfFloor sf WHERE sf.code = :code AND sf.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(sf) > 0 THEN true ELSE false END FROM ShelfFloor sf WHERE sf.designationFr = :designationFr AND sf.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    Page<ShelfFloor> findAll(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.designationAr LIKE %:designationAr%")
    Page<ShelfFloor> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.designationEn LIKE %:designationEn%")
    Page<ShelfFloor> findByDesignationEnContaining(@Param("designationEn") String designationEn, Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.designationFr LIKE %:designationFr%")
    Page<ShelfFloor> findByDesignationFrContaining(@Param("designationFr") String designationFr, Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.code LIKE %:code%")
    Page<ShelfFloor> findByCodeContaining(@Param("code") String code, Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE " +
           "sf.code LIKE %:search% OR " +
           "sf.designationAr LIKE %:search% OR " +
           "sf.designationEn LIKE %:search% OR " +
           "sf.designationFr LIKE %:search%")
    Page<ShelfFloor> searchByAnyField(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(sf) FROM ShelfFloor sf")
    Long countAllShelfFloors();

    @Query("SELECT sf FROM ShelfFloor sf ORDER BY sf.code ASC")
    Page<ShelfFloor> findAllOrderByCode(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf ORDER BY sf.designationFr ASC")
    Page<ShelfFloor> findAllOrderByDesignationFr(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.designationAr IS NOT NULL AND sf.designationAr != ''")
    Page<ShelfFloor> findWithArabicDesignation(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE sf.designationEn IS NOT NULL AND sf.designationEn != ''")
    Page<ShelfFloor> findWithEnglishDesignation(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE " +
           "(sf.designationAr IS NOT NULL AND sf.designationAr != '' AND sf.designationEn IS NOT NULL AND sf.designationEn != '') OR " +
           "(sf.designationAr IS NOT NULL AND sf.designationAr != '' AND sf.designationFr IS NOT NULL AND sf.designationFr != '') OR " +
           "(sf.designationEn IS NOT NULL AND sf.designationEn != '' AND sf.designationFr IS NOT NULL AND sf.designationFr != '')")
    Page<ShelfFloor> findMultilingualShelfFloors(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE " +
           "sf.designationAr LIKE %:designation% OR " +
           "sf.designationEn LIKE %:designation% OR " +
           "sf.designationFr LIKE %:designation%")
    Page<ShelfFloor> findByDesignationPattern(@Param("designation") String designation, Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE " +
           "LOWER(sf.code) LIKE '%top%' OR LOWER(sf.code) LIKE '%5%' OR " +
           "LOWER(sf.designationEn) LIKE '%top%' OR LOWER(sf.designationEn) LIKE '%upper%' OR " +
           "LOWER(sf.designationFr) LIKE '%haut%' OR " +
           "LOWER(sf.designationAr) LIKE '%علوي%'")
    Page<ShelfFloor> findTopShelfFloors(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE " +
           "LOWER(sf.code) LIKE '%bottom%' OR LOWER(sf.code) LIKE '%1%' OR LOWER(sf.code) LIKE '%2%' OR " +
           "LOWER(sf.designationEn) LIKE '%bottom%' OR LOWER(sf.designationEn) LIKE '%lower%' OR " +
           "LOWER(sf.designationFr) LIKE '%bas%' OR " +
           "LOWER(sf.designationAr) LIKE '%سفلي%'")
    Page<ShelfFloor> findBottomShelfFloors(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE " +
           "LOWER(sf.code) LIKE '%middle%' OR LOWER(sf.code) LIKE '%3%' OR LOWER(sf.code) LIKE '%4%' OR " +
           "LOWER(sf.designationEn) LIKE '%middle%' OR LOWER(sf.designationEn) LIKE '%center%' OR " +
           "LOWER(sf.designationFr) LIKE '%milieu%' OR " +
           "LOWER(sf.designationAr) LIKE '%وسط%'")
    Page<ShelfFloor> findMiddleShelfFloors(Pageable pageable);

    @Query("SELECT sf FROM ShelfFloor sf WHERE " +
           "LOWER(sf.code) LIKE '%eye%' OR " +
           "LOWER(sf.designationEn) LIKE '%eye%' OR LOWER(sf.designationEn) LIKE '%sight%' OR " +
           "LOWER(sf.designationFr) LIKE '%niveau%' OR " +
           "LOWER(sf.designationAr) LIKE '%نظر%'")
    Page<ShelfFloor> findEyeLevelShelfFloors(Pageable pageable);

    //@Query("SELECT sf FROM ShelfFloor sf WHERE sf.code REGEXP :levelPattern")
    //Page<ShelfFloor> findByLevelNumber(@Param("levelPattern") String levelPattern, Pageable pageable);
}
