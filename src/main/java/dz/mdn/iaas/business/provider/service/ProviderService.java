/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderService
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
import dz.mdn.iaas.business.provider.dto.ProviderDTO;
import dz.mdn.iaas.business.provider.model.Provider;
import dz.mdn.iaas.business.provider.repository.ProviderRepository;

/**
 * Provider Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ProviderService extends GenericService<Provider, ProviderDTO, ProviderRepository> {

    public ProviderService(ProviderRepository repository) {
        super(repository, Provider.class, ProviderDTO.class);
    }

}
