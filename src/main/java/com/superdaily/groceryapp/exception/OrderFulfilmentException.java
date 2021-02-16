package com.superdaily.groceryapp.exception;


public class OrderFulfilmentException extends Exception {
    private String message;

    public OrderFulfilmentException(String message) {
        super(message);
        this.message = message;
    }
}
