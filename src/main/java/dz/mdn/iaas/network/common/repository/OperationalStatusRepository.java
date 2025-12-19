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

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM OperationalStatus p WHERE p.code = :code")
    boolean existsByCode(@Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM OperationalStatus p WHERE p.code = :code AND p.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM OperationalStatus p WHERE p.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM OperationalStatus p WHERE p.designationFr = :designationFr AND p.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);
    
    @Query("SELECT t FROM OperationalStatus t WHERE LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%'))"
            + " OR LOWER(t.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))"
            + " OR LOWER(t.designationEn) LIKE LOWER(CONCAT('%', :search, '%'))"
            + " OR LOWER(t.designationFr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<OperationalStatus> searchByAnyField(@Param("search") String search, Pageable pageable);

}
