package com.vilensky.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    @NotNull(message = "Age cant be empty")
    private int age;

    @NotNull(message = "Experience cant be empty")
    private int experience;

    @NotEmpty(message = "Name cant be empty")
    private String name;

    @NotEmpty(message = "Phone number cant be empty")
    private String phone;

    @NotEmpty(message = "License number cant be empty")
    private String licenceNumber;

}
