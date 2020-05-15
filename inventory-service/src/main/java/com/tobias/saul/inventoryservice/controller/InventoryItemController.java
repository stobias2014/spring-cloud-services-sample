package com.tobias.saul.inventoryservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tobias.saul.inventoryservice.exception.InventoryItemNotFoundException;
import com.tobias.saul.inventoryservice.pojos.InventoryItem;
import com.tobias.saul.inventoryservice.service.InventoryItemService;

@RestController
@RequestMapping("/api/v1")
public class InventoryItemController {
	
	private final InventoryItemService inventoryItemService;
	
	@Autowired
	public InventoryItemController(InventoryItemService inventoryItemService) {
		this.inventoryItemService = inventoryItemService;
	}
	
	@GetMapping("/inventory/{productCode}")
	public EntityModel<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String code) {
		
		InventoryItem item = inventoryItemService.findByProductCode(code)
				.orElseThrow(() -> new InventoryItemNotFoundException("Inventory item [" + code + "] not found."));
		
		return new EntityModel<InventoryItem>(item,
				linkTo(methodOn(InventoryItemController.class).findInventoryByProductCode(code)).withSelfRel());
	}

}
