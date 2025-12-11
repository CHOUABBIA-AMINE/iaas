/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityController
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
import dz.mdn.iaas.network.core.dto.FacilityDTO;
import dz.mdn.iaas.network.core.service.FacilityService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/network/facility")
@Slf4j
public class FacilityController extends GenericController<FacilityDTO, Long> {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        super(facilityService, "Facility");
        this.facilityService = facilityService;
    }

    @GetMapping("/by-location/{locationId}")
    public ResponseEntity<List<FacilityDTO>> getByLocation(@PathVariable Long locationId) {
        log.info("REST request to get Facilities by location id: {}", locationId);
        return ResponseEntity.ok(facilityService.findByLocation(locationId));
    }

    @GetMapping("/by-facility-type/{typeId}")
    public ResponseEntity<List<FacilityDTO>> getByFacilityType(@PathVariable Long typeId) {
        log.info("REST request to get Facilities by facility type id: {}", typeId);
        return ResponseEntity.ok(facilityService.findByFacilityType(typeId));
    }
}
