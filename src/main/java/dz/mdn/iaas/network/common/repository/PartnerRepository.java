/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PartnerRepository
 *	@CreatedOn	: 12-19-2025
 *	@Updated	: 12-19-2025
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

import dz.mdn.iaas.network.common.model.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Partner p WHERE p.shortName = :shortName")
    boolean existsByShortName(@Param("shortName") String shortName);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Partner p WHERE p.shortName = :shortName AND p.id != :id")
    boolean existsByShortNameAndIdNot(@Param("shortName") String shortName, @Param("id") Long id);

    @Query("SELECT p FROM Partner p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.shortName) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Partner> searchByAnyField(@Param("search") String search, Pageable pageable);
}
