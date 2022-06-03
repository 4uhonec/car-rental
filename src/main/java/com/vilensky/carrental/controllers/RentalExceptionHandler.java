package com.vilensky.carrental.controllers;

import com.vilensky.carrental.exceptions.CarRentalError;
import com.vilensky.carrental.exceptions.NoSuchCarException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RentalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CarRentalError> handleNoSuchCarException(NoSuchCarException exception, HttpServletRequest request){
        CarRentalError error = new CarRentalError(exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(404).body(error);
    }
}
