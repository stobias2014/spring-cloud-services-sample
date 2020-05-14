package com.tobias.saul.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InventoryItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6787815013183465371L;

	public InventoryItemNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InventoryItemNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InventoryItemNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
