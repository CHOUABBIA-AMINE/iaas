/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.repository;

import dz.mdn.iaas.business.amendment.model.AmendmentStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* AmendmentStep Repository with essential CRUD operations
* Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=amendmentPhase
* Unique constraint: F_03 (designationFr)
* Foreign key: F_04 (amendmentPhase) - required
*/
@Repository
public interface AmendmentStepRepository extends JpaRepository<AmendmentStep, Long> {

   /**
    * Find amendment step by French designation (F_03) - unique field
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE cs.designationFr = :designationFr")
   Optional<AmendmentStep> findByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for creation
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM AmendmentStep cs WHERE cs.designationFr = :designationFr")
   boolean existsByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for updates (excluding current ID)
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM AmendmentStep cs WHERE cs.designationFr = :designationFr AND cs.id != :id")
   boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

   /**
    * Find all amendment steps with pagination ordered by French designation
    */
   @Query("SELECT cs FROM AmendmentStep cs ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findAllOrderByDesignationFr(Pageable pageable);

   /**
    * Find amendment step by ID with amendment phase loaded
    */
   @Query("SELECT cs FROM AmendmentStep cs LEFT JOIN FETCH cs.amendmentPhase WHERE cs.id = :id")
   Optional<AmendmentStep> findByIdWithPhase(@Param("id") Long id);

   /**
    * Find all amendment steps with amendment phase loaded
    */
   @Query("SELECT cs FROM AmendmentStep cs LEFT JOIN FETCH cs.amendmentPhase ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findAllWithPhase(Pageable pageable);

   /**
    * Find amendment steps by amendment phase ID
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE cs.amendmentPhase.id = :amendmentPhaseId ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findByAmendmentPhaseId(@Param("amendmentPhaseId") Long amendmentPhaseId, Pageable pageable);

   /**
    * Count amendment steps by amendment phase ID
    */
   @Query("SELECT COUNT(cs) FROM AmendmentStep cs WHERE cs.amendmentPhase.id = :amendmentPhaseId")
   Long countByAmendmentPhaseId(@Param("amendmentPhaseId") Long amendmentPhaseId);

   /**
    * Search amendment steps by any designation field
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "cs.designationAr LIKE %:search% OR " +
          "cs.designationEn LIKE %:search% OR " +
          "cs.designationFr LIKE %:search%")
   Page<AmendmentStep> searchByDesignation(@Param("search") String search, Pageable pageable);

   /**
    * Search amendment steps by any field including amendment phase
    */
   @Query("SELECT cs FROM AmendmentStep cs LEFT JOIN cs.amendmentPhase cp WHERE " +
          "cs.designationAr LIKE %:search% OR " +
          "cs.designationEn LIKE %:search% OR " +
          "cs.designationFr LIKE %:search% OR " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<AmendmentStep> searchByAnyField(@Param("search") String search, Pageable pageable);

   /**
    * Find amendment steps by Arabic designation pattern
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE cs.designationAr LIKE %:pattern%")
   Page<AmendmentStep> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find amendment steps by English designation pattern
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE cs.designationEn LIKE %:pattern%")
   Page<AmendmentStep> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find amendment steps by French designation pattern
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE cs.designationFr LIKE %:pattern%")
   Page<AmendmentStep> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find amendment steps by step type based on designation patterns
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "(:stepType = 'DRAFTING' AND (LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%')) OR " +
          "(:stepType = 'VALIDATION' AND (LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%')) OR " +
          "(:stepType = 'VERIFICATION' AND (LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%')) OR " +
          "(:stepType = 'TRANSMISSION' AND (LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%')) OR " +
          "(:stepType = 'RECEPTION' AND (LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%')) OR " +
          "(:stepType = 'ANALYSIS' AND (LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%')) OR " +
          "(:stepType = 'DECISION' AND (LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%')) OR " +
          "(:stepType = 'ARCHIVING' AND (LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%')) " +
          "ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findByStepType(@Param("stepType") String stepType, Pageable pageable);

   /**
    * Find administrative amendment steps
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' OR " +
          "LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%' OR " +
          "LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' OR " +
          "LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' " +
          "ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findAdministrativeSteps(Pageable pageable);

   /**
    * Find operational amendment steps
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%' OR " +
          "LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%' OR " +
          "LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' " +
          "ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findOperationalSteps(Pageable pageable);

   /**
    * Find amendment steps by amendment phase designation
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE cs.amendmentPhase.designationFr LIKE %:phaseDesignation% ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findByAmendmentPhaseDesignation(@Param("phaseDesignation") String phaseDesignation, Pageable pageable);

   /**
    * Count total amendment steps
    */
   @Query("SELECT COUNT(cs) FROM AmendmentStep cs")
   Long countAllAmendmentSteps();

   /**
    * Find amendment steps ordered by specific language designation
    */
   @Query("SELECT cs FROM AmendmentStep cs ORDER BY cs.designationAr ASC")
   Page<AmendmentStep> findAllOrderByDesignationAr(Pageable pageable);

   @Query("SELECT cs FROM AmendmentStep cs ORDER BY cs.designationEn ASC")
   Page<AmendmentStep> findAllOrderByDesignationEn(Pageable pageable);

   /**
    * Find amendment steps with multilingual support
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "cs.designationAr IS NOT NULL AND cs.designationAr != '' AND " +
          "cs.designationEn IS NOT NULL AND cs.designationEn != '' AND " +
          "cs.designationFr IS NOT NULL AND cs.designationFr != ''")
   Page<AmendmentStep> findMultilingualAmendmentSteps(Pageable pageable);

   /**
    * Find amendment steps with missing translations
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "cs.designationAr IS NULL OR cs.designationAr = '' OR " +
          "cs.designationEn IS NULL OR cs.designationEn = ''")
   Page<AmendmentStep> findAmendmentStepsWithMissingTranslations(Pageable pageable);

   /**
    * Find amendment steps that require approval
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' OR " +
          "LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' " +
          "ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findStepsRequiringApproval(Pageable pageable);

   /**
    * Find amendment steps that can be automated
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' OR " +
          "LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%' OR " +
          "LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' " +
          "ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findAutomatableSteps(Pageable pageable);

   /**
    * Statistics: Count amendment steps by type
    */
   @Query("SELECT " +
          "CASE " +
          "WHEN LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%' THEN 'DRAFTING' " +
          "WHEN LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' THEN 'VALIDATION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%' THEN 'VERIFICATION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' THEN 'TRANSMISSION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%' THEN 'RECEPTION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%' THEN 'ANALYSIS' " +
          "WHEN LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' THEN 'DECISION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' THEN 'ARCHIVING' " +
          "ELSE 'OTHER' END as stepType, COUNT(cs) " +
          "FROM AmendmentStep cs GROUP BY " +
          "CASE " +
          "WHEN LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%' THEN 'DRAFTING' " +
          "WHEN LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' THEN 'VALIDATION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%' THEN 'VERIFICATION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' THEN 'TRANSMISSION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%' THEN 'RECEPTION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%' THEN 'ANALYSIS' " +
          "WHEN LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' THEN 'DECISION' " +
          "WHEN LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' THEN 'ARCHIVING' " +
          "ELSE 'OTHER' END")
   java.util.List<Object[]> countAmendmentStepsByType();

   /**
    * Statistics: Count amendment steps by amendment phase
    */
   @Query("SELECT cp.designationFr, COUNT(cs) FROM AmendmentStep cs " +
          "RIGHT JOIN cs.amendmentPhase cp GROUP BY cp.designationFr ORDER BY COUNT(cs) DESC")
   java.util.List<Object[]> countAmendmentStepsByPhase();

   /**
    * Check for potential duplicate entries (same French designation but different case)
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE UPPER(cs.designationFr) = UPPER(:designationFr)")
   Page<AmendmentStep> findPotentialDuplicatesByDesignationFr(@Param("designationFr") String designationFr, Pageable pageable);

   /**
    * Find amendment steps with longest designations (for display optimization)
    */
   @Query("SELECT cs FROM AmendmentStep cs ORDER BY LENGTH(cs.designationFr) DESC")
   Page<AmendmentStep> findByLongestDesignation(Pageable pageable);

   /**
    * Find amendment steps by amendment phase type
    */
   @Query("SELECT cs FROM AmendmentStep cs WHERE " +
          "(:phaseType = 'PREPARATION' AND (LOWER(cs.amendmentPhase.designationFr) LIKE '%préparation%' OR LOWER(cs.amendmentPhase.designationFr) LIKE '%preparation%')) OR " +
          "(:phaseType = 'EVALUATION' AND (LOWER(cs.amendmentPhase.designationFr) LIKE '%évaluation%' OR LOWER(cs.amendmentPhase.designationFr) LIKE '%analyse%')) OR " +
          "(:phaseType = 'DECISION' AND (LOWER(cs.amendmentPhase.designationFr) LIKE '%décision%' OR LOWER(cs.amendmentPhase.designationFr) LIKE '%adjudication%')) " +
          "ORDER BY cs.designationFr ASC")
   Page<AmendmentStep> findByAmendmentPhaseType(@Param("phaseType") String phaseType, Pageable pageable);
}