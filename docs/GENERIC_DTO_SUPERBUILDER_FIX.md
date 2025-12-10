# GenericDTO @SuperBuilder Fix

**Date:** December 10, 2025  
**Issue:** Lombok @Builder conflict with inherited ID field  
**Solution:** Use @SuperBuilder instead of @Builder  
**Status:** ✅ Fixed

---

## 🐛 The Problem

### Issue Description

When using `@Builder` in a DTO that extends `GenericDTO`, Lombok doesn't include the `id` field from the parent class in the builder.

### Before Fix (BROKEN)

```java
// GenericDTO.java
public abstract class GenericDTO<E> {
    private Long id;  // Parent class field
}

// CurrencyDTO.java
@Data
@Builder  // ❌ PROBLEM: Doesn't include parent's 'id' field
public class CurrencyDTO extends GenericDTO<Currency> {
    private String code;
}

// Usage - COMPILATION ERROR
CurrencyDTO dto = CurrencyDTO.builder()
        .id(1L)        // ❌ ERROR: id() method doesn't exist!
        .code("USD")
        .build();
```

**Error Message:**
```
Cannot resolve method 'id' in 'CurrencyDTOBuilder'
```

---

## ✅ The Solution

### Use Lombok @SuperBuilder

`@SuperBuilder` is designed specifically for inheritance scenarios. It creates builders that include fields from parent classes.

### After Fix (WORKING)

```java
// GenericDTO.java
@Data
@SuperBuilder  // ✅ Use SuperBuilder in parent
@NoArgsConstructor
public abstract class GenericDTO<E> implements Serializable {
    private Long id;  // Will be included in child builders
}

// CurrencyDTO.java
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder  // ✅ Use SuperBuilder in child
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO extends GenericDTO<Currency> {
    private String code;
}

// Usage - WORKS PERFECTLY
CurrencyDTO dto = CurrencyDTO.builder()
        .id(1L)        // ✅ Works! id() method exists
        .code("USD")
        .build();
```

---

## 🔄 Changes Made

### 1. GenericDTO Updated

**Before:**
```java
public abstract class GenericDTO<E> {
    private Long id;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
```

**After:**
```java
@Data                  // ✅ Generates getters/setters
@SuperBuilder          // ✅ Builder for inheritance
@NoArgsConstructor     // ✅ Default constructor
public abstract class GenericDTO<E> implements Serializable {
    private Long id;
    // No manual getters/setters needed
}
```

---

### 2. All DTOs Updated

**Before:**
```java
@Data
@EqualsAndHashCode(callSuper = true)
@Builder               // ❌ Wrong for inheritance
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO extends GenericDTO<Currency> {
```

**After:**
```java
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder          // ✅ Correct for inheritance
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO extends GenericDTO<Currency> {
```

**Change:** `@Builder` → `@SuperBuilder` + import `lombok.experimental.SuperBuilder`

---

## 📊 Updated Files

| File | Change | Status |
|------|--------|--------|
| **GenericDTO.java** | Added @Data, @SuperBuilder, @NoArgsConstructor | ✅ Fixed |
| **CurrencyDTO.java** | @Builder → @SuperBuilder | ✅ Fixed |
| **ApprovalStatusDTO.java** | @Builder → @SuperBuilder | ✅ Fixed |
| **ProcurementDirectorDTO.java** | @Builder → @SuperBuilder | ✅ Fixed |
| **ProcurementNatureDTO.java** | @Builder → @SuperBuilder | ✅ Fixed |
| **ProcurementStatusDTO.java** | @Builder → @SuperBuilder | ✅ Fixed |

---

## ✅ How It Works Now

### Builder Pattern with Inheritance

```java
// 1. Build with all fields (including inherited 'id')
CurrencyDTO dto = CurrencyDTO.builder()
        .id(1L)                    // ✅ From GenericDTO
        .code("USD")               // ✅ From CurrencyDTO
        .designationFr("Dollar")   // ✅ From CurrencyDTO
        .build();

// 2. Use in service
public static CurrencyDTO fromEntity(Currency entity) {
    return CurrencyDTO.builder()
            .id(entity.getId())              // ✅ Works!
            .code(entity.getCode())
            .designationFr(entity.getDesignationFr())
            .build();
}

// 3. JSON serialization/deserialization works
{
  "id": 1,
  "code": "USD",
  "designationFr": "Dollar"
}
```

---

## 🔍 @Builder vs @SuperBuilder

### @Builder (For Simple Classes)

**Use when:** Class doesn't extend another class

```java
@Builder
public class SimpleDTO {
    private Long id;
    private String name;
}

// Builder includes all fields
SimpleDTO dto = SimpleDTO.builder()
        .id(1L)
        .name("Test")
        .build();
```

---

### @SuperBuilder (For Inheritance)

**Use when:** Class extends another class with fields

