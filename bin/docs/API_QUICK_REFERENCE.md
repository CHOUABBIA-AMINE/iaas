# IAAS API Quick Reference

**Quick lookup for all 90 API endpoints** - Updated December 20, 2025

## Quick Navigation

- [Authentication](#authentication)
- [Business APIs](#business-apis) (35 endpoints)
- [Common APIs](#common-apis) (22 endpoints)
- [Network APIs](#network-apis) (25 endpoints)
- [System APIs](#system-apis) (8 endpoints)

---

## Standard Endpoints (All Controllers)

Every controller inherits these 9 standard endpoints:

| Method | Pattern | Description |
|--------|---------|-------------|
| GET | `/{id}` | Get by ID |
| GET | `?page=0&size=20` | Get all (paginated) |
| GET | `/all` | Get all (list) |
| POST | `/` | Create |
| PUT | `/{id}` | Update |
| DELETE | `/{id}` | Delete |
| GET | `/search?q=term` | Search |
| GET | `/exists/{id}` | Check existence |
| GET | `/count` | Get total count |

---

## Authentication

### AuthController
**Base:** `/auth`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/login` | Login |
| POST | `/register` | Register |
| POST | `/refresh` | Refresh token |
| POST | `/logout` | Logout |

---

## Business APIs

### Amendment (4)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| AmendmentController | `/business/amendment/amendment` | Amendments |
| AmendmentPhaseController | `/business/amendment/phase` | Amendment phases |
| AmendmentStepController | `/business/amendment/step` | Amendment steps |
| AmendmentTypeController | `/business/amendment/type` | Amendment types |

**Custom Endpoints:**
- `GET /business/amendment/amendment/list` - All amendments as list

---

### Consultation (5)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| ConsultationController | `/business/consultation/consultation` | Consultations |
| AwardMethodController | `/business/consultation/award-method` | Award methods |
| SubmissionController | `/business/consultation/submission` | Submissions |
| ConsultationPhaseController | `/business/consultation/phase` | Consultation phases |
| ConsultationStepController | `/business/consultation/step` | Consultation steps |

---

### Contract (5)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| ContractController | `/business/contract/contract` | Contracts |
| ContractItemController | `/business/contract/item` | Contract items |
| ContractTypeController | `/business/contract/type` | Contract types |
| ContractPhaseController | `/business/contract/phase` | Contract phases |
| ContractStepController | `/business/contract/step` | Contract steps |

---

### Core Business (5)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| CurrencyController ⭐ | `/business/core/currency` | Currencies |
| ApprovalStatusController | `/business/core/approval-status` | Approval statuses |
| ProcurementDirectorController | `/business/core/procurement-director` | Procurement directors |
| ProcurementNatureController | `/business/core/procurement-nature` | Procurement natures |
| ProcurementStatusController | `/business/core/procurement-status` | Procurement statuses |

**CurrencyController Custom Endpoints:**
- `GET /list` - All currencies as list
- `GET /search/designation?q=term` - Search by designation
- `GET /search/acronym?q=code` - Search by acronym
- `GET /exists/code/{code}` - Check code exists
- `GET /exists/designation-ar/{text}` - Check Arabic designation
- `GET /exists/designation-en/{text}` - Check English designation
- `GET /exists/designation-fr/{text}` - Check French designation

---

### Planning (9)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| BudgetModificationController | `/business/plan/budget-modification` | Budget modifications |
| BudgetTypeController | `/business/plan/budget-type` | Budget types |
| DomainController | `/business/plan/domain` | Domains |
| FinancialOperationController | `/business/plan/financial-operation` | Financial operations |
| ItemController | `/business/plan/item` | Items |
| ItemDistributionController | `/business/plan/item-distribution` | Item distributions |
| ItemStatusController | `/business/plan/item-status` | Item statuses |
| PlannedItemController | `/business/plan/planned-item` | Planned items |
| RubricController | `/business/plan/rubric` | Rubrics |

---

### Provider Management (7)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| ProviderController | `/business/provider/provider` | Providers |
| ProviderExclusionController | `/business/provider/exclusion` | Provider exclusions |
| ProviderRepresentatorController | `/business/provider/representator` | Provider representators |
| ClearanceController | `/business/provider/clearance` | Clearances |
| EconomicDomainController | `/business/provider/economic-domain` | Economic domains |
| EconomicNatureController | `/business/provider/economic-nature` | Economic natures |
| ExclusionTypeController | `/business/provider/exclusion-type` | Exclusion types |

---

## Common APIs

### Administration (10)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| CountryController | `/common/administration/country` | Countries |
| StateController | `/common/administration/state` | States/Provinces |
| LocalityController | `/common/administration/locality` | Localities |
| StructureController | `/common/administration/structure` | Organizational structures |
| StructureTypeController | `/common/administration/structure-type` | Structure types |
| EmployeeController | `/common/administration/employee` | Employees |
| PersonController | `/common/administration/person` | Persons |
| JobController | `/common/administration/job` | Jobs |
| MilitaryRankController | `/common/administration/military-rank` | Military ranks |
| MilitaryCategoryController | `/common/administration/military-category` | Military categories |

---

### Communication (3)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| MailController | `/common/communication/mail` | Mail |
| MailTypeController | `/common/communication/mail-type` | Mail types |
| MailNatureController | `/common/communication/mail-nature` | Mail natures |

---

### Document (2)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| DocumentController | `/common/document/document` | Documents |
| DocumentTypeController | `/common/document/type` | Document types |

---

### Environment (7)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| ArchiveBoxController | `/common/environment/archive-box` | Archive boxes |
| BlocController | `/common/environment/bloc` | Building blocks |
| FloorController | `/common/environment/floor` | Floors |
| RoomController | `/common/environment/room` | Rooms |
| FolderController | `/common/environment/folder` | Folders |
| ShelfController | `/common/environment/shelf` | Shelves |
| ShelfFloorController | `/common/environment/shelf-floor` | Shelf floors |

---

## Network APIs

### Network Common (8)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| AlloyController | `/network/common/alloy` | Alloys |
| LocationController | `/network/common/location` | Locations |
| OperationalStatusController | `/network/common/operational-status` | Operational statuses |
| PartnerController | `/network/common/partner` | Partners |
| ProductController | `/network/common/product` | Products |
| RegionController | `/network/common/region` | Regions |
| VendorController | `/network/common/vendor` | Vendors |
| ZoneController | `/network/common/zone` | Zones |

---

### Network Core (9)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| EquipmentController | `/network/core/equipment` | Equipment |
| FacilityController | `/network/core/facility` | Facilities |
| HydrocarbonFieldController | `/network/core/hydrocarbon-field` | Hydrocarbon fields |
| InfrastructureController | `/network/core/infrastructure` | Infrastructure |
| PipelineController | `/network/core/pipeline` | Pipelines |
| PipelineSegmentController | `/network/core/pipeline-segment` | Pipeline segments |
| PipelineSystemController | `/network/core/pipeline-system` | Pipeline systems |
| StationController | `/network/core/station` | Stations |
| TerminalController | `/network/core/terminal` | Terminals |

---

### Network Types (8)

| Controller | Base Path | Key Entity |
|------------|-----------|------------|
| CompanyTypeController | `/network/type/company-type` | Company types |
| EquipmentTypeController | `/network/type/equipment-type` | Equipment types |
| FacilityTypeController | `/network/type/facility-type` | Facility types |
| HydrocarbonFieldTypeController | `/network/type/hydrocarbon-field-type` | Field types |
| PartnerTypeController | `/network/type/partner-type` | Partner types |
| StationTypeController | `/network/type/station-type` | Station types |
| TerminalTypeController | `/network/type/terminal-type` | Terminal types |
| VendorTypeController | `/network/type/vendor-type` | Vendor types |

---

## System APIs

### Audit (1)

#### AuditedController ⭐
**Base:** `/system/audit/audited`

**Standard CRUD:** All inherited

**Custom Endpoints:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/entity/{entityName}/{entityId}` | Entity audit history |
| GET | `/user/{username}` | User audit trail |
| GET | `/date-range?startDate=...&endDate=...` | Logs by date range |
| GET | `/failed` | Failed operations |
| GET | `/user/{username}/summary?days=30` | Activity summary |

---

### Security (5)

#### UserController ⭐
**Base:** `/system/security/user`

**Standard CRUD:** All inherited

**Custom Endpoints:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/by-username/{username}` | Get by username |
| GET | `/by-email/{email}` | Get by email |
| GET | `/exists/username/{username}` | Check username |
| GET | `/exists/email/{email}` | Check email |
| POST | `/reset-password` | Reset password |
| POST | `/{userId}/roles/{roleId}` | Assign role |
| DELETE | `/{userId}/roles/{roleId}` | Remove role |
| GET | `/by-role/{roleId}` | Users with role |
| POST | `/{userId}/groups/{groupId}` | Assign group |
| DELETE | `/{userId}/groups/{groupId}` | Remove group |
| GET | `/by-group/{groupId}` | Users in group |
| PUT | `/{id}/enable` | Enable account |
| PUT | `/{id}/disable` | Disable account |
| PUT | `/{id}/lock` | Lock account |
| PUT | `/{id}/unlock` | Unlock account |

#### Other Security Controllers

| Controller | Base Path |
|------------|----------|
| RoleController | `/system/security/role` |
| PermissionController | `/system/security/permission` |
| GroupController | `/system/security/group` |
| AuthorityController | `/system/security/authority` |

---

### Utility (1)

#### FileController ⭐
**Base:** `/system/utility/file`

**Standard CRUD:** All inherited

**File Operations:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/upload` | Upload single file |
| POST | `/upload/batch` | Upload multiple files |
| GET | `/{id}/download` | Download file |
| PUT | `/{id}/replace` | Replace file |
| POST | `/{id}/copy` | Copy file |
| DELETE | `/{id}` | Delete file |

**File Info:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/{id}/physical/exists` | Check file exists |
| GET | `/{id}/physical-size` | Get file size |
| GET | `/{id}/content-type` | Get content type |
| GET | `/by-path?path=...` | Find by path |
| GET | `/by-extension/{ext}` | Find by extension |
| GET | `/by-type/{fileType}` | Find by type |

**Content Types:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/content-types` | All supported types |
| GET | `/content-types/check/{ext}` | Check extension |

**Maintenance:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/cleanup/orphans` | Remove orphaned files |
| GET | `/missing-physical` | Find missing files |
| GET | `/stats` | Storage statistics |

---

## Common Patterns

### Pagination
```
GET /business/core/currency?page=0&size=20&sortBy=code&sortDir=asc
```

### Search
```
GET /business/core/currency/search?q=dollar
```

### Filter by Date
```
GET /system/audit/audited/date-range?startDate=2025-01-01&endDate=2025-12-31
```

### Check Existence
```
GET /business/core/currency/exists/code/USD
GET /system/security/user/exists/username/admin
```

### Multi-language Support
```
GET /business/core/currency/search/designation?q=dollar
# Searches in Arabic, French, and English
```

---

## Security Patterns

### Authority Format
- Read: `{ENTITY}:READ`
- Write: `{ENTITY}:ADMIN`

### Examples
```
CURRENCY:READ
CURRENCY:ADMIN
USER:READ
USER:ADMIN
AUDIT:READ
AUDIT:ADMIN
```

### Special Cases
- `AUDIT:USER` - Limited audit access
- Users can view/edit their own data
- Public endpoints: `/auth/login`, `/auth/register`

---

## Response Formats

### Single Entity
```json
{
  "id": 1,
  "code": "USD",
  "designationAr": "دولار",
  "designationFr": "Dollar",
  "designationEn": "Dollar"
}
```

### Paginated List
```json
{
  "content": [...],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20
  },
  "totalElements": 100,
  "totalPages": 5,
  "last": false,
  "first": true
}
```

### Error Response
```json
{
  "timestamp": "2025-12-20T20:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/business/core/currency"
}
```

---

## Quick Tips

1. **Always authenticate first** - Get JWT token from `/auth/login`
2. **Use pagination** - Default is 20 items per page
3. **Check for duplicates** - Use `/exists/...` endpoints
4. **Review audit logs** - Track changes via `/system/audit/audited`
5. **Handle errors** - Check status codes and error messages
6. **Use search** - Global search available on most entities
7. **Leverage filtering** - Use query parameters for filtering
8. **Batch operations** - Use `/upload/batch` for multiple files
9. **Check file integrity** - Use `/missing-physical` for file validation
10. **Monitor activity** - Use `/user/{username}/summary` for user activity

---

## Legend

- ⭐ = Controller with custom endpoints beyond standard CRUD
- Standard = All 9 inherited CRUD endpoints
- Custom = Additional endpoints specific to the controller

---

**For detailed documentation:** See [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

**Last Updated:** December 20, 2025  
**Total Endpoints:** 90 controllers + custom endpoints = 800+ total endpoints
