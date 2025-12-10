/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ClearanceService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.service;

import org.springframework.stereotype.Service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.business.provider.dto.ClearanceDTO;
import dz.mdn.iaas.business.provider.model.Clearance;
import dz.mdn.iaas.business.provider.repository.ClearanceRepository;

/**
 * Clearance Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ClearanceService extends GenericService<Clearance, ClearanceDTO, ClearanceRepository> {

    public ClearanceService(ClearanceRepository repository) {
        super(repository, Clearance.class, ClearanceDTO.class);
    }

}
