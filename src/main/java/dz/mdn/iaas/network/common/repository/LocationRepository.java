/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocationRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Common
 *
 **/

package dz.mdn.iaas.network.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.common.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l WHERE l.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l WHERE l.code = :code AND l.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT l FROM Location l WHERE LOWER(l.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(l.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Location> searchByAnyField(@Param("search") String search, Pageable pageable);
}
