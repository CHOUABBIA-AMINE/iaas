# GenericDTO Guide

**Created:** December 10, 2025  
**Location:** `src/main/java/dz/mdn/iaas/configuration/template/GenericDTO.java`  
**Status:** ✅ Ready for use

## 🎯 Overview

`GenericDTO` is an abstract base class that provides common structure and contract for all DTOs. It eliminates duplicate code across DTOs and enforces consistent patterns.

---

## 🚀 Quick Start

### Basic Usage

```java
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyDTO extends GenericDTO<Currency> {

    @NotBlank
    private String code;
    
    @NotBlank
    private String designation;

    @Override
    public Currency toEntity() {
        Currency entity = new Currency();
        entity.setId(getId());  // Get ID from GenericDTO
        entity.setCode(this.code);
        entity.setDesignation(this.designation);
        return entity;
    }

    @Override
    public void updateEntity(Currency entity) {
        if (this.code != null) entity.setCode(this.code);
        if (this.designation != null) entity.setDesignation(this.designation);
    }

    public static CurrencyDTO fromEntity(Currency entity) {
        if (entity == null) return null;
        return CurrencyDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .designation(entity.getDesignation())
                .build();
    }
}
```

---

## 📦 What GenericDTO Provides

### 1. Common ID Field
```java
private Long id;

public Long getId() { ... }
public void setId(Long id) { ... }
```
**Benefit:** Every DTO has an ID without declaring it

---

### 2. Abstract Methods (Must Implement)

#### toEntity()
```java
public abstract E toEntity();
```
**Purpose:** Convert DTO to entity for create operations  
**Used by:** `GenericService.toEntity()`

#### updateEntity()
```java
public abstract void updateEntity(E entity);
```
**Purpose:** Update existing entity from DTO  
**Used by:** `GenericService.updateEntityFromDTO()`

---

### 3. Utility Methods (Built-in)

#### Check if New Entity
```java
public boolean isNew() {
    return this.id == null;
}
```
**Usage:**
```java
if (dto.isNew()) {
    // Handle new entity
}
```

#### Check if Existing Entity
```java
public boolean isExisting() {
    return this.id != null;
}
```
**Usage:**
```java
if (dto.isExisting()) {
    // Handle existing entity update
}
```

#### Get Entity Type Name
```java
public String getEntityTypeName() {
    return this.getClass().getSimpleName().replace("DTO", "");
}
```
**Usage:**
```java
log.info("Processing {}", dto.getEntityTypeName());  // "Currency"
```

---

### 4. Object Methods (Default Implementation)

#### toString()
```java
@Override
public String toString() {
    return getEntityTypeName() + "DTO{id=" + id + "}";
}
```
**Output:** `"CurrencyDTO{id=123}"`

#### equals()
```java
@Override
public boolean equals(Object obj) {
    // Compares based on ID
}
```
**Note:** Override with `@EqualsAndHashCode(callSuper = true)` for field-based comparison

#### hashCode()
```java
@Override
public int hashCode() {
    return id != null ? id.hashCode() : 0;
}
```
**Note:** Override with `@EqualsAndHashCode(callSuper = true)` for field-based hashing

---

## 🛠️ Implementation Guide

### Step 1: Extend GenericDTO

```java
public class EntityDTO extends GenericDTO<Entity> {
    // Your fields here
}
```

**Type Parameter:**
- `<Entity>` = The entity class this DTO represents

---

### Step 2: Add Lombok Annotations

```java
@Data                                    // Getters, setters, toString, equals, hashCode
@EqualsAndHashCode(callSuper = true)    // Include superclass (GenericDTO) in equals/hashCode
@Builder                                 // Builder pattern
@NoArgsConstructor                       // Default constructor (required for Jackson)
@AllArgsConstructor                      // All-args constructor (required for Builder)
@JsonInclude(JsonInclude.Include.NON_NULL)  // Don't serialize null fields
public class EntityDTO extends GenericDTO<Entity> {
```

**Important:** 
- Always use `@EqualsAndHashCode(callSuper = true)` to include ID from GenericDTO
- Without it, equals/hashCode will ignore the ID field

