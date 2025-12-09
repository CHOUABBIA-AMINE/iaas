/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItemController
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.dto.ContractItemDTO;
import dz.mdn.iaas.business.contract.service.ContractItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ContractItem REST Controller
 * Handles CRUD and search operations for contract items
 * Based on the ContractItem model (F_00=id through F_06=contract)
 */
@RestController
@RequestMapping("/contractItem")
@RequiredArgsConstructor
@Slf4j
public class ContractItemController {

    private final ContractItemService contractItemService;

    // ========== CREATE ONE CONTRACT ITEM ==========

    /**
     * Create new contract item
     */
    @PostMapping
    public ResponseEntity<ContractItemDTO> createContractItem(@Valid @RequestBody ContractItemDTO dto) {
        log.info("Creating contract item with reference: {}, contractId: {}", dto.getReference(), dto.getContractId());
        
        ContractItemDTO created = contractItemService.createContractItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ========== READ ONE ==========

    /**
     * Get contract item metadata by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractItemDTO> getContractItemById(@PathVariable Long id) {
        log.debug("Getting contract item with ID: {}", id);
        ContractItemDTO item = contractItemService.getContractItemById(id);
        return ResponseEntity.ok(item);
    }

    // ========== UPDATE ONE ==========

    /**
     * Update contract item by ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractItemDTO> updateContractItem(@PathVariable Long id, @Valid @RequestBody ContractItemDTO dto) {
        log.info("Updating contract item with ID: {}", id);
        ContractItemDTO updated = contractItemService.updateContractItem(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete contract item by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractItem(@PathVariable Long id) {
        log.info("Deleting contract item with ID: {}", id);
        contractItemService.deleteContractItem(id);
        return ResponseEntity.noContent().build();
    }

    // ========== LIST ALL ==========

    /**
     * Get all contract items with pagination and sorting
     */
    @GetMapping
    public ResponseEntity<Page<ContractItemDTO>> getAllContractItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "reference") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting all contract items - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ContractItemDTO> items = contractItemService.getAllContractItems(pageable);

        return ResponseEntity.ok(items);
    }

    // ========== FILTERS ==========

    /**
     * Get all contract items belonging to a specific contract
     */
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<Page<ContractItemDTO>> getContractItemsByContract(
            @PathVariable Long contractId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting contract items for contract ID: {}", contractId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "reference"));
        Page<ContractItemDTO> items = contractItemService.getContractItemsByContract(contractId, pageable);

        return ResponseEntity.ok(items);
    }

    /**
     * Get contract items by reference
     */
    @GetMapping("/reference/{reference}")
    public ResponseEntity<ContractItemDTO> getContractItemByReference(@PathVariable String reference) {
        log.debug("Getting contract item by reference: {}", reference);
        return contractItemService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search contract items by designation or observation
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ContractItemDTO>> searchContractItems(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "reference") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Searching contract items with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ContractItemDTO> results = contractItemService.searchContractItems(query, pageable);

        return ResponseEntity.ok(results);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if contract item exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkContractItemExists(@PathVariable Long id) {
        log.debug("Checking existence of contract item ID: {}", id);
        boolean exists = contractItemService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if reference exists
     */
    @GetMapping("/exists/reference/{reference}")
    public ResponseEntity<Boolean> checkContractItemExistsByReference(@PathVariable String reference) {
        log.debug("Checking existence of contract item by reference: {}", reference);
        boolean exists = contractItemService.existsByReference(reference);
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of contract items
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getContractItemsCount() {
        log.debug("Getting total count of contract items");
        Long count = contractItemService.getTotalCount();
        return ResponseEntity.ok(count);
    }

    /**
     * Get total quantity sum for a contract
     */
    @GetMapping("/contract/{contractId}/quantity-sum")
    public ResponseEntity<Double> getTotalQuantityByContract(@PathVariable Long contractId) {
        log.debug("Getting total quantity for contract ID: {}", contractId);
        Double totalQuantity = contractItemService.getTotalQuantityByContract(contractId);
        return ResponseEntity.ok(totalQuantity);
    }

    /**
     * Get total value (quantity * unit price) per contract
     */
    @GetMapping("/contract/{contractId}/total-value")
    public ResponseEntity<Double> getTotalValueByContract(@PathVariable Long contractId) {
        log.debug("Getting total value for contract ID: {}", contractId);
        Double totalValue = contractItemService.getTotalValueByContract(contractId);
        return ResponseEntity.ok(totalValue);
    }
}
