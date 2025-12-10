/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItemService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.service;

import org.springframework.stereotype.Service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.business.contract.dto.ContractItemDTO;
import dz.mdn.iaas.business.contract.model.ContractItem;
import dz.mdn.iaas.business.contract.repository.ContractItemRepository;

/**
 * ContractItem Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ContractItemService extends GenericService<ContractItem, ContractItemDTO, ContractItemRepository> {

    public ContractItemService(ContractItemRepository repository) {
        super(repository, ContractItem.class, ContractItemDTO.class);
    }

}
