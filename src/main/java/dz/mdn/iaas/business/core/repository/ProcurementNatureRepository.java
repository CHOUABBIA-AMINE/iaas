/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementNatureRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Repository
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.ProcurementNature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ProcurementNature Repository
 * Methods aligned with ProcurementNatureService requirements
 */
@Repository
public interface ProcurementNatureRepository extends JpaRepository<ProcurementNature, Long> {
    
    /**
     * Check if French designation exists
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProcurementNature p WHERE p.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);
    
    /**
     * Check if French designation exists excluding current ID (for updates)
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProcurementNature p WHERE p.designationFr = :designationFr AND p.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);
    
    /**
     * Search by designation (all languages)
     */
    @Query("SELECT p FROM ProcurementNature p WHERE " +
           "LOWER(p.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<ProcurementNature> searchByDesignation(@Param("search") String search, Pageable pageable);
}
