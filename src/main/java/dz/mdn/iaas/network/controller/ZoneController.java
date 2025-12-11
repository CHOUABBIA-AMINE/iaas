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

package dz.mdn.iaas.network.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.dto.ZoneDTO;
import dz.mdn.iaas.network.service.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network/zone")
@Slf4j
public class ZoneController extends GenericController<ZoneDTO, Long> {

    public ZoneController(ZoneService zoneService) {
        super(zoneService);
    }
}
