/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentTypeController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-12-2025
 *
 *	@Type		: Controller
 *	@Layer		: Network / Controller
 *	@Package	: Network / Controller
 *
 **/

package dz.mdn.iaas.network.core.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.core.dto.EquipmentTypeDTO;
import dz.mdn.iaas.network.core.service.EquipmentTypeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/equipment-type")
@Slf4j
public class EquipmentTypeController extends GenericController<EquipmentTypeDTO, Long> {

    private final EquipmentTypeService equipmentTypeService;

    public EquipmentTypeController(EquipmentTypeService equipmentTypeService) {
        super(equipmentTypeService, "EquipmentType");
        this.equipmentTypeService = equipmentTypeService;
    }

    // ========== SECURED CRUD OPERATIONS ==========

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:READ')")
    public ResponseEntity<EquipmentTypeDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:READ')")
    public ResponseEntity<Page<EquipmentTypeDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return super.getAll(page, size, sortBy, sortDir);
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:READ')")
    public ResponseEntity<List<EquipmentTypeDTO>> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:ADMIN')")
    public ResponseEntity<EquipmentTypeDTO> create(@Valid @RequestBody EquipmentTypeDTO dto) {
        return super.create(dto);
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:ADMIN')")
    public ResponseEntity<EquipmentTypeDTO> update(@PathVariable Long id, @Valid @RequestBody EquipmentTypeDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:READ')")
    public ResponseEntity<Page<EquipmentTypeDTO>> search(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return super.search(q, page, size, sortBy, sortDir);
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:READ')")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return super.exists(id);
    }

    @Override
    @PreAuthorize("hasAuthority('EQUIPMENT_TYPE:READ')")
    public ResponseEntity<Long> count() {
        return super.count();
    }
}
