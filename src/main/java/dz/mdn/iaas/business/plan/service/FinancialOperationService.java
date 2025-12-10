/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperationService
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
import dz.mdn.iaas.business.plan.dto.FinancialOperationDTO;
import dz.mdn.iaas.business.plan.model.FinancialOperation;
import dz.mdn.iaas.business.plan.repository.FinancialOperationRepository;

/**
 * FinancialOperation Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class FinancialOperationService extends GenericService<FinancialOperation, FinancialOperationDTO, FinancialOperationRepository> {

    public FinancialOperationService(FinancialOperationRepository repository) {
        super(repository, FinancialOperation.class, FinancialOperationDTO.class);
    }

}
