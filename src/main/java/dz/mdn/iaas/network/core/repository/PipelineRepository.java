/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineRepository
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

import dz.mdn.iaas.network.core.model.Pipeline;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pipeline p WHERE p.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pipeline p WHERE p.code = :code AND p.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT p FROM Pipeline p WHERE LOWER(p.code) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Pipeline> searchByAnyField(@Param("search") String search, Pageable pageable);
}
