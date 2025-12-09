/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentService
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layaer		: Service
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.amendment.dto.AmendmentDTO;
import dz.mdn.iaas.business.amendment.model.Amendment;
import dz.mdn.iaas.business.amendment.repository.AmendmentRepository;
import dz.mdn.iaas.business.amendment.repository.AmendmentTypeRepository;
import dz.mdn.iaas.business.amendment.repository.AmendmentPhaseRepository;
import dz.mdn.iaas.business.contract.repository.ContractRepository;
import dz.mdn.iaas.business.core.repository.ApprovalStatusRepository;
import dz.mdn.iaas.business.core.repository.CurrencyRepository;
import dz.mdn.iaas.business.core.repository.ProcurementStatusRepository;
import dz.mdn.iaas.common.communication.repository.MailRepository;
import dz.mdn.iaas.common.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AmendmentService {

	private final AmendmentRepository amendmentRepository;

	// Foreign key repositories
	private final ContractRepository contractRepository;
	private final AmendmentTypeRepository amendmentTypeRepository;
	private final ProcurementStatusRepository procurementStatusRepository;
	private final AmendmentPhaseRepository amendmentPhaseRepository;
	private final ApprovalStatusRepository approvalStatusRepository;
	private final CurrencyRepository currencyRepository;
	private final DocumentRepository documentRepository;
	private final MailRepository mailRepository;

	// ========== CREATE ==========

	public AmendmentDTO createAmendment(AmendmentDTO dto) {
		log.info("Creating amendment with internalId: {}", dto.getInternalId());

		validateRequiredFields(dto, "create");
		validateUniqueConstraints(dto, null);

		Amendment entity = new Amendment();
		mapDtoToEntity(dto, entity);
		setEntityRelationships(dto, entity);

		Amendment saved = amendmentRepository.save(entity);
		handleManyToManyRelationships(dto, saved);

		log.info("Successfully created amendment ID: {}", saved.getId());
		return AmendmentDTO.fromEntityWithRelations(saved);
	}

	// ========== READ ==========

	@Transactional(readOnly = true)
	public AmendmentDTO getAmendmentById(Long id) {
		log.debug("Fetching amendment with ID: {}", id);
		Amendment amendment = amendmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Amendment not found with ID: " + id));
		return AmendmentDTO.fromEntityWithRelations(amendment);
	}

	@Transactional(readOnly = true)
	public Optional<AmendmentDTO> findByInternalId(Integer internalId) {
		return amendmentRepository.findByInternalId(internalId)
				.map(AmendmentDTO::fromEntityWithRelations);
	}

	@Transactional(readOnly = true)
	public Page<AmendmentDTO> getAllAmendments(Pageable pageable) {
		return amendmentRepository.findAll(pageable)
				.map(AmendmentDTO::fromEntity);
	}

	@Transactional(readOnly = true)
	public Page<AmendmentDTO> searchAmendments(String searchTerm, Pageable pageable) {
		if (searchTerm == null || searchTerm.isBlank())
			return getAllAmendments(pageable);
		return amendmentRepository.searchByDesignation(searchTerm.trim(), pageable)
				.map(AmendmentDTO::fromEntity);
	}

	// ========== UPDATE ==========

	public AmendmentDTO updateAmendment(Long id, AmendmentDTO dto) {
		log.info("Updating amendment with ID: {}", id);

		Amendment existing = amendmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Amendment not found with ID: " + id));

		validateRequiredFields(dto, "update");
		validateUniqueConstraints(dto, id);

		mapDtoToEntity(dto, existing);
		setEntityRelationships(dto, existing);

		Amendment updated = amendmentRepository.save(existing);
		handleManyToManyRelationships(dto, updated);

		log.info("Successfully updated amendment ID: {}", id);
		return AmendmentDTO.fromEntityWithRelations(updated);
	}

	// ========== DELETE ==========

	public void deleteAmendment(Long id) {
		log.info("Deleting amendment ID: {}", id);
		if (!amendmentRepository.existsById(id))
			throw new RuntimeException("Amendment not found with ID: " + id);
		amendmentRepository.deleteById(id);
	}

	// ========== HELPERS ==========

	private void mapDtoToEntity(AmendmentDTO dto, Amendment entity) {
		entity.setInternalId(dto.getInternalId());
		entity.setReference(dto.getReference());
		entity.setDesignationAr(dto.getDesignationAr());
		entity.setDesignationEn(dto.getDesignationEn());
		entity.setDesignationFr(dto.getDesignationFr());
		entity.setAmount(dto.getAmount());
		entity.setTransferableAmount(dto.getTransferableAmount());
		entity.setStartDate(dto.getStartDate());
		entity.setApprovalDate(dto.getApprovalDate());
		entity.setNotifyDate(dto.getNotifyDate());
		entity.setObservation(dto.getObservation());
	}

	private void setEntityRelationships(AmendmentDTO dto, Amendment entity) {
		entity.setContract(contractRepository.findById(dto.getContractId())
				.orElseThrow(() -> new RuntimeException("Contract not found")));

		entity.setAmendmentType(amendmentTypeRepository.findById(dto.getAmendmentTypeId())
				.orElseThrow(() -> new RuntimeException("AmendmentType not found")));

		entity.setProcurementStatus(procurementStatusRepository.findById(dto.getProcurementStatusId())
				.orElseThrow(() -> new RuntimeException("ProcurementStatus not found")));

		entity.setAmendmentStep(amendmentPhaseRepository.findById(dto.getAmendmentStepId())
				.orElseThrow(() -> new RuntimeException("AmendmentStep not found")));

		if (dto.getApprovalStatusId() != null) {
			entity.setApprovalStatus(approvalStatusRepository.findById(dto.getApprovalStatusId())
					.orElseThrow(() -> new RuntimeException("ApprovalStatus not found")));
		} else {
			entity.setApprovalStatus(null);
		}

		entity.setCurrency(currencyRepository.findById(dto.getCurrencyId())
				.orElseThrow(() -> new RuntimeException("Currency not found")));
	}

	private void handleManyToManyRelationships(AmendmentDTO dto, Amendment entity) {
		if (dto.getDocumentIds() != null) {
			entity.setDocuments(documentRepository.findAllById(dto.getDocumentIds()));
		}
		if (dto.getReferencedMailIds() != null) {
			entity.setReferencedMails(mailRepository.findAllById(dto.getReferencedMailIds()));
		}
	}

	private void validateRequiredFields(AmendmentDTO dto, String operation) {
		if (dto.getInternalId() == null) {
			throw new RuntimeException("Internal ID is required for " + operation);
		}
		if (dto.getDesignationFr() == null || dto.getDesignationFr().isBlank()) {
			throw new RuntimeException("French designation is required for " + operation);
		}
		if (dto.getContractId() == null) {
			throw new RuntimeException("Contract is required for " + operation);
		}
		if (dto.getAmendmentTypeId() == null) {
			throw new RuntimeException("Amendment Type is required for " + operation);
		}
		if (dto.getProcurementStatusId() == null) {
			throw new RuntimeException("Procurement Status is required for " + operation);
		}
		if (dto.getAmendmentStepId() == null) {
			throw new RuntimeException("Amendment Step is required for " + operation);
		}
		if (dto.getCurrencyId() == null) {
			throw new RuntimeException("Currency is required for " + operation);
		}
	}

	private void validateUniqueConstraints(AmendmentDTO dto, Long excludeId) {
		if (dto.getReference() != null && !dto.getReference().isBlank()) {
			boolean exists;
			if (excludeId == null)
				exists = amendmentRepository.existsByReference(dto.getReference());
			else
				exists = amendmentRepository.existsByReferenceAndIdNot(dto.getReference(), excludeId);
			if (exists)
				throw new RuntimeException("Amendment with reference '" + dto.getReference() + "' already exists");
		}
	}
}
