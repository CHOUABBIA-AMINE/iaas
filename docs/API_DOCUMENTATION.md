# IAAS API Documentation

## Overview

Complete REST API documentation for the IAAS (Infrastructure as a Service) application. This document covers all **90 REST controllers** with detailed endpoint specifications, security requirements, and usage examples.

**Last Updated:** December 20, 2025

---

## Quick Stats

| Metric | Count |
|--------|-------|
| **Total Controllers** | 90 |
| **Total Packages** | 17 |
| **Business Domain** | 35 controllers |
| **Common Utilities** | 22 controllers |
| **Network Infrastructure** | 25 controllers |
| **System Management** | 8 controllers |

---

## Table of Contents

1. [Architecture Overview](#architecture-overview)
2. [Authentication & Authorization](#authentication--authorization)
3. [Standard CRUD Endpoints](#standard-crud-endpoints)
4. [Business Package APIs](#business-package-apis)
5. [Common Package APIs](#common-package-apis)
6. [Network Package APIs](#network-package-apis)
7. [System Package APIs](#system-package-apis)
8. [Error Handling](#error-handling)
9. [Complete Endpoint Reference](#complete-endpoint-reference)

---

## Architecture Overview

### Technology Stack

- **Framework:** Spring Boot 3.x
- **Security:** Spring Security with JWT
- **Validation:** Jakarta Bean Validation
- **Data Access:** Spring Data JPA
- **Logging:** SLF4J with Lombok
- **Build Tool:** Maven

### Design Patterns

**Generic Controller Pattern:**
- All CRUD controllers extend `GenericController<DTO, ID>`
- Provides 9 standard endpoints out-of-the-box
- Consistent API structure across all entities
- Centralized exception handling

**DTO Pattern:**
- All entities use DTOs for API communication
- DTOs implement `GenericDTO` interface
- Automatic entity-DTO conversion
- Multi-language support (AR, FR, EN)

---

## Authentication & Authorization

### JWT Token Authentication

**Obtaining a Token:**
```http
POST /auth/login
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600
}
```

**Using the Token:**
```http
GET /business/core/currency
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Authority Pattern

All endpoints use role-based access control:

- **Read Operations:** `{ENTITY}:READ`
- **Write Operations:** `{ENTITY}:ADMIN`

**Example Authorities:**
- `CURRENCY:READ` - View currencies
- `CURRENCY:ADMIN` - Create/update/delete currencies
- `USER:READ` - View users
- `USER:ADMIN` - Manage users

---

## Standard CRUD Endpoints

**All 87 Generic Controllers** inherit these standard endpoints:

### 1. Get by ID
```http
GET /{base-path}/{id}
```
**Security:** `{ENTITY}:READ`  
**Response:** Single DTO object  
**Status Codes:** 200 OK, 404 Not Found

### 2. Get All (Paginated)
```http
GET /{base-path}?page=0&size=20&sortBy=id&sortDir=asc
```
**Security:** `{ENTITY}:READ`  
**Response:** Page<DTO>  
**Default Params:** page=0, size=20, sortBy=id, sortDir=asc

### 3. Get All (Non-Paginated)
```http
GET /{base-path}/all
```
**Security:** `{ENTITY}:READ`  
**Response:** List<DTO>

### 4. Create Entity
```http
POST /{base-path}
Content-Type: application/json

{
  "field1": "value1",
  "field2": "value2"
}
```
**Security:** `{ENTITY}:ADMIN`  
**Response:** Created DTO  
**Status Codes:** 201 Created, 400 Bad Request

### 5. Update Entity
```http
PUT /{base-path}/{id}
Content-Type: application/json

{
  "field1": "newValue1",
  "field2": "newValue2"
}
```
**Security:** `{ENTITY}:ADMIN`  
**Response:** Updated DTO  
**Status Codes:** 200 OK, 404 Not Found

### 6. Delete Entity
```http
DELETE /{base-path}/{id}
```
**Security:** `{ENTITY}:ADMIN`  
**Status Codes:** 204 No Content, 404 Not Found

### 7. Search
```http
GET /{base-path}/search?q=searchTerm&page=0&size=20
```
**Security:** `{ENTITY}:READ`  
**Response:** Page<DTO>

### 8. Check Existence
```http
GET /{base-path}/exists/{id}
```
**Security:** `{ENTITY}:READ`  
**Response:** Boolean

### 9. Count
```http
GET /{base-path}/count
```
**Security:** `{ENTITY}:READ`  
**Response:** Long (total count)

---

## Business Package APIs

### Amendment Module (4 Controllers)

Manages procurement amendments and their lifecycle.

#### AmendmentController
**Base Path:** `/business/amendment/amendment`  
**Security:** `AMENDMENT:READ` / `AMENDMENT:ADMIN`

**Custom Endpoints:**
- `GET /list` - Get all amendments as list

#### AmendmentPhaseController
**Base Path:** `/business/amendment/phase`  
**Security:** `AMENDMENT_PHASE:READ` / `AMENDMENT_PHASE:ADMIN`

#### AmendmentStepController
**Base Path:** `/business/amendment/step`  
**Security:** `AMENDMENT_STEP:READ` / `AMENDMENT_STEP:ADMIN`

#### AmendmentTypeController
**Base Path:** `/business/amendment/type`  
**Security:** `AMENDMENT_TYPE:READ` / `AMENDMENT_TYPE:ADMIN`

---

### Consultation Module (5 Controllers)

Manages public procurement consultations and submissions.

#### ConsultationController
**Base Path:** `/business/consultation/consultation`  
**Security:** `CONSULTATION:READ` / `CONSULTATION:ADMIN`

#### AwardMethodController
**Base Path:** `/business/consultation/award-method`  
**Security:** `AWARD_METHOD:READ` / `AWARD_METHOD:ADMIN`

#### SubmissionController
**Base Path:** `/business/consultation/submission`  
**Security:** `SUBMISSION:READ` / `SUBMISSION:ADMIN`

#### ConsultationPhaseController
**Base Path:** `/business/consultation/phase`  
**Security:** `CONSULTATION_PHASE:READ` / `CONSULTATION_PHASE:ADMIN`

#### ConsultationStepController
**Base Path:** `/business/consultation/step`  
**Security:** `CONSULTATION_STEP:READ` / `CONSULTATION_STEP:ADMIN`

---

### Contract Module (5 Controllers)

Manages procurement contracts and their items.

#### ContractController
**Base Path:** `/business/contract/contract`  
**Security:** `CONTRACT:READ` / `CONTRACT:ADMIN`

#### ContractItemController
**Base Path:** `/business/contract/item`  
**Security:** `CONTRACT_ITEM:READ` / `CONTRACT_ITEM:ADMIN`

#### ContractTypeController
**Base Path:** `/business/contract/type`  
**Security:** `CONTRACT_TYPE:READ` / `CONTRACT_TYPE:ADMIN`

#### ContractPhaseController
**Base Path:** `/business/contract/phase`  
**Security:** `CONTRACT_PHASE:READ` / `CONTRACT_PHASE:ADMIN`

#### ContractStepController
**Base Path:** `/business/contract/step`  
**Security:** `CONTRACT_STEP:READ` / `CONTRACT_STEP:ADMIN`

---

### Core Module (5 Controllers)

Core business entities and configurations.

#### CurrencyController ⭐
**Base Path:** `/business/core/currency`  
**Security:** `CURRENCY:READ` / `CURRENCY:ADMIN`

**Custom Endpoints:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/list` | Get all currencies as list |
| GET | `/search/designation?q=term` | Search by designation (AR/FR/EN) |
| GET | `/search/acronym?q=code` | Search by currency code/acronym |
| GET | `/exists/code/{code}` | Check if code exists |
| GET | `/exists/designation-ar/{text}` | Check Arabic designation |
| GET | `/exists/designation-en/{text}` | Check English designation |
| GET | `/exists/designation-fr/{text}` | Check French designation |

#### ProcurementDirectorController
**Base Path:** `/business/core/procurement-director`  
**Security:** `PROCUREMENT_DIRECTOR:READ` / `PROCUREMENT_DIRECTOR:ADMIN`

#### ProcurementNatureController
**Base Path:** `/business/core/procurement-nature`  
**Security:** `PROCUREMENT_NATURE:READ` / `PROCUREMENT_NATURE:ADMIN`

#### ProcurementStatusController
**Base Path:** `/business/core/procurement-status`  
**Security:** `PROCUREMENT_STATUS:READ` / `PROCUREMENT_STATUS:ADMIN`

#### ApprovalStatusController
**Base Path:** `/business/core/approval-status`  
**Security:** `APPROVAL_STATUS:READ` / `APPROVAL_STATUS:ADMIN`

---

### Plan Module (9 Controllers)

Budget planning and financial operations.

**Controllers:**
- `BudgetModificationController` - `/business/plan/budget-modification`
- `BudgetTypeController` - `/business/plan/budget-type`
- `DomainController` - `/business/plan/domain`
- `FinancialOperationController` - `/business/plan/financial-operation`
- `ItemController` - `/business/plan/item`
- `ItemDistributionController` - `/business/plan/item-distribution`
- `ItemStatusController` - `/business/plan/item-status`
- `PlannedItemController` - `/business/plan/planned-item`
- `RubricController` - `/business/plan/rubric`

---

### Provider Module (7 Controllers)

Supplier and vendor management.

**Controllers:**
- `ProviderController` - `/business/provider/provider`
- `ProviderExclusionController` - `/business/provider/exclusion`
- `ProviderRepresentatorController` - `/business/provider/representator`
- `ClearanceController` - `/business/provider/clearance`
- `EconomicDomainController` - `/business/provider/economic-domain`
- `EconomicNatureController` - `/business/provider/economic-nature`
- `ExclusionTypeController` - `/business/provider/exclusion-type`

---

## Common Package APIs

### Administration Module (10 Controllers)

Organizational structure and personnel management.

**Controllers:**
- `CountryController` - `/common/administration/country`
- `StateController` - `/common/administration/state`
- `LocalityController` - `/common/administration/locality`
- `StructureController` - `/common/administration/structure`
- `StructureTypeController` - `/common/administration/structure-type`
- `EmployeeController` - `/common/administration/employee`
- `PersonController` - `/common/administration/person`
- `JobController` - `/common/administration/job`
- `MilitaryRankController` - `/common/administration/military-rank`
- `MilitaryCategoryController` - `/common/administration/military-category`

---

### Communication Module (3 Controllers)

Mail and correspondence management.

**Controllers:**
- `MailController` - `/common/communication/mail`
- `MailTypeController` - `/common/communication/mail-type`
- `MailNatureController` - `/common/communication/mail-nature`

---

### Document Module (2 Controllers)

Document classification and management.

**Controllers:**
- `DocumentController` - `/common/document/document`
- `DocumentTypeController` - `/common/document/type`

---

### Environment Module (7 Controllers)

Physical location and archiving infrastructure.

**Controllers:**
- `ArchiveBoxController` - `/common/environment/archive-box`
- `BlocController` - `/common/environment/bloc`
- `FloorController` - `/common/environment/floor`
- `RoomController` - `/common/environment/room`
- `FolderController` - `/common/environment/folder`
- `ShelfController` - `/common/environment/shelf`
- `ShelfFloorController` - `/common/environment/shelf-floor`

---

## Network Package APIs

### Network Common Module (8 Controllers)

Shared network infrastructure entities.

**Controllers:**
- `AlloyController` - `/network/common/alloy`
- `LocationController` - `/network/common/location`
- `OperationalStatusController` - `/network/common/operational-status`
- `PartnerController` - `/network/common/partner`
- `ProductController` - `/network/common/product`
- `RegionController` - `/network/common/region`
- `VendorController` - `/network/common/vendor`
- `ZoneController` - `/network/common/zone`

---

### Network Core Module (9 Controllers)

Hydrocarbon infrastructure management.

**Controllers:**
- `EquipmentController` - `/network/core/equipment`
- `FacilityController` - `/network/core/facility`
- `HydrocarbonFieldController` - `/network/core/hydrocarbon-field`
- `InfrastructureController` - `/network/core/infrastructure`
- `PipelineController` - `/network/core/pipeline`
- `PipelineSegmentController` - `/network/core/pipeline-segment`
- `PipelineSystemController` - `/network/core/pipeline-system`
- `StationController` - `/network/core/station`
- `TerminalController` - `/network/core/terminal`

---

### Network Type Module (8 Controllers)

Type classifications for network entities.

**Controllers:**
- `CompanyTypeController` - `/network/type/company-type`
- `EquipmentTypeController` - `/network/type/equipment-type`
- `FacilityTypeController` - `/network/type/facility-type`
- `HydrocarbonFieldTypeController` - `/network/type/hydrocarbon-field-type`
- `PartnerTypeController` - `/network/type/partner-type`
- `StationTypeController` - `/network/type/station-type`
- `TerminalTypeController` - `/network/type/terminal-type`
- `VendorTypeController` - `/network/type/vendor-type`

---

## System Package APIs

### Authentication Module

#### AuthController
**Base Path:** `/auth`  
**Security:** Mixed (some public, some authenticated)

**Endpoints:**

| Method | Endpoint | Request Body | Security | Description |
|--------|----------|--------------|----------|-------------|
| POST | `/login` | LoginRequest | Public | Authenticate user |
| POST | `/register` | RegisterRequest | Public | Register new user |
| POST | `/refresh` | TokenRefreshRequest | Public | Refresh access token |
| POST | `/logout` | None | Authenticated | Logout current user |

**Example Login:**
```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin@example.com",
  "password": "SecurePassword123!"
}
```

**Example Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600,
  "user": {
    "id": 1,
    "username": "admin@example.com",
    "email": "admin@example.com",
    "roles": ["ADMIN", "USER"]
  }
}
```

---

### Audit Module

#### AuditedController ⭐
**Base Path:** `/system/audit/audited`  
**Security:** `AUDIT:READ` / `AUDIT:ADMIN`

**Standard CRUD:** All inherited endpoints

**Custom Audit Endpoints:**

| Method | Endpoint | Security | Description |
|--------|----------|----------|-------------|
| GET | `/entity/{entityName}/{entityId}` | `AUDIT:READ` or `AUDIT:USER` | Get audit history for entity |
| GET | `/user/{username}` | `AUDIT:READ` or own username | Get user's audit trail |
| GET | `/date-range?startDate=2025-01-01&endDate=2025-12-31` | `AUDIT:READ` | Get logs by date range |
| GET | `/failed` | `AUDIT:READ` | Get failed operations |
| GET | `/user/{username}/summary?days=30` | `AUDIT:READ` or own username | Get activity summary |

**Example - Get Entity Audit History:**
```http
GET /system/audit/audited/entity/Currency/123
Authorization: Bearer {token}
```

**Response:**
```json
[
  {
    "id": 1,
    "entityName": "Currency",
    "entityId": 123,
    "action": "UPDATE",
    "username": "admin@example.com",
    "timestamp": "2025-12-20T20:15:00Z",
    "changes": {
      "code": {"old": "USD", "new": "USD"},
      "rate": {"old": 1.0, "new": 1.1}
    },
    "success": true
  }
]
```

---

### Security Module (5 Controllers)

User, role, and permission management.

#### UserController ⭐
**Base Path:** `/system/security/user`  
**Security:** `USER:READ` / `USER:ADMIN` (with exceptions)

**Standard CRUD:** All inherited endpoints  
**Special:** Users can update their own data

**User Search Endpoints:**

| Method | Endpoint | Security | Description |
|--------|----------|----------|-------------|
| GET | `/by-username/{username}` | `USER:ADMIN` or own username | Get user by username |
| GET | `/by-email/{email}` | `USER:ADMIN` | Get user by email |
| GET | `/exists/username/{username}` | Public | Check if username exists |
| GET | `/exists/email/{email}` | Public | Check if email exists |

**Password Management:**

| Method | Endpoint | Security | Description |
|--------|----------|----------|-------------|
| POST | `/reset-password` | `USER:ADMIN` | Reset user password |

**Role Management:**

| Method | Endpoint | Security | Description |
|--------|----------|----------|-------------|
| POST | `/{userId}/roles/{roleId}` | `USER:ADMIN` | Assign role to user |
| DELETE | `/{userId}/roles/{roleId}` | `USER:ADMIN` | Remove role from user |
| GET | `/by-role/{roleId}` | `USER:ADMIN` | Get all users with role |

**Group Management:**

| Method | Endpoint | Security | Description |
|--------|----------|----------|-------------|
| POST | `/{userId}/groups/{groupId}` | `USER:ADMIN` | Assign group to user |
| DELETE | `/{userId}/groups/{groupId}` | `USER:ADMIN` | Remove group from user |
| GET | `/by-group/{groupId}` | `USER:ADMIN` | Get all users in group |

**Account Status:**

| Method | Endpoint | Security | Description |
|--------|----------|----------|-------------|
| PUT | `/{id}/enable` | `USER:ADMIN` | Enable user account |
| PUT | `/{id}/disable` | `USER:ADMIN` | Disable user account |
| PUT | `/{id}/lock` | `USER:ADMIN` | Lock user account |
| PUT | `/{id}/unlock` | `USER:ADMIN` | Unlock user account |

#### RoleController
**Base Path:** `/system/security/role`  
**Security:** `ROLE:READ` / `ROLE:ADMIN`

#### PermissionController
**Base Path:** `/system/security/permission`  
**Security:** `PERMISSION:READ` / `PERMISSION:ADMIN`

#### GroupController
**Base Path:** `/system/security/group`  
**Security:** `GROUP:READ` / `GROUP:ADMIN`

#### AuthorityController
**Base Path:** `/system/security/authority`  
**Security:** `AUTHORITY:READ` / `AUTHORITY:ADMIN`

---

### Utility Module

#### FileController ⭐
**Base Path:** `/system/utility/file`  
**Security:** Various (see individual endpoints)

**File Upload/Download:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/upload` | Upload single file |
| POST | `/upload/batch` | Upload multiple files |
| GET | `/{id}/download` | Download file |
| PUT | `/{id}/replace` | Replace file content |
| POST | `/{id}/copy` | Copy file |
| DELETE | `/{id}` | Delete file (physical + metadata) |

**File Information:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/{id}/physical/exists` | Check if physical file exists |
| GET | `/{id}/physical-size` | Get file size |
| GET | `/{id}/content-type` | Get content type |
| GET | `/by-path?path=/uploads/file.pdf` | Find file by path |
| GET | `/by-extension/{ext}` | Find files by extension |
| GET | `/by-type/{fileType}` | Find files by type |

**Content Type Operations:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/content-types` | Get all supported types |
| GET | `/content-types/check/{extension}` | Check if extension supported |

**Maintenance:**

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/cleanup/orphans` | Remove orphaned files |
| GET | `/missing-physical` | Find missing physical files |
| GET | `/stats` | Get storage statistics |

**Example - Upload File:**
```http
POST /system/utility/file/upload
Content-Type: multipart/form-data
Authorization: Bearer {token}

--boundary
Content-Disposition: form-data; name="file"; filename="document.pdf"
Content-Type: application/pdf

{binary data}
--boundary
Content-Disposition: form-data; name="fileType"

CONTRACT_DOCUMENT
--boundary--
```

**Response:**
```json
{
  "id": 456,
  "filename": "document.pdf",
  "originalFilename": "document.pdf",
  "path": "/uploads/2025/12/20/abc123_document.pdf",
  "extension": "pdf",
  "size": 1048576,
  "fileType": "CONTRACT_DOCUMENT",
  "contentType": "application/pdf",
  "uploadDate": "2025-12-20T20:30:00Z"
}
```

---

## Error Handling

### HTTP Status Codes

| Code | Description | When |
|------|-------------|------|
| 200 | OK | Successful GET, PUT |
| 201 | Created | Successful POST |
| 204 | No Content | Successful DELETE |
| 400 | Bad Request | Validation errors |
| 401 | Unauthorized | Missing/invalid token |
| 403 | Forbidden | Insufficient permissions |
| 404 | Not Found | Entity not found |
| 409 | Conflict | Duplicate unique field |
| 500 | Internal Server Error | Unexpected error |

### Error Response Format

```json
{
  "timestamp": "2025-12-20T20:35:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed for field 'code': must not be blank",
  "path": "/business/core/currency",
  "errors": [
    {
      "field": "code",
      "message": "must not be blank",
      "rejectedValue": null
    }
  ]
}
```

### Common Validation Errors

- `must not be null` - Required field missing
- `must not be blank` - Empty string not allowed
- `size must be between X and Y` - Length validation
- `must be a valid email` - Email format invalid
- `already exists` - Unique constraint violation

---

## Complete Endpoint Reference

### All Base Paths (90 Controllers)

```
# Authentication (1)
/auth

# Business - Amendment (4)
/business/amendment/amendment
/business/amendment/phase
/business/amendment/step
/business/amendment/type

# Business - Consultation (5)
/business/consultation/award-method
/business/consultation/consultation
/business/consultation/phase
/business/consultation/step
/business/consultation/submission

# Business - Contract (5)
/business/contract/contract
/business/contract/item
/business/contract/phase
/business/contract/step
/business/contract/type

# Business - Core (5)
/business/core/approval-status
/business/core/currency
/business/core/procurement-director
/business/core/procurement-nature
/business/core/procurement-status

# Business - Plan (9)
/business/plan/budget-modification
/business/plan/budget-type
/business/plan/domain
/business/plan/financial-operation
/business/plan/item
/business/plan/item-distribution
/business/plan/item-status
/business/plan/planned-item
/business/plan/rubric

# Business - Provider (7)
/business/provider/clearance
/business/provider/economic-domain
/business/provider/economic-nature
/business/provider/exclusion
/business/provider/exclusion-type
/business/provider/provider
/business/provider/representator

# Common - Administration (10)
/common/administration/country
/common/administration/employee
/common/administration/job
/common/administration/locality
/common/administration/military-category
/common/administration/military-rank
/common/administration/person
/common/administration/state
/common/administration/structure
/common/administration/structure-type

# Common - Communication (3)
/common/communication/mail
/common/communication/mail-nature
/common/communication/mail-type

# Common - Document (2)
/common/document/document
/common/document/type

# Common - Environment (7)
/common/environment/archive-box
/common/environment/bloc
/common/environment/floor
/common/environment/folder
/common/environment/room
/common/environment/shelf
/common/environment/shelf-floor

# Network - Common (8)
/network/common/alloy
/network/common/location
/network/common/operational-status
/network/common/partner
/network/common/product
/network/common/region
/network/common/vendor
/network/common/zone

# Network - Core (9)
/network/core/equipment
/network/core/facility
/network/core/hydrocarbon-field
/network/core/infrastructure
/network/core/pipeline
/network/core/pipeline-segment
/network/core/pipeline-system
/network/core/station
/network/core/terminal

# Network - Type (8)
/network/type/company-type
/network/type/equipment-type
/network/type/facility-type
/network/type/hydrocarbon-field-type
/network/type/partner-type
/network/type/station-type
/network/type/terminal-type
/network/type/vendor-type

# System - Audit (1)
/system/audit/audited

# System - Security (5)
/system/security/authority
/system/security/group
/system/security/permission
/system/security/role
/system/security/user

# System - Utility (1)
/system/utility/file
```

---

## Best Practices

### Pagination

**Always use pagination** for list endpoints:
```http
GET /business/core/currency?page=0&size=50&sortBy=code&sortDir=asc
```

### Search

**Use global search** when available:
```http
GET /business/core/currency/search?q=dollar&page=0&size=20
```

### Validation

**Check for duplicates** before creating:
```http
GET /business/core/currency/exists/code/USD
```

### File Upload

**Always specify fileType** for organization:
```http
POST /system/utility/file/upload
- file: document.pdf
- fileType: CONTRACT_DOCUMENT
```

### Audit Trail

**Check audit logs** for sensitive operations:
```http
GET /system/audit/audited/entity/User/123
```

---

## Support

For issues or questions:
- Review this documentation
- Check error messages in responses
- Contact development team
- Review source code in repository

---

**Generated:** December 20, 2025  
**Version:** 1.0  
**Repository:** [CHOUABBIA-AMINE/iaas](https://github.com/CHOUABBIA-AMINE/iaas)
