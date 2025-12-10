/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStepService
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
import dz.mdn.iaas.business.contract.dto.ContractStepDTO;
import dz.mdn.iaas.business.contract.model.ContractStep;
import dz.mdn.iaas.business.contract.repository.ContractStepRepository;

/**
 * ContractStep Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ContractStepService extends GenericService<ContractStep, ContractStepDTO, ContractStepRepository> {

    public ContractStepService(ContractStepRepository repository) {
        super(repository, ContractStep.class, ContractStepDTO.class);
    }

}
