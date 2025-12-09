/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocalityRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Locality;
import dz.mdn.iaas.common.administration.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {

    @Query("SELECT l FROM Locality l WHERE l.code = :code")
    Optional<Locality> findByCode(@Param("code") int code);

    @Query("SELECT l FROM Locality l WHERE l.designationAr = :designationAr")
    Optional<Locality> findByDesignationAr(@Param("designationAr") String designationAr);

    @Query("SELECT l FROM Locality l WHERE l.designationLt = :designationLt")
    Optional<Locality> findByDesignationLt(@Param("designationLt") String designationLt);

    @Query("SELECT l FROM Locality l WHERE l.state = :state")
    Page<Locality> findByState(@Param("state") State state, Pageable pageable);

    @Query("SELECT l FROM Locality l WHERE l.state.id = :stateId")
    Page<Locality> findByStateId(@Param("stateId") Long stateId, Pageable pageable);

    @Query("SELECT l FROM Locality l WHERE l.state.id = :stateId ORDER BY l.code")
    List<Locality> findByStateIdOrderByCode(@Param("stateId") Long stateId);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Locality l WHERE l.code = :code")
    boolean existsByCode(@Param("code") int code);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Locality l WHERE l.designationAr = :designationAr")
    boolean existsByDesignationAr(@Param("designationAr") String designationAr);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Locality l WHERE l.designationLt = :designationLt")
    boolean existsByDesignationLt(@Param("designationLt") String designationLt);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Locality l WHERE l.code = :code AND l.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") int code, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Locality l WHERE l.designationAr = :designationAr AND l.id != :id")
    boolean existsByDesignationArAndIdNot(@Param("designationAr") String designationAr, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Locality l WHERE l.designationLt = :designationLt AND l.id != :id")
    boolean existsByDesignationLtAndIdNot(@Param("designationLt") String designationLt, @Param("id") Long id);

    Page<Locality> findAll(Pageable pageable);

    @Query("SELECT l FROM Locality l WHERE l.designationAr LIKE %:designationAr%")
    Page<Locality> findByDesignationArContaining(@Param("designationAr") String designationAr, Pageable pageable);

    @Query("SELECT l FROM Locality l WHERE l.designationLt LIKE %:designationLt%")
    Page<Locality> findByDesignationLtContaining(@Param("designationLt") String designationLt, Pageable pageable);

    @Query("SELECT l FROM Locality l WHERE " +
           "l.designationAr LIKE %:search% OR " +
           "l.designationLt LIKE %:search%")
    Page<Locality> searchByAnyField(@Param("search") String search, Pageable pageable);

    @Query("SELECT l FROM Locality l WHERE l.state.id = :stateId AND (" +
           "l.designationAr LIKE %:search% OR " +
           "l.designationLt LIKE %:search%)")
    Page<Locality> searchByAnyFieldAndStateId(@Param("search") String search, @Param("stateId") Long stateId, Pageable pageable);

    @Query("SELECT COUNT(l) FROM Locality l WHERE l.state.id = :stateId")
    Long countByStateId(@Param("stateId") Long stateId);

    @Query("SELECT COUNT(l) FROM Locality l")
    Long countAllLocalities();

    @Query("SELECT l FROM Locality l JOIN FETCH l.state ORDER BY l.code")
    Page<Locality> findAllWithState(Pageable pageable);

    @Query("SELECT l FROM Locality l JOIN FETCH l.state WHERE l.state.id = :stateId ORDER BY l.code")
    Page<Locality> findByStateIdWithState(@Param("stateId") Long stateId, Pageable pageable);

    @Query("SELECT l FROM Locality l ORDER BY l.code ASC")
    Page<Locality> findAllOrderByCode(Pageable pageable);

    @Query("SELECT l FROM Locality l ORDER BY l.designationLt ASC")
    Page<Locality> findAllOrderByDesignationLt(Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Locality l WHERE l.state.id = :stateId")
    boolean hasLocalitiesInState(@Param("stateId") Long stateId);
}
