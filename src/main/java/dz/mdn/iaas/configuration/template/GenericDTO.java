/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: GenericDTO
 *	@CreatedOn	: 12-10-2025
 *
 *	@Type		: Abstract Class
 *	@Layer		: DTO Base
 *	@Package	: Configuration / Template
 *
 **/

package dz.mdn.iaas.configuration.template;

import java.io.Serializable;

/**
 * Generic DTO Base Class
 * Provides common structure and contract for all DTOs
 * 
 * Usage Example:
 * <pre>
 * {@code
 * @Data
 * @EqualsAndHashCode(callSuper = true)
 * @Builder
 * @NoArgsConstructor
 * @AllArgsConstructor
 * @JsonInclude(JsonInclude.Include.NON_NULL)
 * public class CurrencyDTO extends GenericDTO<Currency> {
 *     
 *     @NotBlank
 *     private String code;
 *     
 *     @NotBlank
 *     private String designation;
 *     
 *     @Override
 *     public Currency toEntity() {
 *         Currency entity = new Currency();
 *         entity.setId(getId());
 *         entity.setCode(this.code);
 *         entity.setDesignation(this.designation);
 *         return entity;
 *     }
 *     
 *     @Override
 *     public void updateEntity(Currency entity) {
 *         if (this.code != null) {
 *             entity.setCode(this.code);
 *         }
 *         if (this.designation != null) {
 *             entity.setDesignation(this.designation);
 *         }
 *     }
 *     
 *     public static CurrencyDTO fromEntity(Currency entity) {
 *         if (entity == null) return null;
 *         
 *         return CurrencyDTO.builder()
 *                 .id(entity.getId())
 *                 .code(entity.getCode())
 *                 .designation(entity.getDesignation())
 *                 .build();
 *     }
 * }
 * }
 * </pre>
 * 
 * @param <E> Entity type
 */
public abstract class GenericDTO<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Entity ID
     * Common to all entities
     */
    private Long id;

    // ========== GETTERS AND SETTERS ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ========== ABSTRACT METHODS ==========

    /**
     * Convert DTO to entity (for create operations)
     * Must be implemented by subclass
     * 
     * @return New entity instance
     */
    public abstract E toEntity();

    /**
     * Update entity from DTO (for update operations)
     * Should modify the existing entity with values from DTO
     * Must be implemented by subclass
     * 
     * @param entity Existing entity to update
     */
    public abstract void updateEntity(E entity);

    // ========== UTILITY METHODS ==========

    /**
     * Check if DTO represents a new entity (no ID)
     * 
     * @return true if ID is null (new entity)
     */
    public boolean isNew() {
        return this.id == null;
    }

    /**
     * Check if DTO represents an existing entity (has ID)
     * 
     * @return true if ID is not null (existing entity)
     */
    public boolean isExisting() {
        return this.id != null;
    }

    /**
     * Get entity type name for logging/debugging
     * Can be overridden by subclass for custom name
     * 
     * @return Entity type name
     */
    public String getEntityTypeName() {
        return this.getClass().getSimpleName().replace("DTO", "");
    }

    // ========== OBJECT METHODS ==========

    /**
     * Default toString implementation
     * Can be overridden by Lombok @ToString or custom implementation
     */
    @Override
    public String toString() {
        return getEntityTypeName() + "DTO{id=" + id + "}";
    }

    /**
     * Default equals based on ID
     * Override with Lombok @EqualsAndHashCode if needed
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        GenericDTO<?> that = (GenericDTO<?>) obj;
        return id != null && id.equals(that.id);
    }

    /**
     * Default hashCode based on ID
     * Override with Lombok @EqualsAndHashCode if needed
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
