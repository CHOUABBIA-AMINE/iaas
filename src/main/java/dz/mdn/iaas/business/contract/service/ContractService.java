/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractService
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.consultation.repository.ConsultationRepository;
import dz.mdn.iaas.business.contract.dto.ContractDTO;
import dz.mdn.iaas.business.contract.model.Contract;
import dz.mdn.iaas.business.contract.repository.ContractRepository;
import dz.mdn.iaas.business.contract.repository.ContractStepRepository;
import dz.mdn.iaas.business.contract.repository.ContractTypeRepository;
import dz.mdn.iaas.business.core.repository.ApprovalStatusRepository;
import dz.mdn.iaas.business.core.repository.CurrencyRepository;
import dz.mdn.iaas.business.core.repository.ProcurementStatusRepository;
import dz.mdn.iaas.business.plan.repository.PlannedItemRepository;
import dz.mdn.iaas.business.provider.repository.ProviderRepository;
import dz.mdn.iaas.common.communication.repository.MailRepository;
import dz.mdn.iaas.common.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContractService {

	private final ContractRepository contractRepository;

	// Foreign key repositories
	private final ContractTypeRepository contractTypeRepository;
	private final ProviderRepository providerRepository;
	private final ProcurementStatusRepository procurementStatusRepository;
	private final ContractStepRepository contractStepRepository;
	private final ApprovalStatusRepository approvalStatusRepository;
	private final CurrencyRepository currencyRepository;
	private final ConsultationRepository consultationRepository;
	private final DocumentRepository documentRepository;
	private final MailRepository mailRepository;
	private final PlannedItemRepository plannedItemRepository;

	// ========== CREATE ==========

	public ContractDTO createContract(ContractDTO dto) {
		log.info("Creating contract with internalId: {}", dto.getInternalId());

		validateRequiredFields(dto, "create");
		validateUniqueConstraints(dto, null);

		Contract entity = new Contract();
		mapDtoToEntity(dto, entity);
		setEntityRelationships(dto, entity);

		Contract saved = contractRepository.save(entity);
		handleManyToManyRelationships(dto, saved);

		log.info("Successfully created contract ID: {}", saved.getId());
		return ContractDTO.fromEntityWithRelations(saved);
	}

	// ========== READ ==========

	@Transactional(readOnly = true)
	public ContractDTO getContractById(Long id) {
		log.debug("Fetching contract with ID: {}", id);
		Contract contract = contractRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));
		return ContractDTO.fromEntityWithRelations(contract);
	}

	@Transactional(readOnly = true)
	public Optional<ContractDTO> findByInternalId(String internalId) {
		return contractRepository.findByInternalId(internalId)
				.map(ContractDTO::fromEntityWithRelations);
	}

	@Transactional(readOnly = true)
	public Page<ContractDTO> getAllContracts(Pageable pageable) {
		return contractRepository.findAllOrderByContractDate(pageable)
				.map(ContractDTO::fromEntity);
	}

	@Transactional(readOnly = true)
	public Page<ContractDTO> searchContracts(String searchTerm, Pageable pageable) {
		if (searchTerm == null || searchTerm.isBlank())
			return getAllContracts(pageable);
		return contractRepository.searchByDesignation(searchTerm.trim(), pageable)
				.map(ContractDTO::fromEntity);
	}

	// ========== UPDATE ==========

	public ContractDTO updateContract(Long id, ContractDTO dto) {
		log.info("Updating contract with ID: {}", id);

		Contract existing = contractRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));

		validateRequiredFields(dto, "update");
		validateUniqueConstraints(dto, id);

		mapDtoToEntity(dto, existing);
		setEntityRelationships(dto, existing);

		Contract updated = contractRepository.save(existing);
		handleManyToManyRelationships(dto, updated);

		log.info("Successfully updated contract ID: {}", id);
		return ContractDTO.fromEntityWithRelations(updated);
	}

	// ========== DELETE ==========

	public void deleteContract(Long id) {
		log.info("Deleting contract ID: {}", id);
		if (!contractRepository.existsById(id))
			throw new RuntimeException("Contract not found with ID: " + id);
		contractRepository.deleteById(id);
	}

	// ========== HELPERS ==========

	private void mapDtoToEntity(ContractDTO dto, Contract entity) {
		entity.setInternalId(dto.getInternalId());
		entity.setContractYear(dto.getContractYear());
		entity.setReference(dto.getReference());
		entity.setDesignationAr(dto.getDesignationAr());
		entity.setDesignationEn(dto.getDesignationEn());
		entity.setDesignationFr(dto.getDesignationFr());
		entity.setAmount(dto.getAmount());
		entity.setTransferableAmount(dto.getTransferableAmount());
		entity.setStartDate(dto.getStartDate());
		entity.setApprovalReference(dto.getApprovalReference());
		entity.setApprovalDate(dto.getApprovalDate());
		entity.setContractDate(dto.getContractDate());
		entity.setNotifyDate(dto.getNotifyDate());
		entity.setContractDuration(dto.getContractDuration());
		entity.setObservation(dto.getObservation());
	}

	private void setEntityRelationships(ContractDTO dto, Contract entity) {

		entity.setContractType(contractTypeRepository.findById(dto.getContractTypeId())
				.orElseThrow(() -> new RuntimeException("ContractType not found")));

		entity.setProvider(providerRepository.findById(dto.getProviderId())
				.orElseThrow(() -> new RuntimeException("Provider not found")));

		entity.setProcurementStatus(procurementStatusRepository.findById(dto.getProcurementStatusId())
				.orElseThrow(() -> new RuntimeException("ProcurementStatus not found")));

		entity.setContractStep(contractStepRepository.findById(dto.getContractStepId())
				.orElseThrow(() -> new RuntimeException("ContractStep not found")));

		if (dto.getApprovalStatusId() != null) {
			entity.setApprovalStatus(approvalStatusRepository.findById(dto.getApprovalStatusId())
					.orElseThrow(() -> new RuntimeException("ApprovalStatus not found")));
		} else {
			entity.setApprovalStatus(null);
		}

		entity.setCurrency(currencyRepository.findById(dto.getCurrencyId())
				.orElseThrow(() -> new RuntimeException("Currency not found")));

		if (dto.getConsultationId() != null) {
			entity.setConsultation(consultationRepository.findById(dto.getConsultationId())
					.orElseThrow(() -> new RuntimeException("Consultation not found")));
		} else {
			entity.setConsultation(null);
		}

		if (dto.getContractUpId() != null) {
			entity.setContractUp(contractRepository.findById(dto.getContractUpId())
					.orElseThrow(() -> new RuntimeException("Parent contract not found")));
		} else {
			entity.setContractUp(null);
		}
	}

	private void handleManyToManyRelationships(ContractDTO dto, Contract entity) {
		if (dto.getDocumentIds() != null) {
			entity.setDocuments(documentRepository.findAllById(dto.getDocumentIds()));
		}

		if (dto.getReferencedMailIds() != null) {
			entity.setReferencedMails(mailRepository.findAllById(dto.getReferencedMailIds()));
		}

		if (dto.getPlannedItemIds() != null) {
			entity.setPlannedItems(plannedItemRepository.findAllById(dto.getPlannedItemIds()));
		}
	}

	private void validateRequiredFields(ContractDTO dto, String operation) {
		if (dto.getInternalId() == null || dto.getInternalId().isBlank()) {
			throw new RuntimeException("Internal ID is required for " + operation);
		}
		if (dto.getDesignationFr() == null || dto.getDesignationFr().isBlank()) {
			throw new RuntimeException("French designation is required for " + operation);
		}
		if (dto.getContractTypeId() == null) {
			throw new RuntimeException("Contract type is required for " + operation);
		}
		if (dto.getProviderId() == null) {
			throw new RuntimeException("Provider is required for " + operation);
		}
		if (dto.getCurrencyId() == null) {
			throw new RuntimeException("Currency is required for " + operation);
		}
	}

	private void validateUniqueConstraints(ContractDTO dto, Long excludeId) {
		if (dto.getInternalId() != null && !dto.getInternalId().isBlank()) {
			boolean exists;
			if (excludeId == null)
				exists = contractRepository.existsByInternalId(dto.getInternalId());
			else
				exists = contractRepository.existsByInternalIdAndIdNot(dto.getInternalId(), excludeId);
			if (exists)
				throw new RuntimeException("Contract with internalId '" + dto.getInternalId() + "' already exists");
		}
	}
}
