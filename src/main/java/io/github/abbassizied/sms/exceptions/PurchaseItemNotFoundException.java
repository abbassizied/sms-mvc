package io.github.abbassizied.sms.exceptions;

public class PurchaseItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PurchaseItemNotFoundException() {
        super();
    }

    public PurchaseItemNotFoundException(String message) {
        super(message);
    }

    public PurchaseItemNotFoundException(Exception exception) {
        super(exception);
    }
}