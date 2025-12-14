/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ZoneRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.common.model.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN true ELSE false END FROM Zone z WHERE z.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN true ELSE false END FROM Zone z WHERE z.name = :name AND z.id != :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN true ELSE false END FROM Zone z WHERE z.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN true ELSE false END FROM Zone z WHERE z.code = :code AND z.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT z FROM Zone z WHERE LOWER(z.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Zone> searchByName(@Param("search") String search, Pageable pageable);
    
    @Query("SELECT z FROM Zone z WHERE LOWER(z.code) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Zone> searchByCode(@Param("search") String search, Pageable pageable);
    
    @Query("SELECT z FROM Zone z WHERE LOWER(z.name) LIKE LOWER(CONCAT('%', :search, '%')) or LOWER(z.code) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Zone> search(@Param("search") String search, Pageable pageable);
}
