/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentatorRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Repository
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.ProviderRepresentator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProviderRepresentator Repository
 * ProviderRepresentator belongs to Provider (many-to-one)
 */
@Repository
public interface ProviderRepresentatorRepository extends JpaRepository<ProviderRepresentator, Long> {
    
    /**
     * Find provider representators by provider ID
     * Used by ProviderRepresentatorService.getByProviderId()
     */
    @Query("SELECT pr FROM ProviderRepresentator pr WHERE pr.provider.id = :providerId")
    List<ProviderRepresentator> findByProviderId(@Param("providerId") Long providerId);
}
