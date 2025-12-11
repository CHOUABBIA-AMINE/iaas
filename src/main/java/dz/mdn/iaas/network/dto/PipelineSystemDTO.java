/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PipelineSystemDTO
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
import dz.mdn.iaas.network.model.PipelineSystem;
import dz.mdn.iaas.network.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * PipelineSystem Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - code (F_01) - unique, required
 * - designationAr (F_02) - required
 * - designationEn (F_03) - required
 * - designationFr (F_04) - required
 * - productId (F_05) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipelineSystemDTO extends GenericDTO<PipelineSystem> {

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "Arabic designation is required")
    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    private String designationAr;

    @NotBlank(message = "English designation is required")
    @Size(max = 100, message = "English designation must not exceed 100 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    private String designationFr;

    @NotNull(message = "Product is required")
    private Long productId;

    private ProductDTO product;

    @Override
    public PipelineSystem toEntity() {
        PipelineSystem system = new PipelineSystem();
        system.setId(getId());
        system.setCode(this.code);
        system.setDesignationAr(this.designationAr);
        system.setDesignationEn(this.designationEn);
        system.setDesignationFr(this.designationFr);
        
        if (this.productId != null) {
            Product product = new Product();
            product.setId(this.productId);
            system.setProduct(product);
        }
        
        return system;
    }

    @Override
    public void updateEntity(PipelineSystem system) {
        if (this.code != null) system.setCode(this.code);
        if (this.designationAr != null) system.setDesignationAr(this.designationAr);
        if (this.designationEn != null) system.setDesignationEn(this.designationEn);
        if (this.designationFr != null) system.setDesignationFr(this.designationFr);
        
        if (this.productId != null) {
            Product product = new Product();
            product.setId(this.productId);
            system.setProduct(product);
        }
    }

    public static PipelineSystemDTO fromEntity(PipelineSystem system) {
        if (system == null) return null;
        
        return PipelineSystemDTO.builder()
                .id(system.getId())
                .code(system.getCode())
                .designationAr(system.getDesignationAr())
                .designationEn(system.getDesignationEn())
                .designationFr(system.getDesignationFr())
                .productId(system.getProduct() != null ? system.getProduct().getId() : null)
                .product(system.getProduct() != null ? ProductDTO.fromEntity(system.getProduct()) : null)
                .build();
    }
}
