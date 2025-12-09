/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BlocService
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.service;

import dz.mdn.iaas.common.environment.model.Bloc;
import dz.mdn.iaas.common.environment.repository.BlocRepository;
import dz.mdn.iaas.common.environment.dto.BlocDTO;

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
public class BlocService {

    private final BlocRepository blocRepository;

    // ========== CREATE OPERATIONS ==========

    public BlocDTO createBloc(BlocDTO blocDTO) {
        log.info("Creating bloc with codes: {} (AR) | {} (LT)", 
                blocDTO.getCodeAr(), blocDTO.getCodeLt());

        // Validate all required fields
        validateRequiredFields(blocDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(blocDTO, null);

        // Create entity with exact field mapping
        Bloc bloc = new Bloc();
        bloc.setCodeAr(blocDTO.getCodeAr()); // F_01
        bloc.setCodeLt(blocDTO.getCodeLt()); // F_02

        Bloc savedBloc = blocRepository.save(bloc);
        log.info("Successfully created bloc with ID: {}", savedBloc.getId());

        return BlocDTO.fromEntity(savedBloc);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public BlocDTO getBlocById(Long id) {
        log.debug("Getting bloc with ID: {}", id);

        Bloc bloc = blocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloc not found with ID: " + id));

        return BlocDTO.fromEntity(bloc);
    }

    @Transactional(readOnly = true)
    public Bloc getBlocEntityById(Long id) {
        return blocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloc not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<BlocDTO> findByCodeAr(String codeAr) {
        log.debug("Finding bloc with Arabic code: {}", codeAr);

        return blocRepository.findByCodeAr(codeAr)
                .map(BlocDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<BlocDTO> findByCodeLt(String codeLt) {
        log.debug("Finding bloc with Latin code: {}", codeLt);

        return blocRepository.findByCodeLt(codeLt)
                .map(BlocDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BlocDTO> getAllBlocs(Pageable pageable) {
        log.debug("Getting all blocs with pagination");

        Page<Bloc> blocs = blocRepository.findAllOrderByCodeLt(pageable);
        return blocs.map(BlocDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<BlocDTO> findOne(Long id) {
        log.debug("Finding bloc by ID: {}", id);

        return blocRepository.findById(id)
                .map(BlocDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BlocDTO> searchBlocs(String searchTerm, Pageable pageable) {
        log.debug("Searching blocs with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBlocs(pageable);
        }

        Page<Bloc> blocs = blocRepository.searchByAnyField(searchTerm.trim(), pageable);
        return blocs.map(BlocDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BlocDTO> searchByCodeAr(String codeAr, Pageable pageable) {
        log.debug("Searching blocs by Arabic code: {}", codeAr);

        Page<Bloc> blocs = blocRepository.findByCodeArContaining(codeAr, pageable);
        return blocs.map(BlocDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<BlocDTO> searchByCodeLt(String codeLt, Pageable pageable) {
        log.debug("Searching blocs by Latin code: {}", codeLt);

        Page<Bloc> blocs = blocRepository.findByCodeLtContaining(codeLt, pageable);
        return blocs.map(BlocDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public BlocDTO updateBloc(Long id, BlocDTO blocDTO) {
        log.info("Updating bloc with ID: {}", id);

        Bloc existingBloc = getBlocEntityById(id);

        // Validate all required fields
        validateRequiredFields(blocDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(blocDTO, id);

        // Update fields with exact field mapping
        existingBloc.setCodeAr(blocDTO.getCodeAr()); // F_01
        existingBloc.setCodeLt(blocDTO.getCodeLt()); // F_02

        Bloc updatedBloc = blocRepository.save(existingBloc);
        log.info("Successfully updated bloc with ID: {}", id);

        return BlocDTO.fromEntity(updatedBloc);
    }

    public BlocDTO partialUpdateBloc(Long id, BlocDTO blocDTO) {
        log.info("Partially updating bloc with ID: {}", id);

        Bloc existingBloc = getBlocEntityById(id);
        boolean updated = false;

        // Update only non-null fields
        if (blocDTO.getCodeAr() != null) {
            if (blocDTO.getCodeAr().trim().isEmpty()) {
                throw new RuntimeException("Arabic code cannot be empty");
            }
            if (blocRepository.existsByCodeArAndIdNot(blocDTO.getCodeAr(), id)) {
                throw new RuntimeException("Another bloc with Arabic code '" + blocDTO.getCodeAr() + "' already exists");
            }
            existingBloc.setCodeAr(blocDTO.getCodeAr()); // F_01
            updated = true;
        }

        if (blocDTO.getCodeLt() != null) {
            if (blocDTO.getCodeLt().trim().isEmpty()) {
                throw new RuntimeException("Latin code cannot be empty");
            }
            if (blocRepository.existsByCodeLtAndIdNot(blocDTO.getCodeLt(), id)) {
                throw new RuntimeException("Another bloc with Latin code '" + blocDTO.getCodeLt() + "' already exists");
            }
            existingBloc.setCodeLt(blocDTO.getCodeLt()); // F_02
            updated = true;
        }

        if (updated) {
            Bloc updatedBloc = blocRepository.save(existingBloc);
            log.info("Successfully partially updated bloc with ID: {}", id);
            return BlocDTO.fromEntity(updatedBloc);
        } else {
            log.debug("No fields to update for bloc with ID: {}", id);
            return BlocDTO.fromEntity(existingBloc);
        }
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteBloc(Long id) {
        log.info("Deleting bloc with ID: {}", id);

        Bloc bloc = getBlocEntityById(id);
        blocRepository.delete(bloc);

        log.info("Successfully deleted bloc with ID: {}", id);
    }

    public void deleteBlocById(Long id) {
        log.info("Deleting bloc by ID: {}", id);

        if (!blocRepository.existsById(id)) {
            throw new RuntimeException("Bloc not found with ID: " + id);
        }

        blocRepository.deleteById(id);
        log.info("Successfully deleted bloc with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return blocRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByCodeAr(String codeAr) {
        return blocRepository.existsByCodeAr(codeAr);
    }

    @Transactional(readOnly = true)
    public boolean existsByCodeLt(String codeLt) {
        return blocRepository.existsByCodeLt(codeLt);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return blocRepository.countAllBlocs();
    }

    // ========== VALIDATION METHODS ==========

    private void validateRequiredFields(BlocDTO blocDTO, String operation) {
        if (blocDTO.getCodeAr() == null || blocDTO.getCodeAr().trim().isEmpty()) {
            throw new RuntimeException("Arabic code is required for " + operation);
        }

        if (blocDTO.getCodeLt() == null || blocDTO.getCodeLt().trim().isEmpty()) {
            throw new RuntimeException("Latin code is required for " + operation);
        }
    }

    private void validateUniqueConstraints(BlocDTO blocDTO, Long excludeId) {
        // Check Arabic code uniqueness (F_01)
        if (excludeId == null) {
            if (blocRepository.existsByCodeAr(blocDTO.getCodeAr())) {
                throw new RuntimeException("Bloc with Arabic code '" + blocDTO.getCodeAr() + "' already exists");
            }
        } else {
            if (blocRepository.existsByCodeArAndIdNot(blocDTO.getCodeAr(), excludeId)) {
                throw new RuntimeException("Another bloc with Arabic code '" + blocDTO.getCodeAr() + "' already exists");
            }
        }

        // Check Latin code uniqueness (F_02)
        if (excludeId == null) {
            if (blocRepository.existsByCodeLt(blocDTO.getCodeLt())) {
                throw new RuntimeException("Bloc with Latin code '" + blocDTO.getCodeLt() + "' already exists");
            }
        } else {
            if (blocRepository.existsByCodeLtAndIdNot(blocDTO.getCodeLt(), excludeId)) {
                throw new RuntimeException("Another bloc with Latin code '" + blocDTO.getCodeLt() + "' already exists");
            }
        }
    }
}
