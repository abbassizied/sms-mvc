package io.github.abbassizied.sms.exceptions;

public class PurchaseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PurchaseNotFoundException() {
        super();
    }

    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public PurchaseNotFoundException(Exception exception) {
        super(exception);
    }
}