package com.vilensky.carrental.exceptions;

public class NoSuchCarException extends RuntimeException{
    public NoSuchCarException(String message) {
        super(message);
    }
}
