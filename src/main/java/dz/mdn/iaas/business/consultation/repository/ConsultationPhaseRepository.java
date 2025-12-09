/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationPhaseRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.ConsultationPhase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* ConsultationPhase Repository with essential CRUD operations
* Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
* Unique constraint: F_03 (designationFr)
*/
@Repository
public interface ConsultationPhaseRepository extends JpaRepository<ConsultationPhase, Long> {

   /**
    * Find consultation phase by French designation (F_03) - unique field
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE cp.designationFr = :designationFr")
   Optional<ConsultationPhase> findByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for creation
    */
   @Query("SELECT CASE WHEN COUNT(cp) > 0 THEN true ELSE false END FROM ConsultationPhase cp WHERE cp.designationFr = :designationFr")
   boolean existsByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for updates (excluding current ID)
    */
   @Query("SELECT CASE WHEN COUNT(cp) > 0 THEN true ELSE false END FROM ConsultationPhase cp WHERE cp.designationFr = :designationFr AND cp.id != :id")
   boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

   /**
    * Find all consultation phases with pagination ordered by French designation
    */
   @Query("SELECT cp FROM ConsultationPhase cp ORDER BY cp.designationFr ASC")
   Page<ConsultationPhase> findAllOrderByDesignationFr(Pageable pageable);

   /**
    * Find consultation phase by ID with consultation steps loaded
    */
   @Query("SELECT cp FROM ConsultationPhase cp LEFT JOIN FETCH cp.consultationSteps WHERE cp.id = :id")
   Optional<ConsultationPhase> findByIdWithSteps(@Param("id") Long id);

   /**
    * Find all consultation phases with consultation steps count
    */
   @Query("SELECT cp FROM ConsultationPhase cp LEFT JOIN FETCH cp.consultationSteps ORDER BY cp.designationFr ASC")
   Page<ConsultationPhase> findAllWithStepsCount(Pageable pageable);

   /**
    * Search consultation phases by any designation field
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<ConsultationPhase> searchByDesignation(@Param("search") String search, Pageable pageable);

   /**
    * Search consultation phases by any field
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<ConsultationPhase> searchByAnyField(@Param("search") String search, Pageable pageable);

   /**
    * Find consultation phases by Arabic designation pattern
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE cp.designationAr LIKE %:pattern%")
   Page<ConsultationPhase> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find consultation phases by English designation pattern
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE cp.designationEn LIKE %:pattern%")
   Page<ConsultationPhase> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find consultation phases by French designation pattern
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE cp.designationFr LIKE %:pattern%")
   Page<ConsultationPhase> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Check if consultation phase has consultation steps
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM ConsultationStep cs WHERE cs.consultationPhase.id = :phaseId")
   boolean hasConsultationSteps(@Param("phaseId") Long phaseId);

   /**
    * Count consultation steps for a phase
    */
   @Query("SELECT COUNT(cs) FROM ConsultationStep cs WHERE cs.consultationPhase.id = :phaseId")
   Long countConsultationSteps(@Param("phaseId") Long phaseId);

   /**
    * Find consultation phases that have consultation steps
    */
   @Query("SELECT DISTINCT cp FROM ConsultationPhase cp WHERE cp.id IN " +
          "(SELECT cs.consultationPhase.id FROM ConsultationStep cs) ORDER BY cp.designationFr ASC")
   Page<ConsultationPhase> findPhasesWithSteps(Pageable pageable);

