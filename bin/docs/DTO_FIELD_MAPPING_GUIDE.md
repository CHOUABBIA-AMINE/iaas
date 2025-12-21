# DTO Field Mapping Guide

## 🎯 Critical Rule: DTOs Map Only @Column Fields

**DTOs should ONLY contain fields that map to @Column in the model.**
**DTOs should NOT contain @ManyToOne, @OneToMany, or @ManyToMany relationships.**

---

## ❌ What DTOs Should NOT Have

### In the Model:
```java
@ManyToOne
@JoinColumn(name="F_12", foreignKey=@ForeignKey(name="T_02_06_04_FK_01"), nullable=false)
private Contract contract;

@ManyToOne
@JoinColumn(name="F_13", foreignKey=@ForeignKey(name="T_02_06_04_FK_02"), nullable=false)
private AmendmentType amendmentType;
```

### ❌ WRONG in DTO:
```java
private Long contractId;  // ❌ DON'T add IDs for relationships
private Long amendmentTypeId;  // ❌ DON'T add IDs for relationships
private Contract contract;  // ❌ DON'T add entity objects
```

---

## ✅ What DTOs SHOULD Have

### In the Model:
```java
@Column(name="F_01")
private int internalId;

@Column(name="F_02")
private String reference;

@Column(name="F_03", length=300)
private String designationAr;

@Column(name="F_04", length=300)
private String designationEn;

@Column(name="F_05", length=300)
private String designationFr;

@Column(name="F_06")
private double amount;
```

### ✅ CORRECT in DTO:
```java
private int internalId;  // ✅ Maps to @Column F_01
private String reference;  // ✅ Maps to @Column F_02
private String designationAr;  // ✅ Maps to @Column F_03
private String designationEn;  // ✅ Maps to @Column F_04
private String designationFr;  // ✅ Maps to @Column F_05
private double amount;  // ✅ Maps to @Column F_06
```

---

## 📝 Example: Amendment

### Amendment Model (Actual):
```java
@Column(name="F_01")
private int internalId;

@Column(name="F_02")
private String reference;

@Column(name="F_03", length=300)
private String designationAr;

@Column(name="F_04", length=300)
private String designationEn;

@Column(name="F_05", length=300)
private String designationFr;

@Column(name="F_06")
private double amount;

@Column(name="F_07")
private double transferableAmount;

@Column(name="F_08")
private Date startDate;

@Column(name="F_09")
private Date approvalDate;

@Column(name="F_10")
private Date notifyDate;

@Column(name="F_11", length=500)
private String observation;

// Relationships (NOT in DTO):
@ManyToOne
private Contract contract;  // F_12

@ManyToOne
private AmendmentType amendmentType;  // F_13

@ManyToOne
private ProcurementStatus procurementStatus;  // F_14

@ManyToOne
private AmendmentPhase amendmentStep;  // F_15

@ManyToOne
private ApprovalStatus approvalStatus;  // F_16

@ManyToOne
private Currency currency;  // F_17
```

### AmendmentDTO (Correct):
```java
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmendmentDTO extends GenericDTO<Amendment> {

    // ✅ Only @Column fields
    private int internalId;
    private String reference;
    private String designationAr;
    private String designationEn;
    private String designationFr;
    private double amount;
    private double transferableAmount;
    private Date startDate;
    private Date approvalDate;
    private Date notifyDate;
    private String observation;
    
    // ❌ NO relationship IDs
    // ❌ NO entity objects

    @Override
    public Amendment toEntity() {
        Amendment entity = new Amendment();
        entity.setId(getId());
        entity.setInternalId(this.internalId);
        entity.setReference(this.reference);
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        entity.setAmount(this.amount);
        entity.setTransferableAmount(this.transferableAmount);
        entity.setStartDate(this.startDate);
        entity.setApprovalDate(this.approvalDate);
        entity.setNotifyDate(this.notifyDate);
        entity.setObservation(this.observation);
        // Relationships set by service layer
        return entity;
    }

    @Override
    public void updateEntity(Amendment entity) {
        if (this.reference != null) entity.setReference(this.reference);
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
        entity.setAmount(this.amount);
        entity.setTransferableAmount(this.transferableAmount);
        if (this.startDate != null) entity.setStartDate(this.startDate);
        if (this.approvalDate != null) entity.setApprovalDate(this.approvalDate);
        if (this.notifyDate != null) entity.setNotifyDate(this.notifyDate);
        if (this.observation != null) entity.setObservation(this.observation);
        // Relationships updated by service layer
    }

    public static AmendmentDTO fromEntity(Amendment entity) {
        if (entity == null) return null;
        
        return AmendmentDTO.builder()
                .id(entity.getId())
                .internalId(entity.getInternalId())
                .reference(entity.getReference())
                .designationAr(entity.getDesignationAr())
                .designationEn(entity.getDesignationEn())
                .designationFr(entity.getDesignationFr())
                .amount(entity.getAmount())
                .transferableAmount(entity.getTransferableAmount())
                .startDate(entity.getStartDate())
                .approvalDate(entity.getApprovalDate())
                .notifyDate(entity.getNotifyDate())
                .observation(entity.getObservation())
                .build();
    }
}
```

---

## 🛠️ How Relationships Are Handled

### In Controllers/Services:

Relationships are typically passed separately as IDs in request parameters or request bodies:

```java
// Controller
@PostMapping
public ResponseEntity<AmendmentDTO> create(
    @RequestBody AmendmentDTO dto,
    @RequestParam Long contractId,
    @RequestParam Long amendmentTypeId,
    @RequestParam Long currencyId
) {
    // Service handles relationship binding
    return service.create(dto, contractId, amendmentTypeId, currencyId);
}

// Service
public AmendmentDTO create(
    AmendmentDTO dto, 
    Long contractId, 
    Long amendmentTypeId,
    Long currencyId
) {
    Amendment entity = dto.toEntity();
    
    // Set relationships
    entity.setContract(contractRepository.findById(contractId).orElseThrow());
    entity.setAmendmentType(amendmentTypeRepository.findById(amendmentTypeId).orElseThrow());
    entity.setCurrency(currencyRepository.findById(currencyId).orElseThrow());
    
    // Save
    Amendment saved = repository.save(entity);
    return AmendmentDTO.fromEntity(saved);
}
```

---

## 📝 Summary

| Model Field Type | Include in DTO? | How to Handle |
|------------------|----------------|---------------|
| `@Column` | ✅ YES | Direct field mapping |
| `@ManyToOne` | ❌ NO | Pass ID separately to service |
| `@OneToMany` | ❌ NO | Separate endpoint/DTO |
| `@ManyToMany` | ❌ NO | Separate endpoint/DTO |
| `@Embedded` | ✅ YES | Flatten or nest as needed |
| `@Transient` | 🤔 MAYBE | Case by case |

---

## ✅ Action Required

**All DTOs in Amendment, Consultation, Contract, Plan, and Provider modules need to be corrected:**

1. Remove all `*Id` fields that represent relationships
2. Keep only fields that map to `@Column` in the model
3. Update `toEntity()` to map only @Column fields
4. Update `updateEntity()` to map only @Column fields
5. Update `fromEntity()` to map only @Column fields

---

**Created:** December 10, 2025  
**Status:** 🚨 Critical - DTOs need correction  
**Priority:** HIGHEST
