/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentPhaseService
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
import dz.mdn.iaas.business.amendment.dto.AmendmentPhaseDTO;
import dz.mdn.iaas.business.amendment.model.AmendmentPhase;
import dz.mdn.iaas.business.amendment.repository.AmendmentPhaseRepository;

/**
 * AmendmentPhase Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class AmendmentPhaseService extends GenericService<AmendmentPhase, AmendmentPhaseDTO, AmendmentPhaseRepository> {

    public AmendmentPhaseService(AmendmentPhaseRepository repository) {
        super(repository, AmendmentPhase.class, AmendmentPhaseDTO.class);
    }

}
