/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentService
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
import dz.mdn.iaas.business.amendment.dto.AmendmentDTO;
import dz.mdn.iaas.business.amendment.model.Amendment;
import dz.mdn.iaas.business.amendment.repository.AmendmentRepository;

/**
 * Amendment Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class AmendmentService extends GenericService<Amendment, AmendmentDTO, AmendmentRepository> {

    public AmendmentService(AmendmentRepository repository) {
        super(repository, Amendment.class, AmendmentDTO.class);
    }

}
