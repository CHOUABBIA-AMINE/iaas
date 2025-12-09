/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: JobRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Job Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=structure
 * F_03 (designationFr) has unique constraint and is required
 * F_04 (structure) is required foreign key
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Find job by French designation (F_03) - unique field
     */
    @Query("SELECT j FROM Job j WHERE j.designationFr = :designationFr")
    Optional<Job> findByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Find job by Arabic designation (F_01)
     */
    @Query("SELECT j FROM Job j WHERE j.designationAr = :designationAr")
    Optional<Job> findByDesignationAr(@Param("designationAr") String designationAr);

    /**
     * Find job by English designation (F_02)
     */
    @Query("SELECT j FROM Job j WHERE j.designationEn = :designationEn")
    Optional<Job> findByDesignationEn(@Param("designationEn") String designationEn);

    /**
     * Check if job exists by French designation
     */
    @Query("SELECT CASE WHEN COUNT(j) > 0 THEN true ELSE false END FROM Job j WHERE j.designationFr = :designationFr")
    boolean existsByDesignationFr(@Param("designationFr") String designationFr);

    /**
     * Check unique constraint for updates (excluding current ID)
     */
    @Query("SELECT CASE WHEN COUNT(j) > 0 THEN true ELSE false END FROM Job j WHERE j.designationFr = :designationFr AND j.id != :id")
    boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

    /**
     * Find jobs by structure ID (F_04)
     */
    @Query("SELECT j FROM Job j WHERE j.structure.id = :structureId ORDER BY j.designationFr ASC")
    Page<Job> findByStructureId(@Param("structureId") Long structureId, Pageable pageable);

    /**
     * Find all jobs with pagination ordered by French designation
     */
    @Query("SELECT j FROM Job j ORDER BY j.designationFr ASC")
    Page<Job> findAllOrderByDesignationFr(Pageable pageable);

    /**
     * Find all jobs ordered by structure and designation
     */
    @Query("SELECT j FROM Job j ORDER BY j.structure.designationFr ASC, j.designationFr ASC")
    Page<Job> findAllOrderByStructureAndDesignation(Pageable pageable);

    /**
     * Search jobs by any designation field
     */
    @Query("SELECT j FROM Job j WHERE " +
           "j.designationAr LIKE %:search% OR " +
           "j.designationEn LIKE %:search% OR " +
           "j.designationFr LIKE %:search%")
    Page<Job> searchByDesignation(@Param("search") String search, Pageable pageable);

    /**
     * Find jobs by French designation pattern (F_03)
     */
    @Query("SELECT j FROM Job j WHERE j.designationFr LIKE %:pattern%")
    Page<Job> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

    /**
     * Count total jobs
     */
    @Query("SELECT COUNT(j) FROM Job j")
    Long countAllJobs();

    /**
     * Count jobs by structure
     */
    @Query("SELECT COUNT(j) FROM Job j WHERE j.structure.id = :structureId")
    Long countByStructureId(@Param("structureId") Long structureId);

    /**
     * Find jobs that have Arabic designation
     */
    @Query("SELECT j FROM Job j WHERE j.designationAr IS NOT NULL AND j.designationAr != ''")
    Page<Job> findWithArabicDesignation(Pageable pageable);

    /**
     * Find jobs that have English designation
     */
    @Query("SELECT j FROM Job j WHERE j.designationEn IS NOT NULL AND j.designationEn != ''")
    Page<Job> findWithEnglishDesignation(Pageable pageable);

    /**
     * Find multilingual jobs (have at least 2 designations)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "(j.designationAr IS NOT NULL AND j.designationAr != '' AND j.designationEn IS NOT NULL AND j.designationEn != '') OR " +
           "(j.designationAr IS NOT NULL AND j.designationAr != '' AND j.designationFr IS NOT NULL AND j.designationFr != '') OR " +
           "(j.designationEn IS NOT NULL AND j.designationEn != '' AND j.designationFr IS NOT NULL AND j.designationFr != '')")
    Page<Job> findMultilingualJobs(Pageable pageable);

    /**
     * Find leadership jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%commandant%' OR LOWER(j.designationFr) LIKE '%chef%' OR " +
           "LOWER(j.designationFr) LIKE '%directeur%' OR LOWER(j.designationFr) LIKE '%responsable%'")
    Page<Job> findLeadershipJobs(Pageable pageable);

    /**
     * Find administrative jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%secrétaire%' OR LOWER(j.designationFr) LIKE '%assistant%' OR " +
           "LOWER(j.designationFr) LIKE '%administrateur%' OR LOWER(j.designationFr) LIKE '%gestionnaire%'")
    Page<Job> findAdministrativeJobs(Pageable pageable);

    /**
     * Find technical jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%ingénieur%' OR LOWER(j.designationFr) LIKE '%technicien%' OR " +
           "LOWER(j.designationFr) LIKE '%spécialiste%' OR LOWER(j.designationFr) LIKE '%expert%'")
    Page<Job> findTechnicalJobs(Pageable pageable);

    /**
     * Find operational jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%opérateur%' OR LOWER(j.designationFr) LIKE '%pilote%' OR " +
           "LOWER(j.designationFr) LIKE '%conducteur%' OR LOWER(j.designationFr) LIKE '%agent%'")
    Page<Job> findOperationalJobs(Pageable pageable);

    /**
     * Find security jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%garde%' OR LOWER(j.designationFr) LIKE '%sécurité%' OR " +
           "LOWER(j.designationFr) LIKE '%surveillant%' OR LOWER(j.designationFr) LIKE '%contrôleur%'")
    Page<Job> findSecurityJobs(Pageable pageable);

    /**
     * Find medical jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%médecin%' OR LOWER(j.designationFr) LIKE '%infirmier%' OR " +
           "LOWER(j.designationFr) LIKE '%dentiste%' OR LOWER(j.designationFr) LIKE '%pharmacien%'")
    Page<Job> findMedicalJobs(Pageable pageable);

    /**
     * Find legal jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%juriste%' OR LOWER(j.designationFr) LIKE '%avocat%' OR " +
           "LOWER(j.designationFr) LIKE '%conseiller juridique%' OR LOWER(j.designationFr) LIKE '%magistrat%'")
    Page<Job> findLegalJobs(Pageable pageable);

    /**
     * Find financial jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%comptable%' OR LOWER(j.designationFr) LIKE '%financier%' OR " +
           "LOWER(j.designationFr) LIKE '%trésorier%' OR LOWER(j.designationFr) LIKE '%auditeur%'")
    Page<Job> findFinancialJobs(Pageable pageable);

    /**
     * Find human resources jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%ressources humaines%' OR LOWER(j.designationFr) LIKE '%rh%' OR " +
           "LOWER(j.designationFr) LIKE '%personnel%' OR LOWER(j.designationFr) LIKE '%recruteur%'")
    Page<Job> findHumanResourcesJobs(Pageable pageable);

    /**
     * Find communication jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%communication%' OR LOWER(j.designationFr) LIKE '%relations publiques%' OR " +
           "LOWER(j.designationFr) LIKE '%journaliste%' OR LOWER(j.designationFr) LIKE '%porte-parole%'")
    Page<Job> findCommunicationJobs(Pageable pageable);

    /**
     * Find logistics jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%logistique%' OR LOWER(j.designationFr) LIKE '%approvisionnement%' OR " +
           "LOWER(j.designationFr) LIKE '%magasinier%' OR LOWER(j.designationFr) LIKE '%transport%'")
    Page<Job> findLogisticsJobs(Pageable pageable);

    /**
     * Find training jobs (based on French designation patterns)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%formateur%' OR LOWER(j.designationFr) LIKE '%instructeur%' OR " +
           "LOWER(j.designationFr) LIKE '%enseignant%' OR LOWER(j.designationFr) LIKE '%professeur%'")
    Page<Job> findTrainingJobs(Pageable pageable);

    /**
     * Find executive level jobs
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%directeur général%' OR LOWER(j.designationFr) LIKE '%président%' OR " +
           "LOWER(j.designationFr) LIKE '%commandant en chef%' OR LOWER(j.designationFr) LIKE '%secrétaire général%'")
    Page<Job> findExecutiveJobs(Pageable pageable);

    /**
     * Find senior management jobs
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%directeur%' OR LOWER(j.designationFr) LIKE '%chef de service%' OR " +
           "LOWER(j.designationFr) LIKE '%commandant%' OR LOWER(j.designationFr) LIKE '%responsable%'")
    Page<Job> findSeniorManagementJobs(Pageable pageable);

    /**
     * Find middle management jobs
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%chef%' OR LOWER(j.designationFr) LIKE '%superviseur%' OR " +
           "LOWER(j.designationFr) LIKE '%coordinateur%' OR LOWER(j.designationFr) LIKE '%adjoint%'")
    Page<Job> findMiddleManagementJobs(Pageable pageable);

    /**
     * Find specialist jobs
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%spécialiste%' OR LOWER(j.designationFr) LIKE '%ingénieur%' OR " +
           "LOWER(j.designationFr) LIKE '%analyste%' OR LOWER(j.designationFr) LIKE '%technicien%'")
    Page<Job> findSpecialistJobs(Pageable pageable);

    /**
     * Find entry level jobs
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%stagiaire%' OR LOWER(j.designationFr) LIKE '%apprenti%' OR " +
           "LOWER(j.designationFr) LIKE '%junior%' OR LOWER(j.designationFr) LIKE '%débutant%'")
    Page<Job> findEntryLevelJobs(Pageable pageable);

    /**
     * Find jobs ordered by designation in specific language
     */
    @Query("SELECT j FROM Job j ORDER BY j.designationAr ASC")
    Page<Job> findAllOrderByDesignationAr(Pageable pageable);

    @Query("SELECT j FROM Job j ORDER BY j.designationEn ASC")
    Page<Job> findAllOrderByDesignationEn(Pageable pageable);

    /**
     * Count jobs by category
     */
    @Query("SELECT COUNT(j) FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%commandant%' OR LOWER(j.designationFr) LIKE '%chef%' OR " +
           "LOWER(j.designationFr) LIKE '%directeur%'")
    Long countLeadershipJobs();

    @Query("SELECT COUNT(j) FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%secrétaire%' OR LOWER(j.designationFr) LIKE '%assistant%'")
    Long countAdministrativeJobs();

    @Query("SELECT COUNT(j) FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%ingénieur%' OR LOWER(j.designationFr) LIKE '%technicien%'")
    Long countTechnicalJobs();

    @Query("SELECT COUNT(j) FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%opérateur%' OR LOWER(j.designationFr) LIKE '%agent%'")
    Long countOperationalJobs();

    @Query("SELECT COUNT(j) FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%médecin%' OR LOWER(j.designationFr) LIKE '%infirmier%'")
    Long countMedicalJobs();

    /**
     * Find jobs with join fetch for structure
     */
    @Query("SELECT j FROM Job j JOIN FETCH j.structure ORDER BY j.designationFr ASC")
    Page<Job> findAllWithStructure(Pageable pageable);

    /**
     * Find jobs by structure designation
     */
    @Query("SELECT j FROM Job j WHERE j.structure.designationFr = :structureDesignation ORDER BY j.designationFr ASC")
    Page<Job> findByStructureDesignation(@Param("structureDesignation") String structureDesignation, Pageable pageable);

    /**
     * Find jobs by structure acronym
     */
    @Query("SELECT j FROM Job j WHERE j.structure.acronymFr = :structureAcronym ORDER BY j.designationFr ASC")
    Page<Job> findByStructureAcronym(@Param("structureAcronym") String structureAcronym, Pageable pageable);

    /**
     * Find jobs by structure type designation
     */
    @Query("SELECT j FROM Job j WHERE j.structure.structureType.designationFr = :structureTypeDesignation ORDER BY j.designationFr ASC")
    Page<Job> findByStructureTypeDesignation(@Param("structureTypeDesignation") String structureTypeDesignation, Pageable pageable);

    /**
     * Search jobs by job and structure context
     */
    @Query("SELECT j FROM Job j LEFT JOIN j.structure s LEFT JOIN s.structureType t WHERE " +
           "(j.designationFr LIKE %:search% OR j.designationEn LIKE %:search% OR j.designationAr LIKE %:search% OR " +
           "s.designationFr LIKE %:search% OR s.acronymFr LIKE %:search% OR " +
           "t.designationFr LIKE %:search%) " +
           "ORDER BY j.designationFr ASC")
    Page<Job> searchWithStructureContext(@Param("search") String search, Pageable pageable);

    /**
     * Find jobs requiring security clearance (leadership and security roles)
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%commandant%' OR LOWER(j.designationFr) LIKE '%directeur%' OR " +
           "LOWER(j.designationFr) LIKE '%chef%' OR LOWER(j.designationFr) LIKE '%sécurité%' OR " +
           "LOWER(j.designationFr) LIKE '%garde%'")
    Page<Job> findSecurityClearanceJobs(Pageable pageable);

    /**
     * Find jobs by management track
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%directeur%' OR LOWER(j.designationFr) LIKE '%chef%' OR " +
           "LOWER(j.designationFr) LIKE '%commandant%' OR LOWER(j.designationFr) LIKE '%superviseur%' OR " +
           "LOWER(j.designationFr) LIKE '%coordinateur%' OR LOWER(j.designationFr) LIKE '%responsable%'")
    Page<Job> findManagementTrackJobs(Pageable pageable);

    /**
     * Find jobs by specialist track
     */
    @Query("SELECT j FROM Job j WHERE " +
           "LOWER(j.designationFr) LIKE '%spécialiste%' OR LOWER(j.designationFr) LIKE '%expert%' OR " +
           "LOWER(j.designationFr) LIKE '%ingénieur%' OR LOWER(j.designationFr) LIKE '%analyste%' OR " +
           "LOWER(j.designationFr) LIKE '%conseiller%'")
    Page<Job> findSpecialistTrackJobs(Pageable pageable);

    /**
     * Search jobs by category pattern
     */
    @Query("SELECT j FROM Job j WHERE LOWER(j.designationFr) LIKE %:categoryPattern%")
    Page<Job> findByJobCategory(@Param("categoryPattern") String categoryPattern, Pageable pageable);

    /**
     * Find jobs by hierarchy level pattern
     */
    @Query("SELECT j FROM Job j WHERE LOWER(j.designationFr) LIKE %:levelPattern%")
    Page<Job> findByJobLevel(@Param("levelPattern") String levelPattern, Pageable pageable);
}
