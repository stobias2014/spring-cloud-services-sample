package com.tobias.saul.catalogservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tobias.saul.catalogservice.exception.ProductNotFoundException;

@RestControllerAdvice
public class ProductNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String productNotFoundException(ProductNotFoundException ex) {
		return ex.getMessage();
	}

}
