/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.service;

import org.springframework.stereotype.Service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.business.amendment.dto.AmendmentStepDTO;
import dz.mdn.iaas.business.amendment.model.AmendmentStep;
import dz.mdn.iaas.business.amendment.repository.AmendmentStepRepository;

/**
 * AmendmentStep Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class AmendmentStepService extends GenericService<AmendmentStep, AmendmentStepDTO, AmendmentStepRepository> {

    public AmendmentStepService(AmendmentStepRepository repository) {
        super(repository, AmendmentStep.class, AmendmentStepDTO.class);
    }

}
