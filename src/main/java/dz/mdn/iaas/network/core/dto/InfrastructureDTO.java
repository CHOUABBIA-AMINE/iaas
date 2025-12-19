/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: InfrastructureDTO
 *	@CreatedOn	: 12-19-2025
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
import dz.mdn.iaas.network.core.model.Infrastructure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Infrastructure Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - code (F_01) - unique, required
 * - name (F_02) - required
 * - installationDate (F_03) - optional
 * - commissioningDate (F_04) - optional
 * - decommissioningDate (F_05) - optional
 * - operationalStatusId (F_06) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfrastructureDTO extends GenericDTO<Infrastructure> {

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

    @Override
    public Infrastructure toEntity() {
        Infrastructure infrastructure = new Infrastructure();
        infrastructure.setId(getId());
        infrastructure.setCode(this.code);
        infrastructure.setName(this.name);
        infrastructure.setInstallationDate(this.installationDate);
        infrastructure.setCommissioningDate(this.commissioningDate);
        infrastructure.setDecommissioningDate(this.decommissioningDate);
        return infrastructure;
    }

    @Override
    public void updateEntity(Infrastructure infrastructure) {
        if (this.code != null) infrastructure.setCode(this.code);
        if (this.name != null) infrastructure.setName(this.name);
        if (this.installationDate != null) infrastructure.setInstallationDate(this.installationDate);
        if (this.commissioningDate != null) infrastructure.setCommissioningDate(this.commissioningDate);
        if (this.decommissioningDate != null) infrastructure.setDecommissioningDate(this.decommissioningDate);
    }

    public static InfrastructureDTO fromEntity(Infrastructure infrastructure) {
        if (infrastructure == null) return null;
        
        return InfrastructureDTO.builder()
                .id(infrastructure.getId())
                .code(infrastructure.getCode())
                .name(infrastructure.getName())
                .installationDate(infrastructure.getInstallationDate())
                .commissioningDate(infrastructure.getCommissioningDate())
                .decommissioningDate(infrastructure.getDecommissioningDate())
                .operationalStatusId(infrastructure.getOperationalStatus() != null ? infrastructure.getOperationalStatus().getId() : null)
                .build();
    }
}
