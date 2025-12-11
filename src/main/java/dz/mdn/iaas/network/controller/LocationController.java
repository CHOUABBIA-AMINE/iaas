/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocationController
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
import dz.mdn.iaas.network.dto.LocationDTO;
import dz.mdn.iaas.network.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/location")
@Slf4j
public class LocationController extends GenericController<LocationDTO, Long> {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        super(locationService, "Location");
        this.locationService = locationService;
    }

    @GetMapping("/by-region/{regionId}")
    public ResponseEntity<List<LocationDTO>> getByRegion(@PathVariable Long regionId) {
        log.info("REST request to get Locations by region id: {}", regionId);
        return ResponseEntity.ok(locationService.findByRegion(regionId));
    }
}
