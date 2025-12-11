/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CurrencyRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Repository
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Currency Repository
 * Methods aligned with CurrencyService requirements
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    
    // ========== CODE VALIDATIONS ==========
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.code = :code")
    boolean existsByCode(@Param("code") String code);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.code = :code AND c.id != :id")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("id") Long id);
    
    // ========== DESIGNATION VALIDATIONS ==========
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.designationAr = :designationAr")
    boolean existsByDesignationAr(@Param("designationAr") String designationAr);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.designationAr = :designationAr AND c.id != :id")
    boolean existsByDesignationArAndIdNot(@Param("designationAr") String designationAr, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.designationEn = :designationEn")
    boolean existsByDesignationEn(@Param("designationEn") String designationEn);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.designationEn = :designationEn AND c.id != :id")
    boolean existsByDesignationEnAndIdNot(@Param("designationEn") String designationEn, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.designationFr = :designationFr AND c.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);
    
    // ========== ACRONYM VALIDATIONS ==========
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.acronymAr = :acronymAr")
    boolean existsByAcronymAr(@Param("acronymAr") String acronymAr);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.acronymAr = :acronymAr AND c.id != :id")
    boolean existsByAcronymArAndIdNot(@Param("acronymAr") String acronymAr, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.acronymEn = :acronymEn")
    boolean existsByAcronymEn(@Param("acronymEn") String acronymEn);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.acronymEn = :acronymEn AND c.id != :id")
    boolean existsByAcronymEnAndIdNot(@Param("acronymEn") String acronymEn, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.acronymFr = :acronymFr")
    boolean existsByAcronymFr(@Param("acronymFr") String acronymFr);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Currency c WHERE c.acronymFr = :acronymFr AND c.id != :id")
    boolean existsByAcronymFrAndIdNot(@Param("acronymFr") String acronymFr, @Param("id") Long id);
    
    // ========== SEARCH METHODS ==========
    
    /**
     * Search across all fields (code, designations, acronyms)
     */
    @Query("SELECT c FROM Currency c WHERE " +
           "LOWER(c.code) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.acronymFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.acronymEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.acronymAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Currency> searchByAnyField(@Param("search") String search, Pageable pageable);
    
    /**
     * Search by designation (all languages)
     */
    @Query("SELECT c FROM Currency c WHERE " +
           "LOWER(c.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.designationAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Currency> searchByDesignation(@Param("search") String search, Pageable pageable);
    
    /**
     * Search by acronym (all languages)
     */
    @Query("SELECT c FROM Currency c WHERE " +
           "LOWER(c.acronymFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.acronymEn) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.acronymAr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Currency> searchByAcronym(@Param("search") String search, Pageable pageable);
}
