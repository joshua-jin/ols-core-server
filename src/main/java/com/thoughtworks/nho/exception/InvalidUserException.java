package com.thoughtworks.nho.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String s) {
        super(s);
    }
}
