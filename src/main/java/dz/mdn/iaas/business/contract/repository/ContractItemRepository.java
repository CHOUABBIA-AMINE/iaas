/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItemRepository
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Interface
 *	@Layer		: Repository
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.repository;

import dz.mdn.iaas.business.contract.model.ContractItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ContractItem Repository with extended query capabilities
 * Field mapping:
 * F_00=id, F_01=designation, F_02=reference, F_03=quantity, F_04=unitPrice, F_05=observation, F_06=contract
 */
@Repository
public interface ContractItemRepository extends JpaRepository<ContractItem, Long> {

    /**
     * Find by reference (F_02)
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.reference = :reference")
    Optional<ContractItem> findByReference(@Param("reference") String reference);

    /**
     * Check if reference exists for given contract
     */
    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END FROM ContractItem ci WHERE ci.reference = :reference AND ci.contract.id = :contractId")
    boolean existsByReferenceAndContractId(@Param("reference") String reference, @Param("contractId") Long contractId);

    /**
     * Check if reference exists excluding current item ID (for update)
     */
    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END FROM ContractItem ci WHERE ci.reference = :reference AND ci.contract.id = :contractId AND ci.id != :id")
    boolean existsByReferenceAndContractIdAndIdNot(@Param("reference") String reference, @Param("contractId") Long contractId, @Param("id") Long id);

    /**
     * Find all items by contract ID
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.contract.id = :contractId ORDER BY ci.designation ASC")
    Page<ContractItem> findByContractId(@Param("contractId") Long contractId, Pageable pageable);

    /**
     * Search items by designation or reference
     */
    @Query("SELECT ci FROM ContractItem ci WHERE " +
           "LOWER(ci.designation) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(ci.reference) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<ContractItem> searchByDesignationOrReference(@Param("search") String search, Pageable pageable);

    /**
     * Find items by observation keyword
     */
    @Query("SELECT ci FROM ContractItem ci WHERE LOWER(ci.observation) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<ContractItem> findByObservationContaining(@Param("keyword") String keyword, Pageable pageable);

    /**
     * Find items with quantity greater than a value
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.quantity > :minQuantity ORDER BY ci.quantity DESC")
    Page<ContractItem> findByQuantityGreaterThan(@Param("minQuantity") double minQuantity, Pageable pageable);

    /**
     * Find items with unit price within range
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.unitPrice BETWEEN :minPrice AND :maxPrice ORDER BY ci.unitPrice ASC")
    Page<ContractItem> findByUnitPriceBetween(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice, Pageable pageable);

    /**
     * Find items with total price greater than a given threshold
     */
    @Query("SELECT ci FROM ContractItem ci WHERE (ci.quantity * ci.unitPrice) >= :minTotal")
    Page<ContractItem> findByTotalPriceGreaterThan(@Param("minTotal") double minTotal, Pageable pageable);

    /**
     * Find items without observation (null or empty)
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.observation IS NULL OR ci.observation = ''")
    Page<ContractItem> findWithoutObservation(Pageable pageable);

    /**
     * Find items with observation
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.observation IS NOT NULL AND ci.observation != ''")
    Page<ContractItem> findWithObservation(Pageable pageable);

    /**
     * Count total items by contract
     */
    @Query("SELECT COUNT(ci) FROM ContractItem ci WHERE ci.contract.id = :contractId")
    long countByContractId(@Param("contractId") Long contractId);

    /**
     * Count items with total price above given threshold
     */
    @Query("SELECT COUNT(ci) FROM ContractItem ci WHERE (ci.quantity * ci.unitPrice) >= :minTotal")
    long countByTotalPriceGreaterThan(@Param("minTotal") double minTotal);

    /**
     * Calculate total quantity for a contract
     */
    @Query("SELECT SUM(ci.quantity) FROM ContractItem ci WHERE ci.contract.id = :contractId")
    Double sumQuantitiesByContractId(@Param("contractId") Long contractId);

    /**
     * Calculate total value (sum of quantity * unitPrice) for a contract
     */
    @Query("SELECT SUM(ci.quantity * ci.unitPrice) FROM ContractItem ci WHERE ci.contract.id = :contractId")
    Double sumTotalValueByContractId(@Param("contractId") Long contractId);

    /**
     * Get average unit price for a contract
     */
    @Query("SELECT AVG(ci.unitPrice) FROM ContractItem ci WHERE ci.contract.id = :contractId")
    Double averageUnitPriceByContractId(@Param("contractId") Long contractId);

    /**
     * Find top expensive items (highest unitPrice)
     */
    @Query("SELECT ci FROM ContractItem ci ORDER BY ci.unitPrice DESC")
    Page<ContractItem> findTopExpensiveItems(Pageable pageable);

    /**
     * Find top valued items (highest total = quantity * unitPrice)
     */
    @Query("SELECT ci FROM ContractItem ci ORDER BY (ci.quantity * ci.unitPrice) DESC")
    Page<ContractItem> findTopValuedItems(Pageable pageable);

    /**
     * Find duplicate references across contracts
     */
    @Query("SELECT ci.reference FROM ContractItem ci GROUP BY ci.reference HAVING COUNT(ci.reference) > 1")
    java.util.List<String> findDuplicateReferences();

    /**
     * Find all items by designation (case-insensitive)
     */
    @Query("SELECT ci FROM ContractItem ci WHERE LOWER(ci.designation) = LOWER(:designation)")
    Page<ContractItem> findByExactDesignation(@Param("designation") String designation, Pageable pageable);

    /**
     * Find all contract items belonging to multiple contracts
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.contract.id IN :contractIds ORDER BY ci.contract.id ASC, ci.reference ASC")
    Page<ContractItem> findByContractIds(@Param("contractIds") java.util.List<Long> contractIds, Pageable pageable);
    
    // ========== FINDERS ==========

 	boolean existsByReference(String reference);

 	// ========== STATISTICS ==========

 	@Query("SELECT SUM(i.quantity) FROM ContractItem i WHERE i.contract.id = :contractId")
 	Optional<Double> sumQuantityByContractId(@Param("contractId") Long contractId);


 	// ========== SEARCH ==========

 	@Query("""
 		SELECT i FROM ContractItem i
 		WHERE LOWER(i.designation) LIKE LOWER(CONCAT('%', :keyword, '%'))
 		   OR LOWER(i.observation) LIKE LOWER(CONCAT('%', :keyword, '%'))
 		   OR LOWER(i.reference) LIKE LOWER(CONCAT('%', :keyword, '%'))
 	""")
 	Page<ContractItem> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
