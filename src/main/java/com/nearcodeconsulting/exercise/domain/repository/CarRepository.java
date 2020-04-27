package com.nearcodeconsulting.exercise.domain.repository;

import com.nearcodeconsulting.exercise.domain.model.Car;
import com.nearcodeconsulting.exercise.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByLicensePlate(String licensePlate);

    List<Car> findCarsByUser(User user);
}
