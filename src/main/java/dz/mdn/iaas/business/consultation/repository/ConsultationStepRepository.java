/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStepRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* ConsultationStep Repository with essential CRUD operations
* Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=consultationPhase
* Unique constraint: F_03 (designationFr)
* Foreign key: F_04 (consultationPhase) - required
*/
@Repository
public interface ConsultationStepRepository extends JpaRepository<ConsultationStep, Long> {

   /**
    * Find consultation step by French designation (F_03) - unique field
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE cs.designationFr = :designationFr")
   Optional<ConsultationStep> findByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for creation
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM ConsultationStep cs WHERE cs.designationFr = :designationFr")
   boolean existsByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for updates (excluding current ID)
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM ConsultationStep cs WHERE cs.designationFr = :designationFr AND cs.id != :id")
   boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

   /**
    * Find all consultation steps with pagination ordered by French designation
    */
   @Query("SELECT cs FROM ConsultationStep cs ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findAllOrderByDesignationFr(Pageable pageable);

   /**
    * Find consultation step by ID with consultation phase loaded
    */
   @Query("SELECT cs FROM ConsultationStep cs LEFT JOIN FETCH cs.consultationPhase WHERE cs.id = :id")
   Optional<ConsultationStep> findByIdWithPhase(@Param("id") Long id);

   /**
    * Find all consultation steps with consultation phase loaded
    */
   @Query("SELECT cs FROM ConsultationStep cs LEFT JOIN FETCH cs.consultationPhase ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findAllWithPhase(Pageable pageable);

   /**
    * Find consultation steps by consultation phase ID
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE cs.consultationPhase.id = :consultationPhaseId ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findByConsultationPhaseId(@Param("consultationPhaseId") Long consultationPhaseId, Pageable pageable);

   /**
    * Count consultation steps by consultation phase ID
    */
   @Query("SELECT COUNT(cs) FROM ConsultationStep cs WHERE cs.consultationPhase.id = :consultationPhaseId")
   Long countByConsultationPhaseId(@Param("consultationPhaseId") Long consultationPhaseId);

   /**
    * Search consultation steps by any designation field
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "cs.designationAr LIKE %:search% OR " +
          "cs.designationEn LIKE %:search% OR " +
          "cs.designationFr LIKE %:search%")
   Page<ConsultationStep> searchByDesignation(@Param("search") String search, Pageable pageable);

   /**
    * Search consultation steps by any field including consultation phase
    */
   @Query("SELECT cs FROM ConsultationStep cs LEFT JOIN cs.consultationPhase cp WHERE " +
          "cs.designationAr LIKE %:search% OR " +
          "cs.designationEn LIKE %:search% OR " +
          "cs.designationFr LIKE %:search% OR " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<ConsultationStep> searchByAnyField(@Param("search") String search, Pageable pageable);

   /**
    * Find consultation steps by Arabic designation pattern
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE cs.designationAr LIKE %:pattern%")
   Page<ConsultationStep> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find consultation steps by English designation pattern
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE cs.designationEn LIKE %:pattern%")
   Page<ConsultationStep> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find consultation steps by French designation pattern
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE cs.designationFr LIKE %:pattern%")
   Page<ConsultationStep> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find consultation steps by step type based on designation patterns
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "(:stepType = 'DRAFTING' AND (LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%')) OR " +
          "(:stepType = 'VALIDATION' AND (LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%')) OR " +
          "(:stepType = 'VERIFICATION' AND (LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%')) OR " +
          "(:stepType = 'TRANSMISSION' AND (LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%')) OR " +
          "(:stepType = 'RECEPTION' AND (LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%')) OR " +
          "(:stepType = 'ANALYSIS' AND (LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%')) OR " +
          "(:stepType = 'DECISION' AND (LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%')) OR " +
          "(:stepType = 'ARCHIVING' AND (LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%')) " +
          "ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findByStepType(@Param("stepType") String stepType, Pageable pageable);

   /**
    * Find administrative consultation steps
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' OR " +
          "LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%' OR " +
          "LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' OR " +
          "LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findAdministrativeSteps(Pageable pageable);

   /**
    * Find operational consultation steps
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%' OR " +
          "LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%' OR " +
          "LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findOperationalSteps(Pageable pageable);

   /**
    * Find consultation steps by consultation phase designation
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE cs.consultationPhase.designationFr LIKE %:phaseDesignation% ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findByConsultationPhaseDesignation(@Param("phaseDesignation") String phaseDesignation, Pageable pageable);

   /**
    * Count total consultation steps
    */
   @Query("SELECT COUNT(cs) FROM ConsultationStep cs")
   Long countAllConsultationSteps();

   /**
    * Find consultation steps ordered by specific language designation
    */
   @Query("SELECT cs FROM ConsultationStep cs ORDER BY cs.designationAr ASC")
   Page<ConsultationStep> findAllOrderByDesignationAr(Pageable pageable);

   @Query("SELECT cs FROM ConsultationStep cs ORDER BY cs.designationEn ASC")
   Page<ConsultationStep> findAllOrderByDesignationEn(Pageable pageable);

   /**
    * Find consultation steps with multilingual support
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "cs.designationAr IS NOT NULL AND cs.designationAr != '' AND " +
          "cs.designationEn IS NOT NULL AND cs.designationEn != '' AND " +
          "cs.designationFr IS NOT NULL AND cs.designationFr != ''")
   Page<ConsultationStep> findMultilingualConsultationSteps(Pageable pageable);

   /**
    * Find consultation steps with missing translations
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "cs.designationAr IS NULL OR cs.designationAr = '' OR " +
          "cs.designationEn IS NULL OR cs.designationEn = ''")
   Page<ConsultationStep> findConsultationStepsWithMissingTranslations(Pageable pageable);

   /**
    * Find consultation steps that require approval
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' OR " +
          "LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findStepsRequiringApproval(Pageable pageable);

   /**
    * Find consultation steps that can be automated
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' OR " +
          "LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%' OR " +
          "LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findAutomatableSteps(Pageable pageable);

   /**
    * Statistics: Count consultation steps by type
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
          "FROM ConsultationStep cs GROUP BY " +
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
   java.util.List<Object[]> countConsultationStepsByType();

   /**
    * Statistics: Count consultation steps by consultation phase
    */
   @Query("SELECT cp.designationFr, COUNT(cs) FROM ConsultationStep cs " +
          "RIGHT JOIN cs.consultationPhase cp GROUP BY cp.designationFr ORDER BY COUNT(cs) DESC")
   java.util.List<Object[]> countConsultationStepsByPhase();

   /**
    * Check for potential duplicate entries (same French designation but different case)
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE UPPER(cs.designationFr) = UPPER(:designationFr)")
   Page<ConsultationStep> findPotentialDuplicatesByDesignationFr(@Param("designationFr") String designationFr, Pageable pageable);

   /**
    * Find consultation steps with longest designations (for display optimization)
    */
   @Query("SELECT cs FROM ConsultationStep cs ORDER BY LENGTH(cs.designationFr) DESC")
   Page<ConsultationStep> findByLongestDesignation(Pageable pageable);

   /**
    * Find consultation steps by consultation phase type
    */
   @Query("SELECT cs FROM ConsultationStep cs WHERE " +
          "(:phaseType = 'PREPARATION' AND (LOWER(cs.consultationPhase.designationFr) LIKE '%préparation%' OR LOWER(cs.consultationPhase.designationFr) LIKE '%preparation%')) OR " +
          "(:phaseType = 'EVALUATION' AND (LOWER(cs.consultationPhase.designationFr) LIKE '%évaluation%' OR LOWER(cs.consultationPhase.designationFr) LIKE '%analyse%')) OR " +
          "(:phaseType = 'DECISION' AND (LOWER(cs.consultationPhase.designationFr) LIKE '%décision%' OR LOWER(cs.consultationPhase.designationFr) LIKE '%adjudication%')) " +
          "ORDER BY cs.designationFr ASC")
   Page<ConsultationStep> findByConsultationPhaseType(@Param("phaseType") String phaseType, Pageable pageable);
}