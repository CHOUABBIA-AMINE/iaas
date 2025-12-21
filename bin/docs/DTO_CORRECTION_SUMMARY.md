# DTO Corrections Summary

## 🚨 Critical Issue Identified

**All updated DTOs currently have INCORRECT fields**

### ❌ Problem
DTOs include relationship ID fields (like `contractId`, `amendmentTypeId`, etc.) that should NOT be in the DTO.

### ✅ Solution
DTOs should ONLY contain fields that map directly to `@Column` annotations in the model.

---

## 📊 Status: DTOs Updated But Need Field Correction

### ✅ Correctly Implemented (Structure)
- Amendment: 4 DTOs - Have correct methods but wrong fields
- Consultation: 5 DTOs - Have correct methods but wrong fields

### ⏳ Need Complete Fix
- Contract: 5 DTOs
- Plan: 9 DTOs
- Provider: 7 DTOs

---

## 🔧 What Needs to be Fixed

### For ALL DTOs:

1. **Remove ALL relationship ID fields**
   - Remove: `contractId`, `amendmentTypeId`, `currencyId`, etc.
   - Keep: Only `@Column` mapped fields

2. **Keep proper structure** (already correct):
   - ✅ `extends GenericDTO<Entity>`
   - ✅ `@SuperBuilder`
   - ✅ `toEntity()` method
   - ✅ `updateEntity()` method
   - ✅ `fromEntity()` method

---

## 📝 Example: What Changed

### ❌ BEFORE (Incorrect - Has Relationship IDs):
```java
public class AmendmentDTO extends GenericDTO<Amendment> {
    private int internalId;  // ✅ KEEP
    private String reference;  // ✅ KEEP
    private String designationFr;  // ✅ KEEP
    private double amount;  // ✅ KEEP
    
    @NotNull
    private Long contractId;  // ❌ REMOVE - This is a relationship!
    
    @NotNull
    private Long amendmentTypeId;  // ❌ REMOVE - This is a relationship!
    
    @NotNull
    private Long currencyId;  // ❌ REMOVE - This is a relationship!
}
```

### ✅ AFTER (Correct - Only @Column Fields):
```java
public class AmendmentDTO extends GenericDTO<Amendment> {
    private int internalId;  // ✅ Maps to @Column F_01
    private String reference;  // ✅ Maps to @Column F_02
    private String designationAr;  // ✅ Maps to @Column F_03
    private String designationEn;  // ✅ Maps to @Column F_04
    private String designationFr;  // ✅ Maps to @Column F_05
    private double amount;  // ✅ Maps to @Column F_06
    private double transferableAmount;  // ✅ Maps to @Column F_07
    private Date startDate;  // ✅ Maps to @Column F_08
    private Date approvalDate;  // ✅ Maps to @Column F_09
    private Date notifyDate;  // ✅ Maps to @Column F_10
    private String observation;  // ✅ Maps to @Column F_11
    
    // NO *Id fields for relationships!
}
```

---

## 📝 Detailed Corrections Needed

### Amendment Module

#### 1. AmendmentDTO
**Remove:**
- `contractId`
- `amendmentTypeId`
- `procurementStatusId`
- `amendmentStepId`
- `approvalStatusId`
- `currencyId`

**Keep:**
- `internalId`
- `reference`
- `designationAr`
- `designationEn`
- `designationFr`
- `amount`
- `transferableAmount`
- `startDate`
- `approvalDate`
- `notifyDate`
- `observation`

#### 2. AmendmentStepDTO
**Remove:**
- `amendmentId`
- `amendmentPhaseId`

**Keep:**
- `internalId`
- `date`
- `observation`

#### 3-4. AmendmentTypeDTO, AmendmentPhaseDTO
✅ **Already Correct** - No relationship fields

---

### Consultation Module

#### 1. ConsultationDTO
**Check Model First** - Needs model review to identify actual @Column fields vs relationships

#### 2. ConsultationStepDTO  
**Remove:**
- `consultationId`
- `consultationPhaseId`

**Keep:**
- `internalId`
- `date`
- `observation`

#### 3. SubmissionDTO
**Remove:**
- `providerId`
- `consultationId`
- `submissionStatusId`

**Keep:**
- `internalId`
- `financialAmount`
- `technicalScore`
- `submissionDate`
- `evaluationDate`
- `isWinner`
- `observation`

#### 4-5. AwardMethodDTO, ConsultationPhaseDTO
✅ **Already Correct** - No relationship fields

---

### Contract, Plan, Provider Modules

**Status:** Not yet updated with proper implementation

**Action Needed:**
1. Read each model to identify @Column fields
2. Create DTO with ONLY @Column fields
3. Implement `toEntity()`, `updateEntity()`, `fromEntity()`
4. Do NOT include relationship IDs

---

## ⚠️ Important Note

**Relationships are handled in the Service Layer**, not in DTOs!

When creating/updating entities with relationships:
```java
// In Service:
public AmendmentDTO create(AmendmentDTO dto, Long contractId, Long currencyId) {
    Amendment entity = dto.toEntity();  // Only sets @Column fields
    
    // Service sets relationships:
    entity.setContract(contractRepository.findById(contractId).orElseThrow());
    entity.setCurrency(currencyRepository.findById(currencyId).orElseThrow());
    
    Amendment saved = repository.save(entity);
    return AmendmentDTO.fromEntity(saved);
}
```

---

## 🎯 Next Steps

1. ✅ **Documentation Created** - This file + DTO_FIELD_MAPPING_GUIDE.md
2. ⏳ **Fix Amendment DTOs** - Remove relationship IDs
3. ⏳ **Fix Consultation DTOs** - Remove relationship IDs
4. ⏳ **Complete Contract DTOs** - With correct fields only
5. ⏳ **Complete Plan DTOs** - With correct fields only
6. ⏳ **Complete Provider DTOs** - With correct fields only
7. ⏳ **Verify all DTOs** - Ensure no relationship IDs remain

---

**Created:** December 10, 2025, 2:55 PM CET  
**Status:** 🚨 Urgent - Corrections Needed  
**Priority:** CRITICAL
