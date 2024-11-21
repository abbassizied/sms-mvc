package io.github.abbassizied.sms.exceptions;

public class DiscountNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DiscountNotFoundException() {
        super();
    }

    public DiscountNotFoundException(String message) {
        super(message);
    }

    public DiscountNotFoundException(Exception exception) {
        super(exception);
    }
}