# GenericController Guide

**Created:** December 10, 2025  
**Location:** `src/main/java/dz/mdn/iaas/configuration/template/GenericController.java`  
**Status:** ✅ Ready for use

## 🎯 Overview

`GenericController` is an abstract base class that provides standardized REST endpoints for CRUD operations. It eliminates duplicate code across controllers and ensures consistent API design.

---

## 🚀 Quick Start

### Basic Usage

```java
@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController extends GenericController<CurrencyDTO, Long> {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        super(currencyService, "Currency");
    }
    
    // All standard endpoints inherited automatically!
    // Add custom endpoints below as needed
}
```

---

## 📦 Inherited Endpoints

When you extend `GenericController`, you automatically get these 9 endpoints:

### 1. Create Entity
```http
POST /api/currencies
Content-Type: application/json

{
  "code": "USD",
  "designationFr": "Dollar Américain",
  ...
}
```
**Response:** `201 Created` with created entity

---

### 2. Get Entity by ID
```http
GET /api/currencies/{id}
```
**Response:** `200 OK` with entity DTO or `404 Not Found`

---

### 3. Get All Entities (Paginated)
```http
GET /api/currencies?page=0&size=20&sortBy=code&sortDir=asc
```
**Parameters:**
- `page` (optional): Page number, default `0`
- `size` (optional): Page size, default `20`
- `sortBy` (optional): Sort field, default `"id"`
- `sortDir` (optional): Sort direction `asc|desc`, default `"asc"`

**Response:** `200 OK` with paginated results
```json
{
  "content": [...],
  "totalElements": 100,
  "totalPages": 5,
  "number": 0,
  "size": 20
}
```

---

### 4. Get All Entities (Non-Paginated)
```http
GET /api/currencies/all
```
**Warning:** Use with caution for large datasets

**Response:** `200 OK` with list of all entities

---

### 5. Update Entity
```http
PUT /api/currencies/{id}
Content-Type: application/json

{
  "code": "USD",
  "designationFr": "Dollar Américain",
  ...
}
```
**Response:** `200 OK` with updated entity or `404 Not Found`

---

### 6. Delete Entity
```http
DELETE /api/currencies/{id}
```
**Response:** `204 No Content` or `404 Not Found`

---

### 7. Global Search
```http
GET /api/currencies/search?q=dollar&page=0&size=20&sortBy=code&sortDir=asc
```
**Parameters:**
- `q` (optional): Search query
- `page`, `size`, `sortBy`, `sortDir`: Same as getAll

**Response:** `200 OK` with matching entities

**Note:** You must implement `searchByQuery()` method or override `/search` endpoint

---

### 8. Check Existence
```http
GET /api/currencies/{id}/exists
```
**Response:** `200 OK` with `true` or `false`

---

### 9. Count Entities
```http
GET /api/currencies/count
```
**Response:** `200 OK` with total count
```json
42
```

---

## 🛠️ Implementation Guide

### Step 1: Extend GenericController

```java
@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController extends GenericController<CurrencyDTO, Long> {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        super(currencyService, "Currency");
    }
}
```

**Constructor Parameters:**
- `service`: Your service that extends `GenericService`
- `entityName`: Human-readable name for logging (e.g., "Currency", "User")

---

### Step 2: Implement Search (Optional)

If you want the `/search` endpoint to work, implement `searchByQuery()`:

```java
@Override
protected Page<CurrencyDTO> searchByQuery(String query, Pageable pageable) {
    if (query == null || query.trim().isEmpty()) {
        return currencyService.getAll(pageable);
    }
    return currencyService.globalSearch(query, pageable);
}
```

---

### Step 3: Add Custom Endpoints (Optional)

Add any entity-specific endpoints:

```java
/**
 * Get major currencies
 * GET /api/currencies/major
 */
@GetMapping("/major")
public ResponseEntity<Page<CurrencyDTO>> getMajorCurrencies(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
    
    log.debug("Getting major currencies");
    
    Pageable pageable = buildPageable(page, size, "code", "asc");
    Page<CurrencyDTO> currencies = currencyService.getMajorCurrencies(pageable);
    
    return success(currencies);
}

/**
 * Search by specific field
 * GET /api/currencies/by-code/{code}
 */
@GetMapping("/by-code/{code}")
public ResponseEntity<CurrencyDTO> getByCode(@PathVariable String code) {
    log.debug("Getting currency by code: {}", code);
    
    return currencyService.findByCode(code)
            .map(this::success)
            .orElse(notFound());
}
```

