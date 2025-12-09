/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StateRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT s FROM State s WHERE s.code = :code")
    Optional<State> findByCode(@Param("code") String code);

    @Query("SELECT s FROM State s WHERE s.designationAr = :designationAr")
    Optional<State> findByDesignationAr(@Param("designationAr") String designationAr);

    @Query("SELECT s FROM State s WHERE s.designationLt = :designationLt")
    Optional<State> findByDesignationLt(@Param("designationLt") String designationLt);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM State s WHERE s.code = :code")
    boolean existsByCode(@Param("code") int code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM State s WHERE s.designationAr = :designationAr")
    boolean existsByDesignationAr(@Param("designationAr") String designationAr);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM State s WHERE s.designationLt = :designationLt")
    boolean existsByDesignationLt(@Param("designationLt") String designationLt);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM State s WHERE s.code = :code AND s.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") int code, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM State s WHERE s.designationAr = :designationAr AND s.id != :id")
    boolean existsByDesignationArAndIdNot(@Param("designationAr") String designationAr, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM State s WHERE s.designationLt = :designationLt AND s.id != :id")
    boolean existsByDesignationLtAndIdNot(@Param("designationLt") String designationLt, @Param("id") Long id);

    Page<State> findAll(Pageable pageable);

    @Query("SELECT s FROM State s WHERE s.code =:code")
    Page<State> findByCode(@Param("code") int code, Pageable pageable);

    @Query("SELECT s FROM State s WHERE s.designationAr LIKE %:designationAr%")
    Page<State> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT s FROM State s WHERE s.designationLt LIKE %:designationLt%")
    Page<State> findByDesignationLtContaining(@Param("designationLt") String designationLt, Pageable pageable);

    @Query("SELECT s FROM State s WHERE " +
           "s.designationAr LIKE %:search% OR " +
           "s.designationLt LIKE %:search%")
    Page<State> searchByAnyField(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(s) FROM State s")
    Long countAllStates();

    @Query("SELECT s FROM State s ORDER BY s.code ASC")
    Page<State> findAllOrderByCode(Pageable pageable);

    @Query("SELECT s FROM State s ORDER BY s.designationLt ASC")
    Page<State> findAllOrderByDesignationLt(Pageable pageable);
}
