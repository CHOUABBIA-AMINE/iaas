/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemStatusService
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
import dz.mdn.iaas.business.plan.dto.ItemStatusDTO;
import dz.mdn.iaas.business.plan.model.ItemStatus;
import dz.mdn.iaas.business.plan.repository.ItemStatusRepository;

/**
 * ItemStatus Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ItemStatusService extends GenericService<ItemStatus, ItemStatusDTO, ItemStatusRepository> {

    public ItemStatusService(ItemStatusRepository repository) {
        super(repository, ItemStatus.class, ItemStatusDTO.class);
    }

}
