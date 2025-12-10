/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetTypeService
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
import dz.mdn.iaas.business.plan.dto.BudgetTypeDTO;
import dz.mdn.iaas.business.plan.model.BudgetType;
import dz.mdn.iaas.business.plan.repository.BudgetTypeRepository;

/**
 * BudgetType Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class BudgetTypeService extends GenericService<BudgetType, BudgetTypeDTO, BudgetTypeRepository> {

    public BudgetTypeService(BudgetTypeRepository repository) {
        super(repository, BudgetType.class, BudgetTypeDTO.class);
    }

}
