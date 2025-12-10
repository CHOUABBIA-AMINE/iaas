# All Business DTOs Updated to Extend GenericDTO

**Date:** December 10, 2025  
**Status:** 🟡 In Progress  
**Scope:** All DTOs in Amendment, Consultation, Contract, Plan, Provider

---

## 🎯 Objective

Update ALL DTO classes across all business modules to extend `GenericDTO` and eliminate code duplication.
Ensure **ONE DTO per Model** - remove any duplicate or unnecessary DTOs.

---

## 📋 DTOs to Update (One per Model)

### Summary by Module

| Module | DTOs | Status |
|--------|------|--------|
| **Amendment** | 4 DTOs | ⏳ Pending |
| **Consultation** | 5 DTOs | ⏳ Pending |
| **Contract** | 5 DTOs | ⏳ Pending |
| **Plan** | 9 DTOs | ⏳ Pending |
| **Provider** | 7 DTOs | ⏳ Pending |
| **TOTAL** | **30 DTOs** | **Ready to Start** |

---

## 📝 DTO Mapping (1:1 with Models)

### Amendment Module (4 DTOs)
1. ⏳ **AmendmentTypeDTO** ← AmendmentType
2. ⏳ **AmendmentPhaseDTO** ← AmendmentPhase
3. ⏳ **AmendmentDTO** ← Amendment
4. ⏳ **AmendmentStepDTO** ← AmendmentStep

### Consultation Module (5 DTOs)
1. ⏳ **AwardMethodDTO** ← AwardMethod
2. ⏳ **ConsultationPhaseDTO** ← ConsultationPhase
3. ⏳ **ConsultationDTO** ← Consultation
4. ⏳ **ConsultationStepDTO** ← ConsultationStep
5. ⏳ **SubmissionDTO** ← Submission

### Contract Module (5 DTOs)
1. ⏳ **ContractDTO** ← Contract
2. ⏳ **ContractItemDTO** ← ContractItem
3. ⏳ **ContractPhaseDTO** ← ContractPhase
4. ⏳ **ContractStepDTO** ← ContractStep
5. ⏳ **ContractTypeDTO** ← ContractType

### Plan Module (9 DTOs)
1. ⏳ **BudgetModificationDTO** ← BudgetModification
2. ⏳ **BudgetTypeDTO** ← BudgetType
3. ⏳ **DomainDTO** ← Domain
4. ⏳ **FinancialOperationDTO** ← FinancialOperation
5. ⏳ **ItemDTO** ← Item
6. ⏳ **ItemDistributionDTO** ← ItemDistribution
7. ⏳ **ItemStatusDTO** ← ItemStatus
8. ⏳ **PlannedItemDTO** ← PlannedItem
9. ⏳ **RubricDTO** ← Rubric

### Provider Module (7 DTOs)
1. ⏳ **ClearanceDTO** ← Clearance
2. ⏳ **EconomicDomainDTO** ← EconomicDomain
3. ⏳ **EconomicNatureDTO** ← EconomicNature
4. ⏳ **ExclusionTypeDTO** ← ExclusionType
5. ⏳ **ProviderDTO** ← Provider
6. ⏳ **ProviderExclusionDTO** ← ProviderExclusion
7. ⏳ **ProviderRepresentatorDTO** ← ProviderRepresentator

---

## 🔄 Standard Transformation Pattern

### Before (Current)

```java
package dz.mdn.iaas.business.{module}.dto;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelDTO {  // ❌ No inheritance
    
    private Long id;  // ❌ Duplicate ID field
    
    // Business fields
    private String field1;
    private String field2;
    
    // ❌ 100+ lines of duplicate methods:
    public static ModelDTO fromEntity(Model entity) { ... }
    public Model toEntity() { ... }
    public void updateEntity(Model entity) { ... }
    // + many utility methods
}
```

### After (Updated)

```java
package dz.mdn.iaas.business.{module}.dto;

import dz.mdn.iaas.configuration.template.GenericDTO;  // ✅ Add import
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@EqualsAndHashCode(callSuper = true)  // ✅ Add callSuper
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelDTO extends GenericDTO {  // ✅ Extend GenericDTO
    
    // ✅ ID removed - inherited from GenericDTO
    
    // Business fields only
    private String field1;
    private String field2;
    
    // ✅ fromEntity, toEntity, updateEntity inherited
    // Only custom DTO methods here (if any)
}
```

