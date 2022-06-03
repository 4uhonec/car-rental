package com.vilensky.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Data
public class CarDTO {//data transfer object

    @NotNull(message = "Car number cant be empty")
    private int carNumber;

    @NotEmpty(message = "Color of the car cant be empty")
    private String color;

    @NotNull(message = "Motor volume cant be empty")
    private double motorVolume;

    @NotEmpty(message = "Car model cant be empty")
    private String model;

    @NotEmpty(message = "Car brand cant be empty")
    private String brand;

    @NotNull(message = ("Price of the rental cant be empty"))
    private double price;
}
