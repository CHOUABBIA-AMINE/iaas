/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionService
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
import dz.mdn.iaas.business.consultation.dto.SubmissionDTO;
import dz.mdn.iaas.business.consultation.model.Submission;
import dz.mdn.iaas.business.consultation.repository.SubmissionRepository;

/**
 * Submission Service
 * Extends GenericService for automatic CRUD inheritance
 */
@Service
public class SubmissionService extends GenericService<Submission, SubmissionDTO, SubmissionRepository> {

    public SubmissionService(SubmissionRepository repository) {
        super(repository, Submission.class, SubmissionDTO.class);
    }

}
