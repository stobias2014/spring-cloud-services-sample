package com.tobias.saul.catalogservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobias.saul.catalogservice.client.InventoryServiceClient;
import com.tobias.saul.catalogservice.pojos.Product;
import com.tobias.saul.catalogservice.pojos.ProductInventoryResponse;
import com.tobias.saul.catalogservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	private final InventoryServiceClient inventoryServiceClient;
	
	@Autowired
	public ProductService(ProductRepository productRepository, InventoryServiceClient inventoryServiceClient) {
		this.productRepository = productRepository;
		this.inventoryServiceClient = inventoryServiceClient;
	}
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public Optional<Product> findByProductCode(String code) {
		Optional<Product> productOptional = productRepository.findByCode(code);
		if(productOptional.isPresent()) {
			Optional<ProductInventoryResponse> itemResponse = inventoryServiceClient.getProductInventoryByCode(code);
			
			if(itemResponse.isPresent()) {
				Integer quantity = itemResponse.get().getAvailableQuantity();
				productOptional.get().setInStock(quantity > 0);
			}
		}
		
		return productOptional;
	}
	


}
