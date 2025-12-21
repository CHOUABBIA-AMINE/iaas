# Remaining 21 DTOs to Fix

## Status: ⏳ In Progress

### Completed (9/30)
- ✅ Amendment: 4 DTOs
- ✅ Consultation: 5 DTOs

### Remaining (21/30)
- ⏳ Contract: 5 DTOs
- ⏳ Plan: 9 DTOs  
- ⏳ Provider: 7 DTOs

## Required Changes

1. Add generic type parameter: `extends GenericDTO<Entity>`
2. Replace `@NoArgsConstructor` with `@SuperBuilder`
3. Implement `toEntity()` method
4. Implement `updateEntity(Entity entity)` method
5. Add `fromEntity(Entity entity)` static method

## Next Steps

Due to the large number of files, these will be updated in batches of 5-7 files.
