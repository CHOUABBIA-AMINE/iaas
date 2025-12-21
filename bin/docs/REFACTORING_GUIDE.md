# Service Layer Refactoring Guide

## Overview

This guide documents the refactoring improvements made to reduce code duplication in the service layer of the IAAS application.

## Problem Statement

### Before Refactoring

The original `CurrencyService` had **500+ lines of code** with significant duplication:

- Manual CRUD operations repeated in every service
- Repetitive unique constraint validation logic (100+ lines)
- Manual field-by-field validation
- Duplicate entity-to-DTO conversion code
- Inconsistent exception handling

**Example of duplicated validation code:**

```java
// Repeated 7 times for each unique field
if (excludeId == null) {
    if (currencyRepository.existsByCode(currencyDTO.getCode())) {
        throw new RuntimeException("Currency with code '" + currencyDTO.getCode() + "' already exists");
    }
} else {
    if (currencyRepository.existsByCodeAndIdNot(currencyDTO.getCode(), excludeId)) {
        throw new RuntimeException("Another currency with code '" + currencyDTO.getCode() + "' already exists");
    }
}
```

## Solution: Base Classes and Validators

### Architecture

```
┌─────────────────────────────────────┐
│     GenericService<E, D, ID>        │
│  (Common CRUD Operations)           │
│  - create(), update(), delete()     │
│  - getById(), getAll()              │
│  - executeQuery()                   │
└─────────────┬───────────────────────┘
              │ extends
              │
┌─────────────▼───────────────────────┐
│   CurrencyServiceRefactored         │
│  (Currency-specific logic)          │
│  - Custom queries                   │
│  - Validation orchestration         │
└─────────────────────────────────────┘

┌─────────────────────────────────────┐
│    UniqueFieldValidator             │
│  (Centralized validation)           │
│  - validateUniqueForCreate()        │
│  - validateUniqueForUpdate()        │
│  - validateMultipleForCreate()      │
└─────────────────────────────────────┘
```

### New Components

#### 1. GenericService (Base Class)

**Location:** `src/main/java/dz/mdn/iaas/common/service/GenericService.java`

**Purpose:** Provides common CRUD operations for all entities

**Key Methods:**
- `create(D dto)` - Create new entity
- `update(ID id, D dto)` - Update existing entity
- `delete(ID id)` - Delete entity
- `getById(ID id)` - Get entity by ID
- `getAll(Pageable pageable)` - Get all with pagination
- `executeQuery()` - Helper for custom queries

**Abstract Methods to Implement:**
```java
protected abstract JpaRepository<E, ID> getRepository();
protected abstract String getEntityName();
protected abstract D toDTO(E entity);
protected abstract E toEntity(D dto);
protected abstract void updateEntityFromDTO(E entity, D dto);
```

#### 2. UniqueFieldValidator

**Location:** `src/main/java/dz/mdn/iaas/common/validator/UniqueFieldValidator.java`

**Purpose:** Centralize unique field validation logic

**Key Methods:**
- `validateUniqueForCreate()` - Single field validation for create
- `validateUniqueForUpdate()` - Single field validation for update
- `validateMultipleForCreate()` - Batch validation for multiple fields
- `validateMultipleForUpdate()` - Batch validation for update

**Helper Classes:**
- `UniqueFieldValidation<T>` - For create operations
- `UniqueFieldUpdateValidation<T, ID>` - For update operations

## Refactored Example: CurrencyServiceRefactored

### After Refactoring

**Location:** `src/main/java/dz/mdn/iaas/business/core/service/CurrencyServiceRefactored.java`

**Lines of Code:** ~230 lines (was 500+ lines)

**Code Reduction:** 60% reduction

### Key Improvements

#### 1. Simplified CRUD Operations

