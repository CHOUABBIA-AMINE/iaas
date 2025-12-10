/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RubricDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Rubric Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RubricDTO extends GenericDTO {

    @NotBlank(message = "Code is required")
    private String code;
    
    @Size(max = 300, message = "Arabic designation must not exceed 300 characters")
    private String designationAr;

    @Size(max = 300, message = "English designation must not exceed 300 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 300, message = "French designation must not exceed 300 characters")
    private String designationFr;
    
    private String description;
    private Long parentRubricId;

}