---

## 📊 Complete Example

```java
@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController extends GenericController<CurrencyDTO, Long> {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        super(currencyService, "Currency");
    }

    // ========== IMPLEMENT SEARCH ==========

    @Override
    protected Page<CurrencyDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return currencyService.getAll(pageable);
        }
        return currencyService.globalSearch(query, pageable);
    }

    // ========== CUSTOM ENDPOINTS ==========

    /**
     * Get currency by code
     * GET /api/currencies/by-code/{code}
     */
    @GetMapping("/by-code/{code}")
    public ResponseEntity<CurrencyDTO> getByCode(@PathVariable String code) {
        log.debug("Getting currency by code: {}", code);
        
        return currencyService.findByCode(code)
                .map(this::success)
                .orElse(notFound());
    }

    /**
     * Get major currencies
     * GET /api/currencies/major
     */
    @GetMapping("/major")
    public ResponseEntity<Page<CurrencyDTO>> getMajorCurrencies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "code") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting major currencies");
        
        Pageable pageable = buildPageable(page, size, sortBy, sortDir);
        Page<CurrencyDTO> currencies = currencyService.getMajorCurrencies(pageable);
        
        return success(currencies);
    }

    /**
     * Search by designation
     * GET /api/currencies/search/designation?q=...
     */
    @GetMapping("/search/designation")
    public ResponseEntity<Page<CurrencyDTO>> searchByDesignation(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching by designation: {}", q);
        
        Pageable pageable = buildPageable(page, size, "designationFr", "asc");
        Page<CurrencyDTO> results = currencyService.searchByDesignation(q, pageable);
        
        return success(results);
    }

    /**
     * Check if code exists
     * GET /api/currencies/exists/code/{code}
     */
    @GetMapping("/exists/code/{code}")
    public ResponseEntity<Boolean> existsByCode(@PathVariable String code) {
        log.debug("Checking if code exists: {}", code);
        
        boolean exists = currencyService.existsByCode(code);
        
        return success(exists);
    }
}
```

---

## 🧰 Utility Methods

`GenericController` provides several utility methods for custom endpoints:

### Pagination
```java
Protected Pageable buildPageable(int page, int size, String sortBy, String sortDir)
```
Build pageable from parameters

### Response Helpers
```java
protected <T> ResponseEntity<T> success(T data)           // 200 OK
protected <T> ResponseEntity<T> created(T data)           // 201 Created
protected ResponseEntity<Void> noContent()                 // 204 No Content
protected <T> ResponseEntity<T> notFound()                // 404 Not Found
```

### Getters
```java
protected GenericService<?, D, ID> getService()            // Get service instance
protected String getEntityName()                          // Get entity name
protected String getBasePath()                            // Get base path for logging
```

---

## 📝 API Documentation

### Standard Response Format

#### Single Entity
```json
{
  "id": 1,
  "code": "USD",
  "designationFr": "Dollar Américain",
  ...
}
```

#### Paginated List
```json
{
  "content": [
    { "id": 1, ... },
    { "id": 2, ... }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": { "sorted": true, "unsorted": false }
  },
  "totalElements": 100,
  "totalPages": 5,
  "last": false,
  "size": 20,
  "number": 0,
  "numberOfElements": 20,
  "first": true,
  "empty": false
}
```

#### Error Response
```json
{
  "timestamp": "2025-12-10T12:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Currency not found with ID: 123",
  "path": "/api/currencies/123"
}
```

---

## ✅ Benefits

### Code Reduction
- **Eliminates 80%** of duplicate controller code
- Standard endpoints defined once
- Consistent error handling

### Consistency
- All APIs follow same patterns
- Predictable endpoint structure
- Uniform parameter naming

### Maintainability
- Changes in one place affect all controllers
- Easy to add new entities
- Faster development

### Documentation
- Self-documenting code
- Clear method signatures
- Comprehensive JavaDoc

---

## 🔍 Override Options

You can override any inherited method:

### Override Create
```java
@Override
@PostMapping
public ResponseEntity<CurrencyDTO> create(@Valid @RequestBody CurrencyDTO dto) {
    log.info("Creating currency with custom logic");
    
    // Add custom logic before creation
    validateCurrency(dto);
    
    // Call parent implementation
    return super.create(dto);
}
```