**Before:**
```java
public CurrencyDTO createCurrency(CurrencyDTO currencyDTO) {
    log.info("Creating currency...");
    
    // Validate required fields (20 lines)
    validateRequiredFields(currencyDTO, "create");
    
    // Check unique constraints (100+ lines)
    validateAllUniqueConstraints(currencyDTO, null);
    
    // Manual entity creation (10 lines)
    Currency currency = new Currency();
    currency.setCode(currencyDTO.getCode());
    currency.setDesignationAr(currencyDTO.getDesignationAr());
    // ... more setters
    
    Currency savedCurrency = currencyRepository.save(currency);
    return CurrencyDTO.fromEntity(savedCurrency);
}
```

**After:**
```java
@Override
@Transactional
public CurrencyDTO create(CurrencyDTO dto) {
    log.info("Creating currency: code={}", dto.getCode());
    validateUniqueFieldsForCreate(dto);
    return super.create(dto); // Base class handles the rest
}
```

#### 2. Centralized Validation

**Before:** 100+ lines of repetitive if-else statements

**After:**
```java
private void validateUniqueFieldsForCreate(CurrencyDTO dto) {
    uniqueFieldValidator.validateMultipleForCreate(
        UniqueFieldValidation.of("Code", dto.getCode(), currencyRepository::existsByCode),
        UniqueFieldValidation.of("Arabic designation", dto.getDesignationAr(), currencyRepository::existsByDesignationAr),
        UniqueFieldValidation.of("English designation", dto.getDesignationEn(), currencyRepository::existsByDesignationEn),
        UniqueFieldValidation.of("French designation", dto.getDesignationFr(), currencyRepository::existsByDesignationFr),
        UniqueFieldValidation.of("Arabic acronym", dto.getAcronymAr(), currencyRepository::existsByAcronymAr),
        UniqueFieldValidation.of("English acronym", dto.getAcronymEn(), currencyRepository::existsByAcronymEn),
        UniqueFieldValidation.of("French acronym", dto.getAcronymFr(), currencyRepository::existsByAcronymFr)
    );
}
```

#### 3. Simplified Query Methods

**Before:**
```java
public Page<CurrencyDTO> searchCurrencies(String searchTerm, Pageable pageable) {
    log.debug("Searching currencies with term: {}", searchTerm);
    if (searchTerm == null || searchTerm.trim().isEmpty()) {
        return getAllCurrencies(pageable);
    }
    Page<Currency> currencies = currencyRepository.searchByAnyField(searchTerm.trim(), pageable);
    return currencies.map(CurrencyDTO::fromEntity);
}
```

**After:**
```java
public Page<CurrencyDTO> searchCurrencies(String searchTerm, Pageable pageable) {
    if (searchTerm == null || searchTerm.trim().isEmpty()) {
        return getAll(pageable);
    }
    return executeQuery(p -> currencyRepository.searchByAnyField(searchTerm.trim(), p), pageable);
}
```

## Migration Guide

### Step 1: Update DTOs

Add helper methods to your DTO:

```java
public class YourDTO {
    // ... fields
    
    public static YourDTO fromEntity(YourEntity entity) {
        // conversion logic
    }
    
    public YourEntity toEntity() {
        // conversion logic
    }
    
    public void updateEntity(YourEntity entity) {
        // update logic
    }
}
```

### Step 2: Create Refactored Service

Extend `GenericService`:

```java
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class YourServiceRefactored extends GenericService<YourEntity, YourDTO, Long> {
    
    private final YourRepository repository;
    private final UniqueFieldValidator validator;
    
    @Override
    protected JpaRepository<YourEntity, Long> getRepository() {
        return repository;
    }
    
    @Override
    protected String getEntityName() {
        return "YourEntity";
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
    
    // Add custom methods as needed
}
```

### Step 3: Implement Validation

Use `UniqueFieldValidator` for unique constraints:

