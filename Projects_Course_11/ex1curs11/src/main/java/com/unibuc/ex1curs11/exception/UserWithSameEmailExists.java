package com.unibuc.ex1curs11.exception;

public class UserWithSameEmailExists extends RuntimeException {

    public UserWithSameEmailExists() {
        super("A User with the same email already exists");
    }
}