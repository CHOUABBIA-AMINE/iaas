/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationService
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
import dz.mdn.iaas.business.consultation.dto.ConsultationDTO;
import dz.mdn.iaas.business.consultation.model.Consultation;
import dz.mdn.iaas.business.consultation.repository.ConsultationRepository;

/**
 * Consultation Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ConsultationService extends GenericService<Consultation, ConsultationDTO, ConsultationRepository> {

    public ConsultationService(ConsultationRepository repository) {
        super(repository, Consultation.class, ConsultationDTO.class);
    }

}
