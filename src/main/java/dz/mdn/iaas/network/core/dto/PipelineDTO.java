/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Data Transfer Object
 *	@Layer		: Network / DTO
 *	@Package	: Network / DTO
 *
 **/

package dz.mdn.iaas.network.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.model.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Pipeline Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - code (F_01) - unique, required
 * - diameter (F_02) - required
 * - length (F_03) - required
 * - maxPressure (F_04) - required
 * - constructionMaterial (F_05) - required
 * - commissioningDate (F_06) - required
 * - operationalStatusId (F_07) - required
 * - pipelineSystemId (F_08) - required
 * - departureTerminalId (F_09) - required
 * - arrivalTerminalId (F_10) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipelineDTO extends GenericDTO<Pipeline> {

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotNull(message = "Diameter is required")
    private Double diameter;

    @NotNull(message = "Length is required")
    private Double length;

    @NotNull(message = "Max pressure is required")
    private Double maxPressure;

    @NotBlank(message = "Construction material is required")
    @Size(max = 50, message = "Construction material must not exceed 50 characters")
    private String constructionMaterial;

    @NotNull(message = "Commissioning date is required")
    private LocalDate commissioningDate;

    @NotNull(message = "Operational status is required")
    private Long operationalStatusId;

    @NotNull(message = "Pipeline system is required")
    private Long pipelineSystemId;

    @NotNull(message = "Departure terminal is required")
    private Long departureTerminalId;

    @NotNull(message = "Arrival terminal is required")
    private Long arrivalTerminalId;

    // Nested DTOs
    private OperationalStatusDTO operationalStatus;
    private PipelineSystemDTO pipelineSystem;
    private FacilityDTO departureTerminal;
    private FacilityDTO arrivalTerminal;

    @Override
    public Pipeline toEntity() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId(getId());
        pipeline.setCode(this.code);
        pipeline.setDiameter(this.diameter);
        pipeline.setLength(this.length);
        pipeline.setMaxPressure(this.maxPressure);
        pipeline.setConstructionMaterial(this.constructionMaterial);
        pipeline.setCommissioningDate(this.commissioningDate);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            pipeline.setOperationalStatus(status);
        }
        
        if (this.pipelineSystemId != null) {
            PipelineSystem system = new PipelineSystem();
            system.setId(this.pipelineSystemId);
            pipeline.setPipelineSystem(system);
        }
        
        if (this.departureTerminalId != null) {
            Facility facility = new Facility();
            facility.setId(this.departureTerminalId);
            pipeline.setDepartureTerminal(facility);
        }
        
        if (this.arrivalTerminalId != null) {
            Facility facility = new Facility();
            facility.setId(this.arrivalTerminalId);
            pipeline.setArrivalTerminal(facility);
        }
        
        return pipeline;
    }

    @Override
    public void updateEntity(Pipeline pipeline) {
        if (this.code != null) pipeline.setCode(this.code);
        if (this.diameter != null) pipeline.setDiameter(this.diameter);
        if (this.length != null) pipeline.setLength(this.length);
        if (this.maxPressure != null) pipeline.setMaxPressure(this.maxPressure);
        if (this.constructionMaterial != null) pipeline.setConstructionMaterial(this.constructionMaterial);
        if (this.commissioningDate != null) pipeline.setCommissioningDate(this.commissioningDate);
        
        if (this.operationalStatusId != null) {
            OperationalStatus status = new OperationalStatus();
            status.setId(this.operationalStatusId);
            pipeline.setOperationalStatus(status);
        }
        
        if (this.pipelineSystemId != null) {
            PipelineSystem system = new PipelineSystem();
            system.setId(this.pipelineSystemId);
            pipeline.setPipelineSystem(system);
        }
        
        if (this.departureTerminalId != null) {
            Facility facility = new Facility();
            facility.setId(this.departureTerminalId);
            pipeline.setDepartureTerminal(facility);
        }
        
        if (this.arrivalTerminalId != null) {
            Facility facility = new Facility();
            facility.setId(this.arrivalTerminalId);
            pipeline.setArrivalTerminal(facility);
        }
    }

    public static PipelineDTO fromEntity(Pipeline pipeline) {
        if (pipeline == null) return null;
        
        return PipelineDTO.builder()
                .id(pipeline.getId())
                .code(pipeline.getCode())
                .diameter(pipeline.getDiameter())
                .length(pipeline.getLength())
                .maxPressure(pipeline.getMaxPressure())
                .constructionMaterial(pipeline.getConstructionMaterial())
                .commissioningDate(pipeline.getCommissioningDate())
                .operationalStatusId(pipeline.getOperationalStatus() != null ? pipeline.getOperationalStatus().getId() : null)
                .pipelineSystemId(pipeline.getPipelineSystem() != null ? pipeline.getPipelineSystem().getId() : null)
                .departureTerminalId(pipeline.getDepartureTerminal() != null ? pipeline.getDepartureTerminal().getId() : null)
                .arrivalTerminalId(pipeline.getArrivalTerminal() != null ? pipeline.getArrivalTerminal().getId() : null)
                .operationalStatus(pipeline.getOperationalStatus() != null ? OperationalStatusDTO.fromEntity(pipeline.getOperationalStatus()) : null)
                .pipelineSystem(pipeline.getPipelineSystem() != null ? PipelineSystemDTO.fromEntity(pipeline.getPipelineSystem()) : null)
                .departureTerminal(pipeline.getDepartureTerminal() != null ? FacilityDTO.fromEntity(pipeline.getDepartureTerminal()) : null)
                .arrivalTerminal(pipeline.getArrivalTerminal() != null ? FacilityDTO.fromEntity(pipeline.getArrivalTerminal()) : null)
                .build();
    }
}
