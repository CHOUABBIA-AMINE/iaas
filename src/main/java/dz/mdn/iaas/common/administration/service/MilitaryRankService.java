/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRankService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.common.administration.dto.MilitaryRankDTO;
import dz.mdn.iaas.common.administration.model.MilitaryRank;
import dz.mdn.iaas.common.administration.repository.MilitaryCategoryRepository;
import dz.mdn.iaas.common.administration.repository.MilitaryRankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Military Rank Service with CRUD operations
 * Handles military rank management operations with multilingual support and military hierarchy validation
 * Based on exact field names and military rank business rules
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MilitaryRankService {

    private final MilitaryRankRepository militaryRankRepository;
    
    // Repository bean for related entity (injected as needed)
    private final MilitaryCategoryRepository militaryCategoryRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new military rank
     */
    public MilitaryRankDTO createMilitaryRank(MilitaryRankDTO militaryRankDTO) {
        log.info("Creating military rank with French designation: {}", 
                militaryRankDTO.getDesignationFr());

        // Validate required fields and business rules
        validateRequiredFields(militaryRankDTO, "create");
        validateBusinessRules(militaryRankDTO, "create");

        // Check for unique constraints
        validateUniqueConstraints(militaryRankDTO, null);

        // Create entity with exact field mapping
        MilitaryRank militaryRank = new MilitaryRank();
        mapDtoToEntity(militaryRankDTO, militaryRank);

        // Handle foreign key relationship
        setEntityRelationships(militaryRankDTO, militaryRank);

        MilitaryRank savedMilitaryRank = militaryRankRepository.save(militaryRank);
        log.info("Successfully created military rank with ID: {}", savedMilitaryRank.getId());

        return MilitaryRankDTO.fromEntityWithRelations(savedMilitaryRank);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get military rank by ID
     */
    @Transactional(readOnly = true)
    public MilitaryRankDTO getMilitaryRankById(Long id) {
        log.debug("Getting military rank with ID: {}", id);

        MilitaryRank militaryRank = militaryRankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Military rank not found with ID: " + id));

        return MilitaryRankDTO.fromEntityWithRelations(militaryRank);
    }

    /**
     * Get military rank entity by ID
     */
    @Transactional(readOnly = true)
    public MilitaryRank getMilitaryRankEntityById(Long id) {
        return militaryRankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Military rank not found with ID: " + id));
    }

    /**
     * Get all military ranks with pagination
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getAllMilitaryRanks(Pageable pageable) {
        log.debug("Getting all military ranks with pagination");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findAllOrderByDesignationFr(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Find one military rank by ID
     */
    @Transactional(readOnly = true)
    public Optional<MilitaryRankDTO> findOne(Long id) {
        log.debug("Finding military rank by ID: {}", id);

        return militaryRankRepository.findById(id)
                .map(MilitaryRankDTO::fromEntityWithRelations);
    }

    /**
     * Find military rank by French designation (unique)
     */
    @Transactional(readOnly = true)
    public Optional<MilitaryRankDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding military rank by French designation: {}", designationFr);

        return militaryRankRepository.findByDesignationFr(designationFr)
                .map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Search military ranks by designation
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> searchRanksByDesignation(String searchTerm, Pageable pageable) {
        log.debug("Searching military ranks by designation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllMilitaryRanks(pageable);
        }

        Page<MilitaryRank> militaryRanks = militaryRankRepository.searchByDesignation(searchTerm.trim(), pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Search military ranks by abbreviation
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> searchRanksByAbbreviation(String searchTerm, Pageable pageable) {
        log.debug("Searching military ranks by abbreviation with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllMilitaryRanks(pageable);
        }

        Page<MilitaryRank> militaryRanks = militaryRankRepository.searchByAbbreviation(searchTerm.trim(), pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Search military ranks by any field
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> searchRanksByAnyField(String searchTerm, Pageable pageable) {
        log.debug("Searching military ranks by any field with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllMilitaryRanks(pageable);
        }

        Page<MilitaryRank> militaryRanks = militaryRankRepository.searchByAnyField(searchTerm.trim(), pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Get military ranks by military category
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getRanksByMilitaryCategory(Long militaryCategoryId, Pageable pageable) {
        log.debug("Getting military ranks for military category ID: {}", militaryCategoryId);

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findByMilitaryCategory(militaryCategoryId, pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Get all military ranks by military category (without pagination)
     */
    @Transactional(readOnly = true)
    public List<MilitaryRankDTO> getAllRanksByMilitaryCategory(Long militaryCategoryId) {
        log.debug("Getting all military ranks for military category ID: {}", militaryCategoryId);

        List<MilitaryRank> militaryRanks = militaryRankRepository.findAllByMilitaryCategory(militaryCategoryId);
        return militaryRanks.stream().map(MilitaryRankDTO::fromEntity).toList();
    }

    /**
     * Get military ranks by hierarchy level
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getGeneralOfficerRanks(Pageable pageable) {
        log.debug("Getting general officer ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findGeneralOfficerRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getSeniorOfficerRanks(Pageable pageable) {
        log.debug("Getting senior officer ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findSeniorOfficerRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getCompanyGradeOfficerRanks(Pageable pageable) {
        log.debug("Getting company grade officer ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findCompanyGradeOfficerRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getNonCommissionedOfficerRanks(Pageable pageable) {
        log.debug("Getting non-commissioned officer ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findNonCommissionedOfficerRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getEnlistedRanks(Pageable pageable) {
        log.debug("Getting enlisted ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findEnlistedRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Get military ranks by service branch
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getArmyRanks(Pageable pageable) {
        log.debug("Getting army ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findArmyRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getNavyRanks(Pageable pageable) {
        log.debug("Getting navy ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findNavyRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getAirForceRanks(Pageable pageable) {
        log.debug("Getting air force ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findAirForceRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getGendarmerieRanks(Pageable pageable) {
        log.debug("Getting gendarmerie ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findGendarmerieRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Get multilingual military ranks
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getMultilingualRanks(Pageable pageable) {
        log.debug("Getting multilingual military ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findMultilingualRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Get commissioned officer ranks
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getCommissionedOfficerRanks(Pageable pageable) {
        log.debug("Getting commissioned officer ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findCommissionedOfficerRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    /**
     * Get command-eligible ranks
     */
    @Transactional(readOnly = true)
    public Page<MilitaryRankDTO> getCommandEligibleRanks(Pageable pageable) {
        log.debug("Getting command-eligible ranks");

        Page<MilitaryRank> militaryRanks = militaryRankRepository.findCommandEligibleRanks(pageable);
        return militaryRanks.map(MilitaryRankDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update military rank
     */
    public MilitaryRankDTO updateMilitaryRank(Long id, MilitaryRankDTO militaryRankDTO) {
        log.info("Updating military rank with ID: {}", id);

        MilitaryRank existingMilitaryRank = getMilitaryRankEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(militaryRankDTO, "update");
        validateBusinessRules(militaryRankDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(militaryRankDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(militaryRankDTO, existingMilitaryRank);

        // Handle foreign key relationship
        setEntityRelationships(militaryRankDTO, existingMilitaryRank);

        MilitaryRank updatedMilitaryRank = militaryRankRepository.save(existingMilitaryRank);
        log.info("Successfully updated military rank with ID: {}", id);

        return MilitaryRankDTO.fromEntityWithRelations(updatedMilitaryRank);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete military rank
     */
    public void deleteMilitaryRank(Long id) {
        log.info("Deleting military rank with ID: {}", id);

        MilitaryRank militaryRank = getMilitaryRankEntityById(id);
        militaryRankRepository.delete(militaryRank);

        log.info("Successfully deleted military rank with ID: {}", id);
    }

    /**
     * Delete military rank by ID (direct)
     */
    public void deleteMilitaryRankById(Long id) {
        log.info("Deleting military rank by ID: {}", id);

        if (!militaryRankRepository.existsById(id)) {
            throw new RuntimeException("Military rank not found with ID: " + id);
        }

        militaryRankRepository.deleteById(id);
        log.info("Successfully deleted military rank with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if military rank exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return militaryRankRepository.existsById(id);
    }

    /**
     * Check if French designation exists
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return militaryRankRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get count of military ranks by category
     */
    @Transactional(readOnly = true)
    public Long countRanksByMilitaryCategory(Long militaryCategoryId) {
        return militaryRankRepository.countByMilitaryCategory(militaryCategoryId);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countAllMilitaryRanks() {
        return militaryRankRepository.countAllMilitaryRanks();
    }

    @Transactional(readOnly = true)
    public Long countGeneralOfficerRanks() {
        return militaryRankRepository.countGeneralOfficerRanks();
    }

    @Transactional(readOnly = true)
    public Long countCommissionedOfficerRanks() {
        return militaryRankRepository.countCommissionedOfficerRanks();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(MilitaryRankDTO dto, MilitaryRank entity) {
        entity.setDesignationAr(dto.getDesignationAr()); // F_01
        entity.setDesignationEn(dto.getDesignationEn()); // F_02
        entity.setDesignationFr(dto.getDesignationFr()); // F_03
        entity.setAbbreviationAr(dto.getAbbreviationAr()); // F_04
        entity.setAbbreviationEn(dto.getAbbreviationEn()); // F_05
        entity.setAbbreviationFr(dto.getAbbreviationFr()); // F_06
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(MilitaryRankDTO dto, MilitaryRank entity) {
        // F_07 - MilitaryCategory (required)
        if (dto.getMilitaryCategoryId() != null) {
            entity.setMilitaryCategory(militaryCategoryRepository.findById(dto.getMilitaryCategoryId())
                    .orElseThrow(() -> new RuntimeException("Military category not found with ID: " + dto.getMilitaryCategoryId())));
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(MilitaryRankDTO dto, String operation) {
        if (dto.getDesignationFr() == null || dto.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (dto.getAbbreviationFr() == null || dto.getAbbreviationFr().trim().isEmpty()) {
            throw new RuntimeException("French abbreviation is required for " + operation);
        }
        if (dto.getMilitaryCategoryId() == null) {
            throw new RuntimeException("Military category is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(MilitaryRankDTO dto, String operation) {
        // Validate designation lengths
        if (dto.getDesignationFr() != null && dto.getDesignationFr().length() > 50) {
            throw new RuntimeException("French designation cannot exceed 50 characters for " + operation);
        }
        if (dto.getDesignationEn() != null && dto.getDesignationEn().length() > 50) {
            throw new RuntimeException("English designation cannot exceed 50 characters for " + operation);
        }
        if (dto.getDesignationAr() != null && dto.getDesignationAr().length() > 50) {
            throw new RuntimeException("Arabic designation cannot exceed 50 characters for " + operation);
        }

        // Validate abbreviation lengths
        if (dto.getAbbreviationFr() != null && dto.getAbbreviationFr().length() > 10) {
            throw new RuntimeException("French abbreviation cannot exceed 10 characters for " + operation);
        }
        if (dto.getAbbreviationEn() != null && dto.getAbbreviationEn().length() > 10) {
            throw new RuntimeException("English abbreviation cannot exceed 10 characters for " + operation);
        }
        if (dto.getAbbreviationAr() != null && dto.getAbbreviationAr().length() > 10) {
            throw new RuntimeException("Arabic abbreviation cannot exceed 10 characters for " + operation);
        }

        // Validate at least one designation is provided
        boolean hasDesignation = (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty());
        if (!hasDesignation) {
            throw new RuntimeException("At least French designation must be provided for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(MilitaryRankDTO dto, Long excludeId) {
        // Check French designation uniqueness (T_01_04_05_UK_01)
        if (dto.getDesignationFr() != null && !dto.getDesignationFr().trim().isEmpty()) {
            if (excludeId == null) {
                if (militaryRankRepository.existsByDesignationFr(dto.getDesignationFr())) {
                    throw new RuntimeException("Military rank with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            } else {
                if (militaryRankRepository.existsByDesignationFrAndIdNot(dto.getDesignationFr(), excludeId)) {
                    throw new RuntimeException("Another military rank with French designation '" + 
                        dto.getDesignationFr() + "' already exists");
                }
            }
        }
    }
}