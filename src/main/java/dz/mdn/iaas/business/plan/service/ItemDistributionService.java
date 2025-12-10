/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionService
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
import dz.mdn.iaas.business.plan.dto.ItemDistributionDTO;
import dz.mdn.iaas.business.plan.model.ItemDistribution;
import dz.mdn.iaas.business.plan.repository.ItemDistributionRepository;

/**
 * ItemDistribution Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ItemDistributionService extends GenericService<ItemDistribution, ItemDistributionDTO, ItemDistributionRepository> {

    public ItemDistributionService(ItemDistributionRepository repository) {
        super(repository, ItemDistribution.class, ItemDistributionDTO.class);
    }

}
