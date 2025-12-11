/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.configuration.template.GenericDTO;
import dz.mdn.iaas.business.plan.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Item Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO extends GenericDTO<Item> {

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

    @Override
    public Item toEntity() {
        Item entity = new Item();
        entity.setId(this.getId());
        entity.setCode(this.code);
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        entity.setTechnicalSpecification(this.technicalSpecification);
        entity.setUnitMeasure(this.unitMeasure);
        return entity;
    }

    @Override
    public void updateEntity(Item entity) {
        if (this.code != null) entity.setCode(this.code);
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
        if (this.technicalSpecification != null) entity.setTechnicalSpecification(this.technicalSpecification);
        if (this.unitMeasure != null) entity.setUnitMeasure(this.unitMeasure);
    }
}
