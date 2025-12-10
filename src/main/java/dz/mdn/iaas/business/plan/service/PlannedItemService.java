/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemService
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
import dz.mdn.iaas.business.plan.dto.PlannedItemDTO;
import dz.mdn.iaas.business.plan.model.PlannedItem;
import dz.mdn.iaas.business.plan.repository.PlannedItemRepository;

/**
 * PlannedItem Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class PlannedItemService extends GenericService<PlannedItem, PlannedItemDTO, PlannedItemRepository> {

    public PlannedItemService(PlannedItemRepository repository) {
        super(repository, PlannedItem.class, PlannedItemDTO.class);
    }

}
