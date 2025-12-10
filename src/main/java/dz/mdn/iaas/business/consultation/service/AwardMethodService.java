/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AwardMethodService
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.service;

import org.springframework.stereotype.Service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.business.consultation.dto.AwardMethodDTO;
import dz.mdn.iaas.business.consultation.model.AwardMethod;
import dz.mdn.iaas.business.consultation.repository.AwardMethodRepository;

/**
 * AwardMethod Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class AwardMethodService extends GenericService<AwardMethod, AwardMethodDTO, AwardMethodRepository> {

    public AwardMethodService(AwardMethodRepository repository) {
        super(repository, AwardMethod.class, AwardMethodDTO.class);
    }

}
