package com.tobias.saul.catalogservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobias.saul.catalogservice.pojos.Product;
import com.tobias.saul.catalogservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public Optional<Product> findByProductCode(String code) {
		return productRepository.findByCode(code);
	}
	


}
