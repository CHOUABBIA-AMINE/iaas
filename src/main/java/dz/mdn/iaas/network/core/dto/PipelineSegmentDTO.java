/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineSegmentDTO
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
import dz.mdn.iaas.network.model.Pipeline;
import dz.mdn.iaas.network.model.PipelineSegment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * PipelineSegment Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - code (F_01) - required
 * - pipelineId (F_02) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipelineSegmentDTO extends GenericDTO<PipelineSegment> {

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotNull(message = "Pipeline is required")
    private Long pipelineId;

    private PipelineDTO pipeline;

    @Override
    public PipelineSegment toEntity() {
        PipelineSegment segment = new PipelineSegment();
        segment.setId(getId());
        segment.setCode(this.code);
        
        if (this.pipelineId != null) {
            Pipeline pipeline = new Pipeline();
            pipeline.setId(this.pipelineId);
            segment.setPipeline(pipeline);
        }
        
        return segment;
    }

    @Override
    public void updateEntity(PipelineSegment segment) {
        if (this.code != null) segment.setCode(this.code);
        
        if (this.pipelineId != null) {
            Pipeline pipeline = new Pipeline();
            pipeline.setId(this.pipelineId);
            segment.setPipeline(pipeline);
        }
    }

    public static PipelineSegmentDTO fromEntity(PipelineSegment segment) {
        if (segment == null) return null;
        
        return PipelineSegmentDTO.builder()
                .id(segment.getId())
                .code(segment.getCode())
                .pipelineId(segment.getPipeline() != null ? segment.getPipeline().getId() : null)
                .pipeline(segment.getPipeline() != null ? PipelineDTO.fromEntity(segment.getPipeline()) : null)
                .build();
    }
}
