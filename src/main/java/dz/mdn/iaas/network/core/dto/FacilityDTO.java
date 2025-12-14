/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FacilityDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.core.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.dto.LocationDTO;
import dz.mdn.iaas.network.common.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.common.model.Location;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.core.model.Facility;
import dz.mdn.iaas.network.core.model.FacilityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Facility Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 * - code (F_02) - unique, required
 * - operationalStatusId (F_03) - required
 * - locationId (F_04) - required
 * - facilityTypeId (F_05) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityDTO extends GenericDTO<Facility> {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code;
    
    private LocalDate installationDate;

    private LocalDate commissioningDate;

    private LocalDate retirementDate;

    @Size(max = 100, message = "provider must not exceed 100 characters")
    private String provider;

    @NotNull(message = "Operational status is required")
    private Long operationalStatusId;

    @NotNull(message = "Location is required")
    private Long locationId;

    @NotNull(message = "Facility type is required")
    private Long facilityTypeId;

    // Nested DTOs for read operations
    private OperationalStatusDTO operationalStatus;
    private LocationDTO location;
    private FacilityTypeDTO facilityType;

    @Override
    public Facility toEntity() {
        Facility facility = new Facility();
        facility.setId(getId());
        facility.setName(this.name);
        facility.setCode(this.code);
        facility.setInstallationDate(this.installationDate);
        facility.setCommissioningDate(this.commissioningDate);
        facility.setRetirementDate(this.retirementDate);
        facility.setProvider(this.provider);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            facility.setOperationalStatus(status);
        }
        
        if (this.locationId != null) {
            Location location = new Location();
            location.setId(this.locationId);
            facility.setLocation(location);
        }
        
        if (this.facilityTypeId != null) {
            FacilityType type = new FacilityType();
            type.setId(this.facilityTypeId);
            facility.setFacilityType(type);
        }
        
        return facility;
    }

    @Override
    public void updateEntity(Facility facility) {
        if (this.name != null) facility.setName(this.name);
        if (this.code != null) facility.setCode(this.code);
        if (this.installationDate != null) facility.setInstallationDate(this.installationDate);
        if (this.commissioningDate != null) facility.setCommissioningDate(this.commissioningDate);
        if (this.retirementDate != null) facility.setRetirementDate(this.retirementDate);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            facility.setOperationalStatus(status);
        }
        
        if (this.locationId != null) {
            Location location = new Location();
            location.setId(this.locationId);
            facility.setLocation(location);
        }
        
        if (this.facilityTypeId != null) {
            FacilityType type = new FacilityType();
            type.setId(this.facilityTypeId);
            facility.setFacilityType(type);
        }
    }

    public static FacilityDTO fromEntity(Facility facility) {
        if (facility == null) return null;
        
        return FacilityDTO.builder()
                .id(facility.getId())
                .name(facility.getName())
                .code(facility.getCode())
                .installationDate(facility.getInstallationDate())
                .commissioningDate(facility.getCommissioningDate())
                .retirementDate(facility.getRetirementDate())
                .provider(facility.getProvider())
                .operationalStatusId(facility.getOperationalStatus() != null ? facility.getOperationalStatus().getId() : null)
                .locationId(facility.getLocation() != null ? facility.getLocation().getId() : null)
                .facilityTypeId(facility.getFacilityType() != null ? facility.getFacilityType().getId() : null)
                .operationalStatus(facility.getOperationalStatus() != null ? OperationalStatusDTO.fromEntity(facility.getOperationalStatus()) : null)
                .location(facility.getLocation() != null ? LocationDTO.fromEntity(facility.getLocation()) : null)
                .facilityType(facility.getFacilityType() != null ? FacilityTypeDTO.fromEntity(facility.getFacilityType()) : null)
                .build();
    }
}
