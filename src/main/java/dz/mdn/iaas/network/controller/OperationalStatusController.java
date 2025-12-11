/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: OperationalStatusController
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
import dz.mdn.iaas.network.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.service.OperationalStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network/operational-status")
@Slf4j
public class OperationalStatusController extends GenericController<OperationalStatusDTO, Long> {

    private final OperationalStatusService operationalStatusService;

    public OperationalStatusController(OperationalStatusService operationalStatusService) {
        super(operationalStatusService, "OperationalStatus");
        this.operationalStatusService = operationalStatusService;
    }
}