---

### Step 3: Add Fields with Validation

```java
@NotBlank(message = "Code is required")
@Size(max = 20, message = "Code must not exceed 20 characters")
private String code;

@Size(max = 200, message = "Designation must not exceed 200 characters")
private String designation;
```

**Note:** Don't declare `id` field - it's inherited from GenericDTO

---

### Step 4: Implement Abstract Methods

#### Implement toEntity()
```java
@Override
public Entity toEntity() {
    Entity entity = new Entity();
    entity.setId(getId());  // Get ID from GenericDTO
    entity.setField1(this.field1);
    entity.setField2(this.field2);
    return entity;
}
```

#### Implement updateEntity()
```java
@Override
public void updateEntity(Entity entity) {
    if (this.field1 != null) {
        entity.setField1(this.field1);
    }
    if (this.field2 != null) {
        entity.setField2(this.field2);
    }
}
```

---

### Step 5: Add fromEntity() Factory Method

```java
public static EntityDTO fromEntity(Entity entity) {
    if (entity == null) return null;
    
    return EntityDTO.builder()
            .id(entity.getId())  // Set ID explicitly
            .field1(entity.getField1())
            .field2(entity.getField2())
            .build();
}
```

**Note:** Must set `id` explicitly in builder since it's inherited

---

## 📋 Complete Example

```java
/**
 * Currency DTO - Extends GenericDTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyDTO extends GenericDTO<Currency> {

    // ========== FIELDS ==========
    
    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Designation is required")
    @Size(max = 50, message = "Designation must not exceed 50 characters")
    private String designation;

    // ========== GENERIC DTO IMPLEMENTATION ==========

    @Override
    public Currency toEntity() {
        Currency currency = new Currency();
        currency.setId(getId());
        currency.setCode(this.code);
        currency.setDesignation(this.designation);
        return currency;
    }

    @Override
    public void updateEntity(Currency currency) {
        if (this.code != null) {
            currency.setCode(this.code);
        }
        if (this.designation != null) {
            currency.setDesignation(this.designation);
        }
    }

    // ========== FACTORY METHOD ==========

    public static CurrencyDTO fromEntity(Currency currency) {
        if (currency == null) return null;
        
        return CurrencyDTO.builder()
                .id(currency.getId())
                .code(currency.getCode())
                .designation(currency.getDesignation())
                .build();
    }
}
```

---

## 🔗 Integration with GenericService

```java
@Service
public class CurrencyService extends GenericService<Currency, CurrencyDTO, Long> {

    @Override
    protected CurrencyDTO toDTO(Currency entity) {
        return CurrencyDTO.fromEntity(entity);  // Uses static factory method
    }

    @Override
    protected Currency toEntity(CurrencyDTO dto) {
        return dto.toEntity();  // Uses GenericDTO abstract method
    }

    @Override
    protected void updateEntityFromDTO(Currency entity, CurrencyDTO dto) {
        dto.updateEntity(entity);  // Uses GenericDTO abstract method
    }
}
```

---

## ✅ Benefits

### 1. Consistency
- ✅ All DTOs have same structure
- ✅ All DTOs have ID field
- ✅ All DTOs implement same methods
- ✅ Predictable patterns

### 2. Type Safety
- ✅ Generic type parameter enforces entity type
- ✅ Compile-time checking
- ✅ No casting needed

### 3. Code Reduction
- ✅ No duplicate ID declarations
- ✅ No duplicate utility methods
- ✅ No duplicate equals/hashCode logic

### 4. Maintainability
- ✅ Single place to add common DTO functionality
- ✅ Easy to enhance all DTOs at once
- ✅ Clear contract for all DTOs

---

## 🔍 Comparison

### Before GenericDTO
```java
@Data
@Builder
public class CurrencyDTO {
    private Long id;  // Duplicate in every DTO
    private String code;
    private String designation;
    
    // All 3 methods required
    public static CurrencyDTO fromEntity(Currency c) { ... }
    public Currency toEntity() { ... }
    public void updateEntity(Currency c) { ... }
    
    // No utility methods
    // No consistent equals/hashCode
}
```

