/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ZoneController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Network / Controller
 *	@Package	: Network / Controller
 *
 **/

package dz.mdn.iaas.network.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.common.dto.ZoneDTO;
import dz.mdn.iaas.network.common.service.ZoneService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/network/zone")
@Slf4j
public class ZoneController extends GenericController<ZoneDTO, Long> {

    @SuppressWarnings("unused")
	private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        super(zoneService, "Zone");
        this.zoneService = zoneService;
    }
}
