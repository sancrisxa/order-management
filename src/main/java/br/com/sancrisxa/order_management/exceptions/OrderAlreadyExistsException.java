package br.com.sancrisxa.order_management.exceptions;

public class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}