### After GenericDTO
```java
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class CurrencyDTO extends GenericDTO<Currency> {
    // No ID field - inherited from GenericDTO ✅
    private String code;
    private String designation;
    
    // Only 2 abstract methods required
    @Override
    public Currency toEntity() { ... }
    
    @Override
    public void updateEntity(Currency c) { ... }
    
    // Factory method (standard pattern)
    public static CurrencyDTO fromEntity(Currency c) { ... }
    
    // Utility methods inherited ✅
    // - isNew()
    // - isExisting()
    // - getEntityTypeName()
    
    // Consistent equals/hashCode inherited ✅
}
```

---

## 🧰 Common Patterns

### Pattern 1: Conditional Logic Based on New/Existing

```java
// In service or controller
public void processDTO(EntityDTO dto) {
    if (dto.isNew()) {
        // Create new entity
        service.create(dto);
    } else {
        // Update existing entity
        service.update(dto.getId(), dto);
    }
}
```

### Pattern 2: Logging with Entity Type

```java
public void logDTO(GenericDTO<?> dto) {
    log.info("Processing {} with ID: {}", 
             dto.getEntityTypeName(), 
             dto.getId());
}
```

### Pattern 3: Generic DTO Handler

```java
public <E, D extends GenericDTO<E>> E convertToEntity(D dto) {
    E entity = dto.toEntity();
    log.info("Converted {} to entity", dto.getEntityTypeName());
    return entity;
}
```

---

## ⚠️ Important Notes

### 1. Always Use @EqualsAndHashCode(callSuper = true)

```java
// ❌ BAD - Will ignore ID in equals/hashCode
@Data
public class CurrencyDTO extends GenericDTO<Currency> {

// ✅ GOOD - Includes ID in equals/hashCode
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrencyDTO extends GenericDTO<Currency> {
```

### 2. Set ID in Builder

```java
// ✅ CORRECT - Set ID explicitly
public static CurrencyDTO fromEntity(Currency entity) {
    return CurrencyDTO.builder()
            .id(entity.getId())  // Must set ID
            .code(entity.getCode())
            .build();
}
```

### 3. Use getId() Instead of this.id

```java
// ✅ GOOD - Use getter
@Override
public Currency toEntity() {
    Currency entity = new Currency();
    entity.setId(getId());  // Call getter from GenericDTO
    return entity;
}

// ❌ BAD - Direct field access won't work
@Override
public Currency toEntity() {
    Currency entity = new Currency();
    entity.setId(this.id);  // Compilation error - id is private in GenericDTO
    return entity;
}
```

---

## 📊 Updated DTOs

All Business DTOs now extend GenericDTO:

| DTO | Extends | Fields (excluding ID) | Methods |
|-----|---------|----------------------|----------|
| **CurrencyDTO** | GenericDTO<Currency> | 7 | 3 |
| **ApprovalStatusDTO** | GenericDTO<ApprovalStatus> | 3 | 3 |
| **ProcurementDirectorDTO** | GenericDTO<ProcurementDirector> | 1 | 3 |
| **ProcurementNatureDTO** | GenericDTO<ProcurementNature> | 4 | 3 |
| **ProcurementStatusDTO** | GenericDTO<ProcurementStatus> | 4 | 3 |

---

## 🎉 Summary

GenericDTO provides:

✅ **Common ID field** for all DTOs  
✅ **Abstract methods** enforcing contract  
✅ **Utility methods** (isNew, isExisting, getEntityTypeName)  
✅ **Default equals/hashCode** based on ID  
✅ **Type safety** with generics  
✅ **Consistent structure** across all DTOs  
✅ **Less boilerplate** code  
✅ **Easy to maintain** and extend  

**All DTOs now follow the same proven pattern!** 🚀

---

**Created:** December 10, 2025  
**Location:** `configuration/template/GenericDTO.java`  
**Status:** ✅ Production Ready  
**Adoption:** 100% (5/5 DTOs)
