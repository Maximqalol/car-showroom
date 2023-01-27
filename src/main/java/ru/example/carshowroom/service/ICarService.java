package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Car;

import java.util.List;

public interface ICarService {

    Car create(Car car);

    Car findCarById(Integer carId);

    Car update(Car car);

    void remove(Integer carId);

    List<Car> getAvailableCars();

    List<Car> getAllCars();

}
