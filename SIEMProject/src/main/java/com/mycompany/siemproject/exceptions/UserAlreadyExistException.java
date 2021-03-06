package com.mycompany.siemproject.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String email) {
        super(email + " has already been taken.\n");
    }
}
