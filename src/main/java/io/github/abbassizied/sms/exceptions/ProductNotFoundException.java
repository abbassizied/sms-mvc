package io.github.abbassizied.sms.exceptions;

public class ProductNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super(); 
	}

	public ProductNotFoundException(String message) {
		super(message); 
	}

	public ProductNotFoundException(Exception exception) {
		super(exception); 
	}	
}