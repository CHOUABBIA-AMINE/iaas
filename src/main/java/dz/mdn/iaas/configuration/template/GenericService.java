/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: GenericService
 *	@CreatedOn	: 12-09-2025
 *	@Updated	: 01-04-2026
 *
 *	@Type		: Abstract Class
 *	@Layer		: Service Base
 *	@Package	: Configuration / Template
 *
 **/

package dz.mdn.iaas.configuration.template;

import dz.mdn.iaas.configuration.annotation.Auditable;
import dz.mdn.iaas.exception.ResourceNotFoundException;
import dz.mdn.iaas.system.audit.model.Audited.AuditAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Generic Service Base Class with Built-in Audit Logging
 * 
 * Provides common CRUD operations with automatic audit trail.
 * All create, update, and delete operations are automatically logged using @Auditable annotation.
 * 
 * Audit Features:
 * ===============
 * - Automatic logging of all CRUD operations
 * - Captures: entity name, action, user, IP address, timestamp, duration
 * - Records success/failure status with error messages
 * - Tracks before/after values for updates
 * - Logs method parameters and results
 * 
 * Usage Example:
 * <pre>
 * {@code
 * @Service
 * public class EmployeeService extends GenericService<Employee, EmployeeDTO, Long> {
 *     
 *     private final EmployeeRepository repository;
 *     
 *     @Override
 *     protected JpaRepository<Employee, Long> getRepository() {
 *         return repository;
 *     }
 *     
 *     @Override
 *     public String getEntityName() {
 *         return "Employee";
 *     }
 *     
 *     @Override
 *     protected EmployeeDTO toDTO(Employee entity) {
 *         return EmployeeDTO.fromEntity(entity);
 *     }
 *     
 *     @Override
 *     protected Employee toEntity(EmployeeDTO dto) {
 *         return dto.toEntity();
 *     }
 *     
 *     @Override
 *     protected void updateEntityFromDTO(Employee entity, EmployeeDTO dto) {
 *         dto.updateEntity(entity);
 *     }
 *     
 *     // Optional: Override to customize audit behavior
 *     @Override
 *     @Auditable(
 *         entityName = "Employee",
 *         action = AuditAction.CREATE,
 *         module = "ADMINISTRATION",
 *         businessProcess = "EMPLOYEE_ONBOARDING"
 *     )
 *     public EmployeeDTO create(EmployeeDTO dto) {
 *         // Add custom validation
 *         validateUniqueEmail(dto.getEmail());
 *         return super.create(dto);
 *     }
 * }
 * }
 * </pre>
 * 
 * Audit Log Example:
 * ==================
 * When employeeService.create(dto) is called, the system automatically logs:
 * - Action: CREATE
 * - Entity: Employee
 * - Entity ID: 5 (auto-generated)
 * - Username: john.doe
 * - IP Address: 192.168.1.100
 * - Timestamp: 2026-01-04 14:30:45
 * - Duration: 125ms
 * - Status: SUCCESS
 * - Description: "Created new employee with ID 5"
 * - Parameters: [EmployeeDTO{...}]
 * - Result: EmployeeDTO{id=5, ...}
 * 
 * @param <E> Entity type
 * @param <D> DTO type
 * @param <ID> ID type (usually Long)
 * 
 * @see Auditable
 * @see AuditAction
 */
@Slf4j
@Transactional(readOnly = true)
public abstract class GenericService<E, D, ID> {

    // ========== ABSTRACT METHODS (Must be implemented by subclasses) ==========

    /**
     * Get the repository for this service
     * Must be implemented by subclasses
     * 
     * @return JpaRepository instance for the entity
     */
    protected abstract JpaRepository<E, ID> getRepository();

    /**
     * Get the entity name for logging, error messages, and audit trails
     * 
     * Note: Made public to support audit annotation processing
     * 
     * @return Human-readable entity name (e.g., "Employee", "Currency")
     */
    public abstract String getEntityName();

    /**
     * Convert entity to DTO
     * 
     * @param entity Entity to convert
     * @return Corresponding DTO
     */
    protected abstract D toDTO(E entity);

    /**
     * Convert DTO to entity (for create operations)
     * 
     * @param dto DTO to convert
     * @return New entity instance
     */
    protected abstract E toEntity(D dto);

