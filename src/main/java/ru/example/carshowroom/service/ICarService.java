package ru.example.carshowroom.service;


import ru.example.carshowroom.data.dto.CarDto;

import java.util.List;

public interface ICarService {

    CarDto create(CarDto carDto);

    CarDto findCarById(Integer carId);

    void remove(Integer carId);

    List<CarDto> getAvailableCars();

    List<CarDto> getAllCars();

}
