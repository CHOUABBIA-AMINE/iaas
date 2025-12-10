/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RubricService
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
import dz.mdn.iaas.business.plan.dto.RubricDTO;
import dz.mdn.iaas.business.plan.model.Rubric;
import dz.mdn.iaas.business.plan.repository.RubricRepository;

/**
 * Rubric Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class RubricService extends GenericService<Rubric, RubricDTO, RubricRepository> {

    public RubricService(RubricRepository repository) {
        super(repository, Rubric.class, RubricDTO.class);
    }

}
