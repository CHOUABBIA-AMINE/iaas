/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DomainService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.service;

import org.springframework.stereotype.Service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.business.plan.dto.DomainDTO;
import dz.mdn.iaas.business.plan.model.Domain;
import dz.mdn.iaas.business.plan.repository.DomainRepository;

/**
 * Domain Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class DomainService extends GenericService<Domain, DomainDTO, DomainRepository> {

    public DomainService(DomainRepository repository) {
        super(repository, Domain.class, DomainDTO.class);
    }

}