---

## ✅ Changes Required for Each DTO

### 1. Add Import
```java
import dz.mdn.iaas.configuration.template.GenericDTO;
```

### 2. Extend GenericDTO
```java
public class ModelDTO extends GenericDTO {
```

### 3. Update @EqualsAndHashCode
```java
@EqualsAndHashCode(callSuper = true)  // Add if @EqualsAndHashCode exists
```

### 4. Remove ID Field
```java
// Remove this line:
private Long id;
```

### 5. Remove Generic Methods (Inherited)

Delete these methods (now inherited from GenericDTO):
- `fromEntity(Model entity)`
- `toEntity()`
- `updateEntity(Model entity)`
- `getId()` / `setId()`

### 6. Keep Only Custom Business Methods

Retain domain-specific methods like:
- `getDisplayText()`
- `isMultilingual()`
- `getStatusColor()`
- Custom validation methods
- Etc.

### 7. Update @Updated Date
```java
*	@Updated	: 12-10-2025
```

---

## 📊 Statistics

### Code Reduction

| Metric | Before | After | Saved |
|--------|--------|-------|-------|
| **Average DTO LOC** | ~250 lines | ~100 lines | **150 lines** |
| **DTOs to update** | 30 | 30 | - |
| **Total duplicate LOC** | ~7,500 lines | ~3,000 lines | **~4,500 lines** |
| **GenericDTO LOC** | 0 | ~200 lines | (investment) |
| **Net savings** | - | - | **~4,300 lines** |

---

## 🔍 Verification Steps

For each updated DTO:

1. ✅ Import `GenericDTO` added
2. ✅ Class extends `GenericDTO`
3. ✅ `@EqualsAndHashCode(callSuper = true)` if annotation exists
4. ✅ ID field removed
5. ✅ Generic methods removed (fromEntity, toEntity, updateEntity)
6. ✅ Custom business methods preserved
7. ✅ No compilation errors
8. ✅ JSON serialization works

---

## 🚀 Implementation Plan

### Phase 1: Amendment Module (⏳ Next)
- AmendmentTypeDTO
- AmendmentPhaseDTO
- AmendmentDTO
- AmendmentStepDTO

### Phase 2: Consultation Module
- AwardMethodDTO
- ConsultationPhaseDTO
- ConsultationDTO
- ConsultationStepDTO
- SubmissionDTO

### Phase 3: Contract Module
- ContractDTO
- ContractItemDTO
- ContractPhaseDTO
- ContractStepDTO
- ContractTypeDTO

### Phase 4: Plan Module
- BudgetModificationDTO
- BudgetTypeDTO
- DomainDTO
- FinancialOperationDTO
- ItemDTO
- ItemDistributionDTO
- ItemStatusDTO
- PlannedItemDTO
- RubricDTO

### Phase 5: Provider Module
- ClearanceDTO
- EconomicDomainDTO
- EconomicNatureDTO
- ExclusionTypeDTO
- ProviderDTO
- ProviderExclusionDTO
- ProviderRepresentatorDTO

---

## ✨ Benefits

### 1. Zero ID Duplication
- ID defined once in GenericDTO
- 30 DTOs inherit automatically

### 2. Consistent Conversion
- All DTOs have identical entity conversion logic
- Standard fromEntity/toEntity behavior

### 3. Easy Maintenance
- Change conversion logic in one place
- Affects all 30 DTOs instantly

### 4. Clean Code
- DTOs only contain business fields
- Infrastructure hidden in base class

### 5. Scalable
- Future DTOs automatically get base functionality
- No duplication as system grows

---

## 🎯 Progress Tracker

**Updated:** 0 / 30 DTOs (0%)  
**Remaining:** 30 DTOs (100%)  
**Target:** 100% completion

---

**Created:** December 10, 2025  
**Status:** Ready to Start  
**Priority:** HIGH  
**Estimated Savings:** ~4,300 lines of code
