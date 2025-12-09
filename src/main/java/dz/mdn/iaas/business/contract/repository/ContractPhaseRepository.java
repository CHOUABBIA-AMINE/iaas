/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractPhaseRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.repository;

import dz.mdn.iaas.business.contract.model.ContractPhase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* ContractPhase Repository with essential CRUD operations
* Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr
* Unique constraint: F_03 (designationFr)
*/
@Repository
public interface ContractPhaseRepository extends JpaRepository<ContractPhase, Long> {

   /**
    * Find contract phase by French designation (F_03) - unique field
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE cp.designationFr = :designationFr")
   Optional<ContractPhase> findByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for creation
    */
   @Query("SELECT CASE WHEN COUNT(cp) > 0 THEN true ELSE false END FROM ContractPhase cp WHERE cp.designationFr = :designationFr")
   boolean existsByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for updates (excluding current ID)
    */
   @Query("SELECT CASE WHEN COUNT(cp) > 0 THEN true ELSE false END FROM ContractPhase cp WHERE cp.designationFr = :designationFr AND cp.id != :id")
   boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

   /**
    * Find all contract phases with pagination ordered by French designation
    */
   @Query("SELECT cp FROM ContractPhase cp ORDER BY cp.designationFr ASC")
   Page<ContractPhase> findAllOrderByDesignationFr(Pageable pageable);

   /**
    * Find contract phase by ID with contract steps loaded
    */
   @Query("SELECT cp FROM ContractPhase cp LEFT JOIN FETCH cp.contractSteps WHERE cp.id = :id")
   Optional<ContractPhase> findByIdWithSteps(@Param("id") Long id);

   /**
    * Find all contract phases with contract steps count
    */
   @Query("SELECT cp FROM ContractPhase cp LEFT JOIN FETCH cp.contractSteps ORDER BY cp.designationFr ASC")
   Page<ContractPhase> findAllWithStepsCount(Pageable pageable);

   /**
    * Search contract phases by any designation field
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<ContractPhase> searchByDesignation(@Param("search") String search, Pageable pageable);

   /**
    * Search contract phases by any field
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<ContractPhase> searchByAnyField(@Param("search") String search, Pageable pageable);

   /**
    * Find contract phases by Arabic designation pattern
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE cp.designationAr LIKE %:pattern%")
   Page<ContractPhase> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find contract phases by English designation pattern
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE cp.designationEn LIKE %:pattern%")
   Page<ContractPhase> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find contract phases by French designation pattern
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE cp.designationFr LIKE %:pattern%")
   Page<ContractPhase> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Check if contract phase has contract steps
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM ContractStep cs WHERE cs.contractPhase.id = :phaseId")
   boolean hasContractSteps(@Param("phaseId") Long phaseId);

   /**
    * Count contract steps for a phase
    */
   @Query("SELECT COUNT(cs) FROM ContractStep cs WHERE cs.contractPhase.id = :phaseId")
   Long countContractSteps(@Param("phaseId") Long phaseId);

   /**
    * Find contract phases that have contract steps
    */
   @Query("SELECT DISTINCT cp FROM ContractPhase cp WHERE cp.id IN " +
          "(SELECT cs.contractPhase.id FROM ContractStep cs) ORDER BY cp.designationFr ASC")
   Page<ContractPhase> findPhasesWithSteps(Pageable pageable);

   /**
    * Find contract phases that have no contract steps
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE cp.id NOT IN " +
          "(SELECT DISTINCT cs.contractPhase.id FROM ContractStep cs WHERE cs.contractPhase IS NOT NULL) " +
          "ORDER BY cp.designationFr ASC")
   Page<ContractPhase> findPhasesWithoutSteps(Pageable pageable);

   /**
    * Find contract phases by type based on designation patterns
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE " +
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
   Page<ContractPhase> findByPhaseType(@Param("phaseType") String phaseType, Pageable pageable);

   /**
    * Find pre-award contract phases
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE " +
          "LOWER(cp.designationFr) LIKE '%préparation%' OR LOWER(cp.designationFr) LIKE '%preparation%' OR " +
          "LOWER(cp.designationFr) LIKE '%publication%' OR LOWER(cp.designationFr) LIKE '%annonce%' OR " +
          "LOWER(cp.designationFr) LIKE '%soumission%' OR LOWER(cp.designationFr) LIKE '%dépôt%' OR " +
          "LOWER(cp.designationFr) LIKE '%ouverture%' OR LOWER(cp.designationFr) LIKE '%dépouillement%' OR " +
          "LOWER(cp.designationFr) LIKE '%évaluation%' OR LOWER(cp.designationFr) LIKE '%analyse%' " +
          "ORDER BY cp.designationFr ASC")
   Page<ContractPhase> findPreAwardPhases(Pageable pageable);

   /**
    * Find post-award contract phases
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE " +
          "LOWER(cp.designationFr) LIKE '%adjudication%' OR LOWER(cp.designationFr) LIKE '%attribution%' OR " +
          "LOWER(cp.designationFr) LIKE '%notification%' OR LOWER(cp.designationFr) LIKE '%information%' OR " +
          "LOWER(cp.designationFr) LIKE '%signature%' OR LOWER(cp.designationFr) LIKE '%contrat%' " +
          "ORDER BY cp.designationFr ASC")
   Page<ContractPhase> findPostAwardPhases(Pageable pageable);

   /**
    * Count total contract phases
    */
   @Query("SELECT COUNT(cp) FROM ContractPhase cp")
   Long countAllContractPhases();

   /**
    * Find contract phases ordered by specific language designation
    */
   @Query("SELECT cp FROM ContractPhase cp ORDER BY cp.designationAr ASC")
   Page<ContractPhase> findAllOrderByDesignationAr(Pageable pageable);

   @Query("SELECT cp FROM ContractPhase cp ORDER BY cp.designationEn ASC")
   Page<ContractPhase> findAllOrderByDesignationEn(Pageable pageable);

   /**
    * Find contract phases with multilingual support
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE " +
          "cp.designationAr IS NOT NULL AND cp.designationAr != '' AND " +
          "cp.designationEn IS NOT NULL AND cp.designationEn != '' AND " +
          "cp.designationFr IS NOT NULL AND cp.designationFr != ''")
   Page<ContractPhase> findMultilingualContractPhases(Pageable pageable);

   /**
    * Find contract phases with missing translations
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE " +
          "cp.designationAr IS NULL OR cp.designationAr = '' OR " +
          "cp.designationEn IS NULL OR cp.designationEn = ''")
   Page<ContractPhase> findContractPhasesWithMissingTranslations(Pageable pageable);

   /**
    * Find most used contract phases (by contract steps count)
    */
   @Query("SELECT cp FROM ContractPhase cp LEFT JOIN cp.contractSteps cs " +
          "GROUP BY cp ORDER BY COUNT(cs) DESC")
   Page<ContractPhase> findMostUsedContractPhases(Pageable pageable);

   /**
    * Statistics: Count contract phases by type
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
          "FROM ContractPhase cp GROUP BY " +
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
   java.util.List<Object[]> countContractPhasesByType();

   /**
    * Check for potential duplicate entries (same French designation but different case)
    */
   @Query("SELECT cp FROM ContractPhase cp WHERE UPPER(cp.designationFr) = UPPER(:designationFr)")
   Page<ContractPhase> findPotentialDuplicatesByDesignationFr(@Param("designationFr") String designationFr, Pageable pageable);
}