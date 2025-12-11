/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Repository
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.Clearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clearance Repository
 * Clearance belongs to Provider
 */
@Repository
public interface ClearanceRepository extends JpaRepository<Clearance, Long> {
    
    /**
     * Find clearances by provider ID
     * Used by ClearanceService.getByProviderId()
     */
    @Query("SELECT c FROM Clearance c WHERE c.provider.id = :providerId")
    List<Clearance> findByProviderId(@Param("providerId") Long providerId);
}
