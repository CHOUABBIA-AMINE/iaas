/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StationRepository
 *	@CreatedOn	: 12-19-2025
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

import dz.mdn.iaas.network.core.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Station s WHERE s.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Station s WHERE s.code = :code AND s.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT s FROM Station s WHERE LOWER(s.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Station> searchByAnyField(@Param("search") String search, Pageable pageable);
}
