/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStepRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.repository;

import dz.mdn.iaas.business.contract.model.ContractStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* ContractStep Repository with essential CRUD operations
* Based on exact field names: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=contractPhase
* Unique constraint: F_03 (designationFr)
* Foreign key: F_04 (contractPhase) - required
*/
@Repository
public interface ContractStepRepository extends JpaRepository<ContractStep, Long> {

   /**
    * Find contract step by French designation (F_03) - unique field
    */
   @Query("SELECT cs FROM ContractStep cs WHERE cs.designationFr = :designationFr")
   Optional<ContractStep> findByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for creation
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM ContractStep cs WHERE cs.designationFr = :designationFr")
   boolean existsByDesignationFr(@Param("designationFr") String designationFr);

   /**
    * Check unique constraint for updates (excluding current ID)
    */
   @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM ContractStep cs WHERE cs.designationFr = :designationFr AND cs.id != :id")
   boolean existsByDesignationFrAndIdNot(@Param("designationFr") String designationFr, @Param("id") Long id);

   /**
    * Find all contract steps with pagination ordered by French designation
    */
   @Query("SELECT cs FROM ContractStep cs ORDER BY cs.designationFr ASC")
   Page<ContractStep> findAllOrderByDesignationFr(Pageable pageable);

   /**
    * Find contract step by ID with contract phase loaded
    */
   @Query("SELECT cs FROM ContractStep cs LEFT JOIN FETCH cs.contractPhase WHERE cs.id = :id")
   Optional<ContractStep> findByIdWithPhase(@Param("id") Long id);

   /**
    * Find all contract steps with contract phase loaded
    */
   @Query("SELECT cs FROM ContractStep cs LEFT JOIN FETCH cs.contractPhase ORDER BY cs.designationFr ASC")
   Page<ContractStep> findAllWithPhase(Pageable pageable);

   /**
    * Find contract steps by contract phase ID
    */
   @Query("SELECT cs FROM ContractStep cs WHERE cs.contractPhase.id = :contractPhaseId ORDER BY cs.designationFr ASC")
   Page<ContractStep> findByContractPhaseId(@Param("contractPhaseId") Long contractPhaseId, Pageable pageable);

   /**
    * Count contract steps by contract phase ID
    */
   @Query("SELECT COUNT(cs) FROM ContractStep cs WHERE cs.contractPhase.id = :contractPhaseId")
   Long countByContractPhaseId(@Param("contractPhaseId") Long contractPhaseId);

   /**
    * Search contract steps by any designation field
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "cs.designationAr LIKE %:search% OR " +
          "cs.designationEn LIKE %:search% OR " +
          "cs.designationFr LIKE %:search%")
   Page<ContractStep> searchByDesignation(@Param("search") String search, Pageable pageable);

   /**
    * Search contract steps by any field including contract phase
    */
   @Query("SELECT cs FROM ContractStep cs LEFT JOIN cs.contractPhase cp WHERE " +
          "cs.designationAr LIKE %:search% OR " +
          "cs.designationEn LIKE %:search% OR " +
          "cs.designationFr LIKE %:search% OR " +
          "cp.designationAr LIKE %:search% OR " +
          "cp.designationEn LIKE %:search% OR " +
          "cp.designationFr LIKE %:search%")
   Page<ContractStep> searchByAnyField(@Param("search") String search, Pageable pageable);

   /**
    * Find contract steps by Arabic designation pattern
    */
   @Query("SELECT cs FROM ContractStep cs WHERE cs.designationAr LIKE %:pattern%")
   Page<ContractStep> findByDesignationArContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find contract steps by English designation pattern
    */
   @Query("SELECT cs FROM ContractStep cs WHERE cs.designationEn LIKE %:pattern%")
   Page<ContractStep> findByDesignationEnContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find contract steps by French designation pattern
    */
   @Query("SELECT cs FROM ContractStep cs WHERE cs.designationFr LIKE %:pattern%")
   Page<ContractStep> findByDesignationFrContaining(@Param("pattern") String pattern, Pageable pageable);

   /**
    * Find contract steps by step type based on designation patterns
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "(:stepType = 'DRAFTING' AND (LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%')) OR " +
          "(:stepType = 'VALIDATION' AND (LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%')) OR " +
          "(:stepType = 'VERIFICATION' AND (LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%')) OR " +
          "(:stepType = 'TRANSMISSION' AND (LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%')) OR " +
          "(:stepType = 'RECEPTION' AND (LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%')) OR " +
          "(:stepType = 'ANALYSIS' AND (LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%')) OR " +
          "(:stepType = 'DECISION' AND (LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%')) OR " +
          "(:stepType = 'ARCHIVING' AND (LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%')) " +
          "ORDER BY cs.designationFr ASC")
   Page<ContractStep> findByStepType(@Param("stepType") String stepType, Pageable pageable);

   /**
    * Find administrative contract steps
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' OR " +
          "LOWER(cs.designationFr) LIKE '%vérification%' OR LOWER(cs.designationFr) LIKE '%contrôle%' OR " +
          "LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' OR " +
          "LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ContractStep> findAdministrativeSteps(Pageable pageable);

   /**
    * Find operational contract steps
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%rédaction%' OR LOWER(cs.designationFr) LIKE '%élaboration%' OR " +
          "LOWER(cs.designationFr) LIKE '%analyse%' OR LOWER(cs.designationFr) LIKE '%examen%' OR " +
          "LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ContractStep> findOperationalSteps(Pageable pageable);

   /**
    * Find contract steps by contract phase designation
    */
   @Query("SELECT cs FROM ContractStep cs WHERE cs.contractPhase.designationFr LIKE %:phaseDesignation% ORDER BY cs.designationFr ASC")
   Page<ContractStep> findByContractPhaseDesignation(@Param("phaseDesignation") String phaseDesignation, Pageable pageable);

