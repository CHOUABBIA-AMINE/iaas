/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRankRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.MilitaryRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Military Rank Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr (unique), 
 * F_04=abbreviationAr, F_05=abbreviationEn, F_06=abbreviationFr, F_07=militaryCategory
 */
@Repository
public interface MilitaryRankRepository extends JpaRepository<MilitaryRank, Long> {

    /**
     * Find military rank by French designation (F_03) - unique constraint
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.designationFr = :designationFr")
    Optional<MilitaryRank> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find all military ranks ordered by French designation
     */
    @Query("SELECT mr FROM MilitaryRank mr ORDER BY mr.designationFr ASC")
    Page<MilitaryRank> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search military ranks by designation (any language)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "mr.designationFr LIKE %:search% OR " +
           "mr.designationEn LIKE %:search% OR " +
           "mr.designationAr LIKE %:search%")
    Page<MilitaryRank> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Search military ranks by abbreviation (any language)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "mr.abbreviationFr LIKE %:search% OR " +
           "mr.abbreviationEn LIKE %:search% OR " +
           "mr.abbreviationAr LIKE %:search%")
    Page<MilitaryRank> searchByAbbreviation(@Param("search") String search, Pageable pageable);

    /**
     * Search military ranks by any field
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "mr.designationFr LIKE %:search% OR " +
           "mr.designationEn LIKE %:search% OR " +
           "mr.designationAr LIKE %:search% OR " +
           "mr.abbreviationFr LIKE %:search% OR " +
           "mr.abbreviationEn LIKE %:search% OR " +
           "mr.abbreviationAr LIKE %:search%")
    Page<MilitaryRank> searchByAnyField(@Param("search") String search, Pageable pageable);

    /**
     * Find military ranks by military category
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.militaryCategory.id = :militaryCategoryId ORDER BY mr.designationFr ASC")
    Page<MilitaryRank> findByMilitaryCategory(@Param("militaryCategoryId") Long militaryCategoryId, Pageable pageable);

    /**
     * Find all military ranks by military category (without pagination)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.militaryCategory.id = :militaryCategoryId ORDER BY mr.designationFr ASC")
    List<MilitaryRank> findAllByMilitaryCategory(@Param("militaryCategoryId") Long militaryCategoryId);

    /**
     * Find military ranks by French abbreviation
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.abbreviationFr = :abbreviationFr")
    List<MilitaryRank> findByAbbreviationFr(@Param("abbreviationFr") String abbreviationFr);

    /**
     * Find general officer ranks (based on designation patterns)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%général%' OR " +
           "LOWER(mr.designationEn) LIKE '%general%' OR " +
           "LOWER(mr.designationFr) LIKE '%amiral%' OR " +
           "LOWER(mr.designationEn) LIKE '%admiral%'")
    Page<MilitaryRank> findGeneralOfficerRanks(Pageable pageable);

    /**
     * Find senior officer ranks (Colonel level and equivalent)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%colonel%' OR " +
           "LOWER(mr.designationEn) LIKE '%colonel%' OR " +
           "LOWER(mr.designationFr) LIKE '%capitaine de vaisseau%'")
    Page<MilitaryRank> findSeniorOfficerRanks(Pageable pageable);

    /**
     * Find company grade officer ranks
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%commandant%' OR " +
           "LOWER(mr.designationFr) LIKE '%capitaine%' OR " +
           "LOWER(mr.designationFr) LIKE '%lieutenant%' OR " +
           "LOWER(mr.designationEn) LIKE '%major%' OR " +
           "LOWER(mr.designationEn) LIKE '%captain%' OR " +
           "LOWER(mr.designationEn) LIKE '%lieutenant%'")
    Page<MilitaryRank> findCompanyGradeOfficerRanks(Pageable pageable);

    /**
     * Find non-commissioned officer ranks
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%sous-officier%' OR " +
           "LOWER(mr.designationFr) LIKE '%sergent%' OR " +
           "LOWER(mr.designationFr) LIKE '%adjudant%' OR " +
           "LOWER(mr.designationEn) LIKE '%sergeant%'")
    Page<MilitaryRank> findNonCommissionedOfficerRanks(Pageable pageable);

    /**
     * Find enlisted ranks
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%soldat%' OR " +
           "LOWER(mr.designationFr) LIKE '%matelot%' OR " +
           "LOWER(mr.designationEn) LIKE '%soldier%' OR " +
           "LOWER(mr.designationEn) LIKE '%private%'")
    Page<MilitaryRank> findEnlistedRanks(Pageable pageable);

    /**
     * Find army ranks (based on military category)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.militaryCategory.designationFr) LIKE '%terre%' OR " +
           "LOWER(mr.militaryCategory.designationEn) LIKE '%army%'")
    Page<MilitaryRank> findArmyRanks(Pageable pageable);

    /**
     * Find navy ranks (based on military category)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.militaryCategory.designationFr) LIKE '%marine%' OR " +
           "LOWER(mr.militaryCategory.designationEn) LIKE '%navy%'")
    Page<MilitaryRank> findNavyRanks(Pageable pageable);

    /**
     * Find air force ranks (based on military category)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.militaryCategory.designationFr) LIKE '%air%' OR " +
           "LOWER(mr.militaryCategory.designationEn) LIKE '%air%'")
    Page<MilitaryRank> findAirForceRanks(Pageable pageable);

    /**
     * Find gendarmerie ranks (based on military category)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.militaryCategory.designationFr) LIKE '%gendarmerie%'")
    Page<MilitaryRank> findGendarmerieRanks(Pageable pageable);

    /**
     * Count military ranks by military category
     */
    @Query("SELECT COUNT(mr) FROM MilitaryRank mr WHERE mr.militaryCategory.id = :militaryCategoryId")
    Long countByMilitaryCategory(@Param("militaryCategoryId") Long militaryCategoryId);

    /**
     * Find multilingual military ranks (have designations in multiple languages)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "mr.designationFr IS NOT NULL AND mr.designationFr != '' AND " +
           "(mr.designationEn IS NOT NULL AND mr.designationEn != '' OR " +
           "mr.designationAr IS NOT NULL AND mr.designationAr != '')")
    Page<MilitaryRank> findMultilingualRanks(Pageable pageable);

    /**
     * Find military ranks with Arabic designations
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.designationAr IS NOT NULL AND mr.designationAr != ''")
    Page<MilitaryRank> findWithArabicDesignation(Pageable pageable);

    /**
     * Find military ranks with English designations
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.designationEn IS NOT NULL AND mr.designationEn != ''")
    Page<MilitaryRank> findWithEnglishDesignation(Pageable pageable);

    /**
     * Check if French designation exists (for uniqueness validation)
     */
    @Query("SELECT CASE WHEN COUNT(mr) > 0 THEN true ELSE false END FROM MilitaryRank mr WHERE mr.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check if French designation exists excluding current ID (for update validation)
     */
    @Query("SELECT CASE WHEN COUNT(mr) > 0 THEN true ELSE false END FROM MilitaryRank mr WHERE mr.designationFr = :designationFr AND mr.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find military ranks by multiple categories
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE mr.militaryCategory.id IN :categoryIds ORDER BY mr.designationFr ASC")
    Page<MilitaryRank> findByMilitaryCategories(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);

    /**
     * Find military ranks by designation pattern (French)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE LOWER(mr.designationFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<MilitaryRank> findByDesignationFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find military ranks by abbreviation pattern (French)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE LOWER(mr.abbreviationFr) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    Page<MilitaryRank> findByAbbreviationFrPattern(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find military ranks with complete information (all required fields filled)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "mr.designationFr IS NOT NULL AND mr.designationFr != '' AND " +
           "mr.abbreviationFr IS NOT NULL AND mr.abbreviationFr != '' AND " +
           "mr.militaryCategory IS NOT NULL")
    Page<MilitaryRank> findWithCompleteInformation(Pageable pageable);

    /**
     * Get military rank statistics by category
     */
    @Query("SELECT mr.militaryCategory.designationFr, COUNT(mr) FROM MilitaryRank mr GROUP BY mr.militaryCategory.designationFr ORDER BY COUNT(mr) DESC")
    List<Object[]> getRankStatisticsByCategory();

    /**
     * Find highest precedence ranks (general officers)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%général%' OR " +
           "LOWER(mr.designationFr) LIKE '%amiral%' " +
           "ORDER BY " +
           "CASE " +
           "WHEN LOWER(mr.designationFr) LIKE '%corps d''armée%' THEN 1 " +
           "WHEN LOWER(mr.designationFr) LIKE '%division%' THEN 2 " +
           "WHEN LOWER(mr.designationFr) LIKE '%brigade%' THEN 3 " +
           "ELSE 4 " +
           "END")
    Page<MilitaryRank> findHighestPrecedenceRanks(Pageable pageable);

    /**
     * Find military ranks by service branch pattern
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.militaryCategory.designationFr) LIKE LOWER(CONCAT('%', :branchPattern, '%')) OR " +
           "LOWER(mr.militaryCategory.designationEn) LIKE LOWER(CONCAT('%', :branchPattern, '%'))")
    Page<MilitaryRank> findByServiceBranch(@Param("branchPattern") String branchPattern, Pageable pageable);

    /**
     * Find commissioned officer ranks
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%général%' OR " +
           "LOWER(mr.designationFr) LIKE '%amiral%' OR " +
           "LOWER(mr.designationFr) LIKE '%colonel%' OR " +
           "LOWER(mr.designationFr) LIKE '%commandant%' OR " +
           "LOWER(mr.designationFr) LIKE '%capitaine%' OR " +
           "LOWER(mr.designationFr) LIKE '%lieutenant%'")
    Page<MilitaryRank> findCommissionedOfficerRanks(Pageable pageable);

    /**
     * Find command-eligible ranks (officers who can command units)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%général%' OR " +
           "LOWER(mr.designationFr) LIKE '%amiral%' OR " +
           "LOWER(mr.designationFr) LIKE '%colonel%' OR " +
           "LOWER(mr.designationFr) LIKE '%commandant%' OR " +
           "LOWER(mr.designationFr) LIKE '%capitaine%'")
    Page<MilitaryRank> findCommandEligibleRanks(Pageable pageable);

    /**
     * Find most recently added ranks
     */
    @Query("SELECT mr FROM MilitaryRank mr ORDER BY mr.id DESC")
    Page<MilitaryRank> findMostRecentRanks(Pageable pageable);

    /**
     * Count total military ranks
     */
    @Query("SELECT COUNT(mr) FROM MilitaryRank mr")
    Long countAllMilitaryRanks();

    /**
     * Count general officer ranks
     */
    @Query("SELECT COUNT(mr) FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%général%' OR " +
           "LOWER(mr.designationFr) LIKE '%amiral%'")
    Long countGeneralOfficerRanks();

    /**
     * Count commissioned officer ranks
     */
    @Query("SELECT COUNT(mr) FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) LIKE '%général%' OR " +
           "LOWER(mr.designationFr) LIKE '%amiral%' OR " +
           "LOWER(mr.designationFr) LIKE '%colonel%' OR " +
           "LOWER(mr.designationFr) LIKE '%commandant%' OR " +
           "LOWER(mr.designationFr) LIKE '%capitaine%' OR " +
           "LOWER(mr.designationFr) LIKE '%lieutenant%'")
    Long countCommissionedOfficerRanks();

    /**
     * Find similar ranks (for duplicate detection)
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "LOWER(mr.designationFr) = LOWER(:designation) OR " +
           "LOWER(mr.abbreviationFr) = LOWER(:abbreviation)")
    List<MilitaryRank> findSimilarRanks(@Param("designation") String designation, @Param("abbreviation") String abbreviation);

    /**
     * Find ranks requiring specific military category
     */
    @Query("SELECT mr FROM MilitaryRank mr WHERE " +
           "mr.militaryCategory.id = :categoryId AND " +
           "mr.designationFr IS NOT NULL AND mr.abbreviationFr IS NOT NULL")
    List<MilitaryRank> findValidRanksForCategory(@Param("categoryId") Long categoryId);
}