   /**
    * Find consultation phases that have no consultation steps
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE cp.id NOT IN " +
          "(SELECT DISTINCT cs.consultationPhase.id FROM ConsultationStep cs WHERE cs.consultationPhase IS NOT NULL) " +
          "ORDER BY cp.designationFr ASC")
   Page<ConsultationPhase> findPhasesWithoutSteps(Pageable pageable);

   /**
    * Find consultation phases by type based on designation patterns
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE " +
          "(:phaseType = 'PREPARATION' AND (LOWER(cp.designationFr) LIKE '%préparation%' OR LOWER(cp.designationFr) LIKE '%preparation%')) OR " +
          "(:phaseType = 'PUBLICATION' AND (LOWER(cp.designationFr) LIKE '%publication%' OR LOWER(cp.designationFr) LIKE '%annonce%')) OR " +
          "(:phaseType = 'SUBMISSION' AND (LOWER(cp.designationFr) LIKE '%soumission%' OR LOWER(cp.designationFr) LIKE '%dépôt%')) OR " +
          "(:phaseType = 'OPENING' AND (LOWER(cp.designationFr) LIKE '%ouverture%' OR LOWER(cp.designationFr) LIKE '%dépouillement%')) OR " +
          "(:phaseType = 'EVALUATION' AND (LOWER(cp.designationFr) LIKE '%évaluation%' OR LOWER(cp.designationFr) LIKE '%analyse%')) OR " +
          "(:phaseType = 'ADJUDICATION' AND (LOWER(cp.designationFr) LIKE '%adjudication%' OR LOWER(cp.designationFr) LIKE '%attribution%')) OR " +
          "(:phaseType = 'NOTIFICATION' AND (LOWER(cp.designationFr) LIKE '%notification%' OR LOWER(cp.designationFr) LIKE '%information%')) OR " +
          "(:phaseType = 'APPEAL' AND (LOWER(cp.designationFr) LIKE '%recours%' OR LOWER(cp.designationFr) LIKE '%contestation%')) OR " +
          "(:phaseType = 'CONTRACT_SIGNATURE' AND (LOWER(cp.designationFr) LIKE '%signature%' OR LOWER(cp.designationFr) LIKE '%contrat%')) " +
          "ORDER BY cp.designationFr ASC")
   Page<ConsultationPhase> findByPhaseType(@Param("phaseType") String phaseType, Pageable pageable);

   /**
    * Find pre-award consultation phases
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE " +
          "LOWER(cp.designationFr) LIKE '%préparation%' OR LOWER(cp.designationFr) LIKE '%preparation%' OR " +
          "LOWER(cp.designationFr) LIKE '%publication%' OR LOWER(cp.designationFr) LIKE '%annonce%' OR " +
          "LOWER(cp.designationFr) LIKE '%soumission%' OR LOWER(cp.designationFr) LIKE '%dépôt%' OR " +
          "LOWER(cp.designationFr) LIKE '%ouverture%' OR LOWER(cp.designationFr) LIKE '%dépouillement%' OR " +
          "LOWER(cp.designationFr) LIKE '%évaluation%' OR LOWER(cp.designationFr) LIKE '%analyse%' " +
          "ORDER BY cp.designationFr ASC")
   Page<ConsultationPhase> findPreAwardPhases(Pageable pageable);

   /**
    * Find post-award consultation phases
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE " +
          "LOWER(cp.designationFr) LIKE '%adjudication%' OR LOWER(cp.designationFr) LIKE '%attribution%' OR " +
          "LOWER(cp.designationFr) LIKE '%notification%' OR LOWER(cp.designationFr) LIKE '%information%' OR " +
          "LOWER(cp.designationFr) LIKE '%signature%' OR LOWER(cp.designationFr) LIKE '%contrat%' " +
          "ORDER BY cp.designationFr ASC")
   Page<ConsultationPhase> findPostAwardPhases(Pageable pageable);

   /**
    * Count total consultation phases
    */
   @Query("SELECT COUNT(cp) FROM ConsultationPhase cp")
   Long countAllConsultationPhases();

   /**
    * Find consultation phases ordered by specific language designation
    */
   @Query("SELECT cp FROM ConsultationPhase cp ORDER BY cp.designationAr ASC")
   Page<ConsultationPhase> findAllOrderByDesignationAr(Pageable pageable);

   @Query("SELECT cp FROM ConsultationPhase cp ORDER BY cp.designationEn ASC")
   Page<ConsultationPhase> findAllOrderByDesignationEn(Pageable pageable);

