/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-19-2025
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
import dz.mdn.iaas.network.common.model.Alloy;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.common.model.Vendor;
import dz.mdn.iaas.network.core.model.Facility;
import dz.mdn.iaas.network.core.model.Pipeline;
import dz.mdn.iaas.network.core.model.PipelineSystem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Pipeline Data Transfer Object - Extends GenericDTO
 * Inherits Infrastructure fields (code, name, installationDate, commissioningDate, decommissioningDate, operationalStatus)
 * 
 * Pipeline specific fields:
 * - nominalDiameter (F_07) - required, positive
 * - length (F_08) - required, positive
 * - nominalThikness (F_09) - required, positive
 * - nominalRoughness (F_10) - required, positive
 * - designMaxServicePressure (F_11) - required, positive
 * - realMaxServicePressure (F_12) - required, positive
 * - designMinServicePressure (F_13) - required, positive
 * - realMinServicePressure (F_14) - required, positive
 * - designCapacity (F_15) - required, positive
 * - realCapacity (F_16) - required, positive
 * - nominalConstructionMaterialId (F_17) - Alloy relationship, required
 * - nominalExteriorCoatingId (F_18) - Alloy relationship, required
 * - nominalInteriorCoatingId (F_19) - Alloy relationship, required
 * - vendorId (F_20) - Vendor relationship, required
 * - pipelineSystemId (F_21) - PipelineSystem relationship, required
 * - departureFacilityId (F_22) - Facility relationship, required
 * - arrivalFacilityId (F_23) - Facility relationship, required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipelineDTO extends GenericDTO<Pipeline> {

	@NotBlank(message = "Code is required")
    @Size(min = 2, max = 20, message = "Code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    private LocalDate installationDate;

    private LocalDate commissioningDate;

    private LocalDate decommissioningDate;

    @NotNull(message = "Operational status ID is required")
    private Long operationalStatusId;

    @NotNull(message = "Nominal diameter is required")
    @Positive(message = "Nominal diameter must be positive")
    private Double nominalDiameter;

    @NotNull(message = "Length is required")
    @Positive(message = "Length must be positive")
    private Double length;

    @NotNull(message = "Nominal thickness is required")
    @Positive(message = "Nominal thickness must be positive")
    private Double nominalThikness;

    @NotNull(message = "Nominal roughness is required")
    @Positive(message = "Nominal roughness must be positive")
    private Double nominalRoughness;

    @NotNull(message = "Design max service pressure is required")
    @Positive(message = "Design max service pressure must be positive")
    private Double designMaxServicePressure;

    @NotNull(message = "Real max service pressure is required")
    @Positive(message = "Real max service pressure must be positive")
    private Double operationalMaxServicePressure;

    @NotNull(message = "Design min service pressure is required")
    @Positive(message = "Design min service pressure must be positive")
    private Double designMinServicePressure;

    @NotNull(message = "Real min service pressure is required")
    @Positive(message = "Real min service pressure must be positive")
    private Double operationalMinServicePressure;

    @NotNull(message = "Design capacity is required")
    @Positive(message = "Design capacity must be positive")
    private Double designCapacity;

    @NotNull(message = "Real capacity is required")
    @Positive(message = "Real capacity must be positive")
    private Double operationalCapacity;

    @NotNull(message = "Nominal construction material ID is required")
    private Long nominalConstructionMaterialId;

    @NotNull(message = "Nominal exterior coating ID is required")
    private Long nominalExteriorCoatingId;

    @NotNull(message = "Nominal interior coating ID is required")
    private Long nominalInteriorCoatingId;

    @NotNull(message = "Vendor ID is required")
    private Long vendorId;

    @NotNull(message = "Pipeline system ID is required")
    private Long pipelineSystemId;

    @NotNull(message = "Departure facility ID is required")
    private Long departureFacilityId;

    @NotNull(message = "Arrival facility ID is required")
    private Long arrivalFacilityId;

    @Override
    public Pipeline toEntity() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId(getId());
        pipeline.setCode(this.code);
        pipeline.setName(this.name);
        pipeline.setInstallationDate(this.installationDate);
        pipeline.setCommissioningDate(this.commissioningDate);
        pipeline.setDecommissioningDate(this.decommissioningDate);
        pipeline.setNominalDiameter(this.nominalDiameter);
        pipeline.setLength(this.length);
        pipeline.setNominalThikness(this.nominalThikness);
        pipeline.setNominalRoughness(this.nominalRoughness);
        pipeline.setDesignMaxServicePressure(this.designMaxServicePressure);
        pipeline.setOperationalMaxServicePressure(this.operationalMaxServicePressure);
        pipeline.setDesignMinServicePressure(this.designMinServicePressure);
        pipeline.setOperationalMinServicePressure(this.operationalMinServicePressure);
        pipeline.setDesignCapacity(this.designCapacity);
        pipeline.setOperationalCapacity(this.operationalCapacity);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            pipeline.setOperationalStatus(status);
        }
        
        if (this.nominalConstructionMaterialId != null) {
            Alloy material = new Alloy();
            material.setId(this.nominalConstructionMaterialId);
            pipeline.setNominalConstructionMaterial(material);
        }
        
        if (this.nominalExteriorCoatingId != null) {
            Alloy coating = new Alloy();
            coating.setId(this.nominalExteriorCoatingId);
            pipeline.setNominalExteriorCoating(coating);
        }
        
        if (this.nominalInteriorCoatingId != null) {
            Alloy coating = new Alloy();
            coating.setId(this.nominalInteriorCoatingId);
            pipeline.setNominalInteriorCoating(coating);
        }
        
        if (this.vendorId != null) {
            Vendor vendor = new Vendor();
            vendor.setId(this.vendorId);
            pipeline.setVendor(vendor);
        }
        
        if (this.pipelineSystemId != null) {
            PipelineSystem system = new PipelineSystem();
            system.setId(this.pipelineSystemId);
            pipeline.setPipelineSystem(system);
        }
        
        if (this.departureFacilityId != null) {
            Facility facility = new Facility();
            facility.setId(this.departureFacilityId);
            pipeline.setDepartureFacility(facility);
        }
        
        if (this.arrivalFacilityId != null) {
            Facility facility = new Facility();
            facility.setId(this.arrivalFacilityId);
            pipeline.setArrivalFacility(facility);
        }
        
        return pipeline;
    }

    @Override
    public void updateEntity(Pipeline pipeline) {

    	if (this.code != null) pipeline.setCode(this.code);
    	if (this.name != null) pipeline.setName(this.name);
    	if (this.installationDate != null) pipeline.setInstallationDate(this.installationDate);
    	if (this.commissioningDate != null) pipeline.setCommissioningDate(this.commissioningDate);
    	if (this.decommissioningDate != null) pipeline.setDecommissioningDate(this.decommissioningDate);
        if (this.nominalDiameter != null) pipeline.setNominalDiameter(this.nominalDiameter);
        if (this.length != null) pipeline.setLength(this.length);
        if (this.nominalThikness != null) pipeline.setNominalThikness(this.nominalThikness);
        if (this.nominalRoughness != null) pipeline.setNominalRoughness(this.nominalRoughness);
        if (this.designMaxServicePressure != null) pipeline.setDesignMaxServicePressure(this.designMaxServicePressure);
        if (this.operationalMaxServicePressure != null) pipeline.setOperationalMaxServicePressure(this.operationalMaxServicePressure);
        if (this.designMinServicePressure != null) pipeline.setDesignMinServicePressure(this.designMinServicePressure);
        if (this.operationalMinServicePressure != null) pipeline.setOperationalMinServicePressure(this.operationalMinServicePressure);
        if (this.designCapacity != null) pipeline.setDesignCapacity(this.designCapacity);
        if (this.operationalCapacity != null) pipeline.setOperationalCapacity(this.operationalCapacity);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            pipeline.setOperationalStatus(status);
        }
        
        if (this.nominalConstructionMaterialId != null) {
            Alloy material = new Alloy();
            material.setId(this.nominalConstructionMaterialId);
            pipeline.setNominalConstructionMaterial(material);
        }
        
        if (this.nominalExteriorCoatingId != null) {
            Alloy coating = new Alloy();
            coating.setId(this.nominalExteriorCoatingId);
            pipeline.setNominalExteriorCoating(coating);
        }
        
        if (this.nominalInteriorCoatingId != null) {
            Alloy coating = new Alloy();
            coating.setId(this.nominalInteriorCoatingId);
            pipeline.setNominalInteriorCoating(coating);
        }
        
        if (this.vendorId != null) {
            Vendor vendor = new Vendor();
            vendor.setId(this.vendorId);
            pipeline.setVendor(vendor);
        }
        
        if (this.pipelineSystemId != null) {
            PipelineSystem system = new PipelineSystem();
            system.setId(this.pipelineSystemId);
            pipeline.setPipelineSystem(system);
        }
        
        if (this.departureFacilityId != null) {
            Facility facility = new Facility();
            facility.setId(this.departureFacilityId);
            pipeline.setDepartureFacility(facility);
        }
        
        if (this.arrivalFacilityId != null) {
            Facility facility = new Facility();
            facility.setId(this.arrivalFacilityId);
            pipeline.setArrivalFacility(facility);
        }
    }

    public static PipelineDTO fromEntity(Pipeline pipeline) {
        if (pipeline == null) return null;
        
        return PipelineDTO.builder()
                .id(pipeline.getId())
                .code(pipeline.getCode())
                .name(pipeline.getName())
                .installationDate(pipeline.getInstallationDate())
                .commissioningDate(pipeline.getCommissioningDate())
                .decommissioningDate(pipeline.getDecommissioningDate())
                .nominalDiameter(pipeline.getNominalDiameter())
                .length(pipeline.getLength())
                .nominalThikness(pipeline.getNominalThikness())
                .nominalRoughness(pipeline.getNominalRoughness())
                .designMaxServicePressure(pipeline.getDesignMaxServicePressure())
                .operationalMaxServicePressure(pipeline.getOperationalMaxServicePressure())
                .designMinServicePressure(pipeline.getDesignMinServicePressure())
                .operationalMinServicePressure(pipeline.getOperationalMinServicePressure())
                .designCapacity(pipeline.getDesignCapacity())
                .operationalCapacity(pipeline.getOperationalCapacity())
                .operationalStatusId(pipeline.getOperationalStatus() != null ? pipeline.getOperationalStatus().getId() : null)
                .nominalConstructionMaterialId(pipeline.getNominalConstructionMaterial() != null ? pipeline.getNominalConstructionMaterial().getId() : null)
                .nominalExteriorCoatingId(pipeline.getNominalExteriorCoating() != null ? pipeline.getNominalExteriorCoating().getId() : null)
                .nominalInteriorCoatingId(pipeline.getNominalInteriorCoating() != null ? pipeline.getNominalInteriorCoating().getId() : null)
                .vendorId(pipeline.getVendor() != null ? pipeline.getVendor().getId() : null)
                .pipelineSystemId(pipeline.getPipelineSystem() != null ? pipeline.getPipelineSystem().getId() : null)
                .departureFacilityId(pipeline.getDepartureFacility() != null ? pipeline.getDepartureFacility().getId() : null)
                .arrivalFacilityId(pipeline.getArrivalFacility() != null ? pipeline.getArrivalFacility().getId() : null)
                .build();
    }
}
