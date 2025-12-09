/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentPhaseRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.repository;

import dz.mdn.iaas.business.amendment.model.AmendmentPhase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* AmendmentPhase Repository with essential CRUD operations
* Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
* Unique constraint: F_03 (designationFr)
*/
@Repository
public interface AmendmentPhaseRepository extends JpaRepository<AmendmentPhase, Long> {

   /**
    * Find amendment phase by French designation (F_03) - unique field
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE cp.designationFr = :designationFr")
   Optional<AmendmentPhase> findByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for creation
    */
   @Query("SELECT CASE WHEN COUNT(cp) > 0 THEN true ELSE false END FROM AmendmentPhase cp WHERE cp.designationFr = :designationFr")
   boolean existsByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for updates (excluding current ID)
    */
   @Query("SELECT CASE WHEN COUNT(cp) > 0 THEN true ELSE false END FROM AmendmentPhase cp WHERE cp.designationFr = :designationFr AND cp.id != :id")
   boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

   /**
    * Find all amendment phases with pagination ordered by French designation
    */
   @Query("SELECT cp FROM AmendmentPhase cp ORDER BY cp.designationFr ASC")
   Page<AmendmentPhase> findAllOrderByDesignationFr(Pageable pageable);

   /**
    * Find amendment phase by ID with amendment steps loaded
    */
   @Query("SELECT cp FROM AmendmentPhase cp LEFT JOIN FETCH cp.amendmentSteps WHERE cp.id = :id")
   Optional<AmendmentPhase> findByIdWithSteps(@Param("id") Long id);

   /**
    * Find all amendment phases with amendment steps count
    */
   @Query("SELECT cp FROM AmendmentPhase cp LEFT JOIN FETCH cp.amendmentSteps ORDER BY cp.designationFr ASC")
   Page<AmendmentPhase> findAllWithStepsCount(Pageable pageable);

   /**
    * Search amendment phases by any designation field
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<AmendmentPhase> searchByDesignation(@Param("search") String search, Pageable pageable);

   /**
    * Search amendment phases by any field
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<AmendmentPhase> searchByAnyField(@Param("search") String search, Pageable pageable);

   /**
    * Find amendment phases by Arabic designation pattern
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE cp.designationAr LIKE %:pattern%")
   Page<AmendmentPhase> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find amendment phases by English designation pattern
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE cp.designationEn LIKE %:pattern%")
   Page<AmendmentPhase> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find amendment phases by French designation pattern
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE cp.designationFr LIKE %:pattern%")
   Page<AmendmentPhase> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Check if amendment phase has amendment steps
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM AmendmentStep cs WHERE cs.amendmentPhase.id = :phaseId")
   boolean hasAmendmentSteps(@Param("phaseId") Long phaseId);

   /**
    * Count amendment steps for a phase
    */
   @Query("SELECT COUNT(cs) FROM AmendmentStep cs WHERE cs.amendmentPhase.id = :phaseId")
   Long countAmendmentSteps(@Param("phaseId") Long phaseId);

   /**
    * Find amendment phases that have amendment steps
    */
   @Query("SELECT DISTINCT cp FROM AmendmentPhase cp WHERE cp.id IN " +
          "(SELECT cs.amendmentPhase.id FROM AmendmentStep cs) ORDER BY cp.designationFr ASC")
   Page<AmendmentPhase> findPhasesWithSteps(Pageable pageable);

   /**
    * Find amendment phases that have no amendment steps
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE cp.id NOT IN " +
          "(SELECT DISTINCT cs.amendmentPhase.id FROM AmendmentStep cs WHERE cs.amendmentPhase IS NOT NULL) " +
          "ORDER BY cp.designationFr ASC")
   Page<AmendmentPhase> findPhasesWithoutSteps(Pageable pageable);

   /**
    * Find amendment phases by type based on designation patterns
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE " +
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
   Page<AmendmentPhase> findByPhaseType(@Param("phaseType") String phaseType, Pageable pageable);

   /**
    * Find pre-award amendment phases
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE " +
          "LOWER(cp.designationFr) LIKE '%préparation%' OR LOWER(cp.designationFr) LIKE '%preparation%' OR " +
          "LOWER(cp.designationFr) LIKE '%publication%' OR LOWER(cp.designationFr) LIKE '%annonce%' OR " +
          "LOWER(cp.designationFr) LIKE '%soumission%' OR LOWER(cp.designationFr) LIKE '%dépôt%' OR " +
          "LOWER(cp.designationFr) LIKE '%ouverture%' OR LOWER(cp.designationFr) LIKE '%dépouillement%' OR " +
          "LOWER(cp.designationFr) LIKE '%évaluation%' OR LOWER(cp.designationFr) LIKE '%analyse%' " +
          "ORDER BY cp.designationFr ASC")
   Page<AmendmentPhase> findPreAwardPhases(Pageable pageable);

   /**
    * Find post-award amendment phases
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE " +
          "LOWER(cp.designationFr) LIKE '%adjudication%' OR LOWER(cp.designationFr) LIKE '%attribution%' OR " +
          "LOWER(cp.designationFr) LIKE '%notification%' OR LOWER(cp.designationFr) LIKE '%information%' OR " +
          "LOWER(cp.designationFr) LIKE '%signature%' OR LOWER(cp.designationFr) LIKE '%contrat%' " +
          "ORDER BY cp.designationFr ASC")
   Page<AmendmentPhase> findPostAwardPhases(Pageable pageable);

   /**
    * Count total amendment phases
    */
   @Query("SELECT COUNT(cp) FROM AmendmentPhase cp")
   Long countAllAmendmentPhases();

   /**
    * Find amendment phases ordered by specific language designation
    */
   @Query("SELECT cp FROM AmendmentPhase cp ORDER BY cp.designationAr ASC")
   Page<AmendmentPhase> findAllOrderByDesignationAr(Pageable pageable);

   @Query("SELECT cp FROM AmendmentPhase cp ORDER BY cp.designationEn ASC")
   Page<AmendmentPhase> findAllOrderByDesignationEn(Pageable pageable);

   /**
    * Find amendment phases with multilingual support
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE " +
          "cp.designationAr IS NOT NULL AND cp.designationAr != '' AND " +
          "cp.designationEn IS NOT NULL AND cp.designationEn != '' AND " +
          "cp.designationFr IS NOT NULL AND cp.designationFr != ''")
   Page<AmendmentPhase> findMultilingualAmendmentPhases(Pageable pageable);

   /**
    * Find amendment phases with missing translations
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE " +
          "cp.designationAr IS NULL OR cp.designationAr = '' OR " +
          "cp.designationEn IS NULL OR cp.designationEn = ''")
   Page<AmendmentPhase> findAmendmentPhasesWithMissingTranslations(Pageable pageable);

   /**
    * Find most used amendment phases (by amendment steps count)
    */
   @Query("SELECT cp FROM AmendmentPhase cp LEFT JOIN cp.amendmentSteps cs " +
          "GROUP BY cp ORDER BY COUNT(cs) DESC")
   Page<AmendmentPhase> findMostUsedAmendmentPhases(Pageable pageable);

   /**
    * Statistics: Count amendment phases by type
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
          "FROM AmendmentPhase cp GROUP BY " +
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
   java.util.List<Object[]> countAmendmentPhasesByType();

   /**
    * Check for potential duplicate entries (same French designation but different case)
    */
   @Query("SELECT cp FROM AmendmentPhase cp WHERE UPPER(cp.designationFr) = UPPER(:designationFr)")
   Page<AmendmentPhase> findPotentialDuplicatesByDesignationFr(@Param("designationFr") String designationFr, Pageable pageable);
}