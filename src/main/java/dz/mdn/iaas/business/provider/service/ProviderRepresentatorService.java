/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentatorService
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
import dz.mdn.iaas.business.provider.dto.ProviderRepresentatorDTO;
import dz.mdn.iaas.business.provider.model.ProviderRepresentator;
import dz.mdn.iaas.business.provider.repository.ProviderRepresentatorRepository;

/**
 * ProviderRepresentator Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ProviderRepresentatorService extends GenericService<ProviderRepresentator, ProviderRepresentatorDTO, ProviderRepresentatorRepository> {

    public ProviderRepresentatorService(ProviderRepresentatorRepository repository) {
        super(repository, ProviderRepresentator.class, ProviderRepresentatorDTO.class);
    }

}
