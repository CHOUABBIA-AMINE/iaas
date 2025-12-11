/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetTypeRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.BudgetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * BudgetType Repository
 * Methods aligned with BudgetTypeService requirements
 */
@Repository
public interface BudgetTypeRepository extends JpaRepository<BudgetType, Long> {
    
    /**
     * Check if French designation exists
     */
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BudgetType b WHERE b.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);
    
    /**
     * Check if French designation exists excluding current ID (for updates)
     */
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BudgetType b WHERE b.designationFr = :designationFr AND b.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);
    
    /**
     * Check if French acronym exists
     */
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BudgetType b WHERE b.acronymFr = :acronymFr")
    boolean existsByAcronymFr(@Param("acronymFr") String acronymFr);
    
    /**
     * Check if French acronym exists excluding current ID (for updates)
     */
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BudgetType b WHERE b.acronymFr = :acronymFr AND b.id != :id")
    boolean existsByAcronymFrAndIdNot(@Param("acronymFr") String acronymFr, @Param("id") Long id);
    
    /**
     * Search across all fields (designations and acronyms)
     */
    @Query("SELECT b FROM BudgetType b WHERE " +
           "LOWER(b.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.designationAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.acronymFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.acronymEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.acronymAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BudgetType> searchByAnyField(@Param("search") String search, Pageable pageable);
}
