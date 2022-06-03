package com.vilensky.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Data
public class RentalOrderDTO {
    @NotNull(message = "Rental start date cant be null")
    private ZonedDateTime rentStart;

    @NotNull(message = "Rental end date cant be null")
    private ZonedDateTime rentEnd;

    @NotNull(message = "Client cant be null")
    private ClientDTO clientDTO;

    @NotNull(message = "Car cant be null")
    private CarDTO carDTO;
}
