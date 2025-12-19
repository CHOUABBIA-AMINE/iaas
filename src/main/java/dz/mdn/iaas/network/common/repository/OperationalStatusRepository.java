/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: OperationalStatusRepository
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

import dz.mdn.iaas.network.common.model.OperationalStatus;

@Repository
public interface OperationalStatusRepository extends JpaRepository<OperationalStatus, Long> {

    @Query("SELECT s FROM OperationalStatus s WHERE LOWER(s.designation) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<OperationalStatus> searchByAnyField(@Param("search") String search, Pageable pageable);
}
