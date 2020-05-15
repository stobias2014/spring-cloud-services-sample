package com.tobias.saul.catalogservice.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tobias.saul.catalogservice.pojos.ProductInventoryResponse;

@Service
public class InventoryServiceClient {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public InventoryServiceClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@HystrixCommand(fallbackMethod = "getDefaultProductInventoryByCode")
	public Optional<ProductInventoryResponse> getProductInventoryByCode(String code) {
		ResponseEntity<ProductInventoryResponse> itemResponseEntity =
				restTemplate.getForEntity("http://inventory-service/api/v1/inventory/{code}",
						ProductInventoryResponse.class, code);
		
		if(itemResponseEntity.getStatusCode() == HttpStatus.OK) {
			return Optional.ofNullable(itemResponseEntity.getBody());
		} else {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings("unused")
	public Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String code) {
		ProductInventoryResponse response = new ProductInventoryResponse();
		response.setProuctCode(code);
		response.setAvailableQuantity(50);
		return Optional.ofNullable(response);
	}

}
