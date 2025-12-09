/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CountryRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE c.designationFr = :designationFr")
    Optional<Country> findByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Country c WHERE c.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Country c WHERE c.designationFr = :designationFr AND c.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    Page<Country> findAll(Pageable pageable);

    @Query("SELECT c FROM Country c WHERE c.designationAr LIKE %:designationAr%")
    Page<Country> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT c FROM Country c WHERE c.designationEn LIKE %:designationEn%")
    Page<Country> findByDesignationEnContaining(@Param("designationEn") String designationEn, Pageable pageable);

    @Query("SELECT c FROM Country c WHERE c.designationFr LIKE %:designationFr%")
    Page<Country> findByDesignationFrContaining(@Param("designationFr") String designationFr, Pageable pageable);

    @Query("SELECT c FROM Country c WHERE " +
           "c.designationAr LIKE %:search% OR " +
           "c.designationEn LIKE %:search% OR " +
           "c.designationFr LIKE %:search%")
    Page<Country> searchByAnyDesignation(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Country c")
    long countAllCountries();
}
