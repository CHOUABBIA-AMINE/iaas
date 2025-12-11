/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Amendment
 *	@Package	: Business / Amendment / Repository
 *
 **/

package dz.mdn.iaas.business.amendment.repository;

import dz.mdn.iaas.business.amendment.model.Amendment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Amendment Repository
 * Amendment belongs to Contract
 */
@Repository
public interface AmendmentRepository extends JpaRepository<Amendment, Long> {
    
    /**
     * Find amendments by contract ID
     * Used by AmendmentService.getByContractId()
     */
    @Query("SELECT a FROM Amendment a WHERE a.contract.id = :contractId")
    List<Amendment> findByContractId(@Param("contractId") Long contractId);
}
