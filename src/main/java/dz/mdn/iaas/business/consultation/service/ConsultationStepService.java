/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStepService
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
import dz.mdn.iaas.business.consultation.dto.ConsultationStepDTO;
import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import dz.mdn.iaas.business.consultation.repository.ConsultationStepRepository;

/**
 * ConsultationStep Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ConsultationStepService extends GenericService<ConsultationStep, ConsultationStepDTO, ConsultationStepRepository> {

    public ConsultationStepService(ConsultationStepRepository repository) {
        super(repository, ConsultationStep.class, ConsultationStepDTO.class);
    }

}