   /**
    * Find consultation phases with multilingual support
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE " +
          "cp.designationAr IS NOT NULL AND cp.designationAr != '' AND " +
          "cp.designationEn IS NOT NULL AND cp.designationEn != '' AND " +
          "cp.designationFr IS NOT NULL AND cp.designationFr != ''")
   Page<ConsultationPhase> findMultilingualConsultationPhases(Pageable pageable);

   /**
    * Find consultation phases with missing translations
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE " +
          "cp.designationAr IS NULL OR cp.designationAr = '' OR " +
          "cp.designationEn IS NULL OR cp.designationEn = ''")
   Page<ConsultationPhase> findConsultationPhasesWithMissingTranslations(Pageable pageable);

   /**
    * Find most used consultation phases (by consultation steps count)
    */
   @Query("SELECT cp FROM ConsultationPhase cp LEFT JOIN cp.consultationSteps cs " +
          "GROUP BY cp ORDER BY COUNT(cs) DESC")
   Page<ConsultationPhase> findMostUsedConsultationPhases(Pageable pageable);

   /**
    * Statistics: Count consultation phases by type
    */
   @Query("SELECT " +
          "CASE " +
          "WHEN LOWER(cp.designationFr) LIKE '%préparation%' OR LOWER(cp.designationFr) LIKE '%preparation%' THEN 'PREPARATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%publication%' OR LOWER(cp.designationFr) LIKE '%annonce%' THEN 'PUBLICATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%soumission%' OR LOWER(cp.designationFr) LIKE '%dépôt%' THEN 'SUBMISSION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%ouverture%' OR LOWER(cp.designationFr) LIKE '%dépouillement%' THEN 'OPENING' " +
          "WHEN LOWER(cp.designationFr) LIKE '%évaluation%' OR LOWER(cp.designationFr) LIKE '%analyse%' THEN 'EVALUATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%adjudication%' OR LOWER(cp.designationFr) LIKE '%attribution%' THEN 'ADJUDICATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%notification%' OR LOWER(cp.designationFr) LIKE '%information%' THEN 'NOTIFICATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%recours%' OR LOWER(cp.designationFr) LIKE '%contestation%' THEN 'APPEAL' " +
          "WHEN LOWER(cp.designationFr) LIKE '%signature%' OR LOWER(cp.designationFr) LIKE '%contrat%' THEN 'CONTRACT_SIGNATURE' " +
          "ELSE 'OTHER' END as phaseType, COUNT(cp) " +
          "FROM ConsultationPhase cp GROUP BY " +
          "CASE " +
          "WHEN LOWER(cp.designationFr) LIKE '%préparation%' OR LOWER(cp.designationFr) LIKE '%preparation%' THEN 'PREPARATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%publication%' OR LOWER(cp.designationFr) LIKE '%annonce%' THEN 'PUBLICATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%soumission%' OR LOWER(cp.designationFr) LIKE '%dépôt%' THEN 'SUBMISSION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%ouverture%' OR LOWER(cp.designationFr) LIKE '%dépouillement%' THEN 'OPENING' " +
          "WHEN LOWER(cp.designationFr) LIKE '%évaluation%' OR LOWER(cp.designationFr) LIKE '%analyse%' THEN 'EVALUATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%adjudication%' OR LOWER(cp.designationFr) LIKE '%attribution%' THEN 'ADJUDICATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%notification%' OR LOWER(cp.designationFr) LIKE '%information%' THEN 'NOTIFICATION' " +
          "WHEN LOWER(cp.designationFr) LIKE '%recours%' OR LOWER(cp.designationFr) LIKE '%contestation%' THEN 'APPEAL' " +
          "WHEN LOWER(cp.designationFr) LIKE '%signature%' OR LOWER(cp.designationFr) LIKE '%contrat%' THEN 'CONTRACT_SIGNATURE' " +
          "ELSE 'OTHER' END")
   java.util.List<Object[]> countConsultationPhasesByType();

   /**
    * Check for potential duplicate entries (same French designation but different case)
    */
   @Query("SELECT cp FROM ConsultationPhase cp WHERE UPPER(cp.designationFr) = UPPER(:designationFr)")
   Page<ConsultationPhase> findPotentialDuplicatesByDesignationFr(@Param("designationFr") String designationFr, Pageable pageable);
}