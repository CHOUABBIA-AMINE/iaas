/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentController
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.controller;

import dz.mdn.iaas.business.amendment.dto.AmendmentDTO;
import dz.mdn.iaas.business.amendment.service.AmendmentService;
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
 * REST Controller for managing Amendments.
 * Provides endpoints for CRUD operations and searches aligned with the AmendmentService logic.
 */
@RestController
@RequestMapping("/amendment")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AmendmentController {

	private final AmendmentService amendmentService;

	// ========== CREATE ==========

	/**
	 * Create a new amendment.
	 *
	 * @param dto Amendment data to create.
	 * @return The created AmendmentDTO.
	 */
	@PostMapping
	public ResponseEntity<AmendmentDTO> createAmendment(@RequestBody @Validated AmendmentDTO dto) {
		log.info("Received request to create amendment with internalId={}", dto.getInternalId());
		AmendmentDTO created = amendmentService.createAmendment(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	// ========== READ ==========

	/**
	 * Get amendment by ID.
	 *
	 * @param id Amendment ID.
	 * @return AmendmentDTO if found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AmendmentDTO> getAmendmentById(@PathVariable Long id) {
		log.debug("Fetching amendment by ID={}", id);
		AmendmentDTO dto = amendmentService.getAmendmentById(id);
		return ResponseEntity.ok(dto);
	}

	/**
	 * Get all amendments with pagination, sorted by approval date descending.
	 */
	@GetMapping
	public ResponseEntity<Page<AmendmentDTO>> getAllAmendments(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "approvalDate") String sortBy,
			@RequestParam(defaultValue = "desc") String sortDir) {

		log.debug("Fetching all amendments (page={}, size={}, sortBy={}, sortDir={})",
				page, size, sortBy, sortDir);

		Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

		Page<AmendmentDTO> amendments = amendmentService.getAllAmendments(pageable);
		return ResponseEntity.ok(amendments);
	}

	/**
	 * Search amendments by reference or designation.
	 */
	@GetMapping("/search")
	public ResponseEntity<Page<AmendmentDTO>> searchAmendments(
			@RequestParam(required = false) String query,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "designationFr") String sortBy) {

		log.debug("Searching amendments with query='{}'", query);
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
		Page<AmendmentDTO> results = amendmentService.searchAmendments(query, pageable);
		return ResponseEntity.ok(results);
	}

	/**
	 * Get amendment by internal ID.
	 */
	@GetMapping("/internal/{internalId}")
	public ResponseEntity<AmendmentDTO> getByInternalId(@PathVariable int internalId) {
		log.debug("Fetching amendment by internalId='{}'", internalId);
		return amendmentService.findByInternalId(internalId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// ========== UPDATE ==========

	/**
	 * Update an existing amendment.
	 *
	 * @param id  ID of amendment to update.
	 * @param dto Updated amendment data.
	 * @return Updated AmendmentDTO.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<AmendmentDTO> updateAmendment(
			@PathVariable Long id,
			@RequestBody @Validated AmendmentDTO dto) {

		log.info("Updating amendment ID={} with internalId={}", id, dto.getInternalId());
		AmendmentDTO updated = amendmentService.updateAmendment(id, dto);
		return ResponseEntity.ok(updated);
	}

	// ========== DELETE ==========

	/**
	 * Delete amendment by ID.
	 *
	 * @param id Amendment ID.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAmendment(@PathVariable Long id) {
		log.info("Deleting amendment ID={}", id);
		amendmentService.deleteAmendment(id);
		return ResponseEntity.noContent().build();
	}

	// ========== EXCEPTIONS ==========

	/**
	 * Handle business exceptions with uniform 400 response.
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		log.error("AmendmentController error: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
