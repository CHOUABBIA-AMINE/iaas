/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: TerminalTypeRepository
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
 *
 *	@Type		: Repository
 *	@Layer		: Network / Repository
 *	@Package	: Network / Type
 *
 **/

package dz.mdn.iaas.network.type.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.network.type.model.TerminalType;

@Repository
public interface TerminalTypeRepository extends JpaRepository<TerminalType, Long> {

    @Query("SELECT t FROM TerminalType t WHERE LOWER(t.designation) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<TerminalType> searchByAnyField(@Param("search") String search, Pageable pageable);
}