```java
// Parent
@SuperBuilder
public class ParentDTO {
    private Long id;
}

// Child
@SuperBuilder
public class ChildDTO extends ParentDTO {
    private String name;
}

// Builder includes parent AND child fields
ChildDTO dto = ChildDTO.builder()
        .id(1L)        // ✅ From parent
        .name("Test")  // ✅ From child
        .build();
```

---

## ⚠️ Important Rules

### 1. Both Parent and Child Must Use @SuperBuilder

```java
// ✅ CORRECT
@SuperBuilder
public class ParentDTO { ... }

@SuperBuilder
public class ChildDTO extends ParentDTO { ... }

// ❌ WRONG - Won't compile
@Builder  // Wrong!
public class ParentDTO { ... }

@SuperBuilder
public class ChildDTO extends ParentDTO { ... }
```

---

### 2. Need @NoArgsConstructor for Jackson

```java
@SuperBuilder
@NoArgsConstructor  // ✅ Required for JSON deserialization
@AllArgsConstructor // ✅ Required for builder to work with all fields
public class CurrencyDTO extends GenericDTO<Currency> {
```

Without `@NoArgsConstructor`, Jackson (JSON library) can't create instances.

---

### 3. Import from lombok.experimental

```java
import lombok.Builder;              // ❌ Wrong import
import lombok.experimental.SuperBuilder; // ✅ Correct import
```

---

## 🧪 Testing

### Test 1: Builder with ID
```java
@Test
void testBuilderWithId() {
    CurrencyDTO dto = CurrencyDTO.builder()
            .id(1L)
            .code("USD")
            .build();
    
    assertEquals(1L, dto.getId());
    assertEquals("USD", dto.getCode());
}
```

### Test 2: fromEntity()
```java
@Test
void testFromEntity() {
    Currency entity = new Currency();
    entity.setId(1L);
    entity.setCode("USD");
    
    CurrencyDTO dto = CurrencyDTO.fromEntity(entity);
    
    assertEquals(1L, dto.getId());
    assertEquals("USD", dto.getCode());
}
```

### Test 3: JSON Serialization
```java
@Test
void testJsonSerialization() throws Exception {
    CurrencyDTO dto = CurrencyDTO.builder()
            .id(1L)
            .code("USD")
            .build();
    
    String json = objectMapper.writeValueAsString(dto);
    assertTrue(json.contains("\"id\":1"));
    assertTrue(json.contains("\"code\":\"USD\""));
}
```

---

## 📚 Additional Resources

### Lombok Documentation
- [@SuperBuilder Documentation](https://projectlombok.org/features/experimental/SuperBuilder)
- [@Builder Documentation](https://projectlombok.org/features/Builder)

### When to Use Each

| Scenario | Use |
|----------|-----|
| Simple class, no inheritance | `@Builder` |
| Class extends another class | `@SuperBuilder` |
| Parent class with fields | `@SuperBuilder` |
| Child class extends parent | `@SuperBuilder` |

---

## ✅ Benefits

### 1. Builder Works Correctly
```java
// All fields available in builder
CurrencyDTO dto = CurrencyDTO.builder()
        .id(1L)                  // ✅ From GenericDTO
        .code("USD")             // ✅ From CurrencyDTO
        .designationFr("Dollar") // ✅ From CurrencyDTO
        .build();
```

### 2. Type Safety Maintained
```java
// Compile-time type checking
CurrencyDTO dto = CurrencyDTO.builder()
        .id("wrong")  // ❌ Compilation error: String not assignable to Long
        .build();
```

### 3. Clean Code
```java
// No manual builder implementation needed
// No boilerplate code
// Just annotations
```

### 4. Works with Jackson
```java
// JSON deserialization works
String json = "{\"id\":1,\"code\":\"USD\"}";
CurrencyDTO dto = objectMapper.readValue(json, CurrencyDTO.class);
```

---

## 🎉 Summary

**Problem:** `@Builder` doesn't work with inheritance  
**Solution:** Use `@SuperBuilder` in both parent and child classes  
**Result:** Builder pattern works perfectly with inherited fields  

**Changes:**
- ✅ GenericDTO: Added `@Data`, `@SuperBuilder`, `@NoArgsConstructor`
- ✅ All DTOs: Changed `@Builder` to `@SuperBuilder`
- ✅ All DTOs: Added import `lombok.experimental.SuperBuilder`
- ✅ Builder now includes `id` field from GenericDTO
- ✅ All tests pass
- ✅ JSON serialization/deserialization works

**Impact:**
- ✅ No code changes needed in services or controllers
- ✅ No breaking changes to API
- ✅ Existing code continues to work
- ✅ Builder pattern now fully functional

---

**Created:** December 10, 2025  
**Status:** ✅ Fixed and Tested  
**Compatibility:** All DTOs updated, fully backward compatible
