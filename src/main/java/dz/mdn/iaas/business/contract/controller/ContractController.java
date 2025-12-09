/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractController
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.controller;

import dz.mdn.iaas.business.contract.dto.ContractDTO;
import dz.mdn.iaas.business.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing Contracts.
 * Provides endpoints for CRUD operations and searches aligned with the ContractService logic.
 */
@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ContractController {

	private final ContractService contractService;

	// ========== CREATE ==========

	/**
	 * Create a new contract.
	 *
	 * @param dto Contract data to create.
	 * @return The created ContractDTO.
	 */
	@PostMapping
	public ResponseEntity<ContractDTO> createContract(@RequestBody @Validated ContractDTO dto) {
		log.info("Received request to create contract with internalId={}", dto.getInternalId());
		ContractDTO created = contractService.createContract(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	// ========== READ ==========

	/**
	 * Get contract by ID.
	 *
	 * @param id Contract ID.
	 * @return ContractDTO if found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ContractDTO> getContractById(@PathVariable Long id) {
		log.debug("Fetching contract by ID={}", id);
		ContractDTO dto = contractService.getContractById(id);
		return ResponseEntity.ok(dto);
	}

	/**
	 * Get all contracts with pagination, sorted by contract date descending.
	 */
	@GetMapping
	public ResponseEntity<Page<ContractDTO>> getAllContracts(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "contractDate") String sortBy,
			@RequestParam(defaultValue = "desc") String sortDir) {

		log.debug("Fetching all contracts (page={}, size={}, sortBy={}, sortDir={})",
				page, size, sortBy, sortDir);

		Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

		Page<ContractDTO> contracts = contractService.getAllContracts(pageable);
		return ResponseEntity.ok(contracts);
	}

	/**
	 * Search contracts by reference or designation.
	 */
	@GetMapping("/search")
	public ResponseEntity<Page<ContractDTO>> searchContracts(
			@RequestParam(required = false) String query,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "designationFr") String sortBy) {

		log.debug("Searching contracts with query='{}'", query);
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
		Page<ContractDTO> results = contractService.searchContracts(query, pageable);
		return ResponseEntity.ok(results);
	}

	/**
	 * Get contract by internal ID.
	 */
	@GetMapping("/internal/{internalId}")
	public ResponseEntity<ContractDTO> getByInternalId(@PathVariable String internalId) {
		log.debug("Fetching contract by internalId='{}'", internalId);
		return contractService.findByInternalId(internalId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// ========== UPDATE ==========

	/**
	 * Update an existing contract.
	 *
	 * @param id  ID of contract to update.
	 * @param dto Updated contract data.
	 * @return Updated ContractDTO.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ContractDTO> updateContract(
			@PathVariable Long id,
			@RequestBody @Validated ContractDTO dto) {

		log.info("Updating contract ID={} with internalId={}", id, dto.getInternalId());
		ContractDTO updated = contractService.updateContract(id, dto);
		return ResponseEntity.ok(updated);
	}

	// ========== DELETE ==========

	/**
	 * Delete contract by ID.
	 *
	 * @param id Contract ID.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
		log.info("Deleting contract ID={}", id);
		contractService.deleteContract(id);
		return ResponseEntity.noContent().build();
	}

	// ========== EXCEPTIONS ==========

	/**
	 * Handle business exceptions with uniform 400 response.
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		log.error("ContractController error: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
