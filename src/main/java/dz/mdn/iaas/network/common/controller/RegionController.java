/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RegionController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Network / Controller
 *	@Package	: Network / Controller
 *
 **/

package dz.mdn.iaas.network.common.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.common.dto.RegionDTO;
import dz.mdn.iaas.network.common.service.RegionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/network/region")
@Slf4j
public class RegionController extends GenericController<RegionDTO, Long> {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        super(regionService, "Region");
        this.regionService = regionService;
    }

    @GetMapping("/by-zone/{zoneId}")
    public ResponseEntity<List<RegionDTO>> getByZone(@PathVariable Long zoneId) {
        log.info("REST request to get Regions by zone id: {}", zoneId);
        return ResponseEntity.ok(regionService.findByZone(zoneId));
    }
}
