/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationService
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
import dz.mdn.iaas.business.plan.dto.BudgetModificationDTO;
import dz.mdn.iaas.business.plan.model.BudgetModification;
import dz.mdn.iaas.business.plan.repository.BudgetModificationRepository;

/**
 * BudgetModification Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class BudgetModificationService extends GenericService<BudgetModification, BudgetModificationDTO, BudgetModificationRepository> {

    public BudgetModificationService(BudgetModificationRepository repository) {
        super(repository, BudgetModification.class, BudgetModificationDTO.class);
    }

}
