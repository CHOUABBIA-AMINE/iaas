/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeDTO
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.common.administration.model.Employee;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Employee Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO extends GenericDTO<Employee> {

    @NotNull(message = "Person ID is required")
    private Long personId;

    private Long jobId;

    private Long structureId;

    private Long rankId;

    @Size(max = 50, message = "Registration number must not exceed 50 characters")
    private String registrationNumber;

    private Boolean isActive;

    @Override
    public Employee toEntity() {
        Employee entity = new Employee();
        entity.setId(this.getId());
        entity.setRegistrationNumber(this.registrationNumber);
        entity.setIsActive(this.isActive);
        return entity;
    }

    @Override
    public void updateEntity(Employee entity) {
        if (this.registrationNumber != null) entity.setRegistrationNumber(this.registrationNumber);
        if (this.isActive != null) entity.setIsActive(this.isActive);
    }

    public static EmployeeDTO fromEntity(Employee entity) {
        if (entity == null) return null;
        EmployeeDTOBuilder builder = EmployeeDTO.builder()
                .id(entity.getId())
                .registrationNumber(entity.getRegistrationNumber())
                .isActive(entity.getIsActive());
        if (entity.getPerson() != null) {
            builder.personId(entity.getPerson().getId());
        }
        if (entity.getJob() != null) {
            builder.jobId(entity.getJob().getId());
        }
        if (entity.getStructure() != null) {
            builder.structureId(entity.getStructure().getId());
        }
        if (entity.getRank() != null) {
            builder.rankId(entity.getRank().getId());
        }
        return builder.build();
    }
}