/**
 * SERVICE REFACTORING TEMPLATE
 * 
 * Use this template to quickly refactor any service in the application.
 * Simply replace placeholders with your entity/DTO names.
 * 
 * PLACEHOLDERS TO REPLACE:
 * - YourEntity: Your entity class name (e.g., Currency, ApprovalStatus)
 * - YourDTO: Your DTO class name (e.g., CurrencyDTO, ApprovalStatusDTO)
 * - YourRepository: Your repository interface name
 * - yourRepository: Repository field name (camelCase)
 * - yourEntityName: Human-readable entity name for logs
 * 
 * STEPS:
 * 1. Copy this template
 * 2. Replace all placeholders
 * 3. Add unique field validations in create/update methods
 * 4. Add custom query methods as needed
 * 5. Test thoroughly
 **/

package dz.mdn.iaas.[your.package.path];

import dz.mdn.iaas.[your.entity.package].YourEntity;
import dz.mdn.iaas.[your.dto.package].YourDTO;
import dz.mdn.iaas.[your.repository.package].YourRepository;
import dz.mdn.iaas.common.service.GenericService;
import dz.mdn.iaas.common.validator.UniqueFieldValidator;
import dz.mdn.iaas.common.validator.UniqueFieldValidator.UniqueFieldUpdateValidation;
import dz.mdn.iaas.common.validator.UniqueFieldValidator.UniqueFieldValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Refactored YourEntity Service
 * Uses GenericService for common CRUD operations
 * Uses UniqueFieldValidator for centralized validation
 */
@Service("yourEntityServiceRefactored") // Use descriptive bean name to avoid conflicts
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true) // Default for all methods
public class YourEntityServiceRefactored extends GenericService<YourEntity, YourDTO, Long> {

    private final YourRepository yourRepository;
    private final UniqueFieldValidator uniqueFieldValidator;

    // ========== STEP 1: IMPLEMENT ABSTRACT METHODS ==========

    @Override
    protected JpaRepository<YourEntity, Long> getRepository() {
        return yourRepository;
    }

    @Override
    protected String getEntityName() {
        return "YourEntityName"; // e.g., "Currency", "ApprovalStatus"
    }

    @Override
    protected YourDTO toDTO(YourEntity entity) {
        return YourDTO.fromEntity(entity);
    }

    @Override
    protected YourEntity toEntity(YourDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected void updateEntityFromDTO(YourEntity entity, YourDTO dto) {
        dto.updateEntity(entity);
    }

    // ========== STEP 2: OVERRIDE CREATE WITH VALIDATION ==========

    @Override
    @Transactional // Override to make writable
    public YourDTO create(YourDTO dto) {
        log.info("Creating {}: key field={}", getEntityName(), dto.getKeyField());
        
        // OPTION A: Single unique field validation
        uniqueFieldValidator.validateUniqueForCreate(
            "Field Name", 
            dto.getUniqueField(), 
            yourRepository::existsByUniqueField
        );
        
        // OPTION B: Multiple unique fields validation
        // Uncomment and modify as needed:
        /*
        uniqueFieldValidator.validateMultipleForCreate(
            UniqueFieldValidation.of("Field1", dto.getField1(), yourRepository::existsByField1),
            UniqueFieldValidation.of("Field2", dto.getField2(), yourRepository::existsByField2),
            UniqueFieldValidation.of("Field3", dto.getField3(), yourRepository::existsByField3)
        );
        */
        
        return super.create(dto);
    }

    // ========== STEP 3: OVERRIDE UPDATE WITH VALIDATION ==========

    @Override
    @Transactional
    public YourDTO update(Long id, YourDTO dto) {
        log.info("Updating {} with ID: {}", getEntityName(), id);
        
        // OPTION A: Single unique field validation
        uniqueFieldValidator.validateUniqueForUpdate(
            "Field Name",
            dto.getUniqueField(),
            id,
            yourRepository::existsByUniqueFieldAndIdNot
        );
        
        // OPTION B: Multiple unique fields validation
        // Uncomment and modify as needed:
        /*
        uniqueFieldValidator.validateMultipleForUpdate(
            UniqueFieldUpdateValidation.of("Field1", dto.getField1(), id, yourRepository::existsByField1AndIdNot),
            UniqueFieldUpdateValidation.of("Field2", dto.getField2(), id, yourRepository::existsByField2AndIdNot),
            UniqueFieldUpdateValidation.of("Field3", dto.getField3(), id, yourRepository::existsByField3AndIdNot)
        );
        */
        
        return super.update(id, dto);
    }

    // ========== STEP 4: ADD CUSTOM FINDER METHODS ==========

    /**
     * Find by unique field - uses executeSingleQuery helper
     */
    public Optional<YourDTO> findByUniqueField(String uniqueField) {
        return executeSingleQuery(yourRepository::findByUniqueField, uniqueField);
    }

    /**
     * Example: Find by another field
     */
    public Optional<YourDTO> findByAnotherField(String anotherField) {
        return executeSingleQuery(yourRepository::findByAnotherField, anotherField);
    }

    // ========== STEP 5: ADD SEARCH METHODS ==========

    /**
     * Search by term - uses executeQuery helper
     */
    public Page<YourDTO> search(String searchTerm, Pageable pageable) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        return executeQuery(p -> yourRepository.searchByAnyField(searchTerm.trim(), p), pageable);
    }

