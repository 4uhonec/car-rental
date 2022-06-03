package com.vilensky.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalOrderDTO {
    @NotNull(message = "Rental start date cant be null")
    private ZonedDateTime rentStart;

    @NotNull(message = "Rental end date cant be null")
    private ZonedDateTime rentEnd;

    @NotEmpty(message = "Licence number cant be empty")
    private String licenceNumber;

    @NotNull(message = "Car number cant be empty")
    private int carNumber;
}
