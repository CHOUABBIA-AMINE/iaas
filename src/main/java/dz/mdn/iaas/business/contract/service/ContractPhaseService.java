/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractPhaseService
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
import dz.mdn.iaas.business.contract.dto.ContractPhaseDTO;
import dz.mdn.iaas.business.contract.model.ContractPhase;
import dz.mdn.iaas.business.contract.repository.ContractPhaseRepository;

/**
 * ContractPhase Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ContractPhaseService extends GenericService<ContractPhase, ContractPhaseDTO, ContractPhaseRepository> {

    public ContractPhaseService(ContractPhaseRepository repository) {
        super(repository, ContractPhase.class, ContractPhaseDTO.class);
    }

}
