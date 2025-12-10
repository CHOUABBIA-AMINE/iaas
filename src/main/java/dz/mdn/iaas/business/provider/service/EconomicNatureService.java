/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicNatureService
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
import dz.mdn.iaas.business.provider.dto.EconomicNatureDTO;
import dz.mdn.iaas.business.provider.model.EconomicNature;
import dz.mdn.iaas.business.provider.repository.EconomicNatureRepository;

/**
 * EconomicNature Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class EconomicNatureService extends GenericService<EconomicNature, EconomicNatureDTO, EconomicNatureRepository> {

    public EconomicNatureService(EconomicNatureRepository repository) {
        super(repository, EconomicNature.class, EconomicNatureDTO.class);
    }

}
