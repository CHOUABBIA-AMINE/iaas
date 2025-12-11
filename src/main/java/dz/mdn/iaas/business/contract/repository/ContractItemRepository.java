/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItemRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Contract
 *	@Package	: Business / Contract / Repository
 *
 **/

package dz.mdn.iaas.business.contract.repository;

import dz.mdn.iaas.business.contract.model.ContractItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ContractItem Repository
 * ContractItem belongs to Contract
 */
@Repository
public interface ContractItemRepository extends JpaRepository<ContractItem, Long> {
    
    /**
     * Find contract items by contract ID
     * Used by ContractItemService.getByContractId()
     */
    @Query("SELECT ci FROM ContractItem ci WHERE ci.contract.id = :contractId")
    List<ContractItem> findByContractId(@Param("contractId") Long contractId);
}
