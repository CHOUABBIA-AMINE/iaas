/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineSegmentRepository
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.repository;

import dz.mdn.iaas.network.model.Pipeline;
import dz.mdn.iaas.network.model.PipelineSegment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PipelineSegmentRepository extends JpaRepository<PipelineSegment, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM PipelineSegment s WHERE s.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM PipelineSegment s WHERE s.code = :code AND s.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT s FROM PipelineSegment s WHERE LOWER(s.code) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<PipelineSegment> searchByAnyField(@Param("search") String search, Pageable pageable);

    List<PipelineSegment> findByPipeline(Pipeline pipeline);

    List<PipelineSegment> findByPipelineId(Long pipelineId);
}