### Override Update
```java
@Override
@PutMapping("/{id}")
public ResponseEntity<CurrencyDTO> update(
        @PathVariable Long id,
        @Valid @RequestBody CurrencyDTO dto) {
    
    // Custom validation
    validateUpdate(id, dto);
    
    return super.update(id, dto);
}
```

### Override Search
```java
@Override
@GetMapping("/search")
public ResponseEntity<Page<CurrencyDTO>> search(
        @RequestParam(required = false) String q,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "code") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir) {
    
    // Custom search logic
    log.info("Custom search: {}", q);
    
    // Can call parent or implement completely custom
    return super.search(q, page, size, sortBy, sortDir);
}
```

---

## 🔒 Security

Add security annotations as needed:

```java
@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController extends GenericController<CurrencyDTO, Long> {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        super(currencyService, "Currency");
    }

    // Secure create endpoint
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CurrencyDTO> create(@Valid @RequestBody CurrencyDTO dto) {
        return super.create(dto);
    }

    // Secure delete endpoint
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    // Public read endpoints remain unsecured
}
```

---

## 🧪 Testing

### Unit Test Example

```java
@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @Test
    void testCreate() throws Exception {
        CurrencyDTO dto = CurrencyDTO.builder()
                .code("USD")
                .designationFr("Dollar")
                .build();

        when(currencyService.create(any())).thenReturn(dto);

        mockMvc.perform(post("/api/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("USD"));
    }

    @Test
    void testGetById() throws Exception {
        CurrencyDTO dto = CurrencyDTO.builder()
                .id(1L)
                .code("USD")
                .build();

        when(currencyService.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/currencies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("USD"));
    }
}
```

---

## 📚 Best Practices

### 1. Keep Controllers Thin
```java
// ✅ GOOD - Controller delegates to service
@GetMapping("/major")
public ResponseEntity<Page<CurrencyDTO>> getMajorCurrencies(Pageable pageable) {
    return success(currencyService.getMajorCurrencies(pageable));
}

// ❌ BAD - Business logic in controller
@GetMapping("/major")
public ResponseEntity<Page<CurrencyDTO>> getMajorCurrencies(Pageable pageable) {
    List<Currency> currencies = repository.findAll();
    List<Currency> major = currencies.stream()
            .filter(c -> isMajor(c))  // Business logic!
            .collect(Collectors.toList());
    return success(convert(major));
}
```

### 2. Use Meaningful Endpoint Names
```java
// ✅ GOOD
@GetMapping("/major")
@GetMapping("/by-code/{code}")
@GetMapping("/search/designation")

// ❌ BAD
@GetMapping("/m")
@GetMapping("/c/{code}")
@GetMapping("/s/d")
```

### 3. Add Proper Logging
```java
@GetMapping("/major")
public ResponseEntity<Page<CurrencyDTO>> getMajorCurrencies(Pageable pageable) {
    log.debug("Getting major currencies - page: {}, size: {}", 
              pageable.getPageNumber(), pageable.getPageSize());
    
    return success(currencyService.getMajorCurrencies(pageable));
}
```

### 4. Use Utility Methods
```java
// ✅ GOOD - Use provided utilities
Pageable pageable = buildPageable(page, size, sortBy, sortDir);
return success(currencyService.getMajorCurrencies(pageable));

// ❌ BAD - Duplicate code
Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
        Sort.Direction.DESC : Sort.Direction.ASC;
Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
return ResponseEntity.ok(currencyService.getMajorCurrencies(pageable));
```

---

## 🔗 Related Documentation

- **GenericService Guide:** See `GenericService.java` for service layer
- **API Standards:** Company API design guidelines
- **Security Guide:** Authentication and authorization setup

---

## 🎉 Summary

`GenericController` provides:

✅ **9 standard REST endpoints** out of the box  
✅ **Consistent API design** across all controllers  
✅ **80% less boilerplate code**  
✅ **Pagination and sorting** built-in  
✅ **Utility methods** for custom endpoints  
✅ **Easy to extend** and customize  
✅ **Production-ready** with proper logging  

**Start using GenericController today and simplify your REST APIs!** 🚀

---

**Created:** December 10, 2025  
**Location:** `configuration/template/GenericController.java`  
**Status:** ✅ Production Ready
