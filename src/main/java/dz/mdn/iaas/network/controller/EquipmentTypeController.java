/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentTypeController
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
import dz.mdn.iaas.network.dto.EquipmentTypeDTO;
import dz.mdn.iaas.network.service.EquipmentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network/equipment-type")
@Slf4j
public class EquipmentTypeController extends GenericController<EquipmentTypeDTO, Long> {

    @SuppressWarnings("unused")
	private final EquipmentTypeService equipmentTypeService;

    public EquipmentTypeController(EquipmentTypeService equipmentTypeService) {
        super(equipmentTypeService, "EquipmentType");
        this.equipmentTypeService = equipmentTypeService;
    }
}