```java
private void validateUniqueFieldsForCreate(YourDTO dto) {
    validator.validateMultipleForCreate(
        UniqueFieldValidation.of("field1", dto.getField1(), repository::existsByField1),
        UniqueFieldValidation.of("field2", dto.getField2(), repository::existsByField2)
    );
}

private void validateUniqueFieldsForUpdate(YourDTO dto, Long id) {
    validator.validateMultipleForUpdate(
        UniqueFieldUpdateValidation.of("field1", dto.getField1(), id, repository::existsByField1AndIdNot),
        UniqueFieldUpdateValidation.of("field2", dto.getField2(), id, repository::existsByField2AndIdNot)
    );
}
```

### Step 4: Override Create/Update

Add validation to create and update:

```java
@Override
@Transactional
public YourDTO create(YourDTO dto) {
    validateUniqueFieldsForCreate(dto);
    return super.create(dto);
}

@Override
@Transactional
public YourDTO update(Long id, YourDTO dto) {
    validateUniqueFieldsForUpdate(dto, id);
    return super.update(id, dto);
}
```

## Benefits

### Code Reduction
- **60% reduction** in service layer code
- Eliminated 100+ lines of repetitive validation
- Reduced CRUD boilerplate by 80%

### Maintainability
- ✅ Single source of truth for CRUD operations
- ✅ Centralized validation logic
- ✅ Consistent error handling
- ✅ Easier to add new services

### Testing
- ✅ Base class tested once, benefits all services
- ✅ Validation logic tested in one place
- ✅ Reduced test duplication

### Consistency
- ✅ All services follow same patterns
- ✅ Uniform exception handling
- ✅ Standardized logging

## Applying to Other Services

The same pattern can be applied to:
- `ApprovalStatusService`
- `RealizationDirectorService`
- `RealizationNatureService`
- `RealizationStatusService`
- All other services in business and common modules

## Best Practices

### 1. Bean Validation
Use `@Valid` annotation in controllers:
```java
@PostMapping
public ResponseEntity<CurrencyDTO> create(@Valid @RequestBody CurrencyDTO dto) {
    return ResponseEntity.ok(service.create(dto));
}
```

### 2. Custom Exceptions
Use domain-specific exceptions:
- `ResourceNotFoundException` - Entity not found
- `BusinessValidationException` - Validation failures
- `DuplicateResourceException` - Unique constraint violations

### 3. Transaction Management
- Use `@Transactional(readOnly = true)` at class level
- Override with `@Transactional` for write operations

### 4. Logging Levels
- `DEBUG` for routine operations (get, search)
- `INFO` for business events (create, update, delete)
- `WARN` for validation failures
- `ERROR` for unexpected errors

## Testing the Refactored Code

### Unit Test Example
```java
@ExtendWith(MockitoExtension.class)
class CurrencyServiceRefactoredTest {
    
    @Mock
    private CurrencyRepository repository;
    
    @Mock
    private UniqueFieldValidator validator;
    
    @InjectMocks
    private CurrencyServiceRefactored service;
    
    @Test
    void create_shouldValidateAndSave() {
        CurrencyDTO dto = CurrencyDTO.builder()
            .code("USD")
            .designationFr("Dollar")
            .build();
        
        Currency entity = dto.toEntity();
        when(repository.save(any())).thenReturn(entity);
        
        CurrencyDTO result = service.create(dto);
        
        verify(validator).validateMultipleForCreate(any());
        verify(repository).save(any());
        assertNotNull(result);
    }
}
```

## Future Improvements

1. **MapStruct Integration**
   - Replace manual DTO conversions with MapStruct
   - Further reduce boilerplate code

2. **Specification Pattern**
   - Replace multiple finder methods with specifications
   - More flexible query building

3. **Caching Layer**
   - Add caching for reference data
   - Improve read performance

4. **Audit Logging**
   - Centralize audit logging in base class
   - Track all entity changes

## Conclusion

The refactored service layer demonstrates significant improvements in:
- Code maintainability (60% less code)
- Consistency across services
- Testing efficiency
- Developer productivity

This pattern should be adopted for all new services and gradually applied to existing services.

## Questions?

For questions or suggestions, contact the development team.
