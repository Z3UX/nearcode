package com.nearcodeconsulting.exercise.domain.service;

import com.nearcodeconsulting.exercise.domain.exception.DomainException;
import com.nearcodeconsulting.exercise.domain.model.Car;
import com.nearcodeconsulting.exercise.domain.model.User;
import com.nearcodeconsulting.exercise.domain.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        Car existingCar = carRepository.findByLicensePlate(car.getLicensePlate());

        if (existingCar != null && !existingCar.equals(car)) {
            throw new DomainException("There's already a car with this license plate registered.");
        }
        
        return  carRepository.save(car);
    }

}
