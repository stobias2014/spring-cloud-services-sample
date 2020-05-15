package com.tobias.saul.catalogservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tobias.saul.catalogservice.exception.ProductNotFoundException;
import com.tobias.saul.catalogservice.pojos.Product;
import com.tobias.saul.catalogservice.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public CollectionModel<EntityModel<Product>> allProducts() {
		
		List<EntityModel<Product>> products = productService.findAllProducts()
				.stream()
				.map(product -> new EntityModel<Product>(product,
						linkTo(methodOn(ProductController.class).productByCode(product.getCode())).withSelfRel(),
						linkTo(methodOn(ProductController.class).allProducts()).withRel("products")))
				.collect(Collectors.toList());
		
		return new CollectionModel<EntityModel<Product>>(products,
				linkTo(methodOn(ProductController.class).allProducts()).withSelfRel());
	}
	
	@GetMapping("/products/{code}")
	public EntityModel<Product> productByCode(@PathVariable("code") String code) {
		
		Product product = productService.findByProductCode(code)
				.orElseThrow(() -> new ProductNotFoundException("Product with code [" + code + "] does not exist"));
		
		return new EntityModel<Product>(product,
				linkTo(methodOn(ProductController.class).productByCode(code)).withSelfRel(),
				linkTo(methodOn(ProductController.class).allProducts()).withRel("products"));
	}

}