    /**
     * Update entity from DTO (for update operations)
     * Should modify the existing entity with values from DTO
     * 
     * @param entity Existing entity to update
     * @param dto DTO with new values
     */
    protected abstract void updateEntityFromDTO(E entity, D dto);

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new entity
     * 
     * This method is automatically audited with:
     * - Action: CREATE
     * - Entity ID: Auto-extracted from result
     * - Parameters: Input DTO
     * - Result: Created DTO with ID
     * 
     * Override in subclass to:
     * - Add validation
     * - Customize audit module/businessProcess
     * - Add business logic
     * 
     * Example Override:
     * <pre>
     * {@code
     * @Override
     * @Auditable(
     *     entityName = "Employee",
     *     action = AuditAction.CREATE,
     *     module = "ADMINISTRATION",
     *     businessProcess = "EMPLOYEE_REGISTRATION"
     * )
     * public EmployeeDTO create(EmployeeDTO dto) {
     *     validateUniqueEmail(dto.getEmail());
     *     return super.create(dto);
     * }
     * }
     * </pre>
     * 
     * @param dto DTO with entity data
     * @return Created entity as DTO (with generated ID)
     */
    @Transactional
    @Auditable(entityName = "#{target.getEntityName()}", action = AuditAction.CREATE)
    public D create(D dto) {
        log.info("Creating new {}", getEntityName());
        
        E entity = toEntity(dto);
        E savedEntity = getRepository().save(entity);
        
        log.info("Successfully created {} with ID: {}", getEntityName(), getEntityId(savedEntity));
        return toDTO(savedEntity);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get entity by ID
     * Throws ResourceNotFoundException if not found
     * 
     * Note: Read operations are not audited by default to avoid log spam.
     * Add @Auditable in subclass if audit trail is needed for specific reads.
     * 
     * @param id Entity ID
     * @return Entity as DTO
     * @throws ResourceNotFoundException if entity not found
     */
    public D getById(ID id) {
        log.debug("Getting {} with ID: {}", getEntityName(), id);
        
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    getEntityName() + " not found with ID: " + id));
        
