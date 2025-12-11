/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineSystemService
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Service
 *	@Layer		: Network / Service
 *	@Package	: Network / Service
 *
 **/

package dz.mdn.iaas.network.service;

import dz.mdn.iaas.configuration.template.GenericService;
import dz.mdn.iaas.exception.BusinessValidationException;
import dz.mdn.iaas.network.dto.PipelineSystemDTO;
import dz.mdn.iaas.network.model.PipelineSystem;
import dz.mdn.iaas.network.model.Product;
import dz.mdn.iaas.network.repository.PipelineSystemRepository;
import dz.mdn.iaas.network.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PipelineSystemService extends GenericService<PipelineSystem, PipelineSystemDTO, Long> {

    private final PipelineSystemRepository pipelineSystemRepository;
    private final ProductRepository productRepository;

    @Override
    protected JpaRepository<PipelineSystem, Long> getRepository() {
        return pipelineSystemRepository;
    }

    @Override
    protected String getEntityName() {
        return "PipelineSystem";
    }

    @Override
    protected PipelineSystemDTO toDTO(PipelineSystem entity) {
        return PipelineSystemDTO.fromEntity(entity);
    }

    @Override
    protected PipelineSystem toEntity(PipelineSystemDTO dto) {
        PipelineSystem entity = dto.toEntity();
        
        if (dto.getProductId() != null) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId()));
            entity.setProduct(product);
        }
        
        return entity;
    }

    @Override
    protected void updateEntityFromDTO(PipelineSystem entity, PipelineSystemDTO dto) {
        dto.updateEntity(entity);
        
        if (dto.getProductId() != null) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId()));
            entity.setProduct(product);
        }
    }

    @Override
    @Transactional
    public PipelineSystemDTO create(PipelineSystemDTO dto) {
        log.info("Creating pipeline system: code={}", dto.getCode());
        
        if (pipelineSystemRepository.existsByCode(dto.getCode())) {
            throw new BusinessValidationException("Pipeline system with code '" + dto.getCode() + "' already exists");
        }
        
        return super.create(dto);
    }

    @Override
    @Transactional
    public PipelineSystemDTO update(Long id, PipelineSystemDTO dto) {
        log.info("Updating pipeline system with ID: {}", id);
        
        if (pipelineSystemRepository.existsByCodeAndIdNot(dto.getCode(), id)) {
            throw new BusinessValidationException("Pipeline system with code '" + dto.getCode() + "' already exists");
        }
        
        return super.update(id, dto);
    }

    public List<PipelineSystemDTO> getAll() {
        log.debug("Getting all pipeline systems without pagination");
        return pipelineSystemRepository.findAll().stream()
                .map(PipelineSystemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<PipelineSystemDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for pipeline systems with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return executeQuery(p -> pipelineSystemRepository.searchByAnyField(searchTerm.trim(), p), pageable);
    }

    public List<PipelineSystemDTO> findByProduct(Long productId) {
        log.debug("Finding pipeline systems by product id: {}", productId);
        return pipelineSystemRepository.findByProductId(productId).stream()
                .map(PipelineSystemDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
