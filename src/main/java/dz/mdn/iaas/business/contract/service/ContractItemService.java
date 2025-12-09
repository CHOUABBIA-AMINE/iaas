/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItemService
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.service;

import dz.mdn.iaas.business.contract.dto.ContractItemDTO;
import dz.mdn.iaas.business.contract.model.ContractItem;
import dz.mdn.iaas.business.contract.repository.ContractItemRepository;
import dz.mdn.iaas.business.contract.model.Contract;
import dz.mdn.iaas.business.contract.repository.ContractRepository;
import dz.mdn.iaas.exception.ResourceNotFoundException;
import dz.mdn.iaas.exception.BusinessValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContractItemService {

    private final ContractItemRepository contractItemRepository;
    private final ContractRepository contractRepository;

    // ========== CREATE ==========

    public ContractItemDTO createContractItem(ContractItemDTO dto) {
        log.debug("Creating contract item: {}", dto);

        if (contractItemRepository.existsByReference(dto.getReference())) {
            throw new BusinessValidationException("ContractItem with reference already exists: " + dto.getReference());
        }

        Contract contract = contractRepository.findById(dto.getContractId())
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with ID: " + dto.getContractId()));

        ContractItem entity = dto.toEntity();
        entity.setContract(contract);

        ContractItem saved = contractItemRepository.save(entity);
        return ContractItemDTO.fromEntity(saved);
    }

    // ========== READ ==========

    public ContractItemDTO getContractItemById(Long id) {
        ContractItem entity = contractItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractItem not found with ID: " + id));

        return ContractItemDTO.fromEntity(entity);
    }

    // ========== UPDATE ==========

    public ContractItemDTO updateContractItem(Long id, ContractItemDTO dto) {
        log.debug("Updating contract item ID: {}", id);

        ContractItem existing = contractItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractItem not found with ID: " + id));

        if (!existing.getReference().equals(dto.getReference()) &&
            contractItemRepository.existsByReference(dto.getReference())) {
            throw new BusinessValidationException("Reference already used: " + dto.getReference());
        }

        Contract contract = contractRepository.findById(dto.getContractId())
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with ID: " + dto.getContractId()));

        existing.setReference(dto.getReference());
        existing.setDesignation(dto.getDesignation());
        existing.setUnitPrice(dto.getUnitPrice());
        existing.setQuantity(dto.getQuantity());
        existing.setObservation(dto.getObservation());
        existing.setContract(contract);

        ContractItem updated = contractItemRepository.save(existing);
        return ContractItemDTO.fromEntity(updated);
    }

    // ========== DELETE ==========

    public void deleteContractItem(Long id) {
        log.debug("Deleting contract item ID: {}", id);

        ContractItem entity = contractItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractItem not found with ID: " + id));

        contractItemRepository.delete(entity);
    }

    // ========== LIST ALL ==========

    public Page<ContractItemDTO> getAllContractItems(Pageable pageable) {
        return contractItemRepository.findAll(pageable)
                .map(ContractItemDTO::fromEntity);
    }

    // ========== FILTERS ==========

    public Page<ContractItemDTO> getContractItemsByContract(Long contractId, Pageable pageable) {
        return contractItemRepository.findByContractId(contractId, pageable)
                .map(ContractItemDTO::fromEntity);
    }

    public Optional<ContractItemDTO> findByReference(String reference) {
        return contractItemRepository.findByReference(reference)
                .map(ContractItemDTO::fromEntity);
    }

    // ========== VALIDATION ==========

    public boolean existsById(Long id) {
        return contractItemRepository.existsById(id);
    }

    public boolean existsByReference(String reference) {
        return contractItemRepository.existsByReference(reference);
    }

    // ========== STATISTICS ==========

    public long getTotalCount() {
        return contractItemRepository.count();
    }

    public Double getTotalQuantityByContract(Long contractId) {
        return contractItemRepository.sumQuantityByContractId(contractId).orElse(0.0);
    }

    public Double getTotalValueByContract(Long contractId) {
        return contractItemRepository.sumTotalValueByContractId(contractId);
    }

    // ========== SEARCH ==========

    public Page<ContractItemDTO> searchContractItems(String query, Pageable pageable) {
        return contractItemRepository.searchByKeyword(query, pageable)
                .map(ContractItemDTO::fromEntity);
    }
}