        return toDTO(entity);
    }

    /**
     * Get entity by ID (returns entity, not DTO)
     * Useful for internal operations
     * 
     * @param id Entity ID
     * @return Entity instance
     * @throws ResourceNotFoundException if entity not found
     */
    public E getEntityById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    getEntityName() + " not found with ID: " + id));
    }

    /**
     * Find entity by ID (returns Optional)
     * Use when you want to handle absence without exception
     * 
     * @param id Entity ID
     * @return Optional containing DTO if found
     */
    public Optional<D> findById(ID id) {
        log.debug("Finding {} by ID: {}", getEntityName(), id);
        return getRepository().findById(id).map(this::toDTO);
    }

    /**
     * Get all entities with pagination
     * 
     * @param pageable Pagination parameters
     * @return Page of DTOs
     */
    public Page<D> getAll(Pageable pageable) {
        log.debug("Getting all {} with pagination", getEntityName());
        return getRepository().findAll(pageable).map(this::toDTO);
    }

    /**
     * Get all entities without pagination
     * Use with caution for large datasets
     * 
     * @return List of all DTOs
     */
    public List<D> getAll() {
        log.debug("Getting all {} without pagination", getEntityName());
        return getRepository().findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update entity
     * 
     * This method is automatically audited with:
     * - Action: UPDATE
     * - Entity ID: From method parameter
     * - Old Values: Fetched entity
     * - New Values: Updated DTO
     * - Parameters: [ID, DTO]
     * 
     * Override in subclass to:
     * - Add validation
     * - Customize audit module/businessProcess
     * - Add business logic
     * 
     * Example Override:
     * <pre>
     * {@code
     * @Override
     * @Auditable(
     *     entityName = "Employee",
     *     action = AuditAction.UPDATE,
     *     module = "ADMINISTRATION",
     *     businessProcess = "EMPLOYEE_MODIFICATION"
     * )
     * public EmployeeDTO update(Long id, EmployeeDTO dto) {
     *     validateEmailChange(id, dto.getEmail());
     *     return super.update(id, dto);
     * }
     * }
     * </pre>
     * 
     * @param id Entity ID
     * @param dto DTO with updated values
     * @return Updated entity as DTO
     * @throws ResourceNotFoundException if entity not found
     */
    @Transactional
    @Auditable(entityName = "#{target.getEntityName()}", action = AuditAction.UPDATE)
    public D update(ID id, D dto) {
        log.info("Updating {} with ID: {}", getEntityName(), id);
        
        E existingEntity = getEntityById(id);
        updateEntityFromDTO(existingEntity, dto);
        
        E updatedEntity = getRepository().save(existingEntity);
        log.info("Successfully updated {} with ID: {}", getEntityName(), id);
        
        return toDTO(updatedEntity);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete entity by ID
     * 
     * This method is automatically audited with:
     * - Action: DELETE
     * - Entity ID: From method parameter
     * - Old Values: Entity before deletion
     * 
     * Override in subclass to:
     * - Add validation (e.g., check for dependencies)
     * - Customize audit module/businessProcess
     * - Implement soft delete
     * 
     * Example Override:
     * <pre>
     * {@code
     * @Override
     * @Auditable(
     *     entityName = "Employee",
     *     action = AuditAction.DELETE,
     *     module = "ADMINISTRATION",
     *     businessProcess = "EMPLOYEE_TERMINATION"
     * )
     * public void delete(Long id) {
     *     validateNoActiveContracts(id);
     *     super.delete(id);
     * }
     * }
     * </pre>
     * 
     * @param id Entity ID
     * @throws ResourceNotFoundException if entity not found
     */
    @Transactional
    @Auditable(entityName = "#{target.getEntityName()}", action = AuditAction.DELETE)
    public void delete(ID id) {
        log.info("Deleting {} with ID: {}", getEntityName(), id);
        
        E entity = getEntityById(id);
        getRepository().delete(entity);
        
        log.info("Successfully deleted {} with ID: {}", getEntityName(), id);
    }

    /**
     * Delete entity by ID (direct)
     * Slightly more efficient as it doesn't fetch the entity first
     * 
     * This method is automatically audited with:
     * - Action: DELETE
     * - Entity ID: From method parameter
     * - Note: Old values not captured (entity not fetched)
     * 
     * @param id Entity ID
     * @throws ResourceNotFoundException if entity not found
     */
    @Transactional
    @Auditable(entityName = "#{target.getEntityName()}", action = AuditAction.DELETE)
    public void deleteById(ID id) {
        log.info("Deleting {} by ID: {}", getEntityName(), id);
        
        if (!getRepository().existsById(id)) {
            throw new ResourceNotFoundException(getEntityName() + " not found with ID: " + id);
        }
        
        getRepository().deleteById(id);
        log.info("Successfully deleted {} with ID: {}", getEntityName(), id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if entity exists by ID
     * 
     * @param id Entity ID
     * @return true if exists, false otherwise
     */
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    /**
     * Get total count of entities
     * 
     * @return Total number of entities
     */
    public long count() {
        return getRepository().count();
    }

    /**
     * Get entity ID using reflection
     * Override if your entity uses different method name
     * 
     * @param entity Entity instance
     * @return Entity ID or "unknown" if reflection fails
     */
    protected Object getEntityId(E entity) {
        try {
            return entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            log.warn("Could not extract ID from entity: {}", e.getMessage());
            return "unknown";
        }
    }

    // ========== HELPER METHODS FOR SUBCLASSES ==========

    /**
     * Execute a query function and convert results to DTOs
     * Useful for custom query methods in subclasses
     * 
     * Example:
     * <pre>
     * {@code
     * public Page<EmployeeDTO> searchByName(String name, Pageable pageable) {
     *     return executeQuery(p -> repository.searchByName(name, p), pageable);
     * }
     * }
     * </pre>
     * 
     * @param queryFunction Function that executes query and returns Page of entities
     * @param pageable Pagination parameters
     * @return Page of DTOs
     */
    protected Page<D> executeQuery(Function<Pageable, Page<E>> queryFunction, Pageable pageable) {
        return queryFunction.apply(pageable).map(this::toDTO);
    }

    /**
     * Execute a query and return single result as DTO
     * Useful for finder methods in subclasses
     * 
     * Example:
     * <pre>
     * {@code
     * public Optional<EmployeeDTO> findByEmail(String email) {
     *     return executeSingleQuery(repository::findByEmail, email);
     * }
     * }
     * </pre>
     * 
     * @param queryFunction Function that executes query and returns Optional of entity
     * @param param Query parameter
     * @return Optional containing DTO if found
     */
    protected <P> Optional<D> executeSingleQuery(Function<P, Optional<E>> queryFunction, P param) {
        return queryFunction.apply(param).map(this::toDTO);
    }

    /**
     * Execute a query and return list of DTOs
     * Useful for custom list queries in subclasses
     * 
     * Example:
     * <pre>
     * {@code
     * public List<EmployeeDTO> findByDepartment(Long deptId) {
     *     return executeListQuery(repository::findByDepartmentId, deptId);
     * }
     * }
     * </pre>
     * 
     * @param queryFunction Function that executes query and returns List of entities
     * @param param Query parameter
     * @return List of DTOs
     */
    protected <P> List<D> executeListQuery(Function<P, List<E>> queryFunction, P param) {
        return queryFunction.apply(param).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Validate that entity exists
     * Throws exception if not found
     * 
     * @param id Entity ID
     * @throws ResourceNotFoundException if entity not found
     */
    protected void validateExists(ID id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException(getEntityName() + " not found with ID: " + id);
        }
    }

    /**
     * Safe method to get entity or return null
     * Use when null is acceptable
     * 
     * @param id Entity ID
     * @return Entity instance or null
     */
    protected E getEntityByIdOrNull(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    /**
     * Get DTO by ID or return null
     * Use when null is acceptable
     * 
     * @param id Entity ID
     * @return DTO or null
     */
    protected D getDtoByIdOrNull(ID id) {
        return getRepository().findById(id)
                .map(this::toDTO)
                .orElse(null);
    }
}
