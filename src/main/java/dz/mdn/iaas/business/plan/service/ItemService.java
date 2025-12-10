/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemService
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
import dz.mdn.iaas.business.plan.dto.ItemDTO;
import dz.mdn.iaas.business.plan.model.Item;
import dz.mdn.iaas.business.plan.repository.ItemRepository;

/**
 * Item Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ItemService extends GenericService<Item, ItemDTO, ItemRepository> {

    public ItemService(ItemRepository repository) {
        super(repository, Item.class, ItemDTO.class);
    }

}