   /**
    * Count total contract steps
    */
   @Query("SELECT COUNT(cs) FROM ContractStep cs")
   Long countAllContractSteps();

   /**
    * Find contract steps ordered by specific language designation
    */
   @Query("SELECT cs FROM ContractStep cs ORDER BY cs.designationAr ASC")
   Page<ContractStep> findAllOrderByDesignationAr(Pageable pageable);

   @Query("SELECT cs FROM ContractStep cs ORDER BY cs.designationEn ASC")
   Page<ContractStep> findAllOrderByDesignationEn(Pageable pageable);

   /**
    * Find contract steps with multilingual support
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "cs.designationAr IS NOT NULL AND cs.designationAr != '' AND " +
          "cs.designationEn IS NOT NULL AND cs.designationEn != '' AND " +
          "cs.designationFr IS NOT NULL AND cs.designationFr != ''")
   Page<ContractStep> findMultilingualContractSteps(Pageable pageable);

   /**
    * Find contract steps with missing translations
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "cs.designationAr IS NULL OR cs.designationAr = '' OR " +
          "cs.designationEn IS NULL OR cs.designationEn = ''")
   Page<ContractStep> findContractStepsWithMissingTranslations(Pageable pageable);

   /**
    * Find contract steps that require approval
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%validation%' OR LOWER(cs.designationFr) LIKE '%approbation%' OR " +
          "LOWER(cs.designationFr) LIKE '%décision%' OR LOWER(cs.designationFr) LIKE '%choix%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ContractStep> findStepsRequiringApproval(Pageable pageable);

   /**
    * Find contract steps that can be automated
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "LOWER(cs.designationFr) LIKE '%transmission%' OR LOWER(cs.designationFr) LIKE '%envoi%' OR " +
          "LOWER(cs.designationFr) LIKE '%réception%' OR LOWER(cs.designationFr) LIKE '%accusé%' OR " +
          "LOWER(cs.designationFr) LIKE '%archive%' OR LOWER(cs.designationFr) LIKE '%classement%' " +
          "ORDER BY cs.designationFr ASC")
   Page<ContractStep> findAutomatableSteps(Pageable pageable);

   /**
    * Statistics: Count contract steps by type
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
          "FROM ContractStep cs GROUP BY " +
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
   java.util.List<Object[]> countContractStepsByType();

   /**
    * Statistics: Count contract steps by contract phase
    */
   @Query("SELECT cp.designationFr, COUNT(cs) FROM ContractStep cs " +
          "RIGHT JOIN cs.contractPhase cp GROUP BY cp.designationFr ORDER BY COUNT(cs) DESC")
   java.util.List<Object[]> countContractStepsByPhase();

   /**
    * Check for potential duplicate entries (same French designation but different case)
    */
   @Query("SELECT cs FROM ContractStep cs WHERE UPPER(cs.designationFr) = UPPER(:designationFr)")
   Page<ContractStep> findPotentialDuplicatesByDesignationFr(@Param("designationFr") String designationFr, Pageable pageable);

   /**
    * Find contract steps with longest designations (for display optimization)
    */
   @Query("SELECT cs FROM ContractStep cs ORDER BY LENGTH(cs.designationFr) DESC")
   Page<ContractStep> findByLongestDesignation(Pageable pageable);

   /**
    * Find contract steps by contract phase type
    */
   @Query("SELECT cs FROM ContractStep cs WHERE " +
          "(:phaseType = 'PREPARATION' AND (LOWER(cs.contractPhase.designationFr) LIKE '%préparation%' OR LOWER(cs.contractPhase.designationFr) LIKE '%preparation%')) OR " +
          "(:phaseType = 'EVALUATION' AND (LOWER(cs.contractPhase.designationFr) LIKE '%évaluation%' OR LOWER(cs.contractPhase.designationFr) LIKE '%analyse%')) OR " +
          "(:phaseType = 'DECISION' AND (LOWER(cs.contractPhase.designationFr) LIKE '%décision%' OR LOWER(cs.contractPhase.designationFr) LIKE '%adjudication%')) " +
          "ORDER BY cs.designationFr ASC")
   Page<ContractStep> findByContractPhaseType(@Param("phaseType") String phaseType, Pageable pageable);
}