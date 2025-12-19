/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RegionRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Common
 *
 **/

package dz.mdn.iaas.network.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.common.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Region r WHERE r.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Region r WHERE r.code = :code AND r.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Region r WHERE r.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Region r WHERE r.name = :name AND r.id != :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT r FROM Region r WHERE LOWER(r.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(r.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Region> searchByAnyField(@Param("search") String search, Pageable pageable);
    
    List<Region> findByZoneId(Long zoneId);
}
