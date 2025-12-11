/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusionRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Provider
 *	@Package	: Business / Provider / Repository
 *
 **/

package dz.mdn.iaas.business.provider.repository;

import dz.mdn.iaas.business.provider.model.ProviderExclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProviderExclusion Repository
 * ProviderExclusion belongs to Provider and ExclusionType
 */
@Repository
public interface ProviderExclusionRepository extends JpaRepository<ProviderExclusion, Long> {
    
    /**
     * Find provider exclusions by provider ID
     * Used by ProviderExclusionService.getByProviderId()
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.provider.id = :providerId")
    List<ProviderExclusion> findByProviderId(@Param("providerId") Long providerId);
    
    /**
     * Find provider exclusions by exclusion type ID
     * Used by ProviderExclusionService.getByExclusionTypeId()
     */
    @Query("SELECT pe FROM ProviderExclusion pe WHERE pe.exclusionType.id = :exclusionTypeId")
    List<ProviderExclusion> findByExclusionTypeId(@Param("exclusionTypeId") Long exclusionTypeId);
}
