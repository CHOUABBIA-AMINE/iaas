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

package dz.mdn.iaas.network.core.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.network.common.dto.OperationalStatusDTO;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import dz.mdn.iaas.network.core.model.Facility;
import dz.mdn.iaas.network.core.model.Pipeline;
import dz.mdn.iaas.network.core.model.PipelineSystem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    
}
