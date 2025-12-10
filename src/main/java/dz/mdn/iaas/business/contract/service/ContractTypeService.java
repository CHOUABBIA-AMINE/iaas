/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractTypeService
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
import dz.mdn.iaas.business.contract.dto.ContractTypeDTO;
import dz.mdn.iaas.business.contract.model.ContractType;
import dz.mdn.iaas.business.contract.repository.ContractTypeRepository;

/**
 * ContractType Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ContractTypeService extends GenericService<ContractType, ContractTypeDTO, ContractTypeRepository> {

    public ContractTypeService(ContractTypeRepository repository) {
        super(repository, ContractType.class, ContractTypeDTO.class);
    }

}
