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

package dz.mdn.iaas.network.core.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.core.dto.EquipmentDTO;
import dz.mdn.iaas.network.core.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;

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
