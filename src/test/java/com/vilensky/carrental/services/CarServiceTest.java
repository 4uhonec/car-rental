package com.vilensky.carrental.services;

import com.vilensky.carrental.entities.Car;
import com.vilensky.carrental.exceptions.NoSuchCarException;
import com.vilensky.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    Car car = Car.builder()
            .carNumber(534555222)
            .brand("mazda")
            .model("2")
            .motorVolume(1.4)
            .price(200.0)
            .color("black")
            .build();

    @BeforeEach
    void saveToH2(){
        carRepository.save(car);
    }

    @Test
    void getCar() {
        int number = car.getCarNumber();
        Car resultCar = carService.getCar(number);

        assertAll(() -> assertEquals(car.getCarNumber(), resultCar.getCarNumber()),
                () -> assertEquals(car.getBrand(), resultCar.getBrand()),
                () -> assertEquals(car.getModel(), resultCar.getModel()),
                () -> assertEquals(car.getColor(), resultCar.getColor()),
                () -> assertEquals(car.getMotorVolume(), resultCar.getMotorVolume()),
                () -> assertEquals(car.getPrice(), resultCar.getPrice()),
                () -> assertNotNull(resultCar.getId()));
    }

    @Test
    void getCarNoSuchCarException(){
        assertThrows(NoSuchCarException.class, () -> carService.getCar(car.getCarNumber() + 1));
    }
}