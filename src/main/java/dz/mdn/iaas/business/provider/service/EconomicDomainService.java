/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicDomainService
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
import dz.mdn.iaas.business.provider.dto.EconomicDomainDTO;
import dz.mdn.iaas.business.provider.model.EconomicDomain;
import dz.mdn.iaas.business.provider.repository.EconomicDomainRepository;

/**
 * EconomicDomain Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class EconomicDomainService extends GenericService<EconomicDomain, EconomicDomainDTO, EconomicDomainRepository> {

    public EconomicDomainService(EconomicDomainRepository repository) {
        super(repository, EconomicDomain.class, EconomicDomainDTO.class);
    }

}