    /**
     * Get all with custom ordering
     */
    public Page<YourDTO> getAllOrdered(Pageable pageable) {
        return executeQuery(yourRepository::findAllOrderBySomeField, pageable);
    }

    // ========== STEP 6: ADD CATEGORIZED QUERIES (IF APPLICABLE) ==========

    /**
     * Example: Get entities by category
     */
    public Page<YourDTO> getByCategory(String category, Pageable pageable) {
        return executeQuery(p -> yourRepository.findByCategory(category, p), pageable);
    }

    /**
     * Example: Get active entities
     */
    public Page<YourDTO> getActive(Pageable pageable) {
        return executeQuery(yourRepository::findActiveEntities, pageable);
    }

    /**
     * Example: Get inactive entities
     */
    public Page<YourDTO> getInactive(Pageable pageable) {
        return executeQuery(yourRepository::findInactiveEntities, pageable);
    }

    // ========== STEP 7: ADD UTILITY/COUNT METHODS ==========

    /**
     * Get total count
     */
    public Long getTotalCount() {
        return yourRepository.countAllEntities(); // Or use count() from base
    }

    /**
     * Check existence by unique field
     */
    public boolean existsByUniqueField(String uniqueField) {
        return yourRepository.existsByUniqueField(uniqueField);
    }

    /**
     * Example: Get count by category
     */
    public Long getCountByCategory(String category) {
        return yourRepository.countByCategory(category);
    }

    // ========== STEP 8: ADD BUSINESS LOGIC METHODS (IF NEEDED) ==========

    /**
     * Example: Custom business operation
     */
    @Transactional
    public YourDTO performBusinessOperation(Long id, String parameter) {
        log.info("Performing business operation on {} with ID: {}", getEntityName(), id);
        
        YourEntity entity = getEntityById(id);
        
        // Perform business logic
        // entity.setSomeField(parameter);
        // entity.doSomething();
        
        YourEntity updated = yourRepository.save(entity);
        return toDTO(updated);
    }
}

/**
 * COMMON PATTERNS AND EXAMPLES
 * 
 * 1. SINGLE UNIQUE FIELD (e.g., email, code)
 * ------------------------------------------
 * @Override
 * @Transactional
 * public YourDTO create(YourDTO dto) {
 *     uniqueFieldValidator.validateUniqueForCreate(
 *         "Email", 
 *         dto.getEmail(), 
 *         repository::existsByEmail
 *     );
 *     return super.create(dto);
 * }
 * 
 * 2. MULTIPLE UNIQUE FIELDS (e.g., multilingual fields)
 * ------------------------------------------------------
 * @Override
 * @Transactional
 * public YourDTO create(YourDTO dto) {
 *     uniqueFieldValidator.validateMultipleForCreate(
 *         UniqueFieldValidation.of("Code", dto.getCode(), repository::existsByCode),
 *         UniqueFieldValidation.of("Name AR", dto.getNameAr(), repository::existsByNameAr),
 *         UniqueFieldValidation.of("Name EN", dto.getNameEn(), repository::existsByNameEn),
 *         UniqueFieldValidation.of("Name FR", dto.getNameFr(), repository::existsByNameFr)
 *     );
 *     return super.create(dto);
 * }
 * 
 * 3. CUSTOM QUERY WITH PARAMETERS
 * --------------------------------
 * public Page<YourDTO> findByDateRange(LocalDate from, LocalDate to, Pageable pageable) {
 *     return executeQuery(p -> repository.findByDateBetween(from, to, p), pageable);
 * }
 * 
 * 4. COMPLEX BUSINESS VALIDATION
 * -------------------------------
 * @Override
 * @Transactional
 * public YourDTO create(YourDTO dto) {
 *     // Validate unique constraints
 *     validateUniqueFields(dto);
 *     
 *     // Additional business validation
 *     if (dto.getStartDate().isAfter(dto.getEndDate())) {
 *         throw new BusinessValidationException("Start date must be before end date");
 *     }
 *     
 *     return super.create(dto);
 * }
 * 
 * 5. CASCADE DELETE WITH VALIDATION
 * ----------------------------------
 * @Override
 * @Transactional
 * public void delete(Long id) {
 *     // Check if entity is used elsewhere
 *     if (repository.isUsedInRelations(id)) {
 *         throw new BusinessValidationException(
 *             getEntityName() + " is being used and cannot be deleted"
 *         );
 *     }
 *     super.delete(id);
 * }
 */
