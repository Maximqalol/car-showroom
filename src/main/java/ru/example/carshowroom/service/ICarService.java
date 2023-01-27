package ru.example.carshowroom.service;

import ru.example.carshowroom.data.dto.CarDto;

import java.util.List;

public interface ICarService {

    void create(CarDto carDto);

    CarDto findCarById(Integer carId);

    void update(CarDto carDto);

    void remove(Integer carId);

    List<CarDto> getAvailableCars();

    List<CarDto> getAllCars();

}
