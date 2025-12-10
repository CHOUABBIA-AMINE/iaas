/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ExclusionTypeService
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
import dz.mdn.iaas.business.provider.dto.ExclusionTypeDTO;
import dz.mdn.iaas.business.provider.model.ExclusionType;
import dz.mdn.iaas.business.provider.repository.ExclusionTypeRepository;

/**
 * ExclusionType Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ExclusionTypeService extends GenericService<ExclusionType, ExclusionTypeDTO, ExclusionTypeRepository> {

    public ExclusionTypeService(ExclusionTypeRepository repository) {
        super(repository, ExclusionType.class, ExclusionTypeDTO.class);
    }

}
