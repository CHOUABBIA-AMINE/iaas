/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Network / Controller
 *	@Package	: Network / Controller
 *
 **/

package dz.mdn.iaas.network.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.dto.EquipmentDTO;
import dz.mdn.iaas.network.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/equipment")
@Slf4j
public class EquipmentController extends GenericController<EquipmentDTO, Long> {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        super(equipmentService, "Equipment");
        this.equipmentService = equipmentService;
    }

    @GetMapping("/by-facility/{facilityId}")
    public ResponseEntity<List<EquipmentDTO>> getByFacility(@PathVariable Long facilityId) {
        log.info("REST request to get Equipment by facility id: {}", facilityId);
        return ResponseEntity.ok(equipmentService.findByFacility(facilityId));
    }

    @GetMapping("/by-equipment-type/{typeId}")
    public ResponseEntity<List<EquipmentDTO>> getByEquipmentType(@PathVariable Long typeId) {
        log.info("REST request to get Equipment by equipment type id: {}", typeId);
        return ResponseEntity.ok(equipmentService.findByEquipmentType(typeId));
    }
}
