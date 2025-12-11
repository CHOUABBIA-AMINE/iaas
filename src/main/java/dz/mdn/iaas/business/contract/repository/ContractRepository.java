/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Contract
 *	@Package	: Business / Contract / Repository
 *
 **/

package dz.mdn.iaas.business.contract.repository;

import dz.mdn.iaas.business.contract.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contract Repository
 * Contract belongs to Consultation
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    
    /**
     * Find contracts by consultation ID
     * Used by ContractService.getByConsultationId()
     */
    @Query("SELECT c FROM Contract c WHERE c.consultation.id = :consultationId")
    List<Contract> findByConsultationId(@Param("consultationId") Long consultationId);
}
