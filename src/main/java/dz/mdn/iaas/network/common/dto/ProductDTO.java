/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProductDTO
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
 * Product Data Transfer Object - Extends GenericDTO
 * 
 * Fields:
 * - id (F_00) - inherited from GenericDTO
 * - name (F_01) - required
 * - code (F_02) - unique, required
 * - density (F_03) - required
 * - viscosity (F_04) - required
 * - flashPoint (F_05) - required
 * - sulfurContent (F_06) - required
 * - isHazardous (F_07) - required
 * - description (F_08) - required
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO extends GenericDTO<Product> {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotNull(message = "Density is required")
    private Double density;

    @NotNull(message = "Viscosity is required")
    private Double viscosity;

    @NotNull(message = "Flash point is required")
    private Double flashPoint;

    @NotNull(message = "Sulfur content is required")
    private Double sulfurContent;

    @NotNull(message = "Hazardous flag is required")
    private Boolean isHazardous;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Override
    public Product toEntity() {
        Product product = new Product();
        product.setId(getId());
        product.setName(this.name);
        product.setCode(this.code);
        product.setDensity(this.density);
        product.setViscosity(this.viscosity);
        product.setFlashPoint(this.flashPoint);
        product.setSulfurContent(this.sulfurContent);
        product.setIsHazardous(this.isHazardous);
        product.setDescription(this.description);
        return product;
    }

    @Override
    public void updateEntity(Product product) {
        if (this.name != null) product.setName(this.name);
        if (this.code != null) product.setCode(this.code);
        if (this.density != null) product.setDensity(this.density);
        if (this.viscosity != null) product.setViscosity(this.viscosity);
        if (this.flashPoint != null) product.setFlashPoint(this.flashPoint);
        if (this.sulfurContent != null) product.setSulfurContent(this.sulfurContent);
        if (this.isHazardous != null) product.setIsHazardous(this.isHazardous);
        if (this.description != null) product.setDescription(this.description);
    }

    public static ProductDTO fromEntity(Product product) {
        if (product == null) return null;
        
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .density(product.getDensity())
                .viscosity(product.getViscosity())
                .flashPoint(product.getFlashPoint())
                .sulfurContent(product.getSulfurContent())
                .isHazardous(product.getIsHazardous())
                .description(product.getDescription())
                .build();
    }
}
