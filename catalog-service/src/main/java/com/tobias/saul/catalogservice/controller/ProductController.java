package com.tobias.saul.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Product> allProducts() {
		return productService.findAllProducts();
	}
	
	@GetMapping("/products/{code}")
	public Product productByCode(@PathVariable("code") String code) {
		return productService.findByProductCode(code)
				.orElseThrow(() -> new ProductNotFoundException("Product with code [" + code + "] does not exist"));
	}

}
