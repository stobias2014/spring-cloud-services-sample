package com.tobias.saul.inventoryservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tobias.saul.inventoryservice.exception.InventoryItemNotFoundException;

@RestControllerAdvice
public class InventoryItemNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(InventoryItemNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String inventoryItemNotFoundException(InventoryItemNotFoundException ex) {
		return ex.getMessage();
	}

}
