package com.vilensky.carrental.mappers;

import com.vilensky.carrental.dto.CarDTO;
import com.vilensky.carrental.entities.Car;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CarDTOMapper {

    public Car mapToCar(CarDTO carDTO){
        return Car.builder()
                .carNumber(carDTO.getCarNumber())
                .brand(carDTO.getBrand())
                .price(carDTO.getPrice())
                .model(carDTO.getModel())
                .motorVolume(carDTO.getMotorVolume())
                .build();
    }

    public CarDTO mapToDTO(Car car){
        return CarDTO.builder()
                .color(car.getColor())
                .motorVolume(car.getMotorVolume())
                .carNumber(car.getCarNumber())
                .model(car.getModel())
                .brand(car.getBrand())
                .price(car.getPrice())
                .build();
    }
}
