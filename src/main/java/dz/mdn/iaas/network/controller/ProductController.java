/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProductController
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Network / Controller
 *	@Package	: Network / Controller
 *
 **/

package dz.mdn.iaas.network.controller;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.dto.ProductDTO;
import dz.mdn.iaas.network.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network/product")
@Slf4j
public class ProductController extends GenericController<ProductDTO, Long> {

    @SuppressWarnings("unused")
	private final ProductService productService;

    public ProductController(ProductService productService) {
        super(productService, "Product");
        this.productService = productService;
    }
}
