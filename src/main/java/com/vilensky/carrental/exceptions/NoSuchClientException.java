package com.vilensky.carrental.exceptions;

public class NoSuchClientException extends RuntimeException{
    public NoSuchClientException(String message){
        super(message);
    }
}
