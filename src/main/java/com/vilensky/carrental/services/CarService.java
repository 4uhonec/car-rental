package com.vilensky.carrental.services;

import com.vilensky.carrental.dto.CarDTO;
import com.vilensky.carrental.entities.Car;
import com.vilensky.carrental.exceptions.NoSuchCarException;
import com.vilensky.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {//encapsulate car [backend] logic
    private final CarRepository carRepository;

    @Transactional
    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public Car getCar(int carNumber){
        Optional<Car> byNumber = carRepository.findByCarNumber(carNumber);
        if(byNumber.isPresent()) return byNumber.get();
        throw new NoSuchCarException("Wrong car number " + carNumber);
    }

    @Transactional
    public void deleteCar(int carNumber){
        carRepository.deleteByCarNumber(carNumber);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
}
