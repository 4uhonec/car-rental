package com.vilensky.carrental.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vilensky.carrental.dto.CarDTO;
import com.vilensky.carrental.entities.Car;
import com.vilensky.carrental.mappers.CarDTOMapper;
import com.vilensky.carrental.services.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
class CarControllerTest {

    @MockBean
    private CarDTOMapper carDTOMapper;

    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    CarDTO carDTO = CarDTO.builder()
            .carNumber(534555222)
            .brand("mazda")
            .model("2")
            .motorVolume(1.4)
            .price(200.0)
            .color("black")
            .build();

    Car car = Car.builder()
            .id(UUID.randomUUID())
            .carNumber(534555222)
            .brand("mazda")
            .model("2")
            .motorVolume(1.4)
            .price(200.0)
            .color("black")
            .build();

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getCar() throws Exception {
        when(carDTOMapper.mapToDTO(car)).thenReturn(carDTO);
        when(carService.getCar(534555222)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/534555222"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(carDTO)));
    }
}