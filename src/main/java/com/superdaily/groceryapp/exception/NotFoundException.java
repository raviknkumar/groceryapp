package com.superdaily.groceryapp.exception;

import lombok.Data;

@Data
public class NotFoundException extends Exception{
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
