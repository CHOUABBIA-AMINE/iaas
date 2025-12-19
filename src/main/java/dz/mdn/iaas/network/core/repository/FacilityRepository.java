/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.core.model.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Facility f WHERE f.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Facility f WHERE f.code = :code AND f.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT f FROM Facility f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(f.code) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Facility> searchByAnyField(@Param("search") String search, Pageable pageable);
}
