/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: TerminalRepository
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

import dz.mdn.iaas.network.core.model.Terminal;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Terminal t WHERE t.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Terminal t WHERE t.code = :code AND t.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);

    @Query("SELECT t FROM Terminal t WHERE LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Terminal> searchByAnyField(@Param("search") String search, Pageable pageable);
}
