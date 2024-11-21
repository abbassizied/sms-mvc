package io.github.abbassizied.sms.exceptions;

public class SupplierNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public SupplierNotFoundException() {
		super(); 
	}

	public SupplierNotFoundException(String message) {
		super(message); 
	}

	public SupplierNotFoundException(Exception exception) {
		super(exception); 
	}	
}