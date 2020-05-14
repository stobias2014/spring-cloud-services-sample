package com.tobias.saul.inventoryservice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tobias.saul.inventoryservice.pojos.InventoryItem;
import com.tobias.saul.inventoryservice.repository.InventoryItemRepository;

@Service
public class InventoryItemService {
	
	private final InventoryItemRepository inventoryItemRepository;
	
	public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
		this.inventoryItemRepository = inventoryItemRepository;
	}
	
	public Optional<InventoryItem> findByProductCode(String code) {
		return inventoryItemRepository.findByProductCode(code);
	}

}
