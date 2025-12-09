/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.common.administration.model.StructureType;
import dz.mdn.iaas.common.administration.repository.StructureRepository;
import dz.mdn.iaas.common.administration.repository.StructureTypeRepository;
import dz.mdn.iaas.common.administration.dto.StructureDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

/**
 * Structure Service with CRUD operations
 * Handles structure management operations with multilingual support and foreign key relationships
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=acronymAr, F_05=acronymEn, F_06=acronymFr, F_07=structureType, F_08=structureUp
 * F_03 (designationFr) has unique constraint and is required
 * F_06 (acronymFr) has unique constraint and is required
 * F_07 (structureType) is required foreign key
 * F_08 (structureUp) is optional foreign key (self-reference for hierarchy)
 * F_01 (designationAr), F_02 (designationEn), F_04 (acronymAr), F_05 (acronymEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StructureService {

    private final StructureRepository structureRepository;
    private final StructureTypeRepository structureTypeRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new structure
     */
    public StructureDTO createStructure(StructureDTO structureDTO) {
        log.info("Creating structure with French designation: {} and acronym: {}, Type ID: {}, Parent ID: {}", 
                structureDTO.getDesignationFr(), structureDTO.getAcronymFr(),
                structureDTO.getStructureTypeId(), structureDTO.getStructureUpId());

        // Validate required fields
        validateRequiredFields(structureDTO, "create");

        // Check for unique constraint violations
        validateUniqueConstraints(structureDTO, null);

        // Validate structure type exists
        StructureType structureType = validateAndGetStructureType(structureDTO.getStructureTypeId());

        // Validate parent structure exists and prevent circular references
        Structure parentStructure = null;
        if (structureDTO.getStructureUpId() != null) {
            parentStructure = validateAndGetParentStructure(structureDTO.getStructureUpId(), null);
        }

        // Create entity with exact field mapping
        Structure structure = new Structure();
        structure.setDesignationAr(structureDTO.getDesignationAr()); // F_01
        structure.setDesignationEn(structureDTO.getDesignationEn()); // F_02
        structure.setDesignationFr(structureDTO.getDesignationFr()); // F_03
        structure.setAcronymAr(structureDTO.getAcronymAr()); // F_04
        structure.setAcronymEn(structureDTO.getAcronymEn()); // F_05
        structure.setAcronymFr(structureDTO.getAcronymFr()); // F_06
        structure.setStructureType(structureType); // F_07
        structure.setStructureUp(parentStructure); // F_08

        Structure savedStructure = structureRepository.save(structure);
        log.info("Successfully created structure with ID: {}", savedStructure.getId());

        return StructureDTO.fromEntity(savedStructure);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get structure by ID
     */
    @Transactional(readOnly = true)
    public StructureDTO getStructureById(Long id) {
        log.debug("Getting structure with ID: {}", id);

        Structure structure = structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure not found with ID: " + id));

        return StructureDTO.fromEntity(structure);
    }

    /**
     * Get structure entity by ID
     */
    @Transactional(readOnly = true)
    public Structure getStructureEntityById(Long id) {
        return structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure not found with ID: " + id));
    }

    /**
     * Find structure by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<StructureDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding structure with French designation: {}", designationFr);

        return structureRepository.findByDesignationFr(designationFr)
                .map(StructureDTO::fromEntity);
    }

    /**
     * Find structure by French acronym (unique field F_06)
     */
    @Transactional(readOnly = true)
    public Optional<StructureDTO> findByAcronymFr(String acronymFr) {
        log.debug("Finding structure with French acronym: {}", acronymFr);

        return structureRepository.findByAcronymFr(acronymFr)
                .map(StructureDTO::fromEntity);
    }

    /**
     * Find structures by structure type ID (F_07)
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> findByStructureTypeId(Long typeId, Pageable pageable) {
        log.debug("Finding structures for type ID: {}", typeId);

        Page<Structure> structures = structureRepository.findByStructureTypeId(typeId, pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find structures by parent structure ID (F_08) - direct children
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> findByParentStructureId(Long parentId, Pageable pageable) {
        log.debug("Finding child structures for parent ID: {}", parentId);

        Page<Structure> structures = structureRepository.findByStructureUpId(parentId, pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find root structures (no parent)
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> findRootStructures(Pageable pageable) {
        log.debug("Finding root structures");

        Page<Structure> structures = structureRepository.findRootStructures(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find structures with children
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> findStructuresWithChildren(Pageable pageable) {
        log.debug("Finding structures with children");

        Page<Structure> structures = structureRepository.findStructuresWithChildren(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find leaf structures (no children)
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> findLeafStructures(Pageable pageable) {
        log.debug("Finding leaf structures");

        Page<Structure> structures = structureRepository.findLeafStructures(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Get all structures with pagination
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> getAllStructures(Pageable pageable) {
        log.debug("Getting all structures with pagination");

        Page<Structure> structures = structureRepository.findAllOrderByDesignationFr(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Get all structures ordered by hierarchy
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> getAllStructuresOrderedByHierarchy(Pageable pageable) {
        log.debug("Getting all structures ordered by hierarchy");

        Page<Structure> structures = structureRepository.findAllOrderByHierarchy(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find one structure by ID
     */
    @Transactional(readOnly = true)
    public Optional<StructureDTO> findOne(Long id) {
        log.debug("Finding structure by ID: {}", id);

        return structureRepository.findById(id)
                .map(StructureDTO::fromEntity);
    }

    /**
     * Search structures by designation or acronym
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> searchStructures(String searchTerm, Pageable pageable) {
        log.debug("Searching structures with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllStructures(pageable);
        }

        Page<Structure> structures = structureRepository.searchByDesignationOrAcronym(searchTerm.trim(), pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Search structures with type and parent context
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> searchStructuresWithContext(String searchTerm, Pageable pageable) {
        log.debug("Searching structures with context for term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllStructures(pageable);
        }

        Page<Structure> structures = structureRepository.searchWithTypeAndParentContext(searchTerm.trim(), pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Get multilingual structures
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> getMultilingualStructures(Pageable pageable) {
        log.debug("Getting multilingual structures");

        Page<Structure> structures = structureRepository.findMultilingualStructures(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find structures by hierarchy level
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> findStructuresByLevel(int level, Pageable pageable) {
        log.debug("Finding structures at level: {}", level);

        Page<Structure> structures = switch (level) {
            case 0 -> structureRepository.findStructuresByLevel0(pageable);
            case 1 -> structureRepository.findStructuresByLevel1(pageable);
            case 2 -> structureRepository.findStructuresByLevel2(pageable);
            default -> throw new IllegalArgumentException("Unsupported hierarchy level: " + level);
        };
        
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Get organizational level structures
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> getOrganizationalLevelStructures(Pageable pageable) {
        log.debug("Getting organizational level structures");

        Page<Structure> structures = structureRepository.findOrganizationalLevelStructures(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Get departmental level structures
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> getDepartmentalLevelStructures(Pageable pageable) {
        log.debug("Getting departmental level structures");

        Page<Structure> structures = structureRepository.findDepartmentalLevelStructures(pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find structures by structure type designation
     */
    @Transactional(readOnly = true)
    public Page<StructureDTO> findByStructureTypeDesignation(String typeDesignation, Pageable pageable) {
        log.debug("Finding structures by type designation: {}", typeDesignation);

        Page<Structure> structures = structureRepository.findByStructureTypeDesignation(typeDesignation, pageable);
        return structures.map(StructureDTO::fromEntity);
    }

    /**
     * Find potential parents for a structure (prevent circular references)
     */
    @Transactional(readOnly = true)
    public List<StructureDTO> findPotentialParents(Long structureId) {
        log.debug("Finding potential parents for structure ID: {}", structureId);

        List<Structure> structures = structureRepository.findPotentialParents(structureId);
        return structures.stream().map(StructureDTO::fromEntity).toList();
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update structure
     */
    public StructureDTO updateStructure(Long id, StructureDTO structureDTO) {
        log.info("Updating structure with ID: {}", id);

        Structure existingStructure = getStructureEntityById(id);

        // Validate required fields
        validateRequiredFields(structureDTO, "update");

        // Check for unique constraint violations (excluding current record)
        validateUniqueConstraints(structureDTO, id);

        // Validate structure type exists if being updated
        StructureType structureType = null;
        if (structureDTO.getStructureTypeId() != null) {
            structureType = validateAndGetStructureType(structureDTO.getStructureTypeId());
        }

        // Validate parent structure exists and prevent circular references if being updated
        Structure parentStructure = null;
        if (structureDTO.getStructureUpId() != null) {
            parentStructure = validateAndGetParentStructure(structureDTO.getStructureUpId(), id);
        }

        // Update fields with exact field mapping
        existingStructure.setDesignationAr(structureDTO.getDesignationAr()); // F_01
        existingStructure.setDesignationEn(structureDTO.getDesignationEn()); // F_02
        existingStructure.setDesignationFr(structureDTO.getDesignationFr()); // F_03
        existingStructure.setAcronymAr(structureDTO.getAcronymAr()); // F_04
        existingStructure.setAcronymEn(structureDTO.getAcronymEn()); // F_05
        existingStructure.setAcronymFr(structureDTO.getAcronymFr()); // F_06
        if (structureType != null) {
            existingStructure.setStructureType(structureType); // F_07
        }
        existingStructure.setStructureUp(parentStructure); // F_08 (can be null)

        Structure updatedStructure = structureRepository.save(existingStructure);
        log.info("Successfully updated structure with ID: {}", id);

        return StructureDTO.fromEntity(updatedStructure);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete structure
     */
    public void deleteStructure(Long id) {
        log.info("Deleting structure with ID: {}", id);

        Structure structure = getStructureEntityById(id);
        
        // Check if structure has children - prevent deletion if it has children
        Long childrenCount = structureRepository.countDirectChildren(id);
        if (childrenCount > 0) {
            throw new RuntimeException("Cannot delete structure with ID " + id + " because it has " + childrenCount + " child structures");
        }

        structureRepository.delete(structure);

        log.info("Successfully deleted structure with ID: {}", id);
    }

    /**
     * Delete structure by ID (direct)
     */
    public void deleteStructureById(Long id) {
        log.info("Deleting structure by ID: {}", id);

        if (!structureRepository.existsById(id)) {
            throw new RuntimeException("Structure not found with ID: " + id);
        }

        // Check if structure has children
        Long childrenCount = structureRepository.countDirectChildren(id);
        if (childrenCount > 0) {
            throw new RuntimeException("Cannot delete structure with ID " + id + " because it has " + childrenCount + " child structures");
        }

        structureRepository.deleteById(id);
        log.info("Successfully deleted structure with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if structure exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return structureRepository.existsById(id);
    }

    /**
     * Check if structure exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return structureRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Check if structure exists by French acronym
     */
    @Transactional(readOnly = true)
    public boolean existsByAcronymFr(String acronymFr) {
        return structureRepository.existsByAcronymFr(acronymFr);
    }

    /**
     * Get total count of structures
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return structureRepository.countAllStructures();
    }

    /**
     * Get count by structure type
     */
    @Transactional(readOnly = true)
    public Long getCountByType(Long typeId) {
        return structureRepository.countByStructureTypeId(typeId);
    }

    /**
     * Get count of direct children
     */
    @Transactional(readOnly = true)
    public Long getDirectChildrenCount(Long parentId) {
        return structureRepository.countDirectChildren(parentId);
    }

    /**
     * Get count of root structures
     */
    @Transactional(readOnly = true)
    public Long getRootStructuresCount() {
        return structureRepository.countRootStructures();
    }

    /**
     * Check if structure is ancestor of another
     */
    @Transactional(readOnly = true)
    public boolean isAncestorOf(Long ancestorId, Long descendantId) {
        return structureRepository.isAncestorOf(ancestorId, descendantId);
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(StructureDTO structureDTO, String operation) {
        if (structureDTO.getDesignationFr() == null || structureDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (structureDTO.getAcronymFr() == null || structureDTO.getAcronymFr().trim().isEmpty()) {
            throw new RuntimeException("French acronym is required for " + operation);
        }
        if (structureDTO.getStructureTypeId() == null) {
            throw new RuntimeException("Structure type is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(StructureDTO structureDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (structureRepository.existsByDesignationFr(structureDTO.getDesignationFr())) {
                throw new RuntimeException("Structure with French designation '" + structureDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (structureRepository.existsByDesignationFrAndIdNot(structureDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another structure with French designation '" + structureDTO.getDesignationFr() + "' already exists");
            }
        }

        // Check French acronym uniqueness (F_06)
        if (excludeId == null) {
            if (structureRepository.existsByAcronymFr(structureDTO.getAcronymFr())) {
                throw new RuntimeException("Structure with French acronym '" + structureDTO.getAcronymFr() + "' already exists");
            }
        } else {
            if (structureRepository.existsByAcronymFrAndIdNot(structureDTO.getAcronymFr(), excludeId)) {
                throw new RuntimeException("Another structure with French acronym '" + structureDTO.getAcronymFr() + "' already exists");
            }
        }
    }

    /**
     * Validate and get structure type
     */
    private StructureType validateAndGetStructureType(Long typeId) {
        return structureTypeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Structure type not found with ID: " + typeId));
    }

    /**
     * Validate and get parent structure (prevent circular references)
     */
    private Structure validateAndGetParentStructure(Long parentId, Long currentStructureId) {
        Structure parentStructure = structureRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent structure not found with ID: " + parentId));

        // Prevent self-reference
        if (currentStructureId != null && currentStructureId.equals(parentId)) {
            throw new RuntimeException("Structure cannot be its own parent");
        }

        // Prevent circular references - check if current structure is an ancestor of the proposed parent
        if (currentStructureId != null && isAncestorOf(currentStructureId, parentId)) {
            throw new RuntimeException("Circular reference detected: structure " + currentStructureId + " cannot be parent of its ancestor " + parentId);
        }

        return parentStructure;
    }
}
