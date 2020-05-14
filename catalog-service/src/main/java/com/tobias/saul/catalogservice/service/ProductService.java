package com.tobias.saul.catalogservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.tobias.saul.catalogservice.pojos.Product;
import com.tobias.saul.catalogservice.pojos.ProductInventoryResponse;
import com.tobias.saul.catalogservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	private final RestTemplate restTemplate;
	
	@Autowired
	public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
		this.productRepository = productRepository;
		this.restTemplate = restTemplate;
	}
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public Optional<Product> findByProductCode(String code) {
		Optional<Product> productOptional = productRepository.findByCode(code);
		if(productOptional.isPresent()) {
			ProductInventoryResponse itemResponse = restTemplate.getForObject("http://localhost:8282/api/v1/inventory/{code}",
					ProductInventoryResponse.class, code);
			
			if(itemResponse != null) {
				Integer quantity = itemResponse.getAvailableQuantity();
				productOptional.get().setInStock(quantity > 0);
			} else {
				System.out.println("Unable to get inventory");
			}
		}
		
		return productOptional;
	}
	


}
