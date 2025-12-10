/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDTO
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Item Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO extends GenericDTO {

    @NotBlank(message = "Code is required")
    private String code;
    
    @Size(max = 500, message = "Arabic designation must not exceed 500 characters")
    private String designationAr;

    @Size(max = 500, message = "English designation must not exceed 500 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 500, message = "French designation must not exceed 500 characters")
    private String designationFr;
    
    private String technicalSpecification;
    private String unitMeasure;
    
    @NotNull(message = "Rubric is required")
    private Long rubricId;
    
    private Long itemStatusId;

}
