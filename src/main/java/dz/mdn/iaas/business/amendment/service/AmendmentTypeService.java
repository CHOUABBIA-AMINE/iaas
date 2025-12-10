/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentTypeService
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
import dz.mdn.iaas.business.amendment.dto.AmendmentTypeDTO;
import dz.mdn.iaas.business.amendment.model.AmendmentType;
import dz.mdn.iaas.business.amendment.repository.AmendmentTypeRepository;

/**
 * AmendmentType Service with CRUD operations
 * Extends GenericService for automatic CRUD inheritance
 * All standard CRUD methods (create, read, update, delete, search) inherited from base class
 * Only custom business logic methods are defined here
 */
@Service
public class AmendmentTypeService extends GenericService<AmendmentType, AmendmentTypeDTO, AmendmentTypeRepository> {

    public AmendmentTypeService(AmendmentTypeRepository repository) {
        super(repository, AmendmentType.class, AmendmentTypeDTO.class);
    }

    // ========== ALL CRUD METHODS INHERITED FROM GenericService ==========
    
    /*
     * Inherited methods (no need to redefine):
     * 
     * CREATE:
     * - create(AmendmentTypeDTO dto)
     * 
     * READ:
     * - getById(Long id)
     * - getEntityById(Long id)
     * - findOne(Long id)
     * - getAll(Pageable pageable)
     * - search(String term, Pageable pageable)
     * 
     * UPDATE:
     * - update(Long id, AmendmentTypeDTO dto)
     * 
     * DELETE:
     * - delete(Long id)
     * - deleteById(Long id)
     * 
     * UTILITY:
     * - existsById(Long id)
     * - getTotalCount()
     * 
     * Plus 15+ more methods from GenericService
     */

    // ========== CUSTOM BUSINESS LOGIC (IF ANY) ==========
    
    // Add any AmendmentType-specific business logic methods here
    // All generic CRUD is handled by GenericService automatically
    
}
