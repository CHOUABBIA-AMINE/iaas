/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationNatureRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Repository
 *	@Pakage		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.RealizationNature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RealizationNature Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
 * F_03 (designationFr) has unique constraint and is required
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Repository
public interface RealizationNatureRepository extends JpaRepository<RealizationNature, Long> {

    /**
     * Find realization nature by French designation (F_03) - unique field
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationFr = :designationFr")
    Optional<RealizationNature> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find realization nature by Arabic designation (F_01)
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationAr = :designationAr")
    Optional<RealizationNature> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find realization nature by English designation (F_02)
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationEn = :designationEn")
    Optional<RealizationNature> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if realization nature exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RealizationNature r WHERE r.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RealizationNature r WHERE r.designationFr = :designationFr AND r.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find all realization natures with pagination ordered by French designation
     */
    @Query("SELECT r FROM RealizationNature r ORDER BY r.designationFr ASC")
    Page<RealizationNature> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Search realization natures by any designation field
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "r.designationAr LIKE %:search% OR " +
           "r.designationEn LIKE %:search% OR " +
           "r.designationFr LIKE %:search%")
    Page<RealizationNature> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find realization natures by French designation pattern (F_03)
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationFr LIKE %:pattern%")
    Page<RealizationNature> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find realization natures by Arabic designation pattern (F_01)
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationAr LIKE %:pattern%")
    Page<RealizationNature> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Find realization natures by English designation pattern (F_02)
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationEn LIKE %:pattern%")
    Page<RealizationNature> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total realization natures
     */
    @Query("SELECT COUNT(r) FROM RealizationNature r")
    Long countAllRealizationNatures();

    /**
     * Find realization natures that have Arabic designation
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationAr IS NOT NULL AND r.designationAr != ''")
    Page<RealizationNature> findWithArabicDesignation(Pageable pageable);

    /**
     * Find realization natures that have English designation
     */
    @Query("SELECT r FROM RealizationNature r WHERE r.designationEn IS NOT NULL AND r.designationEn != ''")
    Page<RealizationNature> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual realization natures (have at least 2 designations)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationEn IS NOT NULL AND r.designationEn != '') OR " +
           "(r.designationAr IS NOT NULL AND r.designationAr != '' AND r.designationFr IS NOT NULL AND r.designationFr != '') OR " +
           "(r.designationEn IS NOT NULL AND r.designationEn != '' AND r.designationFr IS NOT NULL AND r.designationFr != '')")
    Page<RealizationNature> findMultilingualRealizationNatures(Pageable pageable);

    /**
     * Find infrastructure natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%infrastructure%' OR LOWER(r.designationFr) LIKE '%construction%' OR " +
           "LOWER(r.designationFr) LIKE '%bâtiment%' OR LOWER(r.designationFr) LIKE '%ouvrage%'")
    Page<RealizationNature> findInfrastructureNatures(Pageable pageable);

    /**
     * Find technology natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%technologie%' OR LOWER(r.designationFr) LIKE '%informatique%' OR " +
           "LOWER(r.designationFr) LIKE '%numérique%' OR LOWER(r.designationFr) LIKE '%digital%'")
    Page<RealizationNature> findTechnologyNatures(Pageable pageable);

    /**
     * Find service natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%service%' OR LOWER(r.designationFr) LIKE '%prestation%' OR " +
           "LOWER(r.designationFr) LIKE '%conseil%' OR LOWER(r.designationFr) LIKE '%consultation%'")
    Page<RealizationNature> findServiceNatures(Pageable pageable);

    /**
     * Find manufacturing natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%fabrication%' OR LOWER(r.designationFr) LIKE '%production%' OR " +
           "LOWER(r.designationFr) LIKE '%manufacturier%' OR LOWER(r.designationFr) LIKE '%industriel%'")
    Page<RealizationNature> findManufacturingNatures(Pageable pageable);

    /**
     * Find research and development natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%recherche%' OR LOWER(r.designationFr) LIKE '%développement%' OR " +
           "LOWER(r.designationFr) LIKE '%innovation%' OR LOWER(r.designationFr) LIKE '%r&d%'")
    Page<RealizationNature> findResearchDevelopmentNatures(Pageable pageable);

    /**
     * Find energy and utilities natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%énergie%' OR LOWER(r.designationFr) LIKE '%électrique%' OR " +
           "LOWER(r.designationFr) LIKE '%hydraulique%' OR LOWER(r.designationFr) LIKE '%utilities%'")
    Page<RealizationNature> findEnergyUtilitiesNatures(Pageable pageable);

    /**
     * Find environmental natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%environnement%' OR LOWER(r.designationFr) LIKE '%écologique%' OR " +
           "LOWER(r.designationFr) LIKE '%durable%' OR LOWER(r.designationFr) LIKE '%vert%'")
    Page<RealizationNature> findEnvironmentalNatures(Pageable pageable);

    /**
     * Find commercial natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%commercial%' OR LOWER(r.designationFr) LIKE '%affaires%' OR " +
           "LOWER(r.designationFr) LIKE '%business%' OR LOWER(r.designationFr) LIKE '%marché%'")
    Page<RealizationNature> findCommercialNatures(Pageable pageable);

    /**
     * Find education natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%éducation%' OR LOWER(r.designationFr) LIKE '%formation%' OR " +
           "LOWER(r.designationFr) LIKE '%enseignement%' OR LOWER(r.designationFr) LIKE '%pédagogique%'")
    Page<RealizationNature> findEducationNatures(Pageable pageable);

    /**
     * Find health and medical natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%santé%' OR LOWER(r.designationFr) LIKE '%médical%' OR " +
           "LOWER(r.designationFr) LIKE '%hospitalier%' OR LOWER(r.designationFr) LIKE '%thérapeutique%'")
    Page<RealizationNature> findHealthMedicalNatures(Pageable pageable);

    /**
     * Find transportation natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%transport%' OR LOWER(r.designationFr) LIKE '%logistique%' OR " +
           "LOWER(r.designationFr) LIKE '%mobilité%' OR LOWER(r.designationFr) LIKE '%circulation%'")
    Page<RealizationNature> findTransportationNatures(Pageable pageable);

    /**
     * Find agricultural natures (based on French designation patterns)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%agricole%' OR LOWER(r.designationFr) LIKE '%rural%' OR " +
           "LOWER(r.designationFr) LIKE '%agronomique%' OR LOWER(r.designationFr) LIKE '%cultivation%'")
    Page<RealizationNature> findAgriculturalNatures(Pageable pageable);

    /**
     * Find high complexity natures (infrastructure, technology, research)
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%infrastructure%' OR LOWER(r.designationFr) LIKE '%technologie%' OR " +
           "LOWER(r.designationFr) LIKE '%recherche%' OR LOWER(r.designationFr) LIKE '%développement%'")
    Page<RealizationNature> findHighComplexityNatures(Pageable pageable);

    /**
     * Find public interest natures
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%infrastructure%' OR LOWER(r.designationFr) LIKE '%santé%' OR " +
           "LOWER(r.designationFr) LIKE '%éducation%' OR LOWER(r.designationFr) LIKE '%transport%' OR " +
           "LOWER(r.designationFr) LIKE '%énergie%' OR LOWER(r.designationFr) LIKE '%environnement%'")
    Page<RealizationNature> findPublicInterestNatures(Pageable pageable);

    /**
     * Find realization natures ordered by designation in specific language
     */
    @Query("SELECT r FROM RealizationNature r ORDER BY r.designationAr ASC")
    Page<RealizationNature> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT r FROM RealizationNature r ORDER BY r.designationEn ASC")
    Page<RealizationNature> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count natures by category
     */
    @Query("SELECT COUNT(r) FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%infrastructure%' OR LOWER(r.designationFr) LIKE '%construction%'")
    Long countInfrastructureNatures();

    @Query("SELECT COUNT(r) FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%technologie%' OR LOWER(r.designationFr) LIKE '%informatique%'")
    Long countTechnologyNatures();

    @Query("SELECT COUNT(r) FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%service%' OR LOWER(r.designationFr) LIKE '%prestation%'")
    Long countServiceNatures();

    /**
     * Find natures requiring environmental assessment
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%infrastructure%' OR LOWER(r.designationFr) LIKE '%énergie%' OR " +
           "LOWER(r.designationFr) LIKE '%environnement%' OR LOWER(r.designationFr) LIKE '%fabrication%' OR " +
           "LOWER(r.designationFr) LIKE '%transport%'")
    Page<RealizationNature> findNaturesRequiringEnvironmentalAssessment(Pageable pageable);

    /**
     * Find natures requiring technical expertise
     */
    @Query("SELECT r FROM RealizationNature r WHERE " +
           "LOWER(r.designationFr) LIKE '%technologie%' OR LOWER(r.designationFr) LIKE '%recherche%' OR " +
           "LOWER(r.designationFr) LIKE '%fabrication%' OR LOWER(r.designationFr) LIKE '%énergie%' OR " +
           "LOWER(r.designationFr) LIKE '%infrastructure%'")
    Page<RealizationNature> findNaturesRequiringTechnicalExpertise(Pageable pageable);

    /**
     * Search natures by category pattern
     */
    @Query("SELECT r FROM RealizationNature r WHERE LOWER(r.designationFr) LIKE %:categoryPattern%")
    Page<RealizationNature> findByNatureCategory(@Param("categoryPattern") String categoryPattern, Pageable pageable);
}
