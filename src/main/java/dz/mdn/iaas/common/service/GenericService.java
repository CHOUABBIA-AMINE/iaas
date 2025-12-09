/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: GenericService
 *	@CreatedOn	: 12-09-2025
 *
 *	@Type		: Abstract Class
 *	@Layer		: Service Base
 *	@Package	: Common / Service
 *
 **/

package dz.mdn.iaas.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.function.Function;

/**
 * Generic Service Base Class
 * Provides common CRUD operations to reduce code duplication across services
 * 
 * @param <E> Entity type
 * @param <D> DTO type
 * @param <ID> ID type (usually Long)
 */
@Slf4j
@Transactional(readOnly = true)
public abstract class GenericService<E, D, ID> {

    /**
     * Get the repository for this service
     * Must be implemented by subclasses
     */
    protected abstract JpaRepository<E, ID> getRepository();

    /**
     * Get the entity name for logging and error messages
     */
    protected abstract String getEntityName();

    /**
     * Convert entity to DTO
     */
    protected abstract D toDTO(E entity);

    /**
     * Convert DTO to entity
     */
    protected abstract E toEntity(D dto);

    /**
     * Update entity from DTO
     */
    protected abstract void updateEntityFromDTO(E entity, D dto);

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new entity
     */
    @Transactional
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
     */
    public E getEntityById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    getEntityName() + " not found with ID: " + id));
    }

    /**
     * Find entity by ID (returns Optional)
     */
    public Optional<D> findById(ID id) {
        log.debug("Finding {} by ID: {}", getEntityName(), id);
        return getRepository().findById(id).map(this::toDTO);
    }

    /**
     * Get all entities with pagination
     */
    public Page<D> getAll(Pageable pageable) {
        log.debug("Getting all {} with pagination", getEntityName());
        return getRepository().findAll(pageable).map(this::toDTO);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update entity
     */
    @Transactional
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
     */
    @Transactional
    public void delete(ID id) {
        log.info("Deleting {} with ID: {}", getEntityName(), id);
        
        E entity = getEntityById(id);
        getRepository().delete(entity);
        
        log.info("Successfully deleted {} with ID: {}", getEntityName(), id);
    }

    /**
     * Delete entity by ID (direct)
     */
    @Transactional
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
     */
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    /**
     * Get total count
     */
    public long count() {
        return getRepository().count();
    }

    /**
     * Get entity ID (override if ID field name differs)
     */
    protected Object getEntityId(E entity) {
        try {
            return entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            return "unknown";
        }
    }

    /**
     * Execute a query and convert results to DTOs
     */
    protected Page<D> executeQuery(Function<Pageable, Page<E>> queryFunction, Pageable pageable) {
        return queryFunction.apply(pageable).map(this::toDTO);
    }

    /**
     * Execute a query and return single result as DTO
     */
    protected Optional<D> executeSingleQuery(Function<Object, Optional<E>> queryFunction, Object param) {
        return queryFunction.apply(param).map(this::toDTO);
    }
}
