package com.nearcodeconsulting.exercise.api.controller;

import com.nearcodeconsulting.exercise.api.model.CarRepresentationModel;
import com.nearcodeconsulting.exercise.domain.exception.DomainException;
import com.nearcodeconsulting.exercise.domain.model.Car;
import com.nearcodeconsulting.exercise.domain.model.User;
import com.nearcodeconsulting.exercise.domain.repository.CarRepository;
import com.nearcodeconsulting.exercise.domain.service.CarService;
import com.nearcodeconsulting.exercise.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/{userId}/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CarRepresentationModel> listAll(@PathVariable Long userId) {
        return toCollectionModel(carRepository.findCarsByUser(userService.get(userId)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarRepresentationModel add(@Valid @PathVariable Long userId, @RequestBody Car car) {
        User user = userService.get(userId);

        if (user == null) {
            throw new DomainException("There's no user with that ID.");
        }

        car.setUser(user);
        return toModel(carService.save(car));
    }

    private CarRepresentationModel toModel(Car car) {
        return modelMapper.map(car, CarRepresentationModel.class);
    }

    private List<CarRepresentationModel> toCollectionModel(List<Car> cars) {
        return cars.stream()
                .map(car -> toModel(car))
                .collect(Collectors.toList());
    }

}
