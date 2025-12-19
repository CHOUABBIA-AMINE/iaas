/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: InfrastructureRepository
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Repository
 *
 **/

package dz.mdn.iaas.network.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.core.model.Infrastructure;

@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Infrastructure i WHERE i.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Infrastructure i WHERE i.code = :code AND i.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    Optional<Infrastructure> findByCode(String code);

    @Query("SELECT i FROM Infrastructure i WHERE LOWER(i.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Infrastructure> searchByAnyField(@Param("search") String search, Pageable pageable);

    List<Infrastructure> findByOperationalStatus(OperationalStatus status);

    List<Infrastructure> findByOperationalStatusId(Long statusId);

    @Query("SELECT i FROM Infrastructure i WHERE i.commissioningDate IS NOT NULL ORDER BY i.commissioningDate DESC")
    List<Infrastructure> findAllOrderByCommissioningDateDesc();

    @Query("SELECT i FROM Infrastructure i WHERE i.decommissioningDate IS NOT NULL ORDER BY i.decommissioningDate DESC")
    List<Infrastructure> findAllDecommissioned();
}
