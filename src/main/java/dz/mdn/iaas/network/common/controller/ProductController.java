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

package dz.mdn.iaas.network.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.configuration.template.GenericController;
import dz.mdn.iaas.network.common.dto.ProductDTO;
import dz.mdn.iaas.network.common.service.ProductService;
import lombok.extern.slf4j.Slf4j;

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
