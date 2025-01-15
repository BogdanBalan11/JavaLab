package com.unibuc.ex1curs11.exception;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound(String message) {
        super(message + " not found");
    }
}


