package io.github.abbassizied.sms.exceptions;

public class OrderItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderItemNotFoundException() {
        super();
    }

    public OrderItemNotFoundException(String message) {
        super(message);
    }

    public OrderItemNotFoundException(Exception exception) {
        super(exception);
    }
}