//enable lombok: sett-build-enable annotation processing

package com.vilensky.carrental.controllers;

import com.vilensky.carrental.dto.CarDTO;
import com.vilensky.carrental.mappers.CarDTOMapper;
import com.vilensky.carrental.entities.Car;
import com.vilensky.carrental.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {//front<->back

    private final CarService carService;
    private final CarDTOMapper carDTOMapper;

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO){
        Car car = carService.saveCar(carDTOMapper.mapToCar(carDTO));//
        System.out.println("car created");
        return ResponseEntity.status(201).body(carDTOMapper.mapToDTO(car));
    }

    @GetMapping("/{carNumber}")
    public ResponseEntity<CarDTO> getCar(@PathVariable int carNumber){
        Car car = carService.getCar(carNumber);
        return ResponseEntity.ok(carDTOMapper.mapToDTO(car));
    }

    @DeleteMapping("/{carNumber}")
    public void deleteCar(@PathVariable int carNumber){
        carService.deleteCar(carNumber);
        System.out.println("car number " + carNumber + " deleted");
    }
}
