/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderExclusionService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.service;

import org.springframework.stereotype.Service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.business.provider.dto.ProviderExclusionDTO;
import dz.mdn.iaas.business.provider.model.ProviderExclusion;
import dz.mdn.iaas.business.provider.repository.ProviderExclusionRepository;

/**
 * ProviderExclusion Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ProviderExclusionService extends GenericService<ProviderExclusion, ProviderExclusionDTO, ProviderExclusionRepository> {

    public ProviderExclusionService(ProviderExclusionRepository repository) {
        super(repository, ProviderExclusion.class, ProviderExclusionDTO.class);
    }

}
