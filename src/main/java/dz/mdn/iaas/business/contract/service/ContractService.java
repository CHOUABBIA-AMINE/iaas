/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractService
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
import dz.mdn.iaas.business.contract.dto.ContractDTO;
import dz.mdn.iaas.business.contract.model.Contract;
import dz.mdn.iaas.business.contract.repository.ContractRepository;

/**
 * Contract Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ContractService extends GenericService<Contract, ContractDTO, ContractRepository> {

    public ContractService(ContractRepository repository) {
        super(repository, Contract.class, ContractDTO.class);
    }

}
