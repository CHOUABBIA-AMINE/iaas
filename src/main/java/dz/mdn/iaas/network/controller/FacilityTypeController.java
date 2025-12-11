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

package dz.mdn.iaas.network.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.dto.FacilityTypeDTO;
import dz.mdn.iaas.network.service.FacilityTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network/facility-type")
@Slf4j
public class FacilityTypeController extends GenericController<FacilityTypeDTO, Long> {

    private final FacilityTypeService facilityTypeService;

    public FacilityTypeController(FacilityTypeService facilityTypeService) {
        super(facilityTypeService);
        this.facilityTypeService = facilityTypeService;
    }
}
