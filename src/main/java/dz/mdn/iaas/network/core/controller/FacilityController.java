/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Network / Common 
 *
 **/

package dz.mdn.iaas.network.core.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.core.dto.FacilityDTO;
import dz.mdn.iaas.network.core.service.FacilityService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/facility")
@Slf4j
public class FacilityController extends GenericController<FacilityDTO, Long> {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        super(facilityService, "Facility");
        this.facilityService = facilityService;
    }

    // ========== SECURED CRUD OPERATIONS ==========

    @Override
    @PreAuthorize("hasAuthority('FACILITY:READ')")
    public ResponseEntity<FacilityDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:READ')")
    public ResponseEntity<Page<FacilityDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return super.getAll(page, size, sortBy, sortDir);
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:READ')")
    public ResponseEntity<List<FacilityDTO>> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:ADMIN')")
    public ResponseEntity<FacilityDTO> create(@Valid @RequestBody FacilityDTO dto) {
        return super.create(dto);
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:ADMIN')")
    public ResponseEntity<FacilityDTO> update(@PathVariable Long id, @Valid @RequestBody FacilityDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:READ')")
    public ResponseEntity<Page<FacilityDTO>> search(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return super.search(q, page, size, sortBy, sortDir);
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:READ')")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return super.exists(id);
    }

    @Override
    @PreAuthorize("hasAuthority('FACILITY:READ')")
    public ResponseEntity<Long> count() {
        return super.count();
    }

    // ========== CUSTOM ENDPOINTS ==========

    @GetMapping("/type/{typeId}")
    @PreAuthorize("hasAuthority('FACILITY:READ')")
    public ResponseEntity<List<FacilityDTO>> getByFacilityType(@PathVariable Long typeId) {
        log.info("REST request to get Facility by facility type id: {}", typeId);
        return ResponseEntity.ok(facilityService.findByFacilityType(typeId));
    }
}
