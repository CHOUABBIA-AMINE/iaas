/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineSystemRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.core.model.PipelineSystem;

@Repository
public interface PipelineSystemRepository extends JpaRepository<PipelineSystem, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM PipelineSystem s WHERE s.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM PipelineSystem s WHERE s.code = :code AND s.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT s FROM PipelineSystem s WHERE LOWER(s.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(s.designation) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<PipelineSystem> searchByAnyField(@Param("search") String search, Pageable pageable);

    //List<PipelineSystem> findByProduct(Product product);

    List<PipelineSystem> findByProductId(Long productId);

    //List<PipelineSystem> findByProduct(Product product);

    List<PipelineSystem> findByOperationalStatusId(Long operationStatusId);

    List<PipelineSystem> findByRegionId(Long regionId);
}
