package com.vilensky.carrental.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarRentalError {
    private String message;
    private String uri;
}
