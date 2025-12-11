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

package dz.mdn.iaas.network.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.common.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.common.service.OperationalStatusService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/network/operational-status")
@Slf4j
public class OperationalStatusController extends GenericController<OperationalStatusDTO, Long> {

    @SuppressWarnings("unused")
	private final OperationalStatusService operationalStatusService;

    public OperationalStatusController(OperationalStatusService operationalStatusService) {
        super(operationalStatusService, "OperationalStatus");
        this.operationalStatusService = operationalStatusService;
    }
}
