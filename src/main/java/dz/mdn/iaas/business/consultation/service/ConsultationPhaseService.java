/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationPhaseService
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
import dz.mdn.iaas.business.consultation.dto.ConsultationPhaseDTO;
import dz.mdn.iaas.business.consultation.model.ConsultationPhase;
import dz.mdn.iaas.business.consultation.repository.ConsultationPhaseRepository;

/**
 * ConsultationPhase Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class ConsultationPhaseService extends GenericService<ConsultationPhase, ConsultationPhaseDTO, ConsultationPhaseRepository> {

    public ConsultationPhaseService(ConsultationPhaseRepository repository) {
        super(repository, ConsultationPhase.class, ConsultationPhaseDTO.class);
    }

}
