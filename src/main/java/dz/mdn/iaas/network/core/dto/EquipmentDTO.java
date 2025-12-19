/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EquipmentDTO
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
import dz.mdn.iaas.network.common.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.core.model.Equipment;
import dz.mdn.iaas.network.core.model.Facility;
import dz.mdn.iaas.network.type.dto.EquipmentTypeDTO;
import dz.mdn.iaas.network.type.model.EquipmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Equipment Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 * - code (F_02) - required
 * - manufacturer (F_03) - required
 * - modelNumber (F_04) - required
 * - serialNumber (F_05) - required
 * - manufacturingDate (F_06) - required
 * - installationDate (F_07) - required
 * - lastMaintenanceDate (F_08) - required
 * - operationalStatusId (F_09) - required
 * - equipmentTypeId (F_10) - required
 * - facilityId (F_11) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentDTO extends GenericDTO<Equipment> {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "Manufacturer is required")
    @Size(max = 100, message = "Manufacturer must not exceed 100 characters")
    private String manufacturer;

    @NotBlank(message = "Model number is required")
    @Size(max = 50, message = "Model number must not exceed 50 characters")
    private String modelNumber;

    @NotBlank(message = "Serial number is required")
    @Size(max = 100, message = "Serial number must not exceed 100 characters")
    private String serialNumber;

    @NotNull(message = "Manufacturing date is required")
    private LocalDate manufacturingDate;

    @NotNull(message = "Installation date is required")
    private LocalDate installationDate;

    @NotNull(message = "Last maintenance date is required")
    private LocalDate lastMaintenanceDate;

    @NotNull(message = "Operational status is required")
    private Long operationalStatusId;

    @NotNull(message = "Equipment type is required")
    private Long equipmentTypeId;

    @NotNull(message = "Facility is required")
    private Long facilityId;

    // Nested DTOs
    private OperationalStatusDTO operationalStatus;
    private EquipmentTypeDTO equipmentType;
    private FacilityDTO facility;

    @Override
    public Equipment toEntity() {
        Equipment equipment = new Equipment();
        equipment.setId(getId());
        equipment.setName(this.name);
        equipment.setCode(this.code);
        equipment.setManufacturer(this.manufacturer);
        equipment.setModelNumber(this.modelNumber);
        equipment.setSerialNumber(this.serialNumber);
        equipment.setManufacturingDate(this.manufacturingDate);
        equipment.setInstallationDate(this.installationDate);
        equipment.setLastMaintenanceDate(this.lastMaintenanceDate);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            equipment.setOperationalStatus(status);
        }
        
        if (this.equipmentTypeId != null) {
            EquipmentType type = new EquipmentType();
            type.setId(this.equipmentTypeId);
            equipment.setEquipmentType(type);
        }
        
        if (this.facilityId != null) {
            Facility facility = new Facility();
            facility.setId(this.facilityId);
            equipment.setFacility(facility);
        }
        
        return equipment;
    }

    @Override
    public void updateEntity(Equipment equipment) {
        if (this.name != null) equipment.setName(this.name);
        if (this.code != null) equipment.setCode(this.code);
        if (this.manufacturer != null) equipment.setManufacturer(this.manufacturer);
        if (this.modelNumber != null) equipment.setModelNumber(this.modelNumber);
        if (this.serialNumber != null) equipment.setSerialNumber(this.serialNumber);
        if (this.manufacturingDate != null) equipment.setManufacturingDate(this.manufacturingDate);
        if (this.installationDate != null) equipment.setInstallationDate(this.installationDate);
        if (this.lastMaintenanceDate != null) equipment.setLastMaintenanceDate(this.lastMaintenanceDate);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            equipment.setOperationalStatus(status);
        }
        
        if (this.equipmentTypeId != null) {
            EquipmentType type = new EquipmentType();
            type.setId(this.equipmentTypeId);
            equipment.setEquipmentType(type);
        }
        
        if (this.facilityId != null) {
            Facility facility = new Facility();
            facility.setId(this.facilityId);
            equipment.setFacility(facility);
        }
    }

    public static EquipmentDTO fromEntity(Equipment equipment) {
        if (equipment == null) return null;
        
        return EquipmentDTO.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .code(equipment.getCode())
                .manufacturer(equipment.getManufacturer())
                .modelNumber(equipment.getModelNumber())
                .serialNumber(equipment.getSerialNumber())
                .manufacturingDate(equipment.getManufacturingDate())
                .installationDate(equipment.getInstallationDate())
                .lastMaintenanceDate(equipment.getLastMaintenanceDate())
                .operationalStatusId(equipment.getOperationalStatus() != null ? equipment.getOperationalStatus().getId() : null)
                .equipmentTypeId(equipment.getEquipmentType() != null ? equipment.getEquipmentType().getId() : null)
                .facilityId(equipment.getFacility() != null ? equipment.getFacility().getId() : null)
                .operationalStatus(equipment.getOperationalStatus() != null ? OperationalStatusDTO.fromEntity(equipment.getOperationalStatus()) : null)
                .equipmentType(equipment.getEquipmentType() != null ? EquipmentTypeDTO.fromEntity(equipment.getEquipmentType()) : null)
                .facility(equipment.getFacility() != null ? FacilityDTO.fromEntity(equipment.getFacility()) : null)
                .build();
    }
}
