/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BlocRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.repository;

import dz.mdn.iaas.common.environment.model.Bloc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {

    @Query("SELECT b FROM Bloc b WHERE b.codeAr = :codeAr")
    Optional<Bloc> findByCodeAr(@Param("codeAr") String codeAr);

    @Query("SELECT b FROM Bloc b WHERE b.codeLt = :codeLt")
    Optional<Bloc> findByCodeLt(@Param("codeLt") String codeLt);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bloc b WHERE b.codeAr = :codeAr")
    boolean existsByCodeAr(@Param("codeAr") String codeAr);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bloc b WHERE b.codeLt = :codeLt")
    boolean existsByCodeLt(@Param("codeLt") String codeLt);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bloc b WHERE b.codeAr = :codeAr AND b.id != :id")
    boolean existsByCodeArAndIdNot(@Param("codeAr") String codeAr, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bloc b WHERE b.codeLt = :codeLt AND b.id != :id")
    boolean existsByCodeLtAndIdNot(@Param("codeLt") String codeLt, @Param("id") Long id);

    Page<Bloc> findAll(Pageable pageable);

    @Query("SELECT b FROM Bloc b WHERE b.codeAr LIKE %:codeAr%")
    Page<Bloc> findByCodeArContaining(@Param("codeAr") String codeAr, Pageable pageable);

    @Query("SELECT b FROM Bloc b WHERE b.codeLt LIKE %:codeLt%")
    Page<Bloc> findByCodeLtContaining(@Param("codeLt") String codeLt, Pageable pageable);

    @Query("SELECT b FROM Bloc b WHERE " +
           "b.codeAr LIKE %:search% OR " +
           "b.codeLt LIKE %:search%")
    Page<Bloc> searchByAnyField(@Param("search") String search, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Bloc b")
    Long countAllBlocs();

    @Query("SELECT b FROM Bloc b ORDER BY b.codeLt ASC")
    Page<Bloc> findAllOrderByCodeLt(Pageable pageable);
}
