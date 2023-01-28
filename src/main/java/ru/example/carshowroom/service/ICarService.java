package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Car;

import java.util.List;

public interface ICarService {

    Car save(Car car);

    Car findCarById(Integer carId);

    void remove(Integer carId);

    List<Car> getAvailableCars();

    List<Car> getAllCars();

}
