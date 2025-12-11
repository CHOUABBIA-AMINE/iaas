/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityTypeController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Network / Controller
 *	@Package	: Network / Controller
 *
 **/

package dz.mdn.iaas.network.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.core.dto.FacilityTypeDTO;
import dz.mdn.iaas.network.core.service.FacilityTypeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/network/facility-type")
@Slf4j
public class FacilityTypeController extends GenericController<FacilityTypeDTO, Long> {

    @SuppressWarnings("unused")
	private final FacilityTypeService facilityTypeService;

    public FacilityTypeController(FacilityTypeService facilityTypeService) {
        super(facilityTypeService, "FacilityType");
        this.facilityTypeService = facilityTypeService;
    }
}
